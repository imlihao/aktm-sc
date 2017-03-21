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
     //��ĳ���ͻ��˵����ӻỰ����Ҫͨ���������ͻ��˷�������
     private Session session;
     public com.lh.vo.user user=null; 
     /**
      * ���ӽ����ɹ����õķ���
      * @param session  ��ѡ�Ĳ�����sessionΪ��ĳ���ͻ��˵����ӻỰ����Ҫͨ���������ͻ��˷�������
      */
     @OnOpen
     public void onOpen(Session session){
         this.session = session;
         webSocketSet.add(this);     //����set��         //��������1
         addOnlineCount();   
         System.out.println("��ǰ��������Ϊ" + getOnlineCount());
     }
      
     /**
      * ���ӹرյ��õķ���
      */
     @OnClose
     public void onClose(){
         webSocketSet.remove(this);  //��set��ɾ��
         subOnlineCount();           //��������1    
         System.out.println("��һ���ӹرգ���ǰ��������Ϊ" + getOnlineCount());
     }
      
     /**
      * �յ��ͻ�����Ϣ����õķ���
      * @param message �ͻ��˷��͹�������Ϣ
      * @param session ��ѡ�Ĳ���
      */
     @OnMessage
     public void onMessage(String message, Session session) {
         System.out.println("���Կͻ��˵���Ϣ:" + message);
         new messageProcess().verify(this, message);  
     }
      
     /**
      * ��������ʱ����
      * @param session
      * @param error
      */
     @OnError
     public void onError(Session session, Throwable error){
         System.out.println("��������");
         error.printStackTrace();
     }
      
     /**
      * ������������漸��������һ����û����ע�⣬�Ǹ����Լ���Ҫ��ӵķ�����
      * @param message
      * @throws IOException
      */
     public void sendMessage(String message) throws IOException{
    	 System.out.println("���ͣ�"+message);
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
