import java.util.ArrayList;
import java.util.Stack;

public class ConsistencyCheck {

	public static void main(String[] args) {
		/* example XML  that should be taken as input */
		String s = "<users><id>1</id>\n<name>Ahmed Ali</name></users>";
		
		Stack<String> tagStack = new Stack<String> ();
		
		/* extract tags from file */
		ArrayList<String> strTags = commonMethods.getTags(s);
		
		/* adding tags to stack */
		for (int i=0; i< strTags.size(); i++) {
			String currentTag = strTags.get(i);
			
			/* if the tag is opening, just push to stack */
			if (isOpeningTag(currentTag)) {
				tagStack.add(currentTag);
				
			}else {
				/* if it's a closing tag,,,
				 * if it corresponds to the peek tag, then pop the peek
				 * */
				if (peekMatchTag(tagStack.peek(), currentTag)) {
					tagStack.pop();
				}
			}
		}
		
		if (tagStack.isEmpty()) {
			System.out.println("Tags are balanced");
		}
		else {
			System.out.println("Tags are NOT balanced");
		}
	}
	

	/*
	 * Desc: return true if the XML is an opening tag
	 * 			tags are on form: <tagName> </tagName>
	 * */
	public static boolean isOpeningTag(String tag) {
		return (tag.charAt(1) != '/');
	}
	
	
	/*
	 * Desc: return true if the closing tag {tag} corresponds to the opening tag {stackPeek}
	 * */
	public static boolean peekMatchTag(String stackPeek,String tag) {	
		String tempTag="";
		for (int i=0; i<tag.length(); i++) {
			if (tag.charAt(i) != '/') {
				tempTag += tag.charAt(i);
			}
		}
		
		return (stackPeek.equals(tempTag));
	}
}
