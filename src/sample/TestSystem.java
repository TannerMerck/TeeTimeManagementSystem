package sample;
import org.junit.Test;


import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;


import static org.junit.Assert.assertEquals;

public class TestSystem {

    @Test
    public void testpullTeeTime() throws SQLException {
        LocalTime testTime = LocalTime.of(9, 20);
        LocalDate testDate = LocalDate.of(2020, 03, 15);
        TeeTime teetime = new TeeTime(testTime, testDate);
        assertEquals("09:20", String.valueOf(teetime.getTime()));
        assertEquals("Billy3", teetime.getName());
        assertEquals(3, teetime.getAmtPlayers());
        assertEquals("2020-03-15", String.valueOf(teetime.getDate()));
    }

    @Test
    public void testGettingTimeFromDB() throws SQLException{
        LocalTime testTime = LocalTime.of(9, 10);
        LocalDate testDate = LocalDate.of(2020, 03, 15);
        TeeTime teetime = new TeeTime(testTime, testDate);
        assertEquals("09:10", String.valueOf(teetime.getTime()));
    }

    @Test
    public void testGettingNameFromDB() throws SQLException{
        LocalTime testTime = LocalTime.of(9, 20);
        LocalDate testDate = LocalDate.of(2020, 03, 15);
        TeeTime teetime = new TeeTime(testTime, testDate);
        assertEquals("Billy3", teetime.getName());
    }

    @Test
    public void testGettingNameFromDBTanter() throws SQLException{
        LocalTime testTime = LocalTime.of(9, 40);
        LocalDate testDate = LocalDate.of(2020, 04, 9);
        TeeTime teetime = new TeeTime(testTime, testDate);
        assertEquals("Tanter", teetime.getName());
    }

    @Test
    public void testGettingPlayersFromDB() throws SQLException{
        LocalTime testTime = LocalTime.of(9, 20);
        LocalDate testDate = LocalDate.of(2020, 03, 15);
        TeeTime teetime = new TeeTime(testTime, testDate);
        assertEquals(3, teetime.getAmtPlayers());
    }

    @Test
    public void testGettingDateFromDB() throws SQLException {
        LocalTime testTime = LocalTime.of(9, 20);
        LocalDate testDate = LocalDate.of(2020, 03, 15);
        TeeTime teetime = new TeeTime(testTime, testDate);
        assertEquals("2020-03-15", String.valueOf(teetime.getDate()));
    }
    @Test
    public void testGettingCheckedInFromDB() throws SQLException {
        LocalTime testTime = LocalTime.of(9, 20);
        LocalDate testDate = LocalDate.of(2020, 03, 15);
        TeeTime teetime = new TeeTime(testTime, testDate);
        assertEquals(false, teetime.isCheckedIn());
    }

    @Test
    public void testGettingCheckedInFromDBtrue() throws SQLException {
        LocalTime testTime = LocalTime.of(9, 10);
        LocalDate testDate = LocalDate.of(2020, 03, 15);
        TeeTime teetime = new TeeTime(testTime, testDate);
        assertEquals(true, teetime.isCheckedIn());
    }

    @Test
    public void testCreatingTeeTime(){
        LocalDate date = LocalDate.of(2020, 4, 10);
        LocalTime time = LocalTime.of(9,40);
        SqliteConnection sqlite = new SqliteConnection();
        if(sqlite.checkTime(String.valueOf(time), String.valueOf(date))==false) {
            TeeTime teetime = new TeeTime(time, "Tanter", 1, date, false, 0);
            teetime.storeTeeTime();
            System.out.println(time + " time created");
        }else{
            System.out.println(time + " already exists");
        }
    }

    @Test
    public void testPullingAllTimes() throws SQLException {
        LocalDate date = LocalDate.of(2020, 03, 15);
        List<String> timelist;
        SqliteConnection sqlite = new SqliteConnection();
        timelist = sqlite.pullTime(String.valueOf(date));
        for (int counter = 0; counter < timelist.size(); counter++) {
            System.out.println(timelist.get(counter));
        }
    }

    @Test
    public void testDeletingTime() throws SQLException {
        LocalDate date = LocalDate.of(2020, 03, 15);
        LocalTime time = LocalTime.of(12,00);
        TeeTime teetime = new TeeTime(time,"Delete Pls",3,date, false, 0);
        teetime.deleteTime();
    }

    @Test
    public void testCheckInTime() throws SQLException{
        LocalTime Ttime = LocalTime.of(02, 00);
        LocalDate Tdate = LocalDate.of(2020, 03, 16);
        TeeTime teetime = new TeeTime(Ttime, "Test checkin", 3, Tdate, false, 0);
        assertEquals(true, teetime.checkInTeeTime());
    }

    //Member tests below

    @Test
    public void addMember(){
        Member member = new Member("Ron Swanson", "123-456-7890", "123 Pawnee Rd", "Social");
        assertEquals("Ron Swanson", member.getName());
        assertEquals("123-456-7890", member.getPhoneNum());
        assertEquals("123 Pawnee Rd", member.getAddress());
        assertEquals("Social", member.getMemType());
        assertEquals(4, member.getMemNum());
    }

    @Test
    public void pullmemNumberFromDB(){
        Member member1 = new Member("Ron Swanson");
        assertEquals(4, member1.getMemNum());
    }

    @Test
    public void pullphoneNumFromDB(){
        Member member1 = new Member("Ron Swanson");
        assertEquals("123-456-7890", member1.getPhoneNum());
    }

    @Test
    public void pulladdressFromDB(){
        Member member1 = new Member("Ron Swanson");
        assertEquals("123 Pawnee Rd", member1.getAddress());
    }

    @Test
    public void pullmemTypeFromDB(){
        Member member1 = new Member("Ron Swanson");
        assertEquals("Social", member1.getMemType());
    }

    @Test
    public void pullMemTypeFromTeeTimeClass(){
        LocalTime Ttime = LocalTime.of(02, 00);
        LocalDate Tdate = LocalDate.of(2020, 03, 16);
        TeeTime teetime = new TeeTime(Ttime, "Test checkin", 3, Tdate, false, 4);
        assertEquals("M1", teetime.getMemType());
    }

    @Test
    public void testUserLogin(){
        User user = new User("admin");
        assertEquals(true, user.login("admin", "admin"));
    }

    @Test
    public void testFailedUserLogin(){
        User user = new User("admin");
        assertEquals(false, user.login("admin", "password"));
    }

    @Test
    public void testGetPermissions(){
        User user = new User("admin");
        assertEquals("admin", user.getPermissions());
    }

    @Test
    public void testCreateUser(){
        User user = new User("Test", "123", "admin");
    }

    @Test
    public void testAddingPlayer2() throws SQLException {
        LocalTime t = LocalTime.of(8, 20);
        LocalDate d = LocalDate.of(2020, 03, 29);
        TeeTime teetime = new TeeTime(t, d);
        teetime.setPlayer2("Dwight Schrute");
    }

    @Test
    public void testAddingPlayer3() throws SQLException{
        LocalTime t = LocalTime.of(9, 00);
        LocalDate d = LocalDate.of(2020, 04, 01);
        TeeTime teetime = new TeeTime(t, d);
        teetime.setPlayer3("Tiger Woods");
    }

    @Test
    public void testAddingPlayer4() throws SQLException{
        LocalTime t = LocalTime.of(9, 00);
        LocalDate d = LocalDate.of(2020, 04, 01);
        TeeTime teetime = new TeeTime(t, d);
        teetime.setPlayer4("Jordan Speith");
    }

    @Test
    public void addTime(){
        LocalTime time = LocalTime.of(8, 00);
        LocalDate date = LocalDate.of(2020, 04, 22);
        TeeTime teetime = new TeeTime(time, "Phil", 1, date, false, 0);
    }
}
