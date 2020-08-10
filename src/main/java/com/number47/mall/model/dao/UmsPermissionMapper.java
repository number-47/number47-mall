package com.number47.mall.model.dao;

import com.number47.mall.model.entity.UmsPermission;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * <p>
 * 后台用户权限表 Mapper 接口
 * </p>
 *
 * @author number47
 * @since 2020-08-07
 */
@Repository
public interface UmsPermissionMapper extends BaseMapper<UmsPermission> {

    /**
     * 获取用户所有权限(包括+-权限)
     * @param adminId
     * @return
     */
    List<UmsPermission> getPermissionList(@Param("adminId") Long adminId);

}
