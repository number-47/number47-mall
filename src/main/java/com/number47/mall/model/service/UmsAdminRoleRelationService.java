package com.number47.mall.model.service;

import com.number47.mall.model.entity.UmsAdminRoleRelation;
import com.number47.mall.dto.UmsAdminRoleRelationDto;
import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import java.util.List;

/**
 * <p>
 * 后台用户和角色关系表 服务类
 * </p>
 *
 * @author number47
 * @since 2020-08-07
 */
public interface UmsAdminRoleRelationService extends IService<UmsAdminRoleRelation> {

    List<UmsAdminRoleRelation> listAllUmsAdminRoleRelation(UmsAdminRoleRelationDto umsAdminRoleRelationDto);

    int createUmsAdminRoleRelation(UmsAdminRoleRelationDto umsAdminRoleRelationDto);

    int updateUmsAdminRoleRelation(Long id, UmsAdminRoleRelationDto umsAdminRoleRelationDto);

    int deleteUmsAdminRoleRelation(Long id);

    IPage<UmsAdminRoleRelation> listUmsAdminRoleRelation(Page<UmsAdminRoleRelation> page, UmsAdminRoleRelationDto umsAdminRoleRelationDto);

    UmsAdminRoleRelation getUmsAdminRoleRelation(Long id);

}
