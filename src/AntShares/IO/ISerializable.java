package AntShares.IO;

import java.io.InputStream;
import java.io.OutputStream;

/**
 *  Ϊ���л��ṩһ���ӿ�
 */
public interface ISerializable
{
    /**
     *  ���л�
     *  <param name="writer">������л���Ľ��</param>
     */
    void Serialize(OutputStream writer);
    
    /**
     *  �����л�
     *  <param name="reader">������Դ</param>
     */
    void Deserialize(InputStream reader);
}