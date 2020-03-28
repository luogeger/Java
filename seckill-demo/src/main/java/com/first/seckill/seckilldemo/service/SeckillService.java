package com.first.seckill.seckilldemo.service;

import com.first.seckill.seckilldemo.entity.Seckill;

/**
 * @author luoxiaoqing
 */
public interface SeckillService {

    int deleteById(Integer id);

    int insert(Seckill seckill);

    int insertSelective(Seckill seckill);

    Seckill selectById(Integer id);

    int updateBySelective(Seckill seckill);

}
