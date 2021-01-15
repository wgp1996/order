package com.ruoyi.project.system.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.framework.aspectj.lang.annotation.Excel;
import com.ruoyi.framework.web.domain.BaseEntity;

import java.util.List;

/**
 * 采购订单主表对象 cgdd_info
 * 
 * @author ruoyi
 * @date 2021-01-05
 */
public class CgddInfo extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** id */
    private Long id;

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
    private List<CgddInfoChild> childrenList;
    private String rows;
    private String djStatusName;
    private String orderCount;
    private String cgCount;
    private String jhCount;
    private String rkCount;
    private String gkpCount;
    private String hkpCount;

    public String getGkpCount() {
        return gkpCount;
    }

    public void setGkpCount(String gkpCount) {
        this.gkpCount = gkpCount;
    }

    public String getHkpCount() {
        return hkpCount;
    }

    public void setHkpCount(String hkpCount) {
        this.hkpCount = hkpCount;
    }

    public String getOrderCount() {
        return orderCount;
    }

    public void setOrderCount(String orderCount) {
        this.orderCount = orderCount;
    }

    public String getCgCount() {
        return cgCount;
    }

    public void setCgCount(String cgCount) {
        this.cgCount = cgCount;
    }

    public String getJhCount() {
        return jhCount;
    }

    public void setJhCount(String jhCount) {
        this.jhCount = jhCount;
    }

    public String getRkCount() {
        return rkCount;
    }

    public void setRkCount(String rkCount) {
        this.rkCount = rkCount;
    }

    public String getDjStatusName() {
        return djStatusName;
    }

    public void setDjStatusName(String djStatusName) {
        this.djStatusName = djStatusName;
    }

    public List<CgddInfoChild> getChildrenList() {
        return childrenList;
    }

    public void setChildrenList(List<CgddInfoChild> childrenList) {
        this.childrenList = childrenList;
    }

    public String getRows() {
        return rows;
    }

    public void setRows(String rows) {
        this.rows = rows;
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
