package org.una.cacao_servidor.clases;

import java.util.List;

/**
 *
 * @author adria
 */
public class Transferencia {
    
    private String operacion;
    
    private List<Object> datosOperacion;
    
    private Partida partida;

    public Transferencia() {
    }

    public Transferencia(String operacion, List<Object> datosOperacion, Partida partida) {
        this.operacion = operacion;
        this.datosOperacion = datosOperacion;
        this.partida = partida;
    }

    public void setOperacion(String operacion) {
        this.operacion = operacion;
    }

    public void setDatosOperacion(List<Object> datosOperacion) {
        this.datosOperacion = datosOperacion;
    }

    public void setPartida(Partida partida) {
        this.partida = partida;
    }

    public String getOperacion() {
        return operacion;
    }

    public List<Object> getDatosOperacion() {
        return datosOperacion;
    }

    public Partida getPartida() {
        return partida;
    }
   
}
