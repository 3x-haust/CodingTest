import java.util.*;

class Solution {
    private static List<String> result = new ArrayList<>();
    private static final String EMPTY = "EMPTY";
    private static int[] parents = new int[2501];
    private static String[] table = new String[2501];

    public String[] solution(String[] commands) {
        for (int i = 1; i < parents.length; i++) parents[i] = i;

        for (String command : commands) {
            String[] splitCommand = command.split(" ");
            switch (splitCommand[0]) {
                case "UPDATE" -> doUpdate(splitCommand);
                case "MERGE" -> doMerge(splitCommand);
                case "UNMERGE" -> doUnmerge(splitCommand);
                case "PRINT" -> doPrint(splitCommand);
            }
        }
        
        return result.toArray(new String[0]);
    }

    private void doUpdate(String[] commands) {
        if (commands.length == 4) updateIndexValue(commands);
        if (commands.length == 3) updateValue(commands);
    }

    private void updateIndexValue(String[] commands) {
        int r = Integer.parseInt(commands[1]);
        int c = Integer.parseInt(commands[2]);
        String value = commands[3];

        int parent = find(getIndex(r, c));
        table[parent] = value;
    }

    private int getIndex(int r, int c) {
        return 50 * (r - 1) + c;
    }

    private void updateValue(String[] commands) {
        String value1 = commands[1];
        String value2 = commands[2];
        for (int i = 1; i < parents.length; i++) {
            if (value1.equals(table[i])) table[i] = value2;
        }
    }

    private void doMerge(String[] commands) {
        int r1 = Integer.parseInt(commands[1]);
        int c1 = Integer.parseInt(commands[2]);
        int r2 = Integer.parseInt(commands[3]);
        int c2 = Integer.parseInt(commands[4]);

        union(getIndex(r1, c1), getIndex(r2, c2));
    }

    private int find(int x) {
        if (parents[x] != x) parents[x] = find(parents[x]);
        
        return parents[x];
    }

    private void union(int a, int b) {
        a = find(a);
        b = find(b);
        if (a != b) {
            table[a] = getValue(a, b);
            table[b] = null;
            parents[b] = a;
        }
    }

    private String getValue(int a, int b) {
        String value1 = table[a];
        String value2 = table[b];
        
        if (value1 != null) return value1;
        return value2;
    }

    private void doUnmerge(String[] commands) {
        int r = Integer.parseInt(commands[1]);
        int c = Integer.parseInt(commands[2]);

        for (int i = 1; i < parents.length; i++) find(i);

        int parent = parents[getIndex(r, c)];
        String value = table[parent];
        for (int i = 1; i < parents.length; i++) {
            if (parents[i] == parent) {
                parents[i] = i;
                table[i] = null;
            }
        }
        
        table[getIndex(r, c)] = value;
    }

    private void doPrint(String[] commands) {
        int r = Integer.parseInt(commands[1]);
        int c = Integer.parseInt(commands[2]);

        int parent = find(getIndex(r, c));
        String value = table[parent];
        if (value == null) result.add(EMPTY);
        else result.add(value);
    }
}