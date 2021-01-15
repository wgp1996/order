package com.ruoyi.project.system.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.framework.aspectj.lang.annotation.Excel;
import com.ruoyi.framework.web.domain.BaseEntity;

/**
 * 商品价格管理对象 goods_price_info
 * 
 * @author ruoyi
 * @date 2021-01-05
 */
public class GoodsPriceInfo extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** ID */
    private Long id;

    /** 商品名称 */
    @Excel(name = "商品名称")
    private String goodsName;

    /** 商品编码 */
    @Excel(name = "商品编码")
    private String goodsCode;

    /** 客户名称 */
    @Excel(name = "客户名称")
    private String khName;

    /** 客户编码 */
    @Excel(name = "客户编码")
    private String khCode;

    /** 商品价格 */
    @Excel(name = "商品价格")
    private Float goodsPrice;

    /** 规格 */
    @Excel(name = "规格")
    private String goodsGg;

    /** 单位 */
    @Excel(name = "单位")
    private String goodsDw;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setGoodsName(String goodsName) 
    {
        this.goodsName = goodsName;
    }

    public String getGoodsName() 
    {
        return goodsName;
    }
    public void setGoodsCode(String goodsCode) 
    {
        this.goodsCode = goodsCode;
    }

    public String getGoodsCode() 
    {
        return goodsCode;
    }
    public void setKhName(String khName) 
    {
        this.khName = khName;
    }

    public String getKhName() 
    {
        return khName;
    }
    public void setKhCode(String khCode) 
    {
        this.khCode = khCode;
    }

    public String getKhCode() 
    {
        return khCode;
    }
    public void setGoodsPrice(Float goodsPrice)
    {
        this.goodsPrice = goodsPrice;
    }

    public Float getGoodsPrice()
    {
        return goodsPrice;
    }
    public void setGoodsGg(String goodsGg) 
    {
        this.goodsGg = goodsGg;
    }

    public String getGoodsGg() 
    {
        return goodsGg;
    }
    public void setGoodsDw(String goodsDw) 
    {
        this.goodsDw = goodsDw;
    }

    public String getGoodsDw() 
    {
        return goodsDw;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("goodsName", getGoodsName())
            .append("goodsCode", getGoodsCode())
            .append("khName", getKhName())
            .append("khCode", getKhCode())
            .append("goodsPrice", getGoodsPrice())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .append("remark", getRemark())
            .append("goodsGg", getGoodsGg())
            .append("goodsDw", getGoodsDw())
            .toString();
    }
}
