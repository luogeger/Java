package com.first.tools;

import org.springframework.beans.BeanUtils;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * @author luoxiaoqing
 * @date 2020-05-14__15:34
 * @desc
 */
public class Main {


    /**
     * 对象拷贝
     */
    @Test
    public void main1 () {
        UserDO userDO = new UserDO(1L, "Van", 18, 1);
        UserVO userVO = new UserVO();
        BeanUtils.copyProperties(userDO, userVO);
        System.out.println(userVO);
    }

    /**
     * 列表拷贝
     */
    @Test
    public void main2 () {

        List<UserDO> userDOList = new ArrayList();
        userDOList.add(new UserDO(1L, "Van", 18, 1));
        userDOList.add(new UserDO(2L, "VanVan", 18, 2));
        List<UserVO> userVOList = new ArrayList();
        BeanUtils.copyProperties(userDOList, userVOList);
        System.out.println(userVOList);
    }

    /**
     * 列表拷贝
     */
    @Test
    public void main3 () {
        List<UserDO> userDOList = new ArrayList();
        userDOList.add(new UserDO(1L, "Van", 18, 1));
        userDOList.add(new UserDO(2L, "VanVan", 20, 2));
        List<UserVO> userVOList = new ArrayList();
        userDOList.forEach(userDO ->{
            UserVO userVO = new UserVO();
            BeanUtils.copyProperties(userDO, userVO);
            userVOList.add(userVO);
        });
        System.out.println(userVOList);
    }


    /**
     * 自定义工具类拷贝列表
     */
    @Test
    public void main4 () {
        List<UserDO> userDOList = new ArrayList();
        userDOList.add(new UserDO(1L, "Van", 18, 1));
        userDOList.add(new UserDO(2L, "VanVan", 20, 2));
        List<UserVO> userVOList = BeanUtilsPlus.copyListProperties(userDOList, UserVO::new);
        System.out.println(userVOList);
    }







}
