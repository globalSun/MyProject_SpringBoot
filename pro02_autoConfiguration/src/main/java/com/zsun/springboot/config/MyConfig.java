package com.zsun.springboot.config;

import com.zsun.springboot.bean.Car;
import com.zsun.springboot.bean.Pet;
import com.zsun.springboot.bean.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.ImportResource;

/**
 * ClassName:MyConfig
 * Package:com.zsun.springboot.config
 * Description:
 *      1、@Configuration注解：标记一个类，告诉SpringBoot这是一个配置类 == 配置文件。
 *          1、配置文件能做什么，这个配置类就能做什么。万事万物皆对象。
 *          2、配置类本身也是组件。可在容器获取。
 *          3、proxyBeanMethods：代理bean的方法.
 *              Full(proxyBeanMethods = true) ：保证每个@Bean方法被调用多少次返回的组件都是单实例的。
 *                      每次都会检查IOC容器，保证单例，速度慢。
 *              Lite(proxyBeanMethods = false)：每个@Bean方法被调用多少次返回的组件都是新创建的。
 *                      跳过了检查IOC容器步骤，非单例，速度快。
 *              组件依赖必须使用Full模式默认。其他默认是否Lite模式
 *
 *      2、@Bean注解：给IOC容器中添加组件（bean对象）。
 *              标记一个方法。
 *              1、对象信息：以方法名作为组件的id。可在注解value属性里自定义组件名
 *                        返回类型就是组件类型。
 *                        返回的值，就是组件在容器中的实例.
 *              2、配置类里面使用@Bean标注在方法上给容器注册组件，默认也是单实例的。
 *      @Bean、@Component、@Controller、@Service、@Repository都能为IOC容器添加组件。
 *              主程序所在包及其下面的所有子包里面的 组件 都会被默认扫描进来。
 *
 *      3、@ComponentScan、@Import
 *          @ComponentScan注解：指明需要导入的组件位置，取代Spring配置文件的扫描功能。
 *              如@ComponentScan("com.zsun.springboot")。
 *          @Import：写在容器组件类上，导入指定组件，并使用空参构造器给容器自动创建这个类型的组件。
 *
 *      4、@ImportResource:导入xml配置文件的方式为IOC容器添加组件。
 *          位置：任一配置类上。
 *
 *
 * @author zy
 * @create 2023-04-2110:40
 * @Version 1.0
 */
//@Import({User.class})//给IOC配置组件，写在任一类上。
@Configuration(proxyBeanMethods = true)//告诉SpringBoot这是一个配置类 == 配置文件
@ImportResource("classpath:spring-beans.xml")//导入原生配置文件，为IOC容器配置。
@EnableConfigurationProperties(Car.class)//开启类的属性配置绑定功能、自动赋值、注册到组件中
//1、开启Car配置绑定功能
//2、把这个Car这个组件自动注册到容器中
public class MyConfig {

    @Autowired
    Car car;

    @Bean("petOneByConfiguration")
    public Pet getPetOneByConfiguration(){
        return new Pet("dog");
    }

    //给容器中添加组件。默认以方法名作为组件的id，可设置。返回类型就是组件类型。返回的值，就是组件在容器中的实例.
    @ConditionalOnBean(name = "petOneByConfiguration")
    @Bean("userOneByConfiguration")
    public User getUserOneByConfiguration(){
        User lisi = new User("李四", 28);
        lisi.setPet(getPetOneByConfiguration());//proxyBeanMethods = true模式，单例，组件依赖。
        return lisi;
    }

}
