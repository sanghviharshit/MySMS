package mysms.util.outlook;

import com.moyosoft.connector.com.*;
import com.moyosoft.connector.exception.*;
import com.moyosoft.connector.ms.outlook.*;
import com.moyosoft.connector.ms.outlook.recipient.*;

public class HelloOutlook
{
	public static void main(String[] args)
	{
		try
		{
			// Outlook application
			Outlook outlookApplication = new Outlook();

			OutlookRecipient currentUser = outlookApplication.getCurrentUser();

			System.out.println("Hello " + currentUser.getName() + " !");
			System.out.println(
				"Your e-mail address is " + currentUser.getAddress() + ".");

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
			System.out.println("The Java Outlook Library hasn't been found.");
			ex.printStackTrace();
		}
	}
}