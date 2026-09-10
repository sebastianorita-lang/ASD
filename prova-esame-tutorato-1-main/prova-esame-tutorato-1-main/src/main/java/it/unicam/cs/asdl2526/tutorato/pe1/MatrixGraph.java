package it.unicam.cs.asdl2526.tutorato.pe1;

import java.util.*;

/**
 * Classe che implementa un grafo non orientato tramite matrice di adiacenza.
 * Non sono accettate etichette dei nodi null e non sono accettate etichette
 * duplicate nei nodi (che in quel caso sono lo stesso nodo).
 *
 * I nodi sono indicizzati da 0 a nodeCoount() - 1 seguendo l'ordine del loro
 * inserimento (0 è l'indice del primo nodo inserito, 1 del secondo e così via)
 * e quindi in ogni istante la matrice di adiacenza ha dimensione nodeCount() *
 * nodeCount(). La matrice, sempre quadrata, deve quindi aumentare di dimensione
 * ad ogni inserimento di un nodo. Per questo non è rappresentata tramite array
 * ma tramite ArrayList.
 *
 * Gli oggetti GraphNode<L>, cioè i nodi, sono memorizzati in una mappa che
 * associa ad ogni nodo l'indice assegnato in fase di inserimento. Il dominio
 * della mappa rappresenta quindi l'insieme dei nodi.
 *
 * Gli archi sono memorizzati nella matrice di adiacenza. A differenza della
 * rappresentazione standard con matrice di adiacenza, la posizione i,j della
 * matrice non contiene un flag di presenza, ma è null se i nodi i e j non sono
 * collegati da un arco e contiene un oggetto della classe GraphEdge<L> se lo
 * sono. Tale oggetto rappresenta l'arco. Un oggetto uguale (secondo equals) e
 * con lo stesso peso (se gli archi sono pesati) deve essere presente nella
 * posizione j, i della matrice.
 *
 * Questa classe non supporta i metodi di cancellazione di nodi e archi, ma
 * supporta tutti i metodi che usano indici, utilizzando l'indice assegnato a
 * ogni nodo in fase di inserimento.
 *
 * @author Luca Tesei, Federico Di Petta(template)
 *
 *
 */
public class MatrixGraph<L> extends Graph<L> {
     /*
      * Le seguenti variabili istanza sono protected al solo scopo di agevolare
      * il JUnit testing
      */

     /*
      * Insieme dei nodi e associazione di ogni nodo con il proprio indice nella
      * matrice di adiacenza
      */
     protected List<GraphNode<L>> nodesIndex;

     /*
      * Matrice di adiacenza, gli elementi sono null o oggetti della classe
      * GraphEdge<L>. L'uso di ArrayList permette alla matrice di aumentare di
      * dimensione gradualmente ad ogni inserimento di un nuovo nodo e di
      * ridimensionarsi se un nodo viene cancellato.
      */
     protected ArrayList<ArrayList<GraphEdge<L>>> matrix;

     private int edgeCount; // numero di archi del grafo

     /**
      * Crea un grafo vuoto.
      */
     public MatrixGraph() {
          this.matrix = new ArrayList<ArrayList<GraphEdge<L>>>();
          this.nodesIndex = new ArrayList<>();
     }

     @Override
     public int nodeCount() {
          return this.nodesIndex.size();
     }

     @Override
     public int edgeCount() {
          return edgeCount;
     }

     @Override
     public void clear() {
          this.matrix.clear();
          this.nodesIndex.clear();
          this.edgeCount = 0;
     }

     @Override
     public boolean isDirected() {
          return false;
     }

     /*
      * Gli indici dei nodi vanno assegnati nell'ordine di inserimento a partire
      * da zero
      */
     @Override
     public boolean addNode(L label) {
          if (label == null) {
               throw new NullPointerException("L'etichetta del nodo non può essere nulla");
          }
          
          GraphNode<L> newNode = new GraphNode<L>(label);
          
          if (this.nodesIndex.indexOf(newNode) != -1) {
               return false; // il nodo esiste già
          }
          
          GraphNode<L> newNode = new GraphNode<L>(label);
          this.nodesIndex.add(newNode);
          // Aggiungo una nuova riga alla matrice
          ArrayList<GraphEdge<L>> newRow = new ArrayList<GraphEdge<L>>(this.nodeCount());
          for (int i = 0; i < this.matrix.size(); i++) {
               newRow.add(null);
               this.matrix.get(i).add(null); // Aggiungo una nuova colonna a ogni riga esistente
          }
          newRow.add(null); // L'ultimo elemento della nuova riga è null
          this.matrix.add(newRow);
          return true;
     }


     /*
      * Gli indici dei nodi il cui valore sia maggiore dell'indice del nodo da
      * cancellare devono essere decrementati di uno dopo la cancellazione del
      * nodo
      */
     @Override
     public void removeNode(L label) {
          if (label == null) {
               throw new NullPointerException("L'etichetta del nodo non può essere nulla");
          }
          int indexToRemove = this.nodesIndex.indexOf(new GraphNode<L>(label));
          if (indexToRemove == -1) {
               throw new IllegalArgumentException("Il nodo da rimuovere non esiste");
          }
          // Rimuovo la riga dalla matrice
          this.removeNode(indexToRemove);
     }

     @Override
     public void removeNode(int i) {
          if (i < 0 || i >= this.nodeCount()) {
               throw new IndexOutOfBoundsException("Indice del nodo da rimuovere non valido");
          }
          this.matrix.remove(i);
          // Rimuovo la colonna dalla matrice
          for (ArrayList<GraphEdge<L>> row : this.matrix) {
               if (row.get(i) != null) {
                    this.edgeCount--; // Decremento il conteggio degli archi se c'era un arco
               }
               row.remove(i);
          }
          this.nodesIndex.remove(i);
     }

     @Override
     public GraphNode<L> getNode(L label) {
          if (label == null) {
               throw new NullPointerException("L'etichetta del nodo non può essere nulla");
          }
                    int i = this.nodesIndex.indexOf(new GraphNode<L>(label));

          if (i == -1) {
               return null;
          }

          return this.nodesIndex.get(this.getNodeIndexOf(label));
     }

     @Override
     public GraphNode<L> getNode(int i) {
          if (i < 0 || i >= this.nodeCount()) {
               throw new IndexOutOfBoundsException("Indice del nodo non valido");
          }
         return this.nodesIndex.get(i);
     }

     @Override
     public int getNodeIndexOf(L label) {
          if(label == null) {
               throw new NullPointerException("L'etichetta del nodo non può essere nulla");
          }
          int index = this.nodesIndex.indexOf(new GraphNode<L>(label));
          if (index == -1) {
               throw new IllegalArgumentException("Il nodo con l'etichetta specificata non esisteì");
          }
          return index;
     }

     @Override
     public Set<GraphNode<L>> getNodes() {
          return new HashSet<>(this.nodesIndex);
     }

     @Override
     public boolean addEdge(L label1, L label2) {
          if (label1 == null) {
               throw new NullPointerException("label1 non può essere null");
          }
          
          if (label2 == null) {
               throw new NullPointerException("label2 non può essere null");
          }

          int i = this.nodesIndex.indexOf(new GraphNode<L>(label1));
          int j = this.nodesIndex.indexOf(new GraphNode<L>(label2));
          
          if (i == -1 || j == -1) {
               throw new IllegalArgumentException("label1 o label2 non presenti");
          }
          return this.addEdge(i, j, Double.NaN);
     }

     @Override
     public boolean addWeightedEdge(L label1, L label2, double weight) {
          if (label1 == null) {
               throw new NullPointerException("label1 non può essere null");
          }
          
          if (label2 == null) {
               throw new NullPointerException("label2 non può essere null");
          }

          int i = this.nodesIndex.indexOf(new GraphNode<L>(label1));
          int j = this.nodesIndex.indexOf(new GraphNode<L>(label2));
          
          if (i == -1 || j == -1) {
               throw new IllegalArgumentException("label1 o label2 non presenti");
          }
          return this.addEdge(i, j, weight);
     }

     @Override
     public boolean addEdge(int i, int j) {
          if (i < 0 || i >= this.nodeCount()) {
               throw new IndexOutOfBoundsException("Indice i del nodo da rimuovere non valido");
          }

          if (j < 0 || j >= this.nodeCount()) {
               throw new IndexOutOfBoundsException("Indice j del nodo da rimuovere non valido");
          }

          return this.addEdge(i, j, Double.NaN);
     }

     @Override
     public boolean addWeightedEdge(int i, int j, double weight) {
          if (i < 0 || i >= this.nodeCount()) {
               throw new IndexOutOfBoundsException("Indice i del nodo da rimuovere non valido");
          }

          if (j < 0 || j >= this.nodeCount()) {
               throw new IndexOutOfBoundsException("Indice j del nodo da rimuovere non valido");
          }

          return this.addEdge(i, j, weight);
     }

     @Override
     public void removeEdge(int i, int j) {
          // TODO implementare
     }

     @Override
     public GraphEdge<L> getEdge(L label1, L label2) {
          // Lanciano eccezione se i nodi non esistono
          int i = this.getNodeIndexOf(label1);
          int j = this.getNodeIndexOf(label2);
          return this.getEdge(i, j);
     }

     @Override
     public GraphEdge<L> getEdge(int i, int j) {
          if (i < 0 || i >= this.nodeCount() || j < 0 || j >= this.nodeCount()) {
               throw new IndexOutOfBoundsException("Indice(i o j) non valido");
          }
          return this.matrix.get(i).get(j);
     }

     @Override
     public Set<GraphNode<L>> getAdjacentNodesOf(L label) {
          // TODO implementare
          return null;
     }

     @Override
     public Set<GraphNode<L>> getAdjacentNodesOf(int i) {
          // TODO implementare
          return null;
     }


     @Override
     public Set<GraphEdge<L>> getEdgesOf(int i) {
          // TODO implementare
          return null;
     }

     @Override
     public Set<GraphEdge<L>> getIngoingEdgesOf(int i) {
          throw new UnsupportedOperationException(
                  "Operazione non supportata in un grafo non orientato");
     }

     @Override
     public Set<GraphEdge<L>> getEdges() {
          Set<GraphEdge<L>> edges = new HashSet<>();
          for (int i = 0; i < this.nodeCount(); i++) 
               for (int j = i + 1; j < this.nodeCount(); j++) 
                    if (this.matrix.get(i).get(j) != null)
                         edges.add(this.matrix.get(i).get(j));
          return edges;
     }

     private boolean addEdge(int i, int j, Double w) {
          
          if (this.matrix.get(i).get(j) != null && this.matrix.get(j).get(i) != null)
               return false;
          
          this.matrix.get(i).set(j, 
               w.isNaN()
               ? new GraphEdge<>(
                    this.nodesIndex.get(i), 
                    this.nodesIndex.get(j),
                    isDirected())
               : new GraphEdge<>(
                    this.nodesIndex.get(i), 
                    this.nodesIndex.get(j),
                    isDirected(),
                    w)
          );
          this.matrix.get(j).set(i, 
               w.isNaN()
               ? new GraphEdge<>(
                    this.nodesIndex.get(j), 
                    this.nodesIndex.get(i),
                    isDirected())
               : new GraphEdge<>(
                    this.nodesIndex.get(j), 
                    this.nodesIndex.get(i),
                    isDirected(),
                    w)
          );
          this.edgeCount++;
          return true;
     }
}
