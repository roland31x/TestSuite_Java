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
}
