package de.gymolching.fsb.client.implementation;

import java.io.*;
import java.net.*;

import de.gymolching.fsb.api.FSBPosition;
import de.gymolching.fsb.client.api.*;

public class FSBClient implements FSBClientInterface
{
	private Socket clientSocket = null;
	private DataOutputStream connOutStream = null;

	public void connect(String host, int port) throws IOException
	{
		System.out.println("Establishing connection to Server " + host + ":" + port);
		this.clientSocket = new Socket(host, port);

		System.out.println("Fetching DataOutStream");
		this.connOutStream = new DataOutputStream(this.clientSocket.getOutputStream());
	}
	
	public void disconnect() throws IOException
	{
		this.connOutStream.close();
		this.clientSocket.close();
	}

	public void sendNewPosition(FSBPosition position) throws IOException
	{
		if (this.clientSocket != null)
		{
			System.out.println("Sending new position data");
			this.connOutStream.writeUTF(position.toString());
		}
		else
		{
			System.err.println("No connection established. Can NOT send data");
		}
	}
}
