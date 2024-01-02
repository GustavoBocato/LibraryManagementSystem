import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DatabaseTest {

    public static void main(String[] args) {
        try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/library","root","Procrastinante42")) {

            // Insert data into the 'authors' table
            String insertQuery = "INSERT INTO authors (nam, birth) VALUES (?, ?)";
            try (PreparedStatement preparedStatement = connection.prepareStatement(insertQuery)) {

                // Set values for the parameters
                preparedStatement.setString(1, "James Stweart");
                preparedStatement.setString(2, "1987-06-13");

                // Execute the insert query
                int rowsAffected = preparedStatement.executeUpdate();
                System.out.println(rowsAffected + " row(s) inserted.");

            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}