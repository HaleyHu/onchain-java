package AntShares.Core;

import java.util.*;

import org.bouncycastle.math.ec.ECPoint;

import AntShares.*;
import AntShares.Network.TimeSpan;

/**
 *  ʵ�����������ܵĻ���
 */
public abstract class Blockchain // TODO : IDisposable
{
    /**
     *  �����鱻д�뵽Ӳ�̺󴥷�
     */
    //public static event EventHandler<Block> PersistCompleted;

    /**
     *  ����ÿ�������ʱ����������Ϊ��λ
     */
    public static final int SecondsPerBlock = 15;
    /**
     *  С�ϱҲ����ݼ���ʱ����������������Ϊ��λ
     */
    public static final int DecrementInterval = 2000000;
    /**
     *  ÿ�����������С�ϱҵ�����
     */
    public static final int[] MintingAmount = { 8, 7, 6, 5, 4, 3, 2, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1 };
    /**
     *  ����ÿ�������ʱ����
     */
    public static final TimeSpan TimePerBlock = TimeSpan.FromSeconds(SecondsPerBlock);
    /**
     *  �󱸼������б�
     */
    public static final ECPoint[] StandbyMiners =
    {
//        ECPoint.DecodePoint("0327da12b5c40200e9f65569476bbff2218da4f32548ff43b6387ec1416a231ee8".HexToBytes(), ECCurve.Secp256r1),
//        ECPoint.DecodePoint("026ce35b29147ad09e4afe4ec4a7319095f08198fa8babbe3c56e970b143528d22".HexToBytes(), ECCurve.Secp256r1),
//        ECPoint.DecodePoint("0209e7fd41dfb5c2f8dc72eb30358ac100ea8c72da18847befe06eade68cebfcb9".HexToBytes(), ECCurve.Secp256r1),
//        ECPoint.DecodePoint("039dafd8571a641058ccc832c5e2111ea39b09c0bde36050914384f7a48bce9bf9".HexToBytes(), ECCurve.Secp256r1),
//        ECPoint.DecodePoint("038dddc06ce687677a53d54f096d2591ba2302068cf123c1f2d75c2dddc5425579".HexToBytes(), ECCurve.Secp256r1),
//        ECPoint.DecodePoint("02d02b1873a0863cd042cc717da31cea0d7cf9db32b74d4c72c01b0011503e2e22".HexToBytes(), ECCurve.Secp256r1),
//        ECPoint.DecodePoint("034ff5ceeac41acf22cd5ed2da17a6df4dd8358fcb2bfb1a43208ad0feaab2746b".HexToBytes(), ECCurve.Secp256r1),
    };

    /**
     *  С�Ϲ�
     */
    public static final RegisterTransaction AntShare = new RegisterTransaction()
    {
//        AssetType = AssetType.AntShare,
////#if TESTNET
//        Name = "[{'lang':'zh-CN','name':'С�Ϲ�(����)'},{'lang':'en','name':'AntShare(TestNet)'}]",
////#else
////        Name = "[{'lang':'zh-CN','name':'С�Ϲ�'},{'lang':'en','name':'AntShare'}]",
////#endif
//        Amount = Fixed8.FromDecimal(100000000),
//        Issuer = ECPoint.DecodePoint((new[] { (byte)0x02 }).Concat(ECCurve.Secp256r1.G.EncodePoint(false).Skip(1).Sha256().Sha256()).ToArray(), ECCurve.Secp256r1),
//        Admin = (new[] { (byte)ScriptOp.OP_TRUE }).ToScriptHash(),
//        Attributes = new TransactionAttribute[0],
//        Inputs = new TransactionInput[0],
//        Outputs = new TransactionOutput[0],
//        Scripts = new Script[0]
    };

    /**
     *  С�ϱ�
     */
    public static final RegisterTransaction AntCoin = new RegisterTransaction()
    {
//        AssetType = AssetType.AntCoin,
//#if TESTNET
//        Name = "[{'lang':'zh-CN','name':'С�ϱ�(����)'},{'lang':'en','name':'AntCoin(TestNet)'}]",
//#else
//        Name = "[{'lang':'zh-CN','name':'С�ϱ�'},{'lang':'en','name':'AntCoin'}]",
//#endif
//        Amount = Fixed8.FromDecimal(MintingAmount.Sum(p => p * DecrementInterval)),
//        Issuer = ECPoint.DecodePoint((new[] { (byte)0x02 }).Concat(ECCurve.Secp256r1.G.EncodePoint(false).Skip(1).Sha256().Sha256()).ToArray(), ECCurve.Secp256r1),
//        Admin = (new[] { (byte)ScriptOp.OP_FALSE }).ToScriptHash(),
//        Attributes = new TransactionAttribute[0],
//        Inputs = new TransactionInput[0],
//        Outputs = new TransactionOutput[0],
//        Scripts = new Script[0]
    };

    /**
     *  ��������
     */
    public static final Block GenesisBlock = new Block()
    {
//        PrevBlock = UInt256.Zero,
//        Timestamp = (new DateTime(2016, 7, 15, 15, 8, 21, DateTimeKind.Utc)).ToTimestamp(),
//        Height = 0,
//        Nonce = 2083236893, //����ر��¾�
//        NextMiner = GetMinerAddress(StandbyMiners),
//        Script = new Script
//        {
//            StackScript = new byte[0],
//            RedeemScript = new[] { (byte)ScriptOp.OP_TRUE }
//        },
//        Transactions = new Transaction[]
//        {
//            new MinerTransaction
//            {
//                Nonce = 2083236893,
//                Attributes = new TransactionAttribute[0],
//                Inputs = new TransactionInput[0],
//                Outputs = new TransactionOutput[0],
//                Scripts = new Script[0]
//            },
//            AntShare,
//            AntCoin,
//            new IssueTransaction
//            {
//                Nonce = 2083236893,
//                Attributes = new TransactionAttribute[0],
//                Inputs = new TransactionInput[0],
//                Outputs = new[]
//                {
//                    new TransactionOutput
//                    {
//                        AssetId = AntShare.Hash,
//                        Value = AntShare.Amount,
//                        ScriptHash = MultiSigContract.CreateMultiSigRedeemScript(StandbyMiners.Length / 2 + 1, StandbyMiners).ToScriptHash()
//                    }
//                },
//                Scripts = new[]
//                {
//                    new Script
//                    {
//                        StackScript = new byte[0],
//                        RedeemScript = new[] { (byte)ScriptOp.OP_TRUE }
//                    }
//                }
//            }
//        }
    };

    /**
     *  ���������ṩ�Ĺ���
     */
    public abstract BlockchainAbility getAbility();// { get; }
    /**
     *  ��ǰ��������ɢ��ֵ
     */
    public abstract UInt256 getCurrentBlockHash();// { get; }
    /**
     *  ��ǰ��������ͷ��ɢ��ֵ
     */
    public UInt256 getCurrentHeaderHash(){ return getCurrentBlockHash(); }
    /**
     *  Ĭ�ϵ�������ʵ��
     */
    private static Blockchain _default = null;
    public static Blockchain getDefault() { return _default; }
    /**
     *  ����ͷ�߶�
     */
    public int getHeaderHeight() { return getHeight(); }
    /**
     *  ����߶�
     */
    public abstract int getHeight();
    /**
     *  ��ʾ��ǰ��������ʵ���Ƿ�Ϊֻ����
     */
    public abstract boolean IsReadOnly();

    // TODO
//    static Blockchain()
//    {
//        GenesisBlock.RebuildMerkleRoot();
//    }

    /**
     *  ��ָ����������ӵ���������
     *  <param name="block">Ҫ��ӵ�����</param>
     *  <returns>�����Ƿ���ӳɹ�</returns>
     */
    protected abstract boolean AddBlock(Block block);

    /**
     *  ��ָ��������ͷ��ӵ�����ͷ����
     *  <param name="headers">Ҫ��ӵ�����ͷ�б�</param>
     */
    protected abstract void AddHeaders(Iterable<Block> headers);

    /**
     *  �ж����������Ƿ����ָ�����ʲ�
     *  <param name="hash">�ʲ����</param>
     *  <returns>�������ָ���ʲ��򷵻�true</returns>
     */
    public boolean ContainsAsset(UInt256 hash)
    {
        // TODO;
        //return hash == AntShare.Hash || hash == AntCoin.Hash;
        return false;
    }

    /**
     *  �ж����������Ƿ����ָ��������
     *  <param name="hash">������</param>
     *  <returns>�������ָ�������򷵻�true</returns>
     */
    public boolean ContainsBlock(UInt256 hash)
    {
        // TODO
        //return hash == GenesisBlock.Hash;
        return false;
    }

    /**
     *  �ж����������Ƿ����ָ���Ľ���
     *  <param name="hash">���ױ��</param>
     *  <returns>�������ָ�������򷵻�true</returns>
     */
    public boolean ContainsTransaction(UInt256 hash)
    {
        // TODO
        //return GenesisBlock.Transactions.Any(p => p.Hash == hash);
        return false;
    }

    public boolean ContainsUnspent(TransactionInput input)
    {
        return ContainsUnspent(input.prevHash, input.prevIndex);
    }

    public abstract boolean ContainsUnspent(UInt256 hash, short index);

    public abstract void Dispose();

    public abstract Iterable<RegisterTransaction> GetAssets();

    /**
     *  ����ָ���ĸ߶ȣ����ض�Ӧ��������Ϣ
     *  <param name="height">����߶�</param>
     *  <returns>���ض�Ӧ��������Ϣ</returns>
     */
    public Block GetBlock(int height)
    {
        return GetBlock(GetBlockHash(height));
    }

    /**
     *  ����ָ����ɢ��ֵ�����ض�Ӧ��������Ϣ
     *  <param name="hash">ɢ��ֵ</param>
     *  <returns>���ض�Ӧ��������Ϣ</returns>
     */
    public Block GetBlock(UInt256 hash)
    {
//        if (hash == GenesisBlock.Hash)
//            return GenesisBlock;
        return null;
    }

    /**
     *  ����ָ���ĸ߶ȣ����ض�Ӧ�����ɢ��ֵ
     *  <param name="height">����߶�</param>
     *  <returns>���ض�Ӧ�����ɢ��ֵ</returns>
     */
    public UInt256 GetBlockHash(int height)
    {
//        if (height == 0) return GenesisBlock.Hash;
        return null;
    }

    public Iterable<EnrollmentTransaction> GetEnrollments()
    {
        return GetEnrollments(Collections.emptyList());
    }

    public abstract Iterable<EnrollmentTransaction> GetEnrollments(Iterable<Transaction> others);

    /**
     *  ����ָ���ĸ߶ȣ����ض�Ӧ������ͷ��Ϣ
     *  <param name="height">����߶�</param>
     *  <returns>���ض�Ӧ������ͷ��Ϣ</returns>
     */
    public Block GetHeader(int height)
    {
        return GetHeader(GetBlockHash(height));
    }

    /**
     *  ����ָ����ɢ��ֵ�����ض�Ӧ������ͷ��Ϣ
     *  <param name="hash">ɢ��ֵ</param>
     *  <returns>���ض�Ӧ������ͷ��Ϣ</returns>
     */
    public Block GetHeader(UInt256 hash)
    {
        Block b = GetBlock(hash);
        return b == null ? null : b.getHeader();
    }

    public abstract UInt256[] GetLeafHeaderHashes();

    /**
     *  ��ȡ�����˵ĺ�Լ��ַ
     *  <param name="miners">�����˵Ĺ�Կ�б�</param>
     *  <returns>���ؼ����˵ĺ�Լ��ַ</returns>
     */
    public static UInt160 GetMinerAddress(ECPoint[] miners)
    {
//        return MultiSigContract.CreateMultiSigRedeemScript(miners.Length - (miners.Length - 1) / 3, miners).ToScriptHash();
        return new UInt160();
    }

    private List<ECPoint> _miners = new ArrayList<ECPoint>();
    /**
     *  ��ȡ��һ������ļ������б�
     *  <returns>����һ�鹫Կ����ʾ��һ������ļ������б�</returns>
     */
    public ECPoint[] GetMiners()
    {
        synchronized (_miners)
        {
            if (_miners.size() == 0)
            {
                // TODO
                //_miners.AddRange(GetMiners(Enumerable.Empty<Transaction>()));
            }
            return _miners.toArray(new ECPoint[_miners.size()]);
        }
    }

    public Iterable<ECPoint> GetMiners(Iterable<Transaction> others)
    {
//        if (!Ability.HasFlag(BlockchainAbility.TransactionIndexes) || !Ability.HasFlag(BlockchainAbility.UnspentIndexes))
//            throw new NotSupportedException();
//        //TODO: �˴�������ܽ��ķѴ����ڴ棬�����Ƿ������������
//        Vote[] votes = GetVotes(others).OrderBy(p => p.Enrollments.Length).ToArray();
//        int miner_count = (int)votes.WeightedFilter(0.25, 0.75, p => p.Count.GetData(), (p, w) => new
//        {
//            MinerCount = p.Enrollments.Length,
//            Weight = w
//        }).WeightedAverage(p => p.MinerCount, p => p.Weight);
//        miner_count = Math.Max(miner_count, StandbyMiners.Length);
//        Dictionary<ECPoint, Fixed8> miners = new Dictionary<ECPoint, Fixed8>();
//        Dictionary<UInt256, ECPoint> enrollments = GetEnrollments(others).ToDictionary(p => p.Hash, p => p.PublicKey);
//        foreach (var vote in votes)
//        {
//            foreach (UInt256 hash in vote.Enrollments)
//            {
//                if (!enrollments.ContainsKey(hash)) continue;
//                ECPoint pubkey = enrollments[hash];
//                if (!miners.ContainsKey(pubkey))
//                {
//                    miners.Add(pubkey, Fixed8.Zero);
//                }
//                miners[pubkey] += vote.Count;
//            }
//        }
//        return miners.OrderByDescending(p => p.Value).ThenBy(p => p.Key).Select(p => p.Key).Concat(StandbyMiners).Take(miner_count);
        return null;
    }

    /**
     *  ����ָ����ɢ��ֵ��������һ���������Ϣ
     *  <param name="hash">ɢ��ֵ</param>
     *  <returns>������һ���������Ϣ>
     */
    public abstract Block GetNextBlock(UInt256 hash);

    /**
     *  ����ָ����ɢ��ֵ��������һ�������ɢ��ֵ
     *  <param name="hash">ɢ��ֵ</param>
     *  <returns>������һ�������ɢ��ֵ</returns>
     */
    public abstract UInt256 GetNextBlockHash(UInt256 hash);

    /**
     *  ����ָ�����ʲ���ţ����ض�Ӧ�ʲ��ķ�����
     *  <param name="asset_id">�ʲ����</param>
     *  <returns>���ض�Ӧ�ʲ��ĵ�ǰ�Ѿ����е�����</returns>
     */
    public abstract Fixed8 GetQuantityIssued(UInt256 asset_id);

    /**
     *  ����ָ��������߶ȣ����ض�Ӧ���鼰֮ǰ���������а�����ϵͳ���õ�����
     *  <param name="height">����߶�</param>
     *  <returns>���ض�Ӧ��ϵͳ���õ�����</returns>
     */
    public long GetSysFeeAmount(int height)
    {
        return GetSysFeeAmount(GetBlockHash(height));
    }

    /**
     *  ����ָ��������ɢ��ֵ�����ض�Ӧ���鼰֮ǰ���������а�����ϵͳ���õ�����
     *  <param name="hash">ɢ��ֵ</param>
     *  <returns>����ϵͳ���õ�����</returns>
     */
    public abstract long GetSysFeeAmount(UInt256 hash);

    /**
     *  ����ָ����ɢ��ֵ�����ض�Ӧ�Ľ�����Ϣ
     *  <param name="hash">ɢ��ֵ</param>
     *  <returns>���ض�Ӧ�Ľ�����Ϣ</returns>
     */
    public Transaction GetTransaction(UInt256 hash)
    {
        Out<Integer> height = new Out<Integer>();
        return GetTransaction(hash, height);
    }

    /**
     *  ����ָ����ɢ��ֵ�����ض�Ӧ�Ľ�����Ϣ��ý�����������ĸ߶�
     *  <param name="hash">����ɢ��ֵ</param>
     *  <param name="height">���ظý�����������ĸ߶�</param>
     *  <returns>���ض�Ӧ�Ľ�����Ϣ</returns>
     */
    public Transaction GetTransaction(UInt256 hash, Out<Integer> height)
    {
//        Transaction tx = GenesisBlock.Transactions.FirstOrDefault(p => p.Hash == hash);
//        if (tx != null)
//        {
//            height = 0;
//            return tx;
//        }
        height.set(-1);
        return null;
    }

    public abstract Map<Short, Claimable> GetUnclaimed(UInt256 hash);

    /**
     *  ����ָ����ɢ��ֵ����������ȡ��Ӧ��δ���ѵ��ʲ�
     *  <param name="hash">����ɢ��ֵ</param>
     *  <param name="index">���������</param>
     *  <returns>����һ�������������ʾһ��δ���ѵ��ʲ�</returns>
     */
    public abstract TransactionOutput GetUnspent(UInt256 hash, short index);

    /**
     *  ��ȡѡƱ��Ϣ
     *  <returns>����һ��ѡƱ�б�������ǰ��������������Ч��ѡƱ</returns>
     */
    public Iterable<Vote> GetVotes()
    {
        return GetVotes(Collections.emptyList());
    }

    public abstract Iterable<Vote> GetVotes(Iterable<Transaction> others);

    /**
     *  �жϽ����Ƿ�˫��
     *  <param name="tx">����</param>
     *  <returns>���ؽ����Ƿ�˫��</returns>
     */
    public abstract boolean IsDoubleSpend(Transaction tx);

    /**
     *  �����鱻д�뵽Ӳ�̺����
     *  <param name="block">����</param>
     */
    protected void OnPersistCompleted(Block block)
    {
        synchronized (_miners)
        {
            _miners.clear();
        }
        // TODO
        //if (PersistCompleted != null) PersistCompleted(this, block);
    }

    /**
     *  ע��Ĭ�ϵ�������ʵ��
     *  <param name="blockchain">������ʵ��</param>
     *  <returns>����ע����������ʵ��</returns>
     */
    public static Blockchain RegisterBlockchain(Blockchain blockchain)
    {
        if (blockchain == null) throw new NullPointerException();
        if (getDefault() != null) getDefault().Dispose();
        _default = blockchain;
        return blockchain;
    }
}