import java.sql.*;

public class Exer8 {
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


            PreparedStatement inserir = conexao.prepareStatement(
                    "insert into cliente (nome, email) values (?,?)");
            inserir.setObject(1,"Ricardo");
            inserir.setObject(2,"ricardo@hotmail.com");
            inserir.execute();

            PreparedStatement select = conexao.prepareStatement(
                    "select * from cliente where email like ?");
            select.setObject(1,"%hotmail%");

            ResultSet rs = select.executeQuery();
            while(rs.next())
            {
                System.out.println(rs.getInt("id") + " - " + rs.getString("nome")
                        + " - " + rs.getString("email"));
            }

            rs.close();
            select.close();
            inserir.close();
            conexao.close();
            System.out.println("Conexao fechada com sucesso");
        } catch (SQLException e) {
            System.out.println("Erro ao abrir a conexao");
            e.printStackTrace();
        }
    }
}
