/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package mysms.util;

import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author HarshIT
 */
public class DirectoryOperations {


        public static boolean createCompleteDirectoryHierarchyIfDoesNotExist(String directory) {
        return createCompleteDirectoryHierarchyIfDoesNotExist(new File(directory));
    }

    public static boolean createFileIfDoesNotExist(String directory) {
        File f = new File(directory);
        try {
                if(!f.exists())
                {
                   f.createNewFile();
                }
            }
        catch (IOException ex)
            {
                Logger.getLogger(DirectoryOperations.class.getName()).log(Level.SEVERE, null, ex);
            }
        return(true);
    }

    private static boolean createCompleteDirectoryHierarchyIfDoesNotExist(File f) {
        if(f == null) return true;

        if(false == createCompleteDirectoryHierarchyIfDoesNotExist(f.getParentFile())) {
            return false;
        }

        String path = null;
        try {
            path = f.getCanonicalPath();
        } catch (IOException ex) {
            Logger.getLogger(DirectoryOperations.class.getName()).log(Level.SEVERE, null, ex);
        }
        return createDirectoryIfDoesNotExist(path);
    }

    public static boolean isFileOrDirectoryExist(String fileOrDirectory) {
        java.io.File f = new java.io.File(fileOrDirectory);
        return f.exists();
    }

    public static boolean createDirectoryIfDoesNotExist(String directory) {
        java.io.File f = new java.io.File(directory);

        if(f.exists() == false) {
            if(f.mkdir())
            {
                return true;
            }
            else
            {
                return false;
            }
        }

        return true;
    }

    public static String getUserDataDirectory() {
        return System.getProperty("user.home") + File.separator + ".MySms" + File.separator +  File.separator;
    }

    public static boolean deleteDir(File dir) {
        if (dir.isDirectory()) {
            String[] children = dir.list();
            for (int i=0; i<children.length; i++) {
                boolean success = deleteDir(new File(dir, children[i]));
                if (!success) {
                    return false;
                }
            }
        }

        // The directory is now empty so delete it
        return dir.delete();
    }

        public static void deleteAllOldFiles(File dir, int days) {
        if (dir.isDirectory()) {
            String[] children = dir.list();
            for (int i=0; i<children.length; i++) {
                deleteAllOldFiles(new File(dir, children[i]), days);
            }

            // Delete empty directory
            if(dir.list().length == 0)
            {
                dir.delete();
            }
        } else {
            final long today = System.currentTimeMillis();
            final long timeStamp = dir.lastModified();

            final long difMil = today - timeStamp;
            final long milPerDay = 1000*60*60*24;
            final long d = difMil / milPerDay;

            if(d >= days)
            {
                dir.delete();
            }
        }
    }



}
