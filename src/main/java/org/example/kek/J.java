package org.example.kek;

interface J extends I {

  default Integer m() {
    return 5;
  }
}
