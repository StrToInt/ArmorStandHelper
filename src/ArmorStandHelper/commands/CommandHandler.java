package ArmorStandHelper.commands;

import org.bukkit.entity.Player;


public interface CommandHandler{

	public int getMinArgsLength();

	public boolean handleCommand(Player player, String[] args);

}