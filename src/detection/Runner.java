package detection;

import java.io.File;

public class Runner {
    public static void main(String[] args) {
        System.out.println(FileReader.readFile(new File("test.txt")));
    }
}
