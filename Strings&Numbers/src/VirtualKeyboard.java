import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JTextArea;
import javax.swing.JTextField;

// VirtualKeyboard.java
public class VirtualKeyboard extends JFrame implements KeyListener {

	private static final long serialVersionUID = 1L;
	private JButton[] keyB = new JButton[100]; 
    private JTextArea textField;
    private char keyText;
    private JLabel label1;
    private JLabel label2;
    private Object[] keys;
    int keycode;
    private final char[] normalKey = {'1', 	'2', 	'3', 	'4', 	'5', 	'6', 	'7', 	'8', 	'9', 	'0', 	'-', 	'=', 	'q', 	'w', 	'e', 	'r', 	't', 	'y', 	'u', 	'i', 	'o', 	'p', 	'[', 	']', 	'\\', 	'a', 	's', 	'd', 	'f', 	'g', 	'h', 	'j', 	'k', 	'l', 	';', 	'\'', 	'z', 	'x', 	'c', 	'v', 	'b', 	'n', 	'm', 	',', 	'.', 	'/'};
    private final char[] shiftKey = {'!', 	'@', 	'#', 	'$', 	'%', 	'^', 	'&', 	'*', 	'(', 	')', 	'_', 	'+', 	'Q', 	'W', 	'E', 	'R', 	'T', 	'Y', 	'U', 	'I', 	'O', 	'P', 	'{', 	'}', 	'|', 	'A', 	'S', 	'D', 	'F', 	'G', 	'H', 	'J', 	'K', 	'L', 	':', 	'\"', 	'Z', 	'X', 	'C', 	'V', 	'B', 	'N', 	'M', 	'<', 	'>', 	'?'};
//    private final String[] specialKey = {'Backspace', 'Tab', 'Caps', 'Enter', 'Shift', 'Space'};
    

    public VirtualKeyboard() {
        super("Keyboard");
        setLayout( new FlowLayout() );
        label1 = new JLabel("Type some text using your keyboard.");
        add(label1);
        label2 = new JLabel("Note: clicking the buttons with your mouse will not perform any action");
        add(label2);

        textField = new JTextArea(5,60);
        textField.setEditable(true);
        add( textField , BorderLayout.NORTH);

//        TextFieldHandler handler = new TextFieldHandler();
   
        
        for (int i=0;i<=45;i++){
        	keyB[i] = new JButton();
        }
        
        Backspace = new JButton( "Backspace" );
        add(Backspace);
        Tab = new JButton( "Tab" );
        add(Tab);
        Q = new JButton( "Q" );
        add(Q);
        W = new JButton( "W" );
        add(W);
        E = new JButton( "E" );
        add(E);
        R = new JButton( "R" );
        add(R);
        T = new JButton( "T" );
        add(T);
        Y = new JButton( "Y" );
        add(Y);
        U = new JButton( "U" );
        add(U);
        I = new JButton( "I" );
        add(I);
        O = new JButton( "O" );
        add(O);
        P = new JButton( "P" );
        add(P);

        Caps = new JButton ( "Caps ");
        add(Caps);
        A = new JButton( "A" );
        add(A);
        S = new JButton( "S" );
        add(S);
        D = new JButton( "D" );
        add(D);
        F = new JButton( "F" );
        add(F);
        G = new JButton( "G" );
        add(G);
        H = new JButton( "H" );
        add(H);
        J = new JButton( "J" );
        add(J);
        K = new JButton( "K" );
        add(K);
        L = new JButton( "L" );
        add(L);

        Shift = new JButton ( "Shift" );
        add(Shift);
        Z = new JButton( "Z" );
        add(Z);
        X = new JButton( "X" );
        add(X);
        C = new JButton( "C" );
        add(C);
        V = new JButton( "V" );
        add(V);
        B = new JButton( "B" );
        add(B);
        N = new JButton( "N" );
        add(N);
        M = new JButton( "M" );
        add(M);
    }

      // overridden keyPressed method handles press of any key
    public void keyPressed( KeyEvent event )
    {
//        keycode = event.getKeyCode();
//        keyText = String.format( "%s",KeyEvent.getKeyText( event.getKeyCode() ) );
//        setBackground(Color.PINK);
    }

    // overridden keyReleased method handles press of any key
    public void keyReleased( KeyEvent event )
    {
//        keycode = event.getKeyCode();
//        keyText = String.format( "%s",KeyEvent.getKeyText( event.getKeyCode() ) );
//        getBackground();
    }
    public void keyTyped( KeyEvent event )
    {
        keyText = event.getKeyChar();
    }

	class KeyButton extends JButton implements ActionListener {
    // constructor
		public char normal, shift;
		KeyButton(char normal, char shift) {
			super();
			this.normal = normal;
			this.shift = shift;
			addActionListener(this);
       }
       // button was hit
       public void actionPerformed(ActionEvent e) {
    	   textField.append(getText());    // append to field my label
       }
    }



   private class TextFieldHandler implements ActionListener
   {
      // process textfield events
      public void actionPerformed( ActionEvent event )
      {
         String string = ""; // declare string to display

         // user pressed Enter in JTextField textField1
         if ( event.getSource() == textField )
            string = String.format( "%s", event.getActionCommand() );
       }
    }
   

}