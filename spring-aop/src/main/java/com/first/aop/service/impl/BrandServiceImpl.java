package com.first.aop.service.impl;

import com.first.aop.entity.User;
import com.first.aop.mapper.BrandMapper;
import com.first.aop.mapper.ExhibitInfoMapper;
import com.first.aop.mapper.UserMapper;
import com.first.aop.entity.Brand;
import com.first.aop.entity.vo.ExhibitInfoVO;
import com.first.aop.service.BrandService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class BrandServiceImpl implements BrandService {

    @Resource
    private BrandMapper brandMapper;

    @Resource
    private ExhibitInfoMapper exhibitInfoMapper;

    @Resource
    private UserMapper userMapper;


    @Override
    public Brand selectBrandById(Brand brand) {
        Integer brandId = brand.getId();
        Brand result = brandMapper.selectBrandById(brandId);
        return result;
    }


    /**
     * 通过展品id集合查找展品信息
     * @param exhibitInfoVO
     * @return
     */
    @Override
    public List<ExhibitInfoVO> selectExhibitsByIds(ExhibitInfoVO exhibitInfoVO) {
        List<Integer> exhibitIds = exhibitInfoVO.getExhibitIds();
        /** 展品*/
        List<ExhibitInfoVO> exhibitInfos = exhibitInfoMapper.selectExhibitsByIds(exhibitIds);

        /** 图片*/
        List<ExhibitInfoVO> exhibitPics = exhibitInfoMapper.selectExhibitPic(exhibitIds);
        Map<Integer, List<ExhibitInfoVO>> exhibitPicMap = exhibitPics.stream().collect(Collectors.groupingBy(ExhibitInfoVO::getExhibitId));

        /** 车系*/
        List<ExhibitInfoVO> exhibitCarSeries = exhibitInfoMapper.selectExhibitCarSeries(exhibitIds);
        Map<Integer, List<ExhibitInfoVO>> exhibitCarSeriesMap = exhibitCarSeries.stream().collect(Collectors.groupingBy(ExhibitInfoVO::getExhibitId));

        /** 封装结果*/
        exhibitInfos.stream().forEach( (item) -> {
            Integer id = item.getId();
            item.setExhibitImg(exhibitPicMap.get(id).get(0).getExhibitImg());
            item.setCarSeries(exhibitCarSeriesMap.get(id).get(0).getCarSeries());
        });

        return exhibitInfos;
    }


    /**
     * 测试 User sql: but found 2 2
     * @param user
     * @return
     */
    @Override
    public User selectUserById(User user) {
        String phone = user.getPhone();
        User result = userMapper.selectUserById(phone);
        return result;
    }
}
