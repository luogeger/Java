package com.first.aop.mapper;

import com.first.aop.entity.Brand;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface BrandMapper {

    Brand selectBrandById(@Param("id") Integer brandId);
}
