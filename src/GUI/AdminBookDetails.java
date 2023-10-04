package GUI;

import Book.Book;
import Database.DatabaseOperations;
import Utility.UtilityFunctions;

import javax.swing.*;
import javax.swing.border.SoftBevelBorder;
import javax.xml.crypto.Data;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class AdminBookDetails {
    public AdminBookDetails(Book book,JFrame frame) throws IOException {
        SetAspect();
        SetBookDetails(book);
        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DatabaseOperations.setKeyword(String.valueOf(book.getId()) +','+getValues());
                DatabaseOperations.ExecuteQuery(5);
                JOptionPane.showMessageDialog(null, UtilityFunctions.Get_Saved_Edits_3());
            }
        });
        allRatings.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DatabaseOperations.setBookId((book.getId()));
                DatabaseOperations.ExecuteQuery(15);
                JFrame frame=new JFrame(UtilityFunctions.Get_Ratings_For_4()+book.getTitle());
                UtilityFunctions.SetFrameParameters(frame,500,1000);
                frame.setContentPane(new RatingsBook(DatabaseOperations.getUsernames(),DatabaseOperations.getRatings()).getScrollPane());
            }
        });
        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DatabaseOperations.setBookId((book.getId()));
                DatabaseOperations.ExecuteQuery(20);
                frame.dispose();
            }
        });
    }

    private void SetBookDetails(Book book){
        bestBookId.setText(String.valueOf(book.getBestBookId()));
        workId.setText(String.valueOf(book.getWorkId()));
        isbn.setText(book.getIsbn());
        isbn13.setText(book.getIsbn13());
        authors.setText(book.getAuthors());
        originalTitle.setText(book.getOriginalTitle());
        originalPublicationYear.setText(book.getOriginalPublicationYear());
        title.setText(book.getTitle());
        languageCode.setText(book.getLanguageCode());
        averageRating.setText(String.valueOf(book.getAverageRating()));
        ratingsCount.setText(String.valueOf(book.getRatingsCount()));
        ratings1.setText(String.valueOf(book.getRatings1()));
        ratings2.setText(String.valueOf(book.getRatings2()));
        ratings3.setText(String.valueOf(book.getRatings3()));
        ratings4.setText(String.valueOf(book.getRatings4()));
        ratings5.setText(String.valueOf(book.getRatings5()));
        imageURL.setText(book.getImageUrl());
        smallImageUrl.setText(book.getSmallImageUrl());
    }

    private void SetAspect(){
        Color borderColor=new Color(67, 179, 224);
        UtilityFunctions.SetScrollbarAspect(bookInsertScroll);
        bestBookId.setBorder(new SoftBevelBorder(0,borderColor, borderColor, borderColor, borderColor));
        bestBookId.setCaretColor(borderColor);
        workId.setBorder(new SoftBevelBorder(0,borderColor, borderColor, borderColor, borderColor));
        workId.setCaretColor(borderColor);
        isbn.setBorder(new SoftBevelBorder(0,borderColor, borderColor, borderColor, borderColor));
        isbn.setCaretColor(borderColor);
        isbn13.setBorder(new SoftBevelBorder(0,borderColor, borderColor, borderColor, borderColor));
        isbn13.setCaretColor(borderColor);
        authors.setBorder(new SoftBevelBorder(0,borderColor, borderColor, borderColor, borderColor));
        authors.setCaretColor(borderColor);
        originalTitle.setBorder(new SoftBevelBorder(0,borderColor, borderColor, borderColor, borderColor));
        originalTitle.setCaretColor(borderColor);
        originalPublicationYear.setBorder(new SoftBevelBorder(0,borderColor, borderColor, borderColor, borderColor));
        originalPublicationYear.setCaretColor(borderColor);
        title.setBorder(new SoftBevelBorder(0,borderColor, borderColor, borderColor, borderColor));
        title.setCaretColor(borderColor);
        languageCode.setBorder(new SoftBevelBorder(0,borderColor, borderColor, borderColor, borderColor));
        languageCode.setCaretColor(borderColor);
        averageRating.setBorder(new SoftBevelBorder(0,borderColor, borderColor, borderColor, borderColor));
        averageRating.setCaretColor(borderColor);
        ratingsCount.setBorder(new SoftBevelBorder(0,borderColor, borderColor, borderColor, borderColor));
        ratingsCount.setCaretColor(borderColor);
        ratings1.setBorder(new SoftBevelBorder(0,borderColor, borderColor, borderColor, borderColor));
        ratings1.setCaretColor(borderColor);
        ratings2.setBorder(new SoftBevelBorder(0,borderColor, borderColor, borderColor, borderColor));
        ratings2.setCaretColor(borderColor);
        ratings3.setBorder(new SoftBevelBorder(0,borderColor, borderColor, borderColor, borderColor));
        ratings3.setCaretColor(borderColor);
        ratings4.setBorder(new SoftBevelBorder(0,borderColor, borderColor, borderColor, borderColor));
        ratings4.setCaretColor(borderColor);
        ratings5.setBorder(new SoftBevelBorder(0,borderColor, borderColor, borderColor, borderColor));
        ratings5.setCaretColor(borderColor);
        imageURL.setBorder(new SoftBevelBorder(0,borderColor, borderColor, borderColor, borderColor));
        imageURL.setCaretColor(borderColor);
        smallImageUrl.setBorder(new SoftBevelBorder(0,borderColor, borderColor, borderColor, borderColor));
        smallImageUrl.setCaretColor(borderColor);
        bookInsertScroll.setBorder(BorderFactory.createEmptyBorder());
        saveButton.setBorder(new SoftBevelBorder(0,borderColor, borderColor, borderColor, borderColor));
        deleteButton.setBorder(new SoftBevelBorder(0,borderColor, borderColor, borderColor, borderColor));
        allRatings.setBorder(new SoftBevelBorder(0,borderColor, borderColor, borderColor, borderColor));
    }

    private String getValues() {
        String values = bestBookId.getText() + ',' + workId.getText() + ',' + '\'' + isbn.getText() + '\'' + ',' + '\'' + isbn13.getText() + '\'' + ',' + '\'' + authors.getText() + '\'' + ','
                + '\'' + originalPublicationYear.getText() + '\'' + ',' + '\'' + originalTitle.getText() + '\'' + ',' + '\'' + title.getText() + '\'' + ',' + '\'' + languageCode.getText() + '\'' + ','
                + averageRating.getText() + ',' + ratingsCount.getText() + ',' + ratings1.getText() + ',' + ratings2.getText() + ',' + ratings3.getText() + ',' + ratings4.getText() + ',' + ratings5.getText() + ','
                + '\'' + imageURL.getText() + '\'' + ',' + '\'' + smallImageUrl.getText() + '\'';

        return values;
    }

    private JScrollPane bookInsertScroll;
    private JPanel SearchBookDetailsPanel;
    private JTextField workId;
    private JTextField isbn;
    private JTextField isbn13;
    private JTextField authors;
    private JTextField originalTitle;
    private JTextField originalPublicationYear;
    private JTextField title;
    private JTextField languageCode;
    private JTextField averageRating;
    private JTextField ratingsCount;
    private JTextField ratings1;
    private JTextField ratings2;
    private JTextField ratings3;
    private JTextField ratings4;
    private JTextField ratings5;
    private JTextField imageURL;
    private JTextField smallImageUrl;
    private JButton saveButton;
    private JTextField bestBookId;
    private JPanel bookInsertPanel;
    private JButton allRatings;
    private JButton deleteButton;

    public JScrollPane getBookInsertScroll() {
        return bookInsertScroll;
    }
}
