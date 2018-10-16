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
 * @author lucas.danielian
 */
public class GerarMalha {
    
    List<Noh> aindaNaoExplorados = new ArrayList<Noh>();
    
    List<Noh> jaExplorados = new ArrayList<Noh>();
    
    int nosDiferentesGerados = 0;
    
    /**
     * 
     * @param raiz 
     */
    public void GeraMalha(Noh raiz){
        
        aindaNaoExplorados.add(raiz);
        
        nosDiferentesGerados = 1;
        
        while(nosDiferentesGerados < 360000){
            
            Noh atual = aindaNaoExplorados.get(0);
            jaExplorados.add(atual);
            aindaNaoExplorados.remove(0);
            
            atual.movimentoDeExpansao();
            
            for (int i = 0; i < atual.filhos.size(); i++) {
                
                Noh filhoAtual = atual.filhos.get(i);
                
                if(contem(aindaNaoExplorados,filhoAtual) == true || contem(jaExplorados,filhoAtual) == true){
                    //nao faz nada, esse noh ja foi explorado
                }else{
                    aindaNaoExplorados.add(filhoAtual);
                    nosDiferentesGerados++;
                    if(nosDiferentesGerados % 10000 == 0){
                        System.out.println(nosDiferentesGerados + " nos diferentes gerados");
                    }
                }
            }
            
        }
    }
    
    /**
     * 
     * @param lista
     * @param teste
     * @return 
     */
    public boolean contem(List<Noh> lista, Noh teste){
        for (int i = 0; i < lista.size(); i++) {
            if(lista.get(i).id == teste.id){
                return true;
            }
        }
        
        return false;
    }
}
