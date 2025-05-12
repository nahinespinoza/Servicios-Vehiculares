/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.poo4_1p_g5_mendozaespinozabolanios;

/**
 * Esta clase representa a los Pagos
 * @author Nahin Espinoza 
 */
public class Pago {

    private String codigopago;
    private Cliente cliente;
    private double valorpagar;
    private TipoPago metodopago;
    private double valorfinalp;
    private String razonpago;

    /**
     * Constructor de la clase Pago
     *
     * @param codigopago codigo del pago
     * @param cliente Cedula del cliente
     * @param valorpagar valor del pago primitivo
     * @param metodopago metodo de pago(Efectivo o Tarjeta)
     * @param valorfinalp Valor del pago luego de valdiar si es cliente Estandar
     * o Estrella
     * @param razonpago Razon del pago
     */
    public Pago(String codigopago, Cliente cliente, double valorpagar, TipoPago metodopago, double valorfinalp, String razonpago) {
        this.codigopago = codigopago;
        this.cliente = cliente;
        this.valorpagar = valorpagar;
        this.valorfinalp = valorfinalp;
        this.metodopago = metodopago;
        this.razonpago = razonpago;

    }
//Getters y setters
    /**
     * Obtiene el codigo del pago
     * @return el codigo del pago
     */
    public String getCodigopago() {
        return codigopago;
    }
    /**
     * Estable el codigopago
     * @param codigopago estable el nuevo codigopago
     */
    public void setCodigopago(String codigopago) {
        this.codigopago = codigopago;
    }
    /**
     * Obtiene el cliente 
     * @return el cliente 
     */
    public Cliente getCliente() {
        return cliente;
    }
    /**
     * Establece un cliente
     * @param cliente establece un nuevo cliente
     */
    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }
    /**
     * Obtiene el valor a pagar de la multa
     * @return  el valor a pagar de la multa
     */
   
    public Double getValorpagar() {
        return valorpagar;
    }
    /**
     *  Establece un valor a pagar de la multa
     * @param valorpagar establece un valor nuevo para la multa
     */
    public void setValorpagar(Double valorpagar) {
        this.valorpagar = valorpagar;
    }
    /**
     * Obtiene un metodo de pago
     * @return el metodo de pago
     */
    public TipoPago getMetodopago() {
        return metodopago;
    }
    /**
     * Estable un metodo de pago
     * @param metodopago establece un nuevo metodo de pago
     */
    public void setMetodopago(TipoPago metodopago) {
        this.metodopago = metodopago;
    }
    /**
     * Obtiene el valor final de la multa en caso de ser cliente estrella
     * @return el valor final de la multa en caso de ser cliente estrella
     */
    public Double getValorfinalp() {
        return valorfinalp;
    }
    /**
     * Establece el valor final de la multa en caso de ser cliente estrella
     * @param valorfinalp establece un nuevo valor final de la multa en caso de ser cliente estrella
     */
    public void setValorfinalp(Double valorfinalp) {
        this.valorfinalp = valorfinalp;
    }
    /** 
     * Obtiene la razon de pago de la multa
     * @return la razon de pago de la multa 
     */
    public String getRazonpago() {
        return razonpago;
    }
    /**
     * Establece una razon de pago de la multa
     * @param razonpago establece una nueva razon de pago de la multa 
     */
    public void setRazonpago(String razonpago) {
        this.razonpago = razonpago;
    }

    @Override
    public String toString() {
        return "Pago: " + this.getCliente() + this.getCodigopago() + this.getMetodopago() + this.getRazonpago() + this.getValorfinalp() + this.getValorpagar();
    }
}
