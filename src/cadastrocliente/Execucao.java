package cadastrocliente;

import view.CadastroCliente;

import javax.swing.*;

public class Execucao {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new CadastroCliente();
            }
        });
    }
}
