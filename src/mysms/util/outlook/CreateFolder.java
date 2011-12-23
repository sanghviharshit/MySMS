package mysms.util.outlook;

import com.moyosoft.connector.com.*;
import com.moyosoft.connector.exception.*;
import com.moyosoft.connector.ms.outlook.*;
import com.moyosoft.connector.ms.outlook.folder.*;

public class CreateFolder
{
   public static void main(String[] args)
   {
      try
      {
         // Outlook application
         Outlook outlookApplication = new Outlook();

         // Get the default contacts folder
         OutlookFolder folder = outlookApplication.getDefaultFolder(FolderType.CONTACTS);

         // Create a new folder in the default contacts folder
         // The new folder will contains tasks
         folder.createFolder("My tasks folder", FolderType.TASKS);

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
