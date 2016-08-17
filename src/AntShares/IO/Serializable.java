package AntShares.IO;

import java.io.IOException;

/**
 *  Ϊ���л��ṩһ���ӿ�
 */
public interface Serializable
{
    /**
     *  ���л�
     *  <param name="writer">������л���Ľ��</param>
     * @throws IOException 
     */
    void serialize(BinaryWriter writer) throws IOException;
    
    /**
     *  �����л�
     *  <param name="reader">������Դ</param>
     * @throws IOException 
     */
    void deserialize(BinaryReader reader) throws IOException;
}