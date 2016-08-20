package AntShares.Core;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.bouncycastle.math.ec.ECPoint;

import AntShares.UInt160;
import AntShares.Core.Scripts.Script;
import AntShares.IO.Json.JObject;
import AntShares.Wallets.Contract;
import AntShares.Wallets.ContractParameterType;

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
    private final List<Map<ECPoint, byte[]> > signatures;
    private final boolean[] completed;

    /**
     *  �ж�ǩ���Ƿ����
     */
    public boolean isCompleted()
    {
        //return completed.All(p => p);
        return false;
    }

    /**
     *  ��ָ�������ݹ���ǩ��������
     *  <param name="signable">Ҫǩ��������</param>
     */
    public SignatureContext(Signable signable)
    {
        this.signable = signable;
        this.scriptHashes = signable.getScriptHashesForVerifying();
        this.redeemScripts = new byte[scriptHashes.length][];
        //this.signatures = new Map<ECPoint, byte[]>[scriptHashes.length];
        this.signatures = new ArrayList<Map<ECPoint, byte[]> >();
        for (int i = 0; i < scriptHashes.length; i++) {
            this.signatures.add(new HashMap<ECPoint, byte[]>());
        }
        this.completed = new boolean[scriptHashes.length];
    }

    /**
     *  ���һ��ǩ��
     *  <param name="contract">��ǩ������Ӧ�ĺ�Լ</param>
     *  <param name="pubkey">��ǩ������Ӧ�Ĺ�Կ</param>
     *  <param name="signature">ǩ��</param>
     *  <returns>����ǩ���Ƿ��ѳɹ����</returns>
     */
    public boolean Add(Contract contract, ECPoint pubkey, byte[] signature)
    {
        for (int i = 0; i < scriptHashes.length; i++)
        {
            if (scriptHashes[i] == contract.getScriptHash())
            {
                if (redeemScripts[i] == null)
                    redeemScripts[i] = contract.RedeemScript;
                signatures.get(i).put(pubkey, signature);
                completed[i] |= 
                        contract.getParameterList().length == signatures.get(i).size()
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
    public Script[] GetScripts()
    {
        if (!isCompleted()) throw new IllegalStateException();
        Script[] scripts = new Script[signatures.size()];
        for (int i = 0; i < scripts.length; i++)
        {
//            ScriptBuilder sb = new ScriptBuilder();
//            foreach (byte[] signature in signatures[i].OrderBy(p => p.Key).Select(p => p.Value))
//            {
//                sb.Push(signature);
//            }
//            scripts[i] = new Script
//            {
//                StackScript = sb.ToArray(),
//                RedeemScript = redeemScripts[i]
//            };
        }
        return scripts;
    }

    public static SignatureContext Parse(String value) throws IOException
    {
        return FromJson(JObject.Parse(value));
    }

    /**
     *  ��ǩ��������תΪjson����
     *  <returns>����json����</returns>
     */
    public JObject ToJson()
    {
//        JObject json = new JObject();
//        json["type"] = Signable.GetType().Name;
//        using (MemoryStream ms = new MemoryStream())
//        using (BinaryWriter writer = new BinaryWriter(ms, Encoding.UTF8))
//        {
//            Signable.SerializeUnsigned(writer);
//            writer.Flush();
//            json["hex"] = ms.ToArray().ToHexString();
//        }
//        JArray scripts = new JArray();
//        for (int i = 0; i < signatures.length; i++)
//        {
//            if (signatures[i] == null)
//            {
//                scripts.Add(null);
//            }
//            else
//            {
//                scripts.Add(new JObject());
//                scripts[i]["redeem_script"] = redeemScripts[i].ToHexString();
//                JArray sigs = new JArray();
//                foreach (var pair in signatures[i])
//                {
//                    JObject signature = new JObject();
//                    signature["pubkey"] = pair.Key.EncodePoint(true).ToHexString();
//                    signature["signature"] = pair.Value.ToHexString();
//                    sigs.Add(signature);
//                }
//                scripts[i]["signatures"] = sigs;
//                scripts[i]["completed"] = completed[i];
//            }
//        }
//        json["scripts"] = scripts;
//        return json;
        return new JObject();
    }

    @Override public String toString()
    {
        return ToJson().toString();
    }
}