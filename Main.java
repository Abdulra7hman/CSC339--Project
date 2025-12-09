import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        // ========== ALPHABET ==========
        System.out.print("Enter the number of alphabets: ");
        int numA = scanner.nextInt();

        List<Character> Alphabets = new ArrayList<>();
        for(int i = 0; i < numA; i++) {
            System.out.print("Enter letter " + (i+1) + ": ");
            char alphabet = scanner.next().charAt(0); 
            Alphabets.add(alphabet);
        }
        Alphabets.add('#'); // Lambda/Epsilon
        
        SetOfAlphabets alphabetC = new SetOfAlphabets(Alphabets); 
        System.out.println(alphabetC);
        

        // ========== STATES ==========
        System.out.print("\nEnter the number of States: ");
        int numS = scanner.nextInt();

        List<State> states = new ArrayList<>();
        System.out.print("States: ");
        for(int i = 0; i < numS; i++) { 
            System.out.print(" " + (i+1));
            states.add(new State(String.valueOf(i+1)));
        }
        System.out.println();

        
        // ========== START STATE ==========
        System.out.print("\nEnter the start state number: ");
        int startIndex = scanner.nextInt() - 1;
        states.get(startIndex).setStart(true);
        System.out.println("Start state: " + states.get(startIndex).getName());

        
        // ========== FINAL STATE(S) ==========
        System.out.print("\nHow many final states? ");
        int numFinal = scanner.nextInt();
        for(int i = 0; i < numFinal; i++) {
            System.out.print("Enter final state " + (i+1) + " number: ");
            int finalIndex = scanner.nextInt() - 1;
            states.get(finalIndex).setFinal(true);
        }
        System.out.print("Final states: ");
        for(State s : states) {
            if(s.isFinal()) System.out.print(s.getName() + " ");
        }
        System.out.println();

        
        // ========== TRANSITIONS ==========
        List<TransitionF> transitions = new ArrayList<>();
        System.out.println("\nEnter transitions (format: from_state, symbol, to_state)");
        
        while (true) {
            System.out.println("\n--- New Transition ---");
            
            // From state
            System.out.print("From state (1-" + numS + "): ");
            State from_State = states.get(scanner.nextInt() - 1); 

            // Symbol
            System.out.print("Symbol: ");
            char tempS = scanner.next().charAt(0);
            int indexS = alphabetC.inAlphabet(tempS);
            
            while(indexS == -1) {
                System.out.print("Symbol not in alphabet! Enter again: ");
                tempS = scanner.next().charAt(0);
                indexS = alphabetC.inAlphabet(tempS);
            }
            Character symbol = Alphabets.get(indexS); 
           
            // To state
            System.out.print("To state (1-" + numS + "): ");
            State to_state = states.get(scanner.nextInt() - 1); 

            transitions.add(new TransitionF(from_State, symbol, to_state));
            System.out.println("Transition added: " + from_State.getName() + 
                             " --" + symbol + "--> " + to_state.getName());

            // Continue?
            System.out.print("\nAdd another transition? (yes/no): ");
            String response = scanner.next();
            if (response.equalsIgnoreCase("no") || 
                response.equalsIgnoreCase("n") ||
                response.equalsIgnoreCase("done")) {
                break;
            }
        }

        
        // ========== DISPLAY ALL TRANSITIONS ==========
        System.out.println("\n========== NFA Summary ==========");
        System.out.println("Alphabet: " + alphabetC);
        System.out.println("States: " + states.size());
        System.out.print("Start: ");
        for(State s : states) {
            if(s.isStart()) System.out.print(s.getName() + " ");
        }
        System.out.println();
        System.out.print("Final: ");
        for(State s : states) {
            if(s.isFinal()) System.out.print(s.getName() + " ");
        }
        System.out.println("\n\nTransitions:");
        for (TransitionF t : transitions) { 
            System.out.println("  " + t.toString());
        }
        
        System.out.println("=================================");


        // ========== STRING INPUT ========== (The problem is here)
        System.out.print("\nEnter a string to test: ");
        String inputString = scanner.next();
        System.out.println("Input string: " + inputString);

        while(inputString.isEmpty()) {   
            System.out.println("Input is empty. Please try again.");
            inputString = scanner.next();
        }

        System.out.println("\n========== DFA SIMULATION ==========");
        State current = states.get(startIndex); //To SIMULATE an NFA, you need a list of current states. For each input symbol, you collect all possible next states from all current states, and those become your new current states.
        System.out.println("Initial state: " + current.getName());

        for(int i = 0; i < inputString.length(); i++) {
            char symbol = inputString.charAt(i);
            boolean found = false;
            
            for(int j = 0; j < transitions.size(); j++) {
                if(current.equals(transitions.get(j).getFrom_state()) && 
                symbol == transitions.get(j).getAlphabet()) {
                    current = transitions.get(j).getTo_State();
                    found = true;
                    break;
                }
            }
            
            if(!found) {
                System.out.println("Read '" + symbol + "' => No transition! REJECTED");
                scanner.close();
                return;
            }
            
            System.out.println("Read '" + symbol + "' => State: " + current.getName());
        }

        System.out.println("\nLast state: " + current.getName());

        if(current.isFinal()) {
            System.out.println(" INPUT ACCEPTED");
        } else {
            System.out.println(" INPUT REJECTED");
        }
        
        
        scanner.close();
    }
}


