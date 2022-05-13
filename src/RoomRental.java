import java.sql.SQLException;
import java.util.Scanner;

public interface RoomRental {
    void rentRoom(Scanner scanner) throws SQLException,ClassNotFoundException;
    void removeRoom(Scanner scanner) throws SQLException,ClassNotFoundException;
    void editRoomDetails(Scanner scanner) throws SQLException,ClassNotFoundException;
}
