import com.sun.rowset.JdbcRowSetImpl;

import javax.sql.rowset.JdbcRowSet;

public class Exer14 {
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

            while(rs.next())
            {
                System.out.println(rs.getInt("id") + " - " + rs.getString("nome")
                        + " - " + rs.getString("email"));
            }


            rs.moveToInsertRow();
            rs.updateNull("id");
            rs.updateString("nome","Jader sem sql");
            rs.updateString("email","jader@semsql.com");
            rs.insertRow();

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
