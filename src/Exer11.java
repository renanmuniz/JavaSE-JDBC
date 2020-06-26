import com.sun.rowset.CachedRowSetImpl;

import javax.sql.rowset.CachedRowSet;
import java.sql.*;

public class Exer11 {
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
            ResultSet res = sttm.executeQuery("select * from cliente");
            CachedRowSet cached = new CachedRowSetImpl();
            cached.populate(res); //popula o cache das informacoes

            res.close();
            sttm.close();
            conexao.close();
            System.out.println("Conexao fechada com sucesso");

            //acessa os dados em memoria sem consumir banco de dados:
            while(cached.next())
            {
                System.out.println(cached.getInt("id") + " - " + cached.getString("nome")
                        + " - " + cached.getString("email"));
            }

            cached.close();

        } catch (SQLException e) {
            System.out.println("Erro ao abrir a conexao");
            e.printStackTrace();
        }
    }
}
