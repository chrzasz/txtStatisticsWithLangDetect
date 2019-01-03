package analyzer;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Grzegorz Chrzaszczyk on 02-01-2019  10:25 AM
 */
public class UniqueWordCountAnalyzer implements Analyzer {

  private int uniqueWordsCount;
  private Map<String, Integer> wordsOccurrence = new HashMap<>();

  public Map<String, Integer> getWordsOccurrence() {
    return wordsOccurrence;
  }

  @Override
  public String toString() {
    return "NUMBER OF UNIQUE WORDS:\t" + uniqueWordsCount;
  }

  @Override
  public Integer analyze(String text) {

    String[] words = text.replaceAll(RegexPatterns.CUSTOM, " ")
            .toLowerCase()
            .split(RegexPatterns.WHITE_SPACES);

    for (String word : words) {
      //Get the character
      wordsOccurrence.merge(word, 1, (a, b) -> a + b);
    }

    uniqueWordsCount = wordsOccurrence.size();

    return uniqueWordsCount;
  }
}
