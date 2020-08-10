package com.number47.mall.model.dao;

import com.number47.mall.model.entity.UmsAdminRoleRelation;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.number47.mall.model.entity.UmsPermission;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * <p>
 * 后台用户和角色关系表 Mapper 接口
 * </p>
 *
 * @author number47
 * @since 2020-08-07
 */
@Repository
public interface UmsAdminRoleRelationMapper extends BaseMapper<UmsAdminRoleRelation> {



}
