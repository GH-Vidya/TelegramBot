package cm.accenture.bots;

import org.telegram.telegrambots.TelegramApiException;
import org.telegram.telegrambots.TelegramBotsApi;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.TelegramBotAdapter;
import com.pengrad.telegrambot.response.BaseResponse;

import cm.accenture.bots.handler.JenkinsUpdateHandler;

public class JenkinsBot {

	public static final String BOT_TOKEN = "246023263:AAHfAQDEZDi2xDUu7Tjn9aFgnYs86Yr5AvM";

	public static void main(String[] args) throws TelegramApiException {
		TelegramBot bot = TelegramBotAdapter.build(BOT_TOKEN);

		BaseResponse response = bot.getMe();
		System.out.println(response);

		TelegramBotsApi telegramBotsApi = new TelegramBotsApi();
		try {
			telegramBotsApi.registerBot(new JenkinsUpdateHandler());
		} catch (TelegramApiException e) {
			System.out.println(e);
		}
	}

	
}
