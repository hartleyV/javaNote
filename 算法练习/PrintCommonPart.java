/**
*��ӡ����������Ĺ������֣�
˼·�������������ŵķ���
*@author Hartley
*@version 1.0.0
*/

class  PrintCommonPart
{
	//���� �ڵ���
	public static class Node
	{
		public int value;
		public Node next;
		//���췽��
		public Node(int value)
		{
			this.value = value;
		}
	}
	
	//���������ҳ���ͬ����
	//˭С��˭��ĳ��������ɣ����ӡ����
	public static void printCommonPart(Node node1,Node node2)
	{
		System.out.print("�������֣�");
		while (node1 != null && node2 != null)
		{
			if (node1.value==node2.value)
			{
				System.out.print(node1.value+" , ");
				//������ָ��ͬʱ�ƶ�
				node1 = node1.next;
				node2 = node2.next;
			}else if (node1.value < node2.value)
			{
				node1 = node1.next;//˭С��˭
			}else
			{
				node2 = node2.next;
			}
		}
	}
	public static void printLinkedList(Node node)
	{
		System.out.println("========++=========");
		while ( node !=null)
		{
			System.out.print(node.value+"--");
			node = node.next;
		}
		System.out.println("");
		System.out.println("========++=========");
	}
	//************�������***************
	public static void main(String[] args) 
	{
		Node node1 = new Node(3);//����һ
		node1.next = new Node(6);
		node1.next.next = new Node(11);
		node1.next.next.next = new Node(18);
		node1.next.next.next.next = new Node(28);

		Node node2 = new Node(11);//�����
		node2.next = new Node(18);
		node2.next.next = new Node(19);
		node2.next.next.next  = new Node(20);
		node2.next.next.next.next = new Node(28);

		printLinkedList(node1);
		printLinkedList(node2);
		printCommonPart(node1,node2);

	}


}

