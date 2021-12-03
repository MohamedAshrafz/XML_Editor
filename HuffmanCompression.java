import java.io.*;
import java.util.*;

public class HuffmanCompression {
    private static final int DATA_RANGE = 256;
    private static final char NEWLINE = '\n';

    private HuffmanCompression() {
    }

    public static File compress(File f) {

        // build the char[] input
        char[] input = toCharArr(f);


        int[] freq = new int[DATA_RANGE];
        for (int i = 0; i < input.length; i++)
            freq[input[i]]++;

        // build Huffman trie
        Node root = buildTrie(freq);

        // build code table
        String[] symbolTable = new String[DATA_RANGE];
        buildCodeTable(symbolTable, root, "");

        File output = new File("D:\\Data structure and Algorithms\\summar training" +
                "\\sources\\Data Structure and Algorithm Project\\src\\fileOutput.txt");

        try (FileOutputStream fos = new FileOutputStream(output);
             DataOutputStream dos = new DataOutputStream(fos)) {

            // write trie in output file for decoding
            writeTrie(root, dos);

        } catch (IOException e) {
            e.printStackTrace();
        }


        //

        return f;
    }

    private static Node buildTrie(int[] freq) {
        // using priority queue
        PriorityQueue<Node> pqNodes = new PriorityQueue<>();

        for (char c = 0; c < DATA_RANGE; c++)
            if (freq[c] > 0)
                pqNodes.add(new Node(c, freq[c], null, null));

        while (pqNodes.size() > 1) {
            Node left = pqNodes.poll();
            Node right = pqNodes.poll();
            Node parent = new Node('\0', left.freq + right.freq, left, right);
            pqNodes.add(parent);
        }
        return pqNodes.poll();
    }

    private static void buildCodeTable(String[] symbolTable, Node node, String code) {
        if (!node.isLeaf()) {
            buildCodeTable(symbolTable, node.left, code + '0');
            buildCodeTable(symbolTable, node.right, code + '1');
        } else {
            symbolTable[node.ch] = code;
        }
    }

    private static void writeTrie(Node node, DataOutputStream dos) throws IOException {
        if (node.isLeaf()) {
            dos.writeBoolean(true);
        } else {

        }
    }

    private static class Node implements Comparable<Node> {

        private final char ch;
        private final int freq;
        private final Node left, right;

        Node(char ch, int freq, Node left, Node right) {
            this.ch = ch;
            this.freq = freq;
            this.left = left;
            this.right = right;
        }

        @Override
        public int compareTo(Node that) {
            return Integer.compare(this.freq, that.freq);
        }

        private boolean isLeaf() {
            return ((this.left == null) && (this.right == null));
        }
    }

    private static char[] toCharArr(File f) {
        StringBuilder strBuilder = new StringBuilder();

        InputStream console = System.in;

        try {
            System.setIn(new FileInputStream(f));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        BufferedInputStream in = new BufferedInputStream(System.in);

        // System.setIn(console);
        return ((char)in.read()).toCharArray();
    }


    public static void main(String[] args) {

        HuffmanCompression.compress(new File("D:\\Data structure and Algorithms\\summar training\\"
                + "sources\\Data Structure and Algorithm Project\\src\\file.txt"));


    }
}
