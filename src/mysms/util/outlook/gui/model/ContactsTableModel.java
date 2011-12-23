package mysms.util.outlook.gui.model;

import java.util.*;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.*;

import com.moyosoft.connector.ms.outlook.contact.*;
import com.moyosoft.connector.ms.outlook.folder.*;
import com.moyosoft.connector.ms.outlook.item.*;
import com.moyosoft.connector.com.*;
import javax.swing.SwingWorker;

public class ContactsTableModel extends AbstractTableModel
{
	private ArrayList mContacts = new ArrayList();
	private String[] mHeaders =
		new String[] { "First name", "Last name", "Primary Phone", "Mobile Phone", "Other Phone" };
        public static SwingWorker getItemsThread;
        static ItemsCollection items;
        
	public ContactsTableModel()
	{
	}

	public synchronized void loadContacts(final OutlookFolder pFolder)
		throws ComponentObjectModelException
	{
		mContacts.clear();
		fireTableDataChanged();

		if (pFolder == null)
		{
			return;
		}

		int row = 0;
                getItemsThread = new SwingWorker() {
                    @Override
                    protected Object doInBackground() throws Exception {
                                    items = pFolder.getItems();
                                    return true;
                }
                };
                getItemsThread.execute();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException ex) {
            Logger.getLogger(ContactsTableModel.class.getName()).log(Level.SEVERE, null, ex);
        }
                
//                if (items != null)
//		{
			ItemsIterator it = items.iterator();

                        while(it.hasNext())
			{
				OutlookItem item = it.nextItem();
//Code Added by Harshit for Converting Outlook Contacts Entries Other Phone to Mobile Phone Entries
//  if There is no Mobile Phone Entry Already there
/*
                                OutlookContact pContact = (OutlookContact)item;
                                if(pContact.getMobileTelephoneNumber().equals(""))
                                {
                                    pContact.setMobileTelephoneNumber(pContact.getOtherTelephoneNumber());
                                    pContact.setOtherTelephoneNumber("");
                                }
                                pContact.save();

                                item.save();
*/
                                
                                if (item.getType().isContact())
				{
					mContacts.add(new ListContact((OutlookContact) item));
//                                        fireTableRowsInserted(row, row);

                                        Collections.sort(mContacts,  new ColumnSorter());
                                        fireTableDataChanged();
					
                                        row++;
				}
			}
//		}
//    System.out.println(((ListContact) mContacts.get(2)).getFirstname().toString());

        Collections.sort(mContacts,  new ColumnSorter());

        fireTableDataChanged();
	}

    public class ColumnSorter implements Comparator {
        
        public int compare(Object a, Object b) {
            // Treat empty strains like nulls
/*
            if (a instanceof String && ((String)a).length() == 0) {
            a = null;
            }
            if (b instanceof String && ((String)b).length() == 0) {
            b = null;
            }
 */
            // Sort nulls so they appear last, regardless
            // of sort order

            a = (ListContact) a;
            b = (ListContact) b;
            if (a == null && b == null) {
                return 0;
            } else if (a == null) {
                return 1;
            } else if (b == null) {
                return -1;
            } else if (a instanceof Comparable) {

                    return ((Comparable)a).compareTo(b);

            } else {
                    return ((ListContact) a).getFirstname().toString().compareTo(((ListContact) b).getFirstname().toString());
            }
        }
    }

	public synchronized void addNewContact(OutlookContact pContact)
		throws ComponentObjectModelException
	{
		mContacts.add(new ListContact(pContact));
		fireTableDataChanged();
	}

	public synchronized void updateContact(OutlookContact pContact)
		throws ComponentObjectModelException
	{
		ListContact contact = getListContactById(pContact.getItemId());
		if (contact != null)
		{
			int index = mContacts.indexOf(contact);
			if (index >= 0)
			{
				mContacts.set(index, new ListContact(pContact));
				fireTableDataChanged();
			}
		}
	}

	protected synchronized ListContact getListContactById(OutlookItemID pId)
	{
		if (pId != null)
		{
			for (int i = 0; i < mContacts.size(); i++)
			{
				ListContact contact = (ListContact) mContacts.get(i);
				OutlookItemID id = contact.getId();
				if (pId.equals(id))
				{
					return contact;
				}
			}
		}
		return null;
	}

	public synchronized void removeContactById(OutlookItemID pId)
	{
		mContacts.remove(getListContactById(pId));
		fireTableDataChanged();
	}

	public String getColumnName(int column)
	{
		return mHeaders[column];
	}

	public int getRowCount()
	{
		return mContacts.size();
	}

	public int getColumnCount()
	{
		return mHeaders.length;
	}

	public Object getValueAt(int row, int column)
	{
		ListContact contact = (ListContact) mContacts.get(row);
		if (contact != null)
		{
			switch (column)
			{
				case 0 :
					return contact.getFirstname();
				case 1 :
					return contact.getLastname();
                                case 2 :
					return contact.getPrimaryPhone();
                                case 3 :
					return contact.getMobilePhone();
				case 4 :
					return contact.getOtherPhone();
			}
		}
		return "";
	}

	public ListContact getContactAtRow(int row)
	{
		return (ListContact) mContacts.get(row);
	}

	public OutlookItemID getContactItemIdAtRow(int row)
	{
		ListContact contact = getContactAtRow(row);
		if (contact != null)
		{
			return contact.getId();
		}
		return null;
	}
}
