package GUI;

import Book.Book;
import Database.DatabaseOperations;
import User.User;
import Utility.UtilityFunctions;
import javafx.scene.control.RadioButton;
import javafx.util.Pair;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.util.Vector;

public class UserAdminDetails {
    private JPanel userAdminDetails;
    private JLabel Username;
    private JLabel Password;
    private JTextField curretnUsername;
    private JRadioButton Rank_1;
    private JRadioButton Rank_2;
    private JTextField curretnPassword;
    private JButton deleteAccountButton;
    private JButton saveChangesButton;
    private JList list;
    private JScrollPane scrollPane;
    private Vector<Book> bookList;
    private Vector<String> stringList;

    public UserAdminDetails(User user, JFrame frame) {
        SetAspect();
        curretnUsername.setText(user.getUsername());
        curretnPassword.setText(user.getPassword());
        if (user.getRankId() == 1) {
            Rank_1.setSelected(true);
            Rank_2.setSelected(false);
        } else {
            Rank_2.setSelected(true);
            Rank_1.setSelected(false);
        }
        stringList = new Vector<>();
        bookList = new Vector<>();
        DatabaseOperations.SetTempUser(user);
        DatabaseOperations.ExecuteQuery(11);
        bookList = DatabaseOperations.getBookList();
        UtilityFunctions.PrepareTitlesAndAuthors(stringList, bookList);
        list.setListData(stringList);
        list.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) {
                    DatabaseOperations.SetTempUser(user);
                    Object o = list.getModel().getElementAt(((JList) e.getSource()).locationToIndex(e.getPoint()));
                    Book selectedBook = UtilityFunctions.GetBookClicked(o.toString(), bookList);
                    Pair<String,String> dates=DatabaseOperations.getDates().get(bookList.indexOf(selectedBook));
                    JFrame frame = new JFrame(UtilityFunctions.Get_Selected_Book_1());
                    UtilityFunctions.SetFrameParameters(frame, 1200, 750);
                    try {
                        frame.setContentPane(new BorrowedBookDetails(selectedBook, user,dates,frame).getPanel1());
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                }
            }
        });

        Rank_1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (Rank_1.isSelected()) {
                    Rank_2.setSelected(false);
                }
                else{
                    Rank_2.setSelected(true);
                }
            }
        });
        Rank_2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (Rank_2.isSelected()) {
                    Rank_1.setSelected(false);
                }
                else {
                    Rank_1.setSelected(true);
                }
            }
        });

        saveChangesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int rank = Rank_1.isSelected() ? 1 : 2;
                DatabaseOperations.setKeyword(String.valueOf(user.getUserId() + "," + "'" + curretnUsername.getText() + "'" + "," + "'" + curretnPassword.getText() + "'" + "," + rank + "," + "1"));
                DatabaseOperations.ExecuteQuery(19);
                JOptionPane.showMessageDialog(null, UtilityFunctions.Get_Saved_Edits_3());
            }
        });
        deleteAccountButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DatabaseOperations.setKeyword(String.valueOf(user.getUserId()));
                DatabaseOperations.ExecuteQuery(13);
                frame.dispose();
                JOptionPane.showMessageDialog(null, UtilityFunctions.Get_Deleted_Account_38());
            }
        });
    }

    private void SetAspect(){
        UtilityFunctions.SetScrollbarAspect(scrollPane);
        Color cyan = new Color(67, 179, 224);
        curretnPassword.setBorder(BorderFactory.createLineBorder(cyan));
        curretnPassword.setCaretColor(cyan);
        curretnUsername.setBorder(BorderFactory.createLineBorder(cyan));
        curretnUsername.setCaretColor(cyan);
        saveChangesButton.setBorder(BorderFactory.createLineBorder(cyan));
        deleteAccountButton.setBorder(BorderFactory.createLineBorder(cyan));
        list.setBorder(BorderFactory.createEmptyBorder());
    }

    public JPanel getUserDetails() {
        return userAdminDetails;
    }
}
