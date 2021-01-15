package com.ruoyi.project.system.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.project.system.mapper.PayInfoChildMapper;
import com.ruoyi.project.system.domain.PayInfoChild;
import com.ruoyi.project.system.service.IPayInfoChildService;

/**
 * 付款单子表Service业务层处理
 * 
 * @author ruoyi
 * @date 2021-01-06
 */
@Service
public class PayInfoChildServiceImpl implements IPayInfoChildService 
{
    @Autowired
    private PayInfoChildMapper payInfoChildMapper;

    /**
     * 查询付款单子表
     * 
     * @param id 付款单子表ID
     * @return 付款单子表
     */
    @Override
    public PayInfoChild selectPayInfoChildById(Integer id)
    {
        return payInfoChildMapper.selectPayInfoChildById(id);
    }
    /**
     * 查询付款单子表
     *
     * @param djNumber 付款单单号
     * @return 付款单子表
     */
    @Override
    public  List<PayInfoChild> selectPayInfoChildByNum(String djNumber){
        return payInfoChildMapper.selectPayInfoChildByNum(djNumber);
    }
    /**
     * 查询付款单子表列表
     * 
     * @param payInfoChild 付款单子表
     * @return 付款单子表
     */
    @Override
    public List<PayInfoChild> selectPayInfoChildList(PayInfoChild payInfoChild)
    {
        return payInfoChildMapper.selectPayInfoChildList(payInfoChild);
    }

    /**
     * 新增付款单子表
     * 
     * @param payInfoChild 付款单子表
     * @return 结果
     */
    @Override
    public int insertPayInfoChild(PayInfoChild payInfoChild)
    {
        payInfoChild.setCreateTime(DateUtils.getNowDate());
        return payInfoChildMapper.insertPayInfoChild(payInfoChild);
    }

    /**
     * 修改付款单子表
     * 
     * @param payInfoChild 付款单子表
     * @return 结果
     */
    @Override
    public int updatePayInfoChild(PayInfoChild payInfoChild)
    {
        payInfoChild.setUpdateTime(DateUtils.getNowDate());
        return payInfoChildMapper.updatePayInfoChild(payInfoChild);
    }

    /**
     * 批量删除付款单子表
     * 
     * @param ids 需要删除的付款单子表ID
     * @return 结果
     */
    @Override
    public int deletePayInfoChildByIds(Integer[] ids)
    {
        return payInfoChildMapper.deletePayInfoChildByIds(ids);
    }

    /**
     * 删除付款单子表信息
     * 
     * @param id 付款单子表ID
     * @return 结果
     */
    @Override
    public int deletePayInfoChildById(Integer id)
    {
        return payInfoChildMapper.deletePayInfoChildById(id);
    }
    /**
     * 批量删除付款单子表
     *
     * @param ids 需要删除的付款单子表ID
     * @return 结果
     */
    @Override
    public int deletePayInfoChildByPid(Integer[] ids){
        return payInfoChildMapper.deletePayInfoChildByPid(ids);
    }
}
