package conexao;

import java.sql.*;

public class Conexao {
    private static final String DRIVER = "com.mysql.jdbc.Driver";
    private static final String URL = "jdbc:mysql://localhost:3306/cadastro";
    private static final String USER = "root";
    private static final String PASS = "";

    public static Connection getConnection() {
        try {
            Class.forName(DRIVER);
            return DriverManager.getConnection(URL, USER, PASS);
        }catch (ClassNotFoundException ex){
            throw new RuntimeException("ERRO: ",ex);
        } catch (SQLException e) {
            throw new RuntimeException("ERRO: ",e);
        }
    }

    public static void closeConnection(Connection conexao){
        try {
            if(conexao != null)
                conexao.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void closeConnection(Connection conexao, PreparedStatement stmt){
        closeConnection(conexao);
        try {
            if(stmt != null)
                stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void closeConnection(Connection conexao, PreparedStatement stmt, ResultSet rs){
        closeConnection(conexao, stmt);
        try {
            if(rs != null)
                rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
