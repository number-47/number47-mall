package com.number47.mall.model.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.number47.mall.common.CommonPage;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import com.number47.mall.model.entity.PmsBrand;
import com.number47.mall.dto.PmsBrandDto;
import com.number47.mall.common.CommonResult;
import org.springframework.validation.BindingResult;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.RestController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.number47.mall.model.service.PmsBrandService;
import org.springframework.beans.factory.annotation.Autowired;


import java.util.List;
/**
 * <p>
 * 品牌表 前端控制器
 * </p>
 *
 * @author number47
 * @since 2020-08-06
 */
@RestController
@Api(tags = "PmsBrandController", description = "品牌表有关接口")
@RequestMapping("/brand")
public class PmsBrandController {
    @Autowired
    private PmsBrandService  pmsBrandService;

    private static final Logger LOGGER = LoggerFactory.getLogger(PmsBrandController.class);
    /**
    * @Description: 获取品牌表全部列表
    * @Param: [pmsBrandDto]
    * @return: com.number47.mall.common.CommonResult<java.util.List<com.number47.mall.model.entity.PmsBrand>>
    * @Author: number47
    * @Date: 2020-08-06
    */
    @PreAuthorize("hasAuthority('pms:brand:read')")
    @ApiModelProperty(value = "获取品牌表全部列表")
    @RequestMapping(value = "listAll", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult<List<PmsBrand>> getPmsBrandList(PmsBrandDto pmsBrandDto) {
        return CommonResult.success(pmsBrandService.listAllPmsBrand(pmsBrandDto));
    }

    /**
    * @Description: 添加品牌表
    * @Param: [pmsBrand]
    * @return: com.number47.mall.common.CommonResult
    * @Author: number47
    * @Date: 2020-08-06
    */
    @PreAuthorize("hasAuthority('pms:brand:create')")
    @ApiModelProperty(value = "添加品牌表")
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult createPmsBrand(@Validated @RequestBody PmsBrandDto pmsBrandDto,BindingResult result) {
         if (result.hasErrors()) {
            return CommonResult.validateFailed(result.getFieldError().getDefaultMessage());
         }
         CommonResult commonResult;
         int count = pmsBrandService.createPmsBrand(pmsBrandDto);
         if (count == 1) {
            commonResult = CommonResult.success(pmsBrandDto);
            LOGGER.debug("create PmsBrand success:{}", pmsBrandDto);
         } else {
            commonResult = CommonResult.failed("操作失败");
            LOGGER.debug("create PmsBrand failed:{}", pmsBrandDto);
         }
         return commonResult;
    }

    /**
    * @Description: 通过id更新品牌表
    * @Param: [id, pmsBrand, result]
    * @return: com.number47.mall.common.CommonResult
    * @Author: number47
    * @Date: 2020-08-06
    */
    @PreAuthorize("hasAuthority('pms:brand:update')")
    @ApiModelProperty(value = "通过id更新品牌表")
    @RequestMapping(value = "/update/{id}", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult updatePmsBrand(@PathVariable("id") Long id, @Validated @RequestBody PmsBrandDto pmsBrandDto, BindingResult result) {
        if(result.hasErrors()){
             return CommonResult.validateFailed(result.getFieldError().getDefaultMessage());
        }
        CommonResult commonResult;
        int count = pmsBrandService.updatePmsBrand(id, pmsBrandDto);
        if (count == 1) {
            commonResult = CommonResult.success(pmsBrandDto);
            LOGGER.debug("update pmsBrand success:{}", pmsBrandDto);
        } else {
            commonResult = CommonResult.failed("操作失败");
            LOGGER.debug("update pmsBrand failed:{}", pmsBrandDto);
        }
        return commonResult;
    }

    /**
    * @Description: 通过id删除品牌表
    * @Param: [id]
    * @return: com.number47.mall.common.CommonResult
    * @Author: number47
    * @Date: 2020-08-06
    */
    @PreAuthorize("hasAuthority('pms:brand:delete')")
    @ApiModelProperty(value = "通过id删除品牌表")
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult deletePmsBrand(@PathVariable("id") Long id) {
        int count = pmsBrandService.deletePmsBrand(id);
        if (count == 1) {
            LOGGER.debug("delete PmsBrand success :id={}", id);
            return CommonResult.success(null);
        } else {
            LOGGER.debug("delete PmsBrand failed :id={}", id);
            return CommonResult.failed("操作失败");
        }
    }
    /**
    * @Description: 分页获取品牌表列表
    * @Param: [pageNum, pageSize, pmsBrandDto]
    * @return: com.number47.mall.common.CommonResult<CommonPage<PmsBrand>>
    * @Author: number47
    * @Date: 2020-08-06
    */
    @PreAuthorize("hasAuthority('pms:brand:read')")
    @ApiModelProperty(value = "分页获取品牌表列表")
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult<CommonPage<PmsBrand>> listPmsBrand(PmsBrandDto pmsBrandDto) {
        Page<PmsBrand> page = new Page<>(pmsBrandDto.getPageNum(), pmsBrandDto.getPageSize());
        IPage<PmsBrand> pmsBrandPage = pmsBrandService.listPmsBrand(page,pmsBrandDto);
        return CommonResult.success(CommonPage.restPage(pmsBrandPage));
    }

    /**
    * @Description: 通过id获取品牌表
    * @Param: [id]
    * @return: com.number47.mall.common.CommonResult<com.number47.mall.model.entity.PmsBrand>
    * @Author: number47
    * @Date: 2020-08-06
    */
    @PreAuthorize("hasAuthority('pms:brand:read')")
    @ApiModelProperty(value = "通过id获取品牌表")
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult<PmsBrand> getPmsBrand(@PathVariable("id") Long id) {
        return CommonResult.success(pmsBrandService.getPmsBrand(id));
    }
}
