package commandline;

import analyzer.*;
import picocli.CommandLine.Option;
import picocli.CommandLine.Parameters;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

/**
 * Created by Grzegorz Chrzaszczyk on 09-12-2018  01:32 PM
 */
public class CommandLineApp {

  @Option(names = {"-V", "--version"}, versionHelp = true, description = "display version info")
  private boolean versionInfoRequested;
  @Parameters(index = "0", description = "File to process.")
  private File file;
  @Option(names = {"-v", "--verbose"}, description = "Be verbose.")
  private boolean verbose = false;

  private static String readFileAsString(String fileName) throws Exception {

    if (Files.exists(Paths.get(fileName))) {
      String data;
      data = new String(Files.readAllBytes(Paths.get(fileName)));
      return data;
    } else return ("File: " + fileName + " not exists");
  }

  public void run() throws Exception {

    if (versionInfoRequested) System.out.println("Version:1.0.0");
    if (verbose) System.out.println("Analyzing file: " + file.getAbsolutePath() + "\t| OS:" + System.getProperty("os.name") + "\n");

    String str = readFileAsString(file.getAbsolutePath());

    ArrayList<Analyzer> analyzers = new ArrayList<>();
    analyzers.add(new WordCountAnalyzer());
    analyzers.add(new UniqueWordCountAnalyzer());
    analyzers.add(new TopTenMostPopularWords());
    analyzers.add(new TopTenLongestWords());
    analyzers.add(new TopTenLongestWordsUsedOnce());
    analyzers.add(new CharOccuranceAnalyzer());

    MultiAnalyzer multiAnalyzer = new MultiAnalyzer(analyzers);
    multiAnalyzer.performAnalyzis(str);
    multiAnalyzer.showResult();

  } //END run()

}// END CommandLineApp


