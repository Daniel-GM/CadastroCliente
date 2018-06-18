package model.dao;

import conexao.Conexao;
import model.bean.Cliente;

import javax.swing.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ClienteDAO {
    public void create(Cliente c){
        Connection conexao = Conexao.getConnection();
        PreparedStatement stmt = null;
        try {
            stmt = conexao.prepareStatement("INSERT INTO cliente (nome, telefoneResidencial, "
                                              +"telefoneComercial, telefoneCelular, email)VALUES(?,?,?,?,?)");
            stmt.setString(1, c.getNome());
            stmt.setString(2, c.getTelefoneResidencial());
            stmt.setString(3, c.getTelefoneComercial());
            stmt.setString(4, c.getTelefoneCelular());
            stmt.setString(5, c.getEmail());

            stmt.executeUpdate();

            System.out.println(stmt);
            JOptionPane.showMessageDialog(null,"Deu bom!");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null,"Deu ruim! "+e);
        }finally {
            Conexao.closeConnection(conexao, stmt);
        }
    }
    public int idMax() {
        Connection conexao = Conexao.getConnection();
        Statement stmt;
        try {
            stmt = conexao.createStatement();
            ResultSet save = stmt.executeQuery("SELECT MAX(id) AS 'id' FROM cliente");
            save.next();
            return save.getInt("id");
        }catch (SQLException e){
            System.out.println("deu ruim aqui: "+e);
        }catch (Exception ex){
            System.out.println("deu ruim: "+ex);
            return 0;
        }finally {
            Conexao.closeConnection(conexao);
        }
        return 0;
    }

    public List<Cliente> read(){
        Connection conexao = Conexao.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Cliente> clientes = new ArrayList<>();

        try {
            stmt = conexao.prepareStatement("SELECT id, nome, email FROM cliente");
            rs = stmt.executeQuery();
            for(;rs.next();){
                Cliente cliente = new Cliente();
                cliente.setId(rs.getInt("id"));
                cliente.setNome(rs.getString("nome"));
                cliente.setEmail(rs.getString("email"));
                clientes.add(cliente);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null,"Dados da tabela com problema\n "+e);
        }finally {
            Conexao.closeConnection(conexao,stmt,rs);
        }
        return clientes;
    }

}
