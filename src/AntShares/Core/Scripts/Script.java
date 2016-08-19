package AntShares.Core.Scripts;

import java.io.IOException;

import AntShares.UInt160;
import AntShares.IO.*;

/**
 *  �ű�
 */
public class Script implements Serializable
{
    public byte[] StackScript;
    public byte[] RedeemScript;

    @Override
    public void deserialize(BinaryReader reader) throws IOException
    {
        StackScript = reader.readVarBytes();
        RedeemScript = reader.readVarBytes();
    }

    @Override
    public void serialize(BinaryWriter writer) throws IOException
    {
        writer.writeVarBytes(StackScript);
        writer.writeVarBytes(RedeemScript);
    }

//    /**
//     *  ���json����
//     *  <returns>����json����</returns>
//     */
//    public JObject ToJson()
//    {
//        JObject json = new JObject();
//        json["stack"] = StackScript.ToHexString();
//        json["redeem"] = RedeemScript.ToHexString();
//        return json;
//    }

    public static UInt160 toScriptHash(byte[] script)
    {
    	//TODO
    	return new UInt160();
    }
}
