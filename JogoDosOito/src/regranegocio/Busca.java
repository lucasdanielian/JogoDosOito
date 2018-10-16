/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package regranegocio;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Lucas e Valdeci
 */
public abstract class Busca {
    //Variaveis Globais da classe
    protected List<Noh> caminhoDaSolucao;
    protected List<int[]> caminhos;
    
    /**
     * 
     * @return 
     */
    public List<int[]> getCaminhos(){
        return caminhos;
    }

    /**
     * 
     * @param caminhos 
     */
    public void setCaminhos(List<int[]> caminhos) {
        this.caminhos = caminhos;
    }
    
    /**
     * 
     */
    Busca(){
        caminhoDaSolucao =  new ArrayList<Noh>();
    }
    
    /**
     * 
     * @param raiz
     * @return 
     */
    public abstract List<Noh> realizarBusca(Noh raiz);
    
}
