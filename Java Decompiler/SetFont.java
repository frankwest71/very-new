
//Title:        Font Dialog
//Version:      
//Copyright:    Copyright (c) 2002
//Author:       Prabhu
//Company:      
//Description:  



import java.awt.*;
import borland.jbcl.layout.*;
import javax.swing.border.*;
import java.awt.event.*;
import javax.swing.event.*;
import javax.swing.*;
import java.util.Vector;

public class SetFont extends JFrame {
	
	
	Font font;
	Color color=new Color(10,10,10);
Vector v1=new Vector();
Vector v2=new Vector();
Vector v3=new Vector();

  Border border=new BevelBorder(1);
  JPanel panel=new JPanel();
  
  
  
  
  
  
  XYLayout xYLayout1 = new XYLayout();
  JList jList1;
  JLabel jLabel1 = new JLabel();
  JTextField jTextField1 = new JTextField();
  JLabel jLabel2 = new JLabel();
  JTextField jTextField2 = new JTextField();
  JList jList2;
  JLabel jLabel3 = new JLabel();
  JTextField jTextField3 = new JTextField();
  JList jList3;
  JLabel jLabel4 = new JLabel();
  
  JTextArea jLabel5 = new JTextArea();
  
  
  JButton jButton1 = new JButton();
  JButton jButton2 = new JButton();
  JButton jButton3 = new JButton();
JScrollPane scroll1,scroll2,scroll3;
  MainFrame main;
  
  
  ///////////////CLASS FOR LIST EVENT LISTENER/////////
  class ListEvent implements ListSelectionListener{
  	public void valueChanged(ListSelectionEvent ie)
  	{
  	jTextField1.setText((String)jList1.getSelectedValue());
    jTextField2.setText((String)jList2.getSelectedValue());
    jTextField3.setText((String)jList3.getSelectedValue());

  	}
  }
  
  ///////////////////////////////////////////////////////
  
class ACT implements ActionListener{
	public void actionPerformed(ActionEvent ae){
		String cmd=ae.getActionCommand();
		if(cmd.equals("Ok"))
		{
			try{
				
			//font.setName(jTextField1.getText());
			//font.setSize(9);
			font=new Font(jTextField1.getText(),jList2.getSelectedIndex(),Integer.parseInt(jTextField3.getText()));
			}catch(Exception e){
				font=main.jTextArea1.getFont();
			}
			if(font==null)
	font=main.jTextArea1.getFont();


			main.updateFont(font,color);
			dispose();
		}
		else
		{
			dispose();
		}
	}
}  
  
  
  
  
  
  
  
  ///////////////////////////////////////////////////////

  
  public SetFont() {
    
  }
  
  
  
  
  public void create(MainFrame main1)
	{
		try  {
			main=main1;
      jbInit();
    }
    catch (Exception e) {
      e.printStackTrace();
    }
	}

public void getAllFonts(){
	Font fontlist[]=GraphicsEnvironment.getLocalGraphicsEnvironment().getAllFonts();
	for(int i=0;i<fontlist.length;i++)
	{
		v2.addElement(fontlist[i].getFontName());
	}
}
  private void jbInit() throws Exception {
  	
  	panel.setLayout(xYLayout1);
  	getAllFonts();
v1.addElement("Bold");
v1.addElement("Plain");
v1.addElement("Italic");

for(int i=10;i<40;i++)
v3.addElement(Integer.toString(i));

jList1=new JList(v2);
jList1.setVisibleRowCount(5);

jList2=new JList(v1);
jList3=new JList(v3);

scroll1=new JScrollPane(jList1);
scroll2=new JScrollPane(jList2);
scroll3=new JScrollPane(jList3);
  	
jList1.addListSelectionListener(new ListEvent());
jList2.addListSelectionListener(new ListEvent());
jList3.addListSelectionListener(new ListEvent());
//jList1.addItemListener(new ListEvent());

  	
    //this.getContentPane().setLayout(xYLayout1);
    xYLayout1.setHeight(322);
    this.setTitle("Font Chooser");
    xYLayout1.setWidth(400);
    jList1.setBorder(border);
    jLabel1.setText("Font");
    jLabel1.setFont(new Font("SansSerif", 0, 14));
    jLabel2.setText("Font Style");
    jLabel2.setFont(new Font("SansSerif", 0, 14));
    jList2.setBorder(border);
    jLabel3.setText("Size");
    jLabel3.setFont(new Font("SansSerif", 0, 14));
    jList3.setBorder(border);
    jLabel4.setText("Font Color");
    jLabel4.setFont(new Font("SansSerif", 0, 14));
    jButton1.setText("Choose Color");
    jButton1.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(ActionEvent e) {
        jButton1_actionPerformed(e);
      }
    });
    jButton2.setText("Ok");
    jButton3.setText("Cancel");
    
    jButton2.addActionListener(new ACT());
    jButton3.addActionListener(new ACT());
    
    jLabel5.setEditable(false);


		jTextField1.setText(main.jTextArea1.getFont().getName());
	jTextField2.setText(Integer.toString(main.jTextArea1.getFont().getStyle()));
	jTextField3.setText(Integer.toString(main.jTextArea1.getFont().getSize()));

    
    this.getContentPane().setLayout(new BorderLayout());
    
    

    
   
    panel.add(scroll1, new XYConstraints(18, 72, 170, 120));
    panel.add(jLabel1, new XYConstraints(18, 25, -1, -1));
    panel.add(jTextField1, new XYConstraints(18, 47, 140, -1));
    
    panel.add(jLabel2, new XYConstraints(189, 25, -1, -1));
    panel.add(jTextField2, new XYConstraints(196, 47, 86, -1));
    panel.add(scroll2, new XYConstraints(196, 72, 94, 120));

    panel.add(jLabel3, new XYConstraints(312, 25, 37, -1));
    panel.add(jTextField3, new XYConstraints(314, 47, 63, -1));
    panel.add(scroll3, new XYConstraints(313, 72, 62, 120));

    panel.add(jLabel4, new XYConstraints(32, 218, -1, -1));
    panel.add(jLabel5, new XYConstraints(113, 214, 28, 24));
    panel.add(jButton1, new XYConstraints(156, 215, -1, -1));
    panel.add(jButton2, new XYConstraints(122, 256, -1, -1));
    panel.add(jButton3, new XYConstraints(273, 256, -1, -1));
    
      
   
this.getContentPane().add(panel);//, new XYConstraints(19, 72, 115, 120));

    this.setVisible(true);
    this.pack();
  }

  void jButton1_actionPerformed(ActionEvent e) {
  	MyColor color=new MyColor();
  }

class MyColor implements ActionListener{
	JFrame frame;
	//Color color;
	JColorChooser jcc;
	
	public MyColor(){
     frame=new JFrame("Color Chooser");
    JPanel panel=new JPanel();
    jcc=new JColorChooser();
    JButton ok=new JButton("Ok");
    JButton cancel=new JButton("Cancel");

    
    
    panel.add(jcc,"Center");
    panel.add(ok);
    panel.add(cancel);
    
    ok.addActionListener(this);
    cancel.addActionListener(this);
        
    frame.getContentPane().add(panel);
    frame.setVisible(true);
    frame.pack();


  }
  public void actionPerformed(ActionEvent ae){
  	String cmd=ae.getActionCommand();
  	if(cmd.equals("Ok"))
  	{
  		frame.dispose();
  		color=jcc.getColor();
  		jLabel5.setBackground(color);
  		//jLabel5.setText("asdasd");
  		
  	}
  	else
  	{
  	}
  }
}

}                  