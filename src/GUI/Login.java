package GUI;

import Book.Book;
import Database.DatabaseOperations;
import User.User;
import Utility.UtilityFunctions;

import javax.swing.*;
import javax.swing.border.EtchedBorder;
import javax.swing.border.SoftBevelBorder;
import javax.swing.plaf.BorderUIResource;
import javax.swing.plaf.metal.MetalBorders;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ServiceLoader;
import java.util.Vector;

public class Login {

    private JPanel LoginPanel;
    private JButton Login;
    private JPasswordField password;
    private JFormattedTextField username;
    private JPanel panelUsename;
    private JPanel panelPassword;
    private JPanel panelInputs;
    private JCheckBox administratorCheckBox;
    private JButton Register;
    private JPanel title;
    private boolean administratorInput;
    private JMenuBar menuBarAdmin;
    private JMenuBar menuBarUser;
    private JFrame frame;

    public Login(JMenuBar menuBarAdmin, JMenuBar menuBarUser, JFrame frame, User user, Vector<Book> userBorrowedBooks) {
        this.menuBarAdmin = menuBarAdmin;
        this.menuBarUser = menuBarUser;
        this.frame = frame;
        this.frame.setSize(600, 400);
        SetAspect();
        frame.setLocationRelativeTo(null);
        Login.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (emptyCredentias()) {
                    administratorInput = administratorCheckBox.isSelected();
                    int value = administratorInput ? 1 : 2;
                    if (validateLogin()) {
                        setUserData(user, userBorrowedBooks);
                        if (value == 1) {
                            loginMessage(UtilityFunctions.Get_Admin_Login_11());
                            frame.setJMenuBar(menuBarAdmin);
                            frame.setSize(1200, 750);
                            frame.setContentPane(new AllList(UtilityFunctions.Get_See_All_Accounts_24(), false,frame).getMainPanel());
                            frame.revalidate();
                            frame.repaint();
                        } else {
                            loginMessage(UtilityFunctions.Get_User_Login_13());
                            frame.setJMenuBar(menuBarUser);
                            frame.setSize(1200, 750);
                            frame.setContentPane(new Account(userBorrowedBooks, user, frame).getMainPanel());
                            frame.revalidate();
                            frame.repaint();
                        }
                    } else {
                        loginMessage(UtilityFunctions.Get_Failed_Login_14());
                    }
                } else {
                    loginMessage(UtilityFunctions.Get_Wrong_Credentials_15());
                }
            }
        });

        Register.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (emptyCredentias()) {
                    if (!validateUsername()) {
                        DatabaseOperations.setKeyword("'" + username.getText() + "'" + "," + "'" + password.getText() + "'");
                        DatabaseOperations.ExecuteQuery(4);
                        if (validateLogin()) {
                            setUserData(user,userBorrowedBooks);
                            loginMessage(UtilityFunctions.Get_User_Login_13());
                            frame.setJMenuBar(menuBarUser);
                            frame.setSize(1200, 750);
                            frame.setContentPane(new Account(userBorrowedBooks, user, frame).getMainPanel());
                            frame.revalidate();
                            frame.repaint();
                        } else {
                            loginMessage(UtilityFunctions.Get_Something_Went_Wrong_16());
                        }
                    } else {
                        loginMessage(UtilityFunctions.Get_Username_Already_Taken_17());
                    }
                } else {
                    loginMessage(UtilityFunctions.Get_Wrong_Credentials_15());
                }
            }
        });
    }

    private void SetAspect() {
        Color colorButon = new Color(49, 52, 59);
        Color colorBackground = new Color(49, 52, 59);
        Color colorUsernamePassword = new Color(30, 26, 35);
        Color cyan = new Color(67, 179, 224);

        LoginPanel.setBackground(colorBackground);
        panelUsename.setBackground(colorButon);
        panelPassword.setBackground(colorButon);
        panelInputs.setBackground(colorButon);
        username.setBackground(colorUsernamePassword);
        password.setBackground(colorUsernamePassword);
        username.setCaretColor(cyan);
        password.setCaretColor(cyan);
        username.setSelectedTextColor(colorBackground);
        password.setSelectedTextColor(colorBackground);
        username.setDisabledTextColor(colorBackground);
        password.setDisabledTextColor(colorBackground);
        Login.setBackground(colorButon);
        Register.setBackground(colorButon);
        username.setBorder(BorderFactory.createLineBorder(cyan));
        password.setBorder(BorderFactory.createLineBorder(cyan));
        Login.setBorder(new SoftBevelBorder(0, cyan, cyan, cyan, cyan));
        Register.setBorder(new SoftBevelBorder(0, cyan, cyan, cyan, cyan));
        administratorCheckBox.setBorder(BorderFactory.createEmptyBorder());
    }

    public JPanel getLoginPanel() {
        return LoginPanel;
    }

    private void setUserData(User user, Vector<Book> bookList) {
        User tempUser = DatabaseOperations.getTempUser();
        user.setUserId(tempUser.getUserId());
        user.setUsername(tempUser.getUsername());
        user.setPassword(tempUser.getPassword());
        user.setRankId(tempUser.getRankId());
        user.setActive(tempUser.isActive());
        Vector<Book> tempBookList = DatabaseOperations.getBookList();
        for (Book b : tempBookList)
            bookList.add(b);
    }

    private void loginMessage(String message) {
        final JOptionPane optionPane = new JOptionPane(new JLabel(message, JLabel.CENTER), JOptionPane.INFORMATION_MESSAGE, JOptionPane.INFORMATION_MESSAGE, null, new Object[]{}, null);
        final JDialog dialog = new JDialog();
        dialog.setTitle(UtilityFunctions.Get_Login_18());
        dialog.setModal(true);
        dialog.setContentPane(optionPane);
        dialog.setLocation(990 - 150, 540);
        dialog.setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
        dialog.pack();
        Timer timer = new Timer(700, new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                dialog.dispose();
            }
        });
        timer.setRepeats(false);
        timer.start();
        dialog.setVisible(true);
    }

    private boolean validateLogin() {
        administratorInput = administratorCheckBox.isSelected();
        int value = administratorInput ? 1 : 2;
        DatabaseOperations.setKeyword("'" + username.getText() + "'" + "," + "'" + password.getText() + "'" + "," + value);
        DatabaseOperations.ExecuteQuery(3);
        return DatabaseOperations.getLoginState();
    }

    private boolean validateUsername() {
        DatabaseOperations.setKeyword("'" + username.getText() + "'");
        DatabaseOperations.ExecuteQuery(12);
        return DatabaseOperations.getUserAlreadyExists();
    }

    private boolean emptyCredentias() {
        if (username.getText().length() == 0)
            return false;
        if (password.getText().length() == 0)
            return false;
        return true;
    }

    private Point GetNewLocation(Point p1,Point p2){
        p1.x-=p2.x;
        p1.y-=p2.y;
        return p1;
    }

}
