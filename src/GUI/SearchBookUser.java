package GUI;

import Book.Book;
import Database.DatabaseOperations;
import User.User;
import Utility.UtilityFunctions;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.util.Map;
import java.util.Vector;

public class SearchBookUser {
    private JPanel panel1;
    private JTextField searchField;
    private JButton searchButton;
    private JPanel searchPanel;
    private JList searchedBooks;
    private JScrollPane scrollPane;
    private Vector<Book> bookList;
    private Vector<String> titleAndAuthors;
    private Vector<String> imageLinks;
    private BookImageRenderer bookImageRenderer;

    public SearchBookUser(User user) {
        UtilityFunctions.SetScrollbarAspect(scrollPane);
        searchField.setBorder(BorderFactory.createLineBorder(new Color(67,179,224)));
        titleAndAuthors = new Vector<>();
        imageLinks = new Vector<>();
        bookList = new Vector<>();
        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DatabaseOperations.setKeyword(searchField.getText());
                DatabaseOperations.ExecuteQuery(0);
                bookList = DatabaseOperations.getBookList();
                UtilityFunctions.PrepareTitlesAndLinks(titleAndAuthors,imageLinks,bookList);
                bookImageRenderer = new BookImageRenderer(titleAndAuthors, imageLinks);
                searchedBooks.setListData(titleAndAuthors);
                searchedBooks.setCellRenderer(bookImageRenderer);
            }
        });
        searchedBooks.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) {
                    Object o=searchedBooks.getModel().getElementAt(((JList)e.getSource()).locationToIndex(e.getPoint()));
                    Book selectedBook= UtilityFunctions.GetBookClicked(o.toString(),bookList);
                    JFrame frame=new JFrame(UtilityFunctions.Get_Selected_Book_1());
                    UtilityFunctions.SetFrameParameters(frame,1200,750);
                    try {
                        frame.setContentPane(new SearchedBookDetails(selectedBook,user,frame).getMainPanel());
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                }
            }
        });
    }

    public JPanel getPanel1() {
        return panel1;
    }
}

