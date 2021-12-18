import java.util.ArrayList;
import java.util.Stack;

public class ConsistencyCheck {

	ArrayList<String> rows;
	/* in case of inconsistency */
	ArrayList<String> leftTags = new ArrayList<>();
	int errorsCounter=0;

	ConsistencyCheck(String xml){
		rows = commonMethods.xmlToRows(xml);
	}


	/*
	 * Desc: A function that takes a string and checks if tags are balanced or not,
	 * 			return true if balanced, false otherwise
	 * */
	public boolean checkBalancedTags(){

		Stack<String> tagStack = new Stack<String> ();
		
		/* adding tags to stack */
		for (int i=0; i< rows.size() ; i++) {
			String currentRow = rows.get(i);
			
			/* if the tag is opening, just push to stack */
			if (isOpeningTag(currentRow)) {
				tagStack.add(currentRow);
				
			}else if (isClosingTag(currentRow)){
				/* if it's a closing tag,,,
				 * if it corresponds to the peek tag, then pop the peek
				 * */
				if (peekMatchTag(tagStack.peek(), currentRow)) {
					tagStack.pop();
				}
			}
			/* else: row contains data, ignore it */
		}
		
		/* saving what's left in the stack to display for the user */
		for (String str: tagStack) {
			leftTags.add(str);
			errorsCounter++;
		}
		
		if (tagStack.isEmpty()){
			return true;
		}else{
			return false;
		}
	}


	/*
	 * Desc: return true if the XML is an opening tag
	 * 			tags are on form: <tagName> DATA </tagName>
	 * */
	public static boolean isOpeningTag(String tag) {
		if (tag.length() > 1){
			return ((tag.charAt(0) == '<') && (tag.charAt(1) != '/') && (tag.charAt(tag.length()-1) == '>'));
		}
		return false;
	}


	/*
	 * Desc: return true if the XML is a closing tag
	 * 			tags are on form: <tagName> DATA </tagName>
	 * */
	public static boolean isClosingTag(String tag) {
		if (tag.length() > 1){
			return ((tag.charAt(0) == '<') && (tag.charAt(1) == '/') && (tag.charAt(tag.length()-1) == '>'));
		}
		return false;
	}


	/**
	 * Desc: return true if the string is data [not an opening tag nor a closing one]
	 * 
	 */
	public static boolean checkData(String s){
		if (isClosingTag(s) || isOpeningTag(s)){
			return false;
		}
		return true;
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
