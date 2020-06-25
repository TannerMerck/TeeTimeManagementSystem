package sample;

public class User {
    public String username;
    public String password;
    public String permissions;

    public User(String username){
        this.username = username;
        getPermissions();
    }

    public User(String username, String password, String permissions){
        this.username = username;
        this.password = password;
        this.permissions = permissions;
        createUser(username, password, permissions);
    }

    public void createUser(String un, String pw, String perm){
        SqliteConnection.createUser(un, pw, perm);
    }

    public boolean login(String user, String pass){
        password = SqliteConnection.login(user);
        if(pass.equals(password)){
            return true;
        }else{
            return false;
        }
    }

    public String getPermissions(){
        permissions = SqliteConnection.getPermissions(username);
        return permissions;
    }

    public String getUsername() {
        return username;
    }
}
