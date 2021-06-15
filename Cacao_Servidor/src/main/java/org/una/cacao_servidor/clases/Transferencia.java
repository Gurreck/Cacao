package org.una.cacao_servidor.clases;

import java.util.ArrayList;

/**
 *
 * @author adria
 */
public class Transferencia {
    
    private String operacion;
    
    private ArrayList datosOperacion;
    
    private Partida partida;

    public void Transferencia() {
    }

    public void Transferencia(String operacion) {
        this.operacion = operacion;
    }

    public void Transferencia(String operacion, ArrayList datosOperacion, Partida partida) {
        this.operacion = operacion;
        this.datosOperacion = datosOperacion;
        this.partida = partida;
    }

    public void setOperacion(String operacion) {
        this.operacion = operacion;
    }

    public void setDatosOperacion(ArrayList datosOperacion) {
        this.datosOperacion = datosOperacion;
    }

    public void setPartida(Partida partida) {
        this.partida = partida;
    }

    public String getOperacion() {
        return operacion;
    }

    public ArrayList getDatosOperacion() {
        return datosOperacion;
    }

    public Partida getPartida() {
        return partida;
    }
   
}
