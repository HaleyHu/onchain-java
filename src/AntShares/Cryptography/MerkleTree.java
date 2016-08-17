package AntShares.Cryptography;

import AntShares.UInt256;

/**
 *  ��ϣ��
 */
public class MerkleTree
{
    /**
     *  ������ڵ��ֵ
     *  <param name="hashes">�ӽڵ��б�</param>
     *  <returns>���ؼ���Ľ��</returns>
     */
    public static UInt256 ComputeRoot(UInt256[] hashes)
    {
        if (hashes.length == 0)
            throw new IllegalArgumentException();
        if (hashes.length == 1)
            return hashes[0];
        // TODO
        //return new UInt256(ComputeRoot(hashes.Select(p => p.ToArray()).ToArray()));
        return new UInt256();
    }

// TODO
//    private static byte[] ComputeRoot(byte[][] hashes)
//    {
//        if (hashes.length == 0)
//            throw new IllegalArgumentException();
//        if (hashes.length == 1)
//            return hashes[0];
//        if (hashes.length % 2 == 1)
//        {
//            // TODO
//            //hashes = hashes.Concat(new byte[][] { hashes[hashes.length - 1] }).ToArray();
//        }
//        byte[][] hashes_new = new byte[hashes.length / 2][];
//        for (int i = 0; i < hashes_new.length; i++)
//        {
//            // TODO
//            //hashes_new[i] = hashes[i * 2].Concat(hashes[i * 2 + 1]).Sha256().Sha256();
//        }
//        return ComputeRoot(hashes_new);
//    }
}
