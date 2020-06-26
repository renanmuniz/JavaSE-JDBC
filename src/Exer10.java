import java.sql.*;
import java.time.LocalDateTime;

public class Exer10 {
    public static void main(String[] args) {
        System.out.println("Começou: " + LocalDateTime.now());

        try{
            Class.forName("org.hsqldb.jdbcDriver");
            System.out.println("Driver carregado com sucesso.");
        } catch (ClassNotFoundException e) {
            System.out.println("Erro ao carregar driver.");
            e.printStackTrace();
        }

        Connection conexao = null;
        try {
            String url = "jdbc:hsqldb:file:D:/UniprimeWorkspaceIntelliJ/JavaSE-JDBC/hypersql/bases/aula;shutdown=true";
            String usuario ="SA";
            String senha = "1234";
            conexao = DriverManager.getConnection(url,usuario,senha);
            conexao.setAutoCommit(false);

            PreparedStatement inserir = conexao.prepareStatement(
                    "insert into cliente (nome, email) values (?,?)");

            for(int i = 1; i <= 100000; i++) {
                inserir.setObject(1,"Batch - " + i);
                inserir.setObject(2,"Batch" + i + "@hotmail.com");
                inserir.addBatch();
            }

            int [] linhas = inserir.executeBatch();

            conexao.commit();
            //conexao.rollback();
            inserir.close();

            ResultSet rs = conexao.createStatement().executeQuery("select * from cliente");
            while(rs.next())
            {
                System.out.println(rs.getInt("id") + " - " + rs.getString("nome")
                        + " - " + rs.getString("email"));
            }
            rs.close();

            conexao.close();
            System.out.println("Conexao fechada com sucesso");

        } catch (SQLException e) {
            System.out.println("Erro ao abrir a conexao");
            e.printStackTrace();
        }
        System.out.println("Terminou: " + LocalDateTime.now());
    }
}
