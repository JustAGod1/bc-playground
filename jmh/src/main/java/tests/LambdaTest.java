package tests;

import java.time.Duration;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.function.Supplier;

public class LambdaTest {

  public static void main(String[] args) {
    measure("lambdaCold", LambdasBenches::callAll_lambdaCold);
    measure("lambdaHot", LambdasBenches::callAll_lambdaHot);
    measure("lambdaHot2", LambdasBenches::callAll_lambdaHot);

    measure("anonCold", AnonsBenches::callAll_anonCold);
    measure("anonHot", AnonsBenches::callAll_anonHot);
    measure("anonHot2", AnonsBenches::callAll_anonHot);
  }

  private static void measure(String name, Runnable block) {
    Instant before = Instant.now();

    block.run();

    Instant after = Instant.now();

    System.out.println(name + " elapsed " + ((double)Duration.between(before, after).get(ChronoUnit.NANOS)) / 1000_000 + "ms.");
  }
}
