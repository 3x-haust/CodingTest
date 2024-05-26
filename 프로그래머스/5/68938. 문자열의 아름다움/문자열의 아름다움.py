import collections
import itertools

def solution(s):
    count_by_len_by_ch = collections.defaultdict(
        lambda: collections.defaultdict(int))
    for ch, group in itertools.groupby(s):
        count_by_len_by_ch[ch][len(list(group))] += 1

    answer = (len(s) - 1) * len(s) * (len(s) + 1) // 6
    for count_by_len in count_by_len_by_ch.values():
        t = sum(count_by_len.values())
        s = sum(l * count for l, count in count_by_len.items())
        for l in range(1, max(count_by_len) + 1):
            answer -= s * (s - 1) // 2
            s -= t
            t -= count_by_len[l]
    return answer