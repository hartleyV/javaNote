import java.util.HashMap;
import java.util.Random;
/**
*���һ���ṹ��--ʱ�临�Ӷ�O(1)
		insert(key) ���Ԫ�ز���
		delete(key)
		getRandom() �ȸ��ʻ�ȡkey
*@author Hartley
*@version 1.0.0
*/

class  RandomKeyTest
{
	//************�������***************
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

//���Զ���ṹ
class RandomStructure<K>
{
	private HashMap<K,Integer> keyIndexMap ;//����insert
	private HashMap<Integer,K> indexKeyMap ;//���ڻ�ȡ���key��ͨ��index��
	private int index;

	public RandomStructure()
	{
		keyIndexMap = new HashMap<>();
		indexKeyMap = new HashMap<>();
		index = 0;
	}

	//���Ԫ��
	public void insert(K key)
	{
		if (!keyIndexMap.containsKey(key) )//�����ֵkey��map��û������ӵ�key
		{
			keyIndexMap.put(key,index);//map��û��key��ʱ�򷵻�null
			indexKeyMap.put(index , key);
			index++;
		}//�ظ������~		
	}

	//ɾ��Ԫ��
	public void remove(K key)
	{
		if (keyIndexMap.containsKey(key) )//���Ƚṹ����Ҫ�и�key
		{
			Integer deleteIndex = keyIndexMap.get(key);//��ȡ��Ӧkey��index
			K lastKey = indexKeyMap.get(--index);//��ȡĩβԪ�أ�����index

			keyIndexMap.remove(key);//key-index ɾ����ӦkeyԪ��
			keyIndexMap.put(lastKey,deleteIndex);//����ĩβkey��index����Ϊɾ��key��index
			
			indexKeyMap.remove(index);//index-key ɾ��ĩβindexԪ��	
			indexKeyMap.put(deleteIndex,lastKey);
		}
	}

	//�ȸ��������ȡԪ��
	public K getRandom()
	{
		Random ran = new Random();
		int randomIndex = ran.nextInt(index);//����index
		if (indexKeyMap.containsKey(randomIndex) )
		{
			return indexKeyMap.get(randomIndex);
		}
		return null;

	}
}