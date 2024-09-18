import java.util.*;
import java.util.stream.Collectors;

public class Extra {

    public static String randomString(int length) {
        String chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        StringBuilder sb = new StringBuilder();
        Random rand = new Random();
        for(int i = 0; i < length; i++) {
            sb.append(chars.charAt(rand.nextInt(chars.length())));
        }
        return sb.toString();
    }

    public static long factorial(int n) {
        if(n == 0) return 1;
        return n * factorial(n - 1);
    }

    public static boolean isPalindrome(String s) {
        String str = s.toLowerCase().replaceAll("\\s+", "");
        return new StringBuilder(str).reverse().toString().equals(str);
    }

    public static <T> List<T> uniqueElements(List<T> list) {
        return new ArrayList<>(new HashSet<>(list));
    }

    public static <T> List<T> flattenList(List<List<T>> nestedList) {
        return nestedList.stream().flatMap(Collection::stream).collect(Collectors.toList());
    }

    public static long fibonacci(int n) {
        long a = 0, b = 1;
        for(int i = 0; i < n; i++) {
            long temp = a;
            a = b;
            b = temp + b;
        }
        return a;
    }

    public static long countVowels(String s) {
        long count = 0;
        for(char c : s.toLowerCase().toCharArray()) {
            if("aeiou".indexOf(c) >= 0) count++;
        }
        return count;
    }

    public static Map<String, String> mergeMaps(Map<String, String>... maps) {
        Map<String, String> result = new HashMap<>();
        for(Map<String, String> map : maps) {
            result.putAll(map);
        }
        return result;
    }

    public static String reverseWords(String sentence) {
        return Arrays.stream(sentence.split(" "))
                     .map(w -> new StringBuilder(w).reverse().toString())
                     .collect(Collectors.joining(" "));
    }

    public static <T> List<List<T>> chunkList(List<T> list, int chunkSize) {
        List<List<T>> chunks = new ArrayList<>();
        for(int i = 0; i < list.size(); i += chunkSize) {
            chunks.add(list.subList(i, Math.min(i + chunkSize, list.size())));
        }
        return chunks;
    }
}