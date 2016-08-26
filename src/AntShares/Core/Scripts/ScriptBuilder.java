package AntShares.Core.Scripts;

import java.io.*;
import java.math.BigInteger;
import java.nio.*;

import AntShares.UIntBase;

/**
 *  �ű�������
 */
public class ScriptBuilder implements AutoCloseable
{
    private ByteArrayOutputStream ms = new ByteArrayOutputStream();

    /**
     *  ��Ӳ�����
     *  <param name="op">������</param>
     *  <returns>������Ӳ�����֮��Ľű�������</returns>
     */
    public ScriptBuilder add(ScriptOp op)
    {
        return add(op.getByte());
    }

    private ScriptBuilder add(byte op)
    {
        ms.write(op);
        return this;
    }

    /**
     *  ���һ�νű�
     *  <param name="script">�ű�</param>
     *  <returns>������ӽű�֮��Ľű�������</returns>
     */
    public ScriptBuilder add(byte[] script)
    {
        ms.write(script, 0, script.length);
        return this;
    }

    @Override
    public void close()
    {
        try
        {
			ms.close();
		}
        catch (IOException ex)
        {
			throw new RuntimeException(ex);
		}
    }

    /**
     *  ���һ�νű����ýű��������ǽ�һ������ѹ��ջ��
     *  <param name="number">Ҫѹ��ջ�е�����</param>
     *  <returns>������ӽű�֮��Ľű�������</returns>
     */
    public ScriptBuilder push(BigInteger number)
    {
    	if (number.equals(BigInteger.ONE.negate()))
    		return add(ScriptOp.OP_1NEGATE);
    	if (number.equals(BigInteger.ZERO))
    		return add(ScriptOp.OP_0);
    	if (number.compareTo(BigInteger.ZERO) > 0 && number.compareTo(BigInteger.valueOf(16)) <= 0)
    		return add((byte)(ScriptOp.OP_1.getByte() - 1 + number.byteValue()));
        return push(number.toByteArray());
    }

    /**
     *  ���һ�νű����ýű��������ǽ�һ���ֽ�����ѹ��ջ��
     *  <param name="data">Ҫѹ��ջ�е��ֽ�����</param>
     *  <returns>������ӽű�֮��Ľű�������</returns>
     */
    public ScriptBuilder push(byte[] data)
    {
        if (data == null)
            throw new NullPointerException();
        if (data.length <= (int)ScriptOp.OP_PUSHBYTES75.getByte())
        {
            ms.write((byte)data.length);
            ms.write(data, 0, data.length);
        }
        else if (data.length < 0x100)
        {
            add(ScriptOp.OP_PUSHDATA1);
            ms.write((byte)data.length);
            ms.write(data, 0, data.length);
        }
        else if (data.length < 0x10000)
        {
            add(ScriptOp.OP_PUSHDATA2);
			ms.write(ByteBuffer.allocate(2).order(ByteOrder.LITTLE_ENDIAN).putShort((short)data.length).array(), 0, 2);
            ms.write(data, 0, data.length);
        }
        else if (data.length < 0x100000000L)
        {
            add(ScriptOp.OP_PUSHDATA4);
            ms.write(ByteBuffer.allocate(4).order(ByteOrder.LITTLE_ENDIAN).putInt(data.length).array(), 0, 4);
            ms.write(data, 0, data.length);
        }
        else
        {
            throw new IllegalArgumentException();
        }
        return this;
    }

    /**
     *  ���һ�νű����ýű��������ǽ�һ��ɢ��ֵѹ��ջ��
     *  <param name="hash">Ҫѹ��ջ�е�ɢ��ֵ</param>
     *  <returns>������ӽű�֮��Ľű�������</returns>
     */
    public ScriptBuilder push(UIntBase hash)
    {
        return push(hash.toArray());
    }

    /**
     *  ��ȡ�ű��������а����Ľű�����
     */
    public byte[] toArray()
    {
        return ms.toByteArray();
    }
}
