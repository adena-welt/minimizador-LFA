package program;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.swing.JOptionPane;
import static program.Gui.text1;
import static program.Gui.text2;

public class Program {

    public static void main() {
        String path = "/home/adena/Documentos/automato2.dat";
		
	List<Transicao> list = new ArrayList<Transicao>();
        String[] inicio = null, fim = null, simbs = null;
        String aux=null, auxSim=null;
        int cont=0, cont2=0;
        Qs qs = new Qs();
		
	try (BufferedReader br = new BufferedReader(new FileReader(path))) {
	
            String line = br.readLine();
            qs.qtd = line.split(",");
            line = br.readLine();
            simbs = line.split(",");
            
            line = br.readLine();
            while (!">".equals(line.substring(0,1))) {
				
		String[] vect = line.split(",");
		String origem = vect[0];
		String simbolo = vect[1];
		String destino = vect[2];
				
		Transicao prod = new Transicao(origem, simbolo, destino);
		list.add(prod);
		
                if(cont2==0){
                    aux=origem;
                    auxSim=simbolo;
                }
                
                if(origem.equals(aux)){
                    cont++;
                    if(cont>1 && auxSim.equals(simbolo)){
                        JOptionPane.showMessageDialog(null,"O autômato não pode ser minimizado.\n"
                                + "De cada estado não podem partir múltiplas transições com um mesmo símbolo.");
                        System.exit(0);
                    }
                }
                else
                    cont=1;
                
                aux = origem; auxSim = simbolo;
                
                if(cont>2){
                    JOptionPane.showMessageDialog(null, "O autômato não pode ser minimizado.\n"
                            + "De cada estado não podem partir múltiplas transições com um mesmo símbolo.");
                    System.exit(0);
                }
                
		line = br.readLine();
                cont2++;
            }	
            
            inicio = line.split(","); 
            line = br.readLine();
            fim = line.split(","); 
            
            text2.append("\n q's = " + Arrays.toString(qs.qtd));
            
            text2.append("\n simbolos = " + Arrays.toString(simbs));
           
            text2.append("\n TRANSIÇÕES:");
            
            for (Transicao p : list) {
		String listaComoString = p.toString();
                text2.append("\n " + listaComoString);
            }
            
	}
	catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
	}
        
        text2.append("\n Inicio = " + Arrays.toString(inicio));
        text2.append("\n Fim = " + Arrays.toString(fim));

        qs.qsIniciaisFinais(inicio, fim, qs);
        
        char mat[][]=new char[50][50];
        Passo1(qs, mat); 
        Passo2(qs, mat, simbs, list);
    }
    
    public static void Passo1(Qs qs, char mat[][]){
        for(int i=1; i<qs.qtd.length; i++){
            for(int j=0; j<qs.qtd.length-1; j++){
                if(j<i){
                    if(qs.ehfinal[i]==qs.ehfinal[j])
                        mat[i][j] = 'O';
                    else
                        mat[i][j]='X';
                }
            }
        }
    }
    
    public static void Passo2(Qs qs, char mat[][], String[] simbs, List<Transicao> list){
        String i0=null, i1=null, j0=null, j1=null;
        for(int i=1; i<qs.qtd.length; i++){
            for(int j=0; j<qs.qtd.length-1; j++){
                if(mat[i][j]=='O'){
                    for (Transicao p : list) { 
                        if(qs.qtd[i].equals(p.getOrigem()) && simbs[0].equals(p.getSimbolo()))
                            i0 = p.getDestino();
                        
                        if(qs.qtd[j].equals(p.getOrigem()) && simbs[0].equals(p.getSimbolo()))
                            j0 = p.getDestino();
                            
                        if(qs.qtd[i].equals(p.getOrigem()) && simbs[1].equals(p.getSimbolo()))
                            i1 = p.getDestino();
                        
                        if(qs.qtd[j].equals(p.getOrigem()) && simbs[1].equals(p.getSimbolo()))
                            j1 = p.getDestino();
                        
                    }
                    
                    if(Equivalente(i0, j0, qs) == 0 || Equivalente(i1, j1, qs) == 0)
                        mat[i][j]='Ø';
                    else
                        Passo3(qs, mat, simbs, list);
                }
            }
        }
        Imprime(mat, qs);
    }
    
    public static void Passo3(Qs qs, char mat[][], String[] simbs, List<Transicao> list){
        
        String alf=simbs[0], alf2=simbs[1];
        String a, b;
        for(int i=1; i<qs.qtd.length; i++){
            for(int j=0; j<qs.qtd.length-1; j++){
                if(mat[i][j]=='O'){
                    a=Verifica(qs, mat, simbs,list,i, alf, alf);
                    b=Verifica(qs, mat, simbs,list,j, alf, alf);
                    if(Equivalente(a, b, qs) == 0)
                        mat[i][j]='Ø';
                    a=Verifica(qs, mat, simbs,list,i, alf, alf2);
                    b=Verifica(qs, mat, simbs,list,j, alf, alf2);
                    if(Equivalente(a, b, qs) == 0)
                        mat[i][j]='Ø';
                    a=Verifica(qs, mat, simbs,list,i, alf2, alf);
                    b=Verifica(qs, mat, simbs,list, j, alf2, alf);
                    if(Equivalente(a, b, qs) == 0)
                        mat[i][j]='Ø';
                    a=Verifica(qs, mat, simbs,list,i, alf2, alf2);
                    b=Verifica(qs, mat, simbs,list,j, alf2, alf2);
                    if(Equivalente(a, b, qs) == 0)
                        mat[i][j]='Ø';
                }
            }
        }
    }
    
    public static String Verifica(Qs qs, char mat[][], String[] simbs, List<Transicao> list, int i, String alf, String alf2){
        String a=null, b=null;
        for (Transicao p : list) { 
            if(qs.qtd[i].equals(p.getOrigem()) && alf.equals(p.getSimbolo())){
                a = p.getDestino();
            }
        }
        for (Transicao p : list) { 
            if(a.equals(p.getOrigem()) && alf2.equals(p.getSimbolo())){
                b = p.getDestino();
            }
        }
        return b;
    }
    
    public static int Equivalente(String i0, String j0, Qs qs){
        int lin=0, col=0;
        for(int i=0; i<qs.qtd.length; i++){
            if(qs.qtd[i].equals(i0))
                lin=qs.ehfinal[i];
            if(qs.qtd[i].equals(j0))
                col=qs.ehfinal[i];
        }
        
        if(lin==col)
            return 1;
        return 0;
    }
    
    public static void Imprime(char mat[][], Qs qs){
        text1.append("\n");
        for(int i=1; i<qs.qtd.length; i++){
            for(int j=0; j<qs.qtd.length-1; j++){
                if(j==0){
                    text1.append(" " + qs.qtd[i] + " ");
                }
                text1.append(mat[i][j]+ "  ");
            }
            text1.append("\n");
        }
        text1.append("   ");
        for(int j=0; j<qs.qtd.length-1; j++){
            text1.append(" " + qs.qtd[j]);
        }
    }
}
