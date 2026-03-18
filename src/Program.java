import java.io.File;
import java.util.Scanner;

import lib.Table;

public class Program {
    public static void main(String[] args) {
        String paths[] = new String[1];
        String buffer;
        Table<Character> table = new Table<Character>();

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

        for(String path : paths){
            File file = new File(path);

            try {                
                Scanner scanf = new Scanner(file);
                while (scanf.hasNextLine()) {
                    buffer = scanf.nextLine();
                    for (int i = 0; i < buffer.length(); i++) {
                        System.out.printf("Char: %s\n", buffer.charAt(i));
                        table.insert(buffer.charAt(i));
                    }
                }
                scanf.close();
            } catch (Exception err) {
                System.out.printf("Fehler beim Verarbeiten der Datei\t/\tError in handling file\t/\tError Gerenciando Arquivo:\n%s", err.getMessage());
            }
        }

    }
}