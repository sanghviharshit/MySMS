package mysms.util.outlook;

import java.text.*;
import java.util.*;

import com.moyosoft.connector.com.*;
import com.moyosoft.connector.exception.*;
import com.moyosoft.connector.ms.outlook.*;
import com.moyosoft.connector.ms.outlook.appointment.*;
import com.moyosoft.connector.ms.outlook.folder.*;
import com.moyosoft.connector.ms.outlook.item.*;

public class FindAppointment
{
   public static void main(String[] args)
   {
      try
      {
         // Outlook application
         Outlook outlookApplication = new Outlook();

         // Get the default calendar folder
         OutlookFolder folder =
            outlookApplication.getDefaultFolder(FolderType.CALENDAR);

         // Create the search filter.
         // For more information on the filter format, read Microsoft documentation:
         // http://support.microsoft.com/default.aspx?scid=kb;EN-US;201081
         String filter =
            "[Start] > '01/08/2006 11:59 PM' AND " +
            "[Start] < '06/08/2006 12:00 AM'";

         ItemsIterator iterator = folder.getItems().findItems(filter, true);

         // Display info for all found appointments
         while (iterator.hasNext())
         {
            OutlookItem item = iterator.nextItem();

            // Check the item is an appointment
            if (item != null && item.getType().isAppointment())
            {
               OutlookAppointment appointment = (OutlookAppointment) item;
               System.out.println("Subject: " + appointment.getSubject());
               System.out.println("Location: " + appointment.getLocation());
               System.out.println("Start: " + formatDateToString(appointment.getStart()));
               System.out.println("End: " + formatDateToString(appointment.getEnd()));
               System.out.println();
            }
         }

         // Dispose the library
         outlookApplication.dispose();
      }
      catch (ComponentObjectModelException ex)
      {
         System.out.println("COM error has occured: ");
         ex.printStackTrace();
      }
      catch (LibraryNotFoundException ex)
      {
         // If this error occurs, verify the file 'moyocore.dll' is present
         // in java.library.path
         System.out.println("The Java Outlook Library has not been found.");
         ex.printStackTrace();
      }
   }

   public static String formatDateToString(Date date)
   {
      return new SimpleDateFormat().format(date);
   }
}
