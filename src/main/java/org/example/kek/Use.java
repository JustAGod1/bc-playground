package org.example.kek;

public class Use {
  public static void main(String[] args) {
    useI(new C());
  }

  static void useI(I i) {
    System.out.println(i.m());
  }
}
