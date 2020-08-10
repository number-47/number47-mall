package com.number47.mall.model.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.number47.mall.common.CommonPage;
import io.swagger.annotations.ApiOperation;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import com.number47.mall.model.entity.UmsMember;
import com.number47.mall.dto.UmsMemberDto;
import com.number47.mall.common.CommonResult;
import org.springframework.validation.BindingResult;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.RestController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.number47.mall.model.service.UmsMemberService;
import org.springframework.beans.factory.annotation.Autowired;


import java.util.List;
/**
 * <p>
 * 会员表 前端控制器
 * </p>
 *
 * @author number47
 * @since 2020-08-07
 */
@RestController
@Api(tags = "UmsMemberController", description = "会员登录注册有关接口")
@RequestMapping("/member")
public class UmsMemberController {
    @Autowired
    private UmsMemberService  umsMemberService;

    private static final Logger LOGGER = LoggerFactory.getLogger(UmsMemberController.class);
    /**
    * @Description: 获取会员表全部列表
    * @Param: [umsMemberDto]
    * @return: com.number47.mall.common.CommonResult<java.util.List<com.number47.mall.model.brand.entity.UmsMember>>
    * @Author: number47
    * @Date: 2020-08-07
    */
    @ApiModelProperty(value = "获取会员表全部列表")
    @RequestMapping(value = "listAll", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult<List<UmsMember>> getUmsMemberList(UmsMemberDto umsMemberDto) {
        return CommonResult.success(umsMemberService.listAllUmsMember(umsMemberDto));
    }

    /**
    * @Description: 添加会员表
    * @Param: [umsMember]
    * @return: com.number47.mall.common.CommonResult
    * @Author: number47
    * @Date: 2020-08-07
    */
    @ApiModelProperty(value = "添加会员表")
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult createUmsMember(@Validated @RequestBody UmsMemberDto umsMemberDto,BindingResult result) {
         if (result.hasErrors()) {
            return CommonResult.validateFailed(result.getFieldError().getDefaultMessage());
         }
         CommonResult commonResult;
         int count = umsMemberService.createUmsMember(umsMemberDto);
         if (count == 1) {
            commonResult = CommonResult.success(umsMemberDto);
            LOGGER.debug("create UmsMember success:{}", umsMemberDto);
         } else {
            commonResult = CommonResult.failed("操作失败");
            LOGGER.debug("create UmsMember failed:{}", umsMemberDto);
         }
         return commonResult;
    }

    /**
    * @Description: 通过id更新会员表
    * @Param: [id, umsMember, result]
    * @return: com.number47.mall.common.CommonResult
    * @Author: number47
    * @Date: 2020-08-07
    */
    @ApiModelProperty(value = "通过id更新会员表")
    @RequestMapping(value = "/update/{id}", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult updateUmsMember(@PathVariable("id") Long id, @Validated @RequestBody UmsMemberDto umsMemberDto, BindingResult result) {
        if(result.hasErrors()){
             return CommonResult.validateFailed(result.getFieldError().getDefaultMessage());
        }
        CommonResult commonResult;
        int count = umsMemberService.updateUmsMember(id, umsMemberDto);
        if (count == 1) {
            commonResult = CommonResult.success(umsMemberDto);
            LOGGER.debug("update umsMember success:{}", umsMemberDto);
        } else {
            commonResult = CommonResult.failed("操作失败");
            LOGGER.debug("update umsMember failed:{}", umsMemberDto);
        }
        return commonResult;
    }

    /**
    * @Description: 通过id删除会员表
    * @Param: [id]
    * @return: com.number47.mall.common.CommonResult
    * @Author: number47
    * @Date: 2020-08-07
    */
    @ApiModelProperty(value = "通过id删除会员表")
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult deleteUmsMember(@PathVariable("id") Long id) {
        int count = umsMemberService.deleteUmsMember(id);
        if (count == 1) {
            LOGGER.debug("delete UmsMember success :id={}", id);
            return CommonResult.success(null);
        } else {
            LOGGER.debug("delete UmsMember failed :id={}", id);
            return CommonResult.failed("操作失败");
        }
    }
    /**
    * @Description: 分页获取会员表列表
    * @Param: [pageNum, pageSize, umsMemberDto]
    * @return: com.number47.mall.common.CommonResult<CommonPage<UmsMember>>
    * @Author: number47
    * @Date: 2020-08-07
    */
    @ApiModelProperty(value = "分页获取会员表列表")
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult<CommonPage<UmsMember>> listUmsMember(UmsMemberDto umsMemberDto) {
        Page<UmsMember> page = new Page<>(umsMemberDto.getPageNum(), umsMemberDto.getPageSize());
        IPage<UmsMember> umsMemberPage = umsMemberService.listUmsMember(page,umsMemberDto);
        return CommonResult.success(CommonPage.restPage(umsMemberPage));
    }

    /**
    * @Description: 通过id获取会员表
    * @Param: [id]
    * @return: com.number47.mall.common.CommonResult<com.number47.mall.model.brand.entity.UmsMember>
    * @Author: number47
    * @Date: 2020-08-07
    */
    @ApiModelProperty(value = "通过id获取会员表")
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult<UmsMember> getUmsMember(@PathVariable("id") Long id) {
        return CommonResult.success(umsMemberService.getUmsMember(id));
    }

    /**
     * 获取验证码
     * @param telephone 手机
     * @return
     */
    @ApiOperation("获取验证码")
    @RequestMapping(value = "/getAuthCode", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult<String> getAuthCode(@RequestParam String telephone) {
        return umsMemberService.generateAuthCode(telephone);
    }

    /**
     * 判断验证码是否正确
     * @param telephone 手机
     * @param authCode 验证码
     * @return
     */
    @ApiOperation("判断验证码是否正确")
    @RequestMapping(value = "/verifyAuthCode", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult<String> updatePassword(@RequestParam String telephone,
                                       @RequestParam String authCode) {
        return umsMemberService.verifyAuthCode(telephone,authCode);
    }
}
