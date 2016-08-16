package AntShares.Core;

import java.io.InputStream;
import java.io.OutputStream;

import AntShares.UInt160;
import AntShares.Core.Scripts.Script;
import AntShares.IO.Serializable;

/**
 *  Ϊ��Ҫǩ���������ṩһ���ӿ�
 */
public interface ISignable extends Serializable
{
    /**
     *  ������֤�ö���Ľű��б�
     */
    Script[] getScripts();

    void setScripts(Script[] scripts);

    /**
     *  �����л�δǩ��������
     *  <param name="reader">������Դ</param>
     */
    void DeserializeUnsigned(InputStream reader);

    /**
     *  ���л�δǩ��������
     *  <param name="writer">������л���Ľ��</param>
     */
    void SerializeUnsigned(OutputStream writer);

    /**
     *  �����ҪУ��Ľű�Hashֵ
     *  <returns>������ҪУ��Ľű�Hashֵ</returns>
     */
    UInt160[] GetScriptHashesForVerifying();

}
