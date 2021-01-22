package com.ruoyi.project.system.controller;

import java.io.File;
import java.io.IOException;
import java.util.*;
import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.common.utils.code.MatrixToImageWriter;
import com.ruoyi.framework.aspectj.lang.annotation.DataScope;
import com.ruoyi.framework.config.RuoYiConfig;
import com.ruoyi.framework.config.ServerConfig;
import com.ruoyi.project.system.domain.GoodsType;
import com.ruoyi.project.system.service.IGoodsTypeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
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
import com.ruoyi.project.system.domain.GoodsInfo;
import com.ruoyi.project.system.service.IGoodsInfoService;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.framework.web.page.TableDataInfo;

/**
 * 商品建档Controller
 * 
 * @author ruoyi
 * @date 2020-08-14
 */
@RestController
@RequestMapping("/system/goods")
public class GoodsInfoController extends BaseController
{
    @Autowired
    private IGoodsInfoService goodsInfoService;
    @Autowired
    private IGoodsTypeService goodsTypeService;
    @Autowired
    private ServerConfig serverConfig;

    /**
     * 查询商品建档列表
     */
    @PreAuthorize("@ss.hasPermi('system:goods:list')")
    @GetMapping("/list")
    @DataScope(deptAlias = "d", userAlias = "u")
    public TableDataInfo list(GoodsInfo goodsInfo)
    {
        startPage();
        List<GoodsInfo> list = goodsInfoService.selectGoodsInfoList(goodsInfo);
        return getDataTable(list);
    }

    /**
     * 查询商品价格列表
     */
    @PreAuthorize("@ss.hasPermi('system:goods:list')")
    @GetMapping("/priceList")
    @DataScope(deptAlias = "d", userAlias = "u")
    public TableDataInfo priceList(GoodsInfo goodsInfo)
    {
        startPage();
        List<GoodsInfo> list = goodsInfoService.selectGoodsInfoPriceList(goodsInfo);
        return getDataTable(list);
    }

    @GetMapping("/goodsListAll")
    @DataScope(deptAlias = "d", userAlias = "u")
    public TableDataInfo goodsListAll(GoodsInfo goodsInfo)
    {
        List<GoodsInfo> list = goodsInfoService.selectGoodsInfoList(goodsInfo);
        return getDataTable(list);
    }

    /**
     * 导出商品建档列表
     */
    @PreAuthorize("@ss.hasPermi('system:goods:export')")
    @Log(title = "商品建档", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(GoodsInfo goodsInfo)
    {
        List<GoodsInfo> list = goodsInfoService.selectGoodsInfoList(goodsInfo);
        ExcelUtil<GoodsInfo> util = new ExcelUtil<GoodsInfo>(GoodsInfo.class);
        return util.exportExcel(list, "goods");
    }

    /**
     * 获取商品建档详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:goods:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Integer id)
    {
        return AjaxResult.success(goodsInfoService.selectGoodsInfoById(id));
    }

    /**
     * 新增商品建档
     */
    @PreAuthorize("@ss.hasPermi('system:goods:add')")
    @Log(title = "商品建档", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody GoodsInfo goodsInfo)
    {
        GoodsInfo info=goodsInfoService.selectGoodsInfoByName(goodsInfo.getGoodsName(),-1);
        if(info!=null) {
            return  toAjaxByError("商品重复！");
        }else{
            goodsInfo.setType(0);
            goodsInfo.setCreateBy(SecurityUtils.getUsername());
            goodsInfo.setGoodsCode(StringUtils.getRandomCode("SPM"));
            if(goodsInfo.getGoodsCodeImg()!=""){
                GoodsInfo codeInfo=goodsInfoService.selectGoodsInfoByCode(goodsInfo.getGoodsCodeImg(),-1);
                if(codeInfo!=null) {
                    return  toAjaxByError("料号重复!");
                }
            }
            return toAjax(goodsInfoService.insertGoodsInfo(goodsInfo));
        }
    }

    /**
     * 修改商品建档
     */
    @PreAuthorize("@ss.hasPermi('system:goods:edit')")
    @Log(title = "商品建档", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody GoodsInfo goodsInfo)
    {
        GoodsInfo info=goodsInfoService.selectGoodsInfoByName(goodsInfo.getGoodsName(),goodsInfo.getId());
        if(info!=null) {
            return  toAjaxByError("商品重复!");
        }else{
            if(goodsInfo.getGoodsCodeImg()!=""){
                GoodsInfo codeInfo=goodsInfoService.selectGoodsInfoByCode(goodsInfo.getGoodsCodeImg(),goodsInfo.getId());
                if(codeInfo!=null) {
                    return  toAjaxByError("料号重复!");
                }
            }
            goodsInfo.setUpdateBy(SecurityUtils.getUsername());
            return toAjax(goodsInfoService.updateGoodsInfo(goodsInfo));
        }
    }


    /**
     * 删除商品建档
     */
    @PreAuthorize("@ss.hasPermi('system:goods:remove')")
    @Log(title = "商品建档", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Integer[] ids)
    {
        return toAjax(goodsInfoService.deleteGoodsInfoByIds(ids));
    }
}
