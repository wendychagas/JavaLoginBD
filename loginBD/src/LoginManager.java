import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginManager {

    // Método para verificar as credenciais do usuário
    public boolean verificarCredenciais(String usuarioDigitado, String senhaDigitada) {
        // Informações de conexão
        String url = "jdbc:mysql://localhost:3306/projeto";
        String usuarioBD = "root";
        String senhaBD = "123456";

        // Objeto de conexão
        try (Connection conexao = DriverManager.getConnection(url, usuarioBD, senhaBD)) {
            // Consulta SQL para verificar as credenciais
            String sql = "SELECT * FROM Usuario WHERE usuario = ? AND senha = ?";
            try (PreparedStatement ps = conexao.prepareStatement(sql)) {
                ps.setString(1, usuarioDigitado);
                ps.setString(2, senhaDigitada);
                
                // Executar a consulta
                try (ResultSet rs = ps.executeQuery()) {
                    // Se o conjunto de resultados contiver uma linha, então as credenciais são válidas
                    return rs.next();
                }
            }
        } catch (SQLException e) {
            // Lidar com erros de SQL
            System.err.println("Erro ao verificar as credenciais: " + e.getMessage());
            return false;
        }
    }   
    
    // Método para inserir um novo usuário no banco de dados
    public boolean inserirNovoUsuario(String usuario, String senha) {
        // Informações de conexão com o banco de dados
        String url = "jdbc:mysql://localhost:3306/projeto";
        String usuarioBD = "root";
        String senhaBD = "123456";

        // Objeto de conexão
        try (Connection conexao = DriverManager.getConnection(url, usuarioBD, senhaBD)) {
            // Consulta SQL para inserir um novo usuário
            String sql = "INSERT INTO Usuario (usuario, senha) VALUES (?, ?)";
            try (PreparedStatement ps = conexao.prepareStatement(sql)) {
                ps.setString(1, usuario);
                ps.setString(2, senha);

                // Executar a consulta de inserção
                int linhasAfetadas = ps.executeUpdate();

                // Se linhasAfetadas for maior que zero, significa que a inserção foi bem sucedida
                return linhasAfetadas > 0;
            }
        } catch (SQLException e) {
            // Lidar com erros de SQL
            System.err.println("Erro ao inserir novo usuario: " + e.getMessage());
            return false;
        }
    }
    
    
    public static void main(String[] args) {
        LoginManager loginManager = new LoginManager();

        // Credenciais digitadas pelo usuário
        String usuarioDigitado = "admin";
        String senhaDigitada = "admin";

        // Verificar as credenciais
        if (loginManager.verificarCredenciais(usuarioDigitado, senhaDigitada)) {
            System.out.println("Login bem-sucedido!");
        } else {
            System.out.println("Credenciais invalidas. Tente novamente.");
        }
    }    
}