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
                Blocked
            </div>
            <div class="col-sm">
                Closed
            </div>
        </div>
    </div>
    <hr>
    <div class="row">
        <div class="col-sm">
            <#foreach issue in issues>
                <ul class="list-group list-group-flush">
                    <li class="card" style="margin-top: 5px;">
                        <div class="card-body">
                            <h5 class="card-title" style="text-align: center">${issue.name}</h5>
                            <div style="text-align: center;">
                                <a href="/task" class="btn btn-primary">Open</a>
                            </div>
                        </div>
                    </li>
                </ul>
            </#foreach>
        </div>
        <div class="col-sm">
            <#foreach issue in issues>
                <ul class="list-group list-group-flush">
                    <div class="card" style="margin-top: 5px;">
                        <div class="card-body">
                            <h5 class="card-title" style="text-align: center">${issue.name}</h5>
                            <div style="text-align: center;">
                                <a href="/task" class="btn btn-primary">Open</a>
                            </div>
                        </div>
                    </div>
                </ul>
            </#foreach>
        </div>
        <div class="col-sm">
            <#foreach issue in issues>
                <ul class="list-group list-group-flush">
                    <div class="card" style="margin-top: 5px;">
                        <div class="card-body">
                            <h5 class="card-title" style="text-align: center">${issue.name}</h5>
                            <div style="text-align: center;">
                                <a href="/task" class="btn btn-primary">Open</a>
                            </div>
                        </div>
                    </div>
                </ul>
            </#foreach>
        </div>
        <div class="col-sm">
            <#foreach issue in issues>
                <ul class="list-group list-group-flush">
                    <div class="card" style="margin-top: 5px;">
                        <div class="card-body">
                            <h5 class="card-title" utext="${issue.name}"></h5>
                            <strong>Description:</strong>
                            <p>${issue.getDescription()}</p>
                            <strong>Start Date:</strong>
                            <p>${issue.getStarted()}</p>
                            <strong>End Date:</strong>
                            <p>${issue.getFinished()}</p>
                            <strong>Status:</strong>
                            <p>${issue.getIssueStatus()}</p>
                            <a href="/task" class="btn btn-primary">Open</a>
                        </div>
                    </div>
                </ul>
            </#foreach>
        </div>
    </div>
</div>