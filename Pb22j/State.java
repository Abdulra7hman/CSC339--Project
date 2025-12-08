public class State {
    public String name;
    public State next;
    public Alphabet alphabet;
    public State(String name, Alphabet alphabet){
        this.name=name;
        this.alphabet=alphabet;
    }
    public boolean addState(char a, State next){
        
        if(!alphabet.inAlphabet(a)){
            System.out.println(a +" Is Not in the Alphabet");    
            return false;
        }      
        return true;
    }
}
