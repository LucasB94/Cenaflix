package filmeconexao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexao {

    public static void main(String[] args) {

        Conexao conexao = new Conexao();
        Connection conn = conexao.conectar();
        conexao.desconectar(conn);
    }

    public Connection conectar() {
        Connection conn = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/Cenaflix", "root", "123456");
            System.out.println("Conectou com o banco de dados!!!!");
        } catch (SQLException ex) {
            System.out.println("Erro: Não foi possivel se conectar no banco de dados!");
        } catch (ClassNotFoundException ex) {
            System.out.println("Erro: Driver JDBC nao encontrado!.");
        }

        return conn;
    }

    public void desconectar(Connection conn) {
        try {
            if (conn != null && !conn.isClosed()) {
                conn.close();
                System.out.println("Voce se desconectou do banco de dados.");
            }
        } catch (SQLException ex) {
            System.out.println("Nao foi possivel desconectar do banco dados.");
        }
    }
    
    public Connection getConexao() {
    
            try {
                Connection conn = DriverManager.getConnection(
                        "jdbc:mysql://localhost/Cenaflix ", 
                        "root",
                        "123456"
                );
                return conn;
    
            } catch (Exception e) {
                System.out.println("Erro ao conectar: " + e.getMessage());
                return null;
            }
    
        }

}
