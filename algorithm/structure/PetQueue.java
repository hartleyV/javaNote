package structure;

import java.util.Queue;
import java.util.LinkedList;
/**
*宠物（猫、狗）进队列
//该队列可以压入猫、狗；可以弹出最早进入队列的宠物，可以弹出猫队列最早进入队列的猫...
*@author Hartley
*@version 1.0.0
*/

class  PetQueue
{
	//宠物---猫//狗
	public static class Pet {//因为是内部类，在main调用的时候需要静态
		private String type;

		public Pet(String type) {
			this.type = type;
		}

		public String getPetType() {
			return this.type;
		}
	}

	public static class Dog extends Pet {
		public Dog() {
			super("dog");
		}
	}

	public static class Cat extends Pet {
		public Cat() {
			super("cat");
		}
	}

	//==============以下为添加的代码================

	//将Pet类封装一数据项，用于标记进入队列的次序
	public static class PetEnterQueue
	{
		private Pet pet;
		private long count;

		public PetEnterQueue(Pet pet,long count)
		{
			this.pet = pet;
			this.count = count;
		}

		public void setPet(Pet pet)
		{
			this.pet = pet;
		}
		public void setCount(long count)
		{
			this.count = count;
		}
		public Pet getPet()
		{
			return pet;
		}
		public Long getCount()
		{
			return count;
		}
		
		public String getEnterPetType()//返回当前对象类型==用于传入Pet后判断类型
		{
			return this.pet.getPetType();
		}

	}

	//狗猫队列类==add\pollAll pollDog pollCat\
	public static class DogCatQueue
	{
		private Queue<PetEnterQueue> dogQ;
		private Queue<PetEnterQueue> catQ;
		private long count;

		public DogCatQueue()
		{
			dogQ = new LinkedList<>();
			catQ = new LinkedList<>();
			this.count = 0;
		}

		//进入队列：通过判断pet是Cat还是Dog，从而进入对应队列
		public void add(Pet pet)
		{
			if (pet.getPetType().equals("dog") )
			{
				dogQ.add(new PetEnterQueue(pet,this.count++) );
			}else if (pet.getPetType().equals("cat") )
			{
				catQ.add(new PetEnterQueue(pet,this.count++) ); 
			}else
			{
				throw new RuntimeException("your pet is not dog or cat");
			}
		}
	
		//通过比对猫狗队列-队首元素的count值来判断弹出哪个元素
		public Pet pollAll()
		{
			if ( !dogQ.isEmpty() && !catQ.isEmpty()  )//猫狗队列均有元素
			{
				//比较哪个是在猫狗队列的队首
				if (dogQ.peek().getCount() < catQ.peek().getCount())
				{
					return dogQ.poll().getPet();//poll返回的是PetEnterQueue对象
				}else
				{
					return catQ.poll().getPet();
				}
			}else if (!dogQ.isEmpty())//只有狗队列非空
			{
				return dogQ.poll().getPet();
			}else if (!catQ.isEmpty())
			{
				return catQ.poll().getPet();
			}else
			{
				throw new RuntimeException("all queue is empty");
			}
		}
		//弹出猫队列
		public Cat pollCat()
		{
			if (!catQ.isEmpty() )
			{
				return (Cat)catQ.poll().getPet();
			}else
			{
				throw new RuntimeException("cat queue is empty");
			}
		}
		//弹出狗队列
		public Dog pollDog()
		{
			if (!dogQ.isEmpty() )
			{
				return (Dog)dogQ.poll().getPet();
			}else
			{
				throw new RuntimeException("cat queue is empty");
			}
		}

		//是否队列为空
		public boolean isEmpty()
		{
			return catQ.isEmpty() && dogQ.isEmpty();
		}
		public boolean isDogEmpty()
		{
			return dogQ.isEmpty();
		}
		public boolean isCatEmpty()
		{
			return catQ.isEmpty();
		}
	}

	//************程序入口***************
	public static void main(String[] args) 
	{
		DogCatQueue queue = new DogCatQueue();
		
		Pet dog1 = new Dog();
		Pet cat1 = new Cat();
		Pet dog2 = new Dog();
		Pet cat2 = new Cat();
		Pet dog3 = new Dog();
		Pet cat3 = new Cat();

		queue.add(dog1);
		queue.add(cat1);
		queue.add(cat3);
		queue.add(cat2);
		queue.add(dog2);
		queue.add(dog3);
		/*
		while ( !queue.isDogEmpty() )
		{
			System.out.println("弹出当前队列队首元素："+queue.pollDog().getPetType());
		}
		*/
		while ( !queue.isEmpty() )
		{
			System.out.println("弹出当前队列队首元素："+queue.pollAll().getPetType());
		}
		
		//System.out.println("弹出狗队列队首元素："+queue.pollDog().getPetType());
	}
}

