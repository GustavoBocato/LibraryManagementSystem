import java.sql.Connection;
import java.sql.SQLException;
import java.util.Scanner;

public class Menus {

    public static boolean querTrocarDeConexao(){

        int i;

        do {

            System.out.println("Quer se conectar com a mesma base de dados da última vez? Entre uma das seguintes opções.");
            System.out.println("0 - Não");
            System.out.println("1 - Sim");

            Scanner sc = new Scanner(System.in);
            i = sc.nextInt();

        }while(i != 1 && i != 0);

        if(i == 1){

            return true;

        }

        return false;

    }

    public static int fazLogin() {

        // metodo para o usuário fazer o seu login, como cliente, funcionário ou administrador
        int i;

        do {

            System.out.println("Escolha um tipo de perfil de login: 1 - Cliente," +
                    " 2 - Funcionário, 3 - Administrador. Entre um dos números listados.");

            Scanner sc = new Scanner(System.in);
            i = sc.nextInt();

        } while(i != 1 && i != 2 && i != 3);

        return i;

    }

}
