package com.mycompany.poo4_1p_g5_mendozaespinozabolanios;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Random;
import java.util.Date;
import java.text.SimpleDateFormat;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
/**
 * Esta clase representa a los metodos que puede realizar un cliente
 * @author Guillermo Mendoza
 */
public class Cliente extends Usuario {

    private String Num_Tarjeta; //No cambiar a int da error al leer el archivo
    private int Puntos_lic;
    private Vehiculo vehiculo;
    static ArrayList<Cliente> clientes;
    
    /**
     * Constructor de cliente
     * @param cedula de cliente
     * @param nombre de cliente
     * @param apellidos de cliente
     * @param edad del cliente
     * @param correo del cliente
     * @param usuario del cliente
     * @param contrasenia del cliente
     * @param perfil del cliente
     * @param Num_Tarjeta del cliente
     * @param Puntos_lic del cliente
     * @param vehiculo del cliente
     */
    public Cliente(int cedula, String nombre, String apellidos, int edad, String correo, String usuario, String contrasenia, TipoUsuario perfil, String Num_Tarjeta, int Puntos_lic, Vehiculo vehiculo){
        super(cedula,nombre,apellidos,edad,correo,usuario,contrasenia,perfil);
        this.Num_Tarjeta=Num_Tarjeta;
        this.Puntos_lic=Puntos_lic;
        this.vehiculo=vehiculo;
    }
    
    //GETTERS Y SETTERS
    /**
     * 
     * @return numero de tarjeta del duenio
     */
    public String getNum_Tarjeta() {
        return Num_Tarjeta;
    }
    /**
     * Se cambia el numero de tarjeta
     * @param Num_Tarjeta numero de tarjeta del usuario
     */
    public void setNum_Tarjeta(String Num_Tarjeta) {
        this.Num_Tarjeta = Num_Tarjeta;
    }
    /**
     * 
     * @return puntos de lincencia del cliente
     */
    public int getPuntos_lic() {
        return Puntos_lic;
    }
    /**
     * Cambia los puntos de lincencia del cliente
     * @param Puntos_lic puntos de licencia nuevos
     */
    public void setPuntos_lic(int Puntos_lic) {
        this.Puntos_lic = Puntos_lic;
    }
    /**
     * 
     * @return objeto vehiculo del cliente
     */
    public Vehiculo getVehiculo() {
        return vehiculo;
    }
    /**
     * Cambia el objeto vehiculo del cliente
     * @param vehiculo vehiculo nuevo
     */
    public void setVehiculo(Vehiculo vehiculo) {
        this.vehiculo = vehiculo;
    }
    
    //CONSULTAR MULTAS
    @Override
    /**
     * Metodo mediante el cliente puede consultar todas sus multas.
     */
    public void consultarmultas(){
        ArrayList<Multa> multas = PLATAFORMA.multas; //Llama la lista de multas cargadas en la plataforma
        double saldoPagar=0;
        Scanner sc=new Scanner(System.in); 
        System.out.println("Ingrese su cedula o la placa de su vehiculo (si su cedula inicia con 0 omitalo): ");
        String s = sc.nextLine().trim();
        Multa n=null;//Crea una multa vacia para luego almacenar la multa que se usará para la comparación en el ArrayList de multa
        
        if(s.equals(String.valueOf(this.getCedula()))){
            n= new Multa(this,this.getVehiculo(),"d",0.0,null,null,0); //Caso de que el cliente ingrese cedula
        }
        else if(s.equals(this.getVehiculo().getPlaca())){
            n = new Multa(this,this.getVehiculo(),null,0.0,null,null,0);//Caso de que el cliente ingrese placa
        }
        else if(!s.equals(String.valueOf(this.getCedula()))&&!s.equals(this.getVehiculo().getPlaca())){
            System.out.println("La cedula o la placa es incorrecta"); //Validacion de que la placa o la cedula sean escritas correctamente
        }
       
        if (!multas.contains(n)){
            System.out.println("Usted no tiene multas");
        }
        else{ //Muestra las multas del cliente
            System.out.println("----------------------DETALLE DE MULTAS-----------------------");
            System.out.println("CEDULA | MATRICULA  |  INFRACCION  |  VALOR A PAGAR  |  FECHA DE INFRACCION  |  FECHA DE NOTIFICACION  |  PUNTOS ");
            for(Multa m: multas){
                if(this.getCedula()==m.getCliente().getCedula()){
                     System.out.println(m.getCliente().getCedula()+"|"+m.getVehiculo().getPlaca()+"|"+m.getInfraccion()+"|"+m.getValorMulta()+"|"+m.getFechaInfraccion()+"|"+m.getFechaNotificacion()+"|"+m.getPuntos());
                    saldoPagar+=m.getValorMulta();
            }
        }
        System.out.println("\nVALOR A PAGAR: " + saldoPagar);
        System.out.println("Para PAGAR ACERQUESE A LA AGENCIA MAS CERCANA");
        }
    }

    
    //AGENDAR REVISION
    /**
     * Metodo para agendar y registrar la revision del vehiculo
     */
    public void agendarRevision(){
        SimpleDateFormat sdf=new SimpleDateFormat("dd-MM-YYYY");
        ArrayList<Date> fechas=PLATAFORMA.fechas;//Llama la lista de fechas cargadas en la plataforma
        ArrayList<Multa> multas=PLATAFORMA.multas;//Llama la lista de multas cargadas en la plataforma
        Random r = new Random(); //Se utilizará para el código de la revisión
        Scanner sc = new Scanner(System.in);
        int horario=0; //Almacena el horario elegido por el cliente
        int cont=1;//Se usa para mostrar los horarios disponibles
        double base = 150.0;//Precio base de unaa revisión
        double valor_P=0;
        System.out.println("Ingrese placa: "); //Pide placa al cliente
        String placa= sc.nextLine();
        if(placa.equals(this.getVehiculo().getPlaca())){//Valida que la placa sea escrita correctamente
            Multa n = new Multa(this,this.getVehiculo(),"",0.0,"","",0);
            if(!multas.contains(n)){
                
                System.out.println("Usted no tiene multas");
                System.out.println("---- HORARIOS DISPONIBLES----");
                for(Date F : fechas){
                    System.out.println(cont + ". "+sdf.format(F)+" " + F.getHours() + ":"+F.getMinutes());
                    cont++;//Muestra los horarios disponibles
                }
                System.out.println("Escoga horario: ");
                horario=sc.nextInt();
                if(this.getPerfil().equals(TipoUsuario.E)){//Verifica que el el perfil del cliente sea Estrella para aplicar el descuento
                    valor_P= base*0.8;
                }
                else{
                    valor_P=base+((30-this.Puntos_lic)*10);//Opcion cliente estandar
                    
                }
            System.out.println(this.getNombre()+" "+this.getApellido()+"se ha agendado su cita para el "
                    +sdf.format(fechas.get(horario-1))+" a las " + fechas.get(horario-1).getHours()+":"+fechas.get(horario-1).getMinutes()+
                    "\nValor a pagar: "+valor_P);
            System.out.println("Puede pagar su cita hasta 24 horas antes de la cita.\nDe lo contrario la cita se cancelara");
           
            int cita_Code= r.nextInt(1000, 10000); //Genera codigo de la cita
            
            ManejoArchivos.EscribirArchivo("AgendaRevisiones.txt",cita_Code + "," + this.getCedula()+","+placa+","+sdf.format(fechas.get(horario-1)));
            fechas.remove(horario-1); //Remueve el horario escogido por el cliente de la lista de fechas
            }
            
        
            else{
                System.out.println("Usted tiene multas no puede agendar citas");
            }
        }else{
            System.out.println("La placa ingresada no existe");
        }
    }
    
    @Override
    public String toString(){
        return this.getNombre()+" "+this.getApellido()+" | "+this.getCedula()+" | "+this.getNum_Tarjeta()+" | "+this.getPerfil()+" | "+this.getVehiculo().getPlaca();
    }
    
}
    
    
    
  

  