
$(document).ready(function () {

    $(document).on('submit', '#create-blog', function (e) {

        e.preventDefault();

        var html = tinymce.activeEditor.getContent();

        var blogData = JSON.stringify({
            blogContent: html,
            creationDate: $('#add-creation-date').val(),
            category: $('#add-category').val(),
            title: $('#add-title').val(),
            postDate: $('#add-post-date').val()
        });

        $.ajax({
            type: 'POST',
            url: contextRoot + "/blog/create",
            data: blogData,
            dataType: 'json',
            beforeSend: function (xhr) {
                xhr.setRequestHeader("Accept", "application/json");
                xhr.setRequestHeader("Content-type", "application/json");
            }
        }).success(function (data, status) {

            //Clear the form
            $('#blog-content').val("");

            $('#blog-body-1').html(data.blogContent);

        });

    });

    $(document).on('submit', '#create-stat', function (e) {

        e.preventDefault();

        var html = tinymce.activeEditor.getContent();

        var statData = JSON.stringify({
            staticContent: html,
            creationDate: $('#add-creation-date').val(),
//            category: $('#add-category').val(),
            title: $('#add-title').val()
//            postDate: $('#add-post-date').val()
        });

        $.ajax({
            type: 'POST',
            url: contextRoot + "/stat/create",
            data: statData,
            dataType: 'json',
            beforeSend: function (xhr) {
                xhr.setRequestHeader("Accept", "application/json");
                xhr.setRequestHeader("Content-type", "application/json");
            }
        }).success(function (data, status) {

            //Clear the form
            $('#stat-content').val("");

            $('#stat-body-1').html(data.statContent);

        });

    });

    //for blog.jsp
    $('#showCategorySearchModal').on('show.bs.modal', function (e) {

        var link = $(e.relatedTarget);

        var categoryId = link.data('category-id');

        $.ajax({
            type: 'GET',
            url: contextRoot + "/blog/categorysearch/" + categoryId,
            dataType: 'json',
            beforeSend: function (xhr) {
                xhr.setRequestHeader("Accept", "application/json");
            }
        }).success(function (data, status) {

            $('#categorySearchTable tbody').empty();

            $.each(data, function (index, Blog) {
                var buildCatRow = buildCategoryRow(Blog);

                $('#categorySearchTable tbody').append($(buildCatRow));

            });

        });

    });
    //For viewblog.jsp
    $('#showCategorySearchModal2').on('show.bs.modal', function (e) {

        var link = $(e.relatedTarget);

        var categoryId = link.data('category-id');

        var blogId = link.data('blog-id');

        $.ajax({
            type: 'GET',
            url: contextRoot + "/blog/viewblog/" + blogId + "/categorysearch/" + categoryId,
            dataType: 'json',
            beforeSend: function (xhr) {
                xhr.setRequestHeader("Accept", "application/json");
            }
        }).success(function (data, status) {

            $('#categorySearchTable2 tbody').empty();

            $.each(data, function (index, Blog) {
                var buildCatRow = buildCategoryRow(Blog);

                $('#categorySearchTable tbody').append($(buildCatRow));

            });

        });

    });

    $('#editContactModal').on('show.bs.modal', function (e) {

        var link = $(e.relatedTarget);

        var contactId = link.data('contact-id');

        var modal = $(this);

        $.ajax({
            type: 'GET',
            url: contextRoot + "/contact/" + contactId,
            dataType: 'json',
            beforeSend: function (xhr) {
                xhr.setRequestHeader("Accept", "application/json");
            }
        }).success(function (contact, status) {

            modal.find('#edit-first-name').val(contact.firstName);
            modal.find('#edit-last-name').val(contact.lastName);
            modal.find('#edit-street-address').val(contact.streetAddress);
            modal.find('#edit-city').val(contact.city);
            modal.find('#edit-state').val(contact.state);
            modal.find('#edit-zip').val(contact.zip);

            $('#edit-id').val(contactId);

        });

    });


    $(document).on('click', '#edit-contact-button', function (e) {

        e.preventDefault();

        var contactId = $('#edit-id').val();

        $.ajax({
            type: 'PUT',
            url: contextRoot + '/contact/' + contactId,
            data: JSON.stringify({
                contactId: contactId,
                firstName: $('#edit-first-name').val(),
                lastName: $('#edit-last-name').val(),
                streetAddress: $('#edit-street-address').val(),
                city: $('#edit-city').val(),
                state: $('#edit-state').val(),
                zip: $('#edit-zip').val()

            }),
            dataType: 'json',
            beforeSend: function (xhr) {
                xhr.setRequestHeader("Accept", 'application/json');
                xhr.setRequestHeader("Content-type", 'application/json');
            }
        }).success(function (data, status) {

            console.log("Got here");

            $('#editContactModal').modal('hide');

            var tableRow = buildContactRow(data);

            $('#contact-row-' + data.contactId).replaceWith($(tableRow));

        }).error(function (data, status) {

            var errors = data.responseJSON.errors;

            $.each(errors, function (index, validationError) {
                $('#edit-contact-validation-errors').append(validationError.fieldName + ": " + validationError.message).append("<br/>");
            });

        });

    });


    $(document).on('click', '.delete-link', function (e) {
        e.preventDefault();

        var contactId = $(e.target).data('contact-id');

        $.ajax({
            type: 'DELETE',
            url: contextRoot + '/contact/' + contactId

        }).success(function (data, status) {

            $('#contact-row-' + contactId).remove();

        });

    });

    $(document).on('click', '#static-post', function (e) {
        
        e.preventDefault();

        var statId = $(e.target).data('static-id');
        
//        var orderId = $("#stat-id").val();
//
//        var statId = link.data('value');

        $.ajax({
            type: 'GET',
            url: contextRoot + "/stat/" + statId,
            dataType: 'json',
            beforeSend: function (xhr) {
                xhr.setRequestHeader("Accept", "application/json");
            }
        }).success(function (data, status) {

//            $('#categorySearchTable tbody').empty();
//
//            $.each(data, function (index, Blog) {
//                var buildCatRow = buildCategoryRow(Blog);
//
//                $('#categorySearchTable tbody').append($(buildCatRow));
//
//            });

        });

    });

    function buildCategoryRow(data) {

        return "<tr id='viewblog-" + data.id + "'> \n\
                <td><a href='" + contextRoot + "/blog/viewblog/" + data.id + "'>" + data.title + "</td> \n\
                <td>" + data.showDate + "</td> \n\
                </tr>";

    }

});


