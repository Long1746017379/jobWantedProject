package com.rj.bd.utils;

import java.io.UnsupportedEncodingException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMessage.RecipientType;

/**
* @desc 邮件发送的工具类
* @author 董成龙
* @Date 2019年12月12日
*/

public class EmailUtils {
	// 163邮箱
	public static String sendEmailAccount = "mr_chenglong@163.com";
	// 邮箱授权码
	public static String sendEmailPwd = "dcl001027";
	// 收件人邮箱
	//public static String receiveMailAccount = "1746017379@qq.com";
	
	public static void main(String[] args) throws UnsupportedEncodingException, MessagingException, ParseException {
		/*Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd: HH:mm:ss");
		
		String sendEmail = "2019-12-12: 20:31:00";
		Date sendDate = sdf.parse(sendEmail);
		System.out.println(sdf.format(sendDate));
		
		System.out.println(date);*/
		
		createMimeMessage("2257822366@qq.com","出去玩","明晚8点老地方，不见不散");
	}
	
	
	/**
	 * @desc 创建一封简单的邮件
	 * @param subject 主题
	 * @param content 内容
	 * @param receiveMail 收件人邮箱
	 * @throws MessagingException 
	 * @throws UnsupportedEncodingException 
	 * @throws ParseException 
	 */
	public static void createMimeMessage(String receiveMail, String subject, String content) throws UnsupportedEncodingException, MessagingException, ParseException {
		// 1、属性设置
		Properties props = getProperties();
		
		// 2、创建session会话对象
		Session session = Session.getInstance(props);
		session.setDebug(true); // 开启debug调试模式，可以打印出发送的debug 
		
		// 3、创建一封邮件 （邮件对象）
		MimeMessage message = new MimeMessage(session);
		
		// 4、设定发件人
		message.setFrom(new InternetAddress(sendEmailAccount, "Mr.Long--->along", "utf-8"));
		
		// 5、设定接收人
		message.setRecipient(RecipientType.TO, new InternetAddress(receiveMail, "收件人", "utf-8"));
		
		// 6、设定主题
		message.setSubject(subject);
		
		// 7、设定内容
		message.setContent(content, "text/html;charset=utf-8");
		
		// 8、设定发送时间（默认为立即发送）
		/*
		    String sendEmail = "2019-12-12: 20:34:00";
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd: HH:mm:ss");
			Date sendDate = sdf.parse(sendEmail);
		*/
		
		message.setSentDate(new Date());
		
		// 9、保存设置
		message.saveChanges();
		
		// 10、获得传输对象
		Transport transport = session.getTransport();
		
		// 11、连接上SMTP邮件服务器 -----参数：（发送者邮箱，邮箱授权码）
		transport.connect(sendEmailAccount, sendEmailPwd);
		
		// 12、发送邮件 -----参数：（邮件对象，）
		transport.sendMessage(message, message.getAllRecipients());
		
		// 13、关闭连接
		transport.close();
	}

	private static Properties getProperties() {
		// 1、属性设置
		Properties props = new Properties();
		props.setProperty("mail.transport.protocol","smtp"); // 使用的协议（JavaMail规范要求）
		props.setProperty("mail.smtp.host","smtp.163.com");  // 发件人的邮箱的 SMTP 服务器地址
		props.setProperty("mail.smtp.auth", "true");         // 需要请求认证
		return props;
	}

}
