package com.switchvov.springboot.demo.starter.domain;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.Resource;
import java.util.List;

@Data
@Slf4j
public class School implements ISchool {
    @Autowired
    private Klass class1;

    @Resource(name = "student100")
    private Student student100;

    @Override
    public void ding() {
        List<Student> students = class1.getStudents();
        log.info("Class1 have {}  students and one is {}", students.size(), student100);
        for (Student student : students) {
            student.init();
            log.info("    student:{}", student);
            student.print();
        }
    }
}
