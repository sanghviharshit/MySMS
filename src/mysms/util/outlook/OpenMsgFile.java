package mysms.util.outlook;

import java.io.*;

import com.moyosoft.connector.com.*;
import com.moyosoft.connector.exception.*;
import com.moyosoft.connector.ms.outlook.*;
import com.moyosoft.connector.ms.outlook.item.*;

public class OpenMsgFile
{
    public static void main(String[] args)
    {
        try
        {
            // Outlook application
            Outlook outlookApplication = new Outlook();

            // Open an ".msg" file and create an OutlookItem object from this file
            OutlookItem item = outlookApplication.createItemFromTemplate(new File("my_message.msg"));

            if(item != null)
            {
                System.out.println("Item created successfully from .msg file");

                // Display the Outlook item
                item.display();
            }

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
