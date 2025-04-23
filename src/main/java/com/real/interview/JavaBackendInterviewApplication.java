package com.real.interview;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication(scanBasePackages = {"com.real.interview.*"})
//@ComponentScan("com.real.interview.repo")
public class JavaBackendInterviewApplication {

    public static void main(String[] args) {
        SpringApplication.run(JavaBackendInterviewApplication.class, args);
    }

}
