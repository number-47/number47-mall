package com.number47.mall.model.service;

import com.number47.mall.model.entity.UmsAdmin;
import com.number47.mall.dto.UmsAdminDto;
import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.number47.mall.model.entity.UmsPermission;

import java.util.List;
/**
 * <p>
 * 后台用户表 服务类
 * </p>
 *
 * @author number47
 * @since 2020-08-07
 */
public interface UmsAdminService extends IService<UmsAdmin> {
   List<UmsAdmin> listAllUmsAdmin(UmsAdminDto umsAdminDto);

   int createUmsAdmin(UmsAdminDto umsAdminDto);

   int updateUmsAdmin(Long id, UmsAdminDto umsAdminDto);

   int deleteUmsAdmin(Long id);

    IPage<UmsAdmin> listUmsAdmin(Page<UmsAdmin> page, UmsAdminDto umsAdminDto);

    UmsAdmin getUmsAdmin(Long id);

    /**
     * 根据用户名获取后台管理员
     */
    UmsAdmin getAdminByUsername(String username);

    /**
     * 注册功能
     */
    UmsAdmin register(UmsAdmin umsAdminParam);

    /**
     * 登录功能
     * @param username 用户名
     * @param password 密码
     * @return 生成的JWT的token
     */
    String login(String username, String password);

    /**
     * 获取用户所有权限（包括角色权限和+-权限）
     * @param adminId
     * @return
     */
    List<UmsPermission> getPermissionList(Long adminId);
}
