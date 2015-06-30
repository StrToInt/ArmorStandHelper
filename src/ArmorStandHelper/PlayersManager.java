package ArmorStandHelper;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.Effect;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;


public class PlayersManager {
	private HashMap<Player,PlayerData> data=new HashMap<Player,PlayerData>();
	private ItemStack[][] ASHInventory;
	private ASHelper self;
	
	public void deletePlayerData(Player player){
		if(data.keySet().contains(player))
			data.remove(player);
	}
	
	public void createPlayerData(Player player){
		if(!data.keySet().contains(player))
			data.put(player,new PlayerData());
	}

	public void reloadDataForAll(){
		for(Player pent:self.getServer().getOnlinePlayers()){
			createPlayerData(pent);
		}
	}
	
	public boolean inASH(Player player){
		if(data.keySet().contains(player))
			return data.get(player).inASH;
		return false;
	}
	
	public PlayerData getData(Player player){
		if(data.keySet().contains(player))
			return data.get(player);
		return null;
	}
	
	public ItemStack createNamedItem(Material material,String name, List<String> lore){
		ItemStack item=new ItemStack(material);
		ItemMeta im=item.getItemMeta();
		im.setDisplayName(ChatColor.RESET+""+ChatColor.RESET+""+ChatColor.RESET+name);
		im.setLore(lore);
		item.setItemMeta(im);
		return item;
	}
	
	public ItemStack createNamedItem(Material material,short subdata,String name, List<String> lore){
		ItemStack item=new ItemStack(material,1,subdata);
		ItemMeta im=item.getItemMeta();
		im.setDisplayName(ChatColor.RESET+""+ChatColor.RESET+""+ChatColor.RESET+name);
		im.setLore(lore);
		item.setItemMeta(im);
		return item;
	}
	
	public ItemStack createSkullItem(String owner, String name, List<String> lore){
		ItemStack item=new ItemStack(Material.SKULL_ITEM, 1, (short) 3);
		SkullMeta im=(SkullMeta) item.getItemMeta();
		im.setDisplayName(name);
		im.setLore(lore);
		im.setOwner(owner);
		item.setItemMeta(im);
		return item;
	}
	
	public PlayersManager(ASHelper plugin){
		self=plugin;
		ASHInventory=new ItemStack[6][9];
		//common tools
		ASHInventory[0][0]=createSkullItem("MHF_ArrowLeft",ChatColor.GOLD+""+ChatColor.BOLD+"Previous page",null);
		ASHInventory[0][1]=createNamedItem(Material.WEB,ChatColor.GOLD+""+ChatColor.BOLD+"Select",Arrays.asList(ChatColor.GREEN+"Select armor stand for edit",ChatColor.GREEN+"right click on A.Stand to select"));
		ASHInventory[0][2]=createNamedItem(Material.BARRIER,ChatColor.GOLD+""+ChatColor.BOLD+"Toggle invisibility",null);
		ASHInventory[0][3]=createNamedItem(Material.ANVIL,ChatColor.GOLD+""+ChatColor.BOLD+"Toggle gravity",null);
		ASHInventory[0][4]=createNamedItem(Material.STONE_PLATE,ChatColor.GOLD+""+ChatColor.BOLD+"Toggle base plate",null);
		ASHInventory[0][5]=createNamedItem(Material.LEATHER_CHESTPLATE,ChatColor.GOLD+""+ChatColor.BOLD+"Toggle hands",null);
		ASHInventory[0][6]=createNamedItem(Material.ARMOR_STAND,ChatColor.GOLD+""+ChatColor.BOLD+"Toggle size min/norm",null);
		ASHInventory[0][7]=createNamedItem(Material.BEDROCK,ChatColor.GOLD+""+ChatColor.BOLD+"Toggle invulnerability",null);
		ASHInventory[0][8]=createSkullItem("MHF_ArrowRight",ChatColor.GOLD+""+ChatColor.BOLD+"Next page",null);
		//legs move
		ASHInventory[1][0]=createSkullItem("MHF_ArrowLeft",ChatColor.GOLD+""+ChatColor.BOLD+"Previous page",Arrays.asList(ChatColor.GREEN+"Select armor stand for edit",ChatColor.GREEN+"right click on A.Stand to select"));
		ASHInventory[1][1]=createNamedItem(Material.DIAMOND_LEGGINGS,ChatColor.GOLD+""+ChatColor.BOLD+"Right leg "+ChatColor.GREEN+ChatColor.BOLD+"X",Arrays.asList(ChatColor.GREEN+"Select armor stand for edit",ChatColor.GREEN+"right click on A.Stand to select"));
		ASHInventory[1][2]=createNamedItem(Material.DIAMOND_LEGGINGS,ChatColor.GOLD+""+ChatColor.BOLD+"Right leg "+ChatColor.GREEN+ChatColor.BOLD+"Y",Arrays.asList(ChatColor.GREEN+"Select armor stand for edit",ChatColor.GREEN+"right click on A.Stand to select"));
		ASHInventory[1][3]=createNamedItem(Material.DIAMOND_LEGGINGS,ChatColor.GOLD+""+ChatColor.BOLD+"Right leg "+ChatColor.GREEN+ChatColor.BOLD+"Z",Arrays.asList(ChatColor.GREEN+"Select armor stand for edit",ChatColor.GREEN+"right click on A.Stand to select"));
		ASHInventory[1][4]=createNamedItem(Material.GOLD_LEGGINGS,ChatColor.GOLD+""+ChatColor.BOLD+"Left leg "+ChatColor.GREEN+ChatColor.BOLD+"X",Arrays.asList(ChatColor.GREEN+"Select armor stand for edit",ChatColor.GREEN+"right click on A.Stand to select"));
		ASHInventory[1][5]=createNamedItem(Material.GOLD_LEGGINGS,ChatColor.GOLD+""+ChatColor.BOLD+"Left leg "+ChatColor.GREEN+ChatColor.BOLD+"Y",Arrays.asList(ChatColor.GREEN+"Select armor stand for edit",ChatColor.GREEN+"right click on A.Stand to select"));
		ASHInventory[1][6]=createNamedItem(Material.GOLD_LEGGINGS,ChatColor.GOLD+""+ChatColor.BOLD+"Left leg "+ChatColor.GREEN+ChatColor.BOLD+"Z",Arrays.asList(ChatColor.GREEN+"Select armor stand for edit",ChatColor.GREEN+"right click on A.Stand to select"));
		ASHInventory[1][7]=null;
		ASHInventory[1][8]=createSkullItem("MHF_ArrowRight",ChatColor.GOLD+""+ChatColor.BOLD+"Next page",Arrays.asList(ChatColor.GREEN+"Select armor stand for edit",ChatColor.GREEN+"right click on A.Stand to select"));
		//arms move
		ASHInventory[2][0]=createSkullItem("MHF_ArrowLeft",ChatColor.GOLD+""+ChatColor.BOLD+"Previous page",Arrays.asList(ChatColor.GREEN+"Select armor stand for edit",ChatColor.GREEN+"right click on A.Stand to select"));
		ASHInventory[2][1]=createNamedItem(Material.STICK,ChatColor.GOLD+""+ChatColor.BOLD+"Right arm "+ChatColor.GREEN+ChatColor.BOLD+"X",Arrays.asList(ChatColor.GREEN+"Select armor stand for edit",ChatColor.GREEN+"right click on A.Stand to select"));
		ASHInventory[2][2]=createNamedItem(Material.STICK,ChatColor.GOLD+""+ChatColor.BOLD+"Right arm "+ChatColor.GREEN+ChatColor.BOLD+"Y",Arrays.asList(ChatColor.GREEN+"Select armor stand for edit",ChatColor.GREEN+"right click on A.Stand to select"));
		ASHInventory[2][3]=createNamedItem(Material.STICK,ChatColor.GOLD+""+ChatColor.BOLD+"Right arm "+ChatColor.GREEN+ChatColor.BOLD+"Z",Arrays.asList(ChatColor.GREEN+"Select armor stand for edit",ChatColor.GREEN+"right click on A.Stand to select"));
		ASHInventory[2][4]=createNamedItem(Material.BONE,ChatColor.GOLD+""+ChatColor.BOLD+"Left arm "+ChatColor.GREEN+ChatColor.BOLD+"X",Arrays.asList(ChatColor.GREEN+"Select armor stand for edit",ChatColor.GREEN+"right click on A.Stand to select"));
		ASHInventory[2][5]=createNamedItem(Material.BONE,ChatColor.GOLD+""+ChatColor.BOLD+"Left arm "+ChatColor.GREEN+ChatColor.BOLD+"Y",Arrays.asList(ChatColor.GREEN+"Select armor stand for edit",ChatColor.GREEN+"right click on A.Stand to select"));
		ASHInventory[2][6]=createNamedItem(Material.BONE,ChatColor.GOLD+""+ChatColor.BOLD+"Left arm "+ChatColor.GREEN+ChatColor.BOLD+"Z",Arrays.asList(ChatColor.GREEN+"Select armor stand for edit",ChatColor.GREEN+"right click on A.Stand to select"));
		ASHInventory[2][7]=null;
		ASHInventory[2][8]=createSkullItem("MHF_ArrowRight",ChatColor.GOLD+""+ChatColor.BOLD+"Next page",Arrays.asList(ChatColor.GREEN+"Select armor stand for edit",ChatColor.GREEN+"right click on A.Stand to select"));
		//body and head move
		ASHInventory[3][0]=createSkullItem("MHF_ArrowLeft",ChatColor.GOLD+""+ChatColor.BOLD+"Previous page",Arrays.asList(ChatColor.GREEN+"Select armor stand for edit",ChatColor.GREEN+"right click on A.Stand to select"));
		ASHInventory[3][1]=createNamedItem(Material.DIAMOND_CHESTPLATE,ChatColor.GOLD+""+ChatColor.BOLD+"Body "+ChatColor.GREEN+ChatColor.BOLD+"X",Arrays.asList(ChatColor.GREEN+"Select armor stand for edit",ChatColor.GREEN+"right click on A.Stand to select"));
		ASHInventory[3][2]=createNamedItem(Material.DIAMOND_CHESTPLATE,ChatColor.GOLD+""+ChatColor.BOLD+"Body "+ChatColor.GREEN+ChatColor.BOLD+"Y",Arrays.asList(ChatColor.GREEN+"Select armor stand for edit",ChatColor.GREEN+"right click on A.Stand to select"));
		ASHInventory[3][3]=createNamedItem(Material.DIAMOND_CHESTPLATE,ChatColor.GOLD+""+ChatColor.BOLD+"Body "+ChatColor.GREEN+ChatColor.BOLD+"Z",Arrays.asList(ChatColor.GREEN+"Select armor stand for edit",ChatColor.GREEN+"right click on A.Stand to select"));
		ASHInventory[3][4]=createNamedItem(Material.SKULL_ITEM,(short)3,ChatColor.GOLD+""+ChatColor.BOLD+"Head "+ChatColor.GREEN+ChatColor.BOLD+"X",Arrays.asList(ChatColor.GREEN+"Select armor stand for edit",ChatColor.GREEN+"right click on A.Stand to select"));
		ASHInventory[3][5]=createNamedItem(Material.SKULL_ITEM,(short)3,ChatColor.GOLD+""+ChatColor.BOLD+"Head "+ChatColor.GREEN+ChatColor.BOLD+"Y",Arrays.asList(ChatColor.GREEN+"Select armor stand for edit",ChatColor.GREEN+"right click on A.Stand to select"));
		ASHInventory[3][6]=createNamedItem(Material.SKULL_ITEM,(short)3,ChatColor.GOLD+""+ChatColor.BOLD+"Head "+ChatColor.GREEN+ChatColor.BOLD+"Z",Arrays.asList(ChatColor.GREEN+"Select armor stand for edit",ChatColor.GREEN+"right click on A.Stand to select"));
		ASHInventory[3][7]=null;
		ASHInventory[3][8]=createSkullItem("MHF_ArrowRight",ChatColor.GOLD+""+ChatColor.BOLD+"Next page",Arrays.asList(ChatColor.GREEN+"Select armor stand for edit",ChatColor.GREEN+"right click on A.Stand to select"));
		//rotate move reset
		ASHInventory[4][0]=createSkullItem("MHF_ArrowLeft",ChatColor.GOLD+""+ChatColor.BOLD+"Previous page",Arrays.asList(ChatColor.GREEN+"Select armor stand for edit",ChatColor.GREEN+"right click on A.Stand to select"));
		ASHInventory[4][1]=createNamedItem(Material.CLAY_BALL,ChatColor.GOLD+""+ChatColor.BOLD+"Rotate armor stand",null);
		ASHInventory[4][2]=createNamedItem(Material.INK_SACK,(short)8,ChatColor.GOLD+""+ChatColor.BOLD+"Move "+ChatColor.GREEN+ChatColor.BOLD+"X",null);
		ASHInventory[4][3]=createNamedItem(Material.INK_SACK,(short)9,ChatColor.GOLD+""+ChatColor.BOLD+"Move "+ChatColor.GREEN+ChatColor.BOLD+"Y",null);
		ASHInventory[4][4]=createNamedItem(Material.INK_SACK,(short)10,ChatColor.GOLD+""+ChatColor.BOLD+"Move "+ChatColor.GREEN+ChatColor.BOLD+"Z",null);
		ASHInventory[4][5]=null;
		ASHInventory[4][6]=null;
		ASHInventory[4][7]=null;
		ASHInventory[4][8]=createSkullItem("MHF_ArrowRight",ChatColor.GOLD+""+ChatColor.BOLD+"Next page",Arrays.asList(ChatColor.GREEN+"Select armor stand for edit",ChatColor.GREEN+"right click on A.Stand to select"));
		//others
		ASHInventory[5][0]=createSkullItem("MHF_ArrowLeft",ChatColor.GOLD+""+ChatColor.BOLD+"Previous page",null);
		ASHInventory[5][1]=createNamedItem(Material.CHEST,ChatColor.GOLD+""+ChatColor.BOLD+"Edit inventory",null);
		ASHInventory[5][2]=createNamedItem(Material.ARMOR_STAND,ChatColor.GOLD+""+ChatColor.BOLD+"Right hand 90° rotate",Arrays.asList(ChatColor.GREEN+"Set the angle of right hand to 90 degree",ChatColor.GREEN+"for block items in hand placed by world axes"));
		ASHInventory[5][3]=createNamedItem(Material.ARMOR_STAND,ChatColor.GOLD+""+ChatColor.BOLD+"Right hand 45° rotate",Arrays.asList(ChatColor.GREEN+"Set the angle of right hand to 45 degree",ChatColor.GREEN+"for block items in hand placed by 45deg of world axes"));
		ASHInventory[5][4]=createNamedItem(Material.ARMOR_STAND,ChatColor.GOLD+""+ChatColor.BOLD+"Reset rotation angle",null);
		ASHInventory[5][5]=createNamedItem(Material.MONSTER_EGG,ChatColor.GOLD+""+ChatColor.BOLD+"Clone and select",null);
		ASHInventory[5][6]=createNamedItem(Material.FLOWER_POT_ITEM,ChatColor.GOLD+""+ChatColor.BOLD+"Reset all angles ",null);
		ASHInventory[5][7]=createNamedItem(Material.ENDER_CHEST,ChatColor.GOLD+""+ChatColor.BOLD+"Toggle equipment lock",null);
		ASHInventory[5][8]=createSkullItem("MHF_ArrowRight",ChatColor.GOLD+""+ChatColor.BOLD+"Next page",null);

		reloadDataForAll();
	}
	
	public void cloneAndSelectStand(Player player){
		if(data.get(player).SelectedEntity!=null){
			ArmorStand old=data.get(player).SelectedEntity;
			ArmorStand as=(ArmorStand) player.getWorld().spawnEntity(old.getLocation(), EntityType.ARMOR_STAND);
			
			as.setVisible(old.isVisible());
			as.setArms(old.hasArms());
			as.setBasePlate(old.hasBasePlate());
			as.setGravity(old.hasGravity());
			
			as.setBodyPose(old.getBodyPose());
			as.setHeadPose(old.getHeadPose());
			
			as.setLeftArmPose(old.getLeftArmPose());
			as.setRightArmPose(old.getRightArmPose());
			
			as.setLeftLegPose(old.getLeftLegPose());
			as.setRightLegPose(old.getRightLegPose());
			
			as.setHelmet(old.getHelmet().clone());
			as.setChestplate(old.getChestplate().clone());
			as.setLeggings(old.getLeggings().clone());
			as.setBoots(old.getBoots().clone());
			as.setItemInHand(old.getItemInHand().clone());
			
			as.setSmall(old.isSmall());
			
			data.get(player).SelectedEntity=as;
			
			as.teleport(old.getLocation());
			
			player.sendMessage(ChatColor.GREEN+"Armor stand closed with UUID: "+ChatColor.BLUE+as.getUniqueId()+ChatColor.GREEN+" for you.");
		}
	}
	
	public void SetItemEnchants(ItemStack item,boolean enchanted){
		if(item!=null){
			ItemMeta im=item.getItemMeta();
			if(enchanted){
				im.addEnchant(Enchantment.THORNS, 0, false);
				im.addItemFlags(ItemFlag.HIDE_ENCHANTS);
			}else{
				im.removeEnchant(Enchantment.THORNS);
				im.removeItemFlags(ItemFlag.HIDE_ENCHANTS);
			}
			item.setItemMeta(im);
		}
	}
	
	public void RefreshInventoryFromEntitySettings(Player player){
		ArmorStand as=data.get(player).SelectedEntity;
		if(as!=null){
			switch(data.get(player).InventorySheet){
				case 0:{
					SetItemEnchants(player.getInventory().getItem(2),!as.isVisible());
					SetItemEnchants(player.getInventory().getItem(3),as.hasGravity());
					SetItemEnchants(player.getInventory().getItem(4),as.hasBasePlate());
					SetItemEnchants(player.getInventory().getItem(5),as.hasArms());
					SetItemEnchants(player.getInventory().getItem(6),as.isSmall());
				}break;
				case 1:{
					SetItemEnchants(player.getInventory().getItem(player.getInventory().getHeldItemSlot()),data.get(player).frozenOnAdjust);
				}break;
			}
		}
	}
	
	public void SetInventorySheet(Player player, int sheet){
		final String headcap="\u2588\u2588\u2588\u2588";
		if(data.keySet().contains(player)){
			data.get(player).InventorySheet=sheet;
			for(int j=0;j<=8;j++){
				ItemStack is=ASHInventory[data.get(player).InventorySheet][j];
				player.getInventory().setItem(j, is);
			}
			//player.getInventory().setContents(ASHInventory[data.get(player).InventorySheet]);
			if(sheet==0)RefreshInventoryFromEntitySettings(player);
				switch(sheet){
					case 0:{
						self.getActionBarMsg().actionBarMsg(player, ChatColor.LIGHT_PURPLE+headcap+ChatColor.YELLOW+ChatColor.BOLD+" 1. Common tools "+ChatColor.LIGHT_PURPLE+headcap);
					}break;
					case 1:{
						self.getActionBarMsg().actionBarMsg(player, ChatColor.LIGHT_PURPLE+headcap+ChatColor.YELLOW+ChatColor.BOLD+" 2. Legs edit"+ChatColor.LIGHT_PURPLE+headcap);
					}break;
					case 2:{
						self.getActionBarMsg().actionBarMsg(player, ChatColor.LIGHT_PURPLE+headcap+ChatColor.YELLOW+ChatColor.BOLD+" 3. Arms edit "+ChatColor.LIGHT_PURPLE+headcap);
					}break;
					case 3:{
						self.getActionBarMsg().actionBarMsg(player, ChatColor.LIGHT_PURPLE+headcap+ChatColor.YELLOW+ChatColor.BOLD+" 4. Body and head edit "+ChatColor.LIGHT_PURPLE+headcap);
					}break;
					case 4:{
						self.getActionBarMsg().actionBarMsg(player, ChatColor.LIGHT_PURPLE+headcap+ChatColor.YELLOW+ChatColor.BOLD+" 5. Trimm angle/pos(use pitch), move"+ChatColor.LIGHT_PURPLE+headcap);
					}break;
					case 5:{
						self.getActionBarMsg().actionBarMsg(player, ChatColor.LIGHT_PURPLE+headcap+ChatColor.YELLOW+ChatColor.BOLD+" 6. Other "+ChatColor.LIGHT_PURPLE+headcap);
					}break;
				}
		}
		
	}

	public boolean setInventoryOn(Player player){
		if(data.keySet().contains(player)){
			if(data.get(player).inASH==false){
				data.get(player).inASH=true;
				data.get(player).playerInventory=player.getInventory().getContents();
				SetInventorySheet(player,0);
				return true;
			}
		}
		return false;
	}
	
	public boolean setInventoryOff(Player player){
		if(data.keySet().contains(player)){
			if(data.get(player).inASH==true){
				player.getInventory().setContents(data.get(player).playerInventory);
				data.get(player).playerInventory=null;
				data.get(player).inASH=false;
				return true;
			}
		}
		return false;
	}

	public void Action_SelectArmorStand(Player player, Entity entity){
		if(entity!=null){
			if(entity.getType()==EntityType.ARMOR_STAND){
				data.get(player).SelectedEntity=(ArmorStand)entity;
				player.sendMessage(ChatColor.GREEN+"Selected armor stand with UUID: "+ChatColor.BLUE+entity.getUniqueId()+ChatColor.GREEN+" for you.");
				player.playSound(player.getLocation(), Sound.VILLAGER_YES, 1, 1);
				player.spigot().playEffect(entity.getLocation().clone().add(0,1.5f,0), Effect.FLAME, 0, 0, 0, 0, 0, 0.1f, 50, 5);
				RefreshInventoryFromEntitySettings(player);
			}else{
				player.sendMessage(ChatColor.RED+"Error: it is not Armor Stand!");
				player.playSound(player.getLocation(), Sound.VILLAGER_NO, 1, 1);
			}
		}
	}	

	public void refreshInventoryFromStand(Player player, ArmorStand stand){
		data.get(player).standInventory.setItem(0,stand.getHelmet());	
		data.get(player).standInventory.setItem(1,stand.getChestplate());	
		data.get(player).standInventory.setItem(2,stand.getLeggings());	
		data.get(player).standInventory.setItem(3,stand.getBoots());	
		data.get(player).standInventory.setItem(4,stand.getItemInHand());		
	}
	
	public void refreshInventoryInStand(Player player, Inventory inventory){
		if(inventory.equals(data.get(player).standInventory)){
			data.get(player).SelectedEntity.setHelmet(inventory.getItem(0));
			data.get(player).SelectedEntity.setChestplate(inventory.getItem(1));
			data.get(player).SelectedEntity.setLeggings(inventory.getItem(2));
			data.get(player).SelectedEntity.setBoots(inventory.getItem(3));
			data.get(player).SelectedEntity.setItemInHand(inventory.getItem(4));
		}		
	}
	
	public void Action_EditInventory(Player player){
		if(data.get(player).SelectedEntity!=null){
			ArmorStand as=data.get(player).SelectedEntity;
			refreshInventoryFromStand(player, as);
			player.openInventory(data.get(player).standInventory);
		}
	}	
	
	public boolean processActionOnChangeItemSlot(Player player, int newSlot){
		if(data.keySet().contains(player))
			if(data.get(player).inASH){
				if(data.get(player).frozenOnAdjust==true){
					return true;
				}
				int is=data.get(player).InventorySheet;
				if(newSlot==0){
					is--;
					player.getInventory().setHeldItemSlot(7);
				}
				if(newSlot==8){
					is++;
					player.getInventory().setHeldItemSlot(1);
				}
				if(is>5)is=0;
				if(is<0)is=5;
				if((newSlot==0)||(newSlot==8)){
					SetInventorySheet(player,is);
					return true;
				}
			}
		return false;
	}
	
	public void processActionOnInteract(Player player, Entity entity){
		if(data.keySet().contains(player))
			if(data.get(player).inASH){
				if(data.get(player).frozenOnAdjust){
					data.get(player).frozenOnAdjust=false;
					RefreshInventoryFromEntitySettings(player);
					return;
				}
				switch(data.get(player).InventorySheet){
				case 0:{//common tools
					switch(player.getInventory().getHeldItemSlot()){
						case 1:{
							//select tool
							Action_SelectArmorStand(player,entity);
						}break;
						case 2:{
							//toggle visible
							Action_ToggleInvisibility(player);
						}break;
						case 3:{
							//toggle gravity
							Action_ToggleGravity(player);
						}break;
						case 4:{
							//toggle gravity
							Action_ToggleBasePlate(player);
						}break;
						case 5:{
							//toggle gravity
							Action_ToggleHands(player);
						}break;
						case 6:{
							//toggle gravity
							Action_ToggleSize(player);
						}break;
					}
				}break;
				case 1:{//legs adjust
					if(data.get(player).SelectedEntity!=null)
						if(player.getItemInHand().getType()!=Material.AIR){
							double yaw=0;
							switch(player.getInventory().getHeldItemSlot()){
								case 1:yaw=data.get(player).SelectedEntity.getRightLegPose().getX();break;
								case 2:yaw=data.get(player).SelectedEntity.getRightLegPose().getY();break;
								case 3:yaw=data.get(player).SelectedEntity.getRightLegPose().getZ();break;
								case 4:yaw=data.get(player).SelectedEntity.getLeftLegPose().getX();break;
								case 5:yaw=data.get(player).SelectedEntity.getLeftLegPose().getY();break;
								case 6:yaw=data.get(player).SelectedEntity.getLeftLegPose().getZ();break;
							}
							data.get(player).frozenPlayerYaw=player.getLocation().getYaw();
							data.get(player).frozenStandValue=yaw;
							data.get(player).frozenOnAdjust=true;
							RefreshInventoryFromEntitySettings(player);
						}
				}break;
				case 2:{//arms adjust
					if(data.get(player).SelectedEntity!=null)
						if(player.getItemInHand().getType()!=Material.AIR){
							double yaw=0;
							switch(player.getInventory().getHeldItemSlot()){
								case 1:yaw=data.get(player).SelectedEntity.getRightArmPose().getX();break;
								case 2:yaw=data.get(player).SelectedEntity.getRightArmPose().getY();break;
								case 3:yaw=data.get(player).SelectedEntity.getRightArmPose().getZ();break;
								case 4:yaw=data.get(player).SelectedEntity.getLeftArmPose().getX();break;
								case 5:yaw=data.get(player).SelectedEntity.getLeftArmPose().getY();break;
								case 6:yaw=data.get(player).SelectedEntity.getLeftArmPose().getZ();break;
							}
							data.get(player).frozenPlayerYaw=player.getLocation().getYaw();
							data.get(player).frozenStandValue=yaw;
							data.get(player).frozenOnAdjust=true;
							RefreshInventoryFromEntitySettings(player);
						}
				}break;
				case 3:{//bode/head adjust
					if(data.get(player).SelectedEntity!=null)
						if(player.getItemInHand().getType()!=Material.AIR){
							double yaw=0;
							switch(player.getInventory().getHeldItemSlot()){
								case 1:yaw=data.get(player).SelectedEntity.getBodyPose().getX();break;
								case 2:yaw=data.get(player).SelectedEntity.getBodyPose().getY();break;
								case 3:yaw=data.get(player).SelectedEntity.getBodyPose().getZ();break;
								case 4:yaw=data.get(player).SelectedEntity.getHeadPose().getX();break;
								case 5:yaw=data.get(player).SelectedEntity.getHeadPose().getY();break;
								case 6:yaw=data.get(player).SelectedEntity.getHeadPose().getZ();break;
							}
							data.get(player).frozenPlayerYaw=player.getLocation().getYaw();
							data.get(player).frozenStandValue=yaw;
							data.get(player).frozenOnAdjust=true;
							RefreshInventoryFromEntitySettings(player);
						}
				}break;
				case 4:{//rotation moving reset
					if(data.get(player).SelectedEntity!=null)
						if(player.getItemInHand().getType()!=Material.AIR){
							double yaw=0;
							switch(player.getInventory().getHeldItemSlot()){
								case 1:yaw=data.get(player).SelectedEntity.getLocation().getYaw();break;
								case 2:yaw=data.get(player).SelectedEntity.getLocation().getX();break;
								case 3:yaw=data.get(player).SelectedEntity.getLocation().getY();break;
								case 4:yaw=data.get(player).SelectedEntity.getLocation().getZ();break;
							}
							data.get(player).frozenPlayerYaw=player.getLocation().getPitch();
							data.get(player).frozenStandValue=yaw;
							data.get(player).frozenOnAdjust=true;
							RefreshInventoryFromEntitySettings(player);
						}
				}break;
				case 5:{//other tools
					switch(player.getInventory().getHeldItemSlot()){
						case 1:{
							//edit inventory
							Action_EditInventory(player);
						}break;
						case 5:{
							//clone
							cloneAndSelectStand(player);
						}break;
					}
				}break;
				}
			}
		
	}
	
	public void processWhenPlayerFrozen(Player player,PlayerData data){
		double yaw=0;
		switch(data.InventorySheet){
			case 1:case 2:case 3:{
				yaw=(player.getLocation().getYaw()-data.frozenPlayerYaw);
				yaw=data.frozenStandValue-(yaw * Math.PI) / 180;
				double converted=(yaw*180/Math.PI);
				while (converted>360)converted-=360;
				self.getActionBarMsg().actionBarMsg(player, ChatColor.GOLD+""+ChatColor.BOLD+"Angle: "+ChatColor.YELLOW+ChatColor.BOLD+new java.text.DecimalFormat("0.00").format(converted).replaceAll(",", "."));
			}break;
			case 4:{
				switch(player.getInventory().getHeldItemSlot()){
					case 1:yaw=(player.getLocation().getPitch()-data.frozenPlayerYaw);break;
					case 2:case 3:case 4:{
						yaw=player.getLocation().getPitch()/50-data.frozenPlayerYaw/50;
					}break;
				}
				self.getActionBarMsg().actionBarMsg(player, ChatColor.GOLD+""+ChatColor.BOLD+"Value: "+ChatColor.YELLOW+ChatColor.BOLD+new java.text.DecimalFormat("0.00").format(yaw).replaceAll(",", "."));
				yaw=data.frozenStandValue-yaw;
			}break;
		}
		switch(data.InventorySheet){
			case 1:{//leg adjust
				switch(player.getInventory().getHeldItemSlot()){
					case 1:data.SelectedEntity.setRightLegPose(data.SelectedEntity.getRightLegPose().setX(yaw));break;
					case 2:data.SelectedEntity.setRightLegPose(data.SelectedEntity.getRightLegPose().setY(yaw));break;
					case 3:data.SelectedEntity.setRightLegPose(data.SelectedEntity.getRightLegPose().setZ(yaw));break;
					case 4:data.SelectedEntity.setLeftLegPose(data.SelectedEntity.getLeftLegPose().setX(yaw));break;
					case 5:data.SelectedEntity.setLeftLegPose(data.SelectedEntity.getLeftLegPose().setY(yaw));break;
					case 6:data.SelectedEntity.setLeftLegPose(data.SelectedEntity.getLeftLegPose().setZ(yaw));break;
				}
			}break;
			case 2:{//arms adjust
				switch(player.getInventory().getHeldItemSlot()){
					case 1:data.SelectedEntity.setRightArmPose(data.SelectedEntity.getRightArmPose().setX(yaw));break;
					case 2:data.SelectedEntity.setRightArmPose(data.SelectedEntity.getRightArmPose().setY(yaw));break;
					case 3:data.SelectedEntity.setRightArmPose(data.SelectedEntity.getRightArmPose().setZ(yaw));break;
					case 4:data.SelectedEntity.setLeftArmPose(data.SelectedEntity.getLeftArmPose().setX(yaw));break;
					case 5:data.SelectedEntity.setLeftArmPose(data.SelectedEntity.getLeftArmPose().setY(yaw));break;
					case 6:data.SelectedEntity.setLeftArmPose(data.SelectedEntity.getLeftArmPose().setZ(yaw));break;
				}
			}break;
			case 3:{//body/head adjust
				switch(player.getInventory().getHeldItemSlot()){
					case 1:data.SelectedEntity.setBodyPose(data.SelectedEntity.getBodyPose().setX(yaw));break;
					case 2:data.SelectedEntity.setBodyPose(data.SelectedEntity.getBodyPose().setY(yaw));break;
					case 3:data.SelectedEntity.setBodyPose(data.SelectedEntity.getBodyPose().setZ(yaw));break;
					case 4:data.SelectedEntity.setHeadPose(data.SelectedEntity.getHeadPose().setX(yaw));break;
					case 5:data.SelectedEntity.setHeadPose(data.SelectedEntity.getHeadPose().setY(yaw));break;
					case 6:data.SelectedEntity.setHeadPose(data.SelectedEntity.getHeadPose().setZ(yaw));break;
				}
			}break;
			case 4:{//body/head adjust
				switch(player.getInventory().getHeldItemSlot()){
					case 1:{//rotate
						Location l=data.SelectedEntity.getLocation();
						l.setYaw((float) yaw);
						data.SelectedEntity.teleport(l);
					}break;
					case 2:{//move x y z
						Location l=data.SelectedEntity.getLocation();
						l.setX(yaw);
						data.SelectedEntity.teleport(l);
					}break;
					case 3:{//move x y z
						Location l=data.SelectedEntity.getLocation();
						l.setY(yaw);
						data.SelectedEntity.teleport(l);
					}break;
					case 4:{//move x y z
						Location l=data.SelectedEntity.getLocation();
						l.setZ(yaw);
						data.SelectedEntity.teleport(l);
					}break;
				}
			}break;
		}
		
	}
	
//toggles-------------------------------------------------------------------------
	
	public void Action_ToggleInvisibility(Player player){
		if(data.get(player).SelectedEntity!=null){
			ArmorStand as=data.get(player).SelectedEntity;
			if(as.isVisible())
				as.setVisible(false);
			else 
				as.setVisible(true);
			RefreshInventoryFromEntitySettings(player);
		}
	}	
	
	public void Action_ToggleGravity(Player player){
		if(data.get(player).SelectedEntity!=null){
			ArmorStand as=data.get(player).SelectedEntity;
			if(as.hasGravity())
				as.setGravity(false);
			else 
				as.setGravity(true);
			RefreshInventoryFromEntitySettings(player);
		}
	}
	
	public void Action_ToggleBasePlate(Player player){
		if(data.get(player).SelectedEntity!=null){
			ArmorStand as=data.get(player).SelectedEntity;
			if(as.hasBasePlate())
				as.setBasePlate(false);
			else 
				as.setBasePlate(true);
			RefreshInventoryFromEntitySettings(player);
		}
	}
	
	public void Action_ToggleHands(Player player){
		if(data.get(player).SelectedEntity!=null){
			ArmorStand as=data.get(player).SelectedEntity;
			if(as.hasArms())
				as.setArms(false);
			else 
				as.setArms(true);
			RefreshInventoryFromEntitySettings(player);
		}
	}
	
	public void Action_ToggleSize(Player player){
		if(data.get(player).SelectedEntity!=null){
			ArmorStand as=data.get(player).SelectedEntity;
			if(as.isSmall())
				as.setSmall(false);
			else 
				as.setSmall(true);
			RefreshInventoryFromEntitySettings(player);
		}
	}

}
