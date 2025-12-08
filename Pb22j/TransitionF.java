public class TransitionF {
    public Character alphabet;
    //public State[] state;
    public State from_state; 
    public State to_State;
     
TransitionF(State from_state, Character alphabet, State to_state) {
    this.from_state = from_state;
    this.alphabet = alphabet;
    this.to_State = to_state;

}

@Override
public String toString() {
    return "(" + from_state.getName() + ", " + alphabet + ", " + to_State.getName() + ")";
}

public Character getAlphabet() {
    return alphabet;
}

public void setAlphabet(Character alphabet) {
    this.alphabet = alphabet;
}

public State getFrom_state() {
    return from_state;
}

public void setFrom_state(State from_state) {
    this.from_state = from_state;
}

public State getTo_State() {
    return to_State;
}

public void setTo_State(State to_State) {
    this.to_State = to_State;
}







}