package clique;
import java.util.ArrayList;
import java.util.Collections;

public class Clique_problem {

    private ArrayList<Vertex> super_group;
    private ArrayList<ArrayList<Vertex>> list_of_clique = new ArrayList<ArrayList<Vertex>>();

    public void find_max(Graph graph) {
        ArrayList<Vertex> partial_result = new ArrayList<Vertex>();
        ArrayList<Vertex> ommited_vertex = new ArrayList<Vertex>();
        ArrayList<Vertex> candidate_to_result = new ArrayList<Vertex>();
        candidate_to_result.addAll(graph.getVertex_array());

        find_max_clique(partial_result, candidate_to_result, ommited_vertex);
        super_group = find_best_super_group();
    }

    public String super_group_names() {
        if (super_group.size() != 0) {
            return lexicographic_order_array(super_group);
        } else {
            return "";
        }
    }

    public int super_group_size() {
        if (super_group != null) {
            return super_group.size();
        }
        else {
            return -1;
        }
    }

    private void find_max_clique(ArrayList<Vertex> partial_result, ArrayList<Vertex> candidate_to_result, ArrayList<Vertex> ommited_vertex){
        if(candidate_to_result.size() == 0 && ommited_vertex.size() == 0) {
            list_of_clique.add(partial_result);
        }

        for (int i = 0; i < candidate_to_result.size(); i++) {
            Vertex v = candidate_to_result.get(i);
            ArrayList<Vertex> candidate_to_result_prim = new ArrayList<Vertex>(candidate_to_result);
            ArrayList<Vertex> partial_result_prim = new ArrayList<Vertex>(partial_result);
            ArrayList<Vertex> ommited_vertex_prim = new ArrayList<Vertex>(ommited_vertex);
            partial_result_prim.add(v);
            candidate_to_result_prim = intersect(candidate_to_result_prim, v.get_friend_vertex_ArrayList());
            ommited_vertex_prim = intersect(ommited_vertex_prim, v.get_friend_vertex_ArrayList());
            find_max_clique(partial_result_prim, candidate_to_result_prim, ommited_vertex_prim);
            candidate_to_result.remove(v);
            ommited_vertex.add(v);
        }
    }

    ArrayList<Vertex> intersect(ArrayList<Vertex> fst_array, ArrayList<Vertex> snd_array) {
        ArrayList<Vertex> intersect_array = new ArrayList<Vertex>(fst_array);
        intersect_array.retainAll(snd_array);
        return intersect_array;
    }

    private ArrayList<Vertex> find_best_super_group() {
        ArrayList<Vertex> best_super_group = new ArrayList<Vertex>();
        if (list_of_clique.size() != 0) {
            best_super_group = list_of_clique.get(0);
        }
        for (int i = 1; i < list_of_clique.size(); i++) {
            if (list_of_clique.get(i).size() > best_super_group.size()) {
                best_super_group = list_of_clique.get(i);
            }
            else if (list_of_clique.get(i).size() == best_super_group.size()) {
                if (calcluate_number_of_friends_outside_group(list_of_clique.get(i)) > calcluate_number_of_friends_outside_group(best_super_group)) {
                    best_super_group = list_of_clique.get(i);
                }
                else if (calcluate_number_of_friends_outside_group(list_of_clique.get(i)) == calcluate_number_of_friends_outside_group(best_super_group)) {
                    best_super_group = lexicographic_order_choose_better(best_super_group, list_of_clique.get(i));
                }
            }
        }

        return best_super_group;
    }

    private int calcluate_number_of_friends_outside_group(ArrayList<Vertex> list) {
        int number_friends = 0;
        for (int i = 0; i < list.size(); i++) {
            number_friends = number_friends + list.get(i).get_friend_vertex_ArrayList().size() - list.size() + 1;
        }
        return number_friends;
    }

    private ArrayList<Vertex> lexicographic_order_choose_better(ArrayList<Vertex> fst_array, ArrayList<Vertex> sndArray) {
        ArrayList<Vertex> bigger_array = fst_array;
        for (int i = 0; i < fst_array.size(); i++) {
            if (fst_array.get(i).getName().compareToIgnoreCase(sndArray.get(i).getName()) > 0) {
                bigger_array = sndArray;
                return bigger_array;
            }
        }

        return bigger_array;
    }

    private String lexicographic_order_array (ArrayList<Vertex> vertexArrayList) {
        ArrayList<String> node_to_name_array = new ArrayList<String>();
        for (int i = 0; i < vertexArrayList.size(); i++) {
            node_to_name_array.add(vertexArrayList.get(i).getName());
        }
        Collections.sort(node_to_name_array);

        String full_group_string = "";
        for (int i = 0; i < node_to_name_array.size(); i++) {
            full_group_string = full_group_string + node_to_name_array.get(i) + " ";
        }
        return full_group_string;
    }


}
