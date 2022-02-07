import java.io.*;
import java.util.ArrayList;

public class graph_representation {
    public ArrayList<ArrayList<Integer>> extract_IDs(String xml_input) {
        ArrayList<ArrayList<Integer>> list = new ArrayList<>();
        ArrayList<Integer> sub_list = new ArrayList<>();
        xml_input = Formatting.format(xml_input);
        xml_input = xml_input.replaceAll("\\s", "");
        for (int i = 0; i < xml_input.length(); i++) {
            if (xml_input.charAt(i) == '<' && xml_input.charAt(i + 1) == 'i' && xml_input.charAt(i + 2) == 'd' && xml_input.charAt(i + 3) == '>') {
                char c = xml_input.charAt(i + 4);
                int c_integer = Integer.parseInt(String.valueOf(c));
                sub_list.add(c_integer);
            }
            if (xml_input.charAt(i) == '<' && xml_input.charAt(i + 1) == '/' && xml_input.charAt(i + 2) == 'u' && xml_input.charAt(i + 3) == 's' && xml_input.charAt(i + 4) == 'e' && xml_input.charAt(i + 5) == 'r' && xml_input.charAt(i + 6) == '>') {
                list.add(new ArrayList<>(sub_list));
                sub_list.clear();
            }
        }
        return list;
    }
    void writeDot(ArrayList<ArrayList<Integer>> list) throws IOException{
        File file = new File("graph.dot");
        if(!file.exists()) {
            file.createNewFile();
        }
        PrintWriter pw = new PrintWriter(file);
        pw.println("digraph {");
        for(int i = 0 ; i < list.size(); i++){
            ArrayList<Integer> small_list = list.get(i);
            for(int j = 0 ; j < small_list.size(); j++){
                if((j + 1) < small_list.size()){
                    pw.println(small_list.get(j + 1) + " -> " + small_list.get(0));
                }
            }
        }
        pw.println("}");
        pw.close();
    }

    public static void main(String args[]) throws IOException {
        String s = "<users>\n" +
                "    <user>\n" +
                "        <id>1</id>\n" +
                "        <name>Ahmed Ali</name>\n" +
                "        <posts>\n" +
                "            <post>\n" +
                "                <body>\n" +
                "                    Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat.\n" +
                "                </body>\n" +
                "                <topics>\n" +
                "                    <topic>\n" +
                "                        economy\n" +
                "                    </topic>\n" +
                "                    <topic>\n" +
                "                        finance\n" +
                "                    </topic>\n" +
                "                </topics>\n" +
                "            </post>\n" +
                "            <post>\n" +
                "                <body>\n" +
                "                    Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat.\n" +
                "                </body>\n" +
                "                <topics>\n" +
                "                    <topic>\n" +
                "                        solar_energy\n" +
                "                    </topic>\n" +
                "                </topics>\n" +
                "            </post>\n" +
                "        </posts>\n" +
                "        <followers>\n" +
                "            <follower>\n" +
                "                <id>2</id>\n" +
                "            </follower>\n" +
                "            <follower>\n" +
                "                <id>3</id>\n" +
                "            </follower>\n" +
                "        </followers>\n" +
                "    </user>\n" +
                "    <user>\n" +
                "        <id>2</id>\n" +
                "        <name>Yasser Ahmed</name>\n" +
                "        <posts>\n" +
                "            <post>\n" +
                "                <body>\n" +
                "                    Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat.\n" +
                "                </body>\n" +
                "                <topics>\n" +
                "                    <topic>\n" +
                "                        education\n" +
                "                    </topic>\n" +
                "                </topics>\n" +
                "            </post>\n" +
                "        </posts>\n" +
                "        <followers>\n" +
                "            <follower>\n" +
                "                <id>1</id>\n" +
                "            </follower>\n" +
                "        </followers>\n" +
                "    </user>\n" +
                "    <user>\n" +
                "        <id>3</id>\n" +
                "        <name>Mohamed Sherif</name>\n" +
                "        <posts>\n" +
                "            <post>\n" +
                "                <body>\n" +
                "                    Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat.\n" +
                "                </body>\n" +
                "                <topics>\n" +
                "                    <topic>\n" +
                "                        sports\n" +
                "                    </topic>\n" +
                "                </topics>\n" +
                "            </post>\n" +
                "        </posts>\n" +
                "        <followers>\n" +
                "            <follower>\n" +
                "                <id>1</id>\n" +
                "            </follower>\n" +
                "        </followers>\n" +
                "    </user>\n" +
                "</users>";
        graph_representation g = new graph_representation();
        ArrayList<ArrayList<Integer>> listx = g.extract_IDs(s);
        for(int i = 0 ; i < listx.size(); i++) {
            System.out.println(listx.get(i));
        }
        g.writeDot(listx);
    }
}
