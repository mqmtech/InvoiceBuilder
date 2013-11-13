/* 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 *
 */

/////UTILS///
(function(window){

	var container = null;
    var isCustomAC_OETags=false;

    function cxFlash(){
        //Public Functions
        this.showObj=showObj;

    }

    //***************  AC_OETags FOR FLASH OBJECTS******************//
    if(!isCustomAC_OETags){
        // Flash Player Version Detection - Rev 1.6
        // Detect Client Browser type
        // Copyright(c) 2005-2006 Adobe Macromedia Software, LLC. All rights reserved.
        var isIE  = (navigator.appVersion.indexOf("MSIE") != -1) ? true : false;
        var isWin = (navigator.appVersion.toLowerCase().indexOf("win") != -1) ? true : false;
        var isOpera = (navigator.userAgent.indexOf("Opera") != -1) ? true : false;

        function ControlVersion()
        {
            var version;
            var axo;
            var e;

            // NOTE : new ActiveXObject(strFoo) throws an exception if strFoo isn't in the registry

            try {
                // version will be set for 7.X or greater players
                axo = new ActiveXObject("ShockwaveFlash.ShockwaveFlash.7");
                version = axo.GetVariable("$version");
            } catch (e) {
            }

            if (!version)
            {
                try {
                    // version will be set for 6.X players only
                    axo = new ActiveXObject("ShockwaveFlash.ShockwaveFlash.6");

                    // installed player is some revision of 6.0
                    // GetVariable("$version") crashes for versions 6.0.22 through 6.0.29,
                    // so we have to be careful.

                    // default to the first public version
                    version = "WIN 6,0,21,0";

                    // throws if AllowScripAccess does not exist (introduced in 6.0r47)
                    axo.AllowScriptAccess = "always";

                    // safe to call for 6.0r47 or greater
                    version = axo.GetVariable("$version");

                } catch (e) {
                }
            }

            if (!version)
            {
                try {
                    // version will be set for 4.X or 5.X player
                    axo = new ActiveXObject("ShockwaveFlash.ShockwaveFlash.3");
                    version = axo.GetVariable("$version");
                } catch (e) {
                }
            }

            if (!version)
            {
                try {
                    // version will be set for 3.X player
                    axo = new ActiveXObject("ShockwaveFlash.ShockwaveFlash.3");
                    version = "WIN 3,0,18,0";
                } catch (e) {
                }
            }

            if (!version)
            {
                try {
                    // version will be set for 2.X player
                    axo = new ActiveXObject("ShockwaveFlash.ShockwaveFlash");
                    version = "WIN 2,0,0,11";
                } catch (e) {
                    version = -1;
                }
            }

            return version;
        }

        // JavaScript helper required to detect Flash Player PlugIn version information
        function GetSwfVer(){
            // NS/Opera version >= 3 check for Flash plugin in plugin array
            var flashVer = -1;

            if (navigator.plugins != null && navigator.plugins.length > 0) {
                if (navigator.plugins["Shockwave Flash 2.0"] || navigator.plugins["Shockwave Flash"]) {
                    var swVer2 = navigator.plugins["Shockwave Flash 2.0"] ? " 2.0" : "";
                    var flashDescription = navigator.plugins["Shockwave Flash" + swVer2].description;
                    var descArray = flashDescription.split(" ");
                    var tempArrayMajor = descArray[2].split(".");
                    var versionMajor = tempArrayMajor[0];
                    var versionMinor = tempArrayMajor[1];
                    var versionRevision = descArray[3];
                    if (versionRevision == "") {
                        versionRevision = descArray[4];
                    }
                    if (versionRevision[0] == "d") {
                        versionRevision = versionRevision.substring(1);
                    } else if (versionRevision[0] == "r") {
                        versionRevision = versionRevision.substring(1);
                        if (versionRevision.indexOf("d") > 0) {
                            versionRevision = versionRevision.substring(0, versionRevision.indexOf("d"));
                        }
                    } else if (versionRevision[0] == "b") {
                        versionRevision = versionRevision.substring(1);
                    }
                    var flashVer = versionMajor + "." + versionMinor + "." + versionRevision;
                }
            }
            // MSN/WebTV 2.6 supports Flash 4
            else if (navigator.userAgent.toLowerCase().indexOf("webtv/2.6") != -1) flashVer = 4;
            // WebTV 2.5 supports Flash 3
            else if (navigator.userAgent.toLowerCase().indexOf("webtv/2.5") != -1) flashVer = 3;
            // older WebTV supports Flash 2
            else if (navigator.userAgent.toLowerCase().indexOf("webtv") != -1) flashVer = 2;
            else if ( isIE && isWin && !isOpera ) {
                flashVer = ControlVersion();
            }
            return flashVer;
        }

        // When called with reqMajorVer, reqMinorVer, reqRevision returns true if that version or greater is available
        function DetectFlashVer(reqMajorVer, reqMinorVer, reqRevision)
        {
            versionStr = GetSwfVer();
            if (versionStr == -1 ) {
                return false;
            } else if (versionStr != 0) {
                if(isIE && isWin && !isOpera) {
                    // Given "WIN 2,0,0,11"
                    tempArray         = versionStr.split(" "); 	// ["WIN", "2,0,0,11"]
                    tempString        = tempArray[1];			// "2,0,0,11"
                    versionArray      = tempString.split(",");	// ['2', '0', '0', '11']
                } else {
                    versionArray      = versionStr.split(".");
                }
                var versionMajor      = versionArray[0];
                var versionMinor      = versionArray[1];
                var versionRevision   = versionArray[2];

                // is the major.revision >= requested major.revision AND the minor version >= requested minor
                if (versionMajor > parseFloat(reqMajorVer)) {
                    return true;
                } else if (versionMajor == parseFloat(reqMajorVer)) {
                    if (versionMinor > parseFloat(reqMinorVer))
                        return true;
                    else if (versionMinor == parseFloat(reqMinorVer)) {
                        if (versionRevision >= parseFloat(reqRevision))
                            return true;
                    }
                }
                return false;
            }
        }

        function AC_AddExtension(src, ext)
        {
            var qIndex = src.indexOf('?');
            if ( qIndex != -1)
            {
                // Add the extention (if needed) before the query params
                var path = src.substring(0, qIndex);
                if (path.length >= ext.length && path.lastIndexOf(ext) == (path.length - ext.length))
                    return src;
                else
                    return src.replace(/\?/, ext+'?');
            }
            else
            {
                // Add the extension (if needed) to the end of the URL
                if (src.length >= ext.length && src.lastIndexOf(ext) == (src.length - ext.length))
                    return src;  // Already have extension
                else
                    return src + ext;
            }
        }

        function AC_Generateobj(objAttrs, params, embedAttrs)
        {
            var str = '';
            if (isIE && isWin && !isOpera)
            {
                str += '<object ';
                for (var i in objAttrs)
                    str += i + '="' + objAttrs[i] + '" ';
                str += '>';
                for (var i in params)
                    str += '<param name="' + i + '" value="' + params[i] + '" /> ';
                str += '</object>';
            } else {
                str += '<embed ';
                for (var i in embedAttrs)
                    str += i + '="' + embedAttrs[i] + '" ';
                str += '> </embed>';
            }

            //document.write(str);
            container.innerHTML=str;
        }

        function AC_FL_RunContent(){
            var ret =
            AC_GetArgs
            (  arguments, ".swf", "movie", "clsid:d27cdb6e-ae6d-11cf-96b8-444553540000"
                , "application/x-shockwave-flash"
                );
            AC_Generateobj(ret.objAttrs, ret.params, ret.embedAttrs);
        }

        function AC_GetArgs(args, ext, srcParamName, classid, mimeType){
            var ret = new Object();
            ret.embedAttrs = new Object();
            ret.params = new Object();
            ret.objAttrs = new Object();
            for (var i=0; i < args.length; i=i+2){
                var currArg = args[i].toLowerCase();

                switch (currArg){
                    case "classid":
                        break;
                    case "pluginspage":
                        ret.embedAttrs[args[i]] = args[i+1];
                        break;
                    case "src":
                    case "movie":
                        args[i+1] = AC_AddExtension(args[i+1], ext);
                        ret.embedAttrs["src"] = args[i+1];
                        ret.params[srcParamName] = args[i+1];
                        break;
                    case "onafterupdate":
                    case "onbeforeupdate":
                    case "onblur":
                    case "oncellchange":
                    case "onclick":
                    case "ondblClick":
                    case "ondrag":
                    case "ondragend":
                    case "ondragenter":
                    case "ondragleave":
                    case "ondragover":
                    case "ondrop":
                    case "onfinish":
                    case "onfocus":
                    case "onhelp":
                    case "onmousedown":
                    case "onmouseup":
                    case "onmouseover":
                    case "onmousemove":
                    case "onmouseout":
                    case "onkeypress":
                    case "onkeydown":
                    case "onkeyup":
                    case "onload":
                    case "onlosecapture":
                    case "onpropertychange":
                    case "onreadystatechange":
                    case "onrowsdelete":
                    case "onrowenter":
                    case "onrowexit":
                    case "onrowsinserted":
                    case "onstart":
                    case "onscroll":
                    case "onbeforeeditfocus":
                    case "onactivate":
                    case "onbeforedeactivate":
                    case "ondeactivate":
                    case "type":
                    case "codebase":
                        ret.objAttrs[args[i]] = args[i+1];
                        break;
                    case "id":
                    case "width":
                    case "height":
                    case "align":
                    case "vspace":
                    case "hspace":
                    case "class":
                    case "title":
                    case "accesskey":
                    case "name":
                    case "tabindex":
                        ret.embedAttrs[args[i]] = ret.objAttrs[args[i]] = args[i+1];
                        break;
                    default:
                        ret.embedAttrs[args[i]] = ret.params[args[i]] = args[i+1];
                }
            }
            ret.objAttrs["classid"] = classid;
            if (mimeType) ret.embedAttrs["type"] = mimeType;
            return ret;
        }

    //*************** END AC_OETags FOR FLASH OBJECTS******************//
    } //End If IsCustomLibrary

    // -----------------------------------------------------------------------------
    // Globals
    // Major version of Flash required
    var requiredMajorVersion = 9;
    // Minor version of Flash required
    var requiredMinorVersion = 0;
    // Minor version of Flash required
    var requiredRevision = 124;
    // -----------------------------------------------------------------------------
    // -->


    // Version check for the Flash Player that has the ability to start Player Product Install (6.0r65)
    var hasProductInstall = DetectFlashVer(6, 0, 65);

    // Version check based upon the values defined in globals
    var hasRequestedVersion = DetectFlashVer(requiredMajorVersion, requiredMinorVersion, requiredRevision);

    function showObj(src, node, config){
    	if(!config){
    		config = new Object();
    		config.width = "600px";
    		config.height="800px";
    		config.align="middle";
    		config.id="simpleApp";
    		config.quality="high";
    		config.bgcolor="#869ca7";
    		config.name="simpleApp";
    		//config.allowScriptAccess="sameDomain";
    		config.allowScriptAccess="allways";
    		config.type="application/x-shockwave-flash";
    		config.pluginspage="http://www.adobe.com/go/getflashplayer";
    	}
    	container = window.document.createElement('div'); 
    	node.appendChild(container);
    	
        var swfName= cx.getBaseURL(src);
        var params= cx.getParameters(src);
        var bgcolor=cx.getParameter(src, "bgcolor")
        if(!bgcolor) bgcolor="#869ca7";

        if ( hasProductInstall && !hasRequestedVersion ) {
            // DO NOT MODIFY THE FOLLOWING FOUR LINES
            // Location visited after installation is complete if installation is required
            var MMPlayerType = (isIE == true) ? "ActiveX" : "PlugIn";
            var MMredirectURL = window.location;
            document.title = document.title.slice(0, 47) + " - Flash Player Installation";
            var MMdoctitle = document.title;

            AC_FL_RunContent(
                "src", "playerProductInstall",
                "FlashVars", "MMredirectURL="+MMredirectURL+'&MMplayerType='+MMPlayerType+'&MMdoctitle='+MMdoctitle+"&"+params+"",
                "width", config.width,
                "height", config.height,
                "align", "middle",
                "id", "simpleApp",
                "quality", "high",
                "bgcolor", "#869ca7",
                "name", "simpleApp",
                //"allowScriptAccess","sameDomain",
                "allowScriptAccess","allways",
                "type", "application/x-shockwave-flash",
                "pluginspage", "http://www.adobe.com/go/getflashplayer"
                );
            /*AC_FL_RunContent(
                "src", "playerProductInstall",
                "FlashVars", "MMredirectURL="+MMredirectURL+'&MMplayerType='+MMPlayerType+'&MMdoctitle='+MMdoctitle+"",
                "width", "290",
                "height", "220",
                "align", "middle",
                "id", "Ranking",
                "quality", "high",
                "bgcolor", "#eafaff",
                "name", "Ranking",
                "allowScriptAccess","sameDomain",
                "type", "application/x-shockwave-flash",
                "pluginspage", "http://www.adobe.com/go/getflashplayer"
                );*/
        } else if (hasRequestedVersion) {
            // if we've detected an acceptable version
            // embed the Flash Content SWF when all tests are passed
            AC_FL_RunContent(
                "src", swfName,
                "FlashVars", "MMredirectURL="+MMredirectURL+'&MMplayerType='+MMPlayerType+'&MMdoctitle='+MMdoctitle+"&"+params+"",
                "width", config.width,
                "height", config.height,
                "align", "middle",
                "id", "simpleApp",
                "quality", "high",
                "menu","false",
                "wmode","transparent",
                "bgcolor", bgcolor,
                "name", "simpleApp",
                "allowScriptAccess","sameDomain",
                "type", "application/x-shockwave-flash",
                "pluginspage", "http://www.adobe.com/go/getflashplayer"
                );
            /*AC_FL_RunContent(
                "src", swfName,
                "width", "290",
                "height", "220",
                "align", "middle",
                "id", "Ranking",
                "quality", "high",
                "bgcolor", "#eafaff",
                "name", "Ranking",
                "allowScriptAccess","sameDomain",
                "type", "application/x-shockwave-flash",
                "pluginspage", "http://www.adobe.com/go/getflashplayer"
                );*/

        } else {  // flash is too old or we can't detect the plugin
            var alternateContent = 'Alternate HTML content should be placed here. '
            + 'This content requires the Adobe Flash Player. '
            + '<a href=http://www.adobe.com/go/getflash/>Get Flash</a>';
            //document.write(alternateContent);  // insert non-flash content
            container.innerHTML=alternateContent;

        }
    }

    //window.cxflash=new cxFlash();
    window.cx.flash=new cxFlash();
    
})(window)