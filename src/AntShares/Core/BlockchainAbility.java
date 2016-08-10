package AntShares.Core;

/**
 * ��ʾ�ض�������ʵ�����ṩ�Ĺ���
 */
public enum BlockchainAbility
{
    /**
     *  ��
     */
    None(0),

    /**
     *  ����ʵ�ֵ��麯����GetBlockAndHeight, GetBlockHeight, GetNextBlock, GetNextBlockHash, GetSysFeeAmount
     */
    BlockIndexes(0x01),

    /**
     *  ����ʵ�ֵ��麯����ContainsAsset, GetAssets, GetEnrollments
     */
    TransactionIndexes(0x02),

    /**
     *  ����ʵ�ֵ��麯����ContainsUnspent, GetUnclaimed, GetUnspent, GetVotes, IsDoubleSpend
     */
    UnspentIndexes(0x04),

    /**
     *  ����ʵ�ֵ��麯����GetQuantityIssued
     */
    Statistics(0x08),

    /**
     *  ���еĹ���
     */
    All(0xff);

    private byte value;

	BlockchainAbility(int v)
	{
		value = (byte)v;
	}

	public byte getByte()
	{
		return value;
	}
}
