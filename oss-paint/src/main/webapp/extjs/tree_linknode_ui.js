Ext.tree.TreeLinkNodeUI = function() {
    this.checkModel = 'multiple';
    this.onlyLeafCheckable = false;

    Ext.tree.TreeLinkNodeUI.superclass.constructor.apply(this, arguments);
};

Ext.extend(Ext.tree.TreeLinkNodeUI, Ext.tree.TreeNodeUI, {
// private
    onClick : function(e){

    },

     // private
    renderElements : function(n, a, targetNode, bulkRender){
        // add some indent caching, this helps performance when rendering a large tree
        this.indentMarkup = n.parentNode ? n.parentNode.ui.getChildIndent() : '';
        var viewurl = this.node.getOwnerTree().view_url;
         var addurl = this.node.getOwnerTree().add_url;
         var modify_url = this.node.getOwnerTree().modify_url;
         viewurl = viewurl.replace("$", n.id);
         addurl = addurl.replace("$", n.id);
         modify_url = modify_url.replace("$", n.id);
        var cb = typeof a.checked == 'boolean';
        n.text = n.text + "&nbsp;&nbsp;&nbsp;&nbsp;<span><a href = '"+viewurl+"'>viewurl</a></span>";
        n.text = n.text + "&nbsp;&nbsp;&nbsp;&nbsp;<span><a href = '"+addurl+"'>addurl</a></span>";
         n.text = n.text + "&nbsp;&nbsp;&nbsp;&nbsp;<a href = '"+modify_url+"'>modify_url</a>";
        var href = a.href ? a.href : Ext.isGecko ? "" : "#";
        var buf = ['<li class="x-tree-node"><div ext:tree-node-id="',n.id,'" class="x-tree-node-el x-tree-node-leaf x-unselectable ', a.cls,'" unselectable="on">',
            '<span class="x-tree-node-indent">',this.indentMarkup,"</span>",
            '<img src="', this.emptyIcon, '" class="x-tree-ec-icon x-tree-elbow" />',
            '<img src="', a.icon || this.emptyIcon, '" class="x-tree-node-icon',(a.icon ? " x-tree-node-inline-icon" : ""),(a.iconCls ? " "+a.iconCls : ""),'" unselectable="on" />',
            cb ? ('<input class="x-tree-node-cb" type="checkbox" ' + (a.checked ? 'checked="checked" />' : '/>')) : '',
            '<a hidefocus="on" class="x-tree-node-anchor" href="',href,'" tabIndex="1" ',
             a.hrefTarget ? ' target="'+a.hrefTarget+'"' : "", '><span unselectable="on">',n.text,"</span></a></div>",
            '<ul class="x-tree-node-ct" style="display:none;"></ul>',
            "</li>"].join('');

        var nel;
        if(bulkRender !== true && n.nextSibling && (nel = n.nextSibling.ui.getEl())){
            this.wrap = Ext.DomHelper.insertHtml("beforeBegin", nel, buf);
        }else{
            this.wrap = Ext.DomHelper.insertHtml("beforeEnd", targetNode, buf);
        }

        this.elNode = this.wrap.childNodes[0];
        this.ctNode = this.wrap.childNodes[1];
        var cs = this.elNode.childNodes;
        this.indentNode = cs[0];
        this.ecNode = cs[1];
        this.iconNode = cs[2];
        var index = 3;
        if(cb){
            this.checkbox = cs[3];
            index++;
        }
        this.anchor = cs[index];
        this.textNode = cs[index].firstChild;
    }
});
