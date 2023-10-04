package GUI;

import Book.Book;
import Database.DatabaseOperations;
import User.User;
import Utility.UtilityFunctions;
import javafx.util.Pair;

import javax.swing.*;
import javax.swing.border.SoftBevelBorder;
import javax.xml.crypto.Data;
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.util.Map;
import java.util.Vector;

public class Account {
    private JPanel mainPanel;
    private JButton refreshButton;
    private JButton deleteAccountButton;
    private JList accountBookList;
    private JScrollPane scrollPane;
    private BookImageRenderer bookImageRenderer;
    private Vector<Book> borrowedBookList;
    private Vector<String> titleAndAuthors;
    private Vector<String> imageLinks;
    private MouseAdapter mouseAdapter;
    private JFrame frame;

    public Account(Vector<Book> bookList, User user,JFrame frame) {
        frame.setLocationRelativeTo(null);
        SetAspect();
        titleAndAuthors = new Vector<>();
        imageLinks = new Vector<>();
        this.frame=frame;
        borrowedBookList = bookList;
        UtilityFunctions.PrepareTitlesAndLinks(titleAndAuthors, imageLinks, borrowedBookList);
        bookImageRenderer = new BookImageRenderer(titleAndAuthors, imageLinks);
        accountBookList.setListData(titleAndAuthors);
        accountBookList.setCellRenderer(bookImageRenderer);
        setMouseAdapter(bookList, user);
        accountBookList.addMouseListener(mouseAdapter);



        refreshButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DatabaseOperations.setKeyword(String.valueOf(user.getUserId()));
                DatabaseOperations.ExecuteQuery(11);
                borrowedBookList = DatabaseOperations.getBookList();
                UtilityFunctions.PrepareTitlesAndLinks(titleAndAuthors, imageLinks, borrowedBookList);
                bookImageRenderer = new BookImageRenderer(titleAndAuthors, imageLinks);
                accountBookList.setListData(titleAndAuthors);
                accountBookList.setCellRenderer(bookImageRenderer);
                accountBookList.removeMouseListener(mouseAdapter);
                setMouseAdapter(borrowedBookList, user);
                accountBookList.addMouseListener(mouseAdapter);
            }
        });
        deleteAccountButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DatabaseOperations.setKeyword(String.valueOf(user.getUserId()));
                DatabaseOperations.ExecuteQuery(13);
                resetMainFrame(frame);
                frame.setJMenuBar(null);
                frame.revalidate();
                frame.repaint();
            }
        });
    }

    private void SetAspect(){
        UtilityFunctions.SetScrollbarAspect(scrollPane);
        Color borderColor=new Color(67,179,224);
        deleteAccountButton.setBorder(new SoftBevelBorder(0,borderColor, borderColor, borderColor, borderColor));
        refreshButton.setBorder(new SoftBevelBorder(0,borderColor, borderColor, borderColor, borderColor));
        accountBookList.setBorder(BorderFactory.createEmptyBorder());
        scrollPane.setBorder(BorderFactory.createEmptyBorder());
    }

    private void resetMainFrame(JFrame frame){
        new MainFrame(frame);
        frame.revalidate();
        frame.repaint();
    }

    private void setMouseAdapter(Vector<Book> borrowedBookList, User user) {
        mouseAdapter = new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) {
                    Object o = accountBookList.getModel().getElementAt(((JList) e.getSource()).locationToIndex(e.getPoint()));
                    Book selectedBook = UtilityFunctions.GetBookClicked(o.toString(), borrowedBookList);
                    Pair<String,String>dates=DatabaseOperations.getDates().get(borrowedBookList.indexOf(selectedBook));
                    JFrame frame = new JFrame(UtilityFunctions.Get_Selected_Book_1());
                    UtilityFunctions.SetFrameParameters(frame, 1200, 750);
                    try {
                        frame.setContentPane(new BorrowedBookDetails(selectedBook, user, dates,frame).getPanel1());
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                }
            }
        };
    }

    public JPanel getMainPanel() {
        return mainPanel;
    }
}
