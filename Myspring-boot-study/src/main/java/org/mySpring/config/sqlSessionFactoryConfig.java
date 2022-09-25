package org.mySpring.config;

import com.alibaba.druid.pool.DruidDataSource;
import org.apache.ibatis.session.ExecutorType;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import javax.naming.Context;
import javax.naming.InitialContext;
import java.io.InputStream;
import java.util.Properties;

@Configuration
public class sqlSessionFactoryConfig {

	//配置FactoryBean
	@Bean(name = "sqlSessionFactoryBean")
	public SqlSessionFactoryBean sqlSessionFactoryBean() {
		SqlSessionFactoryBean sqlSessionFactoryBean = null;
		try {
			// 加载JNDI配置
			Context context = new InitialContext();
			// 实例SessionFactory
			sqlSessionFactoryBean = new SqlSessionFactoryBean();
			// 配置数据源
			InputStream is = SqlSessionFactoryBean.class.getClassLoader().getResourceAsStream("application.properties");
			Properties pros = new Properties();
			pros.load(is);
			String user = pros.getProperty("spring.datasource.name");
			String password = pros.getProperty("spring.datasource.password");
			String url = pros.getProperty("spring.datasource.url");
			String driverClass = pros.getProperty("spring.datasource.driver-class-name");

			DruidDataSource dataSource = new DruidDataSource();
			dataSource.setDriverClassName(driverClass);
			dataSource.setUrl(url);
			dataSource.setUsername(user);
			dataSource.setPassword(password);
			sqlSessionFactoryBean.setDataSource(dataSource);
			// 加载MyBatis配置文件
			PathMatchingResourcePatternResolver resourcePatternResolver = new PathMatchingResourcePatternResolver();
			// 能加载多个，所以可以配置通配符(如：classpath*:mapper/**/*.xml)
			String mapperLocations = pros.getProperty("mybatis.mapper-locations");
			sqlSessionFactoryBean.setMapperLocations(resourcePatternResolver.getResources(mapperLocations));
		} catch (Exception e) {
			System.out.println("创建SqlSession连接工厂错误：{}");
		}
		return sqlSessionFactoryBean;
	}
	@Bean
	public SqlSessionTemplate sqlSessionTemplate() throws Exception {
		SqlSessionTemplate sqlSessionTemplate=new SqlSessionTemplate(sqlSessionFactoryBean().getObject(), ExecutorType.BATCH);
		return sqlSessionTemplate;
	}
}
