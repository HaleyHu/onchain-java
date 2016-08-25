package AntShares.Core;

import java.util.EnumSet;

/**
 * ��ʾ�ض�������ʵ�����ṩ�Ĺ���
 */
public enum BlockchainAbility
{
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
    Statistics(0x08);

	public static final EnumSet<BlockchainAbility> None = EnumSet.noneOf(BlockchainAbility.class);
	public static final EnumSet<BlockchainAbility> All = EnumSet.allOf(BlockchainAbility.class);
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
