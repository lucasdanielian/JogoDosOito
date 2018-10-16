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
public class Noh {
    
    public List<Noh> filhos  = new ArrayList<Noh>();
    public Noh pai;
    public int[] matriz = new int[9]; // a matriz é representada por um vetor pois executa mais rápido. 
    public int x = 0;
    public int col = 3;
    int id;
    
    public Noh(int [] p){
        setMatriz(p);
        
        geraId();
    }

    /**
     * 
     * @return 
     */
    public List<Noh> getFilhos() {
        return filhos;
    }
    
    /**
     * 
     * @param p 
     */
    public void setMatriz (int [] p){
        for (int i = 0; i < matriz.length; i++) {
            this.matriz[i] = p[i];
        }
    }
    
    /**
     * 
     * @return 
     */
    public boolean objetivo(){
        
        int objetivo = 123804765;
        
        if(this.id == objetivo){
            return true;
        }else{
            return false;
        }
    }
    
    /**
     * 
     * @param p
     * @param i 
     */
    public void moverParaDireita(int[] p, int i){
        
        if(i % col < col -1){
            int[] possMatriz = new int[9];
            copiaMatriz(possMatriz,p);
            
            int temp = possMatriz[i+1];
            possMatriz[i+1] = possMatriz[i];
            possMatriz[i] = temp;
            
            Noh novoFilho = new Noh(possMatriz);
            novoFilho.pai = this;
            filhos.add(novoFilho);
        }
    }
    
    /**
     * 
     * @param p
     * @param i 
     */
    public void moverParaEsquerda(int[] p, int i){
        
        if(i % col > 0){
            int [] possMatriz = new int[9];
            copiaMatriz(possMatriz,p);
            
            int temp = possMatriz[i-1];
            possMatriz[i-1] = possMatriz[i];
            possMatriz[i] = temp;
            
            Noh novoFilho = new Noh(possMatriz);
            novoFilho.pai = this;
            filhos.add(novoFilho);
        }
    }
    
    /**
     * 
     * @param p
     * @param i 
     */
    public void moverParaCima(int[] p, int i){
        
        if(i - col >= 0){
            int [] possMatriz = new int[9];
            copiaMatriz(possMatriz,p);
            
            int temp = possMatriz[i-3];
            possMatriz[i-3] = possMatriz[i];
            possMatriz[i] = temp;
            
            Noh novoFilho = new Noh(possMatriz);
            novoFilho.pai = this;
            filhos.add(novoFilho);
        }
    }
    
    /**
     * 
     * @param p
     * @param i 
     */
    public void moverParaBaixo(int[] p, int i){
        
        if(i + col < matriz.length){
            int [] possMatriz = new int[9];
            copiaMatriz(possMatriz,p);
            
            int temp = possMatriz[i+3];
            possMatriz[i+3] = possMatriz[i];
            possMatriz[i] = temp;
            
            Noh novoFilho = new Noh(possMatriz);
            novoFilho.pai = this;
            filhos.add(novoFilho);
        }
    }
    
    /**
     * 
     * @param a
     * @param b 
     */
    public void copiaMatriz (int [] a, int [] b){
        for (int i = 0; i < b.length; i++) {
            a[i] = b[i];
        }
    }
    
    /**
     * 
     * @return 
     */
    public int[] getMatriz(){
        return matriz;          
    }
    
    /**
     * 
     * @param outro
     * @return 
     */
    public boolean mesmaMatriz(Noh outro){
        
        if(outro.id == this.id){
            return true;
        }else{
            return false;
        }
    }
    
    /**
     * 
     */
    public void movimentoDeExpansao(){
        
        int posicaoDoZero = 0;
        for (int i = 0; i < matriz.length; i++) {
            if(matriz[i] == 0){
                posicaoDoZero = i;
            }
        }
        
        moverParaDireita(matriz,posicaoDoZero);
        moverParaEsquerda(matriz,posicaoDoZero);
        moverParaCima(matriz,posicaoDoZero);
        moverParaBaixo(matriz,posicaoDoZero);
    }
    
    /**
     * 
     * @return 
     */
    public int calculaDistanciaDoObjetivo(){
        int distancia = 0;
        
        for (int i = 0; i < matriz.length; i++) {
            switch(i){
                case 0:
                    switch(matriz[0]){
                        case 0:
                            distancia = distancia +2;
                        case 2:
                            distancia = distancia +1;
                        case 3:
                            distancia = distancia + 3;
                        case 4: 
                            distancia = distancia + 4;
                        case 5:
                            distancia = distancia + 5;
                        case 6: 
                            distancia = distancia +3;
                        case 7:
                            distancia = distancia + 2;
                        case 8:
                            distancia = distancia + 1;
                    }
                case 1:
                    switch(matriz[1]){
                        case 0:
                            distancia = distancia + 1;
                        case 1:
                            distancia = distancia + 1;
                        case 3:
                            distancia = distancia + 1;
                        case 4:
                            distancia = distancia + 2;
                        case 5: 
                            distancia = distancia + 3;
                        case 6:
                            distancia = distancia + 2;
                        case 7:
                            distancia = distancia + 3;
                        case 8:
                            distancia = distancia + 2;
                    }
                case 2:
                    switch(matriz[2]){
                        case 0:
                            distancia = distancia + 2;
                        case 1:
                            distancia = distancia + 2;
                        case 2:
                            distancia = distancia + 1;
                        case 4:
                            distancia = distancia + 1;
                        case 5:
                            distancia = distancia + 2;
                        case 6:
                            distancia = distancia + 3;
                        case 7:
                            distancia = distancia + 4;
                        case 8:
                            distancia = distancia + 3;
                    }
                case 3:
                    switch(matriz[3]){
                        case 0:
                            distancia = distancia + 1;
                        case 1:
                            distancia = distancia + 1;
                        case 2:
                            distancia = distancia + 2;
                        case 3:
                            distancia = distancia + 3;
                        case 4:
                            distancia = distancia + 2;
                        case 5:
                            distancia = distancia + 3;
                        case 6:
                            distancia = distancia + 2;
                        case 7:
                            distancia = distancia + 1;   
                    }
                case 4:
                    switch(matriz[4]){
                        case 1:
                            distancia = distancia + 2;
                        case 2:
                            distancia = distancia + 1;
                        case 3: 
                            distancia = distancia + 2;
                        case 4:
                            distancia = distancia + 1;
                        case 5:
                            distancia = distancia + 2;
                        case 6:
                            distancia = distancia + 1;
                        case 7:
                            distancia = distancia + 2;
                        case 8:
                            distancia = distancia + 1;
                    }
                case 5:
                    switch(matriz[5]){
                        case 0:
                            distancia = distancia + 1;
                        case 1:
                            distancia = distancia + 3;
                        case 2:
                            distancia = distancia + 2;
                        case 3:
                            distancia = distancia + 1;
                        case 5:
                            distancia = distancia + 1;
                        case 6:
                            distancia = distancia + 2;
                        case 7:
                            distancia = distancia + 3;
                        case 8:
                            distancia = distancia + 2;
                    }
                case 6:
                    switch(matriz[6]){
                        case 0: 
                            distancia = distancia + 2;
                        case 1:
                            distancia = distancia + 2;
                        case 2: 
                            distancia = distancia + 3;
                        case 3:
                            distancia = distancia + 4;
                        case 4:
                            distancia = distancia + 3;
                        case 5:
                            distancia = distancia + 2;
                        case 6:
                            distancia = distancia + 1;
                        case 8:
                            distancia = distancia + 1;
                    }
                case 7:
                    switch(matriz[7]){
                        case 0:
                            distancia = distancia + 1;
                        case 1:
                            distancia = distancia + 3;
                        case 2:
                            distancia = distancia + 2;
                        case 3:
                            distancia = distancia + 3;
                        case 4:
                            distancia = distancia + 2;
                        case 5:
                            distancia = distancia + 1;
                        case 7:
                            distancia = distancia + 1;
                        case 8:
                            distancia = distancia + 2;
                    }
                case 8:
                    switch(matriz[8]){
                        case 0:
                            distancia = distancia + 2;
                        case 1:
                            distancia = distancia + 4;
                        case 2:
                            distancia = distancia + 3;
                        case 3:
                            distancia = distancia + 2;
                        case 4:
                            distancia = distancia + 1;
                        case 6:
                            distancia = distancia + 1;
                        case 7:
                            distancia = distancia + 2;
                        case 8:
                            distancia = distancia + 3;
                    }
                            
            }
        }
        return distancia;
    }
    
    /**
     * 
     */
    public void geraId(){
        
       String mat = "";
       
        for (int i = 0; i < this.matriz.length; i++) {
            mat = mat + this.matriz[i];
        }
        
        int identificador = Integer.parseInt(mat);
        
        this.id = identificador;
    }
}