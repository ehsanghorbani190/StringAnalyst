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
        String s[] = string.split(" ");
        for (int i = 0; i < s.length - 1; i++) {
            s[i] = s[i] + " ";
        }
        int a = 0;

        for (int i = 0; i < s.length; i++) {
            if (root.left == null) {
                root.left = new Node();
                root.left.string = s[i];
                root.left.data = s[i].length();

            } else if (root.right == null) {
                root.right = new Node();
                root.right.string = s[i];
                root.right.data = s[i].length();

            } else if (root.right.string != null && a + s[i].length() < q / 2) {
                Node w = new Node();
                w.left = root.right;
                w.right = new Node();
                w.right.string = s[i];
                w.right.data = s[i].length();
                root.right = w;

            } else {
                Node w = new Node();
                w.right = new Node();
                w.left = root;
                root = w;
                root.right.string = s[i];
                root.right.data = s[i].length();

            }
            a = a + s[i].length();
        }
    }
    public void print(){
        rprint(root);
        System.out.println();
    }
    private void rprint(Node node){
        if(node == null) return;
        if(node.string != null) System.out.print(node.string);
        rprint(node.left);
        rprint(node.right);
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



