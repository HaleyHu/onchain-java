package AntShares.Core;

/**
 *  ��ʾ�������Ե���;
 */
public class TransactionAttributeUsage
{
    /**
     *  �ⲿ��ͬ��ɢ��ֵ
     */
    public static final byte ContractHash = 0x00;

    /**
     *  ����ECDH��Կ�����Ĺ�Կ���ù�Կ�ĵ�һ���ֽ�Ϊ0x02
     */
    public static final byte ECDH02 = 0x02;
    /**
     *  ����ECDH��Կ�����Ĺ�Կ���ù�Կ�ĵ�һ���ֽ�Ϊ0x03
     */
    public static final byte ECDH03 = 0x03;

    /**
     *  ���ڶԽ��׽��ж������֤
     */
    public static final byte Script = 0x20;

    public static final byte CertUrl = (byte)0x80;
    public static final byte DescriptionUrl = (byte)0x81;
    public static final byte Description = (byte)0x90;

    public static final byte Hash1 = (byte)0xa1;
    public static final byte Hash2 = (byte)0xa2;
    public static final byte Hash3 = (byte)0xa3;
    public static final byte Hash4 = (byte)0xa4;
    public static final byte Hash5 = (byte)0xa5;
    public static final byte Hash6 = (byte)0xa6;
    public static final byte Hash7 = (byte)0xa7;
    public static final byte Hash8 = (byte)0xa8;
    public static final byte Hash9 = (byte)0xa9;
    public static final byte Hash10 = (byte)0xaa;
    public static final byte Hash11 = (byte)0xab;
    public static final byte Hash12 = (byte)0xac;
    public static final byte Hash13 = (byte)0xad;
    public static final byte Hash14 = (byte)0xae;
    public static final byte Hash15 = (byte)0xaf;

    /**
     *  ��ע
     */
    public static final byte Remark = (byte)0xf0;
    public static final byte Remark1 = (byte)0xf1;
    public static final byte Remark2 = (byte)0xf2;
    public static final byte Remark3 = (byte)0xf3;
    public static final byte Remark4 = (byte)0xf4;
    public static final byte Remark5 = (byte)0xf5;
    public static final byte Remark6 = (byte)0xf6;
    public static final byte Remark7 = (byte)0xf7;
    public static final byte Remark8 = (byte)0xf8;
    public static final byte Remark9 = (byte)0xf9;
    public static final byte Remark10 = (byte)0xfa;
    public static final byte Remark11 = (byte)0xfb;
    public static final byte Remark12 = (byte)0xfc;
    public static final byte Remark13 = (byte)0xfd;
    public static final byte Remark14 = (byte)0xfe;
    public static final byte Remark15 = (byte)0xff;
}