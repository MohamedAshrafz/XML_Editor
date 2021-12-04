import java.util.*;

public class NetworkAnalysis {
	public static void main(String[] args) {
		String sample= "This XML file does not appear to have any style information associated with it. The document tree is shown below.\n"
				+ "<users>\n"
				+ "<user>\n"
				+ "<id>1</id>\n"
				+ "<name>Ahmed Ali</name>\n"
				+ "<posts>\n"
				+ "<post>\n"
				+ "<body> Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. </body>\n"
				+ "<topics>\n"
				+ "<topic> economy </topic>\n"
				+ "<topic> finance </topic>\n"
				+ "</topics>\n"
				+ "</post>\n"
				+ "<post>\n"
				+ "<body> Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. </body>\n"
				+ "<topics>\n"
				+ "<topic> solar_energy </topic>\n"
				+ "</topics>\n"
				+ "</post>\n"
				+ "</posts>\n"
				+ "<followers>\n"
				+ "<follower>\n"
				+ "<id>2</id>\n"
				+ "</follower>\n"
				+ "<follower>\n"
				+ "<id>3</id>\n"
				+ "</follower>\n"
				+ "</followers>\n"
				+ "</user>\n"
				+ "<user>\n"
				+ "<id>2</id>\n"
				+ "<name>Yasser Ahmed</name>\n"
				+ "<posts>\n"
				+ "<post>\n"
				+ "<body> Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. </body>\n"
				+ "<topics>\n"
				+ "<topic> education </topic>\n"
				+ "</topics>\n"
				+ "</post>\n"
				+ "</posts>\n"
				+ "<followers>\n"
				+ "<follower>\n"
				+ "<id>1</id>\n"
				+ "</follower>\n"
				+ "</followers>\n"
				+ "</user>\n"
				+ "<user>\n"
				+ "<id>3</id>\n"
				+ "<name>Mohamed Sherif</name>\n"
				+ "<posts>\n"
				+ "<post>\n"
				+ "<body> Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. </body>\n"
				+ "<topics>\n"
				+ "<topic> sports </topic>\n"
				+ "</topics>\n"
				+ "</post>\n"
				+ "</posts>\n"
				+ "<followers>\n"
				+ "<follower>\n"
				+ "<id>1</id>\n"
				+ "</follower>\n"
				+ "</followers>\n"
				+ "</user>\n"
				+ "</users>";
		
		System.out.println(getMostInfluencerUser(sample));
	}
	
	
	/*
	 * Desc: return ID of the user with most followers 
	 * */
	public static int getMostInfluencerUser(String xml) {
		
		ArrayList<String> tags = getTags(xml);

		int currentCounter=0, mostInfluencer=0;
		int j=0;
				
		for (int i=0;i<tags.size(); i++) {

			/* if <followers> opening tag is found, count how many <follower> tags are found! */
			if (tags.get(i).equals( "<followers>") ) {

				for (j=i+1; j<tags.size(); j++) {
					
					if (tags.get(j).equals( "<follower>") ) {
						currentCounter++;
					
                    			/* </followers> closing tag found, compare current with maximum and reset counter */
					}else if (tags.get(j).equals( "</followers>") ) {
						mostInfluencer = (currentCounter > mostInfluencer) ? currentCounter : mostInfluencer;
						currentCounter=0;
						break;
					}
				}
			}
		}
		
		return mostInfluencer;
	}
	
	
	/*
	 * Desc: A function that takes a string and returns an array of tags in that string
	 * */
	public static ArrayList<String> getTags(String s) {
		ArrayList<String> tags= new ArrayList<String>();
		
		String tag = "";
		int j=0, counter=0;
		
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
				counter++; tag =""; j=0;
			}
		}
		
		return tags;
	}
}
