/* 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
$(function(){
    var rootURL = "http://localhost:8080/Info5059Case2-war/webresources/";
    
    $("#POHide").hide();
    $("#CollapsiblePO").hide();
    
    $.getJSON(rootURL + "vendors/vendorlist", null, function(data, status, jqXHR){
       renderVendorList(data);
    }).error(function(jqXHR, textStatus, errorThrown){
        console.log(textStatus + " - " + errorThrown);
    });
    
    $('#chooseVendorPO').click(function(){
       $.getJSON(rootURL + "PO/getVendorPOS/" + $('#vendornos').val(), null, function(data,status,jqXHR){
           $("#POHide").show();
           renderVendorPosList(data);
       });
    });
    
    $('#choosePO').click(function(){
        $.getJSON(rootURL + "PO/getPO/" + $('#ponos').val(), null, function(data,status,jqXHR){
           $("#CollapsiblePO").show();
           renderPOData(data);
        }); 
    });
    
    //event handler for vendor# click
    $('#chooseVendor').click(function() {
        $.getJSON(rootURL + "getAVendor/" + $('#vendorno').val(),null,function(data, status, jqXHR) {
            renderVendorInfo(data);
        }).error(function(jqXHR, textStatus, errorThrown){
            console.log(textStatus + " - " + errorThrown);
        });
    });
    
});

function renderVendorList(data){
    $(data).each(function() {
        $('#vendornos').append("<option>" + this + "</option>");
    });
}

function renderPOData(data){
    collapse = $("<div>").attr({'data-role': "collapsible", 'id': "container"});
    var h3 = $("<h3>");
    h3.text("PO# " + data.poNumber + " - " + data.poDate);
    var label = $("<label>");
    label.text("pocode " + data.items[0].prodcd);
    //$('#pocode').text(this.items[0].prodcd);
    
    $(collapse).append(h3);
    $(collapse).append(label);
    $(collapse).appendTo($("#CollapsiblePO"));
    $(collapse).collapsible({refresh:true});
}

function renderVendorPosList(data){
    var po = document.getElementById("ponos");
    po.value = data[0];
    po.innerHTML = "";
    $(data).each(function() {
        $('#ponos').append("<option>" + this + "</option>");
    });
}

function renderVendorInfo(data){
$('#vendorName').text(data.name);
$('#vendorEmail').text(data.email);
$('#vendorPhone').text(data.phone);
$('#vendorType').text(data.type);
$('#vendorAddress').text(data.address1);
$('#vendorCity').text(data.city);
$('#vendorPostal').text(data.postalCode);
$('#vendorProvince').text(data.province);
}




