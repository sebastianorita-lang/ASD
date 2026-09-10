package it.unicam.cs.asdl2526.tutorato.pe1;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

/**
 * Classe di test per la classe PrimMSP.
 * 
 * @author Luca Tesei, Federico Di Petta(template)
 *
 */
class PrimMSPTest {

    @Test
    final void testExceptions() {
        PrimMSP<String> alg = new PrimMSP<String>();
        assertThrows(NullPointerException.class,
                () -> alg.computeMSP(null, null));
        Graph<String> gr = new MatrixGraph<String>();
        GraphNode<String> a = new GraphNode<String>("a");
        gr.addNode("a");
        assertThrows(NullPointerException.class,
                () -> alg.computeMSP(gr, null));
        assertThrows(NullPointerException.class, () -> alg.computeMSP(null, a));
        GraphNode<String> b = new GraphNode<String>("b");
        assertThrows(IllegalArgumentException.class,
                () -> alg.computeMSP(gr, b));
        gr.addNode("b");
        GraphNode<String> c = new GraphNode<String>("c");
        gr.addNode("c");
        GraphNode<String> d = new GraphNode<String>("d");
        gr.addNode("d");
        GraphNode<String> e = new GraphNode<String>("e");
        gr.addNode("e");
        GraphNode<String> f = new GraphNode<String>("f");
        gr.addNode("f");
        GraphNode<String> g = new GraphNode<String>("g");
        gr.addNode("g");
        GraphNode<String> h = new GraphNode<String>("h");
        gr.addNode("h");
        GraphNode<String> i = new GraphNode<String>("i");
        gr.addNode("i");
        gr.addWeightedEdge("a", "b", 4);
        gr.addWeightedEdge("a", "h", 8.5);
        gr.addWeightedEdge("b", "h", 11);
        gr.addWeightedEdge("b", "c", 8);
        gr.addWeightedEdge("c", "i", 2);
        gr.addWeightedEdge("c", "d", 7);
        gr.addWeightedEdge("c", "f", 4);
        gr.addWeightedEdge("d", "f", 14);
        gr.addEdge("d", "e");
        gr.addWeightedEdge("e", "f", 10);
        gr.addWeightedEdge("f", "g", 2);
        gr.addWeightedEdge("g", "i", 6);
        gr.addWeightedEdge("g", "h", 1);
        gr.addWeightedEdge("h", "i", 7);
        assertThrows(IllegalArgumentException.class,
                () -> alg.computeMSP(gr, b));
        gr.clear();
        gr.addNode("a");
        gr.addNode("b");
        gr.addNode("c");
        gr.addNode("d");
        gr.addNode("e");
        gr.addNode("f");
        gr.addNode("g");
        gr.addNode("h");
        gr.addNode("i");
        gr.addWeightedEdge("a", "b", 4);
        gr.addWeightedEdge("a", "h", 8.5);
        gr.addWeightedEdge("b", "h", 11);
        gr.addWeightedEdge("b", "c", 8);
        gr.addWeightedEdge("c", "i", 2);
        gr.addWeightedEdge("c", "d", 7);
        gr.addWeightedEdge("c", "f", 4);
        gr.addWeightedEdge("d", "f", 14);
        gr.addWeightedEdge("d", "e", 9);
        gr.addWeightedEdge("e", "f", 10);
        gr.addWeightedEdge("f", "g", 2);
        gr.addWeightedEdge("g", "i", -6);
        gr.addWeightedEdge("g", "h", 1);
        gr.addWeightedEdge("h", "i", 7);
        assertThrows(IllegalArgumentException.class,
                () -> alg.computeMSP(gr, b));
    }

    @Test
    final void testFindMSP1() {
        Graph<String> gr = new MatrixGraph<String>();
        gr.addNode("a");
        GraphNode<String> a = gr.getNode("a");
        gr.addNode("b");
        GraphNode<String> b = gr.getNode("b");
        gr.addNode("c");
        GraphNode<String> c = gr.getNode("c");
        gr.addNode("d");
        GraphNode<String> d = gr.getNode("d");
        gr.addNode("e");
        GraphNode<String> e = gr.getNode("e");
        gr.addNode("f");
        GraphNode<String> f = gr.getNode("f");
        gr.addNode("g");
        GraphNode<String> g = gr.getNode("g");
        gr.addNode("h");
        GraphNode<String> h = gr.getNode("h");
        gr.addNode("i");
        GraphNode<String> i = gr.getNode("i");
        gr.addWeightedEdge("a", "b", 4);
        gr.addWeightedEdge("a", "h", 8.5);
        gr.addWeightedEdge("b", "h", 11);
        gr.addWeightedEdge("b", "c", 8);
        gr.addWeightedEdge("c", "i", 2);
        gr.addWeightedEdge("c", "d", 7);
        gr.addWeightedEdge("c", "f", 4);
        gr.addWeightedEdge("d", "f", 14);
        gr.addWeightedEdge("d", "e", 9);
        gr.addWeightedEdge("e", "f", 10);
        gr.addWeightedEdge("f", "g", 2);
        gr.addWeightedEdge("g", "i", 6);
        gr.addWeightedEdge("g", "h", 1);
        gr.addWeightedEdge("h", "i", 7);
        PrimMSP<String> alg = new PrimMSP<String>();
        alg.computeMSP(gr, a);
        assertTrue(a.getPrevious() == null);
        assertTrue(b.getPrevious() == a);
        assertTrue(c.getPrevious() == b);
        assertTrue(d.getPrevious() == c);
        assertTrue(e.getPrevious() == d);
        assertTrue(f.getPrevious() == c);
        assertTrue(g.getPrevious() == f);
        assertTrue(h.getPrevious() == g);
        assertTrue(i.getPrevious() == c);
    }

    @Test
    final void testFindMSP2() {
        Graph<String> gr = new MatrixGraph<String>();
        gr.addNode("a");
        gr.addNode("b");
        gr.addNode("c");
        gr.addNode("d");
        GraphNode<String> a = gr.getNode("a");
        GraphNode<String> b = gr.getNode("b");
        GraphNode<String> c = gr.getNode("c");
        GraphNode<String> d = gr.getNode("d");
        gr.addWeightedEdge("a", "b", 1);
        gr.addWeightedEdge("a", "c", 5);
        gr.addWeightedEdge("b", "d", 2);
        gr.addWeightedEdge("b", "c", 3);
        gr.addWeightedEdge("c", "d", 4);
        PrimMSP<String> alg = new PrimMSP<String>();
        alg.computeMSP(gr, a);
        assertTrue(a.getPrevious() == null);
        assertTrue(b.getPrevious() == a);
        assertTrue(c.getPrevious() == b);
        assertTrue(d.getPrevious() == b);
    }

    @Test
    final void testFindMSP3() {
        Graph<String> gr = new MatrixGraph<String>();
        gr.addNode("a");
        gr.addNode("b");
        gr.addNode("c");
        gr.addNode("d");
        GraphNode<String> a = gr.getNode("a");
        GraphNode<String> b = gr.getNode("b");
        GraphNode<String> c = gr.getNode("c");
        GraphNode<String> d = gr.getNode("d");
        gr.addWeightedEdge("a", "b", 1);
        gr.addWeightedEdge("a", "c", 5);
        gr.addWeightedEdge("b", "d", 2);
        gr.addWeightedEdge("b", "c", 3);
        gr.addWeightedEdge("c", "d", 4);
        PrimMSP<String> alg = new PrimMSP<String>();
        alg.computeMSP(gr, b);
        assertTrue(a.getPrevious() == b);
        assertTrue(b.getPrevious() == null);
        assertTrue(c.getPrevious() == b);
        assertTrue(d.getPrevious() == b);
    }

    @Test
    final void testFindMSP4() {
        Graph<String> gr = new MatrixGraph<String>();
        gr.addNode("a");
        gr.addNode("b");
        gr.addNode("c");
        gr.addNode("d");
        GraphNode<String> a = gr.getNode("a");
        GraphNode<String> b = gr.getNode("b");
        GraphNode<String> c = gr.getNode("c");
        GraphNode<String> d = gr.getNode("d");
        gr.addWeightedEdge("a", "b", 1);
        gr.addWeightedEdge("a", "c", 5);
        gr.addWeightedEdge("b", "d", 2);
        gr.addWeightedEdge("b", "c", 3);
        gr.addWeightedEdge("c", "d", 4);
        PrimMSP<String> alg = new PrimMSP<String>();
        alg.computeMSP(gr, c);
        assertTrue(a.getPrevious() == b);
        assertTrue(b.getPrevious() == c);
        assertTrue(c.getPrevious() == null);
        assertTrue(d.getPrevious() == b);
    }

    @Test
    final void testFindMSP5() {
        Graph<String> gr = new MatrixGraph<String>();
        gr.addNode("a");
        gr.addNode("b");
        gr.addNode("c");
        gr.addNode("d");
        GraphNode<String> a = gr.getNode("a");
        GraphNode<String> b = gr.getNode("b");
        GraphNode<String> c = gr.getNode("c");
        GraphNode<String> d = gr.getNode("d");
        gr.addWeightedEdge("a", "b", 1);
        gr.addWeightedEdge("a", "c", 5);
        gr.addWeightedEdge("b", "d", 2);
        gr.addWeightedEdge("b", "c", 3);
        gr.addWeightedEdge("c", "d", 4);
        PrimMSP<String> alg = new PrimMSP<String>();
        alg.computeMSP(gr, d);
        assertTrue(a.getPrevious() == b);
        assertTrue(b.getPrevious() == d);
        assertTrue(c.getPrevious() == b);
        assertTrue(d.getPrevious() == null);
    }

    @Test
    final void testFindMSP6() {
        Graph<String> gr = new MatrixGraph<String>();
        gr.addNode("a");
        gr.addNode("b");
        gr.addNode("c");
        gr.addNode("d");
        gr.addNode("e");
        GraphNode<String> a = gr.getNode("a");
        GraphNode<String> b = gr.getNode("b");
        GraphNode<String> c = gr.getNode("c");
        GraphNode<String> d = gr.getNode("d");
        GraphNode<String> e = gr.getNode("e");
        gr.addWeightedEdge("a", "c", 3);
        gr.addWeightedEdge("b", "c", 10);
        gr.addWeightedEdge("c", "d", 2);
        gr.addWeightedEdge("c", "e", 6);
        gr.addWeightedEdge("b", "d", 4);
        gr.addWeightedEdge("d", "e", 1);
        gr.addWeightedEdge("b", "b", 7);
        gr.addWeightedEdge("c", "c", 0);
        PrimMSP<String> alg = new PrimMSP<String>();
        alg.computeMSP(gr, b);
        assertTrue(a.getPrevious() == c);
        assertTrue(a.getFloatingPointDistance() == 3.0);
        assertTrue(a.getColor() == GraphNode.COLOR_BLACK);
        assertTrue(b.getPrevious() == null);
        assertTrue(b.getFloatingPointDistance() == 0);
        assertTrue(b.getColor() == GraphNode.COLOR_BLACK);
        assertTrue(c.getPrevious() == d);
        assertTrue(c.getFloatingPointDistance() == 2.0);
        assertTrue(c.getColor() == GraphNode.COLOR_BLACK);
        assertTrue(d.getPrevious() == b);
        assertTrue(d.getFloatingPointDistance() == 4.0);
        assertTrue(d.getColor() == GraphNode.COLOR_BLACK);
        assertTrue(e.getPrevious() == d);
        assertTrue(e.getFloatingPointDistance() == 1.0);
        assertTrue(e.getColor() == GraphNode.COLOR_BLACK);
    }

    @Test
    final void testFindMSP7() {
        Graph<String> gr = new MatrixGraph<String>();
        PrimMSP<String> alg = new PrimMSP<String>();
        gr.addNode("a");
        GraphNode<String> a = gr.getNode("a");
        alg.computeMSP(gr, a);
        assertTrue(a.getPrevious() == null);
        assertTrue(a.getFloatingPointDistance() == 0);
        assertTrue(a.getColor() == GraphNode.COLOR_BLACK);
        gr.addNode("b");
        GraphNode<String> b = gr.getNode("b");
        alg.computeMSP(gr, a);
        assertTrue(a.getPrevious() == null);
        assertTrue(a.getFloatingPointDistance() == 0);
        assertTrue(a.getColor() == GraphNode.COLOR_BLACK);
        assertTrue(b.getPrevious() == null);
        assertTrue(b.getFloatingPointDistance() == Double.POSITIVE_INFINITY);
        assertTrue(b.getColor() == GraphNode.COLOR_BLACK);
        gr.addNode("c");
        GraphNode<String> c = gr.getNode("c");
        gr.addWeightedEdge("a", "b", 3);
        alg.computeMSP(gr, a);
        assertTrue(a.getPrevious() == null);
        assertTrue(a.getFloatingPointDistance() == 0);
        assertTrue(a.getColor() == GraphNode.COLOR_BLACK);
        assertTrue(b.getPrevious() == a);
        assertTrue(b.getFloatingPointDistance() == 3);
        assertTrue(b.getColor() == GraphNode.COLOR_BLACK);
        assertTrue(c.getPrevious() == null);
        assertTrue(c.getFloatingPointDistance() == Double.POSITIVE_INFINITY);
        assertTrue(c.getColor() == GraphNode.COLOR_BLACK);
    }
}
