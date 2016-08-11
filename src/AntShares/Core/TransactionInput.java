package AntShares.Core;

import java.io.Serializable;

import AntShares.UInt256;

/**
 *  ��������
 */
public class TransactionInput implements Serializable
{
	private static final long serialVersionUID = -1788728962124438893L;
	/**
     *  ���ý��׵�ɢ��ֵ
     */
    public UInt256 PrevHash;
    /**
     *  ���ý������������
     */
    public short PrevIndex; // TODO ushort -> short ?

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
        return PrevHash.equals(other.PrevHash) && PrevIndex == other.PrevIndex;
    }

    /**
     *  ��ö����HashCode
     *  <returns>���ض����HashCode</returns>
     */
    @Override
    public int hashCode()
    {
        return PrevHash.hashCode() + PrevIndex;
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