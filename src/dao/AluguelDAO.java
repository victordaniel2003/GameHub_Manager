package dao;

import model.Aluguel;
import util.ConnectionFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AluguelDAO {

    public void inserir(Aluguel aluguel) throws SQLException {

       ComputadorDAO computadorDAO = new ComputadorDAO();

       if (!computadorDAO.estaDisponivel(aluguel.getIdComputador())) {
           throw new SQLException("Este computador não está disponível.");
       }

       String sql = "INSERT INTO aluguel(idaluguel,nomecliente,idcomputador,qtdhoras) VALUES(?,?,?,?)";

       try (Connection con = ConnectionFactory.getConnection();
           PreparedStatement pst = con.prepareStatement(sql)) {

           pst.setInt(1, aluguel.getIdAluguel());
           pst.setString(2, aluguel.getNomeCliente());
           pst.setInt(3, aluguel.getIdComputador());
           pst.setString(4, aluguel.getQtdHoras());

           pst.executeUpdate();
       }

       computadorDAO.alterarStatus(aluguel.getIdComputador(), "Ocupado");
   }

    public void excluir(int id) throws SQLException {

        String sql = "DELETE FROM aluguel WHERE idaluguel=?";

        try (Connection con = ConnectionFactory.getConnection();
             PreparedStatement pst = con.prepareStatement(sql)) {

            pst.setInt(1, id);

            pst.executeUpdate();
        }
    }

    public Aluguel buscarPorId(int id) throws SQLException {

        String sql = "SELECT * FROM aluguel WHERE idaluguel=?";

        try (Connection con = ConnectionFactory.getConnection();
             PreparedStatement pst = con.prepareStatement(sql)) {

            pst.setInt(1, id);

            ResultSet rs = pst.executeQuery();

            if (rs.next()) {

                Aluguel aluguel = new Aluguel();

                aluguel.setIdAluguel(rs.getInt("idaluguel"));
                aluguel.setNomeCliente(rs.getString("nomecliente"));
                aluguel.setIdComputador(rs.getInt("idcomputador"));
                aluguel.setQtdHoras(rs.getString("qtdhoras"));

                return aluguel;
            }
        }

        return null;
    }

    public List<Aluguel> listarTodos() throws SQLException {

        List<Aluguel> lista = new ArrayList<>();

        String sql = "SELECT * FROM aluguel ORDER BY idaluguel";

        try (Connection con = ConnectionFactory.getConnection();
             PreparedStatement pst = con.prepareStatement(sql);
             ResultSet rs = pst.executeQuery()) {

            while (rs.next()) {

                Aluguel aluguel = new Aluguel();

                aluguel.setIdAluguel(rs.getInt("idaluguel"));
                aluguel.setNomeCliente(rs.getString("nomecliente"));
                aluguel.setIdComputador(rs.getInt("idcomputador"));
                aluguel.setQtdHoras(rs.getString("qtdhoras"));

                lista.add(aluguel);
            }
        }

        return lista;
    }

    public int contar() throws SQLException {

        String sql = "SELECT COUNT(*) FROM aluguel";

        try (Connection con = ConnectionFactory.getConnection();
             PreparedStatement pst = con.prepareStatement(sql);
             ResultSet rs = pst.executeQuery()) {

            if (rs.next()) {
                return rs.getInt(1);
            }
        }

        return 0;
    }
    
    public void atualizar(Aluguel aluguel) throws SQLException {

        String sql = "UPDATE aluguel SET nomecliente=?, idcomputador=?, qtdhoras=? WHERE idaluguel=?";

        try (Connection con = ConnectionFactory.getConnection();
             PreparedStatement pst = con.prepareStatement(sql)) {

            pst.setString(1, aluguel.getNomeCliente());
            pst.setInt(2, aluguel.getIdComputador());
            pst.setString(3, aluguel.getQtdHoras());
            pst.setInt(4, aluguel.getIdAluguel());

            pst.executeUpdate();
        }
    }
    
    public List<Aluguel> listar() throws SQLException {


    List<Aluguel> lista = new ArrayList<>();


    String sql = "SELECT * FROM aluguel";


    try(Connection con = ConnectionFactory.getConnection();
        PreparedStatement pst = con.prepareStatement(sql);
        ResultSet rs = pst.executeQuery()){

        while(rs.next()){

            Aluguel aluguel = new Aluguel();

            aluguel.setIdAluguel(rs.getInt("idaluguel"));
            aluguel.setNomeCliente(rs.getString("nomecliente"));
            aluguel.setIdComputador(rs.getInt("idcomputador"));
            aluguel.setQtdHoras(rs.getString("qtdhoras"));

            lista.add(aluguel);

            }

        }


        return lista;

    }
}
