import java.util.*;
import java.util.stream.*;

class Solution {    
    int n;
    int [][] vs;
    Double [][] atan;
    double [][][] sorted;
    Comparator<double[]> comparator;
    int s;
    Integer [][][] axisCompare;
    boolean [] existing;

    public int solution(int n, int[][] triangle, int[][] v) {
        this.n = n;
        vs = new int[n + 3][2];
        atan = new Double[n + 3][n + 3];
        sorted = new double[n + 3][][];
        axisCompare = new Integer[3][n + 3][n + 3];
        for (int i = 0; i < 3; i++) {
            vs[i][0] = triangle[i][0];
            vs[i][1] = triangle[i][1];
        }
        for (int i = 0; i < n; i++) {
            vs[i + 3][0] = v[i][0];
            vs[i + 3][1] = v[i][1];
        }
        existing = new boolean[n + 3];

        List<Integer> polygon = new ArrayList<>(n + 2);
        polygon.add(1);
        polygon.add(2);
        polygon.add(0);
        existing[0] = true;
        existing[1] = true;
        existing[2] = true;

        s = Long.compare(((long)vs[0][1] - vs[1][1]) * ((long)vs[2][0] - vs[1][0]), ((long)vs[0][0] - vs[1][0]) * ((long)vs[2][1] - vs[1][1]));
        comparator = (x, y) -> (int)Math.signum(y[0] - x[0]) * s;

        int answer = count(polygon, 0, n, null);
        return answer;
    }

    private int count(List<Integer> polygon, int insertion, int includes, Integer highest) {
        int size = polygon.size();

        if (insertion == size) {
            if (includes == 0) return 1;
            else return 0;
        }

        int count = 0;

        int ppv = polygon.get((insertion - 2 + size) % size);
        int pv = polygon.get((insertion - 1 + size) % size);
        int nv = polygon.get(insertion);

        double patan = pv < 3 ? atan(pv, ppv) : atan(ppv, pv);
        double natan = atan(pv, nv);

        if (pv > 2) {
            double pnatan = atan(ppv, nv);
            int[] range = range(ppv, patan, pnatan);
            int ps = range[0];
            int pe = range[1];
            includes -= pe - (ps + 1);

            if (insertion == size - 1 && includes > 0) return 0;
        }

        double[][] sortedInfo = sortedInfo(pv);
        int[] range = range(pv, patan, natan);
        int s = range[0];
        int e = range[1];
        s = s < 0 ? -s - 2 : s;
        includes += pv < 3 ? 0 : (e - (s + 1));

        int axis = (3 - (size - insertion) + 1) % 3;
        boolean complete = true;
        
        for (int i = s + 1; i < e; i++) {
            int iv = (int) sortedInfo[i][1];
            
            if (existing[iv]) continue;
            if (axis == 0 && compare(axis, polygon.get(0), iv) <= 0) continue;  
            if (insertion == 0 || highest == null || compare(axis, highest, iv) > 0) {
                polygon.add(insertion, iv);
                existing[iv] = true;
                count += count(polygon, insertion + 1, includes - 1, highest);
                polygon.remove(insertion);
                existing[iv] = false;
                if (insertion > 0) highest = iv;
            }
            if (axis == 2) {
                if (compare(0, polygon.get(0), iv) < 0) {
                    complete = false;
                    break;
                }
            } else if (axis == 0) {
                complete = false;
                break;
            }
        }
        
        if (complete) count += count(polygon, insertion + 1, includes, null);

        return count;
    }

    private int compare(int axis, int v1, int v2) {
        if (v1 == v2) return 0;
        else if (v1 == axis) return -1;
        else if (v2 == axis) return 1;
        else {
            if (axisCompare[axis][v1][v2] == null) {
                double eatan1 = atan(axis, v1);
                double eatan2 = atan(axis, v2);
                double satan = atan(axis, (axis + 2) % 3);
                int[] range1 = range(axis, satan, eatan1);
                int[] range2 = range(axis, satan, eatan2);
                axisCompare[axis][v1][v2] = Integer.compare(Math.abs(range1[0] - range1[1]), Math.abs(range2[0] - range2[1]));
            }
            
            return axisCompare[axis][v1][v2];
        }
    }

    private double atan(int from, int to) {
        if (atan[from][to] == null) {
            double y = vs[to][1] - vs[from][1];
            double x = vs[to][0] - vs[from][0];

            if (x == 0) atan[from][to] = y > 0 ? (double)400000002 : (double)0;
            else if (x > 0) atan[from][to] = y / x + 200000001;
            else atan[from][to] = y / x - 200000001;
        }
        
        return atan[from][to];
    }

    private double [][] sortedInfo(int v) {
        if (sorted[v] == null) {
            sorted[v] = new double[(n + 2) * 2][2];
            
            int a = 0;
            for (int i = 0; i < n + 3; i++) {
                if (i != v) {
                    sorted[v][a][0] = atan(v, i);
                    sorted[v][a][1] = i;
                    sorted[v][a + 1][0] = sorted[v][a][0] > 0 ? (sorted[v][a][0] - 800000004) : (sorted[v][a][0] + 800000004);
                    sorted[v][a + 1][1] = i;
                    a += 2;
                }
            }
            
            Arrays.sort(sorted[v], comparator);
        }
        return sorted[v];
    }

    private int [] range(int v, double satan, double eatan) {
        double[][] sortedInfo = sortedInfo(v);
        int s = Arrays.binarySearch(sortedInfo, new double[]{satan, -1}, comparator);
        int e = Arrays.binarySearch(sortedInfo, new double[]{eatan, -1}, comparator);
        
        int si = s < 0 ? -s - 1 : s;
        int ei = e < 0 ? -e - 1 : e;
        if (si > ei) {
            if (si >= sortedInfo.length / 2) si -= sortedInfo.length / 2;
            else ei += sortedInfo.length / 2;
        }
        
        s = s < 0 ? -si - 1 : si;
        e = e < 0 ? -ei - 1 : ei;
        
        return new int[] {s, e};
    }
}
