package com.number47.mall.dto;

import java.time.LocalDateTime;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import com.number47.mall.common.CommonConstant;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 后台用户表
 * </p>
 *
 * @author number47
 * @since 2020-08-07
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="UmsAdminDto对象", description="后台用户表")
public class UmsAdminDto {

    @ApiModelProperty(value = "用户名", required = true)
//    @NotEmpty(message = "用户名不能为空")
    private String username;
    @ApiModelProperty(value = "密码", required = true)
//    @NotEmpty(message = "密码不能为空")
    private String password;

    @ApiModelProperty(value = "头像")
    private String icon;

    @ApiModelProperty(value = "邮箱")
    private String email;

    @ApiModelProperty(value = "昵称")
    private String nickName;

    @ApiModelProperty(value = "备注信息")
    private String note;

    @ApiModelProperty(value = "创建时间")
    private LocalDateTime createTime;

    @ApiModelProperty(value = "最后登录时间")
    private LocalDateTime loginTime;

    @ApiModelProperty(value = "帐号启用状态：0->禁用；1->启用")
    private Integer status;


    @ApiModelProperty(value = "页码")
    private int pageNum = CommonConstant.PAGE_NUM;
    @ApiModelProperty(value = "每页数量")
    private int pageSize = CommonConstant.PAGE_SIZE;
}
