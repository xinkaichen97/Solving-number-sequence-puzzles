import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class Polynomial {
    // the terms making up the polynomial
    private ArrayList<Term> polynomial;

    // creates a zero polynomial
    public Polynomial()
    {
        polynomial = new ArrayList<>();
    }

    // returns the number of terms in polynomial
    public int numberOfTerms()
    {
        return polynomial.size();
    }

    // adds a new term to the end of polynomial
    public void addTerm(Term t)
    {
        polynomial.add(t);
    }

    // returns the indicated term of polynomial
    public Term getTerm(int k)
    {
        if (0 <= k && k < numberOfTerms()) return polynomial.get(k);
        else                               return null;
    }

    // returns polynomial as a String for display
    // see the sample file and the test program for the layout required
    public String displayPolynomial()
    {
        String poly = "";
        // one special case: the sequence contains only a number "0"
        if (polynomial.size() == 1 && polynomial.get(0).getCoefficient() == 0){
            return "0";
        }
        // sort the polynomial in the descending order of exponent
        Collections.sort(polynomial, Collections.reverseOrder(Comparator.comparingInt(Term::getExponent)));
        // update the String for display
        for (Term term : polynomial){
            poly = poly + term.displayImproved() + " ";
        }
        // if the coefficient of the first term is positive, make sure the "+" sign is not displayed
        if (poly.charAt(0) == '+'){
            poly = poly.substring(2);
        }
        return poly;
    }
}
