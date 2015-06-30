package ArmorStandHelper.commands;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import ArmorStandHelper.ASHelper;


public class Command_off implements CommandHandler {

	private ASHelper self;
	
	public Command_off(ASHelper plugin) {
		self = plugin;
	}
	
	@Override
	public int getMinArgsLength() {
		return 0;
	}
	
	@Override
	public boolean handleCommand(Player player, String[] args){
		if(self.getPlayersManager().setInventoryOff(player))
			player.sendMessage(ChatColor.GREEN+"Armor Stand Helper turned "+ChatColor.RED+"off"+ChatColor.GREEN+" for you.");
		return false;	
	}
}
