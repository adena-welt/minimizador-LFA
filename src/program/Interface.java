package program;

import java.awt.Color;
import java.awt.Font;
import java.awt.TextArea;
import java.util.Arrays;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import static javax.swing.WindowConstants.EXIT_ON_CLOSE;

/**
 *
 * @author adena
 */
public class Interface extends JFrame{
    
    public void imprimeMat(char mat[][], Qs vetqs){
        
        for(int i=1; i<vetqs.qtd.length; i++){
            for(int j=0; j<vetqs.qtd.length-1; j++){
                if(j==0){
                    JButton qs = new JButton(vetqs.qtd[i]);
                    setLayout(null);
                    qs.setBounds(10, i*70,60,60);
                    add(qs);
                }
                if(j<i){
                    configuraBotao(80+j*80, i*70, i, j, mat);
                }
            }
            int xq=80, yq=70*vetqs.qtd.length;
            for(int j=0; j<vetqs.qtd.length-1; j++){
                JButton qs = new JButton(vetqs.qtd[j]);
                setLayout(null);
                qs.setBounds(xq, yq,60,60);
                add(qs);
                xq+=80;
            }
        }
        setTitle("Autômato Minimizado");
        setSize(1000,600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
    }
    
    private void configuraBotao(int x, int y, int i, int j, char mat[][]){       
        JButton jb = new JButton(Character.toString(mat[i][j]));
        jb.setLayout(null);
        jb.setBackground(Color.GRAY);
        jb.setBounds(x, y, 60, 60);
        add(jb);
    }
    
    public void imprimeTransicoes(Qs qs, String[] simbs, List<Transicao> list, String[] inicio, String[] fim){
        Font fonte = new Font("Arial", Font.BOLD, 18);
        JTextArea texto = new JTextArea();
        texto.setBackground(new java.awt.Color(0,0,0,0));
        texto.setFont(fonte);
        texto.setBounds(80*(qs.qtd.length+1), 50, 400, 500);
        
        texto.append("\n q's = " + Arrays.toString(qs.qtd));  
        texto.append("\n simbolos = " + Arrays.toString(simbs));
        texto.append("\n TRANSIÇÕES:");
        add(texto);
        for (Transicao p : list) {
		String listaComoString = p.toString();
                texto.append("\n " + listaComoString);
        }
        texto.append("\n Inicio = " + Arrays.toString(inicio));
        texto.append("\n Fim = " + Arrays.toString(fim));
    }
}
