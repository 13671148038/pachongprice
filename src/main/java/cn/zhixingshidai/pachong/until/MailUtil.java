package cn.zhixingshidai.pachong.until;

import java.util.Date;
import java.util.Properties;
import java.util.regex.Pattern;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class MailUtil {
	public static void sendMail(String toMail,  
            String mailTitle,String validate_url) throws Exception {  
		Properties props = new Properties(); //可以加载一个配置文件  
		String regex = "^([a-zA-Z0-9_\\.\\-])+(@|@vip.)qq.com$";
		// 使用smtp：简单邮件传输协议  
		props.put("mail.smtp.host", "smtp.zhixingshidai.com");//存储发送邮件服务器的信息
		props.put("mail.smtp.auth", "true");//同时通过验证
		String fromMail = "sendmail@zhixingshidai.com";
		String emailUser = "sendmail@zhixingshidai.com";
		String emailPwd = "123456zx";

		//email.host=smtp.zhixingshidai.com
		//email.fromMail=sendmail@zhixingshidai.com
		//email.user=sendmail@zhixingshidai.com
		//email.pwd=123456zx

		Session session = Session.getInstance(props);//根据属性新建一个邮件会话  
		//session.setDebug(true); //有他会打印一些调试信息。  
		MimeMessage message = new MimeMessage(session);//由邮件会话新建一个消息对象  
		message.setFrom(new InternetAddress(fromMail));//设置发件人的地址  
		message.setRecipient(Message.RecipientType.TO, new InternetAddress(toMail));//设置收件人,并设置其接收类型为TO  
		message.setSubject(mailTitle);//设置标题  
	
		message.setContent(validate_url, "text/html;charset=gbk"); //发送HTML邮件，内容样式比较丰富  
		message.setSentDate(new Date());//设置发信时间  
		message.saveChanges();//存储邮件信息  
		//发送邮件  
		Transport transport = session.getTransport("smtp");  
		//Transport transport = session.getTransport();  
		transport.connect(emailUser, emailPwd);  
		transport.sendMessage(message, message.getAllRecipients());//发送邮件,其中第二个参数是所有已设好的收件人地址  
		transport.close();  
	}
	
	public static void main(String[] args) throws Exception {  
//		String regex = "^([a-zA-Z0-9_\\.\\-])+(@|@vip.)qq.com$";
//		System.out.println(Pattern.matches(regex, "123de22@vip.qq.com"));
        sendMail("zhushaopeng@zhixingshidai.com",
                "智行时代-邮箱激活",  
                "测试按时吃阿萨德惨");
    }  
}