package algorithm.courseProblem.linkedlist;

import org.junit.Test;

import java.util.Arrays;

/**
 * 案例：以一个基准值a，将一链表分为三段，左边小于a，中间等于a，右边大于a
 * 要求：时间复杂度O(N) 空间O(1)
 * 思路：遍历把第一次比基准的小的、等于的、大于的依次用small、equal、large三个指针指向
 *
 * @author Hartley
 * @create 2020/5/18
 */
public class PartitionLink {
    //额外空间为O(N)时容易
//    思路：将链表元素放入数组中，在数组中partition后再链表连起来
    //可以用 节点数组，在后面容易连成链表
    @Test
    public void linkArrayPartition(){
        Node head = new Node(5);//初始化链表
        head.next = new Node(6);
        head.next.next = new Node(2);
        head.next.next.next = new Node(8);
        head.next.next.next.next = new Node(9);
        head.next.next.next.next.next = new Node(1);
        printLinkedList(head);

        int base = 4;//基准
        Node tmp = head;
        int len = 0;//链表长度
//      获取链表长度
        while (tmp!=null){
            len++;
            tmp = tmp.next;
        }
        //System.out.println(len);
//      链表赋值到数组
        int[] buffer = new int[len];
        int i = 0;
        tmp = head;

        while(tmp != null){
            buffer[i++] = tmp.value;
            tmp = tmp.next;
        }
        //System.out.println(Arrays.toString(buffer));

//        根据基准base的值对数组partition
        partition(buffer,base);
        //System.out.println("Arrays.toString");
        System.out.println(Arrays.toString(buffer));

        tmp = head;
        //划分好的数组再转换为链表
        for(i=0;i<buffer.length;i++){
            tmp.value = buffer[i];
            tmp = tmp.next;
        }

        printLinkedList(head);

    }

    //对数组partition----划区
    public void partition(int[]arr,int base){
        int smallZone = -1;
        int largeZone = arr.length;
        int index = 0;
        //arr[0]=33;


        while (index<largeZone){//循环条件！！不是结束循环条件！！
            //[5, 6, 2, 8, 9, 1] base=4
            //[1, 2, 3, 6, 5, 4] base=3
            //    ^     ^
            //[1, 8, 2, 9, 6, 5]
            //System.out.println(largeZone);
            if(arr[index]<base){//小于基准值，划到小于区(当前值与smallzone位置值交换）
                int temp = arr[index];
                arr[index] = arr[++smallZone];
                arr[smallZone] = temp;
                index++;
            }else if (arr[index]>base){//大于基准值，划到大于区（指针不动）
                int temp = arr[index];
                arr[index] = arr[--largeZone];
                arr[largeZone] = temp;


            }else{//等于时不操作
                index++;
            }
        }
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



}

