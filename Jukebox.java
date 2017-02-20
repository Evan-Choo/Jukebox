import java.io.*;
import java.util.*;

import javax.servlet.*;
import javax.servlet.http.*;

public class Jukebox extends HttpServlet {

	// Do calculations and update the form
	public void doPost(
		HttpServletRequest request,
		HttpServletResponse response)
		throws IOException, ServletException {

		/** 
		 * 	Build the collection of Song's
		 */
		int lCount; // loop counter

		Vector songList = new Vector();

		songList.add( new Song(
				"Pachchai Nirame",
				"2:15",
				"Tamil Pop",
				"Alai Payuthey (2000)",
				false,
				"TPS001",
				"http://www.raaga.com/channels/tamil/movie/T0000209.html") );
		songList.add( new Song(
				"Bombay (Children of Combodia)",
				"2:38",
				"Jazz",
				"Fred Andersen Quartet/1",
				false,
				"WCS002",
				"http://www.epitonic.com/artists/fredandersonquartet.html#tracks") );
		songList.add( new Song(
				"A Dios Le Pido",
				"1:50",
				"Latin Pop",
				"Juanes : Un Dia Normal/1",
				false,
				"SPS003",
				"http://es.artists.mp3s.com/artist_song/2515/2515448.html") );

		/** 
		 * 	Get user input  
		 * 	Update the collection of Song's for selection status
		 */
		for (lCount = 0; lCount < songList.size(); lCount++) {
			String titleChoice;
			titleChoice =
				(String) request.getParameter(((Song)songList.elementAt(lCount)).getSongID());

			if (titleChoice != null)
				((Song)songList.elementAt(lCount)).select(true);
		}

		/**
		 *	 Prepare the HTML and send it out
		 */
		
		displayHtml(songList, response);
	} //end doPost

	

	    private String ifChecked0;
        private String ifChecked1;
        private String ifChecked2;

	public void displayHtml(Vector sList, HttpServletResponse resp)
		throws IOException {
		/**
		 *  Indicate the content type (for example, text/html), 
		 *  being returned by the response
		 */
		resp.setContentType("text/html");


		/** 
		 * 	Retrieve an output stream to use to send data to the client 
		 */
		PrintWriter out = resp.getWriter();
		


		/** 
		 * 	Create an instance of the HtmlPage class 
		 */
		HtmlPage myPage = new HtmlPage();
		


		/** 
		 * 	Set the attributes of the HtmlPageV2 object 
		 */
		myPage.setTitle("Jukebox");
		myPage.setBackgroundColor("fdf5e6");
		

		/** 
		 * 	Add the list of songs, using HtmlTable class and addText() method of HtmlPage class
		 */
		myPage.addText("<FORM action='Jukebox' method='post'>");
		myPage.addText("<h1 align='center'>CTE Jukebox</h1>");
		myPage.addText("<b>Select titles for your playlist:- </b>");
		HtmlTable table1 = new HtmlTable(10);
		//the first row of the table
		table1.startRow();
		table1.addCell("<B>Title</B>");
		table1.addCell("<B>Duration(min)</B>");
		table1.addCell("<B>Category</B>");
		table1.addCell("<B>Album/Track</B>");
		table1.endRow();

        //the second row of the table
        
        if (((Song)sList.elementAt(0)).getTrueOrFalse() == true ){
        	ifChecked0 = "checked";
        }
        else{
        	ifChecked0 = "unchecked";
        }
		table1.startRow();
		table1.addCell("<INPUT TYPE='checkbox' NAME='TPS001' VALUE='title1'"+ifChecked0+"> Pachchai Nirame");
		table1.addCell("2:15");
		table1.addCell("Tamil Pop");
		table1.addCell("Alai Payuthey (2000)");
		table1.addCell("<A href='http://www.raaga.com/channels/tamil/movie/T0000209.html'>Play</A>");
		table1.endRow();

		//the third row
		
        if (((Song)sList.elementAt(1)).getTrueOrFalse() == true ){
        	ifChecked1 = "checked";
        }
        else{
        	ifChecked1 = "unchecked";
        }
		table1.startRow();
		table1.addCell("<INPUT type='checkbox' name='WCS002' value='title2' "+ifChecked1+"> Bombay (Children of Combodia)");
		table1.addCell("2:38");
		table1.addCell("Jazz");
		table1.addCell("Fred Anderson Quartet/1");
		table1.addCell("<A href='http://www.epitonic.com/artists/fredandersonquartet.html#tracks'>Play</A>");
		table1.endRow();

		//the fourth row
		
        if (((Song)sList.elementAt(2)).getTrueOrFalse() == true ){
        	ifChecked2 = "checked";
        }
        else{
        	ifChecked2 = "unchecked";
        }
		table1.startRow();
		table1.addCell("<INPUT type='checkbox' name='SPS003' value='title3' "+ifChecked2+"> A Dios Le Pido");
		table1.addCell("1:50");
		table1.addCell("Latin Pop");
		table1.addCell("Juanes : Un Dia Normal/1");
		table1.addCell("<A href='http://es.artists.mp3s.com/artist_song/2515/2515448.html'>Play</A>");
		table1.endRow();

		//print the table using the method in the html table class
		myPage.addText(table1.buildHtml());




		//tht submit buttom named My Playlist
		myPage.addText("<input TYPE='submit' NAME='UserRequest' VALUE='My Playlist'>");
		myPage.addText("<br>");





		
		/**
		 * Heading first
		 */

		

		/**
		 * Now the list of titles to choose from
		 */

		


		/**
		 *	Add any other HTML to be added as additional text to the body 
		 */
		


		/**
		 * 	Display the playlist now; this is a TEXTAREA element for which
		 * 	must be built using  the addText method of HtmlPageV2 class
		 */
		myPage.addText("<TEXTAREA cols = '50' rows = '6'>");
		//use the for loop to add info to the textarea
		int songIndex;
		for (songIndex=0;songIndex < sList.size();++songIndex)
		    {
			if (((Song)sList.elementAt(songIndex)).getTrueOrFalse() == true){
				/*use the vector to indicate the song  instead of constructing a new
				**object in the Song class
				*/
				myPage.addText(((Song)sList.elementAt(songIndex)).getTitle()+"  ");
				myPage.addText(((Song)sList.elementAt(songIndex)).getPlayTime()+"  ");
				myPage.addText(((Song)sList.elementAt(songIndex)).getCatagory()+"  ");
				myPage.addText(((Song)sList.elementAt(songIndex)).getAlbum()+"  ");
				myPage.addText("       ");
			}

		}
		myPage.addText("</TEXTAREA>");
		myPage.addText("</FORM>");



		/**
		 *	Add any other HTML to be added as additional text to the body 
		 */
		


		/**
		 *	 Construct the HTML response
		 */
		out.print(myPage.buildHtml());

	}
}
