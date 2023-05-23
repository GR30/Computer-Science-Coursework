import java.io.File;
import java.io.IOException;
import java.util.*;
public class FileWordCounter {
    public static void main(String args[]) throws IOException {
        HashMap<String, Word> myMap = new HashMap<String, Word>();

        //Prompts the user to input a file path.
        Scanner scanF1 = new Scanner(System.in);
        System.out.println("Input the name of the first file: ");
        String f1 = scanF1.nextLine();

        //Imports the text from the file path the user input.
        Scanner scan = new Scanner(new File(f1));

        //Lists to hold Word objects and the Strings they hold, respectively.
        List<Word> wordList = new ArrayList<Word>();
        ArrayList<String> keyHold = new ArrayList<String>();
        
        //Scans in each word from the text file, creates new instances of Word,
        //and counts the number of that Wor through the text.
        String next; Word newWord;
        while(scan.hasNext()){
            next = scan.next();
            newWord = new Word(next);
            if(newWord == null){ myMap.put(next, new Word(null)); }
            else if(myMap.containsKey(next)){
                Word temp = myMap.get(next);
                temp.upCnt();
                myMap.replace(next, temp);
            }else{ 
                newWord.upCnt();
                myMap.put(next, newWord);
                keyHold.add(next);
            }
        }
        
        for(String x : keyHold){ wordList.add(myMap.get(x)); }
        
        for(int a=0; a < wordList.size(); a++){
            for(int y=0; y < wordList.size(); y++){
                if(((y+1<wordList.size())&&(wordList.get(y).getCnt() < wordList.get(y+1).getCnt()))){
                    Word temp = wordList.get(y);
                    wordList.set(y, wordList.get(y+1));
                    wordList.set(y+1, temp);
                }
            }
        }

        for(Word w : wordList){
            System.out.println(w.toString());
        }

    }
}

