package AntShares.Core;

import java.io.IOException;

import AntShares.UInt256;
import AntShares.IO.*;

/**
 *  ��������
 */
public class TransactionInput implements Serializable
{
    /**
     *  ���ý��׵�ɢ��ֵ
     */
    public UInt256 prevHash;
    /**
     *  ���ý������������
     */
    public short prevIndex; // unsigned short

    /**
     *  �Ƚϵ�ǰ������ָ�������Ƿ����
     *  <param name="obj">Ҫ�ȽϵĶ���</param>
     *  <returns>���ض����Ƿ����</returns>
     */
    @Override
    public boolean equals(Object obj)
    {
        if (this == obj) return true;
        if (null == obj) return false;
        if (!(obj instanceof TransactionInput)) return false;
        TransactionInput other = (TransactionInput) obj;
        return prevHash.equals(other.prevHash) && prevIndex == other.prevIndex;
    }

    /**
     *  ��ö����HashCode
     *  <returns>���ض����HashCode</returns>
     */
    @Override
    public int hashCode()
    {
        return prevHash.hashCode() + prevIndex;
    }

	@Override
	public void serialize(BinaryWriter writer) throws IOException
	{
		writer.writeSerializable(prevHash);
		writer.writeShort(prevIndex);
	}

	@Override
	public void deserialize(BinaryReader reader) throws IOException
	{
		try
		{
			prevHash = reader.readSerializable(UInt256.class);
			prevIndex = reader.readShort();
		}
		catch (InstantiationException | IllegalAccessException e)
		{
		}
	}

    /**
     *  ����������ת��Ϊjson����
     *  <returns>����json����</returns>
     */
// TODO
//    public JObject ToJson()
//    {
//        JObject json = new JObject();
//        json["txid"] = PrevHash.ToString();
//        json["vout"] = PrevIndex;
//        return json;
//    }
}