package com.safety.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.safety.entity.CheckDayRecord;
import com.safety.excel.util.StringUtils;
import com.safety.exception.ProgramException;
import com.safety.mapper.CheckDayRecordMapper;
import com.safety.service.ICheckDayRecordService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 日治理记录填写 服务实现类
 * </p>
 *
 * @author safety
 * @since 2019-05-23
 */
@Service
public class CheckDayRecordServiceImpl extends ServiceImpl<CheckDayRecordMapper, CheckDayRecord> implements ICheckDayRecordService {

    @Override
    public List<CheckDayRecord> getCheckDayRecord(){
        return baseMapper.selectCheckDayRecord();
    }

    @Override
    public CheckDayRecord getCheckDayRecordListById(String recordId) throws Exception{
        if(StringUtils.isEmpty(recordId)){
            throw new ProgramException("参数错误");
        }
        return baseMapper.selectCheckDayRecordListById(recordId);
    }

    @Override
    public PageInfo<CheckDayRecord> getByPage(Integer currentPage, Integer pageSize) {
        PageHelper.startPage(currentPage, pageSize);
        List<CheckDayRecord> checkMonthRecords = baseMapper.selectAll();
        return new PageInfo<>(checkMonthRecords);
    }
}
