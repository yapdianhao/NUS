import java.util.List;
import java.util.Arrays;
import java.util.ArrayList;
import java.util.stream.*;

public class Parser {
    static List<String> strings;

    public static Parser parse(List<String> list) {
        strings = list;
        return new Parser() {
            @Override
            public String toString() {
                StringBuilder sb = new StringBuilder();
                for (int i = 0; i < list.size(); i++) {
                    if (i == list.size() - 1) {
                        sb.append(list.get(i));
                    } else {
                        sb.append(list.get(i));
                        sb.append("\n");
                    }
                }
                return sb.toString();
            }
        };
    }

    public Parser linecount() {
        String[] array = new String[1];
        int size = strings.size();
        array[0] = String.valueOf(strings.size());
        List<String> parsedStrings = Arrays.asList(array);
        return parse(parsedStrings);
    }

    public Parser wordcount() {
        int count = 0;
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < strings.size(); i++) {
            if (i == strings.size() - 1) {
                sb.append(strings.get(i));
            } else {
                sb.append(strings.get(i));
                sb.append(" ");
            }
        }
        String string = sb.toString();
        char characters[] = new char[string.length()];
        for (int i = 0; i < string.length(); i++) {
            characters[i] = string.charAt(i);
            if ((i > 0 && characters[i] != ' ' && characters[i - 1] == ' ') || (characters[0] != ' ' && i == 0)) {
                count++;
            }
        }
        String[] array = new String[1];
        array[0] = String.valueOf(count);
        List<String> wordCount = Arrays.asList(array);
        return parse(wordCount);
    }

    public Parser grab(String str) {        
        List<String> grabbedStrings = new ArrayList<String>();
        for (String s : strings) {
            if (s.contains(str)) {
                grabbedStrings.add(s);
            }
        }
        return parse(grabbedStrings);
    }

    public Parser echo() {
        String[] echoedString = new String[1];
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < strings.size(); i++) {
            if (i == strings.size() - 1) {
                sb.append(strings.get(i));
            } else {
                sb.append(strings.get(i));
                sb.append(" ");
            }
        }
        String[] array = sb.toString().split(" ");
        List<String> splittedStrings = new ArrayList<String>();
        for (String s : array) {
            if (s.equals(" ") | s.equals("")) {
                continue;
            } else {
                splittedStrings.add(s);
            }
        }
        StringBuilder sb1 = new StringBuilder();
        for (int i = 0; i < splittedStrings.size(); i++) {
            if (i == splittedStrings.size() - 1) {
                sb1.append(splittedStrings.get(i));
            } else {
                sb1.append(splittedStrings.get(i));
                sb1.append(" ");
            }
        }
        echoedString[0] = sb1.toString();
        return parse(Arrays.asList(echoedString));
    }

    public Parser chop(int start, int end) {
        List<String> choppedString = new ArrayList<String>();
        for (String s : strings) {
            if (start - 1 > s.length()) {
                choppedString.add("");
            } else if (start <= 0 && end > s.length()) {
                choppedString.add(s.substring(0, s.length()));
            } else if (end > s.length()) {
                choppedString.add(s.substring(start - 1, s.length()));
            } else if (start <= 0) {
                choppedString.add(s.substring(0, end));
            } else {
                choppedString.add(s.substring(start - 1, end));
            }
        }
        return parse(choppedString);
    }

    public Parser shuffle() {
        List<String> totalStrings = new ArrayList<String>();
        for (String s : strings) {
            StringBuilder sb = new StringBuilder();
            String[] array = s.split(" ");
            List<String> splittedStrings = new ArrayList<String>();
            for (String splittedString : array) {
                if (splittedString.equals(" ") || splittedString.equals("")) {
                    continue;
                } else {
                    splittedStrings.add(splittedString);
                }
            }
            List mergedStrings = splittedStrings.stream()
                                                .map(x -> {
                                                    if (x.length() <= 3) {
                                                        return x;
                                                    } else {
                                                        if (x.substring(x.length() - 1).equals(",") || x.substring(x.length() - 1).equals(".")) {
                                                            return x.substring(0,1) +
                                                                   x.substring(2,x.length() - 2) +
                                                                   x.substring(1,2) + 
                                                                   x.substring(x.length() - 2);
                                                        } else if (x.substring(x.length() - 2, x.length() - 1).equals("'")) {
                                                            return x.substring(0,1) + 
                                                                   x.substring(2,x.length() - 2) + 
                                                                   x.substring(1,2) + 
                                                                   x.substring(x.length() - 2);
                                                        } else {
                                                               return x.substring(0,1) +
                                                                      x.substring(2,x.length() - 1) + 
                                                                      x.substring(1,2) + 
                                                                      x.substring(x.length() - 1);
                                                        }
                                                    }
                                                }
                                                )
                                                .collect(Collectors.toList());
            for (int i = 0; i < mergedStrings.size(); i++) {
                if (i == mergedStrings.size() - 1) {
                    sb.append(mergedStrings.get(i));
                } else {
                    sb.append(mergedStrings.get(i));
                    sb.append(" ");
                }
            }
            totalStrings.add(sb.toString());
        }
        return parse(totalStrings);
    }

    public String toString() {
        return "";
    }
}
