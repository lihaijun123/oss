/**
 * @class SelectTree
 * @authot zhanghua
 * ��ܾ�
 * @param
 * obj:select����, �@����檺���ñ���V�A�Ⱦ����Ѥw�g��ܪ��`�I�H��.
 * treeID:��ҦbDIV��ID
 * action:��o��`�I�H����action�a�}
 */

/**�@�������ܶq�A �ѽեΪ̴��Ѥw�g��ܪ��`�IID�H�� */
var selectChecks = new Array();

function init(){
    var Tree = Ext.tree;
     var root = null;

    var tree = new Tree.TreePanel({
        el:this.treeID,
        onlyLeafCheckable:false,
        checkModel:'cascade',
        useArrows:true,
        autoScroll:true,
        animate:false,
        enableDD:false,
        containerScroll: true,
        loader: new Tree.TreeLoader({
            dataUrl:this.action,
            baseAttrs:{uiProvider:Ext.tree.TreeCheckNodeUI}
        })
    });

    // set the root node
    root = new Tree.AsyncTreeNode({
        text: '资源',
        draggable:false,
        id:'source'
    });
    tree.setRootNode(root);

    // render the tree
    tree.render();
    root.expand(true, false, true);
    function _selectNodes(e) {
    	selectNodes(e);
    }
    function delay(e) {

      	setTimeout(function(){_selectNodes(selectChecks);}, 500);
    }
      tree.on("load",delay);


	//global array
	var array = new Array();
	//all nodes getAllNodes
	var allNodes = new Array();
	function getAllNodes(aNode) {
		if(aNode.leaf){
		 add2Array({'id':aNode.id, 'ui':aNode.getUI().checkbox}, allNodes);
		}else {
		  var i = 0;
		  var childs = aNode.childNodes;
		  for(i=0; i<childs.length; i++) {
		     add2Array({'id':childs[i].id, 'ui':childs[i].getUI().checkbox}, allNodes);
		     getAllNodes(childs[i]);
		  }
		}
	}

	function hasNode2(obj, nodeArray) {
		var i = 0;
		var exitFlag = false;
	    for(i=0; i<nodeArray.length; i++) {
	    	if(nodeArray[i].id == obj.id) {
	    		exitFlag = true;
	    		return exitFlag;
	    	}
	    }


	    return exitFlag;
	}


	function add2Array(node, array) {
	   var i = 0;
	   var len = array.length;
	   if(hasNode2(node, array)) {
	   }else {
	     array.push(node);
	   }

	}

  function selectNode(nodeID) {
	    var j = 0;
	    var len = allNodes.length;
		for(j=0; j<len; j++) {
			if(allNodes[j].id == nodeID) {
				allNodes[j].ui.checked = true;
			}
		}
	}

	function selectNodes(nodes) {
		allNodes = new Array();
		getAllNodes(root);
		var len = nodes.length;
		var k = 0;
		for(k=0; k<len; k++) {
			selectNode(nodes[k]);
		}


		}

    	function getChecked(obj) {
		var i = 0;
		var res= obj;//(document.forms[0].resources);
		for(i=0; i<array.length; i++) {
	     	res.options[res.options.length] = new Option(array[i], array[i]);
	     	res.options[res.options.length-1].selected = true;
	     }
	}

	 function getSelectedItems() {
        allNodes = new Array();
		getAllNodes(root);
		array = new Array();
		for(i=0; i<allNodes.length; i++) {
			if(allNodes[i].ui.checked) {
			 	array.push(allNodes[i].id);
			}
		}
		getChecked(this.obj);


	}

	this.aRoot = root;
	SelectTree.prototype.getSelects = getSelectedItems;
	}

    	function SelectTree(obj, treeID, action) {
    		this.obj = obj;
    		this.treeID = treeID;
    		this.action = action;
			this.aRoot = null;
			SelectTree.prototype.getSelects = null;
			this._selectNodes = null;
			SelectTree.prototype.init = init;
		}