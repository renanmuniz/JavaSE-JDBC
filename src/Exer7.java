import java.sql.*;

public class Exer7 {
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

            ResultSet rs = sttm.executeQuery("select * from cliente");
            while(rs.next())
            {
                System.out.println(rs.getInt("id") + " - " + rs.getString("nome")
                        + " - " + rs.getString("email"));
            }

            rs.close();
            sttm.close();
            conexao.close();
            System.out.println("Conexao fechada com sucesso");
        } catch (SQLException e) {
            System.out.println("Erro ao abrir a conexao");
            e.printStackTrace();
        }
    }
}
