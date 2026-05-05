/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ETEC;

import org.mariadb.jdbc.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author 3dstec2026
 */
public class Conexao {
       public static String status = "Não conectou...";
       public Conexao() {}
       public static java.sql.Connection getConexao() {
        Connection connection = null;

        try {
            // 1. Driver específico para MariaDB
            String driverName = "org.mariadb.jdbc.Driver"; 
            Class.forName(driverName);

            // 2. Configurações do servidor
            String serverName = "localhost";
            String mydatabase = "crudjavav2"; // Substitua pelo nome do seu banco
            
            // 3. URL com protocolo mariadb
            String url = "jdbc:mariadb://" + serverName + "/" + mydatabase;
            
            String username = "root";
            String password = "";

            connection = (Connection) DriverManager.getConnection(url, username, password);

            if (connection != null) {
                status = "Conectado com sucesso!";
            }
        } catch (ClassNotFoundException e) {
            status = "Driver não encontrado: " + e.getMessage();
        } catch (SQLException e) {
            status = "Erro ao conectar: " + e.getMessage();
        }

        return connection;
    }
}