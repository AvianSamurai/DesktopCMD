using System;
using System.Collections.Generic;
using System.Diagnostics;
using System.IO;
using System.Linq;
using System.Text;
using System.Threading;
using System.Threading.Tasks;
using System.Windows;
using System.Windows.Controls;
using System.Windows.Data;
using System.Windows.Documents;
using System.Windows.Input;
using System.Windows.Media;
using System.Windows.Media.Imaging;
using System.Windows.Navigation;
using System.Windows.Shapes;

namespace DesktopCMD
{
    /// <summary>
    /// Interaction logic for MainWindow.xaml
    /// </summary>
    public partial class MainWindow : Window
    {

        private static int FADE_SPEED = 1; // mS
        public static MainWindow mw;
        public static bool runWithout = true;

        public MainWindow()
        {
            InitializeComponent();
            mw = this;
            this.ShowInTaskbar = false;

            btn_RunButton.Click += Btn_RunButton_Click;
            ApplySettings(mainframe, tbx_CommandBox, btn_RunButton, lbl_cmd);
        }

        private void Btn_RunButton_Click(object sender, RoutedEventArgs e)
        {
            if(tbx_CommandBox.Text.Length <= 1) { return; }

            if (tbx_CommandBox.Text.ToLower().Trim().Equals("settings"))
            {
                Process.Start("notepad.exe", Directory.GetCurrentDirectory() + "\\settings.txt");
                ApplySettings(mainframe, tbx_CommandBox, btn_RunButton, lbl_cmd);
                tbx_CommandBox.Text = "";
                return;
            }

            if(runWithout && tbx_CommandBox.Text.Substring(0, 2).Equals("pg"))
            {
                Process.Start("pg\\pg.bat", tbx_CommandBox.Text.Substring(3) + " & timeout 3 > nul");
                tbx_CommandBox.Text = "";
                return;
            }

            bool pause = !(tbx_CommandBox.Text.Substring(0, 2).Equals("pg"));
            Process.Start("cmd", "/C " + tbx_CommandBox.Text + (pause ? " & pause" : " & timeout 3 > nul"));
            tbx_CommandBox.Text = "";
        }

        private void ApplySettings(Rectangle mainframe, TextBox tbx_CommandBox, Button btn_RunButton, Label lbl_cmd)
        {
            using (StreamReader r = new StreamReader(Directory.GetCurrentDirectory() + "\\settings.txt"))
            {
                string settings = r.ReadToEnd();
                foreach (string s in settings.Split("\n"))
                {
                    switch (s.Split(":")[0])
                    {
                        case "bg-color":
                            SolidColorBrush sb = (SolidColorBrush)new BrushConverter().ConvertFrom(s.Split(":")[1]);
                            mainframe.Fill = sb;
                            break;

                        case "fg-color":
                            SolidColorBrush sbfg = (SolidColorBrush)new BrushConverter().ConvertFrom(s.Split(":")[1]);
                            tbx_CommandBox.Foreground = sbfg;
                            btn_RunButton.Foreground = sbfg;
                            lbl_cmd.Foreground = sbfg;
                            break;

                        case "corner-radius":
                            try
                            {
                                int i = Int32.Parse(s.Split(":")[1].Trim());
                                mainframe.RadiusX = i;
                                mainframe.RadiusY = i;
                            } catch (Exception e) { }
                            break;

                        case "run-without-seperate-java-program":
                            runWithout = s.Split(":")[1].ToLower().Trim().Equals("true");
                            break;
                    }
                }

            }
        }
    }
}
