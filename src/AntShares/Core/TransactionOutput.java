package AntShares.Core;

import java.io.IOException;

import AntShares.*;
import AntShares.IO.*;
import AntShares.IO.Json.*;
import AntShares.Wallets.Wallet;

/**
 *  �������
 */
public class TransactionOutput implements Serializable
{
    /**
     *  �ʲ����
     */
    public UInt256 assetId;
    /**
     *  ���
     */
    public Fixed8 value;
    /**
     *  �տ��ַ
     */
    public UInt160 scriptHash;
    
	@Override
	public void serialize(BinaryWriter writer) throws IOException
	{
		writer.writeSerializable(assetId);
		writer.writeSerializable(value);
		writer.writeSerializable(scriptHash);
	}
	
	@Override
	public void deserialize(BinaryReader reader) throws IOException
	{
		try
		{
			assetId = reader.readSerializable(UInt256.class);
			value = reader.readSerializable(Fixed8.class);
			if (value.compareTo(Fixed8.ZERO) <= 0)
				throw new IOException();
			scriptHash = reader.readSerializable(UInt160.class);
		}
		catch (InstantiationException | IllegalAccessException e)
		{
		}
	}

    /**
     *  ���������ת��Ϊjson����
     *  <param name="index">�ý�������ڽ����е�����</param>
     *  <returns>����json����</returns>
     */
    public JObject json(/*ushort*/int index)
    {
        JObject json = new JObject();
        json.set("n", new JNumber(index));
        json.set("asset", new JString(assetId.toString()));
        json.set("value", new JString(value.toString()));
        json.set("high", new JNumber(value.getData() >> 32));
        json.set("low", new JNumber(value.getData() & 0xffffffff));
        json.set("address", new JString(Wallet.toAddress(scriptHash)));
        return json;
    }
}
