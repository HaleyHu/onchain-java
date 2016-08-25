package AntShares.Core;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.math.BigInteger;

import org.bouncycastle.crypto.params.ECPrivateKeyParameters;
import org.bouncycastle.crypto.signers.ECDSASigner;
import org.bouncycastle.util.BigIntegers;

import AntShares.UInt160;
import AntShares.Cryptography.*;
import AntShares.IO.*;
import AntShares.Wallets.Account;

/**
 *  Ϊ��Ҫǩ���������ṩһ���ӿ�
 */
public interface Signable extends Serializable
{
    /**
     *  �����л�δǩ��������
     *  <param name="reader">������Դ</param>
     * @throws IOException 
     */
    void deserializeUnsigned(BinaryReader reader) throws IOException;
    
    default byte[] getHashData()
    {
    	try (ByteArrayOutputStream ms = new ByteArrayOutputStream())
    	{
	    	try (BinaryWriter writer = new BinaryWriter(ms))
	        {
	            serializeUnsigned(writer);
	            writer.flush();
	            return ms.toByteArray();
	        }
    	}
    	catch (IOException ex)
    	{
    		throw new UnsupportedOperationException(ex);
    	}
    }

    /**
     *  �����ҪУ��Ľű�Hashֵ
     *  <returns>������ҪУ��Ľű�Hashֵ</returns>
     */
    UInt160[] getScriptHashesForVerifying();
    
    /**
     *  ���л�δǩ��������
     *  <param name="writer">������л���Ľ��</param>
     * @throws IOException 
     */
    void serializeUnsigned(BinaryWriter writer) throws IOException;
    
    default byte[] sign(Account account)
    {
    	//TODO
//    	try (account.decrypt())
//    	{
	    	ECDSASigner signer = new ECDSASigner();
	    	signer.init(true, new ECPrivateKeyParameters(new BigInteger(1, account.PrivateKey), ECC.secp256r1));
	    	BigInteger[] bi = signer.generateSignature(Digest.sha256(getHashData()));
	    	byte[] signature = new byte[64];
	    	System.arraycopy(BigIntegers.asUnsignedByteArray(32, bi[0]), 0, signature, 0, 32);
	    	System.arraycopy(BigIntegers.asUnsignedByteArray(32, bi[1]), 0, signature, 32, 32);
	    	return signature;
//    	}
    }
}
