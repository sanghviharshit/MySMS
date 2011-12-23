package mysms.util.outlook;

import java.text.*;
import java.util.*;

import com.moyosoft.connector.com.*;
import com.moyosoft.connector.exception.*;
import com.moyosoft.connector.ms.outlook.*;
import com.moyosoft.connector.ms.outlook.folder.*;
import com.moyosoft.connector.ms.outlook.item.*;
import com.moyosoft.connector.ms.outlook.mail.*;

public class FindNewMail
{
    public static void main(String[] args)
    {
        try
        {
            Date receivedTime = new Date();
            ArrayList receivedIds = new ArrayList();

            for(int i=0; i<100; i++)
            {
                // Outlook application
                Outlook outlookApplication = new Outlook();

                // Get the default inbox folder
                OutlookFolder inbox = outlookApplication
                        .getDefaultFolder(FolderType.INBOX);

                String filter = "[CreationTime] >= '" + formatDateToString(receivedTime) + "'";
                receivedTime = new Date();
                
                ItemsIterator iterator = inbox.getItems().findItems(filter);
                ArrayList foundIds = new ArrayList();

                while(iterator.hasNext())
                {
                    OutlookItem item = iterator.nextItem();

                    if(item != null && item.getType().isMail())
                    {
                        OutlookMail mail = (OutlookMail) item;
                        OutlookItemID id = mail.getItemId();

                        if(!receivedIds.contains(id))
                        {
                            handleNewMail(mail);
                        }

                        foundIds.add(id);
                    }
                }

                receivedIds = foundIds;

                // Dispose the library
                outlookApplication.dispose();
                
                Thread.sleep(10 * 1000);
            }
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
        catch(InterruptedException e)
        {
            e.printStackTrace();
        }
    }

    public static void handleNewMail(OutlookMail mail)
    {
        System.out.println("New mail received:");
        System.out.println("  Subject: " + mail.getSubject());
        System.out.println("  From: " + mail.getSenderAddress());
        System.out.println();
    }

    public static String formatDateToString(Date date)
    {
        return new SimpleDateFormat().format(date);
    }
}
