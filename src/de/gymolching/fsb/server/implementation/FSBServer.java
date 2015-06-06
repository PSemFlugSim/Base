package de.gymolching.fsb.server.implementation;

import java.io.*;
import java.net.*;
import java.util.ArrayList;

import de.gymolching.fsb.api.FSBPosition;
import de.gymolching.fsb.server.api.FSBServerInterface;

public class FSBServer implements FSBServerInterface, Runnable
{
	private ServerSocket serverSocket;
	private Thread serverThread;
	private ArrayList<FSBPosition> positions;

	/**
	 * Creates new FSBServer and starts listening
	 * 
	 * @throws IOException
	 * @throws InterruptedException
	 */
	public FSBServer(int port) throws IOException, InterruptedException
	{
		this.serverSocket = new ServerSocket(port);
		this.serverThread = new Thread(this);
		this.positions = new ArrayList<>();
		this.serverThread.start();

		synchronized (this.positions)
		{
			this.positions.wait();
		}
	}

	public void stopServer() throws InterruptedException
	{
		this.serverThread.interrupt();
		this.serverThread.join();
	}

	public FSBPosition getMostRecentPositionUpdate() throws InterruptedException
	{
		boolean shouldWait = false;
		do
		{
			shouldWait = false;
			synchronized (this.positions)
			{
				if (this.positions.size() == 0)
				{
					shouldWait = true;
					this.positions.wait();
				}
			}
		} while (shouldWait == true);

		synchronized (this.positions)
		{
			FSBPosition mostRecentPosition = this.positions.get(this.positions.size() - 1);
			this.positions.clear();
			return mostRecentPosition;
		}
	}

	public void run()
	{
		while (!Thread.interrupted())
		{
			// Wait for incoming connection and
			try
			{
				// Notify main thread that server is started and waiting to accept connection
				synchronized (this.positions)
				{
					this.positions.notifyAll();
				}

				Socket connSocket = this.serverSocket.accept();
				DataInputStream dis = new DataInputStream(connSocket.getInputStream());

				while (connSocket.isConnected())
				{
					// Receive Input from client and store it in our positions list
					String connInputString = dis.readUTF();

					synchronized (this.positions)
					{
						this.positions.add(new FSBPosition(connInputString));
						this.positions.notifyAll();
					}
				}

				dis.close();
				connSocket.close();
			}
			catch (IOException e)
			{
				e.printStackTrace();
			}
		}

		try
		{
			this.serverSocket.close();
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}
}
