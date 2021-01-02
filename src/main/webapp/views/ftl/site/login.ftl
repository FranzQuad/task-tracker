<#include '../base.ftl'>

<#macro title>Login</#macro>

<#macro stylesheets>
    <link rel="stylesheet" href="/webjars/bootstrap/4.0.0-2/css/bootstrap.min.css"/>
</#macro>

<#macro body>
<nav class="navbar navbar-expand-lg navbar-dark bg-dark">
    <a class="navbar-brand" href="/login">
        <strong>PineappleTT</strong>
    </a>
</nav>

<div class="card align-items-center" style="width: 500px; height: 330px; margin-left: 35%; margin-top: 10%;">
    <div class="card-body">
        <form  action="/login" method="post">
            <h2>Welcome</h2>
            <div class="form-group">
                <label for="username">Name</label>
                <input type="text" class="form-control" id="username" name="username">
            </div>
            <div class="form-group">
                <label for="userPassword">Password</label>
                <input type="password" class="form-control" id="userPassword" name="password">
            </div>
            <button type="submit" class="btn btn-primary">Submit</button>
            <a type="submit" class="btn btn-primary" href="/registration">Registration</a>
        </form>
    </div>
</div>
</#macro>

<@display_page/>