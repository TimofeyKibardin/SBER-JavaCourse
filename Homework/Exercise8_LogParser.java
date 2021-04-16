/**
 * Лог файл имеет следующий формат:
 * ip username date event status
 * ------------------------------
 * Где:
 * ip - ip адрес с которого пользователь произвел событие.
 * user - имя пользователя (одно или несколько слов разделенные пробелами).
 * date - дата события в формате day.month.year hour:minute:second.
 * event - одно из событий:
 * •	LOGIN - пользователь залогинился,
 * •	DOWNLOAD_PLUGIN - пользователь скачал плагин,
 * •	WRITE_MESSAGE - пользователь отправил сообщение,
 * •	SOLVE_TASK - пользователь попытался решить задачу,
 * •	DONE_TASK - пользователь решил задачу.
 * ------------------------------
 * Для событий SOLVE_TASK и DONE_TASK существует дополнительный параметр,
 * который указывается через пробел, это номер задачи.
 * ------------------------------
 * status - статус:
 * •	OK - событие выполнилось успешно,
 * •	FAILED - событие не выполнилось,
 * •	ERROR - произошла ошибка.
 * ------------------------------
 * Все ключи разделены между сабой табуляцией.
 */

package Homework;

import jdk.jfr.Event;
import java.io.*;
import java.util.*;
import java.nio.file.Paths;
import java.nio.file.Path;
import jdk.net.SocketFlow;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;


public class Exercise8_LogParser {

    interface IPQuery {
        int getNumberOfUniqueIPs(Date after, Date before);
        Set<String> getUniqueIPs(Date after, Date before);
        Set<String> getIPsForUser(String user, Date after, Date before);
        Set<String> getIPsForEvent(String event, Date after, Date before);
        Set<String> getIPsForStatus(SocketFlow.Status status, Date after, Date before);
    }

    static class LogParser implements IPQuery {

        private Path logDir; //Путь к файлу

        public LogParser(Path logDir) { //Конструктор
            this.logDir = logDir;
        }

        /**
         * Метод принимает директорию лог-файла и возвращает строки с записями
         * @param logDir - путь к директории файла
         * @param after - дата до
         * @param before - дата после
         * @return - возврат строк в формате List<String>
         */
        private List<String> getLines(Path logDir, Date after, Date before) {
            List<File> directoryLogs = new ArrayList<>();
            List<String> result = new ArrayList<>();

            //Перебираем файлы в директории с целью найти .log файл с данными
            for (File file : Objects.requireNonNull(logDir.toFile().listFiles())) {
                if (file.isFile() && file.getName().endsWith(".log")) {
                    directoryLogs.add(file);
                }
            }

            //Читаем строки в лог-файле
            for (File directoryLog : directoryLogs) {

                try (BufferedReader br = new BufferedReader(new FileReader(directoryLog))) {
                    String line;

                    while ((line = br.readLine()) != null) { //Пока не кончатся линии в документе
                        String[] tmpLine = line.split("\t"); //Устанавливаем массив строк разделённых \t

                        //Прочитаем дату
                        String dateFormatted = tmpLine[2];
                        SimpleDateFormat format = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss", Locale.ENGLISH); //устанавливаем паттерн даты как в файле

                        //Парсим дату
                        Date docDate = null;
                        try {
                            docDate = format.parse(dateFormatted);
                        } catch (ParseException exc) {
                            exc.printStackTrace();
                        }

                        //Проверяем на null даты after и before
                        if (after == null && before == null) {
                            result.add(line);
                        } else if (after == null && (before.before(docDate) || (before.compareTo(docDate) == 0))) {
                            result.add(line);
                        } else if (before == null && (after.after(docDate) || (after.compareTo(docDate) == 0))) {
                            result.add(line);
                        } else if (before != null && after != null && before.before(docDate) && after.after(docDate)) {
                            result.add(line);
                        }
                    }
                } catch (IOException exc) {
                    exc.printStackTrace();
                }
            }

            return result;
        }


        /**
         * Метод возвращает количество всех уникальных IP адресов.
         * @param after - период, с которого будут возвращены адреса
         * @param before - период, по который будут возвращены адреса
         * @return - возвращает метод getUniqueIPs
         */
        @Override
        public int getNumberOfUniqueIPs(Date after, Date before) {
            return getUniqueIPs(after, before).size();
        }


        /**
         * Метод возвращает Множество, содержащее все неповторяющиеся IP-адреса
         * @param after - период, с которого будут возвращены адреса
         * @param before - период, по который будут возвращены адреса
         * @return - возвращает все неповторяющиеся IP-адреса из лог файла
         */
        @Override
        public Set<String> getUniqueIPs(Date after, Date before) {
            Set<String> ips = new HashSet<>();

            for (String line : getLines(logDir, after, before)) {
                //Добавляем подстроки от первого символа в строке до \t - здесь находится ip-адрес
                ips.add(line.substring(0, line.indexOf("\t")));
            }

            return ips;
        }

        /**
         * Метод возвращает все IP-адреса, с которых работал данный пользователь
         * @param user - Имя пользователя
         * @param after - период, с которого будут возвращены адреса
         * @param before - период, по который будут возвращены адреса
         * @return - Множество IP-адресов
         */
        @Override
        public Set<String> getIPsForUser(String user, Date after, Date before) {
            if (user == null) return new HashSet<>();

            Set<String> ipsForUser = new HashSet<>();

            for (String line : getLines(logDir, after, before)) {
                String userIP = line.substring(0, line.indexOf("\t"));
                String tmpUser = line.substring(0, line.length())
                        .replaceAll("\\W\\D\\w", "")
                        .trim();
                String usernameFromLog = tmpUser.split("\t")[1]
                        .replaceAll("\\d", "")
                        .trim();

                if (user.equalsIgnoreCase(usernameFromLog)) {
                    ipsForUser.add(userIP);
                }
            }

            return ipsForUser;
        }

        /**
         * Метод возвращает IP, с которых было произведено переданное событие.
         * @param event - событие
         * @param after - период, с которого будут возвращены адреса
         * @param before - период, по который будут возвращены адреса
         * @return Множество IP-адресов с запрашиваемым событием
         */
        @Override
        public Set<String> getIPsForEvent(String event, Date after, Date before) {
            if (event == null) return new HashSet<>();

            Set<String> ipsForEvent = new HashSet<>();

            for (String line : getLines(logDir, after, before)) {
                String userIP = line.substring(0, line.indexOf("\t"));
                String tmpEvent = line.split("\t")[3]
                        .trim()
                        .split(" ")[0];

                if (tmpEvent.equalsIgnoreCase(event)) {
                    ipsForEvent.add(userIP);
                }
            }

            return ipsForEvent;
        }

        /**
         * Метод возвращает IP-адреса, события которых закончились переданным статусом
         * @param status - наименование статуса события
         * @param after - период, с которого будут возвращены адреса
         * @param before - период, по который будут возвращены адреса
         * @return - Множество IP-адресов в запрашиваемым статусом события
         */
        @Override
        public Set<String> getIPsForStatus(SocketFlow.Status status, Date after, Date before) {
            Set<String> ipsForStatus = new HashSet<>();

            for (String line : getLines(logDir, after, before)) {
                String userIP = line.substring(0, line.indexOf("\t"));
                String tmpStatus = line.split("\t")[4].trim();

                if (status != null && tmpStatus.equalsIgnoreCase(status.name())) {
                    ipsForStatus.add(userIP);
                }
            }

            return ipsForStatus;
        }
    }




    public static void main(String[] args) {
        LogParser logParser = new LogParser(Paths.get(/*enter your path here*/));

        System.out.println(logParser.getNumberOfUniqueIPs(null, null));
        System.out.println(logParser.getUniqueIPs(null, null));
        System.out.println(logParser.getIPsForUser("Amigo", null, null));
        System.out.println(logParser.getIPsForEvent("DONE_TASK", null, null));
        System.out.println(logParser.getIPsForStatus(SocketFlow.Status.OK, null, null));
    }
}
