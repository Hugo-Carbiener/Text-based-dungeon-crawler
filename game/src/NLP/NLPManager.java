package NLP;

import java.io.*;

public class NLPManager {

	public static void startNLP(String userInput) throws Exception {
		Process p = Runtime.getRuntime().exec(String.format("python3 NLP/cleaning_and_parsing.py %s", userInput));
		BufferedReader in = new BufferedReader(new InputStreamReader(p.getInputStream()));
		String output = in.readLine();
		return output;
	}

}
