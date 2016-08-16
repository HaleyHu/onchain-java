package AntShares.Wallets;

import java.io.InputStream;
import java.io.OutputStream;

import AntShares.Core.Scripts.ScriptBuilder;
import AntShares.Core.Scripts.ScriptOp;
import AntShares.Cryptography.ECC.ECPoint;

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
    	ScriptBuilder sb = new ScriptBuilder();
        sb.Push(publicKey.EncodePoint(true));
        sb.Add(ScriptOp.OP_CHECKSIG);
        return sb.ToArray();
    }

    /**
     *  �����л�
     *  <param name="reader">�����л���������Դ</param>
     */
    @Override public void Deserialize(InputStream reader)
    {
    	// TODO
        //publicKey = ECPoint.DeserializeFrom(reader, ECCurve.Secp256r1);
        //RedeemScript = CreateSignatureRedeemScript(publicKey);
        //PublicKeyHash = publicKey.EncodePoint(true).ToScriptHash();
    }

    /**
     *  ���л�
     *  <param name="writer">������л���Ľ��</param>
     */
    @Override public void Serialize(OutputStream writer)
    {
    	// TODO
        //writer.Write(publicKey);
    	publicKey.Serialize(writer);
    }
}
