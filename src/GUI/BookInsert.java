package GUI;

import Book.Book;
import Database.DatabaseOperations;
import Utility.UtilityFunctions;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.SoftBevelBorder;
import javax.swing.plaf.basic.BasicScrollBarUI;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;

public class BookInsert {
    public BookInsert(JFrame frame) throws IOException {
        SetAspect();
        insertButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(EmptyFields())
                {
                    DatabaseOperations.setKeyword(getValues());
                    DatabaseOperations.ExecuteQuery(2);
                    JOptionPane.showMessageDialog(null, UtilityFunctions.Get_Inserted_Book_6());
                }
                else
                {
                    JOptionPane.showMessageDialog(null,UtilityFunctions.Get_Not_Enough_Text_Fields_Completed_43());
                }
            }
        });
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
        smallImageUrl.setCaretColor(borderColor);
        bookInsertScroll.setBorder(BorderFactory.createEmptyBorder());
        smallImageUrl.setBorder(new SoftBevelBorder(0,borderColor, borderColor, borderColor, borderColor));
        insertButton.setBorder(new SoftBevelBorder(0,borderColor, borderColor, borderColor, borderColor));
    }

    public JScrollPane getBookInsertScroll() {
        return bookInsertScroll;
    }

    private String getValues() {
        String values = bestBookId.getText() + ',' + workId.getText() + ',' + '\'' + isbn.getText() + '\'' + ',' + '\'' + isbn13.getText() + '\'' + ',' + '\'' + authors.getText() + '\'' + ','
                + '\'' + originalPublicationYear.getText()  + '\'' + ',' + '\'' + originalTitle.getText()  + '\'' + ',' + '\'' + title.getText()  + '\'' + ',' + '\'' + languageCode.getText()  + '\'' + ','
                + averageRating.getText()  + ',' + ratingsCount.getText()  + ',' + ratings1.getText()  + ',' + ratings2.getText()  + ',' + ratings3.getText()  + ',' + ratings4.getText()  + ',' + ratings5.getText()  + ','
                + '\'' + imageURL.getText()  + '\'' + ',' + '\'' + smallImageUrl.getText()  + '\'';

        return values;
    }

    private JPanel SearchBookDetailsPanel;
    private JButton insertButton;
    private JTextField bestBookId;
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
    private JPanel bookInsert;
    private JScrollPane bookInsertScroll;

    private boolean EmptyFields()
    {
        if(bestBookId.getText().length()==0)
            return false;
        if(workId.getText().length()==0)
            return false;
        if(authors.getText().length()==0)
            return false;
        if(title.getText().length()==0)
            return false;
        if(averageRating.getText().length()==0)
            return false;
        return true;
    }
}
