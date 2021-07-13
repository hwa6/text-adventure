import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Scanner;




public class Game {

    static Scanner keyboard = new Scanner(System.in);

    private static final String reset = ConsoleColors.RESET;
    private static final String white = ConsoleColors.WHITE_BOLD;
    private static final String blue = ConsoleColors.BLUE_BOLD;
    private static final String purple = ConsoleColors.PURPLE_BOLD;
    private static final String green = ConsoleColors.GREEN_BOLD;

    public static void main(String[] args) {
        Player character=createCharacter();
        character.incrementCP();
        sigepKitchen(character);
        character.incrementCP();
        System.out.println(character.getInventory());
        }


        public static Player createCharacter(){
            
            System.out.println(green + "\n" + "Hello! Welcome to a very special escape the room challenge! Please enter your name." + "\n"+ purple);
            String name = keyboard.nextLine();
            Player character = new Player(name);
            System.out.println(green + "\n"+"Howdy " + character.getName() + ". Did I hear that right?" + "\n" );
            //MAKE A DO WHILE LOOP TO MAKE SURE THE NAME IS CORRECT
            consoleMessage("standard", "Alright then " + character.getName() + ". It's a real pleasure to meet you. The name's Henry. We got ourselves in a bit of a situation here. ");
            consoleMessage("standard","It appears we're trapped inside my subconscious. I've been known to have the odd dream or two, and this one seems to be no exception. We're gonna have to find our way out of here.");
            consoleMessage("standard","This adventure is going to take us through some weird places. We'll have to solve some puzzles along the way. And we can always take on a challenge for help if we get stuck.");
            consoleMessage("standard","Remember to keep an eye on your health. I sure hope we can find some health replenishers, or else we're going to have to start from the very beginning.");
            consoleMessage("standard","Are you writing all of this down? Seriously, get a pen and paper. I can't figure out how to format this terminal. Oh boy, I can feel it starting. Here we gooooo!");
            return character;
        }


        public static void sigepKitchen(Player character){
            HealItem pierogies = new HealItem("Pierogies",15);
            HealItem boxedWine = new HealItem("Roland's Franzia",50);
            character.addToInventory(pierogies);

            Area cabinetUnderSink = new Area("Cabinet Under the Sink", "I wonder what's in there.", boxedWine);
            Area murkyWater = new Area("Murky Sink Water", "It doesn't look safe...", 10, "Ouch! The water is toxic!");
            Area[] cloggedSinkSubArea = new Area[] {cabinetUnderSink, murkyWater};

            Area fridge = new Area("Fridge", "Weird leftovers", pierogies);
            Area cloggedSink = new Area("Clogged Sink", "Dark and murky", cloggedSinkSubArea);
            Area foodPantry = new Area ("Food Pantry", "Locked!", pierogies, true);
            Area[] sigepKitchen = new Area[] {fridge, cloggedSink, foodPantry};

            int choice;
           
            consoleMessage("standard","\n"+"Oh baby. Roland's kitchen.");
            consoleMessage("standard","It's... disgusting. In a charming way! Spoons all over the floor, the sink is clogged, just like good old times. Maybe I'm on pots today.");
            consoleMessage("standard","We're positioned like we just came out of Roland's bathroom. What were we doing in there? Anyway, the only way out is through the big red doors on the other side of the kitchen.");
            consoleMessage("standard","But... of course they're locked. Time to find a key. Where should be start?");
            consoleMessage("standard","When given a few options like below, simply type in your option. Depending on how lazy I was when I refactored this, there may be some light error handling. It might break the whole program. Who knows."+"\n"+ "Try to be accurate. ");
            choice = threeOptions("Keep Playing", "Quit", "Quit, but with more emphasis", character);
            if(choice == 2 || choice == 3){
                gameOver();
            }
            consoleMessage("standard","Excellent choice! You can also get your health at any point by typing " + white+ "Check Health" + green + ", or inventory with "+ white + "Check Inventory");
            consoleMessage("standard", "Feel free to try that now!");
            oneOption("Try out the new commands, then when you're good to go type", "Ready", character);
            navigator("Roland's Kitchen", "There are utensils on the floor. It's gross, what more do you want me to say?", sigepKitchen, character);


            
            
        }

        public static void gameOver(){
            System.out.println(white + "You Died! Game Over"+ reset);
            System.exit(0);
        }

        public static void delay(String delay){
            int standard = 200;
            //int standard=2500;
            if (delay.equalsIgnoreCase("standard")){
                try {Thread.sleep(standard); } catch(InterruptedException error) {}
            }
            else if (delay.equalsIgnoreCase("half")){
                try {Thread.sleep(standard/2); } catch(InterruptedException error) {}
            }
            else if (delay.equalsIgnoreCase("double")){
                try {Thread.sleep(standard*2); } catch(InterruptedException error) {}
            }
            else{
                System.out.println("IF YOU'RE READING THIS HENRY MESSED UP");
            }


        }

        public static void consoleMessage(String delay, String message){
            System.out.println(green + message + purple);
            delay(delay);
        }

        public static void consoleMessage(String delay, String message, String choice){
            System.out.println(green + message + blue+ " <"+ white + choice + blue + ">"+ purple);
            delay(delay);
        }

        public static void consoleOption(String choice) {
            System.out.print(blue+ " <"+ white + choice + blue + ">"+ purple);
        }

        public static void navigator(String roomName, String roomDescription, Area[] room, Player character){
            boolean typo=true;
            boolean roomComplete=false;
            while (roomComplete==false){
                consoleMessage("standard", "You are in", roomName);
                consoleMessage("standard", roomDescription);
                consoleMessage("standard", "Where in the room would you like to go?");
                for(int i=0; i<room.length; i++){
                    consoleOption(room[i].getName());
                }
                System.out.println();
                String input=keyboard.nextLine();
                for(int i=0; i<room.length; i++){
                    if(input.equalsIgnoreCase(room[i].getName())){
                        roomComplete=areaNavigator(room[i], character);
                        typo=false;
                    } 
                }
                if(typo==true){
                consoleMessage("standard", "I'm sorry I couldn't hear what you said. Try again?");
                input=keyboard.nextLine();
                }
            }
        }

        public static boolean areaNavigator(Area area, Player character){
            if(area.hasSubAreas()==true){
                Area goBack = new Area("Go Back", "", true);
                Area[] subAreasWithBackOption = new Area[area.getSubAreas().length+1]; //{area.getSubAreas()}
                for(int i=0; i<subAreasWithBackOption.length-1; i++){
                    subAreasWithBackOption[i]= (Area)Array.get(area.getSubAreas(), i);
                }
                subAreasWithBackOption[subAreasWithBackOption.length-1]=goBack;
                navigator(area.getName(), area.getDescription(), subAreasWithBackOption, character);
            }
            consoleMessage("standard", "Area: ", area.getName());
            consoleMessage("standard", area.getDescription());
            if(area.beenVisited()==false){
                if(area.hasItem()==true){
                    consoleMessage("standard", area.getItem().getMessage());
                    consoleMessage("standard", area.getItem().toString());
                    character.addToInventory(area.getItem());
                }
                area.visit();
            }
            if(area.isLast()==true){
                //consoleMessage("standard", "Room Completed!");
                return true;
            }
            if(area.hasDamage()==true){
                consoleMessage("standard", area.getDamageDescription());
                character.damageHP(area.getDamage());
            }
            consoleMessage("standard", "Ready to go back?");
            String input=keyboard.nextLine();
            while(input.equalsIgnoreCase("Yes")==false && input.equalsIgnoreCase("Y")==false && input.equalsIgnoreCase("Ready")==false){
                consoleMessage("standard", "Okay you just let me know when you are. Ready yet?");
                input=keyboard.nextLine();
            }
            return false;
        }

        public static boolean subAreaNavigator(){
            return false;
        }

        public static int threeOptions(String string1, String string2, String string3, Player character){
            System.out.println(green+ "Would you like to " + blue+ "<"+ white + string1 + blue + ">"+ " <"+ white + string2 + blue +">" + green + " or "+ blue +"<"+ white + string3 + blue + ">" + purple);
            String input = keyboard.nextLine();
            while(input.equalsIgnoreCase(string1) == false && input.equalsIgnoreCase(string2) == false && input.equalsIgnoreCase(string3)==false){
                if(input.equalsIgnoreCase("Check Health")){
                    System.out.println(white + character.getHP()+ reset);
                }
                else if(input.equalsIgnoreCase("Check Inventory")){
                    System.out.println(reset + character.getInventory() + reset);
                }
                else{
                    consoleMessage("half","I'm sorry I couldn't hear what you said. Try again?");
                }
                System.out.println(green+ "Would you like to " + blue+ "<"+ white + string1 + blue + ">"+ " <"+ white + string2 + blue +">" + green + " or "+ blue +"<"+ white + string3 + blue + ">"+purple);
                input=keyboard.nextLine();
            }
            if (input.equalsIgnoreCase(string1)){
                return 1;
            }
            else if(input.equalsIgnoreCase(string2)){
                return 2;
            }
            else if(input.equalsIgnoreCase(string3)){
                return 3;
            }
            else{
                return 0;
            }
        }
        public static int oneOption(String opener, String desiredWord, Player character){
            System.out.println(green+ opener + " " + blue+ "<"+ white + desiredWord + blue + ">" + purple);
            String input = keyboard.nextLine();
            while(input.equalsIgnoreCase(desiredWord)==false){
                if(input.equalsIgnoreCase("Check Health")){
                    System.out.println(white + character.getHP()+ reset);
                }
                else if(input.equalsIgnoreCase("Check Inventory")){
                    System.out.println(reset + character.getInventory() + reset);
                }
                else{
                    consoleMessage("half","I'm sorry I couldn't hear what you said. Try again?");
                }
                System.out.println(green+ opener + " "+ blue+ "<"+ white + desiredWord + blue + ">" + purple);
                input=keyboard.nextLine();
            }
            if(input.equalsIgnoreCase(desiredWord)){
                return 1;
            }
            else{
                return 0;
            }


        }
}


