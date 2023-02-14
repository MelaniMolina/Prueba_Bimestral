import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
public class conexion {
    public conexion() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conexion = DriverManager.getConnection (
                    "jdbc:mysql://localhost/DATOS_INF","root", "12345");
            Statement s = conexion.createStatement();
            ResultSet rs = s.executeQuery (" SELECT * FROM CLIENTE");

            while (rs.next())
            {
                System.out.println (rs.getString ("ID_Cli") + " " + rs.getString (3));
            }


            conexion.close();

        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
}
