import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        // ========== ALPHABET ==========
        System.out.print("Enter the number of alphabets: ");
        int numA = scanner.nextInt();

        List<Character> Alphabets = new ArrayList<>();
        for(int i = 0; i < numA; i++) {
            System.out.print("Enter letter " + (i+1) + ": ");
            String input = scanner.next();
            char alphabet = input.charAt(0); 
            Alphabets.add(alphabet);
        }
        Alphabets.add('#'); // Lambda/Epsilon added automatically
        
        SetOfAlphabets alphabetC = new SetOfAlphabets(Alphabets); 
        System.out.println(alphabetC);
        

        // ========== STATES ==========
        System.out.print("\nEnter the number of States: ");
        int numS = scanner.nextInt();

        List<State> states = new ArrayList<>();
        System.out.print("States: ");
        for(int i = 0; i < numS; i++) { 
            states.add(new State(String.valueOf(i+1)));
        }
        for(State s : states) System.out.print(s.getName() + " ");
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
        
        // ========== TRANSITIONS ==========
        List<TransitionF> transitions = new ArrayList<>();
        System.out.println("\nEnter transitions (format: from_state, symbol, to_state)");
        System.out.println("Use # for Lambda/Epsilon transitions.");
        
        while (true) {
            System.out.println("\n--- New Transition ---");
            
            // From state
            System.out.print("From state (1-" + numS + "): ");
            int fromIdx = scanner.nextInt() - 1;
            State from_State = states.get(fromIdx); 

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
            int toIdx = scanner.nextInt() - 1;
            State to_state = states.get(toIdx); 

            transitions.add(new TransitionF(from_State, symbol, to_state));
            System.out.println("Transition added: " + from_State.getName() + 
                             " --" + symbol + "--> " + to_state.getName());

            System.out.print("\nAdd another transition? (yes/no): ");
            String response = scanner.next();
            if (response.equalsIgnoreCase("no") || 
                response.equalsIgnoreCase("n") ||
                response.equalsIgnoreCase("done")) {
                break;
            }
        }

        System.out.println("\n========== NFA Configured ==========");
        System.out.println("Machine is ready. You can now test multiple strings.");

        while (true) {
            System.out.print("\n============================================\n");
            System.out.print("Enter input string (or type 'exit' to quit): ");
            String inputString = scanner.next();

            if (inputString.equalsIgnoreCase("exit")) {
                System.out.println("Exiting simulation.");
                break;
            }

            System.out.println("\nProcessing string: " + inputString);

            // 1. Reset Current States to Start State(s)
            Set<State> currentStates = new HashSet<>();
            for(State s : states) {
                if(s.isStart()) currentStates.add(s);
            }

            // 2. Initial Epsilon Closure
            currentStates = getEpsilonClosure(currentStates, transitions);
            System.out.print("Start states (w/ epsilon): { ");
            for(State s : currentStates) System.out.print(s.getName() + " ");
            System.out.println("}");

            // 3. Process string
            for (char symbol : inputString.toCharArray()) {
                Set<State> nextStates = new HashSet<>();

                for (State s : currentStates) {
                    for (TransitionF t : transitions) {
                        if (t.getFrom_state().equals(s) && t.getAlphabet().equals(symbol)) {
                            nextStates.add(t.getTo_State());
                        }
                    }
                }

                // Epsilon Closure on new states
                currentStates = getEpsilonClosure(nextStates, transitions);

                System.out.print("Symbol: " + symbol + " -> States: { ");
                for(State s : currentStates) System.out.print(s.getName() + " ");
                System.out.println("}");
            }

            // 4. Result
            boolean isAccepted = false;
            for (State s : currentStates) {
                if (s.isFinal()) {
                    isAccepted = true;
                    break;
                }
            }

            if (isAccepted) {
                System.out.println("RESULT: Input Accepted");
            } else {
                System.out.println("RESULT: Input Rejected");
            }
        }

        scanner.close();
    }

    // Helper method for Epsilon Closure
    private static Set<State> getEpsilonClosure(Set<State> states, List<TransitionF> transitions) {
        Set<State> closure = new HashSet<>(states);
        Stack<State> stack = new Stack<>();
        stack.addAll(states);

        while (!stack.isEmpty()) {
            State current = stack.pop();
            for (TransitionF t : transitions) {
                if (t.getFrom_state().equals(current) && t.getAlphabet().equals('#')) {
                    State next = t.getTo_State();
                    if (!closure.contains(next)) {
                        closure.add(next);
                        stack.push(next);
                    }
                }
            }
        }
        return closure;
    }
}
