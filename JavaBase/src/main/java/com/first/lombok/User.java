package com.first.lombok;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Objects;

/**
 * 两个对象在用equals比较的时候，如果它们还要比较父类中的某些属性，就要重写equals和hashCode方法，
 * 或者加@EqualsAndHashCode(callSuper = true)
 *
 * @author luoxiaoqing
 * @date 2020-05-20__07:56
 * @desc lombok test
 */
//@EqualsAndHashCode(callSuper = true)
@Data
public class User extends UserParent{
    private String  id;

    private String  name;

    private Integer age;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User)) return false;
        if (!super.equals(o)) return false;
        User user = (User) o;
        return Objects.equals(getId(), user.getId()) &&
                Objects.equals(getName(), user.getName()) &&
                Objects.equals(getAge(), user.getAge());
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), getId(), getName(), getAge());
    }
}
