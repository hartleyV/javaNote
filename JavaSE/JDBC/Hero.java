package JDBC;

/**
 * description：
 *【ORM】=Object Relationship Database Mapping
    数据库的一条数据对应一个对象
 * @author Hartley
 * date： 2020/5/27
 */
public class Hero {
     int id;
     String name;
     float hp;
     int damage;
     public Hero(String name,float hp,int damage ){
         this.name = name;
         this.hp = hp;
         this.damage = damage;
    }
    public Hero(int id,String name,float hp,int damage ){
         this.id = id;
        this.name = name;
        this.hp = hp;
        this.damage = damage;
    }

    @Override
    public String toString(){
         return  id+":"+name+":"+hp+":"+damage;
    }
}
