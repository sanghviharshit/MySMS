package mysms.util.outlook;

import com.moyosoft.connector.com.*;
import com.moyosoft.connector.exception.*;
import com.moyosoft.connector.ms.outlook.*;
import com.moyosoft.connector.ms.outlook.folder.*;
import com.moyosoft.connector.ms.outlook.item.*;
import com.moyosoft.connector.ms.outlook.contact.*;

public class CreateContact
{
    public static void main(String[] args)
    {
        try
        {
            // Outlook application
            Outlook outlookApplication = new Outlook();
            
            // Get the default Contacts folder
            OutlookFolder folder = outlookApplication.getDefaultFolder(FolderType.CONTACTS);
            
            // Create a new contact in the folder
            OutlookContact contact = (OutlookContact) folder.createItem(ItemType.CONTACT);
            
            // Set the contact's first and last names
            contact.setFirstName("John");
            contact.setLastName("Smith");

            // Save the item
            contact.save();

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