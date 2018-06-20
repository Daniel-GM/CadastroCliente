package view;
import com.github.icarohs7.unoxlib.tables.EditableTableModel;
import com.github.icarohs7.unoxlib.tables.ScrollTable;
import model.bean.Cliente;
import model.dao.ClienteDAO;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class CadastroCliente extends JFrame implements ActionListener, MouseListener {
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
    /*AUXILIAR*/
    private int botaoClicado;

    public CadastroCliente() throws HeadlessException{
        setTitle("Cadastro de cliente");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600,390);
        setLocationRelativeTo(null);
        setVisible(true);

        botaoConfirmar.setEnabled(false); //desabilitando o botao
        botaoRemover.setEnabled(false); //desabilitando o botao
        botaoAlterar.setEnabled(false); //desabilitando o botao
        botaoCancelar.setEnabled(false); //desabilitando o botao
        txtId.setEnabled(false); //desabilitando o textField

        setLayout(new FlowLayout(FlowLayout.LEFT));
        jpIdentificacao.setLayout(new GridBagLayout());
        jpTelefone.setLayout(new GridBagLayout());
        jpEmail.setLayout(new GridBagLayout());
        jpBotao.setLayout(new GridBagLayout());
        jpTable.setLayout(new GridBagLayout());


        gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 0,0);
        gbc.anchor = GridBagConstraints.WEST;

        itensTela(); //metodo para inserir todos os componentes na JFrame
        bloqueiaTxt(); //desabilitando todos os textFields
    }

    private void itensTela() {
        insereIdNome(); //inserindo componentes relacionados a id e nome
        insereTelefone(); //inserindo componentes relacionados a telefone
        insereEmail(); //inserindo componentes relacionados email
        insereBotoes(); //inserindo componentes relacionados a botoes
        insereSeparador(); //inserindo componentes relacionados a separador
        insereTabela(); //inserindo componentes relacionados a tabela

        add(jpIdentificacao); //adicionando o JPanel que contém insereIdNome
        add(jpTelefone); //adicionando o JPanel que contém insereTelefone
        add(jpEmail); //adicionando o JPanel que contém insereEmail
        add(jpBotao); //adicionando o JPanel que contém insereBotoes
        add(jpSeparador); //adicionando o JPanel que contém insereSeparador
        add(jpTable); //adicionando o JPanel que contém insereTabela
    }

    /**
     * coluna(); serve para passar para a proxima coluna no GridBagLayout
     * linha(); serve para passar para a proxima linha no GridBagLayout
     *
     * txt(nome da label), é o textField referente a Label em questão. ex: txtId, txtNome, txtCelular
     */

    private void insereIdNome() { /*Adicionando a parte de ID e Nome no Frame*/
        gbc.gridx = 0; //iniciando a coluna na primeira posição
        gbc.gridy = 0; //iniciando a linha na primeira posição
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
        botaoInserir.addActionListener(e->{ //lambda para o ActionListener do botão
            limpaTxt();
            botaoClicado = 1;
            txtId.setText(Integer.toString(cId.idMax()+1));
            limpaBotao();
            desbloqueiaTxt();
            botaoConfirmar.setEnabled(true);
            botaoCancelar.setEnabled(true);
        });
        jpBotao.add(botaoInserir, gbc);
        /*botao de REMOVER*/
        coluna();
        botaoRemover.addActionListener(e->{ //lambda para o ActionListener do botão
            botaoClicado=2;
            limpaBotao();
            botaoConfirmar.setEnabled(true);
            botaoCancelar.setEnabled(true);
        });
        jpBotao.add(botaoRemover, gbc);
        /*botao de ALTERAR*/
        coluna();
        botaoAlterar.addActionListener(e->{ //lambda para o ActionListener do botão
            botaoClicado=3;
            limpaBotao();
            desbloqueiaTxt();
            botaoConfirmar.setEnabled(true);
            botaoCancelar.setEnabled(true);
        });
        jpBotao.add(botaoAlterar, gbc);
        /*botao de CONFIRMAR*/
        coluna();
        botaoConfirmar.addActionListener(e->{ //lambda para o ActionListener do botão
            if(botaoClicado == 1) {
                confirmaInserir();
            }else if(botaoClicado == 2){
                confirmaExcluir();
            }else if (botaoClicado == 3){
                confirmaAlterar();
            }else{
                JOptionPane.showMessageDialog(null,"Ocorreu um erro na confirmação da ação! Por favor entre em contato com o responsável");
            }

        });
        jpBotao.add(botaoConfirmar, gbc);
        /*botao de CANCELAR*/
        coluna();
        botaoCancelar.addActionListener(e->{ //lambda para o ActionListener do botão
            limpaBotao();
            limpaTxt();
            bloqueiaTxt();
            botaoInserir.setEnabled(true);
            botaoSair.setEnabled(true);
        });
        jpBotao.add(botaoCancelar, gbc);
        /*botao de SAIR*/
        coluna();
        botaoSair.addActionListener(e->{ //lambda para o ActionListener do botão
            System.exit(0);
        });
        gbc.insets = new Insets(-5, 70, 0, 0);
        jpBotao.add(botaoSair, gbc);
    }

    private void confirmaInserir() { //metodo para confirmar a inserção
        Cliente c = new Cliente();
        ClienteDAO dao = new ClienteDAO();
        c.setNome(txtNome.getText());
        c.setTelefoneResidencial(txtResidencial.getText());
        c.setTelefoneComercial(txtComercial.getText());
        c.setTelefoneCelular(txtCelular.getText());
        c.setEmail(txtEmail.getText());
        dao.create(c);
        readTable();
        limpaTxt();
        limpaBotao();
        bloqueiaTxt();
        botaoInserir.setEnabled(true);
        botaoSair.setEnabled(true);
    }

    private void confirmaExcluir(){ //metodo para confirmar a exclusão
        Cliente c = new Cliente();
        ClienteDAO dao = new ClienteDAO();
        c.setId(Integer.parseInt(txtId.getText()));
        dao.delete(c);
        readTable();
        limpaTxt();
        limpaBotao();
        bloqueiaTxt();
        botaoInserir.setEnabled(true);
        botaoSair.setEnabled(true);
    }

    private void confirmaAlterar(){ //metodo para confirmar a alteração
        Cliente c = new Cliente();
        ClienteDAO dao = new ClienteDAO();
        c.setNome(txtNome.getText());
        c.setTelefoneResidencial(txtResidencial.getText());
        c.setTelefoneComercial(txtComercial.getText());
        c.setTelefoneCelular(txtCelular.getText());
        c.setEmail(txtEmail.getText());
        c.setId(Integer.parseInt(txtId.getText()));
        dao.update(c);
        readTable();
        limpaTxt();
        limpaBotao();
        bloqueiaTxt();
        botaoInserir.setEnabled(true);
        botaoSair.setEnabled(true);
    }

    private void insereSeparador(){ //metodo para inserir a JLabel e o separador
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
        tabela.addMouseListener(this);
        JScrollPane scroll = tabela.getScrollableTable();
        scroll.setPreferredSize(new Dimension(553,80));
        readTable();
        jpTable.add(scroll, gbc);
    }

    private void readTable() { //metodo para fazer a leitura no banco de dados e retornar na tabela
        EditableTableModel modelo = (EditableTableModel) tabela.getModel();
        modelo.setAllRows(dados); //limpando a tabela
        ClienteDAO cdao = new ClienteDAO();
        for (Cliente c: cdao.read()){ //inserindo os dados na tabela
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
    private void limpaBotao(){ //metodo para desabilitar todos os botoes
        botaoInserir.setEnabled(false);
        botaoRemover.setEnabled(false);
        botaoAlterar.setEnabled(false);
        botaoCancelar.setEnabled(false);
        botaoConfirmar.setEnabled(false);
        botaoSair.setEnabled(false);
    }
    private void bloqueiaTxt(){ //metodo para bloquear edição no textField
        txtCelular.setEnabled(false);
        txtComercial.setEnabled(false);
        txtEmail.setEnabled(false);
        txtNome.setEnabled(false);
        txtResidencial.setEnabled(false);
    }
    private void desbloqueiaTxt(){ //metodo para permitir edição no textField
        txtCelular.setEnabled(true);
        txtComercial.setEnabled(true);
        txtEmail.setEnabled(true);
        txtNome.setEnabled(true);
        txtResidencial.setEnabled(true);
    }
    private void limpaTxt(){ //metodo para limpar os campos textField
        txtCelular.setText("");
        txtComercial.setText("");
        txtEmail.setText("");
        txtId.setText("");
        txtNome.setText("");
        txtResidencial.setText("");
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {    }

    @Override
    public void mouseClicked(MouseEvent mouseEvent) {
        if (mouseEvent.getClickCount() % 2 == 0) {
            Cliente c = new Cliente();
            ClienteDAO dao = new ClienteDAO();
            c.setId(Integer.parseInt((String) tabela.getValueAt(tabela.getSelectedRow(), 0)));
            dao.buscaDados(c);
            txtId.setText(Integer.toString(c.getId()));
            txtNome.setText(c.getNome());
            txtResidencial.setText(c.getTelefoneResidencial());
            txtComercial.setText(c.getTelefoneComercial());
            txtCelular.setText(c.getTelefoneCelular());
            txtEmail.setText(c.getEmail());
            botaoCancelar.setEnabled(false);
            botaoConfirmar.setEnabled(false);
            botaoAlterar.setEnabled(true);
            botaoRemover.setEnabled(true);
            botaoCancelar.setEnabled(true);
        }
    }
    @Override
    public void mousePressed(MouseEvent mouseEvent) {

    }

    @Override
    public void mouseReleased(MouseEvent mouseEvent) {

    }

    @Override
    public void mouseEntered(MouseEvent mouseEvent) {

    }

    @Override
    public void mouseExited(MouseEvent mouseEvent) {

    }
}