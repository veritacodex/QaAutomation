using System;
using System.Diagnostics;
using System.IO;
using System.Text;
using System.Windows.Controls;
using System.Windows.Input;

namespace ContractComparer.Helper;

public class ExcelHelper
{
    public static void SaveToCSV(DataGrid dataGrid)
    {
        var filePath = @"C:\Users\PerezJA\Documents\output.csv";
        CreateCsvfile(dataGrid, filePath);
        Process.Start(@"C:\Program Files\Microsoft Office\root\Office16\EXCEL.EXE", filePath);
    }

    private static void CreateCsvfile(DataGrid dataGrid, string FilePath)
    {
        dataGrid.SelectAllCells();
        dataGrid.ClipboardCopyMode = DataGridClipboardCopyMode.IncludeHeader;
        ApplicationCommands.Copy.Execute(null, dataGrid);
        dataGrid.UnselectAllCells();
        String result = (string)System.Windows.Clipboard.GetData(System.Windows.DataFormats.CommaSeparatedValue);
        File.WriteAllText(FilePath, result, UnicodeEncoding.UTF8);
    }

}
