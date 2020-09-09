package com.yjh.pooTest;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.junit4.SpringRunner;

import javax.sql.DataSource;
@RunWith(SpringRunner.class)
@SpringBootTest
public class DataSourceTest {
    @Autowired
    ApplicationContext applicationContext;

    @Test
    public void poolTest(){
        DataSource dataSource = applicationContext.getBean(DataSource.class);
        System.out.println("数据库连接池：" + dataSource.getClass().getSimpleName());
    }
}
