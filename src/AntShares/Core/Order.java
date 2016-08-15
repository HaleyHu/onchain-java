package AntShares.Core;

import java.io.InputStream;
import java.io.OutputStream;

import AntShares.Fixed8;
import AntShares.UInt160;
import AntShares.UInt256;
import AntShares.Core.Scripts.Script;

/**
 *  ����
 */
public class Order implements ISignable
{
	/**
     *  �ʲ����
     */
    public UInt256 AssetId;
    /**
     *  ���ұ��
     */
    public UInt256 ValueAssetId;
    /**
     *  �����˵ĺ�Լɢ��
     */
    public UInt160 Agent;
    /**
     *  �����������������������ʾ���룬������ʾ����
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
    /**
     *  �����б�
     */
// TODO
//    public TransactionInput[] Inputs;
//    /**
//     *  ������֤�ö����Ľű��б�
//     */
    public Script[] scripts;

    @Override
    public Script[] getScripts()
    {
    	return scripts;
    }
    
    @Override
    public void setScripts(Script[] scrpts)
    {
    	scripts = scrpts;
    }

    @Override
    public void Deserialize(InputStream reader)
    {
//        ((ISignable)this).DeserializeUnsigned(reader);
//        Scripts = reader.ReadSerializableArray<Script>();
    }

//    internal void DeserializeInTransaction(BinaryReader reader, AgencyTransaction tx)
//    {
//        DeserializeUnsignedInternal(reader, tx.AssetId, tx.ValueAssetId, tx.Agent);
//        Scripts = reader.ReadSerializableArray<Script>();
//    }

    @Override
    public void DeserializeUnsigned(InputStream reader)
    {
//        UInt256 asset_id = reader.ReadSerializable<UInt256>();
//        UInt256 value_asset_id = reader.ReadSerializable<UInt256>();
//        if (asset_id == value_asset_id) throw new FormatException();
//        UInt160 agent = reader.ReadSerializable<UInt160>();
//        DeserializeUnsignedInternal(reader, asset_id, value_asset_id, agent);
    }

//    private void DeserializeUnsignedInternal(BinaryReader reader, UInt256 asset_id, UInt256 value_asset_id, UInt160 agent)
//    {
//        AssetId = asset_id;
//        ValueAssetId = value_asset_id;
//        Agent = agent;
//        Amount = reader.ReadSerializable<Fixed8>();
//        if (Amount == Fixed8.Zero) throw new FormatException();
//        if (Amount.GetData() % 10000 != 0) throw new FormatException();
//        Price = reader.ReadSerializable<Fixed8>();
//        if (Price <= Fixed8.Zero) throw new FormatException();
//        if (Price.GetData() % 10000 != 0) throw new FormatException();
//        Client = reader.ReadSerializable<UInt160>();
//        Inputs = reader.ReadSerializableArray<TransactionInput>();
//        if (Inputs.Distinct().Count() != Inputs.Length)
//            throw new FormatException();
//    }

    public UInt160[] GetScriptHashesForVerifying()
    {
//        HashSet<UInt160> hashes = new HashSet<UInt160>();
//        RegisterTransaction asset = Blockchain.Default.GetTransaction(AssetId) as RegisterTransaction;
//        if (asset == null) throw new InvalidOperationException();
//        if (asset.AssetType == AssetType.Share)
//        {
//            hashes.Add(Client);
//        }
//        foreach (var group in Inputs.GroupBy(p => p.PrevHash))
//        {
//            Transaction tx = Blockchain.Default.GetTransaction(group.Key);
//            if (tx == null) throw new InvalidOperationException();
//            hashes.UnionWith(group.Select(p => tx.Outputs[p.PrevIndex].ScriptHash));
//        }
//        return hashes.OrderBy(p => p).ToArray();
    	return null;
    }

    @Override
    public void Serialize(OutputStream writer)
    {
//        ((ISignable)this).SerializeUnsigned(writer);
//        writer.Write(Scripts);
    }

//    internal void SerializeInTransaction(BinaryWriter writer)
//    {
//        writer.Write(Amount);
//        writer.Write(Price);
//        writer.Write(Client);
//        writer.Write(Inputs);
//        writer.Write(Scripts);
//    }
//
    @Override
    public void SerializeUnsigned(OutputStream writer)
    {
//        writer.Write(AssetId);
//        writer.Write(ValueAssetId);
//        writer.Write(Agent);
//        writer.Write(Amount);
//        writer.Write(Price);
//        writer.Write(Client);
//        writer.Write(Inputs);
    }
}