package com.number47.mall.model.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.number47.mall.common.CommonResult;
import com.number47.mall.common.redis.RedisService;
import com.number47.mall.model.dao.UmsMemberMapper;
import com.number47.mall.model.entity.UmsMember;
import com.number47.mall.dto.UmsMemberDto;
import com.number47.mall.model.service.UmsMemberService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;

/**
 * <p>
 * 会员表 服务实现类
 * </p>
 *
 * @author number47
 * @since 2020-08-07
 */
@Service
public class UmsMemberServiceImpl extends ServiceImpl<UmsMemberMapper, UmsMember> implements UmsMemberService {
    @Autowired
    private UmsMemberMapper umsMemberMapper;
    @Autowired
    private RedisService redisService;

    @Value("${redis.key.prefix.authCode}")
    private String REDIS_KEY_PREFIX_AUTH_CODE;
    @Value("${redis.key.expire.seconds}")
    private Long AUTH_CODE_EXPIRE_SECONDS;

    @Override
    public List<UmsMember> listAllUmsMember(UmsMemberDto umsMemberDto) {
        UmsMember umsMember = new UmsMember();
        BeanUtils.copyProperties(umsMemberDto,umsMember);
        QueryWrapper<UmsMember> queryWrapper = new QueryWrapper<>(umsMember);
        return umsMemberMapper.selectList(queryWrapper);
    }

    @Override
    public int createUmsMember(UmsMemberDto umsMemberDto) {
        UmsMember umsMember = new UmsMember();
        BeanUtils.copyProperties(umsMemberDto,umsMember);
        return umsMemberMapper.insert(umsMember);
    }

    @Override
    public int updateUmsMember(Long id, UmsMemberDto umsMemberDto) {
         UmsMember umsMember = new UmsMember();
         BeanUtils.copyProperties(umsMemberDto,umsMember);
         umsMember.setId(id);
         return umsMemberMapper.updateById(umsMember);
    }

    @Override
    public int deleteUmsMember(Long id) {
         return umsMemberMapper.deleteById(id);
    }

    @Override
    public IPage<UmsMember> listUmsMember(Page<UmsMember> page, UmsMemberDto umsMemberDto) {
        UmsMember umsMember = new UmsMember();
        BeanUtils.copyProperties(umsMemberDto,umsMember);
        QueryWrapper<UmsMember> queryWrapper = new QueryWrapper<>(umsMember);
        return umsMemberMapper.selectPage(page,queryWrapper);
    }

    @Override
    public UmsMember getUmsMember(Long id) {
         return umsMemberMapper.selectById(id);
    }

    /**
     * 生成手机验证码
     * @param telephone 手机号
     * @return
     */
    @Override
    public CommonResult<String> generateAuthCode(String telephone) {
        StringBuilder sb = new StringBuilder();
        Random random = new Random();
        for (int i = 0; i < 6; i++) {
            sb.append(random.nextInt(10));
        }
        //验证码绑定手机号并存储到redis
        redisService.set(REDIS_KEY_PREFIX_AUTH_CODE + telephone, sb.toString());
        redisService.expire(REDIS_KEY_PREFIX_AUTH_CODE + telephone, AUTH_CODE_EXPIRE_SECONDS);
        return CommonResult.success(sb.toString(), "获取验证码成功");
    }


    /**
     * 判断验证码和手机号码是否匹配
     * @param telephone 手机号
     * @param authCode 验证码
     * @return
     */
    @Override
    public CommonResult<String> verifyAuthCode(String telephone, String authCode) {
        if (StringUtils.isEmpty(authCode)) {
            return CommonResult.failed("请输入验证码");
        }
        String realAuthCode = redisService.get(REDIS_KEY_PREFIX_AUTH_CODE + telephone);
        boolean result = authCode.equals(realAuthCode);
        if (result) {
            return CommonResult.success(null, "验证码校验成功");
        } else {
            return CommonResult.failed("验证码不正确");
        }
    }
}
