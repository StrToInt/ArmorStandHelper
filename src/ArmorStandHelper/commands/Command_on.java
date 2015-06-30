package ArmorStandHelper.commands;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import ArmorStandHelper.ASHelper;


public class Command_on implements CommandHandler {

	private ASHelper self;
	
	public Command_on(ASHelper plugin) {
		self = plugin;
	}
	
	@Override
	public int getMinArgsLength() {
		return 0;
	}
	
	@Override
	public boolean handleCommand(Player player, String[] args){
		if(self.getPlayersManager().setInventoryOn(player))
			player.sendMessage(ChatColor.GREEN+"Armor Stand Helper turned "+ChatColor.BLUE+"on"+ChatColor.GREEN+" for you.");
		return false;	
	}
}
