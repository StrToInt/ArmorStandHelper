package ArmorStandHelper;

import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

public class GlobalTimer extends BukkitRunnable{
	
	private ASHelper self;
	
	public GlobalTimer(ASHelper plugin) {
		self = plugin;
		this.runTaskTimer(self, 1, 5);
	}

	@Override
	public void run() {
		for(Player pent:self.getServer().getOnlinePlayers()){
			PlayerData data=self.getPlayersManager().getData(pent);
			if(data!=null){
				if(data.frozenOnAdjust){
					self.getPlayersManager().processWhenPlayerFrozen(pent,data);
				}
			}
		}
		
	}
	
}
