
//For displayen products

function displayProducts() {
    $.ajax(
            {
                url: "/product/showall",
            }).then(function (data) {

        //Hide edit-form and empty stats to show new data
        $('#edit-form').hide();
        $('#stats-body').empty();
        $('#stats').show();

        //Loop thru received data and display into our tr, td
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

        //Hide not needed buttons
        $("#purchase-button").hide();
        $("#product-button").hide();
    });
}

//For editing product
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
            document.getElementById('input4').value = result.price;
        }
    });
}

//Show a dialog with info of specific product
function displayProductInfo(data) {
    alert("Description - " + data);
}

//Show history of cart
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

//View for admin, customised with being able to remove product
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

//View for cart, when user have added products into cart, this function is not available for this demo
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

//Not available for this demo
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

//Remove product
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

//Not available for this demo
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

//To catch submit events
$(document).ready(function () {

    preLogin();

    $("#searchField").on("keyup", function () {
        var value = $(this).val().toLowerCase();
        $("#stats-body tr").filter(function () {
            $(this).toggle($(this).text().toLowerCase().indexOf(value) > -1)
        });
    });

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
                    //If this account has role as admin
                    if (result.roles === "admin")
                    {
                        //Display view for admin
                        displayForAdmin();
                        $('#result-message').empty().append("You have logged in: " + result.username + " - Role: " + result.roles);
                        $("#login-form").hide();
                        $("#reg-button").hide();
                        $("#home-admin-button").show();
                        $("#logout-button").show();
                        //$("#stats-admin").show(); 
                    } else
                    {
                        //Else, display for normal user
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

    //Catch submit when register
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

    //Catch submit when editing a product
    $("#edit-form").submit(function (event) {

        // Don't submit the form normally
        event.preventDefault();

        // Get some values from elements on the page
        var $form = $(this),
                id = $form.find("input[name='edit-id']").val(),
                brand = $form.find("input[name='edit-brand']").val(),
                model = $form.find("input[name='edit-model']").val(),
                color = $form.find("input[name='edit-color']").val();
        price = $form.find("input[name='edit-price']").val();

        // Compose the data in the format that the API is expecting
        var data = {id: id, brand: brand, model: model, color: color, price: price};

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

                //Fade out after 1000ms
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
