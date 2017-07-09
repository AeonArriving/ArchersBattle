package net.mcmhsj.archersbattle;

import java.sql.DriverManager;
import java.sql.SQLException;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import net.mcmhsj.archersbattle.commands.AdminCommands;
import net.mcmhsj.archersbattle.commands.Commands;
import net.mcmhsj.archersbattle.managers.ConfigManager;
import net.mcmhsj.archersbattle.managers.Database;

public class Main extends JavaPlugin
{
	private static Main instance;
	public static Main getInstance()
	{
		return instance;
	}
	
	public void onEnable()
	{
		Bukkit.getConsoleSender().sendMessage("��6��lDreamCraft ��7>>> ��a�����ִ���ս���ڼ�����..");
		Bukkit.getPluginCommand("ab").setExecutor(new Commands());
		Bukkit.getPluginCommand("abadmin").setExecutor(new AdminCommands());
		Database db=null;
		try {
			db=new Database(DriverManager.getConnection(ConfigManager.getConfigManager().getMySQLAddress(),
					ConfigManager.getConfigManager().getMySQLUser(), 
					ConfigManager.getConfigManager().getMySQLPassword()));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		if(!db.init())
		{
			Bukkit.getConsoleSender().sendMessage("��6��lDreamCraft ��7>>> ��c���ݿ��ʼ��ʧ�ܣ�ֹͣ����");
			setEnabled(false);
		}
		instance=this;
	}
	
	
}
