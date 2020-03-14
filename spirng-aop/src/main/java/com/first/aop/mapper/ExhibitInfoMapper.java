package com.first.aop.mapper;

import com.first.aop.entity.ExhibitInfo;
import com.first.aop.entity.vo.ExhibitInfoVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ExhibitInfoMapper {

    int insert(@Param("entity") ExhibitInfo pojo);


    /**
     * 通过展品id集合查找展品信息
     * @param exhibitIds
     * @return
     */
    List<ExhibitInfoVO> selectExhibitsByIds(@Param("exhibitIds") List<Integer> exhibitIds);

    /** 展品图片*/
    List<ExhibitInfoVO> selectExhibitPic(@Param("exhibitIds") List<Integer> exhibitIds);

    /** 车系*/
    List<ExhibitInfoVO> selectExhibitCarSeries(@Param("exhibitIds") List<Integer> exhibitIds);
}
