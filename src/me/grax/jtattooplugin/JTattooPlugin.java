package me.grax.jtattooplugin;

import java.util.Map;

import javax.swing.ButtonGroup;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JOptionPane;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;

import org.objectweb.asm.tree.ClassNode;

import me.grax.jbytemod.JByteMod;
import me.grax.jbytemod.plugin.Plugin;

public class JTattooPlugin extends Plugin {

  public JTattooPlugin() {
    super("Look and Feel", "1.1", "GraxCode");
  }

  @Override
  public void init() {
    JMenuBar jmb = this.getMenu();
    ButtonGroup group = new ButtonGroup();
    JMenu theme = new JMenu("Theme");
    for (LookAndFeelInfo laf : UIManager.getInstalledLookAndFeels()) {
      JRadioButtonMenuItem menuItem = new JRadioButtonMenuItem(laf.getName());
      if (laf.getName().equals("Nimbus")) {
        menuItem.setSelected(true);
      }
      menuItem.addActionListener(e -> {
        try {
          JByteMod.instance.changeUI(laf.getClassName());
        } catch (Throwable e1) {
          JOptionPane.showMessageDialog(null, "Couldn't set Look and Feel: " + e1.getMessage());
          e1.printStackTrace();
        }
      });
      group.add(menuItem);
      theme.add(menuItem);
    }
    addJTattooLooks(group, theme);
    jmb.add(theme);
  }

  private void addJTattooLooks(ButtonGroup group, JMenu theme) {
    try {
      Class.forName("com.jtattoo.plaf.About");
    } catch (ClassNotFoundException e) {
      System.out.println("JTattoo not found!");
      return;
    }
    addLook("com.jtattoo.plaf.acryl.AcrylLookAndFeel", group, theme);
    addLook("com.jtattoo.plaf.aero.AeroLookAndFeel", group, theme);
    addLook("com.jtattoo.plaf.aluminium.AluminiumLookAndFeel", group, theme);
    addLook("com.jtattoo.plaf.bernstein.BernsteinLookAndFeel", group, theme);
    addLook("com.jtattoo.plaf.fast.FastLookAndFeel", group, theme);
    addLook("com.jtattoo.plaf.graphite.GraphiteLookAndFeel", group, theme);
    addLook("com.jtattoo.plaf.hifi.HiFiLookAndFeel", group, theme);
    addLook("com.jtattoo.plaf.luna.LunaLookAndFeel", group, theme);
    addLook("com.jtattoo.plaf.mcwin.McWinLookAndFeel", group, theme);
    addLook("com.jtattoo.plaf.mint.MintLookAndFeel", group, theme);
    addLook("com.jtattoo.plaf.smart.SmartLookAndFeel", group, theme);
    addLook("com.jtattoo.plaf.texture.TextureLookAndFeel", group, theme);
    addLook("com.jtattoo.plaf.noire.NoireLookAndFeel", group, theme);
  }

  private void addLook(String classname, ButtonGroup group, JMenu theme) {
    JRadioButtonMenuItem menuItem = new JRadioButtonMenuItem(classname.substring(classname.lastIndexOf('.') + 1).replace("LookAndFeel", ""));
    menuItem.addActionListener(e -> {
      try {
        JByteMod.instance.changeUI(classname);
      } catch (Throwable e1) {
        JOptionPane.showMessageDialog(null, "Couldn't set Look and Feel: " + e1.getMessage());
        e1.printStackTrace();
      }
    });
    group.add(menuItem);
    theme.add(menuItem);
  }

  @Override
  public boolean isClickable() {
    return false;
  }

  @Override
  public void loadFile(Map<String, ClassNode> arg0) {
  }

  @Override
  public void menuClick() {
  }

}
