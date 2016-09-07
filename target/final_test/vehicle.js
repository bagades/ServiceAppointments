/**
 * Created by agrawaay on 9/1/2016.
 */
function logout() {
    $.ajax({
        type:"post",

        url:"serviceAppointments/logout/"+sessionStorage.id,
        success: function (result) {
            window.history.forward(-1);
            sessionStorage.id = null;
            window.location.replace("/login.html");

        },
        error:function (result) {
            alert("Problem in Logging out");
            window.location.replace("login.html");
        }
    })
}
function showRecords() {

    $.ajax({
        type: "post",
        url:"serviceAppointments/customerVehicleList/"+sessionStorage.id,
        dataType:"text",
        success: function(response){
            if(typeof records === null) {
                window.location.replace("/login.html");
                return;
            }
            records=JSON.parse(response);
            var htmlText = "<h2>Registered Vehicles</h2>" +
                "<table class='table table-bordered' id='myTable' border='2'>" +
                "<thread>" +
                "<tr>" +
                "<th><h4>Vehicle VIN</h4></th>" +
                "<th><h4>Model</h4></th>" +
                "<th><h4>Company</h4></th>" +
                "<th><h4>Previous Servicing Date</h4></th>" +
                "<th><h4>Service Subscription</h4></th>" +
                "<th><h4>Book Appointment</h4></th>"+
                "</tr>" +
                "</thread>" +
                "<tbody>";
            for(var i = 0; i < records.Vehicles.length;i++){
                if($.type(records.Vehicles[i].last_servicing_date)==="null"){
                    records.Vehicles[i].last_servicing_date = "NA";
                }
                htmlText += "<tr>" +
                    "<td>"+records.Vehicles[i].vin+
                    "</td><td>" + records.Vehicles[i].model +
                    "</td><td>" + records.Vehicles[i].company+
                    "</td><td>" + records.Vehicles[i].last_servicing_date+
                    "</td><td><select id='serviceType_"+records.Vehicles[i].vin+"'>" +
                    "<option value='Regular'>Regular</option>" +
                    "<option value='Premium'>Premium</option>" +
                    "<option value='Repair'>Repair</option>" +
                    "</select> " +
                    "</td><td><input type='date' name='date' id='"+ records.Vehicles[i].vin +"' onchange='hide("+records.Vehicles[i].vin+");'><button class='btn btn-primary' onclick='checkAvailability("+records.Vehicles[i].vin+")'>Check Availability</button>" +
                    "&nbsp;&nbsp;&nbsp;&nbsp;<Button class='btn btn-primary' type='button' name='bookButton' id='button_"+ records.Vehicles[i].vin+"'value='"+ records.Vehicles[i].vin +"' disabled style='visibility: hidden' onclick='selectCar("+records.Vehicles[i].vin+");'>Book</Button></td></tr>";
            }


            htmlText +="</tbody>" + "</table>";
            htmlText +="<Button type='button' name='Add' class='btn btn-primary' onclick='displayForm();'>Register New Vehicle</Button>"
            document.getElementById("showData").innerHTML = htmlText;

        },
        error: function(response){
            document.getElementById("showData").innerHTML = "<h2>No records available</h2>";
        }
    });


}

function displayForm() {
    var vehicleForm = document.getElementById("vehicleForm");
    vehicleForm.style.visibility = "visible"
    document.getElementById("done").style.visibility = "visible";
}

function addRecord() {
   var data = {
        "vin":document.vehicleForm.vin.value,
       "model":document.vehicleForm.model.value,
       "company": document.vehicleForm.company.value,
       "dateOfPurchase": document.vehicleForm.dateOfPurchase.value
   };
   data = JSON.stringify(data);
    $.ajax({
        type: "post",
        url:"serviceAppointments/addVehicle/"+sessionStorage.id,
        data:data,
        processData: false,
        contentType: "application/json",
        dataType:"json",
        success: function(response){
            if(typeof response === null) {
                window.location.replace("/login.html");
                return;
            }
            document.getElementById("availableStatus").innerHTML += "<h2>Vehicle Successfully Registered</h2>";
        },
        error: function(response){
            document.getElementById("availableStatus").innerHTML += "<h2>Please check details</h2>";
        }
    });

}
function selectCar(id){
    var val = $("#serviceType_"+id).val();
    if (val.localeCompare("Regular")==0||val.localeCompare("Premium")==0||val.localeCompare("Repair")==0){
        $.ajax({
            type: "post",
            url:"serviceAppointments/addVehicleForService/"+sessionStorage.id+"&"+id+"&"+$("#"+id).val()+"&"+val,
            dataType:"text",
            success: function(response){
                if(response === null){
                    window.location.replace("/login.html");
                    return;
                }
                $("#availableStatus").html(response);
            },
            error: function(response){
                $("#availableStatus").html("All bookings for this date have been exhausted<br>Please choose another date</br>");
            }
        });
    }
    else{
        $("#availableStatus").html("Pls select service");
    }

}

function checkAvailability(id){
    $.ajax({
            type: "get",
            url:"serviceAppointments/checkAvailable/"+sessionStorage.id+"&"+$("#"+id).val(),
            dataType:"text",
            success: function(response){
                if(response === null){
                    window.location.replace("/login.html");
                    return;
                }
                if(response == "Slot available for booking.") {
                    $("#button_" + id).prop('disabled', false);
                    $("#button_" + id).css('visibility', 'visible');
                }
                else{
                    $("#button_" + id).prop('disabled', true);
                    $("#button_" + id).css('visibility', 'hidden');
                }
                $("#availableStatus").html(response);
            },
            error: function(response){
                $("#availableStatus").html("Select Date");
            }

    });
}
function showHistory(){
    $.ajax({
        type: "post",
        url:"serviceAppointments/customerVehicleList/"+sessionStorage.id,
        dataType:"text",
        success: function(response){
            if(typeof records === null) {
                window.location.replace("/login.html");
                return;
            }
            records=JSON.parse(response);
            var htmlText = "<h2>Booked Appointments History</h2>" +
                "<div id='showData1' style='color: #1f30ff'></div> " +
                "<table class='table' id='myTable' border='2'>" +
                "<thread>" +
                "<tr>" +
                "<th><h4>Vehicle VIN</h4></th>" +
                "<th><h4>Model</h4></th>" +
                "<th><h4>Company</h4></th>" +
                "<th><h4>Previous Servicing Date</h4></th>" +
                "<th><h4>Status</h4></th>" +
                "</tr>" +
                "</thread>" +
                "<tbody>";
            for(var i = 0; i < records.Vehicles.length;i++){
                if($.type(records.Vehicles[i].last_servicing_date)==="null"){
                    records.Vehicles[i].last_servicing_date = "NA";
                }
                htmlText += "<tr>" +
                    "<td>"+records.Vehicles[i].vin+
                    "</td><td>" + records.Vehicles[i].model +
                    "</td><td>" + records.Vehicles[i].company+
                    "</td><td>" + records.Vehicles[i].last_servicing_date+
                    "</td><td>" +records.Vehicles[i].status+
                    "</td></tr>";
            }


            htmlText +="</tbody>" + "</table>";

            document.getElementById("showData").innerHTML = htmlText;

        },
        error: function(response){
            document.getElementById("showData").innerHTML = "<h2>No records to show</h2>";
        }
    });


}
function checkLogin() {
    if(sessionStorage.id == null){
        window.history.forward(-1);
        window.location.replace("/login.html");
    }
}
function hide(id){
  //  $("#button_" + id).prop('disabled', true);
    $("#button_" + id).prop('readOnly', true);
    $("#button_" + id).css('visibility', 'hidden');
    $("#availableStatus").html("");
}