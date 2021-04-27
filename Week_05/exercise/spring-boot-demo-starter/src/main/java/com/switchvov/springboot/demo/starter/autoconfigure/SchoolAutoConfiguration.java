package com.switchvov.springboot.demo.starter.autoconfigure;

import com.switchvov.springboot.demo.starter.domain.ISchool;
import com.switchvov.springboot.demo.starter.domain.Klass;
import com.switchvov.springboot.demo.starter.domain.School;
import com.switchvov.springboot.demo.starter.domain.Student;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author switch
 * @since 2021/4/27
 */
@Configuration
public class SchoolAutoConfiguration {
    @Bean
    public ISchool school() {
        return new School();
    }

    @Bean
    public Klass klass() {
        return new Klass();
    }

    @Bean("student100")
    public Student student100() {
        return new Student(1, "switch");
    }

    @Bean
    public Student student() {
        return new Student(2, "s");
    }
}
