import java.io.*;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;
import java.util.Scanner;
import java.sql.ResultSet;

public class Utilitarios {

    public static Properties lePropriedadesDoArquvio(String localDoArquivoDeConexao) {

        Properties properties = new Properties();

        try (InputStream input = new FileInputStream(localDoArquivoDeConexao)) {

            properties.load(input);

        } catch (IOException e) {

            e.printStackTrace();

        }

        return properties;

    }

    public static void escrevePropriedadesDoArquivo(Properties properties, String localDoArquivoDeConexao){

        try (OutputStream output = new FileOutputStream(localDoArquivoDeConexao)) {

            properties.store(output, null);

        } catch (IOException e) {

            e.printStackTrace();

        }

    }

    public static Properties pegaPropriedadesConexao(Properties propriedades){

        System.out.println("Entre uma url da base de dados a ser usada pela livraria:");
        Scanner sc = new Scanner(System.in);
        propriedades.setProperty("database.url", sc.next());
        System.out.println("Entre o usuário da base de dados a ser usada pela livraria:");
        propriedades.setProperty("database.user", sc.next());
        System.out.println("Entre a senha desse usuário:");
        propriedades.setProperty("database.password", sc.next());

        return propriedades;

    }

    public static boolean existeArquivoFlagDasTabelas() {

        File file = new File("tabelas_criadas.flag");
        return file.exists();

    }

    public static void criarArquivoFlagDasTabelas() {
        try {
            File file = new File("tabelas_criadas.flag");
            file.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static boolean dataEmFormatoMySQL(String data){

        char[] dataArray = data.toCharArray();

        if(data.length() != 10){

            return false;

        }

        for (int i = 0; i < data.length(); i++) {

            if(i == 4 || i == 7){

                if(dataArray[i] != '-'){

                    return false;

                }

            }else{

                if(!Character.isDigit(dataArray[i])){

                    return false;

                }

            }

        }

        return true;

    }

    public static boolean cpfEstaEmFormato(String cpf){

        if(cpf.length() != 11){

            return false;

        }

        for (char c: cpf.toCharArray()) {

            if(!Character.isDigit(c)){

                return false;

            }

        }

        return true;

    }

    public static boolean inputDeAdministradorCorreto(String login, String senha, String nome, String cpf, String nascimento){

        if(!cpfEstaEmFormato(cpf)){

            return false;

        }

        if(!dataEmFormatoMySQL(nascimento)){

            return false;

        }

        if(login.length() > 50 || senha.length() > 50 || nome.length() > 100){

            return false;

        }

        return true;

    }
}
