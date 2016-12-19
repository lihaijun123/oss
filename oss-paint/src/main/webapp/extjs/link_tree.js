function LinkTree(render, aurl, view_addr, add_addr, modify_addr) {
	this.aurl = aurl;
	this.view_addr=view_addr;
	this.add_addr = add_addr;
	this.modify_addr = modify_addr;
	 var Tree = Ext.tree;
	         alert(aurl);
     var tree = new Tree.TreePanel({
        el:render,
        checkModel:'cascade',       
        useArrows:true,
        autoScroll:true,
        animate:false,
        enableDD:false,
        
        view_url:view_addr,
        add_url:add_addr,
        modify_url:modify_addr,
        containerScroll: true, 
        loader: new Tree.TreeLoader({
            dataUrl:aurl,
             baseAttrs:{uiProvider:Ext.tree.TreeLinkNodeUI}
        })
    });
    
    var root = new Tree.AsyncTreeNode({
        text: '焦點科技後台系統',
        draggable:false,
        id:'source'
    });
    tree.setRootNode(root);
   
    tree.render();
    root.expand();
    
}