package com.safety.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.safety.entity.CheckDangerLedger;
import com.safety.mapper.CheckDangerLedgerMapper;
import com.safety.service.ICheckDangerLedgerService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 隐患治理信息台账 服务实现类
 * </p>
 *
 * @author safety
 * @since 2019-05-23
 */
@Service
public class CheckDangerLedgerServiceImpl extends ServiceImpl<CheckDangerLedgerMapper, CheckDangerLedger> implements ICheckDangerLedgerService {

    @Autowired
    private CheckDangerLedgerMapper checkDangerLedgerMapper;
    @Override
    public PageInfo<CheckDangerLedger> getByPage(Integer currentPage, Integer pageSize, String orgId) {
        PageHelper.startPage(currentPage, pageSize);
        Map map = new HashMap();
        map.put("orgFk",orgId);
        List<CheckDangerLedger> checkMonthRecords = checkDangerLedgerMapper.selectByParam(map);
        return new PageInfo<>(checkMonthRecords);
    }
}
