package hr.freskov.util;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

/**
 * TODO
 * 
 * @author freskov
 * @version 1.0
 */
public class FileLoader {

	private FileLoader() {
	}

	/**
	 * Read all lines from a file. Bytes from the file are decoded into
	 * characters using the UTF-8 charset.
	 * 
	 * @param filePath
	 *            path to the file
	 * @return all lines from a file.
	 * @throws RuntimeException
	 *             if an I/O error occurs reading from the file or a malformed
	 *             or unmappable byte sequence is read
	 */
	public static List<String> readFile(String filePath) {
		Path path = Paths.get(filePath);
		try {
			return Files.readAllLines(path);
		} catch (IOException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

}
