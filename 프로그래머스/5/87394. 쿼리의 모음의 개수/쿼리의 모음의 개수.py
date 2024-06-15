def get_dict(A,B):
    N = len(A)
    temp_dp = {}
    for i in range(N):
        a = A[i]
        b = B[i]
        new_var = a * b
        new_dp = {}
        for (x,y),v in temp_dp.items():
            before_y = y + a * B[x]
            new_dp[(x,before_y)] = new_dp.get((x, before_y), 0) + v
            new_y = y + new_var
            new_dp[(i, new_y)] = new_dp.get((i, new_y), 0) - v
            
            pass
        new_dp[(i, new_var)] = new_dp.get((i, new_var), 0) - 1
        temp_dp = new_dp
    rt = {}
    for (x, y), v in temp_dp.items():
        rt[y] = rt.get(y, 0) + v
    return rt

def seung(a, b, c):
    dic = {}
    var = a
    t = 1
    dic[t] = var
    while t < b:
        t <<= 1
        var *= var
        var %= c
        dic[t] = var
    rt = 1
    while t > 1:
        if t & b:
            rt *= dic[t]
            rt %= c
        t >>= 1
    if t & b: rt *= a
    return rt % c

class goodq:
    def __init__(self, var, inc):
        self.var = var
        self.inc = [inc]
        self.num = [1]
        self.done = False

    def add_inc(self):
        self.num[-1] += 1

    def dup_inc(self, inc_num):
        self.inc.append(inc_num)
        self.num.append(1)

    def __repr__(self):
        return (f'<{str(self.var)}, {str(self.inc)}, {str(self.num)}>')

def solution(q, a):
    answer = 0

    dic_possible = {}
    increase_num = {}
    possible_num = 0
    qnumdict = {}    
    for n,x in enumerate(a):
        increase_num[x] = increase_num.get(x, 0) + 1
        keys = list(increase_num.keys())
        for key in keys:
            if key > x:
                increase_num[x] += increase_num[key]
                del increase_num[key]
                qnumdict[key][-1].done = True
            elif key < x:
                possible_num += key * increase_num[key]
                qnumdict[key][-1].add_inc()
        possible_num += x * increase_num[x]

        try:
            if qnumdict[x][-1].done == False:
                qnumdict[x][-1].dup_inc(increase_num[x])
            else:
                gen_obj = goodq(x, increase_num[x])
                qnumdict[x].append(gen_obj)
        except:
            qnumdict[x] = [goodq(x, increase_num[x])]

    dp = {}
    for x in qnumdict.keys():
        for chain in qnumdict[x]:
            if len(chain.num) > 0: temp_dp = get_dict(chain.num, chain.inc)
            else: continue
            new_dp = {}

            for temp_key, temp_val in temp_dp.items():
                new_dp[temp_key] = new_dp.get(temp_key, 0) + temp_val
                for org_key, org_val in dp.items():
                    gen_key = temp_key + org_key
                    gen_val = temp_val * org_val
                    new_dp[gen_key] = new_dp.get(gen_key, 0) + gen_val

            for org_key, org_val in dp.items():
                new_dp[org_key] = new_dp.get(org_key,0) + org_val

            for key in set(new_dp.keys()):
                if new_dp[key] == 0: del new_dp[key]
            dp = new_dp

    mod_val = 998244353
    answer = seung(possible_num, q, mod_val)

    for key, val in dp.items():
        delta = val * (seung(possible_num - key, q, mod_val))
        answer += delta

    return answer % mod_val