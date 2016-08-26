package AntShares.Wallets;

import AntShares.UInt160;
import AntShares.Core.Scripts.Script;
import AntShares.IO.Serializable;

/**
 *  ���к�Լ�Ļ���
 */
public abstract class Contract implements Serializable
{
    /**
     *  ��Լ�ű�����
     */
    public byte[] redeemScript;
    /**
     *  ��Կɢ��ֵ�����ڱ�ʶ�ú�Լ��Ǯ������������һ���˻�
     */
    public UInt160 publicKeyHash;

    private String _address;
    /**
     *  ��Լ��ַ
     */
    public String address()
    {
        if (_address == null)
        {
            _address = Wallet.toAddress(scriptHash());
        }
        return _address;
    }

    /**
     *  ��Լ����ʽ�����б�
     */
    public abstract ContractParameterType[] parameterList();

    private UInt160 _scriptHash;
    /**
     *  �ű�ɢ��ֵ
     */
    public UInt160 scriptHash()
    {
        if (_scriptHash == null)
        {
            _scriptHash = Script.toScriptHash(redeemScript);
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
        return scriptHash().equals(((Contract) obj).scriptHash());
    }

    /**
     *  ���HashCode
     *  <returns>����HashCode</returns>
     */
    @Override
    public int hashCode()
    {
        return scriptHash().hashCode();
    }
}
