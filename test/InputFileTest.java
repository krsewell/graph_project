import TieFighter.View.InputFile;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.assertEquals;

class InputFileTest {
  InputFile test;
  InputFile empty;


  @BeforeEach
  void setUp() throws IOException {
    BufferedWriter bw = new BufferedWriter(new FileWriter("test.txt"));
    bw.write("Line 1 4.00 3 + 7 abc\r\n");
    bw.write("Line 2 3.00 3 + 7 abc\r\n");
    bw.write("Line 3 2.00 3 + 7 abc\r\n");
    bw.write("Line 4 1.00 3 + 7 abc\r\n");
    bw.write("Line 5 8.00 3 + 7 abc\r\n");
    bw.write("Line 6 16.00 3 + 7 abc\r\n");
    bw.write("Line 7 32.00 3 + 7 abc\r\n");
    bw.close();
    bw = new BufferedWriter(new FileWriter("empty.txt"));
    bw.write("");
    bw.close();
    test = new InputFile(Paths.get("test.txt"));
    empty = new InputFile(Paths.get("empty.txt"));
  }

  @AfterEach
  void tearDown() throws IOException {
    Files.delete(Paths.get("test.txt"));
    Files.delete(Paths.get("empty.txt"));
  }

  @Test
  void getStream() {
  }

  @Test
  void hasNextEmpty() {
    assert !empty.hasNext();
  }

  @Test
  void nextEmpty() {
    try {
      empty.next();
      assert false;
    } catch (NoSuchElementException ex) {
      //System.out.println(ex.getMessage());
      assert true;
    }
  }

  @Test
  void hasNextItem() {
    assert test.hasNext();
  }

  @Test
  void hasNextItemRepeat() {
    if (test.hasNext()) {
      if (!test.hasNext()) throw new AssertionError();
    } else assert false;
  }

  @Test
  void hasNextAfterAll() {
    boolean truth = false;
    for (int i = 0; i < 7; i++) {
      truth = test.hasNext();
      String temp = test.next();
    }
    assert !test.hasNext() && truth;
  }

  @Test
  void fileCheck() {
    StringBuilder sb = new StringBuilder();
    while (test.hasNext()) {
      sb.append(test.next());
      sb.append("\r\n");
    }
    assertEquals(sb.toString(), "Line 1 4.00 3 + 7 abc\r\n" +
            "Line 2 3.00 3 + 7 abc\r\n" +
            "Line 3 2.00 3 + 7 abc\r\n" +
            "Line 4 1.00 3 + 7 abc\r\n" +
            "Line 5 8.00 3 + 7 abc\r\n" +
            "Line 6 16.00 3 + 7 abc\r\n" +
            "Line 7 32.00 3 + 7 abc\r\n");
  }


}