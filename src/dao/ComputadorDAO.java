package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.Computador;
import util.ConnectionFactory;

public class ComputadorDAO {

    public boolean existeId(int id) throws SQLException {

       String sql = "SELECT id FROM computador WHERE id = ?";

       try (Connection con = ConnectionFactory.getConnection();
           PreparedStatement pst = con.prepareStatement(sql)) {

           pst.setInt(1, id);

           ResultSet rs = pst.executeQuery();

           return rs.next();
        }
    }


    public void inserir(Computador computador) throws SQLException {

        if (existeId(computador.getId())) {
           throw new SQLException("Já existe um computador com esse ID.");
        } 

        String sql = "INSERT INTO computador(id,status) VALUES(?,?)";

        try (Connection con = ConnectionFactory.getConnection();
             PreparedStatement pst = con.prepareStatement(sql)) {

            pst.setInt(1, computador.getId());
            pst.setString(2, computador.getStatus());

            pst.executeUpdate();
        }

    }

    public void atualizar(Computador computador) throws SQLException {

        String sql = "UPDATE computador SET status=? WHERE id=?";

        try (Connection con = ConnectionFactory.getConnection();
             PreparedStatement pst = con.prepareStatement(sql)) {

            pst.setString(1, computador.getStatus());
            pst.setInt(2, computador.getId());

            pst.executeUpdate();
        }

    }

    public void excluir(int id) throws SQLException {

        String sql = "DELETE FROM computador WHERE id=?";

        try (Connection con = ConnectionFactory.getConnection();
             PreparedStatement pst = con.prepareStatement(sql)) {

            pst.setInt(1, id);

            pst.executeUpdate();
        }

    }

    public Computador buscarPorId(int id) throws SQLException {

        String sql = "SELECT * FROM computador WHERE id=?";

        try (Connection con = ConnectionFactory.getConnection();
             PreparedStatement pst = con.prepareStatement(sql)) {

            pst.setInt(1, id);

            ResultSet rs = pst.executeQuery();

            if (rs.next()) {

                Computador computador = new Computador();

                computador.setId(rs.getInt("id"));
                computador.setStatus(rs.getString("status"));

                return computador;

            }

        }

        return null;

    }

    public List<Computador> listarTodos() throws SQLException {

        List<Computador> lista = new ArrayList<>();

        String sql = "SELECT * FROM computador ORDER BY id";

        try (Connection con = ConnectionFactory.getConnection();
             PreparedStatement pst = con.prepareStatement(sql);
             ResultSet rs = pst.executeQuery()) {

            while (rs.next()) {

                Computador computador = new Computador();

                computador.setId(rs.getInt("id"));
                computador.setStatus(rs.getString("status"));

                lista.add(computador);

            }

        }

        return lista;

    }

    public int contar() throws SQLException {

        String sql = "SELECT COUNT(*) FROM computador";

        try (Connection con = ConnectionFactory.getConnection();
             PreparedStatement pst = con.prepareStatement(sql);
             ResultSet rs = pst.executeQuery()) {

            if (rs.next()) {
                return rs.getInt(1);
            }

        }

        return 0;

    }

    public boolean estaDisponivel(int id) throws SQLException {

       String sql = "SELECT status FROM computador WHERE id=?";

       try (Connection con = ConnectionFactory.getConnection();
           PreparedStatement pst = con.prepareStatement(sql)) {

           pst.setInt(1, id);

           ResultSet rs = pst.executeQuery();

           if (rs.next()) {
               return rs.getString("status").equalsIgnoreCase("Disponível")
        || rs.getString("status").equalsIgnoreCase("Disponivel")
        || rs.getString("status").equalsIgnoreCase("disponivel")
        || rs.getString("status").equalsIgnoreCase("Livre");
           }

           return false;
       }
   }

   public void alterarStatus(int id, String status) throws SQLException {

       String sql = "UPDATE computador SET status=? WHERE id=?";

       try (Connection con = ConnectionFactory.getConnection();
            PreparedStatement pst = con.prepareStatement(sql)) {

           pst.setString(1, status);
           pst.setInt(2, id);

           pst.executeUpdate();
       }
   }

   public List<Computador> listar() throws SQLException {


        List<Computador> lista = new ArrayList<>();


        String sql = "SELECT * FROM computador";


        try(Connection con = ConnectionFactory.getConnection();
            PreparedStatement pst = con.prepareStatement(sql);
            ResultSet rs = pst.executeQuery()){
            
            while(rs.next()){

             Computador pc = new Computador();

              pc.setId(rs.getInt("id"));

              pc.setStatus(rs.getString("status"));


            lista.add(pc);

        }

    }


    return lista;

    }

}
