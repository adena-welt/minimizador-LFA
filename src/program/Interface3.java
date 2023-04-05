package program;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import static javax.swing.WindowConstants.EXIT_ON_CLOSE;
import javax.swing.border.EmptyBorder;

public class Interface3 extends javax.swing.JFrame {

    public Interface3() {
        initComponents();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jSlider1 = new javax.swing.JSlider();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jSlider1.setMinimum(41);
        jSlider1.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                jSlider1MouseDragged(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jSlider1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 800, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jSlider1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(774, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
    
    int cont=1, cont2=1, cont3=1;
    JButton [] qs = new JButton[300];
    JTextArea texto = new JTextArea();
    
    private void jSlider1MouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jSlider1MouseDragged
        int x = jSlider1.getValue()+20, y=(jSlider1.getValue()+10) * (cont3), pos = cont3;
        for(int i=1; i<(cont3); i++){
            qs[i].setBounds(10, i*(jSlider1.getValue()+10),jSlider1.getValue(), jSlider1.getValue());
            Font fonte = new Font("Arial", Font.PLAIN, jSlider1.getValue()/5);
            qs[i].setFont(fonte);
            qs[i].setBorder(new EmptyBorder(10, 10, 10, 10));
        }
        for(int i=1; i<cont3; i++){
            for(int j=0; j<(cont3-1); j++){
                if(j<i){
                    qs[pos].setBounds((jSlider1.getValue()+20)+j*(jSlider1.getValue()+10), i*(jSlider1.getValue()+10),
                            jSlider1.getValue(), jSlider1.getValue());
                    Font fonte = new Font("Arial", Font.PLAIN, jSlider1.getValue()/5);
                    qs[pos].setFont(fonte);
                    qs[i].setBorder(new EmptyBorder(10, 10, 10, 10));
                pos++;
                }
            }
        }
        for(int i=(cont-cont3+1); i<cont; i++){
            qs[i].setBounds(x, y, jSlider1.getValue(), jSlider1.getValue());
            x+=jSlider1.getValue()+10;
            Font fonte = new Font("Arial", Font.PLAIN, jSlider1.getValue()/5);
            qs[i].setFont(fonte);
            qs[i].setBorder(new EmptyBorder(10, 10, 10, 10));
        }
        texto.setBounds((jSlider1.getValue()+10)*(cont3+1), 50, 900, 700);
        Font fonte = new Font("Arial", Font.BOLD, jSlider1.getValue()/4);
        texto.setFont(fonte);
    }//GEN-LAST:event_jSlider1MouseDragged

    public void imprimeMat(char mat[][], Qs vetqs){
        Font fonte = new Font("Arial", Font.BOLD, 12);
        for(int i=1; i<vetqs.qtd.length; i++){
            if(vetqs.ehfinal[i]==1 && vetqs.inicial[i]==1){
                qs[cont] = new JButton("→*" + vetqs.qtd[i]);
                setLayout(null);
                qs[cont].setBounds(10, i*80,70,70);
                qs[cont].setFont(fonte);
                qs[cont].setFocusable(false);
                add(qs[cont]);
            }
            else if(vetqs.ehfinal[i]==1){
                qs[cont] = new JButton("*" + vetqs.qtd[i]);
                setLayout(null);
                qs[cont].setBounds(10, i*80,70,70);
                qs[cont].setFont(fonte);
                qs[cont].setFocusable(false);
                add(qs[cont]);
            }
            else if(vetqs.inicial[i]==1){
                qs[cont] = new JButton("→" + vetqs.qtd[i]);
                setLayout(null);
                qs[cont].setBounds(10, i*80,70,70);
                qs[cont].setFont(fonte);
                qs[cont].setFocusable(false);
                add(qs[cont]);
            }
            else{
                qs[cont] = new JButton(vetqs.qtd[i]);
                setLayout(null);
                qs[cont].setBounds(10, i*80,70,70);
                qs[cont].setFont(fonte);
                qs[cont].setFocusable(false);
                add(qs[cont]);
            }
            cont++;     
        }
        
        for(int i=1; i<vetqs.qtd.length; i++){
            for(int j=0; j<vetqs.qtd.length-1; j++){
                if(j<i){
                    configuraBotao(90+j*80, i*80, i, j, mat);
                    cont++; cont2++;
                }
            }
        }
        
        int xq=90, yq=80*vetqs.qtd.length;
        for(int j=0; j<vetqs.qtd.length-1; j++){
            if(vetqs.ehfinal[j]==1 && vetqs.inicial[j]==1){
                qs[cont] = new JButton("→*" + vetqs.qtd[j]);
                setLayout(null);
                qs[cont].setBounds(xq, yq,70,70);
                qs[cont].setFont(fonte);
                qs[cont].setFocusable(false);
                add(qs[cont]);
            }
            else if(vetqs.ehfinal[j]==1){
                qs[cont] = new JButton("*" + vetqs.qtd[j]);
                setLayout(null);
                qs[cont].setBounds(xq, yq,70,70);
                qs[cont].setFont(fonte);
                qs[cont].setFocusable(false);
                add(qs[cont]);
            }
            else if(vetqs.inicial[j]==1){
                qs[cont] = new JButton("→" + vetqs.qtd[j]);
                setLayout(null);
                qs[cont].setBounds(xq, yq,70,70);
                qs[cont].setFont(fonte);
                qs[cont].setFocusable(false);
                add(qs[cont]);
            }
            else{
                qs[cont] = new JButton(vetqs.qtd[j]);
                setLayout(null);
                qs[cont].setBounds(xq, yq,70,70);
                qs[cont].setFont(fonte);
                qs[cont].setFocusable(false);
                add(qs[cont]);
            }
            cont++; cont3++;
            xq+=80;
        }
    
        setTitle("Autômato Minimizado");
        setSize(1000,700);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
    }
    
    private void configuraBotao(int x, int y, int i, int j, char mat[][]){       
        Font fonte = new Font("Arial", Font.BOLD, 15);
        qs[cont] = new JButton(Character.toString(mat[i][j]));
        qs[cont].setLayout(null);
        qs[cont].setBackground(Color.GRAY);
        qs[cont].setFont(fonte);
        qs[cont].setBounds(x, y, 70, 70);
        qs[cont].setFocusable(false);
        add(qs[cont]);
    }
    
    public void imprimeTransicoes(Qs qs, String[] simbs, List<Transicao> list, String[] inicio, String[] fim){
        Font fonte = new Font("Arial", Font.BOLD, 18);
        texto.setBackground(new java.awt.Color(0,0,0,0));
        texto.setFont(fonte);
        texto.setBounds(80*(qs.qtd.length+1), 50, 900, 700);
        texto.append(" q's = " + Arrays.toString(qs.qtd));  
        texto.append("\n simbolos = " + Arrays.toString(simbs));
        texto.append("\n TRANSIÇÕES:");
        add(texto);
        for (Transicao p : list) {
		String listaComoString = p.toString();
                texto.append("\n " + listaComoString);
        }
        texto.append("\n Inicial = " + Arrays.toString(inicio));
        texto.append("\n Final = " + Arrays.toString(fim));
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JSlider jSlider1;
    // End of variables declaration//GEN-END:variables
}
