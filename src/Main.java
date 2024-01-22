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
            System.out.println("Quer se conectar com a mesma base de dados da última vez?");
            System.out.println("0 - Não");
            System.out.println("1 - Sim");

            Scanner sc = new Scanner(System.in);
            int i = sc.nextInt();

            if(i == 0){

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
                Statement statement = connection.createStatement();

                String[] comandosDeCriacaoDeTabelas = {"create database livraria;",
                        "create table autores(\n" +
                                "\n" +
                                "\tid int auto_increment,\n" +
                                "    nome varchar(100) not null,\n" +
                                "    nascimento date not null,\n" +
                                "    primary key(id)\n" +
                                "    \n" +
                                ");",
                        "create table nacionalidades(\n" +
                                "\n" +
                                "\tid int auto_increment,\n" +
                                "    nome varchar(100) not null,\n" +
                                "    primary key(id)\n" +
                                "    \n" +
                                ");",
                        "create table generos(\n" +
                                "\n" +
                                "\tid int auto_increment,\n" +
                                "    nome varchar(50) not null,\n" +
                                "    primary key(id)\n" +
                                "    \n" +
                                ");",
                        "create table clientes(\n" +
                                "\n" +
                                "\tid int auto_increment,\n" +
                                "    nome varchar(100) not null,\n" +
                                "    cpf varchar(11) not null,\n" +
                                "    nascimento date not null,\n" +
                                "    genero varchar(2) not null,\n" +
                                "    endereco varchar(100) not null,\n" +
                                "    login varchar(50) not null,\n" +
                                "    senha varchar(50) not null,\n" +
                                "    primary key(id),\n" +
                                "\tcheck (genero in ('M', 'F', 'NB'))\n" +
                                "    \n" +
                                ");",
                        "create table funcionarios(\n" +
                                "\n" +
                                "\tid int auto_increment,\n" +
                                "    login varchar(50) not null,\n" +
                                "    senha varchar(50) not null,\n" +
                                "    nome varchar(100) not null,\n" +
                                "    nascimento date not null,\n" +
                                "    cpf varchar(11) not null,\n" +
                                "    primary key(id)\n" +
                                "    \n" +
                                ");",
                        "create table administradores(\n" +
                                "\n" +
                                "\tid int auto_increment,\n" +
                                "    login varchar(50) not null,\n" +
                                "    senha varchar(50) not null,\n" +
                                "    nome varchar(100) not null,\n" +
                                "    nascimento date not null,\n" +
                                "    cpf varchar(11) not null,\n" +
                                "    primary key(id)\n" +
                                "    \n" +
                                ");",
                        "create table multas(\n" +
                                "\n" +
                                "\tid int auto_increment,\n" +
                                "    id_cliente int not null,\n" +
                                "    valor decimal(8,2) not null,\n" +
                                "    primary key(id),\n" +
                                "    foreign key(id_cliente) references clientes(id)\n" +
                                "    \n" +
                                ");",
                        "create table livros(\n" +
                                "\n" +
                                "\tid int auto_increment,\n" +
                                "    nome varchar(100) not null,\n" +
                                "    ano date not null,\n" +
                                "    id_autor int not null,\n" +
                                "    id_genero int not null,\n" +
                                "    primary key(id),\n" +
                                "    foreign key(id_autor) references autores(id),\n" +
                                "    foreign key(id_genero) references generos(id)\n" +
                                "    \n" +
                                ");",
                        "create table autor_nacionalidade(\n" +
                                "\n" +
                                "\tid int auto_increment,\n" +
                                "    id_autor int not null,\n" +
                                "    id_nacionalidade int not null,\n" +
                                "    primary key(id),\n" +
                                "    foreign key(id_autor) references autores(id),\n" +
                                "    foreign key(id_nacionalidade) references nacionalidades(id)\n" +
                                "    \n" +
                                ");",
                        "create table livros_emprestados(\n" +
                                "\n" +
                                "\tid int auto_increment,\n" +
                                "    id_livro int not null,\n" +
                                "    id_cliente int not null,\n" +
                                "    data_do_emprestimo date not null,\n" +
                                "    primary key(id),\n" +
                                "    foreign key(id_livro) references livros(id),\n" +
                                "    foreign key(id_cliente) references clientes(id)\n" +
                                "    \n" +
                                ");",
                        "create table reservas(\n" +
                                "\n" +
                                "\tid int auto_increment,\n" +
                                "    id_cliente int not null,\n" +
                                "    id_livro_emprestado int not null,\n" +
                                "    data_da_reserva datetime not null,\n" +
                                "    primary key(id),\n" +
                                "    foreign key(id_cliente) references clientes(id),\n" +
                                "    foreign key(id_livro_emprestado) references livros_emprestados(id_livro)\n" +
                                "    \n" +
                                ");"};

                for(String comando : comandosDeCriacaoDeTabelas){

                    statement.executeUpdate(comando);

                }

                statement.close();

                Utilitarios.criarArquivoFlagDasTabelas();

            }

            // selecionar a base de dados livraria
            Statement statement = connection.createStatement();
            statement.executeUpdate("use livraria;");
            statement.close();

            // checar se já existem administradores registrados

            if(Utilitarios.numeroDeEntradasEmUmaTabela("administradores", connection) == 0){

                System.out.println("Nenhum administrador foi registrado ainda. Registre um.");

                // registrar um administrador
                Utilitarios.criaAdministrador(connection);

            }

            // menu de login

            System.out.println("Você quer logar como: 1 - Cliente, 2 - Funcionário ou 3 - Administrador? Entre um número.");


            // Close the connection when done
            connection.close();

        } catch (SQLException e) {

            e.printStackTrace();

        }

    }

}
