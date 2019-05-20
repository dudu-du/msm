package com.welsee.mapper;

import com.welsee.entity.City;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author welsee
 * @since 2019-01-25
 */
public interface CityMapper extends BaseMapper<City> {
    List<City> selectByName(@Param("name") String name);
}
