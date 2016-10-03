<%-- 
    Document   : Blog
    Created on : Mar 8, 2016, 1:52:37 PM
    Author     : apprentice
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <meta name="description" content="Developed By M Abdur Rokib Promy">
        <meta name="author" content="cosmic">
        <meta name="keywords" content="Bootstrap 3, Template, Theme, Responsive, Corporate, Business">
        <link rel="shortcut icon" href="img/favicon.png">

        <title>
            Womens Warehouse | Home
        </title>

        <!-- Bootstrap core CSS -->
        <link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet">
        <link href="${pageContext.request.contextPath}/css/theme.css" rel="stylesheet">
        <link href="${pageContext.request.contextPath}/css/bootstrap-reset.css" rel="stylesheet">
        <!--external css-->
        <link href="${pageContext.request.contextPath}/assets/font-awesome/css/font-awesome.css" rel="stylesheet" />
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/flexslider.css"/>
        <link href="${pageContext.request.contextPath}/assets/bxslider/jquery.bxslider.css" rel="stylesheet" />
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/animate.css">
        <link href='${pageContext.request.contextPath}/http://fonts.googleapis.com/css?family=Lato' rel='stylesheet' type='text/css'>



        <!-- Custom styles for this template -->
        <link href="${pageContext.request.contextPath}/css/style.css" rel="stylesheet">
        <link href="${pageContext.request.contextPath}/css/style-responsive.css" rel="stylesheet" />

        <!-- HTML5 shim and Respond.js IE8 support of HTML5 tooltipss and media queries -->
        <!--[if lt IE 9]>
    <script src="js/html5shiv.js"></script>
    <script src="js/respond.min.js"></script>
    <![endif]-->
    </head>

    <body>
        <!--header start-->
        <header class="head-section">
            <div class="navbar navbar-default navbar-static-top container">
                <div class="navbar-header">
                    <button class="navbar-toggle" data-target=".navbar-collapse" data-toggle="collapse" type="button">
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                    </button>
                    <a class="navbar-brand" href="${pageContext.request.contextPath}">Womans<span>Warehouse</span></a>
                </div>
                <div class="navbar-collapse collapse">
                    <ul class="nav navbar-nav">
                        <li>
                            <a href="${pageContext.request.contextPath}">Home</a>
                        </li>                

                        <li>
                            <a href="${pageContext.request.contextPath}/blog">Blog</a>
                        </li>
                        <li>
                            <a href="${pageContext.request.contextPath}/stat">Post</a>
                        </li>

                    </ul>
                </div>
            </div>
        </header>
        <!--header end-->
        <!--breadcrumbs start-->
        <div class="breadcrumbs">
            <div class="container">
                <div class="row">
                    <div class="col-lg-4 col-sm-4">
                        <h1>
                            ${blog.title}
                        </h1>
                    </div>
                </div>
            </div>
        </div>
        <!--breadcrumbs end-->

        <!--container start-->
        <div class="container">
            <div class="row">
                <!--blog start-->
                <div class="col-lg-9 ">
                    <div class="blog-item">
                        <div class="row">
                            <div class="col-lg-2 col-sm-2">
                                <div class="date-wrap">
                                    <span class="date">
                                        ${blog.creationDate.dayOfMonth}
                                    </span>
                                    <span class="month">
                                        ${blog.creationDate.month}
                                    </span>
                                    <span class="month" style="background-color: pink">
                                        ${blog.creationDate.year}
                                    </span>
                                </div>

                            </div>
                            <div class="col-lg-10 col-sm-10">
                                <div class="blog-img">
                                    <img src="${pageContext.request.contextPath}/img/blog/img7.jpg" alt=""/>
                                </div>

                            </div>
                        </div>
                        <div class="row">
                            <div class="col-lg-2 col-sm-2 text-right">
                                <div class="author">
                                    By
                                    <a href="#">
                                        HashTag
                                    </a>
                                </div>
                                <ul class="list-unstyled">
                                    <li>
                                        <a href="javascript:;">
                                            <em>
                                                travel
                                            </em>
                                        </a>
                                    </li>
                                </ul>
                            </div>
                            <div class="col-lg-10 col-sm-10">

                                <div id="blog-title">

                                </div>
                                <div id="blog-body-1">
                                    ${blog.blogContent}
                                </div>
                            </div>
                        </div>
                    </div>
                </div>

                <div class="col-lg-3">
                    <div class="blog-side-item">
                        <div class="search-row">
                            <input type="text" class="form-control" placeholder="Search here">
                        </div>
                        <div class="category">
                            <h3>
                                Categories
                            </h3>
                            <ul class="list-unstyled">
                                <li>
                                    <c:forEach items="${nameList}" var="nameList">
                                        <a data-blog-id="${blog.id}" data-category-id="${nameList.id}" data-toggle="modal" data-target="#showCategorySearchModal2">
                                            <i class="fa fa-angle-right pr-10">
                                            </i>
                                            ${nameList.name}
                                        </a>    
                                    </c:forEach>
                                </li>
                            </ul>
                        </div>




                        <div class="archive">
                            <h3>
                                Archive
                            </h3>
                            <ul class="list-unstyled">
                                <li>
                                    <a href="javascript:;">
                                        <i class="fa fa-angle-double-right pr-10">
                                        </i>
                                        May 2014
                                    </a>
                                </li>
                                <li>
                                    <a href="javascript:;">
                                        <i class="fa fa-angle-double-right pr-10">
                                        </i>
                                        April 2014
                                    </a>
                                </li>
                                <li>
                                    <a href="javascript:;">
                                        <i class="fa fa-angle-double-right pr-10">
                                        </i>
                                        March 2014
                                    </a>
                                </li>
                                <li>
                                    <a href="javascript:;">
                                        <i class="fa fa-angle-double-right pr-10">
                                        </i>
                                        February 2014
                                    </a>
                                </li>
                                <li>
                                    <a href="javascript:;">
                                        <i class="fa fa-angle-double-right pr-10">
                                        </i>
                                        January 2014
                                    </a>
                                </li>
                            </ul>
                        </div>


                    </div>
                </div>

                <!--blog end-->
            </div>

        </div>
        <!--container end-->

        <!--footer start-->
        <footer class="footer">
            <div class="container">
                <div class="row">
                    <div class="col-lg-3 col-sm-3 address wow fadeInUp" data-wow-duration="2s" data-wow-delay=".1s">
                        <h1>
                            contact info
                        </h1>
                        <address>
                            <p>
                                <i class="fa fa-home pr-10">
                                </i>
                                Address: No.XXXXXX street
                            </p>
                            <p>
                                <i class="fa fa-globe pr-10">
                                </i>
                                Mars city, Country
                            </p>
                            <p>
                                <i class="fa fa-mobile pr-10">
                                </i>
                                Mobile : (123) 456-7890
                            </p>
                            <p>
                                <i class="fa fa-phone pr-10">
                                </i>
                                Phone : (123) 456-7890
                            </p>
                            <p>
                                <i class="fa fa-envelope pr-10">
                                </i>
                                Email :
                                <a href="javascript:;">
                                    support@example.com
                                </a>
                            </p>
                        </address>
                    </div>
                    <div class="col-lg-3 col-sm-3 wow fadeInUp" data-wow-duration="2s" data-wow-delay=".3s">
                        <h1>
                            latest tweet
                        </h1>
                        <div id="owl-slide">
                            <div class="tweet-box">
                                <i class="fa fa-twitter">
                                </i>
                                <em>
                                    Please follow
                                    <a href="javascript:;">
                                        @example
                                    </a>
                                    for all future updates of us!
                                    <a href="javascript:;">
                                        twitter.com/acme
                                    </a>
                                </em>
                            </div>
                            <div class="tweet-box">
                                <i class="fa fa-twitter">
                                </i>
                                <em>
                                    Please follow
                                    <a href="javascript:;">
                                        @example
                                    </a>
                                    for all future updates of us!
                                    <a href="javascript:;">
                                        twitter.com/acme
                                    </a>
                                </em>
                            </div>
                            <div class="tweet-box">
                                <i class="fa fa-twitter">
                                </i>
                                <em>
                                    Please follow
                                    <a href="javascript:;">
                                        @example
                                    </a>
                                    for all future updates of us!
                                    <a href="javascript:;">
                                        twitter.com/acme
                                    </a>
                                </em>
                            </div>
                        </div>
                    </div>
                    <div class="col-lg-3 col-sm-3">
                        <div class="page-footer wow fadeInUp" data-wow-duration="2s" data-wow-delay=".5s">
                            <h1>
                                Our Company
                            </h1>
                            <ul class="page-footer-list">

                                <li>
                                    <i class="fa fa-angle-right">
                                    </i>
                                    <a href="about.html">
                                        About Us
                                    </a>
                                </li>
                                <li>
                                    <i class="fa fa-angle-right">
                                    </i>
                                    <a href="faq.html">
                                        Support
                                    </a>
                                </li>
                                <li>
                                    <i class="fa fa-angle-right">
                                    </i>
                                    <a href="service.html">
                                        Service
                                    </a>
                                </li>
                                <li>
                                    <i class="fa fa-angle-right">
                                    </i>
                                    <a href="privacy-policy.html">
                                        Privacy Policy
                                    </a>
                                </li>
                                <li>
                                    <i class="fa fa-angle-right">
                                    </i>
                                    <a href="career.html">
                                        We are Hiring
                                    </a>
                                </li>
                                <li>
                                    <i class="fa fa-angle-right">
                                    </i>
                                    <a href="terms.html">
                                        Term & condition
                                    </a>
                                </li>

                            </ul>
                        </div>
                    </div>
                    <div class="col-lg-3 col-sm-3">

                        <div class="text-footer wow fadeInUp" data-wow-duration="2s" data-wow-delay=".7s">
                            <h1>
                                Text Widget
                            </h1>
                            <p>
                                This is a text widget.Lorem ipsum dolor sit amet.
                                This is a text widget.Lorem ipsum dolor sit amet
                            </p>
                        </div>
                    </div>

                </div>

            </div>
        </footer>
        <!-- footer end -->
        <!--small footer start -->
        <footer class="footer-small">
            <div class="container">
                <div class="row">
                    <div class="col-lg-6 col-sm-6 pull-right">
                        <ul class="social-link-footer list-unstyled">
                            <li class="wow flipInX" data-wow-duration="2s" data-wow-delay=".1s"><a href="#"><i class="fa fa-facebook"></i></a></li>
                            <li class="wow flipInX" data-wow-duration="2s" data-wow-delay=".2s"><a href="#"><i class="fa fa-google-plus"></i></a></li>
                            <li class="wow flipInX" data-wow-duration="2s" data-wow-delay=".3s"><a href="#"><i class="fa fa-dribbble"></i></a></li>
                            <li class="wow flipInX" data-wow-duration="2s" data-wow-delay=".4s"><a href="#"><i class="fa fa-linkedin"></i></a></li>
                            <li class="wow flipInX" data-wow-duration="2s" data-wow-delay=".5s"><a href="#"><i class="fa fa-twitter"></i></a></li>
                            <li class="wow flipInX" data-wow-duration="2s" data-wow-delay=".6s"><a href="#"><i class="fa fa-skype"></i></a></li>
                            <li class="wow flipInX" data-wow-duration="2s" data-wow-delay=".7s"><a href="#"><i class="fa fa-github"></i></a></li>
                            <li class="wow flipInX" data-wow-duration="2s" data-wow-delay=".8s"><a href="#"><i class="fa fa-youtube"></i></a></li>
                        </ul>
                    </div>
                    <div class="col-md-4">
                        <div class="copyright">
                            <p>&copy; Copyright - Acme Theme by cosmic.</p>
                        </div>
                    </div>
                </div>
            </div>
        </footer>
        
        
        <!-- Show Category Search Modal Start-->
        <div id="showCategorySearchModal2" class="modal fade" role="dialog">
            <div class="modal-dialog">

                <!-- Show Category Search Modal content-->
                <div class="modal-content">
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal">&times;</button>
                        <h4 class="modal-title"> Posts</h4>
                    </div>
                    <div class="modal-body">

                        <table id="categorySearchTable" class="table table-striped">
                            <thead>
                                <tr>
                                    <th>Title</th><th>Post Date</th>
                                </tr>
                            </thead>
                            <tbody>
                            </tbody>

                        </table>

                        <div class="modal-footer">
                            <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <!--small footer end-->

        <!-- js placed at the end of the document so the pages load faster -->
        <script src="${pageContext.request.contextPath}/js/jquery.js">
        </script>
        <script src="${pageContext.request.contextPath}/js/bootstrap.min.js">
        </script>
        <script type="text/javascript" src="${pageContext.request.contextPath}/js/hover-dropdown.js">
        </script>
        <script defer src="${pageContext.request.contextPath}/js/jquery.flexslider.js">
        </script>
        <script type="text/javascript" src="${pageContext.request.contextPath}/assets/bxslider/jquery.bxslider.js">
        </script>

        <script src="${pageContext.request.contextPath}/js/jquery.easing.min.js">
        </script>
        <script src="${pageContext.request.contextPath}/js/link-hover.js">
        </script>
        <script src="${pageContext.request.contextPath}/js/app.js">
        </script>


        <!--common script for all pages-->
        <script src="${pageContext.request.contextPath}/js/common-scripts.js">
        </script>
        
        <script>
            var contextRoot = "${pageContext.request.contextPath}";
        </script>

        <script src="${pageContext.request.contextPath}/js/wow.min.js">
        </script>
        <script>
            wow = new WOW(
                    {
                        boxClass: 'wow', // default
                        animateClass: 'animated', // default
                        offset: 0          // default
                    }
            )
            wow.init();
        </script>

    </body>
</html>
