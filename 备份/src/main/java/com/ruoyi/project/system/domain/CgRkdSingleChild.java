package com.ruoyi.project.system.domain;

import com.ruoyi.framework.aspectj.lang.annotation.Excel;
import com.ruoyi.framework.web.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 进货单子表对象 cg_rkd_child
 * 
 * @author ruoyi
 * @date 2020-08-17
 */
public class CgRkdSingleChild extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** ID */
    private String id;

    /** 单据号 */
    @Excel(name = "单据号")
    private String djNumber;



    /** 商品编码 */
    @Excel(name = "商品编码")
    private String goodsCode;

    /** 商品名称 */
    @Excel(name = "商品名称")
    private String goodsName;

    /** 商品单位 */
    @Excel(name = "商品单位")
    private String goodsDw;

    /** 商品数量 */
    @Excel(name = "商品数量")
    private String goodsNum;

    /** 商品重量 */
    @Excel(name = "商品重量")
    private String goodsWeight;

    /** 产地信息 */
    @Excel(name = "产地信息")
    private String goodsGg;

    /** 单价 */
    @Excel(name = "单价")
    private String goodsPrice;

    /** 金额 */
    @Excel(name = "金额")
    private String goodsMoney;

    /** 税率 */
    @Excel(name = "税率")
    private String goodsRate;

    /** 含税单价 */
    @Excel(name = "含税单价")
    private String goodsPriceRate;

    /** 含税金额 */
    @Excel(name = "含税金额")
    private String goodsMoneyRate;

    private String goodsImg;
    private  String goodsCodeImg;
    private  String pid;
    private  String djStatusName;
    private  String payTypeName;
    private  String isRateName;
    private String djTime;
    private String khName;

    public String getKhName() {
        return khName;
    }

    public void setKhName(String khName) {
        this.khName = khName;
    }
    public String getDjTime() {
        return djTime;
    }

    public void setDjTime(String djTime) {
        this.djTime = djTime;
    }

    /** 门店编号 */
    @Excel(name = "门店编号")
    private String storeid;

    /** 门店名称 */
    @Excel(name = "门店名称")
    private String shopName;

    public String getDjStatusName() {
        return djStatusName;
    }

    public void setDjStatusName(String djStatusName) {
        this.djStatusName = djStatusName;
    }

    public String getPayTypeName() {
        return payTypeName;
    }

    public void setPayTypeName(String payTypeName) {
        this.payTypeName = payTypeName;
    }

    public String getIsRateName() {
        return isRateName;
    }

    public void setIsRateName(String isRateName) {
        this.isRateName = isRateName;
    }

    public String getStoreid() {
        return storeid;
    }

    public void setStoreid(String storeid) {
        this.storeid = storeid;
    }

    public String getShopName() {
        return shopName;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName;
    }

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }

    public String getGoodsPrice() {
        return goodsPrice;
    }

    public void setGoodsPrice(String goodsPrice) {
        this.goodsPrice = goodsPrice;
    }

    public String getGoodsMoney() {
        return goodsMoney;
    }

    public void setGoodsMoney(String goodsMoney) {
        this.goodsMoney = goodsMoney;
    }

    public String getGoodsRate() {
        return goodsRate;
    }

    public void setGoodsRate(String goodsRate) {
        this.goodsRate = goodsRate;
    }

    public String getGoodsPriceRate() {
        return goodsPriceRate;
    }

    public void setGoodsPriceRate(String goodsPriceRate) {
        this.goodsPriceRate = goodsPriceRate;
    }

    public String getGoodsMoneyRate() {
        return goodsMoneyRate;
    }

    public void setGoodsMoneyRate(String goodsMoneyRate) {
        this.goodsMoneyRate = goodsMoneyRate;
    }

    public String getGoodsCodeImg() {
        return goodsCodeImg;
    }

    public void setGoodsCodeImg(String goodsCodeImg) {
        this.goodsCodeImg = goodsCodeImg;
    }

    public String getGoodsImg() {
        return goodsImg;
    }

    public void setGoodsImg(String goodsImg) {
        this.goodsImg = goodsImg;
    }

    public void setId(String id)
    {
        this.id = id;
    }

    public String getId() 
    {
        return id;
    }
    public void setDjNumber(String djNumber) 
    {
        this.djNumber = djNumber;
    }

    public String getDjNumber() 
    {
        return djNumber;
    }

    public void setGoodsCode(String goodsCode) 
    {
        this.goodsCode = goodsCode;
    }

    public String getGoodsCode() 
    {
        return goodsCode;
    }
    public void setGoodsName(String goodsName) 
    {
        this.goodsName = goodsName;
    }

    public String getGoodsName() 
    {
        return goodsName;
    }
    public void setGoodsDw(String goodsDw) 
    {
        this.goodsDw = goodsDw;
    }

    public String getGoodsDw() 
    {
        return goodsDw;
    }
    public void setGoodsNum(String goodsNum) 
    {
        this.goodsNum = goodsNum;
    }

    public String getGoodsNum() 
    {
        return goodsNum;
    }
    public void setGoodsWeight(String goodsWeight) 
    {
        this.goodsWeight = goodsWeight;
    }

    public String getGoodsWeight() 
    {
        return goodsWeight;
    }

    public String getGoodsGg() {
        return goodsGg;
    }

    public void setGoodsGg(String goodsGg) {
        this.goodsGg = goodsGg;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("djNumber", getDjNumber())

            .append("goodsCode", getGoodsCode())
            .append("goodsName", getGoodsName())
            .append("goodsDw", getGoodsDw())
            .append("goodsNum", getGoodsNum())
            .append("goodsWeight", getGoodsWeight())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .append("remark", getRemark())
            .append("goodsGg", getGoodsGg())
            .toString();
    }
}
