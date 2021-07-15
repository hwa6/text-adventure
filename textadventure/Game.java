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

        //methods related to game function

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
            KeyItem rustedKey = new KeyItem("Rusted Key", "It's been in the water for a while. There's flakes of red paint still visible under the rust and grime.");
            character.addToInventory(rustedKey);

            Area cabinetUnderSink = new Area("Cabinet Under the Sink", "I wonder what's in there.", boxedWine);
            Area murkyWater = new Area("Murky Sink Water", "It doesn't look safe...", rustedKey, 10, "Ouch! The water is toxic!", true);
            Area[] cloggedSinkSubArea = new Area[] {cabinetUnderSink, murkyWater};

            Area fridge = new Area("Fridge", "Weird leftovers", pierogies);
            Area cloggedSink = new Area("Clogged Sink", "Dark and murky", cloggedSinkSubArea);
            Area foodPantry = new Area ("Food Pantry", "The key to the whole puzzle", "password123");
            Area fourthRoom = new Area ("Fourth Room", "It has cool stuff inside", rustedKey);
            Area[] sigepKitchen = new Area[] {foodPantry, cloggedSink, fridge, fourthRoom};
            Room rolandsKitchen = new Room("Roland's Kitchen","There are utensils on the floor. It's gross, what more do you want me to say?",sigepKitchen, true);



            int choice;
           
            consoleMessage("standard","\n"+"Oh baby. Roland's kitchen.");
            consoleMessage("standard","It's... disgusting. In a charming way! Spoons all over the floor, the sink is clogged, just like good old times. Maybe I'm on pots today.");
            consoleMessage("standard","We're positioned like we just came out of Roland's bathroom. What were we doing in there? Anyway, the only way out is through the big red doors on the other side of the kitchen.");
            consoleMessage("standard","But... of course they're locked. Time to find a key. Where should be start?");
            consoleMessage("standard","When given a few options like below, simply type in your option. Depending on how lazy I was when I refactored this, there may be some light error handling. It might break the whole program. Who knows."+"\n"+ "Try to be accurate. ");
            /*choice = threeOptions("Keep Playing", "Quit", "Quit, but with more emphasis", character);
            if(choice == 2 || choice == 3){
                gameOver();
            }*/
            consoleMessage("standard","Excellent choice! You can also get your health at any point by asking for " + white+ "Health" + green + ", or inventory with "+ white + "Inventory");
            consoleMessage("standard", "Feel free to try that now!");
            //oneOption("Try out the new commands, then when you're good to go type", "Ready", character);
            navigator(rolandsKitchen, character, false);


            
            
        }

        public static void gameOver(){
            System.out.println(white + "You Died! Game Over"+ reset);
            System.exit(0);
        }


        //methods related to input formatting/printing to console

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

        public static void consoleMessage(String delay, String message1, String choice, String message2){
            System.out.println(green + message1 + blue+ " <"+ white + choice + blue + ">"+ green + message2 + purple);
            delay(delay);
        }

        public static void consoleOption(String choice) {
            System.out.print(blue+ " <"+ white + choice + blue + ">"+ purple);
        }

        public static void handleNonRoomName(String input, Player character){
            String lowercaseInput = input.toLowerCase();
            if(input.contains("inventory")){
                System.out.println(character.getInventory());
                }
            if(input.contains("health")){
                System.out.println(character.getHP());
                }
            else{
                consoleMessage("standard", "I'm sorry I couldn't hear what you said. Try again?");
                }             

        }

        //methods related to navigation

        public static void navigator(Room room,  Player character, boolean recursive){
            boolean puzzleCompleted=false;
            boolean roomNameMatch = false;
            boolean breakNav = false;
            boolean isRecursive = true;
            String input;
            Area area = new Area("Dummy Area", "This area shouldn't ever be visible!");

            //while(isRecursive==true){
                isRecursive=false;
                while(breakNav==false){
                    consoleMessage("standard", "Current Area: ", room.getName());
                    consoleMessage("standard", room.getDescription());
                    while (roomNameMatch==false){
                        consoleMessage("standard", "Where would you like to go?");
                        for(int i=0; i<room.numberOfAreas(); i++){
                            consoleOption(room.getArea(i).getName());
                        }
                        System.out.println();
                        input=keyboard.nextLine();
                        for(int i=0; i<room.numberOfAreas(); i++){
                            if(input.equalsIgnoreCase(room.getArea(i).getName())){
                                roomNameMatch=true;
                                area = room.getArea(i);
                            }
                        }
                        if(roomNameMatch==false){
                            handleNonRoomName(input, character);
                        }
                    }
                    roomNameMatch=false;
                    if(area.hasSubAreas()){
                        isRecursive=recursive;
                        navigator(area, area.getSubAreas(),  character);
                    }
                    consoleMessage("standard", "Current Area: ", area.getName());
                    consoleMessage("standard", area.getDescription());
                    if(area.isLocked()){
                        lockedDoor(area, character);
                    }
                    if(area.firstVisit()==true){
                        area.visit();
                        if(area.hasItem()==true){
                            consoleMessage("standard", area.getItem().getMessage());
                            consoleMessage("standard", area.getItem().toString());
                            character.addToInventory(area.getItem());
                        }
                    }
                    if(area.hasDamage()==true){
                        consoleMessage("standard", area.getDamageDescription());
                        character.damageHP(area.getDamage());
                       
                    }
                    consoleMessage("standard", "Ready to go back?");
                    input=keyboard.nextLine();
                    while(input.equalsIgnoreCase("Yes")==false && input.equalsIgnoreCase("Y")==false && input.equalsIgnoreCase("Ready")==false){
                        consoleMessage("standard", "Okay you just let me know when you are. Ready yet?");
                        input=keyboard.nextLine();
                    }
                    breakNav=true;
                }
            //}  
        }
    

        public static void navigator (Area area, Area[] subAreas, Player character){
            Room room = new Room(area.getName(),area.getDescription(),subAreas);
            navigator(room, character, true);
        }

        //methods related to player options

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

        //methods related to specific puzzles/mechanics

        public static void lockedDoor(Area area, Player character){
            String input;
            KeyItem keyItem = new KeyItem("Dummy KeyItem", "This item shouldn't ever be visible!");

            if(area.hasPasswordLock()){
                input="";
                outer:
                while(input.equalsIgnoreCase("no")==false){
                    consoleMessage("standard", "Would you like to enter a password?");
                    input=keyboard.nextLine();
                    if(input.equalsIgnoreCase("yes")){
                        consoleMessage("standard", "Enter your password attempt");
                        input=keyboard.nextLine();
                        area.unlockRoom(input);
                        if(area.isLocked()){
                            consoleMessage("Standard", "Try again?");
                            input=keyboard.nextLine();
                        }
                        else{break outer;}
                    }
                    else{
                        consoleMessage("Standard", "I'm sorry I couldn't hear what you said. Try again.");
                    }
                }
            }
            else{
                input="";
                consoleMessage("standard", "Would you like to try and open the door?");
                input=keyboard.nextLine();
                outer:
                while(input.equalsIgnoreCase("no")==false){
                    if(input.equalsIgnoreCase("yes")){
                        boolean satisfied=false;
                        while(satisfied==false){
                            consoleMessage("standard", "Which item would you like to try and use?");
                            System.out.println(character.getInventory());
                            input=keyboard.nextLine(); 
                            System.out.println(character.getInventorySize());
                            for(int i=0; i<character.getInventorySize(); i++){
                                System.out.println(i);
                                if(character.getInventory(i).getName().equalsIgnoreCase(input)){
                                    if(character.getInventory(i) instanceof KeyItem){
                                        keyItem= (KeyItem)character.getInventory(i);
                                    }
                                }
                            }
                            if(keyItem.getName().equals("Dummy KeyItem")){
                                consoleMessage("standard", "Wrong type of item silly. Try again?");
                                input=keyboard.nextLine();
                                while(input.equalsIgnoreCase("yes")==false){
                                    if(input.equalsIgnoreCase("no")){
                                        break outer;
                                    }
                                    else{
                                        consoleMessage("Standard", "I'm sorry I couldn't hear what you said. Try again.");
                                        input=keyboard.nextLine();
                                    }
                                }
                            }
                            else{
                                satisfied=true;
                            }
                        }       
                        area.unlockRoom(keyItem);
                        if(area.isLocked()){
                            consoleMessage("Standard", "Try again?");
                            input=keyboard.nextLine();
                        }
                        else{break outer;}
                    }
                    else{
                        consoleMessage("Standard", "I'm sorry I couldn't hear what you said. Try again.");
                        input=keyboard.nextLine();
                    }
                }
            }            
        }
}


