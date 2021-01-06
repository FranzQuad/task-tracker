<div style="width: 60%;">
    <div class="container"
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
                            <p>${issue.getStarted()}</p>
                            <strong>End Date:</strong>
                            <p>${issue.getFinished()}</p>
                            <strong>Status:</strong>
                            <p>${issue.getIssueStatus()}</p>
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
                            <p>${issue.getStarted()}</p>
                            <strong>End Date:</strong>
                            <p>${issue.getFinished()}</p>
                            <strong>Status:</strong>
                            <p>${issue.getIssueStatus()}</p>
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
                            <p>${issue.getStarted()}</p>
                            <strong>End Date:</strong>
                            <p>${issue.getFinished()}</p>
                            <strong>Status:</strong>
                            <p>${issue.getIssueStatus()}</p>
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
                            <p>${issue.getStarted()}</p>
                            <strong>End Date:</strong>
                            <p>${issue.getFinished()}</p>
                            <strong>Status:</strong>
                            <p>${issue.getIssueStatus()}</p>
                            <a href="/issue/${issue.id}" class="btn btn-primary float-right">Open</a>
                        </div>
                    </div>
                </#foreach>
            </ul>
        </div>
    </div>
</div>