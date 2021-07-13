import java.util.ArrayList;
import java.util.Arrays;

public class Player {

    private String name;
    private int HP;
    private int maxHP=100;
    private int CP;
    private ArrayList<Item> inventory;

    private static final String reset = ConsoleColors.RESET;
    private static final String red = ConsoleColors.RED_BOLD;
    private static final String cyan = ConsoleColors.CYAN_BOLD;
  
    //constructor
    public Player(String name){
        HP=maxHP;
        CP=0;
        this.name=name;
        inventory= new ArrayList<Item>();
    }

    //getter setters
    public String getName(){
        return name;
    }

    public int getHP(){
        return HP;
    }
    public void damageHP(int HP){
        this.HP-=HP;
    }

    public int getCP(){
        return CP;
    }

    public void incrementCP(){
        CP++;
    }

    public void addToInventory(Item item){
        inventory.add(item);
    }

    public void removeFromInventory(Item item){
        inventory.remove(item);
    }

    public String getInventory(){
        String[] stringArray = new String[inventory.size()];
        for(int i=0; i<inventory.size(); i++){
            if(inventory.get(i) instanceof HealItem){
                HealItem item = (HealItem)inventory.get(i);
                stringArray[i]= item.toString();
            }
            if(inventory.get(i) instanceof KeyItem){
                KeyItem item = (KeyItem)inventory.get(i);
                stringArray[i] = item.toString();
            }
            
        }
        return Arrays.toString(stringArray);
        
    
    }


}
