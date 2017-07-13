package net.mcmhsj.archersbattle.messages;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import net.mcmhsj.archersbattle.Main;

public class Messages {
	FileConfiguration config;
	public static String prefix,NotInArena,LeavedArena,AlreadyInArena,JoinedArena,AreanNotFound,ArenaAlreadyExists,ArenaCreated;
	public Messages()
	{
		File f=new File(Main.getInstance().getDataFolder(),"messages.yml");
		config=new YamlConfiguration();
		boolean first=!f.exists();
		if(first)
		{
			try {
				f.createNewFile();
			} catch (Exception e) {e.printStackTrace();}
		}
		try {
			config.load(new InputStreamReader(new FileInputStream(f),"UTF-8"));
		} catch (Exception e) {
			e.printStackTrace();
		}
		if(first)
		{
			config.set("prefix", "��6��lArchersBattle ��7>>> ��r");
			config.set("commands.AlreadyInArena","��c���Ѿ���һ����Ϸ����");
			config.set("commands.JoinedArena","��a�ɹ����뾺����%arena%");
			config.set("commands.AreanNotFound", "��c������������");
			config.set("commands.ArenaAlreadyExists", "��c�������Ѵ���");
			config.set("commands.ArenaCreated", "��a�����������ɹ�");
			config.set("commands.NotInArena", "��c�㲻�ھ�������");
			config.set("commands.LeavedArena", "��a�ɹ��˳��˾�����");
			try {
				config.save(f);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		prefix=config.getString("prefix");
		AlreadyInArena=config.getString("commands.AlreadyInArena");
		JoinedArena=config.getString("commands.JoinedArena");
		AreanNotFound=config.getString("commands.AreanNotFound");
		ArenaAlreadyExists=config.getString("commands.ArenaAlreadyExists");
		ArenaCreated=config.getString("commands.ArenaCreated");
		NotInArena=config.getString("commands.NotInArena");
		LeavedArena=config.getString("commands.LeavedArena");
	}
}
