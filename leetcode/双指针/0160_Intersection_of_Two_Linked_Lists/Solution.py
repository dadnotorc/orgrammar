class Solution(object):
    def getIntersectionNode(self, headA, headB):
        """
        :type head1, head1: ListNode
        :rtype: ListNode
        """
        if not headA or not headB:
            return None

        p1, p2 = headA, headB

        while p1 != p2:
            p1 = headB if not p1 else p1.next
            p2 = headA if not p2 else p2.next

            # 等同 Java 中, p1 = (p1 == null ? headB : p1.next);

        return p1



class ListNode(object):
    def __init__(self, val=0, next=None):
        self.val = val
        self.next = next
