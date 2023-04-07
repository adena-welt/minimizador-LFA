package program;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.util.Arrays;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JTextArea;
import static javax.swing.WindowConstants.EXIT_ON_CLOSE;
import javax.swing.border.EmptyBorder;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

public class Interface extends JFrame {
 
    public void imprime(char mat[][], Qs vetqs, String[] simbs, List<Transicao> list, String[] inicio, String[] fim){
        
        JPanel contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JScrollPane scroll = new JScrollPane();
        scroll.setBounds(0, 0, 1052, 660);
        contentPane.add(scroll);

        JPanel panel = new JPanel();
        scroll.setViewportView(panel);
        panel.setLayout(null);
        
        int width = 450;
        if(vetqs.qtd.length>7)
           width = vetqs.qtd.length*50;
        panel.setPreferredSize(new Dimension(80*(vetqs.qtd.length+1)+width, 80*(vetqs.qtd.length+1)));

        for(int i=1; i<vetqs.qtd.length; i++){
            if(vetqs.ehfinal[i]==1 && vetqs.inicial[i]==1){
                JButton qs = new JButton("→*" + vetqs.qtd[i]);
                configuraBotao2(10, i*80, panel, qs); 
            }
            else if(vetqs.ehfinal[i]==1){
                JButton qs = new JButton("*" + vetqs.qtd[i]);
                configuraBotao2(10, i*80, panel, qs); 
            }
            else if(vetqs.inicial[i]==1){
                JButton qs = new JButton("→" + vetqs.qtd[i]);
                configuraBotao2(10, i*80, panel, qs); 
            }
            else{
                JButton qs = new JButton(vetqs.qtd[i]);
                configuraBotao2(10, i*80, panel, qs); 
            }  
        }
        for(int i=1; i<vetqs.qtd.length; i++){
            for(int j=0; j<vetqs.qtd.length-1; j++){
                if(j<i){
                    configuraBotao(90+j*80, i*80, i, j, mat, panel);
                }
            }        
        }
        int xq=90, yq=80*vetqs.qtd.length;
        for(int j=0; j<vetqs.qtd.length-1; j++){
            if(vetqs.ehfinal[j]==1 && vetqs.inicial[j]==1){
                JButton qs = new JButton("→*" + vetqs.qtd[j]);
                configuraBotao2(xq, yq, panel, qs); 
            }
            else if(vetqs.ehfinal[j]==1){
                JButton qs = new JButton("*" + vetqs.qtd[j]);
                configuraBotao2(xq, yq, panel, qs); 
            }
            else if(vetqs.inicial[j]==1){
                JButton qs = new JButton("→" + vetqs.qtd[j]);
                configuraBotao2(xq, yq, panel, qs); 
            }
            else{
                JButton qs = new JButton(vetqs.qtd[j]);
                configuraBotao2(xq, yq, panel, qs);
            }
            xq+=80;
        }

        imprimeTransicoes(vetqs, simbs, list, inicio, fim, panel);
        
        setTitle("Autômato Minimizado");
        setSize(1050,700);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
        setResizable(false);
    }
    
    private void configuraBotao(int x, int y, int i, int j, char mat[][], JPanel panel){       
        Font fonte = new Font("Arial", Font.BOLD, 18);
        JButton qs = new JButton(Character.toString(mat[i][j]));
        qs.setLayout(null);
        qs.setBackground(Color.GRAY);
        qs.setFont(fonte);
        qs.setBounds(x, y, 70, 70);
        qs.setFocusable(false);
        panel.add(qs);
        panel.repaint();
    }
    
    private void configuraBotao2(int x, int y, JPanel panel, JButton qs){       
        Font fonte = new Font("Arial", Font.BOLD, 12);
        setLayout(null);
        qs.setBounds(x, y,70,70);
        qs.setFont(fonte);
        qs.setFocusable(false);
        panel.add(qs);
        panel.repaint();
    }
    
    public void imprimeTransicoes(Qs qs, String[] simbs, List<Transicao> list, String[] inicio, String[] fim, JPanel panel){
        Font fonte = new Font("Arial", Font.BOLD, 18);
        int width = 450;
        if(qs.qtd.length>7)
           width = qs.qtd.length*50;
        JTextArea texto = new JTextArea();
        texto.setBackground(new java.awt.Color(0,0,0,0));
        texto.setFont(fonte);
        texto.setBounds(80*(qs.qtd.length+1), 80, width, (qs.qtd.length*2+5)*25);
        texto.append("q's = " + Arrays.toString(qs.qtd));  
        texto.append("\nsimbolos = " + Arrays.toString(simbs));
        texto.append("\nTRANSIÇÕES:");
        for (Transicao p : list) {
		String listaComoString = p.toString();
                texto.append("\n" + listaComoString);
        }
        texto.append("\nInicial = " + Arrays.toString(inicio));
        texto.append("\nFinal = " + Arrays.toString(fim));
        panel.add(texto);
        panel.repaint();
    }               
}