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
            JOptionPane.showMessageDialog(null,"Cadastrado com sucesso!");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null,"Falha no cadastro! "+e);
        }finally {
            Conexao.closeConnection(conexao, stmt);
        }
    }

    public int idMax() {
        Connection conexao = Conexao.getConnection();
        Statement stmt;
        try {
            stmt = conexao.createStatement();
            ResultSet save = stmt.executeQuery("SHOW TABLE STATUS WHERE `Name` = 'cliente'");
            save.next();
            return save.getInt("Auto_increment");
        }catch (SQLException e){
            System.out.println("Falha ao conectar no banco de dados: "+e);
        }catch (Exception ex){
            System.out.println("Exception idMax: "+ex);
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
            JOptionPane.showMessageDialog(null,"Falha ao conectar no banco de dados "+e);
        }finally {
            Conexao.closeConnection(conexao,stmt,rs);
        }
        return clientes;
    }

    public Cliente buscaDados(Cliente c){
        Connection conexao = Conexao.getConnection();
        PreparedStatement stmt = null;
        try {
            stmt = conexao.prepareStatement("SELECT * FROM cliente WHERE id=?");
            stmt.setInt(1, c.getId());
            ResultSet rs = stmt.executeQuery();
            rs.next();
            c.setId(rs.getInt(1));
            c.setNome(rs.getString(2));
            c.setTelefoneResidencial(rs.getString(3));
            c.setTelefoneComercial(rs.getString(4));
            c.setTelefoneCelular(rs.getString(5));
            c.setEmail(rs.getString(6));
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null,"Falha ao conectar no banco de dados "+e);
        }finally {
            Conexao.closeConnection(conexao, stmt);
        }
        return c;
    }

    public void update(Cliente c){
        Connection conexao = Conexao.getConnection();
        PreparedStatement stmt = null;
        try {
            stmt = conexao.prepareStatement("UPDATE cliente SET nome=?, telefoneResidencial=?,"
                    + "telefoneComercial=?, telefoneCelular=?, email=?"
                    + "WHERE id=?");
            stmt.setString(1, c.getNome());
            stmt.setString(2, c.getTelefoneResidencial());
            stmt.setString(3, c.getTelefoneComercial());
            stmt.setString(4, c.getTelefoneCelular());
            stmt.setString(5, c.getEmail());
            stmt.setInt(6, c.getId());
            stmt.executeUpdate();
            JOptionPane.showMessageDialog(null,"Atualizado com sucesso");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null,"Falha ao atualizar "+e);
        }finally {
            Conexao.closeConnection(conexao, stmt);
        }
    }

    public void delete(Cliente c){
        Connection conexao = Conexao.getConnection();
        PreparedStatement stmt = null;
        try {
            stmt = conexao.prepareStatement("DELETE FROM cliente WHERE id=?");
            stmt.setInt(1, c.getId());
            stmt.executeUpdate();
            JOptionPane.showMessageDialog(null,"Excluido com sucesso");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null,"Erro ao excluir "+e);
        }finally {
            Conexao.closeConnection(conexao, stmt);
        }
    }
}
