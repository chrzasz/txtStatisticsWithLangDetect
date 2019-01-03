package analyzer;

import java.util.*;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toMap;

/**
 * Created by Grzegorz Chrzaszczyk on 02-01-2019  09:15 PM
 */
public class TopTenMostPopularWords implements Analyzer {

  private List<String> mostPopularWords = new ArrayList<>();

  @Override
  public String toString() {
    return "10 MOST POPULAR WORDS:\t" + mostPopularWords;
  }

  @Override
  public List<String> analyze(String text) {

    Map<String, Integer> wordsOccurance = new HashMap<>();

    String[] words = text.replaceAll(RegexPatterns.CUSTOM, " ")
            .toLowerCase()
            .split(RegexPatterns.WHITE_SPACES);

    for (String word : words) {
      wordsOccurance.merge(word, 1, (a, b) -> a + b);
    }
    /* above is simplified version of below code:
     * for (String word : words) {
     *    Integer cnt = wordsOccurance.get(word);
     *    if (cnt == null) {
     *      wordsOccurance.put(word, 1);
     *    } else {
     *      wordsOccurance.put(word, cnt + 1);
     *   }
     */

    // sort this map by values
    Map<String, Integer> sorted = wordsOccurance
            .entrySet()
            .stream()
            //sort this map by values
//            .sorted(comparingByValue())
            //sort this map in decreasing order of value
            .sorted(Collections.reverseOrder(Map.Entry.comparingByValue()))
            .collect(
                    toMap(e -> e.getKey(), e -> e.getValue(), (e1, e2) -> e2,
                            LinkedHashMap::new));

    /* above sort can be simplyfied as below sic;>>
    List<Map.Entry<String, Integer>> toSort = new ArrayList<>();
    for (Map.Entry<String, Integer> e : wordsOccurance.entrySet()) {
      toSort.add(e);
    }
    toSort.sort(Collections.reverseOrder(Map.Entry.comparingByValue()));
    Map<String, Integer> sorted = new LinkedHashMap<>();
    for (Map.Entry<String, Integer> e : toSort) sorted.put(e.getKey(), e.getValue());
*/

    mostPopularWords = sorted
            .keySet()
            .parallelStream()
            .distinct()
            .limit(10)
            .collect(Collectors.toList());
    return mostPopularWords;
  }
}
