package me.zhanshi123.archersbattle.skill;

import org.bukkit.inventory.ItemStack;

public abstract class Skill {
	String skillName;
	ItemStack show,selector;
	/**
	*
	* @param ������
	* @param ʵ��ѡ�����ʾ����Ʒ
	* @param ����ѡ��������ʾ����Ʒ 
	* 
	* @author Soldier 
	*/
	public Skill(String skillName,ItemStack show,ItemStack selector)
	{
		this.show=show;
		this.skillName=skillName;
		this.selector=show;
	}
	
	public String getName()
	{
		return skillName;
	}
	public ItemStack getShow()
	{
		return show;
	}
	public ItemStack getSelector()
	{
		return selector;
	}
}
