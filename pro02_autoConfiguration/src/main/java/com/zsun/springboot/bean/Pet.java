package com.zsun.springboot.bean;

import lombok.*;

/**
 * ClassName:Pet
 * Package:com.zsun.springboot.bean
 * Description:
 *
 * @author zy
 * @create 2023-04-2110:30
 * @Version 1.0
 */
@Data//属性的get、set方法
@ToString//toString方法
@NoArgsConstructor//无参构造器
@AllArgsConstructor//全参构造器
@EqualsAndHashCode
public class Pet {
    private String name;
}
