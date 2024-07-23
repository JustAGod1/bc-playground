package tests;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.function.Consumer;
import java.util.function.Supplier;

public class Gen {

  public static void main(String[] args) throws IOException {
    writeClass("Lambdas", sb -> {
      createTree(sb, "lambdaHot", Gen::lambdaBody);
      createTree(sb, "lambdaCold", Gen::lambdaBody);
    });
    writeClass("Anons", sb -> {
      createTree(sb, "anonCold", Gen::anonBody);
      createTree(sb, "anonHot", Gen::anonBody);
    });

  }


  private static void writeClass(String prefix, Consumer<StringBuilder> body) throws IOException {
    String name = prefix + "Benches.java";
    try (OutputStream os = Files.newOutputStream(Paths.get(name))) {
      os.write(buildClass(prefix, body).toString().getBytes(StandardCharsets.UTF_8));
    }
  }

  private static StringBuilder buildClass(String prefix, Consumer<StringBuilder> body) {
    StringBuilder sb = new StringBuilder();
    sb.append("package tests;\n\nimport java.util.function.Consumer;\nimport java.util.function.Supplier;\n");
    sb.append("public class ").append(prefix).append("Benches {\n");
    body.accept(sb);
    sb.append("  private static int consume(Supplier<Integer> s) {\n    return s.get();\n  }");
    sb.append("}");

    return sb;
  }

  private static void createTree(StringBuilder sb, String prefix, Consumer<StringBuilder> body) {
    for (int i = 0; i < 20; i++) {
      sb.append(String.format("\tprivate static void %s%d() {\n", prefix, i));
      body.accept(sb);
      sb.append("\t}\n");
    }


    sb.append(String.format("\tpublic static void callAll_%s() {\n", prefix));
    for (int i = 0; i < 20; i++) {
      sb.append(String.format("\t\t%s%d();\n", prefix, i));
    }
    sb.append("\t}\n");
  }

  private static void lambdaBody(StringBuilder sb) {
    for (int i = 0; i < 100; i++) {
      sb.append("\t\tconsume(() -> ").append(i).append(");").append("\n");
    }
  }

  private static void anonBody(StringBuilder sb) {
    for (int i = 0; i < 100; i++) {
      sb.append("\t\tconsume(new Supplier<Integer>() { public Integer get() { return ").append(i).append(";}});\n");

    }
  }


}
