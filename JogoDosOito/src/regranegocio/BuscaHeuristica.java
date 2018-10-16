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
public class BuscaHeuristica extends Busca{
    public int k = 0;
    protected int[] caminho1 = {1,2,3,4,5,6,7,0,8};
    protected int[] caminho2 = {1,2,3,4,5,6,0,7,8};
    protected int[] caminho3 = {1,2,3,4,5,0,6,7,8};
    
    /**
     * 
     */
    public BuscaHeuristica(){
        super.caminhos = new ArrayList<int[]>();
        super.caminhos.add(caminho3);
        super.caminhos.add(caminho1);
        super.caminhos.add(caminho2);
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
        List<Noh> reservas = new ArrayList<Noh>();
        List<Noh> candidatos = new ArrayList<Noh>();
        
        aindaNaoExplorados.add(raiz);
        
        boolean encontrouResultado = false;
        
        while(encontrouResultado == false){
            
            Noh atual;
            if (aindaNaoExplorados.size() > 0){
                atual = aindaNaoExplorados.get(0);
                jaExplorados.add(atual);
                aindaNaoExplorados.remove(0);

                atual.movimentoDeExpansao();
            }
            else{
                int indiceMenorNohReservas = buscarReservaMaisProximo(reservas);
                atual = reservas.get(indiceMenorNohReservas);
                reservas.remove(indiceMenorNohReservas);
                jaExplorados.add(atual);
                
                atual.movimentoDeExpansao();
            }
            
            for (int i = 0; i < atual.filhos.size(); i++) {
                
                Noh filhoAtual = atual.filhos.get(i);
                if(filhoAtual.objetivo()){
                    //System.out.println("Objetivo encontrado!");
                    encontrouResultado = true;
                    rastrearCaminho(caminhoDaSolucao, filhoAtual);
                }
                if((contem(aindaNaoExplorados,filhoAtual) == false) && (contem(jaExplorados,filhoAtual)== false) && (contem(reservas,filhoAtual) == false)){
                    candidatos.add(filhoAtual);
                }
            }
            
            int melhorNoh = encontraMelhorFilhoCandidato(candidatos);
            
            
            
            for (int i = 0; i < candidatos.size(); i++) {
                if(i != melhorNoh){
                    reservas.add(candidatos.get(i));
                }
                else{
                    aindaNaoExplorados.add(candidatos.get(i));
                    k++;
                }
            }
            
            for (int i = 0; i < candidatos.size(); i++) {
                candidatos.remove(i);
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
        
        System.out.println("Rastreando o caminho");
        Noh atual = n;
        caminho.add(atual);
        
        while(atual.pai !=null){
            atual = atual.pai;
            caminho.add(atual);
        }
    }
    
    /**
     * 
     * @param reservas
     * @return 
     */
    public int buscarReservaMaisProximo(List<Noh> reservas){
        int indiceNohMenor = 0;
        for (int i = 0; i < reservas.size(); i++) {
            int aux = reservas.get(i).calculaDistanciaDoObjetivo();
            if(aux < reservas.get(indiceNohMenor).calculaDistanciaDoObjetivo()){
                indiceNohMenor = i;
            }
        }
        return indiceNohMenor;
    }
    
    /**
     * 
     * @param candidatos
     * @return 
     */
    public int encontraMelhorFilhoCandidato(List<Noh> candidatos){
         
        int melhorDistancia = 1000;
        int melhorNohIndice = 5;
        int distanciaAux;

        for (int i = 0; i < candidatos.size(); i++) {
            distanciaAux = candidatos.get(i).calculaDistanciaDoObjetivo();
            if (distanciaAux < melhorDistancia) {
                melhorDistancia = distanciaAux;
                melhorNohIndice = i;
            }
        }

        return melhorNohIndice;
    }
}