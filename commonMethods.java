import java.util.*;

/* The class for all common methods across the project */
public class commonMethods {
    
    	/*
	 * Desc: A function that takes a string and returns an array of tags in that string
	 * */
	public static ArrayList<String> getTags(String s) {
		ArrayList<String> tags= new ArrayList<String>();
		
		String tag = "";
		int j=0;
		
		for (int i=0; i< s.length(); i++) {
			/* when finding an opening character <, append chars till the closing character > */
			if ( s.charAt(i) == '<') {
				j=i;
				while(s.charAt(j) != '>') {
					tag += s.charAt(j);
					j++;
				}
				tag += '>';

				/* add tag to the tags array */
				tags.add(tag);
				
				/* reseting variables */
				tag =""; j=0;
			}
		}
		
		return tags;
	}
}
