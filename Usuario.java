/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.poo4_1p_g5_mendozaespinozabolanios;
import java.util.ArrayList;
import java.util.List;
/**
 *Clase abstracta Usuario sera heredada por operador y cliente
 * @author Guillermo Mendoza
 */
public abstract class Usuario {
    private int cedula;
    private String nombre;
    private String apellidos;
    private int edad;
    private String correo;
    private String usuario;
    private String contrasenia;
    private TipoUsuario perfil;
    
    /**
     * 
     * @param cedula del usuario
     * @param nombre del usuario
     * @param apellidos del usuario
     * @param edad del usuario
     * @param correo del usuario
     * @param usuario para iniciar sesion
     * @param contrasenia iniciar sesion
     * @param perfil tipo de usuario
     */
    public Usuario(int cedula,String nombre,String apellidos,int edad,String correo,
            String usuario,String contrasenia,TipoUsuario perfil){
        this.cedula=cedula;
        this.nombre=nombre;
        this.apellidos=apellidos;
        this.edad=edad;
        this.correo=correo;
        this.usuario=usuario;
        this.contrasenia=contrasenia;
        this.perfil=perfil;
    }
    
    /**
     * 
     * @return cedula del usuario
     */
    public int getCedula() {
        return cedula;
    }
    /**
     * Cambia la cedula del usuario
     * @param cedula nueva cedula
     */
    public void setCedula(int cedula) {
        this.cedula = cedula;
    }
    /**
     * 
     * @return nombre del usuario
     */
     public String getNombre() {
        return nombre;
    }
    /**
     * Cambia el nombre del usuario
     * @param nombre nuevo nombre
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    /**
     * 
     * @return el apellido del usuario
     */
     public String getApellido() {
        return apellidos;
    }
    /**
     * Cambia el apellido del usuario
     * @param apellido nuevo apellido
     */
    public void setCedula(String apellido) {
        this.apellidos = apellido;
    }
    /**
     * 
     * @return la edad del usuario
     */
     public int getEdad() {
        return edad;
    }
    /**
     * Cambia la edad del usuario
     * @param edad nueva edad
     */
    public void setEdad(int edad) {
        this.edad = edad;
    }
    /**
     * 
     * @return el correo del usuario
     */
     public String getCorreo() {
        return correo;
    }
    /**
     * Cambia el correro del usuario
     * @param correo nuevo correo
     */
    public void setCorreo(String correo) {
        this.correo = correo;
    }
    /**
     * 
     * @return el usuario
     */
     public String getUsuario() {
        return usuario;
    }
    /**
     * Cambia el usuario
     * @param usuario nuevo usuario
     */
    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }
    /**
     * 
     * @return la contrasenia del usuario
     */
     public String getContrasenia() {
        return contrasenia;
    }
    /**
     * Cambia la contrasenia del usuario
     * @param contrasenia nueva contrasenia
     */
    public void setContrasenia(String contrasenia) {
        this.contrasenia = contrasenia;
    }
    /**
     * 
     * @return el perfil del usuario E,S,O
     */
     public TipoUsuario getPerfil() {
        return perfil;
    }
    /**
     * Cambia el perfil del usuario
     * @param perfil nuevo perfil
     */
    public void setPerfil(TipoUsuario perfil) {
        this.perfil = perfil;
    }
    
    

    /**
     * Metodo abstracto consultarmultas, debe ser sobreescrito en las clases hijas
     */
    public abstract void consultarmultas(); 
}