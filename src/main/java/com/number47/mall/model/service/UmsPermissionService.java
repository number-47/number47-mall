package com.number47.mall.model.service;

import com.number47.mall.model.entity.UmsPermission;
import com.number47.mall.dto.UmsPermissionDto;
import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import java.util.List;
/**
 * <p>
 * 后台用户权限表 服务类
 * </p>
 *
 * @author number47
 * @since 2020-08-07
 */
public interface UmsPermissionService extends IService<UmsPermission> {
   List<UmsPermission> listAllUmsPermission(UmsPermissionDto umsPermissionDto);

   int createUmsPermission(UmsPermissionDto umsPermissionDto);

   int updateUmsPermission(Long id, UmsPermissionDto umsPermissionDto);

   int deleteUmsPermission(Long id);

    IPage<UmsPermission> listUmsPermission(Page<UmsPermission> page, UmsPermissionDto umsPermissionDto);

    UmsPermission getUmsPermission(Long id);

    /**
     * 获取用户所有权限（包括角色权限和+-权限）
     * 加权限是指用户比角色多出的权限，减权限是指用户比角色少的权限
     * @param adminId
     * @return
     */
    List<UmsPermission> getPermissionList(Long adminId);
}
