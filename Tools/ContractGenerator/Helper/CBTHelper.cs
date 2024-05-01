using System.Linq;
using System.Text;
using Newtonsoft.Json.Linq;

namespace ContractGenerator.Helper;

public class CBTHelper
{
    private static string previousLine = "";
    public static void PrintContract(JToken token, int depth, StringBuilder output)
    {
        var spacer = string.Join("", Enumerable.Range(0, depth).Select(_ => "\t"));

        if (token is JProperty jProp)
        {
            var val = jProp.Value is JValue value ? value.Value : string.Empty;

            if (!token.First.HasValues)
                output.AppendLine($"{spacer}.stringValue(\"{jProp.Name}\", \"{val}\")");

            foreach (var child in jProp.Children())
            {
                PrintContract(child, depth + 1, output);
            }
        }
        else if (token is JObject)
        {
            var item = (JObject)token;
            var name = CleanName(item.Path);
            var line = $"{spacer}.object(\"{name}\", {FirstCharToLowerCase(name)} -> {FirstCharToLowerCase(name)}";
            var ignoreEnd = true;

            if (!previousLine.ToLower().Contains("array"))
            {
                output.AppendLine(line);
                ignoreEnd = false;
            }
            previousLine = line;

            foreach (var child in ((JObject)token).Children())
            {
                PrintContract(child, depth + 1, output);
            }
            if (!ignoreEnd)
                output.AppendLine($"{spacer})");
        }
        else if (token is JArray item)
        {
            var name = CleanName(item.Path);
            var line = string.Empty;

            if (item.Children().Count() > 1)
                line = $"{spacer}.array(\"{name}\", {FirstCharToLowerCase(name)} -> {FirstCharToLowerCase(name)}";
            else if (item.Children().Count() == 1)
                line = $"{spacer}.minArrayLike(\"{name}\", 1, {FirstCharToLowerCase(name)} -> {FirstCharToLowerCase(name)}";

            output.AppendLine(line);
            previousLine = line;

            foreach (var child in item.Children())
            {
                PrintContract(child, depth + 1, output);
            }
            output.AppendLine($"{spacer})");
        }
    }

    private static string CleanName(string name)
    {
        if (name.Contains('.'))
        {
            var startName = name.LastIndexOf(".") + 1;
            name = name[startName..];
        }

        return name;
    }

    public static string FirstCharToLowerCase(string str)
    {
        if (!string.IsNullOrEmpty(str) && char.IsUpper(str[0]))
            return str.Length == 1 ? char.ToLower(str[0]).ToString() : char.ToLower(str[0]) + str[1..];

        return str;
    }
}
