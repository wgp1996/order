package com.ruoyi.project.system.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.project.system.mapper.GoodsPriceInfoMapper;
import com.ruoyi.project.system.domain.GoodsPriceInfo;
import com.ruoyi.project.system.service.IGoodsPriceInfoService;

/**
 * 商品价格管理Service业务层处理
 * 
 * @author ruoyi
 * @date 2021-01-05
 */
@Service
public class GoodsPriceInfoServiceImpl implements IGoodsPriceInfoService 
{
    @Autowired
    private GoodsPriceInfoMapper goodsPriceInfoMapper;

    /**
     * 查询商品价格管理
     * 
     * @param id 商品价格管理ID
     * @return 商品价格管理
     */
    @Override
    public GoodsPriceInfo selectGoodsPriceInfoById(Long id)
    {
        return goodsPriceInfoMapper.selectGoodsPriceInfoById(id);
    }
    @Override
    public GoodsPriceInfo selectGoodsPrice(String goodsCode,String khCode){
        return goodsPriceInfoMapper.selectGoodsPrice(goodsCode,khCode);
    }
    /**
     * 查询商品价格管理列表
     * 
     * @param goodsPriceInfo 商品价格管理
     * @return 商品价格管理
     */
    @Override
    public List<GoodsPriceInfo> selectGoodsPriceInfoList(GoodsPriceInfo goodsPriceInfo)
    {
        return goodsPriceInfoMapper.selectGoodsPriceInfoList(goodsPriceInfo);
    }

    /**
     * 新增商品价格管理
     * 
     * @param goodsPriceInfo 商品价格管理
     * @return 结果
     */
    @Override
    public int insertGoodsPriceInfo(GoodsPriceInfo goodsPriceInfo)
    {
        goodsPriceInfo.setCreateTime(DateUtils.getNowDate());
        return goodsPriceInfoMapper.insertGoodsPriceInfo(goodsPriceInfo);
    }

    /**
     * 修改商品价格管理
     * 
     * @param goodsPriceInfo 商品价格管理
     * @return 结果
     */
    @Override
    public int updateGoodsPriceInfo(GoodsPriceInfo goodsPriceInfo)
    {
        goodsPriceInfo.setUpdateTime(DateUtils.getNowDate());
        return goodsPriceInfoMapper.updateGoodsPriceInfo(goodsPriceInfo);
    }

    /**
     * 批量删除商品价格管理
     * 
     * @param ids 需要删除的商品价格管理ID
     * @return 结果
     */
    @Override
    public int deleteGoodsPriceInfoByIds(Long[] ids)
    {
        return goodsPriceInfoMapper.deleteGoodsPriceInfoByIds(ids);
    }

    /**
     * 删除商品价格管理信息
     * 
     * @param id 商品价格管理ID
     * @return 结果
     */
    @Override
    public int deleteGoodsPriceInfoById(Long id)
    {
        return goodsPriceInfoMapper.deleteGoodsPriceInfoById(id);
    }
}
