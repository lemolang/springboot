package com.sunll.springboot.config;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.bind.RelaxedPropertyResolver;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.env.Environment;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.io.IOException;


/**
 * Created by sunll
 * on 2018/2/28
 */
@Configuration
@AutoConfigureAfter(DruidDataSourceConfig.class)
@MapperScan("com.sunll.springboot.mapper")
@EnableTransactionManagement
public class MybatisConfig implements EnvironmentAware {
    private static Log logger = LogFactory.getLog(MybatisConfig.class);

    private RelaxedPropertyResolver propertyResolver;

    public void setEnvironment(Environment env) {
        this.propertyResolver = new RelaxedPropertyResolver(env, "mybatis.");
    }

    @Autowired
    private DataSource dataSource;

    // 提供SqlSeesion
    @Bean(name = "sqlSessionFactory")
    @Primary
    public SqlSessionFactory sqlSessionFactory() {
        try {
            SqlSessionFactoryBean sessionFactoryBean = new SqlSessionFactoryBean();
            sessionFactoryBean.setDataSource(dataSource);

            // 读取配置
            sessionFactoryBean.setTypeAliasesPackage(propertyResolver.getProperty("typeAliasesPackage"));

            //设置mapper.xml文件所在位置
            Resource[] resources = new PathMatchingResourcePatternResolver()
                    .getResources(propertyResolver.getProperty("mapperLocations"));
            sessionFactoryBean.setMapperLocations(resources);
            //设置mybatis-config.xml配置文件位置
            sessionFactoryBean.setConfigLocation(new DefaultResourceLoader()
                    .getResource(propertyResolver.getProperty("configLocation")));

            //添加分页插件、打印sql插件
            /*Interceptor[] plugins = new Interceptor[]{pageHelper()};
            sessionFactoryBean.setPlugins(plugins);*/

            return sessionFactoryBean.getObject();
        } catch (IOException e) {
            logger.error("mybatis resolver mapper*xml is error", e);
            return null;
        } catch (Exception e) {
            logger.error("mybatis sqlSessionFactoryBean create error", e);
            return null;
        }
    }

    @Bean
    public SqlSessionTemplate sqlSessionTemplate(SqlSessionFactory sqlSessionFactory) {
        return new SqlSessionTemplate(sqlSessionFactory);
    }


}
