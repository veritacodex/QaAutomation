using System;

namespace ContractComparer.Model;

public class Result
{
    public String Key { get; set; }
    public String Source { get; set; }
    public String Target { get; set; }
    public bool MissingInSource { get; internal set; }
    public bool MissingInTarget { get; internal set; }

}
