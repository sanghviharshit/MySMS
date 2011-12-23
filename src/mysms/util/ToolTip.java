/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mysms.util;

import java.awt.Color;
import javax.swing.JComponent;
import javax.swing.JTable;
import net.java.balloontip.BalloonTip;
import net.java.balloontip.TablecellBalloonTip;
import net.java.balloontip.styles.*;
import net.java.balloontip.utils.TimingUtils;
import net.java.balloontip.utils.ToolTipUtils;

/**
 *
 * @author Roojuta, Vashishtha
 */
public class ToolTip
{
    private static BalloonTip errBalloon = null;
    private static TablecellBalloonTip tableBalloon = null;
    /**
     * Set a tooltip
     * @param comp		sets a tooltip for this component
     * @param text		the contents of the tooltip (you may use html)
     */
    public static void setToolTip(final JComponent comp, final String text)
    {
        ModernBalloonStyle style = new ModernBalloonStyle(5,5,new Color(169, 205, 221, 220),
                                        new Color(169, 205, 221, 220),  new Color(169, 205, 221, 220));
        final BalloonTip balloon = new BalloonTip(comp, text, style, BalloonTip.Orientation.RIGHT_BELOW, BalloonTip.AttachLocation.EAST, 15, 10, false);
        balloon.enableClickToHide(true);
        ToolTipUtils.balloonToToolTip(balloon, 500, 3000);
    }

    /**
     * Display an error balloon tip
     * @param comp	attach the balloon tip to this component
     * @param text	error message
     */
    public static void showErrorMessage(JComponent comp, String text)
    {
        if (errBalloon != null)
        {
            errBalloon.closeBalloon();
        }
        BalloonTipStyle style = new ModernBalloonStyle(5,5, new Color(255, 0, 0, 220),
                                        new Color(200, 180, 180, 220),  new Color(102,153,0,220));
        errBalloon = new BalloonTip(comp, "<html><font color=\"#ffffff\">" + text + "</font></html>",
                style, BalloonTip.Orientation.LEFT_ABOVE, BalloonTip.AttachLocation.ALIGNED, 15, 10, true);
        errBalloon.enableClickToHide(true);
        TimingUtils.showTimedBalloon(errBalloon, 10000);
    }

    public static void closeErrorMessage()
    {
        if (errBalloon != null)
        {
            errBalloon.closeBalloon();
        }
        if (tableBalloon != null)
        {
            tableBalloon.closeBalloon();
        }
    }

    public static void showTableCellErrorMessage(JTable table, String text, int row, int column)
    {
        tableBalloon = new TablecellBalloonTip(table, "<html><font color=\"#ffffff\">" + text + "</font></html>", row, column,
        new ModernBalloonStyle(5,5, new Color(255, 0, 0, 220),
                                        new Color(200, 180, 180, 220),  new Color(102,153,0,220)),
        BalloonTip.Orientation.LEFT_ABOVE,
        BalloonTip.AttachLocation.ALIGNED,
        40, 20, 
        false);

//        tableBalloon.setViewport(tableScrollPane.getViewport());
    }
}
