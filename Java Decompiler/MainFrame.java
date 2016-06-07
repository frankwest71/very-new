
import java.io.*;
import java.awt.*;
import java.beans.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.text.*;
import javax.swing.border.*;
import borland.jbcl.layout.*;
import javax.swing.plaf.metal.*;
import java.util.*;
//import com.sun.java.swing.preview.*;

public class MainFrame extends JFrame{
	
	
	
  int filesaved=0;	
	String filename="";
	String filepath="";
	File file;
	boolean saved=true;
  
  BorderLayout borderLayout1 = new BorderLayout();
  Font font;
  Border border=new BevelBorder(1);
  JMenuBar menuBar1 = new JMenuBar();
  JToolBar toolBar = new JToolBar();

  JButton bnew = new JButton();
  JButton bopen = new JButton();
  JButton bsave= new JButton();
  JButton bcut = new JButton();
  JButton bcopy = new JButton();
  JButton bpaste = new JButton();
  JButton bhelp = new JButton();

  ImageIcon open,save,cut,copy,paste,help,new1;
  
  JLabel statusBar = new JLabel();
  //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~//
  JMenu jMenu1 = new JMenu();
  JMenuItem jMenuItem1 = new JMenuItem();
  JMenuItem jMenuItem3 = new JMenuItem();
  JMenuItem jMenuItem4 = new JMenuItem();
  JMenuItem jMenuItem5 = new JMenuItem();
  JMenuItem jMenuItem6 = new JMenuItem();
  //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~//
  JMenu jMenu2 = new JMenu();
  JMenuItem jMenuItem8 = new JMenuItem();
  JMenuItem jMenuItem9 = new JMenuItem();
  JMenuItem jMenuItem10 = new JMenuItem();
  JMenuItem jMenuItem11 = new JMenuItem();
  JMenuItem jMenuItem12 = new JMenuItem();
  //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~//
  JMenu jMenu3 = new JMenu();
  JMenu jMenu4 = new JMenu();
  JMenuItem jMenuItem13 = new JMenuItem();
  JMenuItem jMenuItem14 = new JMenuItem();
  JMenuItem jMenuItem15 = new JMenuItem();
  JMenuItem jMenuItem16 = new JMenuItem();
  JMenuItem jMenuItem17 = new JMenuItem();
  //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~//
  JMenu jMenu5 = new JMenu();
  JMenuItem jMenuItem7 = new JMenuItem();
  JMenu jMenu6 = new JMenu();
  JMenu jMenu7 = new JMenu();
  JMenu fontMenu=new JMenu();
  //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~//
  
  
  
  
  
  XYLayout xYLayout1 = new XYLayout();
  GridLayout gridLayout1 = new GridLayout();
  JScrollPane jScrollPane1 = new JScrollPane();
  JPanel jPanel1 = new JPanel();
  JTextArea jTextArea1 = new JTextArea();
  GridLayout gridLayout2 = new GridLayout();
  JMenuItem jMenuItem25 = new JMenuItem();
  JCheckBoxMenuItem jCheckBoxMenuItem1 = new JCheckBoxMenuItem();
  JCheckBoxMenuItem jCheckBoxMenuItem3 = new JCheckBoxMenuItem();
  JCheckBoxMenuItem jCheckBoxMenuItem4 = new JCheckBoxMenuItem();
  JCheckBoxMenuItem jCheckBoxMenuItem5 = new JCheckBoxMenuItem();
  JCheckBoxMenuItem jCheckBoxMenuItem6 = new JCheckBoxMenuItem();
  JCheckBoxMenuItem jCheckBoxMenuItem7 = new JCheckBoxMenuItem();
  JCheckBoxMenuItem jCheckBoxMenuItem8 = new JCheckBoxMenuItem();
  JCheckBoxMenuItem winplaf =new JCheckBoxMenuItem("Windows");
  
  JCheckBoxMenuItem decom = new JCheckBoxMenuItem("Decompile",true);
  JCheckBoxMenuItem dis = new JCheckBoxMenuItem();
  JCheckBoxMenuItem obfus = new JCheckBoxMenuItem();

  
  
  
  public void SetTitle(String title){this.setTitle(title);}
  public void dispose(){System.exit(0);}
  public void saveFile(File f){
  	try{
  	FileOutputStream fo=new FileOutputStream(f);
  	String content=jTextArea1.getText();
  	fo.write(content.getBytes());
  	fo.close();
  		}catch(Exception e){}
  }
  public void newEditor(){jTextArea1.selectAll();jTextArea1.cut();filesaved=0;saved=false;}
///////////////INNER CLASS FOR EVENT HANDLING///////////
class FileEvent implements ActionListener{
	public void actionPerformed(ActionEvent ae){
		String cmd=ae.getActionCommand();
		if(cmd.equals("New")){newEditor();}
		if(cmd.equals("Exit"))
		{
			dispose();
			System.exit(0);
		}
		if(cmd.equals("Open"))
		{
			
	try{
	JFrame frame=new JFrame();
  JFileChooser chose=new JFileChooser();
  String x[]=new String[2];
  x[0]="class";
  x[1]="java";
   ExampleFileFilter filter=new ExampleFileFilter(x,"Java & Class Files");
  chose.setFileFilter(filter);
chose.showOpenDialog(frame);
File inp=chose.getSelectedFile();
File disp=inp;
newEditor();
if((inp.getName().indexOf(".class"))!=-1)
  	{
 DeCompiler d=new DeCompiler(inp);
 if(decom.getState())
 disp=new File("temp.dec");
 if(dis.getState())
 	disp=new File("temp.dis");
 display(disp);
   	}
  	else
  	{
display(inp);	
  	}
  }catch(Exception e8)
  {
  System.out.println(e8);
  }			
			
		}
		if(cmd.equals("Save As"))
		{
			try{
	JFrame frame=new JFrame();
  JFileChooser chose=new JFileChooser();
  chose.showSaveDialog(frame);
  file=chose.getSelectedFile();
  filename=file.getName();
  filepath=file.getAbsolutePath();
  SetTitle(filepath);
 saveFile(file);
  filesaved=1;}catch(Exception e){}

		}
		if(cmd.equals("Save"))
		{
			if(filesaved==0)
			{try{
	JFrame frame=new JFrame();
  JFileChooser chose=new JFileChooser();
  chose.showSaveDialog(frame);
  file=chose.getSelectedFile();
  filename=file.getName();
  filepath=file.getAbsolutePath();
  SetTitle(filepath);
  saveFile(file);
  filesaved=1;
			}catch(Exception e){}
			}
			else
			{
				saveFile(file);
			}
		}
	}
}

/////////////EVENT HANDLING CLASS FOR EDIT MENU/////////
class EditEvent implements ActionListener{
	public void actionPerformed(ActionEvent ae){
		String cmd=ae.getActionCommand();
		if(cmd.equals("Find"))
		{
			
		}
		if(cmd.equals("Cut"))
		{
		jTextArea1.cut();
		jTextArea1.requestFocus();
						}
		if(cmd.equals("Copy"))
		{
			jTextArea1.copy();
			jTextArea1.requestFocus();

						}
if(cmd.equals("Paste"))
{
	jTextArea1.paste();
	jTextArea1.requestFocus();


}			
if(cmd.equals("Select All"))
{
	jTextArea1.selectAll();
	jTextArea1.requestFocus();

}
	}
}

////////////////EVENT HANDLING CLASS FOR HELP MENU/////////////

class HelpEvent implements ActionListener{
	JFrame frame=new JFrame();
	public void actionPerformed(ActionEvent ae){
		String cmd=ae.getActionCommand();
		if(cmd.equals("us"))
		{
			JOptionPane.showMessageDialog(frame,"Java DeCompiler & Obfuscator is a project done by \nPrabhu,\nCoimbatore,\nIndia.","About Us",JOptionPane.INFORMATION_MESSAGE);
		}
			if(cmd.equals("decompiler"))
			{
	JOptionPane.showMessageDialog(frame,"Java DeCompiler & Obfuscator Is An Open Source Program Available At \nhttp://www.dcompiler.sourceforge.net.\n This Program Is Released Under GPL.","About DCompiler",JOptionPane.INFORMATION_MESSAGE);
			}
				if(cmd.equals("visit"))
				{
	JOptionPane.showMessageDialog(frame,"For More Information Visit Us At \n www.linux4us.net","Visit Us",JOptionPane.INFORMATION_MESSAGE);
				}
					if(cmd.equals("mail"))
					{
	JOptionPane.showMessageDialog(frame,"To Report Any Bug Or Suggestions Mail Us At\n prabhu@geek.com\n prabhu006@yahoo.co.in","Mail Address",JOptionPane.INFORMATION_MESSAGE);
					}
						if(cmd.equals("help"))
		JOptionPane.showMessageDialog(frame,"For Help Or Updated Version Please Visit Us At \n www.linux4us.net","Help",JOptionPane.INFORMATION_MESSAGE);

}
}
////////////////////////////////////////////////////////////
  public MainFrame() {
    enableEvents(AWTEvent.WINDOW_EVENT_MASK);
    try  {
      jbInit();
    }
    catch (Exception e) {
      e.printStackTrace();
    }
  }
  
  public MainFrame(String s){}
//Component initialization

  private void jbInit() throws Exception  {
    UIManager.addPropertyChangeListener(new UISwitchListener((JComponent)getRootPane()));
    font=new Font("TimesNewRoman",Font.BOLD,12);
    jTextArea1.setForeground(Color.red);
    

    
    jTextArea1.setFont(font);
    open = new ImageIcon("open.gif");
    save = new ImageIcon("save.gif");
    help = new ImageIcon("help.gif");
    cut = new ImageIcon("cut.gif");
    copy = new ImageIcon("copy.gif");
    paste = new ImageIcon("paste.gif");
	new1 = new ImageIcon("new.gif");

    
    
    
    
    
    
    
    this.getContentPane().setLayout(borderLayout1);
    this.setSize(new Dimension(400, 300));
    this.setTitle("Java DeCompiler -Developed By Prabhu");
    statusBar.setText(" ");
    jMenu1.setText("File");
    jMenuItem1.setText("Open");
    jMenuItem1.addActionListener(new FileEvent());
    jScrollPane1.setBorder(border);
    jMenuItem3.setText("New");
    jMenuItem4.setText("Save");
    jMenuItem5.setText("Save As");
    jMenuItem6.setText("Exit");
 jMenuItem4.addActionListener(new FileEvent());
 jMenuItem5.addActionListener(new FileEvent());
 jMenuItem6.addActionListener(new FileEvent());
 
 
 
 
    jMenu2.setText("Edit");
    jMenuItem8.setText("Copy");
    jMenuItem9.setText("Cut");
    jMenuItem10.setText("Paste");
    jMenuItem11.setText("Find");
    jMenuItem12.setText("Select All");
 
jMenuItem8.addActionListener(new EditEvent()); 
jMenuItem9.addActionListener(new EditEvent()); 
jMenuItem10.addActionListener(new EditEvent()); 
jMenuItem11.addActionListener(new EditEvent()); 
jMenuItem12.addActionListener(new EditEvent()); 

 
 
 
 
 
    jMenu3.setText("Options");
    jMenu4.setText("Help");
    
    jMenuItem13.setText("Mail Us");
    jMenuItem14.setText("About Us");
    jMenuItem15.setText("Visit Us");
    jMenuItem16.setText("About DCompiler");
    jMenuItem17.setText("Help");
    
    jMenuItem14.setActionCommand("us");
    jMenuItem14.addActionListener(new HelpEvent());

    jMenuItem15.setActionCommand("visit");
    jMenuItem15.addActionListener(new HelpEvent());

    jMenuItem13.setActionCommand("mail");
    jMenuItem13.addActionListener(new HelpEvent());

    jMenuItem17.setActionCommand("help");
    jMenuItem17.addActionListener(new HelpEvent());

    jMenuItem16.setActionCommand("decompiler");
    jMenuItem16.addActionListener(new HelpEvent());

    
    
 
    jMenu5.setText("Recent Files");
    jMenuItem7.setText("File1");
 
    jMenu6.setText("Look & Feel");
    jMenu7.setText("Metal");
    fontMenu.setText("Font");
    
    jMenuItem25.setText("Font");
    
    
    decom.setText("Decompile");
    dis.setText("Dis Assemble");
    obfus.setText("Obfuscate");
decom.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(ActionEvent e) {
      dis.setState(false);
      obfus.setState(false);
      }
    });
dis.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(ActionEvent e) {
        decom.setState(false);
      obfus.setState(false);

      }
    });
obfus.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(ActionEvent e) {
     dis.setState(false);
     decom.setState(false);

      }
    });











    jCheckBoxMenuItem1.setText("Motif");
    jCheckBoxMenuItem3.setSelected(true);
    
    winplaf.addActionListener(new ActionListener(){
    	public void actionPerformed(ActionEvent ae){
    		winplaf_actionPerformed(ae);
    	}});
    jCheckBoxMenuItem1.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(ActionEvent e) {
        jCheckBoxMenuItem1_actionPerformed(e);
      }
    });
    jCheckBoxMenuItem3.setText("Emerald");
    jCheckBoxMenuItem3.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(ActionEvent e) {
        jCheckBoxMenuItem3_actionPerformed(e);
      }
    });
    jCheckBoxMenuItem4.setText("Oxide");
    jCheckBoxMenuItem4.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(ActionEvent e) {
        jCheckBoxMenuItem4_actionPerformed(e);
      }
    });
    jCheckBoxMenuItem5.setText("SandStone");
    jCheckBoxMenuItem5.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(ActionEvent e) {
        jCheckBoxMenuItem5_actionPerformed(e);
      }
    });
    jCheckBoxMenuItem6.setText("Default (Steel)");
    jCheckBoxMenuItem6.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(ActionEvent e) {
        jCheckBoxMenuItem6_actionPerformed(e);
      }
    });

    jCheckBoxMenuItem7.setText("Charcoal");
    jCheckBoxMenuItem7.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(ActionEvent e) {
        jCheckBoxMenuItem7_actionPerformed(e);
      }
    });
    jCheckBoxMenuItem8.setText("Contrast");
    jCheckBoxMenuItem8.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(ActionEvent e) {
        jCheckBoxMenuItem8_actionPerformed(e);
      }
    });
    
    
    
    
    
  jMenuItem25.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(ActionEvent e) {
        setfont_actionPerformed(e);
      }
    });   
    
    jPanel1.setLayout(gridLayout2);
//    jScrollPane1.getViewport().setLayout(gridLayout1);
//jScrollPane1.setLayout(gridLayout1);

    bnew.setIcon(new1);
    bnew.setToolTipText("New File");
    bnew.setActionCommand("New");
    bnew.addActionListener(new FileEvent());
    


    bopen.setIcon(open);
    bopen.setToolTipText("Open File");
    bopen.setActionCommand("Open");
    bopen.addActionListener(new FileEvent());
    
    
    bsave.setIcon(save);
    bsave.setToolTipText("Save File");
    bsave.setActionCommand("Save");
    bsave.addActionListener(new FileEvent());

    
    bhelp.setIcon(help);
    bhelp.setToolTipText("Help");
    bhelp.setActionCommand("help");
    bhelp.addActionListener(new HelpEvent());

    bcut.setIcon(cut);
    bcut.setToolTipText("Cut");
    bcut.setActionCommand("Cut");
    bcut.addActionListener(new EditEvent());

    bcopy.setIcon(copy);
    bcopy.setToolTipText("Copy");
    bcopy.setActionCommand("Copy");
    bcopy.addActionListener(new EditEvent());

    bpaste.setIcon(paste);
    bpaste.setToolTipText("Paste");
    bpaste.setActionCommand("Paste");
    bpaste.addActionListener(new EditEvent());

    
    
    //jButton3.addActionListener(new HelpEvent());
toolBar.add(bnew);    
toolBar.add(bopen);
toolBar.add(bsave);
toolBar.add(bcut);
toolBar.add(bcopy);
toolBar.add(bpaste);
toolBar.add(bhelp);


    
    menuBar1.add(jMenu1);
    menuBar1.add(jMenu2);
    menuBar1.add(jMenu3);
    menuBar1.add(jMenu4);

    
    this.setJMenuBar(menuBar1);
    this.getContentPane().add(toolBar, BorderLayout.NORTH);
    this.getContentPane().add(statusBar, BorderLayout.SOUTH);
    this.getContentPane().add(jScrollPane1, BorderLayout.CENTER);
    JTextArea dummy1=new JTextArea("  ");
    dummy1.setEditable(false);
    dummy1.setBackground(Color.white);
    
    JTextArea dummy2=new JTextArea("  ");
    dummy2.setEditable(false);
    dummy2.setBackground(Color.white);

    jPanel1.setLayout(new BorderLayout());
    jTextArea1.setLineWrap(true);
    jPanel1.add(jTextArea1,BorderLayout.CENTER);
    jPanel1.add(dummy1,BorderLayout.WEST);
    jPanel1.add(dummy2,BorderLayout.EAST);

    jScrollPane1.getViewport().add(jPanel1, new BorderLayout(20,20));
    
    //jScrollPane1.getViewport().add(jTextArea1, BorderLayout.CENTER);
    jMenu1.add(jMenuItem3);
    jMenu1.add(jMenuItem1);
    jMenu1.add(jMenuItem4);
    jMenu1.add(jMenuItem5);
    jMenu1.addSeparator();
    jMenu1.add(jMenu5);
    jMenu1.addSeparator();
    jMenu1.add(jMenuItem6);
    jMenu2.add(jMenuItem9);
    jMenu2.add(jMenuItem8);
    jMenu2.add(jMenuItem10);
    jMenu2.add(jMenuItem11);
    jMenu2.add(jMenuItem12);
    jMenu4.add(jMenuItem14);
    jMenu4.add(jMenuItem16);
    jMenu4.add(jMenuItem15);
    jMenu4.add(jMenuItem13);
    jMenu4.add(jMenuItem17);
   // jMenu5.add(jMenuItem7);
    
    jMenu3.add(jMenu6);
    jMenu3.add(jMenuItem25);
    jMenu3.add(decom);
    jMenu3.add(dis);
    jMenu3.add(obfus);

    
    jMenu6.add(jMenu7);
    jMenu6.add(jCheckBoxMenuItem1);
    jMenu6.add(winplaf);

    jMenu7.add(jCheckBoxMenuItem3);
    jMenu7.add(jCheckBoxMenuItem4);
    jMenu7.add(jCheckBoxMenuItem5);
    jMenu7.add(jCheckBoxMenuItem6);
    jMenu7.add(jCheckBoxMenuItem7);
    jMenu7.add(jCheckBoxMenuItem8);
    
    
    
    jMenu1.setMnemonic('F');
    jMenu2.setMnemonic('E');
    jMenu3.setMnemonic('O');
    jMenu4.setMnemonic('H');
  
  
 
 
  
//    jScrollPane1.setLayout(new BorderLayout());
//, new FlowLayout());
  }
  
  
  //public Insets getInsets(){return new Insets(10,10,10,10);}
  
 //FONT Class///////////////
  
 
 
 /////////////////////////////////////// 
//File | Exit action performed
  public void setfont_actionPerformed(ActionEvent ae){
  		SetFont sfont=new SetFont();
  	sfont.create(this);
  		
  		
  }
  
  
  
  public void updateFont(Font font,Color color)
	{
			jTextArea1.setFont(font);
			jTextArea1.setForeground(color);
  		//System.out.println(jTextArea1.getFont().getName());
  		//new StyledEditorKit.ForegroundAction("set-foreground-color",Color.gray);
	}
  
  public void fileExit_actionPerformed(ActionEvent e) {
    System.exit(0);
  }
//Help | About action performed
  
  public void helpAbout_actionPerformed(ActionEvent e) {
    MainFrame_AboutBox dlg = new MainFrame_AboutBox(this);
    Dimension dlgSize = dlg.getPreferredSize();
    Dimension frmSize = getSize();
    Point loc = getLocation();
    dlg.setLocation((frmSize.width - dlgSize.width) / 2 + loc.x, (frmSize.height - dlgSize.height) / 2 + loc.y);
    dlg.setModal(true);
    dlg.show();
  }
//Overriden so we can exit on System Close
  
  protected void processWindowEvent(WindowEvent e) {
    super.processWindowEvent(e);
    if (e.getID() == WindowEvent.WINDOW_CLOSING) {
      fileExit_actionPerformed(null);
    }
  }

  void jCheckBoxMenuItem3_actionPerformed(ActionEvent e) {
    try{
  jCheckBoxMenuItem1.setSelected(false);
  jCheckBoxMenuItem4.setSelected(false);
  jCheckBoxMenuItem5.setSelected(false);
  jCheckBoxMenuItem6.setSelected(false);
  jCheckBoxMenuItem7.setSelected(false);
  jCheckBoxMenuItem8.setSelected(false);

      MetalTheme theme=new GreenMetalTheme();
      MetalLookAndFeel.setCurrentTheme(theme);
      UIManager.setLookAndFeel(new MetalLookAndFeel());

    }
    catch(Exception e1){
    System.out.println(e1);
    }
  }


  public class UISwitchListener implements PropertyChangeListener{
    JComponent componentToSwitch;

    public UISwitchListener(JComponent c) {
        componentToSwitch = c;
    }

    public void propertyChange(PropertyChangeEvent e) {
        String name = e.getPropertyName();
	if (name.equals("lookAndFeel")) {
	    SwingUtilities.updateComponentTreeUI(componentToSwitch);
	    componentToSwitch.invalidate();
	    componentToSwitch.validate();
	    componentToSwitch.repaint();
	}
    }
}

  void jCheckBoxMenuItem4_actionPerformed(ActionEvent e) {
 try{
   jCheckBoxMenuItem1.setSelected(false);
   jCheckBoxMenuItem3.setSelected(false);
  jCheckBoxMenuItem5.setSelected(false);
  jCheckBoxMenuItem6.setSelected(false);
  jCheckBoxMenuItem7.setSelected(false);
  jCheckBoxMenuItem8.setSelected(false);

 MetalTheme theme=new AquaMetalTheme();
 MetalLookAndFeel.setCurrentTheme(theme);
 UIManager.setLookAndFeel(new MetalLookAndFeel());
 }
 catch(Exception e2)
 {
 }
  }

  void jCheckBoxMenuItem5_actionPerformed(ActionEvent e) {
   try{
     jCheckBoxMenuItem1.setSelected(false);
     jCheckBoxMenuItem3.setSelected(false);
  jCheckBoxMenuItem4.setSelected(false);
  jCheckBoxMenuItem6.setSelected(false);
  jCheckBoxMenuItem7.setSelected(false);
  jCheckBoxMenuItem8.setSelected(false);

   MetalTheme theme=new KhakiMetalTheme();
    MetalLookAndFeel.setCurrentTheme(theme);
 UIManager.setLookAndFeel(new MetalLookAndFeel());

   }
   catch(Exception e3)
   {
   	System.out.println(e3);
   }
  }

  void jCheckBoxMenuItem7_actionPerformed(ActionEvent e) {
  try{
    jCheckBoxMenuItem1.setSelected(false);
    jCheckBoxMenuItem3.setSelected(false);
  jCheckBoxMenuItem4.setSelected(false);
  jCheckBoxMenuItem5.setSelected(false);
  jCheckBoxMenuItem6.setSelected(false);
  jCheckBoxMenuItem8.setSelected(false);

MetalTheme theme=new PropertiesMetalTheme(new FileInputStream("MyTheme.theme"));
  MetalLookAndFeel.setCurrentTheme(theme);
 UIManager.setLookAndFeel(new MetalLookAndFeel());
  }
  catch(Exception e4){}
  }

  void jCheckBoxMenuItem8_actionPerformed(ActionEvent e) {
  try{
    jCheckBoxMenuItem1.setSelected(false);
   jCheckBoxMenuItem3.setSelected(false);
  jCheckBoxMenuItem4.setSelected(false);
  jCheckBoxMenuItem5.setSelected(false);
  jCheckBoxMenuItem6.setSelected(false);
  jCheckBoxMenuItem7.setSelected(false);


 MetalTheme theme=new ContrastMetalTheme();
  MetalLookAndFeel.setCurrentTheme(theme);
 UIManager.setLookAndFeel(new MetalLookAndFeel());
 }catch(Exception e5){}
  }

  void jCheckBoxMenuItem6_actionPerformed(ActionEvent e) {
    try{
      jCheckBoxMenuItem1.setSelected(false);
  jCheckBoxMenuItem3.setSelected(false);
  jCheckBoxMenuItem4.setSelected(false);
  jCheckBoxMenuItem5.setSelected(false);
  jCheckBoxMenuItem7.setSelected(false);
  jCheckBoxMenuItem8.setSelected(false);

 MetalLookAndFeel.setCurrentTheme(new DefaultMetalTheme());
 UIManager.setLookAndFeel(new MetalLookAndFeel());

    }catch(Exception e6){}
  }

  void jCheckBoxMenuItem1_actionPerformed(ActionEvent e) {
  try{
    //jCheckBoxMenuItem1.setSelected(false);
  jCheckBoxMenuItem3.setSelected(false);
  jCheckBoxMenuItem4.setSelected(false);
  jCheckBoxMenuItem5.setSelected(false);
  jCheckBoxMenuItem6.setSelected(false);
  jCheckBoxMenuItem7.setSelected(false);
  jCheckBoxMenuItem8.setSelected(false);
  winplaf.setSelected(false);
  UIManager.setLookAndFeel(new com.sun.java.swing.plaf.motif.MotifLookAndFeel());
  }catch(Exception e7){}

  }


 void winplaf_actionPerformed(ActionEvent e) {
  try{
  jCheckBoxMenuItem1.setSelected(false);
  jCheckBoxMenuItem3.setSelected(false);
  jCheckBoxMenuItem4.setSelected(false);
  jCheckBoxMenuItem5.setSelected(false);
  jCheckBoxMenuItem6.setSelected(false);
  jCheckBoxMenuItem7.setSelected(false);
  jCheckBoxMenuItem8.setSelected(false);
  UIManager.setLookAndFeel(new com.sun.java.swing.plaf.windows.WindowsLookAndFeel());
  }catch(Exception e7){}

  }










  /*void jMenuItem1_actionPerformed(ActionEvent e) {
  

  }*/
  
  
public void display(File f)
	{
		try{
FileInputStream in=new FileInputStream(f);
char c[]=new char[in.available()];
int x1,x2=0;
while(true)
  	{
  	x1=in.read();
  	if(x1==-1)
  		break;
  	c[x2]=(char)x1;
  	x2++;
  	}	
 String str=new String(c);
// System.out.println(str);
 jTextArea1.append(str);
		}catch(Exception e)
		{
			System.out.println(e);
		}
	}  
 
}