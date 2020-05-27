package JDBC;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * description：

 *DAO=DataAccess Object
    数据访问对象（符合ORM的思路，把数据库相关操作封装起来的类DAO）
 功能：
    增删改
    查：分页查找，数据总数，数据列表
 代码改进：
    把加载数据库驱动放到DAO类的构造方法（执行一次即可）
    单独把getConnection作为一个方法，便于修改
 * @author Hartley
 * date： 2020/5/27
 */

public class HeroDao implements DAO {

    //构造器
    public HeroDao(){
        try {
            Class.forName("com.mysql.jdbc.Driver");//驱动的初始化
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    //getConnection
    private Connection getConnection() throws SQLException {
        return DriverManager.getConnection("jdbc:mysql://127.0.0.1/firstdb?characterEncoding", "root", "admin");
    }

    @Override
    public void add(Hero hero) {
        String sql = "insert into hero values(null,?,?,?)";
        try(Connection con = getConnection(); PreparedStatement state = con.prepareStatement(sql)){
            con.setAutoCommit(false); //添加事务管理

            state.setString(1,hero.name);
            state.setFloat(2,hero.hp);
            state.setInt(3,hero.damage);
            state.execute();

            ResultSet rs = state.getGeneratedKeys();
            if (rs.next()){//查询id只有一个，所以if即可
                hero.id = rs.getInt(1);
            }

            con.commit();//手动提交
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }


    @Override
    public void delete(int id) {
        try(Connection con = getConnection(); Statement state = con.createStatement()){

            String sql = "delete from hero where id="+id;
            state.execute(sql);
            System.out.println("成功删除了id为"+id+"的数据");
        } catch (SQLException throwables) {
            System.out.println("失败，没能删除了id为"+id+"的数据");
            throwables.printStackTrace();
        }
    }

    @Override
    public void update(Hero hero) {
        String name = hero.name;
        String sql = "update hero set name=? , hp=? , damage=? where id="+hero.id;
        try(Connection con = getConnection(); PreparedStatement state = con.prepareStatement(sql)){
            state.setString(1,hero.name);
            state.setFloat(2,hero.hp);
            state.setInt(3,hero.damage);
            state.execute();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }

    @Override
    public Hero get(int id) {
        try(Connection con = getConnection(); Statement state = con.createStatement()){
            String sql = "select * from hero where id="+id;
            ResultSet rs = state.executeQuery(sql);
            if(rs.next()){
                String name = rs.getString("name");
                Float hp = rs.getFloat("hp");
                int damage = rs.getInt("damage");
                return new Hero(id,name,hp,damage);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Hero> list() {
        try(Connection con = getConnection(); Statement state = con.createStatement()){
            String sql = "select * from hero ";
            ResultSet rs = state.executeQuery(sql);
            List<Hero> heros = new ArrayList<>();
            while(rs.next()){
                int id = rs.getInt(1);
                String name = rs.getString("name");
                Float hp = rs.getFloat("hp");
                int damage = rs.getInt("damage");
                heros.add(new Hero(id,name,hp,damage));
            }
            return heros;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }

    //分页查询
    @Override
    public List<Hero> list(int start, int count) {
        try(Connection con = getConnection(); Statement state = con.createStatement()){

            String sql = "select * from hero limit "+start+","+count;
            ResultSet rs = state.executeQuery(sql);
            List<Hero> heros = new ArrayList<>();
            while(rs.next()){
                int id = rs.getInt(1);
                String name = rs.getString("name");
                Float hp = rs.getFloat("hp");
                int damage = rs.getInt("damage");
                heros.add(new Hero(id,name,hp,damage));
            }
            return heros;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;

    }
}

class TestDao{
    public static void main(String[] args) {
        Hero h = new Hero("张三",25.5f,44);
        HeroDao dao = new HeroDao();
        //dao.add(h);
        //dao.delete(12);
        //Hero gethero = dao.get(14);
        //System.out.println(gethero);
        //dao.update(h);
        System.out.println(dao.list(1,3));
    }
}



