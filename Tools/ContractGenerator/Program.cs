using System;
using System.IO;
using System.Text;
using ContractGenerator.Helper;
using Newtonsoft.Json;

namespace ContractGenerator;

public class Program
{
    public static void Main()
    {
        var json = File.ReadAllText(@"C:\Testing\repositories\tribe-bl-area-lo-bf-offer-bf-delivery\Pledge\Pledge _Company Assets_Inventory_Claim");
        var output = new StringBuilder();
        dynamic root = JsonConvert.DeserializeObject(json);
        CBTHelper.PrintContract(root, 0, output);
        File.WriteAllText(@"C:\Users\PerezJA\Documents\outputContractGen.txt", output.ToString());
        Console.WriteLine("Contract generated");
    }
}
