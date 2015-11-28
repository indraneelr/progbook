package com.progbook;

import com.progbook.persistence.model.Answer;
import com.progbook.persistence.model.Category;
import com.progbook.persistence.model.Language;
import com.progbook.persistence.model.Question;
import com.progbook.persistence.repository.GenericRepository;
import org.h2.server.web.WebServlet;
import org.hibernate.engine.jdbc.connections.internal.DatasourceConnectionProviderImpl;
import org.hibernate.internal.SessionFactoryImpl;
import org.hibernate.jpa.internal.EntityManagerImpl;
import org.springframework.beans.BeansException;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.embedded.ServletRegistrationBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.support.JpaEntityInformation;
import org.springframework.data.jpa.repository.support.JpaMetamodelEntityInformation;

import javax.persistence.EntityManager;

@SpringBootApplication
public class Application implements ApplicationContextAware{
    private ApplicationContext ctx;

    public static void main(String[] args){
        ConfigurableApplicationContext applicationContext = SpringApplication.run(Application.class, args);
    }
    @Bean
    ServletRegistrationBean h2servletRegistration(){
        ServletRegistrationBean registrationBean = new ServletRegistrationBean( new WebServlet());
        registrationBean.addUrlMappings("/console/*");
        return registrationBean;
    }

    @Bean
      public GenericRepository<Question,Long> getQuestionRepository(){
        EntityManager entityManager = ctx.getBean(EntityManager.class);
        JpaEntityInformation<Question,Long> jpaEntityInformation= new JpaMetamodelEntityInformation<>(Question.class,entityManager.getMetamodel());
        GenericRepository<Question,Long> genericRepository = new GenericRepository<>(jpaEntityInformation,entityManager);
        return genericRepository;
    }

    @Bean
    public GenericRepository<Category,Long> getCategoryRepository(){
        EntityManager entityManager = ctx.getBean(EntityManager.class);
        JpaEntityInformation<Category,Long> jpaEntityInformation= new JpaMetamodelEntityInformation<>(Category.class,entityManager.getMetamodel());
        GenericRepository<Category,Long> genericRepository = new GenericRepository<>(jpaEntityInformation,entityManager);
        return genericRepository;
    }

    @Bean
    public GenericRepository<Language,Long> getLanguageRepository(){
        EntityManager entityManager = ctx.getBean(EntityManager.class);
        JpaEntityInformation<Language,Long> jpaEntityInformation= new JpaMetamodelEntityInformation<>(Language.class,entityManager.getMetamodel());
        GenericRepository<Language,Long> genericRepository = new GenericRepository<>(jpaEntityInformation,entityManager);
        return genericRepository;
    }

    @Bean
    public GenericRepository<Answer,Long> getAnswerRepository(){
        EntityManager entityManager = ctx.getBean(EntityManager.class);
        JpaEntityInformation<Answer,Long> jpaEntityInformation= new JpaMetamodelEntityInformation<>(Answer.class,entityManager.getMetamodel());
        GenericRepository<Answer,Long> genericRepository = new GenericRepository<>(jpaEntityInformation,entityManager);
        return genericRepository;
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.ctx = applicationContext;
    }
}
