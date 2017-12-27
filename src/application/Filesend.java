package application;

import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Filesend implements Runnable{
	private File file=null;
	private Socket socket= null;
	public void setting(File file) {
		// TODO 自动生成的方法存根
		this.file=file;
	}
	public void run() {
		// TODO 自动生成的方法存根
		DataOutputStream dout= null;
		FileInputStream fin = null;
		byte[] sendByte= null;
		int length = 0;
		try {			
			try {
	            Message.sendmessage(Data.getYou(),656,"对方已向你发送了文件 请在本地D:\\test2查收");
	            
	    		socket=new Socket();
	            try {
	            	InetAddress address=InetAddress.getByName(Data.getYou());
	    			socket.connect(new InetSocketAddress(address, 33455));
	    		} catch (IOException e) {
	    			// TODO 自动生成的 catch 块
	    			e.printStackTrace();
	    		}
	            dout = new DataOutputStream(socket.getOutputStream());
	            fin = new FileInputStream(file);
	            sendByte = new byte[1024];
	            dout.writeUTF(file.getName());
	            while((length = fin.read(sendByte, 0, sendByte.length))>0){
	                dout.write(sendByte,0,length);
	                dout.flush();
	            }
	            } catch (Exception e) {

	            } 	
	             if (dout != null)
	                   dout.close();
	             if (fin != null)
	                   fin.close();
	             if (socket != null)
	                   socket.close();
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	}

}
