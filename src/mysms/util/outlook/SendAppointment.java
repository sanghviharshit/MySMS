package mysms.util.outlook;

import com.moyosoft.connector.com.*;
import com.moyosoft.connector.exception.*;
import com.moyosoft.connector.ms.outlook.*;
import com.moyosoft.connector.ms.outlook.folder.*;
import com.moyosoft.connector.ms.outlook.meeting.*;
import com.moyosoft.connector.ms.outlook.appointment.*;

import java.util.*;

public class SendAppointment
{
    public static void main(String[] args)
    {
        try
        {
            // Outlook application
            Outlook outlookApplication = new Outlook();

            // Get the default calendar folder
            OutlookFolder calendar = outlookApplication.getDefaultFolder(FolderType.CALENDAR);

            // Create a new appointment in the default calendar folder
            OutlookAppointment appointment = new OutlookAppointment(calendar);

            // Set the subject, start date and duration of the appointment
            appointment.setSubject("My appointment");
            appointment.setStart(new Date());
            appointment.setDuration(30);

            // Add a recipient for the appointment
            appointment.getRecipients().createNew("your_email@example.com");

            // To send the appointment, set the status as Meeting
            appointment.setMeetingStatus(MeetingStatus.MEETING);

            // Send the appointment
            appointment.send();

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