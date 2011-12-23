/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package mysms.DAO;

import com.thoughtworks.xstream.XStream;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import mysms.beans.OptionsBean;
import mysms.util.DirectoryOperations;
import org.apache.commons.io.FileUtils;

/**
 *
 * @author HarshIT
 */
public class OptionsDAO {

    OptionsBean oBean;
    DirectoryOperations dOps;

    com.thoughtworks.xstream.XStream xstream;

    boolean createCompleteDirectoryHierarchyIfDoesNotExist;
    boolean isFileOrDirectoryExist;
    boolean createFileIfDoesNotExist;
    
    private String FileIODirectory = System.getProperty("user.home")+ File.separator + "MySms";

    private String xml;

    public boolean ifLoadOptions() {

        isFileOrDirectoryExist = DirectoryOperations.isFileOrDirectoryExist(
                FileIODirectory + File.separator + "options.xml");
        if(!isFileOrDirectoryExist)
        {
            return false;
        }
        else
            return true;
    }

    public OptionsBean loadOptions() {

            oBean = new OptionsBean();
            xstream = new XStream();

            File file = new File(FileIODirectory + File.separator + "options.xml");
            String content = "";

            try {
                content = FileUtils.readFileToString(file);
            } catch (IOException ex) {
                Logger.getLogger(OptionsDAO.class.getName()).log(Level.SEVERE, null, ex);
            }

            xml = content.toString();

//            System.out.println(xml);

            oBean = (OptionsBean) xstream.fromXML(xml);
            return oBean;
    }

    public void saveOptions(OptionsBean toSaveBean) {

        xstream = new XStream();
        dOps = new DirectoryOperations();
/*
        System.out.println(oBean.getEmailUserName());
        System.out.println(oBean.getEmailPassword().toString());
        System.out.println(oBean.getLoginName160by2());
        System.out.println(oBean.getEmail160by2());
        System.out.println(oBean.getSMTPServer());
        System.out.println(oBean.getSMTPPort());
        System.out.println(oBean.isSSL());
*/

//        isFileOrDirectoryExist = DirectoryOperations.isFileOrDirectoryExist(
//                FileIODirectory + File.separator + "options.xml");

//        if(!isFileOrDirectoryExist)
//        {
            createCompleteDirectoryHierarchyIfDoesNotExist =
                    DirectoryOperations.createCompleteDirectoryHierarchyIfDoesNotExist(
                    FileIODirectory);

            if(createCompleteDirectoryHierarchyIfDoesNotExist)
                System.out.println("Data Directory Created Successfully");
            else
                System.out.println("Data Directory Already Exists");

            createFileIfDoesNotExist = DirectoryOperations.createFileIfDoesNotExist(
                    FileIODirectory + File.separator + "options.xml");

            if(createFileIfDoesNotExist)
                System.out.println("Data Files Created Successfully");
//        }
//        else
//            System.out.println("Data Files Already Exists");

        xml = xstream.toXML(toSaveBean);
//        System.out.println(xml);
        try {
            FileWriter fstream = new FileWriter(
                    FileIODirectory + File.separator + "options.xml");
            BufferedWriter out = new BufferedWriter(fstream);
            out.write(xml);
            //Close the output stream
            out.close();
            System.out.println("Options Saved Successfully");
        }
        catch (IOException ex) {
            Logger.getLogger(OptionsDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}





