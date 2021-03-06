package application;

import java.io.DataInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

public class FileListener implements Runnable {
	private ServerSocket server =null;
    public  void receiveFile(Socket socket) throws IOException {
        byte[] inputByte = null;
        int length = 0;
        DataInputStream din = null;
        FileOutputStream fout = null;
        try {
            din = new DataInputStream(socket.getInputStream());
            File file1=new File("D:\\test2");
            if(!file1.exists()) {
            	file1.mkdirs();
            }
            fout = new FileOutputStream(new File("D:\\test2\\"+din.readUTF()));
            inputByte = new byte[1024];
            System.out.println("开始接收数据...");
            while (true) {
                if (din != null) {
                    length = din.read(inputByte, 0, inputByte.length);
                }
                if (length == -1) {
                    break;
                }
                System.out.println(length);
                fout.write(inputByte, 0, length);
                fout.flush();
            }
            System.out.println("完成接收");
            String url = getClass().getResource("/resource/music.aac").toString();
			Media media = new Media(url);
			MediaPlayer player = new MediaPlayer(media);
			player.setAutoPlay(true);         //设置自动播放
			player.setCycleCount(1);          //设置循环播放次数
			//音频控制(通常写在控件动作中)
			player.play();
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            if (fout != null)
                fout.close();
            if (din != null)
                din.close();
            if (socket != null)
                socket.close();
        }
    }
	@Override
	public void run() {
		// TODO 自动生成的方法存根
		try {
			server = new ServerSocket(33455);///
		} catch (IOException e1) {
			// TODO 自动生成的 catch 块
			e1.printStackTrace();
		}

         while (true) {
                try {
                    System.out.println("开始监听。。。");
                    Socket socket = server.accept();
                    System.out.println("有链接");
                    receiveFile(socket);
                } catch (Exception e) {
                     e.printStackTrace();
                }
         }
	}
}
