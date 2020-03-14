package com.first.aop.entity.vo;

import com.first.aop.entity.ExhibitInfo;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;

@Data
@Accessors(chain = true)
public class ExhibitInfoVO extends ExhibitInfo {
    // ==================================== 参数
    /** 展品id集合*/
    private List<Integer> exhibitIds;



    // ==================================== sql查询参数
    private Integer exhibitId;

    // ==================================== 返回值
    /** 图片集合*/
    private List<String> images;
    /** 展品图片*/
    private String exhibitImg;
    /** 车系*/
    private String carSeries;

}
