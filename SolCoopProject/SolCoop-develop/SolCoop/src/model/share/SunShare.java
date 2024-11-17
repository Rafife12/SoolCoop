package model.share;

import model.Endereco;
import model.Usuario;
import model.seeker.SunSeeker;

import java.util.Scanner;

public class SunShare extends Usuario {
    protected float energiaDisponivel;
    protected float energiaCompartilhada;
    protected float energiaTotal;
    private MercadoEnergia mercado;

    public SunShare(int idUsuario, String nome, String email, String senha, String numeroTelefone, Endereco endereco, MercadoEnergia mercado) {
        super(idUsuario, nome, email, senha, numeroTelefone, endereco);
        this.mercado = mercado;
    }

    public float getEnergiaDisponivel() {
        return energiaDisponivel;
    }

    public void setEnergiaDisponivel(float energiaDisponivel) {
        this.energiaDisponivel = energiaDisponivel;
    }

    public float getEnergiaCompartilhada() {
        return energiaCompartilhada;
    }

    public void setEnergiaCompartilhada(float energiaCompartilhada) {
        this.energiaCompartilhada = energiaCompartilhada;
    }

    public float getEnergiaTotal() {
        return energiaTotal;
    }

    public void setEnergiaTotal(float energiaTotal) {
        this.energiaTotal = energiaTotal;
    }
        
    
    public void entradaDados () { //Pede ao usuário para inserir os dados
        Scanner sc = new Scanner(System.in);
        boolean compartilharEnergia = false;//Variavel local para capturar se o usuario já compartilha energia

        System.out.println("\n/// Cadastro de novo item no Mercado de Energia ///");

        System.out.println("Digite o total de energia que você gera (em kWh): ");
        setEnergiaTotal(energiaTotal = sc.nextFloat());

        System.out.println("\nVocê já compartilha eneriga? (S/N): "); //Se o usuário já compartilha energia, ele pode informar a quantidade
        compartilharEnergia = sc.next().equalsIgnoreCase("S");

        if (compartilharEnergia) {
            System.out.println("Digite a energia que você compartilha (em kWh): ");
            setEnergiaCompartilhada(energiaCompartilhada = sc.nextFloat());

        } else { //Se o usuário não compartilha energia, a energia compartilhada é 0
            System.out.println("Você não compartilha energia.");
            setEnergiaCompartilhada(energiaCompartilhada = 0);
        }

        System.out.println("\nDigite a energia disponivel para a venda (em kWh): ");
        setEnergiaDisponivel(energiaDisponivel = sc.nextFloat());

    }

    public void imprimirShare () {
        System.out.println("\nConfira os dados cadastrados abaixo:");
        System.out.println("Energia total: " + getEnergiaTotal());
        System.out.println("Energia compartilhada: " + getEnergiaCompartilhada());
        System.out.println("Energia disponível: " + getEnergiaDisponivel());
    }

    public void verificarMercado() { //Verifica se os dados estão corretos e se o usuário deseja enviar para o mercado
        Scanner sc = new Scanner(System.in);
        boolean confirmaMercado = false;
        System.out.println("\n\n/// Verificação do Mercado de Energia ///");
        System.out.println("Por favor, confirme se todos os dados estão corretos:");

        imprimirShare();

        System.out.println("\nVocê está ciente de que esses dados serão enviados para o Mercado de Energia, deseja continuar? (S/N)");
        confirmaMercado = sc.next().equalsIgnoreCase("S");
        if (confirmaMercado) {
            System.out.println("Dados enviados com sucesso!");
            mercado.addOferta(this);
            mercado.adicionarOfertasTeste();
            mercado.atualizarMercado();
        } else {
            System.out.println("Operação cancelada.");
            System.out.println("Por favor, digite novamente os dados.");
            entradaDados();
            verificarMercado();
        }
    }

    public void importarPrecoVenda() {
        SunSeeker seeker = new SunSeeker(0, "nome", "email", "senha", "numeroTelefone", new Endereco("cidade", "bairro", "rua", "numero", "cep"));
        float consumoSeeker = seeker.getMediaConsumo();
        System.out.println("O preço de venda da energia é: " + consumoSeeker);
    }
}

