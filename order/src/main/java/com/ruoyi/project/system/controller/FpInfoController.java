package com.ruoyi.project.system.controller;

import java.util.List;

import com.ruoyi.common.utils.SecurityUtils;
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
import com.ruoyi.project.system.domain.FpInfo;
import com.ruoyi.project.system.service.IFpInfoService;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.framework.web.page.TableDataInfo;

/**
 * 发票建档Controller
 * 
 * @author ruoyi
 * @date 2021-01-06
 */
@RestController
@RequestMapping("/system/fpInfo")
public class FpInfoController extends BaseController
{
    @Autowired
    private IFpInfoService fpInfoService;

    /**
     * 查询发票建档列表
     */
    @PreAuthorize("@ss.hasPermi('system:fpInfo:list')")
    @GetMapping("/list")
    public TableDataInfo list(FpInfo fpInfo)
    {
        startPage();
        List<FpInfo> list = fpInfoService.selectFpInfoList(fpInfo);
        return getDataTable(list);
    }

    /**
     * 导出发票建档列表
     */
    @PreAuthorize("@ss.hasPermi('system:fpInfo:export')")
    @Log(title = "发票建档", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(FpInfo fpInfo)
    {
        List<FpInfo> list = fpInfoService.selectFpInfoList(fpInfo);
        ExcelUtil<FpInfo> util = new ExcelUtil<FpInfo>(FpInfo.class);
        return util.exportExcel(list, "fpInfo");
    }

    /**
     * 获取发票建档详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:fpInfo:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Integer id)
    {
        return AjaxResult.success(fpInfoService.selectFpInfoById(id));
    }

    @GetMapping(value = "/getFpInfoByKh/{khCode}")
    public AjaxResult getFpInfoByKh(@PathVariable("khCode") String khCode)
    {
        return AjaxResult.success(fpInfoService.selectFpInfoByKh(khCode));
    }

    /**
     * 新增发票建档
     */
    @PreAuthorize("@ss.hasPermi('system:fpInfo:add')")
    @Log(title = "发票建档", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody FpInfo fpInfo)
    {
        int result=fpInfoService.selectFpInfoByKhName(-1,fpInfo.getKhCode());
        if(result>0){
            return toAjaxByError("该供应商发票信息已存在!");
        }
        fpInfo.setCreateBy(SecurityUtils.getUsername());
        return toAjax(fpInfoService.insertFpInfo(fpInfo));
    }

    /**
     * 修改发票建档
     */
    @PreAuthorize("@ss.hasPermi('system:fpInfo:edit')")
    @Log(title = "发票建档", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody FpInfo fpInfo)
    {
        int result=fpInfoService.selectFpInfoByKhName(fpInfo.getId(),fpInfo.getKhCode());
        if(result>0){
            return toAjaxByError("该供应商发票信息已存在!");
        }
        fpInfo.setUpdateBy(SecurityUtils.getUsername());
        return toAjax(fpInfoService.updateFpInfo(fpInfo));
    }

    /**
     * 删除发票建档
     */
    @PreAuthorize("@ss.hasPermi('system:fpInfo:remove')")
    @Log(title = "发票建档", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Integer[] ids)
    {
        return toAjax(fpInfoService.deleteFpInfoByIds(ids));
    }
}
