/*
* Author: Alan Zheng
* Description: Get the value of PI up to k digits using Chudnovsky algorithm
*/

import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.MathContext;
import java.math.RoundingMode;

public class JavaPI {

  static final BigInteger BIG_A = BigInteger.valueOf(13591409);
  static final BigInteger BIG_B = BigInteger.valueOf(545140134);
  static final BigInteger BIG_C = new BigInteger("-262537412640768000");
  static final BigInteger THREE = BigInteger.valueOf(3);
  static final BigInteger SIX = BigInteger.valueOf(6);

  // complexity of algorithm O(n * log(n)^3)
  public static BigDecimal getValueOfPi(int num) {
    BigDecimal C = new BigDecimal(426880 * Math.sqrt(10005));
    MathContext mc = new MathContext(num+1);
    BigInteger numerator = BIG_A;
    BigInteger denominator = BigInteger.ONE;
    BigInteger BIG_K;
    BigDecimal sum = new BigDecimal(numerator.divide(denominator));
    for (int k = 1; k <= num; k++) {
      BIG_K = BigInteger.valueOf(k);
      numerator = factorial(BIG_K.multiply(SIX))
                    .multiply(BIG_K.multiply(BIG_B).add(BIG_A));
      denominator = factorial(BIG_K.multiply(THREE))
                      .multiply(factorial(BIG_K).pow(3))
                      .multiply(BIG_C.pow(k));

      sum = sum.add((new BigDecimal(numerator))
                .divide(new BigDecimal(denominator), mc)); 
    }
    BigDecimal PI = C.divide(sum, mc);
    return PI.setScale(num, RoundingMode.FLOOR);
  }
  
  private static BigInteger factorial(BigInteger n) {
    BigInteger result = BigInteger.ONE;
    while (!n.equals(BigInteger.ZERO)) {
      result = result.multiply(n);
      n = n.subtract(BigInteger.ONE);
    }
    return result;
  }

  public static void main(String[] args) {
    System.out.println("======= Java PI ========");
    System.out.println(JavaPI.getValueOfPi(10).toString());
  }
}
