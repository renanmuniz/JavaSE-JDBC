import java.io.FileInputStream;
import java.sql.*;
import java.util.Properties;

public class Exer17 {

    public static Properties get(String arquivo) {
        try {
            Properties props = new Properties();
            FileInputStream file = new FileInputStream(arquivo);
            props.load(file);
            return props;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static void jdbcPortavel(Properties ppt) {
        try {
            Class.forName(ppt.getProperty("config.driver"));
            System.out.println("Driver carregado com sucesso.");
        } catch (ClassNotFoundException e) {
            System.out.println("Erro ao carregar driver.");
            e.printStackTrace();
        }
        Connection conexao = null;
        try {

            conexao = DriverManager.getConnection(
                    ppt.getProperty("config.url"),
                    ppt.getProperty("config.usuario"),
                    ppt.getProperty("config.senha"));

            PreparedStatement inserir = conexao.prepareStatement(
                    ppt.getProperty("cliente.inserir"));
            inserir.setObject(1,"jose da silva");
            inserir.setObject(2,"jose@outlook.com");
            inserir.execute();

            ResultSet rs = conexao.createStatement().executeQuery(ppt.getProperty("cliente.todos"));
            while(rs.next()) {
                System.out.println(rs.getInt(ppt.getProperty("cliente.campo.id"))
                        + " - " + rs.getString(ppt.getProperty("cliente.campo.nome"))
                        + " - " + rs.getString(ppt.getProperty("cliente.campo.email")));
            }

            inserir.close();
            rs.close();
            conexao.close();

        } catch (SQLException e) {
            System.out.println("Erro ao abrir a conexao");
            e.printStackTrace();
        }
    }





    public static void main(String[] args) {
        Properties hsqldb = get("./properties/hsqldb.properties");
        if(hsqldb==null) {
            System.out.println("erro na carga do properties");
            System.exit(0);
        }
        System.out.println("No HSQLDB");
        jdbcPortavel(hsqldb);





    }
}
