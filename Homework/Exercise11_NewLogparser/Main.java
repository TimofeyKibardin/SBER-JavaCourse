package Homework.Exercise11_NewLogparser;

import jdk.net.SocketFlow;
import java.nio.file.Paths;
import org.junit.*;
import static org.assertj.core.api.Assertions.assertThat;

public class Main {
    public static void main(String[] args) {
        LogFileParser.LogParser logParser = new LogFileParser.LogParser(Paths.get("C:\\"));

        /*System.out.println(logParser.getNumberOfUniqueIPs(null, null));
        System.out.println(logParser.getUniqueIPs(null, null));
        System.out.println(logParser.getIPsForUser("Amigo", null, null));
        System.out.println(logParser.getIPsForEvent("DONE_TASK", null, null));
        System.out.println(logParser.getIPsForStatus(SocketFlow.Status.OK, null, null));*/
    }

    @Test
    public void testNullDates() throws Exception {
        LogFileParser.LogParser logParser = new LogFileParser.LogParser(Paths.get("C:\\Users\\timos\\Desktop\\Java_pogdotovka\\SBER_Course\\src\\main\\java"));
        Integer a = logParser.getNumberOfUniqueIPs(null, null);
        boolean result = a.equals(null);
        assertThat(a).isNotNegative();
    }
}
