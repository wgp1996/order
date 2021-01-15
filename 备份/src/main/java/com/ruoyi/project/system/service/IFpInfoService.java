package com.ruoyi.project.system.service;

import java.util.List;
import com.ruoyi.project.system.domain.FpInfo;

/**
 * 发票建档Service接口
 * 
 * @author ruoyi
 * @date 2021-01-06
 */
public interface IFpInfoService 
{
    /**
     * 查询发票建档
     * 
     * @param id 发票建档ID
     * @return 发票建档
     */
    public FpInfo selectFpInfoById(Integer id);
    /**
     * 供应商查询发票
     *
     * @param khCode 供应商代码
     * @return 发票建档
     */
    public FpInfo selectFpInfoByKh(String khCode);

    /**
     * 查询发票建档列表
     * 
     * @param fpInfo 发票建档
     * @return 发票建档集合
     */
    public List<FpInfo> selectFpInfoList(FpInfo fpInfo);

    /**
     * 新增发票建档
     * 
     * @param fpInfo 发票建档
     * @return 结果
     */
    public int insertFpInfo(FpInfo fpInfo);

    /**
     * 检查发票信息
     *
     * @return 结果
     */
    public int selectFpInfoByKhName(int id,String khCode);

    /**
     * 修改发票建档
     * 
     * @param fpInfo 发票建档
     * @return 结果
     */
    public int updateFpInfo(FpInfo fpInfo);

    /**
     * 批量删除发票建档
     * 
     * @param ids 需要删除的发票建档ID
     * @return 结果
     */
    public int deleteFpInfoByIds(Integer[] ids);

    /**
     * 删除发票建档信息
     * 
     * @param id 发票建档ID
     * @return 结果
     */
    public int deleteFpInfoById(Integer id);
}
