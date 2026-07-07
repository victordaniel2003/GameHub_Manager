package dao;

import model.Cliente;
import util.ConnectionFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ClienteDAO {

    public void inserir(Cliente cliente) throws SQLException {

        String sql = "INSERT INTO cliente(id,nome,email,endereco) VALUES(?,?,?,?)";

        try (Connection con = ConnectionFactory.getConnection();
             PreparedStatement pst = con.prepareStatement(sql)) {

            pst.setInt(1, cliente.getId());
            pst.setString(2, cliente.getNome());
            pst.setString(3, cliente.getEmail());
            pst.setString(4, cliente.getEndereco());

            pst.executeUpdate();
        }
    }

    public void atualizar(Cliente cliente) throws SQLException {

        String sql = "UPDATE cliente SET nome=?, email=?, endereco=? WHERE id=?";

        try (Connection con = ConnectionFactory.getConnection();
             PreparedStatement pst = con.prepareStatement(sql)) {

            pst.setString(1, cliente.getNome());
            pst.setString(2, cliente.getEmail());
            pst.setString(3, cliente.getEndereco());
            pst.setInt(4, cliente.getId());

            pst.executeUpdate();
        }
    }

    public void excluir(int id) throws SQLException {

        String sql = "DELETE FROM cliente WHERE id=?";

        try (Connection con = ConnectionFactory.getConnection();
             PreparedStatement pst = con.prepareStatement(sql)) {

            pst.setInt(1, id);

            pst.executeUpdate();
        }
    }

    public Cliente buscarPorId(int id) throws SQLException {

        String sql = "SELECT * FROM cliente WHERE id=?";

        try (Connection con = ConnectionFactory.getConnection();
             PreparedStatement pst = con.prepareStatement(sql)) {

            pst.setInt(1, id);

            ResultSet rs = pst.executeQuery();

            if (rs.next()) {

                Cliente cliente = new Cliente();

                cliente.setId(rs.getInt("id"));
                cliente.setNome(rs.getString("nome"));
                cliente.setEmail(rs.getString("email"));
                cliente.setEndereco(rs.getString("endereco"));

                return cliente;
            }
        }

        return null;
    }

    public List<Cliente> listarTodos() throws SQLException {

        List<Cliente> lista = new ArrayList<>();

        String sql = "SELECT * FROM cliente ORDER BY id";

        try (Connection con = ConnectionFactory.getConnection();
             PreparedStatement pst = con.prepareStatement(sql);
             ResultSet rs = pst.executeQuery()) {

            while (rs.next()) {

                Cliente cliente = new Cliente();

                cliente.setId(rs.getInt("id"));
                cliente.setNome(rs.getString("nome"));
                cliente.setEmail(rs.getString("email"));
                cliente.setEndereco(rs.getString("endereco"));

                lista.add(cliente);
            }
        }

        return lista;
    }

    public int contar() throws SQLException {

        String sql = "SELECT COUNT(*) FROM cliente";

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


package dao;

import model.Cliente;
import util.ConnectionFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ClienteDAO {
    
    public boolean existeId(int id) throws SQLException {

      String sql = "SELECT id FROM cliente WHERE id=?";

      try (Connection con = ConnectionFactory.getConnection();
           PreparedStatement pst = con.prepareStatement(sql)) {

           pst.setInt(1, id);

           ResultSet rs = pst.executeQuery();

          return rs.next();
        }
    }

    public boolean existeEmail(String email) throws SQLException {

       String sql = "SELECT email FROM cliente WHERE email = ?";

       try (Connection con = ConnectionFactory.getConnection();
           PreparedStatement pst = con.prepareStatement(sql)) {

           pst.setString(1, email);

           ResultSet rs = pst.executeQuery();

           return rs.next();
       }
   }


    public void inserir(Cliente cliente) throws SQLException {

       if (existeId(cliente.getId())) {
           throw new SQLException("Já existe um cliente com esse ID.");
       }

       if (existeEmail(cliente.getEmail())) {
           throw new SQLException("Já existe um cliente com esse e-mail.");
       }

       String sql = "INSERT INTO cliente(id,nome,email,endereco) VALUES(?,?,?,?)";

       try (Connection con = ConnectionFactory.getConnection();
           PreparedStatement pst = con.prepareStatement(sql)) {

           pst.setInt(1, cliente.getId());
           pst.setString(2, cliente.getNome());
           pst.setString(3, cliente.getEmail());
           pst.setString(4, cliente.getEndereco());

           pst.executeUpdate();
       }
   }


    public void atualizar(Cliente cliente) throws SQLException {

        String sql = "UPDATE cliente SET nome=?, email=?, endereco=? WHERE id=?";

        try (Connection con = ConnectionFactory.getConnection();
             PreparedStatement pst = con.prepareStatement(sql)) {

            pst.setString(1, cliente.getNome());
            pst.setString(2, cliente.getEmail());
            pst.setString(3, cliente.getEndereco());
            pst.setInt(4, cliente.getId());

            pst.executeUpdate();
        }
    }

    public void excluir(int id) throws SQLException {

        String sql = "DELETE FROM cliente WHERE id=?";

        try (Connection con = ConnectionFactory.getConnection();
             PreparedStatement pst = con.prepareStatement(sql)) {

            pst.setInt(1, id);

            pst.executeUpdate();
        }
    }

    public Cliente buscarPorId(int id) throws SQLException {

        String sql = "SELECT * FROM cliente WHERE id=?";

        try (Connection con = ConnectionFactory.getConnection();
             PreparedStatement pst = con.prepareStatement(sql)) {

            pst.setInt(1, id);

            ResultSet rs = pst.executeQuery();

            if (rs.next()) {

                Cliente cliente = new Cliente();

                cliente.setId(rs.getInt("id"));
                cliente.setNome(rs.getString("nome"));
                cliente.setEmail(rs.getString("email"));
                cliente.setEndereco(rs.getString("endereco"));

                return cliente;
            }
        }

        return null;
    }

    public List<Cliente> listarTodos() throws SQLException {

        List<Cliente> lista = new ArrayList<>();

        String sql = "SELECT * FROM cliente ORDER BY id";

        try (Connection con = ConnectionFactory.getConnection();
             PreparedStatement pst = con.prepareStatement(sql);
             ResultSet rs = pst.executeQuery()) {

            while (rs.next()) {

                Cliente cliente = new Cliente();

                cliente.setId(rs.getInt("id"));
                cliente.setNome(rs.getString("nome"));
                cliente.setEmail(rs.getString("email"));
                cliente.setEndereco(rs.getString("endereco"));

                lista.add(cliente);
            }
        }

        return lista;
    }

    public int contar() throws SQLException {

        String sql = "SELECT COUNT(*) FROM cliente";

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

