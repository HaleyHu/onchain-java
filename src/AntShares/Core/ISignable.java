package AntShares.Core;

import java.io.Serializable;

import AntShares.UInt160;

/**
 *  Ϊ��Ҫǩ���������ṩһ���ӿ�
 */
public interface ISignable extends Serializable
{
    /**
     *  ������֤�ö���Ľű��б�
     */
    //Script[] Scripts { get; set; }

    /**
     *  �����л�δǩ��������
     *  <param name="reader">������Դ</param>
     */
    //void DeserializeUnsigned(BinaryReader reader);

    /**
     *  ���л�δǩ��������
     *  <param name="writer">������л���Ľ��</param>
     */
    //void SerializeUnsigned(BinaryWriter writer);

    /**
     *  �����ҪУ��Ľű�Hashֵ
     *  <returns>������ҪУ��Ľű�Hashֵ</returns>
     */
    UInt160[] GetScriptHashesForVerifying();

}
