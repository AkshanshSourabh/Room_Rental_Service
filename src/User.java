import java.sql.*;
import java.util.Scanner;

public class User {
    private int userId;
    protected String userName;
    private String userPassword;
    protected String userEmailId;
    protected String userPhoneNo;

    void setUserDetails(int userId, String userName, String userPassword,
                        String userEmailId, String userPhoneNo) throws ClassNotFoundException,SQLException{
        this.userId = userId;
        this.userName = userName;
        this.userPassword = userPassword;
        this.userEmailId = userEmailId;
        this.userPhoneNo = userPhoneNo;


            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/room_rental_service",
                    "root", "root"
            );
            PreparedStatement check = connection.prepareStatement(
                    "select * from guest where userId=1"
            );
            ResultSet rs = check.executeQuery();
            if (!rs.isBeforeFirst()) {
                PreparedStatement ps = connection.prepareStatement(
                        "insert into guest values(?,?,?,?,?)"
                );
                ps.setInt(1, userId);
                ps.setString(2, userName);
                ps.setString(3, userPassword);
                ps.setString(4, userEmailId);
                ps.setString(5, userPhoneNo);
                ps.executeUpdate();
                ps.close();
            } else {
                System.out.println("User Already exists!!!");
            }
            connection.close();

    }

    boolean login(Scanner scanner) throws SQLException,ClassNotFoundException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection connection = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/room_rental_service",
                "root", "root"
        );
        System.out.println("Enter user name: ");
        String name = scanner.next();
        System.out.println("Enter password: ");
        String password = scanner.next();



        PreparedStatement ps = connection.prepareStatement(
                "select * from guest"
        );
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            if ((name.equals(rs.getString(2))) && (password.equals(rs.getString(3)))) {
                return true;
            }
        } connection.close();
        return false;
    }

    void getUserDetails() throws SQLException,ClassNotFoundException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection connection = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/room_rental_service",
                "root", "root"
        );
        Statement st = connection.createStatement();
        ResultSet rs = st.executeQuery(
                "select * from guest"
        );
        while (rs.next()) {
            System.out.println("Name: " + rs.getString(2));
            System.out.println("Email: " + rs.getString(4));
            System.out.println("Phone Number: " + rs.getString(5));
        } connection.close();
    }

    public void registerUser(Scanner scanner) throws ClassNotFoundException,SQLException{
        System.out.print("Enter UserName: ");
        String userName = scanner.next();

        System.out.print("Enter Password: ");
        String userPassword = scanner.next();


        System.out.print("Enter Email ID: ");
        String userEmailId = scanner.next();

        System.out.print("Enter Phone No: ");
        String userPhoneNo = scanner.next();
        if (userPassword.length() <6) {
            try {
                throw new InvalidPasswordLengthException("Password length should be greater than 6!\n Register again");
            } catch (InvalidPasswordLengthException e) {
                System.out.println(e.toString());
                System.out.println("----------------------------------------------------");
                 registerUser(scanner);
            }}
        else
        {
        User user = new User();
        user.setUserDetails(1, userName, userPassword, userEmailId, userPhoneNo);
    }}
}