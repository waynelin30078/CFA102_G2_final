package mail;

import java.util.Properties;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class MailService {
	
	// 設定傳送郵件:至收信人的Email信箱,Email主旨,Email內容
	public void sendMail(String to, String subject, String messageText,String url) {
			
	   try {
		   // 設定使用SSL連線至 Gmail smtp Server
		   Properties props = new Properties();
		   props.put("mail.smtp.host", "smtp.gmail.com");
		   props.put("mail.smtp.socketFactory.port", "465");
		   props.put("mail.smtp.socketFactory.class","javax.net.ssl.SSLSocketFactory");
		   props.put("mail.smtp.auth", "true");
		   props.put("mail.smtp.port", "465");

       // ●設定 gmail 的帳號 & 密碼 (將藉由你的Gmail來傳送Email)
       // ●須將myGmail的【安全性較低的應用程式存取權】打開
	     final String myGmail = "ludester456@gmail.com";
	     final String myGmail_password = "vtshmwcomocjjiad";
		   Session session = Session.getInstance(props, new Authenticator() {
			   protected PasswordAuthentication getPasswordAuthentication() {
				   return new PasswordAuthentication(myGmail, myGmail_password);
			   }
		   });

		   Message message = new MimeMessage(session);
		   message.setFrom(new InternetAddress(myGmail));
		   message.setRecipients(Message.RecipientType.TO,InternetAddress.parse(to));
		  
		   //設定信中的主旨  
		   message.setSubject(subject);
		   //設定信中的內容 
		   message.setText(messageText);
		   //設定信中的超連結
		   message.setContent("請按下方超連結以完成E-mail驗證<br/><a href=\""+url+"\">E-mail密碼重設驗證</a>", "text/html; charset=UTF-8");
//		   a href='<%=request.getContextPath()%>/front_end/free/member/resetpassword.jap?action=resetpassword&member_mno=mno'
		   Transport.send(message);
		   System.out.println("傳送成功!");
     }catch (MessagingException e){
	     System.out.println("傳送失敗!");
	     e.printStackTrace();
     }
   }
	
//	 public static void main (String args[]){
//
//      String to = "ludester456@gmail.com";
//      
//      String subject = "密碼通知";
//      
//      String ch_name = "peter1";
//      String passRandom = "111";
//      String messageText = "Hello! " + ch_name + " 請謹記此密碼: " + passRandom + "\n" +" (已經啟用)"; 
//       
//      MailService mailService = new MailService();
//      mailService.sendMail(to, subject, messageText);
//
//   }


}
