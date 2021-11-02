import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;

public class FAProgram {
    ArrayList<String> statesSet;
    ArrayList<String> alphabet;
    ArrayList<String> finalStates;
    ArrayList<String> transitions;

    public FAProgram() {
        statesSet = new ArrayList<>();
        alphabet = new ArrayList<>();
        finalStates = new ArrayList<>();
        transitions = new ArrayList<>();
    }
    public void getElements(String file) {
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
                    transitions.add(line);
                }

                line=reader.readLine();
                lineCount++;
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
