<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>TEST ADMIN2</title>
        <!-- Bootstrap core CSS -->
        <link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet">

        <!-- Custom styles for this template -->
        <link href="${pageContext.request.contextPath}/css/starter-template.css" rel="stylesheet">
        
        <script src="${pageContext.request.contextPath}/js/jquery-1.11.1.min.js"></script>        

        <!-- SWC Icon -->
        <link rel="shortcut icon" href="${pageContext.request.contextPath}/img/icon.png">
        
        <!-- Select2 -->
        <link href="https://cdnjs.cloudflare.com/ajax/libs/select2/4.0.2/css/select2.min.css" rel="stylesheet" />
        <script src="https://cdnjs.cloudflare.com/ajax/libs/select2/4.0.2/js/select2.min.js"></script>

        <script type="text/javascript">
            $(document).ready(function () {
                $(".js-example-tags").select2({
                    tags: true,
                    tokenSeparators: [',', ' ']
                });
            });
        </script>       

    </head>
    <body>
        <div class="container">

            <div class="row">
                <h1> hello </h1>

                <form id="create-stat" class="form-horizontal">
                    <div class ="form-group">
                        <textarea id="mytextarea"></textarea>
                        <input id="stat-submit" type="submit" class="btn btn-default" value="Post"/>
                    </div>
                    <div class="form-group">
                        <label for="add-creation-date" class="col-md-4 control-label">Creation Date</label>
                        <div class="col-md-8">
                            <input type="text" name="date" class="form-control" id="add-creation-date" placeholder="Date">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="add-title" class="col-md-4 control-label">title</label>
                        <div class="col-md-8">
                            <input type="text" name="title" class="form-control" id="add-title" placeholder="Title">
                        </div>
                    </div> 

                    <div class="s2-example form-group">
                        <label for="add-tag" class="col-md-4 control-label">Tags</label> 
                        <!--<input type="text" id="placeSelect"/>-->

                        <div class="s2-example form-group col-md-8">
                            <!--<p>-->
                                <select class="js-example-tags form-control" multiple="multiple" >

                                </select>
                            <!--</p>-->
                        </div>
                    </div>                    


                    <input type="submit" class="btn btn-default pull-right" value="Create Post" />


                </form>

                <div class="col-lg-10 col-sm-10">
                    <h1 id="blog-1-title">

                    </h1>
                    <div id="stat-body-1">
                        
                    </div>
                    
                </div>






            </div>
        </div>
        <script type="text/javascript">
            var contextRoot = "${pageContext.request.contextPath}";
        </script>
        <!-- Placed at the end of the document so the pages load faster -->

        <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/app.js"></script>
        <script src='//cdn.tinymce.com/4/tinymce.min.js'></script>
        <script src="${pageContext.request.contextPath}/js/TinyMce.js"></script>

    </body>
</html>

