import java.util.ArrayList;
import java.util.List;

public class Rope {
    class Node {
        Node left = null, right = null;
        int data;
        String string = null;

    }

    Node root;

    Rope() {
        this.root = new Node();
    }

    private Node add(String[] string, Node node, int start, int end) {
        int q = end - start;
        if (q == 1) {
            node.string = string[start];
            node.data = string[start].length();

        } else {
            int p = (q % 2 == 0) ? q / 2 : q / 2 + 1;
            node.left = add(string, new Node(), start, p + start);
            node.right = add(string, new Node(), p + start, end);
            node.data = weight(node.left);

        }
        return node;


    }

    public void make(String str) {
        String[] strs = str.split(" ");
        for (int i = 0; i < strs.length - 1; i++) {
            strs[i] = strs[i] + " ";
        }

        add(strs, root, 0, strs.length);

    }

    public void print() {
        rprint(root);
        System.out.println();
    }

    private void rprint(Node node) {
        if (node == null) return;
        if (node.string != null) System.out.print(node.string);
//        else System.out.println(weight(node));
        rprint(node.left);
        rprint(node.right);
    }

    private void string(String str, Node node) {
        if (node == null) return;
        if (node.string != null) str += node.string;
        string(str, node.left);
        string(str, node.right);
    }

    @Override
    public String toString() {
        String res = "";
        string(res, root);
        return res;
    }

    public void concat(Rope s) {
        Node w = new Node();
        w.left = root;
        w.data = weight(w.left);
        w.right = s.root;
        root = w;
    }


    public void insert(int index, String s2) {
        String s1 = this.toString();
        System.out.println(s1.length());
//        String result = "";
//        result += s1.substring(0, index + 1);
//
//        result += s2;
//        result += s1.substring(index);
        // make(result);
    }

    public Rope split(int index) {
        index++;
        Rope result = new Rope();
        Node temp = root, rtemp = result.root, w;
        while (temp.string == null) {
            if (index >= temp.data) {
                index -= temp.data;
                temp = temp.right;
            } else {
                rtemp.right = root.right;
                rtemp.left = new Node();
                rtemp = rtemp.left;
                temp = temp.left;
                root = temp;
            }
        }
        w = new Node();
        w.string = temp.string.substring(index);
        w.data = w.string.length();
        rtemp.left = w;
        temp.string = temp.string.substring(0, index);
        temp.data = temp.string.length();
        result.root.data = weight(result.root.left);
        deleteEmpties(root);
        deleteEmpties(result.root);
        rebalance(result.root.left);
        return result;
    }

    public int weight(Node node) {
        if (node == null) return 0;
        if (node.string != null) return node.data;
        return weight(node.right) + weight(node.left);
    }


    private void rebalance(Node node) {
        if (node == null) return;
        if (node.string == null) node.data = weight(node.left);
        rebalance(node.left);
        rebalance(node.right);
    }

    private void deleteEmpties(Node node) {
        if (node == null || node.string != null) return;
        if (node.right != null && node.right.string != null && node.right.string.equals("")) node.right = null;
        if (node.left != null && node.left.string != null && node.left.string.equals("")) node.left = null;

        deleteEmpties(node.right);
        deleteEmpties(node.left);
    }


    public static void printTree(Node root) {
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

        r.make("Hello Ehsan How Are You Doing These Days");
        Rope.printTree(r.root);
        r.split(15);
        Rope.printTree(r.root);


    }
}



