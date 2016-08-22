package AntShares.IO;

import java.io.*;

/**
 *  Ϊ���л��ṩһ���ӿ�
 */
public interface Serializable
{    
    /**
     *  �����л�
     *  <param name="reader">������Դ</param>
     * @throws IOException 
     */
    void deserialize(BinaryReader reader) throws IOException;
    
    /**
     *  ���л�
     *  <param name="writer">������л���Ľ��</param>
     * @throws IOException 
     */
    void serialize(BinaryWriter writer) throws IOException;

    default byte[] toArray()
    {
        try (ByteArrayOutputStream ms = new ByteArrayOutputStream())
        {
	        try (BinaryWriter writer = new BinaryWriter(ms))
	        {
	            serialize(writer);
	            writer.flush();
	            return ms.toByteArray();
	        }
        }
        catch (IOException ex)
        {
			throw new UnsupportedOperationException(ex);
		}
    }
    
    static <T extends Serializable> T from(byte[] value, Class<T> t) throws InstantiationException, IllegalAccessException
    {
    	try (ByteArrayInputStream ms = new ByteArrayInputStream(value))
    	{
    		try (BinaryReader reader = new BinaryReader(ms))
    		{
    			return reader.readSerializable(t);
    		}
    	}
    	catch (IOException ex)
    	{
			throw new IllegalArgumentException(ex);
		}
    }
}
