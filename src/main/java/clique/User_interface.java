package clique;

import java.util.ArrayList;
import java.util.Scanner;

public class User_interface {
    private static int number_of_persons = 0;
    private static int number_of_relation = 0;


    public void program_execute(){
        Scanner scanner = new Scanner(System.in);
        Graph graph = new Graph();

        getNumber_of_persons_and_relation();
        ArrayList<String> input_string_names_relation = get_relations();


        ArrayList<Vertex> vertices = new ArrayList<Vertex>();
        for (int i = 0; i<input_string_names_relation.size(); i++) {
            Vertex nowy = new Vertex(input_string_names_relation.get(i));
            vertices.add(nowy);
        }

        for (int i = 0; i< vertices.size(); i++) {
            graph.add_vertex(vertices.get(i));
        }

        if (graph.getVertex_array().size() !=number_of_persons) {
            System.out.println("niezgodna liczba podanych osob");
            program_execute();
            return;
        }

        for (int i = 0; i< vertices.size(); i = i+2) {
            graph.add_edge(vertices.get(findFirst(vertices, vertices.get(i))), vertices.get(findFirst(vertices, vertices.get(i+1))));
        }

        Clique_problem clique_problem2 = new Clique_problem();
        clique_problem2.find_max(graph);
        System.out.println(clique_problem2.super_group_size());
        System.out.println(clique_problem2.super_group_names());
    }


    private void getNumber_of_persons_and_relation() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("podaj liczbe osob a nastepnie liczbe relacji oddzielone spacja");

        boolean fst_number_type_ok = true;
        boolean snd_number_typ_ok = true;
        if (scanner.hasNextInt()) {
            number_of_persons = scanner.nextInt();
        }
        else {
            System.out.println("podane wartosci sa nieprawidlowe, wpisz ponownie");
            scanner.nextLine();
            fst_number_type_ok = false;
            getNumber_of_persons_and_relation();
            return;
        }

        if (scanner.hasNextInt() && fst_number_type_ok == true) {
            number_of_relation = scanner.nextInt();
        }
        else if(fst_number_type_ok == true) {
            System.out.println("podane wartosci sa nieprawidlowe, wpisz ponownie");
            scanner.nextLine();
            snd_number_typ_ok = false;
            getNumber_of_persons_and_relation();
            return;
        }

        if ((number_of_persons<2 || number_of_persons>18 || number_of_relation<1 || number_of_relation > 100)&& fst_number_type_ok == true && snd_number_typ_ok == true) {
            System.out.println("podane wartosci sa nieprawidlowe, wpisz ponownie");
            getNumber_of_persons_and_relation();
        }
    }

    private ArrayList<String> get_relations() {
        System.out.println("Podaj relacje miedzy osobami");
        ArrayList<String> relations_array = new ArrayList<String>();
        for (int i = 0; i<number_of_relation; i++) {
            String[] names = relation_line();
            relations_array.add(names[0]);
            relations_array.add(names[1]);
        }
        return relations_array;
    }

    private String[] relation_line() {
        Scanner scanner = new Scanner(System.in);
        String line = scanner.nextLine();
        String names[] = line.split(" ");
        if (names.length < 2 || names[0].equals(names[1])) {
            System.out.println("podana relacja nieprawidlowa, podaj ja jeszcze raz");
            return relation_line();
        }
        return names;
    }

    private int findFirst(ArrayList<Vertex> vertexArrayList, Vertex vertex) {
        int index = 0;
        for (int i = 0; i < vertexArrayList.size(); i++) {
            if (vertexArrayList.get(i).getName().equals(vertex.getName())) {
                index = i;
                return index;
            }
        }
        return  index;
    }

}
