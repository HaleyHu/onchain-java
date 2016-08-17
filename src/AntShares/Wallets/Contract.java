package AntShares.Wallets;

import AntShares.UInt160;
import AntShares.IO.Serializable;

/**
 *  ���к�Լ�Ļ���
 */
public abstract class Contract implements Serializable
{
    /**
     *  ��Լ�ű�����
     */
    public byte[] RedeemScript;
    /**
     *  ��Կɢ��ֵ�����ڱ�ʶ�ú�Լ��Ǯ������������һ���˻�
     */
    public UInt160 PublicKeyHash;

    private String _address;
    /**
     *  ��Լ��ַ
     */
    public String getAddress()
    {
        if (_address == null)
        {
            // TODO
            //_address = Wallet.ToAddress(ScriptHash);
        }
        return _address;
    }

    /**
     *  ��Լ����ʽ�����б�
     */
    public abstract ContractParameterType[] getParameterList();

    private UInt160 _scriptHash;
    /**
     *  �ű�ɢ��ֵ
     */
    public UInt160 getScriptHash()
    {
        if (_scriptHash == null)
        {
            // TODO
            //_scriptHash = RedeemScript.ToScriptHash();
        }
        return _scriptHash;
    }

    /**
     *  �Ƚ�����һ�������Ƿ����
     *  <param name="obj">��һ������</param>
     *  <returns>���رȽϵĽ��</returns>
     */
    @Override
    public boolean equals(Object obj)
    {
        if (this == obj) return true;
        if (!(obj instanceof Contract)) return false;
        return getScriptHash().equals(((Contract) obj).getScriptHash());
    }

    /**
     *  ���HashCode
     *  <returns>����HashCode</returns>
     */
    @Override
    public int hashCode()
    {
        return getScriptHash().hashCode();
    }
}