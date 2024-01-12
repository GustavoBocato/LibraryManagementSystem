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

        System.out.println("Faça login na base de dados.");
        System.out.println("Entre o seu usuário:");
        System.out.println("Entre a senha do usuário:");
        
    }

}
