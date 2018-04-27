import TieFighter.View.OutputFile;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.assertEquals;


class OutputFileTest {
  OutputFile test;
  Scanner sc;


  @BeforeEach
  void setUp() throws IOException {
    test = new OutputFile(Paths.get("test.txt"));

  }

  @AfterEach
  void tearDown() throws IOException {
    Files.delete(Paths.get("test.txt"));
  }

  @Test
  void write() throws IOException {
    test.write("test\r\n");
    sc = new Scanner(Paths.get("test.txt"));
    assertEquals("test", sc.nextLine());
  }

  @Test
  void writeDouble() throws IOException {
    test.write("test" + 1.00 + "\r\n");
    test.write("test" + 2.00 + "\r\n");
    sc = new Scanner(Paths.get("test.txt"));
    assertEquals("test1.0test2.0", sc.nextLine() + sc.nextLine());
  }

}