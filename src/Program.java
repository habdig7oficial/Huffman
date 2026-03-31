import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.ObjectOutputStream;
import java.util.Scanner;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;

import lib.TupleChar;
import lib.Node;
import lib.Queue;
import lib.Tuple;

public class Program {

    /* REPLACE THIS HORRIBLE BUBLE SORT */
    public static void bubbleSort(TupleChar[] vec) {
        for (int i = 0; i < vec.length - 1; i++) {
            for (int j = 0; j < vec.length - 1; j++) {
                TupleChar aux;
                if (vec[j] == null) {
                    aux = vec[j];
                    vec[j] = vec[j + 1];
                    vec[j + 1] = aux;
                } else if (vec[j + 1] == null)
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

    private static Tuple<Byte[], Boolean> search(Node<Character>[] huffmanHeap, Character target, int index, int iter) {
        if (index >= huffmanHeap.length)
            return new Tuple<Byte[], Boolean>(new Byte[iter], false);
        else if (huffmanHeap[index] != null && (int) huffmanHeap[index].getKey() == (int) target) {
            return new Tuple<Byte[], Boolean>(new Byte[iter], true);
        }

        Tuple<Byte[], Boolean> left, right, res;

        left = search(huffmanHeap, target, 2 * index + 1, iter + 1 );
        right = search(huffmanHeap, target, 2 * index + 2, iter + 1 );

        boolean direction;
        if (left.getValue()) {
            res = left;
            direction = true;
        } else if (right.getValue()) {
            res = right;
            direction = false;
        } else
            return new Tuple<Byte[], Boolean>(new Byte[iter], false);

        Byte[] byte_arr = res.getKey();
        byte_arr[iter] = (byte) (direction ? 1 : 0);
        
        res.setKey(byte_arr); 

        System.out.println(target);

        return res;
    }

    public static void main(String[] args) {
        String paths[] = new String[1];
        String buffer;

        if (args.length == 0) {
            System.out.println("Geben Sie eine Datei ein\t/\tInput a file\t/tEscolha um arquivo");
            Scanner input = new Scanner(System.in);
            paths[0] = input.nextLine();
        } else {
            paths = new String[args.length];
            for (int i = 0; i < args.length; i++) {
                paths[i] = args[i];
            }
        }

        TupleChar characters[] = new TupleChar[256];
        int totalChars = 0;
        String fileBuffer = "";
        for (String path : paths) {
            File file = new File(path);
            try {
                Scanner scanf = new Scanner(file, StandardCharsets.UTF_8);
                while (scanf.hasNextLine()) {
                    buffer = scanf.nextLine();
                    fileBuffer += buffer + "\n";
                    for (int i = 0; i < buffer.length(); i++) {
                        // System.out.printf("Char: %s\n", buffer.charAt(i));
                        // tree.insert(buffer.charAt(i));
                        TupleChar aux = characters[(int) buffer.charAt(i)];
                        if (aux == null) {
                            characters[(int) buffer.charAt(i)] = new TupleChar(buffer.charAt(i), 1);
                            totalChars++;
                        } else
                            aux.setValue(aux.getValue() + 1);
                    }
                }
                scanf.close();

            } catch (Exception err) {
                System.out.printf(
                        "Fehler beim Verarbeiten der Datei\t/\tError in handling file\t/\tError Gerenciando Arquivo:\n%s",
                        err.getMessage());
                return;
            }

            System.out.println("-----------------------");

            bubbleSort(characters);

            for (int i = 0; i < characters.length; i++) {
                if (characters[i] != null)
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

                File compressedFile = new File(path + ".huff");
                FileOutputStream writer = new FileOutputStream(compressedFile);

                ObjectOutputStream treeHeader = new ObjectOutputStream(writer);

                treeHeader.writeObject(huffmanTree);
                treeHeader.flush();
                
                for (int i = 0; i < fileBuffer.length(); i++) {
                    Tuple<Byte[], Boolean> s = search(huffmanTree, fileBuffer.charAt(i), 0, 0);
                    System.out.printf("%c' %b\n", fileBuffer.charAt(i), s.getValue());

                    for (byte b : s.getKey()) {
                        System.out.printf("%d",(int)b);
                        writer.write(b);      
                    }


                    System.out.println("\n-----");
                }



                System.out.println("\n-----------------------------");

                writer.close();
                
            } catch (Exception e) {
                System.out.printf("Error building Huffman tree: %s\n", e.getMessage());
            }
        }
    }
}