package clique;
import java.util.ArrayList;

public class Graph {
    private ArrayList<Vertex> vertex_array = new ArrayList<Vertex>();

    public ArrayList<Vertex> getVertex_array () {
        return vertex_array;
    }

    public void add_vertex(Vertex name){
        if (find_postion_of_vertex(name) == -1) {
            vertex_array.add(name);
        }
    }

    public void add_edge(Vertex fst_name, Vertex snd_name) {
        int fst_postion = find_postion_of_vertex(fst_name);
        int snd_postion = find_postion_of_vertex(snd_name);

        if(fst_postion != -1 && snd_postion != -1) {

            vertex_array.get(fst_postion).addEdge(snd_name);
            vertex_array.get(snd_postion).addEdge(fst_name);
        }
    }

    private int find_postion_of_vertex(Vertex name) {
        int postion = -1;
        for (int i = 0; i < vertex_array.size(); i++) {
            if (vertex_array.get(i).getName().equals(name.getName())) {
                postion = i;
            }
        }

        return postion;
    }

    public int[][] represent_graph_adjacency_matrix () {
        int[][] adjacency_matrix = new int[vertex_array.size()][vertex_array.size()];

        for (int i = 0; i < vertex_array.size(); i++) {
            for (int j = 0; j < vertex_array.size(); j++) {
                adjacency_matrix[i][j] = 0;
            }
        }

        for (int i = 0; i < vertex_array.size(); i++) {
            Vertex x = vertex_array.get(i);
            for (int j = 0; j < x.get_friend_vertex_ArrayList().size(); j++) {
                int find_pos = find_postion_of_vertex(x.get_friend_vertex_ArrayList().get(j));
                adjacency_matrix[i][find_pos] = 1;
            }
        }

        return adjacency_matrix;
    }
}
