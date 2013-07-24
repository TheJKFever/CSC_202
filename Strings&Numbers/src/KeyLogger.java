import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

class KeyLogger extends JFrame implements KeyListener {
    private JTextField textField = new JTextField(60);
    private JLabel label1;
    private int i=0;
    
    public KeyLogger(char[] c) {
        super("KeyLogger");
        c = new char[200];
        setLayout( new FlowLayout() );
        label1 = new JLabel("Type in the StringLog that you would like, then hit enter.");
        add(label1);    
    	this.setPreferredSize(new Dimension(500, 50));
        addKeyListener(this);
        textField.setEditable(false);
        add(textField, BorderLayout.NORTH);
        TextFieldHandler handler = new TextFieldHandler();
 
        
    }

    public void addNotify() {
        super.addNotify();
        requestFocus();
    }

    public void keyPressed(KeyEvent e) { }
    public void keyReleased(KeyEvent e) { }
    public void keyTyped(KeyEvent e) {
    		System.out.println(""+e.getKeyCode());
        c[i] = e.getKeyChar();
        i++;
        repaint();
    }
    
    private class TextFieldHandler implements ActionListener      {
          // process textfield events
          public void actionPerformed( ActionEvent event )
          {
             String string = ""; // declare string to display
     
             // user pressed Enter in JTextField textField1
             if ( event.getSource()==textField )
                string = String.format( "%s", event.getActionCommand() );
           }
        }

    

    public static void main(String[] s) {
        KeyLogger getStringLog = new KeyLogger();
        getStringLog.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getStringLog.setSize(700, 200);      // set dimensions of window
        getStringLog.setVisible(true);
    }
}