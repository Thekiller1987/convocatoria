/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author UNANCH4
 */
public class Database {
    String URL="jdbc:sqlserver://localhost:56000;databaseName=Publicaciones;"+
    "integratedSecurity=true;"+"encrypt=true;trustServerCertificate=true;";

private Connection conexion;

    public Database() {
       try {   
            conexion =DriverManager.getConnection(URL);
            System.out.println("Conexion Establecida");
            
            
    }catch (SQLException e){   
            System.out.println("Error de conexion");
            e.printStackTrace();
            }

    }

public int Actualizar(String consulta) {
    
     try{
        Statement st=conexion.createStatement();   
        return st.executeUpdate(consulta);
    } catch (SQLException e){
         e.printStackTrace();
    }    
     return 0;      
    }


private List OrganizarDatos (ResultSet rs){
    List filas =new ArrayList();
    try {
    
    
    
    int numColumnas=rs.getMetaData().getColumnCount();
    while (rs.next()){
        Map<String, Object> renglon=new HashMap();
        for (int  i=1 ; i<=numColumnas; i++){
            
            String  nombreCampo=rs.getMetaData().getColumnName (i);
            Object  valor=rs.getObject(nombreCampo);
            renglon.put (nombreCampo,valor);
        }
        filas.add(renglon);
    }

    }catch (SQLException e) {
        e.printStackTrace();
    }
    return filas;

}
public List Listar(String consulta){
    ResultSet rs=null; 
    List resultado =new ArrayList();
    try{
        Statement st=conexion.createStatement();
        rs=st.executeQuery(consulta);
        resultado=OrganizarDatos(rs);
        
    }catch (SQLException e){
        System.err.println("No se realizo la consulta");
    e.printStackTrace();
    }
    return resultado;
}
{
    }

public void  cerrarConexion(){
    try{
        conexion.close();
    }catch (SQLException e){
        e.printStackTrace();
    }
}






}


    

