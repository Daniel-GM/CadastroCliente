package view;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CadastroCliente extends JFrame implements ActionListener {
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
    private JPanel jpTable = new JPanel();
    /*TITULOS*/
    private String[] titulos = {"ID", "Nome", "Email"};
    private Object[][] dados = {};
    /*tabela*/
    private JTable table = new JTable(dados,titulos);
    JScrollPane tabela = new JScrollPane(table);

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
        jpTable.setLayout(new GridBagLayout());


        gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 0,0);
        gbc.anchor = GridBagConstraints.WEST;

        itensTela();
    }

    private void itensTela() {
        insereIdNome();
        insereTelefone();
        insereEmail();
        insereBotoes();
        insereTabela();

        add(jpIdentificacao);
        add(jpTelefone);
        add(jpEmail);
        add(jpBotao);
        add(jpTable);
    }

    private void insereIdNome() { /*Adicionando a parte de ID e Nome no Frame*/
        gbc.gridx = 0;
        gbc.gridy = 0;
        jpIdentificacao.add(id, gbc);
        coluna();
        jpIdentificacao.add(nome, gbc);
        linha();
        jpIdentificacao.add(txtId, gbc);
        coluna();
        jpIdentificacao.add(txtNome, gbc);
    }

    private void insereTelefone() { /*Adicionando a parte de Telefone no Frame*/
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
    }

    private void insereEmail() { /*Adicionando a parte de Email no Frame*/
        linha();
        jpEmail.add(email, gbc);
        linha();
        jpEmail.add(txtEmail, gbc);
    }

    private void insereBotoes() { /*Adicionando os botoes no Frame*/
        /*botao de INSERIR*/
        linha();
        botaoInserir.addActionListener(e->{
            System.out.println("Inserir");
        });
        jpBotao.add(botaoInserir, gbc);
        /*botao de REMOVER*/
        coluna();
        botaoRemover.addActionListener(e->{
            System.out.println("Remover");
        });
        jpBotao.add(botaoRemover, gbc);
        /*botao de ALTERAR*/
        coluna();
        botaoAlterar.addActionListener(e->{
            System.out.println("Alterar");
        });
        jpBotao.add(botaoAlterar, gbc);
        /*botao de CONFIRMAR*/
        coluna();
        botaoConfirmar.addActionListener(e->{
            System.out.println("Confirmar");
        });
        jpBotao.add(botaoConfirmar, gbc);
        /*botao de CANCELAR*/
        coluna();
        botaoCancelar.addActionListener(e->{
            System.out.println("Cancelar");
        });
        jpBotao.add(botaoCancelar, gbc);
        /*botao de SAIR*/
        coluna();
        botaoSair.addActionListener(e->{
            System.out.println("Sair");
        });
        gbc.insets = new Insets(5, 70, 0,0);
        jpBotao.add(botaoSair, gbc);
    }

    private void insereTabela() { /*Adicionando a Tabela no Frame*/
        linha();
        gbc.insets = new Insets(0, 5, 0,0);
        tabela.setPreferredSize(new Dimension(553,250));
        jpTable.add(tabela, gbc);
    }

    private void linha(){ /*Metodo para ordenar os itens do Frame (quebra de linha | \n)*/
        gbc.gridy = gbc.gridy + 1;
        gbc.gridx = 0;
    }
    private void coluna() { /*Metodo para ordenar os itens do Frame (passar para a proxima coluna | \t)*/
        gbc.gridx = gbc.gridx + 1;
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {    }
}
