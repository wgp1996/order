package com.ruoyi.project.system.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.framework.aspectj.lang.annotation.Excel;
import com.ruoyi.framework.web.domain.BaseEntity;

/**
 * 付款单子表对象 pay_info_child
 * 
 * @author ruoyi
 * @date 2021-01-06
 */
public class PayInfoChild extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** id */
    private Integer id;

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

    /** 开票日期 */
    @Excel(name = "开票日期")
    private String fpTime;

    /** 规格 */
    @Excel(name = "规格")
    private String goodsGg;

    /** 供应商编码 */
    @Excel(name = "供应商编码")
    private String khCode;

    /** 供应商名称 */
    @Excel(name = "供应商名称")
    private String khName;

    /** 发票号码 */
    @Excel(name = "发票号码")
    private String fpNumber;

    /** 到账日期 */
    @Excel(name = "到账日期")
    private String dzTime;

    /** 开票单位 */
    @Excel(name = "开票单位")
    private String fpDw;

    /** 状态 */
    @Excel(name = "状态")
    private Long status;

    /** 对账订单号 */
    @Excel(name = "对账订单号")
    private String orderNumber;
    @Excel(name = "付款金额")
    private String goodsMoney;

    /** 对账订单明细id */
    @Excel(name = "对账订单明细id")
    private String orderId;
    @Excel(name = "支付方式")
    private String payType;

    public String getPayType() {
        return payType;
    }

    public void setPayType(String payType) {
        this.payType = payType;
    }

    public String getGoodsMoney() {
        return goodsMoney;
    }

    public void setGoodsMoney(String goodsMoney) {
        this.goodsMoney = goodsMoney;
    }

    public void setId(Integer id)
    {
        this.id = id;
    }

    public Integer getId() 
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
    public void setFpTime(String fpTime) 
    {
        this.fpTime = fpTime;
    }

    public String getFpTime() 
    {
        return fpTime;
    }
    public void setGoodsGg(String goodsGg) 
    {
        this.goodsGg = goodsGg;
    }

    public String getGoodsGg() 
    {
        return goodsGg;
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
    public void setFpNumber(String fpNumber) 
    {
        this.fpNumber = fpNumber;
    }

    public String getFpNumber() 
    {
        return fpNumber;
    }
    public void setDzTime(String dzTime) 
    {
        this.dzTime = dzTime;
    }

    public String getDzTime() 
    {
        return dzTime;
    }
    public void setFpDw(String fpDw) 
    {
        this.fpDw = fpDw;
    }

    public String getFpDw() 
    {
        return fpDw;
    }
    public void setStatus(Long status) 
    {
        this.status = status;
    }

    public Long getStatus() 
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
            .append("fpTime", getFpTime())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .append("remark", getRemark())
            .append("goodsGg", getGoodsGg())
            .append("khCode", getKhCode())
            .append("khName", getKhName())
            .append("fpNumber", getFpNumber())
            .append("dzTime", getDzTime())
            .append("fpDw", getFpDw())
            .append("status", getStatus())
            .append("orderNumber", getOrderNumber())
            .append("orderId", getOrderId())
            .toString();
    }
}
