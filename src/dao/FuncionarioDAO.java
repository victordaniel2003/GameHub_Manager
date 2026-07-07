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

    public boolean existeId(int id) throws SQLException {

        String sql = "SELECT idfuncionario FROM funcionario WHERE idfuncionario = ?";

        try (Connection con = ConnectionFactory.getConnection();
            PreparedStatement pst = con.prepareStatement(sql)) {

            pst.setInt(1, id);

            ResultSet rs = pst.executeQuery();

            return rs.next();
        }
    }


    public void inserir(Funcionario funcionario) throws SQLException {

        if (existeId(funcionario.getId())) {
            throw new SQLException("Já existe um funcionário com esse ID.");
        } 

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

package view;

import java.sql.*;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

import dao.FuncionarioDAO;
import model.Funcionario;

public class FuncionarioJFrame extends javax.swing.JFrame {
    
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(FuncionarioJFrame.class.getName());

    
    public FuncionarioJFrame() {
        initComponents();
        FuncionarioData();
    }

    
    private void FuncionarioData(){
         try {

            FuncionarioDAO dao = new FuncionarioDAO();

            DefaultTableModel modelo = (DefaultTableModel) table1.getModel();

            modelo.setRowCount(0);

            for (Funcionario funcionario : dao.listarTodos()) {

                modelo.addRow(new Object[]{

                    funcionario.getId(),
                    funcionario.getNome(),
                    funcionario.getEmail(),
                    funcionario.getEndereco()

                });

            }

        } catch (SQLException ex) {

            Logger.getLogger(FuncionarioJFrame.class.getName()).log(Level.SEVERE, null, ex);

        }
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">                          
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        table1 = new javax.swing.JTable();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        txtIdFuncionario = new javax.swing.JTextField();
        txtNomeFuncionario = new javax.swing.JTextField();
        txtEmailFuncionario = new javax.swing.JTextField();
        txtAddressFuncionario = new javax.swing.JTextField();
        btnInsert = new javax.swing.JButton();
        btnUpdate = new javax.swing.JButton();
        btnDelete = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();
        btnTotalFuncionario = new javax.swing.JButton();
        txtTotalFuncionario = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(153, 255, 153));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("FUNCIONÁRIO");

        jButton1.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jButton1.setText("X");
        jButton1.addActionListener(this::jButton1ActionPerformed);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addGap(164, 164, 164)
                .addComponent(jButton1)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1)
                    .addComponent(jLabel1))
                .addGap(0, 0, Short.MAX_VALUE))
        );

        table1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Nome", "Email", "Endereço"
            }
        ));
        jScrollPane1.setViewportView(table1);

        jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel2.setText("ID");

        jLabel3.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel3.setText("Nome");

        jLabel4.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel4.setText("Email");

        jLabel5.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel5.setText("Endereço");

        txtIdFuncionario.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        txtNomeFuncionario.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        txtEmailFuncionario.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        txtAddressFuncionario.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        btnInsert.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        btnInsert.setText("Insert");
        btnInsert.addActionListener(this::btnInsertActionPerformed);

        btnUpdate.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        btnUpdate.setText("Update");
        btnUpdate.addActionListener(this::btnUpdateActionPerformed);

        btnDelete.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        btnDelete.setText("Delete");
        btnDelete.addActionListener(this::btnDeleteActionPerformed);

        btnTotalFuncionario.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        btnTotalFuncionario.setText("Total de Funcionário");
        btnTotalFuncionario.addActionListener(this::btnTotalFuncionarioActionPerformed);

        txtTotalFuncionario.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jScrollPane1)
            .addGroup(layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, 73, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(txtIdFuncionario, javax.swing.GroupLayout.DEFAULT_SIZE, 192, Short.MAX_VALUE)
                    .addComponent(txtNomeFuncionario)
                    .addComponent(txtEmailFuncionario)
                    .addComponent(txtAddressFuncionario))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnInsert)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnUpdate)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnDelete)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnTotalFuncionario)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txtTotalFuncionario, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(48, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(txtIdFuncionario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(txtNomeFuncionario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(15, 15, 15)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(txtEmailFuncionario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(15, 15, 15)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5)
                            .addComponent(txtAddressFuncionario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnInsert)
                    .addComponent(btnUpdate)
                    .addComponent(btnDelete)
                    .addComponent(btnTotalFuncionario)
                    .addComponent(txtTotalFuncionario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(24, 24, 24)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 143, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>                        

    private void btnInsertActionPerformed(java.awt.event.ActionEvent evt) {                                          
        try {

            Funcionario funcionario = new Funcionario();

            funcionario.setId(Integer.parseInt(txtIdFuncionario.getText()));
            funcionario.setNome(txtNomeFuncionario.getText());
            funcionario.setEmail(txtEmailFuncionario.getText());
            funcionario.setEndereco(txtAddressFuncionario.getText());

            FuncionarioDAO dao = new FuncionarioDAO();

            dao.inserir(funcionario);

            JOptionPane.showMessageDialog(this,
                    "Funcionário inserido com sucesso!");

            FuncionarioData();

        } catch (SQLException ex) {

             JOptionPane.showMessageDialog(this, ex.getMessage());

        } 
    }                                         

    private void btnUpdateActionPerformed(java.awt.event.ActionEvent evt) {                                          
        try {

            Funcionario funcionario = new Funcionario();

            funcionario.setId(Integer.parseInt(txtIdFuncionario.getText()));
            funcionario.setNome(txtNomeFuncionario.getText());
            funcionario.setEmail(txtEmailFuncionario.getText());
            funcionario.setEndereco(txtAddressFuncionario.getText());

            FuncionarioDAO dao = new FuncionarioDAO();

            dao.atualizar(funcionario);

            JOptionPane.showMessageDialog(this,
                    "Funcionário atualizado com sucesso!");

            FuncionarioData();

        } catch (Exception ex) {

            Logger.getLogger(FuncionarioJFrame.class.getName()).log(Level.SEVERE, null, ex);

        }
    }                                         

    private void btnDeleteActionPerformed(java.awt.event.ActionEvent evt) {                                          
        try {

            int id = Integer.parseInt(txtIdFuncionario.getText());

            FuncionarioDAO dao = new FuncionarioDAO();

            dao.excluir(id);

            JOptionPane.showMessageDialog(this,
                    "Funcionário deletado com sucesso!");

            FuncionarioData();

        } catch (Exception ex) {

            Logger.getLogger(FuncionarioJFrame.class.getName()).log(Level.SEVERE, null, ex);

        }
    }                                         

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {                                         
        dispose();
    }                                        

    private void btnTotalFuncionarioActionPerformed(java.awt.event.ActionEvent evt) {                                                    
        try {

            FuncionarioDAO dao = new FuncionarioDAO();

            txtTotalFuncionario.setText(String.valueOf(dao.contar()));

        } catch (SQLException ex) {

            Logger.getLogger(FuncionarioJFrame.class.getName()).log(Level.SEVERE, null, ex);

        }
    }                                                   

    
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ReflectiveOperationException | javax.swing.UnsupportedLookAndFeelException ex) {
            logger.log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> new FuncionarioJFrame().setVisible(true));
    }

    // Variables declaration - do not modify                     
    private javax.swing.JButton btnDelete;
    private javax.swing.JButton btnInsert;
    private javax.swing.JButton btnTotalFuncionario;
    private javax.swing.JButton btnUpdate;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable table1;
    private javax.swing.JTextField txtAddressFuncionario;
    private javax.swing.JTextField txtEmailFuncionario;
    private javax.swing.JTextField txtIdFuncionario;
    private javax.swing.JTextField txtNomeFuncionario;
    private javax.swing.JTextField txtTotalFuncionario;
    // End of variables declaration                   
}
