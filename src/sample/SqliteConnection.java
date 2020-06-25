package sample;

import java.sql.*;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

public class SqliteConnection {
    ResultSet rs;
    public static Connection Connector(){
        try{
            Class.forName("org.sqlite.JDBC");
            Connection conn = DriverManager.getConnection("jdbc:sqlite:TeeTimes.db");
            return conn;
        } catch(Exception e){
                System.out.println(e);
                return null;
        }
    }
    public List pullTime(String Tdate) throws SQLException {
        List<String> timelist = new ArrayList<String>();
        try (Connection conn = DriverManager.getConnection("jdbc:sqlite:TeeTimes.db");
             PreparedStatement pstmt = conn.prepareStatement("SELECT time FROM TeeSheetTable WHERE day = ?")){
            pstmt.setString(1, Tdate);
            rs = pstmt.executeQuery();
            while(rs.next()){
                timelist.add(rs.getString("time"));
            }
            return timelist;
        } catch(Exception e){
            System.out.println(e);
            return null;
        }
    }

    public static String createTeeTime(String time, String name, int players, String date, int checkedIn, int memNum){
        try(Connection conn = DriverManager.getConnection("jdbc:sqlite:TeeTimes.db");
            PreparedStatement pstmt = conn.prepareStatement("INSERT INTO TeeSheetTable(time, name, players, day, checkedin, MemberNumber) Values(?,?,?,?,?,?)")){
            pstmt.setString(1, time);
            pstmt.setString(2, name);
            pstmt.setInt(3, players);
            pstmt.setString(4, date);
            pstmt.setInt(5, checkedIn);
            pstmt.setInt(6, memNum);
            pstmt.executeUpdate();
            return time + " time created";
        } catch(Exception e){
            return e.getMessage();
        }
    }

    public static void addMemNum2(int memNum, String time, String date){
        String sql = "UPDATE TeeSheetTable SET MemberNumber2 = ? WHERE time = ? AND day = ?";
        try(Connection conn = DriverManager.getConnection("jdbc:sqlite:TeeTimes.db");
            PreparedStatement pstmt = conn.prepareStatement(sql)){
            pstmt.setInt(1, memNum);
            pstmt.setString(2, time);
            pstmt.setString(3, date);
            pstmt.executeUpdate();
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }
    }

    public static void addMemNum3(int memNum, String time, String date){
        String sql = "UPDATE TeeSheetTable SET MemberNumber3 = ? WHERE time = ? AND day = ?";
        try(Connection conn = DriverManager.getConnection("jdbc:sqlite:TeeTimes.db");
            PreparedStatement pstmt = conn.prepareStatement(sql)){
            pstmt.setInt(1, memNum);
            pstmt.setString(2, time);
            pstmt.setString(3, date);
            pstmt.executeUpdate();
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }
    }

    public static void addMemNum4(int memNum, String time, String date){
        String sql = "UPDATE TeeSheetTable SET MemberNumber4 = ? WHERE time = ? AND day = ?";
        try(Connection conn = DriverManager.getConnection("jdbc:sqlite:TeeTimes.db");
            PreparedStatement pstmt = conn.prepareStatement(sql)){
            pstmt.setInt(1, memNum);
            pstmt.setString(2, time);
            pstmt.setString(3, date);
            pstmt.executeUpdate();
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }
    }

    public static void addPlayer2(String player2, String time, String date){
        String sql = "UPDATE TeeSheetTable SET player2 = ? WHERE time = ? AND day = ?";
        try(Connection conn = DriverManager.getConnection("jdbc:sqlite:TeeTimes.db");
            PreparedStatement pstmt = conn.prepareStatement(sql)){
            pstmt.setString(1, player2);
            pstmt.setString(2, time);
            pstmt.setString(3, date);
            pstmt.executeUpdate();
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }
    }

    public static void addPlayer3(String player3, String time, String date){
        String sql = "UPDATE TeeSheetTable SET player3 = ? WHERE time = ? AND day = ?";
        try(Connection conn = DriverManager.getConnection("jdbc:sqlite:TeeTimes.db");
            PreparedStatement pstmt = conn.prepareStatement(sql)){
            pstmt.setString(1, player3);
            pstmt.setString(2, time);
            pstmt.setString(3, date);
            pstmt.executeUpdate();
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }
    }

    public static void addPlayer4(String player4, String time, String date){
        String sql = "UPDATE TeeSheetTable SET player4 = ? WHERE time = ? AND day = ?";
        try(Connection conn = DriverManager.getConnection("jdbc:sqlite:TeeTimes.db");
            PreparedStatement pstmt = conn.prepareStatement(sql)){
            pstmt.setString(1, player4);
            pstmt.setString(2, time);
            pstmt.setString(3, date);
            pstmt.executeUpdate();
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }
    }

    public static String pullPlayer2(String Ttime, String Tdate){
        try (Connection conn = DriverManager.getConnection("jdbc:sqlite:TeeTimes.db");
             PreparedStatement pstmt = conn.prepareStatement("SELECT player2 FROM TeeSheetTable WHERE time = ? AND day = ?")){
            pstmt.setString(1, Ttime);
            pstmt.setString(2, Tdate);
            ResultSet rs = pstmt.executeQuery();
            String name2 = rs.getString("player2");
            return name2;
        } catch(Exception e){
            System.out.println(e);
            return null;
        }
    }

    public static String pullPlayer3(String Ttime, String Tdate){
        try (Connection conn = DriverManager.getConnection("jdbc:sqlite:TeeTimes.db");
             PreparedStatement pstmt = conn.prepareStatement("SELECT player3 FROM TeeSheetTable WHERE time = ? AND day = ?")){
            pstmt.setString(1, Ttime);
            pstmt.setString(2, Tdate);
            ResultSet rs = pstmt.executeQuery();
            String name3 = rs.getString("player3");
            return name3;
        } catch(Exception e){
            System.out.println(e);
            return null;
        }
    }

    public static String pullPlayer4(String Ttime, String Tdate){
        try (Connection conn = DriverManager.getConnection("jdbc:sqlite:TeeTimes.db");
             PreparedStatement pstmt = conn.prepareStatement("SELECT player4 FROM TeeSheetTable WHERE time = ? AND day = ?")){
            pstmt.setString(1, Ttime);
            pstmt.setString(2, Tdate);
            ResultSet rs = pstmt.executeQuery();
            String name4 = rs.getString("player4");
            return name4;
        } catch(Exception e){
            System.out.println(e);
            return null;
        }
    }

    public String pullCertainTimeName(String Ttime, String Tdate){
        try (Connection conn = DriverManager.getConnection("jdbc:sqlite:TeeTimes.db");
             PreparedStatement pstmt = conn.prepareStatement("SELECT name FROM TeeSheetTable WHERE time = ? AND day = ?")){
             pstmt.setString(1, Ttime);
            pstmt.setString(2, Tdate);
             ResultSet rs = pstmt.executeQuery();
            String nameDB = rs.getString("name");
             return nameDB;
        } catch(Exception e){
            System.out.println(e);
            return null;
        }
    }

    public int pullCertainTimePlayers(String Ttime, String Tdate){
        try (Connection conn = DriverManager.getConnection("jdbc:sqlite:TeeTimes.db");
             PreparedStatement pstmt = conn.prepareStatement("SELECT players FROM TeeSheetTable WHERE time = ? AND day = ?")){
            pstmt.setString(1, Ttime);
            pstmt.setString(2, Tdate);
            ResultSet rs = pstmt.executeQuery();
            int playersDB = rs.getInt("players");
            return playersDB;
        } catch(Exception e){
            System.out.println(e);
            return 0;
        }
    }

    public String pullCertainTimeDate(String Ttime){
        try (Connection conn = DriverManager.getConnection("jdbc:sqlite:TeeTimes.db");
             PreparedStatement pstmt = conn.prepareStatement("SELECT time, name, players, day FROM TeeSheetTable WHERE time = ?")){
            pstmt.setString(1, Ttime);
            ResultSet rs = pstmt.executeQuery();
            String dateDB = rs.getString("day");
            return dateDB;
        } catch(Exception e){
            System.out.println(e);
            return null;
        }
    }

    public int pullCertainTimeCheckedIn(String Ttime, String Tdate){
        try (Connection conn = DriverManager.getConnection("jdbc:sqlite:TeeTimes.db");
             PreparedStatement pstmt = conn.prepareStatement("SELECT checkedin FROM TeeSheetTable WHERE time = ? AND day = ?")){
            pstmt.setString(1, Ttime);
            pstmt.setString(2, Tdate);
            ResultSet rs = pstmt.executeQuery();
            int checkedinDB = rs.getInt("checkedin");
            return checkedinDB;
        } catch(Exception e){
            System.out.println(e);
            return 0;
        }
    }
    public int pullCheckedInP2(String Ttime, String Tdate){
        try (Connection conn = DriverManager.getConnection("jdbc:sqlite:TeeTimes.db");
             PreparedStatement pstmt = conn.prepareStatement("SELECT checkedin2 FROM TeeSheetTable WHERE time = ? AND day = ?")){
            pstmt.setString(1, Ttime);
            pstmt.setString(2, Tdate);
            ResultSet rs = pstmt.executeQuery();
            int checkedinDB = rs.getInt("checkedin2");
            return checkedinDB;
        } catch(Exception e){
            System.out.println(e);
            return 0;
        }
    }
    public int pullCheckedInP3(String Ttime, String Tdate){
        try (Connection conn = DriverManager.getConnection("jdbc:sqlite:TeeTimes.db");
             PreparedStatement pstmt = conn.prepareStatement("SELECT checkedin3 FROM TeeSheetTable WHERE time = ? AND day = ?")){
            pstmt.setString(1, Ttime);
            pstmt.setString(2, Tdate);
            ResultSet rs = pstmt.executeQuery();
            int checkedinDB = rs.getInt("checkedin3");
            return checkedinDB;
        } catch(Exception e){
            System.out.println(e);
            return 0;
        }
    }
    public int pullCheckedInP4(String Ttime, String Tdate){
        try (Connection conn = DriverManager.getConnection("jdbc:sqlite:TeeTimes.db");
             PreparedStatement pstmt = conn.prepareStatement("SELECT checkedin4 FROM TeeSheetTable WHERE time = ? AND day = ?")){
            pstmt.setString(1, Ttime);
            pstmt.setString(2, Tdate);
            ResultSet rs = pstmt.executeQuery();
            int checkedinDB = rs.getInt("checkedin4");
            return checkedinDB;
        } catch(Exception e){
            System.out.println(e);
            return 0;
        }
    }

    public int pullCertainTimeMemberNumber(String Ttime, String Tdate){
        try (Connection conn = DriverManager.getConnection("jdbc:sqlite:TeeTimes.db");
             PreparedStatement pstmt = conn.prepareStatement("SELECT MemberNumber FROM TeeSheetTable WHERE time = ? AND day = ?")){
            pstmt.setString(1, Ttime);
            pstmt.setString(2, Tdate);
            ResultSet rs = pstmt.executeQuery();
            int memNumDB = rs.getInt("MemberNumber");
            return memNumDB;
        } catch(Exception e){
            System.out.println(e);
            return 0;
        }
    }

    public int pullCertainTimeMemberNumberP2(String Ttime, String Tdate){
        try (Connection conn = DriverManager.getConnection("jdbc:sqlite:TeeTimes.db");
             PreparedStatement pstmt = conn.prepareStatement("SELECT MemberNumber2 FROM TeeSheetTable WHERE time = ? AND day = ?")){
            pstmt.setString(1, Ttime);
            pstmt.setString(2, Tdate);
            ResultSet rs = pstmt.executeQuery();
            int memNumDB = rs.getInt("MemberNumber2");
            return memNumDB;
        } catch(Exception e){
            System.out.println(e);
            return 0;
        }
    }

    public int pullCertainTimeMemberNumberP3(String Ttime, String Tdate){
        try (Connection conn = DriverManager.getConnection("jdbc:sqlite:TeeTimes.db");
             PreparedStatement pstmt = conn.prepareStatement("SELECT MemberNumber3 FROM TeeSheetTable WHERE time = ? AND day = ?")){
            pstmt.setString(1, Ttime);
            pstmt.setString(2, Tdate);
            ResultSet rs = pstmt.executeQuery();
            int memNumDB = rs.getInt("MemberNumber3");
            return memNumDB;
        } catch(Exception e){
            System.out.println(e);
            return 0;
        }
    }

    public int pullCertainTimeMemberNumberP4(String Ttime, String Tdate){
        try (Connection conn = DriverManager.getConnection("jdbc:sqlite:TeeTimes.db");
             PreparedStatement pstmt = conn.prepareStatement("SELECT MemberNumber4 FROM TeeSheetTable WHERE time = ? AND day = ?")){
            pstmt.setString(1, Ttime);
            pstmt.setString(2, Tdate);
            ResultSet rs = pstmt.executeQuery();
            int memNumDB = rs.getInt("MemberNumber4");
            return memNumDB;
        } catch(Exception e){
            System.out.println(e);
            return 0;
        }
    }

    public static boolean checkTime(String time, String date){
        try (Connection conn = DriverManager.getConnection("jdbc:sqlite:TeeTimes.db");
             PreparedStatement pstmt = conn.prepareStatement("SELECT COUNT(1) FROM TeeSheetTable WHERE time = ? AND day = ?")){
            pstmt.setString(1, time);
            pstmt.setString(2, date);
            ResultSet rs = pstmt.executeQuery();
            if(rs.getInt(1) == 0){
                return false;
            }else {
                return true;
            }
        } catch(Exception e){
            System.out.println(e);
            return false;
        }
    }

    public static void deleteTime(String time, String date) throws SQLException {
        try(Connection conn = DriverManager.getConnection("jdbc:sqlite:TeeTimes.db");
            PreparedStatement pstmt = conn.prepareStatement("DELETE FROM TeeSheetTable WHERE time = ? AND day = ?")){
            pstmt.setString(1, time);
            pstmt.setString(2, date);
            pstmt.executeUpdate();
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }
    }

    public static void checkInTime(String time, String date){
        String sql = "UPDATE TeeSheetTable SET checkedin = 1 WHERE time = ? AND day = ?";
        try(Connection conn = DriverManager.getConnection("jdbc:sqlite:TeeTimes.db");
            PreparedStatement pstmt = conn.prepareStatement(sql)){
            pstmt.setString(1, time);
            pstmt.setString(2, date);
            pstmt.executeUpdate();
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }
    }

    public static void checkInP2(String time, String date){
        String sql = "UPDATE TeeSheetTable SET checkedin2 = 1 WHERE time = ? AND day = ?";
        try(Connection conn = DriverManager.getConnection("jdbc:sqlite:TeeTimes.db");
            PreparedStatement pstmt = conn.prepareStatement(sql)){
            pstmt.setString(1, time);
            pstmt.setString(2, date);
            pstmt.executeUpdate();
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }
    }
    public static void checkInP3(String time, String date){
        String sql = "UPDATE TeeSheetTable SET checkedin3 = 1 WHERE time = ? AND day = ?";
        try(Connection conn = DriverManager.getConnection("jdbc:sqlite:TeeTimes.db");
            PreparedStatement pstmt = conn.prepareStatement(sql)){
            pstmt.setString(1, time);
            pstmt.setString(2, date);
            pstmt.executeUpdate();
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }
    }
    public static void checkInP4(String time, String date){
        String sql = "UPDATE TeeSheetTable SET checkedin4 = 1 WHERE time = ? AND day = ?";
        try(Connection conn = DriverManager.getConnection("jdbc:sqlite:TeeTimes.db");
            PreparedStatement pstmt = conn.prepareStatement(sql)){
            pstmt.setString(1, time);
            pstmt.setString(2, date);
            pstmt.executeUpdate();
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }
    }


    //Member Class SQL
    public int addmember(String name, String phoneNum, String address, String memType){
        try(Connection conn = DriverManager.getConnection("jdbc:sqlite:TeeTimes.db");
            PreparedStatement pstmt = conn.prepareStatement("INSERT INTO MemberTable(name, phoneNum, address, memType) Values(?,?,?,?)")){
            pstmt.setString(1, name);
            pstmt.setString(2, phoneNum);
            pstmt.setString(3, address);
            pstmt.setString(4, memType);
            pstmt.executeUpdate();
        } catch(Exception e){
            e.getMessage();
        }
        return pullMemberNumber(name);
    }
    public List pullMember() throws SQLException {
        List<String> memberlist = new ArrayList<String>();
        try (Connection conn = DriverManager.getConnection("jdbc:sqlite:TeeTimes.db");
             PreparedStatement pstmt = conn.prepareStatement("SELECT name FROM MemberTable")){
            rs = pstmt.executeQuery();
            while(rs.next()){
                memberlist.add(rs.getString("name"));
            }
            return memberlist;
        } catch(Exception e){
            System.out.println(e);
            return null;
        }
    }
    public List pullMemberByNumber() throws SQLException {
        List<Integer> memberlist = new ArrayList<Integer>();
        try (Connection conn = DriverManager.getConnection("jdbc:sqlite:TeeTimes.db");
             PreparedStatement pstmt = conn.prepareStatement("SELECT memNum FROM MemberTable")){
            rs = pstmt.executeQuery();
            while(rs.next()){
                memberlist.add(rs.getInt("memNum"));
            }
            return memberlist;
        } catch(Exception e){
            System.out.println(e);
            return null;
        }
    }
    public int pullMemberNumber(String name){
        try (Connection conn = DriverManager.getConnection("jdbc:sqlite:TeeTimes.db");
             PreparedStatement pstmt = conn.prepareStatement("SELECT memNum FROM MemberTable WHERE name = ?")){
            pstmt.setString(1, name);
            ResultSet rs = pstmt.executeQuery();
            int MemberNumber = rs.getInt("memNum");
            return MemberNumber;
        } catch(Exception e){
            System.out.println(e);
            return 0;
        }
    }
    public String pullMemberName(int memNum){
        try (Connection conn = DriverManager.getConnection("jdbc:sqlite:TeeTimes.db");
             PreparedStatement pstmt = conn.prepareStatement("SELECT name FROM MemberTable WHERE memNum = ?")){
            pstmt.setInt(1, memNum);
            ResultSet rs = pstmt.executeQuery();
            String MemberName = rs.getString("name");
            return MemberName;
        } catch(Exception e){
            System.out.println(e);
            return "";
        }
    }
    public String pullphoneNum(int memNum){
        try (Connection conn = DriverManager.getConnection("jdbc:sqlite:TeeTimes.db");
             PreparedStatement pstmt = conn.prepareStatement("SELECT phoneNum FROM MemberTable WHERE memNum = ?")){
            pstmt.setInt(1, memNum);
            ResultSet rs = pstmt.executeQuery();
            String phoneNumDB = rs.getString("phoneNum");
            return phoneNumDB;
        } catch(Exception e){
            System.out.println(e);
            return null;
        }
    }
    public String pulladdress(int memNum){
        try (Connection conn = DriverManager.getConnection("jdbc:sqlite:TeeTimes.db");
             PreparedStatement pstmt = conn.prepareStatement("SELECT address FROM MemberTable WHERE memNum = ?")){
            pstmt.setInt(1, memNum);
            ResultSet rs = pstmt.executeQuery();
            String addressDB = rs.getString("address");
            return addressDB;
        } catch(Exception e){
            System.out.println(e);
            return null;
        }
    }
    public String pullmemType(int memNum){
        try (Connection conn = DriverManager.getConnection("jdbc:sqlite:TeeTimes.db");
             PreparedStatement pstmt = conn.prepareStatement("SELECT memType FROM MemberTable WHERE memNum = ?")){
            pstmt.setInt(1, memNum);
            ResultSet rs = pstmt.executeQuery();
            String memTypeDB = rs.getString("memType");
            return memTypeDB;
        } catch(Exception e){
            System.out.println(e);
            return null;
        }
    }

    //Users class
    public static String login(String username){
        try (Connection conn = DriverManager.getConnection("jdbc:sqlite:TeeTimes.db");
             PreparedStatement pstmt = conn.prepareStatement("SELECT password FROM Users WHERE username = ?")){
            pstmt.setString(1, username);
            ResultSet rs = pstmt.executeQuery();
            String pw = rs.getString("password");
            return pw;
        } catch(Exception e){
            System.out.println(e);
            return null;
        }
    }

    public static String getPermissions(String username){
        try (Connection conn = DriverManager.getConnection("jdbc:sqlite:TeeTimes.db");
             PreparedStatement pstmt = conn.prepareStatement("SELECT permissions FROM Users WHERE username = ?")){
            pstmt.setString(1, username);
            ResultSet rs = pstmt.executeQuery();
            String perm = rs.getString("permissions");
            return perm;
        } catch(Exception e){
            System.out.println(e);
            return null;
        }
    }

    public static void createUser(String un, String pw, String perm){
        try(Connection conn = DriverManager.getConnection("jdbc:sqlite:TeeTimes.db");
            PreparedStatement pstmt = conn.prepareStatement("INSERT INTO Users(username, password, permissions) Values(?,?,?)")){
            pstmt.setString(1, un);
            pstmt.setString(2, pw);
            pstmt.setString(3, perm);
            pstmt.executeUpdate();
        } catch(Exception e){
            e.getMessage();
        }
    }

    public List pullUsers() throws SQLException {
        List<String> userlist = new ArrayList<String>();
        try (Connection conn = DriverManager.getConnection("jdbc:sqlite:TeeTimes.db");
             PreparedStatement pstmt = conn.prepareStatement("SELECT username FROM Users")){
            rs = pstmt.executeQuery();
            while(rs.next()){
                userlist.add(rs.getString("username"));
            }
            return userlist;
        } catch(Exception e){
            System.out.println(e);
            return null;
        }
    }
}
