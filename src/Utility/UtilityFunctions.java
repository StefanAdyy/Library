package Utility;

import Book.Book;
import User.User;

import javax.jws.soap.SOAPBinding;
import javax.swing.*;
import javax.swing.plaf.ButtonUI;
import javax.swing.plaf.basic.BasicScrollBarUI;
import java.awt.*;
import java.util.Vector;

public class UtilityFunctions {


    public static Book GetBookClicked(String titleAndAuthor, Vector<Book> bookList) {
        for (Book b : bookList) {
            if ((b.getTitle() + " - " + b.getAuthors()).equals(titleAndAuthor))
                return b;
        }
        return null;
    }

    public static User GetUserClicked(String user, Vector<User> userVector) {
        for (User b : userVector) {
            if (b.getUsername().equals(user))
                return b;
        }
        return null;
    }

    public static void SetFrameParameters(JFrame frame, int width, int height) {
        //frame.add(menuBar);
        frame.setDefaultCloseOperation(frame.DISPOSE_ON_CLOSE);
        frame.setSize(width, height);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        frame.getLocation();
    }

    public static void PrepareTitlesAndAuthors(Vector<String> titleAndAuthors, Vector<Book> bookList) {
        titleAndAuthors.removeAllElements();
        for (Book b : bookList) {
            titleAndAuthors.add(b.getTitle() + " - " + b.getAuthors());
        }
    }

    public static void PrepareUsers(Vector<String> Names, Vector<User> userList) {
        Names.removeAllElements();
        for (User u : userList) {
            Names.add(u.getUsername());
        }
    }

    public static void PrepareTitlesAndLinks(Vector<String> titleAndAuthors, Vector<String> imageLinks, Vector<Book> bookList) {
        titleAndAuthors.removeAllElements();
        imageLinks.removeAllElements();
        for (Book b : bookList) {
            titleAndAuthors.add(b.getTitle() + " - " + b.getAuthors());
            imageLinks.add(b.getSmallImageUrl());
        }
    }

    public static String Get_Helvetica_0() {
        return "helvitica";
    }

    public static String Get_Selected_Book_1() {
        return "Selected Book";
    }

    public static String Get_Created_Account_2() {
        return "The account has been created!";
    }

    public static String Get_Saved_Edits_3() {
        return "The edits were saved!";
    }

    public static String Get_Ratings_For_4() {
        return "Ratings for ";
    }

    public static String Get_Selected_Account_5() {
        return "Selected Account";
    }

    public static String Get_Inserted_Book_6() {
        return "The book was inserted!";
    }

    public static String Get_Returned_Book_7() {
        return "You have returned the book!\n";
    }

    public static String Get_Thanks_8() {
        return "Thanks!\n";
    }

    public static String Get_Dots_9() {
        return "...";
    }

    public static String Get_Zeros_10() {
        return "0.00";
    }

    public static String Get_Admin_Login_11() {
        return "Login administrator";
    }

    public static String Get_All_Books_12() {
        return "All books";
    }

    public static String Get_User_Login_13() {
        return "Login user";
    }

    public static String Get_Failed_Login_14() {
        return "Login failed";
    }

    public static String Get_Wrong_Credentials_15() {
        return "Credentials are wrong";
    }

    public static String Get_Something_Went_Wrong_16() {
        return "Something went horribly wrong";
    }

    public static String Get_Username_Already_Taken_17() {
        return "Username already taken";
    }

    public static String Get_Login_18() {
        return "Login";
    }

    public static String Get_Books_19() {
        return "Books";
    }

    public static String Get_Add_Book_20() {
        return "Add a book";
    }

    public static String Get_See_All_Books_21() {
        return "See all Books";
    }

    public static String Get_Book_List_22() {
        return "Book list";
    }

    public static String Get_Account_23() {
        return "Account";
    }

    public static String Get_See_All_Accounts_24() {
        return "See all accounts";
    }

    public static String Get_Account_List_25() {
        return "Account list";
    }

    public static String Get_Add_account_26() {
        return "Add an account";
    }

    public static String Get_Logout_27() {
        return "Logout";
    }

    public static String Get_See_account_28() {
        return "See account";
    }

    public static String Get_Book_Search_29() {
        return "Search books";
    }

    public static String Get_Biblioteca_30() {
        return "Biblioteca";
    }

    public static String Get_Usernames_31() {
        return "Usernames";
    }

    public static String Get_Ratings_32() {
        return "Ratings";
    }

    public static String Get_Delete_33() {
        return "Delete";
    }

    public static String Get_Button_Background_34() {
        return "Button.background";
    }

    public static String Get_Returning_Date_Ecxpired_35() {
        return "You have books that are past\n the returning date!";
    }

    public static String Get_Already_Borrowed_Book_36() {
        return "You have already borrowed this book.";
    }

    public static String Get_Borrow_Book_37() {
        return "You have borrowed the book.\nPlease return it in 2 weeks.";
    }

    public static String Get_Deleted_Account_38() {
        return "The account has been deleted!";
    }

    public static String Get_Server_39() {
        return "jdbc:sqlserver://DESKTOP-RBDAFCC\\MSSQLSERVER:1433;database=dbCarti";
    }

    public static String Get_Server_Name_40() {
        return "Alex2";
    }

    public static String Get_Server_Password_41() {
        return "alex";
    }

    public static String Get_Tag_Name_42() {
        return "tag_name";
    }
    public static String Get_Not_Enough_Text_Fields_Completed_43(){return "Not enough text fields completed";}


    private static BasicScrollBarUI GetScrollbarUI() {
        return new BasicScrollBarUI() {
            @Override
            protected void configureScrollBarColors() {
                this.thumbColor = new Color(26, 28, 33);
            }

            @Override
            protected JButton createDecreaseButton(int orientation) {
                JButton jbutton = new JButton();
                jbutton.setPreferredSize(new Dimension(0, 0));
                jbutton.setMinimumSize(new Dimension(0, 0));
                jbutton.setMaximumSize(new Dimension(0, 0));
                return jbutton;
            }

            @Override
            protected JButton createIncreaseButton(int orientation) {
                JButton jbutton = new JButton();
                jbutton.setPreferredSize(new Dimension(0, 0));
                jbutton.setMinimumSize(new Dimension(0, 0));
                jbutton.setMaximumSize(new Dimension(0, 0));
                return jbutton;
            }
        };
    }

    public static void SetScrollbarAspect(JScrollPane scrollPane) {
        scrollPane.setBorder(BorderFactory.createEmptyBorder());
        scrollPane.setBackground(new Color(64, 60, 70));
        scrollPane.getVerticalScrollBar().setBackground(new Color(64, 60, 70));
        scrollPane.getVerticalScrollBar().setUI(GetScrollbarUI());
        scrollPane.getHorizontalScrollBar().setBackground(new Color(64, 60, 70));
        scrollPane.getHorizontalScrollBar().setUI(GetScrollbarUI());
    }

    public static void SetUIManager() {
        UIManager uiManager = new UIManager();
        uiManager.put("OptionPane.background", new Color(49, 52, 59));
        uiManager.put("Panel.background", new Color(49, 52, 59));
        uiManager.put("OptionPane.foreground", new Color(67, 179, 224));
        uiManager.put("Panel.foreground", new Color(67, 179, 224));
        uiManager.put("Label.background", new Color(49, 52, 59));
        uiManager.put("Label.foreground", new Color(67, 179, 224));
        uiManager.put("Label.font", new Font("Roboto Light", Font.BOLD, 16));
        uiManager.put("OptionPane.font", new Font("Roboto Light", Font.BOLD, 16));
        uiManager.put("OptionPane.messageForeground", new Color(67, 179, 224));
        UIManager.put("MenuItem.selectionForeground", new Color(67, 179, 224));
        UIManager.put("Menu.selectionForeground", new Color(67, 179, 224));
        UIManager.put("MenuItem.selectionBackground", new Color(49, 52, 59));
        UIManager.put("Menu.selectionBackground", new Color(49, 52, 59));
        UIManager.put("Menu.border",BorderFactory.createEmptyBorder());
        UIManager.put("MenuBar.border",BorderFactory.createEmptyBorder());
        UIManager.put("MenuItem.border",BorderFactory.createEmptyBorder());
        UIManager.put("PopupMenu.border",BorderFactory.createEmptyBorder());
    }
}
