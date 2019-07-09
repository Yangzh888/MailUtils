package com.example.demo;

import com.example.demo.service.mailUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import javax.mail.MessagingException;

@RunWith(SpringRunner.class)
@SpringBootTest
public class sendMailTest {
    @Resource
    private mailUtils utils;

    /**
     * 测试发送简单邮件
     */
    @Test
    public void sendSimpleMailTset(){
        String to="**@qq.com";//修改为要发送的邮箱地址
        String subject="第一封简单邮件";//修改为要发送的邮件主题
        String content="测试主题";//修改为要发送的邮件内容
        utils.sendSimpleMail(to,subject,content);
    }

    /**
     * 测试发送HTML邮件
     * @throws MessagingException
     */
    @Test
    public void sendHtmlMailTest() throws MessagingException {
        String to="**@qq.com";//修改为要发送的邮箱地址
        String subject="测试发送一封HTML的邮件";//修改为要发送的邮件信息
        String content="<html>\n"+
                "<body>\n"+
                "<h3>hello world,这是一封HTML邮件！"+"</h3>"+
               "</body>\n"+
                "</html>";     //设置邮件内容为HTML格式
        utils.sendHtmlMail(to,subject,content);
    }
}
