package com.ruoyi.project.system.mapper;

import java.util.List;
import com.ruoyi.project.system.domain.CgddInfoChild;

/**
 * 采购订单子表Mapper接口
 * 
 * @author ruoyi
 * @date 2021-01-05
 */
public interface CgddInfoChildMapper 
{
    /**
     * 查询采购订单子表
     * 
     * @param id 采购订单子表ID
     * @return 采购订单子表
     */
    public CgddInfoChild selectCgddInfoChildById(Long id);

    public List<CgddInfoChild> selectCgddInfoChildByNum(String djNumber);
    /**
     * 查询采购订单子表列表
     * 
     * @param cgddInfoChild 采购订单子表
     * @return 采购订单子表集合
     */
    public List<CgddInfoChild> selectCgddInfoChildList(CgddInfoChild cgddInfoChild);
    /**
     * 付款单查询采购订单列表
     *
     * @param cgddInfoChild 采购订单子表
     * @return 采购订单子表集合
     */
    public List<CgddInfoChild> selectCgddOrderList(CgddInfoChild cgddInfoChild);
    /**
     * 查询首页待办提醒
     *
     * @param status 作为类型
     * @return 采购订单子表集合
     */
    public List<CgddInfoChild> selectCgddMessageList(Integer status,String orderNumber);
    /**
     * 订单汇总
     * @return
     */
    public List<CgddInfoChild> selectCgddAllList(CgddInfoChild cgddInfoChild);
    /**
     * 检测订单是否全部采购
     * @param djNumber
     * @return
     */
    public int checkAllOrder(String djNumber);
    /**
     * 根据PID批量删除采购订单子表
     *
     * @param ids 需要删除的采购订单子表ID
     * @return 结果
     */
    public int deleteCgddInfoChildByPid(Long[] ids);
    /**
     * 新增采购订单子表
     * 
     * @param cgddInfoChild 采购订单子表
     * @return 结果
     */
    public int insertCgddInfoChild(CgddInfoChild cgddInfoChild);

    /**
     * 修改采购订单子表
     * 
     * @param cgddInfoChild 采购订单子表
     * @return 结果
     */
    public int updateCgddInfoChild(CgddInfoChild cgddInfoChild);
    /**
     * 批量生效采购订单
     *
     * @param ids 需要生效的进货单ID
     * @return 结果
     */
    public int updateCgddInfoChildStatus(Long[] ids);

    /**
     * 删除采购订单子表
     * 
     * @param id 采购订单子表ID
     * @return 结果
     */
    public int deleteCgddInfoChildById(Long id);

    /**
     * 批量删除采购订单子表
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteCgddInfoChildByIds(Long[] ids);
}
