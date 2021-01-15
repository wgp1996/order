package com.ruoyi.project.system.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.project.system.mapper.FpInfoMapper;
import com.ruoyi.project.system.domain.FpInfo;
import com.ruoyi.project.system.service.IFpInfoService;

/**
 * 发票建档Service业务层处理
 * 
 * @author ruoyi
 * @date 2021-01-06
 */
@Service
public class FpInfoServiceImpl implements IFpInfoService 
{
    @Autowired
    private FpInfoMapper fpInfoMapper;

    /**
     * 查询发票建档
     * 
     * @param id 发票建档ID
     * @return 发票建档
     */
    @Override
    public FpInfo selectFpInfoById(Integer id)
    {
        return fpInfoMapper.selectFpInfoById(id);
    }
    /**
     * 供应商查询发票
     *
     * @param khCode 供应商代码
     * @return 发票建档
     */
    @Override
    public FpInfo selectFpInfoByKh(String khCode){
        return fpInfoMapper.selectFpInfoByKh(khCode);
    }
    /**
     * 查询发票建档列表
     * 
     * @param fpInfo 发票建档
     * @return 发票建档
     */
    @Override
    public List<FpInfo> selectFpInfoList(FpInfo fpInfo)
    {
        return fpInfoMapper.selectFpInfoList(fpInfo);
    }

    /**
     * 新增发票建档
     * 
     * @param fpInfo 发票建档
     * @return 结果
     */
    @Override
    public int insertFpInfo(FpInfo fpInfo)
    {
        fpInfo.setCreateTime(DateUtils.getNowDate());
        return fpInfoMapper.insertFpInfo(fpInfo);
    }
    /**
     * 检查发票信息
     *
     * @return 结果
     */
    @Override
    public int selectFpInfoByKhName(int id,String khCode){
        return fpInfoMapper.selectFpInfoByKhName(id,khCode);
    }

    /**
     * 修改发票建档
     * 
     * @param fpInfo 发票建档
     * @return 结果
     */
    @Override
    public int updateFpInfo(FpInfo fpInfo)
    {
        fpInfo.setUpdateTime(DateUtils.getNowDate());
        return fpInfoMapper.updateFpInfo(fpInfo);
    }

    /**
     * 批量删除发票建档
     * 
     * @param ids 需要删除的发票建档ID
     * @return 结果
     */
    @Override
    public int deleteFpInfoByIds(Integer[] ids)
    {
        return fpInfoMapper.deleteFpInfoByIds(ids);
    }

    /**
     * 删除发票建档信息
     * 
     * @param id 发票建档ID
     * @return 结果
     */
    @Override
    public int deleteFpInfoById(Integer id)
    {
        return fpInfoMapper.deleteFpInfoById(id);
    }
}
