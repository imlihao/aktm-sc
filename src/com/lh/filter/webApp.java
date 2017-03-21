package com.lh.filter;
import java.io.IOException;
import java.util.concurrent.CopyOnWriteArraySet;
import javax.websocket.server.ServerEndpoint;
import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
@ServerEndpoint("/websocket")
public class webApp {
     public static CopyOnWriteArraySet<webApp> webSocketSet = new CopyOnWriteArraySet<webApp>();
     private static int onlineCount = 0;
     //与某个客户端的连接会话，需要通过它来给客户端发送数据
     private Session session;
     public com.lh.vo.user user=null; 
     /**
      * 连接建立成功调用的方法
      * @param session  可选的参数。session为与某个客户端的连接会话，需要通过它来给客户端发送数据
      */
     @OnOpen
     public void onOpen(Session session){
         this.session = session;
         webSocketSet.add(this);     //加入set中         //在线数加1
         addOnlineCount();   
         System.out.println("当前在线人数为" + getOnlineCount());
     }
      
     /**
      * 连接关闭调用的方法
      */
     @OnClose
     public void onClose(){
         webSocketSet.remove(this);  //从set中删除
         subOnlineCount();           //在线数减1    
         System.out.println("有一连接关闭！当前在线人数为" + getOnlineCount());
     }
      
     /**
      * 收到客户端消息后调用的方法
      * @param message 客户端发送过来的消息
      * @param session 可选的参数
      */
     @OnMessage
     public void onMessage(String message, Session session) {
         System.out.println("来自客户端的消息:" + message);
         new messageProcess().verify(this, message);  
     }
      
     /**
      * 发生错误时调用
      * @param session
      * @param error
      */
     @OnError
     public void onError(Session session, Throwable error){
         System.out.println("发生错误");
         error.printStackTrace();
     }
      
     /**
      * 这个方法与上面几个方法不一样。没有用注解，是根据自己需要添加的方法。
      * @param message
      * @throws IOException
      */
     public void sendMessage(String message) throws IOException{
    	 System.out.println("发送："+message);
         this.session.getBasicRemote().sendText(message);
         //this.session.getAsyncRemote().sendText(message);
     }
     
     
     public static synchronized int getOnlineCount() {
         return onlineCount;
     }
  
     public static synchronized void addOnlineCount() {
         webApp.onlineCount++;
     }
      
     public static synchronized void subOnlineCount() {
    	 webApp.onlineCount--;
     }
     
}
