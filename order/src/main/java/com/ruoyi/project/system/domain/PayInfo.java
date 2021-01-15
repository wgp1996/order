package com.ruoyi.project.system.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.framework.aspectj.lang.annotation.Excel;
import com.ruoyi.framework.web.domain.BaseEntity;

import java.util.List;

/**
 * 付款单对象 pay_info
 * 
 * @author ruoyi
 * @date 2021-01-06
 */
public class PayInfo extends BaseEntity
{
    private static final Long serialVersionUID = 1L;

    /** id */
    private Integer id;

    /** 单号 */
    @Excel(name = "单号")
    private String djNumber;

    /** 单据日期 */
    @Excel(name = "单据日期")
    private String djTime;

    /** 状态 */
    @Excel(name = "状态")
    private Integer status;

    /** $column.columnComment */
    @Excel(name = "状态")
    private String fileName;

    /*
    子表
 */
    private List<PayInfoChild> childrenList;
    private String rows;
    private String djStatusName;

    public String getDjStatusName() {
        return djStatusName;
    }

    public void setDjStatusName(String djStatusName) {
        this.djStatusName = djStatusName;
    }

    public List<PayInfoChild> getChildrenList() {
        return childrenList;
    }

    public void setChildrenList(List<PayInfoChild> childrenList) {
        this.childrenList = childrenList;
    }

    public String getRows() {
        return rows;
    }

    public void setRows(String rows) {
        this.rows = rows;
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
    public void setDjTime(String djTime) 
    {
        this.djTime = djTime;
    }

    public String getDjTime() 
    {
        return djTime;
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
            .append("djNumber", getDjNumber())
            .append("djTime", getDjTime())
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
