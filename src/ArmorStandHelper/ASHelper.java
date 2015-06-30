/* 
 * SPLEEF CORE
 * v.1.0
 * for minecraft minigame mechanics
 * with arena-style minigames
 * author Evgenii Chuklinov
 * strtoint@mail.ru
 * 
 */
package ArmorStandHelper;

import org.bukkit.plugin.java.JavaPlugin;
import ArmorStandHelper.commands.SetupCommands;

public class ASHelper extends JavaPlugin{
	
//++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++

	private PlayersManager playersDataManager;
	private ActionBarMsg actionBarMsg;
	private GlobalTimer globalTimer;
	
	@Override
	public void onEnable(){//load settings, greetings,
		playersDataManager=new PlayersManager(this);
		actionBarMsg=new ActionBarMsg(this);
		globalTimer=new GlobalTimer(this);
		getCommand("ash").setExecutor(new SetupCommands(this));
		getServer().getPluginManager().registerEvents(new Listeners(this), this);
		getLogger().info("Build Helper Started Succesfully");
	}

	public PlayersManager getPlayersManager() {
		return playersDataManager;
	}

	public ActionBarMsg getActionBarMsg() {
		return actionBarMsg;
	}

	public GlobalTimer getGlobalTimer() {
		return globalTimer;
	}
}
