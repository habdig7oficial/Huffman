import java.util.Scanner;

public class Program {
    public static void main(String[] args) {
        String files[] = new String[1];

        if(args.length == 0){
            System.out.println("Geben Sie eine Datei ein\t/\tInput a file\t/tEscolha um arquivo");
            Scanner input = new Scanner(System.in);
            files[0] = input.nextLine();
        }
        else {
            files = new String[args.length];
            for (int i = 0; i < args.length; i++) {
                files[i] = args[i];
            }
        }

        for(String file : files){
            System.out.println(file);
        }

    }
}