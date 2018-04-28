package com.sdo.configuration.datasource;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;


@Configuration
@MapperScan(basePackages = "com.sdo.mapper.global", sqlSessionTemplateRef  = "globalSqlSessionTemplate")
public class Global {
//	private final static Logger logger = LoggerFactory.getLogger(Global.class);
	
	
	@Bean(name = "globalDataSource")
	@Primary
	@ConfigurationProperties("spring.datasource.global")
    public DataSource dataSource() {
//		return DataSourceBuilder.create().type(HikariDataSource.class).build();
        return DataSourceBuilder.create().build();
    }

    @Bean(name = "globalSqlSessionFactory")
    public SqlSessionFactory sqlSessionFactory(@Qualifier("globalDataSource") DataSource dataSource) throws Exception {
        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
        bean.setDataSource(dataSource);
        return bean.getObject();
    }

    @Bean(name = "globalTransactionManager")
    public DataSourceTransactionManager transactionManager(@Qualifier("globalDataSource") DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }

    @Bean(name = "globalSqlSessionTemplate")
    public SqlSessionTemplate sqlSessionTemplate(@Qualifier("globalSqlSessionFactory") SqlSessionFactory sqlSessionFactory) throws Exception {
        return new SqlSessionTemplate(sqlSessionFactory);
    }
}
