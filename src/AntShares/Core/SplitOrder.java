package AntShares.Core;

import java.io.Serializable;

import AntShares.Fixed8;
import AntShares.UInt160;

/**
 *  ���ֳɽ��Ķ���
 */
public class SplitOrder implements Serializable
{
	private static final long serialVersionUID = 8781924160988999643L;
	/**
     *  ���������������
     */
    public Fixed8 Amount;
    /**
     *  �۸�
     */
    public Fixed8 Price;
    /**
     *  ί���˵ĺ�Լɢ��
     */
    public UInt160 Client;

}
