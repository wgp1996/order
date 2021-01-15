package com.ruoyi.project.system.service;

import java.util.List;
import com.ruoyi.project.system.domain.PayInfo;

/**
 * 付款单Service接口
 * 
 * @author ruoyi
 * @date 2021-01-06
 */
public interface IPayInfoService 
{
    /**
     * 查询付款单
     * 
     * @param id 付款单ID
     * @return 付款单
     */
    public PayInfo selectPayInfoById(Integer id);

    /**
     * 查询付款单列表
     * 
     * @param payInfo 付款单
     * @return 付款单集合
     */
    public List<PayInfo> selectPayInfoList(PayInfo payInfo);

    /**
     * 新增付款单
     * 
     * @param payInfo 付款单
     * @return 结果
     */
    public int insertPayInfo(PayInfo payInfo);

    /**
     * 修改付款单
     * 
     * @param payInfo 付款单
     * @return 结果
     */
    public int updatePayInfo(PayInfo payInfo);
    /**
     * 修改付款单状态
     *
     * @return 结果
     */
    public int updatePayInfoStatus(Integer[] ids);

    /**
     * 取消提交付款单状态
     *
     * @return 结果
     */
    public int cancelPayInfoStatus(Integer[] ids);

    /**
     * 批量删除付款单
     * 
     * @param ids 需要删除的付款单ID
     * @return 结果
     */
    public int deletePayInfoByIds(Integer[] ids);

    /**
     * 删除付款单信息
     * 
     * @param id 付款单ID
     * @return 结果
     */
    public int deletePayInfoById(Integer id);
}
