/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package POO;

/**
 *
 * @author Vinicius
 */
import java.io.*;
import java.util.*;

import javax.swing.*;
import javax.swing.table.*;

@SuppressWarnings({"unchecked", "serial"})
public class Gerencia extends Components{ 
    protected DefaultTableModel modelo;
    protected File arquivo;
    
    public Gerencia( File arquivo ){
        super();
        this.arquivo = arquivo;
        this.modelo = new DefaultTableModel();
    }
	
    //Pega os dados da JTable
    public void getTabela( DefaultTableModel model ){
        clearNonSorted();
        //Caminho pelas colunas...
        for ( int i = 0; i < model.getColumnCount(); ++i ){
            //Pega o valor do atributo
            String atributo = model.getColumnName(i);
            //Vector dos elementos da linha
            Vector<String> elementos = new Vector<>();
            for ( int j = 0; j < model.getRowCount(); ++j ){
                String elemento = (String)model.getValueAt(j, i);
                //Adiciono os elementos no ArrayList
                elementos.add(elemento);
            }
            //Crio um novo Pair que será inserido no meu Vector de Pair
            Pair< String, Vector<String> > novo = new Pair<>( atributo, elementos ); 
            addElemento(novo);
        }
    }

    //Pega os dados da JTable
    public void getTabelaToSort( DefaultTableModel model, int coluna ){
        clearSorted();
        //Caminho pelas linhas...
        for ( int i = 0; i < model.getRowCount(); ++i ){
            String chave = (String)model.getValueAt(i, coluna);
            Vector<String> elementos = new Vector<>();
            for ( int j = 0; j < model.getColumnCount(); ++j ){
                if ( i == 0 ) addAtributo(model.getColumnName(j));
                String elemento = (String)model.getValueAt(i, j);
                elementos.add(elemento);
            }
            //Crio um novo Pair que será inserido no meu Vector de Pair
            Pair< String, Vector<String> > novo = new Pair<>( chave, elementos ); 
            addElementoSorted(novo);
        }
    }

    public void Grava(){
        try {			
            ObjectOutputStream novoArquivo = new ObjectOutputStream( new FileOutputStream (arquivo) );
            novoArquivo.writeObject( non_sorted );
            novoArquivo.close();
            JOptionPane.showMessageDialog( null, "Dados salvos!", "Salvo", JOptionPane.INFORMATION_MESSAGE );
        }
        catch ( IOException ex ){
            System.out.println(ex.toString());
        }
    }

    public void Carrega(){
        Janela janela = new Janela(arquivo, getModel());
        janela.setVisible(true);

        //Carrega um novo arquivo e coloca os dados na Table
        try{      	
            ObjectInputStream novoArquivo = new ObjectInputStream( new FileInputStream ( arquivo ) );
            clearNonSorted();
            non_sorted = (Vector< Pair<String, Vector<String> > >) novoArquivo.readObject();
            novoArquivo.close();
            JOptionPane.showMessageDialog( null, "Dados carregados!", "Carregado", JOptionPane.INFORMATION_MESSAGE );            
        }
        catch( IOException | ClassNotFoundException ex ){
        }

        //Insere na tabela os atributos
        for ( int i = 0; i < getColunasNonSorted(); ++i )
            modelo.addColumn( getAtributo(i) );
        //Insere as linhas
        for ( int i = 0; i < getLinhasNonSorted(); ++i )
            modelo.addRow( new Object[]{} );
        //Insere os elementos nas linhas
        for ( int i = 0; i < getColunasNonSorted(); ++i )
            for ( int j = 0; j < getLinhasNonSorted(); ++j )
                modelo.setValueAt(getElemento(i, j), j, i);
        janela.setCombo();
    }

    public void Novo(){
        DefaultTableModel model = new DefaultTableModel();
        Janela janela = new Janela(arquivo, model);
        janela.setVisible(true);
    }

    public void Ordena( DefaultTableModel model ){
        //Ordena os elementos
        Collections.sort(sorted, sort_All);
        //Coloca os elementos ordenados na JTable
        for ( int i = 0; i < getLinhas(); ++i )
            for ( int j = 0; j < getColunas(); ++j )
                model.setValueAt(getElementoSorted(i, j), i, j);
    }

    public DefaultTableModel getModel(){
        return modelo;
    }
}
