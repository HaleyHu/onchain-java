package AntShares.Wallets;

import java.io.IOException;

import org.bouncycastle.math.ec.ECPoint;

import AntShares.Core.Scripts.*;
import AntShares.IO.*;

/**
 *  ��ǩ����Լ���ú�Լֻ��Ҫһ��ָ���˻���ǩ��������Ч
 */
public class SignatureContract extends Contract
{
    private ECPoint publicKey;

    public SignatureContract(ECPoint publicKey2) {
        RedeemScript = CreateSignatureRedeemScript(publicKey2);
        // TODO
        //PublicKeyHash = publicKey.EncodePoint(true).ToScriptHash(),
        publicKey = publicKey2;
    }

    /**
     *  ��Լ����ʽ�����б�
     */
    @Override
    public ContractParameterType[] getParameterList()
    {
        return new ContractParameterType[] { ContractParameterType.Signature };
    }

    /**
     *  ��ָ���Ĺ�Կ����һ��SignatureContractʵ��
     *  <param name="publicKey">���ڴ���SignatureContractʵ���Ĺ�Կ</param>
     *  <returns>����һ����ǩ����Լ</returns>
     */
    public static SignatureContract Create(ECPoint publicKey)
    {
        return new SignatureContract(publicKey);
    }

    /**
     *  ��ָ���Ĺ�Կ����һ��SignatureContract��Լ�Ľű�
     *  <param name="publicKey">���ڴ���SignatureContract��Լ�ű��Ĺ�Կ</param>
     *  <returns>����һ�μ�ǩ����Լ�Ľű�����</returns>
     */
    public static byte[] CreateSignatureRedeemScript(ECPoint publicKey)
    {
        try (ScriptBuilder sb = new ScriptBuilder())
        {
	        sb.push(publicKey.getEncoded(true));
	        sb.add(ScriptOp.OP_CHECKSIG);
	        return sb.toArray();
        }
    }

    /**
     *  �����л�
     *  <param name="reader">�����л���������Դ</param>
     */
    @Override public void deserialize(BinaryReader reader)
    {
        // TODO
        //publicKey = ECPoint.DeserializeFrom(reader, ECCurve.Secp256r1);
        //RedeemScript = CreateSignatureRedeemScript(publicKey);
        //PublicKeyHash = publicKey.EncodePoint(true).ToScriptHash();
    }

    /**
     *  ���л�
     *  <param name="writer">������л���Ľ��</param>
     * @throws IOException 
     */
    @Override public void serialize(BinaryWriter writer) throws IOException
    {
    	writer.writeECPoint(publicKey);
    }
}
