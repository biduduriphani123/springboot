package com.piinalpin.websocketserver.config;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.socket.WebSocketHandler;

class WebSocketConfigurationTest {

	@Test
	void test() {
		WebSocketConfiguration webSocketConfiguration = new WebSocketConfiguration();
		WebSocketHandler handler=	webSocketConfiguration.getChatWebSocketHandler();
		assertNotNull(handler);
	}

}
