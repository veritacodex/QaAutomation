
using System.Collections.Generic;
using ContractComparer.Model;

namespace ContractComparer.Helper;

public class ResultsHelper
{
    internal static List<Result> GetResults(string source, string target, string result)
    {
        var resultFlattened = JsonHelper.DeserializeAndFlatten(result);
        var sourceFlattened = JsonHelper.DeserializeAndFlatten(source);
        var targetFlattened = JsonHelper.DeserializeAndFlatten(target);

        var results = new List<Result>();
        foreach (var resultItem in resultFlattened)
        {
            var sourceValue = GetValue(resultItem.Key, sourceFlattened);
            var targetValue = GetValue(resultItem.Key, targetFlattened);
            if (sourceValue.Trim() != targetValue.Trim())
                results.Add(new Result
                {
                    Key = resultItem.Key,
                    Source = sourceValue,
                    Target = targetValue,
                    MissingInSource = string.IsNullOrEmpty(sourceValue) && !string.IsNullOrEmpty(targetValue),
                    MissingInTarget = string.IsNullOrEmpty(targetValue) && !string.IsNullOrEmpty(sourceValue)
                });
        }
        return results;
    }

    public static string GetValue(string key, Dictionary<string, object> dictionary)
    {
        if (dictionary.TryGetValue(key, out object value))
            return value.ToString();
        return string.Empty;
    }
}
