package com.ruoyi.project.system.controller;

import java.util.List;

import com.alibaba.fastjson.JSONArray;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.framework.WebSocket.WebSocketServer;
import com.ruoyi.framework.aspectj.lang.annotation.DataScope;
import com.ruoyi.project.system.domain.CgRkdSingle;
import com.ruoyi.project.system.domain.CgRkdSingleChild;
import com.ruoyi.project.system.domain.CgddInfoChild;
import com.ruoyi.project.system.service.ICgddInfoChildService;
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
import com.ruoyi.project.system.domain.CgddInfo;
import com.ruoyi.project.system.service.ICgddInfoService;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.framework.web.page.TableDataInfo;

/**
 * 采购订单主表Controller
 * 
 * @author ruoyi
 * @date 2021-01-05
 */
@RestController
@RequestMapping("/system/cgddInfo")
public class CgddInfoController extends BaseController
{
    @Autowired
    private ICgddInfoService cgddInfoService;
    @Autowired
    private ICgddInfoChildService cgddInfoChildService;
    /**
     * 查询采购订单主表列表
     */
    @PreAuthorize("@ss.hasPermi('system:cgddInfo:list')")
    @GetMapping("/list")
    @DataScope(deptAlias = "d", userAlias = "u")
    public TableDataInfo list(CgddInfo cgddInfo)
    {
        startPage();
        List<CgddInfo> list = cgddInfoService.selectCgddInfoList(cgddInfo);
        for(CgddInfo info:list){
            info.setChildrenList(cgddInfoChildService.selectCgddInfoChildByNum(info.getDjNumber()));
        }
        return getDataTable(list);
    }

    /**
     * 导出采购订单主表列表
     */
    @PreAuthorize("@ss.hasPermi('system:cgddInfo:export')")
    @Log(title = "采购订单主表", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(CgddInfo cgddInfo)
    {
        List<CgddInfo> list = cgddInfoService.selectCgddInfoList(cgddInfo);
        ExcelUtil<CgddInfo> util = new ExcelUtil<CgddInfo>(CgddInfo.class);
        return util.exportExcel(list, "cgddInfo");
    }

    /**
     * 获取采购订单主表详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:cgddInfo:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        try{
            /*WebSocketServer WebSocketServer=new WebSocketServer();
            String text="admin 你好！ 这是websocket单人发送！";
            WebSocketServer.sendOneMessage("admin",text);

            String text1="你们好！这是websocket群体发送！";
            WebSocketServer.sendAllMessage(text1);*/
        }catch (Exception e){
            e.printStackTrace();
        }
        return AjaxResult.success(cgddInfoService.selectCgddInfoById(id));
    }
    /**
     * 获取首页信息
     */
    @GetMapping(value = "/index")
    public AjaxResult index()
    {
        return AjaxResult.success(cgddInfoService.selectIndexCount());
    }
    /**
     * 获取首页待办列表
     */
    @GetMapping("/indexList/{status}")
    public TableDataInfo indexList(@PathVariable("status") Integer status)
    {
        startPage();
        List<CgddInfoChild> list = cgddInfoChildService.selectCgddMessageList(status);
        return getDataTable(list);
    }
    /**
     * 采购订单选择订单
     */
    @GetMapping("/cgddList")
    @DataScope(deptAlias = "d", userAlias = "u")
    public TableDataInfo cgddList(CgddInfoChild cgddInfoChild)
    {
        startPage();
        List<CgddInfoChild> list = cgddInfoChildService.selectCgddOrderList(cgddInfoChild);
        return getDataTable(list);
    }
    /**
     * 新增采购订单主表
     */
    @PreAuthorize("@ss.hasPermi('system:cgddInfo:add')")
    @Log(title = "采购订单主表", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody CgddInfo cgddInfo)
    {
        if(cgddInfo.getRows()==""){
            return  toAjaxByError("明细信息不能为空!");
        }
        cgddInfo.setDjNumber(StringUtils.getRandomCode("CG"));
        cgddInfo.setStatus(0);
        cgddInfo.setCreateBy(SecurityUtils.getUsername());
        List<CgddInfoChild> childList= JSONArray.parseArray(cgddInfo.getRows(),CgddInfoChild.class);
        for(CgddInfoChild child:childList){
            child.setCreateBy(SecurityUtils.getUsername());
            child.setDjNumber(cgddInfo.getDjNumber());
            child.setCreateTime(DateUtils.getNowDate());
            cgddInfoChildService.insertCgddInfoChild(child);
        }
        return toAjax(cgddInfoService.insertCgddInfo(cgddInfo));
    }

    /**
     * 修改采购订单主表
     */
    @PreAuthorize("@ss.hasPermi('system:cgddInfo:edit')")
    @Log(title = "采购订单主表", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody CgddInfo cgddInfo)
    {
        //检查是否为已生效的合同
        if(cgddInfo.getStatus()>=1){
            return  toAjaxByError("该状态禁止修改!");
        }
        if(cgddInfo.getRows()==""){
            return  toAjaxByError("明细信息不能为空!");
        }
        cgddInfo.setUpdateBy(SecurityUtils.getUsername());
        List<CgddInfoChild> childList= JSONArray.parseArray(cgddInfo.getRows(),CgddInfoChild.class);
        for(CgddInfoChild child:childList){
            if(child.getId()!=null){
                child.setCreateBy(SecurityUtils.getUsername());
                child.setDjNumber(cgddInfo.getDjNumber());
                cgddInfoChildService.updateCgddInfoChild(child);
            }else{
                child.setCreateBy(SecurityUtils.getUsername());
                child.setDjNumber(cgddInfo.getDjNumber());
                child.setCreateTime(DateUtils.getNowDate());
                cgddInfoChildService.insertCgddInfoChild(child);
            }
        }
        return toAjax(cgddInfoService.updateCgddInfo(cgddInfo));
    }
    /**
     * 采购订单生效
     */
    @PreAuthorize("@ss.hasPermi('system:cgddInfo:effect')")
    @Log(title = "进货单生效", businessType = BusinessType.UPDATE)
    @DeleteMapping("/effect/{ids}")
    public AjaxResult effect(@PathVariable Long[] ids)
    {
        //检查提交状态
        for(int i=0;i<ids.length;i++){
            CgddInfo info = cgddInfoService.selectCgddInfoById(ids[i]);
            if(info.getStatus()!=0){
                return toAjaxByError(info.getDjNumber()+"：该单据状态禁止提交!");
            }
        }
        return toAjax(cgddInfoService.updateCgddInfoStatus(ids));
    }
    /**
     * 取消订单生效
     */
    @PreAuthorize("@ss.hasPermi('system:cgddInfo:cancel')")
    @Log(title = "采购订单取消生效", businessType = BusinessType.UPDATE)
    @DeleteMapping("/cancel/{ids}")
    public AjaxResult cancel(@PathVariable Long[] ids)
    {
        //检查提交状态
        for(int i=0;i<ids.length;i++){
            CgddInfo info = cgddInfoService.selectCgddInfoById(ids[i]);
            if(info.getStatus()!=1){
                return toAjaxByError(info.getDjNumber()+"：该单据状态禁止取消!");
            }
            //检测是否存在下级采购订单
            int num=cgddInfoService.checkChildOrder(info.getDjNumber());
            if(num>0){
                return toAjaxByError(info.getDjNumber()+"：该单据存在下级关联付款单!");
            }
        }
        return toAjax(cgddInfoService.cancleCgddInfoStatus(ids));
    }
    /**
     * 删除采购订单主表
     */
    @PreAuthorize("@ss.hasPermi('system:cgddInfo:remove')")
    @Log(title = "采购订单主表", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        for(int i=0;i<ids.length;i++){
            CgddInfo info = cgddInfoService.selectCgddInfoById(ids[i]);
            if(info.getStatus()!=0){
                return toAjaxByError(info.getDjNumber()+"：该单据状态禁止删除!");
            }
        }
        //删除子表信息
        if(ids.length>0){
            cgddInfoChildService.deleteCgddInfoChildByPid(ids);
        }
        int result=cgddInfoService.deleteCgddInfoByIds(ids);
        if(result>0){
            return toAjaxBySuccess("删除成功!");
        }else{
            return  toAjaxByError("删除失败!");
        }
    }
}
