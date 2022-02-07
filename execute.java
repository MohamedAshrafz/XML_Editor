import java.io.IOException;
import java.lang.Runtime;
public class execute{
    public static void main (String args[]) throws IOException{
        Runtime.getRuntime().exec("dot -Tpng -O graph.dot");
    }
}
