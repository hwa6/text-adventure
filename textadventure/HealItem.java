public class HealItem extends Item{
    
    
    private int healPower;
    private boolean consumed;

    private static final String reset = ConsoleColors.RESET;
    private static final String red = ConsoleColors.RED_BOLD;
    
    public HealItem(String name, int healPower){
        super(name);
        consumed=false;
        this.healPower=healPower;
    }

    public int getHealPower(){
        return healPower;
    }

    public String toString(){
        return (red + getName() + "(+" + getHealPower() + " HP)" + reset);
    }
}