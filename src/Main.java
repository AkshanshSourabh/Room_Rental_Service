import java.sql.*;
import java.util.*;

import static java.lang.System.exit;

public class Main {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("---------- WELCOME TO ROOM RENTAL SERVICE ----------");
            System.out.println("1. Sign Up");
            System.out.println("2. Sign In");
            System.out.println("3. Exit");
            System.out.println("Enter your choice: ");

            int choice = scanner.nextInt();
            System.out.println("----------------------------------------------------");
            if (choice == 1) {
                new User().registerUser(scanner);
            } else if (choice == 2) {
                try {
                    if (new User().login(scanner)) {
                        System.out.println("Logged in successfully !");
                        System.out.println("----------------------------------------------------");
                        System.out.println("1. Book Room");
                        System.out.println("2. Host");
                        System.out.println("3. My Account");
                        System.out.println("4. Exit");
                        System.out.println("Enter your choice: ");
                        int loginChoice = scanner.nextInt();
                        switch (loginChoice) {
                            case 1:
                                new Guest().bookRoom(scanner);
                                break;
                            case 2:
                                System.out.println("1. Rent a room");
                                System.out.println("2. Remove room");
                                System.out.println("3. Edit room details");
                                System.out.println("Enter your choice: ");

                                int ch= scanner.nextInt();

                                if (ch == 1) {
                                    new Host().rentRoom(scanner);
                                    System.out.println("----------------------------------------------------");
                                } else if (ch == 2) {
                                    new Host().removeRoom(scanner);
                                    System.out.println("----------------------------------------------------");
                                }
                                else if (ch == 3) {
                                    new Host().editRoomDetails(scanner);
                                    System.out.println("----------------------------------------------------");
                                }
                                break;
                            case 3:
                                new User().getUserDetails();
                                System.out.println("----------------------------------------------------");
                                break;
                            case 4:
                                System.out.println("THANK YOU!!!");
                                System.out.println("----------------------------------------------------");
                                exit(0);
                        }
                    } else {
                        System.out.println("Invalid username or password!!!!");
                        System.out.println("----------------------------------------------------");
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else if (choice == 3) {
                System.out.println("THANK YOU!!!");
                System.out.println("----------------------------------------------------");
                exit(0);
            }
        }
    }
}
