public class KeyItem extends Item{
    
    private boolean consumed;
    private String description;

    private static final String reset = ConsoleColors.RESET;
    private static final String cyan = ConsoleColors.CYAN_BOLD;
    
    public KeyItem(String name, String description){
        super(name);
        this.description=description;
        consumed=false;
    }

    public void setDescription(String description){
        this.description=description;
    }

    public String toString(){
        return (cyan + getName() + reset);
    }

    
}
