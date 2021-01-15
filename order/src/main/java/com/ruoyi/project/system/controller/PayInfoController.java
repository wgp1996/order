package com.ruoyi.project.system.controller;

import java.util.List;

import com.alibaba.fastjson.JSONArray;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.project.system.domain.CgddInfo;
import com.ruoyi.project.system.domain.CgddInfoChild;
import com.ruoyi.project.system.domain.PayInfoChild;
import com.ruoyi.project.system.service.IPayInfoChildService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.ruoyi.framework.aspectj.lang.annotation.Log;
import com.ruoyi.framework.aspectj.lang.enums.BusinessType;
import com.ruoyi.project.system.domain.PayInfo;
import com.ruoyi.project.system.service.IPayInfoService;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.framework.web.page.TableDataInfo;

/**
 * 付款单Controller
 * 
 * @author ruoyi
 * @date 2021-01-06
 */
@RestController
@RequestMapping("/system/payInfo")
public class PayInfoController extends BaseController
{
    @Autowired
    private IPayInfoService payInfoService;
    @Autowired
    private IPayInfoChildService payInfoChildService;

    /**
     * 查询付款单列表
     */
    @PreAuthorize("@ss.hasPermi('system:payInfo:list')")
    @GetMapping("/list")
    public TableDataInfo list(PayInfo payInfo)
    {
        startPage();
        List<PayInfo> list = payInfoService.selectPayInfoList(payInfo);
        for(PayInfo info:list){
            info.setChildrenList(payInfoChildService.selectPayInfoChildByNum(info.getDjNumber()));
        }
        return getDataTable(list);
    }

    @GetMapping("/getPayInfoChild")
    public TableDataInfo getPayInfoChild(String djNumber)
    {
        List<PayInfoChild> list = payInfoChildService.selectPayInfoChildByNum(djNumber);
        return getDataTable(list);
    }

    /**
     * 导出付款单列表
     */
    @PreAuthorize("@ss.hasPermi('system:payInfo:export')")
    @Log(title = "付款单", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(PayInfo payInfo)
    {
        List<PayInfo> list = payInfoService.selectPayInfoList(payInfo);
        ExcelUtil<PayInfo> util = new ExcelUtil<PayInfo>(PayInfo.class);
        return util.exportExcel(list, "payInfo");
    }

    /**
     * 获取付款单详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:payInfo:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Integer id)
    {
        return AjaxResult.success(payInfoService.selectPayInfoById(id));
    }

    /**
     * 新增付款单
     */
    @PreAuthorize("@ss.hasPermi('system:payInfo:add')")
    @Log(title = "付款单", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody PayInfo payInfo)
    {
        if(payInfo.getRows()==""){
            return  toAjaxByError("明细信息不能为空!");
        }
        payInfo.setDjNumber(StringUtils.getRandomCode("FK"));
        payInfo.setStatus(0);
        payInfo.setCreateBy(SecurityUtils.getUsername());
        List<PayInfoChild> childList= JSONArray.parseArray(payInfo.getRows(),PayInfoChild.class);
        for(PayInfoChild child:childList){
            child.setCreateBy(SecurityUtils.getUsername());
            child.setDjNumber(payInfo.getDjNumber());
            child.setCreateTime(DateUtils.getNowDate());
            payInfoChildService.insertPayInfoChild(child);
        }
        return toAjax(payInfoService.insertPayInfo(payInfo));
    }

    /**
     * 修改付款单
     */
    @PreAuthorize("@ss.hasPermi('system:payInfo:edit')")
    @Log(title = "付款单", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody PayInfo payInfo)
    {
        //检查是否为已生效的合同
        if(payInfo.getStatus()>=1){
            return  toAjaxByError("该状态禁止修改!");
        }
        if(payInfo.getRows()==""){
            return  toAjaxByError("明细信息不能为空!");
        }
        payInfo.setUpdateBy(SecurityUtils.getUsername());
        List<PayInfoChild> childList= JSONArray.parseArray(payInfo.getRows(),PayInfoChild.class);
        for(PayInfoChild child:childList){
            if(child.getId()!=null){
                child.setCreateBy(SecurityUtils.getUsername());
                child.setDjNumber(payInfo.getDjNumber());
                payInfoChildService.updatePayInfoChild(child);
            }else{
                child.setCreateBy(SecurityUtils.getUsername());
                child.setDjNumber(payInfo.getDjNumber());
                child.setCreateTime(DateUtils.getNowDate());
                payInfoChildService.insertPayInfoChild(child);
            }
        }
        return toAjax(payInfoService.updatePayInfo(payInfo));
    }
    /**
     * 付款单生效
     */
    @PreAuthorize("@ss.hasPermi('system:payInfo:effect')")
    @Log(title = "进货单生效", businessType = BusinessType.UPDATE)
    @DeleteMapping("/effect/{ids}")
    public AjaxResult effect(@PathVariable Integer[] ids)
    {
        //检查提交状态
        for(int i=0;i<ids.length;i++){
            PayInfo info = payInfoService.selectPayInfoById(ids[i]);
            if(info.getStatus()!=0){
                return toAjaxByError(info.getDjNumber()+"：该单据状态禁止提交!");
            }
        }
        return toAjax(payInfoService.updatePayInfoStatus(ids));
    }
    /**
     * 取消付款单生效
     */
    @PreAuthorize("@ss.hasPermi('system:payInfo:cancel')")
    @Log(title = "取消生效", businessType = BusinessType.UPDATE)
    @DeleteMapping("/cancel/{ids}")
    public AjaxResult cancel(@PathVariable Integer[] ids)
    {
        //检查提交状态
        for(int i=0;i<ids.length;i++){
            PayInfo info = payInfoService.selectPayInfoById(ids[i]);
            if(info.getStatus()!=1){
                return toAjaxByError(info.getDjNumber()+"：该单据状态禁止取消!");
            }
        }
        return toAjax(payInfoService.cancelPayInfoStatus(ids));
    }
    /**
     * 删除付款单
     */
    @PreAuthorize("@ss.hasPermi('system:payInfo:remove')")
    @Log(title = "付款单", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Integer[] ids)
    {
        for(int i=0;i<ids.length;i++){
            PayInfo info = payInfoService.selectPayInfoById(ids[i]);
            if(info.getStatus()!=0){
                return toAjaxByError(info.getDjNumber()+"：该单据状态禁止删除!");
            }
        }
        //删除子表信息
        if(ids.length>0){
            payInfoChildService.deletePayInfoChildByPid(ids);
        }
        int result=payInfoService.deletePayInfoByIds(ids);
        if(result>0){
            return toAjaxBySuccess("删除成功!");
        }else{
            return  toAjaxByError("删除失败!");
        }
    }
}
