package com.example.demo;

import com.example.demo.service.mailUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

@RunWith(SpringRunner.class)
@SpringBootTest
public class test {
    @Resource
    private mailUtils utils;

    @Test
    public void test(){
        utils.sendMail("1121095915@qq.com","第一封邮件","测试主题");
    }
}
