import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        ArrayList<Rope> ropes = new ArrayList<Rope>();
        Trie trie = new Trie();
        Scanner trieInput = new Scanner(new FileInputStream("./src/input.txt"));
        while (trieInput.hasNextLine()) trie.insert(trieInput.nextLine());
        trieInput.close();
        Scanner in = new Scanner(System.in);
        String command;
        do {
            System.out.print(">> ");
            command = in.nextLine();
            String[] cp = command.split(" ");
            command = cp[0];
            if (command.equalsIgnoreCase("new")) {
                StringBuilder str = new StringBuilder();
                for (int i = 1; i < cp.length - 1; i++) {
                    str.append(cp[i] + " ");
                }
                str.append(cp[cp.length - 1]);
                str.deleteCharAt(0);
                str.deleteCharAt(str.length() - 1);
                Rope r = new Rope();
                r.make(str.toString());
                ropes.add(r);
            } else if (command.equalsIgnoreCase("status")) {
                for (int i = 0; i < ropes.size(); i++) {
                    System.out.println((i + 1) + ". " + ropes.get(i).toString());
                }
            } else if (command.equalsIgnoreCase("concat")) {
                ropes.get(Integer.parseInt(cp[1]) - 1).concat(ropes.get(Integer.parseInt(cp[2]) - 1));
                ropes.remove(Integer.parseInt(cp[2]) - 1);
            } else if (command.equalsIgnoreCase("split")) {
                Rope temp = ropes.get(Integer.parseInt(cp[1]) - 1).split(Integer.parseInt(cp[2]) - 1);
                ropes.add(temp);
            } else if (command.equalsIgnoreCase("index")) {
                ropes.get(Integer.parseInt(cp[1]) - 1).index(Integer.parseInt(cp[2]));
            } else if (command.equalsIgnoreCase("delete")) {
                ropes.get(Integer.parseInt(cp[1]) - 1).delete(Integer.parseInt(cp[2]) - 1, Integer.parseInt(cp[3]) - 1);
            } else if (command.equalsIgnoreCase("insert")) {
                ropes.get(Integer.parseInt(cp[1]) - 1).insert(Integer.parseInt(cp[2]), ropes.get(Integer.parseInt(cp[3]) - 1).toString());
            } else if (command.equalsIgnoreCase("tree")) {
                for (Rope r :
                        ropes) {
                    Rope.printTree(r.root);
                }
            } else if (command.equalsIgnoreCase("autocomplete")) {
                String[] g = trie.guess(cp[1]);
                if(g == null || g.length == 0 ) {
                    System.out.println("No word found in trie with that prefix!");
                    continue;
                }
                for (int i = 0; i < g.length; i++) {
                    System.out.println((i + 1) + ". " + g[i]);
                }
                System.out.print("Enter your choice to make a new rope of: ");
                int n = in.nextInt();
                Rope r = new Rope();
                r.make(g[n - 1]);
                ropes.add(r);
            } else if (command.equalsIgnoreCase("exit")) break;
            else if (command.equalsIgnoreCase("")) {
            }
            else {
                System.out.println("**INVALID COMMAND**");
            }
        } while (!command.equalsIgnoreCase("exit"));
    }
}
