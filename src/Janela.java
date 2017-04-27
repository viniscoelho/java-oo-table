/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package POO;

/**
 *
 * @author Vinicius
 */
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import javax.swing.*;
import javax.swing.table.*;

@SuppressWarnings({"serial", "rawtypes", "unchecked"})
public class Janela extends JFrame {

    protected Gerencia gerencia;
    protected JButton col, row, remove, save;
    protected JLabel ordenar;
    protected JTable tabela;
    protected JComboBox lista;
    protected DefaultTableModel modelo;
	
    public Janela( File arquivo, DefaultTableModel model ){
        super();
        this.gerencia = new Gerencia(arquivo);
        this.col = new JButton();
        this.row = new JButton();
        this.remove = new JButton();
        this.save = new JButton();
        this.ordenar = new JLabel("Ordenar por:");
        this.lista = new JComboBox();

        this.modelo = model;
        this.tabela = new JTable(modelo){
            @Override
            public boolean isCellEditable(int rowIndex, int columnIndex){
                return true;
            }
        };

        setTitle(arquivo.getName());
        setSize(800, 600);
        setResizable( true );

        Container content = getContentPane();
        content.setBackground(Color.white);

        JScrollPane scrollPane = new JScrollPane( tabela );
        content.add( scrollPane );
        GroupLayout layout = new GroupLayout(content);

        layout.setHorizontalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(73, 73, 73)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(col)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(row)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(remove)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(save))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 640, GroupLayout.PREFERRED_SIZE)
                        .addGap(32, 32, 32)
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                            .addComponent(lista, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                            .addComponent(ordenar))))
                .addContainerGap(18, Short.MAX_VALUE))
        );

        layout.setVerticalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 480, GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(ordenar)
                        .addGap(12, 12, 12)
                        .addComponent(lista, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 39, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(col)
                    .addComponent(row)
                    .addComponent(remove)
                    .addComponent(save))
                .addContainerGap())
        );

        content.setLayout( layout );

        col.setText("Inserir Atributo");
        col.addActionListener( new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                String titulo;
                do{
                        titulo = JOptionPane.showInputDialog(null, "Digite o nome do atributo: ");
                        if ( titulo == null ) return;
                }while( titulo.compareTo("") == 0 );
                modelo.addColumn(titulo);
                setCombo();
            }
        });

        row.setText("Inserir Linha");
        row.addActionListener( new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                if ( modelo.getColumnCount() == 0 ) return;
                modelo.addRow( new String[]{""} );
            }
        });

        remove.setText("Remover Linha");
        remove.addActionListener( new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                modelo.removeRow(tabela.getSelectedRow());
            }
        });

        save.setText("Salvar");
        save.addActionListener( new ActionListener(){
            @Override
            public void actionPerformed( ActionEvent e ){
                gerencia.getTabela(modelo);
                gerencia.Grava();
            }
        });

        lista.addActionListener( new ActionListener(){
            @Override
            public void actionPerformed( ActionEvent e ){
                gerencia.getTabelaToSort(modelo, lista.getSelectedIndex());
                gerencia.Ordena(modelo);
            }
        });

        content.add(col);
        content.add(row);
        content.add(remove);
        content.add(save);
    }

    public void setCombo(){
        //Adiciono os atributos da JTable em uma caixa de combinação
        int qtdColunas = modelo.getColumnCount();
        String conteudo[] = new String[qtdColunas];

        for ( int i = 0; i < qtdColunas; ++i )
            conteudo[i] = modelo.getColumnName(i);

        lista.setModel( new DefaultComboBoxModel(conteudo) );
    }
}
