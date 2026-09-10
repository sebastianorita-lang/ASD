### Condizioni dell'esame

- **Tempo a disposizione**: **1 ora**
- **È consentito** utilizzare come IDE **solo** IntelliJ IDEA, disponibile nel
  Thin Client.
- **Non è consentito** utilizzare materiale cartaceo a parte il foglio dato in
  dotazione, su cui bisogna scrivere all'inizio della prova Nome, Cognome e
  Matricola.
- **Non è consentito** navigare in Internet con il browser o altre
  applicazioni.

---

### Istruzioni

1. Scaricare il codice in formato `.zip` dalla domanda Moodle nella cartella
   del disco locale `C` del Thin Client `C:\Users\proprionome.propriocognome`.
   **ATTENZIONE** il
   file deve essere scaricato e poi scompattato esattamente in questa
   cartella, altrove IntelliJ IDEA non riesce a leggere correttamente i file.
2. Scompattare lo zip.
3. Aprire con IntelliJ IDEA la cartella scompattata. **ATTENZIONE**: la
   cartella deve contenere `src` (cartella), `pom.xml` e `README.md`.
4. L'IDE dovrebbe riconoscere automaticamente il progetto come **Maven** e
   scaricare le dipendenze necessarie per l’esecuzione dei test. Questo può
   richiedere un po' di tempo.
5. Ignorare la finestra di "Access Denied" o qualcosa di simile che si aprirà.
   Se viene chiusa si riapre. Abbassatela così non dà fastidio.
6. Provare a eseguire i test per verificare che venga fatto il Build del
   progetto. In questa fase ovviamente molti test non passeranno. **ATTENZIONE
   ** se c'è un errore che riguarda l'assenza di una SDK, andare su File ->
   Project Structure -> Tab Project e impostare una SDK tra quelle
   disponibili. Qualora non fosse installata nessuna SDK si potrà scaricare da
   IntelliJ IDEA.
7. Leggere attentamente la descrizione delle classi fornite e del lavoro da
   svolgere in questo file `README.md` (e nella domanda su Moodle).
8. Implementare **esclusivamente** i metodi contrassegnati nel codice con
   `// TODO implementare` e `// TODO implementare ricorsivamente`
9. Sono messi a disposizione alcuni **test JUnit di base** per verificare le
   funzionalità richieste.
   **I test forniti non sono tutti quelli utilizzati in fase di valutazione.**

---

### Descrizione delle classi fornite e del lavoro da fare

Nel progetto è fornita la classe:

- **`ReverseBinarySearchTree<E extends Comparable<E>>`**

che rappresenta un **binary search tree (BST)** parametrico sul tipo generico
`E`,
realizzato tramite **nodi ricorsivi**, rappresentati da oggetti della classe
interna
`RecReverseBST`.

#### Caratteristiche principali della struttura dati

L’albero implementato ha una **proprietà di ordinamento inversa** rispetto a
quella
standard dei BST:

- per ogni nodo con etichetta `E`:
    - tutte le etichette nel **sottoalbero sinistro** sono **maggiori o uguali
      ** a `E`,
    - tutte le etichette nel **sottoalbero destro** sono **strettamente minori
      ** di `E`.

L’albero:

- **non accetta etichette `null`**,
- **non accetta etichette duplicate**,
- può essere **vuoto** (rappresentato dal fatto che il riferimento alla radice
  è `null`).

Ogni nodo mantiene inoltre un riferimento al **nodo genitore** (`parent`), che
deve
essere aggiornato correttamente durante le operazioni di inserimento.

La classe principale fornisce le API pubbliche, che delegano l’effettiva
implementazione
dei comportamenti ai metodi ricorsivi definiti nella classe interna
`RecReverseBST`.

---

#### Lavoro da svolgere

Lo studente deve **completare l’implementazione con ricorsione** dei metodi
contrassegnati nel codice
con il commento:

    // TODO implementare ricorsivamente

In particolare, devono essere implementati i seguenti metodi della classe
interna
`RecReverseBST`:

- `computeHeight()`  
  Calcola ricorsivamente l’altezza del sottoalbero radicato nel nodo corrente.

- `insert(E label)`  
  Inserisce una nuova etichetta nel (sotto)albero rispettando la **proprietà
  di ordinamento
  inversa** del BST e aggiornando correttamente i riferimenti ai nodi
  genitore.

- `search(E label)`  
  Cerca ricorsivamente un nodo con una data etichetta nel (sotto)albero.

- `inOrderVisit()`  
  Esegue una **visita in-order** del (sotto)albero, producendo una lista di
  etichette
  **ordinate secondo l’ordinamento naturale crescente** della classe `E`
  (indipendentemente dal fatto che l’albero sia “reverse”).

- `getMinNode()` e `getMaxNode()`  
  Restituiscono rispettivamente il nodo con l’etichetta minima e massima (
  secondo
  l’ordinamento naturale), presenti nel (sotto)albero.

Lo studente deve **completare l’implementazione** dei metodi contrassegnati
nel codice
con il commento (in questo caso **l'implementazione con ricorsione non è
obbligatoria**):

    // TODO implementare

- `getSuccessorNode()`  
  Restituisce il nodo che contiene l’etichetta **successiva** a quella del
  nodo corrente,
  secondo l’ordinamento naturale della classe `E`, oppure `null` se non
  esiste.

- `getPredecessorNode()`  
  Restituisce il nodo che contiene l’etichetta **precedente** a quella del
  nodo corrente,
  secondo l’ordinamento naturale della classe `E`, oppure `null` se non
  esiste.

I metodi pubblici della classe `ReverseBinarySearchTree` (come `add`,
`contains`,
`getMin`, `getMax`, `getOrderedLabels`, `getSuccessor`, `getPredecessor`,
ecc.)
**devono funzionare correttamente** grazie all’implementazione dei metodi
ricorsivi
sopra elencati.

---

#### Vincoli e indicazioni

- Tutte le operazioni devono essere implementate in modo **coerente con un BST
  **.
  Nel caso pessimo, la complessità può essere **O(h)**, dove `h` è l’altezza
  dell’albero.
- Quando nel metodo è scritto esplicitamente
  `\\TODO implementare ricorsivamente` la soluzione **deve** essere ricorsiva.
- **Non è consentito** utilizzare strutture dati esterne (liste, set, mappe,
  array)
  per simulare il comportamento dell’albero.
- **Non è consentito** ottenere l’ordinamento delle etichette tramite
  algoritmi di
  ordinamento (`sort`): l’ordine deve derivare esclusivamente da una visita
  in-order.
- I casi limite (albero vuoto, nodo foglia, assenza di successore o
  predecessore)
  devono essere gestiti correttamente, come specificato nei commenti del
  codice.
- Il codice deve essere compatibile con **Java 8**.

---

#### Test

Nel progetto sono forniti **alcuni test JUnit di base** per verificare parte
delle
funzionalità richieste.

Il superamento di tutti i test forniti è **necessario ma non sufficiente** per
ottenere
il punteggio massimo: durante la valutazione verranno utilizzati ulteriori
test.

### Consegna delle classi

1. Controllare che non ci siano errori di compilazione.
2. Controllare che tutti i test forniti passino correttamente.
3. Allegare **solo i seguenti file** nella risposta al compito Moodle:
    - `ReverseBinarySearchTree.java`
4. **Non devono essere consegnate**:
    - le classi che non contengono `// TODO implementare`,
    - le classi di test.
5. Terminare la sessione e uscire dalla stanza.
