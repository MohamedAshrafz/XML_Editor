import java.util.ArrayList;

public class NetworkAnalysis {
	
	ArrayList<String> rows;

	NetworkAnalysis(String xml){
		rows = commonMethods.xmlToRows(xml);
	}


	/*
		Desc: returns user ID with most followers
	*/
	public int getMostInfluencerUser(){

		int mostInfluencerFollowers=0, mostInfluencerID=0;
		int currentUserFollowers=0, currentUserID=0;

		for (int i=0; i< rows.size(); i++){
			
			if (rows.get(i).equals("<user>") && rows.get(i+1).equals("<id>")){
				currentUserID = getUserID(i+1);
				currentUserFollowers = getCurrentUserFollowers(i);

				if (currentUserFollowers > mostInfluencerFollowers){
					
					mostInfluencerID = currentUserID;
					mostInfluencerFollowers = currentUserFollowers;
				}
			}
	}

		return mostInfluencerID;
	}


	/*
	 * Desc: retruns number of followers for a specific user 
	 * */
	public int getCurrentUserFollowers(int startingIndex) {
		return 0;
	}


	/**
	 * Desc: 
	 */
	public int getUserID(int startingIndex){
		int id=0;
		/* cast string to int */
		return id;
	}
}
