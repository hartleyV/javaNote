package algorithm.courseProblem.sort.method.review;

/**
 * description：
 *
 * @author Hartley
 * date： 2020/8/9
 */
public class HeapSort {
    public Integer[] sort(Integer[] arr) {
        if (arr.length < 2 || arr == null) return arr;

        heapSort(arr);
        return arr;
    }

    public  void heapSort(Integer[] heap){
        printArray("初始数组：",heap);
        int size = heap.length;
        //转换为大根堆
        for (int i=0;i<size;i++){
           siftUp(heap,i);
        }

        printArray("堆数组：",heap);

        //砍根排序
        while (size!=0){
            swap(heap,0,--size);//交换位置
            siftDown(heap,0,size);//调整为大根堆

        }
        printArray("堆排序后数组：",heap);
    }
    public void siftUp(Integer[]arr,int index){
        while (arr[index]>arr[(index-1)/2]){
            swap(arr,index,(index-1)/2);
            index = (index-1)/2;
        }
    }
    public void siftDown(Integer[] arr,int index,int size){
        while (index*2+1<size){
            int maxChildIndex = index*2+2 < size && arr[index*2+2]>arr[index*2+1]?index*2+2:index*2+1;
            if (arr[maxChildIndex]>arr[index]){
                swap(arr,maxChildIndex,index);
                index = maxChildIndex;
            }else {
                break;
            }

        }
    }
    public void swap(Integer[]arr,int a,int b){
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
