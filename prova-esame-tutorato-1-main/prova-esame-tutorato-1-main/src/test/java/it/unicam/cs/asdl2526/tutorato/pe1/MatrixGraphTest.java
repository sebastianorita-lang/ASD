package it.unicam.cs.asdl2526.tutorato.pe1;

import static org.junit.jupiter.api.Assertions.*;

import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.Test;

/**
 * Classe di test per la classe MatrixGraph.
 *
 * @author Luca Tesei
 */
class MatrixGraphTest {

    @Test
    final void testMatrixGraph() {
        MatrixGraph<String> g = new MatrixGraph<String>();
        assertTrue(g.isEmpty());
    }

    @Test
    final void testNodeCount() {
        Graph<String> g = new MatrixGraph<String>();
        assertEquals(0, g.nodeCount());
        g.addNode("S");
        assertEquals(1, g.nodeCount());
        g.addNode("u");
        assertEquals(2, g.nodeCount());
    }

    @Test
    final void testEdgeCount() {
        Graph<String> g = new MatrixGraph<String>();
        assertEquals(0, g.edgeCount());
        g.addNode("s");
        assertEquals(0, g.edgeCount());
        g.addNode("u");
        g.addWeightedEdge("s", "u", 10.1);
        assertEquals(1, g.edgeCount());
        g.addWeightedEdge("s", "u", 10.1);
        assertEquals(1, g.edgeCount());
        g.addNode("x");
        g.addWeightedEdge("s", "x", 11);
        assertEquals(2, g.edgeCount());
    }

    @Test
    final void testSize() {
        Graph<String> g = new MatrixGraph<String>();
        assertTrue(g.size() == 0);
        g.addNode("ns");
        assertTrue(g.size() == 1);
        g.addNode("nu");
        assertTrue(g.size() == 2);
        g.addEdge("ns", "nu");
        assertTrue(g.size() == 3);
        g.addNode("nx");
        assertTrue(g.size() == 4);
        g.addEdge("ns", "nx");
        assertTrue(g.size() == 5);
        g.clear();
        assertTrue(g.size() == 0);
    }

    @Test
    final void testIsEmpty() {
        Graph<String> g = new MatrixGraph<String>();
        assertTrue(g.isEmpty());
        g.addNode("ns");
        assertFalse(g.isEmpty());
        g.clear();
        assertTrue(g.isEmpty());
    }

    @Test
    final void testClear() {
        Graph<String> g = new MatrixGraph<String>();
        assertTrue(g.isEmpty());
        g.addNode("ns");
        assertFalse(g.isEmpty());
        g.addNode("nu");
        g.clear();
        assertTrue(g.isEmpty());
    }

    @Test
    final void testIsDirected() {
        Graph<String> g = new MatrixGraph<String>();
        assertFalse(g.isDirected());
    }

    @Test
    final void testAddNode() {
        Graph<String> g = new MatrixGraph<String>();
        assertThrows(NullPointerException.class,
                () -> g.addNode(null));
        assertTrue(g.getNode("ns") == null);
        g.addNode("ns");
        assertTrue(g.getNode("ns") != null);
        String lu = "u";
        String luTest = "u";
        assertTrue(g.getNode(luTest) == null);
        g.addNode(lu);
        assertTrue(g.getNode(luTest) != null);
    }

    @Test
    final void testRemoveNode() {
        Graph<String> g = new MatrixGraph<String>();
        assertThrows(NullPointerException.class,
                () -> g.removeNode((String) null));
        assertThrows(IndexOutOfBoundsException.class, () -> g.removeNode(0));
        g.addNode("a");
        g.addNode("b");
        g.addNode("c");
        g.addNode("d");
        g.addEdge("a", "b");
        g.addEdge("b", "c");
        g.addEdge("a", "a");
        g.addEdge("b", "d");
        g.addEdge("a", "d");
        g.addEdge("c", "d");
        assertTrue(g.getNodeIndexOf("a") == 0);
        assertTrue(g.getNodeIndexOf("b") == 1);
        assertTrue(g.getNodeIndexOf("c") == 2);
        assertTrue(g.getNodeIndexOf("d") == 3);
        assertTrue(g.nodeCount() == 4);
        assertThrows(IllegalArgumentException.class, () -> g.removeNode("e"));
        assertThrows(IndexOutOfBoundsException.class, () -> g.removeNode(4));
        g.removeNode("b");
        assertTrue(g.getNodeIndexOf("a") == 0);
        assertTrue(g.getNodeIndexOf("c") == 1);
        assertTrue(g.getNodeIndexOf("d") == 2);
        assertTrue(g.nodeCount() == 3);
        assertTrue(g.getNode("b") == null);
        // Controlla che la matrice sia ancora quadrata e non ci siano buchi
        assertDoesNotThrow(() -> {
            for (int i = 0; i < g.nodeCount(); i++)
                for (int j = 0; j < g.nodeCount(); j++)
                    g.getEdge(i, j);
        });
        assertTrue(g.getEdge("a", "a") != null);
        assertTrue(g.getEdge("a", "d") != null);
        assertTrue(g.getEdge("c", "d") != null);
        assertTrue(g.getEdge("c", "a") == null);
        assertTrue(g.getEdge("d", "d") == null);
        assertTrue(g.getEdge("c", "c") == null);
        g.removeNode(0);
        assertTrue(g.getNodeIndexOf("c") == 0);
        assertTrue(g.getNodeIndexOf("d") == 1);
        assertTrue(g.nodeCount() == 2);
        assertTrue(g.getNode("a") == null);
        // Controlla che la matrice sia ancora quadrata e non ci siano buchi
        assertDoesNotThrow(() -> {
            for (int i = 0; i < g.nodeCount(); i++)
                for (int j = 0; j < g.nodeCount(); j++)
                    g.getEdge(i, j);
        });
        assertTrue(g.getEdge("c", "d") != null);
        assertTrue(g.getEdge("d", "d") == null);
        assertTrue(g.getEdge("c", "c") == null);
    }

    @Test
    final void testGetNode() {
        Graph<String> g = new MatrixGraph<String>();
        assertThrows(NullPointerException.class,
                () -> g.getNode((String) null));

        assertTrue(g.getNode("ns") == null);
        g.addNode("ns");
        assertTrue(g.getNode("ns") != null);
        g.addNode("na");
        GraphNode<String> na = g.getNode("na");
        assertTrue(na != null);
        na.setColor(GraphNode.COLOR_BLACK);
        assertTrue(g.getNode("na").getColor() == GraphNode.COLOR_BLACK);
        assertFalse(g.addNode("na"));
        assertTrue(g.getNode("na").getColor() == GraphNode.COLOR_BLACK);
        assertTrue(g.getNode("b") == null);
    }

    @Test
    final void testGetNodeInt() {
        Graph<String> g = new MatrixGraph<String>();
        assertThrows(IndexOutOfBoundsException.class, () -> g.getNode(0));
        g.addNode("s");
        GraphNode<String> ns = g.getNode("s");
        ns.setColor(1);
        assertThrows(IndexOutOfBoundsException.class, () -> g.getNode(1));
        GraphNode<String> nsTest = new GraphNode<String>("s");
        assertTrue(nsTest.equals(g.getNode(0)));
        assertTrue(g.getNode(0).getColor() == 1);
        g.addNode("u");
        GraphNode<String> nu = g.getNode("u");
        assertThrows(IndexOutOfBoundsException.class, () -> g.getNode(2));
        GraphNode<String> nuTest = new GraphNode<String>("u");
        assertTrue(nuTest.equals(g.getNode(1)));
    }

    @Test
    final void testGetNodeIndexOf() {
        Graph<String> g = new MatrixGraph<String>();
        assertThrows(NullPointerException.class,
                () -> g.getNodeIndexOf((String) null));
        g.addNode("s");
        assertTrue(g.getNodeIndexOf("s") == 0);
        assertThrows(IllegalArgumentException.class,
                () -> g.getNodeIndexOf("u"));
        GraphNode<String> nu = new GraphNode<String>("u");
        g.addNode("u");
        assertTrue(g.getNodeIndexOf("u") == 1);
        assertTrue(g.getNodeIndexOf("s") == 0);
        g.addNode("x");
        assertTrue(g.getNodeIndexOf("x") == 2);
        g.addEdge("s", "x");
        assertTrue(g.getNodeIndexOf("s") == 0);
        g.removeNode("u");
        assertThrows(IllegalArgumentException.class,
                () -> g.getNodeIndexOf("u"));
        assertTrue(g.getNodeIndexOf("s") == 0);
        assertFalse(g.addNode("s"));
        assertFalse(g.addNode("x"));
        assertTrue(g.getNodeIndexOf("x") == 1);
        g.removeNode("s");
        assertThrows(IllegalArgumentException.class,
                () -> g.getNodeIndexOf("s"));
        assertTrue(g.getNodeIndexOf("x") == 0);
    }

    @Test
    final void testGetNodes() {
        Graph<String> g = new MatrixGraph<String>();
        Set<GraphNode<String>> nodes = g.getNodes();
        assertTrue(nodes.isEmpty());
        g.addNode("s");
        g.addNode("u");
        nodes = g.getNodes();
        Set<GraphNode<String>> testNodes = new HashSet<>();
        GraphNode<String> nsTest = new GraphNode<>("s");
        GraphNode<String> nuTest = new GraphNode<>("u");
        testNodes.add(nuTest);
        testNodes.add(nsTest);
        assertTrue(nodes.equals(testNodes));
        g.addNode("n");
        nodes = g.getNodes();
        GraphNode<String> nnTest = new GraphNode<>("n");
        testNodes.add(nnTest);
        assertTrue(nodes.equals(testNodes));
    }

    @Test
    final void testAddEdge() {
        Graph<String> g = new MatrixGraph<String>();
        g.addNode("s");
        GraphNode<String> nu = new GraphNode<String>("u");
        assertThrows(IllegalArgumentException.class,
                () -> g.addEdge("s", "u"));
        g.addNode("u");
        assertTrue(g.addEdge("s", "u"));
        assertNotNull(g.getEdge("s", "u"));
        assertFalse(g.addWeightedEdge("s", "u", 6.0));
        g.addNode("x");
        assertTrue(g.addEdge("x", "s"));
        assertNotNull(g.getEdge("s", "x"));
        assertNotNull(g.getEdge("x", "s"));
        g.addNode("t");
        assertTrue(g.addWeightedEdge("s", "t", 5.0));
        GraphEdge<String> est = g.getEdge("t", "s");
        assertNotNull(est);
        assertEquals(5, est.getWeight());
        g.addNode("w");
        assertTrue(g.addWeightedEdge("w", "u", 4.0));
        assertEquals(4, g.getEdge("u", "w").getWeight());
        assertFalse(g.addEdge("w", "u"));
    }

    @Test
    final void testRemoveEdge() {
        Graph<String> g = new MatrixGraph<String>();
        GraphNode<String> ns = g.getNode("s");
        g.addNode("s");
        g.addNode("a");
        GraphNode<String> nt = g.getNode("t");
        assertThrows(IllegalArgumentException.class,
                () -> g.removeEdge(0, 1));
        assertThrows(IllegalArgumentException.class,
                () -> g.removeEdge(1, 0));
        g.addNode("t");
        assertThrows(IllegalArgumentException.class,
                () -> g.removeEdge(0, 2));
        g.addEdge("t", "s");
        g.addEdge("s", "a");
        assertTrue(g.getEdge("a", "s") != null);
        int aIndex = g.getNodeIndexOf("a");
        int sIndex = g.getNodeIndexOf("s");
        g.removeEdge(aIndex, sIndex);
        assertTrue(g.getEdge("a", "s") == null);
        assertTrue(g.getEdge("s", "a") == null);
        assertTrue(g.getEdge("s", "t") != null);
    }

    @Test
    final void testGetEdge() {
        Graph<String> g = new MatrixGraph<String>();
        assertThrows(NullPointerException.class,
                () -> g.getEdge((String) null, (String) null));
        assertThrows(IndexOutOfBoundsException.class, () -> g.getEdge(0, 0));
        g.addNode("s");
        g.addNode("u");
        assertNull(g.getEdge("s", "u"));
        g.addEdge("s", "u");
        assertNotNull(g.getEdge("s", "u"));
        g.addNode("a");
        g.addNode("b");
        g.addEdge("a", "s");
        g.addWeightedEdge("s", "b", 1);
        assertTrue(g.getEdge("s", "a").getNode1().getLabel().equals("s")
                || g.getEdge("s", "a").getNode1().getLabel().equals("a"));
        assertNotNull(g.getEdge("s", "b"));
        assertNull(g.getEdge("u", "b"));
        int is = g.getNodeIndexOf("s");
        int ia = g.getNodeIndexOf("a");
        int ib = g.getNodeIndexOf("b");
        assertNotNull(g.getEdge(is, ia));
        assertNotNull(g.getEdge(is, ib));
        assertNull(g.getEdge(ib, ia));
        assertThrows(IndexOutOfBoundsException.class, () -> g.getEdge(0, 5));
    }

    @Test
    final void testGetAdjacentNodesOf() {
        Graph<String> g = new MatrixGraph<String>();
        assertThrows(NullPointerException.class,
                () -> g.getAdjacentNodesOf((String) null));
        assertThrows(IndexOutOfBoundsException.class,
                () -> g.getAdjacentNodesOf(0));
        GraphNode<String> ns = new GraphNode<String>("s");
        g.addNode("s");
        Set<GraphNode<String>> adjNodes = new HashSet<GraphNode<String>>();
        assertEquals(g.getAdjacentNodesOf("s"), adjNodes);
        GraphNode<String> nsTest = new GraphNode<String>("s");
        GraphNode<String> nu = new GraphNode<String>("u");
        GraphNode<String> nuTest = new GraphNode<String>("u");
        assertThrows(IllegalArgumentException.class,
                () -> g.getAdjacentNodesOf("u"));
        g.addNode("u");
        double weight = 10.1;
        GraphEdge<String> esu = new GraphEdge<String>(ns, nu, false, weight);
        g.addWeightedEdge("s", "u", weight);
        GraphNode<String> nx = new GraphNode<String>("x");
        GraphNode<String> nxTest = new GraphNode<String>("x");
        g.addNode("x");
        GraphEdge<String> esx = new GraphEdge<String>(ns, nx, false, weight);
        g.addWeightedEdge("s", "x", weight);
        adjNodes.add(nxTest);
        adjNodes.add(nuTest);
        assertEquals(g.getAdjacentNodesOf("s"), adjNodes);
        adjNodes.clear();
        adjNodes.add(nsTest);
        assertEquals(g.getAdjacentNodesOf("x"), adjNodes);
        assertEquals(g.getAdjacentNodesOf("u"), adjNodes);
        GraphNode<String> np = new GraphNode<String>("p");
        GraphNode<String> npTest = new GraphNode<String>("p");
        g.addNode("p");
        adjNodes.clear();
        assertEquals(g.getAdjacentNodesOf("p"), adjNodes);
        g.addNode("q");
        g.addEdge("x", "u");
        g.addEdge("u", "q");
        g.addEdge("p", "u");
        adjNodes.add(nsTest);
        adjNodes.add(nxTest);
        adjNodes.add(new GraphNode<String>("q"));
        adjNodes.add(new GraphNode<String>("p"));
        assertTrue(g.getAdjacentNodesOf("u").equals(adjNodes));
        g.addNode("r");
        int uIndex = g.getNodeIndexOf("u");
        int qIndex = g.getNodeIndexOf("q");
        int pIndex = g.getNodeIndexOf("p");
        g.removeEdge(uIndex, pIndex);
        g.removeEdge(uIndex, qIndex);
        g.addEdge("r", "q");
        g.addEdge("p", "r");
        g.addEdge("r", "r");
        adjNodes.remove(nsTest);
        adjNodes.remove(nxTest);
        adjNodes.add(new GraphNode<String>("r"));
        int i = g.getNodeIndexOf("r");
        assertEquals(g.getAdjacentNodesOf(i), adjNodes);
        adjNodes.remove(new GraphNode<String>("r"));
        g.removeEdge(i, i);
        assertEquals(g.getAdjacentNodesOf(i), adjNodes);
    }

    @Test
    final void testGetEdgesOf() {
        Graph<String> g = new MatrixGraph<String>();
        GraphNode<String> ns = new GraphNode<String>("s");
        g.addNode("s");
        Set<GraphEdge<String>> edgesTest = new HashSet<GraphEdge<String>>();
        assertThrows(IndexOutOfBoundsException.class,
                () -> g.getEdgesOf(-1));
        GraphNode<String> nu = new GraphNode<String>("u");
        g.addNode("u");
        GraphEdge<String> esu = new GraphEdge<String>(ns, nu, false, 5.12);
        g.addEdge("s", "u");
        GraphNode<String> nx = new GraphNode<String>("x");
        g.addNode("x");
        GraphEdge<String> esx = new GraphEdge<String>(ns, nx, false, 5.12);
        g.addEdge("s", "x");
        GraphEdge<String> eux = new GraphEdge<String>(nu, nx, false, 2.05);
        g.addEdge("u", "x");
        GraphNode<String> ny = new GraphNode<String>("y");
        g.addNode("y");
        GraphEdge<String> exy = new GraphEdge<String>(nx, ny, false, 2.0);
        g.addEdge("x", "y");
        GraphEdge<String> eys = new GraphEdge<String>(ny, ns, false, 7.03);
        g.addEdge("y", "s");
        GraphNode<String> nw = new GraphNode<String>("w");
        g.addNode("w");
        edgesTest.add(esu);
        edgesTest.add(esx);
        edgesTest.add(eys);
        assertEquals(g.getEdgesOf(g.getNodeIndexOf("s")), edgesTest);
        edgesTest.clear();
        edgesTest.add(eux);
        edgesTest.add(exy);
        edgesTest.add(new GraphEdge<String>(nx, ns, false));
        assertEquals(g.getEdgesOf(g.getNodeIndexOf("x")), edgesTest);
        edgesTest.clear();
        assertEquals(g.getEdgesOf(g.getNodeIndexOf("w")), edgesTest);
        g.addWeightedEdge("x", "x", 8.9);
        edgesTest.add(esx);
        edgesTest.add(eux);
        edgesTest.add(exy);
        edgesTest.add(new GraphEdge<String>(new GraphNode<String>("x"),
                new GraphNode<String>("x"), false));
        assertEquals(g.getEdgesOf(g.getNodeIndexOf("x")), edgesTest);
        g.addEdge("y", "w");
        int j = g.getNodeIndexOf("y");
        edgesTest.clear();
        edgesTest.add(eys);
        edgesTest.add(exy);
        edgesTest.add(new GraphEdge<String>(new GraphNode<String>("w"),
                new GraphNode<String>("y"), false));
        assertEquals(g.getEdgesOf(j), edgesTest);
    }

    @Test
    final void testGetEdges() {
        Graph<String> g = new MatrixGraph<String>();
        GraphNode<String> ns = new GraphNode<String>("s");
        g.addNode("s");
        Set<GraphEdge<String>> edgesTest = new HashSet<GraphEdge<String>>();
        assertEquals(g.getEdges(), edgesTest);
        GraphNode<String> nu = new GraphNode<String>("u");
        g.addNode("u");
        GraphEdge<String> esu = new GraphEdge<String>(ns, nu, false);
        g.addEdge("s", "u");
        GraphEdge<String> esuTest = new GraphEdge<String>(nu, ns, false);
        edgesTest.add(esuTest);
        assertEquals(g.getEdges(), edgesTest);
        GraphNode<String> nx = new GraphNode<String>("x");
        g.addNode("x");
        GraphEdge<String> esx = new GraphEdge<String>(ns, nx, false, 5.12);
        g.addEdge("s", "x");
        GraphEdge<String> eux = new GraphEdge<String>(nu, nx, false, 2.05);
        g.addEdge("s", "x");
        GraphEdge<String> exu = new GraphEdge<String>(nx, nu, false, 3.04);
        g.addEdge("x", "u");
        edgesTest.add(eux);
        edgesTest.add(esx);
        edgesTest.add(exu);
        assertEquals(g.getEdges(), edgesTest);
        g.clear();
        edgesTest.clear();
        assertEquals(g.getEdges(), edgesTest);
    }

    @Test
    final void testGetDegreeOf() {
        Graph<String> g = new MatrixGraph<String>();
        GraphNode<String> ns = new GraphNode<String>("s");
        g.addNode("s");
        assertEquals(0, g.getDegreeOf(0));
        assertThrows(IndexOutOfBoundsException.class,
                () -> g.getDegreeOf(1));
        GraphNode<String> nu = new GraphNode<String>("u");
        g.addNode("u");
        GraphEdge<String> esu = new GraphEdge<String>(ns, nu, false);
        g.addEdge("s", "u");
        GraphNode<String> nx = new GraphNode<String>("x");
        g.addNode("x");
        GraphEdge<String> esx = new GraphEdge<String>(ns, nx, false, 5.12);
        g.addEdge("s", "x");
        GraphEdge<String> exu = new GraphEdge<String>(nx, nu, false, 3.04);
        g.addEdge("x", "u");
        GraphNode<String> ny = new GraphNode<String>("y");
        g.addNode("y");
        GraphEdge<String> exy = new GraphEdge<String>(nx, ny, false, 2.0);
        g.addEdge("x", "y");
        GraphEdge<String> eys = new GraphEdge<String>(ny, ns, false, 7.03);
        g.addEdge("y", "s");
        GraphNode<String> nw = new GraphNode<String>("w");
        g.addNode("w");
        GraphEdge<String> euw = new GraphEdge<String>(nu, nw, false, 7.07);
        g.addEdge("u", "w");
        GraphNode<String> nz = new GraphNode<String>("z");
        g.addNode("z");
        GraphEdge<String> ezy = new GraphEdge<String>(nz, ny, false, 7.107);
        g.addEdge("z", "y");
        assertEquals(3, g.getDegreeOf(g.getNodeIndexOf("s")));
        assertEquals(3, g.getDegreeOf(g.getNodeIndexOf("u")));
        assertEquals(3, g.getDegreeOf(g.getNodeIndexOf("x")));
        assertEquals(3, g.getDegreeOf(g.getNodeIndexOf("y")));
        assertEquals(1, g.getDegreeOf(g.getNodeIndexOf("z")));
        assertEquals(1, g.getDegreeOf(g.getNodeIndexOf("w")));
    }

}