package TieFighter.View;

import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class OutputFile {
  private File outFile;
  private BufferedWriter bw;
  private boolean valid;


  public OutputFile(Path p) throws IOException{
    outFile = p.toFile();
    bw = Files.newBufferedWriter(outFile.toPath());
    if (outFile.canWrite()) {
      bw.write("");   //initialize output file
      valid = true;
    }

  }


  public void write(String s) throws IOException {
    if (valid) bw.append(s);
  }

  public void close() {
    outFile = null;
    bw = null;
    valid = false;
  }
}
