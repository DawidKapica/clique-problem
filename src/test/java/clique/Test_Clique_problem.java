package clique;
import org.junit.Test;
import org.junit.Assert;
import java.util.ArrayList;

public class Test_Clique_problem {

    @Test
    public void Test_graph_create() {
        Graph graph = new Graph();
        Vertex Ala = new Vertex("Ala");
        Vertex Kasia = new Vertex("Kasia");
        Vertex Lukasz = new Vertex("Lukasz");
        Vertex Jan = new Vertex("Jan");

        graph.add_vertex(Ala);
        graph.add_vertex(Kasia);
        graph.add_vertex(Lukasz);
        graph.add_vertex(Jan);

        graph.add_edge(Ala, Kasia);
        graph.add_edge(Kasia, Lukasz);
        graph.add_edge(Ala, Lukasz);
        graph.add_edge(Ala, Jan);

        int [][] represent_matrix = graph.represent_graph_adjacency_matrix();

        Assert.assertEquals(represent_matrix[1][1], 0);
        Assert.assertEquals(represent_matrix[2][2], 0);
        Assert.assertEquals(represent_matrix[3][3], 0);
        Assert.assertEquals(represent_matrix[0][1], 1);
        Assert.assertEquals(represent_matrix[1][0], 1);
        Assert.assertEquals(represent_matrix[1][2], 1);
        Assert.assertEquals(represent_matrix[2][1], 1);

        graph.add_vertex(Ala);
        graph.add_vertex(Kasia);
        graph.add_vertex(Lukasz);
        graph.add_vertex(Jan);

        graph.add_edge(Ala, Kasia);
        graph.add_edge(Kasia, Lukasz);
        graph.add_edge(Ala, Lukasz);
        graph.add_edge(Ala, Jan);

        Assert.assertEquals(represent_matrix[1][1], 0);
        Assert.assertEquals(represent_matrix[2][2], 0);
        Assert.assertEquals(represent_matrix[3][3], 0);
        Assert.assertEquals(represent_matrix[0][1], 1);
        Assert.assertEquals(represent_matrix[1][0], 1);
        Assert.assertEquals(represent_matrix[1][2], 1);
        Assert.assertEquals(represent_matrix[2][1], 1);
    }

    @Test
    public void Test_add_edge() {
        Vertex vertex0 = new Vertex("0_point");
        Vertex vertex1 = new Vertex("1_point");
        Vertex vertex2 = new Vertex("2_point");
        Vertex vertex3 = new Vertex("3_point");

        Graph graph = new Graph();
        graph.add_vertex(vertex0);
        graph.add_vertex(vertex1);
        graph.add_vertex(vertex2);
        graph.add_vertex(vertex3);

        graph.add_edge(vertex0, vertex1);
        graph.add_edge(vertex0, vertex2);

        Assert.assertEquals(vertex0.get_friend_vertex_ArrayList().size(), 2);
        Assert.assertEquals(graph.getVertex_array().get(0).get_friend_vertex_ArrayList().size(), 2);
        Assert.assertEquals(graph.getVertex_array().get(1).get_friend_vertex_ArrayList().size(), 1);
        Assert.assertEquals(graph.getVertex_array().get(2).get_friend_vertex_ArrayList().size(), 1);
        Assert.assertEquals(graph.getVertex_array().get(3).get_friend_vertex_ArrayList().size(), 0);
    }

    @Test
    public void Test_clique_problem() {
        Graph g = new Graph();
        Vertex Luiza = new Vertex("Luiza");
        Vertex Marcin = new Vertex("Marcin");
        Vertex Lukasz = new Vertex("Lukasz");
        Vertex Kasia = new Vertex("Kasia");
        Vertex Aneta = new Vertex("Aneta");

        g.add_vertex(Luiza);
        g.add_vertex(Marcin);
        g.add_vertex(Lukasz);
        g.add_vertex(Kasia);
        g.add_vertex(Aneta);

        g.add_edge(Luiza, Marcin);
        g.add_edge(Kasia, Aneta);
        g.add_edge(Luiza, Kasia);
        g.add_edge(Luiza, Aneta);
        g.add_edge(Marcin, Lukasz);
        g.add_edge(Lukasz, Luiza);

        Clique_problem cq = new Clique_problem();
        ArrayList<Vertex> P = new ArrayList<Vertex>();
        P.addAll(g.getVertex_array());
        ArrayList<Vertex> R = new ArrayList<Vertex>();
        ArrayList<Vertex> X = new ArrayList<Vertex>();


        cq.find_max(g);
        Assert.assertEquals(cq.super_group_size(), 3);
        Assert.assertEquals(cq.super_group_names(),"Aneta Kasia Luiza ");
    }
}
