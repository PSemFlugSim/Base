package de.gymolching.fsb.implementation;

import java.util.*;

import de.gymolching.fsb.api.*;

public class FSBController implements FSBControllerInterface, FSBPositionContainerInterface
{
	private ArrayList<Position> positionBuffer;

	private FSBClient serverClient;

	public FSBController()
	{
		this.positionBuffer = new ArrayList<>();
		
		this.serverClient = new FSBClient(this);
	}

	@Override
	public void updatePosition(Position position)
	{
		this.positionBuffer.add(position);
	}

	/** this method is used by FSBPiClient to answer the Pi's position update request */
	public Position getPositionUpdate()
	{
		Position position = this.positionBuffer.get(this.positionBuffer.size() - 1);
		this.positionBuffer.remove(this.positionBuffer.size() - 1);
		return position;
	}

}
