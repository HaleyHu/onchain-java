package AntShares.Core;

/**
 *  ��������
 */
public enum TransactionType
{
    /**
     *  ���ڷ����ֽڷѵ����⽻��
     */
    MinerTransaction(0x00),
    /**
     *  ���ڷַ��ʲ������⽻��
     */
    IssueTransaction(0x01),
    /**
     *  ���ڷ���С�ϱҵ����⽻��
     */
    ClaimTransaction(0x02),
    /**
     *  ���ڱ�����Ϊ���˺�ѡ�˵����⽻��
     */
    EnrollmentTransaction(0x20),
    /**
     *  ����ͶƱѡ�������˵����⽻��
     */
    VotingTransaction(0x24),
    /**
     *  �����ʲ��Ǽǵ����⽻��
     */
    RegisterTransaction(0x40),
    /**
     *  ��Լ���ף�������õ�һ�ֽ���
     */
    ContractTransaction(0x80),
    /**
     *  ί�н���
     */
    AgencyTransaction(0xb0),
    ;

    private byte value;

    TransactionType(int v)
    {
        value = (byte)v;
    }

    public byte getByte()
    {
        return value;
    }

}
