package com.ruoyi.project.system.controller;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import com.alibaba.fastjson.JSONArray;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.framework.aspectj.lang.annotation.DataScope;
import com.ruoyi.project.system.domain.CgRkdSingle;
import com.ruoyi.project.system.domain.CgRkdSingleChild;
import com.ruoyi.project.system.domain.CgddInfo;
import com.ruoyi.project.system.service.ICgRkdSingleService;
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
import com.ruoyi.project.system.domain.CgddInfoChild;
import com.ruoyi.project.system.service.ICgddInfoChildService;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.framework.web.page.TableDataInfo;

/**
 * 采购订单子表Controller
 * 
 * @author ruoyi
 * @date 2021-01-05
 */
@RestController
@RequestMapping("/system/cgddInfoChild")
public class CgddInfoChildController extends BaseController
{
    @Autowired
    private ICgddInfoChildService cgddInfoChildService;
    @Autowired
    private ICgRkdSingleService cgRkdService;
    /**
     * 查询采购订单子表列表
     */
    @PreAuthorize("@ss.hasPermi('system:cgddInfoChild:list')")
    @DataScope(deptAlias = "d", userAlias = "u")
    @GetMapping("/list")
    public TableDataInfo list(CgddInfoChild cgddInfoChild)
    {
        startPage();
        List<CgddInfoChild> list = cgddInfoChildService.selectCgddInfoChildList(cgddInfoChild);
        return getDataTable(list);
    }

    /**
     * 订单汇总
     */
    @GetMapping("/allList")
    public TableDataInfo allList(CgddInfoChild cgddInfoChild)
    {
        startPage();
        List<CgddInfoChild> list = cgddInfoChildService.selectCgddAllList(cgddInfoChild);
        return getDataTable(list);
    }

    /**
     * 导出采购订单子表列表
     */
    @PreAuthorize("@ss.hasPermi('system:cgddInfoChild:export')")
    @Log(title = "采购订单子表", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(CgddInfoChild cgddInfoChild)
    {
        List<CgddInfoChild> list = cgddInfoChildService.selectCgddInfoChildList(cgddInfoChild);
        ExcelUtil<CgddInfoChild> util = new ExcelUtil<CgddInfoChild>(CgddInfoChild.class);
        return util.exportExcel(list, "cgddInfoChild");
    }

    /**
     * 获取采购订单子表详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:cgddInfoChild:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(cgddInfoChildService.selectCgddInfoChildById(id));
    }

    /**
     * 新增采购订单子表
     */
    @PreAuthorize("@ss.hasPermi('system:cgddInfoChild:add')")
    @Log(title = "采购订单子表", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody CgddInfoChild cgddInfoChild)
    {
        List<CgddInfoChild> childList= JSONArray.parseArray(cgddInfoChild.getRows(),CgddInfoChild.class);
        for(CgddInfoChild child:childList){
            child.setCreateBy(SecurityUtils.getUsername());
            child.setDjNumber(StringUtils.getRandomCode("CG"));
            child.setStatus(0);
            child.setShStatus(0);
            child.setRkStatus(0);
            cgddInfoChildService.insertCgddInfoChild(child);
        }
        return toAjaxBySuccess("操作成功!");
    }

    @GetMapping("/getCgddInfoChild")
    public TableDataInfo getCgddInfoChild(String djNumber)
    {
        List<CgddInfoChild> list = cgddInfoChildService.selectCgddInfoChildByNum(djNumber);
        return getDataTable(list);
    }
    /**
     * 修改采购订单子表
     */
    @PreAuthorize("@ss.hasPermi('system:cgddInfoChild:edit')")
    @Log(title = "采购订单子表", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody CgddInfoChild cgddInfoChild)
    {
        //未改变状态可修改所有
        if(cgddInfoChild.getRkStatus()==0&&cgddInfoChild.getShStatus()==0&&cgddInfoChild.getStatus()==0){
            cgddInfoChild.setCreateBy(SecurityUtils.getUsername());
            cgddInfoChildService.updateCgddInfoChild(cgddInfoChild);
        }else{
            CgddInfoChild rkTimeChild=new CgddInfoChild();
            rkTimeChild.setId(cgddInfoChild.getId());
            //已入库未交货可以修改入库日期
            if(cgddInfoChild.getRkStatus()==1&&cgddInfoChild.getShStatus()==0){
                rkTimeChild.setRkTime(cgddInfoChild.getRkTime());
            }
            rkTimeChild.setRemark(cgddInfoChild.getRemark());
            cgddInfoChildService.updateCgddInfoChild(rkTimeChild);
            rkTimeChild=null;
        }
        return toAjaxBySuccess("操作成功!");
    }

    /**
     * 修改发票
     */
    @PreAuthorize("@ss.hasPermi('system:cgddInfoChild:edit')")
    @Log(title = "修改发票", businessType = BusinessType.UPDATE)
    @PutMapping("/editFp")
    public AjaxResult editFp(@RequestBody CgddInfoChild cgddInfoChild)
    {
        CgddInfoChild item=cgddInfoChildService.selectCgddInfoChildById(cgddInfoChild.getId());
        if(item.getRkStatus()!=1){
            return  toAjaxByError("该采购订单未入库,禁止开票!");
        }
        //检查是否为已生效的订单
        if(item.getFpNumber()!=null&&!"".equalsIgnoreCase(item.getFpNumber())){
            return  toAjaxByError("禁止重复开票!");
        }
        return toAjax(cgddInfoChildService.updateCgddInfoChild(cgddInfoChild));
    }

    /**
     * 删除采购订单子表
     */
    @PreAuthorize("@ss.hasPermi('system:cgddInfoChild:remove')")
    @Log(title = "采购订单子表", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        //检查提交状态
        for(int i=0;i<ids.length;i++){
            CgddInfoChild info = cgddInfoChildService.selectCgddInfoChildById(ids[i]);
            if(info.getStatus()>0||info.getRkStatus()>0||info.getShStatus()>0){
                return toAjaxByError(info.getDjNumber()+"：该单据状态禁止删除!");
            }
        }
        return toAjax(cgddInfoChildService.deleteCgddInfoChildByIds(ids));
    }

    /**
     * 采购订单确认入库
     */
    @PreAuthorize("@ss.hasPermi('system:cgddInfoChild:effect')")
    @Log(title = "确认入库", businessType = BusinessType.UPDATE)
    @DeleteMapping("/rkEffect/{ids}")
    public AjaxResult rkEffect(@PathVariable Long[] ids)
    {
        //检查状态
        for(int i=0;i<ids.length;i++){
            CgddInfoChild info = cgddInfoChildService.selectCgddInfoChildById(ids[i]);
            if(info.getStatus()!=0){
                return toAjaxByError(info.getDjNumber()+"：该单据状态禁止操作!");
            }
            //修改收货状态
            info.setRkStatus(1);
            cgddInfoChildService.updateCgddInfoChild(info);
        }
        return toAjaxBySuccess("入库成功!");
    }
    //集合去重
    public static List removeDuplicate(List list) {
        HashSet hashSet = new HashSet(list) ;
        list.clear();
        list.addAll(hashSet);
        return list;
    }
    /**
     * 采购订单确认交货
     */
    @PreAuthorize("@ss.hasPermi('system:cgddInfoChild:effect')")
    @Log(title = "进货单生效", businessType = BusinessType.UPDATE)
    @DeleteMapping("/shEffect/{ids}")
    public AjaxResult shEffect(@PathVariable Long[] ids)
    {
        List<String> numList=new ArrayList<>();
        //检查状态
        for(int i=0;i<ids.length;i++){
            CgddInfoChild info = cgddInfoChildService.selectCgddInfoChildById(ids[i]);
            if(info.getRkStatus()==0||info.getStatus()>=1){
                return toAjaxByError(info.getDjNumber()+"：该单据状态禁止操作!");
            }
            numList.add(info.getOrderNumber());
            //修改收货状态
            info.setShStatus(1);
            cgddInfoChildService.updateCgddInfoChild(info);
        }
        //修改采购订单状态
        cgddInfoChildService.updateCgddInfoChildStatus(ids);
        //去重
        System.out.println(numList.size());
        numList=removeDuplicate(numList);
        System.out.println(numList.size());
        //检查订单是否已全部采购
        for(String info:numList){
            int result=cgddInfoChildService.checkAllOrder(info);
            System.out.println(result);
            if(result==1){
                CgRkdSingle cgRkdSingle=new CgRkdSingle();
                cgRkdSingle.setStatus(2);
                cgRkdSingle.setDjNumber(info);
                cgRkdService.updateCgRkdSingleStatusByNumber(cgRkdSingle);
            }
        }
        return toAjaxBySuccess("交货成功!");
    }
}
