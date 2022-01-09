public class Rope {
    class Node {
        Node left, right;
        int data;
        String string = null;

    }

    Node root;

    public void insert(String string) {
        int j;

        int q = string.length();
        String s[] = string.split("_");
        for (int i = 0; i < s.length - 1; i++) {
            s[i] = s[i] + "_";
        }
        for (j = 0; j <= q; j++) {
            while (a < q / 2) {
                if (left == null) {
                    Node v = new Node();//2
                    v.left.insert(a, string);//2
                    string = null;
                    j++;
                    string = s[j];
                    a = a + s[j].length();
                    right.insert(a, string);//3
                    if (a < q / 2) {
                        left.left = v;
                        Node k = new Node();//2
                        left = k;
                        if (k.right != null) {
                            j++;
                            k.right.insert(a, string);
                            string = s[j];
                            a = a + s[j].length();
                        }
                    }
                }
            }

            while (a > q/2) {
                if (j == q - 1) {
                    right.insert(a, string);
                }
                Node ll = new Node();//2
                right = ll;
                j++;
                string = s[j];
                a = a + s[j].length();
                ll.left.insert(a, string);

                if (j < q - 1) {
                    Node M = new Node();
                    ll.right = M;
                    j++;
                    string = s[j];
                    a = a + s[j].length();
                    ll.left.insert(a, string);
                } else {
                    j++;
                    string = s[j];
                    a = a + s[j].length();
                    ll.right.insert(a, string);
                }
            }
        }
    }
    public void Insert(String string  ){
        int q = string.length();
        String s[] = string.split("_");
        for (int i = 0; i < s.length - 1; i++) {
            s[i] = s[i] + "_";
        }
        for (int i = 0; i < s.length; i++)
            if(root.left==null){
          root.left.string = s[i];
          root.left.data = s[i].length();
        }else if (root.right==null ){
                root.right.string = s[i];
                root.right.data = s[i].length();
        }else if(root.right.string != null ){
                if()
            }
        else{
                Node w = new Node();
                w.left = root;
                root = w;
                root.right.string = s[i];
                root.right.data = s[i].length();
            }

    }

    public static void main(String[] args) {
        System.out.println();
    }
}



