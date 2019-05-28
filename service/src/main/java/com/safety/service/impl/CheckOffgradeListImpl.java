package com.safety.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.safety.entity.CheckOffgradeList;
import com.safety.mapper.CheckOffgradeListMapper;
import com.safety.service.ICheckOffgradeListService;
import org.springframework.stereotype.Service;

import java.util.List;


/**
 * <p>
 * 日治理记录 服务实现类
 * </p>
 *
 * @author safety
 * @since 2019-05-23
 */
@Service
public class CheckOffgradeListImpl extends ServiceImpl<CheckOffgradeListMapper, CheckOffgradeList> implements ICheckOffgradeListService {

    @Override
    public PageInfo<CheckOffgradeList> getByPage(Integer currentPage, Integer pageSize) {
        PageHelper.startPage(currentPage, pageSize);
        List<CheckOffgradeList> checkMonthRecords = baseMapper.selectAll();
        return new PageInfo<>(checkMonthRecords);
    }
}
