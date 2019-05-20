package com.welsee.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.welsee.entity.Menu;
import org.apache.ibatis.annotations.Param;

import java.util.List;
/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author welsee
 * @since 2018-11-07
 */
public interface MenuMapper extends BaseMapper<Menu> {
    /**
     * 获取用户权限对于的左侧树列表
     *
     * @param userId
     * @return list
     */
    List<Menu> getMenuByUser(@Param("userId") String userId);

    /**
     * 根据条件获取menu列表
     *
     * @param menu
     * @return list
     */
    List<Menu> getMenu(Menu menu);
}
