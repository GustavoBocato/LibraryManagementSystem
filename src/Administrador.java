
public class Administrador {

    private String login;
    private String senha;
    private String nome;
    private String nascimento;
    private String cpf;

    public void Administrador(String login, String senha, String nome, String nascimento, String cpf) throws IllegalArgumentException {

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

        // checar a data de nascimento

        this.login = login;
        this.nome = nome;
        this.cpf = cpf;
        this.senha = senha;

    }

    public String getNascimento() {
        return nascimento;
    }

    public String getLogin() {
        return login;
    }

    public String getCpf() {
        return cpf;
    }

    public String getNome() {
        return nome;
    }

    public String getSenha() {
        return senha;
    }

}
