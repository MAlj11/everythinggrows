package cn.everythinggrows.service;

import org.springframework.stereotype.Service;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;
import java.util.Random;


@Service
public class emailVerifyService {
    public String getMailVerifyAndSend(String toMail) throws MessagingException {
        Properties props = new Properties();
        props.setProperty("mail.smtp.auth", "true");
        props.setProperty("mail.transport.protocol", "smtp");
        props.put("mail.smtp.host","smtp.163.com");
        // smtp服务器地址

        Session session = Session.getInstance(props);
        session.setDebug(true);

        Message msg = new MimeMessage(session);
        String str="AaBbCcDdEeFfGgHhIiJjKkLlMmNnOoPpQqRrSsTtUuVvWwXxYyZz0123456789";
        StringBuilder sb=new StringBuilder(6);
        for(int i=0;i<6;i++)
        {
            char ch=str.charAt(new Random().nextInt(str.length()));
            sb.append(ch);
        }
        String verify = sb.toString();
        msg.setSubject("<万物生长>注册验证");
        msg.setText("欢迎注册<万物生长>，这是你的验证码：" + verify + "，输入时请区分大小写，有效时间为5分钟，请妥善保存。");
        msg.setFrom(new InternetAddress("everythinggrows@163.com"));
        //发件人邮箱
        msg.setRecipient(Message.RecipientType.TO,
                new InternetAddress(toMail));
        //收件人邮箱
        msg.saveChanges();

        Transport transport = session.getTransport();
        transport.connect("everythinggrows@163.com","egofficialmu123");
        //发件人邮箱,授权码

        transport.sendMessage(msg, msg.getAllRecipients());

        System.out.println("邮件发送成功");
        transport.close();
        return verify;
    }
}
