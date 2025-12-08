import java.util.List;

public class Alphabet {
    public List<Character> Alphabets;
    public Alphabet(List<Character> alphabets){
        this.Alphabets=Alphabets;
    }
    public boolean inAlphabet(char a){
        for(int i=0;i<Alphabets.size();i++){
            if(Alphabets.get(i).equals(a))
                return true;   
        }
        return false;
    }

}
