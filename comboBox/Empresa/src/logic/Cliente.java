/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logic;
//import persistencia.ConectorDB;

import persistencia.ConectorDB;
import igu.frmCliente;

import java.awt.HeadlessException;
import javax.swing.JOptionPane;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import javax.swing.table.DefaultTableModel;
/**
 *
 * @author mfer_
 */
public class Cliente extends Persona{
    private int id;
    private String nit;
    
    
    ConectorDB cn;
    public Cliente (){}
    public Cliente(int id,String nit, String nombres, String apellidos, String direccion, String telefono, String fecha_nacimiento) {
        super(nombres, apellidos, direccion, telefono, fecha_nacimiento);
        this.nit = nit;
        this.id = id;
    }
    

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    public String getNit() {
        return nit;
    }

    public void setNit(String nit) {
        this.nit = nit;
    }
   
    public DefaultTableModel leer(){
    DefaultTableModel tabla = new DefaultTableModel();
    
    try{
    
        cn = new ConectorDB("db_empresa","usr_empresa","Empresa@123");
        cn.abrir_conexion();
        String query;
        query = "Select c.id_clientes as id ,c.nit,c.nombres,c.apellidos,c.direccion,c.telefono,c.fecha_nacimiento, p.puesto from clientes c\n" +
                "inner join puestos p on p.id_puesto = c.id_puesto;";
        ResultSet consulta = cn.ConexionBD.createStatement().executeQuery(query);
        
        String encabezado [] = {"id","Nit","Nombres","Apellidos","Direccion","Telefono","Nacimiento","puesto"};
        tabla.setColumnIdentifiers(encabezado);
        
        String datos [] = new String [8];
        
        while (consulta.next()){
        datos [0] = consulta.getString("id");
        datos [1] = consulta.getString("nit");
        datos [2] = consulta.getString("nombres");
        datos [3] = consulta.getString("apellidos");
        datos [4] = consulta.getString("direccion");
        datos [5] = consulta.getString("telefono");
        datos [6] = consulta.getString("fecha_nacimiento");
        datos [7] = consulta.getString("puesto");
        tabla.addRow(datos);
        
        }
        cn.cerrar_conexion();
    }catch(SQLException ex){
        cn.cerrar_conexion();
        System.out.println("Error:" + ex.getMessage());
    }
    
    return tabla;
    }
    
    @Override
    public void agregar(){
    
    try{
    
        PreparedStatement parametro;
        String query = "INSERT INTO db_empresa.clientes(nit,nombres,apellidos,direccion,telefono,fecha_nacimiento,id_puesto)VALUES(?,?,?,?,?,?,?);";
    cn = new ConectorDB("db_empresa","usr_empresa","Empresa@123");
    cn.abrir_conexion();
    parametro = (PreparedStatement) cn.ConexionBD.prepareStatement(query);
    parametro.setString(1,this.getNit());
    parametro.setString(2,this.getNombres());
    parametro.setString(3,this.getApellidos());
    parametro.setString(4,this.getDireccion());
    parametro.setString(5,this.getTelefono());
    parametro.setString(6,this.getFecha_nacimiento());
    //parametro.setString(7, this.ge);
   // Puesto mpuesto = (Puesto) this.jcb_puestos.getSelectedItem();
    
    
    int executar = parametro.executeUpdate();
   
    cn.cerrar_conexion();
    JOptionPane.showMessageDialog(null, Integer.toString(executar) + "Registro Ingresado","Agregar",JOptionPane.INFORMATION_MESSAGE);
         
    }catch(HeadlessException | SQLException ex){
        
        System.out.println("Error ..." + ex.getMessage());
    }
    }
    
    @Override    
    public void actualizar(){
     try{
    
        PreparedStatement parametro;
        cn = new ConectorDB("db_empresa","usr_empresa","Empresa@123");
        cn.abrir_conexion();
        String query = "update db_empresa.clientes set nit = ?,nombres = ?,apellidos = ?,direccion =?,telefono = ?,fecha_nacimiento = ? where id_clientes = ?;";
  
    parametro = (PreparedStatement) cn.ConexionBD.prepareStatement(query);
    parametro.setString(1,this.getNit());
    parametro.setString(2,this.getNombres());
    parametro.setString(3,this.getApellidos());
    parametro.setString(4,this.getDireccion());
    parametro.setString(5,this.getTelefono());
    parametro.setString(6,this.getFecha_nacimiento());
    parametro.setInt(7,this.getId());
    
    
    int executar = parametro.executeUpdate();
   
    cn.cerrar_conexion();
    JOptionPane.showMessageDialog(null, Integer.toString(executar) + " Registro Actualizado","Agregar",JOptionPane.INFORMATION_MESSAGE);
         
    }catch(HeadlessException | SQLException ex){
        
        System.out.println("Error ..." + ex.getMessage());
    }
    }
    @Override
    public void eliminar (){
    try{
    
        PreparedStatement parametro;
        cn = new ConectorDB("db_empresa","usr_empresa","Empresa@123");
        cn.abrir_conexion();
        String query = "delete from  db_empresa.clientes where id_clientes = ?;";
    
    parametro = (PreparedStatement) cn.ConexionBD.prepareStatement(query);
    parametro.setInt(1, this.getId());
    
    
    int executar = parametro.executeUpdate();
   
    cn.cerrar_conexion();
    JOptionPane.showMessageDialog(null, Integer.toString(executar) + " Registro Eliminado","Agregar",JOptionPane.INFORMATION_MESSAGE);
         
    }catch(HeadlessException | SQLException ex){
        
        System.out.println("Error ..." + ex.getMessage());
    }
    }

    
}
 
    

