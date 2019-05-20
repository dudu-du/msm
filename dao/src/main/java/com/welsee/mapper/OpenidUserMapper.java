package com.welsee.mapper;

import com.welsee.entity.OpenidUser;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.welsee.entity.Person;
import com.welsee.extentity.ExtOpenidChild;
import com.welsee.extentity.ExtOpenidPatriarch;
import com.welsee.extentity.ExtPerson;
import org.apache.ibatis.annotations.Param;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author welsee
 * @since 2019-01-28
 */
public interface OpenidUserMapper extends BaseMapper<OpenidUser> {
    /**
     * 检查是否已绑定
     * @param openid    openid
     * @param userId    用户ID
     * @param userType  用户类型
     * @param childId   孩子ID（家长的话必填）
     * @return
     */
    boolean checkBinding(@Param("openid") String openid,
                         @Param("userId")String userId,
                         @Param("userType")String userType,
                         @Param("childId")String childId);

    /**
     * 获取用户ID
     * @param openid
     * @return
     */
    String getUserIdByOpenid(@Param("openid")String openid);

    /**
     * 解除绑定
     * @param openid        openid
     * @param studentId     可选学生ID，多个以逗号分隔
     * @param modifydatetime 更新时间
     * @return
     */
    boolean unboundStudent(@Param("openid")String openid, @Param("studentId")String studentId,
                    @Param("modifydatetime")LocalDateTime modifydatetime);

    /**
     * 常规的解绑
     * @param openid
     * @param userType
     * @param modifydatetime
     * @return
     */
    boolean unBoundConventional(@Param("openid")String openid, @Param("userType")String userType,
                                @Param("modifydatetime")LocalDateTime modifydatetime);
    /**
     * 获取家长openid信息
     * @param openid
     * @return
     */
    Map getPariarchOpenidInfo(@Param("openid")String openid);

    /**
     * 获取用户信息
     * @param openid
     * @return
     */
    Map getOpenidPerson(@Param("openid")String openid);


    /**
     * 修改绑定状态
     * @param openid
     * @param userId
     * @param del
     * @param modifydatetime
     * @return
     */
    int updateBindStatus(@Param("openid")String openid,
                              @Param("userId")String userId,
                              @Param("isDel") int del,
                              @Param("modifydatetime")LocalDateTime modifydatetime);

    /**
     * 修改家长的绑定状态
     * @param openid
     * @param del
     * @param modifydatetime
     * @return
     */
    int updateBindStatusPatriarch(@Param("openid")String openid,
                                  @Param("isDel")int del,
                                  @Param("modifydatetime")LocalDateTime modifydatetime);
    /**
     * 获取常规用户openid信息
     * @param openid
     * @return
     */
    Map getSimpleOpenidInfo(@Param("openid")String openid);

    /**
     * 获取教师openid信息
     * @param openid
     * @return
     */
    Map getTeacherOpenidInfo(@Param("openid")String openid);

    /**
     * 获取我的孩子
     * @param openid
     * @param schoolYear 学年
     * @return
     */
    List<ExtOpenidChild> getStudentByOpenid(@Param("openid")String openid,@Param("schoolYear")Integer schoolYear);

    /**
     * 获取孩子的家长
     * @param studentId
     * @return
     */
    List<ExtOpenidPatriarch> getPatriarchByStudentId(@Param("studentId")String studentId);

    /**
     * 获取用户信息
     *
     * @param openid
     * @param userType
     * @return
     */
    ExtPerson getPersonByOpenid(@Param("openid") String openid, @Param("userType") String userType);

    /**
     * 获取主键
     * @param openid
     * @param userId
     * @param userType
     * @return
     */
    String getPkId(@Param("openid") String openid, @Param("userId")String userId,@Param("userType") String userType);

}
