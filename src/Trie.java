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

    public void words(TrieNode node, ArrayList<String> result, String str) {
        if (node == null) return;
        else if (node.valid()) result.add(str + node.data());
        for (int i = 0; i < 26; i++) {
            words(node.getChild(i), result, str + node.data());
        }
    }

    public String[] guess(String str) {
        TrieNode p = root;
        ArrayList<String> result = new ArrayList<String>();
        char[] chars = str.toCharArray();
        for (char ch : chars) {
            p = p.getChild(ch);
            if (p == null) return null;
        }
        words(p, result, str.substring(0, str.length() - 1));
        return new String[]{result.get(0), result.get(1), result.get(2)};
    }
}
