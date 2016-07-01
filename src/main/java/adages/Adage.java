package adages;

/**
 * Created by AlexY on 2016/7/1.
 */
public class Adage {

    private String words;

    private int wordCount;

    private int id;


    public Adage() {
    }

    @Override
    public String toString() {


        return String.format("%2d:",id)+ words + "--" + wordCount + "words";
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getWordCount() {
        return wordCount;
    }

//    因为在setWord的时候就可以得到wordCount
    public void setWordCount(int wordCount) {

    }

    public String getWords() {
        return words;
    }

    public void setWords(String words) {
        this.words = words;
        this.wordCount = words.trim().split("\\S+").length;
    }





}
