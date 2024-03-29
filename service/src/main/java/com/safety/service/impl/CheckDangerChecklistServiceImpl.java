package com.safety.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.safety.entity.CheckDangerChecklist;
import com.safety.mapper.CheckDangerChecklistMapper;
import com.safety.service.ICheckDangerChecklistService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 隐患排查清单 服务实现类
 * </p>
 *
 * @author safety
 * @since 2019-05-23
 */
@Service
public class CheckDangerChecklistServiceImpl extends ServiceImpl<CheckDangerChecklistMapper, CheckDangerChecklist> implements ICheckDangerChecklistService {

    @Autowired
    private CheckDangerChecklistMapper checkDangerChecklistMapper;
    @Override
    public PageInfo<CheckDangerChecklist> getByPage(Integer currentPage, Integer pageSize, String orgId) {
        PageHelper.startPage(currentPage, pageSize);
        Map map = new HashMap();
        map.put("orgFk",orgId);
        List<CheckDangerChecklist> CheckDangerChecklists = checkDangerChecklistMapper.selectByParam(map);
        return new PageInfo<>(CheckDangerChecklists);
    }
}
