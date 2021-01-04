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
                    <p style="font-size: x-large;" contenteditable="true">${issue.getName()}</p>
                </div>
                <div class="row mx-0" style="width: 100%; text-align: left; height: 100px; background-color: ghostwhite; margin-top: 10px; border-radius: 15px;">
                    <p style="font-size: medium;" contenteditable="true" onfocus="">${issue.getDescription()}</p>
                </div>
                <div class="row mx-0" style="width: 100%; text-align: left; height: 100px; background-color: ghostwhite; margin-top: 10px; border-radius: 15px;">
                    <div class="container-fluid" style="height: 190px; overflow-y: scroll;">
                        <ul class="list-group list-group-flush mx-0">
                            <#foreach issue in issue.getChildIssues()>
                                <li class="list-group-item d-flex justify-content-between align-items-center">
                                    <h6>${issue.getId()}</h6>
                                    <h6>${issue.getName()}</h6>
                                    <h6>${issue.getStarted()}</h6>
                                    <h6>${issue.getFinished()}</h6>
                                    <a href="/issue/${issue.id}" class="btn btn-primary">Open</a>
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
                                    <th>${issue.reporter.user.getName()}</th>
                                </tr>
                                <tr>
                                    <th>Assignee</th>
                                    <th>Aizat</th>
                                </tr>
                                <tr>
                                    <th>Status</th>
                                    <th>${issue.getIssueStatus()}</th>
                                </tr>
                                <tr>
                                    <th>Created</th>
                                    <th>${issue.getStarted()}</th>
                                </tr>
                                <tr>
                                    <th>Deadline</th>
                                    <th>${issue.getFinished()}</th>
                                </tr>
                                <tr>
                                    <th>Finished</th>
                                    <th>${issue.getFinished()}</th>
                                </tr>
                            </tbody>
                        </table>                       
                    </div>
                </div>
            </div>
        </div>
    </div>
    <div style="width: 85%; overflow-y: scroll; height: 150px; margin-top: 20px; margin-left: 25px;">
        <ul class="list-group list-group-flush mx-0">
            <#foreach comment in comments>
                <li class="list-group-item d-flex" style="background-color: ghostwhite;">
                    <h6 style="margin-left: 10px; width: 10%; border-radius: 10px; background-color: white";>
                        <p>${comment.author.user.getName()}</p>
                        <p>${comment.getCreated()}</p>
                    </h6>
                    <h6 style="margin-left: 10px; width: 80%; border-radius: 10px; background-color: white">${comment.getText()}</h6>
                </li>
            </#foreach>
        </ul>
    </div>
         <div class="row mx-0" style="width: 80%; height: 100px; margin-top: 20px; margin-right: 25px; display: flex; justify-content: flex-end;">
            <form action="/issue/${issue.getId()}/add-comment" method="post">
                <input name="text" type="text" class="form-control" style="width: 100em; margin-left: 25px; display: flex; justify-content: flex-end;">
                <button type="submit" class="btn btn-primary;" style="margin-top: 10px; margin-left: 25px; display: flex; justify-content: flex-end;">Submit</button>
            </form>
        </div>
</#macro>

<@display_page/>