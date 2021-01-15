package com.ruoyi.project.system.controller;

import java.util.List;

import com.ruoyi.project.system.domain.CgRkdSingleChild;
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

    /**
     * 查询采购订单子表列表
     */
    @PreAuthorize("@ss.hasPermi('system:cgddInfoChild:list')")
    @GetMapping("/list")
    public TableDataInfo list(CgddInfoChild cgddInfoChild)
    {
        startPage();
        List<CgddInfoChild> list = cgddInfoChildService.selectCgddInfoChildList(cgddInfoChild);
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
        return toAjax(cgddInfoChildService.insertCgddInfoChild(cgddInfoChild));
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
        return toAjax(cgddInfoChildService.deleteCgddInfoChildByIds(ids));
    }
}
