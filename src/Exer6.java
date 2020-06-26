import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Exer6 {
    public static void main(String[] args) {
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
            System.out.println("Conexao aberta com sucesso");

            Statement sttm = conexao.createStatement();
            System.out.println("Instrução criada com sucesso");

            int linhas = sttm.executeUpdate("insert into cliente(nome, email) values ('Renan', 'renan@gmail.com')");
            System.out.println("Resgistros inseridos: " + linhas);

            linhas = sttm.executeUpdate("insert into cliente(nome, email) values ('Evelyn', 'evelyn@gmail.com')");
            System.out.println("Resgistros inseridos: " + linhas);

            sttm.close();
            conexao.close();
            System.out.println("Conexao fechada com sucesso");
        } catch (SQLException e) {
            System.out.println("Erro ao abrir a conexao");
            e.printStackTrace();
        }
    }
}
