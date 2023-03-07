package Datos.DAO;
import java.sql.*;
import oracle.jdbc.OracleDriver;
/**
 *
 * @author sortizu
 */
public class Conexion {
    //Atributos para generar conexión con la BD
    Connection con;
    String url = "jdbc:oracle:thin:@//localhost:1521/XE";
    private static String user = "";
    private static String pass = "";
    
    //Método para realizar la conexión con la BD
    public Connection Conectar() throws SQLException{
        try{
            DriverManager.registerDriver(new OracleDriver());
            con = DriverManager.getConnection(url, user, pass);
        } catch(SQLException e){
            throw e;
        }
        return con;
    }
    
    public void setStaticRootConfiguration(){
        user="ROOT";
        pass="ROOT@1234";
    }
    public void setStaticUserConfiguration(String newUser, String newPass){
        user=newUser;
        pass=newPass;
    }

    public static String getUser() {
        return user;
    }
    
}
