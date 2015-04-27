package de.gymolching.fsb.api;

import de.gymolching.fsb.implementation.FSBController;

public class FSBControllerFactory
{
	public static FSBControllerInterface getController()
	{
		return new FSBController();
	}
}
