package com.safety.mapper;

import com.safety.entity.CheckRectificationReceipt;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 * 隐患整改回执单 Mapper 接口
 * </p>
 *
 * @author safety
 * @since 2019-05-23
 */
public interface CheckRectificationReceiptMapper extends BaseMapper<CheckRectificationReceipt> {

    List<CheckRectificationReceipt> selectAll();
}
