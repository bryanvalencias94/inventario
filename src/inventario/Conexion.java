/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package inventario;
import java.sql.*;
/**
 *
 * @author BRYAN VALENCIA
 */
public class Conexion {
    //Creacion de variables requeridas para la conexion
    private Connection conectar=null;
    private String login="";
    private String pass="";
    private String ruta="";
    
    /* Con este contructor podemos modificas
     * la ruta de la base de datos
     * el usuario y
     * la contrasena de la base de datos de MYSQL
     */
    public Conexion(String ru, String lo, String pa){
        this.login=lo;
        this.pass=pa;
        this.ruta+=ru;
    }
    public Conexion(){
        this.login="Bryan";
        this.pass="ucp";
        this.ruta="jdbc:ucanaccess://c://Users//humav//OneDrive//Inventario.accdb"; 
        
    }
    public Connection cone(){
        try{
            conectar=DriverManager.getConnection(ruta,login,pass);
//        }catch(ClassNotFoundException e){
//            e.printStackTrace();
//            System.out.println("No se encuentra la clase para conectar con Access "+e);
        }catch(SQLException e){
            e.printStackTrace();
            System.out.println("Error al querer conectar con Access "+e);
        }
        finally{
            return conectar;
        }
    }
    public void cerrarConexionBaseDatos(){
        try{
            //cierra la conexion a la Base de datos
            conectar.close();
        }catch(SQLException e){
            e.printStackTrace();
            System.out.println("Error al cerrar la Conexion a Access");
        }
    }
    
}
