public class State {
    public String name;
    public State[] next;
    public Alphabet alphabet;
    public String IsFinal; //to See if The State is Fianl or not
    public State(String name, Alphabet alphabets){
  
        this.name=name;
        next= new State[alphabets.ListOfChar.length];
        this.alphabet=alphabets; 
    }

    public boolean addState(char a, State next){
        int indexAlphabet=alphabet.inAlphabet(a);
            if(indexAlphabet==-1){
                System.out.println(a+" not In the Alphabet");
                return false;
            }
            this.next[indexAlphabet]=next;
        return true;
    }
}
