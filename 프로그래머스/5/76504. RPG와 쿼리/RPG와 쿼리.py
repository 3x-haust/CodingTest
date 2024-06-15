def solution(n, z, roads, queries):
    from math import inf

    _roads = [[] for _ in range(n)]
    for v1, v2, w in roads:
        _roads[v1].append((v2, w))
    roads = _roads

    dp, dp[0][0] = [[inf for _ in range(z ** 2)] for _ in range(n)], 0

    min_for_money = [inf for _ in range(z ** 2)]

    for money_now in range(z ** 2):

        mm_now = min(dp[v][money_now] for v in range(n))
        min_for_money[money_now] = mm_now
        if mm_now == inf: continue
        for v_now in range(n):
            dp[v_now][money_now] = min(dp[v_now][money_now], mm_now + 1)

        for v_now in range(n):
            if dp[v_now][money_now] == inf: continue
            for v_next, w in roads[v_now]:
                if money_now + w >= z**2: continue
                dp[v_next][money_now + w] = min(dp[v_next][money_now + w], dp[v_now][money_now] + 1)

    prefix_min_for_money = [[] for _ in range(z)]
    for start_money in range(z):
        prefix_min = prefix_min_for_money[start_money]
        for money, offset in zip(range(start_money, z ** 2, z), range(z)):
            prefix_min.append(min_for_money[money] - offset)
        for i in range(1, len(prefix_min)):
            prefix_min[i] = min(prefix_min[i - 1], prefix_min[i])
        del prefix_min

    answer = [prefix_min_for_money[money % z][min(money // z, z - 1)] + money // z for money in queries]
    answer = [-1 if v == inf else v for v in answer]
    
    return answer
