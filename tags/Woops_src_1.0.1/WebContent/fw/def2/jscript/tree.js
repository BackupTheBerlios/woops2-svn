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

/***********************************************************************
 * Name:
 *        tree.js
 *
 * Function:
 *        Provide a dynamic tree in a web browser.
 *
 * Author:
 *        Gernot Schulz (gschulz@scc-gmbh.com)
 *
 * Status:
 *        Version 1, Release 1
 *
 * Environment:
 *        This is a PLATFORM-INDEPENDENT source file. As such it may
 *        contain no dependencies on any specific operating system
 *        environment or hardware platform.
 *
 * Description:
 *        
 *
 * Dependencies:
 *        resourcemap.js
 *        environment.js
 *        common.js
 *
 * TESTED ON:  - InternetExplorer
 *             - Netscape Navigator
 *             - Mozilla
 *             - Firefox
 *             - Safari
 *
 ***********************************************************************/


/*
+ ---------------------------------------------------------------------------------+
| Object.....: Tree()
| Function...: 
|
| date        author            description
| ----------  ----------------  ----------------------------------------------------
| 04.03.2003  G.Schulz (SCC)    Erstversion
|
+ ---------------------------------------------------------------------------------+
*/
function Tree(id, formElement) {
	this.id            = id;                                           // identifier for the tree
	this.root          = null;                                         // the root element of the tree (an object of type treegroup)
	this.runAt         = RunAt.SERVER;
	this.linesAtRoot   = true;                                         // Show lines at the top level
	this.expandMode    = ExpandMode.SINGLE;
	this.showLines     = true;
	this.showRoot      = true;                                          // false, if the root element should be hidden
	this.action        = '';                                            // action to process if an item is clicked
	this.checkboxes    = false;                                         // true if checkboxes should be displayed
	this.imagemap      = null;                                          // imagemap used for tree icons
	this.formElement   = (arguments.length >= 2) ? formElement : false; // indicates if the TabSet should act as a form element.
	this.disabled      = false;                                         // control is disabled
	this.maxlength     = -1;                                            // Maxlength of the labels
	this.groupSelect   = true;                                          // Drilldow on a group allowd
	this.styleClass    = null;                                          // Customized sytlesheet class
	this.imageMap      = null;                                          // an optional associated imagemap for image resources
	this.selectNode    = true;                                          // highlight the node if it was selected on a drilldown
}
function Tree_getId() {
	return this.id;
}
function Tree_setLinesAtRoot(flag) {
	this.linesAtRoot = flag;
}
function Tree_setExpandMode(expandMode) {
	this.expandMode = expandMode;
}
function Tree_getExpandMode() {
	return this.expandMode;
}
function Tree_setCheckboxes(checkboxes) {
	this.checkboxes = checkboxes;
}
function Tree_setShowRoot(showRoot) {
	this.showRoot = showRoot;
}
function Tree_setRunAt(runat) {
	this.runAt = runat;
}
function Tree_getRunAt() {
	return this.runAt;
}
function Tree_setRoot(root) {
	if ((root instanceof TreeGroup) || (root instanceof TreeNode)) {
		this.root = root;
	}
}
function Tree_getRoot(root) {
	return this.root;
}
function Tree_setAction(action) {
	this.action = action;
}
function Tree_getAction() {
	return this.action;
}
function Tree_setImageMap(imagemap) {
	if (imagemap instanceof ImageMap) {
		this.imagemap = imagemap;
	}
}
function Tree_isFormElement() {
	return this.formElement;
}
function Tree_setFormElement(formElement) {
	this.formElement = formElement;
}
function Tree_isDisabled() {
	return this.disabled;
}
function Tree_setDisabled(disabled) {
	this.disabled = disabled;
}
function Tree_getMaxlength() {
	this.maxlength;
}
function Tree_setMaxlength(maxlength) {
	this.maxlength = maxlength;
}
function Tree_isGroupSelect() {
	return this.groupSelect;
}
function Tree_setGroupSelect(groupSelect) {
	this.groupSelect = groupSelect;
}
function Tree_isShowLines() {
	return this.showLines;
}
function Tree_setShowLines(showLines){
	this.showLines = showLines;
}
function Tree_setLocal(local) {
	//this.local = local;
}
function Tree_setStyleClass(styleClass) {
	this.styleClass = styleClass;
}
function Tree_getStyleClass() {
	return this.styleClass;
}
function Tree_setImageMap(imageMap) {
	this.imageMap = imageMap;
}
function Tree_getImageMap() {
	return this.imageMap;
}
function Tree_setSelectNode(selectNode) {
	this.selectNode = selectNode;
}
function Tree_isSelectNode() {
	this.selectNode;
}
function Tree_toString() {
	var out = '';
	out += '********* Tree ***********' + LF
	out += 'Id.............: ' + this.id + LF;
	out += 'ExpandMode.....: ' + this.expandMode + LF;
	out += 'LinesAtRoot....: ' + this.linesAtRoot + LF;
	out += 'Show Root......: ' + this.showRoot + LF;
	out += 'Show Lines.....: ' + this.showLines + LF;
	out += 'GroupSelect....: ' + this.groupSelect + LF;
	out += 'Action.........: ' + this.action + LF;
	out += 'Checkboxes.....: ' + this.checkboxes + LF;
	out += 'FormElement....: ' + this.formElement + LF;
	out += 'Disabled.......: ' + this.disabled + LF;
	out += 'StyleClass.....: ' + this.styleClass + LF;
	out += 'Maxlength......: ' + this.maxlength + LF;
	return out;
}
new Tree();
Tree.prototype.getId             = Tree_getId;
Tree.prototype.setLinesAtRoot    = Tree_setLinesAtRoot;
Tree.prototype.setExpandMode     = Tree_setExpandMode;
Tree.prototype.getExpandMode     = Tree_getExpandMode;
Tree.prototype.setRoot           = Tree_setRoot;
Tree.prototype.getRoot           = Tree_getRoot;
Tree.prototype.setAction         = Tree_setAction;
Tree.prototype.getAction         = Tree_getAction;
Tree.prototype.setImageMap       = Tree_setImageMap;
Tree.prototype.isFormElement     = Tree_isFormElement;
Tree.prototype.setFormElement    = Tree_setFormElement;
Tree.prototype.setCheckboxes     = Tree_setCheckboxes;
Tree.prototype.setShowRoot       = Tree_setShowRoot;
Tree.prototype.setRunAt          = Tree_setRunAt;
Tree.prototype.getRunAt          = Tree_getRunAt;
Tree.prototype.setDisabled       = Tree_setDisabled;
Tree.prototype.isGroupSelect     = Tree_isGroupSelect;
Tree.prototype.setGroupSelect    = Tree_setGroupSelect;
Tree.prototype.isShowLines       = Tree_isShowLines;
Tree.prototype.setShowLines      = Tree_setShowLines;
Tree.prototype.setLocal          = Tree_setLocal;
Tree.prototype.getMaxlength      = Tree_getMaxlength;
Tree.prototype.setMaxlength      = Tree_setMaxlength;
Tree.prototype.setStyleClass     = Tree_setStyleClass;
Tree.prototype.getStyleClass     = Tree_getStyleClass;
Tree.prototype.setImageMap       = Tree_setImageMap;
Tree.prototype.getImageMap       = Tree_getImageMap;
Tree.prototype.setSelectNode     = Tree_setSelectNode;
Tree.prototype.isSelectNode      = Tree_isSelectNode;
Tree.prototype.toString          = Tree_toString;



/*
+ ---------------------------------------------------------------------------------+
| Object.....: TreeGroup ()
| Function...: 
|
| Date        Author            Description
| ----------  ----------------  ----------------------------------------------------
| 04.03.2003  G.Schulz (SCC)    Inital Version
|
+ ---------------------------------------------------------------------------------+
*/
function TreeGroup(id, label, tooltip, nodeState, checkState, selected, exprEvalStr, target, enabled) {
	var a = arguments;
	
	this.childNodes     = new Array();                                          // Buffer for the child nodes
	this.clientHandler  = new Array();                                          // ClientHandler associated with this node
	
	// properties
	this.id            = id;                                                    // identifier for the tree node
	this.parent        = null;                                                  // the parent or root node
	this.label         = label;                                                 // label to display on the node
	this.tooltip       = (a.length >= 3) ? tooltip : '';                        // Tooltip
	this.nodeState     = (a.length >= 4) ? nodeState : NodeState.COLLAPSE;      // state of the node (expand, collapse, expandex)
	this.checkState    = (a.length >= 5) ? checkState : CheckState.UNCHECKED;   // indicates if the node is checked
	this.selected      = (a.length >= 6) ? selected : false;                    // true, if the node is selected
	this.exprEvalStr   = (a.length >= 7) ? exprEvalStr : null;                  // RegEx to match for the image
	this.target        = (a.length >= 8) ? target : null;                       // the target attribute
	this.enabled       = (a.length >= 9) ? enabled : true;                      // indicates if this node was enabled
	this.type          = NodeType.GROUP;                                        // indicates a tree group
}
function TreeGroup_getId() {
	return this.id;
}
function TreeGroup_getType() {
	return this.type;
}
function TreeGroup_getCheckState() {
	return this.checkState;
}
function TreeGroup_setCheckState(checkState) {
	this.checkState = checkState;
}
function TreeGroup_setSelected(selected) {
	this.selected = selected;
}
function TreeGroup_isSelected() {
	return this.selected;
}
function TreeGroup_isExpanded() {
	return (this.nodeState == NodeState.EXPAND);
}
function TreeGroup_isExpandedEx() {
	return (this.nodeState == NodeState.EXPANDEX);
}
function TreeGroup_isCollapse() {
	return (this.nodeState == NodeState.COLLAPSE);
}
function TreeGroup_setNodeState(nodeState) {
	this.nodeState =nodeState;
}
function TreeGroup_getNodeState() {
	return this.nodeState;
}
function TreeGroup_appendChild(child) {
	this.childNodes[this.childNodes.length] = child;
	child.setParent(this);
	return this;
}
function TreeGroup_getChild(index) {
	return this.childNodes[index];
}
function TreeGroup_getChildNodes() {
	return this.childNodes;
}
function TreeGroup_hasChildNodes() {
	return this.childNodes.length > 0;
}
function TreeGroup_getParent() {
	return this.parent;
}
function TreeGroup_setParent(parent) {
	if (parent instanceof TreeGroup) {
		this.parent = parent;
	}
}
function TreeGroup_hasParent() {
	return (null != this.parent);
}
function TreeGroup_isLast() {
	if (null != this.parent) {
		var childs = this.parent.getChildNodes();
		return childs[childs.length-1]['id'] == this.id;
	} else {
		// it's the root element
		return true;
	}
}
function TreeGroup_getCheckedChildNodes() {
	var arr = new Array();
	
	for (var i=0; i < this.childNodes.length; i++) {
		if (this.childNodes[i]['checkState'] == CheckState.CHECKED || this.childNodes[i]['checkState'] == CheckState.UNDEFINED) {
			arr[arr.length] = this.childNodes[i];
		}
	}
	return arr;
}
function TreeGroup_addHandler(handler, script) {
	this.clientHandler[' + handler + '] = script;
	return this;
}
function TreeGroup_getHandler(handler) {
	return this.clientHandler[' + handler + '];
}
function TreeGroup_toString() {
	var out = '';
	out += '********* TreeGroup ***********' + LF
	out += 'Id...........: ' + this.id + LF;
	out += 'Label........: ' + this.label + LF;
	out += 'Tooltip......: ' + this.tooltip + LF;
	out += 'NodeState....: ' + this.nodeState + LF;
	out += 'Selected.....: ' + this.selected + LF;
	out += 'CheckState...: ' + this.checkState + LF;
	out += 'Children.....: ' + this.childNodes.length + LF;
	out += 'Type.........: ' + this.type + LF;
	out += 'ExprEvalStr..: ' + this.exprEvalStr + LF;
	out += 'Target.......: ' + this.target + LF;
	return out;
}
new TreeGroup();
TreeGroup.prototype.getId                 = TreeGroup_getId;
TreeGroup.prototype.getType               = TreeGroup_getType;
TreeGroup.prototype.getCheckState         = TreeGroup_getCheckState;
TreeGroup.prototype.setCheckState         = TreeGroup_setCheckState;
TreeGroup.prototype.setSelected           = TreeGroup_setSelected;
TreeGroup.prototype.isSelected            = TreeGroup_isSelected;
TreeGroup.prototype.isExpanded            = TreeGroup_isExpanded;
TreeGroup.prototype.isExpandedEx          = TreeGroup_isExpandedEx;
TreeGroup.prototype.isCollapse            = TreeGroup_isCollapse;
TreeGroup.prototype.setNodeState          = TreeGroup_setNodeState;
TreeGroup.prototype.getNodeState          = TreeGroup_getNodeState;
TreeGroup.prototype.appendChild           = TreeGroup_appendChild;
TreeGroup.prototype.getChild              = TreeGroup_getChild;
TreeGroup.prototype.hasChildNodes         = TreeGroup_hasChildNodes;
TreeGroup.prototype.getChildNodes         = TreeGroup_getChildNodes;
TreeGroup.prototype.getCheckedChildNodes  = TreeGroup_getCheckedChildNodes;
TreeGroup.prototype.setParent             = TreeGroup_setParent;
TreeGroup.prototype.getParent             = TreeGroup_getParent;
TreeGroup.prototype.hasParent             = TreeGroup_hasParent;
TreeGroup.prototype.isLast                = TreeGroup_isLast;
TreeGroup.prototype.addHandler            = TreeGroup_addHandler;
TreeGroup.prototype.getHandler            = TreeGroup_getHandler;
TreeGroup.prototype.toString              = TreeGroup_toString;



/*
+ ---------------------------------------------------------------------------------+
| Object.....: TreeNode()
| Function...: 
|
| Date        Author            Description
| ----------  ----------------  ----------------------------------------------------
| 04.03.2003  G.Schulz (SCC)    Inital Version
|
+ ---------------------------------------------------------------------------------+
*/
function TreeNode(id, label, tooltip, checkState, selected, exprEvalStr, target, enabled) {
	var a = arguments;
	
	this.clientHandler  = new Array();                                          // ClientHandler associated with this node
	
	this.id            = id;                                                    // identifier for the tree node
	this.label         = label;                                                 // label to display on the node
	this.parent        = null;                                                  // the parent -> a tree group
	this.tooltip       = (a.length >= 3) ? tooltip : '';                        // Tooltip
	this.checkState    = (a.length >= 4) ? checkState : CheckState.UNCHECKED;   // indicates if the node is checked
	this.selected      = (a.length >= 5) ? selected : false;                    // true, if this is the selected node
	this.exprEvalStr   = (a.length >= 6) ? exprEvalStr : null;                  // RegEx to match for the image
	this.target        = (a.length >= 7) ? target : null;                       // The target attribute
	this.enabled       = (a.length >= 8) ? enabled : true;                      // indicates if this node was enabled
	this.type          = NodeType.NODE;                                         // indicates a tree node
}
function TreeNode_getId() {
	return this.id;
}
function TreeNode_getType() {
	return this.type;
}
function TreeNode_getLabel() {
	return this.label;
}
function TreeNode_setSelected(selected) {
	this.selected = selected;
}
function TreeNode_isSelected() {
	return this.selected;
}
function TreeNode_getCheckState() {
	return this.checkState;
}
function TreeNode_setCheckState(flag) {
	this.checkState = flag;
}
function TreeNode_isChecked() {
	return this.checked;
}
function TreeNode_getParent() {
	return this.parent;
}
function TreeNode_setParent(parent) {
	if (parent instanceof TreeGroup) {
		this.parent = parent;
	}
}
function TreeNode_isFist() {
	var childs = this.parent.getChildNodes();
	return childs[0]['id'] == this.id;
}
function TreeNode_isLast() {
	var childs = this.parent.getChildNodes();
	return childs[childs.length-1]['id'] == this.id;
}
function TreeNode_addHandler(handler, script) {
	this.clientHandler[' + handler + '] = script;
	return this;
}
function TreeNode_getHandler(handler) {
	return this.clientHandler[' + handler + '];
}
function TreeNode_toString() {
	var out = '';
	out += '********* TreeNode ***********' + LF
	out += 'Id...........: ' + this.id + LF;
	out += 'Label........: ' + this.label + LF;
	out += 'Tooltip  ....: ' + this.tooltip + LF;
	out += 'CheckSate....: ' + this.checkState + LF;
	out += 'Selected.....: ' + this.selected + LF;
	out += 'Type.........: ' + this.type + LF;
	out += 'ExprEvalStr..: ' + this.exprEvalStr + LF;
	out += 'Target.......: ' + this.target + LF;
	return out;
}
new TreeNode();
TreeNode.prototype.getId            = TreeNode_getId;
TreeNode.prototype.getType          = TreeNode_getType;
TreeNode.prototype.getLabel         = TreeNode_getLabel;
TreeNode.prototype.setSelected      = TreeNode_setSelected;
TreeNode.prototype.isSelected       = TreeNode_isSelected;
TreeNode.prototype.getCheckState    = TreeNode_getCheckState;
TreeNode.prototype.setCheckState    = TreeNode_setCheckState;
TreeNode.prototype.setParent        = TreeNode_setParent;
TreeNode.prototype.getParent        = TreeNode_getParent;
TreeNode.prototype.isFist           = TreeNode_isFist;
TreeNode.prototype.isLast           = TreeNode_isLast;
TreeNode.prototype.addHandler       = TreeNode_addHandler;
TreeNode.prototype.getHandler       = TreeNode_getHandler;
TreeNode.prototype.toString         = TreeNode_toString;




/*
+ ---------------------------------------------------------------------------------+
| Object.....: TreeHelp()
| Function...: Provides some helper and utility functions to manage the tree
|
| Date        Author            Description
| ----------  ----------------  ----------------------------------------------------
| 04.03.2003  G.Schulz (SCC)    Inital Version
|
+ ---------------------------------------------------------------------------------+
*/
function TreeHelp() {
}
function TreeHelp_createOutline(obj, indent_amount) {
	var indent_amount = (arguments.length == 2) ? indent_amount : 4;
	var root = null;
	
	// check argument
	if (obj instanceof Tree) {
		root = obj.getRoot();
	} else if ((obj instanceof TreeGroup) || (obj instanceof TreeNode)) {
		root = obj;
	} else {
		return 'Error: Argument not of Type Tree, TreeGroup or TreeNode';
	}

	// return outline
	return this.insertIndent(root, indent_amount, 0);
}
function TreeHelp_insertIndent(node, indent_amount, level) {

	if (null == node) return;

	out = StringHelp.filler(level++ * indent_amount, ' ') + node.getId() + LF;

	// check child nodes
	if (node instanceof TreeGroup && node.hasChildNodes()) {
		var childNodes = node.getChildNodes();

		for (var i = 0; i < childNodes.length; i++) {
			out += this.insertIndent(childNodes[i], indent_amount, level);
		}
	}
	
	return out;
}
function TreeHelp_getNodeById(nodelist, id) {
	
	if (null == nodelist) {
		return null;
	}
	
	// create iterator
	var ni = new NodeIterator(nodelist);

	for (var e = ni.nextNode(); e != null; e = ni.nextNode()) {
		if (ni.getId().toLowerCase() == id.toLowerCase()) {
			return e;
		}
	}
}
function TreeHelp_createNodeArray(nodelist, nodefilter) {
	var arr = new Array();

	if (null != nodelist) {
		this.serializeNodes(arr, nodelist, nodefilter);
	}
	
	return arr;
}
function TreeHelp_serializeNodes(arr, node, nodefilter) {

	if (null == node) return;

	if (null == nodefilter) {
		arr[arr.length] = node;
	} else if (nodefilter == NodeFilter.GROUP && node.getType() == NodeType.GROUP) {
		arr[arr.length] = node;
	} else if (nodefilter == NodeFilter.NODE && node.getType() == NodeType.NODE) {
		arr[arr.length] = node;
	}

	// check child nodes
	if (node instanceof TreeGroup && node.hasChildNodes()) {
		var childNodes = node.getChildNodes();

		for (var i = 0; i < childNodes.length; i++) {
			this.serializeNodes(arr, childNodes[i], nodefilter);
		}
	}
}
function TreeHelp_checkNode(node, checkState) {

	// check node
	node['checkState'] = checkState;

	// set check state for all childes
	if (node instanceof TreeGroup) {
		
		var nodes = TreeHelp.createNodeArray(node);
		
		for (var i=0; i < nodes.length; i++) {
			nodes[i]['checkState'] = checkState;
		}
	}
	
	// update check state for parent (groups)
	while (node != null) {
		var parent = node.getParent();
		
		if (null == parent) break;
		
		var checkedNodes = parent.getCheckedChildNodes();
		var nodes = parent.getChildNodes();
		
		if (checkedNodes.length == 0) {
			parent['checkState'] = CheckState.UNCHECKED;
		} else if (checkedNodes.length == nodes.length) {
			parent['checkState'] = CheckState.CHECKED;
		} else {
			parent['checkState'] = CheckState.UNDEFINED;
		}
		
		// next parent node
		node = parent;
	}
}
function TreeHelp_collapseAllNodes(node) {
	this.changeNodeState(node, NodeState.COLLAPSE);
}
function TreeHelp_expandAllNodes(node) {
	this.changeNodeState(node, NodeState.EXPAND);
}
function TreeHelp_changeNodeState(node, nodeState) {

	if (node instanceof TreeGroup) {
		var nodes = TreeHelp.createNodeArray(node, NodeFilter.GROUP);
		
		for (var i=0; i < nodes.length; i++) {
			// only change the state for collapsed or expanded nodes
			if (nodes[i].getNodeState() != NodeState.EXPANDEX) {
				nodes[i].setNodeState(nodeState);
			}
		}
	}
}
function TreeHelp_getAncestorNodes(arr, node) {
	
	if (node.hasParent()) {
		var parent = node.getParent();
		arr[arr.length] = parent;
		this.getAncestorNodes(arr, parent);
	}
}
function TreeHelp_unSelect(node) {
	var nodes = TreeHelp.createNodeArray(node);
		
	for (var i=0; i < nodes.length; i++) {
		nodes[i].setSelected(false);
	}
}
new TreeHelp();
TreeHelp.createOutline          = TreeHelp_createOutline;
TreeHelp.getNodeById            = TreeHelp_getNodeById;
TreeHelp.createNodeArray        = TreeHelp_createNodeArray;
TreeHelp.serializeNodes         = TreeHelp_serializeNodes;
TreeHelp.insertIndent           = TreeHelp_insertIndent;
TreeHelp.checkNode              = TreeHelp_checkNode;
TreeHelp.collapseAllNodes       = TreeHelp_collapseAllNodes;
TreeHelp.expandAllNodes         = TreeHelp_expandAllNodes;
TreeHelp.changeNodeState        = TreeHelp_changeNodeState;
TreeHelp.getAncestorNodes       = TreeHelp_getAncestorNodes;
TreeHelp.unSelect               = TreeHelp_unSelect;



/*
+ ---------------------------------------------------------------------------------+
| Object.....: NodeIterator()
| Function...:
| Usage......:  var ni = new NodeIterator(root);
|               for (var e = ni.nextNode(); e != null; e = ni.nextNode()) {
|                  // do something with the node
|               }
|
| date        author            description
| ----------  ----------------  ----------------------------------------------------
| 04.03.2003  G.Schulz (SCC)    Inital Version
|
+ ---------------------------------------------------------------------------------+
*/
function NodeIterator(root, nodefilter) {
	this.nodes = new Array();
	this.pos = 0;
	this.nodefilter = (arguments.length == 2) ? nodefilter : null;
	
	// Constructor
	if (null != root) {
		this.nodes = TreeHelp.createNodeArray(root, this.nodefilter);
	}
}
function NodeIterator_nextNode() {
	if (this.pos < this.nodes.length) {
		return this.nodes[this.pos++];
	} else {
		return null;
	}
}
function NodeIterator_previousNode() {
	if (this.pos >= 0) {
		return this.nodes[this.pos--];
	} else {
		return null;
	}
}
function NodeIterator_reset() {
	this.pos = 0;
}
function NodeIterator_first() {
	this.pos = 0;
	return this.nodes[this.pos];
}
function NodeIterator_last() {
	this.pos = this.nodes.length;
	return this.nodes[this.pos];
}

new NodeIterator();
NodeIterator.prototype.nextNode         = NodeIterator_nextNode;
NodeIterator.prototype.previousNode     = NodeIterator_previousNode;
NodeIterator.prototype.reset            = NodeIterator_reset;
NodeIterator.prototype.first            = NodeIterator_first;
NodeIterator.prototype.last             = NodeIterator_last;



/*
+ ---------------------------------------------------------------------------------+
| Object.....: TreePainterData()
| Function...: Collection for the resources used to render the tree
|
| date        author            description
| ----------  ----------------  ----------------------------------------------------
| 04.03.2003  G.Schulz (SCC)    Inital Version
|
+ ---------------------------------------------------------------------------------+
*/
function TreePainterData(tree, contextPath, arr_imageRes) {
	this.tree                  = tree;                                 // The tree data
	this.contextPath           = contextPath;                          // Needed for SSL problem IE
	this.arr_imageRes          = arr_imageRes;	                       // array including all the images to paint the tabset
	this.DIV_PREVIX            = 'tree_';                              // Prefix for DIV-Tag which embedds the tree

	// StyleSheet
	this.CSS_TABLE	           = 'tc';                 // TreeControl
	this.CSS_ROW_EVEN          = 'tleven';             // odd row
	this.CSS_ROW_ODD           = 'tlodd';              // even row
	this.CSS_TOL               = 'tol';                // outline
	this.CSS_TI                = 'ti'                  // tree item
	this.CSS_TIS               = 'tis'                 // tree item selected


	if (null != tree) {
		this.IMG_ICON_CHECKED      = arr_imageRes[CHECKBOX_INVALID];
		this.IMG_ICON_CHECKED0     = arr_imageRes[CHECKBOX_UNCHECKED];
		this.IMG_ICON_CHECKED1     = arr_imageRes[CHECKBOX_CHECKED];
		this.IMG_ICON_CHECKED2     = arr_imageRes[CHECKBOX_INDETERMINATE];

		this.IMG_FOLDER_OPEN       = arr_imageRes[TREE_FOLDEROPEN];
		this.IMG_FOLDER_CLOSED     = arr_imageRes[TREE_FOLDERCLOSED];
		this.IMG_NODE              = arr_imageRes[TREE_ITEM];
			
		this.LINE0                 = arr_imageRes[TREE_STRUCTURE];
		this.LINE10                = arr_imageRes[TREE_STRUCTURE_10];
		this.LINE12                = arr_imageRes[TREE_STRUCTURE_12];
		this.LINE14                = arr_imageRes[TREE_STRUCTURE_14];
		this.LINE16                = arr_imageRes[TREE_STRUCTURE_16];
		this.LINE18                = arr_imageRes[TREE_STRUCTURE_18];
		this.LINE2                 = arr_imageRes[TREE_STRUCTURE_2];
		this.LINE26                = arr_imageRes[TREE_STRUCTURE_26];
		this.LINE30                = arr_imageRes[TREE_STRUCTURE_30];
		this.LINE32                = arr_imageRes[TREE_STRUCTURE_32];
		this.LINE34                = arr_imageRes[TREE_STRUCTURE_34];
		this.LINE42                = arr_imageRes[TREE_STRUCTURE_42];
		this.LINE46                = arr_imageRes[TREE_STRUCTURE_46];
	}
}
new TreePainterData();

/*
+ ---------------------------------------------------------------------------------+
| Object.....: TreePainter()
| Function...: 
|
| date        author            description
| ----------  ----------------  ----------------------------------------------------
| 04.03.2003  G.Schulz (SCC)    Erstversion
|
+ ---------------------------------------------------------------------------------+
*/
function TreePainter() {

}
function TreePainter_render(tpData) {
	var tree     = tpData.tree;
	
	// create Root
	var doc = document.createElement('SPAN');
	
	//create Table
	var table = document.createElement('Table');
	table.cellSpacing = 0;
	table.cellPadding = 0;
	table.border = 1;
	
	if (null != tree.getStyleClass() &&  '' != tree.getStyleClass()) {
		table.className = tree.getStyleClass();
	} else {
		table.className = tpData.CSS_TABLE;
	}

	doc.appendChild(table);

	// create tree
	this.createTreeElements(table, tree.getRoot(), tpData);
	
	// get the Span-Element from the form and ...
	var span = this.getTreeNode(tpData.DIV_PREVIX + tree.getId());

	// ... replace the tree
	if (span.hasChildNodes()) {
		span.innerHTML = '';
		span.appendChild(table);
	} else {
		span.appendChild(table);
	}
}
function TreePainter_createTreeElements(table, node, tpData, index) {

	if (null == node) {
		return;
	}

	if (null == index) index = 0;

	// check if the root node should be displayed
	if (null != node.getParent() || tpData['tree']['showRoot']) {
		this.renderTreeElement(table, node, tpData, index++);
	}
		
	if (node instanceof TreeGroup) {
		// proccess child nodes
		if (node.hasChildNodes() && node.isExpanded()) {
			var childNodes = node.getChildNodes();
	
			for (var i = 0; i < childNodes.length; i++) {
				index = this.createTreeElements(table, childNodes[i], tpData, index++);
			}
		}
	}
	
	return index;
}
function TreePainter_renderTreeElement(parent, node, tpData, index) {
	var row  = null;
	var cell = null;

	// Container for all elements / rows
	var elementContainer = new Array();

	// create the lines in front of the nodes
	this.doCreateLine(elementContainer, node, tpData);
	elementContainer.reverse();	
	
	// create labels
	this.doCreateLabelLink(elementContainer, node, tpData, index);

	// insert cell 
	if (safari) {
		row = parent.insertRow(-1);
	} else {
		row = parent.insertRow(parent.rows.length);
	}
	row.className = ((index % 2) == 1) ? tpData.CSS_ROW_EVEN : tpData.CSS_ROW_ODD;

	cell = row.insertCell(row.cells.length);

	// Create table element for this row
	var table = document.createElement('Table');
	table.cellSpacing = 0;
	table.cellPadding = 0;
	table.border = 0;
	table.width  = '100%';
	table.height = '100%';
	table.className = tpData.CSS_TOL;
	cell.appendChild(table);

	row = table.insertRow(table.rows.length);
	row.vAlign = 'top';
	cell = row.insertCell(row.cells.length);

	// append elements to the current row
	for (var i=0; i < elementContainer.length; i++) {
		row.appendChild(elementContainer[i]);
	}
}
// 0x20 = Plus Symbol in front of an item
// 0x10 = Minus symbol in front of an item
// 0x08 = Connector to the nord
// 0x04 = Connector to the south
// 0x02 = Connector to the east
// 0x01 = Connector to the west
function TreePainter_doCreateLine(elementContainer, node, tpData) {
	var showLines       = tpData['tree']['showLines'];
	var showLinesAtRoot = tpData['tree']['linesAtRoot'];
	var showRoot        = tpData['tree']['showRoot'];
	var expandMode      = tpData['tree']['expandMode'];
	var showCheckBoxes  = tpData['tree']['checkboxes'];
	var nodeState       = NodeState.COLLAPSE;
	var showButtons     = true;
	var checkState      = -1;
	var selected        = node.isSelected();
	var key             = node.getId();
	var children        = 0;
	

	var img = (showLines == true) ? 0x02 : 0x00;
	var childLevel = true;
	var rootLevel = false;
	var isGroup = (node instanceof TreeGroup);
	
	if (isGroup) {
		nodeState = node.getNodeState();
		
		if (nodeState == NodeState.EXPANDEX) {
			children = -1;
		} else {
			children = node.getChildNodes().length;
		}
	}
		
	// Die Icons zum auf und Zuklappen der Aeste werden nur dann angezeigt,
	// wenn der Baum nicht ohnehin vollstaendig aufgeklappt ist.
	var showButtons = (showButtons && expandMode != ExpandMode.FULL);
	
	while (node != null) {
		var parent = node.getParent();

		if (null == parent && !showRoot) {
			break;
		}
		
		// Wir befinden uns auf der obersten Anzeigeebene das Baumes,
		// wenn der aktuelle Knoten die Baumwurzel ist, oder wenn
		// bei nicht angezeigter Wurzel der uebergeordnete Knoten die
		// Wurzel ist.
		rootLevel = (parent == null) || (!showRoot && (parent.getParent() == null));

		// Wenn auf der Wurzelebene Keine Verbindungslinien gezeichnet
		// werden soll, dann kann an dieser Stelle abgebrochen werden
		if (rootLevel && !showLinesAtRoot) {
			break;
		}
		
		if (!rootLevel && showLines) {
			// Eine Verbindungslinie zum Parent nach Norden zeichnen
			if (childLevel) {
				img |= 0x08;
			}

			if (!node.isLast()) {
				// Es gibt eine Verbindungslinie zu weiteren Soehnen
				// in Richtung Sueden
				img |= 0x08 | 0x04;
			}
		}

		// Der folgende Block wird NUR fuer den and die Prozedur
		// uebergebenen Knoten ausgewerter => childLevel == true
		if (childLevel && showButtons) {
			if (-1 == children) {
				// Die Anzahl der Kinder steht noch nicht fest.
				// Es wird deshalb ein (+) Symbol zum aufklappen angeboten
				img |= 0x20;
			} else if (children > 0) {
				// Der Knoten hat Kinder. Es entscheidet nun der
				// Expansionsstatus ueber das angezeigte Image.
				if (nodeState == NodeState.EXPAND) {
					// Symbol zum Schliessen vorsehen
					img |= 0x10;
				} else {
					// Symbol zum oeffnen vorsehen
					img |= 0x20;
				}
			}
		}

		if (!childLevel) {
			img &= 0x0C;
		}

		if ((img & 0x20) > 0) {
			// Es wird ein (+) Zeichen angezeigt.
			// Es muss also ein Hyperlink zum oeffnen des Knoten eingefuegt werden.
			// Wenn die Kinder des Knotens zu diesem Zeitpunkt bereits geladen sind,
			// dann kann dieses von der JSP-Seite selbst gehandelt werden. Im
			// anderen Fall muss das Controller-Servlet aufgefordert werden.
			if (-1 == children) {
				this.doCreateIcon(elementContainer, node, NodeState.EXPANDEX, tpData);
				if (showCheckBoxes) this.doCreateCheckBox(elementContainer, node, tpData);
				this.doCreateImageLink(elementContainer, node, tpData, img, NodeState.EXPANDEX);
			} else {
				this.doCreateIcon(elementContainer, node, NodeState.EXPAND, tpData);
				if (showCheckBoxes) this.doCreateCheckBox(elementContainer, node, tpData);
				this.doCreateImageLink(elementContainer, node, tpData, img, NodeState.EXPAND);
			}
			
		} else if ((img & 0x10) > 0) {
			// Es wird ein (-) Zeichen angezeigt.
			// Es muss also ein Hyperlink zum schliessen des Knoten eingefuegt werden.
			this.doCreateIcon(elementContainer, node, NodeState.COLLAPSE, tpData);
			if (showCheckBoxes) this.doCreateCheckBox(elementContainer, node, tpData);
			this.doCreateImageLink(elementContainer, node, tpData, img, NodeState.COLLAPSE);
		} else {
			// Pruefen ob ein Image fuer das Blatt gezeichnet werden soll
			if (childLevel) {
				this.doCreateIcon(elementContainer, node, NodeState.NONE, tpData);
				if (showCheckBoxes) this.doCreateCheckBox(elementContainer, node, tpData);
			}

			// Es handelt sich um eine einfache Verbindungslinie welche ueber keinen Link verfuegt
			this.doCreateImage(elementContainer, node, tpData, img);
		}

		node = parent;
		img = 0;
		childLevel = false;
	}
}
function TreePainter_doCreateImage(arr, node, tpData, image) {
	var resource = 'LINE' + image;
	var imgRes = tpData[resource];

	var img = document.createElement('IMG');
	img.src    = imgRes.src;
	img.border = 0;
	img.width  = imgRes.width;
	img.height = imgRes.height;

	var td = document.createElement('TD');
	if ((image & 0x0c) == 0x0c) {
		td.setAttribute('background', tpData.LINE12.src);
	}
	td.appendChild(img);
	
	arr[arr.length] = td;
}
function TreePainter_doCreateIcon(arr, node, nodestate, tpData) {
	var expandMode = tpData['tree']['expandMode'];
	var src        = null;

	// image to create
	var img = document.createElement('IMG');
	img.border = 0;
	
	// was an imagemap specified or use default images ?
	var imageMap = tpData['tree']['imageMap'];

	if (null == imageMap) {
		var imgRes = null;

		// use default images
		if (nodestate == NodeState.EXPAND || nodestate == NodeState.EXPANDEX) {
			imgRes = tpData.IMG_FOLDER_CLOSED;
		} else if (nodestate == NodeState.COLLAPSE || expandMode == ExpandMode.FULL) {
			imgRes = tpData.IMG_FOLDER_OPEN;
		} else {
			imgRes = tpData.IMG_NODE;
		}

		img.src    = imgRes.src;
		img.width  = imgRes.width;
		img.height = imgRes.height;
		
	} else {
		// browse the imagemap for the matching image
		var imageMappings = imageMap.getImageMappings();
		var exprEvalStr   = node['exprEvalStr'];
		var baseDir       = imageMap['base'];
		
		if (null != exprEvalStr) {
			// append '.open' or '.closed' for groups
			if (nodestate == NodeState.EXPAND || nodestate == NodeState.EXPANDEX) {
					exprEvalStr = exprEvalStr + '.closed';
			} else if (nodestate == NodeState.COLLAPSE || expandMode == ExpandMode.FULL) {
				exprEvalStr = exprEvalStr + '.open';
			}
			
			// now check the map 
			for (var i=0; i < imageMappings.length; i++) {
				var mapping = imageMappings[i];
				var rule    = mapping['rule'];
				
				var regexp = new RegExp(rule);
				var match  = regexp.exec(exprEvalStr);
				
				// set the image attributes
				if (null != match) {
					img.src    = baseDir + mapping.getSource();
					
					if (null != mapping.getWidth()) {
						img.width  = mapping.getWidth();
					}

					if (null != mapping.getHeight()) {
						img.height  = mapping.getHeight();
					}
					
					if (null != mapping.getTooltip()) {
						img.title  = mapping.getTooltip();
					}
					
					if (null != mapping.getAlt()) {
						img.alt  = mapping.getAlt();
					}
				}
			}
		}
	}
	
	var td = document.createElement('TD');
	td.appendChild(img);
	
	arr[arr.length] = td;
}
function TreePainter_doCreateImageLink(arr, node, tpData, image, nodestate) {
	var ctrlDisabled = tpData['tree']['disabled'];
	var nodeDisabled = node['disabled'];
	var resource     = 'LINE' + image;
	var imgRes       = tpData[resource];

	// create cell
	var td = document.createElement('TD');
	if ((image & 0x0c) == 0x0c) {
		td.setAttribute('background', tpData.LINE12.src);
	}
	
	// create image
	var img = document.createElement('IMG');
	img.src    = imgRes.src;
	img.border = 0;
	img.width  = imgRes.width;
	img.height = imgRes.height;
	
	// check if control or node was disabled
	if (!ctrlDisabled && !nodeDisabled) {
		// create anchor
		var a = document.createElement('A');
		a.id = node['id'];
		a.appendChild(img);
		a.href ='#';
		
		a.onclick = function() {
				var tree = tpData['tree'];

				if (node.getNodeState() == NodeState.EXPANDEX) {
				
					if (!tree.isFormElement()) {
						this.href =  tree['action'] + '.do?ctrl=' + tree['id'] + '&action=ExpandEx&param=' + node['id'];
					} else {
						// create and update hidden fiels and submit the form
						// todo
					}
				} else {
					// check if expandmode was set to single or multiple
					if (tree.getExpandMode() == ExpandMode.MULTIPLE) {
						// in this case only set the new state
						// for this node
						node['nodeState'] = (node['nodeState'] == NodeState.COLLAPSE) ? NodeState.EXPAND : NodeState.COLLAPSE;
					
					} else if (tree.getExpandMode() == ExpandMode.SINGLE) {
						// we must close all other groups where this node/group
						// does not belong to. So we have to check the parents
						var oldState = node['nodeState'];
						
						// 1te close all nodes
						TreeHelp.collapseAllNodes(tree.getRoot());
						
						// 2nd get and open the parent nodes
						var nodes = new Array();
						TreeHelp.getAncestorNodes(nodes, node);

						for (var i=0; i < nodes.length; i++) {
							nodes[i].setNodeState(NodeState.EXPAND);
						}
						
						// set new state for the current node
						node['nodeState'] = (oldState == NodeState.COLLAPSE) ? NodeState.EXPAND : NodeState.COLLAPSE;
					}					
					
					// render the tree again.
					TreePainter.render(tpData);
					return false;
				}
			}

		td.appendChild(a);
	} else {
		td.appendChild(img);
	}
	
	arr[arr.length] = td;
}
function TreePainter_doCreateLabelLink(arr, node, tpData, index) {
	var tree         = tpData['tree'];
	var ctrldisabled = tree['disabled'];
	var nodeDisabled = node['disabled'];	
	var groupSelect  = tree['groupSelect'];
	var selectNode   = tree['selectNode'];
	var maxlength    = tree['maxlength'];
	var target       = node['target'];

		
	var span  = document.createElement('span');
	if (maxlength == -1) {
		span.innerHTML = node['label']; //  + " " +index;
	} else {
		span.innerHTML = StringHelp.truncate(node['label'], maxlength);
	}
	
	var td = document.createElement('TD');
	var a  = document.createElement('A');
	a.id = node['id'];
	
	// only add a href if not disabled or groupSelect is allowed
	if (!ctrldisabled && !nodeDisabled && groupSelect) {
		a.href = tree['action'] + '.do?ctrl=' + tree['id'] + '&action=Drilldown&param=' + node['id'];
		
		// check if the target attribute must be set
		if (null != target && 'null' != target) {
			a.target = target;
		}

		a.onclick = function() {
				TreeHelp.unSelect(tree.getRoot());
				node.setSelected(true);
				TreePainter.resetSelectStyle(tpData);
				td.className = tpData.CSS_TIS;
				return true;
			}
	}
	
	a.appendChild(span);
	td.appendChild(a);

	if (selectNode && node.isSelected()) {
		td.className = tpData.CSS_TIS;
	} else {
		td.className = tpData.CSS_TI;
	}
	
	arr[arr.length] = td;
}
function TreePainter_doCreateCheckBox(arr, node, tpData) {
	var disabled        = tpData['tree']['disabled'];
//	var showCheckBoxes  = tpData['tree']['checkboxes'];
	var imgRes = null;
	
//	if (!showCheckBoxes) return;

	if (node['checkState'] == CheckState.CHECKED) {
		imgRes = tpData.IMG_ICON_CHECKED1;
	} else if (node['checkState'] == CheckState.UNCHECKED) {
		imgRes = tpData.IMG_ICON_CHECKED0;
	} else if (node['checkState'] == CheckState.UNDEFINED) {
		imgRes = tpData.IMG_ICON_CHECKED2;
	}
	
	// create checkbox image
	var img    = document.createElement('IMG');
	img.src    = imgRes.src;
	img.border = 0;
	img.width  = imgRes.width;
	img.height = imgRes.height;
	

	// embed image within the anchor
	var a = document.createElement('A');
	a.id = node['id'];
	a.appendChild(img);
	
	if (!disabled) {
		a.href = '#';
		
		a.onclick = function() {
					var tree = tpData['tree'];
					var treeId = tree.getId();
					
					if (tree['runAt'] == RunAt.SERVER) {
						// if a RoundTrip is required or the number of children
						// of a node is unknown, we must submit the Form
					
					} else {
						// set the new state
						var checkState = (node['checkState'] == CheckState.CHECKED) ? CheckState.UNCHECKED : CheckState.CHECKED;
						
						TreeHelp.checkNode(node, checkState);
						TreePainter.render(tpData);
						return false;
					}
				}
	}
	
	// add to cell
	var td = document.createElement('TD');
	td.appendChild(a);
	
	arr[arr.length] = td;
}
function TreePainter_resetSelectStyle(tpData) {
	var root  = this.getTreeNode(tpData.DIV_PREVIX + tpData.tree.getId());
	var cells = root.getElementsByTagName('TD');
	
	for (var i=0; i < cells.length; i++) {
		var className = cells[i].className;
		
		if (null != className && className == tpData.CSS_TIS) {
			cells[i].className = tpData.CSS_TI;
		}
	}
}
function TreePainter_getTreeNode(id) {
	return document.getElementById(id);
}
new TreePainter();
TreePainter.render               = TreePainter_render;
TreePainter.getTreeNode          = TreePainter_getTreeNode;
TreePainter.createTreeElements   = TreePainter_createTreeElements;
TreePainter.renderTreeElement    = TreePainter_renderTreeElement;
TreePainter.doCreateLine         = TreePainter_doCreateLine;
TreePainter.doCreateCheckBox     = TreePainter_doCreateCheckBox;
TreePainter.doCreateImage        = TreePainter_doCreateImage;
TreePainter.doCreateImageLink    = TreePainter_doCreateImageLink;
TreePainter.doCreateLabelLink    = TreePainter_doCreateLabelLink;
TreePainter.doCreateIcon         = TreePainter_doCreateIcon;
TreePainter.resetSelectStyle     = TreePainter_resetSelectStyle;

