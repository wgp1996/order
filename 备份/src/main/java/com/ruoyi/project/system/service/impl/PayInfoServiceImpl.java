package com.ruoyi.project.system.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.project.system.mapper.PayInfoMapper;
import com.ruoyi.project.system.domain.PayInfo;
import com.ruoyi.project.system.service.IPayInfoService;

/**
 * 付款单Service业务层处理
 * 
 * @author ruoyi
 * @date 2021-01-06
 */
@Service
public class PayInfoServiceImpl implements IPayInfoService 
{
    @Autowired
    private PayInfoMapper payInfoMapper;

    /**
     * 查询付款单
     * 
     * @param id 付款单ID
     * @return 付款单
     */
    @Override
    public PayInfo selectPayInfoById(Integer id)
    {
        return payInfoMapper.selectPayInfoById(id);
    }

    /**
     * 查询付款单列表
     * 
     * @param payInfo 付款单
     * @return 付款单
     */
    @Override
    public List<PayInfo> selectPayInfoList(PayInfo payInfo)
    {
        return payInfoMapper.selectPayInfoList(payInfo);
    }
    /**
     * 修改付款单状态
     *
     * @return 结果
     */
    @Override
    public int updatePayInfoStatus(Integer[] ids){
        return payInfoMapper.updatePayInfoStatus(ids);
    }
    /**
     * 取消提交付款单状态
     *
     * @return 结果
     */
    @Override
    public int cancelPayInfoStatus(Integer[] ids){
        return payInfoMapper.cancelPayInfoStatus(ids);
    }
    /**
     * 新增付款单
     * 
     * @param payInfo 付款单
     * @return 结果
     */
    @Override
    public int insertPayInfo(PayInfo payInfo)
    {
        payInfo.setCreateTime(DateUtils.getNowDate());
        return payInfoMapper.insertPayInfo(payInfo);
    }

    /**
     * 修改付款单
     * 
     * @param payInfo 付款单
     * @return 结果
     */
    @Override
    public int updatePayInfo(PayInfo payInfo)
    {
        payInfo.setUpdateTime(DateUtils.getNowDate());
        return payInfoMapper.updatePayInfo(payInfo);
    }

    /**
     * 批量删除付款单
     * 
     * @param ids 需要删除的付款单ID
     * @return 结果
     */
    @Override
    public int deletePayInfoByIds(Integer[] ids)
    {
        return payInfoMapper.deletePayInfoByIds(ids);
    }

    /**
     * 删除付款单信息
     * 
     * @param id 付款单ID
     * @return 结果
     */
    @Override
    public int deletePayInfoById(Integer id)
    {
        return payInfoMapper.deletePayInfoById(id);
    }
}
