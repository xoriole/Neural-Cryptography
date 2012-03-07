package ncrypt;

public class NCrypt{
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {

            @Override
            public void run() {
                new NCryptPanel().setVisible(true);
            }
        });

    }
}