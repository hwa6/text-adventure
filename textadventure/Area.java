public class Area {

    private String name;
    private String description;
    private String damageDescription;
    private String unlockDescription;
    private String lockPassword;
    private Integer damage;
    private boolean firstVisit;
    private boolean isRoot;
    private boolean isLocked;
    private boolean isHidden;
    private Item item;
    private Area[] subAreas;
    private KeyItem lockKey;
    private RiddlePuzzle riddlePuzzle;
  
  
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

    //area with no Item that is root
    public Area(String name, String description, boolean isRoot){
        firstVisit=true;
        this.name=name;
        this.description=description;
        this.isRoot=isRoot;
    }

    //area with no Item that contains "sub areas"
    public Area(String name, String description, Area[] subAreas){
        firstVisit=true;
        this.name=name;
        this.description=description;
        this.subAreas=subAreas;
    }

    //area with no Item that is root and contains "sub areas"
    public Area(String name, String description, boolean isRoot, Area[] subAreas){
        firstVisit=true;
        this.name=name;
        this.description=description;
        this.subAreas=subAreas;
        this.isRoot=isRoot;
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

    //area with no Item that has String password
    public Area(String name, String unlockDescription, String lockPassword){
        isLocked=true;
        firstVisit=false;
        this.name=name;
        this.description="It's locked. We need to find the password.";
        this.lockPassword=lockPassword;
        this.unlockDescription=unlockDescription;
    }

    //area with no Item that has a puzzle, isHidden
    public Area(String name, String description, RiddlePuzzle puzzle, boolean isHidden){
        this.isHidden=isHidden;
        firstVisit=true;
        this.name=name;
        this.description=description;
        this.riddlePuzzle=puzzle;
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

    //area with Item that is root
    public Area(String name, String description, Item item, boolean isRoot){
        firstVisit=true;
        this.name=name;
        this.item=item;
        this.description=description;
        this.isRoot=isRoot;
    }

    //area with Item that damages player, break nav
    public Area(String name, String description, Item item, int damage, String damageDescription, boolean isRoot){
        firstVisit=true;
        this.name=name;
        this.description=description;
        this.item=item;
        this.damage=damage;
        this.damageDescription=damageDescription;
        this.isRoot=isRoot;
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

    public boolean isRoot(){
        return isRoot;
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

    public boolean hasRiddlePuzzle(){
        if(riddlePuzzle==null){
            return false;
        }
        else{
            return true;
        }
    }


    public RiddlePuzzle getRiddlePuzzle(){
        return riddlePuzzle;
    }

    public void unhide(){
        System.out.println("A new area, "+ name +" appeared!");
        isHidden=false;
    }

    public boolean isHidden(){
        return isHidden;
    }

}
