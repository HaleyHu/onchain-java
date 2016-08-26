package AntShares.Wallets;

import java.io.IOException;
import java.math.BigInteger;
import java.util.Arrays;
import java.util.stream.Stream;

import org.bouncycastle.math.ec.ECPoint;

import AntShares.UInt160;
import AntShares.Core.Scripts.*;
import AntShares.IO.*;

/**
 *  �෽ǩ����Լ���ú�Լ��Ҫָ����N���˻�������M���˻�ǩ���������Ч
 */
public class MultiSigContract extends Contract
{
    private int m;
    private ECPoint[] publicKeys;

    /**
     *  ��Լ����ʽ�����б�
     */
    @Override
    public ContractParameterType[] parameterList()
    {
    	return Stream.generate(() -> ContractParameterType.Signature).limit(m).toArray(ContractParameterType[]::new);
    }

    /**
     *  ��ָ����N����Կ����һ��MultiSigContractʵ������ָ��������ҪM���˻���ǩ��
     *  <param name="publicKeyHash">��Լ�������˻�</param>
     *  <param name="m">һ���������ú�Լ������Ҫ������������ǩ��������Ч</param>
     *  <param name="publicKeys">��Կ�б��ú�Լ��Ҫ���б�������m���˻�ǩ���������Ч</param>
     *  <returns>����һ���෽ǩ����Լ</returns>
     */
    public static MultiSigContract create(UInt160 publicKeyHash, int m, ECPoint ...publicKeys)
    {
    	MultiSigContract contract = new MultiSigContract();
    	contract.redeemScript = createMultiSigRedeemScript(m, publicKeys);
    	contract.publicKeyHash = publicKeyHash;
    	contract.m = m;
    	contract.publicKeys = publicKeys;
    	return contract;
    }

    /**
     *  ��ָ����N����Կ����һ��MultiSigContract��Լ�Ľű�����ָ��������ҪM���˻���ǩ��
     *  <param name="m">һ���������ú�Լ������Ҫ������������ǩ��������Ч</param>
     *  <param name="publicKeys">��Կ�б��ú�Լ��Ҫ���б�������m���˻�ǩ���������Ч</param>
     *  <returns>����һ�ζ෽ǩ����Լ�Ľű�����</returns>
     */
    public static byte[] createMultiSigRedeemScript(int m, ECPoint ...publicKeys)
    {
        if (!(1 <= m && m <= publicKeys.length && publicKeys.length <= 1024))
            throw new IllegalArgumentException();
        try (ScriptBuilder sb = new ScriptBuilder())
        {
	        sb.push(BigInteger.valueOf(m));
	        for (ECPoint publicKey : Arrays.stream(publicKeys).sorted().toArray(ECPoint[]::new))
	        {
	            sb.push(publicKey.getEncoded(true));
	        }
	        sb.push(BigInteger.valueOf(publicKeys.length));
	        sb.add(ScriptOp.OP_CHECKMULTISIG);
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
        m = (int)reader.readVarInt(Integer.MAX_VALUE);
        publicKeys = new ECPoint[(int) reader.readVarInt(0x10000000)];
        for (int i = 0; i < publicKeys.length; i++)
        {
            publicKeys[i] = reader.readECPoint();
        }
        redeemScript = createMultiSigRedeemScript(m, publicKeys);
        try
        {
			publicKeyHash = reader.readSerializable(UInt160.class);
		}
        catch (InstantiationException | IllegalAccessException ex)
        {
			throw new RuntimeException(ex);
		}
    }

    /**
     *  ���л�
     *  <param name="writer">������л���Ľ��</param>
     * @throws IOException 
     */
    @Override
    public void serialize(BinaryWriter writer) throws IOException
    {
        writer.writeVarInt(m);
        writer.writeVarInt(publicKeys.length);
        for (ECPoint p : publicKeys)
        	writer.writeECPoint(p);
        writer.writeSerializable(publicKeyHash);
    }
}
