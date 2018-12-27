import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TextFiledListener implements DocumentListener {

    private MainForm mainForm;

    private int plainNum = 0;

    char chPlain[][] = new char[4][];
    char chCipher[][] = new char[4][];
    char chKey[];
    int iKeyLength = -1;

    public TextFiledListener(MainForm mainForm, int plainNum) {
        this.mainForm = mainForm;
        this.plainNum = plainNum - 1;
    }

    public void insertUpdate(DocumentEvent e) {
        UpdateText();
    }

    public void removeUpdate(DocumentEvent e) {
        UpdateText();
    }

    public void changedUpdate(DocumentEvent e) {
        UpdateText();
    }

    private void UpdateText() {
        GetTextFromWindow();

        iKeyLength = chPlain[plainNum].length;
        chKey = new char[iKeyLength];

        for (int i = 0; i < iKeyLength; i++) {
            chKey[i] = (char)(chPlain[plainNum][i] ^ chCipher[plainNum][i]);
        }

        UpdatePlainText();


    }

    private void UpdatePlainText() {



        Runnable doUpdate = new Runnable() {
            public void run() {
                for (int i = 0; i < 4; i++) {
                    chPlain[i] = new char[iKeyLength];
                    for (int j = 0; j < iKeyLength; j++) {
                        chPlain[i][j] = (char)(chKey[j] ^ chCipher[i][j]);
                    }
                }

                mainForm.getPlain1().getDocument().removeDocumentListener(mainForm.getListener1());
                mainForm.getPlain2().getDocument().removeDocumentListener(mainForm.getListener2());
                mainForm.getPlain3().getDocument().removeDocumentListener(mainForm.getListener3());
                mainForm.getPlain4().getDocument().removeDocumentListener(mainForm.getListener4());

                mainForm.getPlain1().setText(String.valueOf(chPlain[0]));
                mainForm.getPlain2().setText(String.valueOf(chPlain[1]));
                mainForm.getPlain3().setText(String.valueOf(chPlain[2]));
                mainForm.getPlain4().setText(String.valueOf(chPlain[3]));

                mainForm.getPlain1().getDocument().addDocumentListener(mainForm.getListener1());
                mainForm.getPlain2().getDocument().addDocumentListener(mainForm.getListener2());
                mainForm.getPlain3().getDocument().addDocumentListener(mainForm.getListener3());
                mainForm.getPlain4().getDocument().addDocumentListener(mainForm.getListener4());
            }
        };

        SwingUtilities.invokeLater(doUpdate);


    }

    private void GetTextFromWindow() {
        chCipher = mainForm.getChCipher();

        chPlain[0] = mainForm.getPlain1().getText().toCharArray();
        chPlain[1] = mainForm.getPlain2().getText().toCharArray();
        chPlain[2] = mainForm.getPlain3().getText().toCharArray();
        chPlain[3] = mainForm.getPlain4().getText().toCharArray();

    }
}
