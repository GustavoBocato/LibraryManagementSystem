import java.util.Date;

public class Administrador {

    private String login;
    private String senha;
    private String nome;
    private Date nascimento;
    private String cpf;

    public void Administrador(String login, String senha, String nome, Date nascimento, String cpf) throws IllegalArgumentException {

        if(login.length() > 50){

            throw new IllegalArgumentException("Login é muito longo.");

        }

        if(senha.length() > 50){

            throw new IllegalArgumentException("Senha é muito longa.");

        }

        if(nome.length() > 100){

            throw new IllegalArgumentException("Nome é muito longo");

        }

        if(cpf.length() > 11){

            throw new IllegalArgumentException("CPF é muito longo.");

        }

        this.login = login;
        this.nome = nome;
        this.cpf = cpf;
        this.senha = senha;
        this.nascimento = nascimento;

    }

}
