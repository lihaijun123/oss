 /**
  * 表格行數據的批量提交
<TABLE id = '$ID'>
<TR><TD>產品名︰</TD><TD><INPUT TYPE='text' NAME='pdctName'></TD>	
<TD>價格︰</TD><TD><INPUT TYPE='text' NAME='pdctPrice'></TD>
<TD style = 'width:100px'>
<select id='pdctUnit' name='pdctUnit'>$pdctUnit</select>
</TD><TD>數量︰</TD><TD><INPUT TYPE='text' NAME='pdctCount'></TD>
<TD>折扣︰</TD><TD><select id='pdctUnit' name='pdctUnit'>$pdctUnit</select></TD>
<td><a href='javascript:_action.deleteRow($deleteID);'>刪除</a></td>
</TR>
</TABLE>
  */
 
   function getRow(tableID, index) {
   var aTable = document.getElementById(tableID);
    		return aTable.rows[index];
    }
    
    function test() {
    	//設置，取得combox的值
        //var row = this.getCompValue(this.tableID, "pdctName", 0);
        //alert(row);
        
        var obj = this.getCompnonet(this.tableID, "pdctName", 0);
        obj.value = "michael";
        //this.setCompValue(this.tableID, "pdctUnit", 1, "1");

    }
    
    function getCompValue(tableID, compName, rowIndex) {
          var value = "";
          var array = document.getElementsByName(compName);
          var type = array[rowIndex].type;         
          if(type == "text") {
          	value = array[rowIndex].value;
          }else if(type == "select-one") {
          	value = array[rowIndex].options[array[rowIndex].selectedIndex].value;
          }
          return value;
    }
    
    function getCompnonet(tableID, compName, rowIndex)  {
          var array = document.getElementsByName(compName);
          return array[rowIndex];
    }
    
    function getCompnonetByName(row, aName) {
		    	var cell = row.cells[index];
        return cell.firstChild();
    }
    function setCompValue(tableID, compName, rowIndex, compValue) {
        	var row = getRow(tableID, rowIndex);
          var value = "";
          var array = document.getElementsByName(compName);
          var type = array[rowIndex].type;         
          if(type == "text") {
          	  array[rowIndex].value =  compValue;       
          }else if(type == "select-one") {
          	 //取得值對應的索引
          	 var aIndex = -1;
          	 var len = array[rowIndex].options.length;
          	 var i = 0;
          	 for(i=0; i<len; i++) {
          	 	if(array[rowIndex].options[i].value == compValue) {
          	 		aIndex = i;
          	 		break;
          	 	}
          	 }
          	 if(aIndex >-1)
          	 array[rowIndex].options[aIndex].selected = true;  
          }
           
    
    }
    
    function getRowIndexById(tableId, anID) {
    		var aTable = document.getElementById(tableId);
    		var rowLen = aTable.rows.length;
    		var i = 0;
    		for(i=0; i<rowLen; i++) {
    			var row = aTable.rows[i];    			
    			if(row.id == anID) {
    			
    				return i;
    			} 
    		}
    		return -1;
    }
    function deleteRow(anID) {
    		var index = getRowIndexById(this.tableID, anID);    		
    		pp.deleteRow(index);
    		
    }
    
       function replaceAll(str, substr, replaceStr) {
    		var result = str;
    		var index = -1;
    		while((index=result.indexOf(substr)) != -1) {
    			result = result.replace(substr, replaceStr);
    		}
    		return result;
    		
    }
    function addRow(aTable, templateName) {
	
	
	//trObj.id = "patentID"+(this.totalCount);
	//tdObj.innerHTML = this.templateHTML.replace('$ID', this.totalCount).replace('$deleteID', this.totalCount);
	this.table = document.getElementById(this.tableID);
	var str = this.table.innerHTML.replace("<TBODY>", "").replace("</TBODY>", "");
	this.table.outerHTML = "<table id='"+this.tableID+"'>"+str + this.replaceAll(this.templateHTML, '$ID', this.totalCount) +"</table>";
  	this.totalCount++;
   }
   var _action = null;
    /**
     * listValues:
     * [{'name':name, 'value':[[name,value],...]},...]
     * 比如︰ [{'name':'pdctUnit', 'value':[['元', '0'],['美元','1']]}]
     */
    function TableBuntchAction(templateHTML, tableID, listValues) {
    	_action = this;
    	var i = 0;
    	this.templateHTML = templateHTML;
    	for(i=0; i<listValues.length; i++) {
    		var str ="";
    		var abox = listValues[i];
    		var aName = abox.name;
    		var aValue = abox.value;
    		
    		var j = 0;
    		for(j=0; j<aValue.length; j++) {
    			var nameValue = aValue[j];
    			str = str +"<option value='"+nameValue[1]+"'>"+nameValue[0]+"</option>";
    		}
    		
    		if(this.templateHTML.indexOf("$"+aName) != -1) {
    			this.templateHTML = this.templateHTML.replace("$"+aName, str);
    		}
    		
    	}
    	//this.templateHTML = templateHTML.replace("$pdctUnit", "<option value=''>元</option><option value='2'>美元</option>");
    	this.totalCount = 0;
    	this.tableID = tableID;
    	this.table = document.getElementById(tableID);
        TableBuntchAction.prototype.getRow = getRow;
        TableBuntchAction.prototype.getCompValue = getCompValue;
        TableBuntchAction.prototype.setCompValue = setCompValue;
        TableBuntchAction.prototype.getRowIndexById = getRowIndexById;
        TableBuntchAction.prototype.deleteRow = deleteRow;
        TableBuntchAction.prototype.addRow = addRow;
        TableBuntchAction.prototype.test = test;
        TableBuntchAction.prototype.getCompnonet = getCompnonet;
        TableBuntchAction.prototype.replaceAll = replaceAll;
        
     
    }