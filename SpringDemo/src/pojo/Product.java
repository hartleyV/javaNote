package pojo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author Hartley
 * @create 2020/5/21
 */
@Component("p")//test1的方法【3】通过注解表示该类是bean
public class Product {
    private String name = "Nike";//通过
    private long money;

    @Autowired//test1的方法【2】【3】用于Spring注入对象的注解
    private Category category;

    public Product(){}
    public Product(String name){
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name + " shoes";
    }

    public long getMoney() {
        return money;
    }

    public void setMoney(long money) {
        this.money = money;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }
}
