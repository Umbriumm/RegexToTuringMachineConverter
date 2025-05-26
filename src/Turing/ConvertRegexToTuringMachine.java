package Turing;

import RegexToDFA.NFAtoDFA;
import RegexToDFA.RegexToNFA;

public class ConvertRegexToTuringMachine {
    public static void convertAndSimulate(String regex, String inputString) throws Exception {
        Tape tape = new Tape(inputString);
        TuringMachine tm =  DFAtoTM.convert(
                NFAtoDFA.convert(
                        RegexToNFA.convertRegexToNFA(regex)), tape.input);
        tm.run();

    }

    public static void convertAndPrintTransitions(String regex) throws Exception {
        final String RESET = "\u001B[0m";
        final String CYAN = "\u001B[36m";
        final String GREEN = "\u001B[32m";

        System.out.println();

        TuringMachine tm = DFAtoTM.convert(
                NFAtoDFA.convertAndPrint(
                        RegexToNFA.convertRegexToNFA(regex)));

        System.out.println(CYAN + " === Turing Machine Transitions === " + RESET);
        System.out.println();
        System.out.println("/// Start State: " + tm.startStateName);
        for (TMTransition t : tm.tmTransitions) {
            System.out.print(t);
            if (tm.acceptStates.contains(t.getNextState())) {
                System.out.println(" " + GREEN + "[ACCEPTING]" + RESET);
            } else {
                System.out.println();
            }
        }
    }

}
