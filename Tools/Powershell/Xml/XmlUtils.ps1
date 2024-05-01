function GetXmlNodeContent {

    [CmdletBinding()]
    param (
        [Parameter(Mandatory)]
        [string]$inputXml,
        [Parameter(Mandatory)]
        [string]$xPath
    )
    $value = Select-Xml -Content $inputXml -XPath $xPath
    return $value;
}