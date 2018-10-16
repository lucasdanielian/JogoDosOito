/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jogodosoito;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import view.TelaPrincipal;

/**
 *
 * @author JUNIOR
 */
public class JogoDosOito { 
  
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        
        TelaPrincipal telaPrincipal = new TelaPrincipal();
    
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
		telaPrincipal.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		telaPrincipal.setVisible(true);
            }
	});
    }
}
