/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.poo4_1p_g5_mendozaespinozabolanios;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Date;
import java.util.Scanner;
import java.text.SimpleDateFormat;
import java.util.HashSet;
import java.util.Set;

/**
 * Clase principal en la que se ejecuta el programa
 *
 * @author Grupo 5
 */
public class PLATAFORMA {

    static ArrayList<Date> fechas;
    static ArrayList<Multa> multas;
    static ArrayList<Cliente> clientes;
    static ArrayList<Vehiculo> vehiculos;
    static ArrayList<Operador> operadores;
    static ArrayList<Usuario> usuarios;

    /**
     * Main
     *
     * @param args main
     */
    public static void main(String[] args) {
        multas = new ArrayList<>();
        clientes = new ArrayList<>();
        vehiculos = new ArrayList<>();
        operadores = new ArrayList<>();
        usuarios = new ArrayList<>();
        fechas = new ArrayList<>();

        //CARGA DE LA INFORMACION DE LOS ARCHIVOS Y LAS FECHAS
        generarFechas();
        vehiculos = Vehiculo.cargarVehiculo(vehiculos);
        cargarUsuario();
        multas = Multa.cargarMultas(multas);

        //SE EJECUTA EL PROGRAMA
        System.out.println("-----------------------------------------------\n\n            BIENVENIDO AL SISTEMA \n\n-----------------------------------------------");

        Scanner sc = new Scanner(System.in);

        System.out.print("USUARIO : ");
        String usua1 = sc.nextLine();
        System.out.print("CONTRASEÑA : ");
        String contra1 = sc.nextLine();
        Cliente cli = new Cliente(0, null, null, 0, null, usua1, contra1, null, null, 0, null);//Se crea variable cliente para almacenar al objeto cliente en caso de que lo sea.
        Operador op = new Operador(0, null, null, 0, null, usua1, contra1, null, 0);//Se crea variable operador para almacenar al objeto operador en caso de que lo sea.

        System.out.println("---------------------------------------------------");
        Usuario u = validarUsuario(usua1, contra1); //El metodo retorna un usuario
        TipoUsuario val = null;
        //VALIDACION SI "u" ES OPERADOR O CLIENTE
        if (u instanceof Cliente) {
            cli = (Cliente) u;
            val = cli.getPerfil();
        } else if (u instanceof Operador) {
            op = (Operador) u;
            val = op.getPerfil();
        }
        //VALIDACION DE QUE EL USUARIO Y LA CONTRASENIA SEAN CORRECTOS
        if (val != null) {
            System.out.println("             Ingreso de sesión exitoso\n---------------------------------------------------\n");

            //Empieza el switch, menu
            switch (val) {
                case O: //Caso operador
                    int eleccionO = 0;
                    while (eleccionO != 4) {
                        System.out.println("---------------- Menu de Operador ---------------- \n");

                        System.out.println("1.   Registrar pagos: ");
                        System.out.println("2.   Consultar multas clientes: ");
                        System.out.println("3.   Consultar usuarios: ");
                        System.out.println("4.   Salir");
                        System.out.println(" Ingrese el numero de la opcion que desee: ");
                        eleccionO = sc.nextInt();
                        switch (eleccionO) {
                            case 1:
                                op.registrarPagos();
                                break;
                            case 2:
                                op.consultarmultas();
                                break;
                            case 3:
                                op.consultarUsuarios(usuarios);
                                break;
                        }
                    }
                    break; //Culmina caso operador

                default://Caso cliente estandar o estrella
                    System.out.println("---------------- Menu de Cliente  ----------------\n ");
                    int eleccionCS = 0;
                    while (eleccionCS != 3) {
                        System.out.println("1.   Consultar multas: ");
                        System.out.println("2.   Agendar revision: ");
                        System.out.println("3.   Salir ");
                        System.out.println(" Ingrese el numero de la opcion que desee: ");
                        eleccionCS = sc.nextInt();
                        switch (eleccionCS) {
                            case 1:
                                cli.consultarmultas();

                                break;
                            case 2:
                                cli.agendarRevision();
                                break;
                        }

                    }
                    break;//Culmina caso de clientes
            }
        } else {
            System.out.println("El usuario o contraseña ingresados son incorrectos.");
        }

    }//Culmina la muestra del menu

    /**
     * Se carga la informacion de los archivos usuarios, clientes, y operadores
     */
    public static void cargarUsuario() {
        ArrayList<String> datos = ManejoArchivos.LeeFichero("usuarios.txt");
        ArrayList<String> datos2 = ManejoArchivos.LeeFichero("clientes.txt");
        ArrayList<String> datos3 = ManejoArchivos.LeeFichero("operadores.txt");

        for (String line : datos) {//Lee archivo de usuarios
            Cliente c = null;
            Operador p = null;
            String[] elem = line.trim().split(",");
            String[] nombres = elem[1].split(" ");

            String nombre = nombres[0];
            String apellido = nombres[1];

            String tipoUsuario = elem[6].trim();
            int cedula = Integer.parseInt(elem[0]);

            if (TipoUsuario.O.equals(TipoUsuario.valueOf(elem[6]))) {//Valida si el usuario es un operador 
                for (String line3 : datos3) {//Lee archivo de operadores
                    String[] elem3 = line3.trim().split(",");
                    if (Integer.parseInt(elem3[0]) == cedula) {//Compara el operador con el usuario en el archivo de usuarios
                        p = new Operador(cedula, nombre, apellido, Integer.parseInt(elem[2]),
                                elem[3], elem[4], elem[5], TipoUsuario.valueOf(elem[6]), Integer.parseInt(elem3[1])); //Crea Operador
                        operadores.add(p); //Añade el operador al ArrayList de operadores
                        usuarios.add(p);//Añade al operador al ArrayList de usuarios, se hace upcasting automaticamente.
                    }
                }

            } else {
                //Si el usuario es un cliente entonces lee el archivo de clientes
                Vehiculo v = null;
                for (String line2 : datos2) {
                    String[] elem2 = line2.trim().split(",");
                    for (int i = 0; i < vehiculos.size(); i++) {//Busca el vehiculo del cliente.
                        if (vehiculos.get(i).getDueño() == cedula) {
                            v = vehiculos.get(i);
                        }
                    }

                    c = new Cliente(cedula, nombre, apellido, Integer.parseInt(elem[2]),
                            elem[3], elem[4], elem[5], TipoUsuario.valueOf(elem[6]), elem2[1], Integer.parseInt(elem2[2]), v);
                    //Crea el objeto cliente 
                }
                clientes.add(c);//Añade el cliente al ArrayList de clientes
                usuarios.add(c);//Añade al cliente al ArrayList de usuarios, se hace upcasting automaticamente.

            }

        }

    }

    /**
     *
     * @param usuario ingresado por la persona que usa el sistema
     * @param contrasena ingresada por la persona que usa el sistema
     * @return Usuario o null
     */
    public static Usuario validarUsuario(String usuario, String contrasena) {
        for (Usuario u : usuarios) {
            if (u.getContrasenia().equals(contrasena) && u.getUsuario().equals(usuario)) {
                return u;
            }
        }

        return null;
    }

    /**
     * Genera fechas para las revisiones
     */
    public static void generarFechas() {
        for (int i = 0; i <= 240; i += 30) {
            Date f = new Date(123, 5, 7, 9, i, 0);
            fechas.add(f);
        }
    }
}
