package com.ruoyi.project.system.mapper;

import java.util.List;
import com.ruoyi.project.system.domain.GoodsPriceInfo;

/**
 * 商品价格管理Mapper接口
 * 
 * @author ruoyi
 * @date 2021-01-05
 */
public interface GoodsPriceInfoMapper 
{
    /**
     * 查询商品价格管理
     * 
     * @param id 商品价格管理ID
     * @return 商品价格管理
     */
    public GoodsPriceInfo selectGoodsPriceInfoById(Long id);

    /**
     * 查询商品价格管理列表
     * 
     * @param goodsPriceInfo 商品价格管理
     * @return 商品价格管理集合
     */
    public List<GoodsPriceInfo> selectGoodsPriceInfoList(GoodsPriceInfo goodsPriceInfo);
    public int checkGoods(String goodsCode,Integer id);
    /**
     * 新增商品价格管理
     * 
     * @param goodsPriceInfo 商品价格管理
     * @return 结果
     */
    public int insertGoodsPriceInfo(GoodsPriceInfo goodsPriceInfo);
    public GoodsPriceInfo selectGoodsPrice(String goodsCode,String khCode);
    /**
     * 修改商品价格管理
     * 
     * @param goodsPriceInfo 商品价格管理
     * @return 结果
     */
    public int updateGoodsPriceInfo(GoodsPriceInfo goodsPriceInfo);

    /**
     * 删除商品价格管理
     * 
     * @param id 商品价格管理ID
     * @return 结果
     */
    public int deleteGoodsPriceInfoById(Long id);

    /**
     * 批量删除商品价格管理
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteGoodsPriceInfoByIds(Long[] ids);
}
