package science.freeabyss.hulk.demo.util;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * Created by abyss on 04/15/16.
 */
public class OSExecute {
    public static void command(String command) {
        boolean err = false;
        try {
            Process process = new ProcessBuilder(command.split(" ")).start();
            InputStreamReader inputReader = new InputStreamReader(process.getInputStream());
            BufferedReader results = new BufferedReader(inputReader);
            String s;
            while ((s = results.readLine()) != null) {
                System.out.println(s);
            }

            inputReader = new InputStreamReader(process.getErrorStream());
            BufferedReader errors = new BufferedReader(inputReader);
            while ((s = errors.readLine()) != null) {
                System.err.println(s);
                err = true;
            }
        } catch (Exception e) {
            if (!command.startsWith("CMD /C")) {
                command("CMD /C " + command);
            } else {
                throw new RuntimeException();
            }
        }
        if (err) {
            throw new OSExecuteException("Errors executiing " + command);
        }
    }

    public static void main(String[] args) {
        command("java -version");
    }
}
