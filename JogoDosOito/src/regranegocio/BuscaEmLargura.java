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
public class BuscaEmLargura extends Busca {
    int k = 0;
    
    protected int[] caminho1 = {1,2,3,4,5,6,7,0,8};
    protected int[] caminho2 = {1,2,3,4,5,6,0,7,8};
    protected int[] caminho3 = {1,2,3,4,5,0,6,7,8};
    
    /**
     * 
     */
    public BuscaEmLargura(){
        super.caminhos = new ArrayList<int[]>();
        super.caminhos.add(caminho1);
        super.caminhos.add(caminho2);
        super.caminhos.add(caminho3);
    }

    /**
     * 
     * @param caminhos 
     */
    @Override
    public void setCaminhos(List<int[]> caminhos) {
        super.setCaminhos(caminhos); //To change body of generated methods, choose Tools | Templates.
    }
    
    /**
     * 
     * @param raiz
     * @return 
     */
    @Override
    public List<Noh> realizarBusca(Noh raiz){
        
        List<Noh> aindaNaoExplorados = new ArrayList<Noh>(); // fila de nos ainda nao explorados
        List<Noh> jaExplorados = new ArrayList<Noh>();
        
        aindaNaoExplorados.add(raiz);
        
        boolean encontrouResultado = false;
        
        while(aindaNaoExplorados.size() > 0 && encontrouResultado == false){
            
            Noh atual = aindaNaoExplorados.get(0);
            jaExplorados.add(atual);
            aindaNaoExplorados.remove(0);
            
            atual.movimentoDeExpansao();
            
            for (int i = 0; i < atual.filhos.size(); i++) {
                
                Noh filhoAtual = atual.filhos.get(i);
                if(filhoAtual.objetivo()){
                    //System.out.println("Objetivo encontrado!");
                    encontrouResultado = true;
                    rastrearCaminho(caminhoDaSolucao, filhoAtual);
                }
                if((contem(aindaNaoExplorados,filhoAtual) == false) && (contem(jaExplorados,filhoAtual)== false)){
                    aindaNaoExplorados.add(filhoAtual);
                    k++;
                    //System.out.println("adicionei alguem novo na fila" +k);
                }
                else{
                    
                    //System.out.println("Achei alguem igual " + k);
                }
            }
        }
        
        return caminhoDaSolucao;
    }
    
    /**
     * 
     * @param lista
     * @param teste
     * @return 
     */
    public static boolean contem(List<Noh> lista, Noh teste){
        for (int i = 0; i < lista.size(); i++) {
            if(lista.get(i).id == teste.id){
                return true;
            }
        }
        
        return false;
    }
    
    /**
     * 
     * @param caminho
     * @param n 
     */
    public void rastrearCaminho(List<Noh> caminho, Noh n){
        
        //System.out.println("Rastreando o caminho");
        Noh atual = n;
        caminho.add(atual);
        
        while(atual.pai !=null){
            atual = atual.pai;
            caminho.add(atual);
        }
    }
    
    /**
     * 
     * @return 
     */
    public List<Noh> geraFilhos(){
        int l = 0;
        
        int mat[] = {
            
            1,2,3,
            8,0,4,
            7,6,5
            
        };
        
        Noh raiz = new Noh(mat);
        List<Noh> aindaNaoExplorados = new ArrayList<Noh>(); // fila de nos ainda nao explorados
        List<Noh> jaExplorados = new ArrayList<Noh>();
        
        aindaNaoExplorados.add(raiz);
        
        while(aindaNaoExplorados.size() < 10000){
            
            Noh atual = aindaNaoExplorados.get(0);
            jaExplorados.add(atual);
            aindaNaoExplorados.remove(0);
            
            atual.movimentoDeExpansao();
            
            for (int i = 0; i < atual.filhos.size(); i++) {
                
                Noh filhoAtual = atual.filhos.get(i);

                if((contem(aindaNaoExplorados,filhoAtual) == false) && (contem(jaExplorados,filhoAtual)== false)){
                    aindaNaoExplorados.add(filhoAtual);
                    l++;
                    //System.out.println(aindaNaoExplorados.size());
                }
                else{

                }
            }
        }
        
        return aindaNaoExplorados;
    }
}
