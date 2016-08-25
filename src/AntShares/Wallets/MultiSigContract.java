package AntShares.Wallets;

import java.math.BigInteger;
import java.util.Arrays;

import org.bouncycastle.math.ec.ECPoint;

import AntShares.UInt160;
import AntShares.Core.Scripts.ScriptBuilder;
import AntShares.IO.*;

/**
 *  �෽ǩ����Լ���ú�Լ��Ҫָ����N���˻�������M���˻�ǩ���������Ч
 */
public class MultiSigContract extends Contract
{
    private int m;
    private ECPoint[] publicKeys;

    public MultiSigContract(UInt160 publicKeyHash, int m2, ECPoint[] publicKeys2) {
        // TODO Auto-generated constructor stub
        RedeemScript = CreateMultiSigRedeemScript(m, publicKeys);
        PublicKeyHash = publicKeyHash;
        m = m2;
        publicKeys = publicKeys2;
    }

    /**
     *  ��Լ����ʽ�����б�
     */
    @Override public ContractParameterType[] getParameterList() {
        // TODO
        //return Enumerable.Repeat(ContractParameterType.Signature, m).ToArray();
        return null;
    }

    /**
     *  ��ָ����N����Կ����һ��MultiSigContractʵ������ָ��������ҪM���˻���ǩ��
     *  <param name="publicKeyHash">��Լ�������˻�</param>
     *  <param name="m">һ���������ú�Լ������Ҫ������������ǩ��������Ч</param>
     *  <param name="publicKeys">��Կ�б��ú�Լ��Ҫ���б�������m���˻�ǩ���������Ч</param>
     *  <returns>����һ���෽ǩ����Լ</returns>
     */
    public static MultiSigContract Create(UInt160 publicKeyHash, int m, ECPoint[] publicKeys)
    {
        return new MultiSigContract(publicKeyHash, m, publicKeys);
    }

    /**
     *  ��ָ����N����Կ����һ��MultiSigContract��Լ�Ľű�����ָ��������ҪM���˻���ǩ��
     *  <param name="m">һ���������ú�Լ������Ҫ������������ǩ��������Ч</param>
     *  <param name="publicKeys">��Կ�б��ú�Լ��Ҫ���б�������m���˻�ǩ���������Ч</param>
     *  <returns>����һ�ζ෽ǩ����Լ�Ľű�����</returns>
     */
    public static byte[] CreateMultiSigRedeemScript(int m, ECPoint[] publicKeys)
    {
        if (!(1 <= m && m <= publicKeys.length && publicKeys.length <= 1024))
            throw new IllegalArgumentException();
        ScriptBuilder sb = new ScriptBuilder();
        sb.push(BigInteger.valueOf(m));
        for (ECPoint publicKey : Arrays.stream(publicKeys).sorted().toArray(ECPoint[]::new))
        {
            sb.push(publicKey.getEncoded(true));
        }
        sb.push(BigInteger.valueOf(publicKeys.length));
        // TODO
        //sb.Add(ScriptOp.OP_CHECKMULTISIG);
        return sb.toArray();
    }

    /**
     *  �����л�
     *  <param name="reader">�����л���������Դ</param>
     */
    @Override public void deserialize(BinaryReader reader)
    {
        // TODO
//        m = (int)reader.ReadVarInt(Integer.MAX_VALUE);
//        publicKeys = new ECPoint[reader.ReadVarInt(0x10000000)];
//        for (int i = 0; i < publicKeys.Length; i++)
//        {
//            publicKeys[i] = ECPoint.DeserializeFrom(reader, ECCurve.Secp256r1);
//        }
//        RedeemScript = CreateMultiSigRedeemScript(m, publicKeys);
//        PublicKeyHash = reader.ReadSerializable<UInt160>();
    }

    /**
     *  ���л�
     *  <param name="writer">������л���Ľ��</param>
     */
    @Override public void serialize(BinaryWriter writer)
    {
//        writer.WriteVarInt(m);
//        writer.Write(publicKeys);
//        writer.Write(PublicKeyHash);
    }
}