package hr.freskov.fuzzy.control.ship;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import hr.freskov.fuzzy.control.COADefuzzifier;
import hr.freskov.fuzzy.control.FuzzySystem;
import hr.freskov.fuzzy.control.IDefuzzifier;
import hr.freskov.fuzzy.control.MamdaniRuleBase;
import hr.freskov.util.FileLoader;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		IDefuzzifier defuzzifier = new COADefuzzifier();
		List<String> accelerationRules = FileLoader.readFile("res/acceleration_rules.txt");
		FuzzySystem accelerationFS = new FuzzySystem(MamdaniRuleBase.fromString(accelerationRules), defuzzifier);
		List<String> helmRules = FileLoader.readFile("res/helm_rules.txt");
		FuzzySystem helmFS = new FuzzySystem(MamdaniRuleBase.fromString(helmRules), defuzzifier);
		
		Map<String, Integer> input = new HashMap<>();
		String line = null;

		while (true) {
			if ((line = reader.readLine()) != null) {
				if (line.charAt(0) == 'K') break;
				Scanner sc = new Scanner(line);
				input.put("L", sc.nextInt());
				input.put("R", sc.nextInt());
				input.put("LK", sc.nextInt());
				input.put("RK", sc.nextInt());
				input.put("V", sc.nextInt());
				input.put("S", sc.nextInt());
				sc.close();
			}
			int acceleration = accelerationFS.getOutput(input);
			int helm = helmFS.getOutput(input);
			System.out.println(acceleration + " " + helm);
			System.out.flush();
		}
	}
	
}
