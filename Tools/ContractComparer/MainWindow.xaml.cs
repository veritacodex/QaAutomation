using System.IO;
using System.Windows;
using Microsoft.Win32;
using Newtonsoft.Json.Linq;
using JsonDiffPatchDotNet;
using ContractComparer.Helper;
using System;
using System.Collections.Generic;
using ContractComparer.Model;

namespace ContractComparer;

public partial class MainWindow : Window
{
    private const string Folder = @"C:\Testing\Tools\ContractComparer\Json\";

    public MainWindow()
    {
        InitializeComponent();
    }

    private void SourceFile_Click(object sender, RoutedEventArgs e)
    {
        OpenFileDialog openFileDialog = new OpenFileDialog();
        if (openFileDialog.ShowDialog() == true)
            txtSourceFile.Text = openFileDialog.FileName;
    }

    private void TargetFile_Click(object sender, RoutedEventArgs e)
    {
        OpenFileDialog openFileDialog = new OpenFileDialog();
        if (openFileDialog.ShowDialog() == true)
            txtTargetFile.Text = openFileDialog.FileName;
    }

    private void Compare_Click(object sender, RoutedEventArgs e)
    {
        try
        {
            CleanSource(txtSourceFile.Text);

            var source = File.ReadAllText(Folder + "source.json");
            var target = File.ReadAllText(txtTargetFile.Text);

            SaveFilesForManualCompare(source, target);

            var diffObj = new JsonDiffPatch();
            var result = diffObj.Diff(source, target);
            if (result != null)
            {
                var results = ResultsHelper.GetResults(source, target, result);
                dgResults.ItemsSource = results;
                lblNumberOfChanges.Content = "Number of changes: " + results.Count;
            }
            else
            {
                lblNumberOfChanges.Content = "Number of changes: " + 0;
                dgResults.ItemsSource = new List<Result>();
            }
        }
        catch (Exception ex)
        {
            MessageBox.Show(ex.ToString());
        }
    }

    private void SaveFilesForManualCompare(string source, string target)
    {
        File.WriteAllText(Folder + "consumer.json", source);
        File.WriteAllText(Folder + "provider.json", target);
    }

    private void ExportToExcel_Click(object sender, RoutedEventArgs e)
    {
        ExcelHelper.SaveToCSV(dgResults);
    }

    private void CleanSource(string sourcePath)
    {
        var consumerString = File.ReadAllText(sourcePath);
        JObject json = JObject.Parse(consumerString);
        var result = json.SelectToken("interactions[0].request.body");
        if (result != null)
            File.WriteAllText(Folder + "source.json", result.ToString());
        else
            File.WriteAllText(Folder + "source.json", json.ToString());
    }
}