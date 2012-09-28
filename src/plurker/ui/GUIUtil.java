/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package plurker.ui;

import com.ctreber.aclib.image.ico.ICOFile;
import com.jtattoo.plaf.AbstractLookAndFeel;
import java.awt.Dimension;
import java.awt.Font;
import java.io.IOException;
import java.util.Dictionary;
import java.util.Enumeration;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JEditorPane;
import javax.swing.LookAndFeel;
import javax.swing.ToolTipManager;
import javax.swing.UIDefaults;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.plaf.FontUIResource;
import org.jb2011.lnf.beautyeye.BeautyEyeLNFHelper;
//import org.fit.cssbox.swingbox.BrowserPane;

/**
 *
 * @author SkyforceShen
 */
public class GUIUtil {

    public static void main(String[] args) throws UnsupportedLookAndFeelException {
//        initGUI();
        LookAndFeel beautyEyeLNFCrossPlatform = BeautyEyeLNFHelper.getBeautyEyeLNFCrossPlatform();
// javax.swing.UIManager.installLookAndFeel(beautyEyeLNFCrossPlatform.get);
        javax.swing.UIManager.setLookAndFeel(beautyEyeLNFCrossPlatform);
//        try {
//            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
//                System.out.println(info.getName());
//                if ("Nimbus".equals(info.getName())) {
//                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
//                    break;
//                    
//                    
//                }
//            }
//        } catch (ClassNotFoundException ex) {
//            java.util.logging.Logger.getLogger(PlurkerApplication.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (InstantiationException ex) {
//            java.util.logging.Logger.getLogger(PlurkerApplication.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (IllegalAccessException ex) {
//            java.util.logging.Logger.getLogger(PlurkerApplication.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
//            java.util.logging.Logger.getLogger(PlurkerApplication.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        }
    }

    public final static void initGUI() {
        boolean useJtattoo = true;
        if (useJtattoo) {
            try {
                // select Look and Feel
//                SmartLookAndFeel.setTheme(null);
//                UIManager.setLookAndFeel("com.jtattoo.plaf.smart.SmartLookAndFeel");
                UIManager.setLookAndFeel("com.jtattoo.plaf.fast.FastLookAndFeel");
                // start application
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        boolean useNimbus = false;
        if (useNimbus) {
            try {
                for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
//                    System.out.println(info.getName());
                    if ("Nimbus".equals(info.getName())) {
                        javax.swing.UIManager.setLookAndFeel(info.getClassName());
                        break;


                    }
                }
            } catch (ClassNotFoundException ex) {
                java.util.logging.Logger.getLogger(PlurkerApplication.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
            } catch (InstantiationException ex) {
                java.util.logging.Logger.getLogger(PlurkerApplication.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
            } catch (IllegalAccessException ex) {
                java.util.logging.Logger.getLogger(PlurkerApplication.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
            } catch (javax.swing.UnsupportedLookAndFeelException ex) {
                java.util.logging.Logger.getLogger(PlurkerApplication.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
            }
        }
        boolean useBeautyEye = false;
        if (useBeautyEye) {
            UIManager.put("RootPane.setupButtonVisible", false);
            try {
                BeautyEyeLNFHelper.frameBorderStyle = BeautyEyeLNFHelper.FrameBorderStyle.translucencySmallShadow;
                BeautyEyeLNFHelper.translucencyAtFrameInactive = false;
                BeautyEyeLNFHelper.launchBeautyEyeLNF();
            } catch (Exception ex) {
                Logger.getLogger(GUIUtil.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        GUIUtil.setUI(new FontUIResource(GUIUtil.font));
        ToolTipManager.sharedInstance().setInitialDelay(0);

    }
    public static Font font = new Font("微軟正黑體", Font.PLAIN, 13);
    public static Font smallfont = new Font("微軟正黑體", Font.PLAIN, 12);
    public static Font boldFont = new Font("微軟正黑體", Font.BOLD, 13);
    private static JEditorPane dummyEditorPane;//= null;

    public static int getContentHeight(String content, int width, Dictionary imageCache) {
//        if (null == dummyEditorPane) {
        JEditorPane dummyEditorPane = new JEditorPane();
        dummyEditorPane.setEditorKit(FixedHTMLEditorKit.getInstance());
        dummyEditorPane.setContentType("text/html");
//        dummyEditorPane.setFont(GUIUtil.font);
        dummyEditorPane.getDocument().putProperty("imageCache", imageCache);
        dummyEditorPane.setBorder(null);
//        }

        dummyEditorPane.setSize(width, Short.MAX_VALUE);
        dummyEditorPane.setText(content);

        return dummyEditorPane.getPreferredSize().height;
    }
//    public final static int DefaultScrollBarWidth = 15;
    public final static int DefaultUnitIncrement = 20;
    public final static int PlurksPerFetch = 20;

    public static void setUI(FontUIResource f) {
        Enumeration keys = UIManager.getDefaults().keys();
        while (keys.hasMoreElements()) {
            Object key = keys.nextElement();
            Object value = UIManager.get(key);
//            System.out.println(key);
            if (value != null && value instanceof FontUIResource) {
                UIManager.put(key, f);
            }
        }
    }

    public static java.util.List getIconImages(String icoFilename) throws IOException {
        ICOFile ico = new ICOFile(icoFilename);
        java.util.List list = ico.getImages();
        return list;
    }
}
