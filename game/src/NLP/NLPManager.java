package NLP;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class NLPManager {

	public static String[] startNLP(String userInput) throws Exception {
		Process p = Runtime.getRuntime().exec("pwd");
		BufferedReader in = new BufferedReader(new InputStreamReader(p.getInputStream()));
		String pwd = in.readLine();
        int posDir = pwd.indexOf("Text-based-dungeon-crawler");
        String path = pwd.substring(0, posDir) + "Text-based-dungeon-crawler/game/src/NLP/call_functions.py";
		Process p2 = Runtime.getRuntime().exec(String.format("python3 %s %s", path, userInput));
		BufferedReader in2 = new BufferedReader(new InputStreamReader(p2.getInputStream()));
		String output = in2.readLine();
		// parsing du tableau python
		//Remove brackets
		output = output.substring(1, output.length() - 2);
		//Remove excessive characters
		output = output.replaceAll("'", "");
		output = output.replaceAll(" ", "");
		//Split the words
		String[] result = output.split(",");
		return result;
	}

}
