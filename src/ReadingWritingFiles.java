import javax.swing.*;
import java.io.*;
import java.nio.file.Path;
import java.nio.file.Files;
import static java.nio.file.StandardOpenOption.CREATE;

public class ReadingWritingFiles {
        public static void main(String[] args) throws IOException   {
            JFileChooser chooser = new JFileChooser();

            try {
                File workingDirectory = new File(System.getProperty("user.dir"));
                chooser.setCurrentDirectory(workingDirectory);

                if (chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
                    File selectedFile = chooser.getSelectedFile();
                    Path file = selectedFile.toPath();

                    InputStream in = new BufferedInputStream(Files.newInputStream(file, CREATE));
                    BufferedReader reader = new BufferedReader(new InputStreamReader(in));

                    int character = 0;
                    int line = 0;
                    int space = 0;
                    String recorder = "";
                    System.out.println("File Path: " + file);

                    while (reader.ready()) {
                        recorder = reader.readLine();
                        line++;
                        System.out.printf("\nLine%4d: %-60s ", line, recorder);
                        for (int i = 0; i < recorder.length(); i++) {
                            if (recorder.substring(i, i+1).equals(" ")) {
                                space++;
                            }
                            character++;
                        }
                    }
                    reader.close();
                    System.out.println("\n\nData file read successfully!");
                    System.out.println("Number of lines: " + line);
                    System.out.println("Number of spaces: " + space);
                    System.out.println("Number of words: " + (space + line));
                    System.out.println("Number of characters: " + character);
                } else {
                    System.out.println("File not selected.");
                    System.exit(0);
                }
            }
            //Catch block - Attempts to open a file that cannot be found.
            catch (FileNotFoundException e) {
                System.out.println("File not found!");
                e.printStackTrace();
            }
            //Catch block - Every other possible IO exception.
            catch (IOException e) {
                e.printStackTrace();
            }
        }
}