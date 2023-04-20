class Solution(object):
    def mergeKLists(self, lists):
        """
        :type lists: List[ListNode]
        :rtype: ListNode
        """
        if not lists:
            return None
        if len(lists) == 1:
            return lists[0]

        mid = len(lists) // 2 # 注意是 两条 /

        l1, l2 = self.mergeKLists(lists[:mid]), self.mergeKLists(lists[mid:])

        return self.merge(l1, l2)


    # 变成简单地 merge two sorted list
    def merge(self, l1, l2): 
        dummy = p = ListNode(-1)

        while l1 and l2:
            if l1.val < l2.val:
                p.next = l1
                l1 = l1.next
            else:
                p.next = l2
                l2 = l2.next
            p = p.next

        p.next = l1 or l2

        return dummy.next

    
    # 另一种 recursion 的 merge 解法
    def merge_recursion(self, l1, l2):
        if not l1 or not l2:
            return l1 or l2

        if l1.val < l2.val:
            l1.next = self.merge_recursion(l1.next, l2)
            return l1
        
        l2.next = self.merge(l1, l2.next)
        return l2



class ListNode(object):
    def __init__(self, val=0, next=None):
        self.val = val
        self.next = next