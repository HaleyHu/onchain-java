package AntShares.Core;

import java.io.IOException;

import AntShares.*;
import AntShares.IO.*;

/**
 *  ���ֳɽ��Ķ���
 */
public class SplitOrder implements Serializable
{
    /**
     *  ���������������
     */
    public Fixed8 amount;
    /**
     *  �۸�
     */
    public Fixed8 price;
    /**
     *  ί���˵ĺ�Լɢ��
     */
    public UInt160 client;
    
	@Override
	public void serialize(BinaryWriter writer) throws IOException
	{
        writer.writeSerializable(amount);
        writer.writeSerializable(price);
        writer.writeSerializable(client);
	}
	
	@Override
	public void deserialize(BinaryReader reader) throws IOException
	{
        try
        {
			amount = reader.readSerializable(Fixed8.class);
	        if (amount.equals(Fixed8.ZERO)) throw new IOException();
	        if (amount.getData() % 10000 != 0) throw new IOException();
	        price = reader.readSerializable(Fixed8.class);
	        if (price.compareTo(Fixed8.ZERO) <= 0) throw new IOException();
	        if (price.getData() % 10000 != 0) throw new IOException();
	        client = reader.readSerializable(UInt160.class);
		}
        catch (InstantiationException | IllegalAccessException ex)
        {
        	throw new IOException();
		}
	}
}
