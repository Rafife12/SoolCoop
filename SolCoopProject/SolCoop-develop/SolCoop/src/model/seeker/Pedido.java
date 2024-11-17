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
