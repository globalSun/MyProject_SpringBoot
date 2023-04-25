package com.zsun.springboot;


import com.zsun.springboot.bean.Car;
import com.zsun.springboot.bean.Pet;
import com.zsun.springboot.bean.User;
import com.zsun.springboot.config.MyConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
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
@ComponentScan("com.zsun.springboot")//应用程序扫描
public class MainApplication {

    public static void main(String[] args) {
        //1、返回我们IOC容器
        ConfigurableApplicationContext ioc = SpringApplication.run(MainApplication.class, args);

        //2、查看容器里面的所有组件
        String[] names = ioc.getBeanDefinitionNames();
        for (String name : names) {
            System.out.println(name);
        }

//        //3、从容器获取组件（bean）：测试proxyBeanMethods属性
//        User userOneByConfiguration1 = ioc.getBean(User.class);//默认单例，proxyBeanMethods = true
//        User userOneByConfiguration2 = ioc.getBean(User.class);//默认单例，proxyBeanMethods = true
//        System.out.println("是否是单例："+(userOneByConfiguration1==userOneByConfiguration2));//true
//
//        //4、代理配置对象：IOC容器里有配置类bean，是被框架强化后的对象。
//        //com.zsun.springboot.config.MyConfig$$EnhancerBySpringCGLIB$$9931d239@57562473
//        MyConfig myConfig = ioc.getBean(MyConfig.class);
//        System.out.println("IOC容器里存在配置类："+myConfig);
//
//        //@Configuration(proxyBeanMethods = true)
//        //如果@Configuration(proxyBeanMethods = true)代理对象调用方法。SpringBoot总会检查这个组件是否在容器中有，保持组件单实例。
//        //如果@Configuration(proxyBeanMethods = false)代理对象调用方法,SpringBoot会新建对象，非单例。
//        Pet petOneByConfiguration1 = myConfig.getPetOneByConfiguration();
//        Pet petOneByConfiguration2 = myConfig.getPetOneByConfiguration();
//        System.out.println("配置类对象调用方法，返回的两个对象是否相等："+(petOneByConfiguration2==petOneByConfiguration1));
//
//        //组件依赖：
//        System.out.println("是否组件依赖："+(userOneByConfiguration1.getPet()==petOneByConfiguration1));

        //5、从容器获取User类型组件（bean）：测试import
        String[] users = ioc.getBeanNamesForType(User.class);
        for(String user:users){
            System.out.println(user);
        }

        //6、条件装配：@Conditional
        boolean petOneByConfiguration = ioc.containsBean("petOneByConfiguration");
        System.out.println("容器中有petOneByConfiguration组件："+petOneByConfiguration);

        boolean userOneByConfiguration = ioc.containsBean("userOneByConfiguration");
        System.out.println("容器中有userOneByConfiguration组件："+userOneByConfiguration);

        //7、@ImportResource导入原生配置文件spring-beans.xml
        boolean userOneByXml = ioc.containsBean("userOneByXml");
        System.out.println("容器中有userOneByXml组件："+userOneByXml);

        boolean petOneByXml = ioc.containsBean("petOneByXml");
        System.out.println("容器中有petOneByXml组件："+petOneByXml);

        //8、配置绑定
        Car myCar = ioc.getBean(Car.class);
        System.out.println("容器中有myCar组件："+myCar);

    }
}
