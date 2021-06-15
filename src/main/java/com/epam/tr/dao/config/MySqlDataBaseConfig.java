package com.epam.tr.dao.config;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.hibernate.SessionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;

import javax.sql.DataSource;
import java.util.Properties;

@Configuration
public class MySqlDataBaseConfig {

    private static final String URL = "jdbc:mysql://localhost:3306/EPAM?useUnicode=true&serverTimezone=UTC";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "as122592as";
    public static final String DIALECT = "org.hibernate.dialect.MySQLDialect";
    public static final String SHOW_SQL = "true";
    public static final String CONTEXT_CLASS = "org.springframework.orm.hibernate5.SpringSessionContext";

    @Bean(name = "dataSource")
    public DataSource getDataSource() {
        HikariConfig config = new HikariConfig();
        config.setDriverClassName("com.mysql.jdbc.Driver");
        config.setJdbcUrl(URL);
        config.setUsername(USERNAME);
        config.setPassword(PASSWORD);
        config.setMaximumPoolSize(100);
        return new HikariDataSource(config);
    }

    @Bean(name = "sessionFactory")
    public SessionFactory getSessionFactory(DataSource dataSource) throws Exception {
        Properties properties = new Properties();
        properties.put("hibernate.dialect", DIALECT);
        properties.put("hibernate.show_sql", SHOW_SQL);
        properties.put("current_session_context_class", CONTEXT_CLASS);
        LocalSessionFactoryBean factoryBean = new LocalSessionFactoryBean();
        factoryBean.setPackagesToScan(new String[]{"com.epam.tr"});
        factoryBean.setDataSource(dataSource);
        factoryBean.setHibernateProperties(properties);
        factoryBean.afterPropertiesSet();
        SessionFactory sf = factoryBean.getObject();
        return sf;
    }

    @Bean(name = "transactionManager")
    public HibernateTransactionManager getTransactionManager(SessionFactory sessionFactory) {
        HibernateTransactionManager transactionManager = new HibernateTransactionManager(sessionFactory);
        return transactionManager;
    }
}