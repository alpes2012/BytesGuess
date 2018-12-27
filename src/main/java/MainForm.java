import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;

public class MainForm {
    private JPanel panel1;
    private JTextField Cipher1;
    private JTextField Plain1;
    private JTextField Cipher2;
    private JTextField Plain2;
    private JTextField Cipher3;
    private JTextField Plain3;
    private JTextField Cipher4;
    private JTextField Plain4;

    private TextFiledListener listener1 = new TextFiledListener(this, 1);
    private TextFiledListener listener2 = new TextFiledListener(this, 2);
    private TextFiledListener listener3 = new TextFiledListener(this, 3);
    private TextFiledListener listener4 = new TextFiledListener(this, 4);

    char chPlain[][] = new char[4][];
    char chCipher[][] = new char[4][];
    char chKey[] = new char[1024];
    char iKeyLength = 0;

    public MainForm() throws  Exception {
        /* 读入TXT文件 */
        String pathName = "D:\\PlainText.txt";
        File fileName = new File(pathName);
        InputStreamReader reader = new InputStreamReader(new FileInputStream(fileName));
        BufferedReader br = new BufferedReader(reader);

        for (int i = 0; i < 4; i++) {
            String strLine = br.readLine();
            chPlain[i] = strLine.toCharArray();
            chCipher[i] = new char[chPlain[i].length];
            for (int j = 0; j < strLine.length(); j++) {
                chCipher[i][j] = (char)(chPlain[i][j] ^ 0xffffffff);
            }
        }

        UpdateCipherText();

        //注册listener
        Plain1.getDocument().addDocumentListener(listener1);
        Plain2.getDocument().addDocumentListener(listener2);
        Plain3.getDocument().addDocumentListener(listener3);
        Plain4.getDocument().addDocumentListener(listener4);
    }

    public JPanel getPanel1() {
        return panel1;
    }

    public JTextField getCipher1() {
        return Cipher1;
    }

    public JTextField getPlain1() {
        return Plain1;
    }

    public JTextField getCipher2() {
        return Cipher2;
    }

    public JTextField getPlain2() {
        return Plain2;
    }

    public JTextField getCipher3() {
        return Cipher3;
    }

    public JTextField getPlain3() {
        return Plain3;
    }

    public JTextField getCipher4() {
        return Cipher4;
    }

    public JTextField getPlain4() {
        return Plain4;
    }

    public TextFiledListener getListener1() {
        return listener1;
    }

    public TextFiledListener getListener2() {
        return listener2;
    }

    public TextFiledListener getListener3() {
        return listener3;
    }

    public TextFiledListener getListener4() {
        return listener4;
    }

    public char[][] getChCipher() {
        return chCipher;
    }

    private void UpdateCipherText() {
        Cipher1.setText(String.valueOf(chCipher[0]));
        Cipher2.setText(String.valueOf(chCipher[1]));
        Cipher3.setText(String.valueOf(chCipher[2]));
        Cipher4.setText(String.valueOf(chCipher[3]));
    }

    public static void main(String[] args) {
        try {
            JFrame frame = new JFrame("演示程序");
            MainForm window = new MainForm();
            frame.setContentPane(window.panel1);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setLocationRelativeTo(null);
            frame.setResizable(false);
            frame.pack();
            frame.setVisible(true);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}
