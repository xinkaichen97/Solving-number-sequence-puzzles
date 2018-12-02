public class Term {
    // the term = coefficient * x ^ exponent
    private double coefficient;
    private int    exponent;

    public Term(double c, int e)
    {
        coefficient = c;
        exponent = e;
    }

    // returns the coefficient
    public double getCoefficient()
    {
        return coefficient;
    }

    // returns the exponent
    public int getExponent()
    {
        return exponent;
    }

    // returns the term as a simple String for display
    // e.g. with coefficient = 2 and exponent = 1, return "+ 2.0 x^1"
    public String displaySimple()
    {
        // separate the absolute value of the coefficient and its sign
        double coe = Math.abs(coefficient);
        String sign = "+ ";
        String x = "x^" + exponent;
        if (coefficient == 0){
            return "";
        }
        if (coefficient < 0){
            sign = "- ";
        }
        return sign + coe + x;
    }

    // returns the term as a String for display:
    // see the sample file and the test program for the layout required
    public String displayImproved()
    {
        double coe = Math.abs(coefficient);
        // manipulate three parts (the sign, coefficient, and monomial) of a term respectively
        // using three String objects
        String sign = "+ ";
        String x = "x^" + exponent;
        String co = "" + coe;
        if (coefficient == 0){
            return "";
        }
        if (coefficient < 0){
            sign = "- ";
        }
        // if the absolute value of the coefficient equals 1
        // we should look at the exponent to handle different cases
        if (coe == 1){
            if (exponent != 0){
                co = "";
            } else {
                co = "1";
            }
        }
        // if the coefficient is an integer, display in the integer format
        if (coe != 1 && coe == (int) coe){
            co = "" + (int) coe;
        }
        // handling two special cases: exponent equals 0 or 1
        if (exponent == 1){
            x = "x";
        }
        if (exponent == 0){
            x = "";
        }
        return sign + co + x;
    }
}
