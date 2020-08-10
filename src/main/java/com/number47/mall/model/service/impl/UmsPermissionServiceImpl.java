package com.number47.mall.model.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.number47.mall.model.entity.UmsPermission;
import com.number47.mall.dto.UmsPermissionDto;
import com.number47.mall.model.dao.UmsPermissionMapper;
import com.number47.mall.model.service.UmsPermissionService;
import org.springframework.stereotype.Service;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * <p>
 * 后台用户权限表 服务实现类
 * </p>
 *
 * @author number47
 * @since 2020-08-07
 */
@Service
public class UmsPermissionServiceImpl extends ServiceImpl<UmsPermissionMapper, UmsPermission> implements UmsPermissionService {
    @Autowired
    private UmsPermissionMapper umsPermissionMapper;

    @Override
    public List<UmsPermission> listAllUmsPermission(UmsPermissionDto umsPermissionDto) {
        UmsPermission umsPermission = new UmsPermission();
        BeanUtils.copyProperties(umsPermissionDto,umsPermission);
        QueryWrapper<UmsPermission> queryWrapper = new QueryWrapper<>(umsPermission);
        return umsPermissionMapper.selectList(queryWrapper);
    }

    @Override
    public int createUmsPermission(UmsPermissionDto umsPermissionDto) {
        UmsPermission umsPermission = new UmsPermission();
        BeanUtils.copyProperties(umsPermissionDto,umsPermission);
        return umsPermissionMapper.insert(umsPermission);
    }

    @Override
    public int updateUmsPermission(Long id, UmsPermissionDto umsPermissionDto) {
         UmsPermission umsPermission = new UmsPermission();
         BeanUtils.copyProperties(umsPermissionDto,umsPermission);
         umsPermission.setId(id);
         return umsPermissionMapper.updateById(umsPermission);
    }

    @Override
    public int deleteUmsPermission(Long id) {
         return umsPermissionMapper.deleteById(id);
    }

    @Override
    public IPage<UmsPermission> listUmsPermission(Page<UmsPermission> page, UmsPermissionDto umsPermissionDto) {
        UmsPermission umsPermission = new UmsPermission();
        BeanUtils.copyProperties(umsPermissionDto,umsPermission);
        QueryWrapper<UmsPermission> queryWrapper = new QueryWrapper<>(umsPermission);
        return umsPermissionMapper.selectPage(page,queryWrapper);
    }

    @Override
    public UmsPermission getUmsPermission(Long id) {
         return umsPermissionMapper.selectById(id);
    }

    @Override
    public List<UmsPermission> getPermissionList(Long adminId) {
        return umsPermissionMapper.getPermissionList(adminId);
    }
}
