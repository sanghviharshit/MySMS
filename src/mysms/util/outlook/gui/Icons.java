package mysms.util.outlook.gui;

import javax.swing.*;

public class Icons
{
   public static final Icon SAVE_ICON = loadIcon("mysms/resources/images/outlook/save.gif");
   public static final Icon SAVEAS_ICON = loadIcon("mysms/resources/images/outlook/saveas.gif");
   public static final Icon DELETE_ICON = loadIcon("mysms/resources/images/outlook/delete.gif");
   public static final Icon DELETE_DISABLED_ICON  = loadIcon("mysms/resources/images/outlook/delete_disabled.gif");
   public static final Icon ADD_ATTACHMENT_ICON = loadIcon("mysms/resources/images/outlook/add_attachement.gif");
   public static final Icon ADD_FOLDER_ICON = loadIcon("mysms/resources/images/outlook/add_folder.gif");
   public static final Icon REFRESH_ICON = loadIcon("mysms/resources/images/outlook/refresh.gif");
   public static final Icon CUT_ICON = loadIcon("mysms/resources/images/outlook/cut.gif");
   public static final Icon COPY_ICON = loadIcon("mysms/resources/images/outlook/copy.gif");
   public static final Icon PASTE_ICON = loadIcon("mysms/resources/images/outlook/paste.gif");

   public static final Icon FOLDER_CLOSED_ICON = loadIcon("mysms/resources/images/outlook/folder_closed_icon.gif");
   public static final Icon FOLDER_OPEN_ICON = loadIcon("mysms/resources/images/outlook/folder_open_icon.gif");

   private static Icon loadIcon(String pIconPath)
   {
     try
     {
       return new ImageIcon(
         Icons.class.getClassLoader().getResource(
            pIconPath));
     }
     catch(Exception ex)
     {
       return null;
     }
   }
}
