//**********************************************
//  【联动控件】2.03
//  @author：xingshikang
//  @v2.03  2010-12-08
//  版权归焦点科技所有
//**********************************************
if (!!window.jQuery) {
	(function($) {
		function areaGear() {
			this.receiveObj = null;
			this.arrayChk = new Array();
			this.arraySelected = new Array();
			this.pageObj = null;
			this._options = {
				divPopId : "div_PopFrame",
				popTitle : "经营范围",
				mode : "complete",
				transResult : false,
				modeCfg : {
					useSelect : true,
					chkOrrad : "checkbox",
					maxSelected : 100,
					antiObj : null,
					selMerge : true,
					page : false,
					pageSize : "15",
					html : "",
					actionID : "",
					action : function(action) {
						alert("《联动控件》提示：没有配置获取数据action！")
					},
					createBody : null
				},
				gearCustom : [ {
					url : {
						"quanguo" : "全国"
					},
					dataPar : "",
					id : "",
					name : "",
					defkey : "",
					attVal : "",
					setDef : function() {
					},
					show : false
				}, {
					url : "/areagear/getProvince",
					dataPar : "",
					id : "",
					name : "",
					defkey : "Jiangsu",
					attVal : "",
					setDef : "queryIp",
					show : true
				}, {
					url : "/areagear/getCity",
					dataPar : "province",
					id : "",
					name : "",
					defkey : "",
					attVal : "",
					setDef : function() {
					},
					show : true
				} ]
			};
		}
		;
		Array.prototype.arrayPush = function(el) {
			this.push(el);
			if (this.onchange != null && typeof (this.onchange) == "function") {
				this.onchange.call(this);
			}
			return this;
		};
		Array.prototype.arrayRemove = function(el) {
			var a = $.grep(this, function(n, i) {
				return n != el;
			});
			this.length = 0;
			for ( var i in a) {
				this[i] = a[i];
			}
			if (this.onchange != null && typeof (this.onchange) == "function") {
				this.onchange.call(this);
			}
			return this;
		};
		$
				.extend(
						areaGear.prototype,
						{
							init : function() {
								var _this = this;
								this._reSetCfg();
								if (this._options.mode == "complete") {
									this._creatPop();
									this._creatCon();
									if (this._options.modeCfg.useSelect) {
										this._triggerGear();
										this
												._getDate(
														this._options.gearCustom[0].url,
														'',
														this._handel_normal,
														this._options.gearCustom[0]);
									} else {
										$("#" + this._options.modeCfg.actionID)
												.bind(
														"click",
														function() {
															function _action(
																	url, data) {
																if (!_this._options.modeCfg.page) {
																	_this
																			._getDate(
																					url,
																					data,
																					_this._handel_last,
																					{
																						value : "",
																						text : "全部"
																					});
																} else {
																	_this.pageObj = $(
																			"#"
																					+ _this._options.divPopId
																					+ "_page")
																			.page(
																					{
																						url : url,
																						data : data,
																						handel : _this._handel_last,
																						pageSize : _this._options.modeCfg.pageSize,
																						pThis : _this
																					});
																}
															}
															_this._options.modeCfg.action
																	.call(this,
																			_action);
														});
										$("#" + this._options.modeCfg.actionID)
												.click();
									}
								} else {
									this.receiveObj.append($("<div id=\""
											+ this._options.divPopId
											+ "_areaGear\" class=\"popCon\">"
											+ this._creatFactor() + "</div>"));
									this._triggerGear();
									this._getDate(
											this._options.gearCustom[0].url,
											'', this._handel_normal,
											this._options.gearCustom[0]);
								}
								_this.arraySelected.onchange = function() {
									if (this.length == 0) {
										$(
												"#" + _this._options.divPopId
														+ " .selected").hide();
									} else {
										$(
												"#" + _this._options.divPopId
														+ " .selected").show();
									}
								}
							},
							_reSetCfg : function() {
								var _this = this;
								$
										.each(
												this._options.gearCustom,
												function(i, n) {
													if (n.name == "") {
														_this._options.gearCustom[i].name = _this._options.divPopId
																+ "_sel" + i;
													}
													if (n.id == "") {
														_this._options.gearCustom[i].id = _this._options.gearCustom[i].name
																.replace(".",
																		"_");
													}
													if (typeof _this._options.gearCustom[i].setDef == "function") {
														_this._options.gearCustom[i].defkey = _this._options.gearCustom[i]
																.setDef();
													}
												});
								if (this._options.mode == "complete"
										&& this._options.modeCfg.useSelect) {
									if (this._options.gearCustom.length < 2) {
										this._options.modeCfg.selMerge = false;
									}
								}
								_this.loaded = false;
							},
							_creatPop : function() {
								var htmlPop = "<div id=\""
										+ this._options.divPopId
										+ "\" class=\"areaGear\" style=\"display:none\"></div>";
								var htmlTitle = "<h2><img id=\"close"
										+ this._options.divPopId
										+ "\" src=\"/images/ico_close.gif\" alt=\"关闭\" class=\"close\">"
										+ this._options.popTitle + "</h2>";
								var thisdivPopId = "#" + this._options.divPopId;
								$(htmlPop).appendTo("body");
								$(thisdivPopId).html(htmlTitle);
								$("#close" + this._options.divPopId).bind(
										"click", function() {
											$(thisdivPopId).fadeOut();
										});
							},
							_creatCon : function() {
								var _this = this;
								var htmlAreaGear = "<div id=\""
										+ this._options.divPopId
										+ "_areaGear\" class=\"popCon\">";
								var htmlSelect = this._creatFactor();
								var htmlNodes = "<div class=\"selectBox\"><div id=\""
										+ this._options.divPopId
										+ "_divNodes\" class=\"item\"></div></div>";
								var htmlPage = "";
								if (this._options.modeCfg.page) {
									htmlPage = "<div id=\""
											+ this._options.divPopId
											+ "_page\"></div>";
								}
								var htmlSelected = "<div class=\"selected\" style=\"display:none\"><h4><a href=\"#\" id=\""
										+ this._options.divPopId
										+ "_aCleanSelected\" class=\"clear\">清空已选</a>您选择了：</h4>";
								htmlSelected += "<div id=\""
										+ this._options.divPopId
										+ "_divSelected\" class=\"selectedBox\"></div></div>";
								var htmlErroInfo = "<div id=\""
										+ this._options.divPopId
										+ "_errorInfo\" class=\"errorBox\" style=\"display:none\"></div>"
								var htmlSubmit = "<div class=\"button\"><button type=\"submit\" class=\"big\" id=\""
										+ this._options.divPopId
										+ "_submit\">提交</button></div>";
								$("#" + this._options.divPopId).append(
										htmlAreaGear);
								$("#" + this._options.divPopId + "_areaGear")
										.append(
												$(htmlSelect + htmlNodes
														+ htmlPage
														+ htmlSelected
														+ htmlErroInfo
														+ htmlSubmit));
								$(
										"#" + this._options.divPopId
												+ "_aCleanSelected")
										.bind(
												"click",
												function() {
													_this.arraySelected.length = 0;
													$(
															"#"
																	+ _this._options.divPopId
																	+ "_divSelected div")
															.remove();
													var chks = $(
															"#"
																	+ _this._options.divPopId
																	+ " .selectBox")
															.find(":checkbox");
													$
															.each(
																	chks,
																	function(i,
																			n) {
																		$(n)
																				.get(
																						0).checked = false;
																	});
													$(
															"#"
																	+ _this._options.divPopId
																	+ " .selected")
															.hide();
												});
								$("#" + this._options.divPopId + "_submit")
										.bind(
												"click",
												function() {
													if (_this.receiveObj
															.is("input")) {
														_this.receiveObj
																.val(_this.arraySelected
																		.toString());
													} else {
														_this.receiveObj
																.html(_this.arraySelected
																		.toString());
													}
													var selecteds = $("div[id^='"
															+ _this._options.divPopId
															+ "_selected-']");
													var result = "";
													$
															.each(
																	selecteds,
																	function(i,
																			n) {
																		if ($(n)
																				.find(
																						"img[name='"
																								+ _this._options.divPopId
																								+ "_cancelSelected_P']").length == 0) {
																			if ($(
																					n)
																					.get(
																							0).id
																					.indexOf('_') != -1) {
																				result += $(
																						n)
																						.get(
																								0).id
																						.split('-')[1]
																						+ "#";
																			}
																		}
																	});
													if ($("input[name='"
															+ _this._options.divPopId
															+ "_result']").length == 0) {
														$(
																"<input name=\""
																		+ _this._options.divPopId
																		+ "_result\" type=\"hidden\"></input>")
																.appendTo(
																		"body");
													}
													$(
															"input[name='"
																	+ _this._options.divPopId
																	+ "_result']")
															.val(result);
													$(
															"#"
																	+ _this._options.divPopId)
															.fadeOut();
													_this.onSubmit();
												});
							},
							_creatFactor : function() {
								var _this = this;
								var html = "";
								if (this._options.mode == "simple"
										|| (this._options.mode == "complete" && this._options.modeCfg.useSelect)) {
									$
											.each(
													this._options.gearCustom,
													function(i, n) {
														if (_this._options.mode == "complete"
																&& i == _this._options.gearCustom.length - 1) {
															return false;
														}
														var style = "";
														if (!n.show) {
															style = " style=\"display: none\"";
														}
														html += "<select id=\""
																+ n.id
																+ "\" name=\""
																+ n.name
																+ "\" " + style
																+ "></select>";
													});
								} else {
									html = this._options.modeCfg.html;
								}
								return html;
							},
							_getDate : function(url, data, _fn, obj, datatype,
									asyncType) {
								var _this = this;
								if (typeof url == "object") {
									_fn.call(_this, url, obj);
									return;
								}
								var _dataType = "json";
								if (datatype != null
										&& typeof (datatype) == 'string'
										&& datatype != "") {
									_dataType = datatype;
								}
								var _asyncType = true;
								if (asyncType != null
										&& typeof (asyncType) == 'boolean') {
									_asyncType = asyncType;
								}
								$.ajax( {
									type : "GET",
									url : url,
									data : encodeURI(data),
									dataType : _dataType,
									async : _asyncType,
									success : function(result) {
										_fn.call(_this, result, obj);
									}
								});
							},
							_triggerGear : function() {
								var _this = this;
								$(
										"#" + this._options.divPopId
												+ "_areaGear select")
										.bind(
												"change",
												function(event) {
													var _selobj = this;
													var setting = "";
													var index = 0;
													var data = "";
													if (_this._options.mode == "simple"
															&& $(this).next(
																	"select").length == 0) {
														return;
													}
													var allSelect = $("#"
															+ _this._options.divPopId
															+ "_areaGear select");
													$
															.each(
																	allSelect,
																	function(i,
																			n) {
																		if (_selobj.id == n.id) {
																			index = i;
																			setting = _this._options.gearCustom[i + 1];
																			return false;
																		}
																	});
													if(setting.dataPar!=null){
														if(typeof(setting.dataPar)=="function"){
															data=setting.dataPar.call(_this)+"="+this.value;
														}
														else{
															data=setting.dataPar+"="+this.value;
														}
													}
													if ($(this).next("select").length == 0) {
														if (_this._options.modeCfg.page) {
															_this.pageObj = $(
																	"#"
																			+ _this._options.divPopId
																			+ "_page")
																	.page(
																			{
																				url : setting.url,
																				data : data,
																				handel : _this._handel_last,
																				pageSize : _this._options.modeCfg.pageSize,
																				pThis : _this
																			});
														} else {
															_this
																	._getDate(
																			setting.url,
																			data,
																			_this._handel_last,
																			_this._options.gearCustom[index]);
														}
													} else {
														_this
																._getDate(
																		setting.url,
																		data,
																		_this._handel_normal,
																		setting);
													}
												});
							},
							_handel_normal : function(result, obj) {
								var selectObj = document.getElementById(obj.id);
								$(selectObj).children().remove();
								var tempIndex = 0;
								var defIndex = -1;
								if (obj.attVal != "") {
									selectObj.options.add(new Option(
											obj.attVal, ""));
									tempIndex++;
								}
								$
										.each(
												result,
												function(i, n) {
													var splitIndex = n
															.search("^\\{disabled:true\\}");
													if (splitIndex != -1) {
														var optgroupObj = document
																.createElement("optgroup");
														optgroupObj.label = n
																.substr("{disabled:true}".length);
														optgroupObj.className = "optdisabled";
														optgroupObj
																.appendChild(document
																		.createTextNode(" "));
														selectObj
																.appendChild(optgroupObj);
													} else {
														selectObj.options[tempIndex] = new Option(
																n, i);
														if (selectObj
																.getElementsByTagName("optgroup").length > 0) {
															$(
																	selectObj.options[tempIndex])
																	.remove();
															var optionObj = document
																	.createElement("option");
															optionObj.value = i;
															optionObj
																	.appendChild(document
																			.createTextNode(n));
															selectObj
																	.appendChild(optionObj);
														}
														if (obj.defkey == i) {
															defIndex = tempIndex;
														}
														tempIndex++;
													}
												});
								if (defIndex != -1) {
									selectObj.options[defIndex].selected = true;
								} else {
									if (obj.setDef != null
											&& typeof (obj.setDef) == "string"
											&& obj.setDef == "queryIp") {
										this
												._getDate(
														"/areagear/getLocation",
														'', this._handel_Ip,
														obj, 'text', false);
									} else if (obj.setDef != null
											&& typeof (obj.setDef) == "function") {
										obj.setDef();
									}
								}
								var selects = selectObj.parentNode
										.getElementsByTagName("select");
								var currentIndex;
								$.each(selects, function(i, n) {
									if (n.id == obj.id) {
										currentIndex = i;
										return false;
									}
								});
								if (selectObj.options.length == 0) {
									selectObj.style.display = "none";
									if (this._options.transResult) {
										if (currentIndex >= 1) {
											selectObj.options
													.add(new Option(
															"",
															selects[currentIndex - 1].value));
										}
									}
								} else {
									selectObj.style.display = "";
								}
								if (currentIndex == selects.length - 1
										&& this._options.mode == "simple") {
									this._onLoad.call(this);
								}
								if (obj != null) {
									$("#" + obj.id).change();
								}
							},
							_handel_last : function(result, obj) {
								var _this = this;
								var htmlNodes = "";
								var allSelected = true;
								var existedVal = false;
								var lastOption = {};
								if (obj != null) {
									if (obj.id != null
											&& $("#" + obj.id).is("select")) {
										lastOption = document
												.getElementById(obj.id).options[document
												.getElementById(obj.id).selectedIndex];
									} else {
										lastOption = obj;
									}
								} else {
									lastOption = {
										text : "",
										value : ""
									};
								}
								_this.arrayChk.length = 0;
								for (x in result) {
									existedVal = true;
									break;
								}
								if (_this._options.modeCfg.createBody != null
										&& typeof (_this._options.modeCfg.createBody) == "function") {
									htmlNodes += _this._options.modeCfg.createBody
											.call(_this, result, formatChk);
								} else {
									htmlNodes += formatChk(lastOption.value,
											lastOption.text, "全选", "all");
									$.each(result, function(i, n) {
										htmlNodes += formatChk(i, n);
									});
								}
								$("#" + this._options.divPopId + "_divNodes")
										.html(htmlNodes);
								if (allSelected == true
										&& !_this._options.modeCfg.page) {
									$(
											"input[name='"
													+ this._options.divPopId
													+ "_chkPNode']").get(0).checked = true;
								}
								_this._chkHandel();
								if (_this._options.modeCfg.antiObj != null) {
									$
											.each(
													_this._options.modeCfg.antiObj,
													function(i, n) {
														var obj = {
															"id" : i,
															"value" : n
														};
														if ($("#"
																+ _this._options.divPopId
																+ "_selected-"
																+ obj.id).length == 0) {
															if ($("#" + obj.id).length > 0) {
																$("#" + obj.id)
																		.get(0).checked = true;
															}
															_this.driveSelected
																	.call(
																			obj,
																			"cancelSelected",
																			true);
														}
													});
									_this._options.modeCfg.antiObj = null;
								}
								_this._onLoad.call(_this);
								function formatChk(key, value, showTip,
										isSelAll) {
									var html = "";
									if (showTip == null) {
										showTip = value;
									}
									var inputType = _this._options.modeCfg.chkOrrad;
									if (isSelAll == "all") {
										if (inputType == "checkbox"
												&& !_this._options.modeCfg.page
												&& existedVal) {
											html += "<label><input id=\"" + key
													+ "\" name=\""
													+ _this._options.divPopId
													+ "_chkPNode\" type=\""
													+ inputType + "\" value=\""
													+ value + "\">" + showTip
													+ "</input></label>";
										}
									} else {
										html = "<label><input id=\"" + key
												+ "\" name=\""
												+ _this._options.divPopId
												+ "_chkNodes\" type=\""
												+ inputType + "\" ";
										if ($.inArray(value,
												_this.arraySelected) != -1) {
											html += "checked=\"checked\" ";
										} else {
											allSelected = false;
										}
										html += "value=\"" + value + "\">"
												+ showTip + "</input></label>";
										_this.arrayChk.push(key);
									}
									return html;
								}
							},
							_handel_Ip : function(result, obj) {
								var optionsProvinces = document
										.getElementById(obj.id).options;
								$
										.each(
												optionsProvinces,
												function(i, n) {
													if (result.search(n.text) != -1) {
														document
																.getElementById(obj.id).options[i].selected = true;
														return false;
													}
												});
							},
							_chkHandel : function() {
								var _this = this;
								$(
										"input[name='" + this._options.divPopId
												+ "_chkPNode']")
										.bind(
												"click",
												function() {
													if (this.checked == true) {
														$(
																"input[name='"
																		+ _this._options.divPopId
																		+ "_chkNodes']")
																.attr(
																		"checked",
																		true);
													} else {
														$(
																"input[name='"
																		+ _this._options.divPopId
																		+ "_chkNodes']")
																.attr(
																		"checked",
																		false);
													}
													$(
															"input[name='"
																	+ _this._options.divPopId
																	+ "_chkNodes']")
															.change();
													if (_this._options.modeCfg.selMerge) {
														var chks = $("#"
																+ _this._options.divPopId
																+ "_divNodes :checkbox");
														var allchecked = true;
														$
																.each(
																		chks,
																		function(
																				i,
																				n) {
																			if ($(
																					n)
																					.get(
																							0).checked == false) {
																				allchecked = false;
																				return false;
																			}
																		});
														if (allchecked) {
															$(
																	"div[id^='"
																			+ _this._options.divPopId
																			+ "_selected-"
																			+ this.id
																			+ "']")
																	.css(
																			"display",
																			"none");
															_driveSelected
																	.call(
																			this,
																			"cancelSelected_P",
																			false);
														}
													}
												});
								$(
										"input[name='" + this._options.divPopId
												+ "_chkNodes']")
										.bind(
												"change",
												function() {
													if (this.checked == true) {
														if ($
																.inArray(
																		this.value,
																		_this.arraySelected) == -1) {
															_driveSelected
																	.call(
																			this,
																			"cancelSelected",
																			true);
														}
													} else {
														if (_this._options.modeCfg.chkOrrad == "checkbox"
																&& !_this._options.modeCfg.page) {
															$(
																	"input[name='"
																			+ _this._options.divPopId
																			+ "_chkPNode']")
																	.get(0).checked = false;
															if (_this._options.modeCfg.selMerge) {
																var Parent = $(
																		"input[name='"
																				+ _this._options.divPopId
																				+ "_chkPNode']")
																		.get(0).id;
																$(
																		"div[id^='"
																				+ _this._options.divPopId
																				+ "_selected-"
																				+ Parent
																				+ "']")
																		.css(
																				"display",
																				"");
																$(
																		"#"
																				+ _this._options.divPopId
																				+ "_selected-"
																				+ Parent)
																		.remove();
															}
														}
														if ($
																.inArray(
																		this.value,
																		_this.arraySelected) != -1) {
															$(
																	"#"
																			+ _this._options.divPopId
																			+ "_selected-"
																			+ this.id)
																	.remove();
															_this.arraySelected
																	.arrayRemove(this.value);
														}
													}
												});
								function _driveSelected(imgname, pushed) {
									_this.chkNode = $(
											"#" + _this._options.divPopId)
											.find("#" + this.id).get(0);
									if (!_this._checkMaxSelected()) {
										return;
									}
									if (_this._options.modeCfg.chkOrrad == "radio") {
										var id = "";
										if (this.id.indexOf("_") != -1) {
											id = this.id.split("_")[0];
										}
										$(
												"div[id^='"
														+ _this._options.divPopId
														+ "_selected-" + id
														+ "']").remove();
									}
									var htmlSelected = "<div id=\""
											+ _this._options.divPopId
											+ "_selected-" + this.id + "\">"
											+ this.value;
									htmlSelected += "<a href=\"#\"><img name=\""
											+ _this._options.divPopId
											+ "_"
											+ imgname
											+ "\" src=\"/images/ico_close_gray.gif\" alt=\"取消\"/></a></div>";
									$(
											"#" + _this._options.divPopId
													+ "_divSelected").append(
											htmlSelected);
									if (pushed) {
										_this.arraySelected
												.arrayPush(this.value);
									}
									_this._selectedHandel();
								}
								;
								_this.driveSelected = _driveSelected;
							},
							_selectedHandel : function() {
								var _this = this;
								$(
										"div[id^='" + this._options.divPopId
												+ "_selected-']")
										.bind(
												"mouseover",
												function() {
													$(this)
															.find(
																	"img[name^='"
																			+ _this._options.divPopId
																			+ "_cancelSelected']")
															.get(0).src = "/images/ico_close_red.gif";
												});
								$(
										"div[id^='" + this._options.divPopId
												+ "_selected-']")
										.bind(
												"mouseout",
												function() {
													$(this)
															.find(
																	"img[name^='"
																			+ _this._options.divPopId
																			+ "_cancelSelected']")
															.get(0).src = "/images/ico_close_gray.gif";
												});
								var _this = this;
								$(
										"img[name='" + this._options.divPopId
												+ "_cancelSelected']")
										.bind(
												"click",
												function() {
													var divObj = $(this)
															.parent().parent();
													var divid = divObj.get(0).id
															.split('-')[1];
													if ($.inArray(divid,
															_this.arrayChk) != -1) {
														$(
																"#"
																		+ _this._options.divPopId)
																.find(
																		"#"
																				+ divid)
																.get(0).checked = false;
														$(
																"#"
																		+ _this._options.divPopId)
																.find(
																		"#"
																				+ divid)
																.change();
													}
													divObj.remove();
													if (divObj.get(0).textContent) {
														_this.arraySelected
																.arrayRemove(divObj
																		.get(0).textContent);
													} else {
														_this.arraySelected
																.arrayRemove(divObj
																		.get(0).innerText);
													}
												});
								$(
										"img[name='" + this._options.divPopId
												+ "_cancelSelected_P']")
										.bind(
												"click",
												function() {
													var divObj = $(this)
															.parent().parent();
													var divid = divObj.get(0).id
															.split('-')[1];
													divObj.remove();
													$(
															"div[id^='"
																	+ _this._options.divPopId
																	+ "_selected-"
																	+ divid
																	+ "']")
															.find(
																	"img[name='"
																			+ _this._options.divPopId
																			+ "_cancelSelected']")
															.click();
												});
							},
							_checkMaxSelected : function() {
								if (this._options.modeCfg.maxSelected == this.arraySelected.length) {
									$(
											"#" + this._options.divPopId
													+ "_errorInfo")
											.html(
													"允许最大选择数量为 <font style=\"font-weight:bold\">"
															+ this._options.modeCfg.maxSelected
															+ "</font> 个！");
									$(
											"#" + this._options.divPopId
													+ "_errorInfo").stop(true,
											true);
									$(
											"#" + this._options.divPopId
													+ "_errorInfo").animate( {
										opacity : 'show'
									}, 500).animate( {
										opacity : 'toggle'
									}, 1500).animate( {
										opacity : 'toggle'
									}, 500).animate( {
										opacity : 'toggle'
									}, 1500);
									if (this.chkNode != null
											&& typeof (this.chkNode) != "undefined") {
										this.chkNode.checked = false;
										this.chkNode.blur();
										$(this.chkNode).change();
									}
									return false;
								} else {
									$(
											"#" + this._options.divPopId
													+ "_errorInfo").hide();
									return true;
								}
							},
							getId : function(which) {
								switch (which) {
								case "body": {
									return this._options.divPopId;
								}
									break
								case "select": {
									var selects = $("div[id^='"
											+ this._options.divPopId
											+ "'] select");
									var ids = [];
									$.each(selects, function(i, n) {
										ids.push(n.id);
									});
									return ids;
								}
									break
								}
							},
							onSubmit : function() {
							},
							_onLoad : function() {
								if (this.onLoad != null
										&& typeof (this.onLoad) == "function") {
									this.onLoad();
								} else if (this.onLoadOnce != null
										&& typeof (this.onLoadOnce) == "function"
										&& !this.loaded) {
									this.onLoadOnce();
									this.loaded = true;
								}
							},
							onLoad : null,
							onLoadOnce : null
						});
		$.fn.areaGear = function(options) {
			var _areaGear = new areaGear();
			if (options.gearCustom != null) {
				var template = {
					url : "",
					dataPar : "",
					id : "",
					name : "",
					defkey : "",
					attVal : "",
					setDef : null,
					show : true
				};
				var fmCustom = new Array();
				$.each(options.gearCustom, function(i, n) {
					fmCustom[i] = $.extend(true, {}, template, n);
				});
				options.gearCustom = fmCustom;
				_areaGear._options.gearCustom = null;
			}
			$.extend(true, _areaGear._options, options);
			_areaGear.receiveObj = this;
			if (!$.areaGear.pageInitialized
					&& _areaGear._options.mode == "complete"
					&& _areaGear._options.modeCfg.page) {
				var pageJs = document.createElement("script");
				pageJs.setAttribute("type", "text/javascript");
				pageJs.setAttribute("src", "/script/mice/ajaxPage/page.js");
				pageJs.setAttribute("charset", "UTF-8");
				document.getElementsByTagName("head").item(0).appendChild(
						pageJs);
			}
			var t = setInterval(waitInit, 1);
			function waitInit() {
				if ($.page != null || !_areaGear._options.modeCfg.page) {
					clearInterval(t);
					if (!$.areaGear.pageInitialized) {
						var cssAreaGear = document.createElement("link");
						cssAreaGear.setAttribute("type", "text/css");
						cssAreaGear.setAttribute("rel", "stylesheet");
						cssAreaGear.setAttribute("href",
								"/script/mice/areaGear/areaGear.css");
						document.getElementsByTagName("head").item(0)
								.appendChild(cssAreaGear);
					}
					_areaGear.init();
					$.areaGear.pageInitialized = true;
				}
			}
			return _areaGear;
		};
		$.areaGear = {};
		$.areaGear.pageInitialized = false;
		$.areaGear.version = "2.0.0";
	})(jQuery);
}