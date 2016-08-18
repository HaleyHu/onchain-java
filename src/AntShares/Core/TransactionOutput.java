package AntShares.Core;

import java.io.IOException;

import AntShares.*;
import AntShares.IO.*;

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

// TODO
//    /**
//     *  ���������ת��Ϊjson����
//     *  <param name="index">�ý�������ڽ����е�����</param>
//     *  <returns>����json����</returns>
//     */
//    public JObject ToJson(ushort index)
//    {
//        JObject json = new JObject();
//        json["n"] = index;
//        json["asset"] = AssetId.ToString();
//        json["value"] = Value.ToString();
//        json["high"] = Value.GetData() >> 32;
//        json["low"] = Value.GetData() & 0xffffffff;
//        json["address"] = Wallet.ToAddress(ScriptHash);
//        return json;
//    }
}
