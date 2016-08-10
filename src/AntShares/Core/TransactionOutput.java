package AntShares.Core;

import java.io.Serializable;

import AntShares.Fixed8;
import AntShares.UInt160;
import AntShares.UInt256;

/**
 *  �������
 */
public class TransactionOutput implements Serializable
{
	private static final long serialVersionUID = -8486024867292581586L;
	/**
     *  �ʲ����
     */
    public UInt256 AssetId;
    /**
     *  ���
     */
    public Fixed8 Value;
    /**
     *  �տ��ַ
     */
    public UInt160 ScriptHash;

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
