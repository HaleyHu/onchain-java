package AntShares.Core.Scripts;

import AntShares.UInt160;

public class Helper
{
    /**
     *  ����ű���ɢ��ֵ����ʹ��sha256��Ȼ���ټ���һ��ripemd160
     *  @param script Ҫ����ɢ��ֵ�Ľű�
     *  @return ���ؽű���ɢ��ֵ
     */
    public static UInt160 ToScriptHash(byte[] script)
    {
        // TODO
        //return new UInt160(script.Sha256().RIPEMD160());
        return new UInt160();
    }
}
