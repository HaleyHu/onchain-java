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

    /**
     *  ��Լ����ʽ�����б�
     */
    @Override
    public ContractParameterType[] parameterList()
    {
        return new ContractParameterType[] { ContractParameterType.Signature };
    }

    /**
     *  ��ָ���Ĺ�Կ����һ��SignatureContractʵ��
     *  <param name="publicKey">���ڴ���SignatureContractʵ���Ĺ�Կ</param>
     *  <returns>����һ����ǩ����Լ</returns>
     */
    public static SignatureContract create(ECPoint publicKey)
    {
    	SignatureContract contract = new SignatureContract();
    	contract.redeemScript = createSignatureRedeemScript(publicKey);
    	contract.publicKeyHash = Script.toScriptHash(publicKey.getEncoded(true));
    	contract.publicKey = publicKey;
    	return contract;
    }

    /**
     *  ��ָ���Ĺ�Կ����һ��SignatureContract��Լ�Ľű�
     *  <param name="publicKey">���ڴ���SignatureContract��Լ�ű��Ĺ�Կ</param>
     *  <returns>����һ�μ�ǩ����Լ�Ľű�����</returns>
     */
    public static byte[] createSignatureRedeemScript(ECPoint publicKey)
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
     * @throws IOException 
     */
    @Override
    public void deserialize(BinaryReader reader) throws IOException
    {
        publicKey = reader.readECPoint();
        redeemScript = createSignatureRedeemScript(publicKey);
        publicKeyHash = Script.toScriptHash(publicKey.getEncoded(true));
    }

    /**
     *  ���л�
     *  <param name="writer">������л���Ľ��</param>
     * @throws IOException 
     */
    @Override
    public void serialize(BinaryWriter writer) throws IOException
    {
    	writer.writeECPoint(publicKey);
    }
}
