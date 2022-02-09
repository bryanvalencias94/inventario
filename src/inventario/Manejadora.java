/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package inventario;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;

/**
 *
 * @author BRYAN VALENCIA
 */
public class Manejadora {
    Conexion objConexion;
    ResultSet rs;
    Statement st;
    /**
     * @param args the command line arguments
     */
    public Manejadora(){
        objConexion=new Conexion();
        rs=null;
        st=null;
    }
    public boolean ingresar(String ref, String des, int can, int precioC, int precioV, String proveedor){
        try{
            st=objConexion.cone().createStatement();
            st.execute("Insert into inventario Values ('"+ref+"','"+des+"','"+can+"','"+precioC+"','"+precioV+"','"+proveedor+"')");
            JOptionPane.showMessageDialog(null, "Se grabaron los datos");
            return true;
        }catch(SQLException e){
            if(e.getMessage().equals("General error")){
                JOptionPane.showMessageDialog(null,"Esta referencia ya fue utilizada");
            }else{
            JOptionPane.showMessageDialog(null,"No se pudo grabar revise los datos "+e);
            }
            return false;
        }
    }
    public boolean venta(String ref, int can){
        try{
            st=objConexion.cone().createStatement();
            st.execute("UPDATE inventario SET Cantidad = '"+can+"' where Referencia = '"+ref+"'"); 
            JOptionPane.showMessageDialog(null, "Se actualizaron los datos");
            return true;
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null,"No se pudo grabar revise los datos "+e);
            return false;
        }
    }
    public ResultSet consultaRef(String ref){
        try{
            st=objConexion.cone().createStatement();
            rs=st.executeQuery("select * from inventario where Referencia = '"+ref+"'");
            rs.next();
            return rs;
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null,"No se pudo encontrar nada, revise los datos "+e);
            return rs;
        }
    }
    public boolean actualizar(String ref, String ref2, String des, int can, int precioC, int precioV, String proveedor){
        try{
            st=objConexion.cone().createStatement();
            st.execute("UPDATE inventario SET Referencia = '"+ref2+"', Descripcion = '"+des+"', Cantidad = '"+can+"', PrecioCompra = '"+precioC+"', PrecioVenta = '"+precioV+"', Proveedor = '"+proveedor+"' where Referencia = '"+ref+"'"); 
            JOptionPane.showMessageDialog(null, "Se actualizaron los datos");
            return true;
        }catch(SQLException e){
            if(e.getMessage().equals("General error")){
                JOptionPane.showMessageDialog(null, "La referencia ya fue utilizada");
            }else{
            JOptionPane.showMessageDialog(null,"No se pudo grabar revise los datos "+e);
            }
            return false;
        }
    }
    public int tamanoResultSet(){ //permite contar la cantidad de registros en la base de datos
        int rowcount=0;
        try{
            st=objConexion.cone().createStatement();
            rs=st.executeQuery("select * from inventario"); 
            while (rs.next()) {
                 rowcount++;
            }
            }catch(SQLException e){
            JOptionPane.showMessageDialog(null,"No se pudo grabar revise los datos "+e);
        }
        return rowcount;
    }
    public boolean eliminar(String ref){
        try{
            st=objConexion.cone().createStatement();
            st.execute("delete from inventario where Referencia = '"+ref+"'");
            JOptionPane.showMessageDialog(null,"Se ha borrado el producto");
            return true;
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null,"No se pudo elimar, revise los datos "+e);
            return false;
        }
    }
    public ResultSet consultaDes(String des){
        try{
            st=objConexion.cone().createStatement();
            rs=st.executeQuery("select * from inventario where Descripcion like '%"+des+"%'");
            return rs;
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null,"No se pudo grabar revise los datos "+e);
            return rs;
        }
    }
    public int tamanoResultSetDescripcion(String des){ //permite contar la cantidad de registros en la base de datos
        int rowcount=0;
        try{
            st=objConexion.cone().createStatement();
            rs=st.executeQuery("select * from inventario where Descripcion like '%"+des+"%'"); // para filtrar select placa, modelo
            while (rs.next()) {
                 rowcount++;
            }
            }catch(SQLException e){
            JOptionPane.showMessageDialog(null,"No se pudo grabar revise los datos "+e);
        }
        return rowcount;
    }
    public ResultSet consultaPro(String pro){
        try{
            st=objConexion.cone().createStatement();
            rs=st.executeQuery("select * from inventario where Proveedor like '%"+pro+"%'");
            return rs;
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null,"No se pudo buscar, revise los datos "+e);
            return rs;
        }
    }
    public int tamanoResultSetPro(String pro){ //permite contar la cantidad de registros en la base de datos
        int rowcount=0;
        try{
            st=objConexion.cone().createStatement();
            rs=st.executeQuery("select * from inventario where Proveedor like '%"+pro+"%'"); // para filtrar select placa, modelo
            while (rs.next()) {
                 rowcount++;
            }
            }catch(SQLException e){
            JOptionPane.showMessageDialog(null,"No se pudo buscar, revise los datos "+e);
        }
        return rowcount;
    }
    
}
