import java.io.File;
import java.util.Scanner;

import lib.TupleChar;
import lib.Node;
import lib.Queue;
import lib.Tuple;

public class Program {

    /* REPLACE THIS HORRIBLE BUBLE SORT  */
    public static void bubbleSort(TupleChar []vec){
        for (int i = 0; i < vec.length - 1; i++) {
            for (int j = 0; j < vec.length - 1; j++) {
                TupleChar aux;
                if (vec[j] == null) {
                    aux = vec[j];
                    vec[j] = vec[j + 1];
                    vec[j + 1] = aux;
                }
                else if (vec[j + 1] == null) 
                    continue;
                else if (vec[j].getValue() > vec[j + 1].getValue()) {
                    aux = vec[j];
                    vec[j] = vec[j + 1];
                    vec[j + 1] = aux;
                }
            }
        }
    }

    public static TupleChar[] buildMinHeap(TupleChar[] vec) {
        // Extract non-null elements
        TupleChar[] heap = new TupleChar[256];
        int n = 0;
        for (int i = 0; i < vec.length; i++) {
            if (vec[i] != null) {
                heap[n++] = vec[i];
            }
        }
        
        // Build min-heap through heapify-down process
        for (int i = n / 2 - 1; i >= 0; i--) {
            heapifyDown(heap, i, n);
        }
        
        return heap;
    }

    private static void heapifyDown(TupleChar[] heap, int i, int n) {
        int smallest = i;
        int left = 2 * i + 1;
        int right = 2 * i + 2;
        
        if (left < n && heap[left] != null && heap[left].getValue() < heap[smallest].getValue()) {
            smallest = left;
        }
        if (right < n && heap[right] != null && heap[right].getValue() < heap[smallest].getValue()) {
            smallest = right;
        }
        
        if (smallest != i) {
            TupleChar temp = heap[i];
            heap[i] = heap[smallest];
            heap[smallest] = temp;
            heapifyDown(heap, smallest, n);
        }
    }

    private static Node<Character>[] treeArray;
    private static int treeSize = 0;
    private static int maxIndex = 0;

    public static Node<Character>[] buildHuffmanTree(TupleChar[] characters) throws Exception {
        Queue<Node<Character>> nodeQueue = new Queue<>();
        
        // Create leaf nodes for each character and add to queue
        for (int i = 0; i < characters.length; i++) {
            if (characters[i] != null) {
                Node<Character> leaf = new Node<>(characters[i].getKey());
                leaf.setCount(characters[i].getValue());
                nodeQueue.push(leaf);
            }
        }
        
        // Build tree by combining two minimum frequency nodes
        while (nodeQueue.length() > 1) {
            // Extract all nodes and find two with minimum frequency
            Node<Character>[] nodes = new Node[nodeQueue.length()];
            for (int i = 0; i < nodes.length; i++) {
                nodes[i] = nodeQueue.pop();
            }
            
            // Find indices of two minimum nodes
            int min1Index = 0;
            int min2Index = 1;
            if (nodes[min2Index].getCount() < nodes[min1Index].getCount()) {
                int temp = min1Index;
                min1Index = min2Index;
                min2Index = temp;
            }
            
            for (int i = 2; i < nodes.length; i++) {
                if (nodes[i].getCount() < nodes[min1Index].getCount()) {
                    min2Index = min1Index;
                    min1Index = i;
                } else if (nodes[i].getCount() < nodes[min2Index].getCount()) {
                    min2Index = i;
                }
            }
            
            // Create parent node with combined frequency
            Node<Character> parent = new Node<>('\0');
            parent.setLeft(nodes[min1Index]);
            parent.setRight(nodes[min2Index]);
            parent.setCount(nodes[min1Index].getCount() + nodes[min2Index].getCount());
            
            // Add all remaining nodes and parent back to queue
            for (int i = 0; i < nodes.length; i++) {
                if (i != min1Index && i != min2Index) {
                    nodeQueue.push(nodes[i]);
                }
            }
            nodeQueue.push(parent);
        }
        
        // Return the root in array form
        Node<Character> root = nodeQueue.pop();
        
        // First pass: calculate the maximum index needed
        maxIndex = 0;
        calculateMaxIndex(root, 0);
        
        // Allocate array with exact size needed
        treeArray = new Node[maxIndex + 1];
        treeSize = 0;
        
        // Second pass: store tree in array
        storeTreeInArray(root, 0);
        return treeArray;
    }
    
    private static void calculateMaxIndex(Node<Character> node, int index) {
        if (node == null) {
            return;
        }
        maxIndex = Math.max(maxIndex, index);
        calculateMaxIndex(node.getLeft(), 2 * index + 1);
        calculateMaxIndex(node.getRight(), 2 * index + 2);
    }
    
    private static void storeTreeInArray(Node<Character> node, int index) {
        if (node == null) {
            return;
        }
        treeArray[index] = node;
        treeSize = Math.max(treeSize, index + 1);
        storeTreeInArray(node.getLeft(), 2 * index + 1);
        storeTreeInArray(node.getRight(), 2 * index + 2);
    }

    public static void printTree(Node<Character>[] treeArray) {
        if (treeArray == null || treeArray[0] == null) {
            System.out.println("Tree is empty");
            return;
        }
        printTreeHelper(0, "", true);
    }

    private static void printTreeHelper(int index, String prefix, boolean isLeft) {
        if (index >= treeArray.length || treeArray[index] == null) {
            return;
        }

        System.out.print(prefix);
        if (isLeft) {
            System.out.print("├── ");
        } else {
            System.out.print("└── ");
        }

        Node<Character> node = treeArray[index];
        if (node.getKey() == '\0') {
            System.out.println("(Internal) Freq: " + node.getCount());
        } else {
            System.out.println("'" + node.getKey() + "' Freq: " + node.getCount());
        }

        String newPrefix = prefix;
        if (isLeft) {
            newPrefix += "│   ";
        } else {
            newPrefix += "    ";
        }

        int leftIndex = 2 * index + 1;
        int rightIndex = 2 * index + 2;

        if (leftIndex < treeArray.length && treeArray[leftIndex] != null) {
            printTreeHelper(leftIndex, newPrefix, true);
        }

        if (rightIndex < treeArray.length && treeArray[rightIndex] != null) {
            printTreeHelper(rightIndex, newPrefix, false);
        }
    }

    private static Tuple<String, Boolean> search(Node<Character>[] huffmanHeap, Character target, int index){
        if (index >= huffmanHeap.length) {
            return new Tuple<String, Boolean>("", false);
        } 
        else if (huffmanHeap[index] != null && huffmanHeap[index].getKey() == target) {
            return new Tuple<String, Boolean>("", true);
        }

        Tuple<String, Boolean> left, right, res;

        left = search(huffmanHeap, target, 2 * index + 1);
        right = search(huffmanHeap, target, 2 * index + 2);

        boolean direction;
        if(left.getValue()){
            res = left;
            direction = false;
        }
        else if(right.getValue()){
            res = right;
            direction = true;
        }
        else 
            return left;
        res.setKey(res.getKey() + (direction ? "0" : "1"));

        return res;
    }

    public static void main(String[] args) {
        String paths[] = new String[1];
        String buffer;

        if(args.length == 0){
            System.out.println("Geben Sie eine Datei ein\t/\tInput a file\t/tEscolha um arquivo");
            Scanner input = new Scanner(System.in);
            paths[0] = input.nextLine();
        }
        else {
            paths = new String[args.length];
            for (int i = 0; i < args.length; i++) {
                paths[i] = args[i];
            }
        }

        TupleChar characters[] = new TupleChar[256]; 
        int totalChars = 0;
        String fileBuffer = "";
        for(String path : paths){
            File file = new File(path);
            try {                
                Scanner scanf = new Scanner(file);
                while (scanf.hasNextLine()) {
                    buffer = scanf.nextLine();
                    fileBuffer += buffer + "\n";
                    for (int i = 0; i < buffer.length(); i++) {
                        //System.out.printf("Char: %s\n", buffer.charAt(i));
                        //tree.insert(buffer.charAt(i));
                        TupleChar aux = characters[(int)buffer.charAt(i)];
                        if(aux == null){
                            characters[(int)buffer.charAt(i)] = new TupleChar(buffer.charAt(i), 0);
                            totalChars++;
                        }
                        else 
                            aux.setValue(aux.getValue() + 1);
                    }
                }
                scanf.close();

            } 
            catch (Exception err) {
                System.out.printf("Fehler beim Verarbeiten der Datei\t/\tError in handling file\t/\tError Gerenciando Arquivo:\n%s", err.getMessage());
                return;
            }
        }
        System.out.println("-----------------------");

        bubbleSort(characters);

        
        for (int i = 0; i < characters.length; i++) {
            if(characters[i] != null)
                System.out.println(characters[i]);
        }

        System.out.println("------------------");

        System.out.println(totalChars);

        try {
            Node<Character>[] huffmanTree = buildHuffmanTree(characters);
            
            System.out.println("\n-----------------------------");
            System.out.println("Huffman Tree built successfully!");
            if (huffmanTree[0] != null) {
                System.out.println("Root node frequency: " + huffmanTree[0].getCount());
            }
            System.out.println("Tree array size: " + treeSize);
            System.out.println("\nTree Structure (stored in array):");
            System.out.println("-----------------------------");
            printTree(huffmanTree);



        } catch (Exception e) {
            System.out.printf("Error building Huffman tree: %s\n", e.getMessage());
        }
    }
}