package com.ruoyi.project.system.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.framework.aspectj.lang.annotation.Excel;
import com.ruoyi.framework.web.domain.BaseEntity;

/**
 * 采购订单子表对象 cgdd_info_child
 * 
 * @author ruoyi
 * @date 2021-01-05
 */
public class CgddInfoChild extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** ID */
    private Long id;

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

    /** 采购日期 */
    @Excel(name = "采购日期")
    private String cgTime;

    /** 规格 */
    @Excel(name = "规格")
    private String goodsGg;

    /** 单价 */
    @Excel(name = "单价")
    private String goodsPrice;

    /** 金额 */
    @Excel(name = "金额")
    private String goodsMoney;

    /** 供应商编码 */
    @Excel(name = "供应商编码")
    private String khCode;

    /** 供应商名称 */
    @Excel(name = "供应商名称")
    private String khName;

    /** 下单日期 */
    @Excel(name = "下单日期")
    private String orderTime;

    /** 入库日期 */
    @Excel(name = "入库日期")
    private String rkTime;

    /** 交货日期 */
    @Excel(name = "交货日期")
    private String jhTime;

    /** 收货人 */
    @Excel(name = "收货人")
    private String shPersonName;

    /** 电话 */
    @Excel(name = "电话")
    private String shPersonTel;

    /** 状态 */
    @Excel(name = "状态")
    private Integer status;

    /** 对账订单号 */
    @Excel(name = "对账订单号")
    private String orderNumber;

    /** 对账订单明细id */
    @Excel(name = "对账订单明细id")
    private String orderId;

    @Excel(name = "发票号码")
    private String fpNumber;

    @Excel(name = "开票日期")
    private String fpTime;

    @Excel(name = "入库状态")
    private Integer rkStatus;

    @Excel(name = "收货状态")
    private Integer shStatus;
    private String djStatusName;
    private String fpStatusName;
    private String rkStatusName;
    private String shStatusName;
    private String orderFpStatusName;
    private String personName;
    private String orderDjTime;

    private String rows;

    public String getRows() {
        return rows;
    }

    public void setRows(String rows) {
        this.rows = rows;
    }

    public String getOrderFpStatusName() {
        return orderFpStatusName;
    }

    public void setOrderFpStatusName(String orderFpStatusName) {
        this.orderFpStatusName = orderFpStatusName;
    }

    public String getPersonName() {
        return personName;
    }

    public void setPersonName(String personName) {
        this.personName = personName;
    }

    public String getOrderDjTime() {
        return orderDjTime;
    }

    public void setOrderDjTime(String orderDjTime) {
        this.orderDjTime = orderDjTime;
    }

    public String getDjStatusName() {
        return djStatusName;
    }

    public void setDjStatusName(String djStatusName) {
        this.djStatusName = djStatusName;
    }

    public String getFpStatusName() {
        return fpStatusName;
    }

    public void setFpStatusName(String fpStatusName) {
        this.fpStatusName = fpStatusName;
    }

    public String getRkStatusName() {
        return rkStatusName;
    }

    public void setRkStatusName(String rkStatusName) {
        this.rkStatusName = rkStatusName;
    }

    public String getShStatusName() {
        return shStatusName;
    }

    public void setShStatusName(String shStatusName) {
        this.shStatusName = shStatusName;
    }

    public String getFpNumber() {
        return fpNumber;
    }

    public void setFpNumber(String fpNumber) {
        this.fpNumber = fpNumber;
    }

    public String getFpTime() {
        return fpTime;
    }

    public void setFpTime(String fpTime) {
        this.fpTime = fpTime;
    }

    public Integer getRkStatus() {
        return rkStatus;
    }

    public void setRkStatus(Integer rkStatus) {
        this.rkStatus = rkStatus;
    }

    public Integer getShStatus() {
        return shStatus;
    }

    public void setShStatus(Integer shStatus) {
        this.shStatus = shStatus;
    }

    private String djTime;

    public String getDjTime() {
        return djTime;
    }

    public void setDjTime(String djTime) {
        this.djTime = djTime;
    }

    public void setId(Long id)
    {
        this.id = id;
    }

    public Long getId() 
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
    public void setCgTime(String cgTime) 
    {
        this.cgTime = cgTime;
    }

    public String getCgTime() 
    {
        return cgTime;
    }
    public void setGoodsGg(String goodsGg) 
    {
        this.goodsGg = goodsGg;
    }

    public String getGoodsGg() 
    {
        return goodsGg;
    }
    public void setGoodsPrice(String goodsPrice) 
    {
        this.goodsPrice = goodsPrice;
    }

    public String getGoodsPrice() 
    {
        return goodsPrice;
    }
    public void setGoodsMoney(String goodsMoney) 
    {
        this.goodsMoney = goodsMoney;
    }

    public String getGoodsMoney() 
    {
        return goodsMoney;
    }
    public void setKhCode(String khCode) 
    {
        this.khCode = khCode;
    }

    public String getKhCode() 
    {
        return khCode;
    }
    public void setKhName(String khName) 
    {
        this.khName = khName;
    }

    public String getKhName() 
    {
        return khName;
    }
    public void setOrderTime(String orderTime) 
    {
        this.orderTime = orderTime;
    }

    public String getOrderTime() 
    {
        return orderTime;
    }
    public void setRkTime(String rkTime) 
    {
        this.rkTime = rkTime;
    }

    public String getRkTime() 
    {
        return rkTime;
    }
    public void setJhTime(String jhTime) 
    {
        this.jhTime = jhTime;
    }

    public String getJhTime() 
    {
        return jhTime;
    }
    public void setShPersonName(String shPersonName) 
    {
        this.shPersonName = shPersonName;
    }

    public String getShPersonName() 
    {
        return shPersonName;
    }
    public void setShPersonTel(String shPersonTel) 
    {
        this.shPersonTel = shPersonTel;
    }

    public String getShPersonTel() 
    {
        return shPersonTel;
    }
    public void setStatus(Integer status) 
    {
        this.status = status;
    }

    public Integer getStatus() 
    {
        return status;
    }
    public void setOrderNumber(String orderNumber) 
    {
        this.orderNumber = orderNumber;
    }

    public String getOrderNumber() 
    {
        return orderNumber;
    }
    public void setOrderId(String orderId) 
    {
        this.orderId = orderId;
    }

    public String getOrderId() 
    {
        return orderId;
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
            .append("cgTime", getCgTime())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .append("remark", getRemark())
            .append("goodsGg", getGoodsGg())
            .append("goodsPrice", getGoodsPrice())
            .append("goodsMoney", getGoodsMoney())
            .append("khCode", getKhCode())
            .append("khName", getKhName())
            .append("orderTime", getOrderTime())
            .append("rkTime", getRkTime())
            .append("jhTime", getJhTime())
            .append("shPersonName", getShPersonName())
            .append("shPersonTel", getShPersonTel())
            .append("status", getStatus())
            .append("orderNumber", getOrderNumber())
            .append("orderId", getOrderId())
            .toString();
    }
}
