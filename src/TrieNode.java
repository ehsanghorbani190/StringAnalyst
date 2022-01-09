import java.util.ArrayList;

public class TrieNode {
    private boolean valid;
    private final char data;
    TrieNode[] childs;
    TrieNode(char ch ){
        this.data = ch;
        this.valid = false;
        this.childs = new TrieNode[26];
        for (int i = 0; i < 26; i++) {
            this.childs[i] = null;
        }
    }

    public boolean valid(){
        return this.valid;
    }
    public void validate(){
        this.valid = true;
    }
    public void addChild(TrieNode child){
        this.childs[child.data - 'a'] = child;
    }
    public TrieNode getChild(char ch){
        return this.childs[ch - 'a'];
    }
    public boolean hasChild(){
        for (int i = 0; i < 26; i++) {
            if(this.childs[i] != null) return true;
        }
        return false;
    }
    public boolean childExists(char ch){
        return this.childs[ch - 'a'] != null;
    }
    public char data(){
        return this.data;
    }

}
