package com.ruoyi.project.system.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.project.system.mapper.CgddInfoMapper;
import com.ruoyi.project.system.domain.CgddInfo;
import com.ruoyi.project.system.service.ICgddInfoService;

/**
 * 采购订单主表Service业务层处理
 * 
 * @author ruoyi
 * @date 2021-01-05
 */
@Service
public class CgddInfoServiceImpl implements ICgddInfoService 
{
    @Autowired
    private CgddInfoMapper cgddInfoMapper;
    /**
     * 查询首页信息
     * @return 采购订单主表
     */
    @Override
    public CgddInfo selectIndexCount(){
        return cgddInfoMapper.selectIndexCount();
    }
    /**
     * 查询采购订单主表
     * 
     * @param id 采购订单主表ID
     * @return 采购订单主表
     */
    @Override
    public CgddInfo selectCgddInfoById(Long id)
    {
        return cgddInfoMapper.selectCgddInfoById(id);
    }
    /**
     * 批量生效采购订单
     *
     * @param ids 需要生效的进货单ID
     * @return 结果
     */
    @Override
    public int updateCgddInfoStatus(Long[] ids){
        return cgddInfoMapper.updateCgddInfoStatus(ids);
    }
    /**
     * 查询采购订单主表列表
     * 
     * @param cgddInfo 采购订单主表
     * @return 采购订单主表
     */
    @Override
    public List<CgddInfo> selectCgddInfoList(CgddInfo cgddInfo)
    {
        return cgddInfoMapper.selectCgddInfoList(cgddInfo);
    }

    /**
     * 新增采购订单主表
     * 
     * @param cgddInfo 采购订单主表
     * @return 结果
     */
    @Override
    public int insertCgddInfo(CgddInfo cgddInfo)
    {
        cgddInfo.setCreateTime(DateUtils.getNowDate());
        return cgddInfoMapper.insertCgddInfo(cgddInfo);
    }

    /**
     * 修改采购订单主表
     * 
     * @param cgddInfo 采购订单主表
     * @return 结果
     */
    @Override
    public int updateCgddInfo(CgddInfo cgddInfo)
    {
        cgddInfo.setUpdateTime(DateUtils.getNowDate());
        return cgddInfoMapper.updateCgddInfo(cgddInfo);
    }

    /**
     * 批量删除采购订单主表
     * 
     * @param ids 需要删除的采购订单主表ID
     * @return 结果
     */
    @Override
    public int deleteCgddInfoByIds(Long[] ids)
    {
        return cgddInfoMapper.deleteCgddInfoByIds(ids);
    }

    /**
     * 删除采购订单主表信息
     * 
     * @param id 采购订单主表ID
     * @return 结果
     */
    @Override
    public int deleteCgddInfoById(Long id)
    {
        return cgddInfoMapper.deleteCgddInfoById(id);
    }
    /**
     * 批量取消进货单
     *
     * @param ids 需要生效的进货单ID
     * @return 结果
     */
    @Override
    public int cancleCgddInfoStatus(Long[] ids){
        return cgddInfoMapper.cancleCgddInfoStatus(ids);
    }
    /**
     * 查询是否存在下级采购订单
     *
     * @return 结果
     */
    @Override
    public int checkChildOrder(String djNumber){
        return cgddInfoMapper.checkChildOrder(djNumber);
    }
}
