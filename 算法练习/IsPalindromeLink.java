import java.util.Stack;
/**
*�������жϡ������Ƿ�Ϊ��������
ʱ�临�Ӷ�O(N) ����ռ�O(1)
�߼��򵥣�ʵ���������ѣ�����������

˼·�����ÿ���ָ���ҵ������е㣬Ȼ���Ұ���������αȶԺ�ָ�
�ѵ㣺����ʵ������Ĺ���

  1 ->2->3 ->2 ->1->null
			  ^    ^    ^             =>   n1->null��Ҫn2->n1��Ȼ��ָ��ƽ��
			  n1   n2   n3					   n1=n2; n2=n3; n3=n3.next;  ����������ֱ��n3Ϊnull

*@author Hartley
*@version 1.0.0
*/

class  IsPalindromeLink
{
	//************�������***************
	public static void main(String[] args) 
	{
		Node node1 = new Node(1);//����һ
		node1.next = new Node(1);
		node1.next.next = new Node(2);
		node1.next.next.next = new Node(1);
		node1.next.next.next.next = new Node(1);
		//printLinkedList(node1);

		//boolean flag = stackMethod(node1);
		//boolean flag = halfStackMethod(node1);
		boolean flag = isPalindrome(node1);

		System.out.println("this LinkedList is palimdrom��"+flag);
	}

	//��ӡ����
	public static void printLinkedList(Node head)
	{
		while (head!=null)
		{
			System.out.print(head.value+" -> ");
			head = head.next;
		}
		System.out.println("null");
	}

	//��1������ռ�ΪO(N)================================
	//˼·�����ö�ջ����ȳ���װ���ջ�����ε���������Ƚ�
	public static boolean stackMethod(Node head)
	{
		Stack<Node> stack = new Stack<>();//����ջ�ռ�
		Node cur = head;

		while (cur != null)//ѹջ�Ĺ���
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

	//��2������ռ�ΪO(N/2)================================
	//����������ָ��fast(������) slow(��һ��)���ҵ������е㣬
	//���е��Լ�֮���һ��ŵ���ջ�У������Ƚ�
	public static boolean halfStackMethod(Node head)
	{
		if (head==null || head.next == null)
		{
			return true;//���Ϊnull ����ֻ��һ��Ԫ�أ���Ϊ����
		}
		Node fast = head;
		Node slow = head;
		Stack<Node> stack = new Stack<>();

		while (fast.next!=null && fast.next.next!=null)//fast����һ���Լ�����һ����û�нڵ�
		{
			slow = slow.next;//ѭ��������slowΪ�����е㴦
			fast = fast.next.next;
		}

		while (slow!=null)
		{
			stack.push(slow);//���е�Ԫ���Լ��е�֮���Ԫ�ض�ѹ��ջ��
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
	
	//��3������ռ临�Ӷ�ΪO(1)================================
	//��������ʹ�м�λ�ô�������Ԫ�ض�ָ���м�Ԫ�أ��м�Ԫ��ָ��null
	public static boolean isPalindrome(Node head)
	{
		printLinkedList(head);//��ӡ��ʼ����

		if (head==null || head.next == null)
		{
			return true;
		}
		Node n1 = head;//��ָ��
		Node n2 = head;//��ָ��
		//���е�
		while (n2.next!=null && n2.next.next!=null)
		{
			n1 = n1.next;
			n2 = n2.next.next;
		}
		
		n2 = n1.next;//n2����Ϊn1����Ԫ��
		Node n3 = n2.next;//n3Ϊn1������Ԫ��

		n1.next = null;//�е�n1ָ��null

		//���м�����ҡ�
		while (n3!=null)//��n2��Ϊ�����ɼ򻯴��룬���е��Ρ�����
		{
			
			n2.next = n1;//��Ӧn3��������
			n1 = n2;//����סn2�ڵ�Ϊn1��������һ��ָ���Ŀ��ڵ�
			n2 = n3;//����ס���������ͷn3
			n3 = n3.next;

		}
			n2.next = n1;//������Ұ������ָ��n2Ϊ�������Ҷ˵�ͷ
			n1 = head;
			n3 = n2;//�����n2�ڵ㣬���ں��� �ָ�����
			
			printLinkedList(head);//"���������������"+
			printLinkedList(n2);//"���������������"+
		boolean flag = true;
		//�������---
		while (n1!=null)//1-2-1  1-2-2-1 ,�ж�����ָ���next�ȵ�null
		{
			if (n1.value != n2.value)
			{
				flag = false;
			}
			n1 = n1.next;
			n2 = n2.next;
		}

		n1 = n3;//�����Ҷ� ����˳��Ϊ n3 n2 n1
		n2 = n3.next;
		n3 = n2.next;
		n1.next = null;//ĩβָ��null
		//���ؽ��ǰ�ָ��������Ҷ����м䡿
		while (n3!=null)
		{
			n2.next = n1;
			n1 = n2;
			n2 = n3;
			n3 = n3.next;
		}
		n2.next = n1;
		
		printLinkedList(head);//��ӡ��λ�õ�����

		return flag;
	}
}

class Node
{
	public int value;
	public Node next;
	public Node(int value)
	{
		this.value = value;
	}
}