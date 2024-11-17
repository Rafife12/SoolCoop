<h1>SoolCoop</h1>
<p>Unimos comunidades para um futuro mais sustentável</p>

## Índice:

- [Estrutura](#estrutura)
- [Funcionalidades](#funcionalidades)
- [Contrato](#contrato)
- [SunPartner](#sunpartner)
- [Pedido](#pedido)
- [SunSeeker](#sunseeker)
- [Mercado Energia](#mercado-energia)
- [Oferta Energia](#oferta-energia)
- [Pagamento](#pagamento)
- [SunShare](#sunshare)
- [Endereço](#endereço)
- [Usuário](#usuário)
- [Main](#main)


## Estrutura:

-	Código-fonte em `src`: A pasta contém os arquivos .java com a lógica do sistema.
-	Saída em `bin`: A pasta recebe os arquivos .class, ou seja, o código Java compilado.
-	Bibliotecas externas em `lib`: Arquivos .jar que fornecem funcionalidades adicionais, como frameworks ou APIs, são incluídos dinamicamente.

## Funcionalidades:

Base em Java:

- O uso do Java indica que o projeto pode ter foco em portabilidade e eficiência.
- Aplicativos desenvolvidos em Java são amplamente utilizados para back-end, sistemas de gestão e aplicativos robustos.

Suporte a Bibliotecas Externas:

- A referência a arquivos .jar sugere que o projeto utiliza bibliotecas adicionais para lidar com tarefas específicas, como:
- Conexão com banco de dados.
- Manipulação de interfaces gráficas (como JavaFX ou Swing).
- Comunicação em rede, APIs ou processamento de dados.

## Contrato:

```java
package model.contrato;

import java.util.Scanner;

public class Contrato {
    Scanner sc = new Scanner(System.in);
    //Metodo booleano para concordar ou não com o contrato
    public boolean termosContrato(){
        System.out.println("Contrato Anual de Compartilhamento de Energia Solar\n" +
        "\nEste Contrato Anual de Compartilhamento de Energia Solar é celebrado entre a SolCoop, e Cliente, com a intenção de compartilhar energia solar de acordo com os termos e condições estabelecidos abaixo:\n" +
        "\nServiços Prestados pela Empresa:\nA Empresa fornecerá ao Cliente acesso à energia solar gerada pelos sistemas solares da Empresa. A energia compartilhada será disponibilizada ao Cliente de acordo com os termos deste Contrato.\n" +
        "\nObrigações do Cliente:\nO Cliente concorda em pagar à Empresa o valor estipulado pela energia solar compartilhada, de acordo com o plano selecionado. Além disso, o Cliente concorda em pagar uma taxa de serviço de 15% do valor total da energia compartilhada, a ser pago anualmente.\n" +
        "\nTermo do Contrato:\nEste Contrato entrará em vigor na data de assinatura e permanecerá em vigor por um período de um ano a partir dessa data, podendo ser renovado mediante acordo por escrito entre as partes.\n" +
        "\nPagamento:\nO Cliente concorda em efetuar o pagamento à Empresa dentro de 30 dias após o recebimento da fatura, conforme estabelecido pela Empresa.\n" +
        "\nUso da Energia Solar:\nO Cliente concorda em utilizar a energia solar compartilhada exclusivamente para seus próprios fins e não a revender ou transferir a terceiros sem consentimento prévio por escrito da Empresa.\n" +
        "\nResponsabilidade Limitada:\nA Empresa não será responsável por quaisquer interrupções no fornecimento de energia solar devido a circunstâncias fora de seu controle razoável, incluindo, mas não se limitando a, condições climáticas adversas, falhas no equipamento ou interferência de terceiros.\n" +
        "\nConfidencialidade:\nAmbas as partes concordam em manter confidenciais todas as informações comerciais e técnicas divulgadas durante a vigência deste Contrato.\n" +
        "\nRescisão:\nQualquer uma das partes pode rescindir este Contrato mediante notificação por escrito à outra parte com pelo menos 30 dias de antecedência.\n" +
        "\nLegislação Aplicável:\nEste Contrato será regido e interpretado de acordo com as leis do País/Estado.\n" +
        "\nAssinaturas:\nEste Contrato pode ser assinado em duas vias, sendo cada cópia considerada original e ambas juntas constituindo o mesmo instrumento.\n" +
        "\nAo assinar este Contrato, as partes reconhecem que leram, entenderam e concordam com todos os termos e condições estabelecidos acima.");
        while (true) { // Garante que o usuário aceite o contrato
            System.out.println("\n\nDeseja concordar com o contrato? (S/N)");
            String resposta = sc.next().toUpperCase();
            if (resposta.equals("S")) { // Se for sim, sai do loop e retorna true
                return true;
            } else if (resposta.equals("N")) { //Se for não, sai do loop e retorna false
                System.out.println("Não é possível realizar o pedido, pois você não concordou com o contrato.");
                return false;
            } else { // Se for outra resposta, pede para digitar novamente
                System.out.println("Resposta inválida. Por favor, digite S para sim ou N para não.");
            }
        }
    }
}
```

## SunPartner:

```java
package model.partner;

import model.Endereco;
import model.Usuario;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.LinkedList;
import java.util.Queue;

public class SunPartner extends Usuario {
    public SunPartner(int idUsuario, String nome, String email, String senha, String numeroTelefone, Endereco endereco, String cep, List<Float> ultimasContas, List<String> empresasEscolhidas) {
        super(idUsuario, nome, email, senha, numeroTelefone, endereco);
        this.cep = cep;
        this.ultimasContas = new ArrayList<>();
        this.empresaEscolhida = new ArrayList<>();
    }

    private String cep;
    private List<Float> ultimasContas;
    private List<String> empresaEscolhida;
    private static Queue<SunPartner> filaDeEspera = new LinkedList<>();
    public float mediaConsumo = 0;
    public float margemErro = .15f; // 15% de margem de erro
    public float consumoTotal = 0;

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public List<Float> getUltimasContas() {
        return ultimasContas;
    }

    public void setUltimasContas(List<Float> ultimasContas) {
        this.ultimasContas = ultimasContas;
    }

    public List<String> getEmpresaEscolhida() {
        return empresaEscolhida;
    }

    public void setEmpresaEscolhida(List<String> empresasEscolhidas) {
        this.empresaEscolhida = empresasEscolhidas;
    }

    /* Método para calcular o consumo total de energia */
    public float ConsumoTotalPartner() {
        /* Calcular a média de consumo */
        float soma = 0;
        for (Float valorConta : ultimasContas) { // Percorre a lista de contas e soma os valores
            soma += valorConta;
        }
        mediaConsumo = soma / ultimasContas.size(); // Faz a media dos valores de acordo com o tamanho da lista

        /* Calcular o consumo total de energia (add margem de erro) */
        consumoTotal = mediaConsumo + (mediaConsumo * margemErro);

        return consumoTotal;
    }

    /* Método para solicitar informações do usuário */
    public void solicitarInfoUsuario() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("\nAqui você poderá solicitar um orçamento de energia solar e ficar a espera de um parceiro SolCoop!");
        System.out.println("Por favor, insira suas informações necessárias:");
        System.out.println("Insira o valor em kWh (quilowatt-hora) das suas últimas 12 contas de energia: ");
        System.out.println();//Pular linha

        // Adiciona os valores das últimas contas de energia à lista
        for (int i = 0; i < 12; i++) {
            System.out.println("Conta [" + (i + 1) + "]: ");
            float conta = scanner.nextFloat();
            ultimasContas.add(conta);
        }

        PrintInfoUsuario(); // Chama o metodo de printar as infos do usuario
    }

    /* Metodo para printar as informações do usuário */
    public void PrintInfoUsuario() {
        float consumoFinal = ConsumoTotalPartner();
        System.out.println("\n///////////////////////////////");
        System.out.printf("Sua média de consumo nas últimas 12 contas de energia: [ %.2f kWh ]", mediaConsumo);
        System.out.printf("\nMédia com margem de segurança de 15%%: [ %.2f kWh ]", consumoFinal);
        System.out.println("\nA margem de segurança é importante para que o seu consumo seja sempre atendido com energia limpa.");
        System.out.println("///////////////////////////////");

        filaDeEspera.add(this); // Adiciona o usuário à fila
        escolhaEmpresa(this); // Chama o método para escolher uma empresa
    }

    /* Método para escolher uma empresa */
    public static void escolhaEmpresa(SunPartner sunPartner) {
        Scanner sc = new Scanner(System.in);
        int escolhaDaEmpresa;

        listarEmpresas(); // Chama o método para listar as empresas

        do {
            System.out.println("\nEscolha uma empresa para solicitar um orçamento: ");
            escolhaDaEmpresa = sc.nextInt();

            switch (escolhaDaEmpresa) {
                case 1:
                    sunPartner.getEmpresaEscolhida().add("BRZ Solar"); // Adiciona a empresa escolhida à lista EmpresaEscolhida
                    System.out.println("Orçamento solicitado com sucesso para a empresa BRZ Solar!");
                    break;
                case 2:
                    sunPartner.getEmpresaEscolhida().add("Inove Solar Engenharia");
                    System.out.println("Orçamento solicitado com sucesso para a empresa Inove Solar Engenharia!");
                    break;
                case 3:
                    sunPartner.getEmpresaEscolhida().add("RenovaSol");
                    System.out.println("Orçamento solicitado com sucesso para a empresa RenovaSol!");
                    break;
                case 4:
                    sunPartner.getEmpresaEscolhida().add("DellaSol");
                    System.out.println("Orçamento solicitado com sucesso para a empresa DellaSol!");
                    break;
                case 5:
                    sunPartner.getEmpresaEscolhida().add("Sun7");
                    System.out.println("Orçamento solicitado com sucesso para a empresa Sun7!");
                    break;
                default:
                    System.out.println("Opção inválida!");
                    escolhaDaEmpresa = sc.nextInt();
                    break;
            }
        } while (escolhaDaEmpresa < 1 || escolhaDaEmpresa > 5); // Enquanto a escolha for inválida, pede para escolher novamente
    }

    /* Método para listar a fila de usuários */
    public void listarFilaDeUsuarios() {
        if (filaDeEspera == null || filaDeEspera.isEmpty()) { // Verifica se a fila está vazia
            System.out.println("\nA fila de espera está vazia.");
            return;
        }
        System.out.println("\n//////// Fila de Usuários: ////////");
        for (SunPartner sunPartner : filaDeEspera) { // Percorre a fila de usuários em espera
            // Verifica se a empresa escolhida é diferente de null, se sim, pega o nome da empresa, se não, imprime "Nenhuma empresa escolhida"
            String empresas = (sunPartner.getEmpresaEscolhida() != null) ? sunPartner.getEmpresaEscolhida().toString() : "Nenhuma empresa escolhida";
            System.out.println("\nNome: " + sunPartner.getNome() + " | CEP: " + sunPartner.getCep() + " | Empresas escolhidas: " + empresas);
        }
    }

    /* Método para listar as empresas parceiras */
    public static void listarEmpresas() {
        System.out.println("\nConheça as empresas parceiras do SolCoop:");
        System.out.println("1. BRZ Solar");
        System.out.println("2. Inove Solar Engenharia");
        System.out.println("3. RenovaSol");
        System.out.println("4. DellaSol");
        System.out.println("5. Sun7");
    }

    /* Método para imprimir o orçamento */
    public void solicitarOrcamento() { 
        float consumoFinal = ConsumoTotalPartner();
        System.out.println();//Pular linha
        System.out.println("//////////////////////////////////");
        System.out.println("/// Informações do orçamento: ///");
        System.out.println("\nCEP: " + cep);
        System.out.printf("Quantidade de energia desejada (com margem de segurança de 15%%) é [ %.2f kWh ]: \n", consumoFinal);
        System.out.println("Empresa escolhida: " + empresaEscolhida);
        System.out.println("\nVamos encontrar um parceiro SolCoop para você. Entraremos em contato em breve!\n");
    }
}
```

## Pedido:

```java
package model.seeker;

import java.util.Date;
import java.text.SimpleDateFormat;
public class Pedido {
    private static int contador = 1;
    private int idPedido;
    private String nomeVendedor;
    private int idVendedor;
    private String nomeComprador;
    private int idComprador;
    private Date data;
    private String status;

    public Pedido(String nomeVendedor, int idVendedor, String nomeComprador, int idComprador) {
        this.idPedido = contador++;
        this.nomeVendedor = nomeVendedor;
        this.idVendedor = idVendedor;
        this.nomeComprador = nomeComprador;
        this.idComprador = idComprador;
        this.data = new Date();
        this.status = "Não concluído"; // Inicia sempre como não concluído
    }

    public static int getContador() {
        return contador;
    }

    public static void setContador(int contador) {
        Pedido.contador = contador;
    }

    public int getIdPedido() {
        return idPedido;
    }

    public void setIdPedido(int idPedido) {
        this.idPedido = idPedido;
    }

    public String getNomeVendedor() {
        return nomeVendedor;
    }

    public void setNomeVendedor(String nomeVendedor) {
        this.nomeVendedor = nomeVendedor;
    }

    public int getIdVendedor() {
        return idVendedor;
    }

    public void setIdVendedor(int idVendedor) {
        this.idVendedor = idVendedor;
    }

    public String getNomeComprador() {
        return nomeComprador;
    }

    public void setNomeComprador(String nomeComprador) {
        this.nomeComprador = nomeComprador;
    }

    public int getIdComprador() {
        return idComprador;
    }

    public void setIdComprador(int idComprador) {
        this.idComprador = idComprador;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String formatarData() {
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        return formatter.format(data);
    }
    
    public void imprimirPedido(){
        Pedido pedido = new Pedido(nomeVendedor, idVendedor, nomeComprador, idComprador);
        System.out.println("ID Pedido: " + idPedido + " | Vendedor: " + nomeVendedor + " (ID: " + idVendedor + ")" + "| Data: " + formatarData() + " | Status: " + status);
    }

    public String formatarPedido (){
        return "ID Pedido: " + idPedido + " | Vendedor: " + nomeVendedor + " (ID: " + idVendedor + ")" + "| Data: " + formatarData() + " | Status: " + status;
    }
}
```

## SunSeeker:

```java
package model.seeker;

import model.Endereco;
import model.Usuario;
import model.share.MercadoEnergia;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/* Declaração do SunSeeker */
public class SunSeeker extends Usuario {
    Scanner sc = new Scanner(System.in);
    /* Construtor Seeker */
    public SunSeeker(int idUsuario, String nome, String email, String senha, String numeroTelefone, Endereco endereco) {
        super(idUsuario, nome, email, senha, numeroTelefone, endereco);
        this.ultimasContas = new ArrayList<>();
    }

    private float mediaConsumo = 0;
    private float energiaDesejada;
    public float valorTributo = 0;
    private List<Float> ultimasContas;
    public float margemErro = .15f; // 15% de margem de erro
    public float consumoTotal = 0;

    // *INICIO DOS SET E GETS DO SunSeeker *//
    /* SET do id do usuario */
    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }
    
    /* GET do id do usuario */
    public int getIdUsuario() {
        return idUsuario;
    }
    
    /* SET da energia desejada */
    public void setEnergiaDesejada(float energiaDesejada) {
        this.energiaDesejada = energiaDesejada;
    }

    /* GET da energia desejada */
    public float getEnergiaDesejada() {
        return energiaDesejada;
    }

    /* SET do valor do tributo */
    public void setValorTributo(float valorTributo) {
        this.valorTributo = valorTributo;
    }

    /* GET do valor do tributo */
    public float getValorTributo() {
        return valorTributo;
    }

    /* SET das ultimas contas */
    public void setUltimasContas(List<Float> ultimasContas) {
        this.ultimasContas = ultimasContas;
    }

    /* GET das ultimas contas */
    public List<Float> getUltimasContas() {
        return ultimasContas;
    }

    /* SET MediaConsumo */
    public void setMediaConsumo(float mediaConsumo) {
        this.mediaConsumo = mediaConsumo;
    }
    
    /* GET MediaConsumo */
    public float getMediaConsumo() {
        return mediaConsumo;
    }

    /* SET ConsumoTotal */
    public float setConsumoTotal(float consumoTotal) {
        return this.consumoTotal = consumoTotal;
    }

    /* GET ConsumoTotal */
    public float getConsumoTotal() {
        return consumoTotal;
    }

    // *FIM DOS SET E GETS DO SunSeeker *//

    /* Método para recolher o valor do tributo da energia */
    public void ColetandoDados() {
        System.out.println("Voce se tornou um comprador de energia limpa! Por favor, cadastre os seus dados: ");
        System.out.println("\nPrimeiro informe o valor do tributo da energia da sua região (Deve aparecer em sua conta de energia)\nExemplo: 0,50 (R$/kWh)");
        System.out.println("O valor do tributo será adicionado ao valor final a ser pago.");
        System.out.println("Informe o valor do tributo: ");
        setValorTributo(sc.nextFloat());
        
        /* Pedindo as ultimas contas para realização dos cálculos  */
        System.out.println("\n/// Agora pediremos as ultimas 12 contas de energia para calcular sua media de consumo mensal ///");
        System.out.println("Informe consumo de energia em kWh (quilowatt-hora) presente nas contas de energia que sera usado para calcular sua media de consumo.\n");
        for (int i = 1; i <= 12; i++) {//Loop para pedir as 12 últimas contas
            System.out.println("Informe o valor da conta [" + i + "] (em kWh): ");
            float conta = sc.nextFloat();
            ultimasContas.add(conta); //Adicionando a conta na lista
        }
    }
    
    /* Método para calcular quanto o Seeker vai pagar com adição de 15% de margem de erro e o valor do tributo local */
    public float PrecoFinalSeeker() {      
    /* Calcular a média de consumo */
    float soma = 0;
        for (Float valorConta : ultimasContas) { // Percorre a lista de contas e soma os valores
            soma += valorConta;
        }

        mediaConsumo = soma / ultimasContas.size(); // Faz a media dos valores de acordo com o tamanho da lista
        
        /* Calcular o consumo total de energia (add margem de erro) */
        consumoTotal = mediaConsumo + (mediaConsumo * margemErro);
        
        /* Calcular o valor da conta de energia */ 
        return consumoTotal * valorTributo;
    }

    /* Método para imprimir o valor a ser pago pelo seeker */
    public void PrintValorFinal(){
        float precoFinal = PrecoFinalSeeker(); // Chamando o método para atualizar as variáveis
        System.out.printf("\nSua media de consumo mensal é: %.2f kWh\n", mediaConsumo);
        System.out.printf("O preço final da energia com os 15%% margem de seguranca: R$ %.2f\n", precoFinal);
    }
}
```

## Mercado Energia:

```java
package model.share;

import java.util.Scanner;

import java.util.ArrayList;
import java.util.List;

import model.Usuario;
import model.contrato.Contrato;
import model.seeker.Pedido;
import model.share.Pagamento;

public class MercadoEnergia {
    Scanner sc = new Scanner(System.in);

    private List<OfertaEnergia> ofertasEnergia; // Lista de ofertas de energia
    private List<Pedido> pedidos; // Lista de pedidos de energia
    private boolean ofertasTesteAdd = false;

    public List<Pedido> getPedidos() {
        return pedidos;
    }

    public void setPedidos(List<Pedido> pedidos) {
        this.pedidos = pedidos;
    }

    public MercadoEnergia() {// Inicializa as listas de ofertas e pedidos
        this.ofertasEnergia = new ArrayList<>();
        this.pedidos = new ArrayList<>();
    }

    public void addOferta(SunShare sunShare) {
        OfertaEnergia oferta = new OfertaEnergia(sunShare.getIdUsuario(), sunShare.getEnergiaDisponivel());
        ofertasEnergia.add(oferta);// Adiciona a oferta na lista de ofertas
    }

    public void listarOfertas() {// Lista as ofertas de energia disponíveis
        System.out.println("Bem-vindo ao Mercado de Energia! Aqui você encontra as ofertas de energia disponíveis:");
        System.out.println("/// Mercado de Energia - Ofertas disponíveis ///");

        for (OfertaEnergia oferta : ofertasEnergia) { // Percorre a lista de ofertas e imprime o ID do usuário e a energia disponível
            System.out.println("ID Usuario (SunShare): " + oferta.getIdUsuario() + " | Energia disponível: " + oferta.getEnergiaDisponivel() + " kWh");
        }
    }

    // Atualiza o mercado de energia
    public void atualizarMercado() {
        // Remove as ofertas com energia disponível igual a zero
        ofertasEnergia.removeIf(oferta -> oferta.getEnergiaDisponivel() <= 0);
    }

    // Atualiza o mercado de energia e IMPRIME as ofertas
    public void atualizarMercadoPrint() {
        // Remover ofertas com energia disponível igual a zero
        ofertasEnergia.removeIf(oferta -> oferta.getEnergiaDisponivel() <= 0);//
        listarOfertas();
    }

    // Adicionando 3 ofertas para preencher a lista de ofertas
    public void adicionarOfertasTeste() {
        if (ofertasTesteAdd) {//Controle para saber se a oferta teste já foi inserida
            return;
        }
        List<Usuario> listaUsuarios = Usuario.getListaUsuarios();// Importa a lista de usuários
        int idShare1 = listaUsuarios.get(1).getIdUsuario();
        int idShare2 = listaUsuarios.get(3).getIdUsuario();
        int idShare3 = listaUsuarios.get(4).getIdUsuario();

        OfertaEnergia oferta1 = new OfertaEnergia(idShare1, 250);
        OfertaEnergia oferta2 = new OfertaEnergia(idShare2, 820);
        OfertaEnergia oferta3 = new OfertaEnergia(idShare3, 500);

        ofertasEnergia.add(oferta1);
        ofertasEnergia.add(oferta2);
        ofertasEnergia.add(oferta3);

        ofertasTesteAdd = true;//Marca que a oferta teste foi inserida
    }

    public boolean comprarEnergia(int idOferta, float quantidade) {
        for (OfertaEnergia oferta : ofertasEnergia) {// Percorre a lista de ofertas, se a oferta for encontrada e a quantidade de energia disponível for suficiente
            if (oferta.getIdUsuario() == idOferta && oferta.getEnergiaDisponivel() >= quantidade) {
                oferta.setEnergiaDisponivel(oferta.getEnergiaDisponivel() - quantidade);
                return true;// Retorna true se a compra for realizada com sucesso
            }
        }
        return false;// Retorna false se a compra não for realizada
    }

    public void criarPedido(int idVendedor, int idComprador, String nomeComprador) {
        Usuario vendedor = Usuario.getUsuarioById(idVendedor); // Busca o vendedor pelo ID
        Usuario comprador = Usuario.getUsuarioById(idComprador); // Busca o comprador pelo ID

        if (vendedor != null && comprador != null) { // Se o vendedor e o comprador existirem, cria o pedido
            Pedido pedido = new Pedido(vendedor.getNome(), idVendedor, comprador.getNome(), idComprador);
            pedidos.add(pedido);
            System.out.println("\nPedido criado com sucesso!");
            pedido.imprimirPedido();
        } else {
            System.out.println("ERRO: Usuário não encontrado.\n");
        }
    }

    /* Metodo para solicitar id do vendedor e a quantidade de energia que deseja comprar */
    public void solicitarCompraEnergia() {
        Scanner sc = new Scanner(System.in);
        int idVendedor = 0;
        float quantidade = 0;
        boolean compraValida = false;

        while (!compraValida) {
            atualizarMercadoPrint();
            System.out.println("\nDigite o ID do vendedor (SunShare) que deseja comprar energia: ");
            idVendedor = sc.nextInt();
            System.out.println("Digite a quantidade de energia que deseja comprar (em kWh): ");
            quantidade = sc.nextFloat();

            Usuario vendedor = Usuario.getUsuarioById(idVendedor);
            if (vendedor == null) {
                System.out.println("ERRO: Vendedor não encontrado.\n");
                continue;// Volta para o início do loop
            }

            if (quantidade <= 0) {
                System.out.println("ERRO: Quantidade inválida.\n");
                continue;// Volta para o início do loop
            }

            if (!comprarEnergia(idVendedor, quantidade)) {// Se a compra não for realizada
                System.out.println("Erro ao realizar a compra: Oferta não encontrada ou quantidade insuficiente.");
                continue;// Volta para o início do loop
            }

            compraValida = true;
            System.out.println("\nConfira os dados da compra abaixo:");
            System.out.println("ID do vendedor: " + idVendedor + " | Quantidade de energia: " + quantidade + " kWh");
            System.out.println("\nDeseja continuar a compra (S/N)?\nA seguir será exibido o contrato de compra de energia. É necessário aceitar para finalizar o pedido.");
            String continuarCompra = sc.next();

            if (continuarCompra.equalsIgnoreCase("S")) {//Se o usuário deseja continuar a compra
                Contrato contrato = new Contrato();
                boolean aceitouContrato = contrato.termosContrato();
                if (!aceitouContrato) {
                    System.out.println("Contrato não aceito. Compra cancelada.");
                    System.out.println("Voltando para o menu principal...\n");
                } else {
                    int idComprador = Usuario.getUsuarioById(1).getIdUsuario();
                    criarPedido(idVendedor, Usuario.getUsuarioById(idVendedor).getIdUsuario(), "");
                    Pagamento.processarPagamento(this, pedidos.get(pedidos.size() - 1).getIdPedido());
                }
            } else {
                System.out.println("Compra não realizada. Voltando para o menu principal...\n");
            }
        }
    }

    

    public Pedido getPedidoById(int idPedido) {// Procura um pedido pelo ID e retorna o pedido
        for (Pedido pedido : pedidos) {// Percorre a lista de pedidos
            if (pedido.getIdPedido() == idPedido) {//Se encontrar o pedido que corresponde ao ID procurado, retorna o pedido
                return pedido;
            }
        }
        return null;
    }

    /*Metodo para listar os pedidos */
    public void listarPedidos() {
        System.out.println("/// Mercado de Energia - Pedidos de energia ///");
        for (Pedido pedido : pedidos) {// Percorre a lista de pedidos e imprime os dados do pedido
            pedido.imprimirPedido();
        }
    }
}
```

## Oferta Energia:

```java
package model.share;

public class OfertaEnergia {
    private int idUsuario;
    private float energiaDisponivel;

    public OfertaEnergia(int idUsuario, float energiaDisponivel) {
        this.idUsuario = idUsuario;
        this.energiaDisponivel = energiaDisponivel;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public float getEnergiaDisponivel() {
        return energiaDisponivel;
    }

    public void setEnergiaDisponivel(float energiaDisponivel) {
        this.energiaDisponivel = energiaDisponivel;
    }
}
```

## Pagamento:

```java
package model.share;

import model.seeker.Pedido;
import java.util.Scanner;


public class Pagamento {

    public static void processarPagamento(MercadoEnergia mercadoEnergia, int idPedido) {
        Pedido pedido = mercadoEnergia.getPedidoById(idPedido);//Busca o pedido pelo ID

        if (pedido != null) {//Se o pedido for encontrado, exibe o pedido e pergunta se deseja aceitar
            Scanner sc = new Scanner(System.in);
            System.out.println("\nDeseja aceitar o pedido? (S/N): ");
            String resposta = sc.nextLine();

            if (resposta.equalsIgnoreCase("S")) {//Se a resposta for sim, altera o status do pedido para concluído
                pedido.setStatus("Concluído");
                System.out.println("Pedido aceito e concluído!");
                
                Pedido pedido1 = mercadoEnergia.getPedidoById(idPedido);
                pedido1.imprimirPedido();//Imprime os dados do pedido
                
            } else {//Se a resposta for não, altera o status do pedido para recusado
                System.out.println("Pedido recusado!");
            }
        } else {//Se o pedido não for encontrado, exibe mensagem de erro
            System.out.println("Pedido não encontrado!");
        }
    }
}
```

## SunShare:

```java
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
```

## Endereço:

```java
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
```

## Usuário:

```java
package model;

import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;

import model.partner.SunPartner;
import model.seeker.SunSeeker;
import model.share.MercadoEnergia;
import model.share.Pagamento;
import model.share.SunShare;

public class Usuario {

    protected static int contadorId = 1;
    protected int idUsuario;
    private String nome;
    private String email;
    private String senha;
    private String numeroTelefone;
    private Endereco endereco;
    private static List<Usuario> listaUsuarios = new ArrayList<>();
    static MercadoEnergia mercado = new MercadoEnergia();
    static Pagamento pagamento = new Pagamento();
    

    public Usuario(int idUsuario, String nome, String email, String senha, String numeroTelefone, Endereco endereco) {
        this.idUsuario = idUsuario;
        this.nome = nome;
        this.email = email;
        this.senha = senha;
        this.numeroTelefone = numeroTelefone;
        this.endereco = endereco;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getNumeroTelefone() {
        return numeroTelefone;
    }

    public void setNumeroTelefone(String numeroTelefone) {
        this.numeroTelefone = numeroTelefone;
    }

    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }

    public static List<Usuario> getListaUsuarios() {
        return listaUsuarios;
    }

    public static void setListaUsuarios(List<Usuario> listaUsuarios) {
        Usuario.listaUsuarios = listaUsuarios;
    }

    public static void adicionarUsuariosTestes() {//Adicinando usuários de teste
        SunSeeker seeker1 = new SunSeeker(contadorId++, "Pedro", "pedroteste@gmail.com", "teste123", "71999999999", new Endereco("Cidade1", "Bairro1", "Rua1", "1", "11111-111"));
        SunShare share1 = new SunShare(contadorId++, "Felipe", "felipeteste@gmail.com", "teste123", "71999999999", new Endereco("Cidade2", "Bairro2", "Rua2", "2", "22222-222"), new MercadoEnergia());
        SunSeeker seeker2 = new SunSeeker(contadorId++, "Emily", "emilyteste@gmail.com", "teste123", "71999999999", new Endereco("Cidade3", "Bairro3", "Rua3", "3", "33333-333"));
        SunShare share2 = new SunShare(contadorId++, "Beatriz", "beatrizteste@gmail.com", "teste123", "71999999999", new Endereco("Cidade4", "Bairro4", "Rua4", "4", "44444-444"), new MercadoEnergia());
        SunShare share3 = new SunShare(contadorId++, "Ana", "anateste@gmail.com", "teste123", "71999999999", new Endereco("Cidade5", "Bairro5", "Rua5", "5", "55555-555"), new MercadoEnergia());
    
        listaUsuarios.add(seeker1);
        listaUsuarios.add(share1);
        listaUsuarios.add(seeker2);
        listaUsuarios.add(share2);
        listaUsuarios.add(share3);
    }
    
    public static void listarUsuarios() {//Listar todos os usuários do sistema
        System.out.println("\nListar todos usuarios do SolCoop: ");
        for (Usuario usuario : listaUsuarios) {
            System.out.println("ID: " + usuario.getIdUsuario() + " | Nome: " + usuario.getNome() + " | Email: " + usuario.getEmail() + " | Telefone: " + usuario.getNumeroTelefone() + " | Endereço: " + usuario.getEndereco().formatarEndereco());

        }
    }

    public static void menu() {
        Scanner sc = new Scanner(System.in);
        int opcao;

        while (true) {
            System.out.println("\nBem vindo ao SolCoop! Aqui você pode compartilhar e comprar energia solar!");
            System.out.println("1 - Registar novo usuário");
            System.out.println("2 - Listar ofertas de energia");
            System.out.println("3 - Listar todos usuários");
            System.out.println("4 - Listar pedidos de energia");
            System.out.println("5 - Sair");
            opcao = sc.nextInt();

            switch (opcao) {
                case 1:
                    mercado.atualizarMercado();
                    registarNovoUsuario();
                    break;
                case 2:
                    System.out.println("Listar ofertas de energia");
                    adicionarUsuariosTestes();
                    mercado.adicionarOfertasTeste();
                    mercado.atualizarMercado();
                    mercado.atualizarMercadoPrint();
                    break;
                case 3:
                    listarUsuarios();
                    break;
                case 4:
                    System.out.println("//// Lista de pedidos ////");
                    mercado.listarPedidos();
                    break;
                case 5:
                    System.out.println("Saindo do SolCoop... Até mais!");
                    break;
                default:
                    System.out.println("Opção inválida!");
                    System.out.println("Digite uma opção válida (1, 2, 3 ou 4)");
                    break;
            }
        }
    }

    public static Usuario getUsuarioById(int idProcurado) {//Procura um usuário pelo ID e retorna o usuário
        for (Usuario usuario : listaUsuarios) {
            if (usuario.getIdUsuario() == idProcurado){
                return usuario;//Se encontrar o usuário, retorna o usuário
            }
        }
        return null;//Se não encontrar o usuário, retorna null
    }

    public static void registarNovoUsuario() {
        Scanner sc = new Scanner(System.in);
        int tipoUsuario;
        int novoId = contadorId++; //Gera um novo ID para o usuário

        System.out.println("\n\n/// Cadastro de novo usuário ///");

        System.out.println("Digite o nome do usuário: ");
        String nome = sc.nextLine();

        System.out.println("Digite o email do usuário: ");
        String email = sc.nextLine();

        System.out.println("Digite a senha do usuário: ");
        String senha = sc.nextLine();

        System.out.println("Digite o número de telefone do usuário: ");
        String numeroTelefone = sc.nextLine();

        System.out.println("Digite a cidade do usuário: ");
        String cidade = sc.nextLine();

        System.out.println("Digite o bairro do usuário: ");
        String bairro = sc.nextLine();

        System.out.println("Digite a rua do usuário: ");
        String rua = sc.nextLine();

        System.out.println("Digite o número da casa do usuário: ");
        String numero = sc.nextLine();

        System.out.println("Digite o CEP do usuário: ");
        String endereco = sc.nextLine();

        Endereco enderecoUsuario = new Endereco(cidade, bairro, rua, numero, endereco);

        System.out.println("\nAgora você é um usuário do SolCoop! Seja bem vindo, " + nome + "!\n");

        System.out.println("Escolha qual tipo de usuário você se identifica: ");
        System.out.println("1 - SunShare - Usuario que compartilha a energia gerada através de painéis solares.");
        System.out.println("2 - SunSeeker - Usuario que compra energia gerada através de painéis solares.");
        System.out.println("3 - SunPartner - Usuário que gostaria de dividir uma nova compra, junto a outro usuário, de placa solar.");
        tipoUsuario = sc.nextInt();

        Usuario usuario = null;//Se não for inicializado, o compilador não deixa compilar

        switch (tipoUsuario) {
            case 1:
                System.out.println("\nVocê escolheu ser um SunShare!");
                System.out.println("Você se tornou um compartilhador de energia solar! Por favor, cadastre os dados da sua geração de energia:\n");
                SunShare share4 = new SunShare(novoId, nome, email, senha, numeroTelefone, new Endereco(nome, email, senha, numeroTelefone, endereco), new MercadoEnergia());
                share4.entradaDados();
                share4.verificarMercado();
                listaUsuarios.add(share4);
                mercado.addOferta(share4);
                break;

            case 2:
                System.out.println("\nVocê escolheu ser um SunSeeker!");
                SunSeeker seeker3 = new SunSeeker(novoId, nome, email, senha, numeroTelefone, new Endereco(nome, email, senha, numeroTelefone, endereco));
                seeker3.ColetandoDados();
                seeker3.PrintValorFinal();
                listaUsuarios.add(seeker3);
                mercado.adicionarOfertasTeste();
                mercado.atualizarMercado();
                mercado.solicitarCompraEnergia();
                break;
                
            case 3: 
                System.out.println("\nVocê escolheu ser um SunPartner!");
                SunPartner partner1 = new SunPartner(tipoUsuario, nome, email, senha, numeroTelefone, enderecoUsuario, endereco, null, null);
                partner1.solicitarInfoUsuario();
                partner1.solicitarOrcamento();
                partner1.listarFilaDeUsuarios();
                listaUsuarios.add(partner1);
                break;
            default:
                System.out.println("Opção inválida!");
                System.out.println("Digite uma opção válida (1, 2 ou 3)");
                break;
        }

    }
}
```

## Main:

```java
import model.Usuario;

public class Main {
    public static void main(String[] args) throws Exception {
        Usuario.adicionarUsuariosTestes();
        Usuario.menu();
    }
}
```




