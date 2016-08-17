package AntShares.Core;

import AntShares.UInt160;
import AntShares.Core.Scripts.Script;
import AntShares.IO.*;

/**
 *  Ϊ��Ҫǩ���������ṩһ���ӿ�
 */
public interface Signable extends Serializable
{
    /**
     *  ������֤�ö���Ľű��б�
     */
    Script[] getScripts();

    /**
     *  �����л�δǩ��������
     *  <param name="reader">������Դ</param>
     */
    void deserializeUnsigned(BinaryReader reader);

    /**
     *  ���л�δǩ��������
     *  <param name="writer">������л���Ľ��</param>
     */
    void serializeUnsigned(BinaryWriter writer);

    /**
     *  �����ҪУ��Ľű�Hashֵ
     *  <returns>������ҪУ��Ľű�Hashֵ</returns>
     */
    UInt160[] getScriptHashesForVerifying();

}
