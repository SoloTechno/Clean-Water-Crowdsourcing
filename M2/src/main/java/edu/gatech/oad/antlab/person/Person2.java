package edu.gatech.oad.antlab.person;

import java.util.List;
import java.util.ArrayList;
/**
 *  A simple class for person 2
 *  returns their name and a
 *  modified string 
 *
 * @author Bob
 * @version 1.1
 */
public class Person2 {
    /** Holds the persons real name */
    private String name;
	 	/**
	 * The constructor, takes in the persons
	 * name
	 * @param pname the person's real name
	 */
	 public Person2(String pname) {
	   name = pname;
	 }
	/**
	 * This method should take the string
	 * input and return its characters in
	 * random order.
	 * given "gtg123b" it should return
	 * something like "g3tb1g2".
	 *
	 * @param input the string to be modified
	 * @return the modified string
	 */
	private String calc(String input) {
        List<Character> letters = new ArrayList<>();
        for (char singleChar:input.toCharArray()){
            letters.add(singleChar);
        }
        StringBuilder result = new StringBuilder(input.length());
        for (int i = letters.size(); i > 0; i--) {
            int randLetter = (int) (Math.random()* i);
            result.append(letters.remove(randLetter));
        }
        return result.toString();
	}
	/**
	 * Return a string rep of this object
	 * that varies with an input string
	 *
	 * @param input the varying string
	 * @return the string representing the 
	 *         object
	 */
	public String toString(String input) {
	  return name + calc(input);
	}
}
