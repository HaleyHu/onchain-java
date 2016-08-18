package AntShares.Core;

import java.io.IOException;

import AntShares.UInt160;
import AntShares.IO.*;

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

    /**
     *  ���л�δǩ��������
     *  <param name="writer">������л���Ľ��</param>
     * @throws IOException 
     */
    void serializeUnsigned(BinaryWriter writer) throws IOException;

    /**
     *  �����ҪУ��Ľű�Hashֵ
     *  <returns>������ҪУ��Ľű�Hashֵ</returns>
     */
    UInt160[] getScriptHashesForVerifying();
}
