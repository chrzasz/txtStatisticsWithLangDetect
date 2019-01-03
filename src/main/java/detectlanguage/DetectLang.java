package detectlanguage;

import com.detectlanguage.DetectLanguage;
import com.detectlanguage.Result;

import java.util.List;

/**
 * Created by Grzegorz Chrzaszczyk on 02-01-2019  09:03 PM
 */
public class DetectLang {


  public static void main(String[] args) throws Exception {

    DetectLanguage.apiKey = "f6454aefb7cd00e46e56974ecc0ea137";

    List<Result> results = DetectLanguage.detect("Ala ma kota");

    Result result = results.get(0);

    System.out.println("Language: " + result.language);
    System.out.println("Is reliable: " + result.isReliable);
    System.out.println("Confidence: " + result.confidence);

  }
}