package com.fsb.implementation;

/**
 * Implements Networking stuff
 * @author Dominik
 *
 */
public class FSBClient
{
	private FSBPositionContainerInterface positionContainer;
	
	public FSBClient(FSBPositionContainerInterface positionContainer)
	{
		this.positionContainer = positionContainer;
		
		// TODO: connect to pi, open listen thread for pi's "updatePosition()" requests
	}
}
