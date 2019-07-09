package com.example.demo;

import com.example.demo.service.mailUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import javax.annotation.Resource;
import javax.mail.MessagingException;


@RunWith(SpringRunner.class)
@SpringBootTest
public class sendMailTest {
    @Resource
    private mailUtils utils;

    @Resource
    TemplateEngine templateEngine;      //模板引擎，来自于thymeleaf
    /**
     * 测试发送简单邮件
     */
    @Test
    public void sendSimpleMailTset(){
        String to="";//修改为要发送的邮箱地址
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
        String to="";//修改为要发送的邮箱地址
        String subject="测试发送一封HTML的邮件";//修改为要发送的邮件信息
        String content="<html>\n"+
                "<body>\n"+
                "<h3>hello world,这是一封HTML邮件！"+"</h3>"+
               "</body>\n"+
                "</html>";     //设置邮件内容为HTML格式
        utils.sendHtmlMail(to,subject,content);
    }

    /**
     * 测试发送一封带有附件的HTML邮件
     * 若不带path参数即不发送附件
     * @throws MessagingException
     */
    @Test
    public void sendAttachementHtmlMailTest() throws MessagingException {
        String path="com.example.demo.sendMailTest";//相对路径或者绝对路径
        String to="";//修改为要发送的邮箱地址
        String subject="测试发送一封HTML的邮件";//修改为要发送的邮件信息
        String content="<html>\n"+
                "<body>\n"+
                "<h3>hello world,这是一封HTML邮件！"+"</h3>"+
                "</body>\n"+
                "</html>";     //设置邮件内容为HTML格式
        utils.sendAttachmentsMail(to,subject,content,path);
    }
    @Test
    public void sendInlinkResourceMailTest() throws MessagingException {
        String imgPath="D:/test.jpg";  //图片的地址如"D:/gitProject/MailUtils/src/test/java/com/example/demo/sendMailTest.java"绝对路径
        String to="1121095915@qq.com";//修改为要发送的邮箱地址
        String subject="测试发送一封带有图片的HTML的邮件";//修改为要发送的邮件信息
        String rscId="neo001";//给图片定义一个ID
        /**
         * //修改html样式此处修改
         * //需要发送多个相同图片修改HTML方式即可。
         * //多个不同图片修改发送方法为类似附件那种即可
         */
        String content="<HTML><body>这是有图片的邮件：<img src=\'cid:"+rscId
                         +"\'></img></body></html>";

        utils.sendInlinkResourceMail(to,subject,content,imgPath,rscId);
  }

  public void testTemplateMailTest() throws MessagingException {
      String to="1121095915@qq.com";//修改为要发送的邮箱地址
      String subject="测试发送一封HTML的邮件";//修改为要发送的邮件信息
        Context context=new Context();
        //页面需要传输的参数
        context.setVariable("id","006");
        //process第一个参数为页面的名字，第二个则为配置好的参数
        String emailContent=templateEngine.process("emailTemplate",context);
        //通过发送普通html邮件的方法把html页面传入
        utils.sendHtmlMail(to,subject,emailContent);
  }
}
