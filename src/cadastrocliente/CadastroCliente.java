package cadastrocliente;
import javax.swing.*;
import java.awt.*;
public class CadastroCliente extends JFrame {
    /*POSICAO*/
    GridBagConstraints gbc = null;
    /*NOME*/
    private JLabel id = new JLabel("ID:");
    private JLabel nome = new JLabel("Nome:");
    private JTextField txtId = new JTextField(3);
    private JTextField txtNome = new JTextField(46);
    /*TELEFONES*/
    private JLabel telResidencial = new JLabel("Tel. Residencial:");
    private JLabel telComercial = new JLabel("Tel. Comercial:");
    private JLabel telCelular = new JLabel("Tel. Celular:");
    private JTextField txtResidencial = new JTextField(16);
    private JTextField txtComercial = new JTextField(16);
    private JTextField txtCelular = new JTextField(16);
    /*E-MAIL*/
    private JLabel email = new JLabel("E-mail:");
    private JTextField txtEmail = new JTextField(50);
    /*BOTOES*/
    private JButton botaoInserir = new JButton("Inserir");
    private JButton botaoRemover = new JButton("Remover");
    private JButton botaoAlterar = new JButton("Alterar");
    private JButton botaoConfirmar = new JButton("Confirmar");
    private JButton botaoCancelar = new JButton("Cancelar");
    private JButton botaoSair = new JButton("Sair");
    /*JPanel*/
    private JPanel jpIdentificacao = new JPanel();
    private JPanel jpTelefone = new JPanel();
    private JPanel jpEmail = new JPanel();
    private JPanel jpBotao = new JPanel();


    public CadastroCliente() throws HeadlessException{
        setTitle("Cadastro de cliente");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600,500);
        setLocationRelativeTo(null);
        setVisible(true);

        setLayout(new FlowLayout(FlowLayout.LEFT));
        jpIdentificacao.setLayout(new GridBagLayout());
        jpTelefone.setLayout(new GridBagLayout());
        jpEmail.setLayout(new GridBagLayout());
        jpBotao.setLayout(new GridBagLayout());


        gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 0,0);
        gbc.anchor = GridBagConstraints.WEST;

        gbc.gridx = 0;
        gbc.gridy = 0;
        jpIdentificacao.add(id, gbc);
        coluna();
        jpIdentificacao.add(nome, gbc);
        linha();
        jpIdentificacao.add(txtId, gbc);
        coluna();
        jpIdentificacao.add(txtNome, gbc);
        linha();
        jpTelefone.add(telResidencial, gbc);
        coluna();
        jpTelefone.add(telComercial, gbc);
        coluna();
        jpTelefone.add(telCelular, gbc);
        linha();
        jpTelefone.add(txtResidencial, gbc);
        coluna();
        jpTelefone.add(txtComercial, gbc);
        coluna();
        jpTelefone.add(txtCelular, gbc);
        linha();
        jpEmail.add(email, gbc);
        linha();
        jpEmail.add(txtEmail, gbc);
        linha();
        jpBotao.add(botaoInserir, gbc);
        coluna();
        jpBotao.add(botaoRemover, gbc);
        coluna();
        jpBotao.add(botaoAlterar, gbc);
        coluna();
        jpBotao.add(botaoConfirmar, gbc);
        coluna();
        jpBotao.add(botaoCancelar, gbc);
        coluna();
        gbc.insets = new Insets(5, 70, 0,0);
        jpBotao.add(botaoSair, gbc);




        add(jpIdentificacao);
        add(jpTelefone);
        add(jpEmail);
        add(jpBotao);
    }

    private void linha(){
        gbc.gridy = gbc.gridy + 1;
        gbc.gridx = 0;
    }
    private void coluna() {
        gbc.gridx = gbc.gridx + 1;
    }
}
