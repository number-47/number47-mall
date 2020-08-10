package com.number47.mall.model.service;

import com.number47.mall.common.CommonResult;
import com.number47.mall.model.entity.UmsMember;
import com.number47.mall.dto.UmsMemberDto;
import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import java.util.List;
/**
 * <p>
 * 会员表 服务类
 * </p>
 *
 * @author number47
 * @since 2020-08-07
 */
public interface UmsMemberService extends IService<UmsMember> {
   List<UmsMember> listAllUmsMember(UmsMemberDto umsMemberDto);

   int createUmsMember(UmsMemberDto umsMemberDto);

   int updateUmsMember(Long id, UmsMemberDto umsMemberDto);

   int deleteUmsMember(Long id);

    IPage<UmsMember> listUmsMember(Page<UmsMember> page, UmsMemberDto umsMemberDto);

    UmsMember getUmsMember(Long id);

    /**
     * 生成手机验证码
     * @param telephone 手机号
     * @return
     */
    CommonResult<String> generateAuthCode(String telephone);


    /**
     * 判断验证码和手机号码是否匹配
     * @param telephone 手机号
     * @param authCode 验证码
     * @return
     */
    CommonResult<String> verifyAuthCode(String telephone, String authCode);
}
