package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

@Service
public class mailUtils {

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
     * @param to
     * @param subject
     * @param content
     * @throws MessagingException
     */
    public void sendHtmlMail(String to,String subject,String content ) throws MessagingException {
        MimeMessage message=javaMailSender.createMimeMessage();
        MimeMessageHelper messageHelper=new MimeMessageHelper(message,true);
        messageHelper.setFrom(form);
        messageHelper.setTo(to);
        messageHelper.setSubject(subject);
        messageHelper.setText(content,true);//是否为HTML
        javaMailSender.send(message);
    }
}
