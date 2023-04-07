package program;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.swing.JOptionPane;

public class Program {

    public static void main(String[] args) {
        String path = "/home/adena/Documentos/automato3.dat";//localiza o arquivo
		
	List<Transicao> list = new ArrayList<>();
        String[] inicio = null, fim = null, simbs = null;
        String aux = null, auxSim = null;
        int cont = 0, cont2 = 0;
        Qs qs = new Qs();
		
	try (BufferedReader br = new BufferedReader(new FileReader(path))) {    //leitura do arquivo
	
            String line = br.readLine();
            qs.qtd = line.split(",");   //a primeira linha do arquivo é jogada em um vetor que guada a quantidade de "Q's"
            line = br.readLine();
            simbs = line.split(",");    //o segunda linha é jogada no vetor de simbolos
            
            line = br.readLine();
            while (!">".equals(line.substring(0,1))) {  //le o arquivo ate chegar em >
				
		String[] vect = line.split(",");    //cada linha é divida em origem, simbolo e destino
		String origem = vect[0];
		String simbolo = vect[1];
		String destino = vect[2];
				
		Transicao elementos = new Transicao(origem, simbolo, destino);  //cria um elemento da classe transicao
		list.add(elementos);    //adiciona o elemento na lista
		
                if(cont2 == 0){       //verifica se o automato possui multiplas transicoes ou 2 com o mesmo simbolo
                    aux = origem;
                    auxSim = simbolo;
                }
                
                if(origem.equals(aux)){     //caso possua exibe a mensagem de que ele nao pode ser minimizado
                    cont++;
                    if(cont > 1 && auxSim.equals(simbolo)){
                        JOptionPane.showMessageDialog(null, "O autômato não pode ser minimizado."+
                            "De cada estado não podem partir múltiplas transições com um mesmo símbolo.");
                        System.exit(0);
                    }
                }
                else
                    cont = 1;
                
                aux = origem; auxSim = simbolo;
                
                if(cont > 2){
                    JOptionPane.showMessageDialog(null, "O autômato não pode ser minimizado."+
                        "De cada estado não podem partir múltiplas transições com um mesmo símbolo.");
                    System.exit(0);
                }
                
		line = br.readLine();
                cont2++;
            }	
            
            inicio = line.split(",");   //a penultima linha é jogada no vetor inicio
            line = br.readLine();
            fim = line.split(",");      //a ultima linha é jogada no vetor fim
	}
        
	catch (IOException e) {
            JOptionPane.showMessageDialog(null,"Error: " + e.getMessage()); //erro na leitura do arquivo
            System.out.println("Error: " + e.getMessage());
            System.exit(0);
	}
        
        System.out.println("qs = " + Arrays.toString(qs.qtd));      //impressao das transições
            
        System.out.println("simbolos = " + Arrays.toString(simbs));
           
        System.out.println("TRANSIÇÕES:");
        for (Transicao p : list) {
            System.out.println(p);
        }
        System.out.println("Inicio = " + Arrays.toString(inicio));
        System.out.println("Fim = " + Arrays.toString(fim));

        qs.qsIniciaisFinais(inicio, fim, qs);   //funcao da classe "Qs" para definir quais são iniciais ou finais
        
        char mat[][] = new char[50][50];  //passo 1
        Passo2(qs, mat); 
        Passo3T1(qs, mat, simbs, list);
        
        Interface tela = new Interface();
        tela.imprime(mat, qs, simbs, list, inicio, fim);
    }
    
    public static void Passo2(Qs qs, char mat[][]){     //passo 2
        for(int i = 1; i < qs.qtd.length; i++){     //percorre a matriz
            for(int j = 0; j < qs.qtd.length-1; j++){
                if(j < i){
                    if(qs.ehfinal[i] == qs.ehfinal[j])    //caso os dois elementos sejam equivalentes
                        mat[i][j] = 'O';                //é adicionado um O na matriz
                    else
                        mat[i][j]='X';                  //caso não sejam é adicionado um X
                }
            }
        }
    }
    
    public static void Passo3T1(Qs qs, char mat[][], String[] simbs, List<Transicao> list){   //passo 3 T = 1
        String i0 = null, i1 = null, j0 = null, j1 = null;
        for(int i = 1; i < qs.qtd.length; i++){             //percorre a matriz
            for(int j = 0; j < qs.qtd.length-1; j++){
                if(mat[i][j] == 'O'){                     //caso ainda seja equivalente
                    for (Transicao p : list) {          //percorre a lista
                        if(qs.qtd[i].equals(p.getOrigem()) && simbs[0].equals(p.getSimbolo()))
                            i0 = p.getDestino();        //destino de i0 (primeiro elemento com simbolo 0)
                        
                        if(qs.qtd[j].equals(p.getOrigem()) && simbs[0].equals(p.getSimbolo()))
                            j0 = p.getDestino();        //destino de j0 (segundo elemento com o simbolo 0)
                            
                        if(qs.qtd[i].equals(p.getOrigem()) && simbs[1].equals(p.getSimbolo()))
                            i1 = p.getDestino();        //destino de i1 (primeiro elemento com o simbolo 1)
                        
                        if(qs.qtd[j].equals(p.getOrigem()) && simbs[1].equals(p.getSimbolo()))
                            j1 = p.getDestino();        //destino de j1 (segundo elemento com o simbolo 1)
                    }
                    
                    if(Equivalente(i0, j0, qs) == 0 || Equivalente(i1, j1, qs) == 0)
                        mat[i][j]='Ø';      //(verifica se algum deles não são equivalentes)
                    else
                        Passo3T2(qs, mat, simbs, list);     //caso seja equivalente chama o passo 3 t2
                }
            }
        }
        Imprime(mat, qs);
    }
    
    public static void Passo3T2(Qs qs, char mat[][], String[] simbs, List<Transicao> list){ //passo 3 T = 2
        
        String alf = simbs[0], alf2 = simbs[1];
        String a, b;
        for(int i = 1; i < qs.qtd.length; i++){         //percorre a matriz
            for(int j = 0; j < qs.qtd.length-1; j++){
                if(mat[i][j] == 'O'){                 //caso a posição esteja marcada como equivalente
                    a = Verifica(qs, mat, simbs,list,i, alf, alf);        //chama a função verifica com i como alfabeto 00
                    b = Verifica(qs, mat, simbs,list,j, alf, alf);      //chama a função verifica com j como alfabeto 00
                    if(Equivalente(a, b, qs) == 0)                    //verifica se a e b são equivalentes
                        mat[i][j]='Ø';
                    
                    a = Verifica(qs, mat, simbs,list,i, alf, alf2);           //i com alfabeto 01
                    b = Verifica(qs, mat, simbs,list,j, alf, alf2);         //j com alfabeto 01
                    if(Equivalente(a, b, qs) == 0)
                        mat[i][j]='Ø';
                    
                    a = Verifica(qs, mat, simbs,list,i, alf2, alf);     //i com alfabeto 10
                    b = Verifica(qs, mat, simbs,list, j, alf2, alf);  //j com alfabeto 10
                    if(Equivalente(a, b, qs) == 0)
                        mat[i][j]='Ø';
                    
                    a = Verifica(qs, mat, simbs,list,i, alf2, alf2);        //i com alfabeto 11
                    b = Verifica(qs, mat, simbs,list,j, alf2, alf2);      //j com alfabeto 11
                    if(Equivalente(a, b, qs) == 0)
                        mat[i][j] = 'Ø';                               //caso não forem equivalentes marca com o simbolo Ø
                }
            }
        }
    }
    
    public static String Verifica(Qs qs, char mat[][], String[] simbs, List<Transicao> list, int i, String alf, String alf2){
        String a = null, b = null;
        for (Transicao p : list) {  
            if(qs.qtd[i].equals(p.getOrigem()) && alf.equals(p.getSimbolo())){  //vê pra onde vai a primeira transição
                a = p.getDestino();
            }
        }
        for (Transicao p : list) { 
            if(a.equals(p.getOrigem()) && alf2.equals(p.getSimbolo())){  //vê pra onde vai a segunda transição
                b = p.getDestino();
            }
        }
        return b;       //retorna a segunda transição
    }
    
    public static int Equivalente(String i0, String j0, Qs qs){
        int lin = 0, col = 0;
        for(int i = 0; i < qs.qtd.length; i++){     //percorre o vetor de qs, salva a posição do i e do j e verifica se sao finais
            if(qs.qtd[i].equals(i0))
                lin = qs.ehfinal[i];
            if(qs.qtd[i].equals(j0))
                col = qs.ehfinal[i];
        }
        
        if(lin == col)    //caso os dois sejam equivalentes retorna 1
            return 1;
        return 0;       //se não forem equivalentes retorna 0
    }
    
    public static void Imprime(char mat[][], Qs qs){    //impressão da matriz
        System.out.println("");
        for(int i = 1; i < qs.qtd.length; i++){             
            for(int j = 0; j<qs.qtd.length-1; j++){
                if(j == 0){
                    System.out.print(qs.qtd[i] + " ");
                }
                System.out.print(mat[i][j]+ "\t");
            }
        System.out.println("");
        }
        System.out.print("   ");
        for(int j = 0; j < qs.qtd.length-1; j++){
            System.out.print(qs.qtd[j] + "\t");
        }
        System.out.println("\n");
    }
}