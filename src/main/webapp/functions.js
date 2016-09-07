function login() {
    var login={
        "email":document.forms[0].email.value,
        "password":document.forms[0].password.value
    };
    login = JSON.stringify(login);
    $.ajax({
        type: "post",
        url:"/serviceAppointments/login",
        data:login,
        processData: false,
        contentType: "application/json",
        dataType:"json",
        success: function(response){
            if(response === null){
                window.location.replace("/login.html");
                return;
            }
            sessionStorage.id = response.customerId;
            if(response.type == "admin")
                window.location.replace("/adminView.html");
            else
            window.location.replace("/vehicleView.html");

        },
        error: function(response){
            $("#loginstatus").html("Invalid Details");
        }

    })

}

function register() {
    var data = {
        "name":document.forms[1].name.value,
        "email":document.forms[1].email.value,
        "password":document.forms[1].password.value,
        "phoneNo":document.forms[1].phoneNo.value,
        "address":document.forms[1].address.value,
        "city":document.forms[1].city.value,
        "state":document.forms[1].state.value,
        "postalCode":document.forms[1].postalCode.value,
    };
    data = JSON.stringify(data);
    $.ajax({
        type: "post",
        url:"/serviceAppointments/add",
        data:data,
        processData: false,
        contentType: "application/json",
        dataType:"json",
        success: function(response){
            alert("Registered successfully. Please login to continue");
            window.location.replace("/login.html");
        },
        error: function(response){
            $("#registerStatus").html("Email ID already exists");
        }

    })

}
function getServiceVehicles(){
    var date = $("#date").val();
    $("#show2").html("");
    $.ajax({
        type: "post",
        url:"serviceAppointments/getDetailsByDate/"+date,
        dataType:"text",
        success: function(response){
            if(typeof records === null) {
                window.location.replace("/login.html");
                return;
            }
            records=JSON.parse(response);
            var htmlText = "<table class='table myTable'><thead> <tr> <th>ServiceID</th> <th>Date Of Service</th> " +
                "<th>Service Type</th> <th>Status</th> " +
                "<th>Name</th> <th>Address</th><th>Service Status</th> </tr> </thead>";
            htmlText+="<tbody id='my-tbody'>";
            for(var i = 0; i < records.Vehicles.length;i++){
                htmlText += "<tr><td>"+records.Vehicles[i].serviceId +
                    "</td><td>" + records.Vehicles[i].dateOfService +
                    "</td><td>" + records.Vehicles[i].type+
                    "</td><td>" + records.Vehicles[i].status+
                    "</td><td>" + records.Vehicles[i].name+
                    "</td><td>" + records.Vehicles[i].address+
                    "</td><td><button type='button' class='btn btn-primary' onclick='update("+records.Vehicles[i].serviceId+");'>Update</button> "+
                    "</td></tr>";
            }

            htmlText +="</tbody>" + "</table>";
            document.getElementById("show").innerHTML = htmlText;

        },
        error: function(response){
            document.getElementById("show").innerHTML = "No records available";
        }
    });

}
function update(serviceId){
    htmlText = "Service Id:<input type='text' id='service_id' value='"+serviceId+"'  readonly disabled>";
    htmlText += "<br>Select Status:<br> <input type='radio' name='status' value='Requested'>Requested";
    htmlText +="<br> <input type='radio' name='status' value='Rejected'>Rejected";
    htmlText += "<br> <input type='radio' name='status' value='Started'>Started";
    htmlText += "<br> <input type='radio' name='status' value='Completed'>Completed";
    htmlText +="<br> <input type='button' class='btn btn-primary' name='update' value='Submit' onclick='updateDetails();'>";
    document.getElementById("show").innerHTML = htmlText;
}
function updateDetails() {
    var service_id = $("#service_id").val();
$.ajax({
    type:"post",
    url:"serviceAppointments/updateStatus/"+service_id+"&"+$('input[name=status]:checked').val(),
    dataType:"text",
    success:function (result) {
        document.getElementById("show2").innerHTML = result;
    },
    error:function (result) {
        document.getElementById("show2").innerHTML = "error";
    }
});
}