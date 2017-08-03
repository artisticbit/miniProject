package com.model2.mvc.web.chat;

import java.io.IOException;

import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.RemoteEndpoint.Basic;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/chat/*")
@ServerEndpoint(value="/echo")
public class TestWebSocket {

    private static final java.util.Set<Session> sessions = java.util.Collections.synchronizedSet(new java.util.HashSet<Session>());
    
    
    public TestWebSocket() {
		System.out.println(getClass());
	}
    @RequestMapping("/webSocket")
    public String testView(){
    	System.out.println("socketTest");
      //  return "common/testWebSocket";
    	return "/websocket/websocket.jsp";
    	//return "/user/logonView.jsp";
    }
    
    /**
     * @OnOpen allows us to intercept the creation of a new session.
     * The session class allows us to send data to the user.
     * In the method onOpen, we'll let the user know that the handshake was 
     * successful.
     */
    @OnOpen
    public void onOpen(Session session){
        System.out.println("Open session id : " + session.getId());
        
        try {
            final Basic basic = session.getBasicRemote();
            basic.sendText("Connection Established");
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        
        sessions.add(session);
        System.out.println("server onOpen!");
    }
    
    /**
     * 紐⑤뱺 �궗�슜�옄�뿉寃� 硫붿떆吏�瑜� �쟾�떖�븳�떎.
     * @param self
     * @param message
     */
    private void sendAllSessionToMessage(Session self, String message){
        try {
            for( Session session : TestWebSocket.sessions ){
                if( ! self.getId().equals(session.getId()) )
                    session.getBasicRemote().sendText("All : " + message);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    /**
     * When a user sends a message to the server, this method will intercept the message
     * and allow us to react to it. For now the message is read as a String.
     */
    @OnMessage
    public void onMessage(String message, Session session){
        System.out.println("Message from " + session.getId() + ": " + message);
        try {
            final Basic basic = session.getBasicRemote();
            //basic.sendText("to : " + message);
            	basic.sendText(message);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        
        sendAllSessionToMessage( session, message );
    }
    
    @OnError
    public void onError( Throwable e, Session session){
       e.printStackTrace();
    }
    
    /**
     * The user closes the connection.
     * 
     * Note: you can't send messages to the client from this method
     */
    @OnClose
    public void onClose(Session session){
        System.out.println("Session " +session.getId()+" has ended");
        sessions.remove(session);
    }
}