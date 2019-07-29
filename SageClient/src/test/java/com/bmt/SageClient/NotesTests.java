package com.bmt.SageClient;

import java.util.List;

/*import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
*/

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.bmt.SageClient.api_dataTypes.Note;
import com.bmt.SageClient.api_dataTypes.ServerResponse;
import com.bmt.SageClient.orm.dao.SageAPIMemoHandlerDAO;
import com.bmt.SageClient.service.TokenGetter;



@RunWith(SpringRunner.class)
@SpringBootTest
public class NotesTests {

	@Autowired
	SageAPIMemoHandlerDAO memoHandler;
	
	@Test
	public void contextLoads() {
	} 
	
	
	@Test
	public void testAddNote()
	{
		TokenGetter.getToken();
		TokenGetter.readToken();
		Note note = new Note();
		note.setCustomerId(85680);
		note.setNote("my test note 2");
		ServerResponse response = memoHandler.addNotes(note);
		System.out.println(response.isSuccess());
	}
	
}

