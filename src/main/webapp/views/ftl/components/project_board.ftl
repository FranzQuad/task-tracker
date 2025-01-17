<div style="width: 100%;">
    <div class="container-fluid"
         style="width: 100%; text-align: center; font-weight: bold; margin-top: 10px; color: dimgrey;">
        <div class="row">
            <div class="col-sm">
                To Do
            </div>
            <div class="col-sm">
                In Progress
            </div>
            <div class="col-sm">
                Ready For Testing
            </div>
            <div class="col-sm">
                Completed
            </div>
            <div class="col-sm" style="color: red">
                Outdated
            </div>
        </div>
    </div>
    <hr>
    <div class="row">
        <div class="col" style="height: 80.5vh; overflow-y: scroll;">
            <ul class="list-group list-group-flush">
                <#foreach issue in todoissues>
                    <div class="card px-0" style="margin: auto; width: 90%; margin-top: 5px;">
                        <div class="card-body">
                            <h5 class="card-title">${issue.name}</h5>
                            <strong>Description:</strong>
                            <p>${issue.getDescription()}</p>
                            <strong>Start Date:</strong>
                            <p>
                                <#if issue.getStarted()??>
                                    ${issue.getStarted()}
                                <#else>
                                    <strong style="color: red">Not specified!</strong>
                                </#if>
                            </p>
                            <strong>Deadline:</strong>
                            <p>
                                <#if issue.deadline??>
                                    ${issue.deadline}
                                <#else>
                                    <strong style="color: red">Not specified!</strong>
                                </#if>
                            </p>
                            <strong>Status:</strong>
                            <p>${issue.getIssueStatus()}</p>
                            <strong>Priority:</strong>
                            <p>
                                <#if issue.issuePriority??>
                                    ${issue.issuePriority}
                                <#else>
                                    <strong style="color: red">Not specified!</strong>
                                </#if>
                            </p>

                            <strong>Severity:</strong>
                            <p>
                                <#if issue.issueSeverity??>
                                    ${issue.issueSeverity}
                                <#else>
                                    <strong style="color: red">Not specified!</strong>
                                </#if>
                            </p>
                            <a href="/issue/${issue.id}" class="btn btn-primary float-right">Open</a>
                        </div>
                    </div>
                </#foreach>
            </ul>
        </div>
        <div class="col" style="height: 80.5vh; overflow-y: scroll;">
            <ul class="list-group list-group-flush">
                <#foreach issue in inprogissues>
                    <div class="card px-0" style="margin: auto; width: 90%; margin-top: 5px;">
                        <div class="card-body">
                            <h5 class="card-title">${issue.name}</h5>
                            <strong>Description:</strong>
                            <p>${issue.getDescription()}</p>
                            <strong>Start Date:</strong>
                            <p>
                                <#if issue.getStarted()??>
                                    ${issue.getStarted()}
                                <#else>
                                    <strong style="color: red">Not specified!</strong>
                                </#if>
                            </p>
                            <strong>Deadline:</strong>
                            <p>
                                <#if issue.deadline??>
                                    ${issue.deadline}
                                <#else>
                                    <strong style="color: red">Not specified!</strong>
                                </#if>
                            </p>
                            <strong>Status:</strong>
                            <p>${issue.getIssueStatus()}</p>
                            <strong>Priority:</strong>
                            <p>
                                <#if issue.issuePriority??>
                                    ${issue.issuePriority}
                                <#else>
                                    <strong style="color: red">Not specified!</strong>
                                </#if>
                            </p>

                            <strong>Severity:</strong>
                            <p>
                                <#if issue.issueSeverity??>
                                    ${issue.issueSeverity}
                                <#else>
                                    <strong style="color: red">Not specified!</strong>
                                </#if>
                            </p>
                            <a href="/issue/${issue.id}" class="btn btn-primary float-right">Open</a>
                        </div>
                    </div>
                </#foreach>
            </ul>
        </div>
        <div class="col" style="height: 80.5vh; overflow-y: scroll;">
            <ul class="list-group list-group-flush">
                <#foreach issue in readyfortestingissues>
                    <div class="card px-0" style="margin: auto; width: 90%; margin-top: 5px;">
                        <div class="card-body">
                            <h5 class="card-title">${issue.name}</h5>
                            <strong>Description:</strong>
                            <p>${issue.getDescription()}</p>
                            <strong>Start Date:</strong>
                            <p>
                                <#if issue.getStarted()??>
                                    ${issue.getStarted()}
                                <#else>
                                    <strong style="color: red">Not specified!</strong>
                                </#if>
                            </p>
                            <strong>Deadline:</strong>
                            <p>
                                <#if issue.deadline??>
                                    ${issue.deadline}
                                <#else>
                                    <strong style="color: red">Not specified!</strong>
                                </#if>
                            </p>
                            <strong>Status:</strong>
                            <p>${issue.getIssueStatus()}</p>
                            <strong>Priority:</strong>
                            <p>
                                <#if issue.issuePriority??>
                                    ${issue.issuePriority}
                                <#else>
                                    <strong style="color: red">Not specified!</strong>
                                </#if>
                            </p>

                            <strong>Severity:</strong>
                            <p>
                                <#if issue.issueSeverity??>
                                    ${issue.issueSeverity}
                                <#else>
                                    <strong style="color: red">Not specified!</strong>
                                </#if>
                            </p>
                            <a href="/issue/${issue.id}" class="btn btn-primary float-right">Open</a>
                        </div>
                    </div>
                </#foreach>
            </ul>
        </div>
        <div class="col" style="height: 80.5vh; overflow-y: scroll;">
            <ul class="list-group list-group-flush">
                <#foreach issue in completeIssues>
                    <div class="card px-0" style="margin: auto; width: 90%; margin-top: 5px;">
                        <div class="card-body">
                            <h5 class="card-title">${issue.name}</h5>
                            <strong>Description:</strong>
                            <p>${issue.getDescription()}</p>
                            <strong>Start Date:</strong>
                            <p>
                                <#if issue.getStarted()??>
                                    ${issue.getStarted()}
                                <#else>
                                    <strong style="color: red">Not specified!</strong>
                                </#if>
                            </p>
                            <strong>Deadline:</strong>
                            <p>
                                <#if issue.deadline??>
                                    ${issue.deadline}
                                <#else>
                                    <strong style="color: red">Not specified!</strong>
                                </#if>
                            </p>
                            <strong>Status:</strong>
                            <p>${issue.getIssueStatus()}</p>
                            <strong>Priority:</strong>
                            <p>
                                <#if issue.issuePriority??>
                                    ${issue.issuePriority}
                                <#else>
                                    <strong style="color: red">Not specified!</strong>
                                </#if>
                            </p>

                            <strong>Severity:</strong>
                            <p>
                                <#if issue.issueSeverity??>
                                    ${issue.issueSeverity}
                                <#else>
                                    <strong style="color: red">Not specified!</strong>
                                </#if>
                            </p>
                            <a href="/issue/${issue.id}" class="btn btn-primary float-right">Open</a>
                        </div>
                    </div>
                </#foreach>
            </ul>
        </div>
        <div class="col" style="height: 80.5vh; overflow-y: scroll;">
            <ul class="list-group list-group-flush">
                <#foreach issue in outdatedIssues>
                    <div class="card px-0" style="margin: auto; width: 90%; margin-top: 5px;">
                        <div class="card-body">
                            <h5 class="card-title">${issue.name} <strong style="color: red">Outdated</strong></h5>
                            <strong>Description:</strong>
                            <p>${issue.getDescription()}</p>
                            <strong>Start Date:</strong>
                            <p>
                                <#if issue.getStarted()??>
                                    ${issue.getStarted()}
                                <#else>
                                    <strong style="color: red">Not specified!</strong>
                                </#if>
                            </p>
                            <strong>Deadline:</strong>
                            <p>
                                <#if issue.deadline??>
                                    ${issue.deadline}
                                <#else>
                                    <strong style="color: red">Not specified!</strong>
                                </#if>
                            </p>
                            <strong>Status:</strong>
                            <p>${issue.getIssueStatus()}</p>
                            <strong>Priority:</strong>
                            <p>
                                <#if issue.issuePriority??>
                                    ${issue.issuePriority}
                                <#else>
                                    <strong style="color: red">Not specified!</strong>
                                </#if>
                            </p>

                            <strong>Severity:</strong>
                            <p>
                                <#if issue.issueSeverity??>
                                    ${issue.issueSeverity}
                                <#else>
                                    <strong style="color: red">Not specified!</strong>
                                </#if>
                            </p>
                            <a href="/issue/${issue.id}" class="btn btn-primary float-right">Open</a>
                        </div>
                    </div>
                </#foreach>
            </ul>
        </div>
    </div>
</div>