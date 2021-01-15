package com.ruoyi.project.system.mapper;

import java.util.List;
import com.ruoyi.project.system.domain.PayInfoChild;

/**
 * 付款单子表Mapper接口
 * 
 * @author ruoyi
 * @date 2021-01-06
 */
public interface PayInfoChildMapper 
{
    /**
     * 查询付款单子表
     * 
     * @param id 付款单子表ID
     * @return 付款单子表
     */
    public PayInfoChild selectPayInfoChildById(Integer id);

    /**
     * 查询付款单子表列表
     * 
     * @param payInfoChild 付款单子表
     * @return 付款单子表集合
     */
    public List<PayInfoChild> selectPayInfoChildList(PayInfoChild payInfoChild);
    /**
     * 查询付款单子表
     *
     * @param djNumber 付款单单号
     * @return 付款单子表
     */
    public  List<PayInfoChild> selectPayInfoChildByNum(String djNumber);
    /**
     * 新增付款单子表
     * 
     * @param payInfoChild 付款单子表
     * @return 结果
     */
    public int insertPayInfoChild(PayInfoChild payInfoChild);

    /**
     * 修改付款单子表
     * 
     * @param payInfoChild 付款单子表
     * @return 结果
     */
    public int updatePayInfoChild(PayInfoChild payInfoChild);

    /**
     * 删除付款单子表
     * 
     * @param id 付款单子表ID
     * @return 结果
     */
    public int deletePayInfoChildById(Integer id);

    /**
     * 批量删除付款单子表
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deletePayInfoChildByIds(Integer[] ids);
    /**
     * 批量删除付款单子表
     *
     * @param ids 需要删除的付款单子表ID
     * @return 结果
     */
    public int deletePayInfoChildByPid(Integer[] ids);
}
