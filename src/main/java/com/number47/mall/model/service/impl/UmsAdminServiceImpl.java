package com.number47.mall.model.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.number47.mall.common.util.JwtTokenUtil;
import com.number47.mall.model.entity.UmsAdmin;
import com.number47.mall.dto.UmsAdminDto;
import com.number47.mall.model.dao.UmsAdminMapper;
import com.number47.mall.model.service.UmsAdminService;
import com.number47.mall.model.entity.UmsPermission;
import com.number47.mall.model.service.UmsPermissionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;
import java.util.List;

/**
 * <p>
 * 后台用户表 服务实现类
 * </p>
 *
 * @author number47
 * @since 2020-08-07
 */
@Service
public class UmsAdminServiceImpl extends ServiceImpl<UmsAdminMapper, UmsAdmin> implements UmsAdminService {
    private static final Logger LOGGER = LoggerFactory.getLogger(UmsAdminServiceImpl.class);

    @Autowired
    private JwtTokenUtil jwtTokenUtil;
    @Value("${jwt.tokenHead}")
    private String tokenHead;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private UserDetailsService userDetailsService;
    @Autowired
    private UmsAdminMapper umsAdminMapper;
    @Autowired
    private UmsPermissionService umsPermissionService;


    @Override
    public List<UmsAdmin> listAllUmsAdmin(UmsAdminDto umsAdminDto) {
        UmsAdmin umsAdmin = new UmsAdmin();
        BeanUtils.copyProperties(umsAdminDto,umsAdmin);
        QueryWrapper<UmsAdmin> queryWrapper = new QueryWrapper<>(umsAdmin);
        return umsAdminMapper.selectList(queryWrapper);
    }

    @Override
    public int createUmsAdmin(UmsAdminDto umsAdminDto) {
        UmsAdmin umsAdmin = new UmsAdmin();
        BeanUtils.copyProperties(umsAdminDto,umsAdmin);
        return umsAdminMapper.insert(umsAdmin);
    }

    @Override
    public int updateUmsAdmin(Long id, UmsAdminDto umsAdminDto) {
         UmsAdmin umsAdmin = new UmsAdmin();
         BeanUtils.copyProperties(umsAdminDto,umsAdmin);
         umsAdmin.setId(id);
         return umsAdminMapper.updateById(umsAdmin);
    }

    @Override
    public int deleteUmsAdmin(Long id) {
         return umsAdminMapper.deleteById(id);
    }

    @Override
    public IPage<UmsAdmin> listUmsAdmin(Page<UmsAdmin> page, UmsAdminDto umsAdminDto) {
        UmsAdmin umsAdmin = new UmsAdmin();
        BeanUtils.copyProperties(umsAdminDto,umsAdmin);
        QueryWrapper<UmsAdmin> queryWrapper = new QueryWrapper<>(umsAdmin);
        return umsAdminMapper.selectPage(page,queryWrapper);
    }

    @Override
    public UmsAdmin getUmsAdmin(Long id) {
         return umsAdminMapper.selectById(id);
    }

    /**
     * 通过用户名查询用户（非模糊查询）
     * @param username 用户名
     * @return
     */
    @Override
    public UmsAdmin getAdminByUsername(String username) {
        QueryWrapper<UmsAdmin> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(UmsAdmin::getUsername,username);
        return umsAdminMapper.selectOne(queryWrapper);
    }

    /**
     * 注册用户
     * @param umsAdminParam
     * @return
     */
    @Override
    public UmsAdmin register(UmsAdmin umsAdminParam) {
        //查询是否有相同用户名的用户
        UmsAdmin umsAdmin = getAdminByUsername(umsAdminParam.getUsername());
        if (umsAdmin != null){
            return null;
        }
        BeanUtils.copyProperties(umsAdminParam, umsAdmin);
        umsAdmin.setCreateTime(LocalDateTime.now());
        umsAdmin.setStatus(1);
        //将密码进行加密操作
        String encodePassword = passwordEncoder.encode(umsAdmin.getPassword());
        umsAdmin.setPassword(encodePassword);
        umsAdminMapper.insert(umsAdmin);
        return umsAdmin;
    }

    /**
     * 登录
     * @param username 用户名
     * @param password 密码
     * @return
     */
    @Override
    public String login(String username, String password) {
        String token = null;
        try {
            UserDetails userDetails = userDetailsService.loadUserByUsername(username);
            if (!passwordEncoder.matches(password, userDetails.getPassword())) {
                throw new BadCredentialsException("密码不正确");
            }
            UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(authentication);
            token = jwtTokenUtil.generateToken(userDetails);
        } catch (AuthenticationException e) {
            LOGGER.warn("登录异常:{}", e.getMessage());
        }
        return token;
    }

    /**
     * 获取用户所有权限(包括+-权限)
     * @param adminId
     * @return
     */
    @Override
    public List<UmsPermission> getPermissionList(Long adminId) {
        return umsPermissionService.getPermissionList(adminId);
    }
}
