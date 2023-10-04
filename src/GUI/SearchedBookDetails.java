package GUI;

import Book.Book;
import Database.DatabaseOperations;
import User.User;
import Utility.UtilityFunctions;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import java.text.DecimalFormat;

public class SearchedBookDetails {
    public SearchedBookDetails(Book book, User user, JFrame frame) throws IOException {
        UtilityFunctions.SetScrollbarAspect(scrollPane);
        borrowThisBookButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DatabaseOperations.setKeyword(String.valueOf(user.getUserId()));
                DatabaseOperations.ExecuteQuery(18);
                if (DatabaseOperations.isBookNotReturned()) {
                    JOptionPane.showMessageDialog(null, UtilityFunctions.Get_Returning_Date_Ecxpired_35());
                }else{
                    DatabaseOperations.setKeyword(String.valueOf(user.getUserId()) + "," + String.valueOf(book.getId()));
                    DatabaseOperations.ExecuteQuery(17);
                    if (DatabaseOperations.isBookAlreadyBorrowed()) {
                        JOptionPane.showMessageDialog(null,  UtilityFunctions.Get_Already_Borrowed_Book_36());
                    } else {
                        DatabaseOperations.setKeyword(String.valueOf(user.getUserId()) + "," + String.valueOf(book.getId()));
                        DatabaseOperations.ExecuteQuery(10);
                        frame.dispose();
                        JOptionPane.showMessageDialog(null,  UtilityFunctions.Get_Borrow_Book_37());
                    }
                }
            }
        });

        URL url = new URL(book.getImageUrl());
        BufferedImage image = ImageIO.read(url);
        ImageIcon icon = new ImageIcon(image.getScaledInstance(160, 250, java.awt.Image.SCALE_SMOOTH));
        bookIcon.setIcon(icon);
        DatabaseOperations.setBookId(book.getBestBookId());
        DatabaseOperations.ExecuteQuery(1);
        String tags = DatabaseOperations.getBookTags();
        SetBookDetails(book, tags);
    }

    private void SetBookDetails(Book book, String tags) {
        this.titleLabel.setText(book.getTitle());
        //this.bookIcon.setText(book.);
        this.tagsLabel.setText(tags.substring(0, 50) + UtilityFunctions.Get_Dots_9());
        this.isbnLabel.setText(book.getIsbn());
        this.languageLabel.setText(book.getLanguageCode());
        this.ratingLabel.setText(df.format(book.getAverageRating()));
        this.authorLabel.setText(book.getAuthors());
    }

    public void setBookIcon(URL url) throws IOException {
        BufferedImage image = ImageIO.read(url);
        ImageIcon icon = new ImageIcon(image.getScaledInstance(160, 250, java.awt.Image.SCALE_SMOOTH));
        this.bookIcon.setIcon(icon);
    }

    public void setTitleLabel(String title) {
        this.titleLabel.setText(title);
    }

    public void setAuthorLabel(String author) {
        this.authorLabel.setText(author);
    }

    public void setIsbnLabel(String isbn) {
        this.isbnLabel.setText(isbn);
    }

    public void setRatingLabel(String rating) {
        this.ratingLabel.setText(rating);
    }

    public void setLanguageLabel(String language) {
        this.languageLabel.setText(language);
    }

    public void setTagsLabel(String tags) {
        this.tagsLabel.setText(tags);
    }

    public JPanel getMainPanel() {
        return MainPanel;
    }

    private static final DecimalFormat df = new DecimalFormat(UtilityFunctions.Get_Zeros_10());
    private JPanel MainPanel;
    private JLabel titleLabel;
    private JLabel bookIcon;
    private JButton borrowThisBookButton;
    private JLabel tagsLabel;
    private JLabel isbnLabel;
    private JLabel languageLabel;
    private JLabel ratingLabel;
    private JLabel authorLabel;
    private JScrollPane scrollPane;
}