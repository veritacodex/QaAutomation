using System;
using System.Collections.Generic;
using Newtonsoft.Json.Linq;

namespace ContractComparer.Helper;

public class JsonHelper
{
    public static Dictionary<string, object> DeserializeAndFlatten(string json)
    {
        Dictionary<string, object> dict = new Dictionary<string, object>();
        JToken token = JToken.Parse(json);
        FillDictionaryFromJToken(dict, token, "");
        return dict;
    }

    private static void FillDictionaryFromJToken(Dictionary<string, object> dict, JToken token, string prefix)
    {
        switch (token.Type)
        {
            case JTokenType.Object:
                foreach (JProperty prop in token.Children<JProperty>())
                {
                    if (ValidName(prefix, prop.Name))
                        FillDictionaryFromJToken(dict, prop.Value, Join(prefix, prop.Name));
                }
                break;

            case JTokenType.Array:
                int index = 0;
                foreach (JToken value in token.Children())
                {
                    if (ValidName(prefix, string.Empty))
                        FillDictionaryFromJToken(dict, value, Join(prefix, index.ToString()));
                    index++;
                }
                break;

            default:
                dict.TryAdd(prefix, ((JValue)token).Value);
                break;
        }
    }

    private static bool ValidName(string prefix, string name)
    {
        var valid = !prefix.EndsWith("_t") && !name.EndsWith("_t");
        return valid;
    }

    private static string Join(string prefix, string name)
    {        
        bool isNumeric = int.TryParse(name, out int n);
        if (!isNumeric)
            return string.IsNullOrEmpty(prefix) ? name : prefix + "." + name;
        else return prefix.Trim();
    }
}