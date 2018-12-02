import java.util.ArrayList;

public class Sequence {
    // the numbers in the sequence
    private ArrayList<Double> sequence;

    // sets up sequence by parsing s
    // e.g. Sequence("3, -4, 8.5") sets sequence to <3, -4, 8.5>
    public Sequence(String s)
    {
        sequence = new ArrayList<>();
        for (String x : s.split(","))
            sequence.add(Double.parseDouble(x));
    }

    // returns sequence
    public ArrayList<Double> getSequence()
    {
        return sequence;
    }

    // returns the product of 1..x
    // e.g. factorial(4) = 1 * 2 * 3 * 4 returns 24
    public int factorial(int x)
    {
        int fac = 1;
        for(int i = 1; i <= x; i++){
            fac *= i;
        }
        return fac;
    }

    // returns true if all of the items on seq are equal
    // e.g. allEqual(<4, 4, 4>) returns true, and allEqual(<3, 3, -2>) returns false
    public boolean allEqual(ArrayList<Double> seq)
    {
        // if the sequence contains nothing, return false
        if (seq.size() == 0){
            return false;
        }
        // if the sequence only contains one item, it is considered equal
        if (seq.size() == 1){
            return true;
        }
        for(Double item : seq){
            if(!item.equals(seq.get(0))){
                return false;
            }
        }
        return true;
    }

    // returns a new ArrayList holding the differences between adjacent items on seq
    // e.g. differences(<4, 6, 1, 1>) returns <2, -5, 0>
    public ArrayList<Double> differences(ArrayList<Double> seq)
    {
        // create a new ArrayList to store the differences
        ArrayList<Double> dif = new ArrayList<>();
        for (int i = 1; i < seq.size(); i++){
            dif.add(seq.get(i) - seq.get(i - 1));
        }
        return dif;
    }

    // returns the next term in the simplest polynomial that generates sequence
    // implements Steps 1-3 of the algorithm description on the project web page
    public Term nextTerm()
    {
        int exponent = 0;
        double coefficient;
        ArrayList<Double> dif = sequence;
        while (!allEqual(dif)){
            dif = differences(dif);
            exponent += 1;
        }
        coefficient = dif.get(0) / factorial(exponent);
        return new Term(coefficient, exponent);
    }

    // returns the value to subtract from the kth index of term t
    // e.g. termUpdate(Term<2, 3>, 4) returns 128
    public double termUpdate(Term t, int k)
    {
        return t.getCoefficient() * Math.pow(k, t.getExponent());
    }

    // subtracts from each item in sequence the effect of the term t
    // implements Step 4 of the algorithm description on the project web page
    public void updateSequence(Term t)
    {
        // the k-th indexed item in the sequence is the (k+1)-th item
        for (int k = 0; k < sequence.size(); k++){
            sequence.set(k, sequence.get(k) - termUpdate(t, k + 1));
        }
    }

    // returns the simplest polynomial that generates sequence
    // implements the algorithm description on the project web page
    // and also displays the polynomial as a String
    public Polynomial solveSequence()
    {
        Polynomial polynomial = new Polynomial();
        // compute the new term and update the original sequence until the sequence is "all-equal"
        while (!allEqual(sequence)){
            Term term = nextTerm();
            polynomial.addTerm(term);
            updateSequence(term);
        }
        // get the value of the constant from the "all-equal" sequence and add it to the polynomial
        Term constant = new Term(sequence.get(0), 0);
        polynomial.addTerm(constant);
        String poly = polynomial.displayPolynomial();
        System.out.println(poly);
        return polynomial;
    }

    // reads the file filename, solves the sequences therein, and displays the results
    public static void solveFileSequences(String filename)
    {
        for (String s : (new FileIO(filename)).lines)
            if (s.length() > 0 && "0123456789-".indexOf(s.charAt(0)) != -1)
            {
                System.out.print("Sequence: " + s + "\nPolynomial: ");
                (new Sequence(s)).solveSequence();
                System.out.println();
            }
    }
}