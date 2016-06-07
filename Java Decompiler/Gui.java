import javax.swing.UIManager;
import java.awt.*;
import javax.swing.plaf.*;

import javax.swing.plaf.metal.*;
import javax.swing.plaf.metal.MetalLookAndFeel;
import java.io.*;
public class Gui {
  boolean packFrame = false;
MainFrame frame;
  //Construct the application

  public Gui() {
  	
  }
  public void create()
	{
    frame = new MainFrame();
    //Validate frames that have preset sizes
    //Pack frames that have useful preferred size info, e.g. from their layout
    if (packFrame)
      frame.pack();
    else
      frame.validate();
    //Center the window
    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    Dimension frameSize = frame.getSize();
    if (frameSize.height > screenSize.height)
      frameSize.height = screenSize.height;
    if (frameSize.width > screenSize.width)
      frameSize.width = screenSize.width;
    frame.setLocation((screenSize.width - frameSize.width) / 2, (screenSize.height - frameSize.height) / 2);
    frame.setVisible(true);
  }
//Main method

  public static void main(String[] args) {
    try  {

      
      MetalTheme theme=new AquaMetalTheme();
      MetalLookAndFeel.setCurrentTheme(theme);
      UIManager.setLookAndFeel(new MetalLookAndFeel());
    }
    catch (Exception e) {
    System.out.println(e);
    }
    Gui g=new Gui();
    g.create();
  }





 



}

 