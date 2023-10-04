package User;

public class User {
    private int userId;
    private int rankId;
    private String username,password;
    private boolean active=true;

    public User(int userId,int rankId,boolean active) {
        this.userId=userId;
        this.rankId=rankId;
       // User.username=username;
        //User.password=password;
        this.active=active;
    }

    public User() {
    }
    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public int getRankId() {
        return rankId;
    }

    public void setRankId(int rankId) {
        this.rankId = rankId;
    }
}
