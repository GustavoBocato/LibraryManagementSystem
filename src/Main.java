import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        String localDoArquivoDeConexao = "src/conexao";

        Properties propriedadesDoArquivo = Utilitarios.lePropriedadesDoArquvio(localDoArquivoDeConexao);

        if(propriedadesDoArquivo.isEmpty()){

            System.out.println("Seja bem vindo ao sistema da livraria.");
            System.out.println("Entre uma url da base de dados a ser usada pela livraria:");
            Scanner sc = new Scanner(System.in);
            propriedadesDoArquivo.setProperty("database.url", sc.next());
            System.out.println("Entre o usuário da base de dados a ser usada pela livraria:");
            propriedadesDoArquivo.setProperty("database.user", sc.next());
            System.out.println("Entre a senha desse usuário:");
            propriedadesDoArquivo.setProperty("database.password", sc.next());

            Utilitarios.escrevePropriedadesDoArquivo(propriedadesDoArquivo, localDoArquivoDeConexao);

        }else{

            System.out.println("Seja bem vindo de volta.");

        }

        String url = propriedadesDoArquivo.getProperty("database.url");
        String user = propriedadesDoArquivo.getProperty("database.user");
        String password = propriedadesDoArquivo.getProperty("database.password");

        try {

            // Establish a connection
            Connection connection = DriverManager.getConnection(url, user, password);



            // Close the connection when done
            connection.close();

        } catch (SQLException e) {

            e.printStackTrace();

        }
        
    }

}
