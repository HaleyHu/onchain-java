package AntShares.Core;

import java.io.*;
import java.util.Arrays;
import java.util.function.Function;
import java.util.stream.Stream;

import AntShares.*;
import AntShares.Core.Scripts.*;
import AntShares.Cryptography.MerkleTree;
import AntShares.IO.*;
import AntShares.IO.Serializable;
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
    public int version; // unsigned int
    /**
     *  ǰһ�������ɢ��ֵ
     */
    public UInt256 prevBlock;
    /**
     *  �����������н��׵�Merkle���ĸ�
     */
    public UInt256 merkleRoot;
    /**
     *  ʱ���
     */
    public int timestamp; // unsigned int
    /**
     *  ����߶�
     */
    public int height; // unsigned int
    /**
     *  �����
     */
    public long nonce; // unsigned long
    /**
     *  ��һ������ļ��˺�Լ��ɢ��ֵ
     */
    public UInt160 nextMiner;
    /**
     *  ������֤������Ľű�
     */
    public Script script;
    /**
     *  �����б����б��н��׵�����Ϊ0ʱ����Block�����ʾһ������ͷ
     */
    public Transaction[] transactions;

// TODO
//    [NonSerialized]
    private Block _header = null;
    /**
     *  �����������ͷ
     */
    public Block header()
    {
        if (isHeader()) return this;
        if (_header == null)
        {
            _header = new Block();
            _header.prevBlock = prevBlock;
            _header.merkleRoot = this.merkleRoot;
            _header.timestamp = this.timestamp;
            _header.height = this.height;
            _header.nonce = this.nonce;
            _header.nextMiner = this.nextMiner;
            _header.script = this.script;
            _header.transactions = new Transaction[0];
        }
        return _header;
    }

    /**
     *  �ʲ��嵥������
     */
    @Override public InventoryType getInventoryType() { return InventoryType.Block; }

    /**
     *  ���ص�ǰBlock�����Ƿ�Ϊ����ͷ
     */
    public boolean isHeader() { return transactions.length == 0; }

    public static Fixed8 calculateNetFee(Stream<Transaction> transactions)
    {
    	//TODO
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
        deserializeUnsigned(reader);
        if (reader.readByte() != 1) throw new IOException();
        try
        {
			script = reader.readSerializable(Script.class);
		}
        catch (InstantiationException | IllegalAccessException ex)
        {
        	throw new IOException(ex);
		}
        transactions = new Transaction[(int) reader.readVarInt(0x10000000)];
        for (int i = 0; i < transactions.length; i++)
        {
            transactions[i] = Transaction.deserializeFrom(reader);
        }
        if (transactions.length > 0)
        {
            if (transactions[0].type != TransactionType.MinerTransaction || Arrays.stream(transactions).skip(1).anyMatch(p -> p.type == TransactionType.MinerTransaction))
                throw new IOException();
            if (!merkleRoot.equals(MerkleTree.ComputeRoot((UInt256[]) Arrays.stream(transactions).map(p -> p.hash()).toArray())))
                throw new IOException();
        }
    }

    @Override public void deserializeUnsigned(BinaryReader reader) throws IOException
    {
        try
        {
            version = reader.readInt();
            prevBlock = reader.readSerializable(UInt256.class);
            merkleRoot = reader.readSerializable(UInt256.class);
            timestamp = reader.readInt();
            height = reader.readInt();
            nonce = reader.readInt();
			nextMiner = reader.readSerializable(UInt160.class);
	        transactions = new Transaction[0];
		}
        catch (InstantiationException | IllegalAccessException ex)
        {
        	throw new IOException(ex);
		}
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
    
    public static Block fromTrimmedData(byte[] data, int index) throws IOException
    {
    	return fromTrimmedData(data, index, null);
    }

    public static Block fromTrimmedData(byte[] data, int index, Function<UInt256, Transaction> txSelector) throws IOException
    {
        Block block = new Block();
        try (ByteArrayInputStream ms = new ByteArrayInputStream(data, index, data.length - index))
        {
	        try (BinaryReader reader = new BinaryReader(ms))
	        {
	        	block.deserializeUnsigned(reader);
	        	reader.readByte(); block.script = reader.readSerializable(Script.class);
	        	if (txSelector == null)
	        	{
	        		block.transactions = new Transaction[0];
	        	}
	        	else
	        	{
		        	block.transactions = new Transaction[(int)reader.readVarInt(0x10000000)];
		        	for (int i = 0; i < block.transactions.length; i++)
		        	{
		        		block.transactions[i] = txSelector.apply(reader.readSerializable(UInt256.class));
		        	}
	        	}
	        }
	        catch (InstantiationException | IllegalAccessException ex)
	        {
				throw new IOException(ex);
			}
        }
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
        if (prevBlock.equals(UInt256.ZERO))
            return new UInt160[] { Script.toScriptHash(script.RedeemScript) };
        //TODO
//        Block prev_header = Blockchain.Default.GetHeader(PrevBlock);
//        if (prev_header == null) throw new UnsupportedOperationException();
//        return new UInt160[] { prev_header.NextMiner };
        return new UInt160[0];
    }

    /**
     *  �������������н��׵�Hash����MerkleRoot
     */
    public void rebuildMerkleRoot()
    {
        merkleRoot = MerkleTree.ComputeRoot((UInt256[]) Arrays.stream(transactions).map(p -> p.hash()).toArray());
    }

    /**
     *  ���л�
     *  <param name="writer">������л��������</param>
     * @throws IOException 
     */
    @Override public void serialize(BinaryWriter writer) throws IOException
    {
        serializeUnsigned(writer);
        writer.writeByte((byte)1); writer.writeSerializable(script);
        writer.writeSerializableArray(transactions);
    }

    @Override public void serializeUnsigned(BinaryWriter writer) throws IOException
    {
        writer.writeInt(version);
        writer.writeSerializable(prevBlock);
        writer.writeSerializable(merkleRoot);
        writer.writeInt(timestamp);
        writer.writeInt(height);
        writer.writeLong(nonce);
        writer.writeSerializable(nextMiner);
    }

    /**
     *  ���json����
     *  <returns>����json����</returns>
     */
    public JObject ToJson()
    {
    	//TODO
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
    public byte[] trim()
    {
        try (ByteArrayOutputStream ms = new ByteArrayOutputStream())
        {
	        try (BinaryWriter writer = new BinaryWriter(ms))
	        {
	            serializeUnsigned(writer);
	            writer.writeByte((byte)1); writer.writeSerializable(script);
	            writer.writeSerializableArray((Serializable[]) Arrays.stream(transactions).map(p -> p.hash()).toArray());
	            writer.flush();
	            return ms.toByteArray();
	        }
        }
        catch (IOException ex)
        {
        	throw new UnsupportedOperationException(ex);
		}
    }

    /**
     *  ��֤������ͷ�Ƿ�Ϸ�
     *  <returns>���ظ�����ͷ�ĺϷ��ԣ�����true��Ϊ�Ϸ������򣬷Ƿ���</returns>
     */
    @Override public boolean verify()
    {
        return verify(false);
    }

    /**
     *  ��֤������ͷ�Ƿ�Ϸ�
     *  <param name="completely">�Ƿ�ͬʱ��֤�����е�ÿһ�ʽ���</param>
     *  <returns>���ظ�����ͷ�ĺϷ��ԣ�����true��Ϊ�Ϸ������򣬷Ƿ���</returns>
     */
    public boolean verify(boolean completely)
    {
    	//TODO
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
        return false;
    }
}