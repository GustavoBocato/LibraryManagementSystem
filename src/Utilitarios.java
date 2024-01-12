import java.io.*;
import java.util.Properties;

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

}
