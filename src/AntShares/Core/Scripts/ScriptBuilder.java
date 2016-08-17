package AntShares.Core.Scripts;

import java.io.ByteArrayOutputStream;
import java.math.BigInteger;

import AntShares.UIntBase;

/**
 *  �ű�������
 */
public class ScriptBuilder // TODO : IDisposable
{
    private ByteArrayOutputStream ms = new ByteArrayOutputStream();

    /**
     *  ��Ӳ�����
     *  <param name="op">������</param>
     *  <returns>������Ӳ�����֮��Ľű�������</returns>
     */
    public ScriptBuilder Add(ScriptOp op)
    {
        ms.write(op.getByte());
        return this;
    }

    public ScriptBuilder Add(byte op)
    {
        ms.write(op);
        return this;
    }

    /**
     *  ���һ�νű�
     *  <param name="script">�ű�</param>
     *  <returns>������ӽű�֮��Ľű�������</returns>
     */
    public ScriptBuilder Add(byte[] script)
    {
        ms.write(script, 0, script.length);
        return this;
    }

    public void Dispose()
    {
        //ms.Dispose();
    }

    /**
     *  ���һ�νű����ýű��������ǽ�һ������ѹ��ջ��
     *  <param name="number">Ҫѹ��ջ�е�����</param>
     *  <returns>������ӽű�֮��Ľű�������</returns>
     */
    public ScriptBuilder Push(BigInteger number)
    {
        int n = 0;
        try {
            n = number.intValueExact();
            if (n == -1) return Add(ScriptOp.OP_1NEGATE);
            if (n == 0) return Add(ScriptOp.OP_0);
            if (n > 0 && n <= 16) return Add((byte)(ScriptOp.OP_1.getByte() - 1 + n));
        } catch (ArithmeticException ae) {
            // do nothing.
        }
        return Push(number.toByteArray());
    }

    /**
     *  ���һ�νű����ýű��������ǽ�һ���ֽ�����ѹ��ջ��
     *  <param name="data">Ҫѹ��ջ�е��ֽ�����</param>
     *  <returns>������ӽű�֮��Ľű�������</returns>
     */
    public ScriptBuilder Push(byte[] data)
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
            Add(ScriptOp.OP_PUSHDATA1);
            ms.write((byte)data.length);
            ms.write(data, 0, data.length);
        }
        else if (data.length < 0x10000)
        {
            Add(ScriptOp.OP_PUSHDATA2);
            // TODO
//            ms.write(BitConverter.GetBytes((ushort)data.Length), 0, 2);
//            ms.Write(data, 0, data.Length);
        }
        // TODO
//        else if (data.LongLength < 0x100000000L)
//        {
//            Add(ScriptOp.OP_PUSHDATA4);
//            ms.Write(BitConverter.GetBytes((uint)data.Length), 0, 4);
//            ms.Write(data, 0, data.Length);
//        }
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
    public ScriptBuilder Push(UIntBase hash)
    {
        return Push(hash.toArray());
    }

    /**
     *  ��ȡ�ű��������а����Ľű�����
     */
    public byte[] ToArray()
    {
        return ms.toByteArray();
    }
}
