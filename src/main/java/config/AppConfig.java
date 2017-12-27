package config;

import org.hibernate.SessionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate4.HibernateTransactionManager;
import org.springframework.orm.hibernate4.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import service.*;

import java.util.Properties;

@Configuration
@EnableTransactionManagement
@EnableWebMvc
@ComponentScan({"controller", "config", "service"})
@Import(SecurityConfig.class)
public class AppConfig {
    @Bean
    public QuestionService questionService() {
        return new QuestionService();
    }

    @Bean
    public AnswerService answerService() {
        return new AnswerService();
    }

    @Bean
    public QuestionAnswerService questionAnswerService() {
        return new QuestionAnswerService();
    }

    @Bean
    public ResultAnswerService resultAnswerService() {
        return new ResultAnswerService();
    }

    @Bean
    public ResultService resultService() {
        return new ResultService();
    }

    @Bean
    public UserInfoResultService userInfoResultService() {
        return new UserInfoResultService();
    }

    @Bean
    public UserInfoService userInfoService() {
        return new UserInfoService();
    }

    @Bean
    public LocalSessionFactoryBean sessionFactory(){
        LocalSessionFactoryBean sessionFactoryBean = new LocalSessionFactoryBean();

        Properties properties = new Properties();

        //For Postgresql
        properties.put("hibernate.dialect", "org.hibernate.dialect.PostgreSQLDialect");
        //For mysql
//        properties.put("hibernate.dialect", "org.hibernate.dialect.MySQLDialect");

        properties.put("hibernate.show_sql", true);
        properties.put("hibernate.hbm2ddl.auto", "create-drop");

        sessionFactoryBean.setPackagesToScan("model");

        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("org.postgresql.Driver");
        dataSource.setUrl("jdbc:postgres://bjzfoftjtedvur:783e9612518fad42ec290a2bd242f7ed416a0353ba79d48df421428252c58ab6@ec2-54-163-233-103.compute-1.amazonaws.com:5432/dcn1l6d5r9sgie
");
        dataSource.setUsername("bjzfoftjtedvur");
        dataSource.setPassword("783e9612518fad42ec290a2bd242f7ed416a0353ba79d48df421428252c58ab6");

        sessionFactoryBean.setDataSource(dataSource);
        sessionFactoryBean.setHibernateProperties(properties);

        return sessionFactoryBean;
    }

    @Bean
    public HibernateTransactionManager transactionManager(SessionFactory sessionFactory){
        HibernateTransactionManager tx = new HibernateTransactionManager(sessionFactory);

        return tx;
    }
}


