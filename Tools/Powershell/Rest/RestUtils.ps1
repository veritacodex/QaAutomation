function RestQuery {

    [CmdletBinding()]
    param (
        [Parameter(Mandatory)]
        [string]$queryFile,
        [Parameter(Mandatory)]
        [string]$endPoint,
        [Parameter(Mandatory)]
        [string]$method
    )
   
    # $fileContent = Get-Content $queryFile
    # $queryResponse = Invoke-WebRequest -Body $fileContent -Method 'POST' -Uri $endPoint -Headers $headers

    [hashtable]$headers = @{}
    $headers.Add('Content-Type', 'application/json')
    $body = Get-Content './body.json'
    $queryResponse = Invoke-WebRequest -Uri 'https://ncus-offer-letter-service-dev.apps.cfd06.rabobank.nl/offerletter/?ReportCode=R060&BankCode=1108&UserId=SonaliSA' -Method 'POST' -Body $body -Headers $headers 
    Write-Host $queryResponse



    
    # $body = Get-Content $queryFile
    # $queryResponse = Invoke-WebRequest -Uri $endPoint -Method $method -Body $body -Headers $headers
    # return $queryResponse;
}