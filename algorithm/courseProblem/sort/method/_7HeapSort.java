package algorithm.courseProblem.sort.method;

/**
 * description：
    堆排序（完全二叉树）O(N*logN)
    ---建立最小堆O(N)=从最后一个非叶子节点的节点处*向上调整*；
        删除堆顶元素并重新*向下调整*建立最小堆O(N*logN)
 * @author Hartley
 * date： 2020/8/8
 */
public class _7HeapSort {


    //堆排序
    public Integer[] sort(Integer[] arr){
        printArray("初始数组：",arr);

        heapify(arr);//把数组变成堆
        printArray("变成堆之后",arr);

        int size = arr.length;
        while (size!=0){
            //砍根-交换-向下调序
            swap(arr,0,--size);//此时arr索引范围应为[0,size)
            siftDown(arr,0,size);
        }

        printArray("堆排序结果：",arr);
        return arr;
    }

    //建立堆O(N)
    public void heapify(Integer[] heap){
        //两种方法：注意要存在走完一趟完整的分支的部分
        // [1]从头到尾==非叶子节点向上调整
        /*
        for (int i=0;i<heap.length-1;i++){
            siftUp(heap,i);
        }*/

        //[2]从尾到头==向下调整,,边界！！！
        /**/
        for (int i= (heap.length-1)/2;i>=0;i--){
            siftDown(heap,i,heap.length);//---调整为小根堆

        }

    }

    //向上调整(使插入新数字后的数组满足根节点大于叶子节点O(logN)
    public void siftUp(Integer[] heap,int index){
        if (index==0) return;

        while (heap[index]<heap[(index-1)/2]){
            swap(heap,index,(index-1)/2);
            index = (index-1)/2;
            //if (index==0) break;//分析边界：当index为0时 (index-1)/2 == 0
        }
        /*
        while (index>0){
            Integer father = heap[(index-1)/2];
            Integer cur = heap[index];
            if (cur<father){
                swap(heap,(index-1)/2,index);
                index = index/2;
            }else {
                break;
            }
        }

         */
    }

    //向下调整为小根堆O(logN)===注意不包含size！
    public void siftDown(Integer[] heap,int index,int size){

        //检查有无左子
        while(index*2+1 < size){
            //左右角标
            int left = index*2+1,right = index*2+2;
            //此处的条件精辟！
            int minChildIndex = right<size && heap[right] < heap[left]? right: left;
            //如果父节点比最小子节点大，则交换
            if (heap[minChildIndex] < heap[index]){
                swap(heap,index,minChildIndex);
                index = minChildIndex;
            }else {
                break;
            }
        }
    }
    //向下调整为小根堆O(logN)----写的不够精炼
    public void siftDown2(Integer[] heap,int index,int size){
        Integer cur,leftChild,rightChild;

        //每次循环前判断有无左子节点2*i+1
        while(size> index*2+1){
             cur = heap[index];
             leftChild = heap[index*2+1];
             int minIndex;

            //也有右节点
            if (size> index*2+2){
                 rightChild = heap[index*2+2];
                minIndex = leftChild<rightChild ? index*2+1 : index*2+2;
            }else {
                minIndex = index*2+1;
            }

            //如果当前父节点比其最小子节点大，则调整顺序
            if (cur>heap[minIndex]){
                swap(heap,index,minIndex);
                index = minIndex;
            }else {
                break;//不大就结束循环
            }
        }



    }

    //==================工具=====================
    public void swap(Integer[] arr,int a,int b){
        Integer temp = arr[a];
        arr[a] = arr[b];
        arr[b] = temp;
    }
    public static void printArray(String note,Object[] arr)
    {
        if(arr==null)
            return;
        System.out.println(note);

        for (int i=0;i<arr.length ;i++ )
        {
            System.out.print(arr[i] + " , ");
        }
        System.out.println("");
    }
}
