import java.sql.*;
import java.util.Scanner;

public class Host extends User implements RoomRental {

    @Override
    public void rentRoom(Scanner scanner) throws SQLException, ClassNotFoundException {
        System.out.print("Enter property name : ");
        String propertyName = scanner.next();

        System.out.print("Enter room Type  : ");
        String roomType = scanner.next();

        System.out.print("Enter room Size  : ");
        String roomSize = scanner.next();

        System.out.print("Enter Location   : ");
        String roomLocation = scanner.next();

        System.out.print("Enter Price      : ");
        int roomPrice = scanner.nextInt();

        System.out.print("Enter Facilities : ");
        String roomFacilities = scanner.next();

        System.out.print("Enter Near By    : ");
        String nearBy = scanner.next();

        System.out.println("\nRoom added Successfully!!");

        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection connection = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/room_rental_service",
                "root", "root"
        );
        PreparedStatement ps = connection.prepareStatement(
                "insert into room values(?,?,?,?,?,?,?,?,?)"
        );
        PreparedStatement pst = connection.prepareStatement(
                "select * from guest"
        );
        ResultSet rs = pst.executeQuery();
        while (rs.next()) {
            ps.setString(1, propertyName);
            ps.setString(2, rs.getString(2));
            ps.setString(3, roomType);
            ps.setString(4, roomSize);
            ps.setString(5, roomLocation);
            ps.setInt(6, roomPrice);
            ps.setString(7, roomFacilities);
            ps.setString(8, nearBy);
            ps.setString(9, "available");
        }
        ps.executeUpdate();
        ps.close();
        connection.close();
    }

    @Override
    public void removeRoom(Scanner scanner) throws SQLException, ClassNotFoundException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection connection = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/room_rental_service",
                "root", "root"
        );
        System.out.print("Enter property name: ");
        String name = scanner.next();
        Statement smt = connection.createStatement();
        smt.executeUpdate(
                "delete from room where propertyName='" + name + "'"
        );
        connection.close();
        System.out.println("\nRoom deleted Successfully!!");
    }

    @Override
    public void editRoomDetails(Scanner scanner) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/room_rental_service",
                    "root", "root"
            );
            System.out.print("Enter property name: ");
            String name = scanner.next();
            Statement smt = connection.createStatement();

            ResultSet rs = smt.executeQuery(
                    "select * from room where propertyName='" + name + "'"
            );
            if (rs.next()) {
                //to show the data
                System.out.println("1. Property name :" + rs.getString(1));

                System.out.println("2. Room Type:" + rs.getString(3));
                System.out.println("3. Room Size :" + rs.getString(4));
                System.out.println("4. Room Location :" + rs.getString(5));
                System.out.println("5. Room Price :" + rs.getInt(6));
                System.out.println("6. Room Facilities :" + rs.getString(7));
                System.out.println("7. Near By :" + rs.getString(8));
                System.out.println("8. Availability :" + rs.getString(9));
                System.out.println("9. Exit");
                System.out.println("Select your choice :");
                int ch = scanner.nextInt();
                String a = "";
                switch (ch) {
                    case 1:
                        System.out.print("Enter New property Name : ");
                        String n = scanner.next();
                        a = "propertyName='" + n + "'";
                        break;

                    case 2:
                        System.out.print("Enter New roomType : ");
                        String rt = scanner.next();
                        a = "roomType='" + rt + "'";
                        break;

                    case 3:
                        System.out.print("Enter New room Size : ");
                        String nrs = scanner.next();
                        a = "roomSize='" + nrs + "'";
                        break;

                    case 4:
                        System.out.print("Enter New room Location :");
                        String rl = scanner.next();
                        a = "roomLocation='" + rl + "'";
                        break;

                    case 5:
                        System.out.print("Enter New room Price : ");
                        String rp = scanner.next();
                        a = "roomPrice='" + rp + "'";
                        break;

                    case 6:
                        System.out.print("Enter New room Facilities : ");
                        String nf = scanner.next();
                        a = "roomFacilities='" + nf + "'";
                        break;
                    case 7:
                        System.out.print("Enter New Near by's : ");
                        String nb = scanner.next();
                        a = "nearBy='" + nb + "'";
                        break;

                    case 8:
                        System.out.print("Enter New Availability : ");
                        String ra = scanner.next();
                        a = "availability='" + ra + "'";
                        break;
                    case 9:
                        System.out.println("Exit");
                        break;


                    default:
                        System.out.println("Wrong Option");
                        break;
                }
                if (!a.equals("")) {
                    //query to edit data of a particular record from table employee

                    //to execute update
                    smt.executeUpdate("update room set " + a + " where propertyName='" + name + "'");
                    System.out.println("Record Updated Successfully!!");
                }
            } else {
                System.out.println("Record Not Found...");
            }
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

