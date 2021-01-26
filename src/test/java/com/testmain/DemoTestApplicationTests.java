package com.testmain;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;


import java.util.List;
import java.util.Map;


@SpringBootTest
class DemoTestApplicationTests {

    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Test
    void contextLoads() {
        List<Map<String, Object>> result=jdbcTemplate.queryForList("select * from student");
        System.out.print(result);
    }

}
