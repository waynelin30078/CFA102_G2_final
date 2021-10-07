package mail;

import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMessage.RecipientType;

public class JavaMail {
	
	
	//基本資料
	private String userName = "ludester456@gmail.com";  //寄件者email
	private String password = "vtshmwcomocjjiad";	//寄件者密碼
	private String customer = "ludester456@gmail.com";	//收件者的email
	private String subject = "測試信件";	//寄件標題
	private String txt = "哈哈哈哈";   //內容
	//連線設定
	public void SendMail() {
		//連線設定
		Properties prop = new Properties();
		//設定連線方式為smtp
		prop.setProperty("mail.transport.protocol", "smtp");
		
		//host : smtp.gmail.com
		prop.setProperty("mail.host", "smtp.gmail.com");
		//host port 465
		prop.put("mail.smtp", "465");
		
		//寄件者帳號需要驗證 : 是
		prop.put("mail.smtp.auth", "true");
		//需要安全資料驗證傳輸層(SSL):是
		prop.put("mail.smtp.socketFactory.class","javax.net.ssl.SSLSocketFactory");
		//安全資料傳輸層(SSL) 通訊訃:456
		prop.put("mail.smtp.socketFactory.port","465");
		//帳號驗證
		
		//Session javamail api 默認設定屬性
		//帳號驗證
		//session javamail api 默認設定屬性
	
		//透過匿名類別
//		Session session=Session.getDefaultInstance(prop, new Authenticator() {
//
//			@Override
//			protected PasswordAuthentication getPasswordAuthentication() {
//				return new PasswordAuthentication(userName, password);
//			}
//			
//		});
		
		//一班Class
		Auth auth = new Auth(userName, password) ;
		Session session = Session.getDefaultInstance(prop,auth);
		
		
		// ---------------------message 放入基本資料
		MimeMessage message = new MimeMessage(session);
		//寄件者
		//匿名類別
		try {
			message.setSender(new InternetAddress(userName));
			//收件者
			message.setRecipient(RecipientType.TO, new InternetAddress(customer));
			//標題
			message.setSubject(subject);
			//內容
			message.setContent(txt, "text/html;charset-UTF-8");
			//---------------------Transport 將Message傳出去
			Transport transport = session.getTransport();
			transport.send(message);
			transport.close();
		} catch (AddressException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		}

}

class Auth extends Authenticator{
	private String userName;
	private String password;
	public Auth(String userName, String password) {
		super();
		this.userName = userName;
		this.password = password;
	}



	

	@Override
	protected PasswordAuthentication getPasswordAuthentication() {
		PasswordAuthentication pa = new PasswordAuthentication(userName, password);
		return pa;
	}
public static void main(String[] args) {
	JavaMail mail = new JavaMail();
	mail.SendMail();
	
}	
}

