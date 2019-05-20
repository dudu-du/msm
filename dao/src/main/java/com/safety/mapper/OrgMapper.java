package com.safety.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.safety.entity.Org;
import com.safety.extentity.ExtOrg;
import org.apache.ibatis.annotations.Param;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author safety
 * @since 2018-11-22
 */
public interface OrgMapper extends BaseMapper<Org> {
    int selectCount(Org org);
    List<Org> getOrgListByPage(Map map);
    List<Org> getOrgListByStr(Map map);
    List<ExtOrg> getOrgList(@Param("parentId") String parentId);
    List<ExtOrg> getDepartmentList(@Param("parentId") String parentId);
    List<Org> getOrgListByType(@Param("type") String type);
    List<Org> getOrg(Org org);
    Integer deleteById(@Param("orgId") String orgId, @Param("time") LocalDateTime time);
    List<ExtOrg> getExtOrgList(@Param("orgId") String orgId);
    List<Org> getOrgRow(@Param("parentId") String parentId);
    List<Org> getOrgChildList(@Param("parentId") String parentId);
}
