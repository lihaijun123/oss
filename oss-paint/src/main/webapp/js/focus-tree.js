
Ext.onReady(function() {
    var menuString = document.getElementById("menuString").value;
    menuString = "[" + menuString + "]";
    var selected;
    // shorthand
    var Tree = Ext.tree;
    var tree = new Tree.TreePanel({
        el: 'treePanel',
        title: "",
        autoScroll: true,
        checkModel: 'extCascade', //對樹的級聯多選   
        onlyLeafCheckable: false, //對樹所有結點都可選 
        animate: false,
        enableDD: false,
        rootVisible: false,
        containerScroll: true,
        collapseMode: 'mini',
        loader: new Tree.TreeLoader({
            dataUrl: '/portalConfig.do',
            baseAttrs: {
                uiProvider: Ext.ux.TreeCheckNodeUI
            } //添加 uiProvider 屬性  
        })
    });
    
    
    // set the root node					
    var root = new Tree.AsyncTreeNode({
        text: 'Ext JS',
        // draggable:false,
        id: 'source',
        children: eval(menuString)
    });
    tree.setRootNode(root);
    
    // regist check change    
    tree.on('check', function(node, checked) {
        var checkedNodes = tree.getChecked();//tree必須事先創建好.
        var selectedNodeIds = "";	
        var selectedNodeTexts = "";
        if (checkedNodes != null && checkedNodes.length > 0) {
            for (var i = 0; i < checkedNodes.length; i++) {
                var checkedNode = checkedNodes[i];
                var parentNode = checkedNode.parentNode;
                //parentNode.attributes.checked	
                
                if (checkedNode.leaf && parentNode && parentNode.id != "000" && selectedNodeIds.indexOf(parentNode.id) < 0) {
                    selectedNodeIds += parentNode.id + "-";
                    selectedNodeTexts += parentNode.text + "|";
					
                    var childNodes = parentNode.childNodes;
                    if (childNodes && childNodes.length > 0) {
                        for (var j = 0; j < childNodes.length; j++) {
                            if (childNodes[j].attributes.checked) {
                                selectedNodeIds += childNodes[j].id + ",";
                            }
                        }
                        selectedNodeIds = selectedNodeIds.substr(0, selectedNodeIds.length - 1) + "|";
                    }
                }
            }
        }
        document.getElementById("selectedNodeId").value = selectedNodeIds;
        document.getElementById("selectedNodeText").value = selectedNodeTexts;
    }, tree);
    
    //render the tree
    tree.render();
	if (root.firstChild != null) {
	    root.firstChild.expand();
	    
	    //set the selected tree node
	    var checkedQueueIds = document.getElementById("selectedNodeId").value;
	    root.firstChild.eachChild(function(child) {
	        listNode(child, checkedQueueIds);
	    });
	}
});

/**
 * 遍歷指定樹節點
 */
function listNode(node, checkedQueueIds) {
    if (node != null) {
        node.expand();
        var hasChecked = false;
		var hasInnerChecked = false;
        var allChecked = true;
        for (var i = 0; i < node.childNodes.length; i++) {
            var childNode = node.childNodes[i];
            if (childNode == null) {
                continue;
            }
            
            if (checkedQueueIds.indexOf(childNode.id) >= 0) {
                hasChecked = true;
                childNode.getUI().checkbox.checked = true;
				childNode.attributes.checked = true;
            }
            else {
                childNode.getUI().checkbox.checked = false;
				childNode.attributes.checked = false;
                allChecked = false;
            }
            
			childNode.expand();
            if (childNode.childNodes.length > 0) {
                var innerChecked = listNode(childNode, checkedQueueIds);
				if (innerChecked) {
					hasInnerChecked = true;
				}
            }
        }
        
        if (hasChecked || hasInnerChecked) {
            node.expand();
            if (allChecked) {
                node.getUI().checkbox.checked = true;
				node.attributes.checked = true;
            }
            else {
                node.getUI().checkbox.checked = false;
				node.attributes.checked = false;
            }
			return true;
        }
        else {
            node.collapse();
			return false;
        }
        
    }
}
