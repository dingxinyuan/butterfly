package com.ayding.common.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @Created with IntelliJ IDEA.
 * @author: dingxy
 * @date: 2020/09/10
 * @Description:
 */
@Configuration
@EnableTransactionManagement
@MapperScan("com.ayding.butterfly.*.dao.auto")
public class MybatisConfig {


}
