/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package POO;

/**
 *
 * @author Vinicius
 */
import java.io.Serializable;
import java.util.*;


@SuppressWarnings("serial")
public class Components implements Serializable{
    //Primeiro item do Pair é a chave que será utilizada na comparação
    //para ordenação. O Vector compõe os elementos da linha
    protected List< Pair<String, Vector<String> > > sorted, non_sorted;
    protected Vector<String> columns;

    static Comparator< Pair< String, Vector<String> > > sort_All =
        new Comparator< Pair< String, Vector<String> > >(){
            @Override
            public int compare( Pair< String, Vector<String> > a, Pair< String, Vector<String> > b ){
                if ( a.getFirst().compareTo(b.getFirst()) <= 0 ) return -1;
                else return 1;
            }
        };
	
    protected Components(){
        this.sorted = new Vector<>();
        this.non_sorted = new Vector<>();
        this.columns = new Vector<>();
    }
    //Adiciona um elemento na lista para ser ordenada
    protected void addElementoSorted( Pair< String, Vector<String> > valores ){
        sorted.add(valores);
    }
    //Adiciona um atributo
    protected void addAtributo( String atributo ){
        columns.add(atributo);
    }
    //Retorna o valor de um atributo
    protected String getAtributo( int coluna ){
        return non_sorted.get(coluna).getFirst();
    }
    //Retorna um elemento E na linha L e coluna C
    protected String getElementoSorted( int l, int c ){
        return sorted.get(l).getSecond().get(c);
    }
    //Retorna uma 'chave' de ordenação na linha I
    protected String getChaveSorted( int i ){
        return sorted.get(i).getFirst();
    }
    //Retorna a quantidade de linhas
    protected int getLinhas(){
        return sorted.size();
    }
    //Retorna a quantidade de atributos
    protected int getColunas(){
        return columns.size();
    }
    //Limpa o mapa
    protected void clearSorted(){
        sorted.clear();
        columns.clear();
    }
    //Cria uma nova coluna
    protected void addElemento( Pair< String, Vector<String> > valores ){
        non_sorted.add( valores );
    }
    //Retorna um elemento da linha
    protected String getElemento( int l, int c ){
        return non_sorted.get(l).getSecond().get(c);
    }
    //Retorna a quantidade de linhas
    protected int getLinhasNonSorted( ){
        return non_sorted.get(0).getSecond().size();
    }
    //Retorna a quantidade de linhas
    protected int getColunasNonSorted( ){
        return non_sorted.size();
    }
    //Limpa o Vector não-ordenado
    protected void clearNonSorted(){
        non_sorted.clear();
    }
}
