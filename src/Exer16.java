import com.sun.rowset.JdbcRowSetImpl;

import javax.sql.rowset.JdbcRowSet;

public class Exer16 {
    public static void main(String[] args) {
        try{
            Class.forName("org.hsqldb.jdbcDriver");
            System.out.println("Driver carregado com sucesso.");
        } catch (ClassNotFoundException e) {
            System.out.println("Erro ao carregar driver.");
            e.printStackTrace();
        }

        try {
            String url = "jdbc:hsqldb:file:D:/UniprimeWorkspaceIntelliJ/JavaSE-JDBC/hypersql/bases/aula;shutdown=true";
            String usuario ="SA";
            String senha = "1234";

            JdbcRowSet rs = new JdbcRowSetImpl();
            rs.setUrl(url);
            rs.setUsername(usuario);
            rs.setPassword(senha);

            rs.setCommand("select * from cliente");
            rs.execute();
            rs.first();
            System.out.println(rs.getInt("id") + " - " + rs.getString("nome")
                    + " - " + rs.getString("email"));

            rs.next();
            rs.updateString("nome", "Evelyn Franco");
            rs.updateRow();
            System.out.println(rs.getInt("id") + " - " + rs.getString("nome")
                    + " - " + rs.getString("email"));

            rs.last();
            System.out.println(rs.getInt("id") + " - " + rs.getString("nome")
                    + " - " + rs.getString("email"));

            rs.deleteRow();

            System.out.println("--------");

            rs.setCommand("select * from cliente");
            rs.execute();

            while(rs.next())
            {
                System.out.println(rs.getInt("id") + " - " + rs.getString("nome")
                        + " - " + rs.getString("email"));
            }

            rs.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
