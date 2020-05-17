import java.util.Queue;
import java.util.LinkedList;
/**
*���è������������
//�ö��п���ѹ��è���������Ե������������еĳ�����Ե���è�������������е�è...
*@author Hartley
*@version 1.0.0
*/

class  PetQueue
{
	//����---è//��
	public static class Pet {//��Ϊ���ڲ��࣬��main���õ�ʱ����Ҫ��̬
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

	//==============����Ϊ��ӵĴ���================

	//��Pet���װһ��������ڱ�ǽ�����еĴ���
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
		
		public String getEnterPetType()//���ص�ǰ��������==���ڴ���Pet���ж�����
		{
			return this.pet.getPetType();
		}

	}

	//��è������==add\pollAll pollDog pollCat\
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

		//������У�ͨ���ж�pet��Cat����Dog���Ӷ������Ӧ����
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
	
		//ͨ���ȶ�è������-����Ԫ�ص�countֵ���жϵ����ĸ�Ԫ��
		public Pet pollAll()
		{
			if ( !dogQ.isEmpty() && !catQ.isEmpty()  )//è�����о���Ԫ��
			{
				//�Ƚ��ĸ�����è�����еĶ���
				if (dogQ.peek().getCount() < catQ.peek().getCount())
				{
					return dogQ.poll().getPet();//poll���ص���PetEnterQueue����
				}else
				{
					return catQ.poll().getPet();
				}
			}else if (!dogQ.isEmpty())//ֻ�й����зǿ�
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
		//����è����
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
		//����������
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

		//�Ƿ����Ϊ��
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

	//************�������***************
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
			System.out.println("������ǰ���ж���Ԫ�أ�"+queue.pollDog().getPetType());
		}
		*/
		while ( !queue.isEmpty() )
		{
			System.out.println("������ǰ���ж���Ԫ�أ�"+queue.pollAll().getPetType());
		}
		
		//System.out.println("���������ж���Ԫ�أ�"+queue.pollDog().getPetType());
	}
}

