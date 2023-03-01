package Datos.DAO;
import java.sql.*;
/**
 *
 * @author sortizu
 */
public class Conexion {
    //Atributos para generar conexión con la BD
    Connection con;
    String url = "jdbc:mysql://localhost/spvl?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDateTimeCode=false&serverTimezone=UTC&useSSL=false";
    String user = "root";
    String pass = "1234";
    
    //Método para realizar la conexión con la BD
    public Connection Conectar(){
        try{
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection(url, user, pass);
        } catch(Exception e){
            System.out.println("Excepcion: "+e.getMessage());
        }
        return con;
    }

}
