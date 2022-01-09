public class Rope {
    class Node {
        Node left, right;
        public void insert(int a, String string) {
            int j;
            String str = "i_am_rope_data_structure";
            int q = str.length();
            String s[] = str.split("_");
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

                while (a > q) {
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
    }
    public static void main(String[] args){
        System.out.println();
    }
}



