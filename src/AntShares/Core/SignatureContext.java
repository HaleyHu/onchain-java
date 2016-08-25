package AntShares.Core;

import java.lang.reflect.Array;
import java.util.*;
import java.util.Map.Entry;

import org.bouncycastle.math.ec.ECPoint;

import AntShares.*;
import AntShares.Core.Scripts.*;
import AntShares.Cryptography.ECC;
import AntShares.IO.Json.*;
import AntShares.Wallets.*;

/**
 *  ǩ��������
 */
public class SignatureContext
{
    /**
     *  Ҫǩ��������
     */
    public final Signable signable;
    /**
     *  Ҫ��֤�Ľű�ɢ��ֵ
     */
    public final UInt160[] scriptHashes;
    private final byte[][] redeemScripts;
    private final Map<ECPoint, byte[]>[] signatures;
    private final boolean[] completed;

    /**
     *  �ж�ǩ���Ƿ����
     */
    public boolean isCompleted()
    {
        for (boolean b : completed)
        	if (!b)
        		return false;
        return true;
    }

    /**
     *  ��ָ�������ݹ���ǩ��������
     *  <param name="signable">Ҫǩ��������</param>
     */
    @SuppressWarnings("unchecked")
	public SignatureContext(Signable signable)
    {
        this.signable = signable;
        this.scriptHashes = signable.getScriptHashesForVerifying();
        this.redeemScripts = new byte[scriptHashes.length][];
        this.signatures = (Map<ECPoint, byte[]>[]) Array.newInstance(Map.class, scriptHashes.length);
        this.completed = new boolean[scriptHashes.length];
    }

    /**
     *  ���һ��ǩ��
     *  <param name="contract">��ǩ������Ӧ�ĺ�Լ</param>
     *  <param name="pubkey">��ǩ������Ӧ�Ĺ�Կ</param>
     *  <param name="signature">ǩ��</param>
     *  <returns>����ǩ���Ƿ��ѳɹ����</returns>
     */
    public boolean add(Contract contract, ECPoint pubkey, byte[] signature)
    {
        for (int i = 0; i < scriptHashes.length; i++)
        {
            if (scriptHashes[i].equals(contract.getScriptHash()))
            {
                if (redeemScripts[i] == null)
                    redeemScripts[i] = contract.RedeemScript;
                if (signatures[i] == null)
                	signatures[i] = new HashMap<ECPoint, byte[]>();
                signatures[i].put(pubkey, signature);
                completed[i] |= 
                        contract.getParameterList().length == signatures[i].size()
                        && Arrays.stream(contract.getParameterList()).allMatch(
                                p -> p == ContractParameterType.Signature);
                return true;
            }
        }
        return false;
    }

    /**
     *  ��ָ����json�����н�����ǩ��������
     *  <param name="json">json����</param>
     *  <returns>����������</returns>
     */
    public static SignatureContext FromJson(JObject json)
    {
    	//TODO
//        String typename = String.Format("{0}.{1}", typeof(SignatureContext).Namespace, json["type"].AsString());
//        Signable signable = Assembly.GetExecutingAssembly().CreateInstance(typename) as Signable;
//        using (MemoryStream ms = new MemoryStream(json["hex"].AsString().HexToBytes(), false))
//        using (BinaryReader reader = new BinaryReader(ms, Encoding.UTF8))
//        {
//            signable.DeserializeUnsigned(reader);
//        }
//        SignatureContext context = new SignatureContext(signable);
//        JArray scripts = (JArray)json["scripts"];
//        for (int i = 0; i < scripts.Count; i++)
//        {
//            if (scripts[i] != null)
//            {
//                context.redeemScripts[i] = scripts[i]["redeem_script"].AsString().HexToBytes();
//                context.signatures[i] = new Dictionary<ECPoint, byte[]>();
//                JArray sigs = (JArray)scripts[i]["signatures"];
//                for (int j = 0; j < sigs.Count; j++)
//                {
//                    ECPoint pubkey = ECPoint.DecodePoint(sigs[j]["pubkey"].AsString().HexToBytes(), ECCurve.Secp256r1);
//                    byte[] signature = sigs[j]["signature"].AsString().HexToBytes();
//                    context.signatures[i].Add(pubkey, signature);
//                }
//                context.completed[i] = scripts[i]["completed"].AsBoolean();
//            }
//        }
//        return context;
        return null;
    }

    /**
     *  ��ǩ���������л������ǩ���ĺ�Լ�ű�
     *  <returns>���غ�Լ�ű�</returns>
     */
    public Script[] getScripts()
    {
        if (!isCompleted()) throw new IllegalStateException();
        Script[] scripts = new Script[signatures.length];
        for (int i = 0; i < scripts.length; i++)
        {
            try (ScriptBuilder sb = new ScriptBuilder())
            {
	            for (byte[] signature : signatures[i].entrySet().stream().sorted((a, b) -> ECC.compare(a.getKey(), b.getKey())).map(p -> p.getValue()).toArray(byte[][]::new))
	            {
	                sb.push(signature);
	            }
	            scripts[i] = new Script();
	            scripts[i].stackScript = sb.toArray();
	            scripts[i].redeemScript = redeemScripts[i];
            }
        }
        return scripts;
    }

    public static SignatureContext parse(String value)
    {
        //return FromJson(JObject.Parse(value));
    	//TODO
    	return null;
    }

    /**
     *  ��ǩ��������תΪjson����
     *  <returns>����json����</returns>
     */
    public JObject json()
    {
        JObject json = new JObject();
        json.set("type", new JString(signable.getClass().getTypeName()));
        json.set("hex", new JString(Helper.toHexString(signable.getHashData())));
        JArray scripts = new JArray();
        for (int i = 0; i < signatures.length; i++)
        {
            if (signatures[i] == null)
            {
                scripts.add(null);
            }
            else
            {
                scripts.add(new JObject());
                scripts.get(i).set("redeem_script", new JString(Helper.toHexString(redeemScripts[i])));
                JArray sigs = new JArray();
                for (Entry<ECPoint, byte[]> pair : signatures[i].entrySet())
                {
                    JObject signature = new JObject();
                    signature.set("pubkey", new JString(Helper.toHexString(pair.getKey().getEncoded(true))));
                    signature.set("signature", new JString(Helper.toHexString(pair.getValue())));
                    sigs.add(signature);
                }
                scripts.get(i).set("signatures", sigs);
                scripts.get(i).set("completed", new JBoolean(completed[i]));
            }
        }
        json.set("scripts", scripts);
        return json;
    }

    @Override public String toString()
    {
        return json().toString();
    }
}
