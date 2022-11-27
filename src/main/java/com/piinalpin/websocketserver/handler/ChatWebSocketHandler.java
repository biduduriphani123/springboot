package com.piinalpin.websocketserver.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.piinalpin.websocketserver.domain.dto.MessageDto;
import com.piinalpin.websocketserver.domain.dto.MessageResponse;
import com.piinalpin.websocketserver.domain.dto.RiddlesDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.util.Objects;
import java.util.concurrent.TimeUnit;

@Slf4j
public class ChatWebSocketHandler extends TextWebSocketHandler {

    @Autowired
    private ObjectMapper mapper;

    @Autowired
    private RestTemplate restTemplate;

    @Value("${chatbot.url}")
    private String chatbotUrl;


    @Value("${local}")
    private String local;
    
   
   static SearchAlgorithm searchAlgorithm = new SearchAlgorithm();
    
    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
     System.out.println("this is start");
    	super.handleTextMessage(session, message);
       // log.info("Payload: " + message.getPayload());
        MessageDto messageDto = mapper.readValue(message.getPayload(), MessageDto.class);
        TextMessage response = new
       		  TextMessage(mapper.writeValueAsString("Welcome to Food Chatbot. \n How can I help you?"));
        
     //   session.sendMessage(response);
		
		  if (StringUtils.isEmpty(messageDto.getMessage())) return;
		  
		  ResponseEntity<MessageResponse> responseEntity = null;
		  
		  try { 
			  if(local !=null && local.equalsIgnoreCase("true"))
			  {
				  String responseStr = searchAlgorithm.test(messageDto.getMessage());
				if(responseStr==null || responseStr.length()==0)
				{
					  session.sendMessage(new
							  TextMessage(mapper.writeValueAsString(MessageDto.builder().
							  message("Sorry, your message is not understand. Please enter proper message.").build())));
							  return;
				}
				 session.sendMessage(new
						  TextMessage(mapper.writeValueAsString(MessageDto.builder().
						  message(responseStr).build())));
				 return;
			  }else {
			  responseEntity = restTemplate.postForEntity(chatbotUrl, messageDto,
		  MessageResponse.class);
			  }
			} catch (Exception e) {
				e.printStackTrace();
				/*
				 * log.info("Session ID: " + session.getId()); // log.error("Happened error",
				 * e);
				 */		  session.sendMessage(new
		  TextMessage(mapper.writeValueAsString(MessageDto.builder().
		  message("Sorry, your message is not understand. Please enter proper message.").build())));
		  return;
		  }
		 
		//  MessageResponse messageResponse = responseEntity.getBody();
		  
			/*
			 * if
			 * (Objects.requireNonNull(messageResponse).getType().equalsIgnoreCase("riddles"
			 * )) { RiddlesDto dto = mapper.convertValue(messageResponse.getData(),
			 * RiddlesDto.class); // log.info("RiddlesDto:: " + dto); TextMessage question =
			 * new TextMessage(mapper.writeValueAsString(MessageDto.builder().message(dto.
			 * getQuestion()).build())); TextMessage answer = new
			 * TextMessage(mapper.writeValueAsString(MessageDto.builder().message(dto.
			 * getAnswer()).build())); session.sendMessage(question);
			 * TimeUnit.SECONDS.sleep(1); session.sendMessage(answer); } else { TextMessage
			 * response = new
			 * TextMessage(mapper.writeValueAsString(messageResponse.getData())); //
			 * log.info("Response: " + response.getPayload());
			 * session.sendMessage(response); }
			 */
			 
    }
}
