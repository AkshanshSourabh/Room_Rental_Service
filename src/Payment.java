import java.sql.SQLException;
import java.util.Scanner;

public interface Payment {
    void getInvoice(Scanner scanner) throws SQLException;
}
