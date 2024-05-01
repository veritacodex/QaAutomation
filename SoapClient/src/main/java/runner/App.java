package runner;

import clients.calculator.Calculator;
import clients.numberConversion.NumberConversion;

import java.math.BigInteger;

public class App
{
    public static void main( String[] args )
    {
        NumberConversion numberConversion = new NumberConversion();
        String words = numberConversion.getNumberConversionSoap().numberToWords(BigInteger.valueOf(1000));
        System.out.println(words);

        Calculator calculator = new Calculator();
        int result = calculator.getCalculatorSoap().add(2, 4);
        System.out.println(result);
    }
}
