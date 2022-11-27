package com.piinalpin.websocketserver.handler;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class ChatWebSocketHandlerTest {

	@Test
	void test() {
		ChatWebSocketHandler chatWebSocketHandler = new ChatWebSocketHandler();
		
		assertNotNull(chatWebSocketHandler.searchAlgorithm);
	}
	
	@Test
	void testHandleTextMessage() {
		ChatWebSocketHandler chatWebSocketHandler = new ChatWebSocketHandler();
		
		String message  = chatWebSocketHandler.searchAlgorithm.test("Hi");
	 
		assertEquals( "Welcome to Chatbot", message);
	}

}
