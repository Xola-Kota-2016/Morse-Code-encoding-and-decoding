
/* 
 * Xola Kota
 * email: xkota45@gmail.com
 * Morse Code (encoding and decoding)
 */

package morse;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JFileChooser;

/*  
    import javax.swing.JFrame; to produce a GUI that encodes and decodes 
    text using the Morse Code.
*/
import javax.swing.JFrame;  
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.LayoutStyle;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

/*
    MorseUtils class provides static utility fields and methods for encoding and 
    decoding text using the Morse Code.
*/
class MorseUtils {
    
    
/*
  Messages are composed only of the chars in the MorseUtils.MORSE_ALPHABET array. 
  Messages are encoded using only the strings in the MorseUtils.MORSE_CODE array. 
  All plain text messages have no spaces between chars and a single space char 
  between words. All encoded Morse messages have three space chars separating
  each word in the message and a single space char separating each Morse 
  Code symbol.

*/  
 //The array of Morse Codes used to encode and decode messages.
  public static final String[] MORSE_CODE = new String[] { 
      ".-", "-...", "-.-.", "-..", ".", "..-.", "--.", "....", "..", ".---", 
      "-.-", ".-..", "--", "-.", "---", ".--.", "--.-", ".-.", "...", "-", 
      "..-", "...-", ".--", "-..-", "-.--", "--..", ".----", "..---", "...--", "....-", 
      ".....", "-....", "--...", "---..", "----.", "-----" };
  //The array of chars that are represented by the codes in the Morse Code alphabet.
  public static final char[] MORSE_ALPHABET = new char[] { 
      'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 
      'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 
      'U', 'V', 'W', 'X', 'Y', 'Z', '0', '1', '2', '3', 
      '4', '5', '6', '7', '8', '9' };
  //Encodes a message using the MorseUtils.MORSE_CODE array.
  public static String encode(String message) {
    StringBuilder encoded = new StringBuilder();
    for (int i = 0; i < message.length(); i++) {
      for (int j = 0; j < MORSE_ALPHABET.length; j++) {
        if (message.charAt(i) == ' ' || message.charAt(i) == '\n') {
          encoded.append("   ");
          break;
        } 
        if (message.charAt(i) == MORSE_ALPHABET[j]) {
          encoded.append(MORSE_CODE[j]);
          encoded.append(" ");
          break;
        } 
      } 
    } 
    String txt = encoded.toString();
    return txt.substring(0, txt.length() - 1);
  }
  //Decodes a message using the MorseUtils.MORSE_ALPHABET array.
  public static String decode(String message) {
    StringBuilder decoded = new StringBuilder();
    String[] words = message.split("   ");
    for (int i = 0; i < words.length; i++) {
      String[] codes = words[i].split(" ");
      for (int k = 0; k < codes.length; k++) {
        for (int j = 0; j < MORSE_CODE.length; j++) {
          if (codes[k].equals(MORSE_CODE[j])) {
            decoded.append(MORSE_ALPHABET[j]);
            break;
          } 
        } 
      } 
      decoded.append(" ");
    } 
    String txt = decoded.toString();
    return txt.substring(0, txt.length() - 1);
  }
}


public class Morse extends JFrame {
  private final JFileChooser jfc;
  
  private File f;
  
  private FileReader fr;
  
  private BufferedReader buffer;
  
  private FileWriter fw;
  
  private PrintWriter writer;
  
  private JButton decodeButton;
  
  private JButton encodeButton;
  
  private JButton exitButton;
  
  private JMenuItem exitItem;
  
  private JButton jButton1;
  
  private JMenu jMenu1;
  
  private JMenuBar jMenuBar1;
  
  private JScrollPane jScrollPane1;
  
  private JTextArea jTextArea1;
  
  private JMenuItem openFile;
  
  private JMenuItem saveFile;
  //Creates new form Morse object
  public Morse() {
    initComponents();
    this.jfc = new JFileChooser();
    this.jfc.setCurrentDirectory(new File("\\"));
  }
  
  private void initComponents() {
    this.jScrollPane1 = new JScrollPane();
    this.jTextArea1 = new JTextArea();
    this.encodeButton = new JButton();
    this.decodeButton = new JButton();
    this.exitButton = new JButton();
    this.jButton1 = new JButton();
    this.jMenuBar1 = new JMenuBar();
    this.jMenu1 = new JMenu();
    this.openFile = new JMenuItem();
    this.saveFile = new JMenuItem();
    this.exitItem = new JMenuItem();
    setDefaultCloseOperation(3);
    setTitle("Morse Code GUI");
    setLocation(new Point(600, 250));
    this.jTextArea1.setColumns(20);
    this.jTextArea1.setFont(new Font("Monospaced", 0, 14));
    this.jTextArea1.setLineWrap(true);
    this.jTextArea1.setRows(5);
    this.jTextArea1.setWrapStyleWord(true);
    this.jScrollPane1.setViewportView(this.jTextArea1);
    this.encodeButton.setFont(new Font("Tahoma", 0, 12));
    this.encodeButton.setText("Encode");
    this.encodeButton.addActionListener(new ActionListener() {
          public void actionPerformed(ActionEvent evt) {
            Morse.this.encodeButtonActionPerformed(evt);
          }
        });
    this.decodeButton.setFont(new Font("Tahoma", 0, 12));
    this.decodeButton.setText("Decode");
    this.decodeButton.addActionListener(new ActionListener() {
          public void actionPerformed(ActionEvent evt) {
            Morse.this.decodeButtonActionPerformed(evt);
          }
        });
    this.exitButton.setFont(new Font("Tahoma", 0, 12));
    this.exitButton.setText("Exit");
    this.exitButton.addActionListener(new ActionListener() {
          public void actionPerformed(ActionEvent evt) {
            Morse.this.exitButtonActionPerformed(evt);
          }
        });
    this.jButton1.setFont(new Font("Tahoma", 0, 12));
    this.jButton1.setText("Clear Text");
    this.jButton1.addActionListener(new ActionListener() {
          public void actionPerformed(ActionEvent evt) {
            Morse.this.jButton1ActionPerformed(evt);
          }
        });
    this.jMenu1.setMnemonic('F');
    this.jMenu1.setText("File");
    this.openFile.setMnemonic('O');
    this.openFile.setText("Open file...");
    this.openFile.addActionListener(new ActionListener() {
          public void actionPerformed(ActionEvent evt) {
            Morse.this.openFileActionPerformed(evt);
          }
        });
    this.jMenu1.add(this.openFile);
    this.saveFile.setMnemonic('v');
    this.saveFile.setText("Save file...");
    this.saveFile.addActionListener(new ActionListener() {
          public void actionPerformed(ActionEvent evt) {
            Morse.this.saveFileActionPerformed(evt);
          }
        });
    this.jMenu1.add(this.saveFile);
    this.exitItem.setMnemonic('x');
    this.exitItem.setText("Exit");
    this.exitItem.addActionListener(new ActionListener() {
          public void actionPerformed(ActionEvent evt) {
            Morse.this.exitItemActionPerformed(evt);
          }
        });
    this.jMenu1.add(this.exitItem);
    this.jMenuBar1.add(this.jMenu1);
    setJMenuBar(this.jMenuBar1);
    GroupLayout layout = new GroupLayout(getContentPane());
    getContentPane().setLayout(layout);
    layout.setHorizontalGroup(layout
        .createParallelGroup(GroupLayout.Alignment.LEADING)
        .addGroup(layout.createSequentialGroup()
          .addContainerGap()
          .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addComponent(this.jScrollPane1)
            .addGroup(layout.createSequentialGroup()
              .addComponent(this.encodeButton)
              .addGap(18, 18, 18)
              .addComponent(this.decodeButton)
              .addGap(18, 18, 18)
              .addComponent(this.jButton1)
              .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 124, 32767)
              .addComponent(this.exitButton)))
          .addContainerGap()));
    layout.setVerticalGroup(layout
        .createParallelGroup(GroupLayout.Alignment.LEADING)
        .addGroup(layout.createSequentialGroup()
          .addContainerGap()
          .addComponent(this.jScrollPane1, -2, 189, -2)
          .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
          .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
            .addComponent(this.encodeButton)
            .addComponent(this.decodeButton)
            .addComponent(this.exitButton)
            .addComponent(this.jButton1))
          .addContainerGap(-1, 32767)));
    pack();
  }
  //Menu button actions
  private void encodeButtonActionPerformed(ActionEvent evt) {
    String txt = this.jTextArea1.getText().toUpperCase();
    this.jTextArea1.setText(MorseUtils.encode(txt));
  }
  
  private void exitButtonActionPerformed(ActionEvent evt) {
    System.exit(0);
  }
  
  private void decodeButtonActionPerformed(ActionEvent evt) {
    String txt = this.jTextArea1.getText();
    this.jTextArea1.setText(MorseUtils.decode(txt));
  }
  
  private void exitItemActionPerformed(ActionEvent evt) {
    System.exit(0);
  }
  
  private void openFileActionPerformed(ActionEvent evt) {
    int returnVal = this.jfc.showOpenDialog(this.openFile);
    StringBuffer sb = new StringBuffer();
    try {
      if (returnVal == 0) {
        this.jTextArea1.setText("");
        this.f = this.jfc.getSelectedFile();
        this.fr = new FileReader(this.f);
        this.buffer = new BufferedReader(this.fr);
        String line;
        while ((line = this.buffer.readLine()) != null) {
          sb.append(line);
          sb.append('\n');
        } 
        this.jTextArea1.setText(sb.toString());
        this.fr.close();
        this.buffer.close();
        this.jTextArea1.setCaretPosition(0);
      } else if (returnVal == 1) {
      
      } 
    } catch (IOException e) {
      JOptionPane.showMessageDialog(this, e
          .getMessage(), "ERROR!", 0);
    } 
  }
  
  private void saveFileActionPerformed(ActionEvent evt) {
    String fileText = this.jTextArea1.getText();
    try {
      int returnVal = this.jfc.showSaveDialog(this.jMenuBar1);
      if (returnVal == 0) {
        this.fw = new FileWriter(this.jfc.getSelectedFile());
        this.writer = new PrintWriter(this.fw);
        this.writer.print(fileText);
        this.fw.close();
        this.writer.close();
      } else if (returnVal == 1) {
      
      } 
    } catch (IOException e) {
      JOptionPane.showMessageDialog(this, e
          .getMessage(), "ERROR!", 0);
    } 
  }
  
  private void jButton1ActionPerformed(ActionEvent evt) {
    this.jTextArea1.setText("");
  }
  
  //Programm main method
  public static void main(String[] args) {
    try {
      UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
    } catch (ClassNotFoundException ex) {
      Logger.getLogger(Morse.class.getName()).log(Level.SEVERE, (String)null, ex);
    } catch (InstantiationException ex) {
      Logger.getLogger(Morse.class.getName()).log(Level.SEVERE, (String)null, ex);
    } catch (IllegalAccessException ex) {
      Logger.getLogger(Morse.class.getName()).log(Level.SEVERE, (String)null, ex);
    } catch (UnsupportedLookAndFeelException ex) {
      Logger.getLogger(Morse.class.getName()).log(Level.SEVERE, (String)null, ex);
    } 
    EventQueue.invokeLater(new Runnable() {
          public void run() {
            (new Morse()).setVisible(true);
          }
        });
  }
}