package AntShares.Network;

/**
 *  �����嵥�еĶ�������
 */
public enum InventoryType
{
    /**
     *  ����
     */
    TX(0x01),
    /**
     *  ����
     */
    Block(0x02),
    /**
     *  ��ʶ����
     */
    Consensus(0xe0),
    
    ;
    int value;
    private InventoryType(int v)
    {
        value = v;
    }
    
    public int get() {
        return value;
    }
}

