package model;

public class Aluguel {

    private int idAluguel;
    private String nomeCliente;
    private int idComputador;
    private String qtdHoras;

    public Aluguel() {
    }

    public Aluguel(int idAluguel, String nomeCliente, int idComputador, String qtdHoras) {
        this.idAluguel = idAluguel;
        this.nomeCliente = nomeCliente;
        this.idComputador = idComputador;
        this.qtdHoras = qtdHoras;
    }

    public int getIdAluguel() {
        return idAluguel;
    }

    public void setIdAluguel(int idAluguel) {
        this.idAluguel = idAluguel;
    }

    public String getNomeCliente() {
        return nomeCliente;
    }

    public void setNomeCliente(String nomeCliente) {
        this.nomeCliente = nomeCliente;
    }

    public int getIdComputador() {
        return idComputador;
    }

    public void setIdComputador(int idComputador) {
        this.idComputador = idComputador;
    }

    public String getQtdHoras() {
        return qtdHoras;
    }

    public void setQtdHoras(String qtdHoras) {
        this.qtdHoras = qtdHoras;
    }

}