public class Trie {
    private final TrieNode root;

    Trie() {
        this.root = new TrieNode('\0');
    }

    public void insert(String str) {
        char[] chars = str.toCharArray();
        TrieNode p = this.root, temp;
        for (char ch : chars) {
            if (!p.childExists(ch)) {
                temp = new TrieNode(ch);
                p.addChild(temp);
            }
            p = p.getChild(ch);
        }
        p.validate();
    }


}
