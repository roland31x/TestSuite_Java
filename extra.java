import java.util.*;

public class Extra {

    public static boolean isPrime(int num) {
        if(num < 2) return false;
        for(int i = 2; i <= Math.sqrt(num); i++) {
            if(num % i == 0) return false;
        }
        return true;
    }

    public static int gcd(int a, int b) {
        while(b != 0) {
            int t = b;
            b = a % b;
            a = t;
        }
        return a;
    }

    public static int lcm(int a, int b) {
        return a * (b / gcd(a,b));
    }

    public static Map<String, Object> flattenMap(Map<String, Object> map) {
        Map<String, Object> result = new HashMap<>();
        flattenMapHelper("", map, result);
        return result;
    }

    private static void flattenMapHelper(String prefix, Map<String, Object> map, Map<String, Object> result) {
        for (String key : map.keySet()) {
            Object val = map.get(key);
            String newKey = prefix.isEmpty() ? key : prefix + "." + key;
            if (val instanceof Map) {
                flattenMapHelper(newKey, (Map<String,Object>) val, result);
            } else {
                result.put(newKey, val);
            }
        }
    }

    public static <T> T mostCommon(List<T> list) {
        Map<T, Integer> freq = new HashMap<>();
        T maxKey = null;
        int maxCount = 0;
        for(T item : list) {
            freq.put(item, freq.getOrDefault(item, 0) + 1);
            if(freq.get(item) > maxCount) {
                maxCount = freq.get(item);
                maxKey = item;
            }
        }
        return maxKey;
    }

    public static <T> List<List<T>> transpose(List<List<T>> matrix) {
        List<List<T>> result = new ArrayList<>();
        if(matrix.isEmpty()) return result;
        int rows = matrix.size();
        int cols = matrix.get(0).size();
        for(int i=0; i<cols; i++) {
            List<T> col = new ArrayList<>();
            for(int j=0; j<rows; j++) {
                col.add(matrix.get(j).get(i));
            }
            result.add(col);
        }
        return result;
    }

    public static String camelToSnake(String s) {
        return s.replaceAll("([A-Z])", "_$1").toLowerCase();
    }

    public static String swapCase(String s) {
        StringBuilder sb = new StringBuilder();
        for(char c : s.toCharArray()) {
            if(Character.isUpperCase(c)) sb.append(Character.toLowerCase(c));
            else sb.append(Character.toUpperCase(c));
        }
        return sb.toString();
    }

    public static int nestedSum(List<?> list) {
        int sum = 0;
        for(Object o : list) {
            if(o instanceof List) sum += nestedSum((List<?>) o);
            else if(o instanceof Number) sum += ((Number) o).intValue();
        }
        return sum;
    }

    public static int digitSum(int num) {
        num = Math.abs(num);
        int sum = 0;
        while(num > 0) {
            sum += num % 10;
            num /= 10;
        }
        return sum;
    }
}