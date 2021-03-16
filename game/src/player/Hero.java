package player;

import java.util.ArrayList;
import java.util.List;

import dungeon.Map;
import dungeon.Room;

public class Hero extends Character{
	private String username;
	private Room position;
	private List<Room> visitedRooms;
	private int level;
	int xp;
	
	public Hero(String username, Map map) {
		//Default settings for a new player
		super(20, 20, 1, 2);
		Room root = map.getStartingRoom();
		
		this.username = username;
		this.position = root;
		this.visitedRooms = new ArrayList<>();
		this.xp = 0;
		
		//Hero appears in the starting room so it is visited
		this.position.setAsVisited();
		this.visitedRooms.add(root);
		
	}
	
	public String getUsername() {return this.username;}
	public int getLevel() {return this.level;}
	public Room getPosition() {return this.position;}
	
	public void equipItem(Item item) {
		if (item.isEquipable()|| item.isThrowable()) {
			Item temp = this.equippedItem;
			
			this.equippedItem = item;
			item.equipped = true;
			this.inventory.remove(item);
			
			if (temp != null) { //if their was an item previously equipped 
				temp.equipped = false;
				this.inventory.add(temp);
			}
		}
	}
	
	
	public void throwItem(Item item) {
		if (this.inventory.contains(item)) { //if the designated item is in inventory we get rid of it
			this.inventory.remove(item);
		}
		else if (this.equippedItem == item) { //if the designated item is equipped we either get rid of it or use it if throwable
			this.equippedItem = null;
			if (item.isThrowable()) {
				//do item action
			}
		}
		else {
			System.out.println("The item does not exist");
		}
		
	}
	
	public void moveTo(Room room) {
		//Move player to the chosen room if it is accessible
		Room currentRoom = this.position;
		if (currentRoom.getAccessibleRooms().contains(room)) {
			this.position = room;
			this.visitedRooms.add(room);
			room.setAsVisited();
			//HERE ADD EVENTS THAT OCCUR WHEN ENTERING A ROOM
		} else {
			System.out.println("Room is not accessible");
		}
	}
	
	public void moveForward() {
		//Move the hero to the first child room

		Room nextRoom = position.getNextRooms().get(0);
		this.moveTo(nextRoom);
	}
	
	public void moveBackwards() {
		//Move the hero to the previous room
		Room previousRoom = position.getPreviousRoom();
		this.moveTo(previousRoom);
	}
	
	public void backtrack() {
		//Move the hero to the room previously visited
		visitedRooms.remove(visitedRooms.size()-1);
		Room previousRoom = visitedRooms.remove(visitedRooms.size()-1);
		this.moveTo(previousRoom);
	}
}












