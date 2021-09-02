
package persistencia;
//import java.awt.HeadlessException;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

//import javax.swing.JOptionPane;
import logic.Puesto;




public class ConectorDB {
    
    public Connection ConexionBD;
    public static Statement Sentencia;
    public static ResultSet Resultado;
    public final String bd;
    //public final String urlConexion = String.format("jdbc:mysql://localhost:3306/%s",bd);
    public final String usuario;
    public final String contra;
   // public final String jdbc = "com.mysql.cj.jdbc.Driver";
    
    
    
    public ConectorDB(String bd, String usuario, String contra) {
        ConexionBD = null;
        Sentencia = null;
        Resultado = null;
        this.bd = "db_empresa";
        this.usuario = "usr_empresa";
        this.contra = "Empresa@123";
    }
    
    public boolean abrir_conexion (){
    
    try {
        Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
        ConexionBD = DriverManager.getConnection("jdbc:mysql://localhost/"+bd,usuario,contra);        
        //JOptionPane.showMessageDialog(null,"Conexion Exitosa...","Exito",JOptionPane.INFORMATION_MESSAGE);
        return ConexionBD != null;
    }catch(Exception e){
        System.out.println("Error..." + e.getMessage());
        return false;
    }
    }
    
    public void cerrar_conexion (){
    try{
    this.ConexionBD.close();
    }catch(Exception e ){
        System.out.println("Error..." + e.getMessage());
    }
    }
    
    public ArrayList getListaPuestos(){
    ArrayList mListaPuestos = new ArrayList();
    Puesto mPuesto = null;
    Statement consulta;
    ResultSet resultado;
     try {
            consulta = ConexionBD.createStatement();
            resultado = consulta.executeQuery("select * from puestos;");
            
            while (resultado.next()) {
                mPuesto = new Puesto();
                mPuesto.setId_puesto(resultado.getInt("id_puesto"));
                mPuesto.setPuesto(resultado.getString("puesto"));
                mListaPuestos.add(mPuesto);
            }

    }catch(SQLException e){
    }
    return mListaPuestos;
    }
}
