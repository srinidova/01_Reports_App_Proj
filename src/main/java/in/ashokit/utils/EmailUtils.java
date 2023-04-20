package in.ashokit.utils;

import java.io.File;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

@Component
public class EmailUtils {

	@Autowired
	private JavaMailSender mailSender;

	public void sendEmail(String subject, String body, String mailTo,File file) throws Exception {

		try {
			MimeMessage mimemsg = mailSender.createMimeMessage();

			MimeMessageHelper helper = new MimeMessageHelper(mimemsg, true);
			System.out.println("subject---------------"+subject);
			System.out.println("body---------------"+body);
			System.out.println("mailTo---------------"+mailTo);
			helper.setSubject(subject);
			helper.setText(body, true);
			helper.setTo(mailTo);
			helper.addAttachment("Plans-Info", file);
			
			mailSender.send(mimemsg);
			System.out.println("-----Mail Sent----------");
		} catch (MailException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
