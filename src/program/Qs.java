package program;

public class Qs {
    int[] inicial = new int[50];
    int ehfinal[] = new int[50];
    String[] qtd;
    
    public void inicializaQs(int inicial[], int ehfinal[], String[] qtd) {
		this.inicial = inicial;
		this.ehfinal = ehfinal;
		this.qtd = qtd;
    }
   
    public String[] getQtd() {
        return qtd;
    }
    
    public void qsIniciaisFinais(String[] inicio,String[] fim, Qs qs){
        
        inicio[0] = inicio[0].substring(1);
        fim[0] = fim[0].substring(1);

        for (String inicio1 : inicio) {
            for (int j = 0; j < qs.qtd.length; j++) {
                if (qs.qtd[j].equals(inicio1)) {
                    qs.inicial[j]=1;
                }
            }
        }
        for (String fim1 : fim) {
            for (int j = 0; j < qs.qtd.length; j++) {
                if (qs.qtd[j].equals(fim1)) {
                    qs.ehfinal[j]=1;
                }
            }
        }
    }
    
}
