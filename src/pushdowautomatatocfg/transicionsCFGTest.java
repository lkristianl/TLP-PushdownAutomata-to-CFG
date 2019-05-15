/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pushdowautomatatocfg;

/**
 *
 * @author Kris
 */
public class transicionsCFGTest {
        // Estado no terminal
    public char estado;
            
    
    // Transiciones del estado no terminal
    public int numeroTransiciones = 2;
    public String[] transiciones = new String[numeroTransiciones];

    @Override
    public String toString() {
        String conjTransiciones;
        conjTransiciones = this.transiciones[0];
        for (int i = 1; i < numeroTransiciones; i++)
        {
            conjTransiciones = conjTransiciones + " | " + this.transiciones[i];
        }
        
        return estado + "->" + conjTransiciones + '\n';
    }
    
    

    public char getEstado() {
        return estado;
    }

    public void setEstado(char estado) {
        this.estado = estado;
    }

    public String[] getTransiciones() {
        return transiciones;
    }

    public void setTransiciones(String[] transiciones) {
        this.transiciones = transiciones;
    }
    
}
