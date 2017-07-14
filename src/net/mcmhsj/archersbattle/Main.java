package net.mcmhsj.archersbattle;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import net.mcmhsj.archersbattle.commands.AdminCommands;
import net.mcmhsj.archersbattle.commands.Commands;
import net.mcmhsj.archersbattle.commands.inventory.InventoryListener;
import net.mcmhsj.archersbattle.commands.inventory.PlayerListener;
import net.mcmhsj.archersbattle.commands.inventory.WorldListener;
import net.mcmhsj.archersbattle.managers.ArenaManager;
import net.mcmhsj.archersbattle.managers.ConfigManager;
import net.mcmhsj.archersbattle.managers.Database;
import net.mcmhsj.archersbattle.managers.WeaponItemManager;
import net.mcmhsj.archersbattle.messages.Messages;

//Ŀǰ���е�BUG
//���뾺���������������

public class Main extends JavaPlugin
{
	private static Main instance;
	public static Main getInstance()
	{
		return instance;
	}
	public void loadArenas()
	{
		File f=new File(getDataFolder()+"/arenas/");
		for(File a:f.listFiles())
		{
			if(a.getName().endsWith(".yml"))
			{
				FileConfiguration config=new YamlConfiguration();
				try {
					config.load(a);
					Arena arena=new Arena(config.getString("world"));
					List<Location> locations=new ArrayList<Location>();
					Set<String> keys=config.getKeys(false);
					for(String x:keys)
					{
						if(x.startsWith("loc"))
						{
							locations.add((Location)config.get(x));
						}
					}
					arena.setSpawnLocations(locations);
					ArenaManager.addArena(arena);
				} catch (IOException | InvalidConfigurationException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	public void onEnable()
	{
		Bukkit.getConsoleSender().sendMessage("��6��lArchersBattle ��7>>> ��a�����ִ���ս���ڼ�����..");
		instance=this;
		Bukkit.getPluginCommand("ab").setExecutor(new Commands());
		Bukkit.getPluginCommand("abadmin").setExecutor(new AdminCommands());
		Bukkit.getPluginManager().registerEvents(new InventoryListener(), this);
		Bukkit.getPluginManager().registerEvents(new WorldListener(), this);
		Bukkit.getPluginManager().registerEvents(new PlayerListener(), this);

		loadArenas();
		WeaponItemManager.init();
		new Messages();
		new ConfigManager(this);
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
			Bukkit.getConsoleSender().sendMessage("��6��lArchersBattle ��7>>> ��c���ݿ��ʼ��ʧ�ܣ�ֹͣ����");
			setEnabled(false);
		}
	}
	public void onDisable()
	{
		List<Arena> arenas=ArenaManager.getArenas();
		for(Arena arena:arenas)
		{
			arena.saveFile();
		}
		Bukkit.getConsoleSender().sendMessage("��6��lArchersBattle ��7>>> ��a�������������!");

	}
	
}
