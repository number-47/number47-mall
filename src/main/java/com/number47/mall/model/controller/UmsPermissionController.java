package com.number47.mall.model.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.number47.mall.common.CommonPage;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import com.number47.mall.model.entity.UmsPermission;
import com.number47.mall.dto.UmsPermissionDto;
import com.number47.mall.common.CommonResult;
import org.springframework.validation.BindingResult;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.RestController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.number47.mall.model.service.UmsPermissionService;
import org.springframework.beans.factory.annotation.Autowired;


import java.util.List;
/**
 * <p>
 * 后台用户权限表 前端控制器
 * </p>
 *
 * @author number47
 * @since 2020-08-07
 */
@RestController
@Api(tags = "UmsPermissionController", description = "后台用户权限表有关接口")
@RequestMapping("/permission")
public class UmsPermissionController {
    @Autowired
    private UmsPermissionService  umsPermissionService;

    private static final Logger LOGGER = LoggerFactory.getLogger(UmsPermissionController.class);
    /**
    * @Description: 获取后台用户权限表全部列表
    * @Param: [umsPermissionDto]
    * @return: com.number47.mall.common.CommonResult<java.util.List<com.number47.mall.model.brand.entity.UmsPermission>>
    * @Author: number47
    * @Date: 2020-08-07
    */
    @ApiModelProperty(value = "获取后台用户权限表全部列表")
    @RequestMapping(value = "listAll", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult<List<UmsPermission>> getUmsPermissionList(UmsPermissionDto umsPermissionDto) {
        return CommonResult.success(umsPermissionService.listAllUmsPermission(umsPermissionDto));
    }

    /**
    * @Description: 添加后台用户权限表
    * @Param: [umsPermission]
    * @return: com.number47.mall.common.CommonResult
    * @Author: number47
    * @Date: 2020-08-07
    */
    @ApiModelProperty(value = "添加后台用户权限表")
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult createUmsPermission(@Validated @RequestBody UmsPermissionDto umsPermissionDto,BindingResult result) {
         if (result.hasErrors()) {
            return CommonResult.validateFailed(result.getFieldError().getDefaultMessage());
         }
         CommonResult commonResult;
         int count = umsPermissionService.createUmsPermission(umsPermissionDto);
         if (count == 1) {
            commonResult = CommonResult.success(umsPermissionDto);
            LOGGER.debug("create UmsPermission success:{}", umsPermissionDto);
         } else {
            commonResult = CommonResult.failed("操作失败");
            LOGGER.debug("create UmsPermission failed:{}", umsPermissionDto);
         }
         return commonResult;
    }

    /**
    * @Description: 通过id更新后台用户权限表
    * @Param: [id, umsPermission, result]
    * @return: com.number47.mall.common.CommonResult
    * @Author: number47
    * @Date: 2020-08-07
    */
    @ApiModelProperty(value = "通过id更新后台用户权限表")
    @RequestMapping(value = "/update/{id}", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult updateUmsPermission(@PathVariable("id") Long id, @Validated @RequestBody UmsPermissionDto umsPermissionDto, BindingResult result) {
        if(result.hasErrors()){
             return CommonResult.validateFailed(result.getFieldError().getDefaultMessage());
        }
        CommonResult commonResult;
        int count = umsPermissionService.updateUmsPermission(id, umsPermissionDto);
        if (count == 1) {
            commonResult = CommonResult.success(umsPermissionDto);
            LOGGER.debug("update umsPermission success:{}", umsPermissionDto);
        } else {
            commonResult = CommonResult.failed("操作失败");
            LOGGER.debug("update umsPermission failed:{}", umsPermissionDto);
        }
        return commonResult;
    }

    /**
    * @Description: 通过id删除后台用户权限表
    * @Param: [id]
    * @return: com.number47.mall.common.CommonResult
    * @Author: number47
    * @Date: 2020-08-07
    */
    @ApiModelProperty(value = "通过id删除后台用户权限表")
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult deleteUmsPermission(@PathVariable("id") Long id) {
        int count = umsPermissionService.deleteUmsPermission(id);
        if (count == 1) {
            LOGGER.debug("delete UmsPermission success :id={}", id);
            return CommonResult.success(null);
        } else {
            LOGGER.debug("delete UmsPermission failed :id={}", id);
            return CommonResult.failed("操作失败");
        }
    }
    /**
    * @Description: 分页获取后台用户权限表列表
    * @Param: [pageNum, pageSize, umsPermissionDto]
    * @return: com.number47.mall.common.CommonResult<CommonPage<UmsPermission>>
    * @Author: number47
    * @Date: 2020-08-07
    */
    @ApiModelProperty(value = "分页获取后台用户权限表列表")
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult<CommonPage<UmsPermission>> listUmsPermission(UmsPermissionDto umsPermissionDto) {
        Page<UmsPermission> page = new Page<>(umsPermissionDto.getPageNum(), umsPermissionDto.getPageSize());
        IPage<UmsPermission> umsPermissionPage = umsPermissionService.listUmsPermission(page,umsPermissionDto);
        return CommonResult.success(CommonPage.restPage(umsPermissionPage));
    }

    /**
    * @Description: 通过id获取后台用户权限表
    * @Param: [id]
    * @return: com.number47.mall.common.CommonResult<com.number47.mall.model.brand.entity.UmsPermission>
    * @Author: number47
    * @Date: 2020-08-07
    */
    @ApiModelProperty(value = "通过id获取后台用户权限表")
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult<UmsPermission> getUmsPermission(@PathVariable("id") Long id) {
        return CommonResult.success(umsPermissionService.getUmsPermission(id));
    }
}
