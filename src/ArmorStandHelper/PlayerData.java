package ArmorStandHelper;


import org.bukkit.Bukkit;
import org.bukkit.entity.ArmorStand;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class PlayerData {
	public double frozenStandValue;
	public double frozenPlayerYaw;
	public Inventory standInventory;
	public boolean inASH;
	public boolean frozenOnAdjust;
	public int InventorySheet;
	public ArmorStand SelectedEntity;
	public ItemStack[] playerInventory;
	
	public PlayerData(){
		inASH=false;
		frozenOnAdjust=false;
		InventorySheet=0;
		standInventory=Bukkit.getServer().createInventory(null,InventoryType.HOPPER,"Head/chestp./leg's/boots/item");
	}

}
