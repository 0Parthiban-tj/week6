package businesslogic;

import java.io.IOException;
import com.nexmo.client.NexmoClient;
import com.nexmo.client.NexmoClientException;
import com.nexmo.client.sms.SmsSubmissionResponse;
import com.nexmo.client.sms.SmsSubmissionResponseMessage;
import com.nexmo.client.sms.messages.TextMessage;

public class ApplyLoan {
	
	public void getNewLoan(String name,String mnum,String purpose) throws IOException, NexmoClientException {
		
		NexmoClient client = new NexmoClient.Builder()
				  .apiKey("c74cf033")
				  .apiSecret("h7Y86usZgJ8l2sDg")
				  .build();

				String messageText = "Dear Agent , the customer Name:"+name+"  wants loan in the Purpose:"+purpose+"So please contact for futhur process. Mobile Number:"+mnum;
				TextMessage message = new TextMessage("Insurance System", "919597173727", messageText);

				SmsSubmissionResponse response = client.getSmsClient().submitMessage(message);

				for (SmsSubmissionResponseMessage responseMessage : response.getMessages()) {
				    System.out.println(responseMessage);
				    System.out.println("Request sent sucessfully");
				}
				
		
		
	}
   
	

}







//"Dear Agent , the customer Name:"+name+"  wants loan in the Purpose:"+purpose+"So please contact for futhur process. Mobile Number:"+mnum;