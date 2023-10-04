package GUI;

import Database.DatabaseOperations;
import Utility.UtilityFunctions;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddAccountAdmin {
    private JPanel addAccount;
    private JTextField usernameInput;
    private JTextField passwordInput;
    private JLabel Username;
    private JLabel Password;
    private JComboBox comboBox1;
    private JButton createUserButton;

    public AddAccountAdmin(JFrame frame) {
        SetAspect();
        createUserButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
               if(emptyCredentias())
               {
                   DatabaseOperations.setKeyword(String.valueOf(usernameInput.getText() + "," + passwordInput.getText() + "," + (comboBox1.getSelectedIndex() + 1)));
                   DatabaseOperations.ExecuteQuery(21);
                   JOptionPane.showMessageDialog(null, UtilityFunctions.Get_Created_Account_2());
                   frame.dispose();
               }
               else
               {
                   JOptionPane.showMessageDialog(null,UtilityFunctions.Get_Wrong_Credentials_15());
               }
            }
        });
    }

    private void SetAspect(){
        Color cyan = new Color(67, 179, 224);
        createUserButton.setBorder(BorderFactory.createLineBorder(cyan));
        usernameInput.setBorder(BorderFactory.createLineBorder(cyan));
        passwordInput.setBorder(BorderFactory.createLineBorder(cyan));
        comboBox1.setBorder(BorderFactory.createLineBorder(cyan));
    }

    public JPanel getMainPanel() {
        return addAccount;
    }
    private boolean emptyCredentias() {
        if (usernameInput.getText().length() == 0)
            return false;
        if (passwordInput.getText().length() == 0)
            return false;
        return true;
    }
}
