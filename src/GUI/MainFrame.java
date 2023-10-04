package GUI;

import Book.Book;
import Database.DatabaseOperations;
import User.User;
import Utility.UtilityFunctions;

import javax.swing.*;
import javax.swing.event.MenuEvent;
import javax.swing.event.MenuListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.util.Vector;

public class MainFrame {
    private JPanel mainPanel;
    private JMenuBar menuBarAdmin;
    private JMenuBar menuBarUser;
    private SearchBookUser searchBookUser;
    private JFrame mainFrame;
    //private SearchBookAdmin searchBookAdmin;
    private User user;
    private Vector<Book> userBorrowedBooks;

    public MainFrame(JFrame frame) {
        UtilityFunctions.SetUIManager();
        user=new User();
        userBorrowedBooks=new Vector<>();
        mainFrame=frame;
        menuBarAdmin = new JMenuBar();
        menuBarAdmin.setBackground(new Color(30,26,35));
        menuBarAdmin.setBorder(BorderFactory.createEmptyBorder());
        menuBarUser = new JMenuBar();
        menuBarUser.setBackground(new Color(30,26,35));
        menuBarUser.setBorder(BorderFactory.createEmptyBorder());
        //searchBookAdmin=new SearchBookAdmin();
        GenerateMenuBarAdmin();
        GenerateMenuBarUser();
        Login login = new Login(menuBarAdmin,menuBarUser,mainFrame,user,userBorrowedBooks);
        searchBookUser = new SearchBookUser(user);
        UtilityFunctions.SetFrameParameters(mainFrame,600,400);
        frame.setContentPane(login.getLoginPanel());
    }

    private void GenerateMenuBarAdmin() {
        JMenu x=new JMenu(UtilityFunctions.Get_Books_19());
        x.setForeground(new Color(170,172,177));
        JMenuItem m1=new JMenuItem(UtilityFunctions.Get_Add_Book_20());
        m1.setBorder(BorderFactory.createEmptyBorder());
        m1.setBackground(new Color(30,26,35));
        m1.setForeground(new Color(170,172,177));
        m1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame frame=new JFrame(UtilityFunctions.Get_Add_Book_20());
                UtilityFunctions.SetFrameParameters(frame,1200,750);
                try {
                    frame.setContentPane(new BookInsert(frame).getBookInsertScroll());
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        });
        JMenuItem m5=new JMenuItem(UtilityFunctions.Get_See_All_Books_21());
        m5.setBorder(BorderFactory.createEmptyBorder());
        m5.setBackground(new Color(30,26,35));
        m5.setForeground(new Color(170,172,177));
        m5.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mainFrame.setContentPane(new AllList(UtilityFunctions.Get_Book_List_22(),true,mainFrame).getMainPanel());
                mainFrame.revalidate();
                mainFrame.repaint();
            }
        });
        x.add(m1);
        x.add(m5);

        JMenu x2=new JMenu(UtilityFunctions.Get_Account_23());
        x2.setForeground(new Color(170,172,177));
        JMenuItem m4=new JMenuItem(UtilityFunctions.Get_See_All_Accounts_24());
        m4.setBorder(BorderFactory.createEmptyBorder());
        m4.setBackground(new Color(30,26,35));
        m4.setForeground(new Color(170,172,177));
        m4.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                mainFrame.setContentPane(new AllList(UtilityFunctions.Get_Account_List_25(),false,mainFrame).getMainPanel());
                mainFrame.revalidate();
                mainFrame.repaint();
            }
        });

        JMenuItem m6=new JMenuItem(UtilityFunctions.Get_Add_account_26());
        m6.setBorder(BorderFactory.createEmptyBorder());
        m6.setBackground(new Color(30,26,35));
        m6.setForeground(new Color(170,172,177));
        m6.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame frame=new JFrame(UtilityFunctions.Get_Add_account_26());
                UtilityFunctions.SetFrameParameters(frame,500,500);
                frame.setContentPane(new AddAccountAdmin(frame).getMainPanel());
            }
        });
        x2.add(m6);
        x2.add(m4);
        JMenu x3=new JMenu(UtilityFunctions.Get_Logout_27());
        x3.setForeground(new Color(170,172,177));
        x3.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                user=new User();
                userBorrowedBooks=new Vector<>();
                mainFrame.setJMenuBar(null);
                mainFrame.setContentPane(new Login(menuBarAdmin,menuBarUser,mainFrame,user,userBorrowedBooks).getLoginPanel());
                mainFrame.revalidate();
                mainFrame.repaint();
            }
        });


        menuBarAdmin.add(x);
        menuBarAdmin.add(x2);
        menuBarAdmin.add(x3);
    }

    public JFrame getMainFrame() {
        return mainFrame;
    }

    private void GenerateMenuBarUser() {
        JMenu x=new JMenu(UtilityFunctions.Get_Books_19());
        x.setForeground(new Color(170,172,177));

        JMenuItem m1=new JMenuItem(UtilityFunctions.Get_Book_Search_29());
        m1.setBorder(BorderFactory.createEmptyBorder());
        m1.setBackground(new Color(30,26,35));
        m1.setForeground(new Color(170,172,177));
        m1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mainFrame.setContentPane(searchBookUser.getPanel1());
                mainFrame.revalidate();
                mainFrame.repaint();
            }
        });
        x.add(m1);
        JMenu x2=new JMenu(UtilityFunctions.Get_Account_23());
        x2.setForeground(new Color(170,172,177));
        JMenuItem m2=new JMenuItem(UtilityFunctions.Get_See_account_28());

        m2.setBorder(BorderFactory.createEmptyBorder());
        m2.setBackground(new Color(30,26,35));
        m2.setForeground(new Color(170,172,177));
        m2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DatabaseOperations.setKeyword(String.valueOf(user.getUserId()));
                DatabaseOperations.ExecuteQuery(11);
                userBorrowedBooks=DatabaseOperations.getBookList();
                mainFrame.setContentPane(new Account(userBorrowedBooks,user,mainFrame).getMainPanel());
                mainFrame.revalidate();
                mainFrame.repaint();
            }
        });
        x2.add(m2);
        JMenu x3=new JMenu(UtilityFunctions.Get_Logout_27());
        x3.setForeground(new Color(170,172,177));

        menuBarUser.add(x);
        menuBarUser.add(x2);
        menuBarUser.add(x3);
        x3.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                user=new User();
                userBorrowedBooks=new Vector<>();
                mainFrame.setJMenuBar(null);
                mainFrame.setContentPane(new Login(menuBarAdmin,menuBarUser,mainFrame,user,userBorrowedBooks).getLoginPanel());
                mainFrame.revalidate();
                mainFrame.repaint();
            }
        });

        menuBarUser.add(x);
        menuBarUser.add(x2);
        menuBarUser.add(x3);
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame(UtilityFunctions.Get_Biblioteca_30());
        MainFrame mainUI = new MainFrame(frame);
    }
}
