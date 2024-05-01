function SoapQuery {

    [CmdletBinding()]
    param (
        [Parameter(Mandatory)]
        [string]$queryFile,
        [Parameter(Mandatory)]
        [string]$endPoint
    )
    $queryResponse = Invoke-WebRequest $endPoint -Method Post -ContentType 'text/xml' -InFile $queryFile;
    return $queryResponse;
}