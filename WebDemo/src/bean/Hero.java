package bean;

/**
 * description：
 *用于CRUD的实体类
     Hero类有id,name,hp,damage等属性。
     并且为每一个属性提供public的getter和setter
 * @author Hartley
 * date： 2020/5/29
 */
public class Hero {
    public int id;
    public String name;
    public float hp;
    public int damage;
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
        this.name = name;
    }
    public float getHp() {
        return hp;
    }
    public void setHp(float hp) {
        this.hp = hp;
    }
    public int getDamage() {
        return damage;
    }
    public void setDamage(int damage) {
        this.damage = damage;
    }

    @Override
    public String toString(){
        return id+"::"+name+"::"+"\r\n";
    }


}
