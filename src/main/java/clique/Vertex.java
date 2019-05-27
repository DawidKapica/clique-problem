package clique;
import java.util.ArrayList;

public class Vertex {

    private String name;
    private ArrayList<Vertex> vertex_friend_ArrayList = new ArrayList<Vertex>();

    public Vertex (String name){
        this.name = name;
    }

    public String getName () {
        return name;
    }

    public ArrayList<Vertex> get_friend_vertex_ArrayList () {
        return vertex_friend_ArrayList;
    }

    public void addEdge (Vertex vertex) {
        vertex_friend_ArrayList.add(vertex);
    }
}
