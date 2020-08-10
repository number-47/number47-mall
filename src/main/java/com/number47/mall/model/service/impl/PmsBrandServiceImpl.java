package com.number47.mall.model.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.number47.mall.model.entity.PmsBrand;
import com.number47.mall.dto.PmsBrandDto;
import com.number47.mall.model.dao.PmsBrandMapper;
import com.number47.mall.model.service.PmsBrandService;
import org.springframework.stereotype.Service;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * <p>
 * 品牌表 服务实现类
 * </p>
 *
 * @author number47
 * @since 2020-08-06
 */
@Service
public class PmsBrandServiceImpl extends ServiceImpl<PmsBrandMapper, PmsBrand> implements PmsBrandService {
    @Autowired
    private PmsBrandMapper pmsBrandMapper;

    @Override
    public List<PmsBrand> listAllPmsBrand(PmsBrandDto pmsBrandDto) {
        PmsBrand pmsBrand = new PmsBrand();
        BeanUtils.copyProperties(pmsBrandDto,pmsBrand);
        QueryWrapper<PmsBrand> queryWrapper = new QueryWrapper<>(pmsBrand);
        return pmsBrandMapper.selectList(queryWrapper);
    }

    @Override
    public int createPmsBrand(PmsBrandDto pmsBrandDto) {
        PmsBrand pmsBrand = new PmsBrand();
        BeanUtils.copyProperties(pmsBrandDto,pmsBrand);
        return pmsBrandMapper.insert(pmsBrand);
    }

    @Override
    public int updatePmsBrand(Long id, PmsBrandDto pmsBrandDto) {
         PmsBrand pmsBrand = new PmsBrand();
         BeanUtils.copyProperties(pmsBrandDto,pmsBrand);
         pmsBrand.setId(id);
         return pmsBrandMapper.updateById(pmsBrand);
    }

    @Override
    public int deletePmsBrand(Long id) {
         return pmsBrandMapper.deleteById(id);
    }

    @Override
    public IPage<PmsBrand> listPmsBrand(Page<PmsBrand> page, PmsBrandDto pmsBrandDto) {
        PmsBrand pmsBrand = new PmsBrand();
        BeanUtils.copyProperties(pmsBrandDto,pmsBrand);
        QueryWrapper<PmsBrand> queryWrapper = new QueryWrapper<>(pmsBrand);
        return pmsBrandMapper.selectPage(page,queryWrapper);
    }

    @Override
    public PmsBrand getPmsBrand(Long id) {
         return pmsBrandMapper.selectById(id);
    }
}
