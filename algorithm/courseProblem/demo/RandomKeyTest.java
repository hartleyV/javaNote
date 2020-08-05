package algorithm.courseProblem.demo;

import java.util.HashMap;
import java.util.Random;
/**
*设计一个结构：--时间复杂度O(1)
		insert(key) 添加元素不重
		delete(key)
		getRandom() 等概率获取key
*@author Hartley
*@version 1.0.0
*/

class  RandomKeyTest
{
	//************程序入口***************
	public static void main(String[] args) 
	{
		RandomStructure<String> rand = new RandomStructure<>();
		rand.insert("Ninja");
		rand.insert("Atom");
		rand.insert("Three");
		rand.insert("Joker");
		rand.insert("Atom");

		System.out.println(rand.getRandom() );
		System.out.println(rand.getRandom() );
		System.out.println(rand.getRandom() );
		System.out.println(rand.getRandom() );
	}
}

//该自定义结构
class RandomStructure<K>
{
	private HashMap<K,Integer> keyIndexMap ;//用于insert
	private HashMap<Integer,K> indexKeyMap ;//用于获取随机key（通过index）
	private int index;

	public RandomStructure()
	{
		keyIndexMap = new HashMap<>();
		indexKeyMap = new HashMap<>();
		index = 0;
	}

	//添加元素
	public void insert(K key)
	{
		if (!keyIndexMap.containsKey(key) )//如果键值key的map中没有新添加的key
		{
			keyIndexMap.put(key,index);//map中没有key的时候返回null
			indexKeyMap.put(index , key);
			index++;
		}//重复则不添加~		
	}

	//删除元素
	public void remove(K key)
	{
		if (keyIndexMap.containsKey(key) )//首先结构中需要有该key
		{
			Integer deleteIndex = keyIndexMap.get(key);//获取对应key的index
			K lastKey = indexKeyMap.get(--index);//获取末尾元素，并减index

			keyIndexMap.remove(key);//key-index 删除相应key元素
			keyIndexMap.put(lastKey,deleteIndex);//并把末尾key的index设置为删除key的index
			
			indexKeyMap.remove(index);//index-key 删除末尾index元素	
			indexKeyMap.put(deleteIndex,lastKey);
		}
	}

	//等概率随机获取元素
	public K getRandom()
	{
		Random ran = new Random();
		int randomIndex = ran.nextInt(index);//包含index
		if (indexKeyMap.containsKey(randomIndex) )
		{
			return indexKeyMap.get(randomIndex);
		}
		return null;

	}
}