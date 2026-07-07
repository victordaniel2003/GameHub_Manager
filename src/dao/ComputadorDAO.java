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

    public void inserir(Computador computador) throws SQLException {

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

}