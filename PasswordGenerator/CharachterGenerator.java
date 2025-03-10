package Ogunseye.PersonalProjects;

public class CharachterGenerator extends RandomPassword{

    // given user letter that can be kept in here to genereate new passwprds
    public  static String mergeAlternately(String word1, String word2) {
        String newword = "";
        // space to generate for each word int the word to satay
        // i renitalize
        String wordOne= "";
        String wordtwo = "";
        // the total length of the added words togther
        int totalword = word1.length() + word2.length();

        // lengh minus 1 because it putting each number based on the index loaction
        // if one word is longer ehy does ot not print ret of teh word?
        int i = 0;
        // the minus tw represent how many letters it is subratcting ech time - they are isn sets of two and
        // prits how many spaces are left to fill in the blank
        while (i < totalword  - (newword.length() -2) ){
            wordOne = word1.substring(i,i+1);
            wordtwo = word2.substring(i,i+1);
            newword +=  wordOne + wordtwo;
            i++;
        }

        if ( word1.length() > word2.length()) {
            newword += word1.substring(i, word1.length() );
        }
        else if (word1.length() < word2.length()){
            newword += word2.substring(i,word2.length());
        }
        else {
            newword += "";
        }

        return newword;

    }



}
