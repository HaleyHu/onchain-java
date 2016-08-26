package AntShares.Core;

import AntShares.*;

/**
 *  ���׽������ʾ�������ʲ��ı仯��
 */
public class TransactionResult
{
    /**
     *  �ʲ����
     */
    public final UInt256 assetId;
    /**
     *  ���ʲ��ı仯��
     */
    public final Fixed8 amount;
    
    public TransactionResult(UInt256 assetId, Fixed8 amount)
    {
    	this.assetId = assetId;
    	this.amount = amount;
    }
}
