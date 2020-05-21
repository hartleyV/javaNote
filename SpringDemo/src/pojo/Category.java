package pojo;

import org.springframework.stereotype.Component;

/**
 * pojo(Plain Ordinary Java Object)简单Java对象
 * 通过Spring可以通过调用成员的set方法，对成员进行注入
 *
 * @author Hartley
 * @create 2020/5/21
 */
@Component("c")//test1的方法【3】通过注解表示该类是bean
public class Category {

    private int id;
    private String name = "Colth";

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name ;
    }

}