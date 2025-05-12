/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.poo4_1p_g5_mendozaespinozabolanios;

import static com.mycompany.poo4_1p_g5_mendozaespinozabolanios.PLATAFORMA.vehiculos;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

/**
 *Esta clase representara un vehiculo
 * @author Nahin Espinoza
 */
public class Vehiculo {

    private int duenio;
    private String placa;
    private String marca;
    private String modelo;
    private int año;
    private String chasis;
    private String color;

    /**
     * Constructor del obejto Vehiculo 
     * @param dueño Cedula del dueño del vehiculo
     * @param placa Placa del vehiculo
     * @param marca Marca del vehiculo
     * @param modelo Modelo del vehiculo
     * @param año Año de fabricacion del vahiculo
     * @param chasis Numero de chasis del vehiculo
     * @param color  Color del vehiculo
     */ 
    
    public Vehiculo(int dueño, String placa, String marca, String modelo, int año, String chasis, String color) {
        this.duenio = dueño;
        this.placa = placa;
        this.marca = marca;
        this.modelo = modelo;
        this.año = año;
        this.chasis = chasis;
        this.color = color;
    }
//GETTERS Y SETTERS
    /**
     * Obtiene la cedula del Dueño 
     * @return la cedula del Dueño 
     */
    
    public int getDueño() {
        return duenio;
    }
    /**
     * Establece la cedula del Dueño
     * @param dueño cedula nueva del Dueño
     */
    public void setDueño(int dueño) {
        this.duenio = dueño;
    }
    /**
     * Obtiene la placa del vehiculo
     * @return la placa del vehiculo
     */
    public String getPlaca() {
        return placa;
    }
    /**
     * Establece la placa del vehiculo 
     * @param placa Establece la nueva placa del vehiculo 
     */
    public void setPlaca(String placa) {
        this.placa = placa;
    }
    /**
     * Obtiene la marca del vehiculo 
     * @return la marca del vehiculo 
     */
    public String getMarca() {
        return marca;
    }
    /**
     * Establece la marca del Vehiculo
     * @param marca Establece la nueva marca del Vehiculo
     */
    public void setMarca(String marca) {
        this.marca = marca;
    }
    /**
     * Obtiene el modelo del vehiculo 
     * @return el modelo del vehiculo 
     */
    public String getModelo() {
        return modelo;
    }
    /**
     * Establece un modelo del vehiculo
     * @param modelo Establece un nuevo modelo del vehiculo
     */
    public void setModelo(String modelo) {
        this.modelo = modelo;
    }
    /**
     * Obtiene el año de elaboracion del vehiculo
     * @return  el año de elaboracion del vehiculo
     */
    public int getAño() {
        return año;
    }
    /**
     * Establece el año de elboracion del vehiculo
     * @param año Establece el nuevo año de elboracion del vehiculo
     */
    public void setAño(int año) {
        this.año = año;
    }
    /**
     * Obtiene el codigo del chasis del vehiculo
     * @return el codigo del chasis del vehiculo
     */
    public String getChasis() {
        return chasis;
    }
    /**
     * Establece el codigo del chasis del vehiculo
     * @param chasis Establece un nuevo codigo del chasis del vehiculo
     */
    public void setChasis(String chasis) {
        this.chasis = chasis;
    }
    /**
     * Obtiene el color del vehiculo
     * @return el color del vehiculo
     */
    public String getColor() {
        return color;
    }
    /**
     * Establece el color del vehiculo
     * @param color Establece un nuevo color de vehiculo
     */
    public void setColor(String color) {
        this.color = color;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        Vehiculo otro = (Vehiculo) obj;
        if (!this.getPlaca().equals(otro.getPlaca())) {
            return false;
        }

        return true;
    }
/**
 * Se carga la informacion de el archivo "Vehiculo.txt" en el Arraylist vehiculos
 * @param vehiculos Arraylist de Vehiculos 
 * @return Arraylist de Vehiculos
 */
    static ArrayList<Vehiculo> cargarVehiculo(ArrayList<Vehiculo> vehiculos) {
        ArrayList<String> datos = ManejoArchivos.LeeFichero("vehiculos.txt");
        //Se recorre cada linea del archivo y se va agregando cada objeto en el Arraylist
        for (String line : datos) {
            String[] elementos = line.trim().split(",");
            Vehiculo v = new Vehiculo(Integer.parseInt(elementos[0]), elementos[1], elementos[2],
                    elementos[3], Integer.parseInt(elementos[4]), elementos[5], elementos[6]);
            vehiculos.add(v);
        }
        return vehiculos;
    }
    
    
    public String toString() {
        return "Vehiculo"+this.duenio+this.placa+this.marca+this.modelo+this.año+this.chasis+this.color;
    }
}
