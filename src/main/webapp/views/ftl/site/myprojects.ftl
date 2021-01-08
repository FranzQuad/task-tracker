<#include '../base.ftl'>

<#macro title>My Projects</#macro>

<#macro stylesheets>
    <link rel="stylesheet" href="/webjars/bootstrap/4.0.0-2/css/bootstrap.min.css"/>
</#macro>

<#macro body>
   <#include '../components/navbar.ftl'>

    <div class="container-fluid" style="margin-top: 56px;">
        <div class="row" style="width: 100%;">
            <div style="width: 60%">
                <h2 style="margin-left: 3%; margin-top: 16px; margin-bottom: 0px;">Projects</h2>
            </div>
            <div style="width: 40%;">
                <h2 style="margin-left: 3%; margin-top: 16px; margin-bottom: 0px;">My Tasks</h2>
            </div>
        </div>
        <hr>

        <div class="container-fluid" style="width: 100%;">
            <div class="row">
                <!-- Logged in user projects -->
                <div class="container-fluid" style="width: 60%;">
                    <table class="table" style="width: 100%;">
                        <thead class="thead-dark" style="text-align: center;">
                        <tr style="height: 56px;">
                            <th scope="col" style="width: 5%;">#</th>
                            <th scope="col" style="width: 35%;">Name</th>
                            <th scope="col" style="width: 20%;">Start</th>
                            <th scope="col" style="width: 20%;">Deadline</th>
                            <th scope="col" style="width: 10%;">Tasks</th>
                            <th scope="col" style="width: 10%;"></th>
                        </tr>
                        </thead>
                        <tbody>
                        <#foreach project in projects>
                            <#if project.finished??>
                                <tr>
                                    <th scope="row" style="text-align: center; vertical-align: center; background-color: #6EC679;">${project.id}</th>
                                    <td style="text-align: center; vertical-align: center; background-color: #6EC679;">${project.name}</td>
                                    <td style="text-align: center; vertical-align: center; background-color: #6EC679;">
                                        <#if project.started??>
                                            ${project.started}
                                        <#else>
                                            <strong style="color: red">Not specified!</strong>
                                        </#if>
                                    </td>
                                    <td style="text-align: center; vertical-align: center; background-color: #6EC679;">
                                        <#if project.deadline??>
                                            ${project.deadline}
                                        <#else>
                                            <strong style="color: red">Not specified!</strong>
                                        </#if>
                                    </td>
                                    <td style="text-align: center; vertical-align: center; background-color: #6EC679;">
                                        <span class="badge badge-primary badge-pill">${project.getNotCompletedTasksCount()}</span>
                                    </td>
                                    <td style="text-align: center; vertical-align: center; background-color: #6EC679;">
                                        <a href="/project/${project.id}" class="btn btn-primary">Open</a>
                                    </td>
                                </tr>
                                <#else>
                                    <tr>
                                        <th scope="row" style="text-align: center; vertical-align: center;">${project.id}</th>
                                        <td style="text-align: center; vertical-align: center;">${project.name}</td>
                                        <td style="text-align: center; vertical-align: center;">
                                            <#if project.started??>
                                                ${project.started}
                                            <#else>
                                                <strong style="color: red">Not specified!</strong>
                                            </#if>
                                        </td>
                                        <td style="text-align: center; vertical-align: center;">
                                            <#if project.deadline??>
                                                ${project.deadline}
                                            <#else>
                                                <strong style="color: red">Not specified!</strong>
                                            </#if>
                                        </td>
                                        <td style="text-align: center; vertical-align: center;">
                                            <span class="badge badge-primary badge-pill">${project.getNotCompletedTasksCount()}</span>
                                        </td>
                                        <td style="text-align: center; vertical-align: center;">
                                            <a href="/project/${project.id}" class="btn btn-primary">Open</a>
                                        </td>
                                    </tr>
                            </#if>
                        </#foreach>
                        </tbody>
                    </table>
                </div>

                <!-- Logged in user "In Progress" and "Closed" tasks -->
                <div style="width: 40%;">
                    <div class="row mx-0" style="width: 100%; text-align: center">
                        <div style="width: 50%">
                            <h4 style="margin-left: 3%; margin-top: 1%;">In Progress</h4>
                        </div>
                        <div style="width: 50%">
                            <h4 style="margin-left: 3%; margin-top: 1%;">Completed</h4>
                        </div>
                    </div>
                    <hr style="width: 100%">
                    <div class="row mx-0" style="width: 100%;">
                        <div style="width: 50%; height: 77.4vh; overflow-y: scroll;">
                            <ul class="list-group list-group-flush">
                                <#foreach issue in notCompletedIssues>
                                    <#include '../components/card.ftl'>
                                </#foreach>
                            </ul>
                        </div>
                        <div style="width: 50%; height: 77.4vh; overflow-y: scroll;">
                            <ul class="list-group list-group-flush">
                                <#foreach issue in completedIssues>
                                    <#include '../components/card.ftl'>
                                </#foreach>
                            </ul>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>



</#macro>

<@display_page/>