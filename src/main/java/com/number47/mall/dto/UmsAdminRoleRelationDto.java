package com.number47.mall.dto;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import com.number47.mall.common.CommonConstant;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 后台用户和角色关系表
 * </p>
 *
 * @author number47
 * @since 2020-08-07
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="UmsAdminRoleRelationDto对象", description="后台用户和角色关系表")
public class UmsAdminRoleRelationDto {




    private Long adminId;

    private Long roleId;


    @ApiModelProperty(value = "页码")
    private int pageNum = CommonConstant.PAGE_NUM;
    @ApiModelProperty(value = "每页数量")
    private int pageSize = CommonConstant.PAGE_SIZE;
}
