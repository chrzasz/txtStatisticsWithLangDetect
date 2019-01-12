package analyzer;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Grzegorz Chrzaszczyk on 09-12-2018  03:30 PM
 */
public class WordCountAnalyzer implements Analyzer {

  private int wordsCount;

  @Override
  public String toString() {
    return ("NUMBER OF WORDS:\t" + wordsCount);
  }

  @Override
  public Integer analyze(String text) {

    Map<String, Integer> wordsOccurance = new HashMap<>();

    String[] words = text.replaceAll(RegexPatterns.CUSTOM, " ")
            .toLowerCase()
            .split(RegexPatterns.WHITE_SPACES);

    for (String word : words) {
      wordsOccurance.merge(word, 1, (a, b) -> a + b);
    }

    wordsCount = wordsOccurance
            .values()
            .parallelStream()
            .mapToInt(Integer::intValue)
            .sum();

    return wordsCount;
  }
}


