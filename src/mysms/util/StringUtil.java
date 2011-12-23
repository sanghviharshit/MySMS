/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package mysms.util;

import java.awt.Color;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Pattern;
import javax.swing.text.BadLocationException;
import javax.swing.text.DefaultHighlighter;
import javax.swing.text.Highlighter;
import javax.swing.text.JTextComponent;

/**
 *
 * @author Jogi
 */
public class StringUtil {

    public static String fix(String input)
    {
        String nInput = input.replace("'", "''") ;
        nInput = nInput.replace("\\", "\\\\");
        nInput = nInput.replace("\"", "\\\"");
        //nInput = Util.escape(input);
        return nInput;
    }

    public static String highlight(JTextComponent c,String rep)
    {
        Highlighter hl = c.getHighlighter();
        if (hl == null)
        {            
            hl = new DefaultHighlighter();
            c.setHighlighter(hl);
        }
        Highlighter.HighlightPainter painter = new DefaultHighlighter.DefaultHighlightPainter(new Color(187, 187, 251));
        int j=0;
        for(int i=0;i<c.getText().length();i++)
        {
            j = c.getText().indexOf(rep, i);
            try
            {
                hl.addHighlight(j, j + 2, painter);
            } 
            catch (BadLocationException ex)
            {
                Logger.getLogger(StringUtil.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return c.getText();
    }

    public static String replace(String oldStr, String newStr, String inString)
    {
        Pattern pat = Pattern.compile(oldStr);
        inString = inString.replaceAll(pat.toString(), newStr);
        return inString;
    }

    public static String removeSpace(String oldStr)
    {
        String newStr = oldStr.replaceAll("\\b\\s{2,}\\b", " ");
        return newStr.replaceAll(" ", "");
    }
}
