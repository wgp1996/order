package com.ruoyi.project.system.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.framework.aspectj.lang.annotation.Excel;
import com.ruoyi.framework.web.domain.BaseEntity;

/**
 * 发票建档对象 fp_info
 * 
 * @author ruoyi
 * @date 2021-01-06
 */
public class FpInfo extends BaseEntity
{
    private static final Long serialVersionUID = 1L;

    /** $column.columnComment */
    private Integer id;

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

    /** 客户名称 */
    @Excel(name = "客户名称")
    private String khName;

    /** 客户代码 */
    @Excel(name = "客户代码")
    private String khCode;

    public void setId(Integer id) 
    {
        this.id = id;
    }

    public Integer getId() 
    {
        return id;
    }
    public void setFpNumber(String fpNumber) 
    {
        this.fpNumber = fpNumber;
    }

    public String getFpNumber() 
    {
        return fpNumber;
    }
    public void setFpAccount(String fpAccount) 
    {
        this.fpAccount = fpAccount;
    }

    public String getFpAccount() 
    {
        return fpAccount;
    }
    public void setFpBank(String fpBank) 
    {
        this.fpBank = fpBank;
    }

    public String getFpBank() 
    {
        return fpBank;
    }
    public void setFpRate(String fpRate) 
    {
        this.fpRate = fpRate;
    }

    public String getFpRate() 
    {
        return fpRate;
    }
    public void setFpNum(String fpNum) 
    {
        this.fpNum = fpNum;
    }

    public String getFpNum() 
    {
        return fpNum;
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

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("fpNumber", getFpNumber())
            .append("fpAccount", getFpAccount())
            .append("fpBank", getFpBank())
            .append("fpRate", getFpRate())
            .append("fpNum", getFpNum())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .append("remark", getRemark())
            .append("khName", getKhName())
            .append("khCode", getKhCode())
            .toString();
    }
}
