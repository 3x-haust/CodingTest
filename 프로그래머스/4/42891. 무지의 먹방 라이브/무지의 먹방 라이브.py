import heapq

def solution(food_times, k):
    answer = -1
    hq = []
    
    for i in range(len(food_times)):
        heapq.heappush(hq, (food_times[i], i+1)) 
        
    length = len(hq)
    pre = 0
    
    while hq:
        diff = (hq[0][0] - pre) * length
        
        if diff <= k:
            k -= diff
            length -= 1
            pre, _ = heapq.heappop(hq)   
        else:
            idx = k % length
            hq.sort(key = lambda x: x[1])
            answer = hq[idx][1]
            break
            
    return answer