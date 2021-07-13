public class Item {

    private String name;
    private String message;
    private boolean consumed;

    public Item(String name){
        consumed=false;
        this.name=name;
        this.message="Item found!";
    }

    public Item(String name, String message){
        consumed=false;
        this.name=name;
        this.message=message;
    }

    public void consumeItem(){
        consumed=true;
    }

    public String getName(){
        return name;
    }

    public String getMessage(){
        return message;
    }

}
