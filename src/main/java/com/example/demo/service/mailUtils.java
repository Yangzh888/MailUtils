package com.example.demo.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 这是一个发送邮件的工具类
 * TODO 暂未封装为接口，具体使用请到sendMailTest.java文件测试使用
 *
 */
@Service
public class mailUtils {


    /**
     * 以发送HTML邮件为例使用logger打印日志
     */
    private final Logger logger= LoggerFactory.getLogger(this.getClass());

    @Value("${spring.mail.username}")
    private String form;

    @Autowired
    private JavaMailSender javaMailSender;

    /**
     * 发送普通邮件
     * @param to 要发送给的邮箱地址
     * @param subject 发送的主题
     * @param content 发送的内容
     */
    public void sendSimpleMail(String to,String subject,String content){
        SimpleMailMessage simpleMailMessage=new SimpleMailMessage();
        simpleMailMessage.setTo(to);
        simpleMailMessage.setSubject(subject);
        simpleMailMessage.setText(content);
        simpleMailMessage.setFrom("974541192@qq.com");
        javaMailSender.send(simpleMailMessage);
    }

    /**
     * 发送HTML邮件
     * @param to 要发送给的邮箱地址
     * @param subject 发送的主题
     * @param content 发送的内容
     * @throws MessagingException
     */
    public void sendHtmlMail(String to,String subject,String content ) {
        logger.info("发送HTML邮件开始：{}，{},{}",to,subject,content);
        MimeMessage message=javaMailSender.createMimeMessage();
        MimeMessageHelper messageHelper= null;
        try {
            messageHelper = new MimeMessageHelper(message,true);
            messageHelper.setFrom(form);
            messageHelper.setTo(to);
            messageHelper.setSubject(subject);
            messageHelper.setText(content,true);//是否为HTML
            javaMailSender.send(message);
            logger.info("发送HTML邮件发送成功");
        } catch (MessagingException e) {
            logger.info("发送HTML邮件发送失败",e);
            e.printStackTrace();
        }

    }

    /**
     * 发送一封带有附件的HTML邮件
     * @param to 要发送给的邮箱地址
     * @param subject 发送的主题
     * @param content 发送的内容
     * @param filePath 文件的路径.可以填写一个或者多个路径
     * @throws MessagingException
     */
    public void sendAttachmentsMail(String to,String subject, String content,
                                     String... filePath) throws MessagingException {
        MimeMessage message=javaMailSender.createMimeMessage();
        MimeMessageHelper messageHelper=new MimeMessageHelper(message,true);
        messageHelper.setFrom(form);
        messageHelper.setTo(to);
        messageHelper.setSubject(subject);
        messageHelper.setText(content,true);//是否为HTML
        if(filePath!=null){
            List<String> resultList = new ArrayList<>(filePath.length);
            Collections.addAll(resultList,filePath);
            for (String s:resultList){
                FileSystemResource file=new FileSystemResource(new File(s));//通过文件路径创建一个流的临时地址
                String fileName=file.getFilename(); //获取文件的名字
                messageHelper.addAttachment(fileName,file); //将附件的信息添加进去
            }
        }
        javaMailSender.send(message);
    }

    public void sendInlinkResourceMail(String to,String subject, String content,
                                       String rscPath,String rscId) throws MessagingException {
        MimeMessage message=javaMailSender.createMimeMessage();
        MimeMessageHelper messageHelper=new MimeMessageHelper(message,true);
        messageHelper.setFrom(form);
        messageHelper.setTo(to);
        messageHelper.setSubject(subject);
        messageHelper.setText(content,true);//是否为HTML
        FileSystemResource res=new FileSystemResource(new File(rscPath));
        messageHelper.addInline(rscId,res);
        javaMailSender.send(message);
    }
}
