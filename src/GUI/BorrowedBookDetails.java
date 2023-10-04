package GUI;

import Book.Book;
import Database.DatabaseOperations;
import User.User;
import Utility.UtilityFunctions;
import javafx.util.Pair;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import java.text.DecimalFormat;

public class BorrowedBookDetails {
    public BorrowedBookDetails(Book book, User user, Pair<String,String>dates,JFrame frame) throws IOException {
        UtilityFunctions.SetScrollbarAspect(scrollpane);
        returnBookButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String keyword = String.valueOf(user.getUserId()) + ',' + book.getId();
                DatabaseOperations.setKeyword(keyword);
                DatabaseOperations.ExecuteQuery(9);
                frame.dispose();
                JOptionPane.showMessageDialog(null, UtilityFunctions.Get_Returned_Book_7());
            }
        });

        rateThisBookButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String keyword = String.valueOf(book.getId()) + ',' + String.valueOf(user.getUserId()) + ',' + String.valueOf(ratingValue.getSelectedIndex()+1);
                DatabaseOperations.setKeyword(keyword);
                DatabaseOperations.ExecuteQuery(8);
                JOptionPane.showMessageDialog(null, UtilityFunctions.Get_Thanks_8());
            }
        });

        URL url = new URL(book.getImageUrl());
        BufferedImage image = ImageIO.read(url);
        ImageIcon icon = new ImageIcon(image.getScaledInstance(160, 250, java.awt.Image.SCALE_SMOOTH));
        bookIcon.setIcon(icon);

        DatabaseOperations.setBookId(book.getBestBookId());
        DatabaseOperations.ExecuteQuery(1);
        String tags= DatabaseOperations.getBookTags();
        SetBookDetails(book,tags,dates);
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

    public void setBorrowDate(String borrowDate) {
        this.borrowDate.setText(borrowDate);
    }

    public void setReturnDate(String returnDate) {
        this.returnDate.setText(returnDate);
    }

    private void SetBookDetails(Book book,String tags, Pair<String,String> dates){
        this.titleLabel.setText(book.getTitle());
        this.tagsLabel.setText(tags.substring(0,50)+UtilityFunctions.Get_Dots_9());
        this.isbnLabel.setText(book.getIsbn());
        this.languageLabel.setText(book.getLanguageCode());
        this.ratingLabel.setText(df.format(book.getAverageRating()));
        this.authorLabel.setText(book.getAuthors());
        this.borrowDate.setText(dates.getKey());
        this.returnDate.setText(dates.getValue());
    }

    private static final DecimalFormat df=new DecimalFormat(UtilityFunctions.Get_Zeros_10());
    private JPanel panel1;
    private JButton returnBookButton;
    private JLabel bookIcon;
    private JLabel titleLabel;
    private JLabel authorLabel;
    private JLabel ratingLabel;
    private JLabel languageLabel;
    private JLabel isbnLabel;
    private JLabel tagsLabel;
    private JLabel borrowDate;
    private JLabel returnDate;
    private JButton rateThisBookButton;
    private JComboBox ratingValue;
    private JScrollPane scrollpane;

    public JPanel getPanel1() {
        return panel1;
    }
}
