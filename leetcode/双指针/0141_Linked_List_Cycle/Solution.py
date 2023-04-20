
class Solution(object):
    def hasCycle(self, head):
        """
        :type head: ListNode
        :rtype: bool
        """
        slow = fast = head

        while fast and fast.next:
            fast = fast.next.next
            slow = slow.next

            if (fast == slow):
                return True
        
        return False

class ListNode(object):
    def __init__(self, val=0, next=None):
        self.val = val
        self.next = next
