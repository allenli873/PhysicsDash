import java.io.*;

public class LineCounter {
	public static void main(String[] args) throws IOException {
		File d = new File(".");
		File[] files = d.listFiles();
		int count = 0;
		for(File f : files) {
			BufferedReader in = new BufferedReader(new FileReader(f));
			String line;
			while((line = in.readLine()) != null)
				count++;
			in.close();
		}
		System.out.println(count);
	}
}