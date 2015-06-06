package de.gymolching.fsb.tests;

import static org.junit.Assert.*;

import java.io.IOException;

import org.junit.Test;

import de.gymolching.fsb.api.FSBPosition;
import de.gymolching.fsb.client.implementation.FSBClient;
import de.gymolching.fsb.server.implementation.FSBServer;

public class FSBNetworkTests
{
	@Test
	public void testConnect() throws IOException, InterruptedException
	{
		System.out.println("testConnect()");
		System.out.println("====================================================================");
		FSBServer server = new FSBServer(668, true);
		FSBClient client = new FSBClient(true);
		client.connect("localhost", 668);

		client.disconnect();
		server.stop();
		System.out.println("====================================================================\n\n");
	}

	@Test
	public void testSendNewPosition() throws IOException, InterruptedException
	{
		System.out.println("testSendNewPosition()");
		System.out.println("====================================================================");
		FSBServer server = new FSBServer(666, true);
		FSBClient client = new FSBClient(true);
		client.connect("localhost", 666);

		FSBPosition testPosition = new FSBPosition(1, 2, 3, 4, 5, 6);
		client.sendNewPosition(testPosition);
		assertEquals(testPosition, server.getMostRecentPositionUpdate());
		testPosition = new FSBPosition(2, 1, 4, 3, 6, 5);
		client.sendNewPosition(testPosition);
		assertEquals(testPosition, server.getMostRecentPositionUpdate());

		client.disconnect();
		server.stop();

		System.out.println("====================================================================\n\n");
	}

	@Test
	public void testDisconnectReconnect() throws IOException, InterruptedException
	{
		System.out.println("testDisconnectReconnect()");
		System.out.println("====================================================================");
		FSBServer server = new FSBServer(667, true);
		FSBClient client = new FSBClient(true);
		client.connect("localhost", 667);
		client.disconnect();
		Thread.sleep(2000);
		client.connect("localhost", 667);
		client.disconnect();
		server.stop();

		System.out.println("====================================================================\n\n");
	}

	@Test
	public void testMultipleClientConnect() throws IOException, InterruptedException
	{
		System.out.println("testMultipleClientConnect()");
		System.out.println("====================================================================");
		FSBServer server = new FSBServer(669, true);
		FSBClient client = new FSBClient(true);
		FSBClient client2 = new FSBClient(true);
		client.connect("localhost", 669);
		client2.connect("localhost", 669);

		client.disconnect();
		client2.disconnect();
		server.stop();

		System.out.println("====================================================================\n\n");
	}
}
