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

/**
 * This command line program expects 6 integers on the standard input and for
 * each input outputs 2 integers. Expected input is:
 * <ul>
 * <li>L - distance from the left shore [0, 1500]</li>
 * <li>D - distance from the right shore [0, 1500]</li>
 * <li>LK - distance from the left shore under 45 degree angle [0, 1500]</li>
 * <li>DK - distance from the right shore under 45 degree angle [0, 1500]</li>
 * <li>V - ship speed [0, 100]</li>
 * <li>S - direction 0-wrong direction, 1-right direction</li>
 * </ul>
 * Output is:
 * <ul>
 * <li>A - acceleration [-30, 30]</li>
 * <li>K - helm angle [-90, 90]</li>
 * </ul> 
 * 
 * @author freskov
 * @version 1.0
 */
public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		IDefuzzifier defuzzifier = new COADefuzzifier();
		List<String> accelerationRules = FileLoader.readFile("res/acceleration_rules.txt");
		FuzzySystem accelerationFS = new FuzzySystem(new MamdaniRuleBase(accelerationRules), defuzzifier);
		List<String> helmRules = FileLoader.readFile("res/helm_rules.txt");
		FuzzySystem helmFS = new FuzzySystem(new MamdaniRuleBase(helmRules), defuzzifier);

		Map<String, Integer> input = new HashMap<>();
		String line = null;

		while (true) {
			if ((line = reader.readLine()) != null) {
				if (line.charAt(0) == 'K')
					break;
				Scanner sc = new Scanner(line);
				input.put("L", sc.nextInt());
				input.put("D", sc.nextInt());
				input.put("LK", sc.nextInt());
				input.put("DK", sc.nextInt());
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
