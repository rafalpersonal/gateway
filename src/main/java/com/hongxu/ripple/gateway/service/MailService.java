package com.hongxu.ripple.gateway.service;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Properties;

import javax.annotation.Resource;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import org.springframework.stereotype.Service;

import com.hongxu.ripple.gateway.config.Settings;
import com.hongxu.ripplermb.domain.core.po.DepositRecord;
import com.hongxu.ripplermb.domain.core.po.SysTxt;
import com.hongxu.ripplermb.domain.core.po.User;
import com.hongxu.ripplermb.domain.core.support.PaymentStatus;

@Service
public class MailService {

	public static final String gatewayMailBox = "czh0526@gmail.com";
	public static final String gatewayMailBoxPassword = "CZH19780526new5";
	public static final String activateUser = "感谢您注册我们的用户";
	public static final String recoveryPassword = "重置密码";
	public static final String instructPayment = "付款说明";
	public static final String instructWithdraw = "提现说明";
	public static final String orgName = "SiFu Inc.";
	
	@Resource
	private TemplateService templateService;
	@Resource
	private ConfigService configService;
	@Resource
	private UserService userService;
	
	public boolean verifyUser(
			String userCode,
			String targetMailBox) 
	{	
		String rootUrl = configService.getSettingValue(Settings.ROOT_URL);
		try{
			MimeBodyPart htmlBodypart = new MimeBodyPart(); 
			SysTxt sysMail = configService.getSysText("user_activate_mail");
			htmlBodypart.setContent(
					sysMail.getTxtContent() + 
					"<a href='" + rootUrl + "/user/register_verify/" + userCode + ".html' target='_blank'>" +
					rootUrl + "/user/register_verify/" + userCode + ".html</a>",
					"text/html; charset=UTF-8"); 
	        
	        MimeMultipart htmlMultipart = new MimeMultipart("alternative"); 
			htmlMultipart.addBodyPart(htmlBodypart); 
	        
	        MimeBodyPart htmlPart = new MimeBodyPart();
			htmlPart.setContent(htmlMultipart);
	        
			MimeMultipart mp = new MimeMultipart("related");
			mp.addBodyPart(htmlPart);
			
			
			Session s = getMailSession();
			MimeMessage message = new MimeMessage(s);
			message.setSubject(sysMail.getTxtTitle());
			message.setFrom(new InternetAddress(gatewayMailBox, orgName));
			message.setRecipient(Message.RecipientType.TO, new InternetAddress(targetMailBox));
			message.setContent(mp);
			Transport.send(message);
		
			return true;
					
		}catch(Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	public boolean resetPassword(
			String verifiedCode,
			String targetMailBox) 
	{
		String rootUrl = templateService.getSettingValue(Settings.ROOT_URL);
		try{
			MimeBodyPart htmlBodypart = new MimeBodyPart(); 
	        htmlBodypart.setContent(
					"<a href='" + rootUrl +"/user/password_reset.html?verifiedCode=" + verifiedCode + "&email=" + targetMailBox + "' target='_blank'>重置密码</a>",
					"text/html; charset=GB2312"); 
	        
	        MimeMultipart htmlMultipart = new MimeMultipart("alternative"); 
			htmlMultipart.addBodyPart(htmlBodypart); 
	        
	        MimeBodyPart htmlPart = new MimeBodyPart();
			htmlPart.setContent(htmlMultipart);
	        
			MimeMultipart mp = new MimeMultipart("related");
			mp.addBodyPart(htmlPart);
			
			
			Session s = getMailSession();
			MimeMessage message = new MimeMessage(s);
			message.setSubject(recoveryPassword);
			message.setFrom(new InternetAddress(gatewayMailBox, orgName));
			message.setRecipient(Message.RecipientType.TO, new InternetAddress(targetMailBox));
			message.setContent(mp);
			Transport.send(message);
		
			return true;
					
		}catch(Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	public boolean instructDeposit(
			DepositRecord depositRecord,
			String email) 
	{
		try{
			User user = userService.getUser(depositRecord.getUserId());
			
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			Date createDate = depositRecord.getCreateTime();
			Calendar dueCal = Calendar.getInstance();
			dueCal.setTime(createDate);
			dueCal.add(Calendar.DAY_OF_MONTH, 7);
			Date dueDate = dueCal.getTime();
			
			String content = configService.getSysText("send_mail_for_deposit").getTxtContent();
			content = content.replace("##{invoiceSN}", depositRecord.getSn());
			content = content.replace("##{invoiceDate}", sdf.format(createDate));
			content = content.replace("##{invoiceDueDate}", sdf.format(dueDate));
			content = content.replace("##{invoiceTo}", user.getEmail());
			content = content.replace("##{invoiceFrom}", templateService.getSettingValue(Settings.GATEWAY_ADDRESS));
			content = content.replace("##{invoiceStatus}", PaymentStatus.valueOf(depositRecord.getPmtStatus()).getText()); 
			content = content.replace("##{invoiceSubTotal}", ""+depositRecord.getMoney()); 
			content = content.replace("##{invoiceFee}", ""+depositRecord.getFee()); 
			content = content.replace("##{invoiceTotal}", ""+depositRecord.getTotal()); 
			content = content.replace("##{gatewayName}", ""+templateService.getSettingValue(Settings.GATEWAY_NAME));
			content = content.replace("##{gatewayBankName}", ""+templateService.getSettingValue(Settings.GATEWAY_BANK_NAME));
			content = content.replace("##{gatewayBankRoutingNumber}", ""+templateService.getSettingValue(Settings.GATEWAY_BANK_ROUTING_NUMBER));
			content = content.replace("##{gatewayBankAccountNumber}", ""+templateService.getSettingValue(Settings.GATEWAY_BANK_ACCOUNT_NUMBER)); 
			MimeBodyPart htmlBodypart = new MimeBodyPart(); 
	        htmlBodypart.setContent(
	        		content,
					"text/html; charset=UTF8"); 
	        
	        MimeMultipart htmlMultipart = new MimeMultipart("alternative"); 
			htmlMultipart.addBodyPart(htmlBodypart); 
	        
	        MimeBodyPart htmlPart = new MimeBodyPart();
			htmlPart.setContent(htmlMultipart);
	        
			MimeMultipart mp = new MimeMultipart("related");
			mp.addBodyPart(htmlPart);
			
			Session s = getMailSession();
			MimeMessage message = new MimeMessage(s);
			message.setSubject(instructPayment);
			message.setFrom(new InternetAddress(gatewayMailBox, orgName));
			message.setRecipient(Message.RecipientType.TO, new InternetAddress(email));
			message.setContent(mp);
			Transport.send(message);
		
			return true;
					
		}catch(Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	public boolean instructWithdraw(String email) {
		
		try{
			MimeBodyPart htmlBodypart = new MimeBodyPart(); 
	        htmlBodypart.setContent(
					"提现说明",
					"text/html; charset=GB2312"); 
	        
	        MimeMultipart htmlMultipart = new MimeMultipart("alternative"); 
			htmlMultipart.addBodyPart(htmlBodypart); 
	        
	        MimeBodyPart htmlPart = new MimeBodyPart();
			htmlPart.setContent(htmlMultipart);
	        
			MimeMultipart mp = new MimeMultipart("related");
			mp.addBodyPart(htmlPart);
			
			Session s = getMailSession();
			MimeMessage message = new MimeMessage(s);
			message.setSubject(instructWithdraw);
			message.setFrom(new InternetAddress(gatewayMailBox, orgName));
			message.setRecipient(Message.RecipientType.TO, new InternetAddress(email));
			message.setContent(mp);
			Transport.send(message);
		
			return true;
					
		}catch(Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	private Session getMailSession() {
		
		String smtpServer = templateService.getSettingValue(Settings.SMTP_SERVER);
		String smtpUsername = templateService.getSettingValue(Settings.SMTP_USERNAME);
		String smtpPassword = templateService.getSettingValue(Settings.SMTP_PASSWORD);
		
		Properties prop = new Properties();
		prop.setProperty("mail.smtp.host", smtpServer);
		prop.setProperty("mail.smtp.auth", "true");
		prop.setProperty("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory"); 
		prop.setProperty("mail.smtp.socketFactory.fallback", "false"); 
		prop.setProperty("mail.smtp.port", "465"); 
		prop.setProperty("mail.smtp.socketFactory.port", "465"); 
		Session s = Session.getInstance(prop, new MyAuthenticator(smtpUsername, smtpPassword));
		
		return s;
	}
	
	public static void main(String[] args) {
		
		String msg = "<div class=\"test\">##{invoiceSN}</div>";
		System.out.println(msg.replace("##{invoiceSN}", "111111"));
	}
}

class MyAuthenticator extends Authenticator {
	
	private String username;
	private String password;
	
	public MyAuthenticator(String username, String password) {
		this.username = username;
		this.password = password;
	}
	
	public PasswordAuthentication getPasswordAuthentication() {
		return new PasswordAuthentication(username, password);
	}
}
