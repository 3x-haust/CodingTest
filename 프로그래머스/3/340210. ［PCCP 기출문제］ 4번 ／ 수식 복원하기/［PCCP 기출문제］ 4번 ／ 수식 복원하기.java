import java.util.*;

class Solution {
    public String[] solution(String[] expressions) {
        List<String> result = new ArrayList<>();
        
        Set<Integer> possibleBases = new HashSet<>();
        for (int base = 2; base <= 9; base++) {
            possibleBases.add(base);
        }
        
        for (String expr : expressions) {
            if (!expr.contains("X")) {
                String[] parts = expr.split(" ");
                int a = Integer.parseInt(parts[0]);
                int b = Integer.parseInt(parts[2]);
                int c = Integer.parseInt(parts[4]);
                String op = parts[1];
                
                Set<Integer> validBases = new HashSet<>();
                for (int base : possibleBases) {
                    if (isValidInBase(a, base) && isValidInBase(b, base) && isValidInBase(c, base)) {
                        int decA = toDecimal(a, base);
                        int decB = toDecimal(b, base);
                        int decC = toDecimal(c, base);
                        
                        if ((op.equals("+") && decA + decB == decC) ||
                            (op.equals("-") && decA - decB == decC)) {
                            validBases.add(base);
                        }
                    }
                }
                possibleBases = validBases;
            }
        }
        
        for (String expr : expressions) {
            String[] parts = expr.split(" ");
            int a = Integer.parseInt(parts[0]);
            int b = Integer.parseInt(parts[2]);
            String op = parts[1];
            
            Set<Integer> validBasesForExpr = new HashSet<>();
            for (int base : possibleBases) {
                if (isValidInBase(a, base) && isValidInBase(b, base)) {
                    validBasesForExpr.add(base);
                }
            }
            possibleBases = validBasesForExpr;
        }
        
        for (String expr : expressions) {
            if (expr.contains("X")) {
                String[] parts = expr.split(" ");
                int a = Integer.parseInt(parts[0]);
                int b = Integer.parseInt(parts[2]);
                String op = parts[1];
                
                Set<String> possibleResults = new HashSet<>();
                
                for (int base : possibleBases) {
                    if (isValidInBase(a, base) && isValidInBase(b, base)) {
                        int decA = toDecimal(a, base);
                        int decB = toDecimal(b, base);
                        int decResult;
                        
                        if (op.equals("+")) {
                            decResult = decA + decB;
                        } else {
                            decResult = decA - decB;
                        }
                        
                        if (decResult >= 0) {
                            String baseResult = fromDecimal(decResult, base);
                            possibleResults.add(baseResult);
                        }
                    }
                }
                
                if (possibleResults.size() == 1) {
                    result.add(parts[0] + " " + parts[1] + " " + parts[2] + " = " + possibleResults.iterator().next());
                } else {
                    result.add(parts[0] + " " + parts[1] + " " + parts[2] + " = ?");
                }
            }
        }
        
        return result.toArray(new String[0]);
    }
    
    private boolean isValidInBase(int num, int base) {
        if (num == 0) return true;
        while (num > 0) {
            if (num % 10 >= base) return false;
            num /= 10;
        }
        return true;
    }
    
    private int toDecimal(int num, int base) {
        int result = 0;
        int power = 0;
        
        while (num > 0) {
            result += (num % 10) * Math.pow(base, power);
            num /= 10;
            power++;
        }
        
        return result;
    }
    
    private String fromDecimal(int decimal, int base) {
        if (decimal == 0) return "0";
        
        StringBuilder result = new StringBuilder();
        while (decimal > 0) {
            result.append(decimal % base);
            decimal /= base;
        }
        
        return result.reverse().toString();
    }
}