package sample;

public class Member {
    public String name;
    public int memNum;
    public String phoneNum;
    public String address;
    public String memType;

    public Member(String name, String phoneNum, String address, String memType){
        this.name = name;
        this.phoneNum = phoneNum;
        this.address = address;
        this.memType = memType;
        addMember();
    }
    public Member(String name){
        this.name = name;
        pullmemNum(name);
        pullphoneNum(memNum);
        pulladdress(memNum);
        pullmemType(memNum);
    }
    public Member(int memNum){
        this.memNum = memNum;
        pullname(memNum);
        pullphoneNum(memNum);
        pulladdress(memNum);
        pullmemType(memNum);
    }

    public void addMember(){
        SqliteConnection sqlite = new SqliteConnection();
        memNum = sqlite.addmember(name, phoneNum, address, memType);
    }

    public void pullmemNum(String name){
        SqliteConnection sqlite = new SqliteConnection();
        memNum = sqlite.pullMemberNumber(name);
    }

    public void pullname(int memNum){
        SqliteConnection sqlite = new SqliteConnection();
        name = sqlite.pullMemberName(memNum);
    }

    public void pullphoneNum(int memNum){
        SqliteConnection sqlite = new SqliteConnection();
        phoneNum = sqlite.pullphoneNum(memNum);
    }

    public void pulladdress(int memNum){
        SqliteConnection sqlite = new SqliteConnection();
        address = sqlite.pulladdress(memNum);
    }

    public void pullmemType(int memNum){
        SqliteConnection sqlite = new SqliteConnection();
        memType = sqlite.pullmemType(memNum);
    }



    public String getName() {
        return name;
    }

    public int getMemNum() {
        return memNum;
    }

    public String getPhoneNum() {
        return phoneNum;
    }

    public String getAddress() {
        return address;
    }

    public String getMemType() {
        return memType;
    }
}
