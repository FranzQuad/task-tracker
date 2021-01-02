<#include '../base.ftl'>

<#macro title>Projects List</#macro>

<#macro stylesheets>
    <link rel="stylesheet" href="/webjars/bootstrap/4.0.0-2/css/bootstrap.min.css"/>
</#macro>

<#macro body>
   <#include '../components/navbar.ftl'>

    <div class="row" style="width: 100%;">
        <div style="width: 60%">
            <h2 style="margin-left: 3%; margin-top: 16px; margin-bottom: 0px;">Projects</h2>
        </div>
        <div style="width: 40%;">
            <h2 style="margin-left: 3%; margin-top: 16px; margin-bottom: 0px;">My Tasks</h2>
        </div>
    </div>
    <hr>

    <!-- Logged in user projects -->
    <div class="container-fluid" style="width: 100%;">
        <div class="row">
            <div class="container-fluid" style="width: 60%;">
                <ul class="list-group list-group-flush mx-0">
                    <#foreach project in projects>
                        <li class="list-group-item d-flex justify-content-between align-items-center">
                            <h6>${project.id}</h6>
                            <h6>${project.name}</h6>
                            <h6>${project.finished}</h6>
                            <span class="badge badge-primary badge-pill">14</span>
                            <a href="/project/${project.id}" class="btn btn-primary">Open</a>
                        </li>
                    </#foreach>
                </ul>
            </div>

            <!-- Logged in user "In Progress" and "Closed" tasks -->
            <div style="width: 40%;">
                <div class="row mx-0" style="width: 100%; text-align: center">
                    <div style="width: 50%">
                        <h4 style="margin-left: 3%; margin-top: 1%;">In Progress</h4>
                    </div>
                    <div style="width: 50%">
                        <h4 style="margin-left: 3%; margin-top: 1%;">Closed</h4>
                    </div>
                </div>
                <hr style="width: 100%">
                <div class="row mx-0" style="width: 100%;">
                    <div style="width: 50%; height: 77.4vh; overflow-y: scroll;">
                        <#include '../components/issues_cycle.ftl'>
                    </div>
                    <div style="width: 50%; height: 77.4vh; overflow-y: scroll;">
                        <#include '../components/issues_cycle.ftl'>
                    </div>
                </div>
            </div>
        </div>
    </div>
</#macro>

<@display_page/>