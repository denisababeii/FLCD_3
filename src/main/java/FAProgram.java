import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;

public class FAProgram {
    ArrayList<String> statesSet;
    ArrayList<String> alphabet;
    ArrayList<String> finalStates;
    ArrayList<Triple> transitions;

    public FAProgram() {
        statesSet = new ArrayList<>();
        alphabet = new ArrayList<>();
        finalStates = new ArrayList<>();
        transitions = new ArrayList<>(); // !
    }
    private void getElements(String file) throws Exception {
        BufferedReader reader;
        try {
            reader = new BufferedReader(new FileReader(file));
            String line = reader.readLine();
            int lineCount=1;
            while(line!=null) {
                if(lineCount == 1) { // if I am on the first line, where the states are enumerated
                    var states = line.split(",");
                    statesSet.addAll(Arrays.asList(states));
                }

                else if(lineCount == 2) { // if I am on the second line, where the alphabet is enumerated
                    var tempAlphabet = line.split(",");
                    alphabet.addAll(Arrays.asList(tempAlphabet));
                }

                else if(line.charAt(0) == 'F' && line.charAt(1)=='=') { // if I am on the line where the final states are enumerated
                    var substring = line.substring(2);
                    var states = substring.split(",");
                    finalStates.addAll(Arrays.asList(states));
                }

                else {
                    if(transitions.contains(line))
                        throw new Exception("Duplicate transition!");
                    var tempTransitions = line.split(",");

                    transitions.add(new Triple(tempTransitions[0],tempTransitions[1],tempTransitions[2]));
                }

                line=reader.readLine();
                lineCount++;
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void printElements() {
        // print states
        StringBuilder states = new StringBuilder();
        for (var state:
             statesSet) {
            states.append(state+", ");
        }
        states.delete(states.length()-2,states.length());
        System.out.println("Q={"+states.toString()+"}");

        // print initial state
        System.out.println("q0="+statesSet.get(0).toString());

        // print alphabet
        StringBuilder tempAlphabet = new StringBuilder();
        for (var item:
                alphabet) {
            tempAlphabet.append(item+", ");
        }
        tempAlphabet.delete(tempAlphabet.length()-2,tempAlphabet.length());
        System.out.println("Sigma={"+tempAlphabet.toString()+"}");

        // print transitions

        StringBuilder tempTransitions = new StringBuilder();
        for (var element:
             transitions) {
            tempTransitions.append("Delta("+element.first+","+element.second+")="+element.third+"\n");
        }
        System.out.print(tempTransitions);

        // print final states
        StringBuilder tempFinalStates = new StringBuilder();
        for (var finalState:
                finalStates) {
            tempFinalStates.append(finalState+", ");
        }
        tempFinalStates.delete(tempFinalStates.length()-2,tempFinalStates.length());
        System.out.println("F={"+tempFinalStates.toString()+"}");

    }

    public void run(String file) throws Exception {
        getElements(file);
        printElements();
    }
}
