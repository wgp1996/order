package com.ruoyi.project.system.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.project.system.mapper.CgddInfoChildMapper;
import com.ruoyi.project.system.domain.CgddInfoChild;
import com.ruoyi.project.system.service.ICgddInfoChildService;

/**
 * 采购订单子表Service业务层处理
 * 
 * @author ruoyi
 * @date 2021-01-05
 */
@Service
public class CgddInfoChildServiceImpl implements ICgddInfoChildService 
{
    @Autowired
    private CgddInfoChildMapper cgddInfoChildMapper;

    /**
     * 查询采购订单子表
     * 
     * @param id 采购订单子表ID
     * @return 采购订单子表
     */
    @Override
    public CgddInfoChild selectCgddInfoChildById(Long id)
    {
        return cgddInfoChildMapper.selectCgddInfoChildById(id);
    }
    @Override
    public List<CgddInfoChild> selectCgddInfoChildByNum(String djNumber){
        return cgddInfoChildMapper.selectCgddInfoChildByNum(djNumber);
    }
    /**
     * 付款单查询采购订单列表
     *
     * @param cgddInfoChild 采购订单子表
     * @return 采购订单子表集合
     */
    @Override
    public List<CgddInfoChild> selectCgddOrderList(CgddInfoChild cgddInfoChild){
        return cgddInfoChildMapper.selectCgddOrderList(cgddInfoChild);
    }
    /**
     * 查询首页待办提醒
     *
     * @param status 作为类型
     * @return 采购订单子表集合
     */
    @Override
    public List<CgddInfoChild> selectCgddMessageList(Integer status,String orderNumber){
        return cgddInfoChildMapper.selectCgddMessageList(status,orderNumber);
    }
    /**
     * 订单汇总
     * @return
     */
    @Override
    public List<CgddInfoChild> selectCgddAllList(CgddInfoChild cgddInfoChild){
        return cgddInfoChildMapper.selectCgddAllList(cgddInfoChild);
    }
    /**
     * 根据PID批量删除采购订单子表
     *
     * @param ids 需要删除的采购订单子表ID
     * @return 结果
     */
    @Override
    public int deleteCgddInfoChildByPid(Long[] ids){
        return cgddInfoChildMapper.deleteCgddInfoChildByPid(ids);
    }
    /**
     * 查询采购订单子表列表
     * 
     * @param cgddInfoChild 采购订单子表
     * @return 采购订单子表
     */
    @Override
    public List<CgddInfoChild> selectCgddInfoChildList(CgddInfoChild cgddInfoChild)
    {
        return cgddInfoChildMapper.selectCgddInfoChildList(cgddInfoChild);
    }

    /**
     * 检测订单是否全部采购
     * @param djNumber
     * @return
     */
    @Override
    public int checkAllOrder(String djNumber){
        return cgddInfoChildMapper.checkAllOrder(djNumber);
    }
    /**
     * 新增采购订单子表
     * 
     * @param cgddInfoChild 采购订单子表
     * @return 结果
     */
    @Override
    public int insertCgddInfoChild(CgddInfoChild cgddInfoChild)
    {
        cgddInfoChild.setCreateTime(DateUtils.getNowDate());
        return cgddInfoChildMapper.insertCgddInfoChild(cgddInfoChild);
    }
    /**
     * 批量删除采购订单子表
     *
     * @param ids 需要删除的采购订单子表ID
     * @return 结果
     */
    @Override
    public int updateCgddInfoChildStatus(Long[] ids){
        return cgddInfoChildMapper.updateCgddInfoChildStatus(ids);
    }
    /**
     * 修改采购订单子表
     * 
     * @param cgddInfoChild 采购订单子表
     * @return 结果
     */
    @Override
    public int updateCgddInfoChild(CgddInfoChild cgddInfoChild)
    {
        cgddInfoChild.setUpdateTime(DateUtils.getNowDate());
        return cgddInfoChildMapper.updateCgddInfoChild(cgddInfoChild);
    }

    /**
     * 批量删除采购订单子表
     * 
     * @param ids 需要删除的采购订单子表ID
     * @return 结果
     */
    @Override
    public int deleteCgddInfoChildByIds(Long[] ids)
    {
        return cgddInfoChildMapper.deleteCgddInfoChildByIds(ids);
    }

    /**
     * 删除采购订单子表信息
     * 
     * @param id 采购订单子表ID
     * @return 结果
     */
    @Override
    public int deleteCgddInfoChildById(Long id)
    {
        return cgddInfoChildMapper.deleteCgddInfoChildById(id);
    }
}
