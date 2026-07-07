package dao;

import model.Funcionario;
import util.ConnectionFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class FuncionarioDAO {

    public void inserir(Funcionario funcionario) throws SQLException {

        String sql = "INSERT INTO funcionario(idfuncionario,nomefuncionario,emailfuncionario,enderecofuncionario) VALUES(?,?,?,?)";

        try (Connection con = ConnectionFactory.getConnection();
             PreparedStatement pst = con.prepareStatement(sql)) {

            pst.setInt(1, funcionario.getId());
            pst.setString(2, funcionario.getNome());
            pst.setString(3, funcionario.getEmail());
            pst.setString(4, funcionario.getEndereco());

            pst.executeUpdate();

        }

    }

    public void atualizar(Funcionario funcionario) throws SQLException {

        String sql = "UPDATE funcionario SET nomefuncionario=?, emailfuncionario=?, enderecofuncionario=? WHERE idfuncionario=?";

        try (Connection con = ConnectionFactory.getConnection();
             PreparedStatement pst = con.prepareStatement(sql)) {

            pst.setString(1, funcionario.getNome());
            pst.setString(2, funcionario.getEmail());
            pst.setString(3, funcionario.getEndereco());
            pst.setInt(4, funcionario.getId());

            pst.executeUpdate();

        }

    }

    public void excluir(int id) throws SQLException {

        String sql = "DELETE FROM funcionario WHERE idfuncionario=?";

        try (Connection con = ConnectionFactory.getConnection();
             PreparedStatement pst = con.prepareStatement(sql)) {

            pst.setInt(1, id);

            pst.executeUpdate();

        }

    }

    public Funcionario buscarPorId(int id) throws SQLException {

        String sql = "SELECT * FROM funcionario WHERE idfuncionario=?";

        try (Connection con = ConnectionFactory.getConnection();
             PreparedStatement pst = con.prepareStatement(sql)) {

            pst.setInt(1, id);

            ResultSet rs = pst.executeQuery();

            if (rs.next()) {

                Funcionario funcionario = new Funcionario();

                funcionario.setId(rs.getInt("idfuncionario"));
                funcionario.setNome(rs.getString("nomefuncionario"));
                funcionario.setEmail(rs.getString("emailfuncionario"));
                funcionario.setEndereco(rs.getString("enderecofuncionario"));

                return funcionario;

            }

        }

        return null;

    }

    public List<Funcionario> listarTodos() throws SQLException {

        List<Funcionario> lista = new ArrayList<>();

        String sql = "SELECT * FROM funcionario ORDER BY idfuncionario";

        try (Connection con = ConnectionFactory.getConnection();
             PreparedStatement pst = con.prepareStatement(sql);
             ResultSet rs = pst.executeQuery()) {

            while (rs.next()) {

                Funcionario funcionario = new Funcionario();

                funcionario.setId(rs.getInt("idfuncionario"));
                funcionario.setNome(rs.getString("nomefuncionario"));
                funcionario.setEmail(rs.getString("emailfuncionario"));
                funcionario.setEndereco(rs.getString("enderecofuncionario"));

                lista.add(funcionario);

            }

        }

        return lista;

    }

    public int contar() throws SQLException {

        String sql = "SELECT COUNT(*) FROM funcionario";

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