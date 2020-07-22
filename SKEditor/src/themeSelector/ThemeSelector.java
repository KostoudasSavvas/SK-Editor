package themeSelector;

import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import com.jtattoo.plaf.AbstractLookAndFeel;
import java.awt.Rectangle;
import java.awt.Window;
import javax.swing.*;

public class ThemeSelector {
	private static int selectedTheme = 0;
	private static JList themeList  = new JList();
	private static ListSelectionListener themeListener = null;
	private static ListSelectionListener lafListener = null;
    private static JList lafList = null;
    private static int selectedLaf = 0;
    
    
    public void themeSelect(JMenu themeMenu) {
    	lafList = new JList(Constants.LAF_NAMES);
        lafList.setBorder(BorderFactory.createEmptyBorder(4, 4, 4, 4));
        lafList.setSelectedIndex(0);
        themeMenu.add(lafList);
        lafList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        lafListener = new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (!e.getValueIsAdjusting()) {
                    if (lafList.getSelectedIndex() != -1) {
                        if (selectedLaf != lafList.getSelectedIndex()) {
                            selectedLaf = lafList.getSelectedIndex();
                            SwingUtilities.invokeLater(new Runnable() {
                                public void run() {
                                    setLookAndFeel(true);
                                }
                            });
                        }
                    } else {
                        // We don't want the list to be unselected, so if user unselects the list
                        // we do select the last selected entry
                        lafList.setSelectedIndex(selectedLaf);
                    }
                }
            }
        };
        lafList.addListSelectionListener(lafListener);
        JScrollPane lafScrollPane = new JScrollPane(lafList);
        lafScrollPane.setBorder(new TitleBorder("Themes"));
        themeMenu.add(lafScrollPane);
		themeList.setBorder(BorderFactory.createEmptyBorder(4, 4, 4, 4));
        themeList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        //themeMenu.add(themeList);
        fillThemeList();
        themeListener = new ListSelectionListener() {

            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (!e.getValueIsAdjusting()) {
                    if (themeList.getSelectedIndex() != -1) {
                        if (selectedTheme != themeList.getSelectedIndex()) {
                            selectedTheme = themeList.getSelectedIndex();
                            // We change the look and feel after all pending events are dispatched,
                            // otherwise there will be some serious redrawing problems.
                            SwingUtilities.invokeLater(new Runnable() {

                                @Override
                                public void run() {
                                    setLookAndFeel(false);
                                }
                            });
                        }
                    } else {
                        // We don't want the list to be unselected, so if user unselects the list
                        // we just select the last selected entry
                        themeList.setSelectedIndex(selectedTheme);
                    }
                }
            }
        };
    }
    private static void setLookAndFeel(boolean loadThemes) {
        try {
            String theme = "Default";
            if (!loadThemes) {
                theme = (String)themeList.getSelectedValue();
            }
            switch (selectedLaf) {
                case Constants.LAF_ACRYL :
                    com.jtattoo.plaf.acryl.AcrylLookAndFeel.setTheme(theme);
                    // Now we can set the look and feel
                    UIManager.setLookAndFeel("com.jtattoo.plaf.acryl.AcrylLookAndFeel");
                    break;
                case Constants.LAF_AERO :
                    com.jtattoo.plaf.aero.AeroLookAndFeel.setTheme(theme);
                    UIManager.setLookAndFeel("com.jtattoo.plaf.aero.AeroLookAndFeel");
                    break;
                case Constants.LAF_ALUMINIUM :
                    com.jtattoo.plaf.aluminium.AluminiumLookAndFeel.setTheme(theme);
                    UIManager.setLookAndFeel("com.jtattoo.plaf.aluminium.AluminiumLookAndFeel");
                    break;
                case Constants.LAF_BERNSTEIN :
                    com.jtattoo.plaf.bernstein.BernsteinLookAndFeel.setTheme(theme);
                    UIManager.setLookAndFeel("com.jtattoo.plaf.bernstein.BernsteinLookAndFeel");
                    break;
                case Constants.LAF_FAST :
                    com.jtattoo.plaf.fast.FastLookAndFeel.setTheme(theme);
                    UIManager.setLookAndFeel("com.jtattoo.plaf.fast.FastLookAndFeel");
                    break;
                case Constants.LAF_GRAPHITE :
                    com.jtattoo.plaf.graphite.GraphiteLookAndFeel.setTheme(theme);
                    UIManager.setLookAndFeel("com.jtattoo.plaf.graphite.GraphiteLookAndFeel");
                    break;
                case Constants.LAF_HIFI :
                    com.jtattoo.plaf.hifi.HiFiLookAndFeel.setTheme(theme);
                    UIManager.setLookAndFeel("com.jtattoo.plaf.hifi.HiFiLookAndFeel");
                    break;
                case Constants.LAF_LUNA :
                    com.jtattoo.plaf.luna.LunaLookAndFeel.setTheme(theme);
                    UIManager.setLookAndFeel("com.jtattoo.plaf.luna.LunaLookAndFeel");
                    break;
                case Constants.LAF_MCWIN :
                    com.jtattoo.plaf.mcwin.McWinLookAndFeel.setTheme(theme);
                    UIManager.setLookAndFeel("com.jtattoo.plaf.mcwin.McWinLookAndFeel");
                    break;
                case Constants.LAF_MINT :
                    com.jtattoo.plaf.mint.MintLookAndFeel.setTheme(theme);
                    UIManager.setLookAndFeel("com.jtattoo.plaf.mint.MintLookAndFeel");
                    break;
                case Constants.LAF_NOIRE :
                    com.jtattoo.plaf.noire.NoireLookAndFeel.setTheme(theme);
                    UIManager.setLookAndFeel("com.jtattoo.plaf.noire.NoireLookAndFeel");
                    break;
                case Constants.LAF_SMART :
                    com.jtattoo.plaf.smart.SmartLookAndFeel.setTheme(theme);
                    UIManager.setLookAndFeel("com.jtattoo.plaf.smart.SmartLookAndFeel");
                    break;
                case Constants.LAF_TEXTURE :
                    com.jtattoo.plaf.texture.TextureLookAndFeel.setTheme(theme);
                    UIManager.setLookAndFeel("com.jtattoo.plaf.texture.TextureLookAndFeel");
                    break;
            }
            Window windows[] = Window.getWindows();
            for (Window window : windows) {
                if (window.isDisplayable()) {
                    SwingUtilities.updateComponentTreeUI(window);
                }
            }
            if (loadThemes) {
                fillThemeList();
            }
            scrollSelectedToVisible(lafList);
            scrollSelectedToVisible(themeList);
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }
 private static  void scrollSelectedToVisible(JList list) {
        // Because of the different font size the selected item
        // maybe out of the visible area. So we correct this.
        int idx = list.getLeadSelectionIndex();
        Rectangle rect = list.getCellBounds(idx, idx);
        if (rect != null) {
            list.scrollRectToVisible(rect);
        }
 }

 private static void fillThemeList() {
        themeList.removeListSelectionListener(themeListener);
        // Setup the theme list with data from the look and feel classes
        LookAndFeel laf = UIManager.getLookAndFeel();
        if (laf instanceof com.jtattoo.plaf.acryl.AcrylLookAndFeel) {
            themeList.setListData(com.jtattoo.plaf.acryl.AcrylLookAndFeel.getThemes().toArray());
        } else if (laf instanceof com.jtattoo.plaf.aero.AeroLookAndFeel) {
            themeList.setListData(com.jtattoo.plaf.aero.AeroLookAndFeel.getThemes().toArray());
        } else if (laf instanceof com.jtattoo.plaf.aluminium.AluminiumLookAndFeel) {
            themeList.setListData(com.jtattoo.plaf.aluminium.AluminiumLookAndFeel.getThemes().toArray());
        } else if (laf instanceof com.jtattoo.plaf.bernstein.BernsteinLookAndFeel) {
            themeList.setListData(com.jtattoo.plaf.bernstein.BernsteinLookAndFeel.getThemes().toArray());
        } else if (laf instanceof com.jtattoo.plaf.fast.FastLookAndFeel) {
            themeList.setListData(com.jtattoo.plaf.fast.FastLookAndFeel.getThemes().toArray());
        } else if (laf instanceof com.jtattoo.plaf.graphite.GraphiteLookAndFeel) {
            themeList.setListData(com.jtattoo.plaf.graphite.GraphiteLookAndFeel.getThemes().toArray());
        } else if (laf instanceof com.jtattoo.plaf.hifi.HiFiLookAndFeel) {
            themeList.setListData(com.jtattoo.plaf.hifi.HiFiLookAndFeel.getThemes().toArray());
        } else if (laf instanceof com.jtattoo.plaf.luna.LunaLookAndFeel) {
            themeList.setListData(com.jtattoo.plaf.luna.LunaLookAndFeel.getThemes().toArray());
        } else if (laf instanceof com.jtattoo.plaf.mcwin.McWinLookAndFeel) {
            themeList.setListData(com.jtattoo.plaf.mcwin.McWinLookAndFeel.getThemes().toArray());
        } else if (laf instanceof com.jtattoo.plaf.mint.MintLookAndFeel) {
            themeList.setListData(com.jtattoo.plaf.mint.MintLookAndFeel.getThemes().toArray());
        } else if (laf instanceof com.jtattoo.plaf.noire.NoireLookAndFeel) {
            themeList.setListData(com.jtattoo.plaf.noire.NoireLookAndFeel.getThemes().toArray());
        } else if (laf instanceof com.jtattoo.plaf.smart.SmartLookAndFeel) {
            themeList.setListData(com.jtattoo.plaf.smart.SmartLookAndFeel.getThemes().toArray());
        } else if (laf instanceof com.jtattoo.plaf.texture.TextureLookAndFeel) {
            themeList.setListData(com.jtattoo.plaf.texture.TextureLookAndFeel.getThemes().toArray());
        } else {
            themeList.setListData((Object[])null);
        }
        if (UIManager.getLookAndFeel() instanceof AbstractLookAndFeel) {
            themeList.setSelectedValue("Default", true);
        }
        selectedTheme = themeList.getSelectedIndex();
        themeList.addListSelectionListener(themeListener);
    }
}