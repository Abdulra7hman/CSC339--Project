

public class Alphabet {
    public char[] ListOfChar;

    public Alphabet(char[] alphabets){
        this.ListOfChar=alphabets;
    }
    public Alphabet(){}
    public int inAlphabet(char a){
        for(int i=0;i<ListOfChar.length;i++){
            if(ListOfChar[i]==a)
                return i;   
        }
        return -1;
    }

}
