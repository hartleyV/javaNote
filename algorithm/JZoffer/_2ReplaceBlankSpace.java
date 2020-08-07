package algorithm.JZoffer;

/**
 * description：
    把字符串每个空格替换为 %20
 * @author Hartley
 * date： 2020/8/7
 */
public class _2ReplaceBlankSpace {

    //不使用replace函数,使用额外数组append
    public String replaceSpace(StringBuffer str) {
        StringBuffer helper = new StringBuffer();
        String mark = "%20";
        for (int i=0;i<str.length();i++){
            if (str.charAt(i)==' '){
                helper.append(mark);
            }else{
                helper.append(str.charAt(i));
            }
        }
        return new String(helper);
    }

    //使用了replace函数
    public String replaceSpace2(StringBuffer str) {
        String mark = "%20";
        for(int i=0;i<str.length();i++){
            if(str.charAt(i)==' '){
                str.replace(i,i+1,mark);
            }
        }
        return new String(str);
    }
}
