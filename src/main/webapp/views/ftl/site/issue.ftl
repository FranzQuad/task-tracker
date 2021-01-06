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
                <div class="row mx-0" style="width: 100%; max-width: 100%; height: 50px; max-height: 50px; text-align: left; background-color: ghostwhite; margin-top: 10px; border-radius: 15px; " data-toggle="modal" data-target="#editName">
                    <p style="font-size: x-large; width: 100%; max-width: 100%; height: 50px; max-height: 50px;">${issue.getName()}</p>

                </div>
                <div class="row mx-0" style="width: 100%; text-align: left; height: 100px; background-color: ghostwhite; margin-top: 10px; border-radius: 15px; overflow: hidden; word-wrap: break-word;" data-toggle="modal" data-target="#editDescription">
                    <p style="font-size: medium;">${issue.getDescription()}</p>
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
                                    <th>${issue.reporter.getName()}</th>
                                </tr>
                                <tr>
                                    <th>Assignee</th>
                                    <th>
                                        <#foreach issueParticipant in issue.projectParticipants>
                                            <p>${issueParticipant.user.name}</p>
                                        </#foreach>
                                    </th>
                                </tr>
                                <tr>
                                    <th>Status</th>
                                    <th data-toggle="modal" data-target="#editStatus">${issue.getIssueStatus()}</th>
                                </tr>
                                <tr>
                                    <th>Created</th>
                                    <th>${issue.getCreated()}</th>
                                </tr>
                                <tr>
                                    <th>Started</th>
                                    <th data-toggle="modal" data-target="#editStarted" type="datetime">
                                    <#if issue.getStarted()??>
                                        ${issue.getStarted()}
                                    <#else>
                                        ""
                                    </#if>
                                    </th>
                                </tr>
                                <tr>
                                    <th>Deadline</th>
                                    <th data-toggle="modal" data-target="#editDeadline" type="datetime">
                                        <#if issue.getDeadline()??>
                                            ${issue.getDeadline()}
                                        <#else>
                                            ""
                                        </#if>
                                    </th>
                                </tr>
                                <tr>
                                    <th>Finished</th>
                                    <th data-toggle="modal" data-target="#editFinished" type="datetime">
                                        <#if issue.getFinished()??>
                                            ${issue.getFinished()}
                                        <#else>
                                            ""
                                        </#if>
                                    </th>
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
                    <h6 style="margin-left: 10px; width: 10%; border-radius: 10px; background-color: white;">
                        <p>${comment.author.getName()}</p>
                        <p>${comment.getCreated()}</p>
                    </h6>
                    <h6 style="margin-left: 10px; width: 80%; border-radius: 10px; background-color: white">${comment.getText()}</h6>
                </li>
            </#foreach>
        </ul>
    </div>
    <div class="container-fluid">
        <div class="row mx-0" style="width: 80%; height: 100px; margin-top: 20px; margin-right: 25px; display: flex; justify-content: flex-end;">
            <form action="/issue/${issue.getId()}/add-comment" method="post" style="width: 100%;">
                <input name="text" type="text" class="form-control" style="margin-left: 25px; display: flex; justify-content: flex-end;">
                <button type="submit" class="btn btn-primary float-right" style="margin-top: 10px; margin-left: 25px;">Submit</button>
            </form>
        </div>
    </div>
    <div id="editDescription" class="modal fade" role="dialog">
        <div class="modal-dialog">
            <!-- Modal content-->
            <div class="modal-content">
                <div class="modal-header">
                    <h4 class="modal-title">Edit Description</h4>
                </div>
                <form action="/issue/${issue.id}/edit-description" method="post">
                    <div class="modal-body">
                        <div class="container-fluid">
                            <textarea name="description" class="form-control" id="editDescription" rows="5" >${issue.description}</textarea>
                        </div>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-default" data-dismiss="modal">Cancel</button>
                        <input type="submit" class="btn btn-default" style="background-color: orange">
                    </div>
                </form>
            </div>
        </div>
    </div>

    <div id="editName" class="modal fade" role="dialog">
        <div class="modal-dialog">
            <!-- Modal content-->
            <div class="modal-content">
                <div class="modal-header">
                    <h4 class="modal-title">Edit Name</h4>
                </div>
                <form action="/issue/${issue.id}/edit-name" method="post">
                    <div class="modal-body">
                        <div class="container-fluid">
                            <textarea name="name" class="form-control" id="editName" rows="5" >${issue.name}</textarea>
                        </div>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-default" data-dismiss="modal">Cancel</button>
                        <input type="submit" class="btn btn-default" style="background-color: orange">
                    </div>
                </form>
            </div>
        </div>
    </div>

    <div id="editStatus" class="modal fade" role="dialog">
        <div class="modal-dialog">
            <!-- Modal content-->
            <div class="modal-content">
                <div class="modal-header">
                    <h4 class="modal-title">Edit Status</h4>
                </div>
                <form action="/issue/${issue.id}/edit-status" method="post">
                    <div class="modal-body">
                        <div class="container-fluid">
                            <div class="input-group" style="margin-bottom: 10px;">
                                <div class="input-group-append">
                                    <select name="status" class="form-control" id="editStatus">
                                        <#foreach status in statusList>
                                            <option value="${status.name()}">${status.name()}</option>
                                        </#foreach>
                                    </select>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-default" data-dismiss="modal">Cancel</button>
                        <input type="submit" class="btn btn-default" style="background-color: orange">
                    </div>
                </form>
            </div>
        </div>
    </div>

    <div id="editDeadline" class="modal fade" role="dialog">
        <div class="modal-dialog">
            <!-- Modal content-->
            <div class="modal-content">
                <div class="modal-header">
                    <h4 class="modal-title">Edit deadline</h4>
                </div>
                <form action="/issue/${issue.id}/edit-deadline" method="post">
                    <div class="modal-body">
                        <input name="deadline" type="date" value="${issue.getDeadline()}" class="form-control" aria-label="Sizing example input"
                               aria-describedby="inputGroup-sizing-default">
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-default" data-dismiss="modal">Cancel</button>
                        <input type="submit"  class="btn btn-default" style="background-color: orange">
                    </div>
                </form>
            </div>
        </div>
    </div>

    <div id="editFinished" class="modal fade" role="dialog">
        <div class="modal-dialog">
            <!-- Modal content-->
            <div class="modal-content">
                <div class="modal-header">
                    <h4 class="modal-title">Edit finished</h4>
                </div>
                <form action="/issue/${issue.id}/edit-finished" method="post">
                    <div class="modal-body">
                        <input name="finished" type="date" value="${issue.getFinished()}" class="form-control" aria-label="Sizing example input"
                               aria-describedby="inputGroup-sizing-default">
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-default" data-dismiss="modal">Cancel</button>
                        <input type="submit" class="btn btn-default" style="background-color: orange">
                    </div>
                </form>
            </div>
        </div>
    </div>

    <div id="editStarted" class="modal fade" role="dialog">
        <div class="modal-dialog">
            <!-- Modal content-->
            <div class="modal-content">
                <div class="modal-header">
                    <h4 class="modal-title">Edit started</h4>
                </div>
                <form action="/issue/${issue.id}/edit-started" method="post">
                    <div class="modal-body">
                        <input name="started" type="date" value="${issue.getStarted()}" class="form-control" aria-label="Sizing example input"
                               aria-describedby="inputGroup-sizing-default">
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-default" data-dismiss="modal">Cancel</button>
                        <input type="submit" class="btn btn-default" style="background-color: orange">
                    </div>
                </form>
            </div>
        </div>
    </div>

</#macro>

<@display_page/>
