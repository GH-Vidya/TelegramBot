package cm.accenture.bots.handler;

import org.telegram.telegrambots.TelegramApiException;
import org.telegram.telegrambots.api.objects.Update;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
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
                        sendMessageRequest.setText("you said: " + message.getText());
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

}
