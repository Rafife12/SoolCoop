package model;

import java.util.Scanner;

public class Endereco {
    private String cidade;
    private String bairro;
    private String rua;
    private String numero;
    private String cep;

    public Endereco(String cidade, String bairro, String rua, String numero, String cep) {
        this.cidade = cidade;
        this.bairro = bairro;
        this.rua = rua;
        this.numero = numero;
        this.cep = cep;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getRua() {
        return rua;
    }

    public void setRua(String rua) {
        this.rua = rua;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public void entradaDadosEndereco() { //Cadastra o endereco completo do usuario
        Scanner sc = new Scanner(System.in);

        System.out.println("\n/// Cadastro de Endereço ///");

        System.out.println("Digite a cidade: ");
        setCidade(sc.nextLine());

        System.out.println("Digite o bairro: ");
        setBairro(sc.nextLine());

        System.out.println("Digite a rua: ");
        setRua(sc.nextLine());

        System.out.println("Digite o número: ");
        setNumero(sc.nextLine());

        System.out.println("Digite o CEP: ");
        setCep(sc.nextLine());
    }

    public String formatarEndereco() {
        return rua + ", " + numero + ", " + bairro + ", " + cidade + " - CEP: " + cep;
    }
}
