/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ETEC;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author 3dstec2026
 */
public class CRUD {
    private int id;
    private String name;
    private String senha;

    public CRUD() {}
             
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }
    
    public void cadastrar() {
        String sql = "INSERT INTO usuario (nome, senha) VALUES (?, ?)";
        try (Connection conn = Conexao.getConexao();
            PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            // O PreparedStatement usa os valores que você setou via JPanel
            stmt.setString(1, this.name); 
            stmt.setString(2, this.senha);
            stmt.executeUpdate();
            
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao cadastrar: " + e.getMessage());
        }
        
    }
    
    public void editar()  {
        String sql = "UPDATE usuario SET nome = ?, senha = ? WHERE id = ?";
        try (Connection conn = Conexao.getConexao();
            PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            // O PreparedStatement usa os valores que você setou via JPanel
            stmt.setString(1, this.name); 
            stmt.setString(2, this.senha);
            stmt.setInt(3, this.id);
            stmt.executeUpdate();
            
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao editar  : " + e.getMessage());
        }
    }
    
    public void buscar()  {
        String sql = "SELECT id, nome, senha FROM usuario WHERE id=?";
        try (Connection conn = Conexao.getConexao();
            PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            // O PreparedStatement usa os valores que você setou via JPanel
            stmt.setInt(1, this.id);
            
            ResultSet rs = stmt.executeQuery();
            
            if (rs.next()) {
            this.name = rs.getString("nome");
            this.senha = rs.getString("senha");
        } else {
            throw new RuntimeException("Usuário não encontrado");
        }
            
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao buscar  : " + e.getMessage());
        }
    }
    
    public void deletar() {
        String sql = "DELETE FROM usuario WHERE id = ?";
        try (Connection conn = Conexao.getConexao();
            PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            // O PreparedStatement usa os valores que você setou via JPanel
            stmt.setInt(1, this.id); 
            int linhasAfetadas = stmt.executeUpdate();
        
        if (linhasAfetadas == 0) {
            throw new RuntimeException("Usuário não encontrado para deletar");
        }
        
    } catch (SQLException e) {
        throw new RuntimeException("Erro ao remover: " + e.getMessage());
    }
}
    
    
}
