package pwo.app;

import pwo.seq.SeqType;
import pwo.utils.SequenceTools;
import java.nio.file.Paths;
import java.nio.file.InvalidPathException;

/**
 * KLasa służąca do zapisu do pliku tekstowego  jednego z wybranych ciągów
 *
 * @author natal
 * @version 1.0.0
 */
class SeqToFileApp {

    protected SeqType seqType = null;
    protected Integer from = null, to = null;
    protected String fileName = null;

    protected boolean getArgs(String[] args) {
        try {
            seqType = SeqType.fromString(args[0]);
            from = Integer.parseInt(args[1]);
            to = Integer.parseInt(args[2]);
            fileName = args[3];
        } catch (ArrayIndexOutOfBoundsException
                | NumberFormatException ex) {
            return false;
        }

        try {
            Paths.get(fileName);
        } catch (InvalidPathException ex) {
            return false;
        }

        return seqType != null && from >= 0 && to >= 0;
    }

    protected boolean writeSeq() {
        return SequenceTools.writeToFile(seqType.getGenerator(),
                from, to, fileName);
    }

    /**
    * Funkcja odpowiedzialna za zapis do pliku. Funkcja sprawdza
    * czy argumenty z linii poleceń zostały pobrane prawidłowo i
    * wyświetla stosowny komunikat o udanym zapisie bądź błędzie
    *
    * @param args Argumenty wiersza poleceń
    */
    public void run(String[] args) {
        System.out.println("Sequence to file CLI app");

        if (!getArgs(args)) {
            System.out.println("!Illegal arguments\n"
                    + "Legal usage: seqName from to fileName");
            return;
        }

        if (!writeSeq()) {
            System.out.println("!Write to the file: "
                    + fileName + " FAILED");
            return;
        }

        System.out.println("Results write to " + fileName);
    }
}
