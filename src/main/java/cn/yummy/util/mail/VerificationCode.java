package cn.yummy.util.mail;

import cn.yummy.dao.yummyDao.YummyMailDataService;
import cn.yummy.dao.yummyDao.YummyMailDataServiceImpl;
import cn.yummy.entity.yummy.SystemMail;
import cn.yummy.mapper.SystemMailMapper;
import com.sun.mail.util.MailSSLSocketFactory;
import org.springframework.beans.factory.annotation.Autowired;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.security.GeneralSecurityException;
import java.util.Properties;

public class VerificationCode {

//    private YummyMailDataService yummyMailDataService = new YummyMailDataServiceImpl();


    private SystemMailMapper systemMailMapper;

    public VerificationCode(SystemMailMapper systemMailMapper) {
        this.systemMailMapper = systemMailMapper;
    }

    public void sendVerificationCode(String mailAddress, String verificationCode) throws GeneralSecurityException {
        // 收件人电子邮箱
        String to = mailAddress;

        // 发件人电子邮箱
        String from = "1151138974@qq.com";

        // 指定发送邮件的主机为 smtp.qq.com
        String host = "smtp.qq.com";  //QQ 邮件服务器

        // 获取系统属性
        Properties properties = System.getProperties();

        // 设置邮件服务器
        properties.setProperty("mail.smtp.host", host);

        properties.put("mail.smtp.auth", "true");
        MailSSLSocketFactory sf = new MailSSLSocketFactory();
        sf.setTrustAllHosts(true);
        properties.put("mail.smtp.ssl.enable", "true");
        properties.put("mail.smtp.ssl.socketFactory", sf);
        // 获取默认session对象
//        String pw = yummyMailDataService.getYummyMailPassword();

        SystemMail systemMail = systemMailMapper.getSystemMail().get(0);
        String account = systemMail.getAccount();
        String pw = systemMail.getPassword();


        Session session = Session.getDefaultInstance(properties,new Authenticator(){
            public PasswordAuthentication getPasswordAuthentication()
            {
                return new PasswordAuthentication(account, pw); //发件人邮件用户名、密码
            }
        });

        try{
            // 创建默认的 MimeMessage 对象
            MimeMessage message = new MimeMessage(session);

            // Set From: 头部头字段
            message.setFrom(new InternetAddress(from));

            // Set To: 头部头字段
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));

            // Set Subject: 头部头字段
            message.setSubject("Yummy 验证码");

            // 设置消息体
            message.setText("您的验证码是: "+verificationCode);

            // 发送消息
            Transport.send(message);
            System.out.println("成功发送验证码");
        }catch (MessagingException mex) {
            mex.printStackTrace();
        }
    }

}
