/*
 * $Header: /cvsroot/woops/Woops/WebContent/fw/global/jscript/common.js,v 1.3 2006/03/08 17:39:25 castel Exp $
 * $Revision: 1.3 $
 * $Date: 2006/03/08 17:39:25 $
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
// Global Variables used by the CC-Framework
/////////////////////////////////////////////////////////////


var LF = '\n';
var NBSP = '';

var RunAt = new Object();
RunAt.SERVER = 0;
RunAt.CLIENT = 1;

var SelectMode = new Object();
SelectMode.NONE = 0;
SelectMode.SINGLE = 1;
SelectMode.MULTIPLE = 2;

var NodeType = new Object();
NodeType.NODE  = 1;
NodeType.GROUP = 2;

var ExpandMode = new Object();
ExpandMode.SINGLE   = 0;
ExpandMode.MULTIPLE = 1;
ExpandMode.FULL     = 2;

var NodeState = new Object();
NodeState.NONE      = 0;
NodeState.EXPAND    = 1;
NodeState.COLLAPSE  = 2;
NodeState.EXPANDEX  = 3;

var NodeFilter = new Object();
NodeFilter.ROOT     = 2;
NodeFilter.GROUP    = 3;
NodeFilter.NODE     = 4;

var CheckState = new Object();
CheckState.CHECKED    = 1;
CheckState.UNCHECKED  = 2;
CheckState.UNDEFINED  = 3;

var Orientation = new Object();
Orientation.VERTICAL   = 1;
Orientation.HORIZONTAL = 2;



/*
+ ---------------------------------------------------------------------------------+
| Purpose..:  Opens a PopUp Window which shows a datetime picker.
|
| Date        Author            Notice
| ----------  ----------------  ----------------------------------------------------
| 22.04.2004  G.Schulz (SCC)    Inital version
+ ---------------------------------------------------------------------------------+
*/
var calendar = null;

function popupCalendar(fieldId, locale, formatMask, width, height, template) {
	var obj    = document.getElementById(fieldId);
	var id     = obj.id;
	var value  = (null != obj.value) ? obj.value : '';
	var target = '';
	
	// ----- force the width to 380 ----
	width = 380;
	// ---------------------------------
	
	if (null == width || '' == width) {
		width = 380;
	}
	if (null == height || '' == height) {
		height = 250;
	}
	
	var isTime = DateFormat.isTimeMask(formatMask);
	
	if (!isTime) {
//		width = 215;
	}
	
	target += template;
	target += '?datetime=' + value;
	target += '&fieldid=' + id;
	target += '&locale=' + locale.toUpperCase();
	target += '&mask=' + formatMask;

	// get the coordinate to display the new window
	var left = (screen.width - width) / 2;
	var top  = (screen.height - height) / 2;

	// Check if the window is already opend
	if (null == calendar || calendar.closed) {
		calendar = window.open(target, '', 'top=' + top + ',left=' + left + ',width=' + width + ',height=' + height + ',status=no,toolbar=no,location=no,resizable=no,scrollbars=no,menubar=no');
	} else {
		calendar.focus();
	}
}


/*
+ ---------------------------------------------------------------------------------+
| Purpose..:  Opens a PopUp Window which shows a color picker.
|
| Date        Author            Notice
| ----------  ----------------  ----------------------------------------------------
| 22.04.2004  G.Schulz (SCC)    Inital version
+ ---------------------------------------------------------------------------------+
*/
var colorpicker = null;

function popupCPicker(target, fieldId, locale, palette) {
	var obj    = document.getElementById(fieldId);
	var id     = obj.id;
	var value  = (null != obj.value) ? obj.value : '';

	target += '?fieldid=' + id;
	target += '&locale='  + locale.toUpperCase();
	target += '&value='   + value;
	target += '&palette=' + palette;

	// get the coordinate to display the new window
	var left = (screen.width - 255) / 2;
	var top  = (screen.height - 250) / 2;

	// Check if the window is already opend
	if (null == colorpicker || colorpicker.closed) {
		colorpicker = window.open(target, '', 'top=' + top + ',left=' + left + ',width=255,height=250,status=no,toolbar=no,location=no,resizable=no,scrollbars=no,menubar=no');
	} else {
		colorpicker.focus();
	}
}

/*
+ ---------------------------------------------------------------------------------+
| Purpose..:  Opens a PopUp Window to edit the textarea within a seperate window.
|
| Date        Author            Notice
| ----------  ----------------  ----------------------------------------------------
| 21.10.2004  G.Schulz (SCC)    Inital version
| 15.11.2004  G.Schulz (SCC)    New readonly attribute
+ ---------------------------------------------------------------------------------+
*/
var textpopup = null;

function popupTextPopup(fieldId, locale, maxlength, readonly, width, height, rows, template) {
	var target    = '';
	var obj       = document.getElementById(fieldId);
	var id        = obj.id;
	var winWidth  = (null == width || isNaN(width)) ? 350 : width;
	var winHeight = (null == height || isNaN(height)) ? 150 : height;

	target += template;
	target += '?fieldid=' + id;
	target += '&maxlength=' + maxlength;
	target += '&locale='  + locale.toUpperCase();
	target += '&height='  + winHeight;
	target += '&readonly=' + readonly;
	
	// get the coordinate to display the new window
	var left = (screen.width - winWidth) / 2;
	var top  = (screen.height - winHeight) / 2;

	// Check if the window is already opend
	if (null == textpopup || textpopup.closed) {
		var options = 'top=' + top + ',left=' + left + ',width=' + winWidth + ',height=' + winHeight + ',status=no,toolbar=no,location=no,resizable=no,scrollbars=no,menubar=no'
		textpopup = window.open(target, '', options);
	} else {
		textpopup.focus();
	}
}


/*
+ ---------------------------------------------------------------------------------+
| Purpose..:  Provides some helper functions for a textarea
|
| Date        Author            Notice
| ----------  ----------------  ----------------------------------------------------
| 12.06.2004  G.Schulz (SCC)    Initial version
| 01.11.2004  G.Schulz (SCC)    dirty flag added
| 13.02.2005  G.Schulz (SCC)    Input not longer accepted if maximum no. of characters is exeeded
|
+ ---------------------------------------------------------------------------------+
*/
function Textarea(id, maxlength, message, nobreak) {
	var a = arguments;
	this.id                = id;
	this.obj               = document.getElementById(id);
	this.maxlength         = (a.length >= 1) ? a[1] : null;
	this.limited           = (maxlength != null) ? true : false;
	this.infoLine          = (a.length >= 2) ? a[2] : 'Characters remaining: <b>{0}</b>/{1}';    // Text to display for remaining characters
	this.nobreak           = (a.length >= 3) ? a[3] : false;
	this.infoLineNode      = null;                                                               // span which contains the text
	this.textWarningtAt    = 0;                                                                  // default 10 characters remaining
	this.CSS_INFOLINE      = 'ltail';
	this.dirty             = false;
	this.allowOverflow     = false;
	
	// add eventhandler
	if (null != this.obj && this.limited) {
	
		this.createInfoLine();
	
		this.setUpHandler();
		
		// check if the textare is readonly or disabled
		// In both cases no message is displayed
		if (!this.isReadonly() && !this.isDisabled()) {
			this.checkLimit();
		}
	}
}
function Textarea_getId() {
	return this.id;
}
function Textarea_getMaxLength() {
	return this.maxlength;
}
function Textarea_setInfoLine(infoLine) {
	this.infoLine = infoLine;
}
function Textarea_setUpHandler() {
	var _textarea = this;

	// check if the textare is readonly or disabled
	// In both cases no message is displayed and
	// no handlers where needed
	if (this.isReadonly() || this.isDisabled()) {
		return;
	}
	
	this.obj.onchange = function() {
		_textarea.checkLimit();
	}
	this.obj.onkeyup = function() {
		_textarea.checkLimit();
	}
	this.obj.onchange = function() {
		_textarea.dirty = true;
	}
	this.obj.onkeypress = function() {
		return _textarea.checkLimit();
	}
	this.obj.onpaste = function() {
		_textarea.checkLimit();
	}
}
function Textarea_checkLimit() {

	if (0 == this.maxlength) {
		return;
	}

	var remaining = parseInt(this.maxlength) - parseInt(this.obj.value.length);

	if (!this.allowOverflow && remaining == 0){
		this.updateInfoLine(0, 0);
		return false;
	}
	if (!this.allowOverflow && remaining < 0) {
		this.obj.value = this.obj.value.substr(0, this.maxlength);
		this.updateInfoLine(0, this.textWarningtAt);
	} else {
		this.updateInfoLine(remaining, this.textWarningtAt);
	}
}
function Textarea_updateInfoLine(remaining, warningtAt) {
	var warning = (remaining < warningtAt) ? true : false;

	var	out = this.infoLine;
	
	if (this.infoLine.indexOf('{0}') != -1) {
		out = out.replace('{0}', remaining);
	}
	if (this.infoLine.indexOf('{1}') != -1) {
		out = out.replace('{1}', this.maxlength);
	}
	
	if (warning) {
		out = out.fontcolor('red');
	}

	if (!this.nobreak) {
		out = '<br>' + out;
	}

	this.infoLineNode.innerHTML = out;
}
function Textarea_createInfoLine() {
	var text = document.createTextNode(this.infoLine);
	var span = document.createElement('Span');
	span.appendChild(text);
	span.className = this.CSS_INFOLINE;
	this.obj.parentNode.appendChild(span);
	
	this.infoLineNode = span;
}
function Textarea_encodeHTML(s) {
	s = s.replace(/\&/g, "&amp;").replace(/</g, "&lt;").replace(/>/g, "&gt;").replace(/\n/g, "<BR>");

	while (/\s\s/.test(s)) {
		s = s.replace(/\s\s/, "&nbsp; ");
	}
	
	return s.replace(/\s/g, " ");
}
function Textarea_isDirty() {
	return this.dirty;
}
function Textarea_insertTag(textareaId, tag) {
	var ta = document.getElementById(textareaId);
	var tagOpen = '[' + tag.toLowerCase() + ']';
	var tagClose = '[/' + tag.toLowerCase() + ']';
	
	if (ie) {
		var selected = document.selection.createRange().text;

		if (selected) {
            var addSpace = false;
            if (selected.charAt(selected.length-1) == ' ') {
                selected = selected.substring(0, selected.length-1);
                addSpace = true;
            }
            document.selection.createRange().text = tagOpen + selected + tagClose + ((addSpace) ? ' ': '');
        } else {
            ta.value += tagOpen + tagClose;
        }
    } else {
        ta.value += tagOpen + tagClose;
    }
    
    ta.focus();
    return;
}
function Textarea_isReadonly() {
	if (null == this.obj) {
		return false;
	} else {
		if (ie) {
			return this.obj.getAttribute('readonly');
		} else {
			return this.obj.hasAttribute('readonly');
		}
	}
}
function Textarea_isDisabled() {
	if (null == this.obj) {
		return false;
	} else {
		if (ie) {
			return this.obj.getAttribute('disabled');
		} else {
			return this.obj.hasAttribute('disabled');
		}
	}
}
function Textarea_setAllowOverflow(allowOverflow) {
	this.allowOverflow = allowOverflow;
}
function Textarea_isAllowOverflow(allowOverflow) {
	return this.allowOverflow;
}
function Textarea_toString() {
	var out = '';
	out += '******** TextArea ******* ' + LF;
	out += 'Id.........: ' + this.id + LF;
	out += 'MaxLenght..: ' + this.maxlength + LF;
	out += 'Limited....: ' + this.limited + LF;
	out += 'InfoLine...: ' + this.infoLine + LF;
	out += 'Dirty......: ' + this.dirty;
	out += 'Overflow...: ' + this.allowOverflow;
	return out;
}
new Textarea();
Textarea.prototype.setUpHandler     = Textarea_setUpHandler;
Textarea.prototype.checkLimit       = Textarea_checkLimit;
Textarea.prototype.createInfoLine   = Textarea_createInfoLine;
Textarea.prototype.updateInfoLine   = Textarea_updateInfoLine;
Textarea.prototype.isDirty          = Textarea_isDirty;
Textarea.prototype.isReadonly       = Textarea_isReadonly
Textarea.prototype.isDisabled       = Textarea_isDisabled;
Textarea.prototype.setAllowOverflow = Textarea_setAllowOverflow;
Textarea.prototype.isAllowOverflow  = Textarea_isAllowOverflow;
Textarea.encodeHTML                 = Textarea_encodeHTML;
Textarea.insertTag                  = Textarea_insertTag;
Textarea.prototype.toString         = Textarea_toString;



/*
+ ---------------------------------------------------------------------------------+
| Object....: ImageMapping
| Function..: 
| Arguments.: 
|
| Date        Author            Note
| ----------  ----------------  ----------------------------------------------------
| 05.01.2005  G.Schulz (SCC)    Initial version
+ ---------------------------------------------------------------------------------+
*/
function ImageMapping(rule, src, width, height, tooltip, alt, resource) {
	var a = arguments;

	this.rule     = rule;                              // RegExpression teh image must match
	this.src      = src;                               // image source
	this.width    = width;                             // image width
	this.height   = height;                            // image height
	this.tooltip  = (a.length >= 5) ? tooltip : '';    // additional tooltip
	this.alt      = (a.length >= 6) ? alt : '';        // alt attribute
	this.resource = (a.length >= 7) ? resource : '';
}
function ImageMapping_getSource() {
	return this.src;
}
function ImageMapping_getWidth() {
	return this.width;
}
function ImageMapping_getHeight() {
	return this.height;
}
function ImageMapping_getTooltip() {
	return this.tooltip;
}
function ImageMapping_getAlt() {
	return this.alt;
}
function ImageMapping_toString() {
	var out = '';
	out += '********* ImageMapping ***********' + LF
	out += 'Rule...........: ' + this.rule + LF;
	out += 'Source.........: ' + this.src + LF;
	out += 'Width..........: ' + this.width + LF;
	out += 'Height.........: ' + this.height + LF;
	out += 'Tooltip........: ' + this.tooltip + LF;
	out += 'Alt............: ' + this.alt + LF;
	out += 'Resource.......: ' + this.resource + LF;
	return out;
}
new ImageMapping();
ImageMapping.prototype.getSource  = ImageMapping_getSource;
ImageMapping.prototype.getWidth   = ImageMapping_getWidth;
ImageMapping.prototype.getHeight  = ImageMapping_getHeight;
ImageMapping.prototype.getTooltip = ImageMapping_getTooltip;
ImageMapping.prototype.getAlt     = ImageMapping_getAlt;
ImageMapping.prototype.toString   = ImageMapping_toString;



/*
+ ---------------------------------------------------------------------------------+
| Object....: ImageMap
| Function..: Collection for ImageMapping objects
| Arguments.: id, runAt
|
| Date        Author            Note
| ----------  ----------------  ----------------------------------------------------
| 05.01.2005  G.Schulz (SCC)    Initial version
+ ---------------------------------------------------------------------------------+
*/
function ImageMap(id, runAt, base) {
	var a = arguments;
	
	this.arrImageMappings = new Array();                            // Collection
	this.id     = id;                                               // the id for this image map
	this.runAt  = (a.length >= 2) ? runAt : RunAt.SERVER;           // indicates if the control should work with or without roundtrips
	this.base   = (a.length >= 3) ? base : '';                      // The base directory for all the images
}
function ImageMap_addImageMapping(mapping) {
	if (mapping instanceof ImageMapping) {
		this.arrImageMappings[this.arrImageMappings.length] = mapping;
	}
}
function ImageMap_getImageMapping(rule) {
	for (var i=0; i <= this.arrImageMappings.length; i++) {
		var imRule = this.arrImageMappings[i]['rule'];
		
		if (imRule == rule) {
			return this.arrImageMappings[i];
		}
	}

	return null;
}
function ImageMap_getImageMappings() {
	// return the collection
	return this.arrImageMappings;
}
function ImageMap_toString() {
	var out = '';
	out += '********* ImageMap ***********' + LF
	out += 'Id.............: ' + this.id + LF;
	out += 'RunAt..........: ' + this.runAt + LF;
	out += 'Base...........: ' + this.base + LF;
	return out;
}
new ImageMap();
ImageMap.prototype.toString           = ImageMap_toString;
ImageMap.prototype.addImageMapping    = ImageMap_addImageMapping;
ImageMap.prototype.getImageMapping    = ImageMap_getImageMapping;
ImageMap.prototype.getImageMappings   = ImageMap_getImageMappings;



/*
+ ---------------------------------------------------------------------------------+
| Object....: Icon
| Function..: An Image object
| Arguments.: id, resPath, src, width, height, tooltip, alt
|
| Date        Author            Note
| ----------  ----------------  ----------------------------------------------------
| 17.08.2004  G.Schulz (SCC)    Initial version
| 22.11.2004  G.Schulz (SCC)    Hide ALT + TITLE if null
+ ---------------------------------------------------------------------------------+
*/
function Icon(id, resPath, src, width, height, tooltip, alt) {
	this.id        = id;             // unique button id
	this.width     = width;          // button width
	this.height    = height;         // button height
	this.src       = src;            // src-Attribute
	this.onclick   = Icon_onclick;
	this.resPath   = resPath;
	this.tooltip   = tooltip;        // Tooltip
	this.alt       = alt;
	this.border    = 0;
}
function Icon_onclick() {
	return;
}
function Icon_create() {
	var img    = document.createElement('Img');
	img.src    = this.src;
	img.title  = (null != this.tooltip) ? this.tooltip : '';
	img.alt    = (null != this.alt) ? this.alt : '';
	img.border = 0;
	img.width  = this.width;
	img.height = this.height;
	img.setAttribute('vspace', 0);
	img.border = this.border;
	return img;
}
function Icon_toString() {
	var out = '';
	out += '******* Icon *********' + LF
	out += 'Id.............: ' + this.id + LF;
	out += 'ResPath........: ' + this.resPath + LF;
	out += 'Source.........: ' + this.src + LF;
	out += 'Width..........: ' + this.width + LF;
	out += 'Height.........: ' + this.height + LF;
	out += 'Tooltip........: ' + this.tooltip + LF;
	out += 'Alt............: ' + this.alt + LF;
	out += 'Border.........: ' + this.border + LF;
	return out;
}
new Icon();
Icon.prototype.onclick    = Icon_onclick;       // used to assign an onclick handler
Icon.prototype.toString   = Icon_toString;      // toString
Icon.prototype.create     = Icon_create;        // creates a html img object

/*
+ ---------------------------------------------------------------------------------+
| Calendar Message Resources
| 
| Date        Author            Note
| ----------  ----------------  ----------------------------------------------------
| 22.05.2004  G.Schulz (SCC)    Initial version
|
+ ---------------------------------------------------------------------------------+
*/
function TextButton(id, label, width, resPath, imgSrc, imgWidth, height, tooltip) {
	this.id        = id;             // unique button id
	this.label     = label;          // the button label
	this.width     = width;          //
	this.imgWidth  = imgWidth;       // array including width of the bgimages (left, middle, right)
	this.imgSrc    = imgSrc;         // array including the image resources (left, middle, right)
	this.height    = height;         // button hight
	this.onclick   = TextButton_onclick;
	this.resPath   = resPath;
	this.tooltip   = tooltip;

	if (arguments.length <= 4) {
		// register some default images
		this.imgSrc   = ['btnBkg1_left.gif', 'btnBkg1_middle.gif', 'btnBkg1_right.gif'];
		this.imgWidth = [7, 0, 7];
		this.height   = 24;
		this.tooltip  = label;
	}
}
function TextButton_create() {
	var row   = null;
	var cell  = null;
	var img   = null;
	var span  = null;
	
	// create Table
	var table = document.createElement('Table');
	table.cellSpacing = 0;
	table.cellPadding = 0;
	table.width = (this.width - TextButton.getWidth(this.imgWidth));
	table.className = 'tbtn';
	table.onclick = this.onclick;
	
	row = table.insertRow(table.rows.length);
	row.setAttribute('valign', 'middle');
	
	// left
	cell = row.insertCell(row.cells.length);
	img = document.createElement('Img');
	img.width  = this.imgWidth[0];
	img.height = this.height;
	img.src    = this.resPath + this.imgSrc[0];
	img.border = 0;
	cell.appendChild(img);
	
	// middle
	cell = row.insertCell(row.cells.length);
	cell.width = '100%';
	cell.setAttribute('background-position', 'right');
	cell.setAttribute('word-wrap', true);
	cell.setAttribute('background', this.resPath + this.imgSrc[1]);
	
	span = document.createElement('Span');
	span.appendChild(document.createTextNode(this.label));
	span.title  = this.tooltip;
	cell.appendChild(span);
	
	// right
	cell = row.insertCell(row.cells.length);
	img = document.createElement('Img');
	img.width  = this.imgWidth[2];
	img.height = this.height;
	img.src    = this.resPath + this.imgSrc[2];
	img.border = 0;
	cell.appendChild(img);

	return table;
}
function TextButton_onclick() {
	return;
}
function TextButton_getWidth(widths) {
	var total = 0;
	
	for(var i=0; i < widths.length; i++) {
		var val = parseInt(widths[i]);
		
		if (!isNaN(val)) {
			total =+ val;
		}
	}
	return total;
}
function TextButton_toString() {
	var out = '';
	out += '******* TextButton *********' + LF
	out += 'Id.............: ' + this.id + LF;
	out += 'Label..........: ' + this.label + LF;
	out += 'BGImages.......: ' + this.imgSrc + LF;
	out += 'Tooltip........: ' + this.tooltip + LF;
	out += 'Alt............: ' + this.alt + LF;
	return out;
}
new TextButton();
TextButton.prototype.create     = TextButton_create;
TextButton.prototype.onclick    = TextButton_onclick;
TextButton.prototype.toString   = TextButton_toString;
TextButton.getWidth             = TextButton_getWidth;


/*
+ ---------------------------------------------------------------------------------+
| Object...: SwapSelect
| Function.: 
|
| Date        Author            Description
| ----------  ----------------  ----------------------------------------------------
| 29.12.2004  G.Schulz          Inital version
|
+ ---------------------------------------------------------------------------------+
*/
function SwapSelect(id, runat, hiddenName) {
	var a = arguments;
	
	this.id            = id;                                               // the id for the control
	this.runAt         = (a.length >= 2) ? runat : RunAt.SERVER;           // indicates if the control should work with or without roundtrips
	this.hiddenName    = (a.length >= 3) ? hiddenName : id;                // Name of the hidden field
	this.orientation   = Orientation.VERTICAL;                             // select elementes are displayed vertical or horizontal
	this.keepSortOrder = true;                                             // move item at the end (flase) or use the original position
	this.keepSelection = true;                                             // keep selection for moved options 
	this.disabled      = false;
	this.PREFIX        = 'swc_';
	this.BTNPREFIX     = 'btnswc_';
	this.ROOT          = 'swc_span_'+ id;
	
	if (null != id) {
		this.optionsSrc = document.getElementById('swcl_' + id);
		this.optionsSel = document.getElementById('swcr_' + id);
		this.optionsSrc.swc_type = 'L';
		this.optionsSel.swc_type = 'R';

		if (this.runAt == RunAt.CLIENT) {
			// assign the client handlers		
			this.setupEventHandler();
		}
	}
}
function SwapSelect_setRunAt(runat) {
	this.runAt = runat;
}
function SwapSelect_getRunAt() {
	return this.runAt;
}
function SwapSelect_setOrientation(orientation) {
	this.orientation = orientation;
}
function SwapSelect_getOrientation() {
	return this.orientation;
}
function SwapSelect_setDisabled(disabled) {
	this.disabled = disabled;
}
function SwapSelect_isDisabled() {
	return this.disabled;
}
function SwapSelect_setupEventHandler() {
	// get the buttons
	var objAddAll    = document.getElementById(this.BTNPREFIX + this.id + '_AddAll');
	var objAdd       = document.getElementById(this.BTNPREFIX + this.id + '_Add');
	var objRemove    = document.getElementById(this.BTNPREFIX + this.id + '_Remove');
	var objRemoveAll = document.getElementById(this.BTNPREFIX + this.id + '_RemoveAll');

	var ctrl = this;

	objAddAll.onclick = function() {
							ctrl.doMove(ctrl.optionsSrc, ctrl.optionsSel, true);
							ctrl.updateHiddenFields(ctrl.optionsSel);
							return false;
						}
		
	objAdd.onclick    = function() {
							ctrl.doMove(ctrl.optionsSrc, ctrl.optionsSel, false);
							ctrl.updateHiddenFields(ctrl.optionsSel);
							return false;
						}
	
	objRemove.onclick   = function() {
							ctrl.doMove(ctrl.optionsSel, ctrl.optionsSrc, false);
							ctrl.updateHiddenFields(ctrl.optionsSel);
							return false;
						}
	
	objRemoveAll.onclick = function() {
							ctrl.doMove(ctrl.optionsSel, ctrl.optionsSrc, true);
							ctrl.updateHiddenFields(ctrl.optionsSel);
							return false;
						}
							
	this.optionsSrc.onclick = function() {
							ctrl.unselectOptionList(ctrl.optionsSel);
						}

	this.optionsSel.onclick = function() {	
							ctrl.unselectOptionList(ctrl.optionsSrc);
						}
	
}
function SwapSelect_unselectOptionList(optionList) {

	// unselect option elements
	for(var i=0; i < optionList.options.length; i++) {
		optionList.options[i].selected = false;
	}
}
function SwapSelect_doMove(source, target, moveall) {
	
	if (null == source || null == target) {
		return;
	}

	if (this.keepSelection) {
		this.unselectOptionList(target);
	}

	if(moveall) {
		// copy all options
		var opt = source.options;

		for(var i=0; i < opt.length; i++) {
			var newOption = new Option(opt[i].text, opt[i].value, false, false);
			newOption.swc_type  = opt[i].getAttribute('swc_type');
			newOption.selected  = this.keepSelection;
			
			target.options[target.options.length] = newOption;
		}
		
		// delete all options from the source element
		source.options.length=0;
		
		if (this.keepSortOrder) {
			this.sort(target);
		}
	} else {
		// copy only the selected options
		while (source.selectedIndex != -1) {
			var opt = source.options[source.selectedIndex];
			
			if (opt.selected) {
				// delete the selected option from the source element
				source.options[source.selectedIndex] = null;
				
				// insert the selected option into the target element
				var newOption =  new Option(opt.text, opt.value, false, false);
				newOption.swc_index  = opt.getAttribute('swc_index');
				newOption.selected  = this.keepSelection;
				target.options[target.options.length] = newOption;
				
				if (this.keepSortOrder) {
					this.sort(target);
				}
			}
		}
	}
}
function SwapSelect_sort(optionList) {

	if (null == optionList) {
		return;
	}

	var arr  = new Array();
	
	// add options to array
	for(var i=0; i < optionList.options.length; i++) {
			arr[arr.length] = optionList.options[i];
	}
	
	// sort the array
	arr.sort(SwapSelect_typeOrder);
	
	// reset select element
	optionList.options.length=0;
	
	// add sorted options
	for(var i=0; i < arr.length; i++) {
		var newOption = new Option(arr[i].text, arr[i].value, false, false);
		newOption.swc_index = arr[i].getAttribute('swc_index');
		newOption.selected  = arr[i].selected;
		optionList.options[optionList.options.length] = newOption;
	}
	
}
function SwapSelect_typeOrder(a, b) {
	var va = parseInt(a.getAttribute('swc_index'));
	var vb = parseInt(b.getAttribute('swc_index'));
	
	if (va > vb) {
		return 1;
	} else if (va < vb) {
		return -1;
	} else {
		return 0;
	}
}
function SwapSelect_getOptionList(side) {
	if (side.toUpperCase() == 'L') {
		return this.optionsSrc;
	} else if (side.toUpperCase() == 'R') {
		return this.optionsSel;
	} else {
		return null;
	}
}
function SwapSelect_setKeepSortOrder(keepSortOrder) {
	this.keepSortOrder = keepSortOrder;
}
function SwapSelect_isKeepSortOrder() {
	return this.keepSortOrder;
}
function SwapSelect_setKeepSelection(keepSelection) {
	this.keepSelection = keepSelection;
}
function SwapSelect_isKeepSelection() {
	return this.keepSelection;
}
function SwapSelect_updateHiddenFields(optionList) {
	// This function creates the required hidden fields
	// which are used to synchronize the selected items
	// after a server roundtrip.

	var span = document.getElementById(this.ROOT);

	// remove existing hidden fields
	var inputfields = span.getElementsByTagName('input');
	var length = inputfields.length;
	
	for (var i = length - 1; i >= 0; i--) {
		var node = inputfields[i];
		
		if (node.type == 'hidden' && node.name == this.hiddenName) {
			//alert(i + ") " + inputfields[i].value);
			var parent = node.parentNode;
			parent.removeChild(node);
		}
	}

	// add the new hidden fields for the selected options
	if (optionList.options.length != 0) {
		for (var i=0; i < optionList.options.length; i++) {
			var option = optionList.options[i];
			var hidden = this.createHidden(option.value);
			span.appendChild(hidden);
		}
	} else {
		// we must create a dummy hidden field otherwise
		// the list will not be reseted because the parameter
		// is missing in the http request
		var hidden = this.createHidden('');
		span.appendChild(hidden);
	}
}
function SwapSelect_createHidden(value) {
	var input   = document.createElement('input');
	input.type  = 'hidden';
	input.name  = this.hiddenName;
	input.value = value;
	return input;
}
function SwapSelect_toString() {
	var out = '';
	out += '******* SwapSelect *********' + LF
	out += 'Id..............: ' + this.id + LF;
	out += 'Orientation.....: ' + this.orientation + LF;
	out += 'Keep SortOrder..: ' + this.keepSortOrder + LF;
	out += 'Keep Selection..: ' + this.keepSelection + LF;
	out += 'Disabled........: ' + this.disabled + LF;
	out += 'RunAt...........: ' + this.runAt + LF;
	return out;
}
new SwapSelect();
SwapSelect.prototype.setRunAt           = SwapSelect_setRunAt;
SwapSelect.prototype.getRunAt           = SwapSelect_getRunAt;
SwapSelect.prototype.setOrientation     = SwapSelect_setOrientation;
SwapSelect.prototype.getOrientation     = SwapSelect_getOrientation;
SwapSelect.prototype.setDisabled        = SwapSelect_setDisabled;
SwapSelect.prototype.isDisabled         = SwapSelect_isDisabled;
SwapSelect.prototype.setupEventHandler  = SwapSelect_setupEventHandler;
SwapSelect.prototype.unselectOptionList = SwapSelect_unselectOptionList;
SwapSelect.prototype.doMove             = SwapSelect_doMove;
SwapSelect.prototype.sort               = SwapSelect_sort;
SwapSelect.prototype.getOptionList      = SwapSelect_getOptionList;
SwapSelect.prototype.setKeepSortOrder   = SwapSelect_setKeepSortOrder;
SwapSelect.prototype.isKeepSortOrder    = SwapSelect_isKeepSortOrder;
SwapSelect.prototype.setKeepSelection   = SwapSelect_setKeepSelection;
SwapSelect.prototype.isKeepSelection    = SwapSelect_isKeepSelection;
SwapSelect.prototype.updateHiddenFields = SwapSelect_updateHiddenFields;
SwapSelect.prototype.createHidden       = SwapSelect_createHidden;
SwapSelect.prototype.toString           = SwapSelect_toString;


/*
+ ---------------------------------------------------------------------------------+
| Object...: StringHelp
| Function.: 
|
| Date        Author            Description
| ----------  ----------------  ----------------------------------------------------
| 08.01.2005  G.Schulz          Inital version
|
+ ---------------------------------------------------------------------------------+
*/

function StringHelp() {
}
function StringHelp_truncate(str, maxlength) {
	if ((maxlength == -1) || (str == null) || (str.length <= maxlength)) {
		return str;
	} else {
		return str.substring(0, maxlength) + '...';
	}
}
new StringHelp();
StringHelp.truncate = StringHelp_truncate;


