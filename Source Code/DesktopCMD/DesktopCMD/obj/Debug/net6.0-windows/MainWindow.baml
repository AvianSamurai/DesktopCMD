   M S B A M L   `   `   `  ????E  ADesktopCMD, Version=1.0.0.0, Culture=neutral, PublicKeyToken=null'clr-namespace:DesktopCMD
DesktopCMD  5   	       DesktopCMD.MainWindow   5   	   W SPresentationCore, Version=6.0.0.0, Culture=neutral, PublicKeyToken=31bf3856ad364e35\ XPresentationFramework, Version=6.0.0.0, Culture=neutral, PublicKeyToken=31bf3856ad364e35e aSystem.Windows.Controls.Ribbon, Version=6.0.0.0, Culture=neutral, PublicKeyToken=b77a5c561934e089R NWindowsBase, Version=6.0.0.0, Culture=neutral, PublicKeyToken=31bf3856ad364e35F 9http://schemas.microsoft.com/winfx/2006/xaml/presentation     5   	   R NSystem.Xaml, Version=6.0.0.0, Culture=neutral, PublicKeyToken=b77a5c561934e089:x,http://schemas.microsoft.com/winfx/2006/xaml     5   	   @d2http://schemas.microsoft.com/expression/blend/2008     5   	   Jmc;http://schemas.openxmlformats.org/markup-compatibility/2006     5   	   *localclr-namespace:DesktopCMD     5   	   -   5          ?Name$  win_MainWindow??5   	    ? Title$ DesktopConsole??5   	   $??89??6    $	??600??6,    ? 
ResizeMode$ NoResize=?68    ? WindowStartupLocation$ CenterScreen=?6N    ? WindowStyle$
 None=?6s   
??????  ? AllowsTransparency$
 True??6?    J? OpacityMask
 ?   ?.??5	      ? .+?5
   
   ?? -   5         ??Name$ 	mainframe??5
      $??Center=?6'   $??0,10,0,0q?6D   ???	#3F000000$	??580??6i    ?? RadiusX$ 30??6u   	 ?? RadiusY$	 30??6?   ???	#7F000000$??70??6?   $	??Top=?6?   ?? 5   
   -   5        
 ??Name$
 tbx_CommandBox??5      $??75,28,130,0q?6*    ?? TextWrapping$
 Wrap=?6?   
?????? 
??????? ?? TextAlignment$
 Left=?6   $??24(?6?   $??Cascadia Mono Light)?6?   ???	#7FFFFFFF K? ZIndex$ 10??6?   $??33??6?   $	??Top=?6?   .??6   ? 	Resources 5       ?? 
TargetType ?? Property ?? Value??%^   '??       ?? 5      " ??6   .?5      ??  ?@??CornerRadius$ 5??65   5      5      .??6!   5      ?? 5   
   -   5         ??Name$ btn_RunButton??5      $	??Run??6(   $
??Left=?66   $??33??6Q   $??
475,28,0,0q?6]   $	??Top=?6q   $??95??6?    ?? 	IsDefault$
 True??6?   
??????????	#00000000
??????? 5      ??%^   '??       ?? 5      " ??6   .?5      ??  ?@??CornerRadius$ 5??65   5      5      5      ?? 5   
   -   5         ??Name$ lbl_cmd??5      $	??CMD??6!   $
??Left=?6/   $??33??6J   $??	22,28,0,0q?6V   $	??Top=?6i   $??48??6?   
???????$??22(?6?   $??Cascadia Mono Light)?6?   5      5      