package com.ruoyi.project.system.domain;

import com.ruoyi.framework.aspectj.lang.annotation.Excel;
import com.ruoyi.framework.web.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.List;

/**
 * 进货单对象 cg_rkd
 * 
 * @author ruoyi
 * @date 2020-08-17
 */
public class CgRkdSingle extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** id */
    private String id;
    /** 供应商名称 */
    @Excel(name = "供应商名称")
    private String personName;

    /** 供应商编码 */
    @Excel(name = "供应商编码")
    private String personCode;
    /** 单据编码 */
    @Excel(name = "单据编码")
    private String djNumber;

    /** 摊位编码 */
    @Excel(name = "摊位编码")
    private String storeCode;

    /** 单据日期 */
    @Excel(name = "单据日期")
    private String djTime;

    /** 摊位名称 */
    @Excel(name = "摊位名称")
    private String storeName;

    /** 单据状态 */
    @Excel(name = "单据状态")
    private Integer status;

    /** 结算方式 */
    @Excel(name = "结算方式")
    private String payType;

    private String payTypeName;

    /** 发票号码 */
    @Excel(name = "发票号码")
    private String fpNumber;

    /** 开户账号 */
    @Excel(name = "开户账号")
    private String fpAccount;

    /** 开户行 */
    @Excel(name = "开户行")
    private String fpBank;

    /** 税号 */
    @Excel(name = "税号")
    private String fpRate;

    /** 发票代码 */
    @Excel(name = "发票代码")
    private String fpNum;
    /** 开票日期 */
    @Excel(name = "开票日期")
    private String fpTime;
    private String sumMoney;
    private String fpStatus;

    public String getFpNumber() {
        return fpNumber;
    }

    public void setFpNumber(String fpNumber) {
        this.fpNumber = fpNumber;
    }

    public String getFpAccount() {
        return fpAccount;
    }

    public void setFpAccount(String fpAccount) {
        this.fpAccount = fpAccount;
    }

    public String getFpBank() {
        return fpBank;
    }

    public void setFpBank(String fpBank) {
        this.fpBank = fpBank;
    }

    public String getFpRate() {
        return fpRate;
    }

    public void setFpRate(String fpRate) {
        this.fpRate = fpRate;
    }

    public String getFpNum() {
        return fpNum;
    }

    public void setFpNum(String fpNum) {
        this.fpNum = fpNum;
    }

    public String getFpTime() {
        return fpTime;
    }

    public void setFpTime(String fpTime) {
        this.fpTime = fpTime;
    }

    public String getSumMoney() {
        return sumMoney;
    }

    public void setSumMoney(String sumMoney) {
        this.sumMoney = sumMoney;
    }

    public String getFpStatus() {
        return fpStatus;
    }

    public void setFpStatus(String fpStatus) {
        this.fpStatus = fpStatus;
    }

    /** $column.columnComment */
    @Excel(name = "文件名")
    private String fileName;
    private String rows;
    private String djStatusName;

    private Integer isRate;

    private String sumNum;
    private String goodsName;
    private String goodsDw;
    private String goodsNum;
    private String createName;
    private String goodsAddress;
    private String goodsPrice;
    private String goodsMoney;
    private String isRateName;

    public String getIsRateName() {
        return isRateName;
    }

    public void setIsRateName(String isRateName) {
        this.isRateName = isRateName;
    }

    public String getPayTypeName() {
        return payTypeName;
    }

    public void setPayTypeName(String payTypeName) {
        this.payTypeName = payTypeName;
    }

    public String getPayType() {
        return payType;
    }

    public void setPayType(String payType) {
        this.payType = payType;
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

    public String getGoodsName() {
        return goodsName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }

    public String getGoodsDw() {
        return goodsDw;
    }

    public void setGoodsDw(String goodsDw) {
        this.goodsDw = goodsDw;
    }

    public String getGoodsNum() {
        return goodsNum;
    }

    public void setGoodsNum(String goodsNum) {
        this.goodsNum = goodsNum;
    }

    public String getCreateName() {
        return createName;
    }

    public void setCreateName(String createName) {
        this.createName = createName;
    }

    public String getGoodsAddress() {
        return goodsAddress;
    }

    public void setGoodsAddress(String goodsAddress) {
        this.goodsAddress = goodsAddress;
    }

    public String getPersonName() {
        return personName;
    }

    public void setPersonName(String personName) {
        this.personName = personName;
    }

    public String getPersonCode() {
        return personCode;
    }

    public void setPersonCode(String personCode) {
        this.personCode = personCode;
    }

    public Integer getIsRate() {
        return isRate;
    }

    public void setIsRate(Integer isRate) {
        this.isRate = isRate;
    }

    public String getSumNum() {
        return sumNum;
    }

    public void setSumNum(String sumNum) {
        this.sumNum = sumNum;
    }

    public String getDjStatusName() {
        return djStatusName;
    }

    public void setDjStatusName(String djStatusName) {
        this.djStatusName = djStatusName;
    }

    /*
       子表
    */
    private List<CgRkdSingleChild> childrenList;

    public String getRows() {
        return rows;
    }

    public List<CgRkdSingleChild> getChildrenList() {
        return childrenList;
    }

    public void setChildrenList(List<CgRkdSingleChild> childrenList) {
        this.childrenList = childrenList;
    }

    public void setRows(String rows) {
        this.rows = rows;
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
    public void setStoreCode(String storeCode) 
    {
        this.storeCode = storeCode;
    }

    public String getStoreCode() 
    {
        return storeCode;
    }
    public void setDjTime(String djTime) 
    {
        this.djTime = djTime;
    }

    public String getDjTime() 
    {
        return djTime;
    }
    public void setStoreName(String storeName) 
    {
        this.storeName = storeName;
    }

    public String getStoreName() 
    {
        return storeName;
    }
    public void setStatus(Integer status) 
    {
        this.status = status;
    }

    public Integer getStatus() 
    {
        return status;
    }
    public void setFileName(String fileName) 
    {
        this.fileName = fileName;
    }

    public String getFileName() 
    {
        return fileName;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("personName", getPersonName())
            .append("personCode", getPersonCode())
            .append("djNumber", getDjNumber())
            .append("storeCode", getStoreCode())
            .append("djTime", getDjTime())
            .append("storeName", getStoreName())
            .append("status", getStatus())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .append("remark", getRemark())
            .append("fileName", getFileName())
            .toString();
    }
}
