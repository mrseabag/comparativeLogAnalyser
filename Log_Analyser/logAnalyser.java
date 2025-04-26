import java.io.*;
import java.util.*;
import java.time.*;

public class logAnalyser {
    public static void main(String[] args) {
        if (args.length < 1) {
            System.out.println("Usage: java LogAnalyser <log_file>");
            return;
        }

        String filePath = args[0];
        Map<String, Integer> ipCount = new HashMap<>();
        Instant start = Instant.now();

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] tokens = line.split(" ");
                if (tokens.length > 0) {
                    String ip = tokens[0];
                    ipCount.put(ip, ipCount.getOrDefault(ip, 0) + 1);
                }
            }
        } catch (FileNotFoundException e) {
            System.err.println("File not found: " + filePath);
            return;
        } catch (IOException e) {
            System.err.println("Error reading the file: " + filePath);
            return;
        }

        Instant end = Instant.now();
        Duration duration = Duration.between(start, end);

        System.out.println("== IP Address Summary ==");
        for (Map.Entry<String, Integer> entry : ipCount.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }

        System.out.printf("\n[Time taken: %.3f seconds]\n", duration.toMillis() / 1000.0);
    }
}
