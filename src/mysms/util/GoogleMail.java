/*

 * GoogleMail.java
 *
 * Created on April 5, 2007, 11:59 PM
 *
 * This program is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation; either version 2 of the License, or (at
 * your option) any later version.
 *
 * This program is distributed in the hope that it will be useful, but
 * WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place - Suite 330, Boston, MA  02111-1307, USA.
 *
 * Copyright (C) 2007 Cheok YanCheng <yccheok@yahoo.com>

 */

package mysms.util;

import com.sun.mail.smtp.SMTPTransport;
import java.security.Security;
import java.util.Date;
import java.util.Properties;
import javax.mail.AuthenticationFailedException;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 *
 * @author Harshit
 */

public class GoogleMail {
   /** Creates a new instance of GoogleMail */

    public GoogleMail() {
    }

    public static void Send(final String EmailUserName, final String EmailPassword,
            String recipientEmail, String title, String message,
            String Email160by2, String SMTPServer, String SMTPPort,
            boolean isSSL) throws AddressException, MessagingException {

        Security.addProvider(new com.sun.net.ssl.internal.ssl.Provider());
        final String SSL_FACTORY = "javax.net.ssl.SSLSocketFactory";

        // Get a Properties object
        String ifSSL = "false";
        if(isSSL == true)
            ifSSL = "true";

        Properties props = System.getProperties();
        props.setProperty("mail.smtps.host", SMTPServer);
        props.setProperty("mail.smtp.socketFactory.class", SSL_FACTORY);
        props.setProperty("mail.smtp.socketFactory.fallback", "false");
        props.setProperty("mail.smtp.port", SMTPPort);
        props.setProperty("mail.smtp.socketFactory.port", SMTPPort);
        props.setProperty("mail.smtps.auth", ifSSL);

        /*

        If set to false, the QUIT command is sent and the connection is immediately closed. If set 
        to true (the default), causes the transport to wait for the response to the QUIT command.

        ref :   http://java.sun.com/products/javamail/javadocs/com/sun/mail/smtp/package-summary.html
                http://forum.java.sun.com/thread.jspa?threadID=5205249
                smtpsend.java - demo program from javamail
        */        

        props.put("mail.smtps.quitwait", "false");
        Session session = Session.getInstance(props, null);

        // -- Create a new message --
        Message msg = new MimeMessage(session);

        // -- Set the FROM and TO fields --
        msg.setFrom(new InternetAddress(Email160by2));
        msg.setRecipients(Message.RecipientType.TO,

                InternetAddress.parse(recipientEmail, false));

        msg.setSubject(title);
        msg.setText(message);
        msg.setSentDate(new Date());

        SMTPTransport t =
            (SMTPTransport)session.getTransport("smtps");

        t.connect(SMTPServer, EmailUserName, EmailPassword);

        t.sendMessage(msg, msg.getAllRecipients());
        t.close();

    }

}