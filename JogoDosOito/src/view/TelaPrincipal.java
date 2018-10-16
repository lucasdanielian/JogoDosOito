/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import regranegocio.Busca;
import regranegocio.BuscaEmLargura;
import regranegocio.BuscaHeuristica;
import regranegocio.Noh;

/**
 * Classe responsável pela interação por interface gráfica.
 * @author JUNIOR
 */
public class TelaPrincipal extends JFrame {

    /**
     * Variaveis Globais da classe
     */
    private static final long serialVersionUID = 1L;
    private JPanel jContentPane;
    private JPanel pnlTextFields;
    private JPanel pnlButtons;
    private JButton btnExecutar;
    private JButton btnExibirResultado = null;
    private JButton btnExibirResultadoCompleto;
    private JTextField[][] txtFields = new JTextField[3][3];
    private int[] matriz = new int[9];
    private JRadioButton rdbtnHeuristica;
    private JRadioButton rdbtnBuscaLargura;
    private JLabel labelQtdPassos;
    private JLabel labelTempoExecucao;
    private JButton btnEmbaralhar;
    private int escolhaAlgoritmo;
    private Noh raiz;
    private List<Noh> base;
    List<Noh> solucao;
    Busca busca;
    List<Noh> solucao2;
    int pos;
    boolean finalized;

    /**
     * This is the default constructor
     */
    public TelaPrincipal() {
        super();
        base = geraMatrizesIniciais();
        initialize();
        setarValoresMatriz(matriz);
        montarBotoes();
    }

    /**
     * Metodo responsável pelas configurações de layout dos botões e suas ações.
     */
    public void montarBotoes() {

        finalized = true;

        rdbtnHeuristica = new JRadioButton("Heuristica");
        rdbtnHeuristica.setBackground(Color.WHITE);
        rdbtnHeuristica.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent arg0) {
                escolhaAlgoritmo = 2;
                rdbtnBuscaLargura.setSelected(false);
            }
        });
        rdbtnHeuristica.setFont(new Font("Cambria", Font.BOLD, 12));
        rdbtnHeuristica.setBounds(12, 100, 109, 23);
        jContentPane.add(rdbtnHeuristica);

        rdbtnBuscaLargura = new JRadioButton("Busca em Largura");
        rdbtnBuscaLargura.setBackground(Color.WHITE);
        rdbtnBuscaLargura.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent arg0) {
                escolhaAlgoritmo = 1;
                rdbtnHeuristica.setSelected(false);
            }
        });
        rdbtnBuscaLargura.setFont(new Font("Cambria", Font.BOLD, 12));
        //,cima/baixo
        rdbtnBuscaLargura.setBounds(400, 100, 159, 23);
        jContentPane.add(rdbtnBuscaLargura);

        btnEmbaralhar = new JButton("Embaralhar");
        btnEmbaralhar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                embaralharVetor(base);
                labelQtdPassos.setText("Qtd Passos: ");
                labelTempoExecucao.setText("Tempo: ");
                btnExibirResultado.setEnabled(false);
                btnExibirResultadoCompleto.setEnabled(false);
            }
        });
        btnEmbaralhar.setFont(new Font("Cambria", Font.BOLD, 12));
        btnEmbaralhar.setBounds(240, 126, 100, 50);
        jContentPane.add(btnEmbaralhar);

        btnExecutar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                buttonExecutar();
                btnExibirResultado.setEnabled(true);
                btnExibirResultadoCompleto.setEnabled(true);
                pos = 0;
            }
        });

        btnExibirResultado.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                buttonVisualizarResultado(pos);
                pos++;
            }

        });
        
        btnExibirResultadoCompleto.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                enableAutoPlay();
            }
        });

        labelTempoExecucao = new JLabel("Tempo:");
        labelTempoExecucao.setBounds(1, 228, 200, 14);
        labelTempoExecucao.setFont(new Font("Cambria", Font.BOLD, 14));
        jContentPane.add(labelTempoExecucao);

        labelQtdPassos = new JLabel("Qtd Passos:");
        labelQtdPassos.setFont(new Font("Cambria", Font.BOLD, 14));
        labelQtdPassos.setBounds(30, 253, 200, 14);
        jContentPane.add(labelQtdPassos);
    }

    /**
     * Metodo responsável por chamar o tipo de busca escolhido
     * @param busca espera um tipo de busca (Em Largura ou Heuristica)
     */
    private void buscar(Busca busca) {
        raiz = new Noh(matriz);

        long start = System.currentTimeMillis();

        solucao = busca.realizarBusca(raiz);

        long elapsed = System.currentTimeMillis() - start;

        if (solucao.size() > 0) {
            int j = 0;

            for (int i = 0; i < solucao.size(); i++) {
                int aux = solucao.size() - i - 1;

                for (int k = 1; k < 10; k++) {

                    //solucao.get(aux).matriz[k - 1];
                }
            }

            int aux = solucao.size();
            aux--;

            labelQtdPassos.setText("Qtd Passos: " + aux);
            labelTempoExecucao.setText("Tempo: " + elapsed + " milisegundos");
        } else {
            labelQtdPassos.setText("Nenhum caminho foi encontrado");
            labelTempoExecucao.setText("Nenhum caminho foi encontrado");
        }
    }

    /**
     * Ação do botão Executar
     */
    private void buttonExecutar() {

        if (rdbtnBuscaLargura.isSelected() || rdbtnHeuristica.isSelected()) {
            if (escolhaAlgoritmo == 1) {
                busca = new BuscaEmLargura();
                buscar(busca);
            } else if (escolhaAlgoritmo == 2) {
                busca = new BuscaHeuristica();
                buscar(busca);
            } else {
                JOptionPane.showMessageDialog(null, "Por favor, escolha o metodo de busca");
            }
        } else {
            JOptionPane.showMessageDialog(null, "Por favor, escolha o metodo de busca");
        }
    }

    /**
     * Método responsável pela demonstração automatica do resultado
     */
    private void enableAutoPlay() {
        finalized = false;
        autoPlay();
    }
    
    /**
     * Método responsável pela demonstração automatica do resultado
     */
    private void autoPlay() {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                while (!finalized) {
                    holdOn(50);
                    buttonVisualizarResultado(pos);
                    pos++;
                }
            }
        });
        
        thread.start();
    }
    
    /**
     * Método responsável pela demonstração automatica do resultado
     * @param millis espera o tempo da thread em ms
     */
    private void holdOn(int millis) {
        long ti = System.currentTimeMillis();
        long tf;
        do {
            tf = System.currentTimeMillis();
            System.out.print("");
        } while (tf - ti < millis);
    }

    /**
     * Metodo responsável pela ação do botão visualizar resultado
     * @param pos espera um inteiro com a posição atual.
     */
    private void buttonVisualizarResultado(int pos) {

        int aux = solucao.size() - pos - 1;

        if (aux >= 0) {
            setarValoresMatriz(solucao.get(aux).getMatriz());
        } else {
            finalized = true;
            JOptionPane.showMessageDialog(null, "Chegou ao Fim");
        }
    }
    
    /**
     * Metodo responsável pela ação do botão visualizar resultado automaticamente.
     * @param pos espera um inteiro com a posição atual.
     */
    private void buttonVisualizarResultadoCompleto(int pos){
            
        int aux = solucao.size() - pos - 1;

        if (aux >= 0) {
            setarValoresMatriz(solucao.get(aux).getMatriz());
        } else {
            finalized = true;
            JOptionPane.showMessageDialog(null, "Chegou ao Fim");
        }
    }

    /**
     * radomiza as opções para iniciar o jogo, proporciona o dinamismo de opções de jogada.
     * @param base 
     */
    private void embaralharVetor(List<Noh> base) {
        Random gera = new Random();

        matriz = base.get(gera.nextInt(10000)).getMatriz();

        setarValoresMatriz(matriz);
    }

    /**
     * This method initializes pnlTextFields
     *
     * @return javax.swing.JPanel
     */
    private JPanel getPnlTextFields() {
        if (pnlTextFields == null) {
            GridLayout gridLayout = new GridLayout();
            gridLayout.setRows(3);
            gridLayout.setColumns(3);
            gridLayout.setHgap(2);
            gridLayout.setVgap(2);
            pnlTextFields = new JPanel();
            pnlTextFields.setLayout(gridLayout);
            for (int row = 0; row < 3; row++) {
                for (int col = 0; col < 3; col++) {
                    pnlTextFields.add(getTxtField(row, col));
                }
            }
        }
        return pnlTextFields;
    }

    /**
     * This method initializes btnExecutar
     *
     * @return javax.swing.JButton
     */
    private JButton getBtnExecutar() {
        if (btnExecutar == null) {
            btnExecutar = new JButton();
            btnExecutar.setText("EXECUTAR");
        }
        return btnExecutar;
    }

    /**
     * Criação do botão Exibir Resultado
     * @return JButton
     */
    private JButton getBtnExibirResultado() {
        if (btnExibirResultado == null) {
            btnExibirResultado = new JButton();
            btnExibirResultado.setText("MOVER PEÇA");
            btnExibirResultado.setEnabled(false);
        }
        return btnExibirResultado;
    }
    
    /**
     * Criação do botão Exibir resultado completo
     * 
     * @return JButton
     */
    private JButton getBtnExibirResultadoCompleto() {
        if (btnExibirResultadoCompleto == null) {
            btnExibirResultadoCompleto = new JButton();
            btnExibirResultadoCompleto.setText("EXIBIR RESULTADO COMPLETO");
            btnExibirResultadoCompleto.setEnabled(false);
        }
        return btnExibirResultadoCompleto;
    }

    /**
     * This method initializes pnlButtons
     *
     * @return javax.swing.JPanel
     */
    private JPanel getPnlButtons() {
        if (pnlButtons == null) {
            pnlButtons = new JPanel();
            pnlButtons.setLayout(new FlowLayout());
            pnlButtons.add(getBtnExecutar(), null);
            pnlButtons.add(getBtnExibirResultado(), null);
            pnlButtons.add(getBtnExibirResultadoCompleto(), null);
        }
        return pnlButtons;
    }

    /**
     * This method initializes txtField
     *
     * @return javax.swing.JTextField
     */
    private JTextField getTxtField(int i, int j) {
        if (txtFields[i][j] == null) {
            txtFields[i][j] = new JTextField();
            txtFields[i][j].setEditable(false);
        }
        return txtFields[i][j];
    }

    /**
     * This method initializes jContentPane
     *
     * @return javax.swing.JPanel
     */
    private JPanel getJContentPane() {
        if (jContentPane == null) {
            jContentPane = new JPanel();
            jContentPane.setLayout(new BorderLayout());
            jContentPane.add(getPnlTextFields(), BorderLayout.NORTH);
            jContentPane.add(getPnlButtons(), BorderLayout.SOUTH);
        }
        return jContentPane;
    }

    /**
     * This method initializes this
     *
     * @return void
     */
    private void initialize() {
        this.setSize(600, 400);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setContentPane(getJContentPane());
        this.setTitle("Jogo dos Oito");
        embaralharVetor(base);
    }

    /**
     * Metodo responsável por atualizar a interface gráfica de interação com usuário.
     * @param vet 
     */
    private void setarValoresMatriz(int[] vet) {
        int aux = 0;
        if (vet == null || vet.length < 9 || vet.length > 9) {
            JOptionPane.showMessageDialog(null, "Erro no setarValoresMatriz, a matriz não será atualizada");
        } else {
            for (int row = 0; row < 3; row++) {
                for (int col = 0; col < 3; col++) {
                    this.txtFields[row][col].setText("" + vet[aux]);
                    
                    if (vet[aux] == 0) {
                        
                        this.txtFields[row][col].setBackground(Color.LIGHT_GRAY);
                    } else {
                        
                        this.txtFields[row][col].setBackground(Color.white);
                    }
                    aux++;
                    this.txtFields[row][col].repaint();
                }
            }
        }
    }

    /**
     * Criação das matrizes que contém um possível conjunto solução.
     * @return 
     */
    private List<Noh> geraMatrizesIniciais() {
        List<Noh> lista = new ArrayList<Noh>();

        BuscaEmLargura b = new BuscaEmLargura();

        lista = b.geraFilhos();

        return lista;
    }
}
