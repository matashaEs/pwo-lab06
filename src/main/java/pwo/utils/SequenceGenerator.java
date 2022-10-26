package pwo.utils;

import java.math.BigDecimal;

/**
 * Interfejs dowolnego generatora liczbowego o wyrazach zdefiniowanych w zakresie od 0.
 *
 * @author natal
 * @version 1.0.0
 */
public interface SequenceGenerator {

    void reset();

    BigDecimal nextTerm();

    BigDecimal getTerm(int i);
}
