/*
 * $Header:  $
 * $Revision:  $
 * $Date: $
 *
 * ====================================================================
 *
 * Copyright (c) 2000 - 2004 SCC Informationssysteme GmbH. All rights
 * reserved.
 * Vendor URL : http://www.scc-gmbh.com
 * Product URL: http://www.common-controls.com
 *
 * THIS SOFTWARE IS PROVIDED ``AS IS'' AND ANY EXPRESSED OR IMPLIED
 * WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES
 * OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 * DISCLAIMED.  IN NO EVENT SHALL SCC INFORMATIONSSYSTEME GMBH OR
 * ITS CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL,
 * SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT
 * LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF
 * USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND
 * ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY,
 * OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT
 * OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF
 * SUCH DAMAGE.
 *
 * Note: This file belongs to the Common Controls Presentation
 *       Framework. Permission is given to use this script only
 *       together with the Common Controls Presentation Framework
 *
 * ====================================================================
 */

/////////////////////////////////////////////////////////////
// Helper for the Common Controls Framework
/////////////////////////////////////////////////////////////


/*
+ ---------------------------------------------------------------------------------+
| Purpose..:  Helper for the Common Controls Framework
|             
|
| Date        Author            Notice
| ----------  ----------------  ----------------------------------------------------
| 23.12.2002  G.Schulz (SCC)    Initial version
| 24.10.2004  G.Schulz (SCC)    JScript support for CCUtility_crtCtrla function
|                               New function CCUtility_submitEnclosingForm
|
+ ---------------------------------------------------------------------------------+
*/

function CCUtility() {}
function CCUtility_getEnclosingForm(node) {
	// search the form wich embbeds the Element
	var parent = node.parentNode;
	
	if (null == parent) return null;

	if (parent.nodeName == 'FORM') {
		return parent;
	} else {
		return arguments.callee(parent);
	}
}
function CCUtility_submitEnclosingForm(node) {

	var form = this.getEnclosingForm(node);
	
	if (null == form) {
		// form not specified -> do nothing
		return false;
	} else {
		form.submit();
	}
}
function CCUtility_resetEnclosingForm(node) {

	var form = this.getEnclosingForm(node);
	
	if (null != form) {
		form.reset();
	}

	// do not submit the form
	return false;
}
function CCUtility_createHidden(fldName, fldValue) {
	var input = null;

	// check if the hidden field already exist
	input = document.getElementById(fldName);

	if (null != input) {
		input.value=fldValue;
		return input;
	} else {
		var input=document.createElement('INPUT');
		input.type='hidden';
		input.id=fldName;
		input.name=fldName;
		input.value=fldValue;
		return input;
	}
}
/**
 * node......: Current node which generates the event
 * param.....: action used by the control to identify the event
 * formId....: optional id of the form, if null the enclosing form is searched by the script
 * userscript: optional jscript which must return true before the form is sumbitted
 */
function CCUtility_crtCtrla(node, param, formId, userscript) {
	var form = null;

	// if a user script was defined execute the script
	if (null != userscript) {
		var rtc = true;

		var userfct = new Function(userscript);
		var obj = userfct();

		if (typeof obj == 'boolean') {
			rtc = new Boolean(obj);
		}

		if (!rtc.valueOf()) {	
			return false;
		}
	}

	// first check if the formId was specified
	if (null != formId && '' != formId) {
		form = document.getElementById(formId);
	} else {
		// try to find the enclosing form
		form = this.getEnclosingForm(node);
	}

	if (null != form) {
		form.appendChild(this.createHidden('ctrla', param));
		form.submit();
	} else {
		// not found. Do nothing
	}
}

new CCUtility();
CCUtility.getEnclosingForm     = CCUtility_getEnclosingForm;
CCUtility.submitEnclosingForm  = CCUtility_submitEnclosingForm;
CCUtility.resetEnclosingForm   = CCUtility_resetEnclosingForm;
CCUtility.createHidden         = CCUtility_createHidden;
CCUtility.crtCtrla             = CCUtility_crtCtrla;


/*
+ ---------------------------------------------------------------------------------+
| Purpose..: Helper for the ListControl object
|
| Date        Author            Notice
| ----------  ----------------  ----------------------------------------------------
| 08.04.2004  G.Schulz (SCC)    Erstversion
| 08.06.2004  G.Schulz (SCC)    ListHelp.checkAll added; ListHelp_uncheck renamed
|
+ ---------------------------------------------------------------------------------+
*/
function ListHelp() {
}
/**
 * Unchecks all Checkboxes specified in the argument
 * param: items An array of input fields
 */
function ListHelp_uncheckAll(items) {

	for (var i=0; i < items.length; i++) {
		if (items[i].type == 'checkbox' ) {

			// uncheck checkbox
			items[i].checked = false;
		}
	}
}
function ListHelp_checkAll(items) {
	for (var i=0; i < items.length; i++) {
		if (items[i].type == 'checkbox' ) {

			// uncheck checkbox
			items[i].checked = true;
		}
	}
}
function ListHelp_isChecked(items) {
	for (var i=0; i < items.length; i++) {
		if (items[i].type == 'checkbox' || items[i].type == 'radio') {
			if (items[i].checked) {
				return true;
			}
		}
	}
}
new ListHelp();
ListHelp.uncheckAll     = ListHelp_uncheckAll;
ListHelp.checkAll       = ListHelp_checkAll;
ListHelp.isChecked      = ListHelp_isChecked;


/*
+ ---------------------------------------------------------------------------------+
| Object....: HTTPUtil
|
| Date        Author            Note
| ----------  ----------------  ----------------------------------------------------
| 22.05.2004  G.Schulz (SCC)    Initial version
|
+ ---------------------------------------------------------------------------------+
*/
function HTTPUtil() {
}
function HTTPUtil_getParameters(location) {
	var url = new String(location);
	
	if (null == url) {
		return null;
	}
	var qString = url.split('?');

	if (null == qString || qString.length == 1) {
		return null;
	}
	
	var params = qString[1].split('&');
	return params;
}
function HTTPUtil_getParameter(key, params) {
	
	if (null == params || null == key) {
		return '';
	}
	
	for (var i=0; i < params.length; i++) {
		var arr = params[i].split('=');
		
		if (arr[0].toUpperCase() == key.toUpperCase()) {
			return arr[1];
		}
	}
}
function HTTPUtil_isSecure() {
	return ('https:' == window.location.protocol);
}
new HTTPUtil();
HTTPUtil.getParameters  = HTTPUtil_getParameters;
HTTPUtil.getParameter   = HTTPUtil_getParameter;
HTTPUtil.isSecure       = HTTPUtil_isSecure

