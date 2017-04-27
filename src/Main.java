/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package POO;

/**
 *
 * @author Vinicius
 */
import java.awt.event.*;
import java.awt.*;
import java.io.*;
import javax.swing.*;
import javax.swing.JPopupMenu.*;

@SuppressWarnings({"serial"})
public class Main extends JFrame{
	
    protected JMenuBar barra;
    protected JMenu menu;
    protected JMenuItem itemNovo, itemSair, itemAbrir;
    protected Separator separator, separator2;
    protected JLabel label;
	
    public Main(){
        super();
        this.barra = new JMenuBar();
        this.menu = new JMenu("Arquivo");
        this.itemNovo = new JMenuItem();
        this.itemSair = new JMenuItem();
        this.itemAbrir = new JMenuItem();
        this.separator = new Separator();
        this.separator2 = new Separator();
        this.label = new JLabel( new ImageIcon("dolphin.gif") );

        setMinimumSize( new Dimension(320, 240) );
        setResizable( false );

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        Container content = getContentPane();
        content.add(label);
        content.setBackground(Color.white);
        content.setLayout(new FlowLayout());
        
        itemNovo.setText("Novo"); //Cria um item
        itemNovo.addMouseListener( new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent evt){
                Novo();
            }
        });
        menu.add(itemNovo); //Adiciona o item ao menu
        menu.add(separator); //E adiciona um separador

        itemAbrir.setText("Abrir"); //Cria um item
        itemAbrir.addActionListener( new ActionListener(){
            @Override
            public void actionPerformed( ActionEvent evt ){
                Abrir();
            }
        });
        menu.add(itemAbrir);
        menu.add(separator2);

        itemSair.setText("Fechar");
        itemSair.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                Sair();
            }
        });
        menu.add(itemSair);
        barra.add(menu); //Adiciona a barra o menu com seus itens
        
        setJMenuBar(barra); //Adiciono a barra a janela
        pack();
    }

    private void Sair(){
    	dispose();
    }

    private void Novo() {
    	String nome = new String();
    	do{
            nome = JOptionPane.showInputDialog("Digite o nome: ", nome);
            if ( nome == null ) return;
    	}while ( nome.compareTo("") == 0 );
    	File arquivo = new File(nome);
    	Gerencia gerencia = new Gerencia(arquivo);
    	gerencia.Novo();
    }
    
    private void Abrir(){
    	JFileChooser EscolherArquivo = new JFileChooser();
    	int opcao = EscolherArquivo.showOpenDialog(null);
        
        //Abre uma janela onde o usuário escolherá o arquivo que deseja abrir
    	if ( opcao == JFileChooser.CANCEL_OPTION ) return;
    	else if ( opcao == JFileChooser.APPROVE_OPTION ){
                File diretorio = EscolherArquivo.getSelectedFile();
                Gerencia gerencia = new Gerencia(diretorio); //Cria uma nova instancia de gerencia
                gerencia.Carrega(); //Carrega os dados na tabela		    	
    	}
    }

    public static void main(String args[]) {
        Main principal = new Main();
        principal.setVisible( true );
    }
}
