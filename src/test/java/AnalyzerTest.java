import analyzer.WordCountAnalyzer;
import org.junit.Assert;
import org.junit.Test;

/**
 * Created by Grzegorz Chrzaszczyk on 09-12-2018  03:40 PM
 */

public class AnalyzerTest {

  @Test
  public void wordCountTest() {
    WordCountAnalyzer wordCountAnalyzer = new WordCountAnalyzer();
    String test = "Ala has cat ala";
    Assert.assertEquals(4, (int) wordCountAnalyzer.analyze(test));
  }


}
