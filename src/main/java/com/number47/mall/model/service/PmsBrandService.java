package com.number47.mall.model.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.number47.mall.model.entity.PmsBrand;
import com.number47.mall.dto.PmsBrandDto;

import java.util.List;
/**
 * <p>
 * 品牌表 服务类
 * </p>
 *
 * @author number47
 * @since 2020-08-06
 */
public interface PmsBrandService extends IService<PmsBrand> {
   List<PmsBrand> listAllPmsBrand(PmsBrandDto pmsBrandDto);

   int createPmsBrand(PmsBrandDto pmsBrandDto);

   int updatePmsBrand(Long id, PmsBrandDto pmsBrandDto);

   int deletePmsBrand(Long id);

    IPage<PmsBrand> listPmsBrand(Page<PmsBrand> page, PmsBrandDto pmsBrandDto);

    PmsBrand getPmsBrand(Long id);
}
