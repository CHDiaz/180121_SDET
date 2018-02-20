function allReimAJAX(){
	var xhr = new XMLHttpRequest(); 
	xhr.open("GET","getreimburse"); 

	xhr.onreadystatechange = function(){
		if(xhr.readyState == 4 && xhr.status == 200){
		
			var xmlText = xhr.responseXML;
			var response = xmlText.getElementsByTagName("reimbursement");
			//response is a collection of all reimbursement tags.
			var resultTable = document.getElementById("reimtable");
			//variable referencing our end table.
			
			
			var table = document.createElement("table");
			 table.setAttribute("border","4px");
			 table.setAttribute("id","reimbursementtable");
			var row1st = document.createElement("tr");
			
			var	til1 = document.createElement("th"); // rei_id
			var text1 = document.createTextNode("REIMBURSEMENT_ID");
			til1.appendChild(text1);
			
			var	til2 = document.createElement("th"); // date
			var text2 = document.createTextNode("DATE OF EVENT");
			til2.appendChild(text2);
			
			var	til3 = document.createElement("th"); // time
			var text3 = document.createTextNode("TIME OF EVENT");
			til3.appendChild(text3);
			
			var	til4 = document.createElement("th"); // location
			var text4 = document.createTextNode("LOCATION OF EVENT");
			til4.appendChild(text4);
			
			var	til5 = document.createElement("th"); // desc
			var text5 = document.createTextNode("DESCRIPTION");
			til5.appendChild(text5);
			
			var	til6 = document.createElement("th"); // cost
			var text6 = document.createTextNode("COST");
			til6.appendChild(text6);
			
			var	til7 = document.createElement("th"); // grading format
			var text7 = document.createTextNode("GRADING FORMAT");
			til7.appendChild(text7);
			
			var	til8 = document.createElement("th"); // type
			var text8 = document.createTextNode("TYPE OF EVENT");
			til8.appendChild(text8);
			
			var	til9 = document.createElement("th"); // work
			var text9 = document.createTextNode("WORK RELATED JUSTIFCATION");
			til9.appendChild(text9);
			
			var	til10 = document.createElement("th"); // approval status
			var text10 = document.createTextNode("APPROVAL STATUS");
			til10.appendChild(text10);
			
			var	til11 = document.createElement("th"); // note
			var text11 = document.createTextNode("INTERAL NOTES");
			til11.appendChild(text11);
			
			row1st.appendChild(til1);
			row1st.appendChild(til2);
			row1st.appendChild(til3);
			row1st.appendChild(til4);
			row1st.appendChild(til5);
			row1st.appendChild(til6);
			row1st.appendChild(til7);
			row1st.appendChild(til8);
			row1st.appendChild(til9);
			row1st.appendChild(til10);
			row1st.appendChild(til11);
			
			table.appendChild(row1st);
			
			for(e in response){
				
				var row = document.createElement("tr");
				var td1 = document.createElement("td");
				var td2 = document.createElement("td");
				var td3 = document.createElement("td");
				var td4 = document.createElement("td");
				var td5 = document.createElement("td");
				var td6 = document.createElement("td");
				var td7 = document.createElement("td");
				var td8 = document.createElement("td");
				var td9 = document.createElement("td");
				var td10 = document.createElement("td");
				var td11 = document.createElement("td");
			
				td1.innerHTML = response[e].childNodes[0].innerHTML;
				td2.innerHTML = response[e].childNodes[1].innerHTML;
				td3.innerHTML = response[e].childNodes[2].innerHTML;
				td4.innerHTML = response[e].childNodes[3].innerHTML;
				td5.innerHTML = response[e].childNodes[4].innerHTML;
				td6.innerHTML = response[e].childNodes[5].innerHTML;
				td7.innerHTML = response[e].childNodes[6].innerHTML;
				td8.innerHTML = response[e].childNodes[7].innerHTML;
				td9.innerHTML = response[e].childNodes[8].innerHTML;
				td10.innerHTML = response[e].childNodes[9].innerHTML;
				td11.innerHTML = response[e].childNodes[10].innerHTML;
				
				row.appendChild(td1);
				row.appendChild(td2);
				row.appendChild(td3);
				row.appendChild(td4);
				row.appendChild(td5);
				row.appendChild(td6);
				row.appendChild(td7);
				row.appendChild(td8);
				row.appendChild(td9);
				row.appendChild(td10);
				row.appendChild(td11);
				
				table.appendChild(row);
				
			}
			
			resultTable.appendChild(table);
			
		}else if(xhr.readyState == 4 && xhr.status!=200){
		
		}
	

	}
	
	xhr.send(); 
}

function costAJAX(){
	if (generation != 0) {
		clearTable("costtabletable");
		total = 0;
	}
	
	var xhr = new XMLHttpRequest(); 
	xhr.open("GET","Cost"); 
	xhr.onreadystatechange = function(){
		if(xhr.readyState == 4 && xhr.status == 200){
			
			var table = document.createElement("table");
			 table.setAttribute("border","4px");
			 table.setAttribute("id","costtabletable");
			 
			 var title = document.createElement("tr");
			 var td2 = document.createElement("td")
			 var text = document.createTextNode("AVIALABLE REIMBURSEMENT AMOUNT : ");
			 td2.appendChild(text);
			 title.appendChild(td2);
			 table.appendChild(title);
			 
			var xmlText = xhr.responseXML;
			var response = xmlText.getElementsByTagName("amount");
			var resultTable = document.getElementById("costtable");
				resultTable.setAttribute("class","well");
		
				var row = document.createElement("tr");
				var td1 = document.createElement("td");
				
				console.log(response[0].childNodes[0].innerHTML);
				td1.innerHTML = response[0].childNodes[0].innerHTML;

				row.appendChild(td1);

				table.appendChild(row);
				resultTable.appendChild(table);
				
				generation = 1;
			
		}else if(xhr.readyState == 4 && xhr.status!=200){
			console.log("ERROR, STATUS: " + xhr.status);
			document.getElementById("AJAXError").innerHTML=xhr.status;
		}
		
		
	}
	
	xhr.send(); 
}

var generation = 0;
function clearTable(elementID) {document.getElementById(elementID).remove();}