import java.util.ArrayList;
import java.util.List;

public class Rope {
    class Node {
        Node left = null, right = null;
        int data;
        String string = null;

    }

    Node root;

    Rope(){
        this.root = new Node();
    }
    public void make(String string) {
        int q = string.length();
        String[] s = string.split(" ");
        for (int i = 0; i < s.length - 1; i++) {
            s[i] = s[i] + " ";
        }
        int a = 0;

        for (String str : s) {
            if (root.left == null) {
                root.left = new Node();
                root.left.string = str;
                root.left.data = str.length();
                root.data = weight(root.left);

            } else if (root.right == null) {
                root.right = new Node();
                root.right.string = str;
                root.right.data = str.length();

            } else if (root.right.string != null && a + str.length() < q / 2) {
                Node w = new Node();
                w.left = root.right;
                w.right = new Node();
                w.right.string = str;
                w.right.data = str.length();
                w.data = weight(w.left);
                root.right = w;

            } else {
                Node w = new Node();
                w.right = new Node();
                w.left = root;
                root = w;
                w.right.string = str;
                w.right.data = str.length();
                w.data = weight(w.left);
            }
            a += str.length();
        }
    }
    public void print(){
        rprint(root);
        System.out.println();
    }
    private void rprint(Node node){
        if(node == null) return;
        if(node.string != null) System.out.print(node.string);
//        else System.out.println(weight(node));
        rprint(node.left);
        rprint(node.right);
    }
    public int weight(Node node){
        if(node == null) return 0;
        if(node.string != null) return node.data;
        return weight(node.right) + weight(node.left);
    }

    public static void printTree(Node root)
    {
        List<List<String>> lines = new ArrayList<List<String>>();

        List<Node> level = new ArrayList<Node>();
        List<Node> next = new ArrayList<Node>();

        level.add(root);
        int nn = 1;

        int widest = 0;

        while (nn != 0) {
            List<String> line = new ArrayList<String>();

            nn = 0;

            for (Node n : level) {
                if (n == null) {
                    line.add(null);

                    next.add(null);
                    next.add(null);
                } else {
                    String aa = n.string != null ? n.string : String.valueOf(n.data);
                    line.add(aa);
                    if (aa.length() > widest) widest = aa.length();

                    next.add(n.left);
                    next.add(n.right);

                    if (n.left != null) nn++;
                    if (n.right != null) nn++;
                }
            }

            if (widest % 2 == 1) widest++;

            lines.add(line);

            List<Node> tmp = level;
            level = next;
            next = tmp;
            next.clear();
        }

        int perpiece = lines.get(lines.size() - 1).size() * (widest + 4);
        for (int i = 0; i < lines.size(); i++) {
            List<String> line = lines.get(i);
            int hpw = (int) Math.floor(perpiece / 2f) - 1;

            if (i > 0) {
                for (int j = 0; j < line.size(); j++) {

                    // split node
                    char c = ' ';
                    if (j % 2 == 1) {
                        if (line.get(j - 1) != null) {
                            c = (line.get(j) != null) ? '┴' : '┘';
                        } else {
                            if (j < line.size() && line.get(j) != null) c = '└';
                        }
                    }
                    System.out.print(c);

                    // lines and spaces
                    if (line.get(j) == null) {
                        for (int k = 0; k < perpiece - 1; k++) {
                            System.out.print(" ");
                        }
                    } else {

                        for (int k = 0; k < hpw; k++) {
                            System.out.print(j % 2 == 0 ? " " : "─");
                        }
                        System.out.print(j % 2 == 0 ? "┌" : "┐");
                        for (int k = 0; k < hpw; k++) {
                            System.out.print(j % 2 == 0 ? "─" : " ");
                        }
                    }
                }
                System.out.println();
            }

            // print line of numbers
            for (int j = 0; j < line.size(); j++) {

                String f = line.get(j);
                if (f == null) f = "";
                int gap1 = (int) Math.ceil(perpiece / 2f - f.length() / 2f);
                int gap2 = (int) Math.floor(perpiece / 2f - f.length() / 2f);

                // a number
                for (int k = 0; k < gap1; k++) {
                    System.out.print(" ");
                }
                System.out.print(f);
                for (int k = 0; k < gap2; k++) {
                    System.out.print(" ");
                }
            }
            System.out.println();

            perpiece /= 2;
        }
    }
    public static void main(String[] args) {
        Rope r = new Rope();
        r.make("Hello This is a test String, How Are You?");
        r.print();
        Rope r2 = new Rope();
        r2.make("Hello This is a test 2");
        r2.print();

    }
}



