package com.number47.mall.model.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.number47.mall.model.entity.UmsAdminRoleRelation;
import com.number47.mall.dto.UmsAdminRoleRelationDto;
import com.number47.mall.model.dao.UmsAdminRoleRelationMapper;
import com.number47.mall.model.service.UmsAdminRoleRelationService;
import org.springframework.stereotype.Service;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * <p>
 * 后台用户和角色关系表 服务实现类
 * </p>
 *
 * @author number47
 * @since 2020-08-07
 */
@Service
public class UmsAdminRoleRelationServiceImpl extends ServiceImpl<UmsAdminRoleRelationMapper, UmsAdminRoleRelation> implements UmsAdminRoleRelationService {
    @Autowired
    private UmsAdminRoleRelationMapper umsAdminRoleRelationMapper;

    @Override
    public List<UmsAdminRoleRelation> listAllUmsAdminRoleRelation(UmsAdminRoleRelationDto umsAdminRoleRelationDto) {
        UmsAdminRoleRelation umsAdminRoleRelation = new UmsAdminRoleRelation();
        BeanUtils.copyProperties(umsAdminRoleRelationDto,umsAdminRoleRelation);
        QueryWrapper<UmsAdminRoleRelation> queryWrapper = new QueryWrapper<>(umsAdminRoleRelation);
        return umsAdminRoleRelationMapper.selectList(queryWrapper);
    }

    @Override
    public int createUmsAdminRoleRelation(UmsAdminRoleRelationDto umsAdminRoleRelationDto) {
        UmsAdminRoleRelation umsAdminRoleRelation = new UmsAdminRoleRelation();
        BeanUtils.copyProperties(umsAdminRoleRelationDto,umsAdminRoleRelation);
        return umsAdminRoleRelationMapper.insert(umsAdminRoleRelation);
    }

    @Override
    public int updateUmsAdminRoleRelation(Long id, UmsAdminRoleRelationDto umsAdminRoleRelationDto) {
         UmsAdminRoleRelation umsAdminRoleRelation = new UmsAdminRoleRelation();
         BeanUtils.copyProperties(umsAdminRoleRelationDto,umsAdminRoleRelation);
         umsAdminRoleRelation.setId(id);
         return umsAdminRoleRelationMapper.updateById(umsAdminRoleRelation);
    }

    @Override
    public int deleteUmsAdminRoleRelation(Long id) {
         return umsAdminRoleRelationMapper.deleteById(id);
    }

    @Override
    public IPage<UmsAdminRoleRelation> listUmsAdminRoleRelation(Page<UmsAdminRoleRelation> page, UmsAdminRoleRelationDto umsAdminRoleRelationDto) {
        UmsAdminRoleRelation umsAdminRoleRelation = new UmsAdminRoleRelation();
        BeanUtils.copyProperties(umsAdminRoleRelationDto,umsAdminRoleRelation);
        QueryWrapper<UmsAdminRoleRelation> queryWrapper = new QueryWrapper<>(umsAdminRoleRelation);
        return umsAdminRoleRelationMapper.selectPage(page,queryWrapper);
    }

    @Override
    public UmsAdminRoleRelation getUmsAdminRoleRelation(Long id) {
         return umsAdminRoleRelationMapper.selectById(id);
    }

}
