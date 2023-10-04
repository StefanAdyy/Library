package GUI;

import Book.Book;
import Database.DatabaseOperations;
import User.User;
import Utility.UtilityFunctions;

import javax.swing.*;
import javax.swing.border.SoftBevelBorder;
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.util.Vector;

public class AllList {
    private JList list;
    private JLabel titleList;
    private JPanel mainPanel;
    private JButton searchButton;
    private JButton seeAllButton;
    private JTextField searchText;
    private JScrollPane scrollPane;
    private Vector<Book> bookList;
    private Vector<User> userList;
    private Vector<String> stringList;
    private MouseAdapter mouseAdapter;

    public AllList(String title, boolean isBook, JFrame frame) {
        frame.setLocationRelativeTo(null);
        SetAspect();
        if (isBook) {
            titleList.setText(title);
            prepareAllBooks();
            SetMouseAdapterBooks();
            seeAllButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    prepareAllBooks();
                }
            });
            list.addMouseListener(mouseAdapter);
            searchButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    DatabaseOperations.setKeyword(searchText.getText());
                    DatabaseOperations.ExecuteQuery(0);
                    bookList = DatabaseOperations.getBookList();
                    UtilityFunctions.PrepareTitlesAndAuthors(stringList, bookList);
                    list.setListData(stringList);
                }
            });
        } else {
            titleList.setText(title);
            prepareAllUsers();
            SetMouseAdapterUsers();
            seeAllButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    prepareAllUsers();
                }
            });
            list.addMouseListener(mouseAdapter);
            searchButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    DatabaseOperations.setKeyword(searchText.getText());
                    DatabaseOperations.ExecuteQuery(22);
                    userList = DatabaseOperations.getUserList();
                    UtilityFunctions.PrepareUsers(stringList, userList);
                    list.setListData(stringList);
                }
            });
        }

    }

    private void SetAspect() {
        Color cyan = new Color(67, 179, 224);
        UtilityFunctions.SetScrollbarAspect(scrollPane);
        searchText.setCaretColor(cyan);
        searchText.setBorder(new SoftBevelBorder(0, cyan, cyan, cyan, cyan));
        scrollPane.setBorder(BorderFactory.createEmptyBorder());
        list.setBorder(BorderFactory.createEmptyBorder());
        searchButton.setBorder(new SoftBevelBorder(0, cyan, cyan, cyan, cyan));
        seeAllButton.setBorder(new SoftBevelBorder(0, cyan, cyan, cyan, cyan));
    }

    public JPanel getMainPanel() {
        return mainPanel;
    }

    private void prepareAllBooks() {
        searchText.setText("");
        stringList = new Vector<>();
        bookList = new Vector<>();
        DatabaseOperations.ExecuteQuery(6);
        bookList = DatabaseOperations.getBookList();
        UtilityFunctions.PrepareTitlesAndAuthors(stringList, bookList);
        list.setListData(stringList);
        SetMouseAdapterBooks();
        //list.addMouseListener(mouseAdapter);
    }

    private void prepareAllUsers() {
        searchText.setText("");
        stringList = new Vector<>();
        userList = new Vector<>();
        DatabaseOperations.ExecuteQuery(7);
        userList = DatabaseOperations.getUserList();
        UtilityFunctions.PrepareUsers(stringList, userList);
        list.setListData(stringList);
        SetMouseAdapterUsers();
       // list.addMouseListener(mouseAdapter);
    }

    private void SetMouseAdapterBooks() {
        mouseAdapter = new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) {
                    Object o = list.getModel().getElementAt(((JList) e.getSource()).locationToIndex(e.getPoint()));
                    Book selectedBook = UtilityFunctions.GetBookClicked(o.toString(), bookList);
                    JFrame frame = new JFrame(UtilityFunctions.Get_Selected_Book_1());
                    UtilityFunctions.SetFrameParameters(frame, 1200, 750);
                    try {
                        frame.setContentPane(new AdminBookDetails(selectedBook, frame).getBookInsertScroll());
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                }
            }
        };
    }

    private void SetMouseAdapterUsers() {
        mouseAdapter = new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) {
                    Object o = list.getModel().getElementAt(((JList) e.getSource()).locationToIndex(e.getPoint()));
                    User selectedUser = UtilityFunctions.GetUserClicked(o.toString(), userList);
                    JFrame frame = new JFrame(UtilityFunctions.Get_Selected_Account_5());
                    UtilityFunctions.SetFrameParameters(frame, 600, 650);
                    frame.setContentPane(new UserAdminDetails(selectedUser, frame).getUserDetails());
                }
            }
        };
    }
}
