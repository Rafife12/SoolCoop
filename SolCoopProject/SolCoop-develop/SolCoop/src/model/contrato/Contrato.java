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