import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import java.util.Scanner;
import java.sql.Statement;

public class Main {
    public static void main(String[] args) {

        // configurando um arquivo para armazenar as informações de conexão com o banco de dados MySQL
        String localDoArquivoDeConexao = "conexao";

        Properties propriedadesDoArquivo = Utilitarios.lePropriedadesDoArquvio(localDoArquivoDeConexao);

        System.out.println("Seja bem vindo ao sistema da livraria.");

        if(!propriedadesDoArquivo.isEmpty()){

            // se o arquivo de conexão já está preenchido dar opção para o usuário trocar de conexão

            if(Menus.querTrocarDeConexao()){

                propriedadesDoArquivo = Utilitarios.pegaPropriedadesConexao(propriedadesDoArquivo);

                Utilitarios.escrevePropriedadesDoArquivo(propriedadesDoArquivo, localDoArquivoDeConexao);

            }

        }else{

            propriedadesDoArquivo = Utilitarios.pegaPropriedadesConexao(propriedadesDoArquivo);

            Utilitarios.escrevePropriedadesDoArquivo(propriedadesDoArquivo, localDoArquivoDeConexao);

        }

        String url = propriedadesDoArquivo.getProperty("database.url");
        String user = propriedadesDoArquivo.getProperty("database.user");
        String password = propriedadesDoArquivo.getProperty("database.password");

        try {

            // Establish a connection
            Connection connection = DriverManager.getConnection(url, user, password);

            if(!Utilitarios.existeArquivoFlagDasTabelas()){

                // se for a primeira vez conectando com o banco de dados, então o arquivo flag não existe ainda e se deve criar a base de dados
                BancoDeDados.inicializacao(connection);
                Utilitarios.criarArquivoFlagDasTabelas();

            }

            // selecionar a base de dados livraria
            Statement statement = connection.createStatement();
            statement.executeUpdate("use livraria;");
            statement.close();

            // checar se já existem administradores registrados

            if(BancoDeDados.numeroDeEntradasEmUmaTabela("administradores", connection) == 0){

                System.out.println("Nenhum administrador foi registrado ainda. Registre um.");

                // registrar um administrador
                BancoDeDados.criaAdministrador(connection);

            }

            // menu de login

            int i = Menus.fazLogin();

            if(i == 1){

                // chame o método

            }


            // Close the connection when done
            connection.close();

        } catch (SQLException e) {

            e.printStackTrace();

        }

    }

}
