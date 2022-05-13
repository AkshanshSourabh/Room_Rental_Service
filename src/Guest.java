import java.sql.*;

import java.util.Scanner;

public class Guest extends User implements BookRoom, Payment {

    @Override
    public void bookRoom(Scanner scanner) throws SQLException {
        String type = " ";

        int price = 0;
        int i = 1;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        Connection connection = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/room_rental_service",
                "root", "root"
        );
        Statement st = connection.createStatement();
        System.out.print("Enter location: ");
        String location = scanner.next();
        ResultSet rs = st.executeQuery(
                "select * from room where roomLocation='" + location + "' and availability='" + "available'"
        );
        while (rs.next()) {
            System.out.println("Room No " + i++ + "\n");
            System.out.println("Property Name: " + rs.getString(1));
            System.out.println("Host Name: " + rs.getString(2));
            System.out.println("Room Type: " + rs.getString(3));
            System.out.println("Room Size: " + rs.getString(4));
            System.out.println("Room Location: " + rs.getString(5));
            System.out.println("Room Price: " + rs.getInt(6));
            System.out.println("Room Facilities: " + rs.getString(7));
            System.out.println("Near By: " + rs.getString(8) + "\n");
        }
        System.out.print("Enter property name you wish to book: ");
        String name = scanner.next();

        System.out.print("Number of guest: ");
        int noOfGuest = scanner.nextInt();

        System.out.print("Check in date: ");
        String checkInDate = scanner.next();

        System.out.print("Check out date: ");
        String checkOutDate = scanner.next();

        if (noOfGuest > 4) {
            try {
                throw new InvalidGuestException("No of guests cannot be greater than 4 !");
            } catch (InvalidGuestException e) {
                System.out.println(e.toString());
                System.out.println("----------------------------------------------------\n");
                System.out.println("\t\tTRY AGAIN !\n");
                bookRoom(scanner);
            }
        } else {
            if (noOfGuest > 2) {
                System.out.println("You will be charged 700 /- per additional Guest(>2)");
                price += 700 * (noOfGuest - 2);
            }
        }

        PreparedStatement ps = connection.prepareStatement(
                "insert into bookedroom values(?,?,?,?,?,?,?,?,?,?)"
        );
        ResultSet roomRs = st.executeQuery(
                "select * from room where propertyName='" + name + "'"
        );
        while (roomRs.next()) {
            type += roomRs.getString(3);

            price += roomRs.getInt(6);
        }
        ResultSet userRs = st.executeQuery(
                "select * from guest"
        );
        while (userRs.next()) {
            ps.setString(1, userRs.getString(2));
            ps.setString(2, userRs.getString(4));
            ps.setString(3, userRs.getString(5));
            ps.setString(5, type.toString());
            ps.setString(4, name);
            ps.setString(6, location);
            ps.setInt(7, price);
            ps.setInt(8, noOfGuest);
            ps.setString(9, checkInDate);
            ps.setString(10, checkOutDate);
            ps.executeUpdate();
        }  connection.close();
        System.out.println("----------------------------------------------------");
        new Guest().getInvoice(scanner);
    }

    @Override
    public void getInvoice(Scanner scanner) throws SQLException {
        Connection connection = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/room_rental_service",
                "root", "root"
        );
        Statement smt = connection.createStatement();
        ResultSet rs = smt.executeQuery(
                "select * from bookedroom"
        );
        System.out.println("\n\t---FINAL INVOICE--\t\n");
        while (rs.next()) {
            System.out.println("Name: " + rs.getString(1));
            System.out.println("Email: " + rs.getString(2));
            System.out.println("phone: " + rs.getString(3));
            System.out.println("property name: " + rs.getString(4));
            System.out.println("room type: " + rs.getString(5));
            System.out.println("location: " + rs.getString(6));
            System.out.println("price: " + rs.getString(7));
            System.out.println("No of guest: " + rs.getString(8));
            System.out.println("check in : " + rs.getString(9));
            System.out.println("check out: " + rs.getString(10));


        }  System.out.println("----------------------------------------------------");
        System.out.println("Do you want to confirm booking?(1/0)");
        int ch=scanner.nextInt();
        if(ch==1) {
           String k="booked";
            Statement st = connection.createStatement();
           if(rs.next())
           {
            ResultSet r = smt.executeQuery(
                    "select * from room where propertyName='" + rs.getString(4) + "'"
            );}


                st.executeUpdate("update room set availability = '"+"booked"+"'");

        }

        connection.close();
        }
    }
