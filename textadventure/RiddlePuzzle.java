public class RiddlePuzzle extends Puzzle{

    private String riddle;
    private String answer;
    private boolean isSolved;


    public RiddlePuzzle(String answer, String riddle){
        this.answer=answer;
        isSolved=false;
        this.riddle=riddle;
    }

    public boolean isSolved(){
        return isSolved;
    }

    public String getRiddle(){
        return riddle;
    }

    public void solvePuzzle(String possibleAnswer){
        String lowercaseAnswer = this.answer.toLowerCase();
        String lowercaseInput = possibleAnswer.toLowerCase();
        if(lowercaseAnswer.contains(lowercaseInput)){
            System.out.println("Aha! The password was "+ this.answer + ". We can finally get out of here!.");
            isSolved=true;
        }
        else{
            System.out.println("Nope, that password didn't work.");
        }
    }




}