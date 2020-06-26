import java.sql.*;

public class Exer9 {
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
            conexao.setAutoCommit(false);

            PreparedStatement inserir = conexao.prepareStatement(
                    "insert into cliente (nome, email) values (?,?)");

            inserir.setObject(1,"xxx");
            inserir.setObject(2,"xxxxx@hotmail.com");
            inserir.execute();

            inserir.setObject(1,"yyy");
            inserir.setObject(2,"yyyyy@hotmail.com");
            inserir.execute();

            //conexao.commit();
            conexao.rollback();
            inserir.close();

            System.out.println(" -- depois do commit --");
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
    }
}
