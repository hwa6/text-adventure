public class Area {

    private String name;
    private String description;
    private String damageDescription;
    private Integer damage;
    private boolean firstVisit;
    private boolean isLast;
    private Item item;
    private Area[] subAreas;
  
    //constructor
    //area with no Item
    public Area(String name, String description){
        firstVisit=true;
        this.name=name;
        this.description=description;
    }
    //area with Item
    public Area(String name, String description, Item item){
        firstVisit=true;
        this.name=name;
        this.item=item;
        this.description=description;
    }

    //area with np Item that is "last area"
    public Area(String name, String description, boolean isLast){
        firstVisit=true;
        this.name=name;
        this.description=description;
        this.isLast=isLast;
    }
    //area with Item that is "last area"
    public Area(String name, String description, Item item, boolean isLast){
        firstVisit=true;
        this.name=name;
        this.item=item;
        this.description=description;
        this.isLast=isLast;
    }

      //area with no Iteam that damages player
      public Area(String name, String description, int damage, String damageDescription){
        firstVisit=true;
        this.name=name;
        this.description=description;
        this.damage=damage;
        this.damageDescription=damageDescription;
    }

    //area with no Item that contains "sub areas"
    public Area(String name, String description, Area[] subAreas){
        firstVisit=true;
        this.name=name;
        this.description=description;
        this.subAreas=subAreas;
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

    public boolean beenVisited(){
        if(firstVisit==true){
            return false;
        }
        else{
            return true;
        }
    }

    public boolean hasItem(){
        if(item == null){
            return false;
        }
        else{
            return true;
        }
    }

    public boolean isLast(){
        return isLast;
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

}
