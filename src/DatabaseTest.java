import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DatabaseTest {

    public static void main(String[] args) {
        try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/library", "root", "Procrastinante42")) {

            // Query data from the 'books' table
            String query = "SELECT * FROM authors";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query);
                 ResultSet resultSet = preparedStatement.executeQuery()) {

                while (resultSet.next()) {
                    int id = resultSet.getInt("id");
                    String name = resultSet.getString("nam");
                    String birth = resultSet.getString("birth");
                    System.out.println("ID: " + id + ", Name: " + name + ", Birth: " + birth);
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}