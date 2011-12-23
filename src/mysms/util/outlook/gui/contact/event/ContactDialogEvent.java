package mysms.util.outlook.gui.contact.event;

import java.util.*;

import com.moyosoft.connector.ms.outlook.contact.*;
import mysms.util.outlook.gui.contact.*;

public class ContactDialogEvent extends EventObject
{
	private OutlookContact mContact = null;
	
	public ContactDialogEvent(Object pSource)
	{
		super(pSource);
	}
	
	public ContactDialog getContactDialog()
	{
		return (ContactDialog) getSource();
	}
	
	public OutlookContact getContact()
	{
		return mContact;
	}
	
	public void setContact(OutlookContact pContact)
	{
		mContact = pContact;
	}
}
