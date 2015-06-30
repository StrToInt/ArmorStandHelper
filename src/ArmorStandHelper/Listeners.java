package ArmorStandHelper;

import org.bukkit.Material;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractAtEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerItemHeldEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.inventory.InventoryView;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;

public class Listeners implements Listener{
	
	private ASHelper self;
	
	public Listeners(ASHelper plugin) {
		self = plugin;
	}
	
	// cancell block break for gm -0
	@EventHandler(priority = EventPriority.NORMAL,ignoreCancelled=false)
	public void onPlayerInteract(PlayerInteractEvent e) {
		if(self.getPlayersManager().inASH(e.getPlayer())){
			if((e.getAction()==Action.RIGHT_CLICK_BLOCK)||(e.getAction()==Action.RIGHT_CLICK_AIR)){
				self.getPlayersManager().processActionOnInteract(e.getPlayer(),null);
				ItemStack im=e.getPlayer().getItemInHand();
				if(im!=null){
					if(im.getType()==Material.ARMOR_STAND)
						return;
				}
			}
		}
	}

	// cancell block break for gm -0
	@EventHandler(priority = EventPriority.NORMAL,ignoreCancelled=false)
	public void onPlayerInteract(BlockPlaceEvent e) {
		if(self.getPlayersManager().inASH(e.getPlayer())){
			self.getPlayersManager().processActionOnInteract(e.getPlayer(),null);
			e.setCancelled(true);
		}
	}

	// inventory
	@EventHandler(priority = EventPriority.NORMAL)
	public void onInvetoryEvent(InventoryClickEvent e){
		if(e.getWhoClicked() instanceof Player)
			if(self.getPlayersManager().inASH((Player)e.getWhoClicked())){
				InventoryView iw=e.getWhoClicked().getOpenInventory();
				if(iw!=null){
					if(iw.getTopInventory()!=null){
						new BukkitRunnable(){
							@Override
							public void run() {
								self.getPlayersManager().refreshInventoryInStand((Player)e.getWhoClicked(),iw.getTopInventory());
								this.cancel();
							}
						}.runTaskLater(self, 2);
					}
				}
			}
	}	

	@EventHandler
	public void onJoinServer(PlayerJoinEvent e){
		self.getPlayersManager().createPlayerData(e.getPlayer());
	}

	@EventHandler
	public void onQuitServer(PlayerQuitEvent e){
		self.getPlayersManager().deletePlayerData(e.getPlayer());
	}
	
	//interact with entity
	@EventHandler(priority = EventPriority.MONITOR, ignoreCancelled=false)
	public void onInteractWithArmorStand(PlayerInteractAtEntityEvent e){
		if(self.getPlayersManager().inASH(e.getPlayer()))
			if(e.getRightClicked()instanceof ArmorStand){
				self.getPlayersManager().processActionOnInteract(e.getPlayer(),e.getRightClicked());
				e.setCancelled(true);
			}
	}
	
	//change item in hand
	@EventHandler(priority = EventPriority.MONITOR)
	public void onChangeItemInHand(PlayerItemHeldEvent e){
		if(self.getPlayersManager().inASH(e.getPlayer()))
			if(self.getPlayersManager().processActionOnChangeItemSlot(e.getPlayer(), e.getNewSlot())){
				e.setCancelled(true);
			}
	}
	
}
