<#include '../base.ftl'>

<#macro title>Tasks</#macro>

<#macro stylesheets>
    <link rel="stylesheet" href="/webjars/bootstrap/4.0.0-2/css/bootstrap.min.css"/>
</#macro>

<#macro body>
    <#include '../components/navbar.ftl'>
    <div class="container-fluid" style="width: 100%;">
        <div class="row">
            <div style="width: 60%; margin-left: 25px;">
                <div class="row mx-0" style="width: 100%; height: 50px; text-align: left; background-color: ghostwhite; margin-top: 10px; border-radius: 15px">
                    <p style="font-size: x-large;" contenteditable="true">Task name</p>
                </div>
                <div class="row mx-0" style="width: 100%; text-align: left; height: 100px; background-color: ghostwhite; margin-top: 10px; border-radius: 15px;">
                    <p style="font-size: medium;" contenteditable="true" onfocus="">Description</p>
                </div>
                <div class="row mx-0" style="width: 100%; text-align: left; height: 100px; background-color: ghostwhite; margin-top: 10px; border-radius: 15px;">
                    <div class="container-fluid" style="height: 190px; overflow-y: scroll;">
                        <ul class="list-group list-group-flush mx-0">
                            <#foreach issue in issues>
                                <li class="list-group-item d-flex justify-content-between align-items-center">
                                    <h6>${issue.id}</h6>
                                    <h6>${issue.name}</h6>
                                    <h6>${issue.created}</h6>
                                    <h6>${issue.deadline}</h6>
                                    <a href="/project/${issue.id}" class="btn btn-primary">Open</a>
                                </li>
                            </#foreach>
                        </ul>
                    </div>
                </div>
            </div>
            <div style="width: 20%; margin-left: 25px;">
                <div class="row mx-0" style="width: 100%; text-align: left;" >
                    <div class="card" style="border-radius: 15px;">
                        <table class='table table-striped' style="table-layout: fixed;">
                            <tbody>
                                <tr>
                                    <th>Type</th>
                                    <th>Issue</th>
                                </tr>
                                <tr>
                                    <th>Reporter</th>
                                    <th>Well Played</th>
                                </tr>
                                <tr>
                                    <th>Assignee</th>
                                    <th>Aizat</th>
                                </tr>
                                <tr>
                                    <th>Status</th>
                                    <th>Open</th>
                                </tr>
                                <tr>
                                    <th>Created</th>
                                    <th>01.01.2020</th>
                                </tr>
                                <tr>
                                    <th>Deadline</th>
                                    <th>03.01.2020</th>
                                </tr>
                                <tr>
                                    <th>Finished</th>
                                    <th>03.01.2020</th>
                                </tr>
                            </tbody>
                        </table>
                    </div>
                </div>
            </div>
        </div>
    </div>

    <div class="container-fluid" style="width: 100%; overflow-y: scroll; height: 300px;">
        <ul class="list-group list-group-flush mx-0">
            <#foreach comment in comments>
                <li class="list-group-item d-flex ">
                    <h6 style="width: 10%;">${comment.author}</h6>
                    <h6 style="width: 10%;">${comment.date}</h6>
                    <h6 style="width: 80%; background-color: ghostwhite; height: 75px; border-radius: 15px;">${comment.text}</h6>
                </li>
            </#foreach>
        </ul>
    </div>

</#macro>

<@display_page/>