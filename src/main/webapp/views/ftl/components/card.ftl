<div fragment="copy">
    <div class="card" style="margin: auto; width: 18rem; margin-top: 5px;">
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


            <#if issue.issuePriority??>
                <strong>Priority:</strong>
                <p>
                    ${issue.issuePriority}
                </p>
            </#if>

            <#if issue.issueSeverity??>
                <strong>Severity:</strong>
                <p>
                    ${issue.issueSeverity}
                </p>
            </#if>

            <a href="/issue/${issue.id}" class="btn btn-primary float-right">Open</a>
        </div>
    </div>
</div>