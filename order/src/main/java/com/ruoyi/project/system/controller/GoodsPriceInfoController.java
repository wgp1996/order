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
import com.ruoyi.project.system.domain.GoodsPriceInfo;
import com.ruoyi.project.system.service.IGoodsPriceInfoService;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.framework.web.page.TableDataInfo;

/**
 * 商品价格管理Controller
 * 
 * @author ruoyi
 * @date 2021-01-05
 */
@RestController
@RequestMapping("/system/goodsPrice")
public class GoodsPriceInfoController extends BaseController
{
    @Autowired
    private IGoodsPriceInfoService goodsPriceInfoService;

    /**
     * 查询商品价格管理列表
     */
    @PreAuthorize("@ss.hasPermi('system:goodsPrice:list')")
    @GetMapping("/list")
    public TableDataInfo list(GoodsPriceInfo goodsPriceInfo)
    {
        startPage();
        List<GoodsPriceInfo> list = goodsPriceInfoService.selectGoodsPriceInfoList(goodsPriceInfo);
        return getDataTable(list);
    }

    /**
     * 导出商品价格管理列表
     */
    @PreAuthorize("@ss.hasPermi('system:goodsPrice:export')")
    @Log(title = "商品价格管理", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(GoodsPriceInfo goodsPriceInfo)
    {
        List<GoodsPriceInfo> list = goodsPriceInfoService.selectGoodsPriceInfoList(goodsPriceInfo);
        ExcelUtil<GoodsPriceInfo> util = new ExcelUtil<GoodsPriceInfo>(GoodsPriceInfo.class);
        return util.exportExcel(list, "goodsPrice");
    }

    /**
     * 获取商品价格管理详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:goodsPrice:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(goodsPriceInfoService.selectGoodsPriceInfoById(id));
    }

    @GetMapping(value = "/selectGoodsPrice/{goodsCode}/{khCode}")
    public AjaxResult selectGoodsPrice(@PathVariable("goodsCode") String goodsCode,@PathVariable("khCode") String khCode)
    {
        return AjaxResult.success(goodsPriceInfoService.selectGoodsPrice(goodsCode,khCode));
    }

    /**
     * 新增商品价格管理
     */
    @PreAuthorize("@ss.hasPermi('system:goodsPrice:add')")
    @Log(title = "商品价格管理", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody GoodsPriceInfo goodsPriceInfo)
    {
        if(goodsPriceInfoService.checkGoods(goodsPriceInfo.getGoodsCode(),-1)>0){
            return toAjaxByError("商品重复");
        }
        goodsPriceInfo.setCreateBy(SecurityUtils.getUsername());
        return toAjax(goodsPriceInfoService.insertGoodsPriceInfo(goodsPriceInfo));
    }

    /**
     * 修改商品价格管理
     */
    @PreAuthorize("@ss.hasPermi('system:goodsPrice:edit')")
    @Log(title = "商品价格管理", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody GoodsPriceInfo goodsPriceInfo)
    {
        if(goodsPriceInfoService.checkGoods(goodsPriceInfo.getGoodsCode(),goodsPriceInfo.getId().intValue())>0){
            return toAjaxByError("商品重复");
        }
        goodsPriceInfo.setUpdateBy(SecurityUtils.getUsername());
        return toAjax(goodsPriceInfoService.updateGoodsPriceInfo(goodsPriceInfo));
    }

    /**
     * 删除商品价格管理
     */
    @PreAuthorize("@ss.hasPermi('system:goodsPrice:remove')")
    @Log(title = "商品价格管理", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(goodsPriceInfoService.deleteGoodsPriceInfoByIds(ids));
    }
}
