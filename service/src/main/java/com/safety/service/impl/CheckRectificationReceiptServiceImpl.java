package com.safety.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.safety.entity.CheckRectificationReceipt;
import com.safety.mapper.CheckRectificationReceiptMapper;
import com.safety.service.ICheckRectificationReceiptService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 隐患整改回执单 服务实现类
 * </p>
 *
 * @author safety
 * @since 2019-05-23
 */
@Service
public class CheckRectificationReceiptServiceImpl extends ServiceImpl<CheckRectificationReceiptMapper, CheckRectificationReceipt> implements ICheckRectificationReceiptService {

    @Autowired
    private CheckRectificationReceiptMapper checkRectificationReceiptMapper;

    @Override
    public PageInfo<CheckRectificationReceipt> getByPage(Integer currentPage, Integer pageSize, String orgId) {
        PageHelper.startPage(currentPage, pageSize);
        Map map = new HashMap();
        map.put("orgFk",orgId);
        List<CheckRectificationReceipt> CheckRectificationReceipts = checkRectificationReceiptMapper.selectByParam(map);
        return new PageInfo<>(CheckRectificationReceipts);
    }
}
