package algorithm.courseProblem.linkedlist;

import java.util.Stack;
/**
*案例：判断【链表】是否为回文序列
时间复杂度O(N) 额外空间O(1)
逻辑简单，实现起来好难！！！！！！

思路：利用快慢指针找到链表中点，然后将右半边逆序，依次比对后恢复
难点：链表实现逆序的过程

  1 ->2->3 ->2 ->1->null
			  ^    ^    ^             =>   n1->null后，要n2->n1，然后指针平移
			  n1   n2   n3			 n1=n2; n2=n3; n3=n3.next;  依次往复，直到n3为null

*@author Hartley
*@version 1.0.0
*/

class  IsPalindromeLink
{


	//************程序入口***************
	public static void main(String[] args) 
	{
		Node node1 = new Node(1);//链表一
		node1.next = new Node(1);
		node1.next.next = new Node(2);
		node1.next.next.next = new Node(1);
		node1.next.next.next.next = new Node(1);
		//printLinkedList(node1);

		//boolean flag = stackMethod(node1);
		//boolean flag = halfStackMethod(node1);
		boolean flag = isPalindrome(node1);

		System.out.println("this LinkedList is palimdrom？"+flag);
	}

	//打印链表
	public static void printLinkedList(Node head)
	{
		while (head!=null)
		{
			System.out.print(head.value+" -> ");
			head = head.next;
		}
		System.out.println("null");
	}

	//【1】额外空间为O(N)================================
	//思路：利用堆栈后进先出，装入堆栈后，依次弹出与链表比较
	public static boolean stackMethod(Node head)
	{
		Stack<Node> stack = new Stack<>();//额外栈空间
		Node cur = head;

		while (cur != null)//压栈的过程
		{
			stack.push( cur );
			cur = cur.next;
		}
		
		cur = head;
		while (cur != null)
		{
			if (cur.value != stack.pop().value)
			{
				return false;
			}
			cur = cur.next;
		}
		return true;
	}

	//【2】额外空间为O(N/2)================================
	//用两个快慢指针fast(走两步) slow(走一步)，找到链表中点，
	//把中点以及之后的一半放到堆栈中，弹出比较
	public static boolean halfStackMethod(Node head)
	{
		if (head==null || head.next == null)
		{
			return true;//如果为null 或者只有一个元素，视为回文
		}
		Node fast = head;
		Node slow = head;
		Stack<Node> stack = new Stack<>();

		while (fast.next!=null && fast.next.next!=null)//fast的下一步以及下下一步有没有节点
		{
			slow = slow.next;//循环结束后，slow为链表中点处
			fast = fast.next.next;
		}

		while (slow!=null)
		{
			stack.push(slow);//把中点元素以及中点之后的元素都压入栈中
			slow = slow.next;
		}

		while (!stack.isEmpty())
		{
			if (head.value != stack.pop().value )
			{
				return false;
			}
			head = head.next;
		}
		return true;
	}
	
	//【3】额外空间复杂度为O(1)================================
	//调整链表，使中间位置处的左右元素都指向中间元素，中间元素指向null
	public static boolean isPalindrome(Node head)
	{
		printLinkedList(head);//打印初始链表

		if (head==null || head.next == null)
		{
			return true;
		}
		Node n1 = head;//慢指针
		Node n2 = head;//快指针
		//找中点
		while (n2.next!=null && n2.next.next!=null)
		{
			n1 = n1.next;
			n2 = n2.next.next;
		}
		
		n2 = n1.next;//n2复用为n1的右元素
		Node n3 = n2.next;//n3为n1的右右元素

		n1.next = null;//中点n1指向null

		//【中间点往右】
		while (n3!=null)//以n2作为条件可简化代码，但有点晕。。。
		{
			
			n2.next = n1;//对应n3脱离链表
			n1 = n2;//保存住n2节点为n1，用于下一次指向的目标节点
			n2 = n3;//保存住脱离链表的头n3
			n3 = n3.next;

		}
			n2.next = n1;//完成了右半侧逆序指向，n2为链表最右端的头
			n1 = head;
			n3 = n2;//保存好n2节点，用于后续 恢复操作
			
			printLinkedList(head);//"逆序操作后，左链表："+
			printLinkedList(n2);//"逆序操作后，右链表："+
		boolean flag = true;
		//左右相比---
		while (n1!=null)//1-2-1  1-2-2-1 ,中都是左指针的next先到null
		{
			if (n1.value != n2.value)
			{
				flag = false;
			}
			n1 = n1.next;
			n2 = n2.next;
		}

		n1 = n3;//在最右端 排列顺序为 n3 n2 n1
		n2 = n3.next;
		n3 = n2.next;
		n1.next = null;//末尾指向null
		//返回结果前恢复链表：【右端往中间】
		while (n3!=null)
		{
			n2.next = n1;
			n1 = n2;
			n2 = n3;
			n3 = n3.next;
		}
		n2.next = n1;
		
		printLinkedList(head);//打印复位好的链表

		return flag;
	}
}


class Node {
	public int value;
	public Node next;
	public Node(int value)
	{
		this.value = value;
	}

}
