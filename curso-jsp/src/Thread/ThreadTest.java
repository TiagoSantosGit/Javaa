package Thread;

import javax.swing.JOptionPane;

public class ThreadTest {

    public static void main(String[] args) {

        new Thread() {// thread 1
            public void run() {
                for (int pos = 0; pos < 10; pos++) {
                    try {
                        Thread.sleep(2000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println("Executando rotina " + pos);
                }
            };
        }.start(); // start liga a thread que fica processando paralelamente por tras

        new Thread() {// thread 2
            public void run() {
                for (int pos = 0; pos < 20; pos++) {
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println("Executando denovo " + pos);
                }
            };
        }.start(); // start liga a thread que fica processando paralelamente por tras

        Thread threar2 = new Thread(thread1);
        threar2.start();

        System.out.println("Fim");
        JOptionPane.showMessageDialog(null, "Sistema continua executando para o usuario");
    }

    private static Runnable thread1 = new Runnable() {

        @Override
        public void run() {
            new Thread() {// thread 3
                public void run() {
                    for (int pos = 0; pos < 20; pos++) {
                        try {
                            Thread.sleep(1500);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        System.out.println("Executando Runnable " + pos);
                    }
                };
            }.start(); // start liga a thread que fica processando paralelamente por tras
        }
    };

}
