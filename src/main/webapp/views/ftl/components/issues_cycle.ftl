<div fragment="copy">

    <ul class="list-group list-group-flush">
        <#foreach issue in issues>
            <div class="card" style="margin: auto; width: 18rem; margin-top: 5px;">
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
                    <a href="/issue/${issue.id}" class="btn btn-primary">Open</a>
                </div>
            </div>
        </#foreach>
    </ul>

</div>