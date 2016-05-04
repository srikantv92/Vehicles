$( document ).ready(function() {
   updateList();
});

function updateList(){
	 $.ajax({
    url: '/webapi/getData',
    type: 'GET',
	async: true,
    crossDomain: true,
    dataType: 'json',
    success: function(result){
		var i=1;
		console.log("number of results "+result.results.length);
		if(result.results.length==0){
			$('#list').append("<a href=\"#\" class=\"list-group-item\"> No Results Found! </a>");
		}
		 $.each(result.results,function(key,value){
			
				 $('#list').append("<a href=\"#\" onclick=\" makeElementActive('"+"item"+value.id+"')\" id='item"+value.id+"' class=\"list-group-item\">"+value.make+" - "+value.model+" ("+value.year+")"+"</a>");
				 console.log(value.make + " "+ value.id );
				 i++;
		 });
	}
	});
}

function makeElementActive(id){
	
	$(".list-group-item").removeClass("active");
	$("#"+id).addClass("active");
	var valu = $("#"+id).html();

	var value=valu.split(" ");
	
		console.log("val "+value);
	document.getElementById("year").value=value[3].substring(1,value[3].length-1);
	console.log(value[3].substring(1,value[3].length-1));
	document.getElementById("make").value=value[0].substring(0,value[0].length);
	document.getElementById("model").value=value[2].substring(0,value[2].length);
}

function submitUpdates(){
	try {
	var id = document.getElementsByClassName('active')[0].id;
	}
	catch(er){
		alert("Please select vehicle to be updated from the list of vehicles");
		return;
	}
	id= id.substring(4,id.length);
	console.log(id+" req");
	var request = {"id":id, "model":document.getElementById('model').value, "make":document.getElementById('make').value, "year":document.getElementById('year').value};
	console.log(request.id+" req");
	console.log(request.model+" req");
	console.log(request.make+" req");
	console.log(request.year+" req");
	$.ajax({
    url: '/webapi/getData/',
    type: 'PUT',
	data: JSON.stringify(request),	
	contentType: "application/json",
	async:true,
    dataType: 'json',

    success: function(result){
		console.log("response "+result.status);
		  $('#list').html('');
		  updateList();
	}
	});
	document.getElementById('model').value = '';
	document.getElementById('make').value = '';
	document.getElementById('year').value = '';
	
}

function submitInsertion(){
var request = { "model":document.getElementById('modelIns').value, "make":document.getElementById('makeIns').value, "year":document.getElementById('yearIns').value};	
$.ajax({
    url: '/webapi/getData/',
    type: 'POST',
	async:true,
	data: JSON.stringify(request),	
	contentType: "application/json",
    dataType: 'json',

    success: function(result){
		console.log("response "+result.status);
		if(result.status == "failed"){
			alert("Failed to insert the vehicle details in database. Please try again.");
		}
		  $('#list').html('');
		  updateList();
	}
	});
	document.getElementById('modelIns').value = '';
	document.getElementById('makeIns').value = '';
	document.getElementById('yearIns').value = '';
	
}

function submitDelete(){
	try{
	var id = document.getElementsByClassName("active")[0].id;
	}
	catch(ex){
		alert("Please select vehicle to be deleted from the List of Vehicles");
		return;
	}
	id=id.substring(4,id.length);
	console.log("id is "+id);
	$.ajax({
    url: '/webapi/getData/'+id,
    type: 'DELETE',
	async:true,
    dataType: 'json',
    success: function(result){
		console.log("response "+result.status);
		  $('#list').html('');
		  updateList();
	},
	error: function(e){
		alert(e);
	}
	});
	document.getElementById('model').value = '';
	document.getElementById('make').value = '';
	document.getElementById('year').value = '';
	
}

function validateUpdate(){
	var model = document.getElementById('model').value;
	var make = document.getElementById('make').value;
	var year = document.getElementById('year').value;
	if(model.length == 0){
		document.getElementById('model').style.border="thin solid #9c1826";
		return;
	}
	else{
		document.getElementById('model').style.border="1px solid #ccc";
		
	}
	if(make.length == 0){
		document.getElementById('make').style.border="thin solid #9c1826";
		return;
	}
	else{
		document.getElementById('make').style.border="1px solid #ccc";
		
	}
	if(year.length == 0){
		document.getElementById('year').style.border="thin solid #9c1826";
		return;
	}
	else{
		document.getElementById('year').style.border="1px solid #ccc";	
	}
	if(year>2050 || year<1950){
		document.getElementById('year').value='';
		document.getElementById('year').placeholder = "Enter year between 1950 and 2050";
		document.getElementById('year').style.border="thin solid #9c1826";
		return;
	}
	else{
		document.getElementById('year').style.border="1px solid #ccc";
		document.getElementById('year').placeholder='';
	}
	submitUpdates();
}

function validateDelete(){
	var model = document.getElementById('model').value;
	var make = document.getElementById('make').value;
	var year = document.getElementById('year').value;
	if(model.length == 0){
		document.getElementById('model').style.border="thin solid #9c1826";
		return;
	}
	else{
		document.getElementById('model').style.border="1px solid #ccc";
		
	}
	if(make.length == 0){
		document.getElementById('make').style.border="thin solid #9c1826";
		return;
	}
	else{
		document.getElementById('make').style.border="1px solid #ccc";
		
	}
	if(year.length == 0){
		document.getElementById('year').style.border="thin solid #9c1826";
		return;
	}
	else{
		document.getElementById('year').style.border="1px solid #ccc";	
	}
	submitDelete();
}

function validateInsertion(){
	var modelIns = document.getElementById('modelIns').value;
	var makeIns =  document.getElementById('makeIns').value;
	var yearIns =  document.getElementById('yearIns').value;
	if(modelIns.length == 0){
		document.getElementById('modelIns').style.border="thin solid #9c1826";
		return;
	}
	else{
		document.getElementById('modelIns').style.border="1px solid #ccc";
		
	}
	if(makeIns.length == 0){
		document.getElementById('makeIns').style.border="thin solid #9c1826";
		return;
	}
	else{
		document.getElementById('makeIns').style.border="1px solid #ccc";
		
	}
	if(yearIns.length == 0){
		document.getElementById('yearIns').style.border="thin solid #9c1826";
		return;
	}
	else{
		document.getElementById('yearIns').style.border="1px solid #ccc";	
	}
	if(yearIns>2050 || yearIns<1950){
		document.getElementById('yearIns').value='';
		document.getElementById('yearIns').placeholder = "Enter year between 1950 and 2050";
		document.getElementById('yearIns').style.border="thin solid #9c1826";
		return;
	}
	else{
		document.getElementById('yearIns').style.border="1px solid #ccc";
		document.getElementById('yearIns').placeholder='';
	}
	submitInsertion();
}
function filter(){
	$('#list').html('');
	filtered();
}
function filtered(){
	var modelFil = document.getElementById('modelFil').value;
	var makeFil =  document.getElementById('makeFil').value;
	var yearFil =  document.getElementById('yearFil').value;
	var ur = 'http://localhost:8080/Vehicles/webapi/getData/query?';
	var i=0;
	if(yearFil.length>0){
		ur=ur+"year="+yearFil;
		i++;
	}
	if(makeFil.length>0){
		if(i==0){
			ur=ur+"make="+makeFil;
		}
		else{
			ur=ur+"&make="+makeFil;
		}
	}
	if(modelFil.length>0){
		if(i==0){
			ur=ur+"model="+modelFil;
		}
		else{
			ur=ur+"&model="+modelFil;
		}
	}
	if(modelFil.length==0 && yearFil.length==0 && makeFil.length==0 ){
		updateList();
		return;
	}
	console.log("url "+ur);
	$.ajax({
    url: ''+ur,
    type: 'GET',
	async:true,
    dataType: 'json',
    success: function(result){
		console.log("response "+result.status);
		if(result.status == "failed"){
			alert("Failed to insert the vehicle details in database. Please try again.");
		}
		if(result.results.length==0){
			$('#list').append("<a href=\"#\" class=\"list-group-item\"> No Results Found! </a>");
		}
		 $.each(result.results,function(key,value){
				console.log("id "+value.id);
				 $('#list').append("<a href=\"#\" onclick=\" makeElementActive('"+"item"+value.id+"')\" id='item"+value.id+"' class=\"list-group-item\">"+value.make+" - "+value.model+" ("+value.year+")"+"</a>");
				 
				 
		 });
	}
	});
	
}