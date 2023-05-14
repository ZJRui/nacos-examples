package com.alibaba.nacos.example.spring.cloud.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/config")
/**
 * 一个被标记为@RefreshScope的SpringBean 示例在配置发生变更时可以重新进行初始化，即动态刷新配置。这是为了解决状态Bean示例只能在初始化的时候才能进行属性注入的问题。
 * 被@RefreshScope修斯和的Bean示例是懒加载的，即当他们被使用的时候才会进行初始化（方法被调用的时候），想要在下次方法调用前强制重新初始化一个Bean实例，只需要将他的缓存失效即可。
 * 
 * RefreshScope修饰是上下文中的一个Bean实例，他有一个公共方法refreshAll，该方法可以通过清除目标缓存来刷新作用域中的所有bean实例。RefreshScope也有一个refresh方法来按照名字刷新单个Bean
 *
 *  还需要给加载变量 的 类标注＠RefreshScop 巳，使用该注解 的类 ，在客户端执行 /refresh的时候就会更新此类的变量值，。
 * 也就是说： 要像实现刷新 本地配置属性的值，你的先在本地的客户端执行/refresh ,那么本地客户端怎么才能执行/refresh 呢？参考下文的 /actuator/refresh，也就是需要依赖 acturator的端点。
 * 
 * 那么问题：能不能配置更新的时候自动推送到客户端呢？
 */
@RefreshScope
public class ConfigController {

    @Value("${useLocalCache:false}")
    private boolean useLocalCache;

    /**
     * http://localhost:8080/config/get
     */
    @RequestMapping("/get")
    public boolean get() {
        return useLocalCache;
    }
}