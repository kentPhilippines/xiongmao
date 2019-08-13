package com.payProject.config;


import javax.sql.DataSource;

import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Invocation;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;








//@Configuration
public class DataSourceConfiguration {
	//@Value("${Spring.myBatis.pageSqlId}")
	private String pageSqlId;
	
	//@Bean
	public SqlSessionFactoryBean sessionFactoryBean() {
		SqlSessionFactoryBean sqlSession = new SqlSessionFactoryBean();
		sqlSession.setDataSource(dataSource());
		//sqlSession.setPlugins();
		return null;
	}
	
	
	/**
	 * 数据源
	 * @return
	 */
	//@Bean
	public DataSource dataSource() {
		return null;
	}
	
	
	/**
	 * 分页
	 * @return
	 */
	//@Bean
	/*
	 * public Interceptor[] pagePlugin(){ PagePlugin myPagePlugin = myPagePlugin();
	 * return null; }
	 * 
	 * //@SuppressWarnings("static-access") //@Bean public PagePlugin myPagePlugin()
	 * { PagePlugin pagePl = new PagePlugin(); pagePl.setDialectObject(dialect());
	 * pagePl.setPageSqlId(pageSqlId); return pagePl; }
	 * 
	 * // @Bean public Dialect dialect() { Dialect dialect = new MySQLDialect();
	 * return dialect; }
	 */
	
}
