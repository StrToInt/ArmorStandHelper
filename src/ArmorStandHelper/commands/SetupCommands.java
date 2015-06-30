package ArmorStandHelper.commands;

import java.util.Arrays;
import java.util.HashMap;

import net.md_5.bungee.api.ChatColor;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import ArmorStandHelper.ASHelper;

public class SetupCommands implements CommandExecutor {

	private	ASHelper self;

	private HashMap<String, CommandHandler> commandHandlers = new HashMap<String, CommandHandler>();

	public SetupCommands(ASHelper plugin) {
		self=plugin;
		commandHandlers.put("on",new Command_on(self));//показать тип арены
		commandHandlers.put("off",new Command_off(self));//показать тип арены
	}
	
	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

		// check permissions
		if (sender instanceof Player) {
			// get command
			if (args.length > 0 && commandHandlers.containsKey(args[0])) {
				CommandHandler commandh = commandHandlers.get(args[0]);
				//check args length
				if (args.length - 1 < commandh.getMinArgsLength()) {
					sender.sendMessage(ChatColor.RED+"Error: no enought arguments");
					return false;
				}
				//execute command
				boolean result = commandh.handleCommand((Player)sender, Arrays.copyOfRange(args, 1, args.length));
				return result;
			}else sender.sendMessage(ChatColor.RED+"Error: input command");
		}
		return false;
	}

}
