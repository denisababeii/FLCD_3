import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;

public class FAProgram {
    ArrayList<String> statesSet;
    ArrayList<String> alphabet;
    ArrayList<String> finalStates;
    ArrayList<Triple> transitions;
    boolean isDFA;

    public FAProgram() {
        statesSet = new ArrayList<>();
        alphabet = new ArrayList<>();
        finalStates = new ArrayList<>();
        transitions = new ArrayList<>();
        isDFA = true;
    }

    public void getElements(String file) throws Exception {
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
                    for (var item:
                         tempAlphabet) {
                        if(item.length()>1)
                            throw new Exception("Alphabet only accepts letters and digits!");
                    }
                    alphabet.addAll(Arrays.asList(tempAlphabet));
                }

                else if(line.charAt(0) == 'F' && line.charAt(1)=='=') { // if I am on the line where the final states are enumerated
                    var substring = line.substring(2);
                    var states = substring.split(",");
                    finalStates.addAll(Arrays.asList(states));
                }

                else {
                    var tempTransition = line.split(",");
                    if(isDFA) { // only check if it is NFA in case this has not been established before
                        for (var transition :
                                transitions) {
                            if (transition.first.equals(tempTransition[0]) && transition.second.equals(tempTransition[1])) {
                                isDFA = false;
                                break;
                            }
                        }
                    }
                    transitions.add(new Triple(tempTransition[0],tempTransition[1],tempTransition[2]));
                }

                line=reader.readLine();
                lineCount++;
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void printStates() {
        StringBuilder states = new StringBuilder();
        for (var state :
                statesSet) {
            states.append(state + ", ");
        }
        states.delete(states.length() - 2, states.length());
        System.out.println("Q={" + states.toString() + "}");
    }

    public void printInitialState() {
        System.out.println("q0=" + statesSet.get(0).toString());
    }

    public void printAlphabet() {
        StringBuilder tempAlphabet = new StringBuilder();
        for (var item :
                alphabet) {
            tempAlphabet.append(item + ", ");
        }
        tempAlphabet.delete(tempAlphabet.length() - 2, tempAlphabet.length());
        System.out.println("Sigma={" + tempAlphabet.toString() + "}");
    }

    public void printTransitions() {
        StringBuilder tempTransitions = new StringBuilder();
        for (var element :
                transitions) {
            tempTransitions.append("Delta(" + element.first + "," + element.second + ")=" + element.third + "\n");
        }
        System.out.print(tempTransitions);
    }

    public void printFinalStates() {
        StringBuilder tempFinalStates = new StringBuilder();
        for (var finalState:
                finalStates) {
            tempFinalStates.append(finalState+", ");
        }
        tempFinalStates.delete(tempFinalStates.length()-2,tempFinalStates.length());
        System.out.println("F={"+tempFinalStates.toString()+"}");
    }

    public void printDFA() {
       if(isDFA)
           System.out.println("It is a DFA");
       else
           System.out.println("It is a NFA");
    }

    private String getTransition(String current, String sequenceElem) {
        for (var transition:
             transitions) {
            if(transition.first.equals(current) && transition.second.equals(sequenceElem))
                return transition.third;
        }
        return "";
    }

    private boolean checkSequence(String sequence) {
        var current = statesSet.get(0);

        if(sequence.equals("3") && finalStates.contains(statesSet.get(0))) {  // check for empty string, 3 is epsilon
            return true;
        }

        while(sequence.length()!=0) {
            String sequenceElem = Character.toString(sequence.charAt(0));
            var transition = getTransition(current,sequenceElem);
            if(transition.equals(""))
                return false;
            else {
                current = transition;
                sequence = sequence.substring(1);
            }
        }
        return true;
    }

    public void printValidity(String sequence) {
        if(isDFA) {
            if (checkSequence(sequence))
                System.out.println("It is valid");
            else
                System.out.println("It is not valid");
        }
        else
            System.out.println("It is not a DFA.");
    }
}
