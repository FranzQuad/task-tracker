<#include '../base.ftl'>

<#macro title>Registration</#macro>

<#macro stylesheets>
    <link rel="stylesheet" href="/webjars/bootstrap/4.0.0-2/css/bootstrap.min.css"/>
</#macro>

<#macro body>
<nav class="navbar navbar-expand-lg navbar-dark bg-dark">
    <a class="navbar-brand" href="/login">
        PineappleTT
    </a>
</nav>

<div class="card align-items-center" style="width: 500px; margin-left: 35%; margin-top: 10%;">
    <div class="card-body">
        <form method="post">
            <h2>Registration</h2>
            <div class="form-group">
                <label for="userEmail">Email address</label>
                <input name="email" type="email" class="form-control" id="userEmail" aria-describedby="emailHelp">
                <small id="emailHelp" class="form-text text-muted">We'll never share your email with anyone else.</small>
            </div>
            <div class="form-group">
                <label for="userName">Name</label>
                <input name="name" type="text" class="form-control" id="userName" aria-describedby="emailHelp">
            </div>
            <div class="form-group">
                <label for="userPassword">Password</label>
                <input type="password" class="form-control" id="userPassword">
            </div>
            <div class="form-group">
                <label for="confirmUserPassword">Confirm Password</label>
                <input name="password" type="password" class="form-control" id="confirmUserPassword">
            </div>
            <a type="button" class="btn btn-secondary" href="/login">Cancel</a>
            <button type="submit" class="btn btn-primary">Submit</button>
        </form>
    </div>
</div>
</#macro>

<@display_page/>