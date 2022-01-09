public class Rope {
    class Node {
        Node left, right;
        int data;
        String string = null;

    }

    Node root;



    public void Insert(String string) {
        int q = string.length();
        String s[] = string.split("_");
        for (int i = 0; i < s.length - 1; i++) {
            s[i] = s[i] + "_";
        }
        int a = 0;

        for (int i = 0; i < s.length; i++) {
            if (root.left == null) {
                root.left.string = s[i];
                root.left.data = s[i].length();

            } else if (root.right == null) {
                root.right.string = s[i];
                root.right.data = s[i].length();

            } else if (root.right.string != null && a + s[i].length() < q / 2) {
                Node w = new Node();
                w.left = root.right;
                w.right.string = s[i];
                w.right.data = s[i].length();
                root.right = w;

            } else {
                Node w = new Node();
                w.left = root;
                root = w;
                root.right.string = s[i];
                root.right.data = s[i].length();

            }
            a = a + s[i].length();
        }
    }


    public static void main(String[] args) {
        System.out.println();
    }
}



