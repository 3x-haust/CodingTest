import sys
sys.setrecursionlimit(1000000)

class Node:
    def __init__(self,x):
        self.number = x
        self.query = 0
        self.group = None
        self.prev = None
        self.next = None

class Group:
    def __init__(self,x):
        self.number = x
        self.head = None
        self.tail = None


def findRep(p,x):
    if p[x] == x: return x
    p[x] = findRep(p,p[x])
    return p[x]

def solution(n, queries):
    answer = []
    nodes = [Node(i) for i in range(n)]
    p = [i for i in range(n)]
    g = [Group(i) for i in range(n+len(queries))]

    for i in range(n):
        nodes[i].group = g[i]
        g[i].head = nodes[i]
        g[i].tail = nodes[i]

    for iq, q in enumerate(queries):
        c, x, y = q
        x, y = nodes[findRep(p,x)], nodes[findRep(p,y)]
        if c == 1 and x.group != y.group:
            x, y = x.group.head, y.group.head
            temp = y
            while True:
                p[y.number] = temp.number
                y = y.next
                if not y:break
                
            temp.group.head = None
            temp.group.tail = None
            temp.group = x.group
            temp.prev = x.group.tail
            temp.next = None
            temp.query = iq
            x.group.tail.next = temp
            x.group.tail = temp

        if c == 2 and x.query <= y.query:
            temp = x
            while temp != y:
                p[temp.number] = x.number
                temp = temp.next
                
            p[temp.number] = x.number
            
            if x.prev: x.prev.next = y.next
            else: x.group.head = y.next
            
            if y.next: y.next.prev = x.prev
            else: y.group.tail = x.prev
            x.group = g[n]
            x.prev = None
            x.next = None
            x.query = iq
            g[n].head = x
            g[n].tail = x
            n += 1

        if c == 3: answer.append("Yes" if x.group == y.group else "No")

    return answer
