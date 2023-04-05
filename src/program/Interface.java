package program;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
                  
    JButton qs = new JButton();
    JTextArea texto = new JTextArea();
 
    public void imprimeMat(char mat[][], Qs vetqs, String[] simbs, List<Transicao> list, String[] inicio, String[] fim){
        
        JPanel contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JScrollPane scroll = new JScrollPane();
        scroll.setBounds(25, 25, 1000, 600);
        contentPane.add(scroll);

        JPanel panel = new JPanel();
        scroll.setViewportView(panel);
        panel.setLayout(null);

        panel.setPreferredSize(new Dimension(5000, 5000));

        JButton adicionar = new JButton("Minimização do AFD");
        adicionar.setBounds(430, 1, 200, 23);
        contentPane.add(adicionar);

        adicionar.addActionListener(new ActionListener() 
        {
            public void actionPerformed(ActionEvent e) 
            {
                Font fonte = new Font("Arial", Font.BOLD, 12);
                for(int i=1; i<vetqs.qtd.length; i++){
                    if(vetqs.ehfinal[i]==1 && vetqs.inicial[i]==1){
                        qs = new JButton("→*" + vetqs.qtd[i]);
                        setLayout(null);
                        qs.setBounds(10, i*80,70,70);
                        qs.setFont(fonte);
                        qs.setFocusable(false);
                        panel.add(qs);
                        panel.repaint();
                    }
                    else if(vetqs.ehfinal[i]==1){
                        qs = new JButton("*" + vetqs.qtd[i]);
                        setLayout(null);
                        qs.setBounds(10, i*80,70,70);
                        qs.setFont(fonte);
                        qs.setFocusable(false);
                        panel.add(qs);
                        panel.repaint();
                    }
                    else if(vetqs.inicial[i]==1){
                        qs = new JButton("→" + vetqs.qtd[i]);
                        setLayout(null);
                        qs.setBounds(10, i*80,70,70);
                        qs.setFont(fonte);
                        qs.setFocusable(false);
                        panel.add(qs);
                        panel.repaint();
                    }
                    else{
                        qs = new JButton(vetqs.qtd[i]);
                        setLayout(null);
                        qs.setBounds(10, i*80,70,70);
                        qs.setFont(fonte);
                        qs.setFocusable(false);
                        panel.add(qs);
                        panel.repaint();
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
                        qs = new JButton("→*" + vetqs.qtd[j]);
                        setLayout(null);
                        qs.setBounds(xq, yq,70,70);
                        qs.setFont(fonte);
                        qs.setFocusable(false);
                        panel.add(qs);
                        panel.repaint();
                    }
                    else if(vetqs.ehfinal[j]==1){
                        qs = new JButton("*" + vetqs.qtd[j]);
                        setLayout(null);
                        qs.setBounds(xq, yq,70,70);
                        qs.setFont(fonte);
                        qs.setFocusable(false);
                        panel.add(qs);
                        panel.repaint();
                    }
                    else if(vetqs.inicial[j]==1){
                        qs = new JButton("→" + vetqs.qtd[j]);
                        setLayout(null);
                        qs.setBounds(xq, yq,70,70);
                        qs.setFont(fonte);
                        qs.setFocusable(false);
                        panel.add(qs);
                        panel.repaint();
                    }
                    else{
                        qs = new JButton(vetqs.qtd[j]);
                        setLayout(null);
                        qs.setBounds(xq, yq,70,70);
                        qs.setFont(fonte);
                        qs.setFocusable(false);
                        panel.add(qs);
                        panel.repaint();
                    }
                    xq+=80;
                }

                imprimeTransicoes(vetqs, simbs, list, inicio, fim, panel);
            }
        });
        
        setTitle("Autômato Minimizado");
        setSize(1050,700);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
    }
    
    private void configuraBotao(int x, int y, int i, int j, char mat[][], JPanel panel){       
        Font fonte = new Font("Arial", Font.BOLD, 18);
        qs = new JButton(Character.toString(mat[i][j]));
        qs.setLayout(null);
        qs.setBackground(Color.GRAY);
        qs.setFont(fonte);
        qs.setBounds(x, y, 70, 70);
        qs.setFocusable(false);
        panel.add(qs);
        panel.repaint();
    }
    
    public void imprimeTransicoes(Qs qs, String[] simbs, List<Transicao> list, String[] inicio, String[] fim, JPanel panel){
        Font fonte = new Font("Arial", Font.BOLD, 18);
        texto.setBackground(new java.awt.Color(0,0,0,0));
        texto.setFont(fonte);
        texto.setBounds(80*(qs.qtd.length+1), 50, 900, 700);
        texto.append(" q's = " + Arrays.toString(qs.qtd));  
        texto.append("\n simbolos = " + Arrays.toString(simbs));
        texto.append("\n TRANSIÇÕES:");
        for (Transicao p : list) {
		String listaComoString = p.toString();
                texto.append("\n " + listaComoString);
        }
        texto.append("\n Inicial = " + Arrays.toString(inicio));
        texto.append("\n Final = " + Arrays.toString(fim));
        panel.add(texto);
        panel.repaint();
    }               
}
