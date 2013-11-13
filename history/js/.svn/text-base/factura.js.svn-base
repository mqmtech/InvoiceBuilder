///////////////////////***********ANONYMOUS FUNCTION TO HIDE CODE***********////////////////////////

///////////CXAPI//////////////
(function(window){

    var cxinstances = 0;
    var cx= new cxApi();
    
    function cxApi(){
        cxinstances++;
        this.loading=0;

        //Functions aggregated
        this.getElementsByClass=getElementsByClass;
        this.getElementsByClassPattern=getElementsByClassPattern;
        this.getBaseURL=getBaseURL;
        this.setBaseURL=setBaseURL;
        this.getParameters=getParameters;
        this.getParamFromURL=getParamFromURL;
        this.getParameter=getParameter;
        this.addParameter=addParameter;
        this.setParameter=setParameter;
        this.deleteParam=deleteParam;
        this.copyParams = copyParams;
        this.GetXmlHttpObject=GetXmlHttpObject;
        this.loadData=loadData;
        this.xmlString2Doc=xmlString2Doc;
        this.Doc2xmlString=Doc2xmlString;
        this.loadScript=loadScript;
        this.makeIFrame=makeIFrame;
        this.CrossHttpRequest=CrossHttpRequest;
        //Wizard aggregated
        this.Wizard = Wizard;
        this.WizardStep= WizardStep;
        //HashTable
        //this.Hashtable = Hashtable;

        //Libraries that which cxApi can be extended with
        //this.flash=null;

        //OwnFuctions
        //Example: cx.load("flash", "js/cxflash.js")
        this.load=function(extension, src){
            this.loading++;
            var scope = this;

            var extensionId = "cx"+extension;

            if(!src){
                src ="js/"+extensionId;
                //alert ("loadExtension: flashObject");
            }
      
            loadScript(src, function(){
                //alert ("scriptLoaded");
                scope.loading--;
                //scope.flash=cxflash; //From the added script
                //var addingExtension = "scope."+extension + "=" + extensionId;
                //eval(addingExtension);
            });
        }

        this.jsonp=function(url, callback){
            var request = new CrossHttpRequest();
            request.onreadystatechange = callback;
            request.open('GET', url);
            request.send(null);
            return request;
        }

        this.setOnLoadCallback = function(callback){
            if(this.loading>0){
                var timer = 50;
                var scope = this;
                setTimeout ( function () {
                    scope.onload.call( scope, callback );
                }, timer );
            }
            else
                callback();
        }
    }

    /////UTILS///
    
    //GetElementsByClass Classic
    function getElementsByClass(theClass, node, tag) {
    	var classElements = new Array();
    	if (node == null)
    		node = document;
    	if (tag == null)
    		tag = '*';
	    //Create Array of All HTML Tags
	    var allHTMLTags=node.getElementsByTagName(tag);
	
	    var j=0;
	    //Loop through all tags using a for loop
	    for (i=0; i<allHTMLTags.length; i++) {
		    //Get all tags with the specified class name.
		    if (allHTMLTags[i].className==theClass) {
		    	classElements[j]=allHTMLTags[i];
		    	j++;
		    }
	    }
	    return classElements;
    }
    
    //GetElementsByClass using an alternative method with pattern
    function getElementsByClassPattern(searchClass,node,tag) {

    	var classElements = new Array();
    	if (node == null)
    		node = document;
    	if (tag == null)
    		tag = '*';
    	var els = node.getElementsByTagName(tag);
    	var elsLen = els.length;
    	var pattern = new RegExp("(^|\\s)"+searchClass+"(\\s|$)");
    	var j = 0;
    	for (i = 0; i < elsLen; i++) {
    		if (pattern.test(els[i].className) ) {
    			classElements[j] = els[i];
    			j++;
    		}
    	}
    	return classElements;
    }

    
    
    function getBaseURL ( queryString ) {
        // Add "=" to the parameter name (i.e. parameterName=value)
        if ( queryString.length > 0 ) {
            // Find the beginning of the string
            var end = queryString.indexOf ( "?" );
            // If the parameter name is not found, skip it, otherwise return the value
            if ( end != -1 ) {
                // Add the length (integer) to the beginning
                return unescape ( queryString.substring ( 0, end ) );
            }
            else{
                // Return the string
                return queryString;
            }
        }
        // Return "null" if no parameter has been found
        return "null";
    }
    
    function setBaseURL ( queryString, n_baseURL ) {

        if ( queryString.length > 0 ) {
            // Find the beginning of the string
            var paramsStart = queryString.indexOf ( "?" );
            // If the parameter name is not found, skip it, otherwise return the value
            if ( paramsStart == -1 ) {
            	return n_baseURL;
            }
            else{
            	var paramsEnd = queryString.length;
            	var fixSimbol = n_baseURL.indexOf ( "?" );
            	if ( fixSimbol != -1 ) {
            		paramsStart+=1;
            	}
            	var newQueryString = n_baseURL + queryString.substring ( paramsStart, paramsEnd );
            	return unescape(newQueryString);
            }
        }

        return unescape(n_baseURL);
    }

    function getParameters ( queryString ) {
        // Add "=" to the parameter name (i.e. parameterName=value)
        if ( queryString.length > 0 ) {
            // Find the beginning of the string
            var begin = queryString.indexOf ( "?" );
            // If the parameter name is not found, skip it, otherwise return the value
            if ( begin != -1 ) {
                begin+=1;
                var end = queryString.length;
                // Return the string
                return unescape ( queryString.substring ( begin, end ) );
            }
            else{
                begin = queryString.indexOf ( "=" );
                if ( begin != -1 )
                    return queryString;
            }
        }
        // Return "null" if no parameter has been found
        return "null";
    }

    function getParamFromURL ( parameterName ) {

        //First we acces through DOM to the frameset
        var framesetQuery = "";
//        if ( window.top.location.search != 0 ) {
        if ( window.location.search != 0 ) {
//            framesetQuery = window.top.location.search;
            framesetQuery = window.location.search;
        }
        else {
            framesetQuery = "?OpenFrameset";
        }

        //Then call the function to retrieve a param from a frameset
        return getParameter(framesetQuery, parameterName);

    }

    function getParameter ( queryString, parameterName ) {
        // Add "=" to the parameter name (i.e. parameterName=value)
        var parameterName = parameterName + "=";
        if ( queryString.length > 0 ) {
            // Find the beginning of the string
            begin = queryString.indexOf ( parameterName );
            // If the parameter name is not found, skip it, otherwise return the value
            if ( begin != -1 ) {
                // Add the length (integer) to the beginning
                begin += parameterName.length;
                // Multiple parameters are separated by the "&" sign
                end = queryString.indexOf ( "&" , begin );
                if ( end == -1 ) {
                    end = queryString.length;
                }
                // Return the string
                return unescape ( queryString.substring ( begin, end ) );
            }
            // Return "null" if no parameter has been found
            return "null";
        }
    }
    
    function addParameter ( queryString, parameterName, parameterValue ) {
    
	    var begin = queryString.indexOf ( "?" );
	    if ( begin < 0 ) {
	    	queryString+="?";
	    }
	    else{
	    	//Miramos el final del string, si no es "?" ni "&" agregamos & para concatenar
	    	var lastChar = queryString[queryString.length -1];
	    	if(lastChar!='?' && lastChar!='&'){
	    		queryString+="&";
	    	}
	    }
	    queryString+=parameterName+"="+parameterValue;
	    
	    return queryString;
    }
    
    function setParameter(queryString, paramName, paramValue, addIfNoExists){
    	if(addIfNoExists == null){
    		addIfNoExists = true;
    	}
    	
        // Add "=" to the parameter name (i.e. parameterName=value)
        var parameterName = paramName + "=";
        if ( queryString.length > 0 ) {
            // Find the beginning of the string
            var part1End = queryString.indexOf ( parameterName );
            // If the parameter name is not found, skip it, otherwise return the value
            if ( part1End != -1 ) {
                // Add the length (integer) to the beginning
            	part1End += parameterName.length;
                // Multiple parameters are separated by the "&" sign
                part2Start = queryString.indexOf ( "&" , part1End );
                if ( part2Start == -1 ) {
                	part2Start = queryString.length;
                }
                part1Start=0;
                part2End=queryString.length;
                
                var newqueryString = queryString.substring ( part1Start, part1End );
                newqueryString += paramValue;
                newqueryString += queryString.substring ( part2Start, part2End );
                // Return the string
                return unescape ( newqueryString );
            }
        }
        // Return "null" if no parameter has been found
        if(addIfNoExists){ 
        	queryString = addParameter(queryString, paramName, paramValue);
        }
        
        return unescape(queryString);
    }
    
    function deleteParam(queryString, paramName){
        // Add "=" to the parameter name (i.e. parameterName=value)
        var parameterName = paramName + "=";
        if ( queryString.length > 0 ) {
            // Find the beginning of the string
            var part1End = queryString.indexOf ( parameterName );
            // If the parameter name is not found, skip it, otherwise return the value
            if ( part1End != -1 ) {
                // Multiple parameters are separated by the "&" sign
                part2Start = queryString.indexOf ( "&" , part1End );
                //We go to the character after &
                
                if ( part2Start == -1 ) {
                	part2Start = queryString.length;
                	if(queryString[part1End -1] == "&")
                		part1End-=1;
                }
                else{
                	part2Start+=1;
                }
                part1Start=0;
                part2End=queryString.length;
                
                var newqueryString = queryString.substring ( part1Start, part1End );
                newqueryString += queryString.substring ( part2Start, part2End );
                return unescape ( newqueryString );
            }
            
        }
        
        return unescape(queryString);        
    }
    
    //Copia parametros de un qStringSourve a otro QStringDestino, ejemplo: 
	//var qsDest = "pathDestino?dParam1=dVal1&dtParam2=dVal2&supreParam=na";
	//var qsSource = "pathFuente?param1=val1&param2=val2&supreParam=supreVal";
	//var params="param1,supreParam";
	//qsDest = cx.copyParams(qsDest, qsSource, params);
    function copyParams (qsDest, qsSource, params, separator){
    	if(!separator)
    		separator = ",";
    	    	
    	var paramStart=0;
    	var length = params.length;
    	if(length > 0){
	    	while(true){
	    		var paramEnd = params.indexOf ( separator , paramStart );
	    		if(paramEnd == -1){
	    			if(paramStart < length){
	    				paramEnd = length;
	    			}
	    			else{
	    				return unescape ( qsDest );
	    			}
	    		}	    			
	    		var param = params.substring ( paramStart, paramEnd );
	    		var value = getParameter(qsSource, param);
	    		qsDest = setParameter(qsDest, param, value, true);	
	  		
	    		paramStart = paramEnd + 1;
	    	}
    	}
    }

    function GetXmlHttpObject() {
        if (window.XMLHttpRequest) {
            // code for IE7+, Firefox, Chrome, Opera, Safari
            return new XMLHttpRequest();
        }
        else if (window.ActiveXObject) {
            // code for IE6, IE5
            return new ActiveXObject("Microsoft.XMLHTTP");
        }
        else return null;
    }
    
    //filename = path of the datafile
    //typevalues: ["xml", "text", "json"]
    function loadData(filename, type)
    {
    	var xmlhttp = null;
    	if (window.XMLHttpRequest)
    	{// code for IE7+, Firefox, Chrome, Opera, Safari
    	xmlhttp=new XMLHttpRequest();
    	}
    	else
    	{// code for IE6, IE5
    	xmlhttp=new ActiveXObject("Microsoft.XMLHTTP");
    	}
    	xmlhttp.open("GET",filename,false);
    	xmlhttp.send(null);
    	if(type == "xml"){
    		return xmlhttp.responseXML;
    	}
    	else if(type == "json"){
    		var text = xmlhttp.responseText;
    		//Delete the ";" character at the end of the line if exists
    		var lastChar = text[text.length -1];
	    	if(lastChar ==';'){
	    		text[text.length -1] = '';
	    	}
	    	var json = null;
	    	try{
	    		json = eval('(' + text + ')' );
	    	}
	    	catch(e){
	    		json = eval( text );
	    	}
    		
	    	return json;
    	}
    	else return xmlhttp.responseText;
    }



    function xmlString2Doc(xmlData) {
        var xmlDoc = null;

        if (window.ActiveXObject) {
            // for IE
            xmlDoc = new ActiveXObject("Microsoft.XMLDOM");
            xmlDoc.async = "false";
            xmlDoc.loadXML(xmlData);

        } else if (window.document.implementation
            && window.document.implementation.createDocument) {
            // for Mozila
            var parser = new DOMParser();
            xmlDoc = parser.parseFromString(xmlData, "text/xml");

        }
        return xmlDoc;
    }

    function Doc2xmlString(xmlObject) {
        var xmlString = null;

        if (window.ActiveXObject) {
            // for IE
            xmlString = xmlObject.xml;
        } else {
            // For Firefox
            xmlString = (new XMLSerializer()).serializeToString(xmlObject);
        }

        return xmlString;
    }

    function loadScript(url, callback, scriptCharset){

        var head = window.document.getElementsByTagName("head")[0] || window.document.documentElement;
        var script = window.document.createElement("script");
        script.src = url;
        if ( scriptCharset ) {
            script.charset = scriptCharset;
        }

        // Handle Script loading
        var done = false;
        // Attach handlers for all browsers
        script.onload = script.onreadystatechange = function() {
            if ( !done && (!this.readyState ||
                this.readyState === "loaded" || this.readyState === "complete") ) {
                done = true;

                if( typeof callback == "function") callback();

                // Handle memory leak in IE
                script.onload = script.onreadystatechange = null;
                if ( head && script.parentNode ) {
                    head.removeChild( script );
                }
            }
        };
    
        // Use insertBefore instead of appendChild  to circumvent an IE6 bug.
        // This arises when a base node is used (#2709 and #4378).
        head.insertBefore( script, head.firstChild );
        // We handle everything using the script element injection

        return undefined;
    }

    function makeIFrame(src, node, p_width, p_height) {
        var ifrm = window.document.createElement("IFRAME");
        ifrm.setAttribute("src", src);
        ifrm.setAttribute("name", "ifrm"+node);
        ifrm.setAttribute("id", "ifrm"+node);
        ifrm.style.width = p_width+"px";
        ifrm.style.height = p_height+"px";

        //Add Iframe in the node
        var nodeElement = window.document.getElementById(node);
        if(nodeElement) nodeElement.appendChild(ifrm);
        else window.document.body.appendChild(ifrm);

        return ifrm;
    }
    ///// END UTILS///

    ///////////////////////***********ANONYMOUS FUNCTION TO HIDE CODE***********////////////////////////
    //(function(window){
    

    /////TASK OBJECT ///
    function Task(p_taskId, p_sessionId, p_type, p_tittle, p_submittedDate, p_url, p_timestamp) {
        // public Attributes:

        this.id = p_taskId;
        this.sessionId = p_sessionId;
        this.type = p_type;
        this.tittle = p_tittle;
        this.submittedDate = p_submittedDate;
        this.url = p_url;
        this.timestamp=p_timestamp;

        this.onreadystatechange=null;

        // private attributes
        this._xmlhttp = new CrossHttpRequest();//new CrossXHR();//GetXmlHttpObject();

        this.getInputTask = function(p_userCallback) {

            //store the callback func
            this.onreadystatechange=p_userCallback;

            var endpoint = "http://localhost:8080/evapi/WLMServlet";
            endpoint += "?timestamp=" + new Date().getTime() + "&action=getInput&taskId="+ this.id + "&sessionId=" + this.sessionId;

            var scope = this;
            this._xmlhttp.onreadystatechange = function() {
                //scope.completeCallBack();
                scope.callback.call(scope); //.call se utiliza para recuperar el scope aunque sin call parece que funciona también
            }

            this._xmlhttp.open("GET", endpoint, true);
            this._xmlhttp.send(null);
        }

        this.getOutputTask = function(p_userCallback) {

            //store the callback func
            this.onreadystatechange=p_userCallback;

            var endpoint = "http://localhost:8080/evapi/WLMServlet";
            endpoint += "?timestamp=" + new Date().getTime() + "&action=getOutput&taskId="+ this.id + "&sessionId=" + this.sessionId;

            var scope = this;
            this._xmlhttp.onreadystatechange = function() {
                //scope.completeCallBack();
                scope.callback.call(scope); //.call se utiliza para recuperar el scope aunque sin call parece que funciona también
            }

            this._xmlhttp.open("GET", endpoint, true);
            this._xmlhttp.send(null);

        }

        this.complete = function(p_userCallback){

            //store the callback func
            this.onreadystatechange=p_userCallback;

            var endpoint = "http://localhost:8080/evapi/WLMServlet";
            endpoint += "?timestamp=" + new Date().getTime() + "&action=complete&taskId="+ this.id + "&sessionId=" + this.sessionId;

            var scope = this;
            this._xmlhttp.onreadystatechange = function() {
                //scope.completeCallBack();
                scope.callback.call(scope); //.call se utiliza para recuperar el scope aunque sin call parece que funciona también
            }

            this._xmlhttp.open("GET", endpoint, true);
            this._xmlhttp.send(null);

        }
        this.callback = function(){
            if(this.onreadystatechange)
                this.onreadystatechange(this._xmlhttp.responseText);
        }
    }
    /////END TASK OBJECT ///

    /////EVCLIENT OBJECT///
    function EvClient(p_user) {
        // Attributes:
        this._user= (typeof p_user == "string") ? {
            sessionId : p_user
        } : p_user;
        this._timestamp = 0;
        this._tasks = null;
        this._taskListeners = null;
        this._xmlhttp = new CrossHttpRequest();//new CrossXHR();//GetXmlHttpObject();

        this.start = function() {
            this.getTaskList();
        }

        this.getTaskList = function() {
            var endpoint = "http://localhost:8080/evapi/ControllerServlet";
            endpoint += "?timestamp=" + this._timestamp + "&action="
            + "processNextTask" + "&sessionId=" + this._user.sessionId;

            var scope = this;
            this._xmlhttp.onreadystatechange = function() {
                //scope.getMyTasksCallBack();
                scope.getMyTasksCallBack.call(scope); //.call se utiliza para recuperar el scope aunque sin call parece que funciona también
            }

            this._xmlhttp.open("GET", endpoint, true);
            this._xmlhttp.send(null);
        }

        this.addTaskListener = function(p_listener) {
            if (this._taskListeners == null) {
                this._taskListeners = new Array();
            }
            this._taskListeners.push(p_listener);
        }

        this.fireTaskEvent = function(p_event) {
            //Tell user listeners that a new event has appeared
            var preventPromptLive=false;
        
            if (this._taskListeners != null) {
                for ( var i = 0; i < this._taskListeners.length; i++) {
                    var res=this._taskListeners[i](p_event);
                    preventPromptLive = ( res == false) ? true : preventPromptLive;
                }
            }
            //If it's a simple task we need to show the XForm in a iframe
            if(!this._user.preventPrompt && !preventPromptLive){
                var aiframe=window.document.getElementById("ifrm"+this._user.node);
                if(!aiframe){
                    aiframe = makeIFrame('about:blank', this._user.node, 1280, 1000);
                }
                if(p_event.type == "simpleTask"){
                    aiframe.src="http://localhost:8080/evapi/xforms-jsp/WLMXForms?action=getXForm&taskId="+p_event.id+"&sessionId="+p_event.sessionId;
                }
                else{
                    var url = p_event.url;
                   
                    var begin = url.indexOf ( "?" );
                    if ( begin < 0 ) {
                        url+="?";
                    }
                    else{
                        url+="&";
                    }
                    url+="sessionId="+p_event.sessionId;
                    aiframe.src=url;
                }
            }// End PreventPrompt
        }

        this.getMyTasksCallBack = function() {
            if (this._xmlhttp.readyState == 4) {
                if (this._xmlhttp.status == 200) {
                    window.document.getElementById(this._user.sessionId + "Output").innerHTML += ".";
                    var xmlDoc = this._xmlhttp.responseXML.documentElement;

                    var status = xmlDoc.getElementsByTagName("status")[0].childNodes[0].nodeValue;
                    if (status != null && status == "valid") {

                        // Service values
                        var timestamp = xmlDoc.getElementsByTagName("timestamp")[0].childNodes[0].nodeValue;
                        if (timestamp) this._timestamp = timestamp;

                        // Task Attributes
                        var id = xmlDoc.getElementsByTagName("id")[0].childNodes[0].nodeValue;
                        var sessionId = xmlDoc.getElementsByTagName("sessionId")[0].childNodes[0].nodeValue;
                        var type = xmlDoc.getElementsByTagName("type")[0].childNodes[0].nodeValue;
                        var tittle = xmlDoc.getElementsByTagName("tittle")[0].childNodes[0].nodeValue;
                        var submittedDate = xmlDoc.getElementsByTagName("submittedDate")[0].childNodes[0].nodeValue;
                        var url = xmlDoc.getElementsByTagName("url")[0].childNodes[0].nodeValue;

                        var event = new Task(id, sessionId, type, tittle, submittedDate, url, timestamp);
                        this.fireTaskEvent(event);
                    }

                } //End if

                var timer = 1000;
                var scope = this;
                setTimeout ( function () {
                    scope.getTaskList.call( scope );
                }, timer );
            //setTimeout(function() {
            //scope.getTaskList();
            //}, timer);
            }//End if
        }
    }
    /////FIN EVCLIENT OBJECT///

    /////CROSS AJAX THROUGH SCRIPT HACK//
    RegExp.escape = function(str)
    {
        var specials = new RegExp("[.*+?|()\\[\\]{}\\\\\\/]", "g"); // .*+?|()[]{}\/
        return str.replace(specials, "\\$&");
    }

    // Got this from http://www.sitepoint.com/blogs/2009/08/19/javascript-json-serialization/
    // implement JSON.stringify serialization
    if(typeof JSON == "undefined") {
        JSON = {};
    }

    JSON.stringify = JSON.stringify || function (obj) {
        var t = typeof (obj);
        if (t != "object" || obj === null) {

            // simple data type
            if (t == "string") obj = '"'+obj+'"';
            return String(obj);
        }
        else {
            // recurse array or object
            var n, v, json = [], arr = (obj && obj.constructor == Array);
            for (n in obj) {
                v = obj[n];
                t = typeof(v);
                if (t == "string") v = '"'+v+'"';
                else if (t == "object" && v !== null) v = JSON.stringify(v);
                json.push((arr ? "" : '"' + n + '":') + String(v));
            }
            return (arr ? "[" : "{") + String(json) + (arr ? "]" : "}");
        }
    };

    // implement JSON.parse de-serialization
    JSON.parse = JSON.parse || function (str) {
        if (str === "") str = '""';
        eval("var p=" + str + ";");
        return p;
    };

    //Array de Scopes para almacenar las instancias de los CrossHttpRequests
    var CrossHttpCount = -1;
    var CrossHttpScopeArray = new Array();

    /**
 *Recovers the scope
 */
    function CrossHttpRequestPaddingCallBack(obj, index){
        //Retrieve the scope and call the pre-processing callback func
        var scope=CrossHttpScopeArray[index];
        //scope.callback(response);
        scope.callback.call(scope, obj);
    }

    function CrossHttpRequest(){
        if(CrossHttpCount > 99) CrossHttpCount=0; //Reset count if it is more than 99 instances (TODO: improve it with pooling)

        //public attributes
        this.readyState=0;
        this.status=0;
        this.output="json";
        this.onreadystatechange=null;
        this.responseJson=null;     // The Returned Json object
        this.responseText=null;     // Emulates the httprequest.responseText with the Stringified version of the Json Object or the Javastring
        this.responseXML=null;      // Emulates the httprequest.responseXML

        //private attributes
        this._index = ++CrossHttpCount;
        this._method ='GET';
        this._url = "";
        this._params = null;

        //Store our scope into the Array
        CrossHttpScopeArray[this._index]=this;
    
        this.open = function(method, url) {
            this._method=method;
            this._url=url;
        }
    
        this.send = function(params) {
            //Reset status
            this.status=0;
            this.readyState=0;

            //Insert a script (also used for jsonp)
            this._params=params;
    
            var head = window.document.getElementsByTagName("head")[0] || window.document.documentElement;
            var script = window.document.createElement("script");
            script.id=this._url + this._index;

            //add Callback variable to url
            var urlJsonP=this._url;

            var begin = urlJsonP.indexOf ( "?" );
            if ( begin < 0 ) {
                urlJsonP+="?";
            }
            else{
                urlJsonP+="&";
            }
            urlJsonP+="output="+this.output+"&callback=CrossHttpRequestPaddingCallBack&callbackId=" + this._index;

            script.src = urlJsonP;
            // Use insertBefore instead of appendChild  to circumvent an IE6 bug.
            // This arises when a base node is used (#2709 and #4378).
            head.insertBefore( script, head.firstChild );
            //Now it starts the execution of the Javascript code...
        }

        this.callback = function(obj) {
            // Remove the script from the document
            var script = window.document.getElementById(this._url + this._index);
            try {
                script.parentNode.removeChild(script);
            }
            catch(err) {
            // Just ignore any problems removing the script, because it's already gone
            }

            //Do processing of the returned data
            var t = typeof (obj);
            if (t != "object" || obj === null) {
                // simple data type
                if (t == "string") {
                    this.responseText=obj;
                    this.responseXML= xmlString2Doc(obj);
                }
            }
            else{
                //JSON data type
                this.responseText=JSON.stringify(obj);
                this.responseJson=obj;
            }
        
            //Setup status
            this.status=200;
            this.readyState=4;
            //Execute callback
            if(this.onreadystatechange) this.onreadystatechange(obj);

        }
    }
    ///////END CROSS AJAX THROUGH SCRIPT HACK//////

    //Expose objects to global object
    //Ev Client
    window.EvClient=EvClient;

    //my ajax httprequest
    window.CrossHttpRequest = CrossHttpRequest;
    window.CrossHttpRequestPaddingCallBack = CrossHttpRequestPaddingCallBack;

    //cx library
    window.cx = cx;

    //})(window)

    ///////////////WIZARD////////////////
     function Wizard(n_node){
    	
    	//opcional values
    	this.lastStepClass = "stepSelected";
    	this.stepClass = "step";
    	 
    	this.node=n_node; //Node where steps are going to be added
    	this.stepsMap = []; //Associative Array of WizardSteps
    	this.lastStep=null;
    	this.firstStep = null;
    	
    	this.deleteInnerSteps = function(n_wStep){
    		var aStep = n_wStep.next;
    		while(true){
    			if(aStep){
    				if(aStep.node){
    					//Delete HTML content
    					aStep.node.parentNode.removeChild(aStep.node);
    					
    					//Delete wStep from Structure
    					if(aStep.parentStep){
    						aStep.parentStep.next = null;
    					}
    					//this.stepsMap[aStep.id] = null;
    					delete this.stepsMap[aStep.id];
    				}
    				aStep = aStep.next;
    			}
    			else{
    				break;
    			}				
    		}
    	}
    	
    	this.setStep = function (n_wStep, n_parentWStep, recAdding) {
    		//recAdding = true
    		
    		//The parentStep will be the lastStep if no parentStep is specified
    		n_parentWStep = (n_parentWStep) ? n_parentWStep : this.lastStep;
    		
    		//Set the new lastStep
    		this.lastStep = n_wStep;    			
    		
    		n_wStep.parentStep = n_parentWStep;
    		if(n_parentWStep){
    			this.deleteInnerSteps(n_parentWStep); //Eliminamos los Steps descendientes del nodo padre
    			//Add Step in the parent node of the Structure
    			n_parentWStep.next = n_wStep;
    		}
    		else{
    			//If no exists a parent step then there's no Steps yet, so this will be the first step 
    			this.firstStep = n_wStep;
    		}
    		
    		//Add the step to the main array Structure
    		this.stepsMap[n_wStep.id] = n_wStep;
    		
    		//Add the HTML content
    		if(recAdding && n_parentWStep){ //If recursive adding, added inside the parent html node
   				if(n_parentWStep.node){
   					n_parentWStep.node.appendChild(n_wStep.node);
    			}
    		}
    		else if(this.node){//If not recursive adding, added inside the main wizard html node
    			this.node.appendChild(n_wStep.node);
    		}
    		
    		//Event on step added
    		this.onStepAdded();
    	}
    	
    	this.getStep= function(stepId){
    		return this.stepsMap[stepId];
    	}
    	
    	this.onStepAdded = function(){
    		for (i in this.stepsMap) {
    			if(i == this.lastStep.id){
    				this.stepsMap[i].node.className = this.lastStepClass;
    			}
    			else{
    				this.stepsMap[i].node.className = this.stepClass;
    			}
    		}
    	}
    }
    
   function WizardStep(n_id){
    	this.id= (n_id) ? n_id : null;
    	// Creamos el nuevo div
    	this.node = document.createElement('div');
    	this.node.className = "step"; 	//newdiv.setAttribute('class', 'step');
   		this.next = null; //Logical Structure
   		this.parentStep = null; //Logical Structure
   		
   		this.getStepNumber = function(){
   			var number = 0;
   			var parent=this.parentStep;
   			while(parent){
   				number++;
   				parent = parent.parentStep;
   			}
   			return number;
   		}
   	}

   //////////////END WIZARD////////////////
   
   ////////////HASHTABLE//////////////////   
/**
 * jshashtable
 *
 * jshashtable is a JavaScript implementation of a hash table. It creates a single constructor function called Hashtable
 * in the global scope.
 *
 * Author: Tim Down <tim@timdown.co.uk>
 * Version: 2.1
 * Build date: 21 March 2010
 * Website: http://www.timdown.co.uk/jshashtable
 */

   (function(cx) {
	var FUNCTION = "function";

	var arrayRemoveAt = (typeof Array.prototype.splice == FUNCTION) ?
		function(arr, idx) {
			arr.splice(idx, 1);
		} :

		function(arr, idx) {
			var itemsAfterDeleted, i, len;
			if (idx === arr.length - 1) {
				arr.length = idx;
			} else {
				itemsAfterDeleted = arr.slice(idx + 1);
				arr.length = idx;
				for (i = 0, len = itemsAfterDeleted.length; i < len; ++i) {
					arr[idx + i] = itemsAfterDeleted[i];
				}
			}
		};

	function hashObject(obj) {
		var hashCode;
		if (typeof obj == "string") {
			return obj;
		} else if (typeof obj.hashCode == FUNCTION) {
			// Check the hashCode method really has returned a string
			hashCode = obj.hashCode();
			return (typeof hashCode == "string") ? hashCode : hashObject(hashCode);
		} else if (typeof obj.toString == FUNCTION) {
			return obj.toString();
		} else {
			try {
				return String(obj);
			} catch (ex) {
				// For host objects (such as ActiveObjects in IE) that have no toString() method and throw an error when
				// passed to String()
				return Object.prototype.toString.call(obj);
			}
		}
	}

	function equals_fixedValueHasEquals(fixedValue, variableValue) {
		return fixedValue.equals(variableValue);
	}

	function equals_fixedValueNoEquals(fixedValue, variableValue) {
		return (typeof variableValue.equals == FUNCTION) ?
			   variableValue.equals(fixedValue) : (fixedValue === variableValue);
	}

	function createKeyValCheck(kvStr) {
		return function(kv) {
			if (kv === null) {
				throw new Error("null is not a valid " + kvStr);
			} else if (typeof kv == "undefined") {
				throw new Error(kvStr + " must not be undefined");
			}
		};
	}

	var checkKey = createKeyValCheck("key"), checkValue = createKeyValCheck("value");

	/*----------------------------------------------------------------------------------------------------------------*/

	function Bucket(hash, firstKey, firstValue, equalityFunction) {
        this[0] = hash;
		this.entries = [];
		this.addEntry(firstKey, firstValue);

		if (equalityFunction !== null) {
			this.getEqualityFunction = function() {
				return equalityFunction;
			};
		}
	}

	var EXISTENCE = 0, ENTRY = 1, ENTRY_INDEX_AND_VALUE = 2;

	function createBucketSearcher(mode) {
		return function(key) {
			var i = this.entries.length, entry, equals = this.getEqualityFunction(key);
			while (i--) {
				entry = this.entries[i];
				if ( equals(key, entry[0]) ) {
					switch (mode) {
						case EXISTENCE:
							return true;
						case ENTRY:
							return entry;
						case ENTRY_INDEX_AND_VALUE:
							return [ i, entry[1] ];
					}
				}
			}
			return false;
		};
	}

	function createBucketLister(entryProperty) {
		return function(aggregatedArr) {
			var startIndex = aggregatedArr.length;
			for (var i = 0, len = this.entries.length; i < len; ++i) {
				aggregatedArr[startIndex + i] = this.entries[i][entryProperty];
			}
		};
	}

	Bucket.prototype = {
		getEqualityFunction: function(searchValue) {
			return (typeof searchValue.equals == FUNCTION) ? equals_fixedValueHasEquals : equals_fixedValueNoEquals;
		},

		getEntryForKey: createBucketSearcher(ENTRY),

		getEntryAndIndexForKey: createBucketSearcher(ENTRY_INDEX_AND_VALUE),

		removeEntryForKey: function(key) {
			var result = this.getEntryAndIndexForKey(key);
			if (result) {
				arrayRemoveAt(this.entries, result[0]);
				return result[1];
			}
			return null;
		},

		addEntry: function(key, value) {
			this.entries[this.entries.length] = [key, value];
		},

		keys: createBucketLister(0),

		values: createBucketLister(1),

		getEntries: function(entries) {
			var startIndex = entries.length;
			for (var i = 0, len = this.entries.length; i < len; ++i) {
				// Clone the entry stored in the bucket before adding to array
				entries[startIndex + i] = this.entries[i].slice(0);
			}
		},

		containsKey: createBucketSearcher(EXISTENCE),

		containsValue: function(value) {
			var i = this.entries.length;
			while (i--) {
				if ( value === this.entries[i][1] ) {
					return true;
				}
			}
			return false;
		}
	};

	/*----------------------------------------------------------------------------------------------------------------*/

	// Supporting functions for searching hashtable buckets

	function searchBuckets(buckets, hash) {
		var i = buckets.length, bucket;
		while (i--) {
			bucket = buckets[i];
			if (hash === bucket[0]) {
				return i;
			}
		}
		return null;
	}

	function getBucketForHash(bucketsByHash, hash) {
		var bucket = bucketsByHash[hash];

		// Check that this is a genuine bucket and not something inherited from the bucketsByHash's prototype
		return ( bucket && (bucket instanceof Bucket) ) ? bucket : null;
	}

	/*----------------------------------------------------------------------------------------------------------------*/

	function Hashtable(hashingFunctionParam, equalityFunctionParam) {
		var that = this;
		var buckets = [];
		var bucketsByHash = {};

		var hashingFunction = (typeof hashingFunctionParam == FUNCTION) ? hashingFunctionParam : hashObject;
		var equalityFunction = (typeof equalityFunctionParam == FUNCTION) ? equalityFunctionParam : null;

		this.put = function(key, value) {
			checkKey(key);
			checkValue(value);
			var hash = hashingFunction(key), bucket, bucketEntry, oldValue = null;

			// Check if a bucket exists for the bucket key
			bucket = getBucketForHash(bucketsByHash, hash);
			if (bucket) {
				// Check this bucket to see if it already contains this key
				bucketEntry = bucket.getEntryForKey(key);
				if (bucketEntry) {
					// This bucket entry is the current mapping of key to value, so replace old value and we're done.
					oldValue = bucketEntry[1];
					bucketEntry[1] = value;
				} else {
					// The bucket does not contain an entry for this key, so add one
					bucket.addEntry(key, value);
				}
			} else {
				// No bucket exists for the key, so create one and put our key/value mapping in
				bucket = new Bucket(hash, key, value, equalityFunction);
				buckets[buckets.length] = bucket;
				bucketsByHash[hash] = bucket;
			}
			return oldValue;
		};

		this.get = function(key) {
			checkKey(key);

			var hash = hashingFunction(key);

			// Check if a bucket exists for the bucket key
			var bucket = getBucketForHash(bucketsByHash, hash);
			if (bucket) {
				// Check this bucket to see if it contains this key
				var bucketEntry = bucket.getEntryForKey(key);
				if (bucketEntry) {
					// This bucket entry is the current mapping of key to value, so return the value.
					return bucketEntry[1];
				}
			}
			return null;
		};

		this.containsKey = function(key) {
			checkKey(key);
			var bucketKey = hashingFunction(key);

			// Check if a bucket exists for the bucket key
			var bucket = getBucketForHash(bucketsByHash, bucketKey);

			return bucket ? bucket.containsKey(key) : false;
		};

		this.containsValue = function(value) {
			checkValue(value);
			var i = buckets.length;
			while (i--) {
				if (buckets[i].containsValue(value)) {
					return true;
				}
			}
			return false;
		};

		this.clear = function() {
			buckets.length = 0;
			bucketsByHash = {};
		};

		this.isEmpty = function() {
			return !buckets.length;
		};

		var createBucketAggregator = function(bucketFuncName) {
			return function() {
				var aggregated = [], i = buckets.length;
				while (i--) {
					buckets[i][bucketFuncName](aggregated);
				}
				return aggregated;
			};
		};

		this.keys = createBucketAggregator("keys");
		this.values = createBucketAggregator("values");
		this.entries = createBucketAggregator("getEntries");

		this.remove = function(key) {
			checkKey(key);

			var hash = hashingFunction(key), bucketIndex, oldValue = null;

			// Check if a bucket exists for the bucket key
			var bucket = getBucketForHash(bucketsByHash, hash);

			if (bucket) {
				// Remove entry from this bucket for this key
				oldValue = bucket.removeEntryForKey(key);
				if (oldValue !== null) {
					// Entry was removed, so check if bucket is empty
					if (!bucket.entries.length) {
						// Bucket is empty, so remove it from the bucket collections
						bucketIndex = searchBuckets(buckets, hash);
						arrayRemoveAt(buckets, bucketIndex);
						delete bucketsByHash[hash];
					}
				}
			}
			return oldValue;
		};

		this.size = function() {
			var total = 0, i = buckets.length;
			while (i--) {
				total += buckets[i].entries.length;
			}
			return total;
		};

		this.each = function(callback) {
			var entries = that.entries(), i = entries.length, entry;
			while (i--) {
				entry = entries[i];
				callback(entry[0], entry[1]);
			}
		};

		this.putAll = function(hashtable, conflictCallback) {
			var entries = hashtable.entries();
			var entry, key, value, thisValue, i = entries.length;
			var hasConflictCallback = (typeof conflictCallback == FUNCTION);
			while (i--) {
				entry = entries[i];
				key = entry[0];
				value = entry[1];

				// Check for a conflict. The default behaviour is to overwrite the value for an existing key
				if ( hasConflictCallback && (thisValue = that.get(key)) ) {
					value = conflictCallback(key, thisValue, value);
				}
				that.put(key, value);
			}
		};

		this.clone = function() {
			var clone = new Hashtable(hashingFunctionParam, equalityFunctionParam);
			clone.putAll(that);
			return clone;
		};
	}

	//Put the cx Object to the global cx api object
	cx.Hashtable = Hashtable;
 })(cx);
////////////END JSHASHTABLE////////////////////////////////

})(window)
/////////////END CXAPI//////////////


















////////////XHR CROSS AJAX THOUGH FLASH////////////
    var FlashHttpRequest_ready;
    var FlashHttpRequest_objects;
    var FlashHttpRequest_counter;
    if (typeof(FlashHttpRequest_counter) == 'undefined'){
        /*	SWFObject v2.0 <http://code.google.com/p/swfobject/>
	Copyright (c) 2007 Geoff Stearns, Michael Williams, and Bobby van der Sluis
	This software is released under the MIT License <http://www.opensource.org/licenses/mit-license.php>
*/
        var swfobject=function(){
            var Z="undefined",P="object",B="Shockwave Flash",h="ShockwaveFlash.ShockwaveFlash",W="application/x-shockwave-flash",K="SWFObjectExprInst",G=window,g=document,N=navigator,f=[],H=[],Q=null,L=null,T=null,S=false,C=false;
            var a=function(){
                var l=typeof g.getElementById!=Z&&typeof g.getElementsByTagName!=Z&&typeof g.createElement!=Z&&typeof g.appendChild!=Z&&typeof g.replaceChild!=Z&&typeof g.removeChild!=Z&&typeof g.cloneNode!=Z,t=[0,0,0],n=null;
                if(typeof N.plugins!=Z&&typeof N.plugins[B]==P){
                    n=N.plugins[B].description;
                    if(n){
                        n=n.replace(/^.*\s+(\S+\s+\S+$)/,"$1");
                        t[0]=parseInt(n.replace(/^(.*)\..*$/,"$1"),10);
                        t[1]=parseInt(n.replace(/^.*\.(.*)\s.*$/,"$1"),10);
                        t[2]=/r/.test(n)?parseInt(n.replace(/^.*r(.*)$/,"$1"),10):0
                    }
                }else{
                    if(typeof G.ActiveXObject!=Z){
                        var o=null,s=false;
                        try{
                            o=new ActiveXObject(h+".7")
                        }catch(k){
                            try{
                                o=new ActiveXObject(h+".6");
                                t=[6,0,21];
                                o.AllowScriptAccess="always"
                            }catch(k){
                                if(t[0]==6){
                                    s=true
                                }
                            }
                            if(!s){
                                try{
                                    o=new ActiveXObject(h)
                                }catch(k){}
                            }
                        }
                        if(!s&&o){
                            try{
                                n=o.GetVariable("$version");
                                if(n){
                                    n=n.split(" ")[1].split(",");
                                    t=[parseInt(n[0],10),parseInt(n[1],10),parseInt(n[2],10)]
                                }
                            }catch(k){}
                        }
                    }
                }
                var v=N.userAgent.toLowerCase(),j=N.platform.toLowerCase(),r=/webkit/.test(v)?parseFloat(v.replace(/^.*webkit\/(\d+(\.\d+)?).*$/,"$1")):false,i=false,q=j?/win/.test(j):/win/.test(v),m=j?/mac/.test(j):/mac/.test(v);/*@cc_on i=true;@if(@_win32)q=true;@elif(@_mac)m=true;@end@*/
                return{
                    w3cdom:l,
                    pv:t,
                    webkit:r,
                    ie:i,
                    win:q,
                    mac:m
                }
            }();
            var e=function(){
                if(!a.w3cdom){
                    return
                }
                J(I);
                if(a.ie&&a.win){
                    try{
                        g.write("<script id=__ie_ondomload defer=true src=//:><\/script>");
                        var i=c("__ie_ondomload");
                        if(i){
                            i.onreadystatechange=function(){
                                if(this.readyState=="complete"){
                                    this.parentNode.removeChild(this);
                                    V()
                                }
                            }
                        }
                    }catch(j){}
                }
                if(a.webkit&&typeof g.readyState!=Z){
                    Q=setInterval(function(){
                        if(/loaded|complete/.test(g.readyState)){
                            V()
                        }
                    },10)
                }
                if(typeof g.addEventListener!=Z){
                    g.addEventListener("DOMContentLoaded",V,null)
                }
                M(V)
            }();
            function V(){
                if(S){
                    return
                }
                if(a.ie&&a.win){
                    var m=Y("span");
                    try{
                        var l=g.getElementsByTagName("body")[0].appendChild(m);
                        l.parentNode.removeChild(l)
                    }catch(n){
                        return
                    }
                }
                S=true;
                if(Q){
                    clearInterval(Q);
                    Q=null
                }
                var j=f.length;
                for(var k=0;k<j;k++){
                    f[k]()
                }
            }
            function J(i){
                if(S){
                    i()
                }else{
                    f[f.length]=i
                }
            }
            function M(j){
                if(typeof G.addEventListener!=Z){
                    G.addEventListener("load",j,false)
                }else{
                    if(typeof g.addEventListener!=Z){
                        g.addEventListener("load",j,false)
                    }else{
                        if(typeof G.attachEvent!=Z){
                            G.attachEvent("onload",j)
                        }else{
                            if(typeof G.onload=="function"){
                                var i=G.onload;
                                G.onload=function(){
                                    i();
                                    j()
                                }
                            }else{
                                G.onload=j
                            }
                        }
                    }
                }
            }
            function I(){
                var l=H.length;
                for(var j=0;j<l;j++){
                    var m=H[j].id;
                    if(a.pv[0]>0){
                        var k=c(m);
                        if(k){
                            H[j].width=k.getAttribute("width")?k.getAttribute("width"):"0";
                            H[j].height=k.getAttribute("height")?k.getAttribute("height"):"0";
                            if(O(H[j].swfVersion)){
                                if(a.webkit&&a.webkit<312){
                                    U(k)
                                }
                                X(m,true)
                            }else{
                                if(H[j].expressInstall&&!C&&O("6.0.65")&&(a.win||a.mac)){
                                    D(H[j])
                                }else{
                                    d(k)
                                }
                            }
                        }
                    }else{
                        X(m,true)
                    }
                }
            }
            function U(m){
                var k=m.getElementsByTagName(P)[0];
                if(k){
                    var p=Y("embed"),r=k.attributes;
                    if(r){
                        var o=r.length;
                        for(var n=0;n<o;n++){
                            if(r[n].nodeName.toLowerCase()=="data"){
                                p.setAttribute("src",r[n].nodeValue)
                            }else{
                                p.setAttribute(r[n].nodeName,r[n].nodeValue)
                            }
                        }
                    }
                    var q=k.childNodes;
                    if(q){
                        var s=q.length;
                        for(var l=0;l<s;l++){
                            if(q[l].nodeType==1&&q[l].nodeName.toLowerCase()=="param"){
                                p.setAttribute(q[l].getAttribute("name"),q[l].getAttribute("value"))
                            }
                        }
                    }
                    m.parentNode.replaceChild(p,m)
                }
            }
            function F(i){
                if(a.ie&&a.win&&O("8.0.0")){
                    G.attachEvent("onunload",function(){
                        var k=c(i);
                        if(k){
                            for(var j in k){
                                if(typeof k[j]=="function"){
                                    k[j]=function(){}
                                }
                            }
                            k.parentNode.removeChild(k)
                        }
                    })
                }
            }
            function D(j){
                C=true;
                var o=c(j.id);
                if(o){
                    if(j.altContentId){
                        var l=c(j.altContentId);
                        if(l){
                            L=l;
                            T=j.altContentId
                        }
                    }else{
                        L=b(o)
                    }
                    if(!(/%$/.test(j.width))&&parseInt(j.width,10)<310){
                        j.width="310"
                    }
                    if(!(/%$/.test(j.height))&&parseInt(j.height,10)<137){
                        j.height="137"
                    }
                    g.title=g.title.slice(0,47)+" - Flash Player Installation";
                    var n=a.ie&&a.win?"ActiveX":"PlugIn",k=g.title,m="MMredirectURL="+G.location+"&MMplayerType="+n+"&MMdoctitle="+k,p=j.id;
                    if(a.ie&&a.win&&o.readyState!=4){
                        var i=Y("div");
                        p+="SWFObjectNew";
                        i.setAttribute("id",p);
                        o.parentNode.insertBefore(i,o);
                        o.style.display="none";
                        G.attachEvent("onload",function(){
                            o.parentNode.removeChild(o)
                        })
                    }
                    R({
                        data:j.expressInstall,
                        id:K,
                        width:j.width,
                        height:j.height
                    },{
                        flashvars:m
                    },p)
                }
            }
            function d(j){
                if(a.ie&&a.win&&j.readyState!=4){
                    var i=Y("div");
                    j.parentNode.insertBefore(i,j);
                    i.parentNode.replaceChild(b(j),i);
                    j.style.display="none";
                    G.attachEvent("onload",function(){
                        j.parentNode.removeChild(j)
                    })
                }else{
                    j.parentNode.replaceChild(b(j),j)
                }
            }
            function b(n){
                var m=Y("div");
                if(a.win&&a.ie){
                    m.innerHTML=n.innerHTML
                }else{
                    var k=n.getElementsByTagName(P)[0];
                    if(k){
                        var o=k.childNodes;
                        if(o){
                            var j=o.length;
                            for(var l=0;l<j;l++){
                                if(!(o[l].nodeType==1&&o[l].nodeName.toLowerCase()=="param")&&!(o[l].nodeType==8)){
                                    m.appendChild(o[l].cloneNode(true))
                                }
                            }
                        }
                    }
                }
                return m
            }
            function R(AE,AC,q){
                var p,t=c(q);
                if(typeof AE.id==Z){
                    AE.id=q
                }
                if(a.ie&&a.win){
                    var AD="";
                    for(var z in AE){
                        if(AE[z]!=Object.prototype[z]){
                            if(z=="data"){
                                AC.movie=AE[z]
                            }else{
                                if(z.toLowerCase()=="styleclass"){
                                    AD+=' class="'+AE[z]+'"'
                                }else{
                                    if(z!="classid"){
                                        AD+=" "+z+'="'+AE[z]+'"'
                                    }
                                }
                            }
                        }
                    }
                    var AB="";
                    for(var y in AC){
                        if(AC[y]!=Object.prototype[y]){
                            AB+='<param name="'+y+'" value="'+AC[y]+'" />'
                        }
                    }
                    t.outerHTML='<object classid="clsid:D27CDB6E-AE6D-11cf-96B8-444553540000"'+AD+">"+AB+"</object>";
                    F(AE.id);
                    p=c(AE.id)
                }else{
                    if(a.webkit&&a.webkit<312){
                        var AA=Y("embed");
                        AA.setAttribute("type",W);
                        for(var x in AE){
                            if(AE[x]!=Object.prototype[x]){
                                if(x=="data"){
                                    AA.setAttribute("src",AE[x])
                                }else{
                                    if(x.toLowerCase()=="styleclass"){
                                        AA.setAttribute("class",AE[x])
                                    }else{
                                        if(x!="classid"){
                                            AA.setAttribute(x,AE[x])
                                        }
                                    }
                                }
                            }
                        }
                        for(var w in AC){
                            if(AC[w]!=Object.prototype[w]){
                                if(w!="movie"){
                                    AA.setAttribute(w,AC[w])
                                }
                            }
                        }
                        t.parentNode.replaceChild(AA,t);
                        p=AA
                    }else{
                        var s=Y(P);
                        s.setAttribute("type",W);
                        for(var v in AE){
                            if(AE[v]!=Object.prototype[v]){
                                if(v.toLowerCase()=="styleclass"){
                                    s.setAttribute("class",AE[v])
                                }else{
                                    if(v!="classid"){
                                        s.setAttribute(v,AE[v])
                                    }
                                }
                            }
                        }
                        for(var u in AC){
                            if(AC[u]!=Object.prototype[u]&&u!="movie"){
                                E(s,u,AC[u])
                            }
                        }
                        t.parentNode.replaceChild(s,t);
                        p=s
                    }
                }
                return p
            }
            function E(k,i,j){
                var l=Y("param");
                l.setAttribute("name",i);
                l.setAttribute("value",j);
                k.appendChild(l)
            }
            function c(i){
                return g.getElementById(i)
            }
            function Y(i){
                return g.createElement(i)
            }
            function O(k){
                var j=a.pv,i=k.split(".");
                i[0]=parseInt(i[0],10);
                i[1]=parseInt(i[1],10);
                i[2]=parseInt(i[2],10);
                return(j[0]>i[0]||(j[0]==i[0]&&j[1]>i[1])||(j[0]==i[0]&&j[1]==i[1]&&j[2]>=i[2]))?true:false
            }
            function A(m,j){
                if(a.ie&&a.mac){
                    return
                }
                var l=g.getElementsByTagName("head")[0],k=Y("style");
                k.setAttribute("type","text/css");
                k.setAttribute("media","screen");
                if(!(a.ie&&a.win)&&typeof g.createTextNode!=Z){
                    k.appendChild(g.createTextNode(m+" {"+j+"}"))
                }
                l.appendChild(k);
                if(a.ie&&a.win&&typeof g.styleSheets!=Z&&g.styleSheets.length>0){
                    var i=g.styleSheets[g.styleSheets.length-1];
                    if(typeof i.addRule==P){
                        i.addRule(m,j)
                    }
                }
            }
            function X(k,i){
                var j=i?"visible":"hidden";
                if(S){
                    c(k).style.visibility=j
                }else{
                    A("#"+k,"visibility:"+j)
                }
            }
            return{
                registerObject:function(l,i,k){
                    if(!a.w3cdom||!l||!i){
                        return
                    }
                    var j={};

                    j.id=l;
                    j.swfVersion=i;
                    j.expressInstall=k?k:false;
                    H[H.length]=j;
                    X(l,false)
                },
                getObjectById:function(l){
                    var i=null;
                    if(a.w3cdom&&S){
                        var j=c(l);
                        if(j){
                            var k=j.getElementsByTagName(P)[0];
                            if(!k||(k&&typeof j.SetVariable!=Z)){
                                i=j
                            }else{
                                if(typeof k.SetVariable!=Z){
                                    i=k
                                }
                            }
                        }
                    }
                    return i
                },
                embedSWF:function(n,u,r,t,j,m,k,p,s){
                    if(!a.w3cdom||!n||!u||!r||!t||!j){
                        return
                    }
                    r+="";
                    t+="";
                    if(O(j)){
                        X(u,false);
                        var q=(typeof s==P)?s:{};

                        q.data=n;
                        q.width=r;
                        q.height=t;
                        var o=(typeof p==P)?p:{};

                        if(typeof k==P){
                            for(var l in k){
                                if(k[l]!=Object.prototype[l]){
                                    if(typeof o.flashvars!=Z){
                                        o.flashvars+="&"+l+"="+k[l]
                                    }else{
                                        o.flashvars=l+"="+k[l]
                                    }
                                }
                            }
                        }
                        J(function(){
                            R(q,o,u);
                            if(q.id==u){
                                X(u,true)
                            }
                        })
                    }else{
                        if(m&&!C&&O("6.0.65")&&(a.win||a.mac)){
                            X(u,false);
                            J(function(){
                                var i={};

                                i.id=i.altContentId=u;
                                i.width=r;
                                i.height=t;
                                i.expressInstall=m;
                                D(i)
                            })
                        }
                    }
                },
                getFlashPlayerVersion:function(){
                    return{
                        major:a.pv[0],
                        minor:a.pv[1],
                        release:a.pv[2]
                    }
                },
                hasFlashPlayerVersion:O,
                createSWF:function(k,j,i){
                    if(a.w3cdom&&S){
                        return R(k,j,i)
                    }else{
                        return undefined
                    }
                },
                createCSS:function(j,i){
                    if(a.w3cdom){
                        A(j,i)
                    }
                },
                addDomLoadEvent:J,
                addLoadEvent:M,
                getQueryParamValue:function(m){
                    var l=g.location.search||g.location.hash;
                    if(m==null){
                        return l
                    }
                    if(l){
                        var k=l.substring(1).split("&");
                        for(var j=0;j<k.length;j++){
                            if(k[j].substring(0,k[j].indexOf("="))==m){
                                return k[j].substring((k[j].indexOf("=")+1))
                            }
                        }
                    }
                    return""
                },
                expressInstallCallback:function(){
                    if(C&&L){
                        var i=c(K);
                        if(i){
                            i.parentNode.replaceChild(L,i);
                            if(T){
                                X(T,true);
                                if(a.ie&&a.win){
                                    L.style.display="block"
                                }
                            }
                            L=null;
                            T=null;
                            C=false
                        }
                    }
                }
            }
        }();

        ////////////////

        //var SWF_URL = "http://www.pliantcode.com/lib/crossxhr.swf"; // set if you want to hardcode path to crossxhr.swf
        //var SWF_URL = "http://localhost:8080/cxapi/lib/crossxhr.swf";

        FlashHttpRequest_objects = new Object();
        FlashHttpRequest_counter = 0;
        function FlashHttpRequest_(parent) {
            this.parent = parent;
            this.id = FlashHttpRequest_counter++;
            FlashHttpRequest_objects[this.id] = this;
            var gateway;
            this.open = function(method, url) {
                gateway = window.document.getElementById("FlashHttpRequest_gateway");
                gateway.create(this.id, method, url);
            }
            this.send = function(content) {
                gateway.send(this.id, content);
            }
            this.handler = function(status, data) {
                var obj = this.parent ? parent : this;
                obj.readyState = 4;
                obj.responseText = data;
                obj.status = status;
                /////MODDED - Adding the responseXML element - extra work!///
                obj.responseXML=xmlString2Doc(data);
                /////END MODDED///

                var id = this.id;
                setTimeout(function(){  // must release flash
                    gateway.finished(id);
                    obj.onreadystatechange.apply(obj);
                }, 10);
            }
        }
        function FlashHttpRequest_handler(id, status, data) {
            FlashHttpRequest_objects[id].handler(status, data);
        }
        function CrossXHR() {
      
            var obj;
            var queue = new Array();
            var max_wait = 100;
            var gateway = window.document.getElementById("FlashHttpRequest_gateway");
            if (gateway && gateway.create)
                if (typeof(FlashHttpRequest_ready) != 'undefined')
                    obj = new FlashHttpRequest_(this);
            if (!obj) {

                var self = this;
                queue.push( function() {
                    obj = new FlashHttpRequest_(self)
                } );
                setTimeout(function(){
                    self._process_queue()
                }, 100);
            }

            this.open = function(arg1,arg2) {
                if (obj)
                    obj.open(arg1,arg2);
                else {
                    queue.push( function() {
                        obj.open(arg1,arg2)
                    } );
                }
            }
            this.send = function(arg1) {
                if (obj) {
                    obj.send(arg1);
                } else {
                    queue.push( function() {
                        obj.send(arg1)
                    } );
                }
            }
            this._process_queue = function() {
                gateway = window.document.getElementById("FlashHttpRequest_gateway");
                var ok = obj ? true : false;
                if (!ok)
                    if (gateway && gateway.create)
                        if (typeof(FlashHttpRequest_ready) != 'undefined')
                            ok = true;
                if (!ok) {
                    if (max_wait-- > 0)
                        setTimeout(function(){
                            self._process_queue()
                        }, 100);
                } else {
                    while (queue.length > 0) {
                        var task = queue.shift();
                        task.apply(this);
                    }
                }
            }
        }

        if (typeof(SWF_URL) == 'undefined' || !SWF_URL) {
            var prefix="";
            var tags = window.document.getElementsByTagName("script");
            for ( var i = 0; i < tags.length; i++ ) {
                //var pos = tags[i].src.toLowerCase().indexOf("crossxhr.js")
                var pos = tags[i].src.toLowerCase().indexOf("cxapi.js")
                if ( pos != -1 )
                    prefix = tags[i].src.substring(0,pos);
            }
            SWF_URL = prefix+'crossxhr.swf';
        }

        window.document.write('<span style="position:absolute;top:0;left:0"><span id="FlashHttpRequest_gateway"></span></span>');
        swfobject.embedSWF(SWF_URL, "FlashHttpRequest_gateway", "1", "1", "9.0.0", "expressInstall.swf", {}, {
            wmode: 'transparent',
            allowscriptaccess:"always"
        } );
    }
///////////////END XHR FLASH AJAX OBJECT//////////////////