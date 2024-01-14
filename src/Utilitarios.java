import java.io.*;
import java.util.Properties;
import java.util.Scanner;

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

}
