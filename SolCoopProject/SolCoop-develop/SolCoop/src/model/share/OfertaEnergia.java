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
