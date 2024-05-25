import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static int[] parents;
    static Set<Integer> knownTruthPeople;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        parents = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            parents[i] = i;
        }

        st = new StringTokenizer(br.readLine());
        int en = Integer.parseInt(st.nextToken());
        knownTruthPeople = new HashSet<>();
        for (int i = 0; i < en; i++) {
            knownTruthPeople.add(Integer.parseInt(st.nextToken()));
        }

        List<List<Integer>> partyList = new ArrayList<>();
        for (int i = 0; i < m; i++) {
            partyList.add(new ArrayList<>());
            st = new StringTokenizer(br.readLine());
            int pn = Integer.parseInt(st.nextToken());

            int firstPerson = Integer.parseInt(st.nextToken());
            partyList.get(i).add(firstPerson);
            for (int j = 1; j < pn; j++) {
                int nextPerson = Integer.parseInt(st.nextToken());
                mergeGroups(firstPerson, nextPerson);
                partyList.get(i).add(nextPerson);
            }
        }

        int cnt = 0;
        for (List<Integer> party : partyList) {
            if (canAttendParty(party)) {
                cnt++;
            }
        }
        System.out.println(cnt);
    }

    static int findParent(int x) {
        if (parents[x] == x) return x;
        return parents[x] = findParent(parents[x]);
    }

    static void mergeGroups(int x, int y) {
        int rootX = findParent(x);
        int rootY = findParent(y);

        if (knownTruthPeople.contains(rootY)) {
            int tmp = rootX;
            rootX = rootY;
            rootY = tmp;
        }

        parents[rootY] = rootX;
    }

    static boolean canAttendParty(List<Integer> party) {
        for (int person : party) {
            if (knownTruthPeople.contains(findParent(person))) {
                return false;
            }
        }
        return true;
    }
}
