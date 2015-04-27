package com.fsb.api;

import com.fsb.implementation.FSBController;

public class FSBControllerFactory
{
	public static FSBControllerInterface getController()
	{
		return new FSBController();
	}
}
