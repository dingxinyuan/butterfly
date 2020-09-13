package com.ayding.system.init;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

/**
 * @Created with IntelliJ IDEA.
 * @author: dingxy
 * @date: 2020/09/13
 * @Description:
 */
@Component
@Slf4j
public class MyListenery implements ApplicationListener{

    @Override
    public void onApplicationEvent(ApplicationEvent applicationEvent) {
        //        ApplicationStartingEvent//启动开始的时候执行的事件
//        ApplicationEnvironmentPreparedEvent//上下文创建之前运行的事件
//        ApplicationContextInitializedEvent//
//        ApplicationPreparedEvent//上下文创建完成，注入的bean还没加载完成
//        ContextRefreshedEvent//上下文刷新
//        ServletWebServerInitializedEvent//web服务器初始化
//        ApplicationStartedEvent//
//        ApplicationReadyEvent//启动成功
//        ApplicationFailedEvent//在启动Spring发生异常时触发
        switch (applicationEvent.getClass().getSimpleName()){
            case "ApplicationStartingEvent":
                log.info("<=================启动开始的时候执行的事件ApplicationStartingEvent================>");
                break;
            case "ApplicationEnvironmentPreparedEvent":
                log.info("<=================上下文创建之前运行的事件ApplicationEnvironmentPreparedEvent================>");
                break;
            case "ApplicationContextInitializedEvent":
                log.info("<=================上下文初始化ApplicationContextInitializedEvent================>");
                break;
            case "ApplicationPreparedEvent":
                log.info("<=================上下文创建完成，注入的bean还没加载完成ApplicationPreparedEvent================>");
                break;
            case "ContextRefreshedEvent":
                log.info("<=================上下文刷新ContextRefreshedEvent================>");
                if( applicationEvent instanceof ContextRefreshedEvent){
                    Object stu = ((ContextRefreshedEvent) applicationEvent).getApplicationContext().getBean("jacksonObjectMapper");
                    log.info("<=================Spring容器中获取注册Bean{}================>",stu);
                }
                break;
            case "ApplicationStartedEvent":
                log.info("<=================ApplicationStartedEvent================>");
                break;
            case "ApplicationReadyEvent":
                log.info("<=================启动成功ApplicationReadyEvent================>");
                break;
            case "ApplicationFailedEvent":
                log.info("<=================启动失败ApplicationFailedEvent================>");
                break;
        }

    }
}
