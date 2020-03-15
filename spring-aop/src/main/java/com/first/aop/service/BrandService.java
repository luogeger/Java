package com.first.aop.service;

import com.first.aop.entity.Brand;
import com.first.aop.entity.User;
import com.first.aop.entity.vo.ExhibitInfoVO;

import java.util.List;

public interface BrandService {

    Brand selectBrandById(Brand brand);

    /**
     * 通过展品id集合查找展品信息
     * @param exhibitInfoVO
     * @return
     */
    List<ExhibitInfoVO> selectExhibitsByIds(ExhibitInfoVO exhibitInfoVO);


    /**
     * 测试 User sql: but found 2 2
     * @param user
     * @return
     */
    User selectUserById(User user);
}
