package Database;

import Book.Book;
import User.User;
import Utility.UtilityFunctions;
import javafx.util.Pair;

import java.lang.reflect.UndeclaredThrowableException;
import java.sql.*;
import java.util.Vector;

public class DatabaseOperations {
    private static final String DB_URL = UtilityFunctions.Get_Server_39();
    private static final String USER = UtilityFunctions.Get_Server_Name_40();
    private static final String PASS = UtilityFunctions.Get_Server_Password_41();
    private static Connection connection = null;
    private static Statement statement = null;
    private static StringBuilder stringBuilder;
    private static Vector<Book> bookList;
    private static Vector<User> userList;
    private static Book tempBook;
    private static User tempUser;
    private static String keyword;
    private static int bookId;
    private static String bookTags;
    private static boolean loginState;
    private static boolean userAlreadyExists;
    private static boolean bookAlreadyBorrowed;
    private static boolean bookNotReturned;
    private static Vector<Pair<String, String>> dates;
    private static Vector<String> usernames;
    private static Vector<Integer> ratings;

    public static void Connect() {
        try {
            connection = DriverManager.getConnection(DB_URL, USER, PASS);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void Disconnect() {
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void ExecuteQuery(int option) {
        switch (option) {
            case 0:
                spBooksSearch();
                break;
            case 1:
                spTagsGetByBook();
                break;
            case 2:
                spBooksInsert();
                break;
            case 3:
                spBooksBorrowedListLogin();
                break;
            case 4:
                spUserInsert();
                keyword += ",2";
                spBooksBorrowedListLogin();
                break;
            case 5:
                spBooksUpdate();
            case 6:
                spBooksSelectAll();
                break;
            case 7:
                spUserSelectAll();
                break;
            case 8:
                spRatingsInsert();
                break;
            case 9:
                spBorrowedBooksDelete();
                break;
            case 10:
                spBorrowedBooksInsert();
                break;
            case 11:
                spBooksBorrowedList();
                break;
            case 12:
                spUserCheckExistingUser();
                break;
            case 13:
                spUserDelete();
                break;
            case 15:
                spRatingsSelectByBook();
                break;
            case 16:
                spRatingsDeleteByBook();
                break;
            case 17:
                spBorrowedBooksCheckBorrowed();
                break;
            case 18:
                spBorrowedBooksCheckDate();
                break;
            case 19:
                spUserUpdate();
                break;
            case 20:
                spBooksDelete();
                break;
            case 21:
                spUserInsertAdmin();
                break;
            case 22:
                spUserSearch();
                break;
        }
    }

    private static void spBooksSearch() {
        try {
            bookList = new Vector<>();
            Connect();
            statement = connection.createStatement();
            ResultSet rs = statement.executeQuery("EXEC spBooksSearch '" + keyword + "';");
            while (rs.next()) {
                int i = 1;
                tempBook = new Book();
                tempBook.setId(rs.getInt(i++));
                tempBook.setBookId(rs.getInt(i++));
                tempBook.setBestBookId(rs.getInt(i++));
                tempBook.setWorkId(rs.getInt(i++));
                tempBook.setTitle(rs.getString(i++));
                tempBook.setIsbn(rs.getString(i++));
                tempBook.setIsbn13(rs.getString(i++));
                tempBook.setAuthors(rs.getString(i++));
                tempBook.setOriginalPublicationYear(rs.getString(i++));
                tempBook.setOriginalTitle(rs.getString(i++));
                tempBook.setTitle(rs.getString(i++));
                tempBook.setLanguageCode(rs.getString(i++));
                tempBook.setAverageRating(rs.getDouble(i++));
                tempBook.setRatingsCount(rs.getInt(i++));
                tempBook.setRatings1(rs.getInt(i++));
                tempBook.setRatings2(rs.getInt(i++));
                tempBook.setRatings3(rs.getInt(i++));
                tempBook.setRatings4(rs.getInt(i++));
                tempBook.setRatings5(rs.getInt(i++));
                tempBook.setImageUrl(rs.getString(i++));
                tempBook.setSmallImageUrl(rs.getString(i++));
                tempBook.setActive(rs.getBoolean(i++));
                bookList.add(tempBook);
            }
            Disconnect();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void spTagsGetByBook() {
        try {
            bookTags = new String();
            Connect();
            statement = connection.createStatement();
            ResultSet rs = statement.executeQuery("EXEC spTagsGetByBook " + bookId + ";");
            while (rs.next())
                bookTags += '#' + rs.getString(UtilityFunctions.Get_Tag_Name_42()) + ' ';
            Disconnect();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void spBooksInsert() {
        try {
            Connect();
            statement = connection.createStatement();
            statement.execute("EXEC spBooksInsert " + keyword + ";");
            Disconnect();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void spBooksBorrowedListLogin() {
        try {
            Connect();
            statement = connection.createStatement();
            ResultSet rs = statement.executeQuery("EXEC spUserSelectLogin " + keyword + ";");
            rs.next();
            int i = rs.getInt(1);
            if (i == 1) {
                loginState = true;
                tempUser = new User();
                rs = statement.executeQuery("EXEC spUserSelect " + keyword.substring(0, keyword.indexOf(',')) + ";");
                while (rs.next()) {
                    int j = 1;
                    tempUser.setUserId(rs.getInt(j++));
                    tempUser.setUsername(rs.getString(j++));
                    tempUser.setPassword(rs.getString(j++));
                    tempUser.setRankId(rs.getInt(j++));
                    tempUser.setActive(rs.getBoolean(j++));
                }
                bookList = new Vector<>();
                dates = new Vector<>();
                rs = statement.executeQuery("EXEC spBooksBorrowedList " + tempUser.getUserId() + ";");
                Pair<String, String> tempDates = new Pair<>("", "");
                while (rs.next()) {
                    int j = 1;
                    tempBook = new Book();
                    tempBook.setId(rs.getInt(j++));
                    tempBook.setBookId(rs.getInt(j++));
                    tempBook.setBestBookId(rs.getInt(j++));
                    tempBook.setWorkId(rs.getInt(j++));
                    tempBook.setTitle(rs.getString(j++));
                    tempBook.setIsbn(rs.getString(j++));
                    tempBook.setIsbn13(rs.getString(j++));
                    tempBook.setAuthors(rs.getString(j++));
                    tempBook.setOriginalPublicationYear(rs.getString(j++));
                    tempBook.setOriginalTitle(rs.getString(j++));
                    tempBook.setTitle(rs.getString(j++));
                    tempBook.setLanguageCode(rs.getString(j++));
                    tempBook.setAverageRating(rs.getDouble(j++));
                    tempBook.setRatingsCount(rs.getInt(j++));
                    tempBook.setRatings1(rs.getInt(j++));
                    tempBook.setRatings2(rs.getInt(j++));
                    tempBook.setRatings3(rs.getInt(j++));
                    tempBook.setRatings4(rs.getInt(j++));
                    tempBook.setRatings5(rs.getInt(j++));
                    tempBook.setImageUrl(rs.getString(j++));
                    tempBook.setSmallImageUrl(rs.getString(j++));
                    tempBook.setActive(rs.getBoolean(j++));
                    tempDates = new Pair<>(rs.getString(j), rs.getString(j + 1));
                    dates.add(tempDates);
                    bookList.add(tempBook);
                }
            } else {
                loginState = false;
                i = rs.getInt(1);
                if (i == 1) {
                    loginState = true;
                } else {
                    loginState = false;
                }
            }
            Disconnect();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void spUserInsert() {
        try {
            Connect();
            statement = connection.createStatement();
            statement.execute("EXEC spUserInsert " + keyword + ";");
            Disconnect();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void spBooksUpdate() {
        try {
            Connect();
            statement = connection.createStatement();
            statement.execute("EXEC spBooksUpdate " + keyword + ";");
            Disconnect();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void spBooksSelectAll() {
        try {
            bookList = new Vector<>();
            Connect();
            statement = connection.createStatement();
            ResultSet rs = statement.executeQuery("EXEC spBooksSelectAll;");
            while (rs.next()) {
                int i = 1;
                tempBook = new Book();
                tempBook.setId(rs.getInt(i++));
                tempBook.setBookId(rs.getInt(i++));
                tempBook.setBestBookId(rs.getInt(i++));
                tempBook.setWorkId(rs.getInt(i++));
                tempBook.setTitle(rs.getString(i++));
                tempBook.setIsbn(rs.getString(i++));
                tempBook.setIsbn13(rs.getString(i++));
                tempBook.setAuthors(rs.getString(i++));
                tempBook.setOriginalPublicationYear(rs.getString(i++));
                tempBook.setOriginalTitle(rs.getString(i++));
                tempBook.setTitle(rs.getString(i++));
                tempBook.setLanguageCode(rs.getString(i++));
                tempBook.setAverageRating(rs.getDouble(i++));
                tempBook.setRatingsCount(rs.getInt(i++));
                tempBook.setRatings1(rs.getInt(i++));
                tempBook.setRatings2(rs.getInt(i++));
                tempBook.setRatings3(rs.getInt(i++));
                tempBook.setRatings4(rs.getInt(i++));
                tempBook.setRatings5(rs.getInt(i++));
                tempBook.setImageUrl(rs.getString(i++));
                tempBook.setSmallImageUrl(rs.getString(i++));
                tempBook.setActive(rs.getBoolean(i++));
                bookList.add(tempBook);
            }
            Disconnect();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void spUserSelectAll() {
        try {
            userList = new Vector<>();
            Connect();
            statement = connection.createStatement();
            ResultSet rs = statement.executeQuery("EXEC spUserSelectAll;");
            while (rs.next()) {
                int i = 1;
                tempUser = new User();
                tempUser.setUserId(rs.getInt(i++));
                tempUser.setUsername(rs.getString(i++));
                tempUser.setPassword(rs.getString(i++));
                tempUser.setRankId(rs.getInt(i++));
                tempBook.setActive(rs.getBoolean(i++));
                userList.add(tempUser);
            }
            Disconnect();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void spRatingsInsert() {
        try {
            Connect();
            statement = connection.createStatement();
            statement.execute("EXEC spRatingsInsert " + keyword + ';');
            Disconnect();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void spBorrowedBooksDelete() {
        try {
            Connect();
            statement = connection.createStatement();
            statement.execute("EXEC spBorrowedBooksDelete " + keyword + ';');
            Disconnect();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void spBorrowedBooksInsert() {
        try {
            Connect();
            statement = connection.createStatement();
            statement.execute("EXEC spBorrowedBooksInsert " + keyword + ';');
            Disconnect();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void spBooksBorrowedList() {
        try {
            bookList = new Vector<>();
            dates = new Vector<>();
            Connect();
            statement = connection.createStatement();
            ResultSet rs = statement.executeQuery("EXEC spBooksBorrowedList " + tempUser.getUserId() + ";");
            Pair<String, String> tempDates = new Pair<>("", "");
            while (rs.next()) {
                int j = 1;
                tempBook = new Book();
                tempBook.setId(rs.getInt(j++));
                tempBook.setBookId(rs.getInt(j++));
                tempBook.setBestBookId(rs.getInt(j++));
                tempBook.setWorkId(rs.getInt(j++));
                tempBook.setTitle(rs.getString(j++));
                tempBook.setIsbn(rs.getString(j++));
                tempBook.setIsbn13(rs.getString(j++));
                tempBook.setAuthors(rs.getString(j++));
                tempBook.setOriginalPublicationYear(rs.getString(j++));
                tempBook.setOriginalTitle(rs.getString(j++));
                tempBook.setTitle(rs.getString(j++));
                tempBook.setLanguageCode(rs.getString(j++));
                tempBook.setAverageRating(rs.getDouble(j++));
                tempBook.setRatingsCount(rs.getInt(j++));
                tempBook.setRatings1(rs.getInt(j++));
                tempBook.setRatings2(rs.getInt(j++));
                tempBook.setRatings3(rs.getInt(j++));
                tempBook.setRatings4(rs.getInt(j++));
                tempBook.setRatings5(rs.getInt(j++));
                tempBook.setImageUrl(rs.getString(j++));
                tempBook.setSmallImageUrl(rs.getString(j++));
                tempBook.setActive(rs.getBoolean(j++));
                tempDates = new Pair<>(rs.getString(j), rs.getString(j + 1));
                dates.add(tempDates);
                bookList.add(tempBook);
            }
            Disconnect();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void spUserCheckExistingUser() {
        try {
            Connect();
            statement = connection.createStatement();
            ResultSet rs = statement.executeQuery("EXEC spUserCheckExistingUser " + keyword + ";");
            rs.next();
            int i = rs.getInt(1);
            if (i == 1) {
                userAlreadyExists = true;
            } else {
                userAlreadyExists = false;
            }
            Disconnect();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void spUserDelete() {
        try {
            Connect();
            statement = connection.createStatement();
            statement.execute("EXEC spUserDelete " + keyword + ";");
            Disconnect();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void spRatingsSelectByBook() {
        try {
            usernames = new Vector<>();
            ratings = new Vector<>();
            Connect();
            statement = connection.createStatement();
            ResultSet rs = statement.executeQuery("EXEC spRatingsSelectByBook " + bookId + ";");
            while (rs.next()) {
                int i = 1;
                usernames.add(rs.getString(i++));
                ratings.add(rs.getInt(i++));
            }
            Disconnect();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void spRatingsDeleteByBook() {
        try {
            Connect();
            statement = connection.createStatement();
            statement.execute("EXEC spRatingsDeleteByBook " + bookId + "," + keyword + ";");
            Disconnect();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void spBorrowedBooksCheckBorrowed() {
        try {
            Connect();
            statement = connection.createStatement();
            ResultSet rs = statement.executeQuery("EXEC spBorrowedBooksCheckBorrowed " + keyword + ";");
            rs.next();
            int i = rs.getInt(1);
            if (i > 0)
                bookAlreadyBorrowed = true;
            else bookAlreadyBorrowed = false;
            Disconnect();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void spBorrowedBooksCheckDate() {
        try {
            Connect();
            statement = connection.createStatement();
            ResultSet rs = statement.executeQuery("EXEC spBorrowedBooksCheckDate " + keyword + ";");
            rs.next();
            int i = rs.getInt(1);
            if (i > 0)
                bookNotReturned = true;
            else bookNotReturned = false;
            Disconnect();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void spUserUpdate() {
        try {
            Connect();
            statement = connection.createStatement();
            statement.execute("EXEC spUserUpdate " + keyword + ";");
            Disconnect();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void spBooksDelete() {
        try {
            Connect();
            statement = connection.createStatement();
            statement.execute("EXEC spBooksDelete " + bookId + ";");
            Disconnect();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void spUserInsertAdmin() {
        try {
            Connect();
            statement = connection.createStatement();
            statement.execute("EXEC spUserInsertAdmin " + keyword + ";");
            Disconnect();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void spUserSearch() {
        try {
            userList = new Vector<>();
            Connect();
            statement = connection.createStatement();
            ResultSet rs = statement.executeQuery("EXEC spUserSearch '" + keyword + "';");
            while (rs.next()) {
                int i = 1;
                tempUser = new User();
                tempUser.setUserId(rs.getInt(i++));
                tempUser.setUsername(rs.getString(i++));
                tempUser.setPassword(rs.getString(i++));
                tempUser.setRankId(rs.getInt(i++));
                userList.add(tempUser);
            }
            Disconnect();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static String getKeyword() {
        return keyword;
    }

    public static void setKeyword(String keyword) {
        DatabaseOperations.keyword = keyword;
    }

    public static Vector<Book> getBookList() {
        return bookList;
    }

    public static Vector<User> getUserList() {
        return userList;
    }

    public static void setBookId(int bookId) {
        DatabaseOperations.bookId = bookId;
    }

    public static String getBookTags() {
        return bookTags;
    }

    public static boolean getLoginState() {
        return loginState;
    }

    public static boolean getUserAlreadyExists() {
        return userAlreadyExists;
    }

    public static User getTempUser() {
        return tempUser;
    }

    public static Vector<Pair<String, String>> getDates() {
        return dates;
    }

    public static Vector<String> getUsernames() {
        return usernames;
    }

    public static Vector<Integer> getRatings() {
        return ratings;
    }

    public static void SetTempUser(User user) {
        tempUser = user;
    }

    public static boolean isBookAlreadyBorrowed() {
        return bookAlreadyBorrowed;
    }

    public static boolean isBookNotReturned() {
        return bookNotReturned;
    }
}
