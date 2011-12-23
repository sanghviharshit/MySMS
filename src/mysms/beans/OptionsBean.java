/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package mysms.beans;

/**
 *
 * @author HarshIT
 */
public class OptionsBean {

    private String LoginName160by2;
    private String Email160by2;
    private String SMTPServer;
    private String SMTPPort;
    private boolean SSL;
    private String EmailUserName;
    private String EmailPassword;

    public String getEmail160by2() {
        return Email160by2;
    }

    public void setEmail160by2(String Email160by2) {
        this.Email160by2 = Email160by2;
    }

    public String getEmailPassword() {
        return EmailPassword;
    }

    public void setEmailPassword(String EmailPassword) {
        this.EmailPassword = EmailPassword;
    }

    public String getEmailUserName() {
        return EmailUserName;
    }

    public void setEmailUserName(String EmailUserName) {
        this.EmailUserName = EmailUserName;
    }

    public String getLoginName160by2() {
        return LoginName160by2;
    }

    public void setLoginName160by2(String LoginName160by2) {
        this.LoginName160by2 = LoginName160by2;
    }

    public String getSMTPPort() {
        return SMTPPort;
    }

    public void setSMTPPort(String SMTPPort) {
        this.SMTPPort = SMTPPort;
    }

    public String getSMTPServer() {
        return SMTPServer;
    }

    public void setSMTPServer(String SMTPServer) {
        this.SMTPServer = SMTPServer;
    }

    public boolean isSSL() {
        return SSL;
    }

    public void setSSL(boolean SSL) {
        this.SSL = SSL;
    }

}

