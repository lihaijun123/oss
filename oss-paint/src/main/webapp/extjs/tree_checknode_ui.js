/**  
 * @class Ext.tree.TreeCheckNodeUI  
 * @extends Ext.tree.TreeNodeUI  
 *   
 * 對 Ext.tree.TreeNodeUI 進行checkbox功能的擴展,後台返回的結點信息不用非要包含checked屬性  
 *   
 * 擴展的功能點有︰  
 * 一、支持只對樹的葉子進行選擇  
 *    只有當返回的樹結點屬性leaf = true 時，結點才有checkbox可選  
 *    使用時，只需在聲明樹時，加上屬性 onlyLeafCheckable: true 既可，默認是false  
 *   
 * 二、支持對樹的單選  
 *    只允許選擇一個結點  
 *    使用時，只需在聲明樹時，加上屬性 checkModel: "single" 既可  
 *   
 * 二、支持對樹的級聯多選   
 *    當選擇結點時，自動選擇該結點下的所有子結點，以及該結點的所有父結點（根結點除外），特別是支持異步，當子結點還沒顯示時，會從後台取得子結點，然後將其選中/取消選中  
 *    使用時，只需在聲明樹時，加上屬性 checkModel: "cascade" 既可  
 *   
 * 三、添加"check"事件  
 *    該事件會在樹結點的checkbox發生改變時觸發  
 *    使用時，只需給樹注冊事件,如︰  
 *    tree.on("check",function(node,checked){...});  
 *   
 * 默認情況下，checkModel為'multiple'，也就是多選，onlyLeafCheckable為false，所有結點都可選  
 *   
 * 使用方法︰在loader里加上 baseAttrs:{uiProvider:Ext.tree.TreeCheckNodeUI} 既可.  
 * 例如︰  
 *   var tree = new Ext.tree.TreePanel({  
 *      el:'tree-ct',  
 *      width:568,  
 *      height:300,  
 *      checkModel: 'cascade',   //對樹的級聯多選  
 *      onlyLeafCheckable: false,//對樹所有結點都可選  
 *      animate: false,  
 *      rootVisible: false,  
 *      autoScroll:true,  
 *      loader: new Ext.tree.DWRTreeLoader({  
 *          dwrCall:Tmplt.getTmpltTree,  
 *          baseAttrs: { uiProvider: Ext.tree.TreeCheckNodeUI } //添加 uiProvider 屬性  
 *      }),  
 *      root: new Ext.tree.AsyncTreeNode({ id:'0' })  
 *  });  
 *  tree.on("check",function(node,checked){alert(node.text+" = "+checked)}); //注冊"check"事件  
 *  tree.render();  
 *   
 */  
  
Ext.tree.TreeCheckNodeUI = function() {   
    //'multiple':多選; 'single':單選; 'cascade':級聯多選   
    this.checkModel = 'multiple';   
       
    //only leaf can checked   
    this.onlyLeafCheckable = false;   
       
    Ext.tree.TreeCheckNodeUI.superclass.constructor.apply(this, arguments);   
};   
  
Ext.extend(Ext.tree.TreeCheckNodeUI, Ext.tree.TreeNodeUI, {   
  
    renderElements : function(n, a, targetNode, bulkRender){   
        var tree = n.getOwnerTree();   
        this.checkModel = tree.checkModel || this.checkModel;   
        this.onlyLeafCheckable = tree.onlyLeafCheckable || false;   
           
        // add some indent caching, this helps performance when rendering a large tree   
        this.indentMarkup = n.parentNode ? n.parentNode.ui.getChildIndent() : '';   
  
    var cb = (!this.onlyLeafCheckable || a.leaf);   
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
            Ext.fly(this.checkbox).on('click', this.check.createDelegate(this,[null]));   
            index++;   
        }   
        this.anchor = cs[index];   
        this.textNode = cs[index].firstChild;   
    },   
       
    // private   
    check : function(checked){   
        var n = this.node;   
        var tree = n.getOwnerTree();   
        this.checkModel = tree.checkModel || this.checkModel;   
           
        if( checked === null ) {   
            checked = this.checkbox.checked;   
        } else {   
            this.checkbox.checked = checked;   
        }   
           
        n.attributes.checked = checked;   
        tree.fireEvent('check', n, checked);   
           
        if(!this.onlyLeafCheckable && this.checkModel == 'cascade'){   
            var parentNode = n.parentNode;   
            if(parentNode !== null) {   
                this.parentCheck(parentNode,checked);   
            }   
            if( !n.expanded && !n.childrenRendered ) {   
                n.expand(false,false,this.childCheck);   
            }   
            else {   
                this.childCheck(n);   
            }   
        }else if(this.checkModel == 'single'){   
            var checkedNodes = tree.getChecked();   
            for(var i=0;i<checkedNodes.length;i++){   
                var node = checkedNodes[i];   
                if(node.id != n.id){   
                    node.getUI().checkbox.checked = false;   
                    node.attributes.checked = false;   
                    tree.fireEvent('check', node, false);   
                }   
            }   
        }   
           
    },   
       
    // private   
    childCheck : function(node){   
        var a = node.attributes;   
        if(!a.leaf) {   
            var cs = node.childNodes;   
            var csui;   
            for(var i = 0; i < cs.length; i++) {   
                csui = cs[i].getUI();   
                if(csui.checkbox.checked ^ a.checked)   
                    csui.check(a.checked);   
            }   
        }   
    },   
       
    // private   
    parentCheck : function(node ,checked){   
        var checkbox = node.getUI().checkbox;   
        if(typeof checkbox == 'undefined')return ;   
        if(!(checked ^ checkbox.checked))return;   
        if(!checked && this.childHasChecked(node))return;   
        checkbox.checked = checked;   
        node.attributes.checked = checked;   
        node.getOwnerTree().fireEvent('check', node, checked);   
           
        var parentNode = node.parentNode;   
        if( parentNode !== null){   
            this.parentCheck(parentNode,checked);   
        }   
    },   
       
    // private   
    childHasChecked : function(node){   
        var childNodes = node.childNodes;   
        if(childNodes || childNodes.length>0){   
            for(var i=0;i<childNodes.length;i++){   
                if(childNodes[i].getUI().checkbox.checked)   
                    return true;   
            }   
        }   
        return false;   
    },   
       
    toggleCheck : function(value){   
        var cb = this.checkbox;   
        if(cb){   
            var checked = (value === undefined ? !cb.checked : value);   
            this.check(checked);   
        }   
    }   
});  
