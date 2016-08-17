package AntShares.Core;

/**
 * �ʲ����
 */
public enum AssetType
{
    CreditFlag(0x40),
    DutyFlag(0x80),

    /**
     * С�Ϲ�
     */
    AntShare(0x00),

    /**
     * С�ϱ�
     */
    AntCoin(0x01),

    /**
     * ����
     */
    Currency(0x08),

    /**
     * ��Ȩ
     */
    Share(DutyFlag.getByte() | 0x10),

    Invoice(DutyFlag.getByte() | 0x18),

    Token(CreditFlag.getByte() | 0x20);

    private byte value;

    AssetType(int v)
    {
        value = (byte)v;
    }

    public byte getByte()
    {
        return value;
    }
}
