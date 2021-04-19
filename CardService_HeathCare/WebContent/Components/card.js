$(document).ready(function() 
{
	$("#alertSuccess").hide();
	$("#alertError").hide();
});

// save=============================================================================
$(document).on("click", "#btnSave", function(event) 
		{
	// Clear alerts---------------------
	$("#alertSuccess").text("");
	$("#alertSuccess").hide();
	$("#alertError").text("");
	$("#alertError").hide();

	// Form validation-------------------
	var status = validateCardForm();
	if (status != true) 
{
		$("#alertError").text(status);
		$("#alertError").show();
		return;
	}
	
	//If valid
	var type = ($("#hidCardIDSave").val() == "") ? "POST" : "PUT";

	$.ajax(
	{
		url : "CardsAPI",
		type : type,
		data : $("#formCard").serialize(),
		dataType : "text",
		complete : function(response, status) 
		{
			onCardSaveComplete(response.responseText, status);
		}
	});
		});

function onCardSaveComplete(response, status) 
{
	if (status == "success")  
	{  
		var resultSet = JSON.parse(response); 

if (resultSet.status.trim() == "success")   
{   
	$("#alertSuccess").text("Successfully saved.");  
	$("#alertSuccess").show(); 

 $("#divCardsGrid").html(resultSet.data); 
 } else if (resultSet.status.trim() == "error")   
 {    
	 $("#alertError").text(resultSet.data);   
	 $("#alertError").show();  
	 } 

} else if (status == "error")  
{  
	$("#alertError").text("Error while saving.");  
	$("#alertError").show(); 
	} else 
	{   
		$("#alertError").text("Unknown error while saving.."); 
 $("#alertError").show();  
 } 

$("#hidCardIDSave").val(""); 
$("#formCard")[0].reset(); 
}

//update=================
$(document).on("click", ".btnUpdate", function(event) {  
    $("#hidCardIDSave").val($(this).closest("tr").find('#hidCardIDUpdate').val());  
    $("#customerID").val($(this).closest("tr").find('td:eq(0)').text());    
    $("#cardNo").val($(this).closest("tr").find('td:eq(1)').text());   
    $("#cardName").val($(this).closest("tr").find('td:eq(2)').text());    
    $("#cardType").val($(this).closest("tr").find('td:eq(3)').text()); 
}); 


//remove
$(document).on("click", ".btnRemove", function(event)
		{  
	$.ajax( 
			{   
				url : "CardsAPI", 
				type : "DELETE",  
				data : "id=" + $(this).data("cardid"),
				dataType : "text", 
				complete : function(response, status)   
				{    
					onCardDeleteComplete(response.responseText, status);  
					}
			}); 
	}); 

function onCardDeleteComplete(response, status)
{ 
	if (status == "success") 
	{  
		var resultSet = JSON.parse(response); 

if (resultSet.status.trim() == "success") 
{   
	$("#alertSuccess").text("Successfully deleted.");  
	$("#alertSuccess").show(); 

 $("#divCardsGrid").html(resultSet.data);   
 } else if (resultSet.status.trim() == "error")  
 {   
	 $("#alertError").text(resultSet.data);  
	 $("#alertError").show();   
	 } 

} else if (status == "error") 
{ 
	$("#alertError").text("Error while deleting."); 
	$("#alertError").show();  
	} else 
	{   
		$("#alertError").text("Unknown error while deleting.."); 
		$("#alertError").show(); 
		} 
	} 



//-------------------------------------------------------------------------------------------------------------------------------------------------------------- 
//--------------------------------------validation-------------------------------------------------------------------------------------------------------- 

function validateCardForm() {  
	
	// customer id  
	if ($("#customerID").val().trim() == "") 
	{  
		return "customerID."; 
	} 
	//  customer id  is numerical value  
	var cusID = $("#customerID").val().trim(); 
	if (!$.isNumeric(cusID)) 
	{  
		return "Insert your customer number correctly and it should be numerical value.";  
		} 
	
	
	if(cusID.length != 5) { 
		return "Your customer id is invalid !";  
	} 
	 
	
//-------------------------------------------------------------------------------------------------------------------------------------------------------
	
	
	//card number
	 
	if ($("#cardNo").val().trim() == "") 
	{  
		return "Insert your card number.";  
	} 
	 
//  card no is numerical value  
	var card = $("#cardNo").val().trim(); 
	if (!$.isNumeric(card)) 
	{  
		return "Insert your card number correctly and it should be numerical values.";  
		} 
	

	
	if(cusID.length != 10) 
	{ 
		return "Your card number is invalid !";  
	} 
	 
	
	 	 	 
	 //---------------------------------------------------------------------------------------------------------------------------------------------------
	
	// name
	if ($("#cardName").val().trim() == "") 
	{  
		return "Insert card name."; 
	} 
	
	//-----------------------------------------------------------------------------------------------------------------------------------------------------	
	// type  
	if ($("#cardType").val().trim() == "") 
	{  
		return "Insert card type."; 
	} 
	
	
	
 return true;
 } 
 
 

	






























