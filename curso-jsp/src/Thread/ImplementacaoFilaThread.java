package Thread;

import java.util.Iterator;
import java.util.concurrent.ConcurrentLinkedQueue;

public class ImplementacaoFilaThread extends Thread {
    private static ConcurrentLinkedQueue<ObjetoFilaThread> pilha_filha = new ConcurrentLinkedQueue<ObjetoFilaThread>();

    public static void add(ObjetoFilaThread objetoFilaThread) {
        pilha_filha.add(objetoFilaThread);
    }

    public void run() {

        System.out.println("Fila rodando...");

        while (true) {

            synchronized (pilha_filha) {// bloquear o acesso a esta lista por outros processos
            
                //Iterator iteracao = pilha_filha.iterator();
                Iterator<ObjetoFilaThread> iteracao = pilha_filha.iterator();
                
                while (iteracao.hasNext()) {// Enquanto conter dados na lista irá processar
                    ObjetoFilaThread processar = (ObjetoFilaThread) iteracao.next();// pega objeto atual
                    // processar 10 mil notas fiscais
                    // gerar uma lista enorme de PDF
                    // gerar um envio em massa de email

                    System.out.println("-----------------------");
                    System.out.println(processar.getEmail());
                    System.out.println(processar.getNome());

                    iteracao.remove();

                    try {
                        Thread.sleep(100);// dar um tempo pra descarga de memoria
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }

            try {
                Thread.sleep(1000);// processou toda a lista, dar um tempo pra descarga de memoria
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
