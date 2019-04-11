window.addEventListener('load', function() {


// ********************** Init Forms *************************
    create_forms("address");
    create_forms("person");
    create_forms("telephone");
    create_forms("telephoneType");
    create_forms("carrier");


//  ***************************  End Forms ******************************
});

function create_forms(table_name){
    document.getElementById(table_name + "_insert").onsubmit = function () {
        if(table_name == "person"){
            telephone_array = [];
            if($("#person_telephone").serializeFormJSON()) {
                telephone_array[0] = $("#person_telephone").serializeFormJSON();
            }
            if($("#person_telephone2").serializeFormJSON()) {
                telephone_array[1] = $("#person_telephone2").serializeFormJSON();
            }
            if($("#person_telephone3").serializeFormJSON()) {
                telephone_array[2] = $("#person_telephone3").serializeFormJSON();
            }
            console.log(telephone_array);
            console.log(JSON.stringify(telephone_array));
            document.getElementById("person_telephones_input").value = JSON.stringify(telephone_array);
            document.getElementById("person_address_field_input").value = JSON.stringify($("#person_address").serializeFormJSON());
        }

        request_data = JSON.stringify($(this).serializeFormJSON());
        request_data = request_data.replace(/\\/g, "");
        request_data = request_data.replace(/\"\[/g, "\[");
        request_data = request_data.replace(/\"{/g, "{");
        request_data = request_data.replace(/\]\"/g, "\]");
        request_data = request_data.replace(/}\"/g, "}");
        console.log(request_data);
        $.ajax({
            url: '/' + table_name,
            type: 'post',
            dataType: 'json',
            data: request_data,
            contentType: "application/json",
            success: function (data) {
                alert(JSON.stringify(data));
            },
            error: function (data) {
                console.log(data);
                alert('Status Code: ' + JSON.stringify(data['status']));
            }
        });
        return false;
    };


    document.getElementById(table_name + "_update").onsubmit = function () {
        req_id = $(this).getId();
        request_data = JSON.stringify($(this).serializeFormJSON());
        console.log(request_data);
        $.ajax({
            url: '/' + table_name +'/' + req_id,
            type: 'put',
            dataType: 'json',
            data: request_data,
            contentType: "application/json",
            success: function (data) {
                alert(JSON.stringify(data));
            },
            error: function (data) {
                console.log(data);
                alert('Status Code: ' + JSON.stringify(data['status']));
            }
        });
        return false;
    };

    document.getElementById(table_name + "_get").onsubmit = function () {
        req_id = $(this).getId();
        $.ajax({
            url: '/' + table_name +'/' + req_id,
            type: 'get',
            dataType: 'json',
            contentType: "application/json",
            success: function (data) {
                // alert(JSON.stringify(data));
                document.getElementById(table_name + "_get_results").innerHTML = JSON.stringify(data);

            },
            error: function (data) {
                console.log(data);
                alert('Status Code: ' + JSON.stringify(data['status']));
            }
        });
        return false;
    };

    document.getElementById( table_name + "_get_all").onsubmit = function () {
        $.ajax({
            url: '/' + table_name,
            type: 'get',
            dataType: 'json',
            contentType: "application/json",
            success: function (data) {
                // alert(JSON.stringify(data));
                document.getElementById(table_name + "_get_all_results").innerHTML = JSON.stringify(data);
            },
            error: function (data) {
                alert('Status Code: ' + JSON.stringify(data['status']));
            }
        });
        return false;
    };

    document.getElementById(table_name + "_delete_all").onsubmit = function () {
        $.ajax({
            url: '/' + table_name,
            type: 'delete',
            dataType: 'json',
            contentType: "application/json",
            success: function (data) {
                alert(JSON.stringify(data));
            },
            error: function (data) {
                alert('Status Code: ' + JSON.stringify(data['status']));
            }
        });
        return false;
    };

    document.getElementById(table_name + "_delete").onsubmit = function () {
        req_id = $(this).getId();
        $.ajax({
            url: '/' + table_name +'/' + req_id,
            type: 'delete',
            dataType: 'json',
            contentType: "application/json",
            success: function (data) {
                alert(JSON.stringify(data));
            },
            error: function (data) {
                console.log(data);
                alert('Status Code: ' + JSON.stringify(data['status']));
            }
        });
        return false;
    };
}


(function ($) {
    $.fn.serializeFormJSON = function () {

        //creating nested object from flat array. Use person.name to indicate nesting needed. something similar for arrays
        var obj = {};
        var array = this.serializeArray();
        $.each(array, function () {
                if (this.value) {
                    if (obj[this.name]) {
                        if (!obj[this.name].push) {
                            obj[this.name] = [obj[this.name]];
                        }
                        obj[this.name].push(this.value);
                    } else {
                        obj[this.name] = this.value;
                    }
                }
            }
        );
        return obj;
    };
})(jQuery);

(function ($) {
    $.fn.getId = function () {

        var a = this.serializeArray();
        return a[0]['value'];
    };
})(jQuery);