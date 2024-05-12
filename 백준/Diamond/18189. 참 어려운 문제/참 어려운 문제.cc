#include <bits/stdc++.h>

using namespace std;

typedef long long ll;

int n;
int arr[250002];
vector<int> link[250002];
vector<int> eulerVec[250002];

int in[250002], idx[250002], sz[250002], out[250002], inCnt;
ll sum[250002];

void dfs(int x, int par = -1){
    in[x] = ++inCnt, idx[inCnt] = x;
    sz[x] = 1;
    for(auto y: link[x]){
        if(y == par) continue;
        dfs(y, x);
        sz[x] += sz[y];
    }
    out[x] = in[x] + sz[x] - 1;
}

int main(){
    scanf("%d", &n);
    for(int i=1; i<=n; i++){
        scanf("%d", &arr[i]);
    }
    for(int i=1; i<n; i++){
        int x, y;
        scanf("%d %d", &x, &y);
        link[x].push_back(y);
        link[y].push_back(x);
    }

    dfs(1);
    for(int i=1; i<=n; i++) eulerVec[arr[i]].push_back(in[i]);
    for(int i=1; i<=n; i++) sort(eulerVec[i].begin(), eulerVec[i].end());

    for(int i=1; i<=n; i++){
        int c = arr[i];
        for(auto y: link[i]){
            if(in[y] < in[i]){ /// y가 i의 부모
                if(eulerVec[c].front() < in[i] || eulerVec[c].back() > out[i]){
                    sum[in[i]]++;
                    sum[out[i]+1]--;
                }
            }
            else{ /// y가 i의 자식
                if(lower_bound(eulerVec[c].begin(), eulerVec[c].end(), in[y]) !=
                   upper_bound(eulerVec[c].begin(), eulerVec[c].end(), out[y])){
                    sum[1]++;
                    sum[in[y]]--;
                    sum[out[y]+1]++;
                }
            }
        }
    }

    for(int i=1; i<=n; i++) sum[i] += sum[i-1];

    ll ans0 = 0, ans1 = 0, ans2 = 0;
    for(int i=1; i<=n; i++) if(!sum[in[i]]) ans0++, ans1 += i, ans2 += ll(i)*ll(i);
    printf("%lld\n%lld\n%lld", ans0, ans1, ans2);
}