public class Puzzle {
    
    private boolean isSolved;

    public Puzzle(){
        isSolved=false;
    }
    
    public boolean isSolved(){
        return isSolved;
    }

    public void solvePuzzle(String input){
        System.out.println("Default Puzzle Method");
        isSolved=true;
    }

}
