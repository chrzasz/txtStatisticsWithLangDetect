package analyzer;

import java.util.List;

/**
 * Created by Grzegorz Chrzaszczyk on 09-12-2018  12:56 PM
 */
public class MultiAnalyzer {

  private List<Analyzer> analyzers;

  public MultiAnalyzer(List<Analyzer> analyzers) {
    this.analyzers = analyzers;
  }

  public void performAnalyzis(String text) {
    for (Analyzer analizer : analyzers) {
      analizer.analyze(text);
    }
  }

  public void showResult() {
    System.out.println("---------RESUTLTS---------");
    for (Analyzer analizer : analyzers) {
      System.out.println(analizer);
    }
  }
}
