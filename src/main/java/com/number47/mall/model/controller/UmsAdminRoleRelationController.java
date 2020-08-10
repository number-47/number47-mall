package com.number47.mall.model.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.number47.mall.common.CommonPage;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import com.number47.mall.model.entity.UmsAdminRoleRelation;
import com.number47.mall.dto.UmsAdminRoleRelationDto;
import com.number47.mall.common.CommonResult;
import org.springframework.validation.BindingResult;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.RestController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.number47.mall.model.service.UmsAdminRoleRelationService;
import org.springframework.beans.factory.annotation.Autowired;


import java.util.List;
/**
 * <p>
 * 后台用户和角色关系表 前端控制器
 * </p>
 *
 * @author number47
 * @since 2020-08-07
 */
@RestController
@Api(tags = "UmsAdminRoleRelationController", description = "后台用户和角色关系表有关接口")
@RequestMapping("/admin-role-relation")
public class UmsAdminRoleRelationController {
    @Autowired
    private UmsAdminRoleRelationService  umsAdminRoleRelationService;

    private static final Logger LOGGER = LoggerFactory.getLogger(UmsAdminRoleRelationController.class);
    /**
    * @Description: 获取后台用户和角色关系表全部列表
    * @Param: [umsAdminRoleRelationDto]
    * @return: com.number47.mall.common.CommonResult<java.util.List<com.number47.mall.model.brand.entity.UmsAdminRoleRelation>>
    * @Author: number47
    * @Date: 2020-08-07
    */
    @ApiModelProperty(value = "获取后台用户和角色关系表全部列表")
    @RequestMapping(value = "listAll", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult<List<UmsAdminRoleRelation>> getUmsAdminRoleRelationList(UmsAdminRoleRelationDto umsAdminRoleRelationDto) {
        return CommonResult.success(umsAdminRoleRelationService.listAllUmsAdminRoleRelation(umsAdminRoleRelationDto));
    }

    /**
    * @Description: 添加后台用户和角色关系表
    * @Param: [umsAdminRoleRelation]
    * @return: com.number47.mall.common.CommonResult
    * @Author: number47
    * @Date: 2020-08-07
    */
    @ApiModelProperty(value = "添加后台用户和角色关系表")
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult createUmsAdminRoleRelation(@Validated @RequestBody UmsAdminRoleRelationDto umsAdminRoleRelationDto,BindingResult result) {
         if (result.hasErrors()) {
            return CommonResult.validateFailed(result.getFieldError().getDefaultMessage());
         }
         CommonResult commonResult;
         int count = umsAdminRoleRelationService.createUmsAdminRoleRelation(umsAdminRoleRelationDto);
         if (count == 1) {
            commonResult = CommonResult.success(umsAdminRoleRelationDto);
            LOGGER.debug("create UmsAdminRoleRelation success:{}", umsAdminRoleRelationDto);
         } else {
            commonResult = CommonResult.failed("操作失败");
            LOGGER.debug("create UmsAdminRoleRelation failed:{}", umsAdminRoleRelationDto);
         }
         return commonResult;
    }

    /**
    * @Description: 通过id更新后台用户和角色关系表
    * @Param: [id, umsAdminRoleRelation, result]
    * @return: com.number47.mall.common.CommonResult
    * @Author: number47
    * @Date: 2020-08-07
    */
    @ApiModelProperty(value = "通过id更新后台用户和角色关系表")
    @RequestMapping(value = "/update/{id}", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult updateUmsAdminRoleRelation(@PathVariable("id") Long id, @Validated @RequestBody UmsAdminRoleRelationDto umsAdminRoleRelationDto, BindingResult result) {
        if(result.hasErrors()){
             return CommonResult.validateFailed(result.getFieldError().getDefaultMessage());
        }
        CommonResult commonResult;
        int count = umsAdminRoleRelationService.updateUmsAdminRoleRelation(id, umsAdminRoleRelationDto);
        if (count == 1) {
            commonResult = CommonResult.success(umsAdminRoleRelationDto);
            LOGGER.debug("update umsAdminRoleRelation success:{}", umsAdminRoleRelationDto);
        } else {
            commonResult = CommonResult.failed("操作失败");
            LOGGER.debug("update umsAdminRoleRelation failed:{}", umsAdminRoleRelationDto);
        }
        return commonResult;
    }

    /**
    * @Description: 通过id删除后台用户和角色关系表
    * @Param: [id]
    * @return: com.number47.mall.common.CommonResult
    * @Author: number47
    * @Date: 2020-08-07
    */
    @ApiModelProperty(value = "通过id删除后台用户和角色关系表")
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult deleteUmsAdminRoleRelation(@PathVariable("id") Long id) {
        int count = umsAdminRoleRelationService.deleteUmsAdminRoleRelation(id);
        if (count == 1) {
            LOGGER.debug("delete UmsAdminRoleRelation success :id={}", id);
            return CommonResult.success(null);
        } else {
            LOGGER.debug("delete UmsAdminRoleRelation failed :id={}", id);
            return CommonResult.failed("操作失败");
        }
    }
    /**
    * @Description: 分页获取后台用户和角色关系表列表
    * @Param: [pageNum, pageSize, umsAdminRoleRelationDto]
    * @return: com.number47.mall.common.CommonResult<CommonPage<UmsAdminRoleRelation>>
    * @Author: number47
    * @Date: 2020-08-07
    */
    @ApiModelProperty(value = "分页获取后台用户和角色关系表列表")
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult<CommonPage<UmsAdminRoleRelation>> listUmsAdminRoleRelation(UmsAdminRoleRelationDto umsAdminRoleRelationDto) {
        Page<UmsAdminRoleRelation> page = new Page<>(umsAdminRoleRelationDto.getPageNum(), umsAdminRoleRelationDto.getPageSize());
        IPage<UmsAdminRoleRelation> umsAdminRoleRelationPage = umsAdminRoleRelationService.listUmsAdminRoleRelation(page,umsAdminRoleRelationDto);
        return CommonResult.success(CommonPage.restPage(umsAdminRoleRelationPage));
    }

    /**
    * @Description: 通过id获取后台用户和角色关系表
    * @Param: [id]
    * @return: com.number47.mall.common.CommonResult<com.number47.mall.model.brand.entity.UmsAdminRoleRelation>
    * @Author: number47
    * @Date: 2020-08-07
    */
    @ApiModelProperty(value = "通过id获取后台用户和角色关系表")
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult<UmsAdminRoleRelation> getUmsAdminRoleRelation(@PathVariable("id") Long id) {
        return CommonResult.success(umsAdminRoleRelationService.getUmsAdminRoleRelation(id));
    }
}
