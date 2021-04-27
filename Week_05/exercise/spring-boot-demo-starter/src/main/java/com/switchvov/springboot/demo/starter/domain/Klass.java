package com.switchvov.springboot.demo.starter.domain;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Data
@Slf4j
public class Klass {
    @Autowired
    List<Student> students;

    public void dong() {
        log.info("students:{}", getStudents());
    }
}
