import java.util.List;

public class SetOfAlphabets {
    private List<Character> alphabets;
    
    public SetOfAlphabets(List<Character> alphabets){
        this.alphabets = alphabets;
    }
    
    public int inAlphabet(char a){
        for(int i = 0; i < alphabets.size(); i++){
            if(alphabets.get(i).equals(a))
                return i;   
        }
        return -1;
    }
    
    public boolean contains(char a) {
        return alphabets.contains(a);
    }
    
    public List<Character> getAlphabets() {
        return alphabets;
    }
    
    public void setAlphabets(List<Character> alphabets) {
        this.alphabets = alphabets;
    }
    
    public int size() {
        return alphabets.size();
    }
    
    @Override
    public String toString() {
        return "Alphabet: " + alphabets;
    }
}