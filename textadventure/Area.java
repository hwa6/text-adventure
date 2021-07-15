public class Area {

    private String name;
    private String description;
    private String damageDescription;
    private String unlockDescription;
    private Integer damage;
    private boolean firstVisit;
    private boolean breakNav;
    private Item item;
    private Area[] subAreas;
    private boolean isLocked;
    private KeyItem lockKey;
    private String lockPassword;
  
    //constructor
    //area with no Item
    public Area(String name, String description){
        firstVisit=true;
        this.name=name;
        this.description=description;
    }

    //area with no Item that damages player
    public Area(String name, String description, int damage, String damageDescription){
        firstVisit=true;
        this.name=name;
        this.description=description;
        this.damage=damage;
        this.damageDescription=damageDescription;
    }

    //area with no Item that breaks nav
    public Area(String name, String description, boolean breakNav){
        firstVisit=true;
        this.name=name;
        this.description=description;
        this.breakNav=breakNav;
    }

    //area with no Item that contains "sub areas"
    public Area(String name, String description, Area[] subAreas){
        firstVisit=true;
        this.name=name;
        this.description=description;
        this.subAreas=subAreas;
    }

    //area with no Item that has KeyItem password
    public Area(String name, String unlockDescription, KeyItem lockKey){
        isLocked=true;
        firstVisit=false;
        this.name=name;
        this.description="It's locked. We need to find a key.";
        this.lockKey=lockKey;
        this.unlockDescription=unlockDescription;
    }

    public void unlockRoom(KeyItem lockKey){
        if(this.lockKey.equals(lockKey)){
            System.out.println("Aha! The " + lockKey.getName() + " unlocked the area.");
            firstVisit=true;
            isLocked=false;
            this.description=this.unlockDescription;
        }
        else{
            System.out.println("You don't have the correct item.");
        }
    }

    

    //area with no Item that has String password
    public Area(String name, String unlockDescription, String lockPassword){
        isLocked=true;
        firstVisit=false;
        this.name=name;
        this.description="It's locked. We need to find the password.";
        this.lockPassword=lockPassword;
        this.unlockDescription=unlockDescription;
    }

    public void unlockRoom(String lockPassword){
        if(this.lockPassword.equals(lockPassword)){
            System.out.println("Aha! The password was "+ lockPassword + ". Area unlocked.");
            firstVisit=true;
            isLocked=false;
            this.description=this.unlockDescription;
        }
        else{
            System.out.println("Nope, that password didn't work.");
        }
    }

     //area with Item
     public Area(String name, String description, Item item){
        firstVisit=true;
        this.name=name;
        this.item=item;
        this.description=description;
    }

    //area with Item that damages player
    public Area(String name, String description, Item item, int damage, String damageDescription){
        firstVisit=true;
        this.name=name;
        this.description=description;
        this.item=item;
        this.damage=damage;
        this.damageDescription=damageDescription;
    }    

    //area with Item that breaks nav
    public Area(String name, String description, Item item, boolean breakNav){
        firstVisit=true;
        this.name=name;
        this.item=item;
        this.description=description;
        this.breakNav=breakNav;
    }

    //area with Item that damages player, break nav
    public Area(String name, String description, Item item, int damage, String damageDescription, boolean breakNav){
        firstVisit=true;
        this.name=name;
        this.description=description;
        this.item=item;
        this.damage=damage;
        this.damageDescription=damageDescription;
        this.breakNav=breakNav;
    }



    

    //getter setters
    public String getName(){
        return name;
    }

    public String getDescription(){
        return description;
    }

    public Item getItem(){
        return item;
    }

    public void visit(){
        firstVisit=false;
    }

    public boolean firstVisit(){
       return firstVisit;
    }

    public boolean hasItem(){
        if(item == null){
            return false;
        }
        else{
            return true;
        }
    }

    public boolean breakNav(){
        return breakNav;
    }

    public boolean hasSubAreas(){
        if(subAreas == null){
            return false;
        }
        else{
            return true;
        }
    }
    
    public Area[] getSubAreas(){
        return subAreas;
    }

    public boolean hasDamage(){
        if(damage == null){
            return false;
        }
        else{
            return true;
        }
    }

    public int getDamage(){
        return damage;
    }

    public String getDamageDescription(){
        return damageDescription;
    }

    public boolean isLocked(){
        return isLocked;
    }

    public boolean hasPasswordLock(){
        if(lockPassword==null){
            return false;
        }
        else{
            return true;
        }
    }

}
