package AntShares.Core;

import java.time.Duration;
import java.util.*;
import java.util.stream.Stream;

import org.bouncycastle.math.ec.ECPoint;

import AntShares.*;
import AntShares.Core.Scripts.Script;
import AntShares.Cryptography.ECC;
import AntShares.IO.Serializable;
import AntShares.Wallets.MultiSigContract;

/**
 *  ʵ�����������ܵĻ���
 */
public abstract class Blockchain implements AutoCloseable
{
    /**
     *  �����鱻д�뵽Ӳ�̺󴥷�
     */
    //public static event EventHandler<Block> PersistCompleted;

    /**
     *  ����ÿ�������ʱ����������Ϊ��λ
     */
    public static final int SECONDS_PER_BLOCK = 15;
    /**
     *  С�ϱҲ����ݼ���ʱ����������������Ϊ��λ
     */
    public static final int DECREMENT_INTERVAL = 2000000;
    /**
     *  ÿ�����������С�ϱҵ�����
     */
    public static final int[] MINTING_AMOUNT = { 8, 7, 6, 5, 4, 3, 2, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1 };
    /**
     *  ����ÿ�������ʱ����
     */
    public static final Duration TIME_PER_BLOCK = Duration.ofSeconds(SECONDS_PER_BLOCK);
    /**
     *  �󱸼������б�
     */
    public static final ECPoint[] STANDBY_MINERS =
    {
        ECC.secp256r1.getCurve().decodePoint(Helper.hexToBytes("0327da12b5c40200e9f65569476bbff2218da4f32548ff43b6387ec1416a231ee8")),
        ECC.secp256r1.getCurve().decodePoint(Helper.hexToBytes("026ce35b29147ad09e4afe4ec4a7319095f08198fa8babbe3c56e970b143528d22")),
        ECC.secp256r1.getCurve().decodePoint(Helper.hexToBytes("0209e7fd41dfb5c2f8dc72eb30358ac100ea8c72da18847befe06eade68cebfcb9")),
        ECC.secp256r1.getCurve().decodePoint(Helper.hexToBytes("039dafd8571a641058ccc832c5e2111ea39b09c0bde36050914384f7a48bce9bf9")),
        ECC.secp256r1.getCurve().decodePoint(Helper.hexToBytes("038dddc06ce687677a53d54f096d2591ba2302068cf123c1f2d75c2dddc5425579")),
        ECC.secp256r1.getCurve().decodePoint(Helper.hexToBytes("02d02b1873a0863cd042cc717da31cea0d7cf9db32b74d4c72c01b0011503e2e22")),
        ECC.secp256r1.getCurve().decodePoint(Helper.hexToBytes("034ff5ceeac41acf22cd5ed2da17a6df4dd8358fcb2bfb1a43208ad0feaab2746b")),
    };

    /**
     *  С�Ϲ�
     */
    public static final RegisterTransaction ANTSHARE;

    /**
     *  С�ϱ�
     */
    public static final RegisterTransaction ANTCOIN;
    
    //TODO: mainnet
    /**
     *  ��������
     */
    public static final Block GENESIS_BLOCK;

    /**
     *  ���������ṩ�Ĺ���
     */
    public abstract BlockchainAbility ability();
    /**
     *  ��ǰ��������ɢ��ֵ
     */
    public abstract UInt256 currentBlockHash();
    /**
     *  ��ǰ��������ͷ��ɢ��ֵ
     */
    public UInt256 currentHeaderHash(){ return currentBlockHash(); }
    /**
     *  Ĭ�ϵ�������ʵ��
     */
    private static Blockchain _default = null;
    public static Blockchain current() { return _default; }
    /**
     *  ����ͷ�߶�
     */
    public int headerHeight() { return height(); }
    /**
     *  ����߶�
     */
    public abstract int height();
    /**
     *  ��ʾ��ǰ��������ʵ���Ƿ�Ϊֻ����
     */
    public abstract boolean isReadOnly();

    static //Blockchain()
    {
        try
        {
			GENESIS_BLOCK = Serializable.from(Helper.hexToBytes("000000000000000000000000000000000000000000000000000000000000000000000000854f0d1fc6b4ebdd594132e399ac842976d7f5b2fc8a4dc68385766760e7714165fc8857000000001dac2b7c00000000f3812db982f3b0089a21a278988efeec6a027b250100015104001dac2b7c000000004000565b7b276c616e67273a277a682d434e272c276e616d65273a27e5b08fe89a81e882a128e6b58be8af9529277d2c7b276c616e67273a27656e272c276e616d65273a27416e74536861726528546573744e657429277d5d0000c16ff286230002a2d6adf934fe7f7e860ed48117e7590fd19db1ad018d15d5425fc5b3d6f11e74da1745e9b549bd0bfa1a569971c77eba30cd5a4b000000004001555b7b276c616e67273a277a682d434e272c276e616d65273a27e5b08fe89a81e5b88128e6b58be8af9529277d2c7b276c616e67273a27656e272c276e616d65273a27416e74436f696e28546573744e657429277d5d0000c16ff286230002a2d6adf934fe7f7e860ed48117e7590fd19db1ad018d15d5425fc5b3d6f11e749f7fd096d37ed2c0e3f7f0cfc924beef4ffceb6800000000011dac2b7c000001c9b4afd3375aa51e02531d5b2b5d9d1e0dad11b6f958ed6c86a4132da19d3ddc0000c16ff2862300197ff6783d512a740d42f4cc4f5572955fa44c9501000151"), Block.class);
		}
        catch (InstantiationException | IllegalAccessException ex)
        {
        	throw new UnsupportedOperationException(ex);
		}
        ANTSHARE = Arrays.stream(GENESIS_BLOCK.transactions).filter(p -> p.type == TransactionType.RegisterTransaction).map(p -> (RegisterTransaction)p).filter(p -> p.assetType == AssetType.AntShare).findFirst().get();
        ANTCOIN = Arrays.stream(GENESIS_BLOCK.transactions).filter(p -> p.type == TransactionType.RegisterTransaction).map(p -> (RegisterTransaction)p).filter(p -> p.assetType == AssetType.AntCoin).findFirst().get();
    }

    /**
     *  ��ָ����������ӵ���������
     *  <param name="block">Ҫ��ӵ�����</param>
     *  <returns>�����Ƿ���ӳɹ�</returns>
     */
    protected abstract boolean addBlock(Block block);

    /**
     *  ��ָ��������ͷ��ӵ�����ͷ����
     *  <param name="headers">Ҫ��ӵ�����ͷ�б�</param>
     */
    protected abstract void addHeaders(Iterable<Block> headers);
    
    @Override
    public abstract void close();

    /**
     *  �ж����������Ƿ����ָ�����ʲ�
     *  <param name="hash">�ʲ����</param>
     *  <returns>�������ָ���ʲ��򷵻�true</returns>
     */
    public boolean containsAsset(UInt256 hash)
    {
        return hash.equals(ANTSHARE.hash()) || hash.equals(ANTCOIN.hash());
    }

    /**
     *  �ж����������Ƿ����ָ��������
     *  <param name="hash">������</param>
     *  <returns>�������ָ�������򷵻�true</returns>
     */
    public boolean containsBlock(UInt256 hash)
    {
        return hash.equals(GENESIS_BLOCK.hash());
    }

    /**
     *  �ж����������Ƿ����ָ���Ľ���
     *  <param name="hash">���ױ��</param>
     *  <returns>�������ָ�������򷵻�true</returns>
     */
    public boolean containsTransaction(UInt256 hash)
    {
        return Arrays.stream(GENESIS_BLOCK.transactions).anyMatch(p -> p.hash().equals(hash));
    }

    public boolean containsUnspent(TransactionInput input)
    {
        return containsUnspent(input.prevHash, input.prevIndex);
    }

    public abstract boolean containsUnspent(UInt256 hash, short index);

    public abstract Stream<RegisterTransaction> getAssets();

    /**
     *  ����ָ���ĸ߶ȣ����ض�Ӧ��������Ϣ
     *  <param name="height">����߶�</param>
     *  <returns>���ض�Ӧ��������Ϣ</returns>
     */
    public Block getBlock(int height)
    {
        return getBlock(getBlockHash(height));
    }

    /**
     *  ����ָ����ɢ��ֵ�����ض�Ӧ��������Ϣ
     *  <param name="hash">ɢ��ֵ</param>
     *  <returns>���ض�Ӧ��������Ϣ</returns>
     */
    public Block getBlock(UInt256 hash)
    {
        if (hash.equals(GENESIS_BLOCK.hash()))
            return GENESIS_BLOCK;
        return null;
    }

    /**
     *  ����ָ���ĸ߶ȣ����ض�Ӧ�����ɢ��ֵ
     *  <param name="height">����߶�</param>
     *  <returns>���ض�Ӧ�����ɢ��ֵ</returns>
     */
    public UInt256 getBlockHash(int height)
    {
        if (height == 0) return GENESIS_BLOCK.hash();
        return null;
    }

    public Stream<EnrollmentTransaction> getEnrollments()
    {
        return getEnrollments(Stream.empty());
    }

    public abstract Stream<EnrollmentTransaction> getEnrollments(Stream<Transaction> others);

    /**
     *  ����ָ���ĸ߶ȣ����ض�Ӧ������ͷ��Ϣ
     *  <param name="height">����߶�</param>
     *  <returns>���ض�Ӧ������ͷ��Ϣ</returns>
     */
    public Block getHeader(int height)
    {
        return getHeader(getBlockHash(height));
    }

    /**
     *  ����ָ����ɢ��ֵ�����ض�Ӧ������ͷ��Ϣ
     *  <param name="hash">ɢ��ֵ</param>
     *  <returns>���ض�Ӧ������ͷ��Ϣ</returns>
     */
    public Block getHeader(UInt256 hash)
    {
        Block b = getBlock(hash);
        return b == null ? null : b.header();
    }

    public abstract UInt256[] getLeafHeaderHashes();

    /**
     *  ��ȡ�����˵ĺ�Լ��ַ
     *  <param name="miners">�����˵Ĺ�Կ�б�</param>
     *  <returns>���ؼ����˵ĺ�Լ��ַ</returns>
     */
    public static UInt160 getMinerAddress(ECPoint[] miners)
    {
        return Script.toScriptHash(MultiSigContract.CreateMultiSigRedeemScript(miners.length - (miners.length - 1) / 3, miners));
    }

    private ArrayList<ECPoint> _miners = new ArrayList<ECPoint>();
    /**
     *  ��ȡ��һ������ļ������б�
     *  <returns>����һ�鹫Կ����ʾ��һ������ļ������б�</returns>
     */
    public ECPoint[] getMiners()
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

    public Stream<ECPoint> getMiners(Stream<Transaction> others)
    {
    	//TODO
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
        return Stream.empty();
    }

    /**
     *  ����ָ����ɢ��ֵ��������һ���������Ϣ
     *  <param name="hash">ɢ��ֵ</param>
     *  <returns>������һ���������Ϣ>
     */
    public abstract Block getNextBlock(UInt256 hash);

    /**
     *  ����ָ����ɢ��ֵ��������һ�������ɢ��ֵ
     *  <param name="hash">ɢ��ֵ</param>
     *  <returns>������һ�������ɢ��ֵ</returns>
     */
    public abstract UInt256 getNextBlockHash(UInt256 hash);

    /**
     *  ����ָ�����ʲ���ţ����ض�Ӧ�ʲ��ķ�����
     *  <param name="asset_id">�ʲ����</param>
     *  <returns>���ض�Ӧ�ʲ��ĵ�ǰ�Ѿ����е�����</returns>
     */
    public abstract Fixed8 getQuantityIssued(UInt256 asset_id);

    /**
     *  ����ָ��������߶ȣ����ض�Ӧ���鼰֮ǰ���������а�����ϵͳ���õ�����
     *  <param name="height">����߶�</param>
     *  <returns>���ض�Ӧ��ϵͳ���õ�����</returns>
     */
    public long getSysFeeAmount(int height)
    {
        return getSysFeeAmount(getBlockHash(height));
    }

    /**
     *  ����ָ��������ɢ��ֵ�����ض�Ӧ���鼰֮ǰ���������а�����ϵͳ���õ�����
     *  <param name="hash">ɢ��ֵ</param>
     *  <returns>����ϵͳ���õ�����</returns>
     */
    public abstract long getSysFeeAmount(UInt256 hash);

    /**
     *  ����ָ����ɢ��ֵ�����ض�Ӧ�Ľ�����Ϣ
     *  <param name="hash">ɢ��ֵ</param>
     *  <returns>���ض�Ӧ�Ľ�����Ϣ</returns>
     */
    public Transaction getTransaction(UInt256 hash)
    {
        Out<Integer> height = new Out<Integer>();
        return getTransaction(hash, height);
    }

    /**
     *  ����ָ����ɢ��ֵ�����ض�Ӧ�Ľ�����Ϣ��ý�����������ĸ߶�
     *  <param name="hash">����ɢ��ֵ</param>
     *  <param name="height">���ظý�����������ĸ߶�</param>
     *  <returns>���ض�Ӧ�Ľ�����Ϣ</returns>
     */
    public Transaction getTransaction(UInt256 hash, Out<Integer> height)
    {
        Optional<Transaction> tx = Arrays.stream(GENESIS_BLOCK.transactions).filter(p -> p.hash().equals(hash)).findFirst();
        if (tx.isPresent())
        {
            height.set(0);
            return tx.get();
        }
        height.set(-1);
        return null;
    }

    public abstract Map<Short, Claimable> getUnclaimed(UInt256 hash);

    /**
     *  ����ָ����ɢ��ֵ����������ȡ��Ӧ��δ���ѵ��ʲ�
     *  <param name="hash">����ɢ��ֵ</param>
     *  <param name="index">���������</param>
     *  <returns>����һ�������������ʾһ��δ���ѵ��ʲ�</returns>
     */
    public abstract TransactionOutput getUnspent(UInt256 hash, short index);

    /**
     *  ��ȡѡƱ��Ϣ
     *  <returns>����һ��ѡƱ�б�������ǰ��������������Ч��ѡƱ</returns>
     */
    public Stream<Vote> getVotes()
    {
        return getVotes(Stream.empty());
    }

    public abstract Stream<Vote> getVotes(Stream<Transaction> others);

    /**
     *  �жϽ����Ƿ�˫��
     *  <param name="tx">����</param>
     *  <returns>���ؽ����Ƿ�˫��</returns>
     */
    public abstract boolean isDoubleSpend(Transaction tx);

    /**
     *  �����鱻д�뵽Ӳ�̺����
     *  <param name="block">����</param>
     */
    protected void onPersistCompleted(Block block)
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
    public static Blockchain register(Blockchain blockchain)
    {
        if (blockchain == null) throw new NullPointerException();
        if (_default != null) _default.close();
        _default = blockchain;
        return blockchain;
    }
}
