/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.poo4_1p_g5_mendozaespinozabolanios;

import static com.mycompany.poo4_1p_g5_mendozaespinozabolanios.PLATAFORMA.clientes;
import static com.mycompany.poo4_1p_g5_mendozaespinozabolanios.PLATAFORMA.vehiculos;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
/**
 * Esta clase crea las multas de los clientes
 * @author Tomas Bolaños
 */

public class Multa {
    private Cliente cliente;
    private Vehiculo vehiculo;
    private String infraccion;
    private double valorMulta;
    private String fechaInfraccion;
    private String fechaNotificacion;
    private int puntos;
    static ArrayList<Multa> multas;

    /**
     * 
     * @param cliente usuario de tipo cliente estrella o estandar
     * @param vehiculo perteneciente al cliente
     * @param infraccion descripcion de la multa
     * @param valorMulta valor a pegar
     * @param fechaInfraccion fecha de cuando se cometio la infracción
     * @param fechaNotificacion fecha en la que se notificó al cliente sobre la infracción
     * @param puntos puntos que se restan a la licencia
     */
    public  Multa(Cliente cliente, Vehiculo vehiculo, String infraccion,double valorMulta,
    String fechaInfraccion, String fechaNotificacion,int puntos){
        this.cliente = cliente;
        this.vehiculo = vehiculo;
        this.infraccion = infraccion;
        this.valorMulta = valorMulta;
        this.fechaInfraccion = fechaInfraccion;
        this.fechaNotificacion = fechaNotificacion;
        this.puntos = puntos;
    }
    //GETTERS Y SETTERS
    /**
     * 
     * @return el cliente al que le pertenece la multa 
     */
    public Cliente getCliente() {
        return cliente;
    }
    /**
     * Cambia el cliente de la multa
     * @param cliente nuevo cliente
     */
    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }
    /**
     * 
     * @return el vehiculo que se registra en la infraccion
     */
    public Vehiculo getVehiculo() {
        return vehiculo;
    }
    /**
     * Cambia el vehiculo de la infraccion
     * @param vehiculo nuevo vehiculo
     */
    public void setVehiculo(Vehiculo vehiculo) {
        this.vehiculo = vehiculo;
    }
    /**
     * 
     * @return la razon de la multa
     */
    public String getInfraccion() {
        return infraccion;
    }
    /**
     * Cambia la razon de la infraccion de la multa
     * @param infraccion nueva infraccion
     */
    public void setInfraccion(String infraccion) {
        this.infraccion = infraccion;
    }
    /**
     * 
     * @return el valor a cancelar de la multa
     */
    public double getValorMulta() {
        return valorMulta;
    }
    /**
     * Cambiar valor de la multa
     * @param valorMulta nuevo valor a cancelar
     */
    public void setValorMulta(double valorMulta) {
        this.valorMulta = valorMulta;
    }
    /**
     * 
     * @return la fecha en la que se cometio la infraccion
     */
    public String getFechaInfraccion() {
        return fechaInfraccion;
    }
    /**
     * Cambia la fecha de la infraccion
     * @param fechaInfraccion nueva fecha de infraccion
     */
    public void setFechaInfraccion(String fechaInfraccion) {
        this.fechaInfraccion = fechaInfraccion;
    }
    /**
     * 
     * @return la fecha en la que se le notifico al cliente la multa
     */
    public String getFechaNotificacion() {
        return fechaNotificacion;
    }
    /**
     * Cambia la fecha en la que se notifico la multa
     * @param fechaNotificacion nueva fecha de notificacion
     */
    public void setFechaNotificacion(String fechaNotificacion) {
        this.fechaNotificacion = fechaNotificacion;
    }
    /**
     * 
     * @return puntos de la licencia que se quitan al cliente
     */
    public int getPuntos() {
        return puntos;
    }
    /**
     * Cambia los puntos que se quitan en la licencia
     * @param puntos  nuevos puntos a quitar
     */
    public void setPuntos(int puntos) {
        this.puntos = puntos;
    }
    
    @Override
    public boolean equals(Object obj){
        if(this==obj){
            return true;
        }if(obj==null){
            return false;
        }if(getClass()!=obj.getClass()){
            return false;
        }
        Multa otro=(Multa)obj;
        if(this.cliente.getCedula()==otro.getCliente().getCedula() || 
                (this.vehiculo.getPlaca().equals(otro.getVehiculo().getPlaca()))){
            return true;
        }
        
        return false;
        }
    /**
     * 
     * @param multas ArrayList de multas
     * Carga la informacion del archivo de multas
     * @return lista de multas 
     */
    static ArrayList<Multa> cargarMultas(ArrayList<Multa> multas){
        ArrayList<String> datos=ManejoArchivos.LeeFichero("multas.txt");
        for(String line:datos){
            String[] elem=line.trim().split(",");
            Vehiculo v=null;
            Cliente c = null;
            for(Vehiculo a:vehiculos){
                if (a.getDueño()==Integer.parseInt(elem[0])){
                    v=a; //Extrae el vehiculo con la cedula encontrada en el archivo de multas
                }
            }
            for(Cliente p:clientes){
                if (p.getCedula()==Integer.parseInt(elem[0])){
                    c=p; //Extrae el cliente con la cedula encontrada en el archivo de multas
                }
            }
            Multa m = new Multa(c,v,elem[2],Double.parseDouble(elem[3]),elem[4],elem[5],Integer.parseInt(elem[6]));
            
            multas.add(m); //Añade la multa a las listas de multas
        }
        return multas;
    }
        @Override
        public String toString(){
            return this.getCliente().getCedula()+" | "+this.getInfraccion()+" | "+this.getValorMulta()+" | "+this.getPuntos();
        }
        
            
            
    }


