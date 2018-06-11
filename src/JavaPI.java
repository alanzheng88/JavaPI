/*
* Author: Alan Zheng
* Description: Get the value of PI up to d digits using Chudnovsky algorithm
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
  static final BigInteger TWELVE = BigInteger.valueOf(12);
  static final BigInteger SIXTEEN = BigInteger.valueOf(16);

  // complexity of algorithm O(n * log(n)^3)
  public static BigDecimal getValueOfPi(int num) {
    BigDecimal C = new BigDecimal(426880 * Math.sqrt(10005));
    MathContext mc = new MathContext(num+1);
    BigDecimal sum = new BigDecimal(BIG_A);
    BigInteger BIG_D;
    BigInteger L = BIG_A;
    BigInteger X = BigInteger.ONE;
    BigDecimal M = BigDecimal.ONE;
    BigInteger K = SIX;
    for (int d = 1; d <= num; d++) {
      BIG_D = BigInteger.valueOf(Long.valueOf(d));
      L = L.add(BIG_B);
      X = X.multiply(BIG_C);
      M = M.multiply( new BigDecimal(K.pow(3).subtract(SIXTEEN.multiply(K)))
                            .divide(new BigDecimal(BIG_D)
                                    .add(BigDecimal.ONE).pow(3), mc)  );
      K = K.add(TWELVE);
      sum = sum.add( M.multiply(new BigDecimal(L))
                          .divide(new BigDecimal(X), mc) ); 
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
    System.out.println(JavaPI.getValueOfPi(20).toString());
  }
}
