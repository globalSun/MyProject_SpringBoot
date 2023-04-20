package com.zsun.springboot;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;

/**
 * 主程序类;主配置类
 * @SpringBootApplication：这是一个SpringBoot应用
 *      1、主程序所在包及其下面的所有子包里面的 组件 都会被默认扫描进来。
 *      2、无需以前的包扫描配置。
 *      3、想要改变扫描路径，两种方式
 */

//@SpringBootApplication(scanBasePackages="com.zsun")
//等同于
@SpringBootConfiguration
@EnableAutoConfiguration
@ComponentScan("com.zsun")
public class MainApplication {

    public static void main(String[] args) {
        //1、返回我们IOC容器
        ConfigurableApplicationContext run = SpringApplication.run(MainApplication.class, args);

        //2、查看容器里面的组件
        String[] names = run.getBeanDefinitionNames();
        for (String name : names) {
            System.out.println(name);
        }
    }
}
