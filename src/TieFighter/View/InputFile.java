package TieFighter.View;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;


public final class InputFile implements Iterator<String> {
  final private Path inputFile;
  final private List<String> file;
  final private Iterator<String> f_it;

  public InputFile(Path p) throws IOException {
    inputFile = p;
    file = Files.lines(inputFile)
            .collect(toList());
    f_it = file.iterator();
  }

  public Stream<String> getStream() throws IOException {
    return Files.lines(inputFile);
  }

  /**
   * Returns {@code true} if the iteration has more elements.
   * (In other words, returns {@code true} if {@link #next} would
   * return an element rather than throwing an exception.)
   *
   * @return {@code true} if the iteration has more elements
   */
  @Override
  public boolean hasNext() {
    return f_it.hasNext();
  }

  /**
   * Returns the next element in the iteration.
   *
   * @return the next element in the iteration
   * @throws NoSuchElementException if the iteration has no more elements
   */
  @Override
  public String next() {
    if (hasNext())
      return f_it.next();
    else throw new NoSuchElementException("No more lines.");
  }
}
