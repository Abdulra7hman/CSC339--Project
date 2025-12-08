public class State {
    private String name;
    private boolean isFinal; 
    private boolean isStart; 
    
    public State(String name){
        this.name = name;
        this.isFinal = false; 
        this.isStart = false; 
    }
   
    public State(String name, boolean isFinal, boolean isStart){
        this.name = name;
        this.isFinal = isFinal;
        this.isStart = isStart;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isFinal() {
        return isFinal;
    }

    public void setFinal(boolean isFinal) {
        this.isFinal = isFinal;
    }

    public boolean isStart() {
        return isStart;
    }

    public void setStart(boolean isStart) {
        this.isStart = isStart;
    }

    @Override
    public String toString() {
        String type = "";
        if(isStart && isFinal) type = " [START, FINAL]";
        else if(isStart) type = " [START]";
        else if(isFinal) type = " [FINAL]";
        
        return "State " + name + type;
    }

    @Override
    public boolean equals(Object obj) {
        if(this == obj) return true;
        if(obj == null || getClass() != obj.getClass()) return false;
        State state = (State) obj;
        return name.equals(state.name);
    }

    @Override
    public int hashCode() {
        return name.hashCode();
    }
}