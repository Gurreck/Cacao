/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.una.cacao_servidor.clases;

/**
 *
 * @author adria
 */
public class Transferencia {
    
    private String operacion;
    
    public void Transferencia(String _operacion){
        this.operacion = _operacion;
    }
    
    public String getOperacion(){
        return operacion;
    }
}
