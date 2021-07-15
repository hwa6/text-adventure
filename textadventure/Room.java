public class Room {

    private String name;
    private String description;
    private Area[] areas;
    private boolean isRoot;

    public Room(String name, String description, Area[] areas){
        this.name=name;
        this.description=description;
        this.areas=areas;
        isRoot=false;
    }

    public Room(String name, String description, Area[] areas, boolean isRoot){
        this.name=name;
        this.description=description;
        this.areas=areas;
        this.isRoot=isRoot;
    }
    
    public String getName(){
        return name;
    }

    public String getDescription(){
        return description;
    }

    public Area[] getAreas(){
        return areas;
    }

    public Area getArea(int i){
        return areas[i];
    }

    public int numberOfAreas(){
        return areas.length;
    }

    public boolean isRoot(){
        return isRoot;
    }
}
