package view;
import com.github.icarohs7.unoxlib.tables.EditableTableModel;
import com.github.icarohs7.unoxlib.tables.ScrollTable;
import model.bean.Cliente;
import model.dao.ClienteDAO;

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
    /*JPANEL*/
    private JPanel jpIdentificacao = new JPanel();
    private JPanel jpTelefone = new JPanel();
    private JPanel jpEmail = new JPanel();
    private JPanel jpBotao = new JPanel();
    private JPanel jpTable = new JPanel();
    private JPanel jpSeparador = new JPanel();
    /*TITULOS*/
    private String[] titulos = {"ID", "Nome", "Email"};
    private Object[][] dados = {};
    /*TABELA*/
    private ScrollTable tabela = ScrollTable.ofMutableCells(dados,titulos);
    /*PEGAR O ULTIMO ID*/
    private ClienteDAO cId  = new ClienteDAO();

    public CadastroCliente() throws HeadlessException{
        setTitle("Cadastro de cliente");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600,390);
        setLocationRelativeTo(null);
        setVisible(true);

        botaoInserir.setEnabled(false);
        botaoRemover.setEnabled(false);
        botaoAlterar.setEnabled(false);
        txtId.setEnabled(false);
        txtId.setText(Integer.toString(cId.idMax()+1));

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
        insereSeparador();
        insereTabela();

        add(jpIdentificacao);
        add(jpTelefone);
        add(jpEmail);
        add(jpBotao);
        add(jpSeparador);
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
        gbc.insets = new Insets(10, 5, 15, 0);
        botaoInserir.addActionListener(e->{
            System.out.println("Inserir");
            Cliente c = new Cliente();
            ClienteDAO dao = new ClienteDAO();
            c.setNome(txtNome.getText());
            c.setTelefoneResidencial(txtResidencial.getText());
            c.setTelefoneComercial(txtComercial.getText());
            c.setTelefoneCelular(txtCelular.getText());
            c.setEmail(txtEmail.getText());
            dao.create(c);
        });
        jpBotao.add(botaoInserir, gbc);
        /*botao de REMOVER*/
        coluna();
        botaoRemover.addActionListener(e->{
            System.out.println("Remover");
            limpaBotao();
            limpaTxt();
            txtId.setText(Integer.toString(cId.idMax()+1));
        });
        jpBotao.add(botaoRemover, gbc);
        /*botao de ALTERAR*/
        coluna();
        botaoAlterar.addActionListener(e->{
            System.out.println("Alterar");
            limpaBotao();
        });
        jpBotao.add(botaoAlterar, gbc);
        /*botao de CONFIRMAR*/
        coluna();
        botaoConfirmar.addActionListener(e->{
            System.out.println("Confirmar");
            insereBotao();
        });
        jpBotao.add(botaoConfirmar, gbc);
        /*botao de CANCELAR*/
        coluna();
        botaoCancelar.addActionListener(e->{
            System.out.println("Cancelar");
            limpaBotao();
            limpaTxt();
            txtId.setText(Integer.toString(cId.idMax()+1));
        });
        jpBotao.add(botaoCancelar, gbc);
        /*botao de SAIR*/
        coluna();
        botaoSair.addActionListener(e->{
            System.out.println("Sair");
            System.exit(0);
        });
        gbc.insets = new Insets(-5, 70, 0, 0);
        jpBotao.add(botaoSair, gbc);
    }

    private void insereSeparador(){
        linha();
        gbc.insets = new Insets(10, 5, 0, 0);
        JLabel cc = new JLabel("Clientes cadastrados");
        jpSeparador.add(cc, gbc);
        coluna();
        JSeparator separador = new JSeparator();
        separador.setPreferredSize(new Dimension(427, 1));
        jpSeparador.add(separador, gbc);
    }
    
    private void insereTabela() { /*Adicionando a Tabela no Frame*/
        linha();
        gbc.insets = new Insets(0, 5, 0,0);
        tabela.setPreferredSize(new Dimension(553,80));
        readTable();
        jpTable.add(tabela.getScrollableTable(), gbc);
    }

    private void readTable() {
        EditableTableModel modelo = (EditableTableModel) tabela.getModel();
        ClienteDAO cdao = new ClienteDAO();
        for (Cliente c: cdao.read()){
            modelo.addRow(new String[]{Integer.toString(c.getId()),c.getNome(),c.getEmail()});
        }
    }

    private void linha(){ /*Metodo para ordenar os itens do Frame (quebra de linha | \n)*/
        gbc.gridy = gbc.gridy + 1;
        gbc.gridx = 0;
    }
    private void coluna() { /*Metodo para ordenar os itens do Frame (passar para a proxima coluna | \t)*/
        gbc.gridx = gbc.gridx + 1;
    }
    private void insereBotao(){
        botaoInserir.setEnabled(true);
        botaoRemover.setEnabled(true);
        botaoAlterar.setEnabled(true);
    }
    private void limpaBotao(){
        botaoInserir.setEnabled(false);
        botaoRemover.setEnabled(false);
        botaoAlterar.setEnabled(false);
    }
    private void limpaTxt(){
        txtCelular.setText("");
        txtComercial.setText("");
        txtEmail.setText("");
        txtId.setText("");
        txtNome.setText("");
        txtResidencial.setText("");
    }
    @Override
    public void actionPerformed(ActionEvent actionEvent) {    }
}
