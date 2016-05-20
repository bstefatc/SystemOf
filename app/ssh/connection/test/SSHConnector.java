package ssh.connection.test;

import com.jcraft.jsch.Channel;
import com.jcraft.jsch.ChannelExec;
import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.Session;

import java.io.*;

public class SSHConnector {
	public static void main(String args[]) {
		fileReader();
		checkMongoServer();
	}

	private static void checkMongoServer() {
		JSch jsch = new JSch();

		try
		{
		  Session session = jsch.getSession("egnarodev", "192.168.1.124", 22);
		  java.util.Properties config = new java.util.Properties();
		  config.put("StrictHostKeyChecking", "no");
		  session.setConfig(config);
		  session.setPassword("egnaro123");
		  session.connect();

		  String command = "docker --version";
		  Channel channel = session.openChannel("exec");
		  ((ChannelExec) channel).setCommand(command);
		  channel.setInputStream(null);
		  ((ChannelExec) channel).setErrStream(System.err);
		  InputStream in = channel.getInputStream();
		  channel.connect();
		  byte[] tmp = new byte[1024];
		  while (true)
		  {
		    while (in.available() > 0)
		    {
		      int i = in.read(tmp, 0, 1024);
		      if (i < 0)
		        break;
		      System.out.print(new String(tmp, 0, i));
		    }
		    if (channel.isClosed())
		    {
		      System.out.println("exit-status: " + channel.getExitStatus());
		      break;
		    }
		    try
		    {
		      Thread.sleep(1000);
		    }
		    catch (Exception ee)
		    {
		    }
		  }

		  channel.disconnect();
		  session.disconnect();
		}
		catch (Exception e)
		{
		  System.out.println(e.getMessage());
		}

		
	}

	private static void fileReader() {
		String user = "egnarodev";
		String password = "egnaro123";
		String host = "192.168.1.124";
		int port = 22;

		String remoteFile = "/home/egnarodev/sshFolder/iptable.txt";

		try {
			JSch jsch = new JSch();
			Session session = jsch.getSession(user, host, port);
			session.setPassword(password);
			session.setConfig("StrictHostKeyChecking", "no");
			System.out.println("Establishing Connection...");
			session.connect();
			System.out.println("Connection established.");
			System.out.println("Crating SFTP Channel.");
			ChannelSftp sftpChannel = (ChannelSftp) session.openChannel("sftp");
			sftpChannel.connect();
			System.out.println("SFTP Channel created.");

			InputStream out = null;
			out = sftpChannel.get(remoteFile);
			BufferedReader br = new BufferedReader(new InputStreamReader(out));
			String line;
			while ((line = br.readLine()) != null)
				System.out.println(line);
			br.close();
		} catch (Exception e) {
			System.err.print(e);
		}
	}

}
