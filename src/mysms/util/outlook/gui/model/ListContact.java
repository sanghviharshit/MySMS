package mysms.util.outlook.gui.model;

import com.moyosoft.connector.com.*;
import com.moyosoft.connector.ms.outlook.contact.*;
import com.moyosoft.connector.ms.outlook.item.*;

public class ListContact
{
	private String mFirstname = null;
	private String mLastname = null;
        private String mPrimaryPhone = null;
        private String mMobilePhone = null;
        private String mOtherPhone = null;
	private String mEmail = null;
	private OutlookItemID mId = null;

	
	public ListContact(OutlookContact pContact) throws ComponentObjectModelException
	{
		mFirstname = pContact.getFirstName();
		mLastname = pContact.getLastName();
		mPrimaryPhone = pContact.getPrimaryTelephoneNumber();
                mMobilePhone = pContact.getMobileTelephoneNumber();
                mOtherPhone = pContact.getOtherTelephoneNumber();
//		mEmail = pContact.getEmail1Address();
		mId = pContact.getItemId();
	}
	
	public ListContact()
	{
	}
	
	public String getFirstname()
	{
		return mFirstname;
	}

   	public void setFirstname(String string)
	{
		mFirstname = string;
	}

	public OutlookItemID getId()
	{
		return mId;
	}

	public void setId(OutlookItemID itemID)
	{
		mId = itemID;
	}

	public String getLastname()
	{
		return mLastname;
	}

    public void setLastname(String string)
	{
		mLastname = string;
	}

    public String getOtherPhone()
	{
		return mOtherPhone;
	}

	public void setOtherPhone(String string)
	{
		mOtherPhone = string;
	}

    public String getMobilePhone() {
        return mMobilePhone;
    }

    public void setMobilePhone(String mMobilePhone) {
        this.mMobilePhone = mMobilePhone;
    }

    public String getPrimaryPhone() {
        return mPrimaryPhone;
    }

    public void setPrimaryPhone(String mPrimaryPhone) {
        this.mPrimaryPhone = mPrimaryPhone;
    }

    public String getEmail()
	{
		return mEmail;
	}

    public void setEmail(String string)
	{
		mEmail = string;
	}
}
