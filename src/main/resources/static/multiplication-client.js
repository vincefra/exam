function displayProducts() {
    $.ajax(
            {
                url: "/product/showall",
            }).then(function (data) {

        $('#edit-form').hide();
        $('#stats-body').empty();
        $('#stats').show();

        data.forEach(function (row)
        {
            $('#stats-body').append(
                    "<tr>" +
                    "<td>" + row.id + "</td>" +
                    "<td>" + row.brand + "</td>" +
                    "<td>" + row.model + "</td>" +
                    "<td>" + row.color + "</td>" +
                    "<td>" + row.price + "</td>" +
                    //"<td><button class='btn btn-info' onclick='displayProductInfo(" + JSON.stringify(row.description) + ")'>More info</button></td>" +
                    "<td><button class='btn btn-primary' onclick='editProduct(" + row.id + ")'>Edit</button></td></tr>");
            //"<td><button class='btn btn-success' onclick='addToCart(" + row.id + ")'>Add to order</button></td></tr>");
        });
        $("#purchase-button").hide();
        $("#product-button").hide();

    });
}

function editProduct(data) {
    var data = data;
    $.ajax({
        url: '/product/edit',
        type: 'POST',
        data: JSON.stringify(data),
        contentType: "application/json; charset=utf-8",
        dataType: "json",
        async: false,
        success: function (result)
        {
            $('#stats-body').empty();
            $('#stats').hide();
            $("#edit-form").show();

            document.getElementById('input0').value = result.id;
            document.getElementById('input1').value = result.brand;
            document.getElementById('input2').value = result.model;
            document.getElementById('input3').value = result.color;

            //$("#cart-button").hide();
            //$("#purchase-button").show();
            //$("#product-button").show();
            //$(".remove-button").show();
        }
    });
}

function displayProductInfo(data) {
    alert("Description - " + data);
}


function checkHistory(data) {
    var data = data;
    $.ajax({
        url: '/cart/check',
        type: 'POST',
        data: JSON.stringify(data),
        contentType: "application/json; charset=utf-8",
        dataType: "json",
        async: false,
        success: function (result)
        {
            $('#stats-admin').hide();
            $('#stats-body').empty();
            $('#stats').show();
            $("#admin-button").show();

            result.forEach(function (row)
            {
                $('#stats-body').append(
                        "<tr>" +
                        "<td>" + row.id + "</td>" +
                        "<td>" + row.brand + "</td>" +
                        "<td>" + row.model + "</td>" +
                        "<td>" + row.color + "</td>" +
                        "<td>" + row.price + "</td>");
            });
        }
    });
}

function displayForAdmin() {
    $.ajax(
            {
                url: "/product/showall",
            }).then(function (data) {

        $('#edit-form').hide();
        $('#stats-body').empty();
        $('#stats').show();

        data.forEach(function (row)
        {
            $('#stats-body').append(
                    "<tr>" +
                    "<td>" + row.id + "</td>" +
                    "<td>" + row.brand + "</td>" +
                    "<td>" + row.model + "</td>" +
                    "<td>" + row.color + "</td>" +
                    "<td>" + row.price + "</td>" +
                    //"<td><button class='btn btn-info' onclick='displayProductInfo(" + JSON.stringify(row.description) + ")'>More info</button></td>" +
                    "<td><button class='btn btn-primary' onclick='editProduct(" + row.id + ")'>Edit</button></td>" +
                    "<td><button class='btn btn-success' onclick='removeProduct(" + row.id + ")'>Remove</button></td></tr>");
        });
        $("#purchase-button").hide();
        $("#product-button").hide();

    });
}

/*
 function displayForAdmin() {
 $.ajax(
 {
 url: "/product/admin",
 }).then(function (data) {
 
 $('#stats-admin').show();
 $('#stats-admin-body').empty();
 $('#stats-body').empty();
 $('#stats').hide();
 $("#admin-button").hide();
 
 data.forEach(function (row) 
 {
 $('#stats-admin-body').append(
 "<tr>" + 
 "<td>" + row.id + "</td>" +
 "<td>" + row.name + "</td>" +
 "<td>" + row.username + "</td>" +
 "<td>" + row.roles + "</td>" +
 "<td><button class='btn btn-success' onclick='checkHistory(" + row.id + ")'>Purchase history</button></td></tr>");
 }); 
 $("#purchase-button").hide();
 $("#product-button").hide();
 
 });
 }
 */

function displayCart() {
    $.ajax({
        url: "/cart/products",
    }).then(function (data) {
        $('#stats-body').empty();
        data.forEach(function (row) {
            $('#stats-body').append('<tr><td>' + row.id + '</td>' +
                    '<td>' + row.name + '</td>' +
                    '<td>' + row.brand + '</td>' +
                    '<td>' + row.description + '</td>' +
                    '<td>' + row.price + '</td>' +
                    "<td><button class='btn btn-success remove-button' onclick='removeFromCart(" + row.id + ")'>Remove Product</button></td></tr>");
        });
        $("#cart-button").hide();
        $("#purchase-button").show();
        $("#product-button").show();
        $(".remove-button").show();



    });
}

/*
 * Gjorde om button till button onclick, den lästes annars som att den anropade metoden istället, det var aldrig en onclick
 * Gjorde om reg-button hide, det klassades som id istället (eller tvärtom, minns ej)
 * Kodade om addToCart att den hanterar row.id istället för objekt, fick problem annars
 * Kodade om serverdelen att den tar emot product id, söker upp i db och skapar upp nytt product objekt
 */

function hideCartButtons() {
    $("#cart-button").hide();
    $("#product-button").hide();
    $("#purchase-button").hide();
}

function preLogin() {
    $("#stats").hide();
    $("#stats-admin").hide();
    $("#reg-form").hide();
    $("#edit-form").hide();
    $("#result-message").hide();
    $("#product-button").hide();
    $("#purchase-button").hide();
    $("#cart-button").hide();
    $("#logout-button").hide();
    $("#admin-button").hide();
    $("#home-button").hide();
    $("#home-admin-button").hide();

}

function registerClick() {
    event.preventDefault();
    $("#login-form").hide();
    $("#reg-button").hide();
    $("#reg-form").show();
}

function purchase() {
    $.ajax(
            {
                url: "/cart/buy",
            }).then(function (data) {
        console.log(data.id);
        $("#result-message").show();
        if (data.id > 0) {
            $('#result-message').empty().append("Your orderid: " + data.id + " - Totalprice: " + data.totalprice + "USD");
            $("#login-form").hide();
            $(".reg-button").hide();
            $(".remove-button").hide();
            $(".stats").show();
        } else {
            $('.result-message').empty().append("No!");
        }
    });
}

function removeProduct(data) {
    var data = data;
    $.ajax({
        url: '/product/remove',
        type: 'POST',
        data: JSON.stringify(data),
        contentType: "application/json; charset=utf-8",
        dataType: "json",
        async: false,
        success: function (result) {
            console.log(result);
            if (result.id > 0) {
                displayForAdmin();
            } else {
                $("#result-message").show().delay(1000).fadeOut();
                ;
                $('.result-message').empty().append("Ooops something went wrong!");
            }
        }
    });
}


function addToCart(data) {
    var data = data;
    $.ajax({
        url: '/cart/add',
        type: 'POST',
        data: JSON.stringify(data),
        contentType: "application/json; charset=utf-8",
        dataType: "json",
        async: false,
        success: function (result) {
            console.log(result);
            $("#result-message").show();
            if (result.id > 0) {
                $('#result-message').empty().append("You added a product: " + result.productType);
                $("#login-form").hide();
                $(".reg-button").hide();
                $(".stats").show();
            } else {
                $('.result-message').empty().append("Ooops that's not correct! But keep trying!");
            }
        }
    });
}

$(document).ready(function () {

    preLogin();
    $("#login-form").submit(function (event) {

        // Don't submit the form normally
        event.preventDefault();

        // Get some values from elements on the page
        var $form = $(this),
                username = $form.find("input[name='user-name']").val(),
                password = $form.find("input[name='user-pass']").val();

        // Compose the data in the format that the API is expecting
        var data = {username: username, password: password};
        // Send the data using post
        $.ajax({
            url: '/login',
            type: 'POST',
            data: JSON.stringify(data),
            contentType: "application/json; charset=utf-8",
            dataType: "json",
            async: false,
            success: function (result) {
                console.log(result);
                if (result.username !== null)
                {
                    if (result.roles === "admin")
                    {
                        displayForAdmin();
                        $('#result-message').empty().append("You have logged in: " + result.username + " - Role: " + result.roles);
                        $("#login-form").hide();
                        $("#reg-button").hide();
                        $("#home-admin-button").show();
                        $("#logout-button").show();
                        //$("#stats-admin").show(); 
                    } else
                    {
                        displayProducts();
                        $('#result-message').empty().append("You have logged in: " + result.username + " - Role: " + result.roles);
                        $("#login-form").hide();
                        $("#reg-button").hide();
                        //$("#cart-button").show();
                        $("#logout-button").show();
                        $("#home-button").show();
                        $("#stats").show();
                    }
                } else
                {
                    $('#result-message').empty().append("Ooops that's not correct! But keep trying!");
                }
            }
        });
    });


    $("#reg-form").submit(function (event) {

        // Don't submit the form normally
        event.preventDefault();
        // Get some values from elements on the page
        var $form = $(this),
                name = $form.find("input[name='name']").val(),
                username = $form.find("input[name='user-name']").val(),
                password = $form.find("input[name='user-pass']").val();
        // Compose the data in the format that the API is expecting
        var data = {name: name, username: username, password: password};
        // Send the data using post
        $.ajax({
            url: '/registration',
            type: 'POST',
            data: JSON.stringify(data),
            contentType: "application/json; charset=utf-8",
            dataType: "json",
            async: false,
            success: function (result) {
                console.log(result);
                if (result.username !== null) {
                    $('#result-message').empty().append("You have been registered");
                    $("#reg-form").hide();
                    $("#login-form").show();
                    $("#reg-button").show();
                } else {
                    $('#result-message').empty().append("Ooops there is already a user!");
                }
            }
        });
    });


    $("#edit-form").submit(function (event) {

        // Don't submit the form normally
        event.preventDefault();

        // Get some values from elements on the page
        var $form = $(this),
                id = $form.find("input[name='edit-id']").val(),
                brand = $form.find("input[name='edit-brand']").val(),
                model = $form.find("input[name='edit-model']").val(),
                color = $form.find("input[name='edit-color']").val();

        // Compose the data in the format that the API is expecting
        var data = {id: id, brand: brand, model: model, color: color};

        // Send the data using post
        $.ajax({
            url: '/product/update',
            type: 'POST',
            data: JSON.stringify(data),
            contentType: "application/json; charset=utf-8",
            dataType: "json",
            async: false,
            success: function (result) {
                console.log(result);
                $('#result-message').show().delay(1000).fadeOut();

                if (result.brand !== null) {
                    $('#result-message').empty().append("Product has been updated!");
                } else {
                    $('#result-message').empty().append("Ooops something went wrong!");
                }
            }
        });
    });

});
