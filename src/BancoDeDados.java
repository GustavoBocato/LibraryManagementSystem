import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class BancoDeDados {

    public static void inicializacao(Connection connection) throws SQLException {

        Statement statement = connection.createStatement();

        String[] comandosDeCriacaoDeTabelas = {"create database livraria;",
                "use livraria;",
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

    }

    public static int numeroDeEntradasEmUmaTabela(String nomeDaTabela, Connection connection) throws SQLException {

        Statement statement = connection.createStatement();

        ResultSet resultSet = statement.executeQuery("SELECT COUNT(*) FROM " + nomeDaTabela + ";");

        resultSet.next();

        return resultSet.getInt(1);

    }

    public static void criaAdministrador(Connection connection) throws SQLException {

        String nome;
        String login;
        String senha;
        String cpf;
        String nascimento;

        do {

            Scanner sc = new Scanner(System.in);

            System.out.println("Entre o nome do administrador (deve ter no máximo 100 caracteres).");

            nome = sc.next();

            System.out.println("Entre o login do administrador (deve ter no máximo 50 caracteres).");

            login = sc.next();

            System.out.println("Entre a senha do administrador (deve ter no máximo 50 caracteres).");

            senha = sc.next();

            System.out.println("Entre o cpf do administrador (deve ter exatamente 11 digitos, não use hifen ou ponto).");

            cpf = sc.next();

            System.out.println("Entre a data de nascimento do administrador no formato AAAA-MM-DD.");

            nascimento = sc.next();

        } while(Utilitarios.inputDeAdministradorCorreto(login, senha, nome, cpf, nascimento));

        Statement statement = connection.createStatement();

        String comandoMySQL = "INSERT INTO Administradores (login, senha, nome, nascimento, cpf) VALUES (" + login + ", " + senha + ", " + nome + "," +
                " " + nascimento + ", " + cpf + ")";

        statement.executeUpdate(comandoMySQL);
        statement.close();

    }

}
