class Solution {
    public int myAtoi(String s) {
        
        // Handle null or empty string cases immediately
        if (s == null || s.length() == 0) {
            return 0;
        }

        int i = 0;
        int n = s.length();

        // 1. Discard leading whitespaces
        while (i < n && s.charAt(i) == ' ') {
            i++;
        }

        // If the string contains only spaces, return 0
        if (i == n) {
            return 0;
        }

        // 2. Check for optional polarity sign
        int sign = 1;
        if (s.charAt(i) == '+' || s.charAt(i) == '-') {
            sign = (s.charAt(i) == '-') ? -1 : 1;
            i++;
        }

        // 3. Process numerical characters
        int res = 0;
        while (i < n && Character.isDigit(s.charAt(i))) {
            int digit = s.charAt(i) - '0';

            // 4. Preemptive overflow validation
            // Check if multiplying res by 10 will overshoot Integer.MAX_VALUE
            if (res > Integer.MAX_VALUE / 10 || (res == Integer.MAX_VALUE / 10 && digit > Integer.MAX_VALUE % 10)) {
                return (sign == 1) ? Integer.MAX_VALUE : Integer.MIN_VALUE;
            }

            res = res * 10 + digit;
            i++;
        }

        return sign * res;
    }
}
