package Homework.Exercise8_Logparser;

import jdk.net.SocketFlow;
import java.nio.file.Paths;

public class TestLogParse {
    public static void main(String[] args) {
        LogFileParser.LogParser logParser = new LogFileParser.LogParser(Paths.get("C:\\Users\\timos\\Desktop\\Java_pogdotovka\\SBER_Course\\src\\LogFile.log"));

        System.out.println(logParser.getNumberOfUniqueIPs(null, null));
        System.out.println(logParser.getUniqueIPs(null, null));
        System.out.println(logParser.getIPsForUser("Amigo", null, null));
        System.out.println(logParser.getIPsForEvent("DONE_TASK", null, null));
        System.out.println(logParser.getIPsForStatus(SocketFlow.Status.OK, null, null));
    }
}
