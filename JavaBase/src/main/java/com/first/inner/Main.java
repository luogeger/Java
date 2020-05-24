package com.first.inner;

import lombok.Data;
import lombok.ToString;
import lombok.experimental.Accessors;
import org.junit.Test;

/**
 * @author luoxiaoqing
 * @date 2020-05-19__08:04
 * @desc
 */
@Data
@ToString(exclude = "")
public class Main {


    @Test
    public void main1 () {
        System.out.println(ModelDTO.Create.class);


        System.out.println(VarDTO.Update.class);

    }
}
