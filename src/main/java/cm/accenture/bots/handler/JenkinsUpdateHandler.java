package cm.accenture.bots.handler;

import org.telegram.telegrambots.TelegramApiException;
import org.telegram.telegrambots.api.objects.Update;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

import org.telegram.telegrambots.api.methods.send.SendMessage;
import  org.telegram.telegrambots.api.objects.Message;

public class JenkinsUpdateHandler extends TelegramLongPollingBot{

	public void onUpdateReceived(Update update) {
		//check if the update has a message
        if(update.hasMessage()){
               Message message = update.getMessage();

                //check if the message has text. it could also  contain for example a location ( message.hasLocation() )
                if(message.hasText()){

                        //create a object that contains the information to send back the message
                        SendMessage sendMessageRequest = new SendMessage();
                        sendMessageRequest.setChatId(message.getChatId().toString()); //who should get the message? the sender from which we got the message...
                        String messageee = message.getText();
                       String[] commads = messageee.split(" "); 
                       
                      
                    
                      String output = startJenkinsBuild();
                      
                       sendMessageRequest.setText("Build started for " + commads[1]);
                        try {
                                sendMessage(sendMessageRequest); //at the end, so some magic and send the message ;)
                        } catch (TelegramApiException e) {
                                //do some error handling
                        }//end catch()
                }//end if()
        }//end  if()
		
	}

	public String getBotUsername() {
		return "SpyingBot";
	}

	@Override
	public String getBotToken() {
		return "246023263:AAHfAQDEZDi2xDUu7Tjn9aFgnYs86Yr5AvM";
	}
	
	public static String startJenkinsBuild(){
		Client client = Client.create();

		WebResource webResource = client
		   .resource("http://localhost:8080/job/StartTelegraphBot/build");

		ClientResponse response = webResource.accept("application/json")
                   .get(ClientResponse.class);

		String output = response.getEntity(String.class);

		
		System.out.println(output);
		return output;
	}

}
