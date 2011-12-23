package mysms.util.outlook;

import java.io.*;

import com.moyosoft.connector.com.*;
import com.moyosoft.connector.exception.*;
import com.moyosoft.connector.ms.outlook.*;
import com.moyosoft.connector.ms.outlook.folder.*;
import com.moyosoft.connector.ms.outlook.mail.*;

public class SendMailAttachment
{
    public static void main(String[] args)
    {
        try
        {
            // Outlook application
            Outlook outlookApplication = new Outlook();

            // Get the Outbox folder
            OutlookFolder outbox = outlookApplication.getDefaultFolder(FolderType.OUTBOX);

            // Create a new mail in the outbox folder
            OutlookMail mail = new OutlookMail(outbox);

            // Set the subject, destination and contents of the mail
            mail.setSubject("Hello world !");
            mail.setTo("your_email@example.com");

            // Attach a file named "test.txt"
            mail.getAttachments().add(new File("test.txt"));

            // Send the mail
            mail.send();

            // Dispose the library
            outlookApplication.dispose();
        }
        catch(ComponentObjectModelException ex)
        {
            System.out.println("COM error has occured: ");
            ex.printStackTrace();
        }
        catch(LibraryNotFoundException ex)
        {
            // If this error occurs, verify the file 'moyocore.dll' is present
            // in java.library.path
            System.out.println("The Java Outlook Library has not been found.");
            ex.printStackTrace();
        }
    }
}
