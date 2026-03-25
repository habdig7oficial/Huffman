import java.io.File;
import java.util.Scanner;

import lib.Tuple;

public class Program {

    /* REPLACE THIS HORRIBLE BUBLE SORT  */
    public static void bubbleSort(Tuple []vec){
        for (int i = 0; i < vec.length - 1; i++) {
            for (int j = 0; j < vec.length - 1; j++) {
                Tuple aux;
                if (vec[j] == null) {
                    aux = vec[j];
                    vec[j] = vec[j + 1];
                    vec[j + 1] = aux;
                }
                else if (vec[j + 1] == null) 
                    continue;
                else if (vec[j].getValue() < vec[j + 1].getValue()) {
                    aux = vec[j];
                    vec[j] = vec[j + 1];
                    vec[j + 1] = aux;
                }
            }
        }
    }

    public static void heap(Tuple []vec, Tuple []heap, int step, int pos){
        if (step > vec.length) {
            return;
        }
        int left = vec[step] == null?vec[step].getValue() : 0;
        int right = vec[step + 1] == null?vec[step + 1].getValue() : 0;
        heap[pos] = new Tuple('\0',  left + right );
        if (vec.length - step <= 2) {
            heap[2 * pos + 1] = vec[step];
            heap[2 * pos + 2] = vec[step + 1];
            return;
        }
        heap[2 * pos + 1] = vec[step];
        System.out.println(vec[step]);
        System.out.printf("%s - %d\n", heap[2 * pos + 1], 2 * pos + 1);
        heap(vec, heap, step + 1, 2 * pos + 2);
        
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

        Tuple characters[] = new Tuple[256]; 
        int totalChars = 0;

        for(String path : paths){
            File file = new File(path);
            try {                
                Scanner scanf = new Scanner(file);
                while (scanf.hasNextLine()) {
                    buffer = scanf.nextLine();
                    for (int i = 0; i < buffer.length(); i++) {
                        //System.out.printf("Char: %s\n", buffer.charAt(i));
                        //tree.insert(buffer.charAt(i));
                        Tuple aux = characters[(int)buffer.charAt(i)];
                        if(aux == null){
                            characters[(int)buffer.charAt(i)] = new Tuple(buffer.charAt(i), 0);
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
            }
        }
        System.out.println("-----------------------");

        bubbleSort(characters);

        
        for (int i = 0; i < characters.length; i++) {
            System.out.println(characters[i]);
        }

        System.out.println("------------------");

        Tuple []minHeap = new Tuple[2 * totalChars - 1];

        //heap(characters, minHeap, 0, 0);

    }
}