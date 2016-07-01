package com.yin.myproject.demo.pattern.decorator.ex1;

/**
 * 具体装饰者三
 *
 */
public class Decorator_two extends Decorator{

	public Decorator_two(Human human) {  
        super(human);  
    }  
  
    public void findClothes() {  
        System.out.println("找到一件D&G。。");  
    }  
  
    public void findTheTarget() {  
        System.out.println("在Map上找到神秘花园和城堡。。");  
    }  
  
    @Override  
    public void wearClothes() {  
        // TODO Auto-generated method stub  
        super.wearClothes();  
        findClothes();  
    }  
  
    @Override  
    public void walkToWhere() {  
        // TODO Auto-generated method stub  
        super.walkToWhere();  
        findTheTarget();  
	}
}
