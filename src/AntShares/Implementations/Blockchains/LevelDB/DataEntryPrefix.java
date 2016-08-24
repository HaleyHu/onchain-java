package AntShares.Implementations.Blockchains.LevelDB;

public enum DataEntryPrefix // : byte
{
    /**
     *  ����ͷ��
     */
    DATA_HeaderList(0x00),

    /**
     *  ����
     */
    DATA_Block(0x01),

    /**
     *  ����
     */
    DATA_Transaction(0x02),

    /**
     *  ��ǰ���飬�������ĵ�ǰ״̬���������е�������ͳ����Ϣ���ɸ������Լ����е�ǰ�����鹲ͬ����
     */
    SYS_CurrentBlock(0x40),

    /**
     *  �ʲ�����
     */
    IX_Asset(0x81),

    /**
     *  ��ѡ������
     */
    IX_Enrollment(0x84),

    /**
     *  δ��������
     */
    IX_Unspent(0x90),

    /**
     *  δ��ȡС�ϱҵ�С�Ϲ�
     */
    IX_Unclaimed(0x91),

    /**
     *  ѡƱ����
     */
    IX_Vote(0x94),

    /**
     *  �ʲ����ѷ�����
     */
    ST_QuantityIssued(0xc1),

    /**
     *  ���ݿ�汾
     */
    CFG_Version(0xf0),
    ;
    byte value;
    private DataEntryPrefix(int v)
    {
        value = (byte) v;
    }
    
    public byte get() {
        return value;
    }
}
