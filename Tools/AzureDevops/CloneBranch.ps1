[CmdletBinding()]
param(
    [Parameter(Mandatory = $true)]
    [String]$branchName
)

$masterRepoBase = "repo_url/_git/"
$masterRepoName = "repoName"
$baseFolder = "C:\Testing\repositories\"
$repoFolder = $baseFolder + $branchName
$checkoutFolder = $baseFolder + "checkout\"

Write-Host "Create folders for repository"
New-Item -ItemType Directory -Path $repoFolder
New-Item -ItemType Directory -Path $checkoutFolder

Write-Host "Clone repository"
Set-Location -Path $checkoutFolder
$cloneBranch = "git clone -b " + $branchName + " --single-branch " + $masterRepoBase + $masterRepoName
Invoke-Expression $cloneBranch

Write-Host "CleanupFolders"
$sourceFolder = $checkoutFolder + $masterRepoName
Copy-Item -Path $sourceFolder\* -Destination $repoFolder -Recurse -Force
Set-Location -Path "C:\Testing\Tools\AzureDevops"
Remove-Item -Recurse -Force $checkoutFolder

Write-Host "End of the script"
