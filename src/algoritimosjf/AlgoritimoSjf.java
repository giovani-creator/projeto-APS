/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package algoritimosjf;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Scanner;

public class AlgoritimoSjf {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
 Scanner scanner = new Scanner(System.in);

   // declaracao de variaveis
    int N, entrada;
  double tempoExecucao, tempoEspera, turnaround;
  ArrayList processos, chegada, cpchegada = new ArrayList(), duracoes;
  int[] temposFinais = new int[1], temposIniciais = new int[1];
  int idProcessoAtual;
  String ordemExecucao = "", formato, saida;
  int contTeste = 0;
  DecimalFormat decimal = new DecimalFormat("0.00");  
        
    System.out.println("quantos processos deseja armazenar?");
    N= scanner.nextInt();
    
    while (N != 0) {
   contTeste++;
   // inicializacao de variaveis
   processos = new ArrayList();
   chegada = new ArrayList();
   duracoes = new ArrayList();
   temposFinais = new int[N];
   temposIniciais = new int[N];
 
    
   for (int i = 1; i <= N; i++) {
    System.out.println(" tempo de chegada do  p" + (i));
      entrada = scanner.nextInt();
     chegada.add(entrada);
     System.out.println("tempo de burst do p" + (i));
     entrada = scanner.nextInt();
     duracoes.add(entrada);
   }
   
   cpchegada = (ArrayList) duracoes.clone();
   int execucao;
   int qteprocessos = N;
   int tempoAtual = (int) chegada.get(0);
   
   while (qteprocessos > 0) {
    // percorre ingressos para achar processos que ingressam nesse
    // tempo
    processos = new ArrayList();
    for (int i = 0; i < N; i++) {
     if ((int) chegada.get(i) != -1 && (int) chegada.get(i) <= tempoAtual){
      processos.add(i);
     }
    }
    // assumindo que o primeiro da lista eh o de menor duracao
    if (processos.isEmpty()) {
     tempoAtual++;
    } else {
     execucao = (int) processos.get(0);
      for (int i = 0; i < processos.size(); i++) {
      idProcessoAtual = (int) processos.get(i);
      // se a duracao do processo atual for menor do que a
      // menor
      // duracao
      // ja encontrada
      if ((int) duracoes.get(idProcessoAtual) < (int) duracoes
        .get(execucao)) {
       // entao alteramos o processo que vai executar
       execucao = (int) processos.get(i);
      }
     }
    temposIniciais[execucao] = tempoAtual;
     tempoAtual += (int) duracoes.get(execucao);
     temposFinais[execucao] = tempoAtual;
     chegada.set(execucao, -1);
     ordemExecucao += "p" + (execucao + 1) + " ";
     qteprocessos --;
    } 
   }
   // calculo tempo de execucao e tempo de espera
   tempoExecucao = 0;
   tempoEspera = 0;
   for (int i = 0; i < N; i++) {
    tempoExecucao += temposFinais[i] - (int) cpchegada.get(i);
    tempoEspera += temposIniciais[i] - (int) cpchegada.get(i);
   }
   System.out.println("Processamento - parte" + contTeste);
   for(int i=0; i< N; i++){
      turnaround =(int) temposFinais[i] - (int) cpchegada.get(i);
       formato = decimal.format(turnaround);
       saida = "|Turnaround| P" + i + ": " + formato + "ms";
       saida = saida.replace(".", ",");
       System.out.println(saida); 
   }
   
   
   
   
   
   
   
   tempoExecucao = tempoExecucao / N;
   tempoEspera = tempoEspera / N;
   
    formato = decimal.format(tempoExecucao);
   saida = "\n Tempo medio de espera: " + formato + "s";
   saida = saida.replace(".", ",");
   System.out.println(saida);

 /*/  formato = decimal.format(tempoEspera);
   saida = "Tempo medio de espera: " + formato + "s";
   saida = saida.replace(".", ",");
   System.out.println(saida);
  /*/
   System.out.println("ORDEM DE EXECUCAO: "+ordemExecucao);
   System.out.println();
   System.out.println("Deseja armazenar quantos processos?");
   N = scanner.nextInt();
    }
    }
    
}
