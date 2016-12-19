<%@page language="java" contentType="text/html; charset=utf-8"%>
<link rel="stylesheet" type="text/css" href="/js/areaGear/areaGear.css" />
<link rel="stylesheet" type="text/css" href="/css/product/product.css" />
<script src="js/category/jquery.category.js" type="text/javascript" charset="utf-8"></script>
<script src="js/category/jquery.linkage.js" type="text/javascript" charset="utf-8"></script>
<script src="js/category/jquery.pop.js" type="text/javascript" charset="utf-8"></script>
<div class="outLayer" id="cateLayer" style="width:530px;display:none;">
  <h3>请选择产品目录</h3>
  <div class="outCon">
    <div id="cataLog">
      <select size="20" style="width:160px;height:400px;" id="cate_0"></select>
      <select size="20" style="width:160px;height:400px;" id="cate_1"></select>
      <select size="20" style="width:160px;height:400px;" id="cate_2"></select>
      <div class="cl"></div>
      <div class="tResult" id="selectedCate">您尚未选中任何一项</div>
   </div>
  </div>
  <div class="outBtn">
    <button type="submit" class="fixed" id="okCateLayer"></button>
    <button type="submit" class="cancel" id="closeCateLayer"></button>
  </div>
</div>