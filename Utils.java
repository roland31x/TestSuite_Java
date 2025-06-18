import java.io.*;
import java.nio.file.*;
import java.text.*;
import java.util.*;
import java.util.regex.*;

public class Utils {
    public static void greet(String name) {
        System.out.println("Hello from " + name + "!");
    }

    public static boolean isEmpty_1(String s) {
        return s == null || s.isEmpty();
    }

    // 2. Check if string is null or blank (only whitespace)
    public static boolean isBlank_2(String s) {
        return s == null || s.trim().isEmpty();
    }

    // 3. Reverse a string
    public static String reverse_3(String s) {
        return s == null ? null : new StringBuilder(s).reverse().toString();
    }

    // 4. Capitalize first letter
    public static String capitalize_4(String s) {
        if (isEmpty_1(s)) return s;
        return s.substring(0,1).toUpperCase() + s.substring(1);
    }

    // 5. Repeat string n times
    public static String repeat_5(String s, int n) {
        if (s == null || n <= 0) return "";
        return s.repeat(n);
    }

    // 6. Check if number is prime
    public static boolean isPrime_6(int n) {
        if (n <= 1) return false;
        for (int i=2; i<=Math.sqrt(n); i++) {
            if (n % i == 0) return false;
        }
        return true;
    }

    // 7. Find gcd of two numbers
    public static int gcd_7(int a, int b) {
        while (b != 0) {
            int t = b;
            b = a % b;
            a = t;
        }
        return Math.abs(a);
    }

    // 8. Find lcm of two numbers
    public static int lcm_8(int a, int b) {
        if (a == 0 || b == 0) return 0;
        return Math.abs(a * b) / gcd_7(a, b);
    }

    // 9. Convert int array to string with delimiter
    public static String join_9(int[] arr, String delimiter) {
        if (arr == null || arr.length == 0) return "";
        StringBuilder sb = new StringBuilder();
        for (int i=0; i<arr.length; i++) {
            sb.append(arr[i]);
            if (i < arr.length - 1) sb.append(delimiter);
        }
        return sb.toString();
    }

    // 10. Convert list to CSV string
    public static <T> String join_10(List<T> list, String delimiter) {
        if (list == null || list.isEmpty()) return "";
        StringBuilder sb = new StringBuilder();
        for (int i=0; i<list.size(); i++) {
            sb.append(list.get(i));
            if (i < list.size() - 1) sb.append(delimiter);
        }
        return sb.toString();
    }

    // 11. Parse int safely with default
    public static int parseInt_11(String s, int defaultValue) {
        try {
            return Integer.parseInt(s);
        } catch (Exception e) {
            return defaultValue;
        }
    }

    // 12. Parse double safely with default
    public static double parseDouble_12(String s, double defaultValue) {
        try {
            return Double.parseDouble(s);
        } catch (Exception e) {
            return defaultValue;
        }
    }

    // 13. Check if string is numeric
    public static boolean isNumeric_13(String s) {
        if (isEmpty_1(s)) return false;
        return s.matches("-?\\d+(\\.\\d+)?");
    }

    // 14. Shuffle an int array
    public static void shuffle_14(int[] arr) {
        if (arr == null) return;
        Random rnd = new Random();
        for (int i = arr.length - 1; i > 0; i--) {
            int j = rnd.nextInt(i + 1);
            int tmp = arr[i];
            arr[i] = arr[j];
            arr[j] = tmp;
        }
    }

    // 15. Reverse int array
    public static void reverse_15(int[] arr) {
        if (arr == null) return;
        int i=0, j=arr.length - 1;
        while (i<j) {
            int tmp = arr[i];
            arr[i] = arr[j];
            arr[j] = tmp;
            i++; j--;
        }
    }

    // 16. Check if array contains a value
    public static boolean contains_16(int[] arr, int val) {
        if (arr == null) return false;
        for (int v : arr) {
            if (v == val) return true;
        }
        return false;
    }

    // 17. Find max value in int array
    public static int max_17(int[] arr) {
        if (arr == null || arr.length == 0) throw new IllegalArgumentException("Empty array");
        int max = arr[0];
        for (int v : arr) if (v > max) max = v;
        return max;
    }

    // 18. Find min value in int array
    public static int min_18(int[] arr) {
        if (arr == null || arr.length == 0) throw new IllegalArgumentException("Empty array");
        int min = arr[0];
        for (int v : arr) if (v < min) min = v;
        return min;
    }

    // 19. Sum values in int array
    public static int sum_19(int[] arr) {
        if (arr == null) return 0;
        int s = 0;
        for (int v : arr) s += v;
        return s;
    }

    // 20. Average of int array
    public static double average_20(int[] arr) {
        if (arr == null || arr.length == 0) return 0;
        return (double) sum_19(arr) / arr.length;
    }

    // 21. Get current timestamp in milliseconds
    public static long currentTimestamp_21() {
        return System.currentTimeMillis();
    }

    // 22. Format date to string
    public static String formatDate_22(Date date, String pattern) {
        if (date == null || isEmpty_1(pattern)) return "";
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        return sdf.format(date);
    }

    // 23. Parse date from string
    public static Date parseDate_23(String dateString, String pattern) {
        if (isEmpty_1(dateString) || isEmpty_1(pattern)) return null;
        try {
            SimpleDateFormat sdf = new SimpleDateFormat(pattern);
            return sdf.parse(dateString);
        } catch (ParseException e) {
            return null;
        }
    }

    // 24. Check if file exists
    public static boolean fileExists_24(String path) {
        if (isEmpty_1(path)) return false;
        return new File(path).exists();
    }

    // 25. Read all text from file (UTF-8)
    public static String readFile_25(String path) throws IOException {
        if (isEmpty_1(path)) return "";
        return Files.readString(Paths.get(path));
    }

    // 26. Write text to file (UTF-8)
    public static void writeFile_26(String path, String content) throws IOException {
        if (isEmpty_1(path)) return;
        Files.writeString(Paths.get(path), content);
    }

    // 27. Delete file
    public static boolean deleteFile_27(String path) {
        if (isEmpty_1(path)) return false;
        return new File(path).delete();
    }

    // 28. Generate random int between min (inclusive) and max (exclusive)
    public static int randomInt_28(int min, int max) {
        if (min >= max) throw new IllegalArgumentException("min must be < max");
        return new Random().nextInt(max - min) + min;
    }

    // 29. Generate random double between min and max
    public static double randomDouble_29(double min, double max) {
        if (min >= max) throw new IllegalArgumentException("min must be < max");
        return min + (max - min) * new Random().nextDouble();
    }

    // 30. Sleep thread quietly for milliseconds
    public static void sleep_30(long millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException ignored) {}
    }

    // 31. Convert boolean to int (true=1, false=0)
    public static int boolToInt_31(boolean b) {
        return b ? 1 : 0;
    }

    // 32. Convert int to boolean (0=false, others=true)
    public static boolean intToBool_32(int i) {
        return i != 0;
    }

    // 33. Remove all whitespace from string
    public static String removeWhitespace_33(String s) {
        if (s == null) return null;
        return s.replaceAll("\\s+", "");
    }

    // 34. Count occurrences of a char in string
    public static int countChar_34(String s, char c) {
        if (s == null) return 0;
        int count = 0;
        for (char ch : s.toCharArray()) {
            if (ch == c) count++;
        }
        return count;
    }

    // 35. Check if string is palindrome
    public static boolean isPalindrome_35(String s) {
        if (s == null) return false;
        String clean = s.replaceAll("\\W", "").toLowerCase();
        return clean.equals(new StringBuilder(clean).reverse().toString());
    }

    // 36. Get file extension from filename
    public static String getFileExtension_36(String filename) {
        if (isEmpty_1(filename)) return "";
        int idx = filename.lastIndexOf('.');
        if (idx < 0) return "";
        return filename.substring(idx + 1);
    }

    // 37. Get filename without extension
    public static String getFileNameWithoutExtension_37(String filename) {
        if (isEmpty_1(filename)) return "";
        int idx = filename.lastIndexOf('.');
        if (idx < 0) return filename;
        return filename.substring(0, idx);
    }

    // 38. Convert array to list
    public static <T> List<T> toList_38(T[] arr) {
        if (arr == null) return new ArrayList<>();
        return Arrays.asList(arr);
    }

    // 39. Convert list to array
    public static <T> T[] toArray_39(List<T> list, Class<T> cls) {
        if (list == null) return null;
        @SuppressWarnings("unchecked")
        T[] arr = (T[]) java.lang.reflect.Array.newInstance(cls, list.size());
        return list.toArray(arr);
    }

    // 40. Sleep for random time between min and max ms
    public static void sleepRandom_40(int minMillis, int maxMillis) {
        sleep_30(randomInt_28(minMillis, maxMillis));
    }

    // 41. Safe equals for strings (handles null)
    public static boolean safeEquals_41(String a, String b) {
        return Objects.equals(a, b);
    }

    // 42. Convert list to set
    public static <T> Set<T> toSet_42(List<T> list) {
        if (list == null) return new HashSet<>();
        return new HashSet<>(list);
    }

    // 43. Clamp integer between min and max
    public static int clamp_43(int val, int min, int max) {
        return Math.max(min, Math.min(max, val));
    }

    // 44. Clamp double between min and max
    public static double clamp_44(double val, double min, double max) {
        return Math.max(min, Math.min(max, val));
    }

    // 45. Get unique elements from array
    public static int[] unique_45(int[] arr) {
        if (arr == null) return new int[0];
        return Arrays.stream(arr).distinct().toArray();
    }

    // 46. Count words in string
    public static int countWords_46(String s) {
        if (isBlank_2(s)) return 0;
        return s.trim().split("\\s+").length;
    }

    // 47. Convert array of strings to upper case
    public static String[] toUpperCase_47(String[] arr) {
        if (arr == null) return null;
        String[] res = new String[arr.length];
        for (int i=0; i<arr.length; i++) {
            res[i] = arr[i] == null ? null : arr[i].toUpperCase();
        }
        return res;
    }

    // 48. Convert array of strings to lower case
    public static String[] toLowerCase_48(String[] arr) {
        if (arr == null) return null;
        String[] res = new String[arr.length];
        for (int i=0; i<arr.length; i++) {
            res[i] = arr[i] == null ? null : arr[i].toLowerCase();
        }
        return res;
    }

    // 49. Find the mode (most frequent element) in int array
    public static int mode_49(int[] arr) {
        if (arr == null || arr.length == 0) throw new IllegalArgumentException("Empty array");
        Map<Integer, Integer> freq = new HashMap<>();
        for (int v : arr) freq.put(v, freq.getOrDefault(v, 0) + 1);
        int mode = arr[0];
        int maxCount = 0;
        for (Map.Entry<Integer, Integer> e : freq.entrySet()) {
            if (e.getValue() > maxCount) {
                maxCount = e.getValue();
                mode = e.getKey();
            }
        }
        return mode;
    }

    // 50. Check if string matches regex pattern
    public static boolean matchesRegex_50(String s, String pattern) {
        if (s == null || pattern == null) return false;
        return Pattern.matches(pattern, s);
    }

    // 51. Convert byte array to hex string
    public static String bytesToHex_51(byte[] bytes) {
        if (bytes == null) return null;
        StringBuilder sb = new StringBuilder();
        for (byte b : bytes) {
            sb.append(String.format("%02x", b));
        }
        return sb.toString();
    }

    // 52. Convert hex string to byte array
    public static byte[] hexToBytes_52(String hex) {
        if (hex == null || hex.length() % 2 != 0) return null;
        int len = hex.length() / 2;
        byte[] result = new byte[len];
        for (int i = 0; i < len; i++) {
            result[i] = (byte) Integer.parseInt(hex.substring(2*i, 2*i+2), 16);
        }
        return result;
    }

    // 53. Check if string starts with uppercase letter
    public static boolean startsWithUpper_53(String s) {
        if (s == null || s.isEmpty()) return false;
        return Character.isUpperCase(s.charAt(0));
    }

    // 54. Check if string ends with punctuation (. ! ?)
    public static boolean endsWithPunctuation_54(String s) {
        if (s == null || s.isEmpty()) return false;
        char c = s.charAt(s.length() - 1);
        return c == '.' || c == '!' || c == '?';
    }

    // 55. Generate UUID string
    public static String generateUUID_55() {
        return UUID.randomUUID().toString();
    }

    // 56. Check if string is valid email address (simple regex)
    public static boolean isValidEmail_56(String email) {
        if (email == null) return false;
        String regex = "^[\\w.-]+@[\\w.-]+\\.[a-zA-Z]{2,}$";
        return email.matches(regex);
    }

    // 57. Convert collection to comma separated string
    public static <T> String collectionToString_57(Collection<T> coll) {
        if (coll == null || coll.isEmpty()) return "";
        return String.join(", ", coll.stream().map(Object::toString).toArray(String[]::new));
    }

    // 58. Count occurrences of substring in string
    public static int countSubstring_58(String s, String sub) {
        if (s == null || sub == null || sub.isEmpty()) return 0;
        int count = 0, idx = 0;
        while ((idx = s.indexOf(sub, idx)) != -1) {
            count++;
            idx += sub.length();
        }
        return count;
    }

    // 59. Convert map to string with format key=value, separated by delimiter
    public static <K,V> String mapToString_59(Map<K,V> map, String delimiter) {
        if (map == null || map.isEmpty()) return "";
        StringBuilder sb = new StringBuilder();
        int i = 0;
        for (Map.Entry<K,V> e : map.entrySet()) {
            sb.append(e.getKey()).append("=").append(e.getValue());
            if (i++ < map.size() -1) sb.append(delimiter);
        }
        return sb.toString();
    }

    // 60. Parse boolean safely with default
    public static boolean parseBoolean_60(String s, boolean defaultValue) {
        if (s == null) return defaultValue;
        s = s.trim().toLowerCase();
        if ("true".equals(s) || "1".equals(s)) return true;
        if ("false".equals(s) || "0".equals(s)) return false;
        return defaultValue;
    }

    // 61. Convert string to title case
    public static String toTitleCase_61(String s) {
        if (s == null || s.isEmpty()) return s;
        String[] words = s.split("\\s+");
        StringBuilder sb = new StringBuilder();
        for (String w : words) {
            if (w.length() > 0) {
                sb.append(Character.toUpperCase(w.charAt(0)));
                if (w.length() > 1) sb.append(w.substring(1).toLowerCase());
                sb.append(" ");
            }
        }
        return sb.toString().trim();
    }

    // 62. Remove duplicates from string list
    public static List<String> removeDuplicates_62(List<String> list) {
        if (list == null) return Collections.emptyList();
        return new ArrayList<>(new LinkedHashSet<>(list));
    }

    // 63. Get file size in bytes
    public static long getFileSize_63(String path) {
        if (path == null) return 0;
        File f = new File(path);
        return f.exists() && f.isFile() ? f.length() : 0;
    }

    // 64. Copy file from source to destination
    public static void copyFile_64(String srcPath, String destPath) throws IOException {
        if (srcPath == null || destPath == null) return;
        Files.copy(Paths.get(srcPath), Paths.get(destPath), StandardCopyOption.REPLACE_EXISTING);
    }

    // 65. Check if string is valid IPv4 address
    public static boolean isValidIPv4_65(String ip) {
        if (ip == null) return false;
        String regex = "^((25[0-5]|2[0-4]\\d|[0-1]?\\d{1,2})\\.){3}(25[0-5]|2[0-4]\\d|[0-1]?\\d{1,2})$";
        return ip.matches(regex);
    }

    // 66. Get substring between two strings
    public static String substringBetween_66(String s, String start, String end) {
        if (s == null || start == null || end == null) return null;
        int startIdx = s.indexOf(start);
        if (startIdx == -1) return null;
        startIdx += start.length();
        int endIdx = s.indexOf(end, startIdx);
        if (endIdx == -1) return null;
        return s.substring(startIdx, endIdx);
    }

    // 67. Check if string contains only letters
    public static boolean isAlpha_67(String s) {
        if (s == null || s.isEmpty()) return false;
        return s.matches("[a-zA-Z]+");
    }

    // 68. Check if string contains only digits
    public static boolean isDigits_68(String s) {
        if (s == null || s.isEmpty()) return false;
        return s.matches("\\d+");
    }

    // 69. Normalize whitespace (replace multiple spaces with single space)
    public static String normalizeWhitespace_69(String s) {
        if (s == null) return null;
        return s.trim().replaceAll("\\s+", " ");
    }

    // 70. Get the first N characters of a string safely
    public static String firstNChars_70(String s, int n) {
        if (s == null) return null;
        if (n <= 0) return "";
        return s.length() <= n ? s : s.substring(0, n);
    }

    // 71. Get the last N characters of a string safely
    public static String lastNChars_71(String s, int n) {
        if (s == null) return null;
        if (n <= 0) return "";
        return s.length() <= n ? s : s.substring(s.length() - n);
    }

    // 72. Get current working directory
    public static String getCurrentWorkingDirectory_72() {
        return System.getProperty("user.dir");
    }

    // 73. Get environment variable safely
    public static String getEnv_73(String name) {
        if (name == null) return null;
        return System.getenv(name);
    }

    // 74. Join array of strings with newline
    public static String joinLines_74(String[] lines) {
        if (lines == null) return "";
        return String.join(System.lineSeparator(), lines);
    }

    // 75. Validate if string is valid URL (simple regex)
    public static boolean isValidURL_75(String url) {
        if (url == null) return false;
        String regex = "^(https?|ftp)://[^\\s/$.?#].[^\\s]*$";
        return url.matches(regex);
    }

    // 76. Generate random alphanumeric string of given length
    public static String randomAlphaNum_76(int length) {
        if (length <= 0) return "";
        String chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        Random rnd = new Random();
        StringBuilder sb = new StringBuilder(length);
        for (int i=0; i<length; i++) {
            sb.append(chars.charAt(rnd.nextInt(chars.length())));
        }
        return sb.toString();
    }

    // 77. Convert List<Integer> to int[]
    public static int[] toIntArray_77(List<Integer> list) {
        if (list == null) return new int[0];
        int[] arr = new int[list.size()];
        for (int i=0; i<list.size(); i++) arr[i] = list.get(i);
        return arr;
    }

    // 78. Convert int[] to List<Integer>
    public static List<Integer> toIntegerList_78(int[] arr) {
        List<Integer> list = new ArrayList<>();
        if (arr == null) return list;
        for (int v : arr) list.add(v);
        return list;
    }

    // 79. Check if array is sorted ascending
    public static boolean isSortedAsc_79(int[] arr) {
        if (arr == null || arr.length < 2) return true;
        for (int i=1; i<arr.length; i++) {
            if (arr[i] < arr[i-1]) return false;
        }
        return true;
    }

    // 80. Check if array is sorted descending
    public static boolean isSortedDesc_80(int[] arr) {
        if (arr == null || arr.length < 2) return true;
        for (int i=1; i<arr.length; i++) {
            if (arr[i] > arr[i-1]) return false;
        }
        return true;
    }

    // 81. Find index of first occurrence of value in array, -1 if not found
    public static int indexOf_81(int[] arr, int val) {
        if (arr == null) return -1;
        for (int i=0; i<arr.length; i++) {
            if (arr[i] == val) return i;
        }
        return -1;
    }

    // 82. Find index of last occurrence of value in array, -1 if not found
    public static int lastIndexOf_82(int[] arr, int val) {
        if (arr == null) return -1;
        for (int i=arr.length - 1; i>=0; i--) {
            if (arr[i] == val) return i;
        }
        return -1;
    }

    // 83. Convert array of objects to list safely
    public static <T> List<T> arrayToList_83(T[] arr) {
        if (arr == null) return Collections.emptyList();
        return new ArrayList<>(Arrays.asList(arr));
    }

    // 84. Find factorial of n (n!)
    public static long factorial_84(int n) {
        if (n < 0) throw new IllegalArgumentException("Negative number");
        long fact = 1;
        for (int i=2; i<=n; i++) fact *= i;
        return fact;
    }

    // 85. Convert milliseconds to formatted time string HH:mm:ss
    public static String millisToTime_85(long millis) {
        long seconds = millis / 1000;
        long hh = seconds / 3600;
        long mm = (seconds % 3600) / 60;
        long ss = seconds % 60;
        return String.format("%02d:%02d:%02d", hh, mm, ss);
    }

    // 86. Convert seconds to formatted time string MM:ss
    public static String secondsToTime_86(int seconds) {
        int mm = seconds / 60;
        int ss = seconds % 60;
        return String.format("%02d:%02d", mm, ss);
    }

    // 87. Format number with commas (thousands separator)
    public static String formatWithCommas_87(long number) {
        return String.format("%,d", number);
    }

    // 88. Escape HTML special characters in string
    public static String escapeHtml_88(String s) {
        if (s == null) return null;
        return s.replace("&", "&amp;")
                .replace("<", "&lt;")
                .replace(">", "&gt;")
                .replace("\"", "&quot;")
                .replace("'", "&#39;");
    }

    // 89. Unescape HTML special characters in string
    public static String unescapeHtml_89(String s) {
        if (s == null) return null;
        return s.replace("&lt;", "<")
                .replace("&gt;", ">")
                .replace("&quot;", "\"")
                .replace("&#39;", "'")
                .replace("&amp;", "&");
    }

    // 90. Convert string to snake_case
    public static String toSnakeCase_90(String s) {
        if (s == null) return null;
        return s.trim().replaceAll("([a-z])([A-Z])", "$1_$2")
                .replaceAll("\\s+", "_")
                .toLowerCase();
    }

    // 91. Convert string to camelCase
    public static String toCamelCase_91(String s) {
        if (s == null) return null;
        s = s.toLowerCase().replaceAll("[_\\-\\s]+", " ");
        String[] words = s.split(" ");
        StringBuilder sb = new StringBuilder();
        for (int i=0; i<words.length; i++) {
            String w = words[i];
            if (i == 0) sb.append(w);
            else sb.append(Character.toUpperCase(w.charAt(0))).append(w.substring(1));
        }
        return sb.toString();
    }

    // 92. Generate an array of random ints of given size and bounds
    public static int[] randomIntArray_92(int size, int min, int max) {
        if (size <= 0 || min >= max) return new int[0];
        Random rnd = new Random();
        int[] arr = new int[size];
        for (int i=0; i<size; i++) {
            arr[i] = rnd.nextInt(max - min) + min;
        }
        return arr;
    }

    // 93. Capitalize every word in a sentence
    public static String capitalizeWords_93(String s) {
        if (s == null) return null;
        String[] words = s.split("\\s+");
        StringBuilder sb = new StringBuilder();
        for (String w : words) {
            if (w.length() > 0) {
                sb.append(Character.toUpperCase(w.charAt(0)));
                if (w.length() > 1) sb.append(w.substring(1).toLowerCase());
                sb.append(" ");
            }
        }
        return sb.toString().trim();
    }

    // 94. Get extension from file name safely (empty string if none)
    public static String getExtension_94(String filename) {
        return getFileExtension_36(filename);
    }

    // 95. Check if two arrays are equal (int[])
    public static boolean arraysEqual_95(int[] a, int[] b) {
        return Arrays.equals(a, b);
    }

    // 96. Check if two lists are equal (generic)
    public static <T> boolean listsEqual_96(List<T> a, List<T> b) {
        return Objects.equals(a, b);
    }

    // 97. Convert string to int with exception
    public static int toInt_97(String s) {
        return Integer.parseInt(s);
    }

    // 98. Convert string to double with exception
    public static double toDouble_98(String s) {
        return Double.parseDouble(s);
    }

    // 99. Remove all non-alphanumeric characters from string
    public static String removeNonAlphanumeric_99(String s) {
        if (s == null) return null;
        return s.replaceAll("[^a-zA-Z0-9]", "");
    }

    // 100. Calculate Euclidean distance between two points (x1,y1) and (x2,y2)
    public static double euclideanDistance_100(double x1, double y1, double x2, double y2) {
        double dx = x1 - x2;
        double dy = y1 - y2;
        return Math.sqrt(dx*dx + dy*dy);
    }

    // 101. Reverse the order of words in a sentence
    public static String reverseWords_101(String s) {
        if (s == null) return null;
        String[] words = s.trim().split("\\s+");
        Collections.reverse(Arrays.asList(words));
        return String.join(" ", words);
    }

    // 102. Check if string is palindrome (ignoring case and spaces)
    public static boolean isPalindrome_102(String s) {
        if (s == null) return false;
        String clean = s.replaceAll("\\s+", "").toLowerCase();
        int n = clean.length();
        for (int i = 0; i < n/2; i++) {
            if (clean.charAt(i) != clean.charAt(n - i - 1)) return false;
        }
        return true;
    }

    // 103. Remove all vowels from string
    public static String removeVowels_103(String s) {
        if (s == null) return null;
        return s.replaceAll("(?i)[aeiou]", "");
    }

    // 104. Get current timestamp in milliseconds
    public static long currentTimestamp_104() {
        return System.currentTimeMillis();
    }

    // 105. Round double to specified decimal places
    public static double roundToDecimals_105(double value, int places) {
        if (places < 0) throw new IllegalArgumentException("Invalid decimal places");
        long factor = (long) Math.pow(10, places);
        return (double) Math.round(value * factor) / factor;
    }

    // 106. Convert array of doubles to List<Double>
    public static List<Double> toDoubleList_106(double[] arr) {
        List<Double> list = new ArrayList<>();
        if (arr == null) return list;
        for (double d : arr) list.add(d);
        return list;
    }

    // 107. Convert List<Double> to array of doubles
    public static double[] toDoubleArray_107(List<Double> list) {
        if (list == null) return new double[0];
        double[] arr = new double[list.size()];
        for (int i=0; i<list.size(); i++) arr[i] = list.get(i);
        return arr;
    }

    // 108. Get max value from int array
    public static int maxValue_108(int[] arr) {
        if (arr == null || arr.length == 0) throw new IllegalArgumentException("Array is empty");
        int max = arr[0];
        for (int v : arr) if (v > max) max = v;
        return max;
    }

    // 109. Get min value from int array
    public static int minValue_109(int[] arr) {
        if (arr == null || arr.length == 0) throw new IllegalArgumentException("Array is empty");
        int min = arr[0];
        for (int v : arr) if (v < min) min = v;
        return min;
    }

    // 110. Convert string to boolean with default false
    public static boolean toBoolean_110(String s) {
        if (s == null) return false;
        return Boolean.parseBoolean(s.trim());
    }

    // 111. Convert seconds to HH:mm:ss format string
    public static String formatSeconds_111(int seconds) {
        int hh = seconds / 3600;
        int mm = (seconds % 3600) / 60;
        int ss = seconds % 60;
        return String.format("%02d:%02d:%02d", hh, mm, ss);
    }

    // 112. Check if string contains only whitespace
    public static boolean isWhitespaceOnly_112(String s) {
        if (s == null) return false;
        return s.trim().isEmpty();
    }

    // 113. Check if string contains only lowercase letters
    public static boolean isLowerCase_113(String s) {
        if (s == null || s.isEmpty()) return false;
        return s.equals(s.toLowerCase());
    }

    // 114. Check if string contains only uppercase letters
    public static boolean isUpperCase_114(String s) {
        if (s == null || s.isEmpty()) return false;
        return s.equals(s.toUpperCase());
    }

    // 115. Generate random boolean
    public static boolean randomBoolean_115() {
        return new Random().nextBoolean();
    }

    // 116. Convert string to UTF-8 bytes
    public static byte[] toUtf8Bytes_116(String s) {
        if (s == null) return null;
        try {
            return s.getBytes("UTF-8");
        } catch (UnsupportedEncodingException e) {
            return null;
        }
    }

    // 117. Convert UTF-8 bytes to string
    public static String fromUtf8Bytes_117(byte[] bytes) {
        if (bytes == null) return null;
        try {
            return new String(bytes, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            return null;
        }
    }

    // 118. Check if a string matches regex pattern
    public static boolean matchesPattern_118(String s, String regex) {
        if (s == null || regex == null) return false;
        return Pattern.matches(regex, s);
    }

    // 119. Split string by regex into list
    public static List<String> splitToList_119(String s, String regex) {
        if (s == null) return Collections.emptyList();
        String[] parts = s.split(regex);
        return Arrays.asList(parts);
    }

    // 120. Remove all whitespace from string
    public static String removeWhitespace_120(String s) {
        if (s == null) return null;
        return s.replaceAll("\\s+", "");
    }

    // 121. Get first non-null string from varargs
    public static String firstNonNull_121(String... values) {
        if (values == null) return null;
        for (String v : values) {
            if (v != null) return v;
        }
        return null;
    }

    // 122. Calculate sum of int array
    public static int sum_122(int[] arr) {
        if (arr == null) return 0;
        int sum = 0;
        for (int v : arr) sum += v;
        return sum;
    }

    // 123. Calculate average of int array
    public static double average_123(int[] arr) {
        if (arr == null || arr.length == 0) return 0.0;
        return (double) sum_122(arr) / arr.length;
    }

    // 124. Convert array to set (removes duplicates)
    public static <T> Set<T> arrayToSet_124(T[] arr) {
        if (arr == null) return Collections.emptySet();
        return new HashSet<>(Arrays.asList(arr));
    }

    // 125. Get all keys from map as list
    public static <K,V> List<K> mapKeys_125(Map<K,V> map) {
        if (map == null) return Collections.emptyList();
        return new ArrayList<>(map.keySet());
    }

    // 126. Get all values from map as list
    public static <K,V> List<V> mapValues_126(Map<K,V> map) {
        if (map == null) return Collections.emptyList();
        return new ArrayList<>(map.values());
    }

    // 127. Check if list contains duplicates
    public static <T> boolean containsDuplicates_127(List<T> list) {
        if (list == null) return false;
        Set<T> set = new HashSet<>();
        for (T item : list) {
            if (!set.add(item)) return true;
        }
        return false;
    }

    // 128. Remove null elements from list
    public static <T> List<T> removeNulls_128(List<T> list) {
        if (list == null) return Collections.emptyList();
        List<T> res = new ArrayList<>();
        for (T item : list) {
            if (item != null) res.add(item);
        }
        return res;
    }

    // 129. Convert string array to uppercase
    public static String[] toUpperCaseArray_129(String[] arr) {
        if (arr == null) return null;
        String[] res = new String[arr.length];
        for (int i = 0; i < arr.length; i++) {
            res[i] = arr[i] != null ? arr[i].toUpperCase() : null;
        }
        return res;
    }

    // 130. Convert string array to lowercase
    public static String[] toLowerCaseArray_130(String[] arr) {
        if (arr == null) return null;
        String[] res = new String[arr.length];
        for (int i = 0; i < arr.length; i++) {
            res[i] = arr[i] != null ? arr[i].toLowerCase() : null;
        }
        return res;
    }

    // 131. Get nth Fibonacci number (recursive)
    public static long fibonacci_131(int n) {
        if (n < 0) throw new IllegalArgumentException("Negative index");
        if (n <= 1) return n;
        return fibonacci_131(n-1) + fibonacci_131(n-2);
    }

    // 132. Get nth Fibonacci number (iterative)
    public static long fibonacciIter_132(int n) {
        if (n < 0) throw new IllegalArgumentException("Negative index");
        if (n <= 1) return n;
        long a = 0, b = 1;
        for (int i=2; i<=n; i++) {
            long temp = a + b;
            a = b;
            b = temp;
        }
        return b;
    }

    // 133. Convert string to char array safely
    public static char[] toCharArray_133(String s) {
        if (s == null) return new char[0];
        return s.toCharArray();
    }

    // 134. Join list of strings with given delimiter
    public static String joinList_134(List<String> list, String delimiter) {
        if (list == null || list.isEmpty()) return "";
        return String.join(delimiter, list);
    }

    // 135. Check if number is prime
    public static boolean isPrime_135(int n) {
        if (n <= 1) return false;
        if (n == 2) return true;
        if (n % 2 == 0) return false;
        for (int i=3; i <= Math.sqrt(n); i += 2) {
            if (n % i == 0) return false;
        }
        return true;
    }

    // 136. Find greatest common divisor (GCD) of two integers
    public static int gcd_136(int a, int b) {
        if (b == 0) return Math.abs(a);
        return gcd_136(b, a % b);
    }

    // 137. Find least common multiple (LCM) of two integers
    public static int lcm_137(int a, int b) {
        if (a == 0 || b == 0) return 0;
        return Math.abs(a / gcd_136(a, b) * b);
    }

    // 138. Convert string array to List<String>
    public static List<String> arrayToList_138(String[] arr) {
        if (arr == null) return Collections.emptyList();
        return Arrays.asList(arr);
    }

    // 139. Check if string contains only ASCII characters
    public static boolean isAscii_139(String s) {
        if (s == null) return false;
        return s.chars().allMatch(c -> c < 128);
    }

    // 140. Find median of int array (sorted or unsorted)
    public static double median_140(int[] arr) {
        if (arr == null || arr.length == 0) throw new IllegalArgumentException("Empty array");
        int[] copy = Arrays.copyOf(arr, arr.length);
        Arrays.sort(copy);
        int mid = copy.length / 2;
        if (copy.length % 2 == 0) {
            return (copy[mid - 1] + copy[mid]) / 2.0;
        } else {
            return copy[mid];
        }
    }

    // 141. Format date to string with given pattern
    public static String formatDate_141(Date date, String pattern) {
        if (date == null || pattern == null) return null;
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        return sdf.format(date);
    }

    // 142. Parse date from string with given pattern
    public static Date parseDate_142(String dateString, String pattern) {
        if (dateString == null || pattern == null) return null;
        try {
            SimpleDateFormat sdf = new SimpleDateFormat(pattern);
            return sdf.parse(dateString);
        } catch (ParseException e) {
            return null;
        }
    }

    // 143. Check if two dates are on the same day
    public static boolean isSameDay_143(Date d1, Date d2) {
        if (d1 == null || d2 == null) return false;
        Calendar c1 = Calendar.getInstance();
        c1.setTime(d1);
        Calendar c2 = Calendar.getInstance();
        c2.setTime(d2);
        return c1.get(Calendar.YEAR) == c2.get(Calendar.YEAR) &&
               c1.get(Calendar.DAY_OF_YEAR) == c2.get(Calendar.DAY_OF_YEAR);
    }

    // 144. Add days to date
    public static Date addDays_144(Date date, int days) {
        if (date == null) return null;
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.DAY_OF_MONTH, days);
        return cal.getTime();
    }

    // 145. Calculate days between two dates
    public static long daysBetween_145(Date d1, Date d2) {
        if (d1 == null || d2 == null) return 0;
        long diff = d2.getTime() - d1.getTime();
        return diff / (24 * 60 * 60 * 1000);
    }

    // 146. Check if file exists
    public static boolean fileExists_146(String filePath) {
        if (filePath == null) return false;
        File file = new File(filePath);
        return file.exists();
    }

    // 147. Read all text from a file (UTF-8)
    public static String readFile_147(String filePath) {
        if (filePath == null) return null;
        try {
            return new String(java.nio.file.Files.readAllBytes(java.nio.file.Paths.get(filePath)), "UTF-8");
        } catch (Exception e) {
            return null;
        }
    }

    // 148. Write text to a file (UTF-8), overwrite if exists
    public static boolean writeFile_148(String filePath, String content) {
        if (filePath == null || content == null) return false;
        try {
            java.nio.file.Files.write(java.nio.file.Paths.get(filePath), content.getBytes("UTF-8"));
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    // 149. Delete a file
    public static boolean deleteFile_149(String filePath) {
        if (filePath == null) return false;
        File file = new File(filePath);
        return file.delete();
    }

    // 150. Get file extension from filename
    public static String getFileExtension_150(String filename) {
        if (filename == null) return null;
        int dotIndex = filename.lastIndexOf('.');
        if (dotIndex == -1 || dotIndex == filename.length() - 1) return "";
        return filename.substring(dotIndex + 1);
    }

    // 151. Capitalize the first letter of each word in a string
    public static String capitalizeWords_151(String s) {
        if (s == null || s.isEmpty()) return s;
        String[] words = s.split("\\s+");
        StringBuilder sb = new StringBuilder();
        for (String word : words) {
            if (word.length() > 0) {
                sb.append(Character.toUpperCase(word.charAt(0)));
                sb.append(word.substring(1).toLowerCase());
            }
            sb.append(" ");
        }
        return sb.toString().trim();
    }

    // 152. Check if a string is a valid email address
    public static boolean isValidEmail_152(String email) {
        if (email == null) return false;
        String emailRegex = "^[\\w.-]+@[\\w.-]+\\.[a-zA-Z]{2,}$";
        return Pattern.matches(emailRegex, email);
    }

    // 153. Generate a random alphanumeric string of given length
    public static String randomAlphaNumeric_153(int length) {
        if (length <= 0) return "";
        String chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        Random rand = new Random();
        StringBuilder sb = new StringBuilder(length);
        for (int i=0; i<length; i++) {
            sb.append(chars.charAt(rand.nextInt(chars.length())));
        }
        return sb.toString();
    }

    // 154. Convert string to integer with default value
    public static int toIntOrDefault_154(String s, int defaultValue) {
        if (s == null) return defaultValue;
        try {
            return Integer.parseInt(s.trim());
        } catch (NumberFormatException e) {
            return defaultValue;
        }
    }

    // 155. Convert string to double with default value
    public static double toDoubleOrDefault_155(String s, double defaultValue) {
        if (s == null) return defaultValue;
        try {
            return Double.parseDouble(s.trim());
        } catch (NumberFormatException e) {
            return defaultValue;
        }
    }

    // 156. Check if string contains only digits
    public static boolean isDigitsOnly_156(String s) {
        if (s == null) return false;
        return s.matches("\\d+");
    }

    // 157. Convert a collection to a comma-separated string
    public static <T> String joinCollection_157(Collection<T> coll) {
        if (coll == null || coll.isEmpty()) return "";
        StringBuilder sb = new StringBuilder();
        for (T item : coll) {
            sb.append(item).append(",");
        }
        sb.setLength(sb.length() - 1);
        return sb.toString();
    }

    // 158. Generate MD5 hash of a string
    public static String md5Hash_158(String input) {
        if (input == null) return null;
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] digest = md.digest(input.getBytes("UTF-8"));
            BigInteger number = new BigInteger(1, digest);
            return String.format("%032x", number);
        } catch (Exception e) {
            return null;
        }
    }

    // 159. Generate SHA-256 hash of a string
    public static String sha256Hash_159(String input) {
        if (input == null) return null;
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] digest = md.digest(input.getBytes("UTF-8"));
            StringBuilder hexString = new StringBuilder();
            for (byte b : digest) {
                String hex = Integer.toHexString(0xff & b);
                if (hex.length() == 1) hexString.append('0');
                hexString.append(hex);
            }
            return hexString.toString();
        } catch (Exception e) {
            return null;
        }
    }

    // 160. Generate a UUID string
    public static String generateUUID_160() {
        return UUID.randomUUID().toString();
    }

    // 161. Check if a string is a valid URL
    public static boolean isValidUrl_161(String url) {
        if (url == null) return false;
        String regex = "^(https?|ftp)://[^\\s/$.?#].[^\\s]*$";
        return Pattern.matches(regex, url);
    }

    // 162. Count occurrences of a substring in a string
    public static int countOccurrences_162(String str, String sub) {
        if (str == null || sub == null || sub.isEmpty()) return 0;
        int count = 0, idx = 0;
        while ((idx = str.indexOf(sub, idx)) != -1) {
            count++;
            idx += sub.length();
        }
        return count;
    }

    // 163. Remove duplicates from an int array
    public static int[] removeDuplicates_163(int[] arr) {
        if (arr == null) return new int[0];
        return Arrays.stream(arr).distinct().toArray();
    }

    // 164. Reverse an int array in-place
    public static void reverseArray_164(int[] arr) {
        if (arr == null) return;
        int left = 0, right = arr.length - 1;
        while (left < right) {
            int temp = arr[left];
            arr[left] = arr[right];
            arr[right] = temp;
            left++;
            right--;
        }
    }

    // 165. Check if a string is numeric (including decimals)
    public static boolean isNumeric_165(String s) {
        if (s == null) return false;
        return s.matches("-?\\d+(\\.\\d+)?");
    }

    // 166. Get substring between two delimiters
    public static String substringBetween_166(String s, String start, String end) {
        if (s == null || start == null || end == null) return null;
        int startIdx = s.indexOf(start);
        if (startIdx == -1) return null;
        int endIdx = s.indexOf(end, startIdx + start.length());
        if (endIdx == -1) return null;
        return s.substring(startIdx + start.length(), endIdx);
    }

    // 167. Convert camelCase to snake_case
    public static String camelToSnake_167(String s) {
        if (s == null) return null;
        return s.replaceAll("([a-z])([A-Z])", "$1_$2").toLowerCase();
    }

    // 168. Convert snake_case to camelCase
    public static String snakeToCamel_168(String s) {
        if (s == null) return null;
        String[] parts = s.split("_");
        StringBuilder camel = new StringBuilder();
        camel.append(parts[0].toLowerCase());
        for (int i=1; i<parts.length; i++) {
            camel.append(parts[i].substring(0,1).toUpperCase());
            camel.append(parts[i].substring(1).toLowerCase());
        }
        return camel.toString();
    }

    // 169. Shuffle elements in a list
    public static <T> void shuffleList_169(List<T> list) {
        if (list == null) return;
        Collections.shuffle(list);
    }

    // 170. Check if string is a valid IPv4 address
    public static boolean isValidIPv4_170(String ip) {
        if (ip == null) return false;
        String ipv4Regex = "^((25[0-5]|2[0-4]\\d|[01]?\\d\\d?)\\.){3}(25[0-5]|2[0-4]\\d|[01]?\\d\\d?)$";
        return Pattern.matches(ipv4Regex, ip);
    }

    // 171. Convert a List of Integers to int array
    public static int[] listToIntArray_171(List<Integer> list) {
        if (list == null) return new int[0];
        int[] arr = new int[list.size()];
        for (int i=0; i<list.size(); i++) {
            arr[i] = list.get(i);
        }
        return arr;
    }

    // 172. Convert int array to List<Integer>
    public static List<Integer> intArrayToList_172(int[] arr) {
        if (arr == null) return new ArrayList<>();
        List<Integer> list = new ArrayList<>(arr.length);
        for (int v : arr) list.add(v);
        return list;
    }

    // 173. Format bytes as human-readable string (KB, MB, GB)
    public static String formatBytes_173(long bytes) {
        if (bytes < 1024) return bytes + " B";
        int exp = (int) (Math.log(bytes) / Math.log(1024));
        char unit = "KMGTPE".charAt(exp - 1);
        return String.format("%.1f %sB", bytes / Math.pow(1024, exp), unit);
    }

    // 174. Get the file name without extension
    public static String getFileNameWithoutExtension_174(String filename) {
        if (filename == null) return null;
        int dotIndex = filename.lastIndexOf('.');
        if (dotIndex == -1) return filename;
        return filename.substring(0, dotIndex);
    }

    // 175. Get file size in bytes
    public static long getFileSize_175(String filePath) {
        if (filePath == null) return 0;
        File file = new File(filePath);
        return file.exists() ? file.length() : 0;
    }

    // 176. Convert milliseconds to formatted duration string (e.g., "1h 3m 5s")
    public static String formatDuration_176(long millis) {
        long seconds = millis / 1000;
        long hours = seconds / 3600;
        long minutes = (seconds % 3600) / 60;
        long secs = seconds % 60;
        StringBuilder sb = new StringBuilder();
        if (hours > 0) sb.append(hours).append("h ");
        if (minutes > 0) sb.append(minutes).append("m ");
        sb.append(secs).append("s");
        return sb.toString().trim();
    }

    // 177. Check if a string contains only letters and digits
    public static boolean isAlphanumeric_177(String s) {
        if (s == null) return false;
        return s.matches("[A-Za-z0-9]+");
    }

    // 178. Remove all special characters from a string (keep letters and digits)
    public static String removeSpecialCharacters_178(String s) {
        if (s == null) return null;
        return s.replaceAll("[^A-Za-z0-9]", "");
    }

    // 179. Repeat a string N times
    public static String repeatString_179(String s, int times) {
        if (s == null || times <= 0) return "";
        return s.repeat(times);
    }

    // 180. Get random element from list
    public static <T> T getRandomElement_180(List<T> list) {
        if (list == null || list.isEmpty()) return null;
        Random rand = new Random();
        return list.get(rand.nextInt(list.size()));
    }

    // 181. Calculate factorial of a number
    public static long factorial_181(int n) {
        if (n < 0) throw new IllegalArgumentException("Negative number");
        long result = 1;
        for (int i=2; i<=n; i++) {
            result *= i;
        }
        return result;
    }

    // 182. Check if year is a leap year
    public static boolean isLeapYear_182(int year) {
        return (year % 4 == 0 && year % 100 != 0) || (year % 400 == 0);
    }

    // 183. Convert integer to binary string
    public static String toBinaryString_183(int n) {
        return Integer.toBinaryString(n);
    }

    // 184. Convert integer to hex string
    public static String toHexString_184(int n) {
        return Integer.toHexString(n);
    }

    // 185. Convert hex string to integer
    public static int hexStringToInt_185(String hex) {
        if (hex == null) return 0;
        try {
            return Integer.parseInt(hex, 16);
        } catch (NumberFormatException e) {
            return 0;
        }
    }

    // 186. Remove duplicate words in a sentence
    public static String removeDuplicateWords_186(String s) {
        if (s == null) return null;
        String[] words = s.split("\\s+");
        LinkedHashSet<String> set = new LinkedHashSet<>(Arrays.asList(words));
        return String.join(" ", set);
    }

    // 187. Convert list to string with custom delimiter
    public static <T> String listToString_187(List<T> list, String delimiter) {
        if (list == null || list.isEmpty()) return "";
        if (delimiter == null) delimiter = ",";
        StringBuilder sb = new StringBuilder();
        for (T item : list) {
            sb.append(item).append(delimiter);
        }
        sb.setLength(sb.length() - delimiter.length());
        return sb.toString();
    }

    // 188. Find index of max element in int array
    public static int indexOfMax_188(int[] arr) {
        if (arr == null || arr.length == 0) return -1;
        int maxIndex = 0;
        for (int i=1; i<arr.length; i++) {
            if (arr[i] > arr[maxIndex]) maxIndex = i;
        }
        return maxIndex;
    }

    // 189. Find index of min element in int array
    public static int indexOfMin_189(int[] arr) {
        if (arr == null || arr.length == 0) return -1;
        int minIndex = 0;
        for (int i=1; i<arr.length; i++) {
            if (arr[i] < arr[minIndex]) minIndex = i;
        }
        return minIndex;
    }

    // 190. Clamp a number between min and max
    public static int clamp_190(int value, int min, int max) {
        if (min > max) throw new IllegalArgumentException("min > max");
        return Math.max(min, Math.min(max, value));
    }

    // 191. Format double as percentage string with 2 decimals
    public static String formatPercentage_191(double value) {
        return String.format("%.2f%%", value * 100);
    }

    // 192. Convert seconds to readable format "Xd Yh Zm Ws"
    public static String formatSecondsDetailed_192(long seconds) {
        long days = seconds / 86400;
        long hours = (seconds % 86400) / 3600;
        long minutes = (seconds % 3600) / 60;
        long secs = seconds % 60;
        StringBuilder sb = new StringBuilder();
        if (days > 0) sb.append(days).append("d ");
        if (hours > 0) sb.append(hours).append("h ");
        if (minutes > 0) sb.append(minutes).append("m ");
        sb.append(secs).append("s");
        return sb.toString().trim();
    }

    // 193. Check if a string is a palindrome (case-insensitive, ignore spaces)
    public static boolean isPalindrome_193(String s) {
        if (s == null) return false;
        String cleaned = s.replaceAll("\\s+", "").toLowerCase();
        int left = 0, right = cleaned.length() - 1;
        while (left < right) {
            if (cleaned.charAt(left) != cleaned.charAt(right)) return false;
            left++;
            right--;
        }
        return true;
    }

    // 194. Convert RGB components to hex color string
    public static String rgbToHex_194(int r, int g, int b) {
        r = clamp_190(r, 0, 255);
        g = clamp_190(g, 0, 255);
        b = clamp_190(b, 0, 255);
        return String.format("#%02X%02X%02X", r, g, b);
    }

    // 195. Convert hex color string to RGB int array [r, g, b]
    public static int[] hexToRgb_195(String hex) {
        if (hex == null) return null;
        hex = hex.replace("#", "");
        if (hex.length() != 6) return null;
        try {
            int r = Integer.parseInt(hex.substring(0, 2), 16);
            int g = Integer.parseInt(hex.substring(2, 4), 16);
            int b = Integer.parseInt(hex.substring(4, 6), 16);
            return new int[]{r, g, b};
        } catch (NumberFormatException e) {
            return null;
        }
    }

    // 196. Get current timestamp in milliseconds
    public static long currentTimestampMillis_196() {
        return System.currentTimeMillis();
    }

    // 197. Sleep for given milliseconds, ignoring InterruptedException
    public static void sleepMillis_197(long millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException ignored) {
        }
    }

    // 198. Convert List<String> to Set<String>
    public static Set<String> listToSet_198(List<String> list) {
        if (list == null) return new HashSet<>();
        return new HashSet<>(list);
    }

    // 199. Reverse a string
    public static String reverseString_199(String s) {
        if (s == null) return null;
        return new StringBuilder(s).reverse().toString();
    }

    // 200. Extract digits from a string (return as concatenated string)
    public static String extractDigits_200(String s) {
        if (s == null) return "";
        return s.replaceAll("\\D+", "");
    }

    // 201. Check if string starts with a prefix (case-insensitive)
    public static boolean startsWithIgnoreCase_201(String s, String prefix) {
        if (s == null || prefix == null) return false;
        return s.toLowerCase().startsWith(prefix.toLowerCase());
    }

    // 202. Check if string ends with a suffix (case-insensitive)
    public static boolean endsWithIgnoreCase_202(String s, String suffix) {
        if (s == null || suffix == null) return false;
        return s.toLowerCase().endsWith(suffix.toLowerCase());
    }

    // 203. Convert an array of strings to a list
    public static List<String> arrayToList_203(String[] arr) {
        if (arr == null) return new ArrayList<>();
        return Arrays.asList(arr);
    }

    // 204. Convert a list of strings to an array
    public static String[] listToArray_204(List<String> list) {
        if (list == null) return new String[0];
        return list.toArray(new String[0]);
    }

    // 205. Get the current date as a formatted string (yyyy-MM-dd)
    public static String currentDate_205() {
        return new SimpleDateFormat("yyyy-MM-dd").format(new Date());
    }

    // 206. Get the current time as a formatted string (HH:mm:ss)
    public static String currentTime_206() {
        return new SimpleDateFormat("HH:mm:ss").format(new Date());
    }

    // 207. Convert date string from one format to another
    public static String convertDateFormat_207(String dateStr, String fromFormat, String toFormat) {
        if (dateStr == null || fromFormat == null || toFormat == null) return null;
        try {
            SimpleDateFormat sdfFrom = new SimpleDateFormat(fromFormat);
            SimpleDateFormat sdfTo = new SimpleDateFormat(toFormat);
            Date date = sdfFrom.parse(dateStr);
            return sdfTo.format(date);
        } catch (ParseException e) {
            return null;
        }
    }

    // 208. Round a double to N decimal places
    public static double round_208(double value, int places) {
        if (places < 0) throw new IllegalArgumentException();
        long factor = (long) Math.pow(10, places);
        return (double) Math.round(value * factor) / factor;
    }

    // 209. Get the day of week from date string (yyyy-MM-dd), e.g. "Monday"
    public static String getDayOfWeek_209(String dateStr) {
        if (dateStr == null) return null;
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Date date = sdf.parse(dateStr);
            SimpleDateFormat dayFormat = new SimpleDateFormat("EEEE");
            return dayFormat.format(date);
        } catch (ParseException e) {
            return null;
        }
    }

    // 210. Convert seconds to HH:mm:ss format
    public static String secondsToHHmmss_210(long totalSeconds) {
        long hours = totalSeconds / 3600;
        long minutes = (totalSeconds % 3600) / 60;
        long seconds = totalSeconds % 60;
        return String.format("%02d:%02d:%02d", hours, minutes, seconds);
    }

    // 211. Get the nth Fibonacci number (0-based)
    public static long fibonacci_211(int n) {
        if (n < 0) throw new IllegalArgumentException();
        if (n == 0) return 0;
        if (n == 1) return 1;
        long a = 0, b = 1, c = 0;
        for (int i = 2; i <= n; i++) {
            c = a + b;
            a = b;
            b = c;
        }
        return c;
    }

    // 212. Convert a map to a query string (e.g. key1=value1&key2=value2)
    public static String mapToQueryString_212(Map<String, String> map) {
        if (map == null || map.isEmpty()) return "";
        StringBuilder sb = new StringBuilder();
        for (Map.Entry<String, String> entry : map.entrySet()) {
            if (entry.getKey() != null && entry.getValue() != null) {
                sb.append(entry.getKey()).append("=").append(entry.getValue()).append("&");
            }
        }
        sb.setLength(sb.length() - 1);
        return sb.toString();
    }

    // 213. Parse query string to a map
    public static Map<String, String> queryStringToMap_213(String query) {
        Map<String, String> map = new HashMap<>();
        if (query == null || query.isEmpty()) return map;
        String[] pairs = query.split("&");
        for (String pair : pairs) {
            String[] kv = pair.split("=", 2);
            if (kv.length == 2) {
                map.put(kv[0], kv[1]);
            } else if (kv.length == 1) {
                map.put(kv[0], "");
            }
        }
        return map;
    }

    // 214. Check if a number is prime
    public static boolean isPrime_214(int n) {
        if (n <= 1) return false;
        if (n <= 3) return true;
        if (n % 2 == 0 || n % 3 == 0) return false;
        for (int i = 5; i * i <= n; i += 6) {
            if (n % i == 0 || n % (i + 2) == 0) return false;
        }
        return true;
    }

    // 215. Get prime numbers up to a limit (inclusive)
    public static List<Integer> primesUpTo_215(int limit) {
        List<Integer> primes = new ArrayList<>();
        for (int i = 2; i <= limit; i++) {
            if (isPrime_214(i)) primes.add(i);
        }
        return primes;
    }

    // 216. Calculate Greatest Common Divisor (GCD)
    public static int gcd_216(int a, int b) {
        if (b == 0) return Math.abs(a);
        return gcd_216(b, a % b);
    }

    // 217. Calculate Least Common Multiple (LCM)
    public static int lcm_217(int a, int b) {
        if (a == 0 || b == 0) return 0;
        return Math.abs(a * b) / gcd_216(a, b);
    }

    // 218. Convert a string to title case
    public static String toTitleCase_218(String s) {
        if (s == null || s.isEmpty()) return s;
        String[] words = s.toLowerCase().split("\\s+");
        StringBuilder sb = new StringBuilder();
        for (String word : words) {
            if (!word.isEmpty()) {
                sb.append(Character.toUpperCase(word.charAt(0)));
                if (word.length() > 1) sb.append(word.substring(1));
                sb.append(" ");
            }
        }
        return sb.toString().trim();
    }

    // 219. Convert boolean to "yes" or "no"
    public static String boolToYesNo_219(boolean value) {
        return value ? "yes" : "no";
    }

    // 220. Parse "yes" or "no" to boolean
    public static boolean yesNoToBool_220(String s) {
        if (s == null) return false;
        s = s.trim().toLowerCase();
        return s.equals("yes") || s.equals("true") || s.equals("1");
    }

    // 221. Generate a random integer between min and max (inclusive)
    public static int randomIntInRange_221(int min, int max) {
        if (min > max) throw new IllegalArgumentException("min > max");
        Random rand = new Random();
        return rand.nextInt(max - min + 1) + min;
    }

    // 222. Check if a string is blank (null, empty, or whitespace only)
    public static boolean isBlank_222(String s) {
        return s == null || s.trim().isEmpty();
    }

    // 223. Remove whitespace from start and end of string
    public static String trim_223(String s) {
        if (s == null) return null;
        return s.trim();
    }

    // 224. Check if array contains a value
    public static <T> boolean arrayContains_224(T[] arr, T value) {
        if (arr == null) return false;
        for (T elem : arr) {
            if (Objects.equals(elem, value)) return true;
        }
        return false;
    }

    // 225. Convert boolean array to int array (true=1, false=0)
    public static int[] booleanArrayToIntArray_225(boolean[] arr) {
        if (arr == null) return new int[0];
        int[] res = new int[arr.length];
        for (int i = 0; i < arr.length; i++) {
            res[i] = arr[i] ? 1 : 0;
        }
        return res;
    }

    // 226. Convert int array to boolean array (0=false, others=true)
    public static boolean[] intArrayToBooleanArray_226(int[] arr) {
        if (arr == null) return new boolean[0];
        boolean[] res = new boolean[arr.length];
        for (int i = 0; i < arr.length; i++) {
            res[i] = arr[i] != 0;
        }
        return res;
    }

    // 227. Convert camelCase string to Sentence case
    public static String camelToSentence_227(String s) {
        if (s == null) return null;
        String result = s.replaceAll("([a-z])([A-Z])", "$1 $2");
        return Character.toUpperCase(result.charAt(0)) + result.substring(1).toLowerCase();
    }

    // 227. Convert camelCase string to Sentence case
    public static String camelToSentence_227(String s) {
        if (s == null || s.isEmpty()) return s;
        String result = s.replaceAll("([a-z])([A-Z])", "$1 $2");
        return Character.toUpperCase(result.charAt(0)) + result.substring(1).toLowerCase();
    }

    // 228. Generate a random hex color string (e.g., "#A1B2C3")
    public static String randomHexColor_228() {
        Random rand = new Random();
        int r = rand.nextInt(256);
        int g = rand.nextInt(256);
        int b = rand.nextInt(256);
        return String.format("#%02X%02X%02X", r, g, b);
    }

    // 229. Check if a string contains only digits
    public static boolean isNumeric_229(String s) {
        if (s == null) return false;
        return s.matches("\\d+");
    }

    // 230. Convert an int array to a comma-separated string
    public static String intArrayToCsv_230(int[] arr) {
        if (arr == null || arr.length == 0) return "";
        StringBuilder sb = new StringBuilder();
        for (int num : arr) {
            sb.append(num).append(",");
        }
        sb.setLength(sb.length() - 1);
        return sb.toString();
    }

    // 231. Convert a CSV string to int array
    public static int[] csvToIntArray_231(String csv) {
        if (csv == null || csv.trim().isEmpty()) return new int[0];
        String[] parts = csv.split(",");
        int[] result = new int[parts.length];
        for (int i = 0; i < parts.length; i++) {
            try {
                result[i] = Integer.parseInt(parts[i].trim());
            } catch (NumberFormatException e) {
                result[i] = 0;
            }
        }
        return result;
    }

    // 232. Capitalize the first letter of each word in a string
    public static String capitalizeWords_232(String s) {
        if (s == null || s.isEmpty()) return s;
        String[] words = s.split("\\s+");
        StringBuilder sb = new StringBuilder();
        for (String word : words) {
            if (!word.isEmpty()) {
                sb.append(Character.toUpperCase(word.charAt(0)));
                if (word.length() > 1) sb.append(word.substring(1).toLowerCase());
                sb.append(" ");
            }
        }
        return sb.toString().trim();
    }

    // 233. Calculate factorial of a number (recursive)
    public static long factorial_233(int n) {
        if (n < 0) throw new IllegalArgumentException("Negative factorial");
        if (n == 0) return 1;
        return n * factorial_233(n - 1);
    }

    // 234. Check if two arrays are equal (same length and elements)
    public static <T> boolean arraysEqual_234(T[] arr1, T[] arr2) {
        if (arr1 == arr2) return true;
        if (arr1 == null || arr2 == null) return false;
        if (arr1.length != arr2.length) return false;
        for (int i = 0; i < arr1.length; i++) {
            if (!Objects.equals(arr1[i], arr2[i])) return false;
        }
        return true;
    }

    // 235. Flatten a list of lists into a single list
    public static <T> List<T> flattenList_235(List<List<T>> lists) {
        List<T> flat = new ArrayList<>();
        if (lists == null) return flat;
        for (List<T> list : lists) {
            if (list != null) {
                flat.addAll(list);
            }
        }
        return flat;
    }

    // 236. Convert a list of Integers to int array
    public static int[] listToIntArray_236(List<Integer> list) {
        if (list == null) return new int[0];
        int[] arr = new int[list.size()];
        for (int i = 0; i < list.size(); i++) {
            arr[i] = list.get(i);
        }
        return arr;
    }

    // 237. Remove duplicates from a list while preserving order
    public static <T> List<T> removeDuplicates_237(List<T> list) {
        if (list == null) return new ArrayList<>();
        Set<T> seen = new LinkedHashSet<>();
        seen.addAll(list);
        return new ArrayList<>(seen);
    }

    // 238. Check if a string is a valid email address (simple regex)
    public static boolean isValidEmail_238(String email) {
        if (email == null) return false;
        return email.matches("^[\\w._%+-]+@[\\w.-]+\\.[A-Za-z]{2,6}$");
    }

    // 239. Generate MD5 hash of a string
    public static String md5Hash_239(String input) {
        if (input == null) return null;
        try {
            java.security.MessageDigest md = java.security.MessageDigest.getInstance("MD5");
            byte[] bytes = md.digest(input.getBytes("UTF-8"));
            StringBuilder sb = new StringBuilder();
            for (byte b : bytes) {
                sb.append(String.format("%02x", b & 0xff));
            }
            return sb.toString();
        } catch (Exception e) {
            return null;
        }
    }

    // 240. Convert a string to snake_case
    public static String toSnakeCase_240(String s) {
        if (s == null) return null;
        String result = s.replaceAll("([a-z])([A-Z])", "$1_$2");
        return result.toLowerCase().replaceAll("\\s+", "_");
    }

    // 241. Generate a UUID string
    public static String generateUUID_241() {
        return UUID.randomUUID().toString();
    }

    // 242. Count vowels in a string
    public static int countVowels_242(String s) {
        if (s == null) return 0;
        int count = 0;
        for (char c : s.toLowerCase().toCharArray()) {
            if ("aeiou".indexOf(c) >= 0) count++;
        }
        return count;
    }

    // 243. Count consonants in a string
    public static int countConsonants_243(String s) {
        if (s == null) return 0;
        int count = 0;
        for (char c : s.toLowerCase().toCharArray()) {
            if (c >= 'a' && c <= 'z' && "aeiou".indexOf(c) == -1) count++;
        }
        return count;
    }

    // 244. Replace multiple spaces with a single space
    public static String normalizeSpaces_244(String s) {
        if (s == null) return null;
        return s.trim().replaceAll("\\s+", " ");
    }

    // 245. Get the maximum element in an int array
    public static int maxInt_245(int[] arr) {
        if (arr == null || arr.length == 0) throw new IllegalArgumentException("Array empty");
        int max = arr[0];
        for (int val : arr) {
            if (val > max) max = val;
        }
        return max;
    }

    // 246. Get the minimum element in an int array
    public static int minInt_246(int[] arr) {
        if (arr == null || arr.length == 0) throw new IllegalArgumentException("Array empty");
        int min = arr[0];
        for (int val : arr) {
            if (val < min) min = val;
        }
        return min;
    }

    // 247. Swap two elements in a list by indices
    public static <T> void swapElements_247(List<T> list, int i, int j) {
        if (list == null || i < 0 || j < 0 || i >= list.size() || j >= list.size()) return;
        T temp = list.get(i);
        list.set(i, list.get(j));
        list.set(j, temp);
    }

    // 248. Check if a string is all uppercase
    public static boolean isAllUpperCase_248(String s) {
        if (s == null || s.isEmpty()) return false;
        return s.equals(s.toUpperCase());
    }

    // 249. Check if a string is all lowercase
    public static boolean isAllLowerCase_249(String s) {
        if (s == null || s.isEmpty()) return false;
        return s.equals(s.toLowerCase());
    }

    // 250. Convert a List<Integer> to comma-separated string
    public static String listIntToCsv_250(List<Integer> list) {
        if (list == null || list.isEmpty()) return "";
        StringBuilder sb = new StringBuilder();
        for (Integer i : list) {
            sb.append(i).append(",");
        }
        sb.setLength(sb.length() - 1);
        return sb.toString();
    }

    // 251. Check if two strings are anagrams of each other
    public static boolean areAnagrams_251(String s1, String s2) {
        if (s1 == null || s2 == null) return false;
        char[] a = s1.replaceAll("\\s+", "").toLowerCase().toCharArray();
        char[] b = s2.replaceAll("\\s+", "").toLowerCase().toCharArray();
        if (a.length != b.length) return false;
        Arrays.sort(a);
        Arrays.sort(b);
        return Arrays.equals(a, b);
    }

    // 252. Reverse an array of integers
    public static void reverseArray_252(int[] arr) {
        if (arr == null) return;
        int i = 0, j = arr.length - 1;
        while (i < j) {
            int temp = arr[i];
            arr[i] = arr[j];
            arr[j] = temp;
            i++;
            j--;
        }
    }

    // 253. Find the second largest number in an int array
    public static int secondLargest_253(int[] arr) {
        if (arr == null || arr.length < 2) throw new IllegalArgumentException("Array too small");
        int max = Integer.MIN_VALUE;
        int secondMax = Integer.MIN_VALUE;
        for (int val : arr) {
            if (val > max) {
                secondMax = max;
                max = val;
            } else if (val > secondMax && val < max) {
                secondMax = val;
            }
        }
        if (secondMax == Integer.MIN_VALUE) throw new IllegalArgumentException("No second largest");
        return secondMax;
    }

    // 254. Check if a string is palindrome (ignoring case and non-alphanumeric)
    public static boolean isPalindrome_254(String s) {
        if (s == null) return false;
        String filtered = s.replaceAll("[^A-Za-z0-9]", "").toLowerCase();
        return filtered.equals(new StringBuilder(filtered).reverse().toString());
    }

    // 255. Remove all whitespace from a string
    public static String removeWhitespace_255(String s) {
        if (s == null) return null;
        return s.replaceAll("\\s+", "");
    }

    // 256. Count occurrences of a substring in a string
    public static int countOccurrences_256(String str, String sub) {
        if (str == null || sub == null || sub.isEmpty()) return 0;
        int count = 0, idx = 0;
        while ((idx = str.indexOf(sub, idx)) != -1) {
            count++;
            idx += sub.length();
        }
        return count;
    }

    // 257. Get the nth triangular number
    public static int triangularNumber_257(int n) {
        if (n < 0) throw new IllegalArgumentException();
        return n * (n + 1) / 2;
    }

    // 258. Generate a random alphanumeric string of given length
    public static String randomAlphaNumeric_258(int length) {
        if (length <= 0) return "";
        String chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        Random rand = new Random();
        StringBuilder sb = new StringBuilder(length);
        for (int i = 0; i < length; i++) {
            sb.append(chars.charAt(rand.nextInt(chars.length())));
        }
        return sb.toString();
    }

    // 259. Convert seconds to days, hours, minutes, and seconds string
    public static String secondsToDHMS_259(long totalSeconds) {
        long days = totalSeconds / 86400;
        long hours = (totalSeconds % 86400) / 3600;
        long minutes = (totalSeconds % 3600) / 60;
        long seconds = totalSeconds % 60;
        return String.format("%d days %02d:%02d:%02d", days, hours, minutes, seconds);
    }

    // 260. Check if a number is a perfect square
    public static boolean isPerfectSquare_260(int n) {
        if (n < 0) return false;
        int root = (int) Math.sqrt(n);
        return root * root == n;
    }

    // 261. Merge two sorted arrays into a single sorted array
    public static int[] mergeSortedArrays_261(int[] a, int[] b) {
        if (a == null) return b == null ? new int[0] : b.clone();
        if (b == null) return a.clone();
        int[] merged = new int[a.length + b.length];
        int i = 0, j = 0, k = 0;
        while (i < a.length && j < b.length) {
            if (a[i] <= b[j]) merged[k++] = a[i++];
            else merged[k++] = b[j++];
        }
        while (i < a.length) merged[k++] = a[i++];
        while (j < b.length) merged[k++] = b[j++];
        return merged;
    }

    // 262. Find the mode (most frequent element) in an int array
    public static Integer mode_262(int[] arr) {
        if (arr == null || arr.length == 0) return null;
        Map<Integer, Integer> freq = new HashMap<>();
        int maxCount = 0;
        Integer mode = null;
        for (int num : arr) {
            int count = freq.getOrDefault(num, 0) + 1;
            freq.put(num, count);
            if (count > maxCount) {
                maxCount = count;
                mode = num;
            }
        }
        return mode;
    }

    // 263. Calculate sum of digits in an integer
    public static int sumOfDigits_263(int n) {
        n = Math.abs(n);
        int sum = 0;
        while (n > 0) {
            sum += n % 10;
            n /= 10;
        }
        return sum;
    }

    // 264. Find common elements between two lists
    public static <T> List<T> commonElements_264(List<T> list1, List<T> list2) {
        if (list1 == null || list2 == null) return Collections.emptyList();
        Set<T> set1 = new HashSet<>(list1);
        List<T> common = new ArrayList<>();
        for (T item : list2) {
            if (set1.contains(item)) common.add(item);
        }
        return common;
    }

    // 265. Convert byte array to hex string
    public static String bytesToHex_265(byte[] bytes) {
        if (bytes == null) return null;
        StringBuilder sb = new StringBuilder();
        for (byte b : bytes) {
            sb.append(String.format("%02x", b & 0xff));
        }
        return sb.toString();
    }

    // 266. Convert hex string to byte array
    public static byte[] hexToBytes_266(String hex) {
        if (hex == null) return null;
        int len = hex.length();
        if (len % 2 != 0) return null;
        byte[] data = new byte[len / 2];
        try {
            for (int i = 0; i < len; i += 2) {
                data[i / 2] = (byte) ((Character.digit(hex.charAt(i), 16) << 4)
                        + Character.digit(hex.charAt(i+1), 16));
            }
        } catch (Exception e) {
            return null;
        }
        return data;
    }

    // 267. Generate SHA-256 hash of a string
    public static String sha256Hash_267(String input) {
        if (input == null) return null;
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] bytes = md.digest(input.getBytes("UTF-8"));
            return bytesToHex_265(bytes);
        } catch (Exception e) {
            return null;
        }
    }

    // 268. Get substring between two substrings (first occurrence)
    public static String substringBetween_268(String text, String start, String end) {
        if (text == null || start == null || end == null) return null;
        int startIndex = text.indexOf(start);
        if (startIndex == -1) return null;
        startIndex += start.length();
        int endIndex = text.indexOf(end, startIndex);
        if (endIndex == -1) return null;
        return text.substring(startIndex, endIndex);
    }

    // 269. Convert a list of strings to uppercase
    public static List<String> toUpperCaseList_269(List<String> list) {
        if (list == null) return new ArrayList<>();
        List<String> res = new ArrayList<>();
        for (String s : list) {
            res.add(s == null ? null : s.toUpperCase());
        }
        return res;
    }

    // 270. Convert a list of strings to lowercase
    public static List<String> toLowerCaseList_270(List<String> list) {
        if (list == null) return new ArrayList<>();
        List<String> res = new ArrayList<>();
        for (String s : list) {
            res.add(s == null ? null : s.toLowerCase());
        }
        return res;
    }

    // 271. Find all indexes of a character in a string
    public static List<Integer> indexesOfChar_271(String s, char c) {
        List<Integer> indexes = new ArrayList<>();
        if (s == null) return indexes;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == c) indexes.add(i);
        }
        return indexes;
    }

    // 272. Check if an int array is sorted ascending
    public static boolean isSortedAsc_272(int[] arr) {
        if (arr == null || arr.length <= 1) return true;
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] < arr[i-1]) return false;
        }
        return true;
    }

    // 273. Remove null elements from a list
    public static <T> List<T> removeNulls_273(List<T> list) {
        if (list == null) return new ArrayList<>();
        List<T> res = new ArrayList<>();
        for (T t : list) {
            if (t != null) res.add(t);
        }
        return res;
    }

    // 274. Get the length of longest word in a sentence
    public static int longestWordLength_274(String sentence) {
        if (sentence == null || sentence.isEmpty()) return 0;
        String[] words = sentence.split("\\s+");
        int maxLen = 0;
        for (String w : words) {
            if (w.length() > maxLen) maxLen = w.length();
        }
        return maxLen;
    }

    // 275. Generate a random boolean
    public static boolean randomBoolean_275() {
        return new Random().nextBoolean();
    }

    // 276. Join a list of strings with a delimiter
    public static String joinWithDelimiter_276(List<String> list, String delimiter) {
        if (list == null || list.isEmpty()) return "";
        if (delimiter == null) delimiter = "";
        StringBuilder sb = new StringBuilder();
        for (String s : list) {
            if (s != null) sb.append(s);
            sb.append(delimiter);
        }
        if (sb.length() > 0 && delimiter.length() > 0) {
            sb.setLength(sb.length() - delimiter.length());
        }
        return sb.toString();
    }

    // 277. Generate a random integer between min and max (inclusive)
    public static int randomIntBetween_277(int min, int max) {
        if (min > max) throw new IllegalArgumentException("min > max");
        return new Random().nextInt(max - min + 1) + min;
    }

    // 278. Format a double to n decimal places
    public static String formatDecimal_278(double value, int decimalPlaces) {
        if (decimalPlaces < 0) throw new IllegalArgumentException("Negative decimal places");
        return String.format("%." + decimalPlaces + "f", value);
    }

    // 279. Convert a map to a query string (e.g., key1=val1&key2=val2)
    public static String mapToQueryString_279(Map<String, String> map) {
        if (map == null || map.isEmpty()) return "";
        StringBuilder sb = new StringBuilder();
        for (Map.Entry<String, String> entry : map.entrySet()) {
            if (entry.getKey() != null && entry.getValue() != null) {
                sb.append(entry.getKey()).append("=").append(entry.getValue()).append("&");
            }
        }
        if (sb.length() > 0) sb.setLength(sb.length() - 1);
        return sb.toString();
    }

    // 280. Parse a query string to a map (e.g., key1=val1&key2=val2)
    public static Map<String, String> queryStringToMap_280(String qs) {
        Map<String, String> map = new HashMap<>();
        if (qs == null || qs.isEmpty()) return map;
        String[] pairs = qs.split("&");
        for (String pair : pairs) {
            String[] kv = pair.split("=", 2);
            if (kv.length == 2) {
                map.put(kv[0], kv[1]);
            }
        }
        return map;
    }

    // 281. Check if a string contains only letters
    public static boolean isAlpha_281(String s) {
        if (s == null || s.isEmpty()) return false;
        return s.matches("[a-zA-Z]+");
    }

    // 282. Generate a random string from a given set of characters
    public static String randomStringFromSet_282(int length, String charset) {
        if (length <= 0) return "";
        if (charset == null || charset.isEmpty()) charset = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        Random rand = new Random();
        StringBuilder sb = new StringBuilder(length);
        for (int i = 0; i < length; i++) {
            sb.append(charset.charAt(rand.nextInt(charset.length())));
        }
        return sb.toString();
    }

    // 283. Compute the greatest common divisor (GCD) of two numbers
    public static int gcd_283(int a, int b) {
        a = Math.abs(a);
        b = Math.abs(b);
        while (b != 0) {
            int temp = b;
            b = a % b;
            a = temp;
        }
        return a;
    }

    // 284. Compute the least common multiple (LCM) of two numbers
    public static int lcm_284(int a, int b) {
        if (a == 0 || b == 0) return 0;
        return Math.abs(a * b) / gcd_283(a, b);
    }

    // 285. Check if a number is prime
    public static boolean isPrime_285(int n) {
        if (n <= 1) return false;
        if (n <= 3) return true;
        if (n % 2 == 0 || n % 3 == 0) return false;
        for (int i = 5; i * i <= n; i += 6) {
            if (n % i == 0 || n % (i + 2) == 0) return false;
        }
        return true;
    }

    // 286. Find the factorial of a number (iterative)
    public static long factorialIter_286(int n) {
        if (n < 0) throw new IllegalArgumentException("Negative");
        long fact = 1;
        for (int i = 2; i <= n; i++) {
            fact *= i;
        }
        return fact;
    }

    // 287. Convert binary string to decimal integer
    public static int binaryToDecimal_287(String binary) {
        if (binary == null || !binary.matches("[01]+")) throw new IllegalArgumentException("Invalid binary");
        return Integer.parseInt(binary, 2);
    }

    // 288. Convert decimal integer to binary string
    public static String decimalToBinary_288(int decimal) {
        return Integer.toBinaryString(decimal);
    }

    // 289. Check if a number is even
    public static boolean isEven_289(int n) {
        return n % 2 == 0;
    }

    // 290. Check if a number is odd
    public static boolean isOdd_290(int n) {
        return n % 2 != 0;
    }

    // 291. Calculate the sum of all elements in an int array
    public static int sumIntArray_291(int[] arr) {
        if (arr == null) return 0;
        int sum = 0;
        for (int val : arr) {
            sum += val;
        }
        return sum;
    }

    // 292. Calculate average of elements in a double array
    public static double averageDoubleArray_292(double[] arr) {
        if (arr == null || arr.length == 0) throw new IllegalArgumentException("Empty array");
        double sum = 0;
        for (double v : arr) {
            sum += v;
        }
        return sum / arr.length;
    }

    // 293. Convert a string to title case
    public static String toTitleCase_293(String s) {
        if (s == null || s.isEmpty()) return s;
        String[] words = s.toLowerCase().split("\\s+");
        StringBuilder sb = new StringBuilder();
        for (String w : words) {
            if (w.length() > 0) {
                sb.append(Character.toUpperCase(w.charAt(0)));
                sb.append(w.substring(1));
                sb.append(" ");
            }
        }
        return sb.toString().trim();
    }

    // 294. Check if a string ends with any of a given list of suffixes
    public static boolean endsWithAny_294(String s, List<String> suffixes) {
        if (s == null || suffixes == null) return false;
        for (String suffix : suffixes) {
            if (suffix != null && s.endsWith(suffix)) return true;
        }
        return false;
    }

    // 295. Generate a random color in RGB format "rgb(r,g,b)"
    public static String randomRgbColor_295() {
        Random rand = new Random();
        return String.format("rgb(%d,%d,%d)", rand.nextInt(256), rand.nextInt(256), rand.nextInt(256));
    }

    // 296. Convert a string array to list
    public static List<String> arrayToList_296(String[] arr) {
        if (arr == null) return new ArrayList<>();
        return Arrays.asList(arr);
    }

    // 297. Check if a list contains duplicates
    public static <T> boolean hasDuplicates_297(List<T> list) {
        if (list == null) return false;
        Set<T> set = new HashSet<>();
        for (T item : list) {
            if (!set.add(item)) return true;
        }
        return false;
    }

    // 298. Round a double to nearest integer
    public static int roundDouble_298(double val) {
        return (int) Math.round(val);
    }

    // 299. Calculate the sum of squares of integers in an array
    public static int sumSquares_299(int[] arr) {
        if (arr == null) return 0;
        int sum = 0;
        for (int val : arr) {
            sum += val * val;
        }
        return sum;
    }

    // 300. Convert degrees to radians
    public static double degreesToRadians_300(double degrees) {
        return degrees * Math.PI / 180.0;
    }

    // 301. Convert radians to degrees
    public static double radiansToDegrees_301(double radians) {
        return radians * 180.0 / Math.PI;
    }

    // 302. Check if two arrays are equal (same elements in order)
    public static boolean arraysEqual_302(int[] a, int[] b) {
        if (a == b) return true;
        if (a == null || b == null) return false;
        if (a.length != b.length) return false;
        for (int i = 0; i < a.length; i++) {
            if (a[i] != b[i]) return false;
        }
        return true;
    }

    // 303. Capitalize first letter of each sentence in a paragraph
    public static String capitalizeSentences_303(String paragraph) {
        if (paragraph == null || paragraph.isEmpty()) return paragraph;
        String[] sentences = paragraph.split("(?<=[.!?])\\s*");
        StringBuilder sb = new StringBuilder();
        for (String sentence : sentences) {
            if (sentence.length() > 0) {
                sb.append(Character.toUpperCase(sentence.charAt(0)));
                sb.append(sentence.substring(1));
                sb.append(" ");
            }
        }
        return sb.toString().trim();
    }

    // 304. Flatten a list of lists into a single list
    public static <T> List<T> flattenListOfLists_304(List<List<T>> listOfLists) {
        List<T> flat = new ArrayList<>();
        if (listOfLists == null) return flat;
        for (List<T> list : listOfLists) {
            if (list != null) flat.addAll(list);
        }
        return flat;
    }

    // 305. Find the maximum value in an int array
    public static int maxInArray_305(int[] arr) {
        if (arr == null || arr.length == 0) throw new IllegalArgumentException("Empty array");
        int max = arr[0];
        for (int val : arr) {
            if (val > max) max = val;
        }
        return max;
    }

    // 306. Find the minimum value in an int array
    public static int minInArray_306(int[] arr) {
        if (arr == null || arr.length == 0) throw new IllegalArgumentException("Empty array");
        int min = arr[0];
        for (int val : arr) {
            if (val < min) min = val;
        }
        return min;
    }

    // 307. Remove duplicates from a list, preserving order
    public static <T> List<T> removeDuplicates_307(List<T> list) {
        if (list == null) return new ArrayList<>();
        Set<T> seen = new LinkedHashSet<>(list);
        return new ArrayList<>(seen);
    }

    // 308. Repeat a string n times
    public static String repeatString_308(String s, int n) {
        if (s == null) return null;
        if (n <= 0) return "";
        StringBuilder sb = new StringBuilder(s.length() * n);
        for (int i = 0; i < n; i++) {
            sb.append(s);
        }
        return sb.toString();
    }

    // 309. Convert a list of integers to an int array
    public static int[] listToIntArray_309(List<Integer> list) {
        if (list == null) return new int[0];
        int[] arr = new int[list.size()];
        for (int i = 0; i < list.size(); i++) {
            arr[i] = list.get(i);
        }
        return arr;
    }

    // 310. Check if a string is numeric (integer)
    public static boolean isNumeric_310(String s) {
        if (s == null || s.isEmpty()) return false;
        return s.matches("-?\\d+");
    }

    // 311. Calculate the nth Fibonacci number (iterative)
    public static long fibonacci_311(int n) {
        if (n < 0) throw new IllegalArgumentException("Negative");
        if (n == 0) return 0;
        if (n == 1) return 1;
        long a = 0, b = 1;
        for (int i = 2; i <= n; i++) {
            long temp = a + b;
            a = b;
            b = temp;
        }
        return b;
    }

    // 312. Convert a character to its ASCII integer value
    public static int charToAscii_312(char c) {
        return (int) c;
    }

    // 313. Convert an ASCII integer value to a character
    public static char asciiToChar_313(int ascii) {
        return (char) ascii;
    }

    // 314. Check if a year is a leap year
    public static boolean isLeapYear_314(int year) {
        if (year % 400 == 0) return true;
        if (year % 100 == 0) return false;
        return year % 4 == 0;
    }

    // 315. Convert string to int safely (returns default if fails)
    public static int parseIntOrDefault_315(String s, int defaultValue) {
        if (s == null) return defaultValue;
        try {
            return Integer.parseInt(s);
        } catch (NumberFormatException e) {
            return defaultValue;
        }
    }

    // 316. Check if a string contains only digits
    public static boolean isDigitsOnly_316(String s) {
        if (s == null || s.isEmpty()) return false;
        return s.matches("\\d+");
    }

    // 317. Calculate the Euclidean distance between two points
    public static double euclideanDistance_317(double x1, double y1, double x2, double y2) {
        double dx = x1 - x2;
        double dy = y1 - y2;
        return Math.sqrt(dx * dx + dy * dy);
    }

    // 318. Count words in a string
    public static int countWords_318(String s) {
        if (s == null || s.trim().isEmpty()) return 0;
        return s.trim().split("\\s+").length;
    }

    // 319. Reverse the words in a sentence
    public static String reverseWords_319(String sentence) {
        if (sentence == null || sentence.isEmpty()) return sentence;
        String[] words = sentence.split("\\s+");
        Collections.reverse(Arrays.asList(words));
        return String.join(" ", words);
    }

    // 320. Find the longest common prefix between two strings
    public static String longestCommonPrefix_320(String s1, String s2) {
        if (s1 == null || s2 == null) return "";
        int minLen = Math.min(s1.length(), s2.length());
        int i = 0;
        while (i < minLen && s1.charAt(i) == s2.charAt(i)) {
            i++;
        }
        return s1.substring(0, i);
    }

    // 321. Remove all vowels from a string
    public static String removeVowels_321(String s) {
        if (s == null) return null;
        return s.replaceAll("[aeiouAEIOU]", "");
    }

    // 322. Check if two lists are permutations of each other
    public static <T> boolean arePermutations_322(List<T> list1, List<T> list2) {
        if (list1 == null || list2 == null) return false;
        if (list1.size() != list2.size()) return false;
        List<T> copy1 = new ArrayList<>(list1);
        List<T> copy2 = new ArrayList<>(list2);
        Collections.sort((List)copy1);
        Collections.sort((List)copy2);
        return copy1.equals(copy2);
    }

    // 323. Get the factorial of a number recursively
    public static long factorialRec_323(int n) {
        if (n < 0) throw new IllegalArgumentException("Negative");
        if (n <= 1) return 1;
        return n * factorialRec_323(n - 1);
    }

    // 324. Replace all occurrences of a substring with another string
    public static String replaceAll_324(String text, String target, String replacement) {
        if (text == null || target == null || replacement == null) return text;
        return text.replace(target, replacement);
    }

    // 325. Check if a string starts with a vowel
    public static boolean startsWithVowel_325(String s) {
        if (s == null || s.isEmpty()) return false;
        return s.toLowerCase().matches("^[aeiou].*");
    }

    // 326. Get the initials from a full name
    public static String getInitials_326(String fullName) {
        if (fullName == null || fullName.trim().isEmpty()) return "";
        String[] parts = fullName.trim().split("\\s+");
        StringBuilder sb = new StringBuilder();
        for (String p : parts) {
            if (!p.isEmpty()) sb.append(Character.toUpperCase(p.charAt(0)));
        }
        return sb.toString();
    }

    // 327. Calculate the power of a number (base^exponent)
    public static double power_327(double base, int exponent) {
        return Math.pow(base, exponent);
    }

    // 328. Check if a number is a palindrome (e.g., 121)
    public static boolean isNumberPalindrome_328(int num) {
        String s = String.valueOf(num);
        return s.equals(new StringBuilder(s).reverse().toString());
    }

    // 329. Get current timestamp in milliseconds
    public static long currentTimestamp_329() {
        return System.currentTimeMillis();
    }

    // 330. Convert a decimal number to hexadecimal string
    public static String decimalToHex_330(int num) {
        return Integer.toHexString(num);
    }

    // 331. Convert a hexadecimal string to decimal number
    public static int hexToDecimal_331(String hex) {
        if (hex == null) throw new IllegalArgumentException("Null");
        return Integer.parseInt(hex, 16);
    }

    // 332. Find all prime numbers up to n using Sieve of Eratosthenes
    public static List<Integer> primesUpTo_332(int n) {
        List<Integer> primes = new ArrayList<>();
        if (n < 2) return primes;
        boolean[] isPrime = new boolean[n + 1];
        Arrays.fill(isPrime, true);
        isPrime[0] = false;
        isPrime[1] = false;
        for (int i = 2; i * i <= n; i++) {
            if (isPrime[i]) {
                for (int j = i * i; j <= n; j += i) {
                    isPrime[j] = false;
                }
            }
        }
        for (int i = 2; i <= n; i++) {
            if (isPrime[i]) primes.add(i);
        }
        return primes;
    }

    // 333. Reverse an int array in place
    public static void reverseArray_333(int[] arr) {
        if (arr == null) return;
        int left = 0, right = arr.length - 1;
        while (left < right) {
            int temp = arr[left];
            arr[left] = arr[right];
            arr[right] = temp;
            left++;
            right--;
        }
    }

    // 334. Find the second largest number in an int array
    public static int secondLargest_334(int[] arr) {
        if (arr == null || arr.length < 2) throw new IllegalArgumentException("Array too small");
        int max = Integer.MIN_VALUE;
        int secondMax = Integer.MIN_VALUE;
        for (int val : arr) {
            if (val > max) {
                secondMax = max;
                max = val;
            } else if (val > secondMax && val != max) {
                secondMax = val;
            }
        }
        if (secondMax == Integer.MIN_VALUE) throw new IllegalArgumentException("No second largest found");
        return secondMax;
    }

    // 335. Check if two strings are anagrams
    public static boolean areAnagrams_335(String s1, String s2) {
        if (s1 == null || s2 == null) return false;
        char[] a1 = s1.replaceAll("\\s", "").toLowerCase().toCharArray();
        char[] a2 = s2.replaceAll("\\s", "").toLowerCase().toCharArray();
        Arrays.sort(a1);
        Arrays.sort(a2);
        return Arrays.equals(a1, a2);
    }

    // 336. Get the number of occurrences of a character in a string
    public static int countCharOccurrences_336(String s, char c) {
        if (s == null) return 0;
        int count = 0;
        for (char ch : s.toCharArray()) {
            if (ch == c) count++;
        }
        return count;
    }

    // 337. Generate a UUID string
    public static String generateUUID_337() {
        return UUID.randomUUID().toString();
    }

    // 338. Convert a list of strings to uppercase
    public static List<String> toUpperCaseList_338(List<String> list) {
        if (list == null) return new ArrayList<>();
        List<String> result = new ArrayList<>();
        for (String s : list) {
            result.add(s == null ? null : s.toUpperCase());
        }
        return result;
    }

    // 339. Convert list of strings to lowercase
    public static List<String> toLowerCaseList_339(List<String> list) {
        if (list == null) return new ArrayList<>();
        List<String> result = new ArrayList<>();
        for (String s : list) {
            result.add(s == null ? null : s.toLowerCase());
        }
        return result;
    }

    // 340. Get factorial digits count (number of digits in n!)
    public static int factorialDigitCount_340(int n) {
        if (n < 0) throw new IllegalArgumentException("Negative");
        if (n <= 1) return 1;
        double digits = 0;
        for (int i = 2; i <= n; i++) {
            digits += Math.log10(i);
        }
        return (int) digits + 1;
    }

    // 341. Check if string is a palindrome (ignoring case and non-alphanumeric)
    public static boolean isPalindrome_341(String s) {
        if (s == null) return false;
        String filtered = s.replaceAll("[^A-Za-z0-9]", "").toLowerCase();
        return filtered.equals(new StringBuilder(filtered).reverse().toString());
    }

    // 342. Convert a list of integers to a comma-separated string
    public static String listToCommaSeparated_342(List<Integer> list) {
        if (list == null || list.isEmpty()) return "";
        StringBuilder sb = new StringBuilder();
        for (Integer i : list) {
            sb.append(i).append(",");
        }
        sb.setLength(sb.length() - 1);
        return sb.toString();
    }

    // 343. Generate a random alphanumeric string of given length
    public static String randomAlphanumeric_343(int length) {
        if (length <= 0) return "";
        String chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        Random rnd = new Random();
        StringBuilder sb = new StringBuilder(length);
        for (int i = 0; i < length; i++) {
            sb.append(chars.charAt(rnd.nextInt(chars.length())));
        }
        return sb.toString();
    }

    // 344. Check if a string contains any whitespace
    public static boolean containsWhitespace_344(String s) {
        if (s == null) return false;
        return s.matches(".*\\s.*");
    }

    // 345. Convert a boolean to "Yes" or "No"
    public static String booleanToYesNo_345(boolean b) {
        return b ? "Yes" : "No";
    }

    // 346. Compute the sum of digits of an integer
    public static int sumOfDigits_346(int num) {
        num = Math.abs(num);
        int sum = 0;
        while (num > 0) {
            sum += num % 10;
            num /= 10;
        }
        return sum;
    }

    // 347. Check if a number is a power of two
    public static boolean isPowerOfTwo_347(int n) {
        return n > 0 && (n & (n - 1)) == 0;
    }

    // 348. Get substring between two indices safely
    public static String safeSubstring_348(String s, int start, int end) {
        if (s == null) return null;
        if (start < 0) start = 0;
        if (end > s.length()) end = s.length();
        if (start > end) return "";
        return s.substring(start, end);
    }

    // 349. Convert an array of integers to a List<Integer>
    public static List<Integer> intArrayToList_349(int[] arr) {
        List<Integer> list = new ArrayList<>();
        if (arr == null) return list;
        for (int v : arr) list.add(v);
        return list;
    }

    // 350. Compute the average of integers in a list
    public static double averageIntList_350(List<Integer> list) {
        if (list == null || list.isEmpty()) throw new IllegalArgumentException("Empty list");
        double sum = 0;
        for (Integer i : list) {
            sum += i;
        }
        return sum / list.size();
    }

     // 351. Count the number of lines in a string
    public static int countLines_351(String s) {
        if (s == null || s.isEmpty()) return 0;
        return s.split("\r\n|\r|\n").length;
    }

    // 352. Remove punctuation from a string
    public static String removePunctuation_352(String s) {
        if (s == null) return null;
        return s.replaceAll("\\p{Punct}", "");
    }

    // 353. Get current day of the week
    public static String currentDayOfWeek_353() {
        return java.time.LocalDate.now().getDayOfWeek().toString();
    }

    // 354. Check if string is a valid hex color code
    public static boolean isValidHexColor_354(String s) {
        return s != null && s.matches("^#?[0-9A-Fa-f]{6}$");
    }

    // 355. Convert boolean to 1 (true) or 0 (false)
    public static int booleanToInt_355(boolean b) {
        return b ? 1 : 0;
    }

    // 356. Get first non-repeating character in a string
    public static Character firstNonRepeatingChar_356(String s) {
        if (s == null) return null;
        Map<Character, Integer> counts = new LinkedHashMap<>();
        for (char c : s.toCharArray()) {
            counts.put(c, counts.getOrDefault(c, 0) + 1);
        }
        for (Map.Entry<Character, Integer> entry : counts.entrySet()) {
            if (entry.getValue() == 1) return entry.getKey();
        }
        return null;
    }

    // 357. Count the number of true values in a boolean array
    public static int countTrue_357(boolean[] arr) {
        if (arr == null) return 0;
        int count = 0;
        for (boolean b : arr) {
            if (b) count++;
        }
        return count;
    }

    // 358. Check if a string is in camelCase
    public static boolean isCamelCase_358(String s) {
        return s != null && s.matches("^[a-z]+([A-Z][a-z0-9]*)*$");
    }

    // 359. Count occurrences of a word in a sentence
    public static int countWordOccurrences_359(String sentence, String word) {
        if (sentence == null || word == null) return 0;
        String[] tokens = sentence.split("\\s+");
        int count = 0;
        for (String token : tokens) {
            if (token.equals(word)) count++;
        }
        return count;
    }

    // 360. Sort a list of strings ignoring case
    public static List<String> sortIgnoreCase_360(List<String> list) {
        if (list == null) return new ArrayList<>();
        list.sort(String.CASE_INSENSITIVE_ORDER);
        return list;
    }

    // 361. Remove nulls from a list
    public static <T> List<T> removeNulls_361(List<T> list) {
        if (list == null) return new ArrayList<>();
        List<T> cleaned = new ArrayList<>();
        for (T item : list) {
            if (item != null) cleaned.add(item);
        }
        return cleaned;
    }

    // 362. Find the most frequent character in a string
    public static Character mostFrequentChar_362(String s) {
        if (s == null || s.isEmpty()) return null;
        Map<Character, Integer> map = new HashMap<>();
        for (char c : s.toCharArray()) {
            map.put(c, map.getOrDefault(c, 0) + 1);
        }
        return map.entrySet().stream()
                .max(Map.Entry.comparingByValue())
                .map(Map.Entry::getKey)
                .orElse(null);
    }

    // 363. Shuffle a list
    public static <T> void shuffleList_363(List<T> list) {
        if (list != null) Collections.shuffle(list);
    }

    // 364. Pad string with zeros on the left
    public static String padLeftZeros_364(String s, int length) {
        if (s == null) return null;
        return String.format("%" + length + "s", s).replace(' ', '0');
    }

    // 365. Get all substrings of a string
    public static List<String> getAllSubstrings_365(String s) {
        List<String> substrings = new ArrayList<>();
        if (s == null) return substrings;
        int len = s.length();
        for (int i = 0; i < len; i++) {
            for (int j = i + 1; j <= len; j++) {
                substrings.add(s.substring(i, j));
            }
        }
        return substrings;
    }

    // 366. Check if string contains only alphabets
    public static boolean isAlpha_366(String s) {
        return s != null && s.matches("^[A-Za-z]+$");
    }

    // 367. Check if list is sorted ascending
    public static <T extends Comparable<T>> boolean isSortedAscending_367(List<T> list) {
        if (list == null || list.size() < 2) return true;
        for (int i = 1; i < list.size(); i++) {
            if (list.get(i).compareTo(list.get(i - 1)) < 0) return false;
        }
        return true;
    }

    // 368. Create a map from two lists (keys and values)
    public static <K, V> Map<K, V> zipToMap_368(List<K> keys, List<V> values) {
        Map<K, V> map = new HashMap<>();
        if (keys == null || values == null) return map;
        for (int i = 0; i < Math.min(keys.size(), values.size()); i++) {
            map.put(keys.get(i), values.get(i));
        }
        return map;
    }

    // 369. Remove duplicate characters from a string
    public static String removeDuplicateChars_369(String s) {
        if (s == null) return null;
        StringBuilder sb = new StringBuilder();
        Set<Character> seen = new HashSet<>();
        for (char c : s.toCharArray()) {
            if (!seen.contains(c)) {
                seen.add(c);
                sb.append(c);
            }
        }
        return sb.toString();
    }

    // 370. Truncate string to a certain length with ellipsis
    public static String truncateWithEllipsis_370(String s, int maxLength) {
        if (s == null || maxLength <= 0) return "";
        if (s.length() <= maxLength) return s;
        return s.substring(0, maxLength - 3) + "...";
    }

    // 371. Convert a string to binary representation
    public static String stringToBinary_371(String s) {
        if (s == null) return null;
        StringBuilder sb = new StringBuilder();
        for (char c : s.toCharArray()) {
            sb.append(String.format("%8s", Integer.toBinaryString(c)).replace(' ', '0')).append(" ");
        }
        return sb.toString().trim();
    }

    // 372. Convert binary string to ASCII
    public static String binaryToString_372(String binary) {
        if (binary == null || binary.isEmpty()) return "";
        String[] binaries = binary.split(" ");
        StringBuilder sb = new StringBuilder();
        for (String b : binaries) {
            sb.append((char) Integer.parseInt(b, 2));
        }
        return sb.toString();
    }

    // 373. Count uppercase letters in a string
    public static int countUppercase_373(String s) {
        if (s == null) return 0;
        int count = 0;
        for (char c : s.toCharArray()) {
            if (Character.isUpperCase(c)) count++;
        }
        return count;
    }

    // 374. Get common elements from two lists
    public static <T> List<T> getCommonElements_374(List<T> a, List<T> b) {
        if (a == null || b == null) return new ArrayList<>();
        Set<T> setA = new HashSet<>(a);
        List<T> result = new ArrayList<>();
        for (T item : b) {
            if (setA.contains(item)) result.add(item);
        }
        return result;
    }

    // 375. Normalize whitespace in a string
    public static String normalizeWhitespace_375(String s) {
        if (s == null) return null;
        return s.trim().replaceAll("\\s+", " ");
    }

    // 376. Get ordinal suffix of an integer (e.g., 1st, 2nd)
    public static String ordinalSuffix_376(int number) {
        if (number % 100 >= 11 && number % 100 <= 13) return number + "th";
        switch (number % 10) {
            case 1: return number + "st";
            case 2: return number + "nd";
            case 3: return number + "rd";
            default: return number + "th";
        }
    }

    // 377. Check if string is null or empty
    public static boolean isNullOrEmpty_377(String s) {
        return s == null || s.isEmpty();
    }

    // 378. Count characters in a string
    public static int countCharacters_378(String s) {
        return s == null ? 0 : s.length();
    }

    // 379. Convert list of booleans to string of 1s and 0s
    public static String boolListToBinaryString_379(List<Boolean> list) {
        if (list == null) return "";
        StringBuilder sb = new StringBuilder();
        for (Boolean b : list) {
            sb.append(b != null && b ? "1" : "0");
        }
        return sb.toString();
    }

    // 380. Split a list into chunks
    public static <T> List<List<T>> chunkList_380(List<T> list, int chunkSize) {
        List<List<T>> chunks = new ArrayList<>();
        if (list == null || chunkSize <= 0) return chunks;
        for (int i = 0; i < list.size(); i += chunkSize) {
            chunks.add(list.subList(i, Math.min(i + chunkSize, list.size())));
        }
        return chunks;
    }

    // 381. Compare two strings ignoring case and spaces
    public static boolean compareIgnoreCaseAndSpaces_381(String a, String b) {
        if (a == null || b == null) return false;
        return a.replaceAll("\\s+", "").equalsIgnoreCase(b.replaceAll("\\s+", ""));
    }

    // 382. Return true if all characters in a string are unique
    public static boolean allUniqueChars_382(String s) {
        if (s == null) return false;
        Set<Character> chars = new HashSet<>();
        for (char c : s.toCharArray()) {
            if (!chars.add(c)) return false;
        }
        return true;
    }

    // 383. Return max string length from list
    public static int maxStringLength_383(List<String> list) {
        if (list == null || list.isEmpty()) return 0;
        int max = 0;
        for (String s : list) {
            if (s != null && s.length() > max) max = s.length();
        }
        return max;
    }

    // 384. Find index of max value in an int array
    public static int indexOfMax_384(int[] arr) {
        if (arr == null || arr.length == 0) return -1;
        int maxIdx = 0;
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] > arr[maxIdx]) maxIdx = i;
        }
        return maxIdx;
    }

    // 385. Format integer as currency (e.g., 1000 → 1,000)
    public static String formatCurrency_385(int value) {
        return String.format("%,d", value);
    }

    // 386. Convert seconds to HH:mm:ss format
    public static String secondsToTime_386(int totalSeconds) {
        int hours = totalSeconds / 3600;
        int minutes = (totalSeconds % 3600) / 60;
        int seconds = totalSeconds % 60;
        return String.format("%02d:%02d:%02d", hours, minutes, seconds);
    }

    // 387. Convert HH:mm:ss to total seconds
    public static int timeToSeconds_387(String time) {
        if (time == null || !time.matches("\\d{2}:\\d{2}:\\d{2}")) return 0;
        String[] parts = time.split(":");
        return Integer.parseInt(parts[0]) * 3600 +
               Integer.parseInt(parts[1]) * 60 +
               Integer.parseInt(parts[2]);
    }

    // 388. Count digit characters in a string
    public static int countDigits_388(String s) {
        if (s == null) return 0;
        int count = 0;
        for (char c : s.toCharArray()) {
            if (Character.isDigit(c)) count++;
        }
        return count;
    }

    // 389. Get the difference in days between two dates
    public static long daysBetween_389(LocalDate d1, LocalDate d2) {
        return ChronoUnit.DAYS.between(d1, d2);
    }

    // 390. Convert an int to a Roman numeral
    public static String intToRoman_390(int num) {
        String[] romans = {"M","CM","D","CD","C","XC","L","XL","X","IX","V","IV","I"};
        int[] values = {1000,900,500,400,100,90,50,40,10,9,5,4,1};
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < values.length; i++) {
            while (num >= values[i]) {
                sb.append(romans[i]);
                num -= values[i];
            }
        }
        return sb.toString();
    }

    // 391. Convert a Roman numeral to int
    public static int romanToInt_391(String roman) {
        Map<Character, Integer> map = Map.of(
            'I', 1, 'V', 5, 'X', 10, 'L', 50,
            'C', 100, 'D', 500, 'M', 1000
        );
        int result = 0, prev = 0;
        for (int i = roman.length() - 1; i >= 0; i--) {
            int curr = map.getOrDefault(roman.charAt(i), 0);
            if (curr < prev) result -= curr;
            else result += curr;
            prev = curr;
        }
        return result;
    }

    // 392. Capitalize the first letter of each word
    public static String capitalizeWords_392(String s) {
        if (s == null || s.isEmpty()) return s;
        String[] words = s.toLowerCase().split("\\s+");
        StringBuilder sb = new StringBuilder();
        for (String word : words) {
            if (!word.isEmpty()) {
                sb.append(Character.toUpperCase(word.charAt(0)))
                  .append(word.substring(1)).append(" ");
            }
        }
        return sb.toString().trim();
    }

    // 393. Replace the nth occurrence of a substring
    public static String replaceNth_393(String s, String find, String replace, int n) {
        int index = -1;
        while (n-- > 0 && (index = s.indexOf(find, index + 1)) != -1) {}
        if (index != -1) {
            return s.substring(0, index) + replace + s.substring(index + find.length());
        }
        return s;
    }

    // 394. Count the number of vowels in a string
    public static int countVowels_394(String s) {
        if (s == null) return 0;
        int count = 0;
        for (char c : s.toLowerCase().toCharArray()) {
            if ("aeiou".indexOf(c) != -1) count++;
        }
        return count;
    }

    // 395. Reverse the words in a sentence
    public static String reverseWords_395(String sentence) {
        if (sentence == null) return null;
        String[] words = sentence.trim().split("\\s+");
        Collections.reverse(Arrays.asList(words));
        return String.join(" ", words);
    }

    // 396. Format duration in milliseconds to readable time
    public static String formatDuration_396(long ms) {
        long seconds = ms / 1000;
        long minutes = seconds / 60;
        long hours = minutes / 60;
        return String.format("%02dh %02dm %02ds", hours, minutes % 60, seconds % 60);
    }

    // 397. Get the middle character(s) of a string
    public static String middleChars_397(String s) {
        if (s == null || s.isEmpty()) return "";
        int mid = s.length() / 2;
        return (s.length() % 2 == 0) ? s.substring(mid - 1, mid + 1) : String.valueOf(s.charAt(mid));
    }

    // 398. Get the current Unix timestamp (seconds)
    public static long getUnixTimestamp_398() {
        return Instant.now().getEpochSecond();
    }

    // 399. Get the number of days in a given month/year
    public static int daysInMonth_399(int year, int month) {
        return YearMonth.of(year, month).lengthOfMonth();
    }

    // 400. Check if a number is even
    public static boolean isEven_400(int number) {
        return number % 2 == 0;
    }

    // 401. Check if a number is odd
    public static boolean isOdd_401(int number) {
        return number % 2 != 0;
    }

    // 402. Check if a number is a palindrome
    public static boolean isPalindromeNumber_402(int number) {
        String str = Integer.toString(number);
        return new StringBuilder(str).reverse().toString().equals(str);
    }

    // 403. Check if a string is a palindrome (case-insensitive)
    public static boolean isPalindromeString_403(String s) {
        if (s == null) return false;
        String clean = s.replaceAll("[^a-zA-Z0-9]", "").toLowerCase();
        return new StringBuilder(clean).reverse().toString().equals(clean);
    }

    // 404. Get all prime numbers up to a limit
    public static List<Integer> getPrimesUpTo_404(int limit) {
        List<Integer> primes = new ArrayList<>();
        for (int i = 2; i <= limit; i++) {
            if (isPrime_405(i)) primes.add(i);
        }
        return primes;
    }

    // 405. Check if a number is prime
    public static boolean isPrime_405(int n) {
        if (n < 2) return false;
        if (n == 2) return true;
        if (n % 2 == 0) return false;
        for (int i = 3; i <= Math.sqrt(n); i += 2) {
            if (n % i == 0) return false;
        }
        return true;
    }

    // 406. Get the factorial of a number
    public static long factorial_406(int n) {
        if (n < 0) throw new IllegalArgumentException("Negative number");
        long result = 1;
        for (int i = 2; i <= n; i++) result *= i;
        return result;
    }

    // 407. Generate a random UUID
    public static String generateUUID_407() {
        return UUID.randomUUID().toString();
    }

    // 408. Get the first non-repeated character
    public static Character firstNonRepeatedChar_408(String s) {
        if (s == null) return null;
        Map<Character, Integer> map = new LinkedHashMap<>();
        for (char c : s.toCharArray()) map.put(c, map.getOrDefault(c, 0) + 1);
        for (Map.Entry<Character, Integer> entry : map.entrySet()) {
            if (entry.getValue() == 1) return entry.getKey();
        }
        return null;
    }

    // 409. Count occurrences of a character
    public static int countCharOccurrences_409(String s, char c) {
        if (s == null) return 0;
        int count = 0;
        for (char ch : s.toCharArray()) {
            if (ch == c) count++;
        }
        return count;
    }

    // 410. Find the longest word in a sentence
    public static String longestWord_410(String sentence) {
        if (sentence == null || sentence.isEmpty()) return "";
        String[] words = sentence.split("\\s+");
        return Arrays.stream(words).max(Comparator.comparingInt(String::length)).orElse("");
    }

    // 411. Round a double to n decimal places
    public static double round_411(double value, int places) {
        if (places < 0) throw new IllegalArgumentException();
        BigDecimal bd = BigDecimal.valueOf(value);
        bd = bd.setScale(places, RoundingMode.HALF_UP);
        return bd.doubleValue();
    }

    // 412. Remove non-ASCII characters
    public static String removeNonAscii_412(String s) {
        if (s == null) return null;
        return s.replaceAll("[^\\x00-\\x7F]", "");
    }

    // 413. Count words in a string
    public static int countWords_413(String s) {
        if (s == null || s.trim().isEmpty()) return 0;
        return s.trim().split("\\s+").length;
    }

    // 414. Check if a year is a leap year
    public static boolean isLeapYear_414(int year) {
        return Year.isLeap(year);
    }

    // 415. Get current date in custom format
    public static String getCurrentDateFormatted_415(String pattern) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);
        return LocalDate.now().format(formatter);
    }

    // 416. Generate random password of given length
    public static String generatePassword_416(int length) {
        String chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789!@#$%^&*()";
        Random rand = new Random();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < length; i++) {
            sb.append(chars.charAt(rand.nextInt(chars.length())));
        }
        return sb.toString();
    }

    // 417. Escape HTML tags
    public static String escapeHtml_417(String s) {
        if (s == null) return null;
        return s.replace("&", "&amp;")
                .replace("<", "&lt;")
                .replace(">", "&gt;");
    }

    // 418. Unescape HTML tags
    public static String unescapeHtml_418(String s) {
        if (s == null) return null;
        return s.replace("&lt;", "<")
                .replace("&gt;", ">")
                .replace("&amp;", "&");
    }

    // 419. Get number of lines in a string
    public static int countLines_419(String s) {
        if (s == null || s.isEmpty()) return 0;
        return s.split("\r\n|\r|\n").length;
    }

    // 420. Repeat a string n times
    public static String repeat_420(String s, int times) {
        return s == null ? null : s.repeat(times);
    }

    // 421. Find GCD of two numbers
    public static int gcd_421(int a, int b) {
        while (b != 0) {
            int tmp = b;
            b = a % b;
            a = tmp;
        }
        return a;
    }

    // 422. Find LCM of two numbers
    public static int lcm_422(int a, int b) {
        return a * (b / gcd_421(a, b));
    }

    // 423. Count uppercase letters
    public static int countUppercase_423(String s) {
        if (s == null) return 0;
        int count = 0;
        for (char c : s.toCharArray()) {
            if (Character.isUpperCase(c)) count++;
        }
        return count;
    }

    // 424. Count lowercase letters
    public static int countLowercase_424(String s) {
        if (s == null) return 0;
        int count = 0;
        for (char c : s.toCharArray()) {
            if (Character.isLowerCase(c)) count++;
        }
        return count;
    }

    // 425. Get first N characters
    public static String firstN_425(String s, int n) {
        if (s == null || n < 0) return "";
        return s.length() <= n ? s : s.substring(0, n);
    }

    // 426. Get last N characters
    public static String lastN_426(String s, int n) {
        if (s == null || n < 0) return "";
        return s.length() <= n ? s : s.substring(s.length() - n);
    }

    // 427. Count punctuation characters
    public static int countPunctuation_427(String s) {
        if (s == null) return 0;
        return (int) s.chars().filter(ch -> ",.;:!?".indexOf(ch) != -1).count();
    }

    // 428. Get weekday name from date
    public static String getWeekdayName_428(LocalDate date) {
        return date.getDayOfWeek().getDisplayName(TextStyle.FULL, Locale.ENGLISH);
    }

    // 429. Convert string to boolean (true, yes, 1)
    public static boolean stringToBoolean_429(String s) {
        if (s == null) return false;
        return s.equalsIgnoreCase("true") ||
               s.equalsIgnoreCase("yes") ||
               s.equals("1");
    }

    // 430. Safe parse int with default
    public static int parseIntOrDefault_430(String s, int def) {
        try {
            return Integer.parseInt(s);
        } catch (Exception e) {
            return def;
        }
    }

    // 431. Safe parse double with default
    public static double parseDoubleOrDefault_431(String s, double def) {
        try {
            return Double.parseDouble(s);
        } catch (Exception e) {
            return def;
        }
    }

    // 432. Clamp integer within range
    public static int clamp_432(int value, int min, int max) {
        return Math.max(min, Math.min(max, value));
    }

    // 433. Shuffle characters in a string
    public static String shuffleString_433(String s) {
        if (s == null) return null;
        List<Character> chars = s.chars().mapToObj(c -> (char)c).collect(Collectors.toList());
        Collections.shuffle(chars);
        StringBuilder sb = new StringBuilder();
        chars.forEach(sb::append);
        return sb.toString();
    }

    // 434. Pad string left
    public static String padLeft_434(String s, int width, char padChar) {
        return String.format("%" + width + "s", s).replace(' ', padChar);
    }

    // 435. Pad string right
    public static String padRight_435(String s, int width, char padChar) {
        return String.format("%-" + width + "s", s).replace(' ', padChar);
    }

    // 436. Check if string contains only digits
    public static boolean isNumeric_436(String s) {
        return s != null && s.matches("\\d+");
    }

    // 437. Convert boolean to "Yes"/"No"
    public static String boolToYesNo_437(boolean b) {
        return b ? "Yes" : "No";
    }

    // 438. Get ordinal suffix (1st, 2nd, 3rd, etc.)
    public static String getOrdinalSuffix_438(int number) {
        if (number % 100 >= 11 && number % 100 <= 13) return number + "th";
        switch (number % 10) {
            case 1: return number + "st";
            case 2: return number + "nd";
            case 3: return number + "rd";
            default: return number + "th";
        }
    }

    // 439. Convert array to comma-separated string
    public static String arrayToCSV_439(String[] array) {
        return String.join(",", array);
    }

    // 440. Convert comma-separated string to array
    public static String[] csvToArray_440(String csv) {
        return csv.split("\\s*,\\s*");
    }

    // 441. Format number with commas
    public static String formatNumber_441(int number) {
        return String.format("%,d", number);
    }

    // 442. Check if two strings are anagrams
    public static boolean areAnagrams_442(String s1, String s2) {
        if (s1 == null || s2 == null) return false;
        char[] a1 = s1.replaceAll("\\s", "").toLowerCase().toCharArray();
        char[] a2 = s2.replaceAll("\\s", "").toLowerCase().toCharArray();
        Arrays.sort(a1); Arrays.sort(a2);
        return Arrays.equals(a1, a2);
    }

    // 443. Generate random alphanumeric string
    public static String randomAlphaNumeric_443(int length) {
        String chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        Random rand = new Random();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < length; i++) {
            sb.append(chars.charAt(rand.nextInt(chars.length())));
        }
        return sb.toString();
    }

    // 444. Convert byte array to hex string
    public static String bytesToHex_444(byte[] bytes) {
        StringBuilder sb = new StringBuilder();
        for (byte b : bytes) sb.append(String.format("%02x", b));
        return sb.toString();
    }

    // 445. Convert hex string to byte array
    public static byte[] hexToBytes_445(String hex) {
        int len = hex.length();
        byte[] result = new byte[len / 2];
        for (int i = 0; i < len; i += 2) {
            result[i / 2] = (byte) ((Character.digit(hex.charAt(i), 16) << 4)
                                    + Character.digit(hex.charAt(i+1), 16));
        }
        return result;
    }

    // 446. Count alphanumeric characters
    public static int countAlphaNumeric_446(String s) {
        if (s == null) return 0;
        return (int) s.chars().filter(Character::isLetterOrDigit).count();
    }

    // 447. Convert double to percentage string
    public static String toPercentage_447(double value, int decimals) {
        return String.format("%." + decimals + "f%%", value * 100);
    }

    // 448. Parse percentage string to double
    public static double parsePercentage_448(String s) {
        return Double.parseDouble(s.replace("%", "").trim()) / 100.0;
    }

    // 449. Remove duplicate characters from a string
    public static String removeDuplicates_449(String s) {
        if (s == null) return null;
        return s.chars().distinct().mapToObj(c -> String.valueOf((char) c)).collect(Collectors.joining());
    }

    // 450. Shuffle an integer array
    public static void shuffleArray_450(int[] array) {
        Random rand = new Random();
        for (int i = array.length - 1; i > 0; i--) {
            int j = rand.nextInt(i + 1);
            int temp = array[i]; array[i] = array[j]; array[j] = temp;
        }
    }

    // 451. Get the median of a list of integers
    public static double median_451(List<Integer> list) {
        if (list == null || list.isEmpty()) return 0;
        List<Integer> sorted = new ArrayList<>(list);
        Collections.sort(sorted);
        int middle = sorted.size() / 2;
        if (sorted.size() % 2 == 0) {
            return (sorted.get(middle - 1) + sorted.get(middle)) / 2.0;
        } else {
            return sorted.get(middle);
        }
    }

    // 452. Count vowels in a string
    public static int countVowels_452(String s) {
        if (s == null) return 0;
        return (int) s.toLowerCase().chars().filter("aeiou"::indexOf).count();
    }

    // 453. Count consonants in a string
    public static int countConsonants_453(String s) {
        if (s == null) return 0;
        return (int) s.toLowerCase().chars()
                .filter(c -> Character.isLetter(c) && "aeiou".indexOf(c) == -1).count();
    }

    // 454. Check if string is all uppercase
    public static boolean isAllUppercase_454(String s) {
        return s != null && s.equals(s.toUpperCase());
    }

    // 455. Check if string is all lowercase
    public static boolean isAllLowercase_455(String s) {
        return s != null && s.equals(s.toLowerCase());
    }

    // 456. Capitalize every word in a string
    public static String capitalizeWords_456(String s) {
        if (s == null || s.isEmpty()) return s;
        return Arrays.stream(s.split("\\s+"))
                .map(w -> w.isEmpty() ? w :
                        Character.toUpperCase(w.charAt(0)) + w.substring(1).toLowerCase())
                .collect(Collectors.joining(" "));
    }

    // 457. Get unique characters from string
    public static Set<Character> uniqueChars_457(String s) {
        if (s == null) return Set.of();
        return s.chars().mapToObj(c -> (char) c).collect(Collectors.toSet());
    }

    // 458. Reverse word order in a sentence
    public static String reverseWords_458(String sentence) {
        if (sentence == null || sentence.isEmpty()) return sentence;
        String[] words = sentence.trim().split("\\s+");
        Collections.reverse(Arrays.asList(words));
        return String.join(" ", words);
    }

    // 459. Remove excess whitespace
    public static String normalizeWhitespace_459(String s) {
        return s == null ? null : s.trim().replaceAll("\\s+", " ");
    }

    // 460. Get current timestamp in milliseconds
    public static long currentTimestampMillis_460() {
        return System.currentTimeMillis();
    }

    // 461. Convert int to binary string
    public static String intToBinary_461(int n) {
        return Integer.toBinaryString(n);
    }

    // 462. Convert binary string to int
    public static int binaryToInt_462(String binary) {
        return Integer.parseInt(binary, 2);
    }

    // 463. Convert int to hex string
    public static String intToHex_463(int n) {
        return Integer.toHexString(n);
    }

    // 464. Convert hex string to int
    public static int hexToInt_464(String hex) {
        return Integer.parseInt(hex, 16);
    }

    // 465. Convert seconds to HH:mm:ss
    public static String secondsToTime_465(int seconds) {
        int h = seconds / 3600;
        int m = (seconds % 3600) / 60;
        int s = seconds % 60;
        return String.format("%02d:%02d:%02d", h, m, s);
    }

    // 466. Convert HH:mm:ss to seconds
    public static int timeToSeconds_466(String time) {
        String[] parts = time.split(":");
        int h = Integer.parseInt(parts[0]);
        int m = Integer.parseInt(parts[1]);
        int s = Integer.parseInt(parts[2]);
        return h * 3600 + m * 60 + s;
    }

    // 467. Get file extension from filename
    public static String getFileExtension_467(String filename) {
        if (filename == null) return "";
        int dot = filename.lastIndexOf('.');
        return (dot == -1) ? "" : filename.substring(dot + 1);
    }

    // 468. Remove file extension from filename
    public static String removeFileExtension_468(String filename) {
        if (filename == null) return "";
        int dot = filename.lastIndexOf('.');
        return (dot == -1) ? filename : filename.substring(0, dot);
    }

    // 469. Truncate string with ellipsis
    public static String truncateWithEllipsis_469(String s, int maxLength) {
        if (s == null || maxLength < 3 || s.length() <= maxLength) return s;
        return s.substring(0, maxLength - 3) + "...";
    }

    // 470. Check if email is valid
    public static boolean isValidEmail_470(String email) {
        if (email == null) return false;
        return email.matches("^[\\w._%+-]+@[\\w.-]+\\.[a-zA-Z]{2,6}$");
    }

    // 471. Remove HTML tags from string
    public static String stripHtmlTags_471(String s) {
        if (s == null) return null;
        return s.replaceAll("<[^>]*>", "");
    }

    // 472. Get number of days between two dates
    public static long daysBetween_472(LocalDate start, LocalDate end) {
        return ChronoUnit.DAYS.between(start, end);
    }

    // 473. Get difference in minutes between two LocalTime values
    public static long minutesBetween_473(LocalTime t1, LocalTime t2) {
        return ChronoUnit.MINUTES.between(t1, t2);
    }

    // 474. Check if a string is a valid Java identifier
    public static boolean isJavaIdentifier_474(String s) {
        if (s == null || s.isEmpty() || !Character.isJavaIdentifierStart(s.charAt(0))) return false;
        for (char c : s.toCharArray()) {
            if (!Character.isJavaIdentifierPart(c)) return false;
        }
        return true;
    }

    // 475. Convert camelCase to snake_case
    public static String camelToSnake_475(String s) {
        if (s == null) return null;
        return s.replaceAll("([a-z])([A-Z])", "$1_$2").toLowerCase();
    }

    // 476. Convert snake_case to camelCase
    public static String snakeToCamel_476(String s) {
        if (s == null) return null;
        String[] parts = s.split("_");
        return parts[0] + Arrays.stream(parts, 1, parts.length)
                .map(w -> Character.toUpperCase(w.charAt(0)) + w.substring(1))
                .collect(Collectors.joining());
    }

    // 477. Convert list to set
    public static <T> Set<T> listToSet_477(List<T> list) {
        return new HashSet<>(list);
    }

    // 478. Convert set to list
    public static <T> List<T> setToList_478(Set<T> set) {
        return new ArrayList<>(set);
    }

    // 479. Check if string is a valid IPv4
    public static boolean isValidIPv4_479(String ip) {
        if (ip == null) return false;
        return ip.matches("^((25[0-5]|2[0-4]\\d|[01]?\\d\\d?)\\.){3}(25[0-5]|2[0-4]\\d|[01]?\\d\\d?)$");
    }

    // 480. Check if list is sorted ascending
    public static <T extends Comparable<T>> boolean isSorted_480(List<T> list) {
        for (int i = 1; i < list.size(); i++) {
            if (list.get(i).compareTo(list.get(i - 1)) < 0) return false;
        }
        return true;
    }

    // 481. Count digits in an integer
    public static int countDigits_481(int number) {
        return Integer.toString(Math.abs(number)).length();
    }

    // 482. Convert LocalDateTime to ISO string
    public static String toIsoString_482(LocalDateTime dt) {
        return dt.format(DateTimeFormatter.ISO_DATE_TIME);
    }

    // 483. Parse ISO string to LocalDateTime
    public static LocalDateTime parseIsoString_483(String iso) {
        return LocalDateTime.parse(iso, DateTimeFormatter.ISO_DATE_TIME);
    }

    // 484. Swap two integers using an array
    public static void swapInts_484(int[] a, int i, int j) {
        int tmp = a[i]; a[i] = a[j]; a[j] = tmp;
    }

    // 485. Get average from list
    public static double average_485(List<Integer> list) {
        if (list == null || list.isEmpty()) return 0;
        return list.stream().mapToInt(Integer::intValue).average().orElse(0);
    }

    // 486. Check if a string is a pangram
    public static boolean isPangram_486(String s) {
        return s != null && s.toLowerCase().chars().filter(Character::isLetter)
                .map(c -> c - 'a').distinct().count() == 26;
    }

    // 487. Remove trailing slash from URL
    public static String removeTrailingSlash_487(String url) {
        if (url == null) return null;
        return url.endsWith("/") ? url.substring(0, url.length() - 1) : url;
    }

    // 488. Get domain from email
    public static String getEmailDomain_488(String email) {
        if (email == null) return "";
        int at = email.lastIndexOf('@');
        return at != -1 ? email.substring(at + 1) : "";
    }

    // 489. Get initials from full name
    public static String getInitials_489(String name) {
        if (name == null || name.isEmpty()) return "";
        return Arrays.stream(name.trim().split("\\s+"))
                .filter(w -> !w.isEmpty())
                .map(w -> w.substring(0, 1).toUpperCase())
                .collect(Collectors.joining());
    }

    // 490. Convert milliseconds to readable time (Xh Ym Zs)
    public static String millisToReadableTime_490(long millis) {
        long sec = millis / 1000;
        long min = sec / 60;
        long hr = min / 60;
        sec %= 60;
        min %= 60;
        return String.format("%dh %dm %ds", hr, min, sec);
    }

    // 491. Get current Unix timestamp
    public static long unixTimestamp_491() {
        return Instant.now().getEpochSecond();
    }

    // 492. Parse boolean with default
    public static boolean parseBooleanOrDefault_492(String s, boolean def) {
        if (s == null) return def;
        return s.equalsIgnoreCase("true") || s.equalsIgnoreCase("yes") || s.equals("1");
    }

    // 493. Encode string to Base64
    public static String base64Encode_493(String s) {
        return Base64.getEncoder().encodeToString(s.getBytes(StandardCharsets.UTF_8));
    }

    // 494. Decode Base64 string
    public static String base64Decode_494(String base64) {
        return new String(Base64.getDecoder().decode(base64), StandardCharsets.UTF_8);
    }

    // 495. Check if string is blank
    public static boolean isBlank_495(String s) {
        return s == null || s.trim().isEmpty();
    }

    // 496. Generate random integer in range
    public static int randomIntInRange_496(int min, int max) {
        return new Random().nextInt(max - min + 1) + min;
    }

    // 497. Capitalize first character only
    public static String capitalizeFirst_497(String s) {
        if (s == null || s.isEmpty()) return s;
        return Character.toUpperCase(s.charAt(0)) + s.substring(1);
    }

    // 498. Decapitalize first character only
    public static String decapitalizeFirst_498(String s) {
        if (s == null || s.isEmpty()) return s;
        return Character.toLowerCase(s.charAt(0)) + s.substring(1);
    }

    // 499. Remove all digits from string
    public static String removeDigits_499(String s) {
        return s == null ? null : s.replaceAll("\\d", "");
    }

    // 500. Remove all letters from string
    public static String removeLetters_500(String s) {
        return s == null ? null : s.replaceAll("[a-zA-Z]", "");
    }
}
