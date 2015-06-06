package de.gymolching.fsb.tests;

import static org.junit.Assert.*;

import java.io.IOException;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import de.gymolching.fsb.api.FSBPosition;
import de.gymolching.fsb.client.implementation.FSBClient;
import de.gymolching.fsb.server.implementation.FSBServer;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class FSBNetworkTests
{
	private FSBServer server;
	private FSBClient client;

	@Test
	public void testConnect() throws IOException, InterruptedException
	{
		this.server = new FSBServer(666);
		this.client = new FSBClient();
		this.client.connect("localhost", 666);
		
		this.client.disconnect();
		this.server.stopServer();
	}

	@Test
	public void testSendNewPosition() throws IOException, InterruptedException
	{
		this.server = new FSBServer(666);
		this.client = new FSBClient();
		this.client.connect("localhost", 666);
		
		FSBPosition testPosition = new FSBPosition(1, 2, 3, 4, 5, 6);
		this.client.sendNewPosition(testPosition);
		assertEquals(testPosition, this.server.getMostRecentPositionUpdate());
		
		this.client.disconnect();
		this.server.stopServer();
	}
}
