import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexao {
    public static void main(String[] args) {
        // Informações de conexão
        String url = "jdbc:mysql://localhost:3306/projeto";
        String usuario = "root";
        String senha = "123456";

        // Objeto de conexão
        Connection conexao = null;

        try {
            // Criar a conexão
            conexao = DriverManager.getConnection(url, usuario, senha);
            
            // Verificar se a conexão foi bem sucedida
            if (conexao != null) {
                System.out.println("Conexao bem sucedida!");
                // Aqui você pode realizar operações com o banco de dados
            } else {
                System.out.println("Nao foi possível estabelecer conexao com o banco de dados.");
            }
        } catch (SQLException e) {
            // Lidar com erros de SQL
            System.out.println("Ocorreu um erro ao tentar se conectar ao banco de dados:");
            e.printStackTrace();
        } finally {
            // Fechar a conexão
            if (conexao != null) {
                try {
                    conexao.close();
                } catch (SQLException e) {
                    // Lidar com erros de fechamento de conexão
                    e.printStackTrace();
                }
            }
        }
    }
}

