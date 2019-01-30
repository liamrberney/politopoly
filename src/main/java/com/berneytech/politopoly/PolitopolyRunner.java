
package com.berneytech.politopoly;

import static java.lang.System.out;

public class PolitopolyRunner {

    public static void main(String[] args) {
        Mechanics mechanics= new Mechanics();
      //  out.println(flipText("monopoly"));
    }
    public static String flipText(String str){
        char[] text=str.toCharArray();
        char[] letters=new char[]{'a','b','c','d','e','f','g','h','i','j','k','l','m',
            'n','o','p','q','r','s','t','u','v','w','x','y','z'};
        String stringbuilder="";
        for (char a:text){
            if (getLetterIndex(a)==-1)
                stringbuilder+=a;
            else
                stringbuilder+=letters[25-getLetterIndex(a)];    
            
        }
        return stringbuilder;
    }
    public static int getLetterIndex(char x){
        char[] letters=new char[]{'a','b','c','d','e','f','g','h','i','j','k','l','m',
            'n','o','p','q','r','s','t','u','v','w','x','y','z'};
        for (int y=0; y<26; y++){
            if (letters[y]==x)
                return y;
        }
        return -1;
    }
}
