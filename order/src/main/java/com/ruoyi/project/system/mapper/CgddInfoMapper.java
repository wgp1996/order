package com.ruoyi.project.system.mapper;

import java.util.List;
import com.ruoyi.project.system.domain.CgddInfo;

/**
 * 采购订单主表Mapper接口
 * 
 * @author ruoyi
 * @date 2021-01-05
 */
public interface CgddInfoMapper 
{
    /**
     * 查询首页信息
     * @return 采购订单主表
     */
    public CgddInfo selectIndexCount();
    /**
     * 查询采购订单主表
     * 
     * @param id 采购订单主表ID
     * @return 采购订单主表
     */
    public CgddInfo selectCgddInfoById(Long id);
    /**
     * 批量生效采购订单
     *
     * @param ids 需要生效的进货单ID
     * @return 结果
     */
    public int updateCgddInfoStatus(Long[] ids);
    /**
     * 查询采购订单主表列表
     * 
     * @param cgddInfo 采购订单主表
     * @return 采购订单主表集合
     */
    public List<CgddInfo> selectCgddInfoList(CgddInfo cgddInfo);

    /**
     * 新增采购订单主表
     * 
     * @param cgddInfo 采购订单主表
     * @return 结果
     */
    public int insertCgddInfo(CgddInfo cgddInfo);

    /**
     * 修改采购订单主表
     * 
     * @param cgddInfo 采购订单主表
     * @return 结果
     */
    public int updateCgddInfo(CgddInfo cgddInfo);

    /**
     * 删除采购订单主表
     * 
     * @param id 采购订单主表ID
     * @return 结果
     */
    public int deleteCgddInfoById(Long id);

    /**
     * 批量删除采购订单主表
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteCgddInfoByIds(Long[] ids);
    /**
     * 批量取消进货单
     *
     * @param ids 需要生效的进货单ID
     * @return 结果
     */
    public int cancleCgddInfoStatus(Long[] ids);
    /**
     * 查询是否存在下级采购订单
     *
     * @return 结果
     */
    public int checkChildOrder(String djNumber);
}
