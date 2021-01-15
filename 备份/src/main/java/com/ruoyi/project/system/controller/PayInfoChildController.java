package com.ruoyi.project.system.controller;

import java.util.List;
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
import com.ruoyi.project.system.domain.PayInfoChild;
import com.ruoyi.project.system.service.IPayInfoChildService;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.framework.web.page.TableDataInfo;

/**
 * 付款单子表Controller
 * 
 * @author ruoyi
 * @date 2021-01-06
 */
@RestController
@RequestMapping("/system/payInfoChild")
public class PayInfoChildController extends BaseController
{
    @Autowired
    private IPayInfoChildService payInfoChildService;

    /**
     * 查询付款单子表列表
     */
    @PreAuthorize("@ss.hasPermi('system:payInfoChild:list')")
    @GetMapping("/list")
    public TableDataInfo list(PayInfoChild payInfoChild)
    {
        startPage();
        List<PayInfoChild> list = payInfoChildService.selectPayInfoChildList(payInfoChild);
        return getDataTable(list);
    }

    /**
     * 导出付款单子表列表
     */
    @PreAuthorize("@ss.hasPermi('system:payInfoChild:export')")
    @Log(title = "付款单子表", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(PayInfoChild payInfoChild)
    {
        List<PayInfoChild> list = payInfoChildService.selectPayInfoChildList(payInfoChild);
        ExcelUtil<PayInfoChild> util = new ExcelUtil<PayInfoChild>(PayInfoChild.class);
        return util.exportExcel(list, "payInfoChild");
    }

    /**
     * 获取付款单子表详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:payInfoChild:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Integer id)
    {
        return AjaxResult.success(payInfoChildService.selectPayInfoChildById(id));
    }

    /**
     * 新增付款单子表
     */
    @PreAuthorize("@ss.hasPermi('system:payInfoChild:add')")
    @Log(title = "付款单子表", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody PayInfoChild payInfoChild)
    {
        return toAjax(payInfoChildService.insertPayInfoChild(payInfoChild));
    }

    /**
     * 修改付款单子表
     */
    @PreAuthorize("@ss.hasPermi('system:payInfoChild:edit')")
    @Log(title = "付款单子表", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody PayInfoChild payInfoChild)
    {
        return toAjax(payInfoChildService.updatePayInfoChild(payInfoChild));
    }

    /**
     * 删除付款单子表
     */
    @PreAuthorize("@ss.hasPermi('system:payInfoChild:remove')")
    @Log(title = "付款单子表", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Integer[] ids)
    {
        return toAjax(payInfoChildService.deletePayInfoChildByIds(ids));
    }
}
