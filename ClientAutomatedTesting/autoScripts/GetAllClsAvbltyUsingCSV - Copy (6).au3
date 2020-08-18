#cs ----------------------------------------------------------------------------

 AutoIt Version: 3.3.14.2
 Author:         myName

 Script Function:
	Template AutoIt script.

#ce ----------------------------------------------------------------------------

; Script Start - Add your code below here

#include <GUIConstants.au3>
#include <string.au3>
#include <File.au3>


Global $Skipline = 0 ;0==> first line
Global $shortWaitInMilliSec = 500
Global $waitInMilliSec = 1000
Global $longWaitInMilliSec = 2000
Global $stnCodeSize = 4
Global $mcpEnquiryOption = 12
Global $continueFlag = 1


Func _WinWaitActivate($title,$text,$timeout=0)
	WinWait($title,$text,$timeout)
	If Not WinActive($title,$text) Then WinActivate($title,$text)
	WinWaitActive($title,$text,$timeout)
 EndFunc

 Func _processData($line)
	_WinWaitActivate("(B) TELNET (10.64.0.130) - PowerTerm 525","")
   ; MsgBox(0, '', 'Received ' & $line)
   $input = StringSplit($line, ",", 1)
   $trainNo = $input[1]
   $doj = $input[2]
   $dojDD = StringSplit($input[2], "-", 1)[2]
   $dojMM = StringSplit($input[2], "-", 1)[1]
   $src = $input[3]
   $destn = $input[4]

   $quota = $input[5]
 ;  MsgBox(0, "Values Read ", $trainNo & " , " & $doj & " , " &  $src  & " , " & $destn & " , " & $quota & " , "   )
   Send("{CTRLDOWN}w{CTRLUP}")
   Send("  ")
   Send($mcpEnquiryOption)
   Sleep($shortWaitInMilliSec)
   Send($trainNo)
  ; Send("{TAB}")
   Sleep($waitInMilliSec)
   Send($dojDD)
   Sleep($waitInMilliSec)
   ;Send("{TAB}")

   Send($dojMM)
   Sleep($waitInMilliSec)
   Send($src)

   If StringLen ( $src ) < $stnCodeSize  Then Send("{TAB}")



   Send($destn)
   Sleep($waitInMilliSec)
   If StringLen ( $destn ) < $stnCodeSize  Then Send("{TAB}")

   Send($quota)
   Sleep($waitInMilliSec)


   MouseClick("right",685,299,1)
   Sleep($shortWaitInMilliSec)
MouseMove(53,284)
Sleep($shortWaitInMilliSec)
MouseDown("left")
Sleep($shortWaitInMilliSec)
MouseMove(971,351)
Sleep($shortWaitInMilliSec)
MouseUp("left")



Sleep($waitInMilliSec)


EndFunc







 _WinWaitActivate("Program Manager","FolderView")

;for array - start

Local $aArray_Base[2] = ["Org Item 0", "Org item 1"]
Local $aArray = $aArray_Base
Local $stringToAddInArray = ''
Local $stringToVerifySuccess = "DATE*_CLASS"


;for array - end




$file = FileOpen("D:\APOSWorkSpaceInGradle\UsingKatlon\ClientAutomatedTesting\UserDataFiles\inputfiles\TDRC_InputFile.csv", 0)

If $file = -1 Then
   MsgBox(0, "error", "File doesn't exist or can't be read")
Exit
EndIf



$countLine = 0




;Loop for every line in CSV
While 1
   $continueFlag = 1
   $lineRead = (FileReadLine($file))
   If @error = -1 Then ExitLoop
   If $Skipline = $countLine Then
	  $countLine += 1
	  ContinueLoop
   EndIf
   while ($continueFlag = 1)
	  _processData($lineRead)

; Retrieve the data stored in the clipboard.
	  Local $sData = ClipGet()
	  Sleep($waitInMilliSec)
	  If (@error = 3 Or @error = 4) Then MsgBox($MB_SYSTEMMODAL, "", "EROR OCCURED DURING CLIPGET. Data stored is  " & @CRLF & $sData)
	 ; MsgBox($MB_SYSTEMMODAL, "", "The following data is stored in the clipboard: " & @CRLF & $sData)
	  If (StringInStr ($sData, $stringToVerifySuccess) = 0 )  Then
	;	 MsgBox($MB_SYSTEMMODAL, "", "SUCCESS MESSAGE NOT FOUND SO CONTINUING AGAIN FOR SAME VALUES"   )
		 Send("{ENTER}")
		 Sleep($waitInMilliSec)
		 Send("{CTRLDOWN}a{CTRLUP}")
		 Sleep($shortWaitInMilliSec)
		 $continueFlag = 1
	  Else
		 $continueFlag = 0
		 	 Send("{ENTER}")
		 Sleep($waitInMilliSec)
		 Send("{CTRLDOWN}a{CTRLUP}")
		 Sleep($shortWaitInMilliSec)
	  EndIf

   WEnd
   $sData =   $lineRead & @CRLF & $sData
	  Sleep($WaitInMilliSec)
	ConsoleWrite($sdata)
   Sleep($waitInMilliSec)




;If $value1 = $path Then
$countLine += 1
;Sleep($waitInMilliSec)

WEnd

FileClose($file)




