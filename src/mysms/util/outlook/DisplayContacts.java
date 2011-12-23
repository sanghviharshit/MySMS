package mysms.util.outlook;

import com.moyosoft.connector.com.*;
import com.moyosoft.connector.exception.*;
import com.moyosoft.connector.ms.outlook.*;
import com.moyosoft.connector.ms.outlook.folder.*;
import com.moyosoft.connector.ms.outlook.item.*;
import com.moyosoft.connector.ms.outlook.contact.*;

public class DisplayContacts
{
   public static void main(String[] args)
   {
      try
      {
         // Outlook application
         Outlook outlookApplication = new Outlook();

         // Get the default contacts folder
         OutlookFolder folder = outlookApplication.getDefaultFolder(FolderType.CONTACTS);

         // Get the folder's items collection
         ItemsCollection items = folder.getItems();

         // Display info for all contacts in the folder
         for(ItemsIterator it = items.iterator(); it.hasNext();)
         {
           OutlookItem item = it.nextItem();

           // Check the item is a contact
           if(item != null && item.getType().isContact())
           {
              OutlookContact contact = (OutlookContact) item;
              System.out.println("First name: " + contact.getFirstName());
              System.out.println("Last name: " + contact.getLastName());
              System.out.println("Company: " + contact.getCompanyName());
              System.out.println();
           }
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
         System.out.println("The Java Outlook Library couln't be found.");
         ex.printStackTrace();
      }
   }
}