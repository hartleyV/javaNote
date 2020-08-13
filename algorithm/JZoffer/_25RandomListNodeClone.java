package algorithm.JZoffer;

import algorithm.utils.ListNode;
import algorithm.utils.RandomListNode;

import java.util.HashMap;

/**
 输入一个复杂链表（每个节点中有节点值，以及两个指针，一个指向下一个节点，另一个特殊指针random指向一个随机节点），
 请对此链表进行**深拷贝**，并返回拷贝后的头结点。
 （注意，输出结果中请不要返回参数中的节点引用，否则判题程序会直接返回空）
 * @author Hartley
 * date： 2020/8/13
 */
public class _25RandomListNodeClone {

    //[1]借助辅助空间，简单
    public RandomListNode Clone(RandomListNode pHead)
    {
        //深拷贝（简单办法，通过辅助的HashMap）
        RandomListNode newHead = new RandomListNode(-1);
        RandomListNode newHeadCur = newHead,pHeadCur = pHead;

        HashMap<RandomListNode,RandomListNode> mapper = new HashMap<>();
        //HashMap<RandomListNode,RandomListNode> mapper2 = new HashMap<>();
        while(pHeadCur!=null){
            newHeadCur.next = new RandomListNode(pHeadCur.label);
//======================以原数组为key，存入HashMap===================================
            mapper.put(pHeadCur,newHeadCur.next);
            //mapper2.put(pHead,newHeadCur.next);
            newHeadCur = newHeadCur.next;
            pHeadCur = pHeadCur.next;
        }

        newHeadCur = newHead.next;
        pHeadCur = pHead;
        while(newHeadCur!=null){
            //newHeadCur.random = mapper2.get(mapper1.get(newHeadCur).random);
//===========1和1‘在同一位置，只需要找到1的Random指向的3对应的3‘，并让1‘指向3’===============
            newHeadCur.random = mapper.get(pHeadCur.random);
            newHeadCur =newHeadCur.next;
            pHeadCur = pHeadCur.next;
        }
        return newHead.next;
    }

    //[2]不借助辅助空间（按1 1' 2 2' 3 3'...插入原链表中，借此赋予random指针
    public static RandomListNode Clone2(RandomListNode pHead)
    {
        RandomListNode cur = pHead;
        while (cur!=null){
            //依次遍历，遍历途中拷贝1'
            RandomListNode next = cur.next;
            cur.next = new RandomListNode(cur.label);
            cur.next.next = next;
            cur = next;
        }

        //RandomListNode.printRandomListNode(pHead);

        cur = pHead;
        while (cur!=null){
            //依次遍历，赋random给新节点
            //注意！！random有可能为null
            cur.next.random = cur.random!=null?cur.random.next:null;
            //把1对应random指针为3，则该操作为1'的random指向3'
            cur = cur.next.next;
        }

        //RandomListNode.printRandomListNode(pHead);

        cur = pHead;
        //将新拷贝链表整出来,并且还原原链表！！
        RandomListNode pClone = new RandomListNode(-1);
        RandomListNode pCur = pClone;
        while (cur!=null){
            //根据奇数个是old链表 偶数个是new链表
            pCur.next = cur.next;
            cur.next = cur.next.next;

            pCur = pCur.next;//next默认为null！！
            cur = cur.next;


        }

        //RandomListNode.printRandomListNode(pHead);
        RandomListNode.printRandomListNode(pClone.next);
        return pClone.next;
    }
}
