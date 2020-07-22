import java.util.ArrayList;
import java.util.List;

/**
 * description：
 *  给一个字符串，按 Z 字型打印
    如： “ABCDEFG” R=3
    则：|A   E|
       |B D F|
       |C   G|
    输出字符串：AEBDFCG //不含空格！！

 * @author Hartley
 * date： 2020/7/22
 */
public class ZigzagConversion {

    //字符数组法
     static String convert(String s,int nRows){

         if(nRows==1) return s;//注意边界，，

        int n=s.length();
        int c=0,r=0,flag=1;
        //定义临时字符数组
        char[][] temp = new char[nRows][n/2+1];
        for (int i=0;i<n;i++){
            //在当前列向下打印flag==1
            if (r<nRows && flag==1){
                temp[r++][c] = s.charAt(i);
                if (r==nRows){
                        //向上斜线部分flag=0
                        flag=0;
                        r--;
                        continue;
                }
            }
            if (r!=0 && flag==0){
                temp[--r][++c] = s.charAt(i);

                if (r==0){
                    flag=1;
                    r++;
                    continue;
                }
            }


        }
        StringBuilder ret = new StringBuilder();
       //打印到字符串
        for (int j=0;j<temp.length;j++){
            for (int k=0;k<temp[0].length;k++){
                char t = temp[j][k];
                if (t!=0) ret.append(t);
                //ret.append("\t");
            }
                //ret.append("\r\n");
        }
        return ret.toString();
    }

    //用list
    static String convert2(String s,int nRows){
         if(nRows<=1) return s;

         int n = s.length();
         int currR = 0;
         List<StringBuilder> rows = new ArrayList<>();
         boolean flag = false;

         for (int i=0;i<nRows;i++){
             rows.add(new StringBuilder());
         }
        //for-each代码更简练
         for (char c:s.toCharArray()){
             rows.get(currR).append(c);//在当前行的行尾加字符
             if(currR==0 || currR==nRows-1) flag = !flag;//判断是否到头 0-ture nRows-false
             currR += flag? 1:-1;//true表示向下走
         }
         StringBuilder ret = new StringBuilder();
         for(StringBuilder row:rows){
             ret.append(row);
         }

        return  ret.toString();
    }

    public static void main(String[] args) {
        String str = "ABCDEFG";
        System.out.println(convert2(str, 3));
        //AEiBDFhCG
    }
}
