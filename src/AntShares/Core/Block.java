package AntShares.Core;

import java.io.IOException;

import AntShares.*;
import AntShares.Core.Scripts.*;
import AntShares.IO.*;
import AntShares.IO.Json.JObject;
import AntShares.Network.*;

/**
 *  ���������ͷ
 */
public class Block extends Inventory
{
    /**
     *  ����汾
     */
    public int Version;
    /**
     *  ǰһ�������ɢ��ֵ
     */
    public UInt256 PrevBlock;
    /**
     *  �����������н��׵�Merkle���ĸ�
     */
    public UInt256 MerkleRoot;
    /**
     *  ʱ���
     */
    public int Timestamp;
    /**
     *  ����߶�
     */
    public int Height;
    /**
     *  �����
     */
    public long Nonce;
    /**
     *  ��һ������ļ��˺�Լ��ɢ��ֵ
     */
    public UInt160 NextMiner;
    /**
     *  ������֤������Ľű�
     */
    public Script Script;
    /**
     *  �����б����б��н��׵�����Ϊ0ʱ����Block�����ʾһ������ͷ
     */
    public Transaction[] Transactions;

// TODO
//    [NonSerialized]
    private Block _header = null;
    /**
     *  �����������ͷ
     */
    public Block getHeader()
    {
        if (IsHeader()) return this;
        if (_header == null)
        {
            _header = new Block();
            _header.PrevBlock = PrevBlock;
            _header.MerkleRoot = this.MerkleRoot;
            _header.Timestamp = this.Timestamp;
            _header.Height = this.Height;
            _header.Nonce = this.Nonce;
            _header.NextMiner = this.NextMiner;
            _header.Script = this.Script;
            _header.Transactions = new Transaction[0];
        }
        return _header;
    }

    /**
     *  �ʲ��嵥������
     */
    @Override public InventoryType getInventoryType() { return InventoryType.Block; }

    //@Override 
    public Script[] getScripts()
    {
        return new Script[] { Script };
    }
    
    //@Override
    public void setScripts(Script[] value)
    {
        if (value.length != 1) throw new IllegalArgumentException();
        Script = value[0];
    }

    /**
     *  ���ص�ǰBlock�����Ƿ�Ϊ����ͷ
     */
    public boolean IsHeader() { return Transactions.length == 0; }

    public static Fixed8 CalculateNetFee(Iterable<Transaction> transactions)
    {
//        Transaction[] ts = transactions.Where(p => p.Type != TransactionType.MinerTransaction && p.Type != TransactionType.ClaimTransaction).ToArray();
//        Fixed8 amount_in = ts.SelectMany(p => p.References.Values.Where(o => o.AssetId == Blockchain.AntCoin.Hash)).Sum(p => p.Value);
//        Fixed8 amount_out = ts.SelectMany(p => p.Outputs.Where(o => o.AssetId == Blockchain.AntCoin.Hash)).Sum(p => p.Value);
//        Fixed8 amount_sysfee = ts.Sum(p => p.SystemFee);
//        return amount_in - amount_out - amount_sysfee;
        return new Fixed8(0);
    }

    /**
     *  �����л�
     *  <param name="reader">������Դ</param>
     * @throws IOException 
     * @throws IllegalAccessException 
     * @throws InstantiationException 
     */
    @Override public void deserialize(BinaryReader reader) throws IOException
    {
        ((Signable)this).deserializeUnsigned(reader);
        if (reader.readByte() != 1) throw new FormatException();
        // TODO
        //Script = reader.readSerializable(Script.getClass());
        Transactions = new Transaction[(int) reader.readVarInt(0x10000000)];
        for (int i = 0; i < Transactions.length; i++)
        {
            Transactions[i] = Transaction.deserializeFrom(reader);
        }
        if (Transactions.length > 0)
        {
            // TODO
//            if (Transactions[0].type != TransactionType.MinerTransaction || Transactions.Skip(1).Any(p => p.Type == TransactionType.MinerTransaction))
//                throw new FormatException();
//            if (MerkleTree.ComputeRoot(Transactions.Select(p => p.Hash).ToArray()) != MerkleRoot)
//                throw new FormatException();
        }
    }

    @Override public void deserializeUnsigned(BinaryReader reader) throws IOException
    {
        Version = reader.readInt();
        // TODO
//        PrevBlock = reader.readSerializable(UInt256.class);
//        MerkleRoot = reader.readSerializable(UInt256.class);
        Timestamp = reader.readInt();
        Height = reader.readInt();
        Nonce = reader.readInt();
        // TODO
//        NextMiner = reader.readSerializable(UInt160.class);
        Transactions = new Transaction[0];
    }

    /**
     *  �Ƚϵ�ǰ������ָ�������Ƿ����
     *  <param name="obj">Ҫ�Ƚϵ�����</param>
     *  <returns>���ض����Ƿ����</returns>
     */
    @Override public boolean equals(Object obj)
    {
        if (this == obj) return true;
        if (!(obj instanceof Block)) return false;
        return this.hash().equals(((Block) obj).hash());
    }

    // TODO
    public static Block FromTrimmedData(byte[] data, int index /*, Func<UInt256, Transaction> txSelector = null*/)
    {
        Block block = new Block();
//        using (MemoryStream ms = new MemoryStream(data, index, data.Length - index, false))
//        using (BinaryReader reader = new BinaryReader(ms))
//        {
//            ((ISignable)block).DeserializeUnsigned(reader);
//            reader.ReadByte(); block.Script = reader.ReadSerializable<Script>();
//            if (txSelector == null)
//            {
//                block.Transactions = new Transaction[0];
//            }
//            else
//            {
//                block.Transactions = new Transaction[reader.ReadVarInt(0x10000000)];
//                for (int i = 0; i < block.Transactions.Length; i++)
//                {
//                    block.Transactions[i] = txSelector(reader.ReadSerializable<UInt256>());
//                }
//            }
//        }
        return block;
    }

    /**
     *  ��������HashCode
     *  <returns>���������HashCode</returns>
     */
    @Override public int hashCode()
    {
        return hash().hashCode();
    }

    @Override public UInt160[] getScriptHashesForVerifying()
    {
        // TODO
//        if (PrevBlock == UInt256.ZERO)
//            return new UInt160[] { Script.RedeemScript.ToScriptHash() };
//        Block prev_header = Blockchain.Default.GetHeader(PrevBlock);
//        if (prev_header == null) throw new UnsupportedOperationException();
//        return new UInt160[] { prev_header.NextMiner };
        return null;
    }

    /**
     *  �������������н��׵�Hash����MerkleRoot
     */
    public void RebuildMerkleRoot()
    {
        //MerkleRoot = MerkleTree.ComputeRoot(Transactions.Select(p => p.Hash).ToArray());
    }

    /**
     *  ���л�
     *  <param name="writer">������л��������</param>
     * @throws IOException 
     */
    @Override public void serialize(BinaryWriter writer) throws IOException
    {
        ((Signable)this).serializeUnsigned(writer);
        writer.writeByte((byte)1); writer.writeSerializable(Script);
        writer.writeSerializableArray(Transactions);
    }

    @Override public void serializeUnsigned(BinaryWriter writer) throws IOException
    {
        writer.writeInt(Version);
        writer.writeSerializable(PrevBlock);
        writer.writeSerializable(MerkleRoot);
        writer.writeInt(Timestamp);
        writer.writeInt(Height);
        writer.writeLong(Nonce);
        writer.writeSerializable(NextMiner);
    }

    /**
     *  ���json����
     *  <returns>����json����</returns>
     */
    public JObject ToJson()
    {
        JObject json = new JObject();
//        json["hash"] = Hash.ToString();
//        json["version"] = Version;
//        json["previousblockhash"] = PrevBlock.ToString();
//        json["merkleroot"] = MerkleRoot.ToString();
//        json["time"] = Timestamp;
//        json["height"] = Height;
//        json["nonce"] = Nonce.ToString("x16");
//        json["nextminer"] = Wallet.ToAddress(NextMiner);
//        json["script"] = Script.ToJson();
//        json["tx"] = Transactions.Select(p => p.ToJson()).ToArray();
        return json;
    }

    /**
     *  ����������Ϊֻ��������ͷ�ͽ���Hash���ֽ����飬ȥ����������
     *  <returns>����ֻ��������ͷ�ͽ���Hash���ֽ�����</returns>
     */
    public byte[] Trim()
    {
//        using (MemoryStream ms = new MemoryStream())
//        using (BinaryWriter writer = new BinaryWriter(ms))
//        {
//            ((ISignable)this).SerializeUnsigned(writer);
//            writer.Write((byte)1); writer.Write(Script);
//            writer.Write(Transactions.Select(p => p.Hash).ToArray());
//            writer.Flush();
//            return ms.ToArray();
//        }
        return null;
    }

    /**
     *  ��֤������ͷ�Ƿ�Ϸ�
     *  <returns>���ظ�����ͷ�ĺϷ��ԣ�����true��Ϊ�Ϸ������򣬷Ƿ���</returns>
     */
    @Override public boolean verify()
    {
        return Verify(false);
    }

    /**
     *  ��֤������ͷ�Ƿ�Ϸ�
     *  <param name="completely">�Ƿ�ͬʱ��֤�����е�ÿһ�ʽ���</param>
     *  <returns>���ظ�����ͷ�ĺϷ��ԣ�����true��Ϊ�Ϸ������򣬷Ƿ���</returns>
     */
    public boolean Verify(boolean completely)
    {
//        if (Hash == Blockchain.GenesisBlock.Hash) return true;
//        if (Blockchain.Default.ContainsBlock(Hash)) return true;
//        if (completely && IsHeader) return false;
//        if (!Blockchain.Default.Ability.HasFlag(BlockchainAbility.TransactionIndexes) || !Blockchain.Default.Ability.HasFlag(BlockchainAbility.UnspentIndexes))
//            return false;
//        Block prev_header = Blockchain.Default.GetHeader(PrevBlock);
//        if (prev_header == null) return false;
//        if (prev_header.Height + 1 != Height) return false;
//        if (prev_header.Timestamp >= Timestamp) return false;
//        if (!this.VerifySignature()) return false;
//        if (completely)
//        {
//            if (NextMiner != Blockchain.GetMinerAddress(Blockchain.Default.GetMiners(Transactions).ToArray()))
//                return false;
//            foreach (Transaction tx in Transactions)
//                if (!tx.Verify()) return false;
//            Transaction tx_gen = Transactions.FirstOrDefault(p => p.Type == TransactionType.MinerTransaction);
//            if (tx_gen?.Outputs.Sum(p => p.Value) != CalculateNetFee(Transactions)) return false;
//        }
        return true;
    }
}