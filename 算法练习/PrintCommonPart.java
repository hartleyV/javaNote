/**
*打印【有序】链表的公共部分：
思路：利用类似外排的方法
*@author Hartley
*@version 1.0.0
*/

class  PrintCommonPart
{
	//链表 节点类
	public static class Node
	{
		public int value;
		public Node next;
		//构造方法
		public Node(int value)
		{
			this.value = value;
		}
	}
	
	//利用外排找出相同数据
	//谁小动谁，某个遍历完成，则打印结束
	public static void printCommonPart(Node node1,Node node2)
	{
		System.out.print("公共部分：");
		while (node1 != null && node2 != null)
		{
			if (node1.value==node2.value)
			{
				System.out.print(node1.value+" , ");
				//两链表指针同时移动
				node1 = node1.next;
				node2 = node2.next;
			}else if (node1.value < node2.value)
			{
				node1 = node1.next;//谁小动谁
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
	//************程序入口***************
	public static void main(String[] args) 
	{
		Node node1 = new Node(3);//链表一
		node1.next = new Node(6);
		node1.next.next = new Node(11);
		node1.next.next.next = new Node(18);
		node1.next.next.next.next = new Node(28);

		Node node2 = new Node(11);//链表二
		node2.next = new Node(18);
		node2.next.next = new Node(19);
		node2.next.next.next  = new Node(20);
		node2.next.next.next.next = new Node(28);

		printLinkedList(node1);
		printLinkedList(node2);
		printCommonPart(node1,node2);

	}


}

