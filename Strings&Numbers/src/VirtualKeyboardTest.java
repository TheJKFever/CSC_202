
// VirtualKeyboardTest.java 
import javax.swing.JFrame;

public class VirtualKeyboardTest
{
    public static void main(String[] args)
    {
        VirtualKeyboard typingTutor = new VirtualKeyboard();    // creates TypingTutor
        typingTutor.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        typingTutor.setSize(700, 500);      // set dimensions of window
        typingTutor.setVisible(true);
        JFrame frame = new JFrame();



    }   // end main
}   // end class TypingTutorTest