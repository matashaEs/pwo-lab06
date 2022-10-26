package pwo.seq;

import java.util.stream.Stream;

/**
 * Typ wyliczeniowy obejmujący wszystkie zrealizowane ciągi. <br>
 * Jest to również menadżer służący do pozyskania obiektu klasy ciągu metodą {@link #getGenerator()
 * }
 *
 * @author natal
 * @version 1.0.0
 */
public enum SeqType {

    FIB, //Fibonacci
    LUC, //Lucas
    TRI; //Tribonacci

    private static final int B = 0, L = 3;
    private static final String FIX_SEQTYPE
            = "Problem in " + SeqType.class.getName();

    static {
        Stream.of(SeqType.values()).forEach(t -> {
            if (t.toString().length() != L) {
                throw new IllegalStateException(FIX_SEQTYPE);
            }
        });
    }

    /**
    * Pozyskanie wartości typu na podstawie tekstu
    *
    * @param type FIB, LUC lub TRI
    * @return Odpowiadająca argumentowi wartość typu lub null
    */
    public static SeqType fromString(String type) {
        try {
            return valueOf(type.trim()
                    .substring(B, L).toUpperCase());
        } catch (NullPointerException
                | StringIndexOutOfBoundsException
                | IllegalArgumentException ex) {
            return null;
        }
    }

    /**
    * Tworzy obiekt generatora. Korespondujacy z nadaną wartością.
    *
    * @return Obiekt implementujący SequenceGenerator
    */
    public Generator getGenerator() {
        switch (this) {
            case FIB:
                return new FibonacciGenerator();
            case LUC:
                return new LucasGenerator();
            case TRI:
                return new TribonacciGenerator();
            default:
                throw new IllegalStateException(FIX_SEQTYPE);
        }
    }
}
