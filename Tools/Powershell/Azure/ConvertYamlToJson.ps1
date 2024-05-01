[CmdletBinding()]
param(
  [string]$InputFile,
  [string]$OutputFile
)

$yaml = Get-Content $InputFile | ConvertFrom-Yaml
$json = $yaml | ConvertTo-Json
Set-Content -Path $OutputFile -Value $json