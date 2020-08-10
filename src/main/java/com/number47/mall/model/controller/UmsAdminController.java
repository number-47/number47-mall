package com.number47.mall.model.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.number47.mall.common.CommonPage;
import com.number47.mall.dto.UmsAdminLoginParam;
import com.number47.mall.model.entity.UmsAdmin;
import com.number47.mall.dto.UmsAdminDto;
import com.number47.mall.model.entity.UmsPermission;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import com.number47.mall.common.CommonResult;
import org.springframework.validation.BindingResult;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.RestController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.number47.mall.model.service.UmsAdminService;
import org.springframework.beans.factory.annotation.Autowired;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 后台用户表 前端控制器
 * </p>
 *
 * @author number47
 * @since 2020-08-07
 */
@RestController
@Api(tags = "UmsAdminController", description = "后台用户表有关接口")
@RequestMapping("/admin")
public class UmsAdminController {
    @Autowired
    private UmsAdminService umsAdminService;
    @Value("${jwt.tokenHeader}")
    private String tokenHeader;
    @Value("${jwt.tokenHead}")
    private String tokenHead;

    private static final Logger LOGGER = LoggerFactory.getLogger(UmsAdminController.class);
    /**
    * @Description: 获取后台用户表全部列表
    * @Param: [umsAdminDto]
    * @return: com.number47.mall.common.CommonResult<java.util.List<com.number47.mall.model.brand.entity.UmsAdmin>>
    * @Author: number47
    * @Date: 2020-08-07
    */
    @ApiModelProperty(value = "获取后台用户表全部列表")
    @RequestMapping(value = "listAll", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult<List<UmsAdmin>> getUmsAdminList(UmsAdminDto umsAdminDto) {
        return CommonResult.success(umsAdminService.listAllUmsAdmin(umsAdminDto));
    }

    /**
    * @Description: 添加后台用户表
    * @Param: [umsAdmin]
    * @return: com.number47.mall.common.CommonResult
    * @Author: number47
    * @Date: 2020-08-07
    */
    @ApiModelProperty(value = "添加后台用户表")
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult<Object> createUmsAdmin(@Validated @RequestBody UmsAdminDto umsAdminDto,BindingResult result) {
         if (result.hasErrors()) {
            return CommonResult.validateFailed(result.getFieldError().getDefaultMessage());
         }
        CommonResult<Object> commonResult;
         int count = umsAdminService.createUmsAdmin(umsAdminDto);
         if (count == 1) {
            commonResult = CommonResult.success(umsAdminDto);
            LOGGER.debug("create UmsAdmin success:{}", umsAdminDto);
         } else {
            commonResult = CommonResult.failed("操作失败");
            LOGGER.debug("create UmsAdmin failed:{}", umsAdminDto);
         }
         return commonResult;
    }

    /**
    * @Description: 通过id更新后台用户表
    * @Param: [id, umsAdmin, result]
    * @return: com.number47.mall.common.CommonResult
    * @Author: number47
    * @Date: 2020-08-07
    */
    @ApiModelProperty(value = "通过id更新后台用户表")
    @RequestMapping(value = "/update/{id}", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult<Object> updateUmsAdmin(@PathVariable("id") Long id, @Validated @RequestBody UmsAdminDto umsAdminDto, BindingResult result) {
        if(result.hasErrors()){
             return CommonResult.validateFailed(result.getFieldError().getDefaultMessage());
        }
        CommonResult<Object> commonResult;
        int count = umsAdminService.updateUmsAdmin(id, umsAdminDto);
        if (count == 1) {
            commonResult = CommonResult.success(umsAdminDto);
            LOGGER.debug("update umsAdmin success:{}", umsAdminDto);
        } else {
            commonResult = CommonResult.failed("操作失败");
            LOGGER.debug("update umsAdmin failed:{}", umsAdminDto);
        }
        return commonResult;
    }

    /**
    * @Description: 通过id删除后台用户表
    * @Param: [id]
    * @return: com.number47.mall.common.CommonResult
    * @Author: number47
    * @Date: 2020-08-07
    */
    @ApiModelProperty(value = "通过id删除后台用户表")
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult<Object> deleteUmsAdmin(@PathVariable("id") Long id) {
        int count = umsAdminService.deleteUmsAdmin(id);
        if (count == 1) {
            LOGGER.debug("delete UmsAdmin success :id={}", id);
            return CommonResult.success(null);
        } else {
            LOGGER.debug("delete UmsAdmin failed :id={}", id);
            return CommonResult.failed("操作失败");
        }
    }
    /**
    * @Description: 分页获取后台用户表列表
    * @Param: [pageNum, pageSize, umsAdminDto]
    * @return: com.number47.mall.common.CommonResult<CommonPage<UmsAdmin>>
    * @Author: number47
    * @Date: 2020-08-07
    */
    @ApiModelProperty(value = "分页获取后台用户表列表")
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult<CommonPage<UmsAdmin>> listUmsAdmin(UmsAdminDto umsAdminDto) {
        Page<UmsAdmin> page = new Page<>(umsAdminDto.getPageNum(), umsAdminDto.getPageSize());
        IPage<UmsAdmin> umsAdminPage = umsAdminService.listUmsAdmin(page,umsAdminDto);
        return CommonResult.success(CommonPage.restPage(umsAdminPage));
    }

    /**
    * @Description: 通过id获取后台用户表
    * @Param: [id]
    * @return: com.number47.mall.common.CommonResult<com.number47.mall.model.brand.entity.UmsAdmin>
    * @Author: number47
    * @Date: 2020-08-07
    */
    @ApiModelProperty(value = "通过id获取后台用户表")
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult<UmsAdmin> getUmsAdmin(@PathVariable("id") Long id) {
        return CommonResult.success(umsAdminService.getUmsAdmin(id));
    }

    @ApiModelProperty(value = "用户注册")
    @RequestMapping(value = "/register", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult<UmsAdmin> register(@Validated @RequestBody UmsAdmin umsAdminParam, BindingResult result) {
        if(result.hasErrors()){
            return CommonResult.validateFailed(result.getFieldError().getDefaultMessage());
        }
        UmsAdmin umsAdmin = umsAdminService.register(umsAdminParam);
        if (umsAdmin == null) {
            CommonResult.failed();
        }
        return CommonResult.success(umsAdmin);
    }

    @ApiModelProperty(value = "登录以后返回token")
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult<Map<String,String>> login(@RequestBody UmsAdminLoginParam umsAdminLoginParam, BindingResult result) {
        String token = umsAdminService.login(umsAdminLoginParam.getUsername(), umsAdminLoginParam.getPassword());
        if (token == null) {
            return CommonResult.validateFailed("用户名或密码错误");
        }
        Map<String, String> tokenMap = new HashMap<>(2);
        tokenMap.put("token", token);
        tokenMap.put("tokenHead", tokenHead);
        return CommonResult.success(tokenMap);
    }

    @ApiModelProperty("获取用户所有权限（包括+-权限）")
    @RequestMapping(value = "/permission/{adminId}", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult<List<UmsPermission>> getPermissionList(@PathVariable Long adminId) {
        List<UmsPermission> permissionList = umsAdminService.getPermissionList(adminId);
        return CommonResult.success(permissionList);
    }
}
