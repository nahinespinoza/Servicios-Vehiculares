/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.poo4_1p_g5_mendozaespinozabolanios;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

/**
 * Esta clase representara un Operador
 *
 * @author Nahin Espinoza
 */
public class Operador extends Usuario {

    private int sueldo;

    /**
     *
     * @param cedula Cedula del Operador
     * @param nombre Nombre del Operador
     * @param apellidos Apeliido del Operador
     * @param edad Edad del Operador
     * @param correo Correro del Operador
     * @param usuario Usuario del Operador
     * @param contrasenia Contraseña del Operador
     * @param perfil Tipo de Usuario
     * @param sueldo Sueldo del Operador
     */
    public Operador(int cedula, String nombre, String apellidos, int edad, String correo, String usuario, String contrasenia, TipoUsuario perfil, int sueldo) {
        super(cedula, nombre, apellidos, edad, correo, usuario, contrasenia, perfil);
        this.sueldo = sueldo;
    }
//GETTERS Y SETTERS 

    /**
     * Obtiene el sueldo del operador
     *
     * @return el sueldo del operador
     */
    public int getSueldo() {
        return sueldo;
    }

    /**
     * Establece un sueldo para el operador
     *
     * @param sueldo Establece un nuevo sueldo para el operador
     */
    public void setSueldo(int sueldo) {
        this.sueldo = sueldo;
    }

    /**
     * Este metodo permite conocer todas las multas de los clientes en el mes
     * ingresado
     */
    @Override
    public void consultarmultas() {
        ArrayList<Multa> multas = PLATAFORMA.multas;
        ArrayList<Cliente> clientes = PLATAFORMA.clientes;
        String[] meses = {"Enero", "Febrero", "Marzo", "Abril", "Mayo", "Junio", "Julio", "Agosto", "Septiembre", "Octubre", "Noviembre", "Diciembre"};// Creacion de array de meses 

        System.out.println("----------------------------------------------------CONSULTAR MULTAS-------------------------------------------");
        //Se pide al usuario el mes a utilizar
        System.out.print("Ingrese un mes (Primera letra en mayuscula) : ");
        Scanner sc = new Scanner(System.in);
        String mes1 = sc.nextLine();
        boolean val = Arrays.asList(meses).contains(mes1);
        while (val == false) {
            System.out.println("La palabra que ingreso no es un mes, porfavor ingrese denuevo: ");
            String mes2 = sc.nextLine();
            boolean val2 = Arrays.asList(meses).contains(mes2);
            val = val2;

        }
        System.out.println(" ----------------------------------------Conductores multados----------------------------------------");
        System.out.println("CEDULA | MATRICULA  |  INFRACCION  |  VALOR A PAGAR  |  FECHA DE INFRACCION  |  FECHA DE NOTIFICACION  |  PUNTOS ");

        for (int x = 0; x < multas.size(); x++) {
            //Se crea un objeto Multa y cliente para poder obtener sus datos en cada iteracion
            Multa val1 = multas.get(x);
            Cliente val2 = clientes.get(x);
            //Obtenemos la fecha de infraccion del cliente y la convertimos en int
            String elem = val1.getFechaInfraccion();
            String[] lelem = elem.split("-");
            int mes = Integer.parseInt(lelem[1]);
            //Si el mes obtenido es igual al mes que ingreso el usuario, se presenta la informacion
            if (meses[mes - 1].equals(mes1)) {
                System.out.println(val1.getCliente().getApellido() + " " + val1.getCliente().getNombre() + " | " + val1.getVehiculo().getPlaca() + " | " + val1.getInfraccion() + " | " + val1.getValorMulta() + " | " + val1.getFechaInfraccion() + " | " + val1.getFechaNotificacion() + " | " + val1.getPuntos());
            }
        }

    }

    /**
     * Este metodo muestra lo datos de los usuarios, dependiendo si es Operador
     * o Cliente (varian dependiendo de cada uno)
     *
     * @param datos Arraylist de datos
     */
    public void consultarUsuarios(ArrayList<Usuario> datos) {
        // Se obtienen las listas de clientes y operadores de la clase plataforma

        ArrayList<Cliente> clientes = PLATAFORMA.clientes;
        ArrayList<Operador> operadores = PLATAFORMA.operadores;
        System.out.println("----------------------------------------------------CONSULTAR USUARIOS-------------------------------------------\n");
        // Se recorre la lista de usuarios proporcionada como parámetro

        for (Usuario usuario : datos) {
            // Se busca el usuario en la lista de clientes
            for (int y = 0; y < clientes.size(); y++) {
                Cliente val2 = clientes.get(y);
                // Se encuentra el cliente con la misma cédula que el usuario actual
                if (val2.getCedula() == usuario.getCedula()) {
                    String usua = val2.getUsuario();
                    String contra = val2.getContrasenia();
                    Usuario validacion = PLATAFORMA.validarUsuario(usua, contra);
                    // Se imprime la información del cliente según su tipo (estándar o estrella)

                    if (validacion.getPerfil().equals(TipoUsuario.E)) {
                        System.out.println(val2.getApellido() + " " + val2.getNombre() + "  |  " + "CLIENTE ESTRANDAR" + "  |  " + val2.getCedula());
                    } else {
                        System.out.println(val2.getApellido() + " " + val2.getNombre() + "  |  " + "CLIENTE ESTRELLA" + "  |  " + val2.getCedula());
                    }
                }
            }
            // Se busca el usuario en la lista de operadores y si se encuentra el operador con la misma cédula que el usuario actual se presenta su informacion
            for (int y = 0; y < operadores.size(); y++) {
                Operador val2 = operadores.get(y);
                if (val2.getCedula() == usuario.getCedula()) {
                    System.out.println(val2.getApellido() + " " + val2.getNombre() + "  |  " + "OPERADOR" + "  |  " + val2.sueldo);
                }
            }

        }
    }

    /**
     * Este metodo registra un pago segun los datos ingresados en el archivo
     * "Pago.txt"
     */
    public void registrarPagos() {
        ArrayList<Cliente> clientes = PLATAFORMA.clientes;
        Cliente clencontrado = null;
        Scanner sc = new Scanner(System.in);
        //Se pide al usuario toda la informacion a necesitar 
        System.out.println("Ingrese numero de cedula del cliente: ");
        String ced = sc.nextLine();
        int cedint;
        int cedint2 = Integer.parseInt(ced);
        //Validacion por si el usuario ingresa una cedula incorrecta
        boolean veri = false;
        while (veri == false) {
            for (Cliente cli : clientes) {
                if (cli.getCedula() == cedint2) {
                    clencontrado = cli;
                    veri = true;
                }
            }
            if (veri == false) {
                System.out.println("La cedula que ingreso no esta registrada, porfavor ingrese una cedula valida:  ");
                String ced2 = sc.nextLine();
                int cedint3 = Integer.parseInt(ced2);
                cedint2 = cedint3;

            }

        }

        System.out.println("QUE DESEA PAGAR?\n1. Multas \n2. Revision  \nElija una opcion: ");
        int op1 = sc.nextInt();
        sc.nextLine();
        System.out.println("Valor a pagar: ");
        double valor = sc.nextDouble();
        sc.nextLine();
        System.out.println("Que metodo de pago va a usar?\n1. Efectivo \n2. Tarjeta de credito  \nElija una opcion: ");
        int op2 = sc.nextInt();
        sc.nextLine();
        // Se creaa el valor en caso de que el metodo de pago sea tarjeta de credito 
        double adiciontc = (valor * 0.10) + valor;
        //Se crea la fecha y hora del pago 
        Date today = Calendar.getInstance().getTime();
        //Genera un código basado en los primeros y últimos dos dígitos de la cédula
        String codigo = ced.substring(0, 2) + ced.substring(ced.length() - 2);
        // Se guarda la informacion del pago en el archivo txt y se valida dependiendo  su metodo o razon de pago 
        //Se recorre la lista de Clientes para obtener el cliente al que le pertenece la cedula ingresada

        //-------------------------
//        for (Cliente cli : clientes) {
//            if (cli.getCedula() == cedint) {
//                clencontrado = cli;
//            }
//        }
        //-----------------
        //Se valida para cada caso de registro de texto 
        if (op2 == 1) {
            if (op1 == 1) {

                Pago pa = new Pago(codigo, clencontrado, valor, TipoPago.E, valor, "Multa");
                ManejoArchivos.EscribirArchivo("Pago.txt", pa.getCodigopago() + "," + pa.getCliente().getCedula() + "," + pa.getValorpagar() + "," + pa.getMetodopago() + "," + pa.getValorpagar() + "," + today + "," + pa.getRazonpago());
                System.out.println("Se registro con exito su pago");

            } else {
                Pago pa = new Pago(codigo, clencontrado, valor, TipoPago.E, valor, "Revision");
                ManejoArchivos.EscribirArchivo("Pago.txt", pa.getCodigopago() + "," + pa.getCliente().getCedula() + "," + pa.getValorpagar() + "," + pa.getMetodopago() + "," + pa.getValorpagar() + "," + today + "," + pa.getRazonpago());
                System.out.println("Se registro con exito su pago");

            }
        } else {
            if (op1 == 1) {
                Pago pa = new Pago(codigo, clencontrado, valor, TipoPago.T, adiciontc, "Multa");
                ManejoArchivos.EscribirArchivo("Pago.txt", pa.getCodigopago() + "," + pa.getCliente().getCedula() + "," + pa.getValorpagar() + "," + pa.getMetodopago() + "," + pa.getValorpagar() + "," + today + "," + pa.getRazonpago());
                System.out.println("Se registro con exito su pago");

            } else {
                Pago pa = new Pago(codigo, clencontrado, valor, TipoPago.T, adiciontc, "Revision");
                ManejoArchivos.EscribirArchivo("Pago.txt", pa.getCodigopago() + "," + pa.getCliente().getCedula() + "," + pa.getValorpagar() + "," + pa.getMetodopago() + "," + pa.getValorpagar() + "," + today + "," + pa.getRazonpago());
                System.out.println("Se registro con exito su pago");

            }
        }
    }

    @Override
    public String toString() {
        return "Operador" + this.getCedula() + this.getNombre() + this.getApellido() + this.getEdad() + this.getCorreo() + this.getUsuario() + this.getContrasenia() + this.getPerfil() + this.getSueldo();

    }
}
